package err;

public class ExcepcionMalFormatoURL extends Exception {
    public ExcepcionMalFormatoURL() {
        super("Url con formato incorrecto");
    }
}
