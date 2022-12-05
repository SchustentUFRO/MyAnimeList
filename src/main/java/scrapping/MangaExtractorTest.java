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
        mangoExtractor.iniciarScrapper();
        System.out.println(mangoExtractor.mangaTopPreview);
        assertNotNull(mangoExtractor.mangaTopPreview);
    }

    @Test
    void crearDetallesManga(){
        mangoExtractor.iniciarScrapper();
        mangoExtractor.seleccionarPreviewTopParaMostrarDetalles(mangoExtractor.mangaTopPreview.get(0));
        System.out.println(mangoExtractor.mangaMediaList.get(0));
        assertFalse(mangoExtractor.mangaMediaList.isEmpty());
    }



}