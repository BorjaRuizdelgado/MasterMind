package Test;

import Domain.Cerebro;
import Domain.Codigo;
import Domain.Fila;
import Domain.Respuesta;
import Util.Console;

import java.util.Scanner;

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
        Codigo intento = cerebro.getIntentoInicial();
        System.out.print(intento.codigo);

        while(!code.equals(intento)){
            Respuesta respuesta = intento.getRespuesta(code);
            System.out.println(respuesta);

            Fila fila = new Fila(columns);
            fila.setColores(intento);
            fila.setRespuestas(respuesta);

            intento = cerebro.getSiguienteIntento(fila);
            System.out.println(intento.codigo);
        }

    }
}
