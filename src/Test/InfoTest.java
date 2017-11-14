package Test;

import Domain.Info;
import Util.Console;

import java.util.Scanner;

/**
 * Info Test
 * Implementa un Main para poder probar de manera interactiva la clase.
 * @author Omar
 */
public class InfoTest {

    private static Info info;

    /**
     * Imprime el usuario
     */
    private static void opcion1(){
        String usuario = info.getUsuario();
        Console.print("El nombre del usuario es: ");
        Console.println(usuario, "green");
    }

    /**
     * Imprime la puntuación
     */
    private static void opcion2(){
        int puntuacion= info.getPuntuacion();
        Console.print("La puntuación es: ");
        Console.println(String.valueOf(puntuacion), "green");
    }

    /**
     * Imprime la fecha
     */
    private static void opcion3(){
        String fecha = info.getFecha();
        Console.print("La fecha es: ");
        Console.println(fecha, "green");
    }

    /**
     * Enseñam un menú interactivo que enseña diversas opción para probar los métodos de la clase 'Info'.
     * @param args
     */
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
            while(option != 5){
                String menu = "Escoge entre estas opciones: \n" +
                        "[1] Obtén el usuario. \n" +
                        "[2] Obtén la puntuación. \n" +
                        "[3] Obtén la fecha. \n" +
                        "[4] Muestra un resumen con la información del usuario. \n" +
                        "[5] Crear otro usuario. \n" +
                        "[6] Salir.";
                Console.println(menu);
                do {
                    option = in.nextInt();
                }while (option > 6 || option < 1);

                if (option == 1) opcion1();
                else if(option == 2) opcion2();
                else if(option == 3) opcion3();
                else if(option == 4) System.out.println(info.toString());
                else if(option == 6){
                    option = 5;
                    exit = true;
                }
            }
        }
    }
}
