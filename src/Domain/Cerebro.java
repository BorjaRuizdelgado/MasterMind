package Domain;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author borja | ISA | Omar
 */
public class Cerebro {

    private Codigo intentoActual;
    private List<Codigo> combinacionesTotales;
    private List<Codigo> solucionesPotenciales;
    private int numeroColores;
    private int numeroColumnas;
    private boolean firstGuess = true;



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
        imprimePotenciales();
    }

    /**
     * Genera el primer intento
     */
    private void generaIntentoInicial(){
        for (int i = 0; i < numeroColores; i++) {
            if(i < numeroColores / 2) intentoActual.codigo.add(1);
            else intentoActual.codigo.add(2);
        }

    }

    /**
     * Genera la combinacion de posibles potenciales.
     */
    private void generaPotenciales(){
        Codigo actual = new Codigo(numeroColumnas);
        generadorRecursivo(0, actual);
    }

    /**
     * Recursivamente genera todos los potenciales necesarios
     * @param posicion
     * @param actual
     */
    private void generadorRecursivo(int posicion, Codigo actual){
        if(posicion == numeroColumnas){
            solucionesPotenciales.add(actual);
            return;
        }
        for(int i = 1; i <= numeroColores; i++){
            actual.codigo.set(posicion, i);
            Codigo copia = new Codigo(actual.size);
            try {
                copia = (Codigo) actual.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
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

    private void actualizaPotenciales(Fila ultimoIntento) {
        Respuesta ultimaRespuesta = ultimoIntento.getRespuestas();
        for (int i = 0; i < solucionesPotenciales.size(); i++) {
            Respuesta respuestaPosible = ultimoIntento.getColores().getRespuesta(solucionesPotenciales.get(i));
            if(!respuestaPosible.equals(ultimaRespuesta)) solucionesPotenciales.remove(i);
        }
    }
    
    
/*
    public Codigo getSiguienteIntento(Fila ultimoIntento) {
        actualizaPotenciales(ultimoIntento);
        //find Next ()
    }
*/
}
