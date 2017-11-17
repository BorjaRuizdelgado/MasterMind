package Test;

import Domain.Info;
import Domain.SistemaRanking;
import Util.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * SistemaRanking Test
 * Implementa un Main para poder probar de manera interactiva la clase.
 * @author Omar
 */
public class SistemaRankingTest {

    private static SistemaRanking sistemaRanking;

    private static Random random;

    /**
     * Nombres que se usan para generar las listas.
     */
    private static String []names = new String[]{
            "Yasuo",
            "Camden",
            "Janna",
            "Galvin",
            "Vayne",
            "Marta",
            "Colt",
            "Crosak",
            "Silas",
            "Zed",
            };

    /**
     * Función que nos sirve para mostrar una lista de tipo 'Info' de manera estética.
     * @param ranking Es la lista que enseñaremos en pantalla.
     */
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

    /**
     * Con esta función, generamos una lista de tipo 'Info' con datos aleatorios.
     * @return Devolvemos una lista de tamaño diez, cada instancia de clase 'Info' contiene un nombre cogido del
     * atributo privado 'names', una puntuación de 0 a 9999, y una fecha del random del 01-1-70 al 30-12-90 (La fecha
     * generado puede no respetar la realidad.)
     */
    private static List<Info> getRandomInfo(){
        List<Info> Return = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String name = names[random.nextInt(10)];
            int puntuacion = random.nextInt(10000);
            int day = 1 + random.nextInt(31);
            int month = 1 + random.nextInt(12);
            int year = 70 + random.nextInt(21);
            String data = String.valueOf(day) + "-" + String.valueOf(month) + "-" + String.valueOf(year);
            Return.add(new Info(name, puntuacion, data));
        }

        return Return;
    }

    /**
     * Función que actualiza la lista de la instáncia 'sistemaRanking', dependiendo de lo que indica el parámetro 'which'.
     * El ranking al que haga referencia 'which', se le añadirá información random.
     * @param which Parámetro que indica el tipo de ranking (Fácil, normal o difícil).
     */
    private static void actualiza(String which){
        switch (which){
            case "facil":
                List<Info> random1 = getRandomInfo();
                sistemaRanking.setRankingFacil(random1);
                break;
            case "normal":
                List<Info> random2 = getRandomInfo();
                sistemaRanking.setRankingMedio(random2);
                break;
            case "dificil":
                List<Info> random3 = getRandomInfo();
                sistemaRanking.setRankingDificil(random3);
                break;
        }
    }

    /**
     * Añade una nueva puntuación al tipo de ranking introducido por consola.
     */
    private static void anade(){
        Scanner in = new Scanner(System.in);
        Console.println("Inserta un nombre de usuario");
        String nombre = in.next();
        Console.println("Inserta la puntuación");
        int puntuacion = in.nextInt();
        Console.println("Inserta una fecha [Formato: dd-mm-yy]");
        String fecha = in.next();
        Console.println("¿Qué dificultad? [Facil/Medio/Dificil]");
        String modo = " ";
        do{
            if(!modo.equals(" ")) Console.println("Escribe: Facil, Medio o Dificil");
            modo = in.next();
        }
        while (!modo.equals("Facil") && !modo.equals("Medio") && !modo.equals("Dificil"));
        Console.print("Insertando información... ");
        sistemaRanking.addNewPuntuation(nombre, puntuacion, fecha, modo);
        Console.println("[Hecho]", "green");
    }

    /**
     * Este main muestra un menú en pantalla, en el que mostramos diversas opciones que pueden ser escogidas. En este
     * menú, podemos mostrar la información de los rankings (que se generan al principio del programa), también filtrar
     * las información de los rankings por nombres introducidos por el usuario.
     * @param args -
     */
    public static void main(String[] args) {
        try {
            random = new Random(System.currentTimeMillis());
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
            while (option != 8) {
                String menu = "Menú: Selecciona una de estas opciones: \n" +
                        "[1] Mostrar ranking fácil \n" +
                        "[2] Mostrar ranking normal \n" +
                        "[3] Mostrar ranking dificil \n" +
                        "[4] Filtrar ranking fácil por nombre \n" +
                        "[5] Filtrar ranking normal por nombre \n" +
                        "[6] Filtrar ranking díficil por nombre \n" +
                        "[7] Añadir nueva puntuación \n" +
                        "[8] Salir";
                Console.println(menu);

                option = in.nextInt();
                if (option == 1) muestra(sistemaRanking.getRankingFacil());
                else if (option == 2) muestra(sistemaRanking.getRankingMedio());
                else if (option == 3) muestra(sistemaRanking.getRankingDificil());
                else if (option == 4 || option == 5 || option == 6) {
                    Console.println("Introduce un nombre a filtrar", "blue");
                    String name = in.next();
                    switch (option) {
                        case 4:
                            muestra(sistemaRanking.getRankingFacil(name));
                            break;
                        case 5:
                            muestra(sistemaRanking.getRankingMedio(name));
                            break;
                        case 6:
                            muestra(sistemaRanking.getRankingDificil(name));
                            break;
                    }
                }
                else if(option == 7) anade();
            }
        }
        catch (Exception e){
            Console.println(e.getMessage() + " [ERROR]", "red");
        }
    }
}
