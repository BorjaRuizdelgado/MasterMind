package Data;

import Domain.Excepciones.ExcepcionUsuarioInexistente;
import Domain.Partida;
import Domain.Usuario;

import java.util.List;

/**
 * Clase Controlador Persistencia.
 * Controlador de la capa de datos que comunica el controlador de dominio con los tres gestores.
 * Contiene los gestores de Usuarios, SistemaRanking y de Partidas guardadas.
 * @author ISA
 */

public class ControladorPersistencia {
    private static ControladorPersistencia uniqueInstance;
    private GestionSistemaRanking gestorSistRank;
    private GestionUsuario gestorUsuario;
    private GestionPartida gestorPartida;

    /**
     * Creadora Controlador Persistencia.
     * Inicia los tres gestores.
     */
    private ControladorPersistencia() {
        gestorSistRank = GestionSistemaRanking.getInstance();
        gestorUsuario = GestionUsuario.getInstance();
        gestorPartida = GestionPartida.getInstance();
    }


    /**
     * Controlador Persistencia es un singleton y por ello se debe acceder mediante getInstance.
     * @return uniqueInstance de ControladorPersistencia
     */
    public static ControladorPersistencia getInstance() {
        if (uniqueInstance == null) uniqueInstance = new ControladorPersistencia();
        return uniqueInstance;
    }

    /**
     * Carga el sistema ranking del fichero guardado.
     */
    public void cargarSistemaRanking() {
        if (gestorSistRank == null) {
            gestorSistRank = GestionSistemaRanking.getInstance();
        }
        gestorSistRank.cargar();
    }

    /**
     * Guarda el sistema ranking en el fichero.
     */
    public void guardarSistemaRanking() {
        if (gestorSistRank == null) {
            gestorSistRank = GestionSistemaRanking.getInstance();
        }
        gestorSistRank.guardar();
    }

    /* PARTIDA */

    /**
     * Carga la partida cuyo identificador es pasado por parámetro
     * @param idPartida identificador de la partida a cargar
     * @return objeto partida cuyo identificador se ha pasado por parámetro.
     */
    public Partida cargarPartida(String idPartida) {
        if (gestorPartida == null) {
            gestorPartida = GestionPartida.getInstance();
        }
        return gestorPartida.cargar(idPartida);
    }

    /**
     * Guarda la partida que se haya pasado por parámetro.
     * @param partida partida a guardar
     */
    public void guardar(Partida partida) {
        if (gestorPartida == null) {
            gestorPartida = GestionPartida.getInstance();
        }
        gestorPartida.guardar(partida);
    }

    /**
     * Elimina la partida cuyo identificador es pasado por parámetro
     * @param idPartida identificador de la partida a cargar
     */
    public void eliminarPartida(String idPartida) {
        if (gestorPartida == null) {
            gestorPartida = GestionPartida.getInstance();
        }
        gestorPartida.eliminar(idPartida);
    }

    /* USUARIO */

    /**
     * Carga el usuario cuyo nombre de usuario es pasado por parámetro
     * @param username nombre del usuario a cargar
     * @return Usuario cuyo nombre se ha pasado por parámetro
     * @throws ExcepcionUsuarioInexistente el nombre pasado por parámetro no coincide con ningún usuario existente.
     */
    public Usuario cargarUsuario(String username) throws ExcepcionUsuarioInexistente {
        if (gestorUsuario == null) {
            gestorUsuario = GestionUsuario.getInstance();
        }
        return gestorUsuario.cargar(username);
    }

    /**
     * Guarda el usuario que se ha pasado por parámetro.
     * @param user Usuario a guardar
     */
    public void guardar(Usuario user) {
        if (gestorUsuario == null) {
            gestorUsuario = GestionUsuario.getInstance();
        }
        gestorUsuario.guardar(user);
    }

    /**
     * Elimina el usuario cuyo nombre de usuario es pasado por parámetro
     * @param username nombre del usuario a eliminar
     */
    public void eliminarUsuario(String username) {
        if (gestorUsuario == null) {
            gestorUsuario = GestionUsuario.getInstance();
        }
        gestorUsuario.eliminar(username);
    }

    /**
     * Devuelve si existe un usuario con el nombre de usuario pasado por parámetro
     * @param username nombre del usuario a comprobar
     * @return cierto si existe; falso si no existe.
     */
    public boolean existeUsuario(String username) {
        if (gestorUsuario == null) {
            gestorUsuario = GestionUsuario.getInstance();
        }
        return gestorUsuario.existe(username);
    }

    /**
     * Devuelve si hay algún usuario creado en el sistema.
     * @return cierto si existe alguno; falso si no hay ninguno.
     */
    public boolean existeAlgunUsuario() {
        if (gestorUsuario == null) {
            gestorUsuario = GestionUsuario.getInstance();
        }
        return gestorUsuario.existeAlguno();
    }

    /**
     * Devuelve una lista de todos los nombres de usuarios que hay guardados en el sistema
     * @return lista de todos los nombres de usuario.
     */
    public List<String> getTodosUsuarios() {
        if (gestorUsuario == null) {
            gestorUsuario = GestionUsuario.getInstance();
        }
        return gestorUsuario.getTodos();
    }




}
