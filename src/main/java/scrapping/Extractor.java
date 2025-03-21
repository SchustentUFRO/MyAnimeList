package scrapping;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import err.ExcepcionDeConexion;
import err.ExcepcionMalFormatoURL;
import err.ExcepcionPaginaNoPreparada;
import scrapping.Media.Preview.Preview;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public abstract class Extractor {
    String topURL,pageTopURL,searchType;


    String anchorXpathRef,searchCat,searchRowXpath,searchElementNameXpath,SearchElementTypeXpath,
    searchElementScoreXpath;
    WebClient client;
    HtmlPage topPage,searchPage;
    HtmlPage articlePage;

    HtmlImage previewImage;

    int numeroPaginaEnTop,rankPosFromDetails;
    String baseSearchUrl="https://myanimelist.net/",searchURL,typeOfMediaUrl;

    List<HtmlElement> topRowsOfMedia,searchRowsOfMedia;
    List<String> articlesURLs,categoriasList;
    List<HtmlElement> rawInformationElements, usableInformationElements;
    HtmlElement articleTags;



    public Extractor() {
        topURL=baseSearchUrl;
        typeOfMediaUrl=baseSearchUrl;
        articlesURLs =new ArrayList<>();
        client=new WebClient(BrowserVersion.FIREFOX_ESR);



        //client.getOptions().setUseInsecureSSL(true);
        client.getOptions().setCssEnabled(false);
        client.getOptions().setJavaScriptEnabled(false);
    }

    public HtmlPage setupPage(String targetURL) throws ExcepcionDeConexion, ExcepcionMalFormatoURL {
            try{
                return client.getPage(targetURL);
            }
            catch (MalformedURLException malURL){
                throw new ExcepcionMalFormatoURL();
            }
            catch (IOException iox){
                throw new ExcepcionDeConexion();
            }
    }
    void setupTopPage(String targetURL) throws ExcepcionDeConexion, ExcepcionMalFormatoURL {
        HtmlPage tempPage=setupPage(targetURL);
        if (tempPage==null){
            System.out.println("No se pudo crear pagina");
        }
        else {
            topPage=tempPage;
        }
    }
    private void setupArticlePage(String targetURL) throws ExcepcionDeConexion, ExcepcionMalFormatoURL {
            HtmlPage tempPage = setupPage(targetURL);
            articlePage=tempPage;
    }



    void usePreviewToCreateDetailsArticle(Preview preview) throws ExcepcionDeConexion, ExcepcionMalFormatoURL, ExcepcionPaginaNoPreparada {
        try {
            extractDataFromArticle(preview.getLink());
        }
        catch (NullPointerException nullp){
            throw new ExcepcionPaginaNoPreparada();
        }
    }


    void testsExtractDataFromArticle(String articleURL){
        try{
            extractDataFromArticle(articleURL);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    private void extractDataFromArticle(String articleURL) throws ExcepcionDeConexion, ExcepcionMalFormatoURL, ExcepcionPaginaNoPreparada {
            setupArticlePage(articleURL);
            extractArticleBody(articlePage);
    }

    private void extractArticleBody(HtmlPage article) throws ExcepcionPaginaNoPreparada{
        try {
            articleTags = article.getFirstByXPath(AnimeXpaths.animeDetailsBase.xpath);
        }
        catch (NullPointerException nullp){
            throw new ExcepcionPaginaNoPreparada();
        }
    }

    void extractTopTags() throws NullPointerException {
        //igual para top manga y anime
        topRowsOfMedia =new ArrayList<>(topPage.getByXPath(AnimeXpaths.mediaRowInTop.xpath));
    }

    private void getTags(){

        System.out.println(topRowsOfMedia);
    }

    void getAnchors(){
        topRowsOfMedia.stream()
                .forEach(rowInTop->
                {
                    HtmlAnchor anchor=extractAnchorFromTitleInTop(rowInTop);
                    addToArticlesArray(anchor.getHrefAttribute());
                });
    }


    HtmlAnchor extractAnchorFromTitleInTop(HtmlElement element){
        HtmlAnchor anchor=((HtmlAnchor) element.getFirstByXPath(anchorXpathRef));
        return anchor;
    }

    String getHrefFromAnchor(HtmlElement anchor){
        return extractAnchorFromTitleInTop(anchor).getHrefAttribute();
    }



    void addToArticlesArray(String targetURL){
        articlesURLs.add(targetURL);
    }

    int obtenerID(String url){
        String[] descartarURLbase=url.split(typeOfMediaUrl,2);
        String[] idYnombre=descartarURLbase[1].split("/",2);
        return Integer.parseInt(idYnombre[0]);
    }

    String definirCategoria(String datosEmision){ //saca el top
        String preliminaryResult=categoriasList.stream()
                    .filter(categoriaIndiv->datosEmision.contains(categoriaIndiv))
                        .reduce("", String::concat);
        return preliminaryResult.equals("")?"Other":preliminaryResult;
    }

    void regresarPaginaTop(){
        if (numeroPaginaEnTop>1) {
            numeroPaginaEnTop--;
            pageTopURL = topURL + convertirPaginaTopAUrl(numeroPaginaEnTop);
        }
        else {
            System.err.println("Intentando acceder a página menor a 1");
        }
    }

    void avanzarPaginaTop(){
        numeroPaginaEnTop++;
        pageTopURL=topURL+convertirPaginaTopAUrl(numeroPaginaEnTop);

    }

    String convertirPaginaTopAUrl(int paginas){
        //String numeroPag=paginas==1?"":"?limit="+((paginas-1)*50);
        return paginas==1?"":"?limit="+((paginas-1)*50);
    }

    //busquedas
    void createSearchURL(String searchTerm){
        searchURL=baseSearchUrl+searchType+searchTerm+searchCat;
        System.out.println(searchURL);
    }

    void realizarBusqueda() throws ExcepcionDeConexion, ExcepcionMalFormatoURL {
        prepararPaginaBusqueda();
        obtenerFilasBusqueda();
    }


    void prepararPaginaBusqueda() throws ExcepcionDeConexion, ExcepcionMalFormatoURL {
            searchPage = setupPage(searchURL);
    }
    void prepararPaginaBusqueda(String urlBusqueda) throws ExcepcionDeConexion, ExcepcionMalFormatoURL {
            searchPage=setupPage(urlBusqueda);

    }

    void obtenerFilasBusqueda(){
        try {
            List<HtmlElement> tempSearchRowsOfMedia = new ArrayList<>(searchPage.getByXPath(searchRowXpath));
            searchRowsOfMedia = tempSearchRowsOfMedia.subList(1, tempSearchRowsOfMedia.size());
        }
        catch (NullPointerException nullp){
            System.out.println("Página no preparada");
        }
    }

}