package errores;

public class Errores extends Exception{
    public static final long serialVersionUID = 700L;
    public Errores(String message) {
        super(message);
    }
}

