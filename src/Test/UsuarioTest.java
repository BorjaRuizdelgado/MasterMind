package Test;

import Domain.Partida;
import Domain.Usuario;

import java.util.Scanner;

public class UsuarioTest {

    private static Partida creaPartida () {
        Scanner in = new Scanner(System.in);
        print("Introduce el ROL: 1 (CodeMaker) ó 0 (CodeBreaker)");
        int rol = in.nextInt();
        while (rol != 1 && rol != 0) {
            print("Rol no válido");
            rol = in.nextInt();
        }
        print("Introduce la DIFICULTAD: 'Facil', 'Medio' o 'Dificil'");
        String dif = in.next();
        while (!dif.equals("Facil") && !dif.equals("Medio") && !dif.equals("Dificil")) {
            print("Dificultad no válida");
            dif = in.next();
        }
        return new Partida(rol == 1, dif);
    }

    private static void print (String message) {
        System.out.print(message+"\n");
    }

    public static void main (String[] args) {
        print("Aquí probamos la clase Usuario");
        print("Introduce tu nombre de usuario:");
        Scanner in = new Scanner(System.in);
        String name = in.next();
        Usuario test = new Usuario(name);
        print("Se ha creado el usuario con nombre: "+test.getNombre());

        int n;
        Boolean fin = false;
        Boolean p = false;
        while(!fin) {
            print("¿Qué desea hacer?");
            print("1: Ver atributos. 2: Cambiar nombre. 3: Crear una partida\n" +
                    "4: Guardar la partida actual. 5: Obtener info de las partidas guardadas.\n" +
                    "6: Finalizar partida actual. 7: Acabar la prueba.");
            n = in.nextInt();
            switch (n) {
                case 1:
                    print("- Nombre: "+test.getNombre());
                    print("- Partidas Finalizadas: "+test.getNumPartidasFinalizadas());
                    break;
                case 2:
                    print("Introduce el nuevo nombre de usuario:");
                    name = in.next();
                    test.setNombre(name);
                    break;
                case 3:
                    if(!p) {
                        test.setPartidaActual(creaPartida());
                        print("Partida creada con ID: " + test.getIdPartidaActual());
                        p = true;
                    }
                    else print("Ya existe una partida actual. Guárdala antes de crear otra.");
                    break;
                case 4:
                    if (p) {
                        test.guardaPartidaActual();
                        print("Partida guardada.");
                        p = false;
                    }
                    else print("No hay ninguna partida actual");
                    break;
                case 5:
                    test.imprimeInfoPartidasGuardadas();
                    break;
                case 6:
                    if (p) {
                        test.finalizarPartidaActual();
                        print("Partida Finalizada");
                        p = false;
                    }
                    else print("No hay partida actual");
                    break;
                case 7:
                    fin = true;
                    break;
                default:
                    print("Opción no válida");
                    break;
            }
        }
    }

}
