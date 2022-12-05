package DataAndCollection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
class ManejoDeDBTest {

    @BeforeEach
    void setUp() throws Exception {
        Conectar.conectar();
    }

    @Test
    void deteleContent() throws ExecutionException, InterruptedException {
        ManejoDeDB.deteleContent();
    }

}