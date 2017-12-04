package Data;

import Domain.Excepciones.ExcepcionUsuarioInexistente;
import Domain.Partida;
import Domain.SistemaRanking;
import Domain.Usuario;

public class ControladorPersistencia {
    private static SistemaRanking uniqueInstance;
    private GestionSistemaRanking gsr;
    private GestionUsuario gu;
    private GestionPartida gp;

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

    public void guardar(SistemaRanking sr) {

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
