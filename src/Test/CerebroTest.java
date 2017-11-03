package Test;

import Domain.Cerebro;
import Domain.Codigo;
import Domain.Fila;

public class CerebroTest {

    public static void main(String[] args){
        Cerebro cerebro = new Cerebro(6, 4);
        cerebro.generaIntentoInicial();
        Fila fila = new Fila(4);
        Codigo codigo = new Codigo(4);
        fila.setColores(codigo);
    }
}
