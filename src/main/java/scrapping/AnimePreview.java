package scrapping;

public record AnimePreview(int id,String nombre,String tipo, int posicionRanking, double puntuacion,String link) {
    public AnimePreview(int id,String nombre, String tipo, int posicionRanking, double puntuacion,String link) {
        this.id=id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.posicionRanking = posicionRanking;
        this.puntuacion = puntuacion;
        this.link=link;
    }

    @Override
    public String toString() {
        return "Anime Preview: " +
                "id='"+id+"\'"+
                ", nombre='" + nombre + '\'' +
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

