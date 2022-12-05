package scrapping;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import err.ExcepcionDeConexion;
import err.MalFormatoURL;
import scrapping.Media.DetailedMedia.MangaMedia;
import scrapping.Media.Preview.MangaPreview;

import java.util.*;

public class MangaExtractor extends Extractor{

    List<HtmlElement> emissionDataFromTop;
    protected String topURL=baseSearchUrl+"topmanga.php";
    public List<MangaPreview> mangaSearchPreview,mangaTopPreview;
    public List<MangaMedia> mangaMediaList;

    public MangaExtractor() {
        typeOfMediaUrl+="manga/";
        anchorXpathRef=MangaXpaths.relHrefToMangaInTop.xpath;
        searchCat="&cat=manga";
        numeroPaginaEnTop=1;
        topURL+="topmanga.php";
        searchType="manga.php?q=";
        mangaMediaList=new ArrayList<>();
        categoriasList=Arrays.asList("Manga","Light Novel","Novel","Manhwa","Manhua","Doujinshi","One-shot");
    }

    public void iniciarScrapper(){
        try {
            collectFromTopAndFormPreviews();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void paginaTopAdelante(){
        try {
            avanzarPaginaTop();
            collectFromTopAndFormPreviews(pageTopURL);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void paginaTopAtras(){
        try {
            if (numeroPaginaEnTop!=1){
                regresarPaginaTop();
                collectFromTopAndFormPreviews(pageTopURL);
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void seleccionarPreviewTopParaMostrarDetalles(MangaPreview preview){
        try {
            formarDetallesMangaDeTop(preview);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    private void formarDetallesMangaDeTop(MangaPreview preview) throws ExcepcionDeConexion,MalFormatoURL{
        usePreviewToCreateDetailsArticle(preview);
        MangaMedia nuevoDetalles =crearMangaDetalles(preview);
        obtenerInformacionImportanteManga();
        obtenerDetallesDeEmision(nuevoDetalles);
        mangaMediaList.add(nuevoDetalles);

    }

    public void seleccionarPreviewSearchParaMostrarDetalles(MangaPreview preview){
        try {
            formarDetallesMangaDeBusqueda(preview);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    private void formarDetallesMangaDeBusqueda(MangaPreview previewBusqueda) throws ExcepcionDeConexion,MalFormatoURL{
        usePreviewToCreateDetailsArticle(previewBusqueda);
        MangaMedia nuevoDetalles=crearMangaDetalles(previewBusqueda);
        obtenerInformacionImportanteManga();
        obtenerDetallesDeEmision(nuevoDetalles);
        mangaMediaList.add(nuevoDetalles);
    }

    public void obtenerInformacionImportanteManga(){
        getAnimeImportantInformationRaw();
        formarListaInfoImportante();
    }


    public void getAnimeImportantInformationRaw(){
        rawInformationElements =articleTags.getByXPath(AnimeXpaths.relAnimeDetailsGeneralInfo.xpath);
    }

    public void obtenerDetallesDeEmision(MangaMedia objetivo){
        usableInformationElements.stream().forEach(infoRow->{
            HtmlElement tempElem=infoRow;
            try{
                List<HtmlElement>tempDeleteList=infoRow.getByXPath("span[@style=\"display: none\"]");
                tempDeleteList.stream().forEach(deleteElement->
                {
                    tempElem.removeChild(deleteElement);
                });
            }
            catch (Exception e){
                System.out.println("no contiene nodo invisible");
            }
            String[] infoPair=infoRow.asNormalizedText().split(":",0);
            agregarAHashMapManga(objetivo,infoPair);

        });

    }

    public void formarListaInfoImportante(){
        int inicio,fin;
        inicio=buscarPosicionInicioInformacionRelevante()+1;
        fin=buscarPosicionFinInformacionRelevante()-1; //exclusive y evitar incluir el <br>
        usableInformationElements = rawInformationElements.subList(inicio,fin);
    }


    public int buscarPosicionInicioInformacionRelevante(){
        System.out.println(rawInformationElements.size());
        HtmlElement elementoBuscado =(HtmlElement) articleTags.getByXPath(AnimeXpaths.relAnimeImportantGeneralInfoDetails.xpath).get(0);
        System.out.println(rawInformationElements);

        return rawInformationElements.indexOf(elementoBuscado);
    }

    public int buscarPosicionFinInformacionRelevante(){
        HtmlElement elementoBuscado =(HtmlElement) articleTags.getByXPath(AnimeXpaths.relAnimeEndofImportantGeneralInfoDetails.xpath).get(0);
        return rawInformationElements.indexOf(elementoBuscado);
    }

    public void agregarAHashMapManga(MangaMedia manga, String[] detallesSeparados){
        try {
            manga.agregarInfoEmision(detallesSeparados[0],detallesSeparados[1]);
        }
        catch (NullPointerException exception){
            System.err.println("No existe un par en "+Arrays.toString(detallesSeparados));
        }
    }



    public Map<String,String> ponerInfoImportanteEnMaps(){
        Map<String,String> importantInfoPairs=new HashMap<>();
        usableInformationElements.stream().forEach(importantInfoRow->
        {
            String[] separatedImportantInfo =importantInfoRow.asNormalizedText().split(":",2);
            importantInfoPairs.put(separatedImportantInfo[0], separatedImportantInfo[1]);

        });
        return importantInfoPairs;
    }

    public void collectFromTopAndFormPreviews() throws ExcepcionDeConexion,MalFormatoURL{
        collectFromTop();
        mangaTopPreview=new ArrayList<>(formarPreviewsPagTop());
    }
    public void collectFromTopAndFormPreviews(String url) throws ExcepcionDeConexion,MalFormatoURL{
        collectFromTop(url);
        mangaTopPreview=new ArrayList<>(formarPreviewsPagTop());
    }

    public void collectFromSearchAndFormPreviews(String searchTerm){
        try{
            createSearchURL(searchTerm);
            realizarBusqueda();
            mangaSearchPreview=new ArrayList<>(pasarTodasFilasBusquedaApreview());
        }
        catch (ExcepcionDeConexion excCon){
            System.out.println(excCon);
        }
        catch(MalFormatoURL malURL){
            System.out.println(malURL);
        }
    }


    public List<MangaPreview> pasarTodasFilasBusquedaApreview(){
        List<MangaPreview> searchPreviewList = new ArrayList<>();
        searchRowsOfMedia.stream().forEach(searchRow -> searchPreviewList.add(pasarFilaSearchAPreview(searchRow)));
        System.out.println(searchPreviewList);
        return searchPreviewList;
    }

    public MangaPreview pasarFilaSearchAPreview(HtmlElement filaBusqueda){
        String url=obtenerLinkBusqueda(filaBusqueda);
        int id=obtenerID(url);
        String tipoEmision=obtenerTipoEmisionBusqueda(filaBusqueda);
        String nombre=obtenerNombreBusqueda(filaBusqueda);
        double puntuacion=obtenerPuntajeBusqueda(filaBusqueda);
        return new MangaPreview(id,nombre,tipoEmision,puntuacion,url);

    }


    public String obtenerNombreBusqueda(HtmlElement filaBusqueda){
        String tempNombre=((HtmlElement) filaBusqueda.getFirstByXPath(AnimeXpaths.relAnimeSearchTitle.xpath)).asNormalizedText();
        return tempNombre;
    }

    public String obtenerLinkBusqueda(HtmlElement filaBusqueda){

        return ((HtmlAnchor) filaBusqueda.getFirstByXPath(AnimeXpaths.relAnimeSearchHref.xpath)).getHrefAttribute();
    }


    public double obtenerPuntajeBusqueda(HtmlElement filaBusqueda){
        String puntajeString=((HtmlElement) filaBusqueda.getFirstByXPath(AnimeXpaths.relAnimeSearchScore.xpath)).asNormalizedText();
        return puntajeString.equals("N/A")?0.0:Double.parseDouble(puntajeString);
    }

    public String obtenerTipoEmisionBusqueda(HtmlElement filaBusqueda){
        return ((HtmlElement) filaBusqueda.getFirstByXPath(AnimeXpaths.relAnimeSearchEmissionType.xpath)).asNormalizedText();
    }


    private MangaMedia crearMangaDetalles(MangaPreview preview){
        MangaMedia detalleMangaSimple = new MangaMedia(preview);
        if (detalleMangaSimple.getPosicionRanking()!=0){
            return detalleMangaSimple;
        } else {
            return new MangaMedia(preview,rankPosFromDetails);

        }
    }







    private void collectFromTop() throws ExcepcionDeConexion,MalFormatoURL {
        setupTopPage(topURL);
        extractTopTags();
        getAnchors();

        //client.close();
    }
    private void collectFromTop(String targetURL) throws ExcepcionDeConexion,MalFormatoURL{
        setupTopPage(targetURL);
        extractTopTags();
        getAnchors();

        //client.close();
    }

    public List<MangaPreview> formarPreviewsPagTop(){
        List<MangaPreview> tempPreviewsList=new ArrayList<>();
        topRowsOfMedia.stream().forEach(mangaRow ->tempPreviewsList.add(formarRecordPreview(mangaRow)));
        return tempPreviewsList;
    }

    public MangaPreview formarRecordPreview(HtmlElement mangaRow){
        String urlManga =getHrefFromAnchor(mangaRow);

        return new MangaPreview(obtenerID(urlManga), obtenerNombreMangaPreview(mangaRow), obtenerCategoriaManga(mangaRow),obtenerNumeroRank(mangaRow),obtenerNumeroPuntos(mangaRow), urlManga);
    }



    public String obtenerNombreMangaPreview(HtmlElement mangaPreview){
        return ((HtmlElement) mangaPreview.getFirstByXPath(MangaXpaths.relTitleMangaInTop.xpath)).getVisibleText();
    }


    public String obtenerCategoriaManga(HtmlElement elementoInteres){
        getMangaFormatDetailsFromTop(elementoInteres);
        String datosEmision=emissionDataFromTop.get(0).getVisibleText();
        return definirCategoria(datosEmision);
    }


    public void getMangaFormatDetailsFromTop(HtmlElement elementoInteres){
        emissionDataFromTop=new ArrayList<>(elementoInteres.getByXPath(MangaXpaths.relFormatDetailsMangaInTop.xpath));
    }




    public int obtenerNumeroRank(HtmlElement mangaPreview){
        List<HtmlElement> templist=new ArrayList<>(mangaPreview.getByXPath(MangaXpaths.relRankingNumberMangaInTop.xpath));
        return Integer.parseInt(templist.get(0).getVisibleText());
    }

    public double obtenerNumeroPuntos(HtmlElement mangaPreview){
        List<HtmlElement> templist=new ArrayList<>(mangaPreview.getByXPath(MangaXpaths.relScoreNumberMangaInTop.xpath));
        return Double.parseDouble(templist.get(0).getVisibleText());
    }





}
