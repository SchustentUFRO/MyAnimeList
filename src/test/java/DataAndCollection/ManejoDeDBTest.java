package DataAndCollection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

class ManejoDeDBTest {

    @BeforeEach
    void setUp() throws Exception {
        Conectar.conectar();
    }

    @Test
    void deteleContent() throws ExecutionException, InterruptedException {
        ManejoDeDB.deteleContent();
    }

    @Test
    void guardarInformacionPreview() {
        ManejoDeDB.guardarInformacionPreview();
    }

    @Test
    void updateTop50() {
        ManejoDeDB.updateTop50();
    }


    void testLeerInfoAnimes() throws ExecutionException, InterruptedException {
        ManejoDeDB.leerInfoAnimes();
    }
}