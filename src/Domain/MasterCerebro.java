package Domain;

/**
 * @author Omar
 */

/**
 * Implementa la IA de Mastermind con el algoritmo genético.
 */
public class MasterCerebro implements Inteligencia {

    private int numeroColores;
    private int numeroColumnas;

    public MasterCerebro(int colores, int columnas){
        numeroColores = colores;
        numeroColumnas = columnas;
    }

    /**
     * Genera el primer intento.
     */
    public Codigo getIntentoInicial(){
        return new Codigo(4);
    }

    /**
     * Genera el Codigo del siguiente intento
     * @param ultimoIntento El último intento que se ha hecho en el tablero
     * @return El siguiente intento en forma de Codigo
     */
    public Codigo getSiguienteIntento(Fila ultimoIntento) {
        return new Codigo(4);
    }
}
