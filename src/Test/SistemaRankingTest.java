package Test;


import Domain.Codigo;
import Domain.Respuesta;
import Domain.SistemaRanking;
import Util.Console;

import java.util.Scanner;

/**
 * @author Omar
 */

/**
 * Implementa un Main para poder probar de manera interactiva la clase.
 */

public class SistemaRankingTest {

    private static SistemaRanking sistemaRanking = SistemaRanking.getInstance();
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Console.println("Aquí probamos la Clase SistemaRanking.");
    }
}
