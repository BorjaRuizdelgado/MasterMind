package Domain.Excepciones;

/**
 * Excepcion Pista Usada
 * Lanzada cuando se intenta acceder por segunda vez a una pista ya obtenida.
 * @author ISA
 */

public class ExcepcionUsuarioInexistente extends Exception{
    public ExcepcionUsuarioInexistente (String message) {
        super(message);
    }

    public ExcepcionUsuarioInexistente () {}
}