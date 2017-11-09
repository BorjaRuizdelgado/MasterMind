package Test;

import static Util.Console.*;
import Domain.Partida;
import Domain.Usuario;

import java.util.Scanner;

/**
 * @author ISA
 * Implementa un Main para poder probar de manera interactiva la clase.
 */
public class UsuarioTest {

    /**
     * Crea una partida actual para el usuario pasado por parámetro y la devuelve
     * @param test usuario al que se le asigna la nueva partida
     * @return nueva partida actual creada para el usuario
     */
    private static Partida creaPartida (Usuario test) {
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
        return test.getPartidaActual();
    }

    public static void main (String[] args) {
        println("Aquí probamos la clase Usuario");
        println("Introduce tu nombre de usuario sin espacios:");
        Scanner in = new Scanner(System.in);
        String name = in.next();
        Usuario test = new Usuario(name);
        println("Se ha creado el usuario con nombre: "+test.getNombre());

        int n;
        Boolean fin = false;
        Boolean p = false;
        while(!fin) {
            println("¿Qué desea hacer?");
            println("1: Ver mi información.\n2: Cambiar nombre.\n3: Crear una partida\n" +
                    "4: Guardar la partida actual.\n5: Obtener info de las partidas guardadas.\n" +
                    "6: Finalizar partida actual. \n7: Acabar la prueba.");
            n = in.nextInt();
            switch (n) {
                case 1:
                    println("- Nombre: "+test.getNombre());
                    println("- Partidas Finalizadas: "+test.getNumPartidasFinalizadas());
                    break;
                case 2:
                    println("Introduce el nuevo nombre de usuario sin espacios:");
                    name = in.next();
                    test.setNombre(name);
                    break;
                case 3:
                    if(!p) {
                        creaPartida(test);
                        println("Partida creada con ID: " + test.getIdPartidaActual());
                        p = true;
                    }
                    else println("Ya existe una partida actual. Guárdala antes de crear otra.");
                    break;
                case 4:
                    if (p) {
                        test.guardaPartidaActual();
                        println("Partida guardada.");
                        p = false;
                    }
                    else println("No hay ninguna partida actual");
                    break;
                case 5:
                    test.imprimeInfoPartidasGuardadas();
                    break;
                case 6:
                    if (p) {
                        test.finalizarPartidaActual();
                        println("Partida Finalizada");
                        p = false;
                    }
                    else println("No hay partida actual");
                    break;
                case 7:
                    fin = true;
                    break;
                default:
                    println("Opción no válida");
                    break;
            }
        }
    }

}
