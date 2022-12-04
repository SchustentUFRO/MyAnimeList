package DataAndCollection;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import errores.Errores;
import scrapping.Media.Preview.Preview;

import java.util.*;
import java.util.concurrent.ExecutionException;

public class ManejoDeDB {
    private static Firestore db = Conectar.getDb();
    private static Scanner teclado = new Scanner(System.in);

    public ManejoDeDB() {

    }

    public static void inputRegistarUsuario() {
        // entradas
        String contraseña;
        String correo;
        String nombre;
        // manejos
        System.out.println("ingresa nombre del usuario");
        nombre = teclado.next();
        System.out.println("ingresa correo del usuario");
        correo = teclado.next();
        ;
        System.out.println("ingresa contraseña del usuario");
        contraseña = teclado.next();
        registrarUsuario(nombre, correo, contraseña);

    }

    public static void inputIniciarSesion() throws ExecutionException, InterruptedException, Errores {
        String correo;
        String contraseña;
        System.out.println("Ingresa correo");
        correo = teclado.next();
        System.out.println("Ingresa contraseña");
        contraseña = teclado.next();
        try {
            iniciarSesion(correo, contraseña);
        } catch (Errores e) {
            System.out.println("error al iniciar sesion: " + e.getMessage());
        }
    }

    private static void registrarUsuario(String nombre, String correo, String contraseña) {
        // Creamos una nueva colección en Firestore llamada "usuarios"
        // donde guardaremos los datos del usuario
        CollectionReference usuarios = ManejoDeDB.db.collection("usuarios");

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

    private static void iniciarSesion(String correo, String contraseña) throws ExecutionException, InterruptedException, Errores {
        // Buscamos en la colección "usuarios" de Firestore un usuario con el correo electrónico especificado
        ApiFuture<QuerySnapshot> future = ManejoDeDB.db.collection("usuarios").whereEqualTo("correo", correo).get();

        // Obtenemos el primer documento encontrado (si existe)
        List<QueryDocumentSnapshot> documentos = future.get().getDocuments();
        if (documentos.size() > 0) {
            DocumentSnapshot documento = documentos.get(0);

            // Verificamos que la contraseña es correcta
            if (documento.getString("contraseña").equals(contraseña)) {
                // Si la contraseña es correcta, mostramos un mensaje de éxito y continuamos con la sesión
                System.out.println("Bienvenido, " + documento.getString("nombre"));
            } else {
                throw new Errores("la contraseña es incorrecta");
            }
        } else {
            // Si no se encuentra ningún usuario con el correo electrónico especificado, mostramos un mensaje de error
            System.out.println("No se encontró ningún usuario con el correo electrónico especificado");
        }
    }

    public static void guardarInformacionPreview(ArrayList<Preview> previews) {

    }


}










