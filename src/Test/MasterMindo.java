package Test;

import Domain.Codigo;
import Domain.Partida;
import Domain.Respuesta;
import Domain.Usuario;

import java.util.Scanner;

public class MasterMindo {
    public static Usuario usr;
    public static Partida pary;
    public static void main(String[] args) {

            Scanner scan = new Scanner(System.in);
            print("Bienvenid@ a MasterMindo un juego completamente nuevo y, \n" +
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

    private static void juegaCodeBreaker() {
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
            Codigo code = new Codigo(pary.getNumColumnas());
            for(int i = 0; i < pary.getNumColumnas();++i){
                code.codigo.add(scan.nextInt());
            }
            System.out.println(code.codigo);
            pary.setIntento(code);
            pary.generaRespuesta();
            print("Has obtenido esta respuesta: " + pary.getUltimaRespuesta().toString());
            System.out.println(pary.getCodigoSecreto().codigo); //delete
        }
        if (!pary.isGanado()) {
            print("Has perdido! Este era el código secreto:");
            System.out.println(pary.getCodigoSecreto().codigo);
        }
        else {
            print("Has ganado con "+pary.getNumeroFilaActual()+" intentos.");
        }

    }

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
}


