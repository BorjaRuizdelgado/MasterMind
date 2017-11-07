package Test;

import Domain.Partida;

import java.util.Scanner;

/**
 * @author ISA
 *
 */
/**
 * Implementa un Main para poder probar de manera interactiva la clase.
 */
public class PartidaTestNoJUnit {

    private static Partida creaPartida() {
        Scanner in = new Scanner(System.in);
        print("Introduce 1 para ser CodeMaker o 0 para ser CodeBreaker");
        int rolMaker = in.nextInt();
        Boolean rol;
        while (rolMaker != 0 && rolMaker != 1) {
            print("Rol no válido");
            rolMaker = in.nextInt();
        }
        rol = (rolMaker == 1);
        print("Introduce la dificultad: 'Facil', 'Medio' o 'Dificil'");
        String dif = in.next();
        while (!dif.equals("Facil") && !dif.equals("Medio") && !dif.equals("Dificil")) {
            print("Dificultad no válida");
            dif = in.next();
        }
        return new Partida(rolMaker==1,dif);
    }

    private static void print (String message) {
        System.out.print(message+"\n");
    }

    public static void main (String[] args) {
        print("Aquí probaremos la clase Partida");
        Scanner in = new Scanner(System.in);
        Partida test = creaPartida();
        Boolean fin = false;
        int n;
        while (!fin) {
            print("¿Qué quieres hacer con la nueva partida creada?");
            print("1: Ver información principal.\n" +
                    "2: Ver toda la información.\n" +
                    "3: Probar operaciones.\n" +
                    "4: Acabar la prueba");
            n = in.nextInt();
            switch (n) {
                case 1:
                    test.imprimeInfo();
                    break;
                case 2:
                    test.imprimeAllInfo();
                    break;
                case 3:
                    // FALTA ACABAR
                    print("Opción no implementada por el momento");
                    break;
                case 4:
                    fin = true;
                    break;
                default:
                    print("Opción no válida.");
                    break;
            }
        }

    }

}
