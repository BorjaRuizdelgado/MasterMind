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
    }

    public SistemaRanking cargarSistemaRanking() {

    }

    public void guardar(SistemaRanking sr) {

    }

    public void guardar(Usuario u) {

    }

    public void guardar(Partida p) {

    }

    public boolean existeUsuario(String usuario) {

    }




}
