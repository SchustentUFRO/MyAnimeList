package DataAndCollection;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class RegistrarUsuario {
    private String user;
    private String password;

    public RegistrarUsuario (String user, String password){
        this.user=user;
        this.password=password;
    }

    public static void registrarUsuario(Firestore db, String nombre, String correo, String contraseña) throws ExecutionException, InterruptedException {
        // Creamos una nueva colección en Firestore llamada "usuarios"
        // donde guardaremos los datos del usuario
        CollectionReference usuarios = db.collection("usuarios");

        // Creamos un nuevo documento en la colección "usuarios"
        // donde guardaremos los datos del usuario
        DocumentReference nuevoUsuario = usuarios.document();

        // Creamos un mapa de clave-valor donde guardaremos los datos del usuario
        Map<String, Object> usuario = new HashMap<>();
        usuario.put("nombre", nombre);
        usuario.put("correo", correo);
        usuario.put("contraseña", contraseña);

        try {
            // Guardamos los datos del usuario en el documento creado previamente
            ApiFuture<WriteResult> result = nuevoUsuario.set(usuario);
            System.out.println("Usuario registrado correctamente: " + result.get().getUpdateTime());
        } catch (Exception e) {
            // Si ocurre algún error, mostramos un mensaje de error al usuario
            System.out.println("Error al registrar el usuario: " + e.getMessage());
        }
    }


}






