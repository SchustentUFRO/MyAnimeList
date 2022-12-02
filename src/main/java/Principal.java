import DataAndCollection.Conectar;
import DataAndCollection.ManejoDeUsuarios;

import java.util.Scanner;


public class Principal {

    public static void main(String[] args) throws Exception {
        try{
            Conectar.conectar();
            Scanner teclado = new Scanner(System.in);
            int op=0;
            System.out.println("¿que desea hacer?");
            System.out.println("1: registrar usuario\n2: iniciar sesion");
            op= teclado.nextInt();
            switch (op) {
                case 1 -> ManejoDeUsuarios.inputRegistarUsuario();
                case 2 -> ManejoDeUsuarios.inputIniciarSesion();
            }

        }catch(IllegalStateException e){
            System.out.println("error: "+e);
        }




    }
}

