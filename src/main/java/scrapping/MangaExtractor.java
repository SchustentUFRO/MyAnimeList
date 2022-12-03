package scrapping;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import err.ExcepcionDeConexion;
import err.MalFormatoURL;
import scrapping.Media.Preview.MangaPreviewTop;

import java.io.IOException;
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
        searchCat="&cat=manga";
        numeroPaginaEnTop=1;
        topURL+="topmanga.php";
        searchType="manga.php?q=";
        categoriasList=Arrays.asList("Manga","Light Novel","Novel","Manhwa","Manhua","Doujinshi","One-shot");
    }



    public void startCollectFromTop(){
        try{
            collectFromTop();
        }
        catch (ExcepcionDeConexion ioEx){
            System.out.println(ioEx);
        }
        catch (MalFormatoURL urlEx){
            System.out.println(urlEx);
        }
    }

    public void startCollectFromTop(String targetURL){
        try{
            collectFromTop(targetURL);
        }
        catch (ExcepcionDeConexion ioEx){
            System.out.println(ioEx);
        }
        catch (MalFormatoURL urlEx){
            System.out.println(urlEx);
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
            startCollectFromTop(urlObjetivo);
        });
    }



}
