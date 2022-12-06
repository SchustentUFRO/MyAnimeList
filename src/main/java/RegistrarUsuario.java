import javax.swing.*;

public class RegistrarUsuario extends JFrame{
    private JPanel registrar;
    public RegistrarUsuario(String titulo){
        super(titulo);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(registrar);
        this.pack();
    }

    public static void main(String[] args) {
        JFrame jFrame = new RegistrarUsuario("titulo");
        jFrame.setVisible(true);
    }
}


