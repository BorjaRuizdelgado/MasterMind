package Data;

import Domain.Partida;


public class GestionPartida {
    private static GestionPartida uniqueInstance;
    private String path;

    private GestionPartida() {
        path = "aaaa";
    }

    public static GestionPartida getInstance() {
        return uniqueInstance;
    }

    public Partida cargarPartida(String partida) {
        return null;
    }

    public void guardarPartida(Partida p) {

    }
}
