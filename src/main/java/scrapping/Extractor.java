package scrapping;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class Extractor {
    String xpathTitleRowsInTop,xpathTitleLinkFromTop;


    String mediaRankXpath="div[1]/div[2]/div[3]/div[2]/table/tbody/tr/td[2]/div[1]/table/tbody/tr[1]/td/div[1]/div[1]/div[1]/div[1]/div[2]/span[1]/strong";
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
        HtmlAnchor anchor=((HtmlAnchor) element.getFirstByXPath(AnimeXpaths.relHrefToAnimeInTop.xpath));
        return anchor;
    }

    public String getHrefFromAnchor(HtmlElement anchor){
        return extractAnchorFromTitleInTop(anchor).getHrefAttribute();
    }



    public void addToArticlesArray(String targetURL){
        articlesURLs.add(targetURL);
    }






}