package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel {
    private JTextField textField9;
    private JTextField textField1;
    private JButton registrarButton;
    private JButton iniciarSesiónButton;

    class IniciarSesionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            iniciarSesiónButton.setText("");
        }
    }

}
