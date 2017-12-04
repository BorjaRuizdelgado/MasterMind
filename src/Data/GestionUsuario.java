package Data;

import Domain.Excepciones.ExcepcionUsuarioInexistente;
import Domain.Usuario;

import java.io.*;
import java.util.Map;

public class GestionUsuario {
    private String path;
    private Map<String, Usuario> finder;
    private static GestionUsuario uniqueInstance;

    private GestionUsuario() {
        path = System.getProperty("user.dir") + "/Data/Users/GestionUsuarios.obj";

    }

    public static GestionUsuario getInstance() {
        return uniqueInstance;
    }

    public void cargarFinder(){
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            finder = (Map<String, Usuario>) objectInputStream.readObject();

            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    public void guardarFinder(){
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

    public Usuario cargar(String id) throws ExcepcionUsuarioInexistente {
        Usuario aux = finder.get(id);
        if (aux == null) throw new ExcepcionUsuarioInexistente();
        return finder.get(id);
    }

    public void guardar(Usuario usuario){
        finder.put(usuario.getNombre(),usuario);
    }

    public boolean exists(String usuario){
        return finder.containsKey(usuario);
    }
}
