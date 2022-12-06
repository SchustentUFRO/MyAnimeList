package errores;

import javax.swing.*;

public class Errores extends Exception{
    public static final long serialVersionUID = 700L;
    public Errores(String message) {
        super();
        JOptionPane.showMessageDialog(null,message);
    }
}

