package Test;

import Domain.Codigo;
import Domain.Respuesta;
import Domain.Tablero;

import java.util.Scanner;

/**
 * @author borja
 */
/**
 * Implementa un Main para poder probar de manera interactiva la clase.
 */
public class TableroTest {
    private static Tablero tablero;

    public static void main(String[] args) {
        System.out.print("Aquí probamos la Clase Tablero\n");
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
                    "* 8 para que el tablero genere la respuesta automatica segun el ultimo Guess\n" +
                    "* 9 para pedir la fila actual que se está tratando\n");
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
                case 9:
                    imprimeIntentoActual();

            }
        }
    }

    /**
     * Prueba la iserción de un codigo secreto en el tablero
     * @param tam Se utiliza para agilizar el proceso de lectura por pantalla
     */
    private static void insertarCodigoSecreto(int tam){
        System.out.print("Introduce un codigo secreto para el tablero tablero\n");
        Scanner sca = new Scanner(System.in);
        Codigo secreto = new Codigo(tam);
        for(int i = 0; i< tam; ++i){
            secreto.codigo.add(sca.nextInt());
        }
        tablero.setCodigoSecreto(secreto);
    }

    /**
     * Imprime el codigo secreto
     */
    private static void imprimeCodigoSecreto(){
        System.out.println(tablero.getCodigoSecreto().codigo);
    }

    /**
     * Imprime el ultimo intento
     */
    private static void imprimeUltimoIntento(){
        System.out.println(tablero.getUltimoIntento().getColores().codigo);
        System.out.println(tablero.getUltimoIntento().getRespuestas().respuesta);
    }

    /**
     * Prueba el poner un Guess en el tablero en la posición adecuada
     * @param tam Utilizado para agilizar el proceso de lectura de atributos
     */
    private static void setGuess(int tam){
        Scanner sca = new Scanner(System.in);
        Codigo secreto = new Codigo(tam);
        System.out.print("Introduce un GUESS al codigo del tablero\n");
        for(int i = 0; i< tam; ++i){
            secreto.codigo.add(sca.nextInt());
        }
        //if(tablero.getNumeroFilaActual() != 0) tablero.incrementaFilaActual();
        tablero.setUltimoColores(secreto);

    }

    /**
     * Prueba poner una respuesta en el tablero
     * @param tam Utilizado para agilizar el proceso de lectura
     */
    private static void setRespuesta(int tam){
        Scanner sca = new Scanner(System.in);
        Respuesta secreto;
        while(true) {
            secreto = new Respuesta(tam);
            System.out.print("Introduce una RESPUESTA valida al codigo del tablero\n");
            for (int i = 0; i < tam; ++i) {
                secreto.respuesta.add(sca.nextInt());
            }

            if(secreto.equals(tablero.getCodigoSecreto().getRespuesta(tablero.getIntentoActual()))){
                break;
            }
        }
        tablero.setUltimoRespuestas(secreto);

    }

    /**
     * Imprime el ultimo guess introducido en el tablero
     */
    //no puedes imprimir un guess si no has puesto una respuesta antes
    private static void imprimeUltimoGuess(){
        System.out.println(tablero.getUltimoColores().codigo);
    }

    /**
     * Imprime el turno actual.
     */
    private static void imprimeTurno(){
        System.out.println(tablero.getNumeroFilaActual());
    }

    /**
     * Genera una respuesta y la pone en el tablero a partir del ulrimo guess que hay en el tablero
     */
    private static void generaRespuesta(){
        tablero.generaRespuesta();
    }

    /**
     * Imprime el intento actual.
     */
    private static void imprimeIntentoActual(){
        System.out.println(tablero.getIntentoActual().codigo);
    }
}
