package scrapping;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class Extractor {
    String xpathTitleRowsInTop,xpathTitleLinkFromTop;


    String anchorXpathRef,typeOfMediaUrl;
    WebClient client;
    HtmlPage topPage;
    HtmlPage articlePage;

    HtmlImage previewImage;

    String baseSearchUrl="https://myanimelist.net/";

    List<HtmlElement> topRowsOfMedia;
    List<String> articlesURLs;
    List<HtmlElement> rawInformationElements, usableInformationElements;
    List<HtmlElement> articleTags;



    public Extractor() {
        typeOfMediaUrl=baseSearchUrl;
        articlesURLs =new ArrayList<>();
        client=new WebClient(BrowserVersion.FIREFOX_ESR);
        //evitar errores/advertencias raras.
        //client.getOptions().setThrowExceptionOnScriptError(false);
        //client.getOptions().setPrintContentOnFailingStatusCode(false);
        //client.getOptions().setThrowExceptionOnScriptError(false);



        client.getOptions().setUseInsecureSSL(true);
        client.getOptions().setCssEnabled(false);
        client.getOptions().setJavaScriptEnabled(false);
    }

    public HtmlPage setupPage(String targetURL){
        try {
            return client.getPage(targetURL);
        } catch (IOException e) {
            System.out.println("something bad");
            return null;

        }
    }
    public void setupTopPage(String targetURL){
        topPage=setupPage(targetURL);
    }
    public void setupArticlePage(String targetURL){
        articlePage=setupPage(targetURL);
    }

    public void extractDataFromArticle(String articleURL){
        setupArticlePage(articleURL);
        extractArticleBody(articlePage);
    }

    public void extractArticleBody(HtmlPage article){
        articleTags=new ArrayList<>(article.getByXPath(AnimeXpaths.animeDetailsBase.xpath));

    }

    public void extractTopTags(){
        //igual para top manga y anime
        topRowsOfMedia =new ArrayList<>(topPage.getByXPath(AnimeXpaths.mediaRowInTop.xpath));
    }

    public void getTags(){

        System.out.println(topRowsOfMedia);
    }

    public void getAnchors(){
        for (HtmlElement tag:
                topRowsOfMedia) {
            HtmlAnchor anchor= extractAnchorFromTitleInTop(tag);
            addToArticlesArray(anchor.getHrefAttribute());
        }
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






}