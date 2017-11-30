package Data;

import Domain.Partida;


public class GestionPartida {
    private static GestionPartida uniqueInstance;
    private String path;

    private GestionPartida() {
        path = "aaaa";
    }

    private GestionPartida getInstance() {
        return uniqueInstance;
    }

    public Partida cargarPartida(String partida) {

    }

    public void guardarPartida(Partida p) {

    }
}
