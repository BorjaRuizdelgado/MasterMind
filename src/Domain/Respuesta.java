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

    @Override
    public String toString() {
        String Return = "";
        for (int i = 0; i < respuesta.size(); i++) {
            if (respuesta.get(i) == 7){
                Return += "B";
            }
            else if(respuesta.get(i) == 8){
                Return += "W";
            }
        }
        return Return;
    }
}
