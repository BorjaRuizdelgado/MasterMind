package Domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * @author ISA
 */

/**
 * Clase de tipo Partida que contiene el tablero y otros atriburos relevantes para jugar y puntuar al usuario.
 */
public class Partida {

    private String id;
    private float tiempo;
    private boolean rolMaker;
    private String dificultad;
    private int numColores;
    private int numColumnas;
    private boolean ganado = false;

    private final Tablero tablero;
    private Cerebro ia;


    private boolean pista1 = false;
    private boolean pista2 = false;
    private boolean pista3 = false;


    /**
     * Creadora Partida.
     * Inicia el tiempo a 0. Y el id es la fecha+hora
     * @param rolMaker indica si el jugador juega como
     *                 codemaker(true) o codebreaker(false)
     * @param dificultad nivel de dificultad
     */
    public Partida(boolean rolMaker, String dificultad) {
        DateFormat formato = new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss");
        Date hoy = Calendar.getInstance().getTime();
        this.id = formato.format(hoy);
        this.tiempo = 0;
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
            ia = new Cerebro(numColores, numColumnas);
        }
    }

    /**
     * Devuelve el identificador de la partida
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * Coloca el identificador de partida
     * @param id nombre a asignar de la partida
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtenemos el tiempo total
     * @return tiempo
     */
    public float getTiempo() {
        return tiempo;
    }

    /**
     * Coloca el tiempo
     * @param tiempo tiempo que se va a asignar a la partida
     */
    public void setTiempo(float tiempo) {
        this.tiempo = tiempo;
    }

    /**
     * Devuelve si el jugador está jugando como CodeMaker o Breaker
     * @return rolMaker
     */
    public boolean isRolMaker() {
        return rolMaker;
    }

    /**
     * Coloca si el jugador es CodeMaker o Breaker
     * @param RolMaker asignar el rol a partida
     */
    public void setRolMaker(boolean RolMaker) {
        this.rolMaker = RolMaker;
    }

    /**
     * Devuelve el nivel de dificultad
     * @return dificultad
     */
    public String getDificultad() {
        return dificultad;
    }

    /**
     * Coloca el nivel de dificultad
     * @param dificultad a asignar
     */
    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    /**
     * Devuelve el numero de colores
     * @return numColores
     */
    public int getNumColores() {
        return numColores;
    }

    /**
     * Coloca el numero de colores
     * @param numColores numero de colores a asignar
     */
    public void setNumColores(int numColores) {
        this.numColores = numColores;
    }

    /**
     * Devuelve el numero de columnas
     * @return numColumnas
     */
    public int getNumColumnas() {
        return numColumnas;
    }

    /**
     * Coloca el numero de columnas
     * @param numColumnas numero columnas a asignar
     */
    public void setNumColumnas(int numColumnas) {
        this.numColumnas = numColumnas;
    }

    /**
     *
     * @return Retrona si la partida ya se ha ganado.
     */
    public boolean isGanado() {
        return ganado;
    }


    private String rolToString () {
        String rol;
        if (rolMaker) rol = "CodeMaker";
        else rol = "CodeBreaker";
        return rol;
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
     * Devuelve el código secreto
     * @return Codigo secreto del tablero
     */
    public Codigo getCodigoSecreto() {
        return tablero.getCodigoSecreto();
    }

    /**
     * Devuelve el numero de fila actual
     * @return El numero de fila que se está tratando actualmente
     */
    public int getNumeroFilaActual() {
        return tablero.getNumeroFilaActual();
    }

    /**
     * Genera un codigo aleatorio para el tablero y se lo asigna.
     */
    public void generaCodigoSecretoAleatorio(){
        Codigo codigoSecreto = new Codigo(numColumnas);
        Random rn = new Random();
        for(int i = 0; i < numColumnas; i++)
            codigoSecreto.codigo.add(rn.nextInt(numColores) + 1);
        setCodigoSecreto(codigoSecreto);
    }

    public Codigo getSiguienteIntento() {
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
     * Genera la puntuación en base al tiempo y la dificultad
     * @return puntuación de la partida
     */
    public int generaPuntuacion() {
        float resultado = 0;

        resultado += (15 - tablero.getNumeroFilaActual())*777;
        resultado += 1000000/(tiempo/1000);
        switch(dificultad){
            case "Medio":
                resultado *= 1.10;
                break;
            case "Dificil":
                resultado *= 1.20;
        }
        return (int)resultado;
    }

    public void imprimeInfo() {
        System.out.println("ID: "+id+". Dificultad: "+dificultad+
                ". Rol: "+rolToString()+".");
    }
    public void imprimeAllInfo() {
        System.out.println("- ID: "+id+"\n" +
                "- Tiempo: " +tiempo+"\n" +
                "- Dificultad: " +dificultad+"\n" +
                "- Rol: " +rolToString()+"\n" +
                "- Numero Colores: " +numColores+"\n" +
                "- Numero Columnas: "+ numColumnas);

    }

    public void generaRespuesta () {
        tablero.generaRespuesta();
        if (tablero.getUltimoIntento().getRespuestas().esGanadora())
            ganado = true;
    }

    public void setIntento(Codigo code){
        tablero.setUltimoColores(code);
    }

    public void setRespuesta(Respuesta respuesta){
        tablero.setUltimoRespuestas(respuesta);
        if(respuesta.esGanadora())ganado = true;
    }

    public Respuesta getUltimaRespuesta () {
        return tablero.getUltimoIntento().getRespuestas();
    }

    /**
     * Elimina un color.
     * @return -1 si la lista esta vacía, si no devuelve un color sobrante.
     */
    public int getPista1(){
        pista1 = true;
        ArrayList<Integer> auxiliar = getColoresNoSecretos();
        if(auxiliar.size() == 0) return -1;
        else return auxiliar.get(0);

    }

    /**
     *
     * @return Devuelve una lista de colores que no estan en el codigo secreto.
     */
    private ArrayList<Integer> getColoresNoSecretos(){
        ArrayList<Integer> auxiliar = new ArrayList<>();
        for(int i = 0; i < numColores;++i){
            auxiliar.add(i);
        }

        for(int i = 0; i < numColumnas;++i){
            if(auxiliar.contains(tablero.getCodigoSecreto().codigo.get(i))){
                auxiliar.remove(tablero.getCodigoSecreto().codigo.get(i));
            }
        }
        return auxiliar;
    }

    /**
     *
     * @return Devuelve los colores que no estan en el codgigo secreto.
     */
    public ArrayList<Integer> getPista2(){
        pista2 = true;
        return getColoresNoSecretos();
    }

    /**
     * Da un color en una posición.
     * @return Un codigo con una unica posición no vacia que indica el color y la posición de uno de los colores del
     * codigo secreto
     */
    public Codigo getPista3(){
        pista3 = true;
        Random rn = new Random();
        Codigo auxilair = new Codigo(numColumnas);
        int posicion = rn.nextInt(numColumnas);
        for(int i = 0; i < numColumnas;++i){
            if(i == posicion) auxilair.codigo.add(tablero.getCodigoSecreto().codigo.get(posicion));
            else auxilair.codigo.add(0);
        }

        return auxilair;
    }





}
