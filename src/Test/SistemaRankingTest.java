package Test;


import Domain.Codigo;
import Domain.Respuesta;

import java.util.Scanner;

/**
 * @author Omar
 */

/**
 * Implementa un Main para poder probar de manera interactiva la clase.
 */

public class SistemaRankingTest {

    private static void iniciaCodigo (Codigo codigo, int num) {
        Scanner in = new Scanner(System.in);
        System.out.print("Introduce los números (del 1 al 6) del código uno por uno\n");
        int code;
        for (int i = 0; i < num; i++) {
            code = in.nextInt();
            while (code < 1 || code > 6){
                System.out.print("Número no válido. Introduce uno del 1 al 6\n");
                code = in.nextInt();
            }
            codigo.codigo.add(code);
        }
    }

    private static void imprimeInfoCodigo (Codigo codigo) {
        System.out.print("Este es tu código: "+codigo.codigo+"\n" +
                "Y tiene este tamaño: "+codigo.size+"\n");
    }

    public static void main(String[] args) {
        System.out.print("Aquí probamos la Clase Código\n");
        while (true) {
            System.out.print("Probaremos un nuevo código.\n" +
                    "Por favor, introduce el tamaño del código deseado: 4 o 6\n");
            Scanner in = new Scanner(System.in);
            int num = in.nextInt();
            if (num == 4 || num == 6) {
                Codigo test = new Codigo(num);
                iniciaCodigo(test, num);
                imprimeInfoCodigo(test);
                System.out.print("Ahora probaremos de comparar dos códigos.\n" +
                        "Crearemos un nuevo código que funcionará como código secreto\n");
                Codigo testSecreto = new Codigo(num);
                iniciaCodigo(testSecreto, num);
                imprimeInfoCodigo(testSecreto);
                System.out.print("Obtendríamos esta respuesta: "+test.getRespuesta(testSecreto).respuesta+"\n" +
                        "Los 8 son fichas negras y los 7 fichas blancas. Los 0 fichas vacías.\n");
                System.out.print("Prueba acabada\n");
            } else System.out.print("Tamaño no válido\n");
        }
    }
}
