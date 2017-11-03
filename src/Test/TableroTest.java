package Test;

import Domain.Codigo;
import Domain.Respuesta;
import Domain.Tablero;

import java.util.Scanner;

public class TableroTest {
    private static Tablero tablero;

    public static void main(String[] args) {
        System.out.print("Aquí probamos la Clase Fila\n");
        System.out.println("Puedes crear un tablero de 4 o 6 columnas introduce 4 o 6 para continuar.\n");
        Scanner es = new Scanner(System.in);
        int tamTablero = es.nextInt();
        tablero = new Tablero(tamTablero);
        while (true) {
            System.out.println("PULSA:\n"+
                    "* 1 para insertar el codigo secreto en el tablero\n" +
                    "* 2 para ver el codigo secreto del tablero\n" +
                    "* 3 para imprimir la ultima fila que se ha insertado en el tablero\n" +
                    "* 4 para insertar un 'guess' en el tablero\n" +
                    "* 5 para insertar una respuesta en el tablero\n" +
                    "* 6 para pedir el ultimo Guess introducido en el tablro\n" +
                    "* 7 para pedir el numero de turno en el que estamos\n" +
                    "* 8 para que el tablero genere la respuesta automatica segun el ultimo Guess\n");
            switch (es.nextInt()){
                case 1:
                    insertarCodigoSecreto(tamTablero);
                    break ;
                case 2:
                    imprimeCodigoSecreto();
                    break;
                case 3:
                    imprimeUltimoIntento();
                    break;
                case 4:
                    setGuess(tamTablero);
                    break;
                case 5:
                    setRespuesta(tamTablero);
                    break;
                case 6:
                    imprimeUltimoGuess();
                    break;
                case 7:
                    imprimeTurno();
                    break;
                case 8:
                    generaRespuesta();
                    break;

            }
        }
    }


    private static void insertarCodigoSecreto(int tam){
        Scanner sca = new Scanner(System.in);
        Codigo secreto = new Codigo(tam);
        for(int i = 0; i< tam; ++i){
            secreto.codigo.add(sca.nextInt());
        }
        tablero.setCodigoSecreto(secreto);
    }

    private static void imprimeCodigoSecreto(){
        System.out.println(tablero.getCodigoSecreto().codigo);
    }

    private static void imprimeUltimoIntento(){
        System.out.println(tablero.getUltimoIntento().getColores().codigo);
        System.out.println(tablero.getUltimoIntento().getRespuestas().respuesta);
    }

    private static void setGuess(int tam){
        Scanner sca = new Scanner(System.in);
        Codigo secreto = new Codigo(tam);
        System.out.print("Introduce un GUESS al codigo del tablero\n");
        for(int i = 0; i< tam; ++i){
            secreto.codigo.add(sca.nextInt());
        }
        if(tablero.getNumeroFilaActual() != 0) tablero.incrementaFilaActual();
        tablero.setUltimoColores(secreto);

    }

    private static void setRespuesta(int tam){
        Scanner sca = new Scanner(System.in);
        Respuesta secreto = new Respuesta(tam);
        while(true) {
            System.out.print("Introduce una RESPUESTA valida al codigo del tablero\n");
            for (int i = 0; i < tam; ++i) {
                secreto.respuesta.add(sca.nextInt());
            }

            if(secreto.respuesta.equals(tablero.getCodigoSecreto().getRespuesta(tablero.getUltimoColores()).respuesta)) break;
        }
        tablero.setUltimoRespuestas(secreto);

    }

    private static void imprimeUltimoGuess(){
        System.out.println(tablero.getUltimoColores().codigo);
    }

    private static void imprimeTurno(){
        System.out.println(tablero.getNumeroFilaActual());
    }

    private static void generaRespuesta(){
        tablero.generaRespuesta();
    }
}
