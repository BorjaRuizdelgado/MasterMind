package Test;

import Domain.Respuesta;

import java.util.Scanner;

/**
 * @author ISA
 */
/**
 * Implementa un Main para poder probar de manera interactiva la clase.
 */
public class RespuestaTest {

    public static void main(String[] args) {
        System.out.print("Aquí probamos la Clase Respuesta\n");
        while (true) {
            System.out.print("Por favor, introduce el tamaño de la respuesta deseada: 4 o 6\n");
            Scanner in = new Scanner(System.in);
            int num = in.nextInt();
            if (num == 4 || num == 6) {
                System.out.println("Por favor introduce una serie de 0 == Vacio, un 7 == Blanco o un 8 == Negro\n");
                Respuesta test = new Respuesta(num);
                for (int i = 0; i < num; i++) {
                    int code = in.nextInt();
                    test.respuesta.add(code);
                }
                System.out.print("Esta es tu respuesta:\n");
                System.out.print(test.respuesta +"\n");
                System.out.println("Vamos a comporbar la funcionalidad que nos permite comprobar si dos respuestas del mastermind\n" +
                        "son iguales la respuesta que has introuducido con una nueva\n");
                System.out.println("Por favor introduce una serie de 0 == Vacio, un 7 == Blanco o un 8 == Negro\n");
                Respuesta test2 = new Respuesta(num);
                for (int i = 0; i < num; i++) {
                    int code = in.nextInt();
                    test2.respuesta.add(code);
                }
                if(test.equals(test2)) System.out.println("son iguales\n");
                else System.out.println("Són diferentes\n");
                System.out.println("Ahora miramos si la respuesta es de tipo ganadora y la traducimos a strings\n");
                if(test.esGanadora()) System.out.println("Ganadora\n");
                else System.out.println("No ganadora\n");
                System.out.println(test.toString());
            }
            else System.out.print("Tamaño no válido\n");
        }
    }
}
