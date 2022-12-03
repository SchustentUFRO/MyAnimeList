package scrapping;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import err.ExcepcionDeConexion;
import err.MalFormatoURL;
import org.apache.commons.lang3.ObjectUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Extractor {
    String topURL,pageTopURL,searchType;


    String anchorXpathRef,searchCat,searchRowXpath,searchElementNameXpath,SearchElementTypeXpath,
    searchElementScoreXpath;
    WebClient client;
    HtmlPage topPage,searchPage;
    HtmlPage articlePage;

    HtmlImage previewImage;

    int numeroPaginaEnTop;
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

    public HtmlPage setupPage(String targetURL) throws ExcepcionDeConexion, MalFormatoURL {
            try{
                return client.getPage(targetURL);
            }
            catch (MalformedURLException malURL){
                throw new MalFormatoURL("Formato de URL incorrecto");
            }
            catch (IOException iox){
                throw new ExcepcionDeConexion("Sin conexion a internet o página no disponible");
            }
    }
    public void setupTopPage(String targetURL) throws ExcepcionDeConexion, MalFormatoURL{
        HtmlPage tempPage=setupPage(targetURL);
        if (tempPage==null){
            System.out.println("No se pudo crear pagina");
        }
        else {
            topPage=tempPage;
        }
    }
    public void setupArticlePage(String targetURL) throws ExcepcionDeConexion, MalFormatoURL{
        HtmlPage tempPage=setupPage(targetURL);
        if (tempPage==null){
            System.out.println("No se pudo crear pagina");
        }
        else {
            articlePage=tempPage;
        }
    }

    public void extractDataFromArticle(String articleURL){
        try {
            setupArticlePage(articleURL);
            extractArticleBody(articlePage);
        }
        catch (ExcepcionDeConexion ioEx){
            System.out.println(ioEx);
        }
        catch (MalFormatoURL urlEx){
            System.out.println(urlEx);
        }
        catch (NullPointerException nullp){
            System.out.println("Error: página no inicializada");
        }
    }

    public void extractArticleBody(HtmlPage article) throws NullPointerException{
        articleTags=article.getFirstByXPath(AnimeXpaths.animeDetailsBase.xpath);

    }

    public void extractTopTags() throws NullPointerException {
        //igual para top manga y anime
        topRowsOfMedia =new ArrayList<>(topPage.getByXPath(AnimeXpaths.mediaRowInTop.xpath));
    }

    public void getTags(){

        System.out.println(topRowsOfMedia);
    }

    public void getAnchors(){
        /*for (HtmlElement tag:
                topRowsOfMedia) {
            HtmlAnchor anchor= extractAnchorFromTitleInTop(tag);
            addToArticlesArray(anchor.getHrefAttribute());
        }*/
        topRowsOfMedia.stream().forEach(rowInTop->
        {
            HtmlAnchor anchor=extractAnchorFromTitleInTop(rowInTop);
            addToArticlesArray(anchor.getHrefAttribute());
        });
    }


    public HtmlAnchor extractAnchorFromTitleInTop(HtmlElement element){
        HtmlAnchor anchor=((HtmlAnchor) element.getFirstByXPath(anchorXpathRef));
        return anchor;
    }

    public String getHrefFromAnchor(HtmlElement anchor){
        return extractAnchorFromTitleInTop(anchor).getHrefAttribute();
    }



    public void addToArticlesArray(String targetURL){
        articlesURLs.add(targetURL);
    }

    public int obtenerID(String url){
        String[] descartarURLbase=url.split(typeOfMediaUrl,2);
        String[] idYnombre=descartarURLbase[1].split("/",2);
        return Integer.parseInt(idYnombre[0]);
    }

    public String definirCategoria(String datosEmision){ //saca el top
        String preliminaryResult=categoriasList.stream()
                .filter(categoriaIndiv->datosEmision.contains(categoriaIndiv)).reduce("", String::concat);
        return preliminaryResult.equals("")?"Other":preliminaryResult;
    }

    public void regresararPaginaTop(){
        if (numeroPaginaEnTop>1) {
            numeroPaginaEnTop--;
            pageTopURL = topURL + convertirPaginaTopAUrl(numeroPaginaEnTop);
        }
        else {
            System.err.println("Intentando acceder a página menor a 1");
        }
    }

    public void avanzarPaginaTop(){
        numeroPaginaEnTop++;
        pageTopURL=topURL+convertirPaginaTopAUrl(numeroPaginaEnTop);

    }

    public String convertirPaginaTopAUrl(int paginas){
        //String numeroPag=paginas==1?"":"?limit="+((paginas-1)*50);
        return paginas==1?"":"?limit="+((paginas-1)*50);
    }

    //busquedas
    public void createSearchURL(String searchTerm){
        searchURL=baseSearchUrl+searchType+searchTerm+searchCat;
        System.out.println(searchURL);
    }

    public void realizarBusqueda(){
        prepararPaginaBusqueda();
        obtenerFilasBusqueda();
    }


    private void prepararPaginaBusqueda(){
        try {
            searchPage = setupPage(searchURL);
        }
        catch (ExcepcionDeConexion ioEx){
            System.out.println(ioEx);
        }
        catch (MalFormatoURL urlEx){
            System.out.println(urlEx);
        }
    }
    private void prepararPaginaBusqueda(String urlBusqueda) throws ExcepcionDeConexion,MalFormatoURL{
        //try{
            searchPage=setupPage(urlBusqueda);
        //}
        /*catch (ExcepcionDeConexion conx){
            System.out.println(conx);
        }
        catch (MalformedURLException badURL){
            System.out.println(badURL);
        }*/

    }

    public void obtenerFilasBusqueda(){
        List<HtmlElement> tempSearchRowsOfMedia=new ArrayList<>(searchPage.getByXPath(searchRowXpath));
        searchRowsOfMedia=tempSearchRowsOfMedia.subList(1,tempSearchRowsOfMedia.size());
    }










}