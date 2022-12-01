package scrapping;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import scrapping.Media.Preview.MangaPreviewTop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class MangaExtractor extends Extractor{

    List<HtmlElement> emissionDataFromTop;
    protected String topURL=baseSearchUrl+"topmanga.php";

    public MangaExtractor() {
        typeOfMediaUrl+="manga/";
        anchorXpathRef=MangaXpaths.relHrefToMangaInTop.xpath;
    }

    public void collectFromTop(){
        setupTopPage(topURL);
        extractTopTags();
        getAnchors();

        //client.close();
    }
    public void collectFromTop(String targetURL){
        setupTopPage(targetURL);
        extractTopTags();
        getAnchors();

        //client.close();
    }

    public List<MangaPreviewTop> formarPreviewsPagTop(){
        List<MangaPreviewTop> tempPreviewsList=new ArrayList<>();
        topRowsOfMedia.stream().forEach(mangaRow ->tempPreviewsList.add(formarRecordPreview(mangaRow)));
        return tempPreviewsList;
    }

    public MangaPreviewTop formarRecordPreview(HtmlElement mangaRow){
        String urlManga =getHrefFromAnchor(mangaRow);

        return new MangaPreviewTop(obtenerID(urlManga), obtenerNombreMangaPreview(mangaRow), obtenerCategoriaManga(mangaRow),obtenerNumeroRank(mangaRow),obtenerNumeroPuntos(mangaRow), urlManga);
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


    public String definirCategoria(String datosEmision){ //saca el top
        List<String> categoriasList= Arrays.asList("Manga","Light Novel","Novel","Manhwa","Manhua","Doujinshi","One-shot");
        return categoriasList.stream()
                .filter(categoriaIndiv->datosEmision.contains(categoriaIndiv))
                .findFirst()
                .get();
    }


    public int obtenerNumeroRank(HtmlElement mangaPreview){
        List<HtmlElement> templist=new ArrayList<>(mangaPreview.getByXPath(MangaXpaths.relRankingNumberMangaInTop.xpath));
        return Integer.parseInt(templist.get(0).getVisibleText());
    }

    public double obtenerNumeroPuntos(HtmlElement mangaPreview){
        List<HtmlElement> templist=new ArrayList<>(mangaPreview.getByXPath(MangaXpaths.relScoreNumberMangaInTop.xpath));
        return Double.parseDouble(templist.get(0).getVisibleText());
    }


    public void extraerVariasPaginasTop(int numPaginas){
        IntStream.range(1,numPaginas+1).forEach(pageNumber->{
            String urlObjetivo=baseSearchUrl+convertirPaginaTopAUrl(pageNumber);
            collectFromTop(urlObjetivo);
        });
    }
    public String convertirPaginaTopAUrl(int paginas){
        //String numeroPag=paginas==1?"":"?limit="+((paginas-1)*50);
        return paginas==1?"":"?limit="+((paginas-1)*50);
    }



}
