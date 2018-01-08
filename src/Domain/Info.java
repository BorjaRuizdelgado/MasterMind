package Domain;


import java.io.Serializable;

/**
 * Clase Info
 * Estructura de datos para los Rankings
 * @author Omar
 */
public class Info implements Serializable {

    private String usuario;
    private int puntuacion;
    private String fecha;

    /**
     * Creadora Info.
     * @param usuario Nombre de usuario
     * @param puntuacion Puntuación total de la partida
     * @param fecha Fecha en la que la partida ha sido jugada
     */
    public Info(String usuario, int puntuacion, String fecha) {
        this.usuario = usuario;
        this.puntuacion = puntuacion;
        this.fecha = fecha;
    }

    /**
     * Devuelve el nombre del usuario
     * @return usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Devuelve la puntuación de la partida
     * @return puntuación
     */
    public int getPuntuacion() {
        return puntuacion;
    }

    /**
     * Devuelve la fecha
     * @return fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * Convierte una info a un String comprensible.
     * Override de 'toString'
     * @return Devolvemos un string con el formato adecuado
     */
    @Override
    public String toString() {
        return
                "usuario='" + usuario + '\'' + '\n' +
                "puntuacion=" + puntuacion + '\n' +
                "fecha='" + fecha + '\'';

    }

    /**
     * Coloca un nuevo nombre pasado por parámetro
     * @param nuevoUsuario nombre nuevo a colocar
     */
    public void setUsuario(String nuevoUsuario) {
        usuario = nuevoUsuario;
    }
}