package DataAndCollection;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import errores.Errores;
import scrapping.AnimeExtractor;
import scrapping.Media.Comparations.PreviewRankingComparator;
import scrapping.Media.Preview.AnimePreview;

import javax.swing.*;
import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ManejoDeDB {
    private static Firestore db = Conectar.getDb();
    private static Scanner teclado = new Scanner(System.in);

    public ManejoDeDB() {

    }
    /*
    // estas funciones fueron usadas para probar el registro e ingresos de usuarios
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
    // estas funciones fueron usadas para probar el registro e ingresos de usuarios
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
     // estas funciones fueron usadas para probar el registro e ingresos de usuarios
     */

    public static boolean registrarUsuario(String nombre, String correo, String contraseña) throws Errores {
        System.out.println(nombre);
        System.out.println(correo);
        System.out.println(contraseña);
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
            validarCorreo(correo);
            // Guardamos los datos del usuario en el documento creado previamente
            ApiFuture<WriteResult> result = nuevoUsuario.set(usuario);
            JOptionPane.showMessageDialog(null,"Usuario Registrado");
            System.out.println(result.get().getUpdateTime());
            return true;
        } catch (Exception e) {
            // Si ocurre algún error, mostramos un mensaje de error al usuario
            System.out.println("Error al registrar el usuario: " + e.getMessage());
        }
        return false;
    }

    public static boolean iniciarSesion(String correo, String contraseña) throws ExecutionException, InterruptedException, Errores {
        // Buscamos en la colección "usuarios" de Firestore un usuario con el correo electrónico especificado
        ApiFuture<QuerySnapshot> future = ManejoDeDB.db.collection("usuarios").whereEqualTo("correo", correo).get();

        // Obtenemos el primer documento encontrado (si existe)
        List<QueryDocumentSnapshot> documentos = future.get().getDocuments();
        if (documentos.size() > 0) {
            DocumentSnapshot documento = documentos.get(0);

            // Verificamos que la contraseña es correcta
            if (documento.getString("contraseña").equals(contraseña)) {
                // Si la contraseña es correcta, mostramos un mensaje de éxito y continuamos con la sesión
                JOptionPane.showMessageDialog(null,"Bienvenido, " + documento.getString("nombre"));
                return true;
            } else {
                throw new Errores("la contraseña es incorrecta");
            }
        } else {
            // Si no se encuentra ningún usuario con el correo electrónico especificado, mostramos un mensaje de error
            JOptionPane.showMessageDialog(null,"No se encontró ningún usuario con el correo electrónico especificado");
        }
        return false;
    }

    //funcion solo usada para crear y guardar el top 50 de la lista de animes en la db
    public static void guardarInformacionPreview() {
        AnimeExtractor animeExtractor = new AnimeExtractor();
        animeExtractor.iniciarScrapper();
        Gson gson = new Gson();
        for (int i = 0; i < animeExtractor.getAnimeTopPreview().size(); i++) {
            Type type = new TypeToken<Map<String, Object>>() {
            }.getType();
            Map<String, Object> map = gson.fromJson(gson.toJson(animeExtractor.getAnimeTopPreview().get(i)), type);
            CollectionReference collectionReference = db.collection("animes");
            try {
                ApiFuture<WriteResult> result = collectionReference.document(String.valueOf(i + 1)).set(map);
                System.out.println("animes guardados, tiempo: " + result.get().getUpdateTime());
            } catch (Exception e) {
                e.getMessage();
            }
        }
    }

    public static void updateTop50() {
        AnimeExtractor animeExtractor = new AnimeExtractor();
        animeExtractor.iniciarScrapper();
        Gson gson = new Gson();
        for (int i = 0; i < animeExtractor.getAnimeTopPreview().size(); i++) {
            Type type = new TypeToken<Map<String, Object>>() {
            }.getType();
            Map<String, Object> map = gson.fromJson(gson.toJson(animeExtractor.getAnimeTopPreview().get(i)), type);
            DocumentReference animes = db.collection("animes").document(String.valueOf(i + 1));
            try {
                ApiFuture<WriteResult> future = animes.set(map);
                WriteResult result = future.get();
                System.out.println("Write result: " + result);
            } catch (Exception e) {
                System.out.println("Error" + e.getMessage());
            }
        }
    }

    // metodo usado por ahora solo con fines de testeo
    public static void deteleContent() throws ExecutionException, InterruptedException {

        CollectionReference collection = db.collection("usuarios");

        List<QueryDocumentSnapshot> documents = collection.get().get().getDocuments();

        for (DocumentSnapshot document : documents) {
            ApiFuture<WriteResult> result = document.getReference().delete();
            System.out.println("documento borrado: " + result.get().getUpdateTime());
        }
    }

    public static List<AnimePreview> leerInfoAnimes() throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> query = db.collection("animes").get();
        //instancia para comparar los datos
        PreviewRankingComparator p = new PreviewRankingComparator();
        Gson gson = new Gson();
        QuerySnapshot querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        List<AnimePreview> listaEnJSon = new ArrayList<>();
        documents.stream().forEach(querysnap ->
                listaEnJSon.add(querysnap.toObject(AnimePreview.class)));
        Collections.sort(listaEnJSon, p);
        for (AnimePreview document : listaEnJSon) {
            System.out.println("{");
            System.out.println(" position ranking: " + document.getPosicionRanking());
            System.out.println(" link: " + document.getLink());
            System.out.println(" nombre: " + document.getNombre());
            System.out.println(" punctuacion: " + document.getPuntuacion());
            System.out.println(" tipo: " + document.getTipo());
            System.out.println("}");
            System.out.println();

        }
        return listaEnJSon;
    }
    private static boolean validarCorreo(String email) throws Errores {

        // Patrón para validar el email
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        Matcher mather = pattern.matcher(email);

        if (mather.find() == true) {
            return true;
        } else {
            throw new Errores("mail no valido");
        }
    }
}













