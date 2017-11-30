package Data;

import Domain.Usuario;

import java.util.Map;

public class GestionUsuario {
    String path;
    Map<String, Usuario> finder;
    private static GestionUsuario uniqueInstance;

    private GestionUsuario() {
        path = "aaa";

    }



    public static GestionUsuario getInstance() {
        return uniqueInstance;
    }

    private void cargarFinder(){

    }

    private void guardarFinder(){

    }

    public Usuario cargar(String id){

    }

    public void guardar(Usuario usuario){

    }

    public boolean exists(String id){
        return false;
    }
}
