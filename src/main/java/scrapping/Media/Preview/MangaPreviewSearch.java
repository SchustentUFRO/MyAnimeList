package scrapping.Media.Preview;

public record MangaPreviewSearch(int id, String nombre, int posicionRanking, double puntuacion, String link) {

    public MangaPreviewSearch(int id, String nombre, int posicionRanking, double puntuacion, String link) {
        this.id = id;
        this.nombre = nombre;
        this.posicionRanking = posicionRanking;
        this.puntuacion = puntuacion;
        this.link = link;
    }
}

