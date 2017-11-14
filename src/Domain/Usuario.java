package Domain;

import Domain.Excepciones.ExcepcionNoHayPartidaActual;
import Domain.Excepciones.ExcepcionNoHayPartidasGuardadas;
import Domain.Excepciones.ExcepcionYaExistePartidaActual;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase Usuario
 * Tiene un nombre único en el sistema.
 * Tiene una partida actual y una lista de partidas guardadas. Así como un recuento de las finalizadas.
 * @author ISA
 */
public class Usuario {

    private String nombre;
    private int numPartidasFinalizadasCB;   //Partidas acabadas como rol CodeBreaker
    private int numPartidasGanadasCB;       //Partidas ganadas como rol CodeBreaker
    private int numPartidasFinalizadasCM;   //Partidas acabadas como rol CodeMaker
    private List <Partida> partidasGuardadas;
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
     * Actualiza el nombre del usario
     * @param nuevoNombre Nuevo nombre del usuario.
     */
    public void setNombre(String nuevoNombre) {
        this.nombre = nuevoNombre;
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
     *
     */
    public Partida getPartidaActual() throws ExcepcionNoHayPartidaActual {
        if (partidaActual == null) throw new ExcepcionNoHayPartidaActual("** ERROR **: No hay una partida actual.");
        return this.partidaActual;
    }

    /**
     * Devuelve el Id de la Partida Actual
     * @return id partida actual
     * @throws ExcepcionNoHayPartidaActual cuando no hay una partida actual
     */
    public String getIdPartidaActual() throws ExcepcionNoHayPartidaActual {
        if (partidaActual == null) throw new ExcepcionNoHayPartidaActual("** ERROR **: No hay una partida actual.");
        return partidaActual.getId();
    }

    /* MODIFICADORAS */

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
     * @throws ExcepcionYaExistePartidaActual cuando ya existe una partida actual.
     */
    public void creaPartidaActual(Boolean rolMaker, String dif) throws ExcepcionYaExistePartidaActual {
        if (partidaActual != null) throw new ExcepcionYaExistePartidaActual("** ERROR **: Ya hay una partida actual. Guarda la actual o abandonala para crear otra.");
        this.partidaActual = new Partida(rolMaker, dif);
    }

    /**
     * Añade la partida actual a la lista de partidas guardadas y la elimina de partida actual.
     * @throws ExcepcionNoHayPartidaActual cuando no hay una partida actual
     */
    public void guardaPartidaActual() throws ExcepcionNoHayPartidaActual {
        if (partidaActual == null) throw new ExcepcionNoHayPartidaActual("** ERROR **: No hay una partida actual.");
        partidasGuardadas.add(partidaActual);
        partidaActual = null;
    }

    /**
     * Finaliza la partida actual y aumenta el número de partidas finalizadas según si es CodeMaker o CodeMaker y si la ha ganado.
     * @param ganada indica si la partida ha sido ganada.
     * @throws ExcepcionNoHayPartidaActual cuando no hay una partida actual
     */
    public void finalizarPartidaActual(Boolean ganada) throws ExcepcionNoHayPartidaActual {
        if (partidaActual == null) throw new ExcepcionNoHayPartidaActual("** ERROR **: No hay una partida actual.");
        if (partidaActual.isRolMaker()) incrementaPartidasFinalizadasCM();
        else {
            incrementaPartidasFinalizadasCB();
            if (ganada) incrementaPartidasGanadasCB();
        }
        partidaActual = null;

    }

    /**
     * Abandona la partida actual.
     * @throws ExcepcionNoHayPartidaActual cuando no hay una partida actual
     */
    public void abandonaPartidaActual() throws ExcepcionNoHayPartidaActual {
        if (partidaActual == null) throw new ExcepcionNoHayPartidaActual("** ERROR **: No hay una partida actual.");
        partidaActual = null;
    }


    /* ESCRITURAS */

    /**
     * Imprime por pantalla la información de todas las partidas guardadas
     * @throws ExcepcionNoHayPartidasGuardadas cuando no hay ninguna partida guardada
     */
    public void imprimeInfoPartidasGuardadas() throws ExcepcionNoHayPartidasGuardadas {
        if (partidasGuardadas.size() == 0) throw new ExcepcionNoHayPartidasGuardadas("** ERROR **:No tienes ninguna partida guardada.");
        for (Partida pG : partidasGuardadas) {
            pG.imprimeInfo();
        }
    }



}
