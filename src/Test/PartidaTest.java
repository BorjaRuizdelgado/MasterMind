package Test;

import Domain.Partida;

import java.util.Scanner;

public class PartidaTest {

    private static void print (String message) {
        System.out.print(message+"\n");
    }

    private static void verAtributos (Partida p) {
        print("¿Qué atributo deseas ver?");
        print("1: id. 2: tiempo. 3: rol del Jugador. 4: dificultad.\n" +
                "5: número Colores. 6: número Columnas");
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        switch (num) {
            case 1:
                print("ID: "+p.getIdPartida());
                break;
            case 2:
                print("Tiempo: "+p.getTiempo());
                break;
            case 3:
                if (p.isRolMaker()) print("Rol: CodeMaker");
                else print("Rol: CodeBreaker");
                break;
            case 4:
                print("Dificultad: "+p.getDificultad());
                break;
            case 5:
                print("NumColores: "+p.getNumColores());
                break;
            case 6:
                print("NumColumnas: "+p.getNumColumnas());
                break;
            default:
                print("Opción no válida.");
                break;
        }
    }

    private static void cambiarAtrbutos (Partida p) {
        print("¿Qué atributo deseas cambiar?");
        print("1: id. 2: tiempo. 3: rol del Jugador. 4: dificultad.\n" +
                "5: número Colores. 6: número Columnas");
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        switch (num) {
            case 1:
                print("¿Qué id quieres poner?");
                p.setIdPartida(in.next());
                break;
            case 2:
                print("¿Qué tiempo quieres poner?");
                p.setTiempo(in.nextFloat());
                break;
            case 3:
                print("¿Qué rol quieres que tenga? 1: CodeMaker. 0: CodeBreaker");
                int r = in.nextInt();
                if (r == 1) p.setRolMaker(true);
                else p.setRolMaker(false);
                break;
            case 4:
                print("¿Qué dificultad quieres poner?");
                print("Escoge entre 'Facil', 'Medio' o 'Dificil'");
                p.setDificultad(in.next());
                break;
            case 5:
                print("¿Cuántos colores quieres tener?");
                p.setNumColores(in.nextInt());
                break;
            case 6:
                print("¿Cuántas columnas quieres tener?");
                p.setNumColumnas(in.nextInt());
                break;
            default:
                print("Opción no válida.");
                break;
        }
    }

    private static void probarAtributos (Partida p) {
        Boolean fin = false;
        while (!fin) {
            print("¿Qué quieres hacer?");
            print("1: Ver atributos. 2: Cambiar atributos.");
            Scanner in = new Scanner(System.in);
            int num = in.nextInt();
            while (num != 1 && num != 2) {
                print("Opción no válida");
                num = in.nextInt();
            }
            switch (num) {
                case 1:
                    verAtributos(p);
                    break;
                case 2:
                    cambiarAtrbutos(p);
                    break;
                default:
                    break;
            }
            print("¿Desea continuar probando los atributos? 'SI' o 'NO'");
            String fi = in.next();
            if (fi.equals("NO")) fin = true;
        }
    }

    private static void probarOperaciones (Partida p) {
        print("Qué quieres hacer?");
        print("1: Sumar tiempo. 2: ");
    }

    public static void main (String[] args) {
        print("Aquí probaremos la clase Partida");
        print("Para crear una nueva partida necesitamos saber el tipo y la dificultad");
        Scanner in = new Scanner(System.in);
        print("Introduce 1 para jugar como CodeMaker o 0 para jugar como CodeBreaker");
        int rolMaker = in.nextInt();
        Boolean rol;
        while (rolMaker != 0 && rolMaker != 1) {
            print("Rol no válido");
            rolMaker = in.nextInt();
        }
        rol = (rolMaker == 1);
        print("Introduce la dificultad");
        print("Escoge entre 'Facil', 'Medio' o 'Dificil'");
        String dif = in.next();
        while (!dif.equals("Facil") && !dif.equals("Medio") && !dif.equals("Dificil")) {
            print("Dificultad no válida");
            dif = in.next();
        }
        Partida test = new Partida(rol,dif);
        Boolean fin = false;
        int choice;
        while (!fin) {
            print("¿Qué quieres hacer con la nueva partida creada?");
            print("1: Ver y modificar los atributos de Partida. 2: Probar operaciones de partida");
            choice = in.nextInt();
            while (choice != 1 && choice != 2) {
                print("Opción no válida");
                choice = in.nextInt();
            }
            switch (choice) {
                case 1:
                    probarAtributos(test);
                    break;
                case 2:
                    probarOperaciones(test);
                    break;
                default:
                    break;
            }
            print("¿Desea continuar probando partida? 'SI' o 'NO'");
            String fi = in.next();
            if (fi.equals("NO")) fin = true;
        }

    }

}
