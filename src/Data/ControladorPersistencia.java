package Data;

import Domain.Excepciones.ExcepcionUsuarioInexistente;
import Domain.Partida;
import Domain.SistemaRanking;
import Domain.Usuario;

public class ControladorPersistencia {
    private static ControladorPersistencia uniqueInstance;
    private GestionSistemaRanking gsr;
    private GestionUsuario gu;
    private GestionPartida gp;


    public static ControladorPersistencia getInstance() {
        return uniqueInstance;
    }

    public Usuario cargarUsuario(String usuario) throws ExcepcionUsuarioInexistente {
        if (gu == null) {
            gu = GestionUsuario.getInstance();
        }
        return gu.cargar(usuario);
    }

    public Partida cargarPartida(String partida) {
        if (gp == null) {
            gp = GestionPartida.getInstance();
        }
        return gp.cargarPartida(partida);
    }

    public void cargarSistemaRanking() {
        if (gsr == null) {
            gsr = GestionSistemaRanking.getInstance();
        }
        gsr.cargar();
    }

    public void guardarSistemaRanking() {

    }

    public void guardar(Usuario u) {
        if (gu == null) {
            gu = GestionUsuario.getInstance();
        }
        gu.guardar(u);
    }

    public void guardar(Partida p) {

    }

    public boolean existeUsuario(String usuario) {
        if (gu == null) {
            gu = GestionUsuario.getInstance();
        }
        return gu.exists(usuario);
    }




}
