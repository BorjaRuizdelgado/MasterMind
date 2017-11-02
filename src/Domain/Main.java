package Domain;

import Util.Console;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
/**
 *
 * @author borja | ISA | Omar
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*iweputa omar no me toques el main que te corto las manos
        SistemaRanking ranking = SistemaRanking.getInstance();
        */
        while(true){


            System.out.print("Escribe 1 para probar el tablero. 2 para probar partida\n");
            Scanner in = new Scanner(System.in);
            int num = in.nextInt();
            if (num == 1) {
                num = in.nextInt();
                if((num < 4) || (num > 6))break;
                Tablero tablero = new Tablero(num);
                System.out.print(tablero);
                System.out.print("Dame una fila para que rellene todo el tablero\n");
                Codigo filaFilita = new Codigo(num);

                System.out.print("Dame una fila para que rellene todo el tablero\n");
                int itera = num;
                for(int i = 0; i < itera; i++){
                    num = in.nextInt();
                    filaFilita.codigo.add(num);
                    System.out.println(i);
                }

                for(int i = 0; i < 15;++i){
                    tablero.setUltimoColores(filaFilita);
                    Fila aux = tablero.getUltimoIntento();
                    System.out.println("aquitamos\n");
                    System.out.println(aux.getColores().codigo);
                    tablero.incrementaFilaActual();
                }
            }
            else if(num == 2){
                System.out.print("Aqui pruebamos la Partida\n");
                System.out.print("Introduce NumeroPartida Rol(1True/0False) Dificultad (0..2)\n");
                boolean rol = false;
                if (in.nextInt() == 1) rol = true;
                String dif = in.next();
                System.out.print("Creamos una nueva partida:\n");
                Partida p = new Partida(rol,dif);
                System.out.print("Y le pedimos cosas al tablero de la partida:\n");
                System.out.print(p.getNumeroFilaActual());


            }
            else if(num == 3){
                System.out.print("Aqui pruebamos la Partida\n");
                System.out.print("Introduce NumeroPartida Rol(1True/0False) Dificultad (0..2)\n");
                boolean rol = false;
                if (in.nextInt() == 1) rol = true;
                String dif = in.next();
                System.out.print("Creamos una nueva partida:\n");
                Partida p = new Partida(rol,dif);
                p.generaCodigoAleatorio();
                System.out.println(p.getCodigoSecreto().codigo);
            }

            else if(num == 4){
                Console.println("Bienvenido a Mastermind", "blue");
                Console.println("[1] Crear usuario");
                Console.println("[2] Carga usuario");

                int input = in.nextInt();
                while (input > 2 || input < 1) {
                    Console.println("Introduce 1 o 2.", "red");
                    input = in.nextInt();
                }
                if (input == 1) {
                    Console.println("Crear usuario seleccionado.", "green");
                }
                else if(input == 2){
                    Console.println("Cargar usuario seleccionado.", "green");
                }

                Console.println("[String] Introduce tu nombre.");
                String usuario = in.next();
                while (usuario.length() < 1 || usuario.length() > 8) {
                    Console.println("El nombre no puede estar vacío ni ser más largo que 8.", "red");
                    usuario = in.next();
                }
                Console.println("Nombre introducido correctamente", "green");

                // A partir de aquí jugamos como codeMaker
                Console.println("Introduce el código:");
                List<Integer> codigo = new ArrayList<>();
                //Cerebro cerebro = new Cerebro(6, 4);
                for (int i = 0; i < 4; i++) {
                    codigo.add(in.nextInt());
                }
                boolean juegoFinalizado = false;
                /*while(!juegoFinalizado){

                }*/

            }
            else if(num == 5){
                Cerebro c = new Cerebro(4,4);

            }
            else {
                Codigo codigo = new Codigo(4);
                codigo.codigo.add(5);
                codigo.codigo.add(3);
                codigo.codigo.add(1);
                codigo.codigo.add(2);

                Codigo codigo2 = new Codigo(4);
                codigo2.codigo.add(1);
                codigo2.codigo.add(1);
                codigo2.codigo.add(1);
                codigo2.codigo.add(3);

                Respuesta respuesta = codigo.getRespuesta(codigo2);
                System.out.println(respuesta.respuesta);
            }
        }
    }

}
