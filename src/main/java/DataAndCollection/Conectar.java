package DataAndCollection;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;


public class Conectar {
    //TODO
    public static void conectar() throws Exception {
        FileInputStream serviceAccount =
                new FileInputStream("Credencials/fir-1-fac9c-firebase-adminsdk-i0vux-e361a8960c.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();
        FirebaseApp.initializeApp(options);

        Firestore db = FirestoreClient.getFirestore();
        System.out.println("exito");

    }

}
