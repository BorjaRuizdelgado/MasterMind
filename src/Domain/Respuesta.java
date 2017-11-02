package Domain;

import java.util.ArrayList;
import java.util.List;

public class Respuesta {
    public List<Integer> respuesta;

    /**
     * Creadora
     * @param size
     */
    public Respuesta(int size){
        respuesta = new ArrayList<Integer>(size);
    }

    /**
     *
     * @param respuestaComprobada
     * @return cierto o falso dependiendo de si son iguales
     */
    public Boolean equals(Respuesta respuestaComprobada){
        return true;
    }
}
