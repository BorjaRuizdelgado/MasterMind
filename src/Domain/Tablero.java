package Domain;

import Domain.Excepciones.ExcepcionRespuestaIncorrecta;

import java.io.Serializable;
import java.util.*;

/**
 * Clase Tablero.
 * Contiene una lista de Filas y un código secreto.
 * @author Borja
 */
public class Tablero implements Serializable {

    /*
    VACIO = 0;
    ROJO = 1;
    VERDE = 2;
    AZUL = 3;
    AMARILLO = 4;
    NARANJA = 5;
    MORADO = 6;
    BLANCO = 7;
    NEGRO = 8;
    */

    private final int numeroColumnas;
    private final int numeroFilas = 12;
    private int numeroFilaActual = 0;

    private List<Fila> tablero = new ArrayList<>(numeroFilas);
    private Codigo codigoSecreto;

    /**
     * Creadora Tablero.
     * Inicia el tablero lleno de filas del tamaño numeroColumnas.
     * @param columnas numero de columnas del tablero
     */
    public Tablero(int columnas){
        numeroColumnas = columnas;
        codigoSecreto = new Codigo(numeroColumnas);
        iniciaTablero();
    }


    /* CONSULTORAS */

    /**
     * Devuelve el numero de fila actual.
     * @return Numero de fila actual.
     */
    public int getNumeroFilaActual() {
        return numeroFilaActual;
    }

    /**
     * Devuelve el código secreto del tablero.
     * @return codigo secreto.
     */
    public Codigo getCodigoSecreto(){
        return codigoSecreto;
    }

    /**
     * Devuelve el código intento de la fila actual.
     * @return Actual código del tablero.
     */
    public Codigo getActualColores(){
        return tablero.get(numeroFilaActual).getColores();
    }

    /**
     * Devuelve la fila anterior.
     * Si estamos en la primera fila, se devuelve esta.
     * @return Fila anterior.
     */
    public Fila getUltimoIntento(){
        if(numeroFilaActual - 1 == -1) return tablero.get(numeroFilaActual);
        return tablero.get(numeroFilaActual - 1);
    }


    /* MODIFICADORAS */

    /**
     * Inicia el tablero con filas de un tamaño concreto.
     */
    private void iniciaTablero(){
        for(int i = 0; i < numeroFilas; ++i)
            tablero.add(new Fila(numeroColumnas));
    }

    /**
     * Se pasa a la siguiente fila
     */
    private void incrementaFilaActual(){
        numeroFilaActual++;
    }

    /**
     * Coloca el código secreto
     * @param codigoSecreto codigo que se asigna al tablero como codigo secreto.
     */
    public void setCodigoSecreto(Codigo codigoSecreto){
        this.codigoSecreto.codigo = new ArrayList<>(codigoSecreto.codigo);
    }

    /**
     * Añade el código a la fila actual
     * @param guess se establece como el intnto de adivinar el jugador.
     */
    public void setUltimoColores(Codigo guess){
        tablero.get(numeroFilaActual).setColores(guess);
    }

    /**
     * Añade la respuesta a la fila actual y incrementa la fila.
     * @param answer respuesta del código
     * @throws ExcepcionRespuestaIncorrecta si la respuesta es no se corresponde al código
     */
    public void setUltimoRespuestas(Respuesta answer) throws ExcepcionRespuestaIncorrecta {
        if (answer.equals(codigoSecreto.getRespuesta(getActualColores())))
            tablero.get(numeroFilaActual).setRespuestas(answer);
        else throw new ExcepcionRespuestaIncorrecta("** ERROR **: Respuesta incorrecta. Comprueba tu respuesta.");
        incrementaFilaActual();
    }

    /**
     * Genera la respuesta según el último código y la añade a la fila actual del tablero.
     */
    public void generaRespuesta() {
        try {
            setUltimoRespuestas(getActualColores().getRespuesta(codigoSecreto));
        } catch (Exception e) {
            //Esta función siempre añade correctamente la respuesta
        }
    }

    public List<List<List<Integer>>> getTablero(){
        List<List<List<Integer>>> tableroAPasar = new ArrayList<>();

        for(int i = 0; i < numeroFilas; ++i){
            List<List<Integer>> fila = new ArrayList<>();
            fila.add(tablero.get(i).getColores().codigo);
            fila.add(tablero.get(i).getRespuestas().respuesta);
            tableroAPasar.add(i,fila);
        }
        return tableroAPasar;
    }
}

