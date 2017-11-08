package Util;

import Domain.Codigo;
/**
 *
 * @author Omar
 */
public class Comparator implements java.util.Comparator<Codigo> {

    /**
     * Convertimos un código a número, Ej: [1, 2, 3, 2] -> 1232
     * @param code El código a que vamos a tomar para devolver un número
     * @return Devolvemos el número generado.
     */
    private int toNumber(Codigo code){
        String Return = "";
        for (int i = 0; i < code.codigo.size(); i++) {
            Return += String.valueOf(code.codigo.get(i));
        }
        return Integer.valueOf(Return);
    }

    /**
     * Esta función evalúa los dos códigos que le pasamos como parámetro, de manera que podamos ordenarlos de menor a
     * mayor cuándo esté en una lista.
     * @param o1 Es el código 1 a comparar
     * @param o2 Es el código 2 a comparar
     * @return Si lo que devolvemos es negativo, o1 irá primero, es caso contrario, será o2 quien vaya primero.
     * Si da 0, no importa quien vaya primero.
     */
    @Override
    public int compare(Codigo o1, Codigo o2) {
        int numA = toNumber(o1);
        int numB = toNumber(o2);
        return numA - numB;
    }
}
