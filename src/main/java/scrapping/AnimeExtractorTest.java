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
        System.out.println(animuExtractor.extraerDatosObrasRelacionadas(animuExtractor.articleTags.get(0)));
        assertNotEquals(null, animuExtractor.extraerDatosObrasRelacionadas(animuExtractor.articleTags.get(0)));
    }

    @Test
    void extraerDatosSinObrasRelacionadas(){
        String link="https://myanimelist.net/anime/32979/Flip_Flappers?q=flip%20flappers&cat=anime";
        animuExtractor.extractDataFromArticle(link);
        System.out.println(animuExtractor.extraerDatosObrasRelacionadas(animuExtractor.articleTags.get(0)));
        assertEquals(null, animuExtractor.extraerDatosObrasRelacionadas(animuExtractor.articleTags.get(0)));
    }

    @ParameterizedTest
    @ValueSource(strings = {"https://myanimelist.net/anime/5114/Fullmetal_Alchemist__Brotherhood","https://myanimelist.net/anime/32979/Flip_Flappers?q=flip%20flappers","https://myanimelist.net/anime/37991/JoJo_no_Kimyou_na_Bouken_Part_5__Ougon_no_Kaze?q=jojo&cat=anime","https://myanimelist.net/anime/33010/FLCL_Progressive?q=flcl%20prog&cat=anime"})
    void checkOpeningExtraction(String url) {
        animuExtractor.extractDataFromArticle(url);
        System.out.println(animuExtractor.articleTags);
        animuExtractor.extraerMusica(animuExtractor.articleTags.get(0));
        System.out.println("animu.openingRows = " + animuExtractor.openingRows);
        assertFalse(animuExtractor.openingRows.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(strings = {"https://myanimelist.net/anime/5114/Fullmetal_Alchemist__Brotherhood","https://myanimelist.net/anime/32979/Flip_Flappers?q=flip%20flappers","https://myanimelist.net/anime/37991/JoJo_no_Kimyou_na_Bouken_Part_5__Ougon_no_Kaze?q=jojo&cat=anime","https://myanimelist.net/anime/33010/FLCL_Progressive?q=flcl%20prog&cat=anime"})
    void checkEndingExtraction(String url) {
        animuExtractor.extractDataFromArticle(url);
        //System.out.println(animuExtractor.articleTags);
        animuExtractor.extraerMusica(animuExtractor.articleTags.get(0));
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
        animuExtractor.collectFromTop();
        System.out.println(animuExtractor.formarRecordPreview(animuExtractor.topRowsOfMedia.get(1)));
        assertNotNull(animuExtractor.formarRecordPreview(animuExtractor.topRowsOfMedia.get(1)));
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

}