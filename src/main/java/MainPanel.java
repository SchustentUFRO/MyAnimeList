import DataAndCollection.ManejoDeDB;
import scrapping.Media.Preview.AnimePreview;
import scrapping.Media.Preview.Preview;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainPanel {
    private JPanel mainPanel;
    private JLabel TopAnime;
    private JList list1;
    private JFrame mainFrame;
    private List<AnimePreview> lista = ManejoDeDB.leerInfoAnimes();
    private DefaultListModel modelo = new DefaultListModel();

    public MainPanel() throws ExecutionException, InterruptedException {
        mainFrame=new JFrame();
        mainFrame.setTitle("Panel Principal");
        mainFrame.setContentPane(mainPanel);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainFrame.setVisible(true);
        setLista();
        list1.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
            }
        });
    }

    private void setLista() {
        list1.setModel(modelo);
        modelo.removeAllElements();
        for (int i = 0; i < lista.size(); i++) {
            modelo.addElement(lista.get(i));
        }
    }
}
