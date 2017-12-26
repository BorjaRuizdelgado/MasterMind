package Domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase Usuario
 * Tiene un nombre único en el sistema.
 * Tiene una partida actual y una lista de los ids de las partidas guardadas.
 * Contiene información para calcular estadísticas sobre sus partidas.
 * @author ISA
 */
public class Usuario implements Serializable {
    private String nombre;
    private int numPartidasFinalizadasCB;   //Partidas acabadas como rol CodeBreaker
    private int numPartidasFinalizadasCM;   //Partidas acabadas como rol CodeMaker
    private int numPartidasGanadasCB;       //Partidas ganadas  como rol CodeBreaker
    private List <String> partidasGuardadas;
    private Partida partidaActual;

    /**
     * Creadora Usuario.
     * Crea un usuario con el nombre pasado como parámetro
     * @param nombre El nombre del usuario.
     */
    public Usuario(String nombre) {
        this.nombre = nombre;
        numPartidasFinalizadasCB = 0;
        numPartidasGanadasCB = 0;
        numPartidasFinalizadasCM = 0;
        partidasGuardadas = new ArrayList<>();
    }


    /* CONSULTORAS */

    /**
     * Devuelve el nombre del usuario
     * @return Nombre del usuario
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Devuelve el número de partidas totales finalizadas como CodeBreaker
     * @return numPartidasFinalizadasCB
     */
    public int getNumPartidasFinalizadasCB() {
        return numPartidasFinalizadasCB;
    }

    /**
     * Devuelve el número de partidas ganadas
     * @return numPartidasGanadasCB
     */
    public int getNumPartidasGanadasCB() {
        return numPartidasGanadasCB;
    }

    /**
     * Devuelve el número de partidas finalizadas como CodeMaker
     * @return numPartidasFinalizadasCM
     */
    public int getNumPartidasFinalizadasCM() {
        return numPartidasFinalizadasCM;
    }

    /**
     * Devuelve la partida actual del usuario
     * @return partidaActual
     */
    public Partida getPartidaActual()  {
        return this.partidaActual;
    }


    /* MODIFICADORAS */

    /**
     * Actualiza el nombre del usario
     * @param nuevoNombre Nuevo nombre del usuario.
     */
    public void setNombre(String nuevoNombre) {
        this.nombre = nuevoNombre;
    }

    /**
     * Incrementa el número de partidas totales finalizadas como CodeBreaker
     */
    private void incrementaPartidasFinalizadasCB(){
        numPartidasFinalizadasCB++;
    }

    /**
     * Incrementa el número de partidas totales ganadas como CodeBreaker
     */
    private void incrementaPartidasGanadasCB(){
        numPartidasGanadasCB++;
    }

    /**
     * Incrementa el número de partidas totales finalizadas como CodeMaker
     */
    private void incrementaPartidasFinalizadasCM(){
        numPartidasFinalizadasCM++;
    }

    /**
     * Reinicia los contadores del usuario.
     */
    public void reiniciaEstadisticas() {
        numPartidasFinalizadasCB = 0;
        numPartidasGanadasCB = 0;
        numPartidasFinalizadasCM = 0;
    }

    /**
     * Crea una nueva partida y la asigna como partida actual
     * @param rolMaker rol de la partida
     * @param dif dificultad de la partida
     */
    public void creaPartidaActual(Boolean rolMaker, String dif) {
        this.partidaActual = new Partida(rolMaker, dif);
    }

    /**
     * Añade la partida actual a la lista de partidas guardadas y la elimina de partida actual.
     */
    public void guardaPartidaActual() {
        partidasGuardadas.remove(partidaActual.getId());
        partidasGuardadas.add(partidaActual.getId());
        partidaActual = null;
    }

    /**
     * Carga la partida pasada por parámetro: la coloca como partida actual.
     * @param partida partida cargada.
     */
    public void cargaPartida(Partida partida)  {
        partidaActual = partida;
        //partidasGuardadas.remove(partida.getId());
    }

    /**
     * Finaliza la partida actual y aumenta el número de partidas finalizadas según si es CodeMaker o CodeBreaker y si la ha ganado.
     * @param ganada indica si la partida ha sido ganada.
     */
    public void finalizarPartidaActual(Boolean ganada)  {
        if (partidaActual.isRolMaker()) incrementaPartidasFinalizadasCM();
        else {
            incrementaPartidasFinalizadasCB();
            if (ganada) incrementaPartidasGanadasCB();
        }
        partidasGuardadas.remove(partidaActual.getId());
        partidaActual = null;
    }

    /**
     * Abandona la partida actual sin guardarla.
     */
    public void abandonaPartidaActual() {
        partidasGuardadas.remove(partidaActual.getId());
        partidaActual = null;
    }

    /**
     * Devuelve la lista de identificadores de las partidas que tiene guardadas.
     * @return
     */
    public List<String> getPartidasGuardadas(){
        return partidasGuardadas;
    }



}
