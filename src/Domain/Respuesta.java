package Domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Respuesta {
    public List<Integer> respuesta;
    public int size = 4;

    public Respuesta(int size){
        respuesta = new ArrayList<Integer>(size);
        this.size = size;
    }
    public Boolean equals(Respuesta respuestaComprobada){
        List<Integer> auxiliar1 = new ArrayList<Integer>(this.respuesta);
        Collections.sort(auxiliar1);
        List<Integer> auxiliar2 = new ArrayList<Integer>(respuestaComprobada.respuesta);
        Collections.sort(auxiliar2);
        return auxiliar1.equals(auxiliar2);
    }
}
