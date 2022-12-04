package scrapping.Media.DetailedMedia;

import scrapping.Media.Preview.AnimePreview;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnimeMedia extends Media{

    final String tipo;


    Map<String,String> infoEmision,infoStaff;
    Map<String,String> obrasRelacionadas;
    List<String> emisoras;
    List<String> openings,endings;


    public AnimeMedia(int id, String nombre, int posRanking, double puntuacion, String link,String tipo){
        super(id,nombre,posRanking,puntuacion,link);
        this.tipo=tipo;
        infoEmision=new HashMap<>();
    }

    public AnimeMedia(AnimePreview preview) {
        super(preview.getId(),preview.getNombre(), preview.getPosicionRanking(), preview.getPuntuacion(), preview.getLink());
        this.tipo=preview.getTipo();
        infoEmision=new HashMap<>();
    }
    public AnimeMedia(AnimePreview preview, int posicionRanking) {
        super(preview.getId(),preview.getNombre(), posicionRanking, preview.getPuntuacion(), preview.getLink());
        this.tipo=preview.getTipo();
        infoEmision=new HashMap<>();
    }

    public void agregarInfoEmision(String llave, String valor){
        infoEmision.put(llave,valor);
    }

    public void setObrasRelacionadas(Map<String, String> obrasRelacionadas) {
        this.obrasRelacionadas = obrasRelacionadas;
    }

    public void setEmisoras(List<String> emisoras) {
        this.emisoras = emisoras;
    }

    public void setOpenings(List<String> openings) {
        this.openings = openings;
    }

    public void setEndings(List<String> endings) {
        this.endings = endings;
    }

    public Map<String, String> getInfoStaff() {
        return infoStaff;
    }

    public void setInfoStaff(Map<String, String> infoStaff) {
        this.infoStaff = infoStaff;
    }
}
