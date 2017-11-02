package Domain;

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
    private int partidasTotales;
    private List <Partida> partidasGuardadas;
    private List <Partida> partidaActual;

    /**
     * Creadora que coje un nombre como parámetro
     * @param nombre El nombre del usuario.
     */
    public Usuario(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devolvemos el nombre del usuario
     * @return Nombre.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Devolvemos el número de partidas totales del jugador
     * @return partidasTotales
     */
    public int getPartidasTotales() {
        return partidasTotales;
    }

    /**
     * Incrementamos el número de partidas totales
     */
    public void incrementaPartidasTotales(){
        partidasTotales++;
    }
}
