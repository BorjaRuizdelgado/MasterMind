package Test;

import Domain.Controllers.ControladorDominio;
import Domain.Excepciones.ExcepcionUsuarioExiste;
import Domain.Excepciones.ExcepcionUsuarioInexistente;
import groovy.lang.Tuple2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestControladorDominio {
    private static ControladorDominio ctrl;
    private static Scanner scan = new Scanner(System.in);
    private static Boolean rolMaker;
    private static String dificultad;

    public static void main(String[] args) {
        ctrl = new ControladorDominio();
        System.out.println("Pulsa 1 para cargar tu usuario o 2 para crear uno nuevo:");
        if(scan.nextInt() == 1) cargaUsuario();
        else creaUsuario();
        while(true) {
            System.out.println("Pulsa 1 para cargar una partida o 2 para crear una nueva (de momento solo code breaker)");
            if (scan.nextInt() == 1) {
                cargarPartida();
                if (ctrl.getPartidasGuardadasUsr().size() != 0) break;
            } else {
                crearPartida();
                break;
            }
        }

        while(true){
            System.out.println("Pulsa 1 para guardar la partida o 2 para seguir jugando:");
            if(scan.nextInt() == 1) {
                guardaPartida();
                System.exit(0);
            }
            else jugarCodeBreaker();
        }

    }

    private static void creaUsuario(){
        System.out.println("Introduce tu nombre de usuario:");
        while(true) {
            String nombreUsuario = scan.next();
            try {
                ctrl.crearUsuario(nombreUsuario);
                break;
            } catch (ExcepcionUsuarioExiste e) {
                System.out.println("Lo sentimos el usuario ya existe en el sistema prueba con otro.");
            }
        }

        System.out.println("Usuario creado con éxito.");
    }

    private static void cargaUsuario(){
        System.out.println("Introduce tu nombre de usuario:");
        while(true) {
            String nombreUsuario = scan.next();
            try {
                ctrl.cargarUsuario(nombreUsuario);
                break;
            } catch (ExcepcionUsuarioInexistente e) {
                System.out.println("Lo sentimos el usuario no existe en el sistema prueba con otro.");
                System.exit(0);
            }
        }

        System.out.println("Usuario cargado con éxito.");

    }

    private static void cargarPartida(){
        if(ctrl.getPartidasGuardadasUsr().size() == 0){
            System.out.println("El usuario no tiene ninguna partida guardada, por favor crea una partida nueva.");
            return;
        }
        System.out.println("Esta es la lista de partidas del usuario cargado actualmente:");
        System.out.println(ctrl.getPartidasGuardadasUsr());
        System.out.println("Escribe el id de la partida que quieres guardar.");
        Tuple2<Boolean, String> aux = ctrl.cargarPartidaUsuario(scan.next());
        rolMaker = aux.getFirst();
        dificultad = aux.getSecond();
        System.out.println("Partida cargada.");
    }

    private static void crearPartida(){
        System.out.println("Introduce el rol 1 para Maker y 0 para Breaker:");
        rolMaker = scan.nextBoolean();
        System.out.println("Introduce la dificultad Facil, Medio, Dificil.");
        dificultad = scan.next();
        if(rolMaker){
            System.out.println("Dame el codigo secreto de tamaño 4 con 4 clores si es Facil," +
                    " 4 con 6 colores si es Medio y 6 con 6 colores si es Dificil");
            int dif = 4;
            if(dificultad.equals("Dificil")){
                dif = 6;
            }
            ArrayList<Integer> arr = new ArrayList<>();
            for(int i = 0; i < dif;++i){
                arr.add(scan.nextInt());
            }
            ctrl.crearPartidaUsuarioCargadoRolMaker(dificultad,arr);
        }
        else{
            ctrl.crearPartidaUsuarioCargadoRolBreaker(dificultad);
        }
    }

    private static void jugarCodeMaker(){

    }

    private static void jugarCodeBreaker(){
        imprimeTablero();
        System.out.println("Escribe tu siguiente guess:");
       ArrayList<Integer> intento = new ArrayList<>();
        int dif = 4;
        if(dificultad.equals("Dificil")){
            dif = 6;
        }
        for(int i = 0; i < dif; ++i){
            intento.add(scan.nextInt());
        }
        ctrl.juegaCodeBreaker(intento,1);

    }

    private static void imprimeTablero(){
        List<List<List<Integer>>> tablero = ctrl.getTablero();
        for(int i = tablero.size() - 1 ; i >= 0; --i){
            System.out.print(tablero.get(i).get(0));
            System.out.println(tablero.get(i).get(1));
        }
    }

    private static void guardaPartida(){
        ctrl.guardaPartidaActual();
    }
}
