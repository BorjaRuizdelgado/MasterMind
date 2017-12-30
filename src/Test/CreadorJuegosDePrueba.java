package Test;

import Domain.Controllers.ControladorDominio;
import Domain.Excepciones.ExcepcionUsuarioExiste;
import Domain.Excepciones.ExcepcionUsuarioInexistente;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import static Util.Console.*;

/**
 * CREADOR DE JUEGOS DE PRUEBA
 * Permite crear, ver y borrar usuarios, sus partidas guardadas y clasificaciones.
 * @author ISA
 */
public class CreadorJuegosDePrueba {
    private static ControladorDominio controladorDominio;
    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        println("Bienvenid@ al creador de juegos de prueba de MasterMindo.\n" +
                "Aquí podrás crear y borrar Usuarios, Partidas guardadas y Clasificaciones.\n" +
                "Para que no haya ningún error, por favor cierre el creador correctamente.\n");
        controladorDominio = ControladorDominio.getInstance();
        menu();
    }

    private static void menu() {
        int number = 0;
        while (number != 4) {
            println("¿Qué quieres hacer?");
            println("[1] Crear.\n" +
                    "[2] Visualizar.\n" +
                    "[3] Borrar.\n" +
                    "[4] Cerrar creador.");
            number = scan.nextInt();
            switch (number) {
                case 1:
                    menuCrear();
                    break;
                case 2:
                    menuVer();
                    break;
                case 3:
                    menuBorrar();
                    break;
                case 4:
                    controladorDominio.onClose();
                    println("Creación finalizada.");
                    break;
                default:
                    println("Opción no válida.");
                    break;
            }
        }
    }

    private static void menuCrear() {
        println("[1] Crear usuario.\n" +
                "[2] Crear partida y guardarla.\n" +
                "[3] Crear un nuevo récord.\n" +
                "[4] Atrás.");
        int number;
        number = scan.nextInt();
        switch (number) {
            case 1:
                crearUsuario();
                break;
            case 2:
                crearPartidaUsuario();
                break;
            case 3:
                crearNuevoRecord();
                break;
            case 4:
                break;
            default:
                println("Opción no válida.");
                break;
        }

    }

    private static void menuVer() {
        println("[1] Ver usuarios.\n" +
                "[2] Ver usuarios y sus partidas guardadas.\n" +
                "[3] Ver ránking.\n" +
                "[4] Atrás.");
        int number;
        number = scan.nextInt();
        switch (number) {
            case 1:
                verUsuarios();
                break;
            case 2:
                verPartidasUsuarios();
                break;
            case 3:
                verRecords();
                break;
            case 4:
                break;
            default:
                println("Opción no válida.");
                break;
        }
    }

    private static void menuBorrar() {
        println("[1] Borrar usuario.\n" +
                "[2] Borrar partida guardada.\n" +
                "[3] Borrar récord.\n" +
                "[4] Atrás.");
        int number;
        number = scan.nextInt();
        switch (number) {
            case 1:
                borrarUsuario();
                break;
            case 2:
                borrarPartidaUsuario();
                break;
            case 3:
                borrarRecord();
                break;
            case 4:
                break;
            default:
                println("Opción no válida.");
                break;
        }
    }

    private static void crearUsuario() {
        Boolean done = false;
        while (!done) {
            println("Introduce el nombre del nuevo usuario. Sin espacios ni carácteres especiales.");
            String name = scan.next();
            try {
                controladorDominio.crearUsuario(name);
                done = true;
                println("Usuario creado.");
            } catch (ExcepcionUsuarioExiste e) {
                println(e.getMessage());
            }
        }
    }

    private static void crearPartidaUsuario() {
        if (verUsuarios()) {
            escogerUsuario();
            int rol = -1;
            while (rol != 0 && rol != 1) {
                if (rol != -1) println("Rol incorrecto.");
                println("Escoge rol: [0] CodeBreaker, [1] CodeMaker.");
                rol = scan.nextInt();
            }
            String dif = escogerDificultad();
            if(rol == 0) controladorDominio.crearPartidaUsuarioCargadoRolBreaker(dif);
            else controladorDominio.crearPartidaUsuarioCargadoRolMaker(dif,null);
            println("Partida creada.");
        }
    }

    private static void crearNuevoRecord() {
        if (verUsuarios()) {
            escogerUsuario();
            println("Escribe la puntuación");
            int points = -1;
            while (points < 0 || points > 300001) {
                if (points != -1) println("Puntuación incorrecta.");
                println("Escribe la puntuación [rango: 0 - 300000].");
                points = scan.nextInt();
            }
            String dif = escogerDificultad();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDate localDate = LocalDate.now();
            String fecha = dtf.format(localDate);
            controladorDominio.creaPuntuacion(controladorDominio.getUsuario(), points, fecha, dif);
        }

    }

    private static Boolean verUsuarios() {
        List<String> userNames = controladorDominio.getTodosUsuarios();
        if (userNames == null) {
            println("Crea primero algún usuario.");
            return false;
        }
        else {
            for (String userName : userNames) {
                println("* " + userName);
            }
            return true;
        }
    }

    private static void verPartidasUsuarios() {

    }

    private static void verRecords() {

    }

    private static void borrarUsuario() {

    }

    private static void borrarPartidaUsuario() {

    }

    private static void borrarRecord() {

    }




    private static void escogerUsuario() {
        println("Escribe el nombre de usuario al que asignar la partida guardada.");
        Boolean done = false;
        while (!done) {
            String username = scan.next();
            try {
                controladorDominio.cargarUsuario(username);
                done = true;
                println("Usuario " + username + " cargado.");
            } catch (ExcepcionUsuarioInexistente e) {
                println(e.getMessage());
            }
        }
    }

    private static String escogerDificultad() {
        String dif = "Dif";
        while (!dif.equals("Facil") && !dif.equals("Medio") && !dif.equals("Dificil")) {
            if (!dif.equals("Dif")) println("Dificultad incorrecta.");
            println("Escige dificultad: [Facil], [Medio] o [Dificil].");
            dif = scan.next();
        }
        return dif;
    }

}
