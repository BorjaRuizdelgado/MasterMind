package Test;


import Domain.Codigo;

import java.util.Scanner;

/**
 * @author ISA
 * Implementa un Main para poder probar de manera interactiva la clase.
 */
public class CodigoTest {

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

    private static void print (String message) {
        System.out.print(message+"\n");
    }

    public static void main(String[] args) {
        print("Aquí probamos la Clase Código");
        print("Probaremos un nuevo código.");
        print("Por favor, introduce el tamaño del código deseado: 4 o 6");
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        while (num != 4 && num != 6) {
            print("Tamaño no válido");
            num = in.nextInt();
        }
        Boolean fin = false;
        Codigo test = new Codigo(num);
        iniciaCodigo(test, num);
        imprimeInfoCodigo(test);
        int n;
        while (!fin) {
            print("¿Qué desea hacer?");
            print("1: Comparar dos códigos y obtener la respuesta." +
                    "2: Finalizar la prueba." +
                    "3: verificar si los codigosson iguales.");
            n = in.nextInt();
            switch (n) {
                case 1:
                    Codigo testSecreto = new Codigo(num);
                    iniciaCodigo(testSecreto, num);
                    imprimeInfoCodigo(testSecreto);
                    print("Obtendríamos esta respuesta: " + test.getRespuesta(testSecreto).toString());
                    break;
                case 2:
                    fin = true;
                    print("Prueba acabada");
                    break;
                case 3:
                    Codigo prueba = new Codigo(num);
                    iniciaCodigo(prueba, num);
                    imprimeInfoCodigo(prueba);
                    print("Los codigos son iguales: " + test.equals(prueba));
                    break;

            }
        }
    }
}
