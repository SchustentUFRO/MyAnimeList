package scrapping.Media.DetailedMedia;

import scrapping.Media.Preview.MangaPreview;

public class MangaMedia extends Media{
    public MangaMedia(int id, String nombre, int posRanking, double puntuacion, String link){
        super(id,nombre,posRanking,puntuacion,link);
    }

    public MangaMedia(MangaPreview preview) {
        super(preview.getId(),preview.getNombre(), preview.getPosicionRanking(), preview.getPuntuacion(), preview.getLink());
    }
}
