package Domain;

import Domain.Excepciones.ExcepcionNoHayPartidaActual;
import Domain.Excepciones.ExcepcionNoHayPartidasGuardadas;
import Domain.Excepciones.ExcepcionYaExistePartidaActual;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase Usuario
 * Tiene un nombre único en el sistema.
 * Tiene una partida actual y una lista de partidas guardadas. Así como un recuento de las finalizadas.
 * @author ISA
 */
public class Usuario implements Serializable {
//todo quitar excepcion crear partidaActual, getPartidaActual :)
    private String nombre;
    private int numPartidasFinalizadasCB;   //Partidas acabadas como rol CodeBreaker
    private int numPartidasGanadasCB;       //Partidas ganadas como rol CodeBreaker
    private int numPartidasFinalizadasCM;   //Partidas acabadas como rol CodeMaker
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
     * Devuelve el número de partidas finalizads como CodeMaker
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
     * Carga la partida pasada por parámetro: la coloca como partida actual y la elimina de partidas guardadas.
     * @param partida partida cargada.
     */
    public void cargaPartida(Partida partida)  {
        partidaActual = partida;
        //partidasGuardadas.remove(partida.getId());
    }

    /**
     * Finaliza la partida actual y aumenta el número de partidas finalizadas según si es CodeMaker o CodeMaker y si la ha ganado.
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

    public List<String> getPartidasGuardadas(){
        return partidasGuardadas;
    }



}
