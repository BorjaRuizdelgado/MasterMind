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

    public static void main(String[] args){
        final int colours = 6;
        final int columns;
        Console.println("Introduce el número de columnas:");
        Scanner in = new Scanner(System.in);
        columns = in.nextInt();
        Console.println("Introduce el código secreto con números del 1 al 6 separados por espacios:");

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
