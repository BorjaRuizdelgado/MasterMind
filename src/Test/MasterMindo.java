package Test;

import Domain.*;
import Domain.Excepciones.*;

import static Util.Console.*;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * MASTERMINDO GAME TEST
 * Implementa un Main para poder probar de manera interactiva el juego.
 * @author ISA
 */
public class MasterMindo {
    private static Usuario usr;
    private static Partida pary;
    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
            println("Bienvenid@ a MasterMindo un juego completamente nuevo y \n" +
                    "que no infringe ningun tipo de derechos de autor");
            println(">> ATENCIÓN: En esta prueba solo probaremos el juego interactivamente. <<\n" +
                "Las demás funcionalidades se encuentran en los tests concretos.\n");

            println("Ahora introduce tu nombre de usuario y presiona Enter.");
            String nombreUsuario = scan.next();
            usr = new Usuario(nombreUsuario);
            println("** ¡Se ha creado tu usuario! **");

            println("¡Vamos a jugar! Crearemos una nueva partida.");
            println("Escribe 1 para JUGAR como CODEMAKER o 2 para JUGAR como CODEBREAKER y presiona Enter.");
            int rol = scan.nextInt();
            if(rol == 1) juega(true);
            else if(rol == 2) juega(false);

            println("El juego ha terminado.");
    }

    /**
     * Crea una partida con el rol pasado por parámetro y la dificultad elegida por el usuario.
     * @param rol rol de la partida. True si es CodeMaker, false si es CodeBreaker.
     */
    private static void juega(Boolean rol) {
        println("Escribe el nivel de dificultad que elijas entre los siguientes: 'Facil', 'Medio' y 'Dificil'");
        String diff = scan.next();
        try {
            usr.creaPartidaActual(rol, diff);
            pary = usr.getPartidaActual();
            if (rol) juegaCodeMaker();
            else juegaCodeBreaker();
        } catch (ExcepcionPartidaAbandonada | ExcepcionYaExistePartidaActual | ExcepcionNoHayPartidaActual e) {
            println(e.getMessage());
        }
    }

    /**
     * Permite al usuario jugar como CodeBreaker.
     * @throws ExcepcionPartidaAbandonada cuando la partida se abandone.
     * @throws ExcepcionNoHayPartidaActual cuando se intente acceder a la partida actual y no exista.
     */
    private static void juegaCodeBreaker() throws ExcepcionPartidaAbandonada, ExcepcionNoHayPartidaActual {
        println("Partida empezada.");
        println("Intenta adivinar el código secreto.");
        int n;
        Boolean nuevoIntento = false;
        while(pary.getNumeroFilaActual() != 15 && !pary.isGanado()){
            while (!nuevoIntento) {
                println("¿Qué quieres hacer?");
                println("[1] Introducir un nuevo intento.\n" +
                        "[2] Pedir una pista.\n" +
                        "[3] Ayuda.\n" +
                        "[4] Abandonar la partida.");
                n = scan.nextInt();
                switch (n) {
                    case 1:
                        nuevoIntento = true;
                        break;
                    case 2:
                        obtenerPista();
                        break;
                    case 3:
                        obtenerAyuda(pary.isRolMaker());
                        break;
                    case 4:
                        println("¿Estás seguro de querer abandonar? Responde: 'Si' / 'No'.");
                        if (scan.next().equals("Si")) {
                            usr.abandonaPartidaActual();
                            throw new ExcepcionPartidaAbandonada("Partida abandonada.");
                        }
                        break;
                    default:
                        println("Opción no válida.");
                        break;
                }
            }

            nuevoIntento = false;

            computarIntentoUsuario();

            println("-> Has obtenido esta respuesta: " + pary.getUltimaRespuesta().toString());
        }

        if (!pary.isGanado()) {
            println("Has perdido! Este era el código secreto:");
            System.out.println(pary.getCodigoSecreto().codigo);
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
    private static void juegaCodeMaker() throws ExcepcionPartidaAbandonada, ExcepcionNoHayPartidaActual {
        println("Partida empezada.");
        setCodigoSecreto();
        int n;
        while(pary.getNumeroFilaActual() != 15 && !pary.isGanado()){
            println("¿Qué quieres hacer?");
            println("[1] Siguiente intento.\n" +
                    "[2] Ver tu código secreto.\n" +
                    "[3] Obtener ayuda.\n" +
                    "[4] Abandonar la partida.");
            n = scan.nextInt();
            switch (n) {
                case 1:
                    corrige();
                    break;
                case 2:
                    System.out.println("Tu código secreto: "+pary.getCodigoSecreto().codigo);
                    break;
                case 3:
                    obtenerAyuda(pary.isRolMaker());
                    break;
                case 4:
                    println("¿Estás seguro de querer abandonar? Responde: 'Si' / 'No'.");
                    if (scan.next().equals("Si")) {
                        usr.abandonaPartidaActual();
                        throw new ExcepcionPartidaAbandonada("Partida abandonada.");
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
        usr.finalizarPartidaActual(null);

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
        println("ATENCIÓN: El uso de pistas hará que la puntuación de la partida sea 0." +
                "ATENCIÓN: Solo puedes usar cada pista una vez.");
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
     * Devuelve el manual de usuario sobre como jugar según el rol
     * @param rol rol que está jugando el usuario.
     */
    private static void obtenerAyuda (Boolean rol) {
        if (rol) { //codemaker
            println("** COMO JUGAR EL ROL CODEMAKER **\n" +
                    "En este modo de juego debes crear tu código secreto.\n" +
                    "La máquina intentará descubrirlo. Solo debes corregir sus intentos.\n" +
                    "Hay un máximo de 15 intentos para descubrirlo.\n" +
                    "Para corregir sus intentos debes responder por cada ficha:\n" +
                    "> '8' si el número y la posición de la ficha coinciden con tu código secreto.\n" +
                    "> '7' si el número de la ficha está en otra posición de tu código secreto.\n" +
                    "> '0' si el número de la ficha no está en tu código secreto.");
            println(">> EJEMPLO <<\n" +
                    "Tu código secreto:  [1 2 3 4 5 6].\n" +
                    "Intento máquina:    [1 1 2 2 5 6].\n" +
                    "Respuesta correcta: [8 8 8 7 0 0].\n" +
                    "> Tres '8' porque coinciden el primer 1, el 5 y el 6.\n" +
                    "> Un '7' porque está en otra posición uno de los 2.\n" +
                    "> Dos '0' porque no se repiten el segundo 1 y el otro 2.\n");
        }
        else { //codebreaker
            println("** COMO JUGAR EL ROL CODEMAKER **\n" +
                    "En este modo de juego la máquina creará un código secreto.\n" +
                    "Debes intentar descubrirlo probando con códigos. La máquina corregirá tus intentos.\n" +
                    "Hay un máximo de 15 intentos para descubrirlo.\n" +
                    "Para intentar encontrarlo debes tener en cuenta la respuesta cada intento:\n" +
                    "> '8' si el número y la posición de una ficha coinciden con el código secreto.\n" +
                    "> '7' si el número de una ficha está en otra posición del código secreto.\n" +
                    "> '0' si el número de una ficha no está en el código secreto.");
        }
    }

    /**
     * Lee el intento del usuario, lo añade al tablero y genera la respuesta.
     * Aumenta el tiempo de la partida en lo que haya tardado el usuario en introducir el código.
     */
    private static void computarIntentoUsuario(){
        Scanner scan = new Scanner(System.in);
        Codigo code = new Codigo(pary.getNumColumnas());

        println("Introduce un código de tamaño " + pary.getNumColumnas() + " separado por espacios\n" +
                "Formado por numeros del 1 al " + pary.getNumColores());
        long startTime = System.currentTimeMillis();

        for(int i = 0; i < pary.getNumColumnas();++i){
            code.codigo.add(scan.nextInt());
        }
        pary.setIntento(code);

        long endTime = System.currentTimeMillis();
        pary.sumaTiempo((endTime - startTime)/1000);

        pary.generaRespuesta();
    }

}


