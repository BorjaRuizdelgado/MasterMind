package Data;

import Domain.SistemaRanking;

import java.io.*;

public class GestionSistemaRanking {
    private String path;
    private static GestionSistemaRanking uniqueInstance;

    private GestionSistemaRanking(){
        path = System.getProperty("user.dir") + "/Data/GSR/GestionSistemaRanking.obj";
    }

    public static GestionSistemaRanking getInstance() {
        if(uniqueInstance == null)
            uniqueInstance = new GestionSistemaRanking();
        return uniqueInstance;
    }

    private void createDirectory(){
        File folder = new File(System.getProperty("user.dir") + "/Data/GSR");
        folder.mkdirs();
    }

    private void createFile(){
        File file = new File(path);
        try {
            file.createNewFile();
        }
        catch (IOException e1) {
            e1.printStackTrace();
        }
    }

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
