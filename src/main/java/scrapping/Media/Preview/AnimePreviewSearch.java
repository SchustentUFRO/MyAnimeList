package scrapping.Media.Preview;

public class AnimePreviewSearch extends Preview {
    public AnimePreviewSearch(int id, String nombre, String tipo, double puntuacion, String link) {
        super(id,nombre,tipo,link,puntuacion);
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
