package scrapping.DetailedMedia;

import scrapping.PreviewRecords.AnimePreviewTop;

import java.util.HashMap;
import java.util.Map;

public class AnimeMedia extends Media{

    final String tipo;


    Map<String,String> infoEmision;
    Map<String,String> obrasRelacionadas;

    public AnimeMedia(int id, String nombre, int posRanking, double puntuacion, String link,String tipo){
        super(id,nombre,posRanking,puntuacion,link);
        this.tipo=tipo;
        infoEmision=new HashMap<>();
    }

    public AnimeMedia(AnimePreviewTop preview) {
        super(preview.id(),preview.nombre(), preview.posicionRanking(), preview.puntuacion(), preview.link());
        this.tipo=preview.tipo();
        infoEmision=new HashMap<>();
    }

    public void agregarInfoEmision(String llave, String valor){
        infoEmision.put(llave,valor);
    }
}
