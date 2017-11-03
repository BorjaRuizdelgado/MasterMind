package Domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author ISA
 */
public class Partida {

    private String idPartida;
    private float tiempo;
    private boolean isRolMaker;
    private String dificultad;
    private int numColores;
    private int numColumnas;

    private final Tablero tablero;
    //private final Cerebro ia;


    /**
     * Crea un nuevo objeto partida.
     * Inicia el tiempo a 0. Y el id es la fecha+hora
     * @param isRolMaker indica si el jugador juega como
     *                 codemaker(true) o codebreaker(false)
     * @param dificultad nivel de dificultad
     */
    public Partida(boolean isRolMaker, String dificultad) {
        DateFormat formato = new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss");
        Date hoy = Calendar.getInstance().getTime();
        String fecha = formato.format(hoy);
        this.idPartida = fecha;
        this.tiempo = 0;
        this.isRolMaker = isRolMaker;
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
    }

    /**
     * Devuelve el identificador de la partida
     * @return idPartida
     */
    public String getIdPartida() {
        return idPartida;
    }

    /**
     * Coloca el identificador de partida
     * @param idPartida
     */
    public void setIdPartida(String idPartida) {
        this.idPartida = idPartida;
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
     * @param tiempo
     */
    public void setTiempo(float tiempo) {
        this.tiempo = tiempo;
    }

    /**
     * Devuelve si el jugador está jugando como CodeMaker o Breaker
     * @return isRolMaker
     */
    public boolean isRolMaker() {
        return isRolMaker;
    }

    /**
     * Coloca si el jugador es CodeMaker o Breaker
     * @param RolMaker
     */
    public void setRolMaker(boolean RolMaker) {
        this.isRolMaker = RolMaker;
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
     * @param dificultad
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
     * @param numColores
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
     * @param numColumnas
     */
    public void setNumColumnas(int numColumnas) {
        this.numColumnas = numColumnas;
    }





    /**
     * Añade el tiempo de un turno al total
     * @param tiempo es lo que ha tardado en un turno
     */
    private void sumaTiempo(float tiempo) {
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
    public void generaCodigoAleatorio(){
        Codigo codigoSecreto = new Codigo(numColumnas);
        Random rn = new Random();
        for(int i = 0; i < numColumnas; i++)
            codigoSecreto.codigo.add(rn.nextInt(numColores) + 1);
        setCodigoSecreto(codigoSecreto);
    }

    /**
     * Genera la puntuación en base al tiempo y la dificultad
     * @return puntuación de la partida
     */
    private int generaPuntuación() {
        return 0;
    }


}
