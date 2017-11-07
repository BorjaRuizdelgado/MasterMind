package Domain;

/**
 *
 * @author borja
 */

import java.util.ArrayList;
import java.util.List;

/**
 * Implementa el tablero sobre el que Mastermind juega.
 */
public class Tablero {

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
    private final int numeroFilas = 15;
    private int numeroFilaActual = 0;


    private List<Fila> tablero = new ArrayList<>(numeroFilas);
    private Codigo codigoSecreto;

    /**
     * Creadora.
     * @param columnas numero de columnas del tablero
     */
    public Tablero(int columnas){
        numeroColumnas = columnas;
        codigoSecreto = new Codigo(numeroColumnas);
        iniciaTablero();
    }

    /**
     * Inicia el tablero con filas de un tamaño concreto.
     */
    private void iniciaTablero(){
        for(int i = 0; i < numeroFilas; ++i)
            tablero.add(new Fila(numeroColumnas));
    }

    /**
     * @return Array de codigo secreto.
     */
    public Codigo getCodigoSecreto(){
        return codigoSecreto;
    }

    /**
     * @param codigoSecreto copiado en la variable codigoSecreto del tablero.
     */
    public void setCodigoSecreto(Codigo codigoSecreto){
            this.codigoSecreto.codigo = new ArrayList<>(codigoSecreto.codigo);
    }

    /**
     * @return La fila que utilizan los jugadores en ese momento.
     */
    public Fila getUltimoIntento(){
        if(numeroFilaActual - 1 == -1) return tablero.get(numeroFilaActual);
        return tablero.get(numeroFilaActual - 1);
    }

    /**
     *
     * @return Retrona el intento actual.
     */
    public Codigo getIntentoActual(){
        return tablero.get(numeroFilaActual).getColores();
    }

    /**
     * @param guess se establece como el intnto de adivinar el jugador.
     */
    public void setUltimoColores(Codigo guess){
        tablero.get(numeroFilaActual).setColores(guess);
    }

    /**
     * Se añade la respuesta a la fila actual y se incrementa la fila.
     * @param answer se establece como la correccion que ha hecho el jugador.
     */
    public void setUltimoRespuestas(Respuesta answer){
        tablero.get(numeroFilaActual).setRespuestas(answer);
        incrementaFilaActual();
    }

    /**
     * Se pasa a la siguiente fila para jugar.
     */
    public void incrementaFilaActual(){
        numeroFilaActual++;
    }

    /**
     * @return El El tablero sobre el que se juega.
     */
    public List<Fila> getTablero(){
        return tablero;
    }

    /**
     * Devuelve el numero de fila actual
     * @return numero de fila actual
     */
    public int getNumeroFilaActual() {
        return numeroFilaActual;
    }

    /**
     * @return La ultima solución del tablero.
     */
    public Codigo getUltimoColores(){
        return getUltimoIntento().getColores();
    }

    /**
     * En el momento en el que se llama a esta función el sistema corrige el ultimo guess que se le ha hecho al tablero
     */
    public void generaRespuesta(){
        setUltimoRespuestas(getIntentoActual().getRespuesta(codigoSecreto));
    }




}

