package Controllers;

import Domain.Codigo;

import java.util.ArrayList;

public class TrHumanoCodeBreaker extends Transaccion {
    private Codigo guess;


    public TrHumanoCodeBreaker(Codigo codigo){
        guess = new Codigo(codigo.size);
        guess.codigo = new ArrayList<>(codigo.codigo);

    }
    public void ejecutar(){


    }
}
