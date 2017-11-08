package Domain;

/**
 * @author Omar
 */

/**
 * Interfaz que contiene métodos abstractos para que puedan ser implementados en las clases que definen la IA.
 */
public interface Inteligencia {
    Codigo getIntentoInicial();
    Codigo getSiguienteIntento(Fila ultimoIntento);
}
