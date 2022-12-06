import DataAndCollection.ManejoDeDB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrarUsuario extends JFrame{
    private JPanel registrar;
    private JTextField nombreField;
    private JTextField correoField;
    private JPasswordField passwordField1;
    private JButton atrasButton;
    private JButton registrarButton;
    private JFrame registerFrame;

    public RegistrarUsuario(){
        registerFrame = new JFrame();
        registerFrame.setTitle("Registrar");
        registerFrame.setContentPane(registrar);
        registerFrame.pack();
        registerFrame.setLocationRelativeTo(null);
        registerFrame.setVisible(true);
        registerFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManejoDeDB.registrarUsuario(nombreField.getText(),correoField.getText(), String.valueOf(passwordField1.getPassword()));
            }
        });
        atrasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerFrame.dispose();
                new LoginPanel();
            }
        });
    }
}


