package Test;

import Domain.*;
import Util.Console;


import java.util.Scanner;

/**
 * MasterCerebro Test
 * Implementa un Main para poder probar de manera interactiva la clase.
 * @author Omar
 */

public class MasterCerebroTest {

    /**
     * Método en el que inicializamos y probamos la clase Cerebro con los datos (colores/columnas) introducidos.
     * @param args
     */
    public static void main(String[] args){
        final int colours;
        final int columns;
        Scanner in = new Scanner(System.in);

        Console.println("Introduce el número de columnas:");
        columns = in.nextInt();

        Console.println("Introduce el número de colores:");
        colours = in.nextInt();

        if (columns >= 10 || colours >= 20) Console.println("Al tener muchas columnas/filas esto puede tardar unos minutos.", "red");

        Console.println("Introduce el código secreto con números del 1 al " + String.valueOf(colours) + " separados por espacios:");
        Codigo code = new Codigo(columns);
        for (int i = 0; i < columns; i++) {
            code.codigo.add(in.nextInt());
        }

        Inteligencia cerebro = new MasterCerebro(colours, columns);
        Codigo intento = cerebro.getIntentoInicial();
        System.out.print(intento.codigo);

        while(true){
            Respuesta respuesta = intento.getRespuesta(code);
            System.out.println(" -> " +  "{" + respuesta + "}");
            if(code.equals(intento)) break; // Finalizamos si ya hemos encontrado el código

            Fila fila = new Fila(columns);
            fila.setColores(intento);
            fila.setRespuestas(respuesta);

            intento = cerebro.getSiguienteIntento(fila);
            System.out.print(intento.codigo);
        }
    }
}
