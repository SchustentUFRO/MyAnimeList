package scrapping.Media.DetailedMedia;

import scrapping.Media.Preview.AnimePreview;
import scrapping.Media.Preview.MangaPreview;

import java.util.HashMap;
import java.util.Map;

public class MangaMedia extends Media{

    final String tipo;
    Map<String,String> infoEmision;


    public MangaMedia(MangaPreview preview) {
        super(preview.getId(),preview.getNombre(), preview.getPosicionRanking(), preview.getPuntuacion(), preview.getLink());
        this.tipo=preview.getTipo();
        infoEmision=new HashMap<>();
    }
    public MangaMedia(MangaPreview preview, int posicionRanking) {
        super(preview.getId(),preview.getNombre(), posicionRanking, preview.getPuntuacion(), preview.getLink());
        this.tipo=preview.getTipo();
        infoEmision=new HashMap<>();
    }
    public void agregarInfoEmision(String llave, String valor){
        infoEmision.put(llave,valor);
    }

    @Override
    public String toString() {
        return "MangaMedia{" +
                "tipo='" + tipo + '\'' +
                ", infoEmision=" + infoEmision +
                ", id=" + id +
                ", nombre='" + nombre + '\'' +
                ", posicionRanking=" + posicionRanking +
                ", puntuacion=" + puntuacion +
                ", link='" + link + '\'' +
                '}';
    }
}
