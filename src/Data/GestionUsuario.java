package Data;

import Domain.Excepciones.ExcepcionUsuarioInexistente;
import Domain.Usuario;

import java.io.*;
import java.util.*;

/**
 * Clase Gestion Usuario
 * Gestor de la capa de datos que gestiona los usuarios.
 * @author ISA
 */
public class GestionUsuario {
    private static String path;
    private static Map<String, Usuario> finder;
    private static GestionUsuario uniqueInstance;

    /**
     * Creadora Gestion Usuario
     * Inicia el path donde se guarda el map que contiene los usuarios.
     */
    private GestionUsuario() {
        path = System.getProperty("user.dir") + "/Data/Users/GestionUsuarios.obj";
        cargarFinder();
        guardarFinder();
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
            System.out.println("Problema al crear el fichero del finder.");
        }
    }

    /**
     * Carga el finder del directorio
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
     * Guarda el finder en el directorio.
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

    /**
     * Carga el usuario cuyo nombre de usuario se ha pasado por parámetro.
     * @param username nombre del usuario a cargar
     * @return usuario cuyo nombre se ha pasado por parámetro
     * @throws ExcepcionUsuarioInexistente el nombre de usuario no corresponde a ningún usuario del sistema.
     */
    public Usuario cargar(String username) throws ExcepcionUsuarioInexistente {
        Usuario aux = finder.get(username);
        if (aux == null) throw new ExcepcionUsuarioInexistente("El nombre de usuario no existe.");
        return finder.get(username);
    }

    /**
     * Guarda el usuario que se ha pasado por parámetro en el finder.
     * @param usuario usuario a guardar.
     */
    public void guardar(Usuario usuario){
        finder.put(usuario.getNombre(),usuario);
        guardarFinder();
    }

    /**
     * Elimina el usuario cuyo nombre de usuario se ha pasado por parámetro.
     * @param username nombre del usuario a eliminar
     */
    public void eliminar(String username) {
        finder.remove(username);
        guardarFinder();
    }

    /**
     * Devuelve si existe un usuario con el nombre de usuario pasado por parámetro
     * @param username nombre del usuario a comprobar
     * @return cierto si existe; falso si no existe.
     */
    public boolean existe(String username){
        return finder.containsKey(username);
    }

    /**
     * Devuelve si hay algún usuario creado en el sistema.
     * @return cierto si existe alguno; falso si no existe.
     */
    public boolean existeAlguno(){
        return finder.size() != 0;
    }

    /**
     * Devuelve una lista de todos los nombres de usuario que hay guardados en el sistema
     * @return lista de todos los nombres de usuario
     */
    public List<String> getTodos() {
        List<String> nombres = new ArrayList<>();
        if (finder.size() == 0) return null;
        for (Map.Entry<String, Usuario> entry : finder.entrySet()) {
            nombres.add(entry.getKey());
        }
        return nombres;
    }
}
