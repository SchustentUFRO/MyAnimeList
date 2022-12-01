package scrapping.DetailedMedia;

import scrapping.PreviewRecords.MangaPreviewTop;

public class MangaMedia extends Media{
    public MangaMedia(int id, String nombre, int posRanking, double puntuacion, String link){
        super(id,nombre,posRanking,puntuacion,link);
    }

    public MangaMedia(MangaPreviewTop preview) {
        super(preview.id(),preview.nombre(), preview.posicionRanking(), preview.puntuacion(), preview.link());
    }
}
