package Data;

import Domain.SistemaRanking;

import java.io.*;

public class GestionSistemaRanking {
    private String path;
    private static GestionSistemaRanking uniqueInstance;

    private GestionSistemaRanking(){
        path = System.getProperty("user.dir") + "/Data/GestionSistemaRanking.obj";
    }

    public static GestionSistemaRanking getInstance() {
        if(uniqueInstance == null)
            uniqueInstance = new GestionSistemaRanking();
        return uniqueInstance;
    }

    public void guardar(){
        SistemaRanking sistemaRanking = SistemaRanking.getInstance();

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(sistemaRanking);

            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
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
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
