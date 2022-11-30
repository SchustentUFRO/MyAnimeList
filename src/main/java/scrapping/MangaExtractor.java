package scrapping;

import java.util.stream.IntStream;

public class MangaExtractor extends Extractor{
    protected String topURL=baseSearchUrl+"topmanga.php";

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
