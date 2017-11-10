package Test;

import Domain.*;
import Domain.Excepciones.ExcepcionNoHayColoresSinUsar;
import Domain.Excepciones.ExcepcionPistaUsada;
import Domain.Excepciones.ExcepcionRespuestaIncorrecta;
import Domain.Excepciones.ExcepcionUno;

import static Util.Console.*;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * MASTERMINDO GAME TEST
 * Implementa un Main para poder probar de manera interactiva el juego completo.
 * @author ISA
 */
public class MasterMindo {
    public static Usuario usr;
    public static Partida pary;
    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
            println("Bienvenid@ a MasterMindo un juego completamente nuevo y \n" +
                    "que no infringe ningun tipo de derechos de autor");

            println("Ahora introduce tu nombre de usuario y presiona Enter:");
            String nombreUsuario = scan.next();
            usr = new Usuario(nombreUsuario);
            println("¡Se ha creado tu usuario!\n" +
                    "\nPresiona 1 para JUGAR como CODEMAKER o 2 para JUGAR como CODEBREAKER");
            int rol = scan.nextInt();
            if(rol == 1) juega(true);
            else if(rol == 2) juega(false);

            println("El juego ha terminado.");
    }


    private static void juega(Boolean rol) {
        println("Dame un nivel de dificultad: Facil, Medio y Dificil");
        String diff = scan.next();
        usr.creaPartidaActual(rol,diff);
        pary = usr.getPartidaActual();
        if (rol) juegaCodeMaker();
        else juegaCodeBreaker();
    }

    /**
     * Permite al usuario jugar como CodeBreaker.
     */
    private static void juegaCodeBreaker() {
        println("Partida empezada.");
        println("Intenta adivinar el código secreto.");
        int n;
        Boolean abandonada = false;
        Boolean nuevoIntento = false;
        while(pary.getNumeroFilaActual() != 15 && !pary.isGanado() && !abandonada){
            while (!nuevoIntento && !abandonada) {
                println("¿Qué quieres hacer?");
                println("[1] Introducir un nuevo intento.\n[2] Pedir una pista.\n" +
                        "[3] Abandonar la partida.\n");
                n = scan.nextInt();
                switch (n) {
                    case 1:
                        nuevoIntento = true;
                        break;
                    case 2:
                        obtenerPista();
                        break;
                    case 3:
                        println("¿Estás seguro de querer abandonar? Responde: 'Si' / 'No'.");
                        if (scan.next().equals("Si")) {
                            abandonada = true;
                        }
                        break;
                    default:
                        println("Opción no valida");
                        break;
                }
            }
            if (!abandonada) {
                nuevoIntento = false;
                println("Introduce un código de tamaño " + pary.getNumColumnas() + " separado por espacios\n" +
                        "Formado por numeros del 1 al " + pary.getNumColores());
                long startTime = System.currentTimeMillis();

                computarIntentoUsuario();

                long endTime = System.currentTimeMillis();
                pary.sumaTiempo(endTime - startTime);
                println("Has obtenido esta respuesta: " + pary.getUltimaRespuesta().toString());
            }
        }

        if (!pary.isGanado()) {
            println("Has perdido! Este era el código secreto:");
            System.out.println(pary.getCodigoSecreto().codigo);
            if (abandonada) {
                usr.abandonaPartidaActual();
                return;
            }
        }
        else {
            println("Has ganado con "+pary.getNumeroFilaActual()+" intentos.");
        }
        usr.finalizarPartidaActual(pary.isGanado());

        println(usr.getNombre() + " tu puntuación total es de: " + pary.generaPuntuacion());

        //todo añadir la puntuación al ranking

    }

    /**
     * Permite al usuario jugar como CodeMaker.
     */
    private static void juegaCodeMaker(){
        println("Partida empezada.");
        setCodigoSecreto();
        Boolean abandonada = false;
        int n;
        while(pary.getNumeroFilaActual() != 15 && !pary.isGanado() && !abandonada){
            println("¿Qué quieres hacer?");
            println("[1] Siguiente intento.\n[2] Abandonar la partida.\n");
            n = scan.nextInt();
            switch (n) {
                case 1:
                    corrige();
                    break;
                case 2:
                    println("¿Estás seguro de querer abandonar? Responde: 'Si' / 'No'.");
                    if (scan.next().equals("Si")) {
                        abandonada = true;
                    }
                    break;
                default:
                    println("Opción no válida");
                    break;
            }
        }

        if (!pary.isGanado()) {
            println("¡No ha conseguido descubrir tu código!");
        }
        else {
            println("Ha descubierto tu código en "+pary.getNumeroFilaActual()+" intentos.");
        }
        // todo falta ver si se suma a algun contador
    }

    /**
     * El usuario puede poner un codigo secreto en el tablero
     */
    private static void setCodigoSecreto(){
        println("Dame el codigo secreto de tamaño " + pary.getNumColumnas() +" separado por espacios\n" +
                "Formado por numeros del 1 al "+ pary.getNumColores());
        Codigo code = new Codigo(pary.getNumColumnas());
        Scanner scan = new Scanner(System.in);
        for(int i = 0; i < pary.getNumColumnas();++i){
            code.codigo.add(scan.nextInt());
        }

        pary.setCodigoSecreto(code);

        println("EL JUEGO EMPIEZA\n");
    }

    /**
     * El usuario corrige el codigo de la maquina.
     */
    private static void corrige(){
        Respuesta respuestaUsr;
        Codigo intento = pary.generaSiguienteIntento();
        println("Corrige el intento de la maquina\n" +
                "Pon 8 como Negro, 7 como Blanco y 0 como vacio.\nCODIGO A CORREGIR:");
        System.out.println(intento.codigo);
        Scanner scan = new Scanner(System.in);

        while(true){
            respuestaUsr = new Respuesta(pary.getNumColumnas());
            for(int i = 0; i < pary.getNumColumnas();i++) respuestaUsr.respuesta.add(scan.nextInt());
            try {
                pary.setRespuesta(respuestaUsr);
                break;
            } catch (ExcepcionRespuestaIncorrecta e) {
                println(e.getMessage());
            }
        }
    }

    /**
     * El jugador puede obtener una pista o cancelar la obtención.
     */
    private static void obtenerPista() {
        println("Escoge el nivel de pista que quieres:\n" +
                "[1] Obtener uno de los colores que no aparece en el código secreto.\n" +
                "[2] Obtener todos los colores que no aparecen en el código secreto.\n" +
                "[3] Obtener el color de una posición aleatoria del código secreto.\n" +
                "[4] Cancelar.");
        println("ATENCIÓN: El uso de pistas hará que la puntuación de la partida sea 0.");
        int n = scan.nextInt();
        switch (n) {
            case 1:
                try {
                    System.out.println("No aparece en el código secreto: "+pary.getPista1()+".");
                } catch (Exception e) {
                    println(e.getMessage());
                }
                break;
            case 2:
                try {
                    String result = "No aparecen en el código secreto: ";
                    ArrayList<Integer> aux = pary.getPista2();
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
                try {
                    Codigo aux = pary.getPista3();
                    for (int i = 0; i < aux.size; i++) {
                        if (aux.codigo.get(i) != 0) println("En la posición " + (i+1) + " está el " + aux.codigo.get(i) + ".");
                    }
                }
                catch (ExcepcionPistaUsada e) {
                    println(e.getMessage());
                }
                break;
            case 4:
                break;
            default:
                println("Opción no válida.");
                break;
        }

    }

    /**
     * Lee el intento del usuario, lo añade al tablero y genera la respuesta.
     */
    private static void computarIntentoUsuario(){
        Scanner scan = new Scanner(System.in);
        Codigo code = new Codigo(pary.getNumColumnas());
        for(int i = 0; i < pary.getNumColumnas();++i){
            code.codigo.add(scan.nextInt());
        }
        pary.setIntento(code);
        pary.generaRespuesta();
    }

}


