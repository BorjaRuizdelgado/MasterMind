package Data;

import Domain.Usuario;

import java.io.*;
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
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            finder = (Map<String, Usuario>) objectInputStream.readObject();

            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    private void guardarFinder(){
        try {
            FileOutputStream fos = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(finder);
            oos.close();
            fos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Usuario cargar(String id){
        return finder.get(id);
    }

    public void guardar(Usuario usuario){
        finder.put(usuario.getNombre(),usuario);
    }

    public boolean exists(String usuario){
        return finder.containsKey(usuario);
    }
}
