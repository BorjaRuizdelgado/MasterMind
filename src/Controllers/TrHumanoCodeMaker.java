package Controllers;

import Domain.Codigo;
import Domain.Respuesta;

import java.util.ArrayList;

public class TrHumanoCodeMaker extends Transaccion {
    public Codigo codigoSecreto;
    public Respuesta respuesta;

    public TrHumanoCodeMaker(Respuesta respuesta){
        this.respuesta = new Respuesta(respuesta.size);
        this.respuesta.respuesta = new ArrayList<>(respuesta.respuesta);

    }

    public void ejecutar(){

    }
}
