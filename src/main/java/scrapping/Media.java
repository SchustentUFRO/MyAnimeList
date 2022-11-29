package scrapping;

public abstract class Media implements Comparable<Media> {
    String nombre;
    int posicionRanking;
    double puntuacion;
    String link;

    public Media(String nombre, int posicionRanking, double puntuacion, String link) {
        this.nombre = nombre;
        this.posicionRanking = posicionRanking;
        this.puntuacion = puntuacion;
        this.link = link;
    }

    @Override
    public int compareTo(Media o) {
        if (this.puntuacion>o.puntuacion){
            return 1;
        } else if (this.puntuacion<o.puntuacion) {
            return -1;
        }
        else {
            return 0;
        }
    }
}
