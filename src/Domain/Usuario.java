package Domain;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Omar
 */
public class Usuario {
    /**
     * Atributos
     */
    private String nombre;
    private int numPartidasFinalizadas;
    private List <Partida> partidasGuardadas;
    private Partida partidaActual;

    /**
     * Creadora que coje un nombre como parámetro
     * @param nombre El nombre del usuario.
     */
    public Usuario(String nombre) {
        this.nombre = nombre;
        numPartidasFinalizadas = 0;
        partidasGuardadas = new ArrayList<>();
    }

    /**
     * Devolvemos el nombre del usuario
     * @return Nombre.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Actualizamos el nombre del usario
     * @param nuevoNombre Nuevo nombre del usuario.
     */
    public void setNombre(String nuevoNombre) {
        this.nombre = nuevoNombre;
    }

    /**
     * Devolvemos el número de partidas totales del jugador
     * @return numPartidasFinalizadas
     */
    public int getNumPartidasFinalizadas() {
        return numPartidasFinalizadas;
    }

    /**
     * Creamos una nueva partida y la asignamos como partida actual
     * @param rolMaker rol de la partida
     * @param dif dificultad de la partida
     */
    public void creaPartidaActual(Boolean rolMaker, String dif) {
        this.partidaActual = new Partida(rolMaker, dif);
    }

    public Partida getPartidaActual() {
        return this.partidaActual;
    }


    /**
     * Añade la partida actual a la lista de partidas guardadas
     */
    public void guardaPartidaActual() throws NullPointerException {
        partidasGuardadas.add(partidaActual);
        partidaActual = null;
    }

    /**
     * Incrementamos el número de partidas totales
     */
    public void incrementaPartidasTotales(){
        numPartidasFinalizadas++;
    }

    /**
     * Devuelve el Id de la Partida Actual
     * @return id partida actual
     */
    public String getIdPartidaActual() {
        return partidaActual.getId();
    }

    /**
     * Imprime por pantalla la información de todas las partidas guardadas
     */
    public void imprimeInfoPartidasGuardadas() {
        if (partidasGuardadas.size() == 0)
            System.out.println("No hay partidas guardadas");
        for (Partida pG : partidasGuardadas) {
            pG.imprimeInfo();
        }
    }

    /**
     * Finaliza la partida actual y aumenta el número de partidas finalizadas
     */
    public void finalizarPartidaActual() {
        partidaActual = null;
        numPartidasFinalizadas++;
    }

}
