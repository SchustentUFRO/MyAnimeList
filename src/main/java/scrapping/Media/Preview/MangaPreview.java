package scrapping.Media.Preview;

public class MangaPreview extends Preview implements Comparable<MangaPreview>{


    public MangaPreview(int id, String nombre, String tipo, int posicionRanking, double puntuacion, String link) {
        super(id,nombre,tipo,posicionRanking,link,puntuacion);
        tipoMedia="Manga";
    }

    public MangaPreview(int id, String nombre, String tipo, double puntuacion, String link) {
        super(id,nombre,tipo,link,puntuacion);
        tipoMedia="Manga";
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


    @Override
    public int compareTo(MangaPreview o) {
        if (this.id>o.getId()){
            return 1;
        } else if (this.puntuacion<o.getId()) {
            return -1;
        }
        else {
            return 0;
        }
    }
}
