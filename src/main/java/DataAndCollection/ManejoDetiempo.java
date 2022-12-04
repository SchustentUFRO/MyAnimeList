package DataAndCollection;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.gson.Gson;
import scrapping.AnimeExtractor;

import java.util.Date;
import java.util.List;

public class ManejoDetiempo {
    // instancia de la db;
    private static Firestore db = Conectar.getDb();

    // este metodo decide si hacer scrapping a la página MyAnimeList.com u obtener los datos desde firestone
    // segun el tiempo pasado, podra hacer una cosa u otra
    public static void manejarTiempo() throws Exception {
        Date date = new Date();
        long consultaOriginal = getTime();
        if (date.getTime() > consultaOriginal + 240000) {
            updateTime(date.getTime());
        }else{
            //si pasa el tiempo correspondido se formará un json del top 50 y luego este se enviará
            ManejoDeDB.guardarInformacionPreview();
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
