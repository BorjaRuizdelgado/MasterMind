package Domain;

import java.util.ArrayList;
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
        return Collections.sort(this.respuesta).equals(Collections.sort(respuestaComprobada.respuesta));
    }
}
