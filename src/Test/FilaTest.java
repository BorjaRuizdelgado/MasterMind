package Test;


import Domain.Fila;

import java.util.Scanner;

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
                    num = in.nextInt();
                    if (num == 1) imprimeColores();
                    if (num == 2) imprimeRespuestas();
                    if (num == 3) insertarColores();
                    if (num == 4) insertarRespuesta();
                }
            }
            else System.out.print("Tamaño no válido\n");
        }
    }

    private static void imprimeColores(){
        System.out.println(fila.getColores().codigo);
    }
    private static void imprimeRespuestas(){
        System.out.println(fila.getRespuestas().respuesta);
    }
}
