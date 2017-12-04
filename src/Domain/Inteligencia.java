package Domain;




/**
 * Interfaz Inteligencia
 * Contiene métodos abstractos para que puedan ser implementados en las clases que definen la IA.
 * @author Omar
 */
public interface Inteligencia {
    Codigo getIntentoInicial();
    Codigo getSiguienteIntento(Fila ultimoIntento);
}
