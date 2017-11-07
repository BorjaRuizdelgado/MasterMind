package Test;


import Domain.Codigo;
import Domain.Info;
import Domain.Respuesta;
import Domain.SistemaRanking;
import Util.Console;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Omar
 */

/**
 * Implementa un Main para poder probar de manera interactiva la clase.
 */

public class SistemaRankingTest {

    private static SistemaRanking sistemaRanking;
    private static String []names = new String[]{
            "Carl",
            "Camden",
            "Tucker",
            "Galvin",
            "Holmes",
            "Aquila",
            "Colt",
            "Russell",
            "Silas",
            "Yasir",
            };


    private static void muestra(List<Info> ranking){
        if(ranking.size() != 0) {
            for (Info info : ranking) {
                Console.println("#########################", "red");
                Console.println(info.toString());
            }
            Console.println("#########################", "red");
        }
        else{
            Console.println("####### Sin datos #######", "red");
        }
    }

    private static List<Info> getRandomInfo(){
        List<Info> Return = new ArrayList<>();
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < 10; i++) {
            String name = names[random.nextInt(10)];
            int puntuacion = random.nextInt(10000);
            int day = 1 + random.nextInt(31);
            int month = 1 + random.nextInt(12);
            int year = 70 + random.nextInt(20);
            String data = String.valueOf(day) + "-" + String.valueOf(month) + "-" + String.valueOf(year);
            Return.add(new Info(name, puntuacion, data));
        }

        return Return;
    }

    private static void actualiza(String which){
        switch (which){
            case "facil":
                List<Info> random1 = getRandomInfo();
                sistemaRanking.setRankingFacil(random1);
                break;
            case "normal":
                List<Info> random2 = getRandomInfo();
                sistemaRanking.setRankingNormal(random2);
                break;
            case "dificil":
                List<Info> random3 = getRandomInfo();
                sistemaRanking.setRankingDificil(random3);
                break;
        }
    }
    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(System.in);
            Console.println("Aquí probamos la Clase SistemaRanking.");
            Console.print("Obteniendo instancia y creando el objeto... ");
            sistemaRanking = SistemaRanking.getInstance();
            Console.println("[Hecho]", "green");

            Console.print("Insertando información random a Ranking fácil... ");
            actualiza("facil");
            Console.println("[Hecho]", "green");

            Console.print("Insertando información random a Ranking normal... ");
            actualiza("normal");
            Console.println("[Hecho]", "green");

            Console.print("Insertando información random a Ranking difícil... ");
            actualiza("dificil");
            Console.println("[Hecho]\n", "green");

            int option = 0;
            while (option != 7) {
                Console.println("Menú: Selecciona una de estas opciones:");
                Console.println("[1] Mostrar ranking fácil");
                Console.println("[2] Mostrar ranking normal");
                Console.println("[3] Mostrar ranking dificil");
                Console.println("[4] Filtrar ranking fácil por nombre");
                Console.println("[5] Filtrar ranking normal por nombre");
                Console.println("[6] Filtrar ranking díficil por nombre");
                Console.println("[7] Salir");


                option = in.nextInt();
                if (option == 1) muestra(sistemaRanking.getRankingFacil());
                else if (option == 2) muestra(sistemaRanking.getRankingNormal());
                else if (option == 3) muestra(sistemaRanking.getRankingDificil());
                else if (option == 4 || option == 5 || option == 6) {
                    Console.println("Introduce un nombre a filtrar", "blue");
                    String name = in.next();
                    switch (option) {
                        case 4:
                            muestra(sistemaRanking.getRankingFacil(name));
                            break;
                        case 5:
                            muestra(sistemaRanking.getRankingNormal(name));
                            break;
                        case 6:
                            muestra(sistemaRanking.getRankingDificil(name));
                            break;
                    }
                }
            }
        }
        catch (Exception e){
            Console.println(e.getMessage() + " [ERROR]", "red");
        }
    }
}
