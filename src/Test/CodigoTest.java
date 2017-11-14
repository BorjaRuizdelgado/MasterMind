package Test;


import Domain.Codigo;
import static Util.Console.*;
import java.util.Scanner;

/**
 * Codigo Test
 * Implementa un Main para poder probar de manera interactiva la clase.
 * @author ISA
 */
public class CodigoTest {

    private static void iniciaCodigo (Codigo codigo, int num) {
        Scanner in = new Scanner(System.in);
        println("Introduce los números (del 1 al 6) del código uno por uno");
        int code;
        for (int i = 0; i < num; i++) {
            code = in.nextInt();
            while (code < 1 || code > 6){
                println("Número no válido. Introduce uno del 1 al 6");
                code = in.nextInt();
            }
            codigo.codigo.add(code);
        }
    }

    private static void imprimeInfoCodigo (Codigo codigo) {
        println("Este es tu código: "+codigo.codigo+"\n" +
                "Y tiene este tamaño: "+codigo.size);
    }

    public static void main(String[] args) {
        println("Aquí probamos la Clase Código");
        println("Probaremos un nuevo código.");
        println("Por favor, introduce el tamaño del código deseado: 4 o 6");
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        while (num != 4 && num != 6) {
            println("Tamaño no válido.");
            num = in.nextInt();
        }
        Boolean fin = false;
        Codigo test = new Codigo(num);
        iniciaCodigo(test, num);
        imprimeInfoCodigo(test);
        int n;
        while (!fin) {
            println("¿Qué desea hacer?");
            println("[1] Comparar dos códigos y obtener la respuesta.\n" +
                    "[2] Verificar si los codigos son iguales.\n" +
                    "[3] Finalizar la prueba. ");
            n = in.nextInt();
            switch (n) {
                case 1:
                    Codigo testSecreto = new Codigo(num);
                    iniciaCodigo(testSecreto, num);
                    imprimeInfoCodigo(testSecreto);
                    println("Obtendríamos esta respuesta: " + test.getRespuesta(testSecreto).toString());
                    break;
                case 2:
                    Codigo prueba = new Codigo(num);
                    iniciaCodigo(prueba, num);
                    imprimeInfoCodigo(prueba);
                    println("Los codigos son iguales: " + test.equals(prueba));
                    break;
                case 3:
                    fin = true;
                    println("Prueba acabada");
                    break;
            }
        }
    }
}
