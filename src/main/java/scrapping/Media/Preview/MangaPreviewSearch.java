package scrapping.Media.Preview;

public class MangaPreviewSearch extends Preview{

    public MangaPreviewSearch(int id, String nombre,String tipo, double puntuacion, String link) {
        super(id,nombre,tipo,link,puntuacion);
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

