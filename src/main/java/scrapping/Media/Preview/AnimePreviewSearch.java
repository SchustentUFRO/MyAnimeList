package scrapping.Media.Preview;

public record AnimePreviewSearch(int id, String nombre, String tipo, double puntuacion, String link) {
    public AnimePreviewSearch(int id, String nombre, String tipo, double puntuacion, String link) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.puntuacion = puntuacion;
        this.link = link;
    }

    @Override
    public String toString() {
        return "AnimePreviewSearch{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", tipo='" + tipo + '\'' +
                ", puntuacion=" + puntuacion +
                ", link='" + link + '\'' +
                "}\n";
    }
}
