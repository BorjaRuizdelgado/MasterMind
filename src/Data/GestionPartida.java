package Data;

import Domain.Partida;

import java.io.*;


public class GestionPartida {
    private static GestionPartida uniqueInstance;
    private String path;

    private GestionPartida() {
        path = System.getProperty("user.dir") + "/Data/Games";
    }

    public static GestionPartida getInstance() {
        if (uniqueInstance == null) uniqueInstance = new GestionPartida();
        return uniqueInstance;
    }

    private String getPath(String name) {
        return path+"/"+name+".obj";
    }

    private void createDirectory(){
        File folder = new File(path);
        folder.mkdirs();
    }


    private void createFile(String name){
        File file = new File(getPath(name));
        try {
            file.createNewFile();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Carga la partida cuyo identificador se ha pasado por parámetro. Se elimina el fichero de la partida guardada.
     * @param partida identificador de la partida a cargar
     * @return partida a cargar
     */
    public Partida cargarPartida(String partida) {
        Partida p;
        try {
            FileInputStream fileInputStream = new FileInputStream(getPath(partida));
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            p = (Partida) objectInputStream.readObject();

            objectInputStream.close();
            fileInputStream.close();

            //File file = new File(getPath(partida));
            //file.delete();

            return p;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Se guarda la partida pasada por parámetro. Si no existe el directorio se crea.
     * @param p partida a guardar.
     */
    public void guardarPartida(Partida p) {
        try {
            File folder = new File (path);
            if(!folder.exists()) createDirectory();
            eliminarPartida(p.getId());
            createFile(p.getId());

            FileOutputStream fos = new FileOutputStream(getPath(p.getId()));
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(p);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void eliminarPartida(String p) {
        File file = new File(getPath(p));
        file.delete();
    }
}
