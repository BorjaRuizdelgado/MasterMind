package Domain.Excepciones;

/**
 * Excepcion Respuesta Incorrecta
 * Lanzada cuando una respuesta no corresponde a la respuesta que debería tener el código.
 * @author ISA
 */
public class ExcepcionRespuestaIncorrecta extends Exception{
    public ExcepcionRespuestaIncorrecta (String message) {
        super(message);
    }
}
