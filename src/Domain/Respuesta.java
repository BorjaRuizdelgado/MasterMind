package Domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Clase Respuesta
 * Estructura de datos para las Respuestas del juego
 * @author borja
 */

public class Respuesta {

    public List<Integer> respuesta;
    public int size = 4;

    /**
     * Creadora Respuesta.
     * Crea un vector de tamaño pasado por parámetro.
     * @param size de la respuesta
     */
    public Respuesta(int size){
        respuesta = new ArrayList<>(size);
        this.size = size;
    }

    /**
     * Comprueba si la respuesta pasada por parametro es igual a la de la clase.
     * @param respuestaComprobada Respuesta a comprobar
     * @return Cierto si lo son, Falso si no.
     */
    public Boolean equals(Respuesta respuestaComprobada){
        respuesta.sort(Collections.reverseOrder());
        respuestaComprobada.respuesta.sort(Collections.reverseOrder());
        return respuesta.equals(respuestaComprobada.respuesta);
    }

    /**
     * Devuelve si la respuesta es de tipo ganadora, es decir negro
     * @return Devulve Cierto o Falso en fuención de si es ganadora o no.
     */
    public boolean esGanadora(){
        for(int i = 0; i < size; ++i)
            if (respuesta.get(i) !=  8) return false;
        return true;
    }

    /**
     * Convierte los numeros de la respuesta en B y W para poder hacer el MINMAX en la IA
     * @return la respuesta en letras
     */
    @Override
    public String toString() {
        String Return = "";
        for (Integer nRespuesta : respuesta) {
            if (nRespuesta == 7) Return += "W";
            else if (nRespuesta == 8) Return += "B";
        }
        return Return;
    }
}
