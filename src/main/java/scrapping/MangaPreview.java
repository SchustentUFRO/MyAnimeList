package scrapping;

public record MangaPreview(int id,String nombre, int posicionRanking, double puntuacion,String link) {

    public MangaPreview(int id, String nombre, int posicionRanking, double puntuacion, String link) {
        this.id = id;
        this.nombre = nombre;
        this.posicionRanking = posicionRanking;
        this.puntuacion = puntuacion;
        this.link = link;
    }
}
