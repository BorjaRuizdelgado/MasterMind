package Test;

import Data.GestionSistemaRanking;
import Domain.SistemaRanking;

public class GestionSistemaRankingTest {
    public static void main(String[] args){
        GestionSistemaRanking gestionSistemaRanking = GestionSistemaRanking.getInstance();

        SistemaRanking sistemaRanking = SistemaRanking.getInstance();
        sistemaRanking.addNewPuntuation("Pole", 99, "asd", "asdaasd");

        gestionSistemaRanking.guardar();
    }
}
