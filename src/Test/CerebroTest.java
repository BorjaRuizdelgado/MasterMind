package Test;

import Domain.Cerebro;
import Domain.Codigo;
import Domain.Fila;
import Domain.Respuesta;
import Util.Console;


import java.util.Scanner;

/**
 * @author Omar
 */

/**
 * Implementa un Main para poder probar de manera interactiva la clase.
 */
public class CerebroTest {

    public static void main(String[] args){
        final int colours = 6;
        final int columns;
        Console.println("Introduce el número de columnas:");
        Scanner in = new Scanner(System.in);
        columns = in.nextInt();
        Console.println("Introduce el código:");

        Codigo code = new Codigo(columns);
        for (int i = 0; i < columns; i++) {
            code.codigo.add(in.nextInt());
        }

        Cerebro cerebro = new Cerebro(colours, columns);
        Codigo codigo = cerebro.generaIntentoInicial();
        System.out.println(codigo.codigo);

        while(!code.codigo.equals(codigo.codigo)){
            Respuesta respuesta = codigo.getRespuesta(code);

            Fila fila = new Fila(columns);
            fila.setColores(codigo);
            fila.setRespuestas(respuesta);

            codigo = cerebro.getSiguienteIntento(fila);
            System.out.println(codigo.codigo);
        }

    }
}
