package DataAndCollection;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class ManejoDeUsuarios {
    private static Firestore db = Conectar.getDb();
    private static Scanner teclado = new Scanner(System.in);
    public ManejoDeUsuarios(){

    }
    public static void inputRegistarUsuario(){
        // entradas
        String contraseña;
        String correo;
        String nombre;
        // manejos
        System.out.println("ingresa nombre del usuario");
        nombre=teclado.next();
        System.out.println("ingresa correo del usuario");
        correo= teclado.next();;
        System.out.println("ingresa contraseña del usuario");
        contraseña=teclado.next();
        registrarUsuario(db,nombre,correo,contraseña);

    }
    public static void inputIniciarSesion() throws ExecutionException, InterruptedException {
        String correo;
        String contraseña;
        System.out.println("Ingresa correo");
        correo= teclado.next();
        System.out.println("Ingresa contraseña");
        contraseña=teclado.next();
        iniciarSesion(db,correo,contraseña);
    }

    private static void registrarUsuario(Firestore db, String nombre, String correo, String contraseña) {
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

    private static void iniciarSesion(Firestore db, String correo, String contraseña) throws ExecutionException, InterruptedException {
        // Buscamos en la colección "usuarios" de Firestore un usuario con el correo electrónico especificado
        ApiFuture<QuerySnapshot> future = db.collection("usuarios").whereEqualTo("correo", correo).get();

        // Obtenemos el primer documento encontrado (si existe)
        List<QueryDocumentSnapshot> documentos = future.get().getDocuments();
        if (documentos.size() > 0) {
            DocumentSnapshot documento = documentos.get(0);

            // Verificamos que la contraseña es correcta
            if (documento.getString("contraseña").equals(contraseña)) {
                // Si la contraseña es correcta, mostramos un mensaje de éxito y continuamos con la sesión
                System.out.println("Bienvenido, " + documento.getString("nombre"));
            } else {
                // Si la contraseña es incorrecta, mostramos un mensaje de error
                System.out.println("La contraseña es incorrecta");
            }
        } else {
            // Si no se encuentra ningún usuario con el correo electrónico especificado, mostramos un mensaje de error
            System.out.println("No se encontró ningún usuario con el correo electrónico especificado");
        }
    }


}






