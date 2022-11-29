package scrapping;

import com.google.api.client.util.Value;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class AnimeExtractorTest {


    AnimeExtractor animu;

    @BeforeEach
    void setUp() {
        animu = new AnimeExtractor();
    }

    @AfterEach
    void tearDown() {
        animu.client.close();
        animu=null;
    }

    @ParameterizedTest
    @ValueSource(strings = {"https://myanimelist.net/anime/37991/JoJo_no_Kimyou_na_Bouken_Part_5__Ougon_no_Kaze?q=jojo&cat=anime","https://myanimelist.net/anime/33010/FLCL_Progressive?q=flcl%20prog&cat=anime","https://myanimelist.net/anime/5114/Fullmetal_Alchemist__Brotherhood"} )
    void extraerDatosObrasRelacionadas(String link) {
        animu.extractDataFromArticle(link);
        System.out.println(animu.extraerDatosObrasRelacionadas(animu.articleTags.get(0)));
        assertNotEquals(null,animu.extraerDatosObrasRelacionadas(animu.articleTags.get(0)));
    }

    @Test
    void extraerDatosSinObrasRelacionadas(){
        String link="https://myanimelist.net/anime/32979/Flip_Flappers?q=flip%20flappers&cat=anime";
        animu.extractDataFromArticle(link);
        System.out.println(animu.extraerDatosObrasRelacionadas(animu.articleTags.get(0)));
        assertEquals(null,animu.extraerDatosObrasRelacionadas(animu.articleTags.get(0)));
    }

    @ParameterizedTest
    @ValueSource(strings = {"https://myanimelist.net/anime/5114/Fullmetal_Alchemist__Brotherhood","https://myanimelist.net/anime/32979/Flip_Flappers?q=flip%20flappers","https://myanimelist.net/anime/37991/JoJo_no_Kimyou_na_Bouken_Part_5__Ougon_no_Kaze?q=jojo&cat=anime","https://myanimelist.net/anime/33010/FLCL_Progressive?q=flcl%20prog&cat=anime"})
    void checkOpeningExtraction(String url) {
        animu.extractDataFromArticle(url);
        System.out.println(animu.articleTags);
        animu.extraerMusica(animu.articleTags.get(0));
        System.out.println("animu.openingRows = " + animu.openingRows);
        assertTrue(!animu.openingRows.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(strings = {"https://myanimelist.net/anime/5114/Fullmetal_Alchemist__Brotherhood","https://myanimelist.net/anime/32979/Flip_Flappers?q=flip%20flappers","https://myanimelist.net/anime/37991/JoJo_no_Kimyou_na_Bouken_Part_5__Ougon_no_Kaze?q=jojo&cat=anime","https://myanimelist.net/anime/33010/FLCL_Progressive?q=flcl%20prog&cat=anime"})
    void checkEndingExtraction(String url) {
        animu.extractDataFromArticle(url);
        System.out.println(animu.articleTags);
        animu.extraerMusica(animu.articleTags.get(0));
        System.out.println("animu.endingRows = " + animu.endingRows);
        assertTrue(!animu.endingRows.isEmpty());
    }
}