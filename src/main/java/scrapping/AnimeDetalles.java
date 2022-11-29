package scrapping;

import java.util.HashMap;
import java.util.Map;

public class AnimeDetalles extends Media{

    String tipo;


    Map<String,String> infoEmision;

    public AnimeDetalles(String nombre,int posRanking,double puntuacion,String link){
        super(nombre,posRanking,puntuacion,link);
        infoEmision=new HashMap<>();
    }

    public AnimeDetalles(AnimePreview preview) {
        super(preview.nombre(), preview.posicionRanking(), preview.puntuacion(), preview.link());
        infoEmision=new HashMap<>();
    }

    public void agregarInfoEmision(String llave, String valor){
        infoEmision.put(llave,valor);
    }
}
