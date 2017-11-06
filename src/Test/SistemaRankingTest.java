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
    private static String []names = new String[]{"Carl",
            "Camden",
            "Tucker",
            "Galvin",
            "Holmes",
            "Aquila",
            "Colt",
            "Russell",
            "Silas",
            "Yasir",
            "Gary",
            "Rashad",
            "Wade",
            "Vincent",
            "Jonah",
            "Kirk",
            "Dale",
            "Vladimir",
            "Oliver",
            "Allen",
            "Kaseem",
            "Tanek",
            "Axel",
            "Dolan",
            "Darius",
            "Dillon",
            "Lamar",
            "Harrison",
            "Lucius",
            "Chandler",
            "Ignatius",
            "Rogan",
            "Kane",
            "Gray",
            "Lawrence",
            "Troy",
            "Ian",
            "Bevis",
            "Hamish",
            "Ronan",
            "Lyle",
            "Garth",
            "Laith",
            "Phillip",
            "Kibo",
            "Nero",
            "Hoyt",
            "Blake",
            "Lucian",
            "Edward",
            "Lance",
            "Baker",
            "Lamar",
            "Levi",
            "Reed",
            "Amos",
            "Colorado",
            "Giacomo",
            "Clinton",
            "Ulric",
            "Baxter",
            "Curran",
            "Kane",
            "Rigel",
            "Wing",
            "Kermit",
            "Lee",
            "Solomon",
            "Cooper",
            "Jack",
            "Chandler",
            "Zeph",
            "Berk",
            "Ignatius",
            "Justin",
            "Vance",
            "Emery",
            "Odysseus",
            "Beck",
            "Graiden",
            "William",
            "Sawyer",
            "Finn",
            "Reese",
            "Caesar",
            "Griffith",
            "Jared",
            "Bruno",
            "Caldwell",
            "Ross",
            "Jackson",
            "Aristotle",
            "Noble",
            "Griffin",
            "Tucker",
            "Jesse",
            "Rigel",
            "Kareem",
            "Ira",
            "Joel"};


    private static List<Info> getRandomInfo(){
        List<Info> Return = new ArrayList<>();
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < 10; i++) {
            String name = names[random.nextInt(100)];
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
        Scanner in = new Scanner(System.in);
        Console.println("Aquí probamos la Clase SistemaRanking.");
        Console.print("Obteniendo instancia y creando el objeto... ");
        sistemaRanking = SistemaRanking.getInstance();
        Console.println("[Hecho]", "red");

        Console.print("Insertando información random a Ranking fácil... ");
        actualiza("facil");
        Console.println("[Hecho]", "red");

        Console.print("Insertando información random a Ranking normal... ");
        actualiza("normal");
        Console.println("[Hecho]", "red");

        Console.print("Insertando información random a Ranking difícil... ");
        actualiza("dificil");
        Console.println("[Hecho]", "red");

        System.out.println(sistemaRanking.getRankingFacil());
    }
}
