package scrapping;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AnimeExtractorTest {


    AnimeExtractor animuExtractor;

    @BeforeEach
    void setUp() {
        animuExtractor = new AnimeExtractor();
    }

    @AfterEach
    void tearDown() {
        animuExtractor.client.close();
        animuExtractor =null;
    }

    @ParameterizedTest
    @ValueSource(strings = {"https://myanimelist.net/anime/37991/JoJo_no_Kimyou_na_Bouken_Part_5__Ougon_no_Kaze?q=jojo&cat=anime","https://myanimelist.net/anime/33010/FLCL_Progressive?q=flcl%20prog&cat=anime","https://myanimelist.net/anime/5114/Fullmetal_Alchemist__Brotherhood"} )
    void extraerDatosObrasRelacionadas(String link) {
        animuExtractor.extractDataFromArticle(link);
        System.out.println(animuExtractor.extraerDatosObrasRelacionadas(animuExtractor.articleTags));
        assertNotEquals(null, animuExtractor.extraerDatosObrasRelacionadas(animuExtractor.articleTags));
    }

    @Test
    void extraerDatosSinObrasRelacionadas(){
        String link="https://myanimelist.net/anime/32979/Flip_Flappers?q=flip%20flappers&cat=anime";
        animuExtractor.extractDataFromArticle(link);
        System.out.println(animuExtractor.extraerDatosObrasRelacionadas(animuExtractor.articleTags));
        assertEquals(null, animuExtractor.extraerDatosObrasRelacionadas(animuExtractor.articleTags));
    }

    @ParameterizedTest
    @ValueSource(strings = {"https://myanimelist.net/anime/5114/Fullmetal_Alchemist__Brotherhood","https://myanimelist.net/anime/32979/Flip_Flappers?q=flip%20flappers","https://myanimelist.net/anime/37991/JoJo_no_Kimyou_na_Bouken_Part_5__Ougon_no_Kaze?q=jojo&cat=anime","https://myanimelist.net/anime/33010/FLCL_Progressive?q=flcl%20prog&cat=anime"})
    void checkOpeningExtraction(String url) {
        animuExtractor.extractDataFromArticle(url);
        animuExtractor.iniciarExtraerMusica(animuExtractor.articleTags);
        assertFalse(animuExtractor.openingRows.isEmpty());
    }

    @Test
    void testNullHtmlPageAsArgument() {
        animuExtractor.iniciarExtraerMusica(null);
    }

    @ParameterizedTest
    @ValueSource(strings = {"https://myanimelist.net/anime/5114/Fullmetal_Alchemist__Brotherhood","https://myanimelist.net/anime/32979/Flip_Flappers?q=flip%20flappers","https://myanimelist.net/anime/37991/JoJo_no_Kimyou_na_Bouken_Part_5__Ougon_no_Kaze?q=jojo&cat=anime","https://myanimelist.net/anime/33010/FLCL_Progressive?q=flcl%20prog&cat=anime"})
    void checkEndingExtraction(String url) {
        animuExtractor.extractDataFromArticle(url);
        //System.out.println(animuExtractor.articleTags);
        animuExtractor.iniciarExtraerMusica(animuExtractor.articleTags);
        System.out.println("animu.endingRows = " + animuExtractor.endingRows);
        assertFalse(animuExtractor.endingRows.isEmpty());
    }
    @ParameterizedTest
    @ValueSource(strings = {"https://myanimelist.net/anime/5114/Fullmetal_Alchemist__Brotherhood","https://myanimelist.net/anime/32979/Flip_Flappers?q=flip%20flappers","https://myanimelist.net/anime/37991/JoJo_no_Kimyou_na_Bouken_Part_5__Ougon_no_Kaze?q=jojo&cat=anime","https://myanimelist.net/anime/33010/FLCL_Progressive?q=flcl%20prog&cat=anime"})
    void checkImportantInfoExtraction(String url) {
        animuExtractor.extractDataFromArticle(url);
        System.out.println(animuExtractor.articleTags);
        animuExtractor.obtenerInformacionImportanteAnime();
        System.out.println(animuExtractor.ponerInfoImportanteEnMaps(animuExtractor.usableInformationElements));
        assertTrue(!animuExtractor.ponerInfoImportanteEnMaps(animuExtractor.usableInformationElements).isEmpty());
    }

    @ParameterizedTest
    @CsvSource({"https://myanimelist.net/anime/5114/Fullmetal_Alchemist__Brotherhood,5114",
            "https://myanimelist.net/anime/37991/JoJo_no_Kimyou_na_Bouken_Part_5__Ougon_no_Kaze?q=jojo&cat=anime,37991",
            "https://myanimelist.net/anime/32979/Flip_Flappers?q=flip%20flappers&cat=anime,32979",
            "https://myanimelist.net/anime/33010/FLCL_Progressive?q=flcl%20prog&cat=anime,33010"})
    void testExtraerVariasIDanime(String url,String idEsperada){
        int idEsperadaInt=Integer.parseInt(idEsperada);
        int numObtenido= animuExtractor.obtenerID(url);
        assertEquals(idEsperadaInt,numObtenido);

    }

    @Test
    void testExtraerIDAnime(){
        int numObtenido= animuExtractor.obtenerID("https://myanimelist.net/anime/5114/Fullmetal_Alchemist__Brotherhood");
        assertEquals(5114,numObtenido);
    }


    @Test
    void formarPreviewAnime(){
        animuExtractor.startCollectFromTop();
        System.out.println(animuExtractor.formarRecordPreview(animuExtractor.topRowsOfMedia.get(1)));
        assertNotNull(animuExtractor.formarRecordPreview(animuExtractor.topRowsOfMedia.get(1)));
    }
    @ParameterizedTest
    @ValueSource(strings = {"unknown","Unknown","AAAAA"})
    void categoriasDesconocidasDebenRetornarOther(String categoria){
        assertEquals("Other",animuExtractor.definirCategoria(categoria));

    }

    @Test
    void categoriaTVretornaTV(){
        assertEquals("TV",animuExtractor.definirCategoria("TV"));
    }

    @Test
    void formarPreviewsTop50(){
        animuExtractor.startCollectFromTop();
        System.out.println(animuExtractor.formarPreviewsPagTop());
        assertFalse(animuExtractor.formarPreviewsPagTop().isEmpty());
    }

    @Test
    void testTieneEmisoras(){
        animuExtractor.extractDataFromArticle("https://myanimelist.net/anime/37991/JoJo_no_Kimyou_na_Bouken_Part_5__Ougon_no_Kaze?q=jojo&cat=anime");
        System.out.println(animuExtractor.obtenerEmisorasDelAnime());
        List<String> listaEmisoras= Arrays.asList("Crunchyroll","Netflix","Bilibili Global");
        assertEquals(listaEmisoras,animuExtractor.obtenerEmisorasDelAnime());
    }

    @Test
    void testTieneEmisorasFF(){
        animuExtractor.extractDataFromArticle("https://myanimelist.net/anime/32979/Flip_Flappers?q=flip%20flappers&cat=anime");
        System.out.println(animuExtractor.obtenerEmisorasDelAnime());
        assertNotEquals(0,animuExtractor.obtenerEmisorasDelAnime().size());
    }

    @ParameterizedTest
    @CsvSource({"1,''","2,?limit=50","3,?limit=100","4,?limit=150","5,?limit=200"})
    void transformarNumPagAURL(String numPaginaString,String resultado){
        int numPagina=Integer.parseInt(numPaginaString);
        assertEquals(resultado,animuExtractor.convertirPaginaTopAUrl(numPagina));
    }

    @Test
    void extraerInfoBusquedaUnaPalabra(){
        String termino="jojo";
        animuExtractor.createSearchURL(termino);
        animuExtractor.realizarBusqueda();
        animuExtractor.pasarTodasFilasAPreview();
    }

    @Test
    void extraerInfoBusquedaTerminoCompuesto(){
        String termino="shoujo shuumatsu";
        animuExtractor.createSearchURL(termino);
        animuExtractor.realizarBusqueda();
        animuExtractor.pasarTodasFilasAPreview();
    }

    @Test
    void extraerInfoStaff(){
        animuExtractor.extractDataFromArticle("https://myanimelist.net/anime/666/JoJo_no_Kimyou_na_Bouken");
        System.out.println(animuExtractor.extraerInfoStaff(animuExtractor.articleTags));
    }


}