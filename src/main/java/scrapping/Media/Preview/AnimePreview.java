package scrapping.Media.Preview;

public class AnimePreview extends Preview{
    public AnimePreview(int id, String nombre, String tipo, int posicionRanking, double puntuacion, String link) {
        super(id,nombre,tipo,posicionRanking,link,puntuacion);
    }

    public AnimePreview(int id, String nombre, String tipo, double puntuacion, String link) {
        super(id,nombre,tipo,link,puntuacion);
    }

    @Override
    public String toString() {
        return "AnimePreviewTop{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", tipo='" + tipo + '\'' +
                ", posicionRanking=" + posicionRanking +
                ", puntuacion=" + puntuacion +
                ", link='" + link + '\'' +
                "}\n";
    }




}

