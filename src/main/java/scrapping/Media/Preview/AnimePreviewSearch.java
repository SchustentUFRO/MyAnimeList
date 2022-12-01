package scrapping.Media.Preview;

public record AnimePreviewSearch(int id, String nombre, String tipo, int posicionRanking, double puntuacion, String link) {
    public AnimePreviewSearch(int id, String nombre, String tipo, int posicionRanking, double puntuacion, String link) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.posicionRanking = posicionRanking;
        this.puntuacion = puntuacion;
        this.link = link;
    }
}
