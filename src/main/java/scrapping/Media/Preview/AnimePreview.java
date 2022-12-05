package scrapping.Media.Preview;

import scrapping.Media.DetailedMedia.Media;

public class AnimePreview extends Preview implements Comparable<AnimePreview>{
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



    @Override
    public int compareTo(AnimePreview o) {
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

