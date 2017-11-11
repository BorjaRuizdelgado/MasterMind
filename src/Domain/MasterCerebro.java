package Domain;

import Util.Console;

import java.util.*;

/**
 * Clase MasterCerebro
 * Implementa la IA de Mastermind con el algoritmo genético.
 * @author Omar
 */
public class MasterCerebro implements Inteligencia {

    private static int MAXGEN = 100; //El máximo de generaciones que permitimos
    private static int MAXSIZE = 60; //El tamaño máximo de las poblaciones
    private int numeroColores;
    private int numeroColumnas;
    private Random random;
    private List<Codigo> intentos;

    public MasterCerebro(int colores, int columnas){
        numeroColores = colores;
        numeroColumnas = columnas;
        random = new Random(System.currentTimeMillis());
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
        return intentoActual;
    }

    /**
     * Genera el Codigo del siguiente intento
     * @param ultimoIntento El último intento que se ha hecho en el tablero
     * @return El siguiente intento en forma de Codigo
     */
    public Codigo getSiguienteIntento(Fila ultimoIntento) {
        List<Codigo> posiblesCandidatos = evolution();
        Codigo candidato = seleccionaCandidato(posiblesCandidatos);
        return candidato;
    }

    private Codigo seleccionaCandidato(List<Codigo> candidatos){
        return new Codigo(4);
    }

    private List<Codigo> evolution(){
        List<Codigo> poblacion = generarPoblacion();

        List<Codigo> candidatos = new ArrayList<>(); // Candidatos óptimos que devolveremos
        int generationCount = 0; // Contador para saber la generación en la que estamos

        while (candidatos.size() <= MAXSIZE && generationCount <= MAXGEN){
            List<Codigo> hijos = new ArrayList<>();

            for (int i = 0; i < poblacion.size(); i++) {
                // Mutaciones
            }

            Map<Integer, Codigo> puntuaciones = calculaPuntuaciones(hijos);

        }

        return new ArrayList<>();
    }

    private List<Codigo> generarPoblacion(){
        List<Codigo> poblacion = new ArrayList<>();
        for (int i = 0; i < MAXSIZE; i++) {
            Codigo codigo = new Codigo(numeroColumnas);
            for (int j = 0; j < numeroColumnas; j++) {
                codigo.codigo.add(1 + random.nextInt(numeroColores));
            }
            poblacion.add(codigo);
        }
        return poblacion;
    }

    private HashMap<Integer, Codigo> calculaPuntuaciones(List<Codigo> hijos){
        Map<Integer, Codigo> Result = new HashMap<>();
        for (int i = 0; i < hijos.size(); i++) {
            int score = calculateFitness(hijos.get(i));
            Result.put(score, hijos.get(i));
        }
        return new HashMap<>();
    }

    private void ordenaPuntuaciones(HashMap<Integer, Codigo> puntuaciones){

    }

    public int calculateFitness(Codigo codigo){

        for (int i = 0; i < intentos.size(); i++) {
            
        }
        return 0;
    }

    private Codigo cruce(Codigo codigo){
        return new Codigo(5);
    }

    private Codigo mutacion(Codigo codigo){
        return new Codigo(4);
    }

    private Codigo permutacion(Codigo codigo){
        return new Codigo(4);
    }
}
