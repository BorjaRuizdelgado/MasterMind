package Domain;

import java.util.*;

/**
 *
 * @author borja | ISA | Omar
 */

/**
 * Implementa la IA de Mastermind.
 */
public class Cerebro {

    private List<Codigo> combinacionesTotales;
    private List<Codigo> solucionesPotenciales;
    private int numeroColores;
    private int numeroColumnas;

    /**
     * Creadora.
     * @param colores numero de colores que tiene el juego
     * @param columnas numero de columnas que tiene el tablero
     */
    public Cerebro(int colores, int columnas){
        numeroColores = colores;
        numeroColumnas = columnas;

        solucionesPotenciales = new ArrayList<>();
        generaPotenciales();
        combinacionesTotales = new ArrayList<>(solucionesPotenciales);
    }

    /**
     * Genera el primer intento.
     */
    public Codigo getIntentoInicial(){
        Codigo intentoActual = new Codigo(numeroColumnas);
        for (int i = 0; i < numeroColumnas; i++) {
            if(i < numeroColumnas / 2) intentoActual.codigo.add(1);
            else intentoActual.codigo.add(2);
        }
        borraIntento(intentoActual);
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
     * Recursivamente genera todos los potenciales necesarios.
     * @param posicion posición para
     * @param actual codigo a duplicar
     */
    private void generadorRecursivo(int posicion, Codigo actual){
        if(posicion == numeroColumnas){
            solucionesPotenciales.add(actual);
            return;
        }
        for(int i = 1; i <= numeroColores; i++){
            actual.codigo.set(posicion, i);
            Codigo copia = new Codigo(actual.size);

                copia.codigo = new ArrayList<>(actual.codigo);

            generadorRecursivo(posicion + 1, copia);
        }
    }

    /**
     * Hace la criba de los potenciales descartados dada una fila
     * @param ultimoIntento fila que contiene el codigo de colores
     *                      del ultimo intento y la respuesta
     */
    private void actualizaPotenciales(Fila ultimoIntento) {
        Respuesta ultimaRespuesta = ultimoIntento.getRespuestas();
        for (int i = 0; i < solucionesPotenciales.size(); i++) {
            Respuesta respuestaPosible = ultimoIntento.getColores().getRespuesta(solucionesPotenciales.get(i));
            if(!respuestaPosible.equals(ultimaRespuesta)) solucionesPotenciales.remove(i);
        }
    }

    /**
     * Genera el Codigo del siguiente intento
     * @param ultimoIntento El último intento que se ha hecho en el tablero
     * @return El siguiente intento en forma de Codigo
     */
    public Codigo getSiguienteIntento(Fila ultimoIntento) {
        actualizaPotenciales(ultimoIntento);

        List<Codigo> posiblesCandidatos = minmax();
        Codigo candidato = seleccionaCandidato(posiblesCandidatos);
        borraIntento(candidato);
        return candidato;
    }

    /**
     * @return
     */
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

        Iterator<Codigo> iterator = puntuacion.keySet().iterator();
        while (iterator.hasNext()) {
            Codigo key = iterator.next();
            int aux = puntuacion.get(key);
            if (aux == min) posiblesCandidatos.add(key);
        }
        return posiblesCandidatos;
    }

    /**
     * @param candidatos
     * @return
     */
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

    /**
     * @param contadorPuntuaciones
     * @return
     */
    private int getMaximaPuntuacion(Map<String, Integer> contadorPuntuaciones){
        int max = 0;

        Iterator<String> iterator = contadorPuntuaciones.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            int aux = contadorPuntuaciones.get(key);
            if (aux > max) max = aux;
        }

        return max;
    }

    /**
     * @param puntuacion
     * @return
     */
    private int getMinimaPuntuacion(Map<Codigo, Integer> puntuacion){
        int min = Integer.MAX_VALUE;

        Iterator<Codigo> iterator = puntuacion.keySet().iterator();
        while (iterator.hasNext()) {
            Codigo key = iterator.next();
            int aux = puntuacion.get(key);
            if (aux < min) min = aux;
        }
        return min;
    }

    /**
     * Imprime por pantalla los potenciales actuales que tiene el objeto.
     */
    private void imprimePotenciales(){
        for (Codigo candidata : solucionesPotenciales) {
            System.out.println(candidata.codigo);
        }
    }

    /**
     * Borra un intento de las combinaciones totales y potenciales.
     * @param intento el intento que se quiere borrar.
     */
    private void borraIntento(Codigo intento){
        combinacionesTotales.remove(intento);
        solucionesPotenciales.remove(intento);
    }
}
