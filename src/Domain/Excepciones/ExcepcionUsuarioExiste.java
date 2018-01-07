package Domain.Excepciones;

public class ExcepcionUsuarioExiste extends Exception {
    public ExcepcionUsuarioExiste (String message) {
        super(message);
    }
}
