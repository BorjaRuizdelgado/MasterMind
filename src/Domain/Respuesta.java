package Domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author borja
 */

/**
 * Estructura de datos para las Respuestas de mastermind
 */

public class Respuesta {
    public List<Integer> respuesta;
    public int size = 4;

    /**
     * Creadora.
     * @param size de la respuesta
     */
    public Respuesta(int size){
        respuesta = new ArrayList<Integer>(size);
        this.size = size;
    }

    /**
     * Comprueba si la respuesta pasada por parametro es igual a la de la clase.
     * @param respuestaComprobada
     * @return Cierto si lo son, Falso si no.
     */
    public Boolean equals(Respuesta respuestaComprobada){
        List<Integer> auxiliar1 = new ArrayList<Integer>(this.respuesta);
        Collections.sort(auxiliar1);
        List<Integer> auxiliar2 = new ArrayList<Integer>(respuestaComprobada.respuesta);
        Collections.sort(auxiliar2);
        return auxiliar1.equals(auxiliar2);
    }

    /**
     * Convierte los numeros de la respuesta en B y W para poder hacer el MINMAX en la IA
     * @return
     */
    @Override
    public String toString() {
        String Return = "";
        for (int i = 0; i < respuesta.size(); i++) {
            if (respuesta.get(i) == 7){
                Return += "W";
            }
            else if(respuesta.get(i) == 8){
                Return += "B";
            }
        }
        return Return;
    }
}
