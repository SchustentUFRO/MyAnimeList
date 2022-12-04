package DataAndCollection;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import scrapping.AnimeExtractor;

import java.util.Date;
import java.util.List;

public class ManejoDetiempo {


    // este metodo decide si hacer scrapping a la página MyAnimeList.com u obtener los datos desde firestone
    // segun el tiempo pasado, podra hacer una cosa u otra
    private static Firestore db = Conectar.getDb();
    public static void manejarTiempo() throws Exception {
        Date date = new Date();
        long consultaOriginal = getTime();
        if (date.getTime() > consultaOriginal + 240000) {
            updateTime(date.getTime());
        }else{
            AnimeExtractor animeExtractor = new AnimeExtractor();
            animeExtractor.iniciarScrapper();
            System.out.println(animeExtractor.getAnimeTopPreview());
        }
    }



    public static void updateTime(long time) {
        DocumentReference nuevoTiempo = db.collection("tiempo").document("tiempo");
        try {
            ApiFuture<WriteResult> future = nuevoTiempo.update("tiempo", time);
            WriteResult result = future.get();
            System.out.println("Write result: " + result);
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
    }

    public static long getTime() throws NullPointerException {
        try {
            CollectionReference tiempo = db.collection("tiempo");
            List<QueryDocumentSnapshot> timeGetter = tiempo.get().get().getDocuments();
            for (QueryDocumentSnapshot document : timeGetter) {
                return document.getLong("tiempo");
            }
        }catch (Exception e) {
            return 0;
        }
        return 0;
    }
}
