package err;

import java.net.MalformedURLException;

public class MalFormatoURL extends Exception {
    public MalFormatoURL() {
        super("Url con formato incorrecto");
    }
}
