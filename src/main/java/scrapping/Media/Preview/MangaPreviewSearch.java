package scrapping.Media.Preview;

public record MangaPreviewSearch(int id, String nombre, double puntuacion, String link) {

    public MangaPreviewSearch(int id, String nombre, double puntuacion, String link) {
        this.id = id;
        this.nombre = nombre;
        this.puntuacion = puntuacion;
        this.link = link;
    }

    @Override
    public String toString() {
        return "MangaPreviewSearch{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", puntuacion=" + puntuacion +
                ", link='" + link + '\'' +
                "}\n";
    }
}

