package scrapping.DetailedMedia;

public abstract class Media implements Comparable<Media> {
    int id;
    String nombre;
    int posicionRanking;
    double puntuacion;
    String link;

    public Media(int id,String nombre, int posicionRanking, double puntuacion, String link) {
        this.id=id;
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
