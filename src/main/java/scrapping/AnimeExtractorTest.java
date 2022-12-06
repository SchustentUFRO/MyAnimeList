package scrapping;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import scrapping.Media.Preview.AnimePreview;
import scrapping.Media.Preview.Preview;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AnimeExtractorTest {


    AnimeExtractor animuExtractor;

    Map<String,String> noneMap;
    @BeforeEach
    void setUp() {
        animuExtractor = new AnimeExtractor();
        noneMap=new HashMap<>();
        noneMap.put("none","none");
    }

    @AfterEach
    void tearDown() {
        animuExtractor.client.close();
        animuExtractor =null;
    }



    @ParameterizedTest
    @ValueSource(strings = {"https://myanimelist.net/anime/37991/JoJo_no_Kimyou_na_Bouken_Part_5__Ougon_no_Kaze?q=jojo&cat=anime","https://myanimelist.net/anime/33010/FLCL_Progressive?q=flcl%20prog&cat=anime","https://myanimelist.net/anime/5114/Fullmetal_Alchemist__Brotherhood"} )
    void extraerDatosObrasRelacionadas(String link) {
        animuExtractor.testsExtractDataFromArticle(link);
        //System.out.println(animuExtractor.extraerDatosObrasRelacionadas(animuExtractor.articleTags));
        System.out.println(animuExtractor.extraerDatosObrasRelacionadas(animuExtractor.articleTags));
        assertNotEquals(noneMap, animuExtractor.extraerDatosObrasRelacionadas(animuExtractor.articleTags));
    }

    @Test
    void extraerDatosSinObrasRelacionadas(){
        String link="https://myanimelist.net/anime/32979/Flip_Flappers?q=flip%20flappers&cat=anime";
        animuExtractor.testsExtractDataFromArticle(link);
        System.out.println(animuExtractor.extraerDatosObrasRelacionadas(animuExtractor.articleTags));
        assertEquals(noneMap, animuExtractor.extraerDatosObrasRelacionadas(animuExtractor.articleTags));
    }

    @ParameterizedTest
    @ValueSource(strings = {"https://myanimelist.net/anime/5114/Fullmetal_Alchemist__Brotherhood","https://myanimelist.net/anime/32979/Flip_Flappers?q=flip%20flappers","https://myanimelist.net/anime/37991/JoJo_no_Kimyou_na_Bouken_Part_5__Ougon_no_Kaze?q=jojo&cat=anime","https://myanimelist.net/anime/33010/FLCL_Progressive?q=flcl%20prog&cat=anime"})
    void checkOpeningExtraction(String url) {
        animuExtractor.testsExtractDataFromArticle(url);
        animuExtractor.iniciarExtraerMusica(animuExtractor.articleTags);
        System.out.println(animuExtractor.openingRows);
        assertFalse(animuExtractor.openingRows.isEmpty());
    }



    @ParameterizedTest
    @ValueSource(strings = {"https://myanimelist.net/anime/5114/Fullmetal_Alchemist__Brotherhood","https://myanimelist.net/anime/32979/Flip_Flappers?q=flip%20flappers","https://myanimelist.net/anime/37991/JoJo_no_Kimyou_na_Bouken_Part_5__Ougon_no_Kaze?q=jojo&cat=anime","https://myanimelist.net/anime/33010/FLCL_Progressive?q=flcl%20prog&cat=anime"})
    void checkEndingExtraction(String url) {
        animuExtractor.testsExtractDataFromArticle(url);
        //System.out.println(animuExtractor.articleTags);
        animuExtractor.iniciarExtraerMusica(animuExtractor.articleTags);
        System.out.println("animu.endingRows = " + animuExtractor.endingRows);
        assertFalse(animuExtractor.endingRows.isEmpty());
    }
    @ParameterizedTest
    @ValueSource(strings = {"https://myanimelist.net/anime/5114/Fullmetal_Alchemist__Brotherhood","https://myanimelist.net/anime/32979/Flip_Flappers?q=flip%20flappers","https://myanimelist.net/anime/37991/JoJo_no_Kimyou_na_Bouken_Part_5__Ougon_no_Kaze?q=jojo&cat=anime","https://myanimelist.net/anime/33010/FLCL_Progressive?q=flcl%20prog&cat=anime"})
    void checkImportantInfoExtraction(String url) {
        animuExtractor.testsExtractDataFromArticle(url);
        animuExtractor.obtenerInformacionImportanteAnime();
        System.out.println(animuExtractor.ponerInfoImportanteEnMaps());
        assertFalse(animuExtractor.ponerInfoImportanteEnMaps().isEmpty());
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
        animuExtractor.iniciarScrapper();
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
        animuExtractor.iniciarScrapper();
        System.out.println(animuExtractor.formarPreviewsPagTop());
        assertFalse(animuExtractor.formarPreviewsPagTop().isEmpty());
    }

    @Test
    void formarPreviewsPag650(){
        for (int i = 1; i < 14; i++) {
            animuExtractor.avanzarPaginaTop();
            System.out.println(animuExtractor.pageTopURL);
        }
        animuExtractor.paginaTopAdelante();
        System.out.println(animuExtractor.topRowsOfMedia);
        System.out.println(animuExtractor.animeTopPreview);
        assertFalse(animuExtractor.topRowsOfMedia.isEmpty());
    }

    @Test
    void testTieneEmisoras(){
        animuExtractor.testsExtractDataFromArticle("https://myanimelist.net/anime/37991/JoJo_no_Kimyou_na_Bouken_Part_5__Ougon_no_Kaze?q=jojo&cat=anime");
        System.out.println(animuExtractor.obtenerEmisorasDelAnime());
        List<String> listaEmisoras= Arrays.asList("Crunchyroll","Netflix","Bilibili Global");
        assertEquals(listaEmisoras,animuExtractor.obtenerEmisorasDelAnime());
    }

    @Test
    void testTieneEmisorasUnaSola(){
        animuExtractor.testsExtractDataFromArticle("https://myanimelist.net/anime/32979/Flip_Flappers?q=flip%20flappers&cat=anime");
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
    void extraerInfoStaff(){
        Map<String,String> noneMap=new HashMap<>();
        noneMap.put("none","none");
        animuExtractor.iniciarScrapper();
        AnimePreview previewUsada=animuExtractor.testsSeleccionarPreviewMedianteInt(2);
        animuExtractor.seleccionarPreviewTopParaMostrarDetalles(previewUsada);
        //System.out.println(animuExtractor.safeExtraerInfoStaff(animuExtractor.articleTags));
        assertAll(
                ()->assertFalse(animuExtractor.animeMediaList.isEmpty()),
                ()->assertNotEquals(noneMap,animuExtractor.animeMediaList.get(0).getInfoStaff()));
    }



    @Test
    void formarPreviewsBusqueda(){
        animuExtractor.collectFromSearchAndFormPreviews("jojo bizarre");
        System.out.println(animuExtractor.animeSearchPreview);
        assertNotNull(animuExtractor.animeSearchPreview);
    }

    @Test
    void formarDetalleBusqueda(){
        animuExtractor.collectFromSearchAndFormPreviews("shoujo shuumatsu");
        animuExtractor.seleccionarPreviewSearchParaMostrarDetalles(animuExtractor.animeSearchPreview.get(0));
        System.out.println(animuExtractor.animeMediaList);
        assertNotNull(animuExtractor.animeMediaList.get(0));
    }





}