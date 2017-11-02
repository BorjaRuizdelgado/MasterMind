package Domain;

import java.util.ArrayList;
import java.util.List;

public class Respuesta {
    public List<Integer> respuesta;

    public Respuesta(int size){
        respuesta = new ArrayList<Integer>(size);
    }
    public Boolean equals(Respuesta respuestaComprobada){
        return true;
    }
}
