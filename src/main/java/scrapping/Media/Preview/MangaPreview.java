package scrapping.Media.Preview;

public class MangaPreview extends Preview{


    public MangaPreview(int id, String nombre, String tipo, int posicionRanking, double puntuacion, String link) {
        super(id,nombre,tipo,posicionRanking,link,puntuacion);
    }

    public MangaPreview(int id, String nombre, String tipo, double puntuacion, String link) {
        super(id,nombre,tipo,link,puntuacion);
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
