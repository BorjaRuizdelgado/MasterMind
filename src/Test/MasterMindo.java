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
            System.out.println("Bienvenid@ a MasterMindo una juego completamente nuevo y, \n" +
                    "que no infringe ningun tipo de derechos de autor");

            System.out.println("Ahora selecciona tu nombre de usuario, introduce tu nombre aqui y presiona Enter:\n");
            String nombreUsuario = scan.next();
            usr = new Usuario(nombreUsuario);
            System.out.println("¡Se ha creado tu usuario!\n" +
                    "\nPresiona 1 para JUGAR como CODEMAKER o 2 para JUGAR com CODEBREAKER\n");
            int rol = scan.nextInt();
            if(rol == 1) {
                juegaCodeMaker();
            }
            else if(rol == 2) juegaCodeBreaker();
    }

    private static void juegaCodeBreaker() {

    }

    private static void juegaCodeMaker(){
        System.out.println("Dame un nivel de dificultad siendo: Facil, Medio y Dificil\n");
        Scanner scan = new Scanner(System.in);
        String diff = scan.next();
        usr.creaPartidaActual(true,diff);
        pary = usr.getPartidaActual();
        setCodigoSecreto();
        while(pary.getNumeroFilaActual() != 15){
            corrige();
        }
    }

    private static void setCodigoSecreto(){
        System.out.println("Dame el codigo secreto de tamaño " + pary.getNumColumnas() +" separado por espacios\n" +
                "Formado por numeros del 1 al "+ pary.getNumColores()+"\n");
        Codigo code = new Codigo(pary.getNumColumnas());
        Scanner scan = new Scanner(System.in);
        for(int i = 0; i < pary.getNumColumnas();++i){
            code.codigo.add(scan.nextInt());
        }

        pary.setCodigoSecreto(code);

        System.out.println("EL JUEGO EMPIEZA\n");
    }

    private static void corrige(){
        Respuesta respuestaUsr = new Respuesta(pary.getNumColumnas());
        Codigo intento = pary.getSiguienteIntento();
        System.out.println("Corrige el intento de la maquina\n" +
                +" poniendo 8 como Negro, 7 como Blanco y 0 como vacio.\n CODIGO A CORREGIR:\n");
        System.out.println(intento.codigo + "\n");
        Respuesta resUsr = new Respuesta(pary.getNumColumnas());
        Scanner scan = new Scanner(System.in);
        while(! intento.getRespuesta(pary.getCodigoSecreto()).equals(respuestaUsr)){
            respuestaUsr = new Respuesta(pary.getNumColumnas());
            for(int i = 0; i < pary.getNumColumnas();++i) respuestaUsr.respuesta.


        }


    }
}


