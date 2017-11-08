package Test;

import Domain.Codigo;
import Util.Comparator;
import Util.Console;
import java.util.*;

/**
 * @author Omar
 */

/**
 * Clase auxiliar que compara dos códigos y que nos servirá para ordenar una lista de ellos.
 */
public class ComparatorTest {

    private static void showList(List<Codigo> codigos){
        for (int i = 0; i < codigos.size(); i++) {
            System.out.println(codigos.get(i).codigo);
        }
    }

    private static List<Codigo> generateRandomCodes(){
        List<Codigo> Return = new ArrayList<>();
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < 10; i++) {
            Codigo aux = new Codigo(4);
            for (int j = 0; j < 4; j++) {
                aux.codigo.add(1 + random.nextInt(6));
            }
            Return.add(aux);
        }
        return Return;
    }

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        Console.println("Aquí probamos el funcionamiento la Clase Comparator.");
        Console.print("Generando lista de códigos aleatorios...");
        List<Codigo> codigos = generateRandomCodes();
        Console.println(" [Hecho]", "green");
        int option = 0;
        while (option != 3){
            String menu = "\n[1] Muestra la lista de códigos generados \n" +
                    "[2] Ordena la lista de códigos generados \n" +
                    "[3] Salir";
            Console.println(menu);

            option = in.nextInt();
            if (option == 1){
                showList(codigos);
            }
            else if(option == 2){
                Console.print("Ordenando haciendo uso de la clase 'Comparator'...");
                Collections.sort(codigos, new Comparator());
                Console.println(" [Hecho]", "green");
            }
        }
    }
}
