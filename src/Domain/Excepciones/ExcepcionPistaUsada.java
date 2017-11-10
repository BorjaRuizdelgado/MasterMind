package Domain.Excepciones;

/**
 * Excepcion Pista Usada
 * Lanzada cuando se intenta acceder por segunda vez a una pista ya obtenida.
 * @author ISA
 */

public class ExcepcionPistaUsada extends Exception{
    public ExcepcionPistaUsada (String message) {
        super(message);
    }
}
