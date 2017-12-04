package Data;

import Domain.Excepciones.ExcepcionUsuarioInexistente;
import Domain.Usuario;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class GestionUsuario {
    private static String path;
    private static Map<String, Usuario> finder;
    private static GestionUsuario uniqueInstance;

    private GestionUsuario() {
        path = System.getProperty("user.dir") + "/Data/Users/GestionUsuarios.obj";
        cargarFinder();
    }

    /**
     * Obtener la instancia unica de GestionUsuario
     * @return instancia unica
     */
    public static GestionUsuario getInstance() {
        if (uniqueInstance == null) uniqueInstance = new GestionUsuario();
        return uniqueInstance;
    }

    /**
     * Crea el directorio si no está creado ya.
     */
    private void createDirectory(){
        File folder = new File(System.getProperty("user.dir") + "/Data/Users");
        folder.mkdirs();
    }

    /**
     * Crea el fichero del finder si no existe.
     */
    private void createFile(){
        File file = new File(path);
        try {
            file.createNewFile();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Carga el finder.
     */
    private void cargarFinder(){
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            finder = (Map<String, Usuario>) objectInputStream.readObject();

            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException e) { //si no encuentra el fichero, que lo cree y haga un finder vacío.
            createDirectory();
            createFile();
            finder = new HashMap<>();
        } catch ( ClassNotFoundException e) {
            System.out.println("Clase not working.");
        }


    }

    /**
     * Guarda el finder.
     */
    private void guardarFinder(){
        try {
            createDirectory();
            createFile();

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
        guardarFinder();
    }

    public boolean exists(String usuario){
        return finder.containsKey(usuario);
    }

    public boolean existsAny(){
        if (finder.size()==0)
            return false;
        return true;
    }
}
