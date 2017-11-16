package Domain;

import Domain.Excepciones.ExcepcionNoHayColoresSinUsar;
import Domain.Excepciones.ExcepcionPistaUsada;
import Domain.Excepciones.ExcepcionRespuestaIncorrecta;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Clase Partida.
 * Contiene un tablero.
 * Puede contener una inteligencia si y solo si el jugador juega como CodeMaker.
 * Contiene atributos para puntuar al usuario cuando gane la partida.
 * @author ISA
 */
public class Partida {

    private String id;
    private float tiempo;
    private boolean rolMaker;
    private String dificultad;
    private int numColores;
    private int numColumnas;
    private boolean ganado = false;

    private boolean pista1 = false;
    private boolean pista2 = false;
    private boolean pista3 = false;

    private final Tablero tablero;
    private Inteligencia ia;


    /**
     * Creadora Partida.
     * Inicia el tiempo a 0. Y el id es la fecha+hora.
     * Si el jugador juega como CodeMaker, se asigna automáticamente la inteligencia según la dificultad.
     * Si el jugador juega como CodeBreaker, se genera automáticamente el código secreto del tablero.
     * @param rolMaker indica si el jugador juega como
     *                 codemaker(true) o codebreaker(false)
     * @param dificultad nivel de dificultad
     */
    public Partida(boolean rolMaker, String dificultad) {
        DateFormat formato = new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss");
        Date hoy = Calendar.getInstance().getTime();
        this.id = formato.format(hoy);
        this.tiempo = 0.0f;
        this.rolMaker = rolMaker;
        this.dificultad = dificultad;
        switch (dificultad) {
            case "Facil":
                numColores = 4;
                numColumnas = 4;
                break;
            case "Medio":
                numColores = 6;
                numColumnas = 4;
                break;
            case "Dificil":
                numColores = 6;
                numColumnas = 6;
                break;
        }
        tablero = new Tablero(numColumnas);

        if (rolMaker) {
            if (dificultad.equals("Dificil"))
                ia = new MasterCerebro(numColores, numColumnas);
            else
                ia = new Cerebro(numColores, numColumnas);
        }
        else {
            generaCodigoSecretoAleatorio();
        }
    }



    /* CONSULTORAS */

    /**
     * Devuelve el identificador de la partida
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * Obtenemos el tiempo total
     * @return tiempo
     */
    public float getTiempo() {
        return tiempo;
    }

    /**
     * Devuelve si el jugador está jugando como CodeMaker o Breaker
     * @return rolMaker
     */
    public boolean isRolMaker() {
        return rolMaker;
    }

    /**
     * Devuelve el nivel de dificultad
     * @return dificultad
     */
    public String getDificultad() {
        return dificultad;
    }

    /**
     * Devuelve el numero de colores
     * @return numColores
     */
    public int getNumColores() {
        return numColores;
    }

    /**
     * Devuelve el numero de columnas
     * @return numColumnas
     */
    public int getNumColumnas() {
        return numColumnas;
    }

    /**
     * Devuelve si la partida ha sido ganada.
     * @return Retorna si la partida ya se ha ganado.
     */
    public boolean isGanado() {
        return ganado;
    }

    /**
     * Devuelve el numero de fila actual
     * @return El numero de fila que se está tratando actualmente
     */
    public int getNumeroFilaActual() {
        return tablero.getNumeroFilaActual();
    }

    /**
     * Devuelve el código secreto
     * @return Codigo secreto del tablero
     */
    public Codigo getCodigoSecreto() {
        return tablero.getCodigoSecreto();
    }

    /**
     * Consultora que devuelve la última respuesta añadida al tablero.
     * @return respuestas de la última fila jugada.
     */
    public Respuesta getUltimaRespuesta () {
        return tablero.getUltimoIntento().getRespuestas();
    }

    /**
     * Función auxiliar de las pistas 1 y 2 para obtener los colores que no se encuentran en el código secreto.
     * @return Devuelve una lista de colores que no estan en el codigo secreto.
     */
    private ArrayList<Integer> getColoresNoSecretos(){
        ArrayList<Integer> aux = new ArrayList<>();
        for(int i = 1; i <= numColores;++i){
            aux.add(i);
        }

        for(int i = 0; i < numColumnas;++i){
            if(aux.contains(tablero.getCodigoSecreto().codigo.get(i))){
                aux.remove(tablero.getCodigoSecreto().codigo.get(i));
            }
        }
        return aux;
    }

    /**
     * Devuelve una pista de nivel 1 que da un color que no se encuentra en el código secreto.
     * Solo se puede pedir una vez por partida.
     * @return Devuelve un color que no se encuentra en el código secreto.
     * @throws ExcepcionNoHayColoresSinUsar si no hay ningún color que no esté en el código secreto.
     * @throws ExcepcionPistaUsada si se accede a la función por segunda vez.
     */
    public int getPista1() throws ExcepcionPistaUsada, ExcepcionNoHayColoresSinUsar {
        if (!pista1) {
            pista1 = true;
            ArrayList<Integer> auxiliar = getColoresNoSecretos();
            if (auxiliar.size() == 0) throw new ExcepcionNoHayColoresSinUsar("No hay ningún color sin usar.");
            else return auxiliar.get(0);
        }
        throw new ExcepcionPistaUsada("Ya has obtenido una pista de nivel 1.");

    }

    /**
     * Devuelve una pista de nivel 2 que da los colores que no se encuentran en el código secreto.
     * Solo se puede pedir una vez por partida. Si se accede una segunda vez, lanza una excepción.
     * @return Devuelve los colores que no estan en el codigo secreto.
     * @throws ExcepcionPistaUsada si se accede a la función por segunda vez.
     * @throws ExcepcionNoHayColoresSinUsar si no hay ningún color que no esté en el código secreto.
     */
    public ArrayList<Integer> getPista2() throws ExcepcionPistaUsada, ExcepcionNoHayColoresSinUsar {
        if (!pista2) {
            pista2 = true;
            ArrayList<Integer> auxiliar = getColoresNoSecretos();
            if (auxiliar.size() == 0) throw new ExcepcionNoHayColoresSinUsar("No hay ningún color sin usar.");
            else return auxiliar;
        }
        throw new ExcepcionPistaUsada("Ya has obtenido una pista de nivel 2.");
    }

    /**
     * Devuelve una pista de nivel 3 que da un color en una posición.
     * Solo se puede pedir una vez por partida. Si se accede una segunda vez, lanza una excepción.
     * @return Un codigo con una unica posición no vacia que indica el color y la posición de uno de los colores del
     * @throws ExcepcionPistaUsada si se accede a la función por segunda vez.
     * codigo secreto
     */
    public Codigo getPista3() throws ExcepcionPistaUsada {
        if (!pista3) {
            pista3 = true;
            Random rn = new Random();
            Codigo aux = new Codigo(numColumnas);
            int posicion = rn.nextInt(numColumnas);
            for (int i = 0; i < numColumnas; ++i) {
                if (i == posicion) aux.codigo.add(tablero.getCodigoSecreto().codigo.get(posicion));
                else aux.codigo.add(0);
            }
            return aux;
        }
        throw new ExcepcionPistaUsada("Ya has obtenido una pista de nivel 3.");
    }

    /**
     * Genera la puntuación en base al tiempo y la dificultad
     * @return puntuación de la partida
     */
    public int generaPuntuacion() {
        if(pista3 || pista2 || pista1 || (tablero.getNumeroFilaActual() == 15 && !ganado)) return 0;
        float resultado = 0;

        resultado += (15 - tablero.getNumeroFilaActual())*777;
        resultado += 1000000/(tiempo/1000);
        switch(dificultad){
            case "Medio":
                resultado *= 1.10;
                break;
            case "Dificil":
                resultado *= 1.20;
                break;
        }
        return (int)resultado;
    }


    /* MODIFICADORAS */

    /**
     * Coloca la nueva ID
     * @param id nuevo id de la partida
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Añade el tiempo de un turno al total
     * @param tiempo es lo que ha tardado en un turno
     */
    public void sumaTiempo(float tiempo) {
        this.tiempo += tiempo;
    }

    /**
     * Coloca el código secreto
     * @param codigoSecreto codigo que se asigna al tablero
     */
    public void setCodigoSecreto(Codigo codigoSecreto) {
        tablero.setCodigoSecreto(codigoSecreto);
    }

    /**
     * Genera un codigo aleatorio para el tablero y se lo asigna como código secreto
     */
    private void generaCodigoSecretoAleatorio(){
        Codigo codigoSecreto = new Codigo(numColumnas);
        Random rn = new Random();
        for(int i = 0; i < numColumnas; i++)
            codigoSecreto.codigo.add(rn.nextInt(numColores) + 1);
        setCodigoSecreto(codigoSecreto);
    }

    /**
     * Añade un código a la fila actual del tablero.
     * @param code codigo a añadir
     */
    public void setIntento(Codigo code){
        tablero.setUltimoColores(code);
    }

    /**
     * Devuelve el siguiente código intento de la máquina en base a la última fila jugada.
     * Si es el primer intento, genera un intento inicial.
     * Añade el intento al tablero.
     * @return código intento generado por la máquina
     */
    public Codigo generaSiguienteIntento() {
        if (tablero.getNumeroFilaActual() == 0) {
            Codigo newGuess = ia.getIntentoInicial();
            tablero.setUltimoColores(newGuess);
            return newGuess;
        }
        Codigo newGuess = ia.getSiguienteIntento(tablero.getUltimoIntento());
        tablero.setUltimoColores(newGuess);
        return newGuess;
    }

    /**
     * Añade una respuesta a la fila actual del tablero.
     * Si la respuesta es ganadora, se modifica el estado de la partida a ganada.
     * @param respuesta respuesta del último código.
     * @throws ExcepcionRespuestaIncorrecta si la respuesta no se corresponde al código.
     */
    public void setRespuesta(Respuesta respuesta) throws ExcepcionRespuestaIncorrecta {
        tablero.setUltimoRespuestas(respuesta);
        if(respuesta.esGanadora())ganado = true;
    }

    /**
     * Genera la respuesta según el último código y se añade a la fila actual del tablero.
     * Si la respuesta es ganadora, se modifica el estado de la partida a ganada.
     */
    public void generaRespuesta() {
        tablero.generaRespuesta();
        if (tablero.getUltimoIntento().getRespuestas().esGanadora())
            ganado = true;
    }



    /* ESCRITURAS */

    /**
     * Convierte el rol en String para mejor comprensión.
     * @return rol en texto.
     */
    private String rolToString () {
        String rol;
        if (rolMaker) rol = "CodeMaker";
        else rol = "CodeBreaker";
        return rol;
    }

    /**
     * Función de impresión sencilla. Imprime el ID, la dificultad y el Rol.
     */
    public void imprimeInfo() {
        System.out.println("ID: "+id+". Dificultad: "+dificultad+
                ". Rol: "+rolToString()+".");
    }

    /**
     * Función de impresión completa. Imprime ID, dificultad, Rol, número de colores y número de columnas.
     */
    public void imprimeAllInfo() {
        System.out.println("- ID: "+id+"\n" +
                "- Tiempo: " +tiempo+"\n" +
                "- Dificultad: " +dificultad+"\n" +
                "- Rol: " +rolToString()+"\n" +
                "- Numero Colores: " +numColores+"\n" +
                "- Numero Columnas: "+ numColumnas);

    }





}
