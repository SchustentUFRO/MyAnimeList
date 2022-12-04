package err;

import java.io.IOException;

public class ExcepcionDeConexion extends Exception {
    public ExcepcionDeConexion() {
        super("Sin conexion a internet o página no disponible");
    }
}
