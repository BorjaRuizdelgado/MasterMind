package Test;

import static Util.Console.*;

import Domain.Codigo;
import Domain.Excepciones.ExcepcionPistaUsada;
import Domain.Excepciones.ExcepcionRespuestaIncorrecta;
import Domain.Partida;
import Domain.Respuesta;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Partida Test
 * Implementa un Main para poder probar de manera interactiva la clase.
 * @author ISA
 */
public class PartidaTestNoJUnit {
    private static Scanner in = new Scanner(System.in);

    public static void main (String[] args) {
        println("Aquí probaremos la clase Partida");

        Partida test = creaPartida();
        if(test.isRolMaker()) setCodigoSecreto(test);
        Boolean fin = false;
        int n;
        while (!fin) {
            println("¿Qué quieres hacer con la nueva partida creada?");
            println("[1] Ver información principal.\n" +
                    "[2] Ver toda la información.\n" +
                    "[3] Ver código secreto.\n" +
                    "[4] Pedir una pista (Solo disponible en rol CodeBreaker).\n" +
                    "[5] Sumar tiempo.\n" +
                    "[6] Siguiente intento.\n" +
                    "[7] Acabar la prueba");
            n = in.nextInt();
            switch (n) {
                case 1:
                    test.imprimeInfo();
                    break;
                case 2:
                    test.imprimeAllInfo();
                    break;
                case 3:
                    System.out.println("Código secreto: " +test.getCodigoSecreto().codigo);
                    break;
                case 4:
                    if (!test.isRolMaker())
                        obtenerPista(test);
                    else
                        println("No disponible para rol CodeMaker.");
                    break;
                case 5:
                    sumaTiempo(test);
                    break;
                case 6:
                    if (test.getNumeroFilaActual() < 15 && !test.isGanado()) {
                        if (!test.isRolMaker()) {
                            Codigo intento = nuevoIntentoJugador(test);
                            Respuesta rintento = test.getUltimaRespuesta();
                            System.out.println(intento.codigo + " -> {" + rintento.toString()+"}");
                        } else {
                            Codigo intento = nuevoIntentoMaquina(test);
                            Respuesta rintento = test.getUltimaRespuesta();
                            System.out.println(intento.codigo + " -> {" + rintento.toString()+"}");
                        }
                    } else {
                        if (!test.isGanado()) println("Has alcanzado el máximo de intentos.");
                        if (!test.isRolMaker()) {
                            print("La partida está: ");
                            if (test.isGanado()) {
                                println("Ganada.");
                                System.out.println("Con una puntuación de "+test.generaPuntuacion());
                            }
                            else println("Perdida.");
                        }
                        else {
                            if (test.isGanado()) println("Ha descubierto tu código");
                            else println("No ha descubierto tu código.");
                        }
                    }
                    break;
                case 7:
                    fin = true;
                    break;
                default:
                    print("Opción no válida.");
                    break;
            }
        }
        println("Prueba terminada.");

    }

    private static Partida creaPartida() {
        Scanner in = new Scanner(System.in);
        println("Introduce 1 para ser CodeMaker o 0 para ser CodeBreaker");
        int rolMaker = in.nextInt();
        while (rolMaker != 0 && rolMaker != 1) {
            print("Rol no válido");
            rolMaker = in.nextInt();
        }

        println("Introduce la dificultad: 'Facil', 'Medio' o 'Dificil'");
        String dif = in.next();
        while (!dif.equals("Facil") && !dif.equals("Medio") && !dif.equals("Dificil")) {
            println("Dificultad no válida");
            dif = in.next();
        }
        return new Partida(rolMaker==1,dif);
    }

    /**
     * El usuario puede poner un codigo secreto en el tablero
     */
    private static void setCodigoSecreto(Partida test){
        println("Dame el codigo secreto de tamaño " + test.getNumColumnas() +" separado por espacios\n" +
                "Formado por numeros del 1 al "+ test.getNumColores());
        Codigo code = new Codigo(test.getNumColumnas());
        Scanner scan = new Scanner(System.in);
        for(int i = 0; i < test.getNumColumnas();++i){
            code.codigo.add(scan.nextInt());
        }

        test.setCodigoSecreto(code);
    }

    private static void sumaTiempo(Partida test) {
        println("¿Cuanto tiempo quieres sumar?");
        int s = in.nextInt();
        test.sumaTiempo(s);
        println("Tiempo añadido.");
    }

    /**
     * Lee el intento del usuario, lo añade al tablero y genera la respuesta.
     * Aumenta el tiempo de la partida en lo que haya tardado el usuario en introducir el código.
     */
    private static Codigo nuevoIntentoJugador(Partida test){
        Codigo code = new Codigo(test.getNumColumnas());

        println("Introduce un código de tamaño " + test.getNumColumnas() + " separado por espacios\n" +
                "Formado por numeros del 1 al " + test.getNumColores());
        long startTime = System.currentTimeMillis();

        for(int i = 0; i < test.getNumColumnas();++i){
            code.codigo.add(in.nextInt());
        }
        test.setIntento(code);

        long endTime = System.currentTimeMillis();
        test.sumaTiempo((endTime - startTime)/1000);

        test.generaRespuesta();

        return code;
    }

    /**
     * El usuario corrige el codigo de la maquina.
     */
    private static Codigo nuevoIntentoMaquina (Partida test){
        Respuesta rUsr;
        Codigo intento = test.generaSiguienteIntento();
        println("Corrige el intento de la maquina\n" +
                "Pon 8 como Negro, 7 como Blanco y 0 como vacio.\n" +
                "CODIGO A CORREGIR:");
        System.out.println(intento.codigo);
        Scanner scan = new Scanner(System.in);
        while(true){
            rUsr = new Respuesta(test.getNumColumnas());
            for(int i = 0; i < test.getNumColumnas();i++) rUsr.respuesta.add(scan.nextInt());
            try {
                test.setRespuesta(rUsr);
                break;
            } catch (ExcepcionRespuestaIncorrecta e) {
                println(e.getMessage());
            }
        }
        return intento;
    }

    /**
     * El jugador puede obtener una pista o cancelar la obtención.
     */
    private static void obtenerPista(Partida test) {
        println("Escoge el nivel de pista que quieres:\n" +
                "[1] Obtener uno de los colores que no aparece en el código secreto.\n" +
                "[2] Obtener todos los colores que no aparecen en el código secreto.\n" +
                "[3] Obtener el color de una posición aleatoria del código secreto.\n" +
                "[4] Cancelar.");
        println("ATENCIÓN: El uso de pistas hará que la puntuación de la partida sea 0." +
                "ATENCIÓN: Solo puedes usar cada pista una vez.");
        int n = in.nextInt();
        switch (n) {
            case 1:
                try {
                    System.out.println("No aparece en el código secreto: "+test.getPista1()+".");
                } catch (Exception e) {
                    println(e.getMessage());
                }
                break;
            case 2:
                try {
                    String result = "No aparecen en el código secreto: ";
                    ArrayList<Integer> aux = test.getPista2();
                    for (Integer i : aux) {
                        result += i;
                        result += " ";
                    }
                    println(result);
                }
                catch (Exception e) {
                    println(e.getMessage());
                }
                break;
            case 3:
                /*try {
                    Codigo aux = test.getPista3();
                    for (int i = 0; i < aux.size; i++) {
                        if (aux.codigo.get(i) != 0) println("En la posición " + (i+1) + " está el " + aux.codigo.get(i) + ".");
                    }
                }
                catch (ExcepcionPistaUsada e) {
                    println(e.getMessage());
                }
                break;*/
            case 4:
                break;
            default:
                println("Opción no válida.");
                break;
        }

    }


}
