package Test;

import static Util.Console.*;

import Domain.Excepciones.ExcepcionNoHayPartidaActual;
import Domain.Excepciones.ExcepcionNoHayPartidasGuardadas;
import Domain.Excepciones.ExcepcionPruebaTerminada;
import Domain.Excepciones.ExcepcionYaExistePartidaActual;
import Domain.Usuario;

import java.util.Scanner;

/**
 * Usuario Test
 * Implementa un Main para poder probar de manera interactiva la clase.
 * @author ISA
 */
public class UsuarioTest {
    private static Scanner in = new Scanner(System.in);

    public static void main (String[] args) {
        println("Aquí probamos la clase Usuario");
        println("Introduce tu nombre de usuario sin espacios:");

        String name = in.next();
        Usuario test = new Usuario(name);
        println("Se ha creado el usuario con nombre: "+test.getNombre());

        testea(test);

    }

    /**
     * @param test usuario de la prueba
     */
    private static void testea (Usuario test)  {
        int n;
        Boolean fin = false;
        while(!fin) {
            println("¿Qué desea hacer?");
            println("[1] Ver mi información y estadísticas.\n" +
                    "[2] Cambiar nombre.\n" +
                    "[3] Crear una partida\n" +
                    "[4] Guardar la partida actual.\n" +
                    "[5] Obtener info de las partidas guardadas.\n" +
                    "[6] Cargar una partida actual\n" +
                    "[7] Finalizar como perdida la partida actual.\n" +
                    "[8] Finalizar como ganada la partida actual.\n" +
                    "[9] Reiniciar estadísticas.\n" +
                    "[10] Acabar la prueba.");

            n = in.nextInt();
            switch (n) {
                case 1: // ver informacion y estadisticas usuario
                    println("- Nombre: "+test.getNombre());
                    println("- Partidas Finalizadas CodeBreaker: "+test.getNumPartidasFinalizadasCB());
                    println("- Partidas Ganadas CodeBreaker: "+test.getNumPartidasGanadasCB());
                    println("- Partidas Finalizadas CodeMaker: "+test.getNumPartidasFinalizadasCM());
                    float pGanadasCodeBreaker = (float)test.getNumPartidasGanadasCB()/test.getNumPartidasFinalizadasCB()*100;
                    float pCodeMaker = (float)test.getNumPartidasFinalizadasCM()/(test.getNumPartidasFinalizadasCB()+test.getNumPartidasFinalizadasCM())*100;
                    println("- Porcentaje ganadas CodeBreaker: "+pGanadasCodeBreaker);
                    println("- Porcentaje CodeMaker: "+pCodeMaker);
                    break;
                case 2:  // cambiar nombre
                    println("Introduce el nuevo nombre de usuario sin espacios:");
                    String name = in.next();
                    test.setNombre(name);
                    break;
                case 3: // crear una nueva partida
                    try {
                        creaPartida(test);
                        println("Partida creada con ID: " + test.getIdPartidaActual());
                    } catch (ExcepcionYaExistePartidaActual | ExcepcionNoHayPartidaActual e) {
                        println(e.getMessage());
                    }
                    break;
                case 4: // guardar partida
                    try {
                        test.guardaPartidaActual();
                        println("Partida guardada.");
                    } catch (ExcepcionNoHayPartidaActual e) {
                        println(e.getMessage());
                    }
                    break;
                case 5: // obtener info partidas guardadas
                    try {
                        test.imprimeInfoPartidasGuardadas();
                    } catch (ExcepcionNoHayPartidasGuardadas e) {
                        println(e.getMessage());
                    }
                    break;
                case 6: // cargar partida
                    try {
                        test.imprimeInfoPartidasGuardadas();
                        println("Escribe el número de partida guardada que quieras cargar.");
                        int i = in.nextInt();
                        test.cargaPartida(i);
                        println("Partida cargada.");
                    } catch (ExcepcionNoHayPartidasGuardadas | ExcepcionYaExistePartidaActual e) {
                        println(e.getMessage());
                    }
                    break;
                case 7: // finalizar partida actual como perdida
                    try {
                        test.finalizarPartidaActual(false);
                        println("Partida Finalizada.");
                    } catch (ExcepcionNoHayPartidaActual e) {
                        println(e.getMessage());
                    }
                    break;
                case 8: // finalizar partida actual como ganada
                    try {
                        test.finalizarPartidaActual(true);
                        println("Partida Finalizada");
                    } catch (ExcepcionNoHayPartidaActual e) {
                        println(e.getMessage());
                    }
                    break;
                case 9: // reiniciar estadísticas
                    test.reiniciaEstadisticas();
                    println("Estadísticas Reiniciadas.");
                    break;
                case 10: // acabar prueba
                    fin = true;
                    break;
                default:
                    println("Opción no válida");
                    break;
            }
        }
    }

    /**
     * Crea una partida actual para el usuario pasado por parámetro y la devuelve
     * @param test usuario al que se le asigna la nueva partida
     * @throws ExcepcionYaExistePartidaActual cuando el usuario ya tiene una partida actual
     */
    private static void creaPartida (Usuario test) throws ExcepcionYaExistePartidaActual {
        Scanner in = new Scanner(System.in);
        println("Introduce el ROL: 1 (CodeMaker) ó 0 (CodeBreaker)");
        int rol = in.nextInt();
        while (rol != 1 && rol != 0) {
            println("Rol no válido");
            rol = in.nextInt();
        }
        println("Introduce la DIFICULTAD: 'Facil', 'Medio' o 'Dificil'");
        String dif = in.next();
        while (!dif.equals("Facil") && !dif.equals("Medio") && !dif.equals("Dificil")) {
            println("Dificultad no válida");
            dif = in.next();
        }
        test.creaPartidaActual(rol==1, dif);
    }

}
