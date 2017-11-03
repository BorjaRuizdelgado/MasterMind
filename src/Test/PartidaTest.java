package Test;

import Domain.Partida;

import java.util.Scanner;

public class PartidaTest {

    private static void print (String message) {
        System.out.print(message+"\n");
    }

    private static void probarAtributos (Partida p) {

    }

    private static void probarOperaciones (Partida p) {

    }

    public static void main (String[] args) {
        print("Aquí probaremos la clase Partida");
        while (true) {
            print("Para crear una nueva partida necesitamos saber el tipo y la dificultad");
            Scanner in = new Scanner(System.in);
            print("Introduce 1 para jugar como CodeMaker o 0 para jugar como CodeBreaker");
            int rolMaker = in.nextInt();
            Boolean rol;
            while (rolMaker != 0 && rolMaker != 1) {
                print("Rol no válido");
                rolMaker = in.nextInt();
            }
            if (rolMaker == 1) rol = true;
            else rol = false;
            print("Introduce la dificultad");
            print("Escoge entre 'Facil', 'Medio' o 'Dificil'");
            String dif = in.next();
            while (dif != "Facil" && dif != "Medio" && dif != "Dificil") {
                print("Dificultad no válida");
                dif = in.next();
            }
            Partida test = new Partida(rol,dif);
            print("¿Qué quieres hacer con la nueva partida creada?");
            print("1: Conocer los atributos de Partida. 2: Probar operaciones de partida");
            int choice = in.nextInt();
            while (choice != 1 && choice != 2) {
                print("Opción no valida");
                choice = in.nextInt();
            }
            while (true) {


                switch (choice) {
                    case 1:
                        probarAtributos(test);
                        break;
                    case 2:
                        probarOperaciones(test);
                        break;
                    default:
                        print("Escoge. 1: probar atributos. 2: probar operaciones.");
                        break;
                }
            }


        }

    }
}
