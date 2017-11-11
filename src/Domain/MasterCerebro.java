package Domain;

import Util.Console;
import com.sun.org.apache.regexp.internal.RE;
import jdk.internal.util.xml.impl.Pair;

import java.lang.reflect.Array;
import java.util.*;

import static java.lang.Math.abs;

/**
 * Clase MasterCerebro
 * Implementa la IA de Mastermind con el algoritmo genético.
 * @author Omar
 */
public class MasterCerebro implements Inteligencia {

    private static int MAXGEN = 100; //El máximo de generaciones que permitimos
    private static int MAXSIZE = 60; //El tamaño máximo de las poblaciones
    private static double PROBABILIDAD_CRUCE = 0.5;
    private static double PROBABILIDAD_MUTACION = 0.3;
    private static double PROBABILIDAD_PERMUTACION = 0.3;

    private int numeroColores;
    private int numeroColumnas;
    private Random random;
    private List<Fila> intentos;

    private class ResultPair {
        public int white;
        public int black;

        public ResultPair(int white, int black) {
            this.white = white;
            this.black = black;
        }
    }

    public MasterCerebro(int colores, int columnas) {
        numeroColores = colores;
        numeroColumnas = columnas;
        random = new Random(System.currentTimeMillis());
    }

    /**
     * Genera el primer intento.
     */
    public Codigo getIntentoInicial() {
        Codigo intentoActual = new Codigo(numeroColumnas);
        for (int i = 0; i < numeroColumnas; i++) {
            if (i < numeroColumnas / 2) intentoActual.codigo.add(1);
            else intentoActual.codigo.add(2);
        }
        return intentoActual;
    }

    /**
     * Genera el Codigo del siguiente intento
     *
     * @param ultimoIntento El último intento que se ha hecho en el tablero
     * @return El siguiente intento en forma de Codigo
     */
    public Codigo getSiguienteIntento(Fila ultimoIntento) {
        List<Codigo> posiblesCandidatos = evolution();
        Codigo candidato = seleccionaCandidato(posiblesCandidatos);
        return candidato;
    }

    private Codigo seleccionaCandidato(List<Codigo> candidatos) {
        return new Codigo(4);
    }

    private List<Codigo> evolution() {
        List<Codigo> poblacion = generarPoblacion();

        List<Codigo> candidatos = new ArrayList<>(); // Candidatos óptimos que devolveremos
        int generationCount = 0; // Contador para saber la generación en la que estamos

        while (candidatos.size() <= MAXSIZE && generationCount <= MAXGEN) {
            List<Codigo> hijos = new ArrayList<>();

            for (int i = 0; i < poblacion.size(); i++) {
                // Mutaciones
                Codigo hijo = cruce(poblacion.get(i), poblacion.get(i + 1));

                if (random.nextDouble() <= PROBABILIDAD_MUTACION)
                    hijo = mutacion(hijo);

                if (random.nextDouble() <= PROBABILIDAD_PERMUTACION)
                    hijo = permutacion(hijo);

                hijos.add(hijo);
            }

            Map<Integer, Codigo> puntuaciones = calculaPuntuaciones(hijos);
            ordenaPuntuaciones(puntuaciones);
        }

        return new ArrayList<>();
    }

    private List<Codigo> generarPoblacion() {
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

    private Map<Integer, Codigo> calculaPuntuaciones(List<Codigo> hijos) {
        Map<Integer, Codigo> Result = new HashMap<>();
        for (int i = 0; i < hijos.size(); i++) {
            int score = calculateFitness(hijos.get(i));
            Result.put(score, hijos.get(i));
        }
        return new HashMap<>();
    }

    private void ordenaPuntuaciones(Map<Integer, Codigo> puntuaciones) {
        
    }

    public int calculateFitness(Codigo codigo) {
        List<ResultPair> differences = new ArrayList<>();
        for (int i = 0; i < intentos.size(); i++) {
            ResultPair intentoResultado = toResultPair(intentos.get(i).getRespuestas().toString());
            Codigo intentoCodigo = intentos.get(i).getColores();

            ResultPair resultadoCodigo = toResultPair(intentoCodigo.getRespuesta(codigo).toString());

            int differenceWhite = abs(resultadoCodigo.white - intentoResultado.white);
            int differenceBlack = abs(resultadoCodigo.black - intentoResultado.black);
            differences.add(new ResultPair(differenceWhite, differenceBlack));
        }

        int totalWhite = 0;
        int totalBlack = 0;
        for (int i = 0; i < differences.size(); i++) {
            totalWhite += differences.get(i).white;
            totalBlack += differences.get(i).black;
        }

        return totalBlack + totalWhite + 2*numeroColumnas*(intentos.size() - 1);
    }

    private Codigo cruce(Codigo codigoA, Codigo codigoB) {
        // De momento probaremos con un solo corte
        Codigo Return = new Codigo(numeroColumnas);

        int i = random.nextInt(numeroColumnas - 1);
        List<Integer> aPart1 = new ArrayList<>(codigoA.codigo.subList(0, i));
        List<Integer> aPart2 = new ArrayList<>(codigoA.codigo.subList(i + 1, numeroColumnas - 1));
        List<Integer> bPart1 = new ArrayList<>(codigoB.codigo.subList(0, i));
        List<Integer> bPart2 = new ArrayList<>(codigoB.codigo.subList(i + 1, numeroColumnas - 1));

        if (random.nextDouble() <= PROBABILIDAD_CRUCE) {
            Return.codigo.addAll(aPart1);
            Return.codigo.addAll(bPart2);
        } else {
            Return.codigo.addAll(bPart1);
            Return.codigo.addAll(aPart2);
        }

        return Return;
    }

    private Codigo mutacion(Codigo codigo) {
        Codigo Return = new Codigo(numeroColumnas);
        Return.codigo = new ArrayList<>(codigo.codigo);
        Return.codigo.set(random.nextInt(numeroColumnas - 1), 1 + random.nextInt(numeroColores - 1));
        return Return;
    }

    private Codigo permutacion(Codigo codigo) {
        Codigo Return = new Codigo(numeroColumnas);
        Return.codigo = new ArrayList<>(codigo.codigo);

        int positionA = 0;
        int positionB = 0;
        do {
            positionA = random.nextInt(numeroColumnas);
            positionB = random.nextInt(numeroColumnas);
        }
        while (positionA == positionB);
        int aux = codigo.codigo.get(positionA);
        Return.codigo.set(positionA, codigo.codigo.get(positionB));
        Return.codigo.set(positionB, aux);
        return Return;
    }

    private Codigo inversion(Codigo codigo) {
        return new Codigo(numeroColumnas);
    }

    private ResultPair toResultPair(String result) {
        int white = 0, black = 0;
        char aux[] = result.toCharArray();
        for (int i = 0; i < Array.getLength(aux); i++) {
            if (aux[i] == 'W') white++;
            else if (aux[i] == 'B') black++;
        }
        return new ResultPair(white, black);
    }
}