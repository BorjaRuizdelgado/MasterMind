package Test;

import Domain.*;
import static Util.Console.*;
import java.util.Scanner;
public class MasterMindo {
    public static Usuario usr;
    public static Partida pary;
    public static void main(String[] args) {

            Scanner scan = new Scanner(System.in);
            println("Bienvenid@ a MasterMindo un juego completamente nuevo y \n" +
                    "que no infringe ningun tipo de derechos de autor");

            println("Ahora introduce tu nombre de usuario y presiona Enter:");
            String nombreUsuario = scan.next();
            usr = new Usuario(nombreUsuario);
            println("¡Se ha creado tu usuario!\n" +
                    "\nPresiona 1 para JUGAR como CODEMAKER o 2 para JUGAR com CODEBREAKER");
            int rol = scan.nextInt();
            if(rol == 1) juegaCodeMaker();
            else if(rol == 2) juegaCodeBreaker();
    }

    /**Permite al usuario jugar como CodeBreaker.
     */
    private static void juegaCodeBreaker() {
        println("Dame un nivel de dificultad: Facil, Medio y Dificil");
        Scanner scan = new Scanner(System.in);
        String diff = scan.next();
        usr.creaPartidaActual(false,diff);
        pary = usr.getPartidaActual();
        pary.generaCodigoSecretoAleatorio();
        println("Intenta adivinar el código secreto.");
        while(pary.getNumeroFilaActual() != 15 && !pary.isGanado()){
            println("Introduce un código de tamaño " + pary.getNumColumnas() +" separado por espacios\n" +
                    "Formado por numeros del 1 al "+ pary.getNumColores());
            long startTime = System.currentTimeMillis();

            computarIntentoUsuario();

            long endTime = System.currentTimeMillis();
            pary.sumaTiempo(endTime - startTime);

            println("Has obtenido esta respuesta: " + pary.getUltimaRespuesta().toString());
        }
        if (!pary.isGanado()) {
            println("Has perdido! Este era el código secreto:");
            System.out.println(pary.getCodigoSecreto().codigo);
        }

        else {
            println("Has ganado con "+pary.getNumeroFilaActual()+" intentos.");
        }

        System.out.println(usr.getNombre() + " tu puntuación total es de: " + pary.generaPuntuacion());

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
            println("Has perdido!");
        }
        else {
            println("Has ganado con "+pary.getNumeroFilaActual()+" intentos.");
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
            } catch (Exception e) {
                println("Respuesta no válida. Comprueba tu respuesta.");
            }
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


