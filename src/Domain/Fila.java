package Domain;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Clase Fila
 * Estructura de datos para las filas del tablero
 * Contiene un código de colores y una respuesta.
 * @author borja
 */
public class Fila {

    private Codigo colores;
    private Respuesta respuestas;

    /**
     * Creadora Fila.
     * Crea un código de colores y una respuesta de tamaño numero columnas.
     * @param sizeColumnas tamaño
     */
    public Fila(int sizeColumnas){
        colores = new Codigo(sizeColumnas);
        respuestas = new Respuesta(sizeColumnas);
    }


    /* CONSULTORAS */

    /**
     * Devuelve el código de colores
     * @return codigo de colores.
     */
    public Codigo getColores(){
        return colores;
    }

    /**
     * Devuelve la respuesta al codigo de colores.
     * @return respuesta al código.
     */
    public Respuesta getRespuestas(){
        return respuestas;
    }


    /* MODIFICADORAS */

    /**
     * Añade el código de colores.
     * @param colours es el array que se copiara en la variable colores.
     */
    public void setColores(Codigo colours){
        colores.codigo = new ArrayList<>(colours.codigo);

    }

    /**
     * Añade la respuesta al código de colores.
     * Se añade siempre ordenada.
     * @param answers respuesta al código.
     */
    public void setRespuestas(Respuesta answers) {
        respuestas.respuesta = new ArrayList<>(answers.respuesta);
        Collections.sort(respuestas.respuesta, Collections.reverseOrder());
    }

}

