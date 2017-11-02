package Domain;

import java.util.List;
import Domain.Fila;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author borja | ISA | Omar
 */
public class Cerebro {

    private List<Integer> intentoActual;
    private List<List<Integer>> combinacionesTotales;
    private List<List<Integer>> solucionesPotenciales = new ArrayList<List<Integer>>();
    private int numeroColores;
    private int numeroColumnas;
    private boolean firstGuess = true;


    private void generaIntentoInicial(){
        for (int i = 0; i < numeroColores; i++) {
            if(i < numeroColores / 2) intentoActual.add(1);
            else intentoActual.add(2);
        }
    }
    /**
     * Creadora
     * @param colores numero de colores que tiene el juego
     * @param columnas numero de columnas que tiene el tablero
     */
    public Cerebro(int colores, int columnas){
        numeroColores = colores;
        numeroColumnas = columnas;
        generaPotenciales();
        // Hacemos una copia de lo que hemos generado
        combinacionesTotales = new ArrayList<>(solucionesPotenciales);
        generaIntentoInicial();
    }

    /**
     * Genera la combinacion de posibles potenciales.
     */
    private void generaPotenciales(){
        List<Integer> actual = new ArrayList<>(numeroColumnas);

        generadorRecursivo(0, actual);
    }

    /**
     * Recursivamente genera todos los potenciales necesarios.
     *
     * @param posicion
     * @param actual
     */
    private void generadorRecursivo(int posicion, List<Integer> actual){
        if(posicion == numeroColumnas){
            solucionesPotenciales.add(actual);
            return;
        }
        for(int i = 1; i <= numeroColores; i++){
            actual.set(posicion, i);
            List<Integer> copia = new ArrayList<>(actual);
            generadorRecursivo(posicion + 1, copia);
        }
    }

    /**
     * Imprime por pantalla los potenciales actuales que tiene el objeto
     */
    private void imprimePotenciales(){
        for(int i = 0; i < solucionesPotenciales.size(); i++){
            System.out.println(solucionesPotenciales.get(i));
        }
    }


    // HACE FALTA EDITARLA
    private List<Integer> getRespuesta(List<Integer> intento, List<Integer> codigo) {
        return intento;
    }

    private boolean sonIguales(List<Integer> array1, List<Integer> array2) {
        /*Arrays.sort(array1);
        Arrays.sort(array2);
        return array1.equals(array2);
        */
        return false;
    }


    private void actualizaPotenciales(Fila ultimoIntento) {
        /*List<Integer> ultimaRespuesta = ultimoIntento.getRespuestas();
        for (int i = 0; i < solucionesPotenciales.size(); i++) {
            List<Integer> respuestaPosible = getRespuesta(ultimoIntento.getColores(),solucionesPotenciales.get(i));
            if(!sonIguales(respuestaPosible, ultimaRespuesta)) solucionesPotenciales.remove(i);
        }
        */
    }
    
    
    /*
    public List<Integer> getSiguienteIntento(Fila ultimoIntento) {
        actualizaPotenciales(ultimoIntento);
        //findNext();
    }
*/

}
