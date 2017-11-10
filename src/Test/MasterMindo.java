package Test;

import Domain.*;
import Domain.Excepciones.ExcepcionRespuestaIncorrecta;

import static Util.Console.*;
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
                    "\nPresiona 1 para JUGAR como CODEMAKER o 2 para JUGAR com CODEBREAKER");
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
        if (rol) juegaCodeBreaker();
        else juegaCodeMaker();
    }

    /**
     * Permite al usuario jugar como CodeBreaker.
     */
    private static void juegaCodeBreaker() {
        println("Partida empezada.");
        println("Intenta adivinar el código secreto.");
        int n;
        Boolean abandonada = false;
        while(pary.getNumeroFilaActual() != 15 && !pary.isGanado() && !abandonada){
            println("¿Qué quieres hacer?");
            println("[1] Introducir un nuevo intento.\n[2] Pedir una pista.\n" +
                    "[3] Abandonar la partida.\n.");
            while((n=scan.nextInt()) != 1) {
                switch (n) {
                    case 2:
                        obtenerPista();
                        break;
                    case 3:
                        println("¿Estás seguro de querer abandonar? Responde: 'Si' / 'No'.");
                        if (scan.next().equals("Si")) abandonada = true;
                        else break;
                    default:
                        println("¿Qué quieres hacer?");
                        println("[1] Introducir un nuevo intento.\n[2] Pedir una pista.\n" +
                                "[3] Abandonar la partida.\n[4] Reiniciar la partida.");
                        break;
                }
            }
            if (!abandonada) {
                println("Introduce un código de tamaño " + pary.getNumColumnas() + " separado por espacios\n" +
                        "Formado por numeros del 1 al " + pary.getNumColores());
                long startTime = System.currentTimeMillis();

                computarIntentoUsuario();

                long endTime = System.currentTimeMillis();
                pary.sumaTiempo(endTime - startTime);

                println("Has obtenido esta respuesta: " + pary.getUltimaRespuesta().toString());

                //todo falta añadir el uso de pistas
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
        println("Dame un nivel de dificultad: Facil, Medio y Dificil");
        Scanner scan = new Scanner(System.in);
        String diff = scan.next();
        usr.creaPartidaActual(true,diff);
        pary = usr.getPartidaActual();
        setCodigoSecreto();
        while(pary.getNumeroFilaActual() != 15 && !pary.isGanado()){
            corrige();
        }

        if (!pary.isGanado()) {
            println("¡No ha conseguido descubrir tu código!");
        }
        else {
            println("Ha descubierto tu código en "+pary.getNumeroFilaActual()+" intentos.");
        }
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


    private static void obtenerPista() {

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


