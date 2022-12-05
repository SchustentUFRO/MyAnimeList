package scrapping.Media.Preview;

public abstract class Preview {
    int id,posicionRanking;
    String nombre,tipo,link;
    double puntuacion;

    public Preview() {
    }

    public Preview(int id, String nombre, String tipo, String link, double puntuacion) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.link = link;
        this.puntuacion = puntuacion;
    }

    public Preview(int id,  String nombre, String tipo,int posicionRanking, String link, double puntuacion) {
        this.id = id;
        this.posicionRanking = posicionRanking;
        this.nombre = nombre;
        this.tipo = tipo;
        this.link = link;
        this.puntuacion = puntuacion;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public String getLink() {
        return link;
    }

    public double getPuntuacion() {
        return puntuacion;
    }
    public int getPosicionRanking(){
        return posicionRanking;
    }
}
