package Test;

import Domain.*;

import java.util.Scanner;

public class MasterMindo {
    public static Usuario usr;
    public static Partida pary;
    public static void main(String[] args) {

            Scanner scan = new Scanner(System.in);
            print("Bienvenid@ a MasterMindo un juego completamente nuevo y \n" +
                    "que no infringe ningun tipo de derechos de autor");

            print("Ahora introduce tu nombre de usuario y presiona Enter:");
            String nombreUsuario = scan.next();
            usr = new Usuario(nombreUsuario);
            print("¡Se ha creado tu usuario!\n" +
                    "\nPresiona 1 para JUGAR como CODEMAKER o 2 para JUGAR com CODEBREAKER");
            int rol = scan.nextInt();
            if(rol == 1) juegaCodeMaker();
            else if(rol == 2) juegaCodeBreaker();
    }

    /**Permite al usuario jugar como CodeBreaker.
     */
    private static void juegaCodeBreaker() {
        long totalTime = 0;
        print("Dame un nivel de dificultad: Facil, Medio y Dificil");
        Scanner scan = new Scanner(System.in);
        String diff = scan.next();
        usr.creaPartidaActual(false,diff);
        pary = usr.getPartidaActual();
        pary.generaCodigoSecretoAleatorio();
        print("Intenta adivinar el código secreto.");
        while(pary.getNumeroFilaActual() != 15 && !pary.isGanado()){
            print("Introduce un código de tamaño " + pary.getNumColumnas() +" separado por espacios\n" +
                    "Formado por numeros del 1 al "+ pary.getNumColores());
            long startTime = System.currentTimeMillis();

            computarIntentoUsuario();

            long endTime = System.currentTimeMillis();
            totalTime = totalTime + (endTime - startTime);

            print("Has obtenido esta respuesta: " + pary.getUltimaRespuesta().toString());
        }
        if (!pary.isGanado()) {
            print("Has perdido! Este era el código secreto:");
            System.out.println(pary.getCodigoSecreto().codigo);
        }

        else {
            print("Has ganado con "+pary.getNumeroFilaActual()+" intentos.");
        }

        System.out.println(usr.getNombre() + " tu puntuación total es de: " + computarRanking(totalTime,pary.getDificultad(),pary.getNumeroFilaActual())  +
                " una puntuación percecta es de 100, cuanto más te pases de ese numero,\n" +
                "peor puntuacón.");

    }

    /**
     * Permite al usuario jugar como CodeMaker.
     */
    private static void juegaCodeMaker(){
        print("Dame un nivel de dificultad: Facil, Medio y Dificil");
        Scanner scan = new Scanner(System.in);
        String diff = scan.next();
        usr.creaPartidaActual(true,diff);
        pary = usr.getPartidaActual();
        setCodigoSecreto();
        while(pary.getNumeroFilaActual() != 15 && !pary.isGanado()){
            corrige();
        }

        if (!pary.isGanado()) {
            print("Has perdido!");
        }
        else {
            print("Has ganado con "+pary.getNumeroFilaActual()+" intentos.");
        }
    }

    /**
     * El usuario puede poner un codigo secreto en el tablero
     */
    private static void setCodigoSecreto(){
        print("Dame el codigo secreto de tamaño " + pary.getNumColumnas() +" separado por espacios\n" +
                "Formado por numeros del 1 al "+ pary.getNumColores());
        Codigo code = new Codigo(pary.getNumColumnas());
        Scanner scan = new Scanner(System.in);
        for(int i = 0; i < pary.getNumColumnas();++i){
            code.codigo.add(scan.nextInt());
        }

        pary.setCodigoSecreto(code);

        print("EL JUEGO EMPIEZA\n");
    }

    /**
     * El usuario corrige a la maquina.
     */
    private static void corrige(){
        Respuesta respuestaUsr = new Respuesta(pary.getNumColumnas());
        Codigo intento = pary.getSiguienteIntento();
        print("Corrige el intento de la maquina\n" +
                "Pon 8 como Negro, 7 como Blanco y 0 como vacio.\nCODIGO A CORREGIR:");
        System.out.println(intento.codigo);
        Scanner scan = new Scanner(System.in);
        Boolean First = true;
        while(!intento.getRespuesta(pary.getCodigoSecreto()).equals(respuestaUsr)){
            if (!First) {
                print("Respuesta no válida. Introduce tu respuesta de nuevo.");
            }
            respuestaUsr = new Respuesta(pary.getNumColumnas());
            for(int i = 0; i < pary.getNumColumnas();i++) respuestaUsr.respuesta.add(scan.nextInt());
            First = false;

        }
        pary.setRespuesta(respuestaUsr);
    }

    private static void print (String message) {
        System.out.print(message+"\n");
    }

    /**
     * Dado una dificultad, un tiempo total de usuario y el numero de intentos esta funcion genera una puntuación.
     * @param tiempo Tiempo total del Usuario para resolver el Mastermind.
     * @param dificltad Dificultad Escogida.
     * @param numeroFila Numero de intentos empleados.
     * @return Puntuación.
     */
    private static int computarRanking(float tiempo, String dificltad, int numeroFila){
        float resultado = 0;

        resultado += (15 - numeroFila)*777;
        resultado += 1000000/(tiempo/1000);
        switch(dificltad){
            case "Medio":
                resultado *= 1.10;
                break;
            case "Dificil":
                resultado *= 1.20;
        }
        return (int)resultado;
    }

    /**
     * Lee el intento del usuario y lo pone en el tablero.
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


