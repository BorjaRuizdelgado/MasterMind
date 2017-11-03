package Test;


import Domain.Codigo;

import java.util.Scanner;


public class CodigoTest {

    public static void main(String[] args) {
        System.out.print("Aquí probamos la Clase Código\n");
        while (true) {
            System.out.print("Por favor, introduce el tamaño del código deseado: 4 o 6\n");
            Scanner in = new Scanner(System.in);
            int num = in.nextInt();
            if (num == 4 || num == 6) {
                Codigo test = new Codigo(num);
                System.out.print("Por favor, introduce los números del código uno por uno\n");
                for (int i = 0; i < num; i++) {
                    int code = in.nextInt();
                    test.codigo.add(code);
                }
                System.out.print("Este es tu código:\n");
                System.out.print(test.codigo+"\n");
            } else System.out.print("Tamaño no válido\n");
        }
    }
}
