package Domain;

/**
 *
 * @author borja
 */

import java.util.ArrayList;
import java.util.List;

public class Tablero {



    private static final int VACIO = 0;
    private static final int ROJO = 1;
    private static final int VERDE = 2;
    private static final int AZUL = 3;
    private static final int AMARILLO = 4;
    private static final int NARANJA = 5;
    private static final int MORADO = 6;
    private static final int BLANCO = 7;
    private static final int NEGRO = 8;


    private final int numeroColumnas;
    private final int numeroFilas = 15;
    private int numeroFilaActual = 0;


    private Fila[] tablero = new Fila[numeroFilas];
    private Codigo codigoSecreto;

    /**
     * Se crea un tablero nuevo con columnas numero de columnas.
     * @param columnas
     */
    public Tablero(int columnas){
        //el tablero deberia ir a la BD y coger la clave de la ultima partida
        //que se guradó para asi poder sumarle uno y saber el identificador del tablero.
        numeroColumnas = columnas;
        codigoSecreto = new Codigo(numeroColumnas);
        iniciaTablero();
    }

    /**
     * Inicia el tablero con filas de un tamaño concreto.
     */
    private void iniciaTablero(){
        for(int i = 0; i < numeroFilas; ++i)
            tablero[i] = new Fila(numeroColumnas);
    }

    /**
     *
     * @return Array de codigo secreto.
     */
    public Codigo getCodigoSecreto(){
        return codigoSecreto;
    }

    /**
     *
     * @param codigoSecreto copiado en la variable codigoSecreto del tablero.
     */
    public void setCodigoSecreto(CodigocodigoSecreto){
        System.arraycopy(codigoSecreto, 0, this.codigoSecreto, 0, codigoSecreto.length);
    }

    /**
     *
     * @return La fila que utilizan los jugadores en ese momento.
     */
    public Fila getUltimoIntento(){
        return tablero[numeroFilaActual];
    }

    /**
     *
     * @param guess se establece como el intnto de adivinar el jugador.
     */
    public void setUltimoColores(Codigo guess){
        tablero[numeroFilaActual].setColores(guess);
    }

    /**
     *
     * @param answer se establece como la correccion que ha hecho el jugador.
     */
    public void setUltimoRespuestas(Respuesta answer){
        tablero[numeroFilaActual].setRespuestas(answer);
    }

    /**
     * Se pasa a la siguiente fila para jugar.
     */
    public void incrementaFilaActual(){
        numeroFilaActual++;
    }

    /**
     *
     * @return El El rablero sobre el que se juega.
     */
    public Fila[] getTablero(){
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
     *
     * @return La ultima solución del tablero.
     */
    public Codigo getUltimoColores(){
        return getUltimoIntento().getColores();
    }

    public void generaRespuesta(){
        setUltimoRespuestas(gimmeAnswer(getUltimoColores()));
    }

    public Respuesta gimmeAnswer(Codigo codigo){
        Codigo codigoAComprobar = new Codigo(numeroColumnas);
        System.arraycopy(codigo,0,codigoAComprobar,0,codigo.length);
        Respuesta answer = new Respuesta(numeroColumnas);
        int ultimaInsertada = 0;
        for(int i = 0; i < numeroColumnas;++i){
            if(codigoSecreto.codigo.get(i) == codigoAComprobar.codigo.get(i)){
                answer.respuesta.set(ultimaInsertada, 8);
                ultimaInsertada++;
                codigoAComprobar.codigo.set(i, -1);

            }
        }
        for(int i = 0; i < numeroColumnas; ++i){
            if(codigoAComprobar.codigo.get(i) > 0){

                answer.respuesta.set(ultimaInsertada, 7);
                ultimaInsertada++;
            }
        }

        return answer;
    }

    private boolean codigoSecretoContiene(int color){
        for(int i = 0; i < numeroColumnas; ++i){
            if(codigoSecreto.codigo.get(i) == color) return true;
        }
        return false;
    }
}

