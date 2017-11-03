package Domain;

/**
 *
 * @author borja
 */

import java.util.ArrayList;
import java.util.List;

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


    private List<Fila> tablero = new ArrayList<Fila>(numeroFilas);
    private Codigo codigoSecreto;

    /**
     * Se crea un tablero nuevo con columnas numero de columnas.
     * @param columnas
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

            this.codigoSecreto.codigo = new ArrayList<Integer>(codigoSecreto.codigo);
    }

    /**
     * @return La fila que utilizan los jugadores en ese momento.
     */
    public Fila getUltimoIntento(){
        return tablero.get(numeroFilaActual);
    }

    /**
     * @param guess se establece como el intnto de adivinar el jugador.
     */
    public void setUltimoColores(Codigo guess){
        tablero.get(numeroFilaActual).setColores(guess);
    }

    /**
     * @param answer se establece como la correccion que ha hecho el jugador.
     */
    public void setUltimoRespuestas(Respuesta answer){
        tablero.get(numeroFilaActual).setRespuestas(answer);
    }

    /**
     * Se pasa a la siguiente fila para jugar.
     */
    public void incrementaFilaActual(){
        numeroFilaActual++;
    }

    /**
     * @return El El rablero sobre el que se juega.
     */
    public List<Fila> getTablero(){
        return tablero;
    }

    /**
     * Devuelve el numero de fila actual
     * @return
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
        setUltimoRespuestas(getUltimoColores().getRespuesta(codigoSecreto));
    }




}

