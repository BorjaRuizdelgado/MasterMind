package Data;

import Domain.Partida;

import java.io.*;

/**
 * Clase Gestion Partida
 * Gestor de la capa de datos que gestiona las partidas guardadas.
 * @author ISA
 */
public class GestionPartida {
    private static GestionPartida uniqueInstance;
    private String path;

    /**
     * Creadora Gestion Partida
     * Inicia el path donde se guardan las partidas.
     */
    private GestionPartida() {
        path = System.getProperty("user.dir") + "/Data/Games";
    }

    /**
     * Gestion Partida es un singleton y por ello se debe acceder a él mediante esta función.
     * @return uniqueInstance de GestionPartida
     */
    public static GestionPartida getInstance() {
        if (uniqueInstance == null) uniqueInstance = new GestionPartida();
        return uniqueInstance;
    }

    /**
     * Crea el path con el identificador de partida que le pasen por parámetro.
     * @param idPartida identificador de la partida cuyo path se quiere obtener
     * @return path del fichero que la partida.
     */
    private String getPath(String idPartida) {
        return path+"/"+idPartida+".obj";
    }

    /**
     * Crea el directorio para guardar partidas.
     */
    private void createDirectory(){
        File folder = new File(path);
        folder.mkdirs();
    }

    /**
     * Crea el fichero de la partida cuyo identificador se ha pasado por parámetro.
     * @param idPartida identificador de la partida
     */
    private void createFile(String idPartida){
        File file = new File(getPath(idPartida));
        try {
            file.createNewFile();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Carga la partida cuyo identificador se ha pasado por parámetro.
     * @param idPartida identificador de la partida a cargar
     * @return partida a cargar
     */
    public Partida cargar(String idPartida) {
        Partida p;
        try {
            FileInputStream fileInputStream = new FileInputStream(getPath(idPartida));
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            p = (Partida) objectInputStream.readObject();

            objectInputStream.close();
            fileInputStream.close();

            return p;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Se guarda la partida pasada por parámetro. Si no existe el directorio se crea.
     * @param partida partida a guardar.
     */
    public void guardar(Partida partida) {
        try {
            File folder = new File (path);
            if(!folder.exists()) createDirectory();
            eliminar(partida.getId());
            createFile(partida.getId());

            FileOutputStream fos = new FileOutputStream(getPath(partida.getId()));
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(partida);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * Elimina el fichero de la partida cuyo identificador se ha pasado por parámetro.
     * @param idPartida identificador de la partida a eliminar
     */
    public void eliminar(String idPartida) {
        File file = new File(getPath(idPartida));
        file.delete();
    }
}
