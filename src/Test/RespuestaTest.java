package Test;

import Domain.Respuesta;

import java.util.Scanner;

import static Util.Console.*;

/**
 * Respuesta Test
 * Implementa un Main para poder probar de manera interactiva la clase.
 * @author Borja
 */
public class RespuestaTest {

    public static void main(String[] args) {
        println("Aquí probamos la Clase Respuesta");
        while (true) {
            println("Por favor, introduce el tamaño de la respuesta deseada: 4 o 6");
            Scanner in = new Scanner(System.in);
            int num = in.nextInt();
            if (num == 4 || num == 6) {
                println("Por favor introduce la respuesta de tamaño "+num+" con 0 (Vacio), 7 (Blanco) o 8 (Negro) uno a uno");
                Respuesta test = new Respuesta(num);
                for (int i = 0; i < num; i++) {
                    int code = in.nextInt();
                    test.respuesta.add(code);
                }
                println("Esta es tu respuesta:");
                System.out.println(test.respuesta);

                println("Vamos a comprobar la funcionalidad que nos permite comprobar si dos respuestas del mastermind\n" +
                        "son iguales la respuesta que has introuducido con una nueva");
                println("Por favor introduce otra respuesta de tamaño "+num+" con 0 (Vacio), 7 (Blanco) o 8 (Negro) uno a uno");
                Respuesta test2 = new Respuesta(num);
                for (int i = 0; i < num; i++) {
                    int code = in.nextInt();
                    test2.respuesta.add(code);
                }
                if(test.equals(test2)) println("Son iguales");
                else println("Son diferentes");

                println("Ahora miramos si la respuesta es de tipo ganadora y la traducimos a string");
                if(test.esGanadora()) println("Ganadora");
                else println("- No ganadora");
                println("- En string: "+test.toString());
            }
            else System.out.print("Tamaño no válido\n");
        }
    }
}
