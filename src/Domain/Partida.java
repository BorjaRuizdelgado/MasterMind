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

    private final Tablero tablero;
    private Cerebro ia;


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
    private int generaPuntuacion() {
        return 0;
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


}
