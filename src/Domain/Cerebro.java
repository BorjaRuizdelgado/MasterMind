package Domain;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author borja | ISA | Omar
 */
public class Cerebro {


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
        solucionesPotenciales = new ArrayList<Codigo>();
        generaIntentoInicial();
        generaPotenciales();

        combinacionesTotales = new ArrayList<>(solucionesPotenciales);
    }

    /**
     * Genera el primer intento
     */
    private Codigo generaIntentoInicial(){
        Codigo intentoActual = new Codigo(numeroColumnas);
        for (int i = 0; i < numeroColumnas; i++) {
            if(i < numeroColumnas / 2) intentoActual.codigo.add(1);
            else intentoActual.codigo.add(2);
        }
        return intentoActual;
    }

    /**
     * Genera la combinacion de posibles potenciales.
     */
    private void generaPotenciales(){
        Codigo actual = new Codigo(numeroColumnas);
        for(int i = 0; i < numeroColumnas; i++){
            actual.codigo.add(0);
        }
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

                copia.codigo = new ArrayList<Integer>(actual.codigo);

            generadorRecursivo(posicion + 1, copia);
        }
    }

    /**
     * Imprime por pantalla los potenciales actuales que tiene el objeto
     */
    private void imprimePotenciales(){
        for(int i = 0; i < solucionesPotenciales.size(); i++){
            System.out.println(solucionesPotenciales.get(i).codigo);
        }
    }

    /**
     * Hace la criba de los potenciales descartados dada una fila
     * @param ultimoIntento
     */
    private void actualizaPotenciales(Fila ultimoIntento) {
        Respuesta ultimaRespuesta = ultimoIntento.getRespuestas();
        for (int i = 0; i < solucionesPotenciales.size(); i++) {
            Respuesta respuestaPosible = ultimoIntento.getColores().getRespuesta(solucionesPotenciales.get(i));
            if(!respuestaPosible.equals(ultimaRespuesta)) solucionesPotenciales.remove(i);
        }
    }


    public Codigo getSiguienteIntento(Fila ultimoIntento) {
        actualizaPotenciales(ultimoIntento);

        List<Codigo> posiblesCandidatos = minmax();
        return seleccionaCandidato(posiblesCandidatos);
    }

    private List<Codigo> minmax(){
        int max, min;
        Map<String, Integer> contadorPuntuaciones = new HashMap<>();
        Map<Codigo, Integer> puntuacion = new HashMap<>();
        List<Codigo> posiblesCandidatos = new ArrayList<>();

        for (int i = 0; i < combinacionesTotales.size(); i++) {
            for (int j = 0; j < solucionesPotenciales.size(); j++) {
                Respuesta respuesta = combinacionesTotales.get(i).getRespuesta(solucionesPotenciales.get(j));
                String result = respuesta.toString();
                if(contadorPuntuaciones.containsKey(result)){
                    contadorPuntuaciones.put(result, contadorPuntuaciones.get(result) + 1);
                }
                else{
                    contadorPuntuaciones.put(result, 1);
                }
            }
            max = getMaximaPuntuacion(contadorPuntuaciones);
            puntuacion.put(combinacionesTotales.get(i), max);
            contadorPuntuaciones.clear();
        }
        min = getMinimaPuntuacion(puntuacion);
        for (int i = 0; i < puntuacion.size(); i++) {
            if(puntuacion.get(i) == min){
                posiblesCandidatos.add((Codigo) puntuacion.keySet().toArray()[i]);
            }
        }
        return posiblesCandidatos;
    }

    private Codigo seleccionaCandidato(List<Codigo> candidatos){
        int idCandidato = -1;

        for (int i = 0; i < candidatos.size(); i++) {
            if(solucionesPotenciales.contains(candidatos.get(i))){
                idCandidato = i;
                break;
            }
        }
        
        if(idCandidato == -1){
            for (int i = 0; i < candidatos.size(); i++) {
                if(combinacionesTotales.contains(candidatos.get(i))){
                    idCandidato = i;
                    break;
                }
            }
        }

        return candidatos.get(idCandidato);
    }

    private int getMaximaPuntuacion(Map<String, Integer> contadorPuntuaciones){
        int max = 0;
        for (int i = 0; i < contadorPuntuaciones.size(); i++) {
            int aux = contadorPuntuaciones.get(i);
            if (aux > max) max = aux;
        }
        return max;
    }

    private int getMinimaPuntuacion(Map<Codigo, Integer> puntuacion){
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < puntuacion.size(); i++) {
            int aux = puntuacion.get(i);
            if(aux < min) min = aux;
        }
        return min;
    }
}
