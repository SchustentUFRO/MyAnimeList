package scrapping;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MangaExtractorTest {
    MangaExtractor mangoExtractor;

    @BeforeEach
    void setUp() {
        mangoExtractor=new MangaExtractor();

    }
    @AfterEach
    void tearDown() {
        mangoExtractor.client.close();
        mangoExtractor =null;
    }
    @Test
    void collectFromTop() {
        mangoExtractor.collectFromTop();
        System.out.println(mangoExtractor.topRowsOfMedia);
        System.out.println(mangoExtractor.articlesURLs);
        assertFalse(mangoExtractor.topRowsOfMedia.isEmpty());
    }

    @Test
    void formarPreviewsTop50(){
        mangoExtractor.collectFromTop();
        System.out.println(mangoExtractor.formarPreviewsPagTop());
        assertFalse(mangoExtractor.formarPreviewsPagTop().isEmpty());
    }



}