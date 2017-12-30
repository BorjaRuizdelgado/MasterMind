package Data;

import Domain.Excepciones.ExcepcionUsuarioInexistente;
import Domain.Partida;
import Domain.Usuario;

import java.util.List;

public class ControladorPersistencia {
    private static ControladorPersistencia uniqueInstance;
    private GestionSistemaRanking gsr;
    private GestionUsuario gu;
    private GestionPartida gp;

    private ControladorPersistencia() {
        gsr = GestionSistemaRanking.getInstance();
        gu = GestionUsuario.getInstance();
        gp = GestionPartida.getInstance();
    }


    public static ControladorPersistencia getInstance() {
        if (uniqueInstance == null) uniqueInstance = new ControladorPersistencia();
        return uniqueInstance;
    }


    public void cargarSistemaRanking() {
        if (gsr == null) {
            gsr = GestionSistemaRanking.getInstance();
        }
        gsr.cargar();
    }

    public void guardarSistemaRanking() {
        if (gsr == null) {
            gsr = GestionSistemaRanking.getInstance();
        }
        gsr.guardar();
        //todo @Omar esto no estaba hecho. Lo he añadido pero peta al cargarse.
    }

    /* PARTIDA */

    public Partida cargarPartida(String partida) {
        if (gp == null) {
            gp = GestionPartida.getInstance();
        }
        return gp.cargarPartida(partida);
    }

    public void guardar(Partida p) {
        if (gp == null) {
            gp = GestionPartida.getInstance();
        }
        gp.guardarPartida(p);
    }

    public void eliminarPartida(String p) {
        if (gp == null) {
            gp = GestionPartida.getInstance();
        }
        gp.eliminarPartida(p);
    }

    /* USUARIO */

    public Usuario cargarUsuario(String usuario) throws ExcepcionUsuarioInexistente {
        if (gu == null) {
            gu = GestionUsuario.getInstance();
        }
        return gu.cargar(usuario);
    }

    public void guardar(Usuario u) {
        if (gu == null) {
            gu = GestionUsuario.getInstance();
        }
        gu.guardar(u);
    }

    public boolean existeUsuario(String usuario) {
        if (gu == null) {
            gu = GestionUsuario.getInstance();
        }
        return gu.existe(usuario);
    }

    public boolean existeAlgunUsuario() {
        if (gu == null) {
            gu = GestionUsuario.getInstance();
        }
        return gu.existeAlguno();
    }

    public List<String> getTodosUsuarios() {
        if (gu == null) {
            gu = GestionUsuario.getInstance();
        }
        return gu.getTodos();
    }




}
