package Domain;

import Util.Comparator;
import java.util.*;

/**
 * Clase Cerebro.
 * Inteligencia que implementa el algoritmo 'Five Guess'
 * @author borja and ISA and Omar
 */
public class Cerebro implements Inteligencia{

    private List<Codigo> combinacionesTotales;
    private List<Codigo> solucionesPotenciales;
    private int numeroColores;
    private int numeroColumnas;


    /**
     * Creadora Cerebro.
     * Inicia las listas de soluciones potenciales y de combinaciones totales.
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


    /* CONSULTORAS */

    /**
     * Genera el codigo del siguiente intento
     * @param ultimoIntento El último intento que se ha hecho en el tablero
     * @return Codigo para el siguiente intento
     */
    public Codigo getSiguienteIntento(Fila ultimoIntento) {
        actualizaPotenciales(ultimoIntento);

        List<Codigo> posiblesCandidatos = minmax();
        Codigo candidato = seleccionaCandidato(posiblesCandidatos);
        borraIntento(candidato);
        return candidato;
    }

    /**
     * Función auxiliar del minmax para conseguir la máxima puntuación.
     * @param contadorPuntuaciones map de contador de puntuaciones
     * @return máximo de contador de puntuaciones.
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
     * Función auxiliar del minmax para conseguir la mínima puntuación.
     * @param puntuacion map de puntuaciones
     * @return puntuacion mínima
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
     * Selecciona el mejor codigo candidato de la lista.
     * @param candidatos lista de posibles codigos candidatos
     * @return mejor codigo para el siguiente intento
     */
    private Codigo seleccionaCandidato(List<Codigo> candidatos){
        Collections.sort(candidatos, new Comparator());
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
     * Devuelve la lista de códigos candidatos para ser el siguiente intento
     * @return lista de códigos candidatos.
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
     * Descarta de la lista de potenciales los que no corresponden.
     * @param ultimoIntento fila que contiene el codigo de colores
     *                      del ultimo intento y la respuesta
     */
    private void actualizaPotenciales(Fila ultimoIntento) {
        List<Codigo> codigosParaBorrar = new ArrayList<>();
        Respuesta ultimaRespuesta = ultimoIntento.getRespuestas();
        for (int i = 0; i < solucionesPotenciales.size(); i++) {
            Respuesta respuestaPosible = solucionesPotenciales.get(i).getRespuesta(ultimoIntento.getColores());
            if(!respuestaPosible.equals(ultimaRespuesta))
                codigosParaBorrar.add(solucionesPotenciales.get(i));
        }

        for (int i = 0; i < codigosParaBorrar.size(); i++)
            solucionesPotenciales.remove(codigosParaBorrar.get(i));

    }

    /**
     * Devuelve el primer intento.
     * Y lo borra de las listas.
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


    /* MODIFICADORAS */

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
     * Genera recursivamente todos los potenciales necesarios.
     * @param posicion posición a añadir
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
     * Borra de ambas listas el código
     * @param intento codigo a eliminar.
     */
    private void borraIntento(Codigo intento){
        for (int i = 0; i < solucionesPotenciales.size(); i++) {
            if(solucionesPotenciales.get(i).codigo.equals(intento.codigo)) solucionesPotenciales.remove(i);
        }
        for (int i = 0; i < combinacionesTotales.size(); i++) {
            if(combinacionesTotales.get(i).codigo.equals(intento.codigo)) combinacionesTotales.remove(i);
        }
    }
}
