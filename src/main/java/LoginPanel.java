import DataAndCollection.ManejoDeDB;
import errores.Errores;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;

public class LoginPanel extends JFrame {
    private JTextField correoField;
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
        iniciarSesiónButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (ManejoDeDB.iniciarSesion(correoField.getText(), String.valueOf(passwordField1.getPassword()))) {
                        loginFrame.dispose();
                        new MainPanel();
                    }
                } catch (Errores | ExecutionException | InterruptedException ex) {
                    loginFrame.dispose();
                    new LoginPanel();
                }
            }
        });
    }
}



