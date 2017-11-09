package Domain;


/**
 * Clase Info
 * Estructura de datos para los Rankings
 * @author Omar
 */
public class Info {
    private String usuario;
    private int puntuacion;
    private String fecha;

    /**
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
     * Devolvemos el nombre del usuario
     * @return usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Devolvemos la puntuación de la partida
     * @return puntuación
     */
    public int getPuntuacion() {
        return puntuacion;
    }

    /**
     * Devolvemos la fecha
     * @return fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
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
}