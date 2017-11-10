package Domain;

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
    private int numPartidasFinalizadas;
    private int numPartidasGanadas;
    private List <Partida> partidasGuardadas;
    private Partida partidaActual;

    /**
     * Creadora Usuario.
     * Crea un usuario con el nombre pasado como parámetro
     * @param nombre El nombre del usuario.
     */
    public Usuario(String nombre) {
        this.nombre = nombre;
        numPartidasFinalizadas = 0;
        numPartidasGanadas = 0;
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
     * Devuelve el número de partidas totales
     * @return numPartidasFinalizadas
     */
    public int getNumPartidasFinalizadas() {
        return numPartidasFinalizadas;
    }

    /**
     * Devuelve la partida actual del usuario
     * @return partidaActual
     */
    public Partida getPartidaActual() {
        return this.partidaActual;
    }

    /**
     * Devuelve el Id de la Partida Actual
     * @return id partida actual
     */
    public String getIdPartidaActual() {
        return partidaActual.getId();
    }

    /* MODIFICADORAS */

    /**
     * Incrementa el número de partidas totales finalizadas
     */
    private void incrementaPartidasTotalesFinalizadas(){
        numPartidasFinalizadas++;
    }

    /**
     * Incrementa el número de partidas totales ganadas
     */
    private void incrementaPartidasTotalesGanadas(){
        numPartidasGanadas++;
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
        partidasGuardadas.add(partidaActual);
        partidaActual = null;
    }

    /**
     * Finaliza la partida actual y aumenta el número de partidas finalizadas
     */
    public void finalizarPartidaActual(Boolean ganada) {
        partidaActual = null;
        incrementaPartidasTotalesFinalizadas();
        if (ganada) incrementaPartidasTotalesGanadas();
    }

    /**
     * Abandona la partida actual.
     */
    public void abandonaPartidaActual() {
        partidaActual = null;
    }


    /* ESCRITURAS */

    /**
     * Imprime por pantalla la información de todas las partidas guardadas
     * TODO excepción concreta
     */
    public void imprimeInfoPartidasGuardadas() throws Exception {
        if (partidasGuardadas.size() == 0) throw new Exception();
        for (Partida pG : partidasGuardadas) {
            pG.imprimeInfo();
        }
    }



}
