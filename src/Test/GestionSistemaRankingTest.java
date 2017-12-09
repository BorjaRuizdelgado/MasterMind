package Test;

import Data.GestionSistemaRanking;
import Domain.Info;
import Domain.SistemaRanking;

import java.util.List;

public class GestionSistemaRankingTest {
    public static void main(String[] args){
        GestionSistemaRanking gestionSistemaRanking = GestionSistemaRanking.getInstance();

        SistemaRanking sistemaRanking = SistemaRanking.getInstance();
        sistemaRanking.addNewPuntuation("Pole", 99, "asd", "Facil");

        //gestionSistemaRanking.guardar();

        sistemaRanking.addNewPuntuation("Fakeee", 666, "asd", "Facil");

        gestionSistemaRanking.cargar();

        List<Info> facil = sistemaRanking.getRankingFacilInfo();
        for (Info i: facil) {
            System.out.println(i.toString() + "*********");
        }
    }
}
