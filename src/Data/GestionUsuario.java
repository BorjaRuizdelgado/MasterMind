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
        path = System.getProperty("user.dir") + "\\Data\\Users\\GestionUsuarios.obj";
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
        File folder = new File(System.getProperty("user.dir") + "\\Data\\Users");
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
            System.out.println("Problema al crear el fichero del finder.");
        }
    }

    /**
     * Carga el finder.
     */
    private void cargarFinder(){
        try {
            File folder = new File (path);
            if(!folder.exists()) {
                createDirectory();
                createFile();
                finder = new HashMap<>();
            }
            else {
                FileInputStream fileInputStream = new FileInputStream(path);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                finder = (Map<String, Usuario>) objectInputStream.readObject();

                objectInputStream.close();
                fileInputStream.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    /**
     * Guarda el finder.
     */
    private void guardarFinder(){
        try {
            File folder = new File (path);
            if(!folder.exists()) {
                createDirectory();
                createFile();
            }

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

    public boolean existe(String usuario){
        return finder.containsKey(usuario);
    }

    public boolean existeAlguno(){
        return finder.size() != 0;
    }
}
