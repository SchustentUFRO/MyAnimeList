package scrapping.Media.Preview;

public record MangaPreviewTop(int id, String nombre, String tipo, int posicionRanking, double puntuacion, String link) {

    public MangaPreviewTop(int id, String nombre,String tipo, int posicionRanking, double puntuacion, String link) {
        this.id = id;
        this.nombre = nombre;
        this.tipo=tipo;
        this.posicionRanking = posicionRanking;
        this.puntuacion = puntuacion;
        this.link = link;
    }

    @Override
    public String toString() {
        return "MangaPreviewTop{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", tipo='" + tipo + '\'' +
                ", posicionRanking=" + posicionRanking +
                ", puntuacion=" + puntuacion +
                ", link='" + link + '\'' +
                "}\n";
    }
}
