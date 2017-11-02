package Domain;

import java.util.ArrayList;

public class Fila {

    private Codigo colores;
    private Respuesta respuestas;


    public Fila(int sizeColumnas){
        colores = new Codigo(sizeColumnas);
        respuestas = new Respuesta(sizeColumnas);
    }

    /**
     *
     * @return retorna la variable colores.
     */
    public Codigo getColores(){
        return colores;
    }

    /**
     *
     * @param colours es el array que se copiara en la variable colores.
     */
    public void setColores(Codigo colours){
        colores.codigo = new ArrayList<Integer>(colours.codigo);

    }

    /**
     *
     * @return retorna la variable respuestas.
     */
    public Respuesta getRespuestas(){
        return respuestas;
    }

    /**
     *
     * @param answers es el array que se copiara en la variable respuestas.
     */
    public void setRespuestas(Respuesta answers){
        respuestas.respuesta = new ArrayList<Integer>(answers.respuesta);
    }

}

