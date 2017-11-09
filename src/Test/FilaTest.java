package Test;



import Domain.Codigo;
import Domain.Fila;
import Domain.Respuesta;
import static Util.Console.*;

import java.util.Scanner;

/**
 * Fila Test
 * Implementa un Main para poder probar de manera interactiva la clase.
 * @author borja
 */
public class FilaTest {
    public static Fila fila;

    public static void main(String[] args) {
        System.out.print("Aquí probamos la Clase Fila\n");
        while (true) {
            System.out.print("Por favor, introduce el tamaño de la Fila deseada: 4 o 6\n");
            Scanner in = new Scanner(System.in);
            int num = in.nextInt();
            if (num == 4 || num == 6) {
               fila = new Fila(num);
                while(true) {
                    System.out.println("INTRODUCE: 1 para ver que colores tiene la fila, 2 para ver las respuestas,\n" +
                            "3 para insertar colores, 4 para insertar una respuesta");
                    int numerito = in.nextInt();
                    if (numerito == 1) imprimeColores();
                    if (numerito == 2) imprimeRespuestas();
                    if (numerito == 3) insertarColores(num);
                    if (numerito == 4) insertarRespuesta(num);
                }
            }
            else System.out.print("Tamaño no válido\n");
        }
    }

    /**
     * Imprime los colores de una fila determinada
     */
    private static void imprimeColores(){
        System.out.println(fila.getColores().codigo);
    }

    /**
     * Imprime las respuestas de una fila determinada
     */
    private static void imprimeRespuestas(){
        System.out.println(fila.getRespuestas().respuesta);
    }

    /**
     * Permite insertar los colores en una fila determinada
     * @param num sirve para agilizar el proceso de insercion ya que se dispone de el en el main
     */
    private static void insertarColores(int num){
        println("Introduce una serie de colores en forma de numeros");
        Codigo code = new Codigo(num);
        Scanner s = new Scanner(System.in);
        for(int i = 0; i < num; i++) {
            int numeroAInsertar = s.nextInt();
            code.codigo.add(numeroAInsertar);
        }
        fila.setColores(code);
    }

    /**
     * Permite insertar las respuestas en una fila determinada
     * @param num sirve para agilizar el proceso de insercion ya que se dispone del el en el main
     */
    private static void insertarRespuesta(int num){
        println("Intorduce una serie de 8s (negro) y 7s (blanco)\n");
        Respuesta code = new Respuesta(num);
        Scanner s = new Scanner(System.in);
        for(int i = 0; i < num; i++) {
            int numeroAInsertar = s.nextInt();
            code.respuesta.add(numeroAInsertar);
        }
        fila.setRespuestas(code);
    }
}
