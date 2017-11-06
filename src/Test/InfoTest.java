package Test;

/**
 * @author Omar
 */

import Domain.Info;
import Util.Console;

import java.util.Scanner;

/**
 * Implementa un Main para poder probar de manera interactiva la clase.
 */
public class InfoTest {

    private static Info info;

    private static void opcion1(){
        String usuario = info.getUsuario();
        Console.print("El nombre del usuario es: ");
        Console.println(usuario, "green");
    }

    private static void opcion2(){
        int puntuacion= info.getPuntuacion();
        Console.print("La puntuación es: ");
        Console.println(String.valueOf(puntuacion), "green");
    }

    private static void opcion3(){
        String fecha = info.getFecha();
        Console.print("La fecha es: ");
        Console.println(fecha, "green");
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Console.println("Aquí probamos la Clase Info.");
        boolean exit = false;
        while (!exit) {
            String usuario, fecha;
            int puntuacion;

            Console.println("Por favor, introduzca los datos para crear una instancia.\n");
            Console.print("Nombre de usuario (Máximo de 8 carácteres): ", "green");
            usuario = in.next();
            Console.print("Puntuación (Tiene que ser un número entre 0 y 10000): ", "green");
            puntuacion = in.nextInt();
            Console.print("Fecha (Sigue el formato: 'dd-mm-aa'): ", "green");
            fecha = in.next();
            Console.println("Creando...");
            info = new Info(usuario, puntuacion, fecha);

            int option = 0;
            while(option != 4){
                Console.println("Escoge entre estas opciones:");
                Console.println("[1] Obtén el usuario.");
                Console.println("[2] Obtén la puntuación.");
                Console.println("[3] Obtén la fecha.");
                Console.println("[4] Crear otro usuario.");
                Console.println("[5] Salir.");
                do {
                    option = in.nextInt();
                }while (option > 5 || option < 1);

                if (option == 1) opcion1();
                else if(option == 2) opcion2();
                else if(option == 3) opcion3();
                else if(option == 5){
                    option = 4;
                    exit = true;
                }
            }
        }
    }
}
