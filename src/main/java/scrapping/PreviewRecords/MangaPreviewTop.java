package scrapping.PreviewRecords;

public record MangaPreviewTop(int id, String nombre, int posicionRanking, double puntuacion, String link) {

    public MangaPreviewTop(int id, String nombre, int posicionRanking, double puntuacion, String link) {
        this.id = id;
        this.nombre = nombre;
        this.posicionRanking = posicionRanking;
        this.puntuacion = puntuacion;
        this.link = link;
    }
}
