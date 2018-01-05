package Data;

import Domain.SistemaRanking;

import java.io.*;

/**
 * Clase GestionSistemRanking
 * Gestor de la capa de datos que gestiona el ranking
 * @author Omar
 */
public class GestionSistemaRanking {
    private String path;
    private static GestionSistemaRanking uniqueInstance;

    /**
     * Creadora GestionSistemaRanking
     * Inicia el path donde se guarda el ranking
     */
    private GestionSistemaRanking(){
        path = System.getProperty("user.dir") + "/Data/GSR/GestionSistemaRanking.obj";
    }

    /**
     * Obtener la instancia única de GestionSistemaRanking
     * @return instancia unica
     */
    public static GestionSistemaRanking getInstance() {
        if(uniqueInstance == null)
            uniqueInstance = new GestionSistemaRanking();
        return uniqueInstance;
    }

    /**
     * Crea el directorio donde se guarda el ranking.
     */
    private void createDirectory(){
        File folder = new File(System.getProperty("user.dir") + "/Data/GSR");
        folder.mkdirs();
    }

    /**
     * Crea el archivo que guarda el ranking si no existe.
     */
    private void createFile(){
        File file = new File(path);
        try {
            file.createNewFile();
        }
        catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * Guarda el ranking en el directorio que contiene la clase.
     */
    public void guardar(){
        SistemaRanking sistemaRanking = SistemaRanking.getInstance();

        try {
            createDirectory();
            createFile();

            FileOutputStream fileOutputStream = new FileOutputStream(path);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(sistemaRanking);

            objectOutputStream.close();
            fileOutputStream.close();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Carga sistemaRanking con los datos que contiene el archivo que tenemos apuntado en la clase como atributo.
     */
    public void cargar(){
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            SistemaRanking.setInstance((SistemaRanking) objectInputStream.readObject());

            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException e) {
            SistemaRanking.getInstance().clear();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
