package scrapping;

import java.util.HashMap;

public class MangaMedia extends Media{
    public MangaMedia(int id, String nombre, int posRanking, double puntuacion, String link){
        super(id,nombre,posRanking,puntuacion,link);
    }

    public MangaMedia(MangaPreview preview) {
        super(preview.id(),preview.nombre(), preview.posicionRanking(), preview.puntuacion(), preview.link());
    }
}
