import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JFrame {
    private JTextField textField1;
    private JButton registrarButton;
    private JButton iniciarSesiónButton;
    private JPasswordField passwordField1;
    private JPanel loginPanel;

    private JFrame loginFrame;

    class IniciarSesionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            iniciarSesiónButton.setText("");
        }
    }

    public LoginPanel() {
        loginFrame = new JFrame();
        loginFrame.setTitle("Login");
        loginFrame.setContentPane(loginPanel);
        loginFrame.pack();
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setVisible(true);
        registrarButton.addActionListener(e -> {
            new RegistrarUsuario();
            loginFrame.dispose();
        });
    }

}
