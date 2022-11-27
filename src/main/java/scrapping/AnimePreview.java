package scrapping;

public record AnimePreview(String nombre,String tipo, int posicionRanking, double puntuacion,String link) {
    public AnimePreview(String nombre, String tipo, int posicionRanking, double puntuacion,String link) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.posicionRanking = posicionRanking;
        this.puntuacion = puntuacion;
        this.link=link;
    }

    @Override
    public String toString() {
        return "Anime Preview: " +
                "nombre='" + nombre + '\'' +
                ", tipo='" + tipo + '\'' +
                ", posicionRanking=" + posicionRanking +
                ", puntuacion=" + puntuacion +
                '}';
    }

    @Override
    public int posicionRanking() {
        return posicionRanking;
    }

    @Override
    public String link() {
        return link;
    }
}

