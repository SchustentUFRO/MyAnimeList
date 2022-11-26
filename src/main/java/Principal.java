import DataAndCollection.Conectar;


public class Principal {

    public static void main(String[] args) throws Exception {
        try{
            Conectar.conectar();
        }catch(IllegalStateException e){
            System.out.println("error: "+e);
        }




    }
}

