import DataAndCollection.ManejoDeDB;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel {
    private JTextField textField1;
    private JButton registrarButton;
    private JButton iniciarSesiónButton;
    private JPasswordField passwordField1;

    class IniciarSesionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            iniciarSesiónButton.setText("");
        }
    }

    public LoginPanel() {
        registrarButton.addActionListener(e -> {

        });
    }
}
