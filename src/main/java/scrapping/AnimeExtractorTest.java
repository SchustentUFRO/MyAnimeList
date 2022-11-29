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
        animu.extraerDatosObrasRelacionadas(animu.articleTags.get(0));
        System.out.println(animu.extraerDatosObrasRelacionadas(animu.articleTags.get(0)));

    }
}