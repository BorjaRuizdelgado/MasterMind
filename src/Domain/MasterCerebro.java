package Domain;

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

    /**
     * Clase auxiliar que sirve para tener una representación de la clase 'Respuesta' de manera simplista y así realizar
     * el algoritmo de manera más sencilla.
     */
    private class ResultPair {
        public int white;
        public int black;

        public ResultPair(int white, int black) {
            this.white = white;
            this.black = black;
        }
    }

    /**
     * Creadora de la clase donde se le indican los dos atributos necesarios para realizar sus métodos correctamente.
     * Se interpretará esta información para la creación de códigos dentro del algoritmo.
     * @param colores Número de colores.
     * @param columnas Número de columnas.
     */
    public MasterCerebro(int colores, int columnas) {
        numeroColores = colores;
        numeroColumnas = columnas;
        random = new Random(System.currentTimeMillis());
        intentos = new ArrayList<>();
    }

    /**
     * Genera el primer intento. La mitad de los colores són '1' y la otra mitad són números que comienzan por el '2'
     * de forma creciente.
     * Sea el número de columnas 6:
     * Devuelve: [1, 1, 1, 2, 3, 4]
     * Se sigue este patrón debido a que suele obtener mejores resultados.
     * @return El código generado.
     */
    public Codigo getIntentoInicial() {
        Codigo intentoActual = new Codigo(numeroColumnas);
        int aux = 2;
        for (int i = 0; i < numeroColumnas; i++) {
            if (i < numeroColumnas / 2) {
                intentoActual.codigo.add(1);
            }
            else {
                intentoActual.codigo.add(aux);
                aux++;
            }
        }
        return intentoActual;
    }

    /**
     * Genera el Codigo del siguiente intento.
     * @param ultimoIntento El último intento que se ha hecho en el tablero.
     * @return El siguiente intento en forma de Codigo.
     */
    public Codigo getSiguienteIntento(Fila ultimoIntento) {
        intentos.add(ultimoIntento);

        List<Codigo> posiblesCandidatos = evolucion();
        Codigo candidato = seleccionaCandidato(posiblesCandidatos);
        return candidato;
    }

    /**
     * Selecciona un candidato de entre los posibles pasado como parámetro. Si no hay posibles candidatos, hacemos
     * una llamada recursiva hasta obtener al menos uno.
     * @param candidatos Lista de código que contiene los posibles candidatos.
     * @return Devolvemos el primer candidato que se encuentra en la lista.
     */
    private Codigo seleccionaCandidato(List<Codigo> candidatos) {
        if (candidatos.size() == 0)
            return seleccionaCandidato(evolucion());
        else return candidatos.get(0);
    }

    /**
     * Genera una población (Códigos aleatorios), seguidamente se simula una evolución de ésta para obtener unos candi-
     * datos óptimos.
     * En la evolución, se generan hijos a partir de mutaciones. Estas mutaciones son: Cruces entre códigos, mutaciones
     * de algun color o intercambios de color.
     * Estos hijos que están en una generación en concreto, son filtrados, es decir, nos quedamos con los mejores y éstos
     * són añadidos a un grupo llamado 'candidatos'.
     * Una vez realizada esta generacion, la siguiente partirá de los mejores hijos de la generación anterior, y así
     * obtendremos unos candidatos eficaces a lo largo de distintas generaciones.
     * @return La lista de los mejores candidatos.
     */
    private List<Codigo> evolucion() {
        List<Codigo> poblacion = generarPoblacion();

        List<Codigo> candidatos = new ArrayList<>(); // Candidatos óptimos que devolveremos
        int generationCount = 0; // Contador para saber la generación en la que estamos

        while (candidatos.size() < MAXSIZE && generationCount < MAXGEN) {
            List<Codigo> hijos = new ArrayList<>();

            for (int i = 0; i < poblacion.size(); i++) {
                // Mutaciones
                Codigo hijo;
                if(i == poblacion.size() - 1) hijo = cruce(poblacion.get(i), poblacion.get(0));
                else hijo = cruce(poblacion.get(i), poblacion.get(i + 1));

                if (random.nextDouble() <= PROBABILIDAD_MUTACION)
                    hijo = mutacion(hijo);

                if (random.nextDouble() <= PROBABILIDAD_PERMUTACION)
                    hijo = permutacion(hijo);

                hijos.add(hijo);
            }

            Map<Codigo, Integer> puntuaciones = calculaPuntuaciones(hijos);
            puntuaciones = getPuntuacionesOrdenadas(puntuaciones);

            List<Codigo> elegibles = new ArrayList<>();
            Iterator<Codigo> iterator = puntuaciones.keySet().iterator();
            while (iterator.hasNext()) {
                Codigo key = iterator.next();
                if (puntuaciones.get(key) == 0)
                    elegibles.add(key);
            }

            if (elegibles.size() == 0) {
                generationCount++;
                continue;
            }

            for (int i = 0; i < elegibles.size(); i++) {
                if (!candidatos.contains(elegibles.get(i)) && candidatos.size() <= MAXSIZE)
                    candidatos.add(elegibles.get(i));
            }

            poblacion = new ArrayList<>(elegibles);
            while (poblacion.size() < MAXSIZE)
                poblacion.add(generaCodigoRandom());

            generationCount++;
        }

        return candidatos;
    }

    /**
     * Método que devuelve un código con información aleatoria.
     * @return Código con colores aleatorios.
     */
    private Codigo generaCodigoRandom(){
        Codigo codigo = new Codigo(numeroColumnas);
        for (int j = 0; j < numeroColumnas; j++) {
            codigo.codigo.add(1 + random.nextInt(numeroColores));
        }
        return codigo;
    }

    /**
     * Genera una lista de códigos con información aleatoria.
     * @return Una lista de códigos.
     */
    private List<Codigo> generarPoblacion() {
        List<Codigo> poblacion = new ArrayList<>();
        for (int i = 0; i < MAXSIZE; i++) {
            Codigo codigo = generaCodigoRandom();
            poblacion.add(codigo);
        }
        return poblacion;
    }

    /**
     * Crea y devuelve un map con las puntuaciones de la lista de códigos pasado como parámetro. Los códigos són la llave
     * y su puntuación el valor.
     * @param hijos Lista de códigos que estarán dentro del map.
     * @return Map generado a partir de la lista de códigos, cada uno con su puntuación adquirida.
     */
    private Map<Codigo, Integer> calculaPuntuaciones(List<Codigo> hijos) {
        Map<Codigo, Integer> Result = new HashMap<>();
        for (int i = 0; i < hijos.size(); i++) {
            int score = calculateFitness(hijos.get(i));
            Result.put(hijos.get(i), score);
        }
        return Result;
    }

    /**
     * Coge el map pasado como parámetro y devuelve un map de manera creciente según los valores (puntuaciones) que tiene
     * el map original.
     * @param puntuaciones Map de origen a partir del cual se creará una instancia con sus valores ordenador.
     * @return Devuelve el Map generado con los valores del parámetro 'puntuaciones'.
     */
    private Map<Codigo, Integer> getPuntuacionesOrdenadas(Map<Codigo, Integer> puntuaciones) {
        Set<Map.Entry<Codigo, Integer>> entradas = puntuaciones.entrySet();
        // Usamos un LinkedList porque sus inserciones son más rápidas
        List<Map.Entry<Codigo, Integer>> list = new LinkedList<>(entradas);
        Collections.sort(list, new Comparator<Map.Entry<Codigo, Integer>>() {
            @Override
            public int compare(Map.Entry<Codigo, Integer> codigoIntegerEntry, Map.Entry<Codigo, Integer> t1) {
                return codigoIntegerEntry.getValue() - t1.getValue();
            }
        });
        // Una vez ordenados, creamos un map y los insertamos.
        puntuaciones = new LinkedHashMap<>();
        for (Map.Entry<Codigo, Integer> entrada: list) {
            puntuaciones.put(entrada.getKey(), entrada.getValue());
        }
        return puntuaciones;
    }

    /**
     * Se calcula la puntuación del código pasado como parámetro, en base a sus resultados si jugase con los 'intentos'
     * ya realizados.
     * El método considera que un código es óptimo cuando la diferencia entre el resultado obtenido al
     * jugar con un intento realizado y el resultado del intento realizado es pequeño.
     * Por lo que se obtiene esta diferencia para cada intento y finalmente se suma todo.
     * Nota: En un principio, se intentó obtener el resultado final aplicando la fórmula:
     * F = constanteA * diferencias negras + diferencias blancas + constante B*(número de intentos - 1)
     * Pero no funcionaba del todo bien, por lo que este método utiliza un fórmula más simple que de todos modos da
     * buenos resultados.
     * @param codigo Código a valorar.
     * @return La valoración (Cuánto más cerca de 0, mejor).
     */
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

        //return totalBlack + totalWhite + 2*numeroColumnas*(intentos.size() - 1);
        return totalBlack + totalWhite;
    }

    /**
     * Realiza un cruce entre dos códigos con un solo punto de corte.
     * Se adquiere una posición aleatoria y se dividen los dos códigos en cuatro sub-códigos (generados al cortas los
     * dos códigos iniciales) y se intercambian entre ellos, dando así a dos posibles nuevos códigos de los que se coge
     * uno de manera aleatoria.
     * @param codigoA Código uno a cruzar.
     * @param codigoB Código dos a cruzar.
     * @return El código generado a partir de los cortes.
     */
    private Codigo cruce(Codigo codigoA, Codigo codigoB) {
        // De momento probaremos con un solo corte
        Codigo Return = new Codigo(numeroColumnas);

        int i = numeroColumnas == 1 ? 0 : random.nextInt(numeroColumnas - 1);
        List<Integer> aPart1 = new ArrayList<>(codigoA.codigo.subList(0, i + 1));
        List<Integer> aPart2 = new ArrayList<>(codigoA.codigo.subList(i + 1, numeroColumnas));
        List<Integer> bPart1 = new ArrayList<>(codigoB.codigo.subList(0, i + 1));
        List<Integer> bPart2 = new ArrayList<>(codigoB.codigo.subList(i + 1, numeroColumnas));

        if (random.nextDouble() <= PROBABILIDAD_CRUCE) {
            Return.codigo.addAll(aPart1);
            Return.codigo.addAll(bPart2);
        } else {
            Return.codigo.addAll(bPart1);
            Return.codigo.addAll(aPart2);
        }

        return Return;
    }

    /**
     * Cambia un color del código de forma aleatoria.
     * @param codigo El código al que se le va a cambiar un color.
     * @return El código cambiado..
     */
    private Codigo mutacion(Codigo codigo) {
        Codigo Return = new Codigo(numeroColumnas);
        Return.codigo = new ArrayList<>(codigo.codigo);
        Return.codigo.set(numeroColumnas == 1 ? 0 : random.nextInt(numeroColumnas - 1), 1 + random.nextInt(numeroColores - 1));
        return Return;
    }

    /**
     * Intercambia de posición dos colores de forma aleatoria.
     * @param codigo El código a cambiar.
     * @return El código al que se le han cambiado los colores.
     */
    private Codigo permutacion(Codigo codigo) {
        Codigo Return = new Codigo(numeroColumnas);
        Return.codigo = new ArrayList<>(codigo.codigo);

        int positionA = 0;
        int positionB = 0;
        do {
            positionA = random.nextInt(numeroColumnas);
            positionB = random.nextInt(numeroColumnas);
            if (numeroColumnas == 1) break;
        }
        while (positionA == positionB);
        int aux = codigo.codigo.get(positionA);
        Return.codigo.set(positionA, codigo.codigo.get(positionB));
        Return.codigo.set(positionB, aux);
        return Return;
    }

    /**
     * Crea una instáncia 'ResultPair' con los datos del String 'result' y la devuelve
     * @param result El String del que se tomará la información.
     * @return Una instáncia 'ResultPair' con el atributo 'white' con la cantidad de "W" que hayan en el String y con el
     * atributo 'black' con la cantidad de "B".
     */
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