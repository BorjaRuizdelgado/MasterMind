package Data;

import Domain.Excepciones.ExcepcionUsuarioInexistente;
import Domain.Partida;
import Domain.Usuario;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static java.nio.file.StandardCopyOption.*;

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
    public static ControladorPersistencia getInstance(String juegoPrueba) {
        if (!juegoPrueba.equals("normal")) loadJuegoPrueba(juegoPrueba);
        if (uniqueInstance == null) uniqueInstance = new ControladorPersistencia();
        return uniqueInstance;
    }

    /**
     * Carga el juego de prueba pasado por parámetro
     * @param juegoPrueba juego de prueba a cargar
     */
    private static void loadJuegoPrueba(String juegoPrueba) {
        copyDirectory(juegoPrueba, "Games");
        copyDirectory(juegoPrueba, "GSR");
        copyDirectory(juegoPrueba, "Users");
    }

    /**
     * Crea los paths y copia la carpeta.
     * @param juegoPrueba juego de prueba a cargar
     * @param filename nombre del fichero a copiar
     */
    private static void copyDirectory(String juegoPrueba, String filename) {
        File fileIn = getFileIn(juegoPrueba,filename);
        File fileOut = getFileOut(filename);
        try {
            copiarCarpeta(fileIn,fileOut);
        } catch (IOException e) {
            System.err.println("Problema al cargar el Juego De Prueba.");
            e.printStackTrace();
        }
    }

    /**
     * Función recursiva de copia de ficheros.
     * @param fileIn fichero a copiar
     * @param fileOut fichero donde copiar
     * @throws IOException excepción si hay algún problema copiando la carpeta
     */
    private static void copiarCarpeta(File fileIn, File fileOut) throws IOException {
        if (fileIn.isDirectory()) {
            if (!fileOut.exists()) fileOut.mkdir();

            String files[] = fileIn.list();

            for (String file : files) {
                File srcFile = new File(fileIn, file);
                File destFile = new File(fileOut, file);

                copiarCarpeta(srcFile, destFile);
            }
        }
        else {
            Files.copy(fileIn.toPath(), fileOut.toPath(), REPLACE_EXISTING, COPY_ATTRIBUTES);
        }

    }

    /**
     * Crea el fichero de entrada con el nombre que le pases por parámetro
     * @param nameFile nombre de la carpeta
     * @return file de la carpeta
     */
    private static File getFileOut(String nameFile) {
        return new File(System.getProperty("user.dir")+"/Data/"+nameFile);
    }

    /**
     * Crea el fichero de salida con el nombre que le pases por parámetro
     * @param nameFile nombre de la carpeta
     * @param nameJuegoPrueba nombre del juego de prueba del que cargar
     * @return file de la carpeta
     */
    private static File getFileIn(String nameJuegoPrueba, String nameFile) {
        return new File(System.getProperty("user.dir") + "/JuegosDePrueba/"+nameJuegoPrueba+"/Data/"+nameFile);
    }

    /**
     * Carga el sistema ranking del fichero guardado.
     */
    public void cargarSistemaRanking() {
        getSistRankInstanceIfNull();
        gestorSistRank.cargar();
    }

    /**
     * Guarda el sistema ranking en el fichero.
     */
    public void guardarSistemaRanking() {
        getSistRankInstanceIfNull();
        gestorSistRank.guardar();
    }

    private void getSistRankInstanceIfNull() {
        if (gestorSistRank == null) {
            gestorSistRank = GestionSistemaRanking.getInstance();
        }
    }

    /* PARTIDA */

    /**
     * Carga la partida cuyo identificador es pasado por parámetro
     * @param idPartida identificador de la partida a cargar
     * @return objeto partida cuyo identificador se ha pasado por parámetro.
     */
    public Partida cargarPartida(String idPartida) {
        getGestionPartidaInstanceIfNull();
        return gestorPartida.cargar(idPartida);
    }

    /**
     * Guarda la partida que se haya pasado por parámetro.
     * @param partida partida a guardar
     */
    public void guardar(Partida partida) {
        getGestionPartidaInstanceIfNull();
        gestorPartida.guardar(partida);
    }

    /**
     * Elimina la partida cuyo identificador es pasado por parámetro
     * @param idPartida identificador de la partida a cargar
     */
    public void eliminarPartida(String idPartida) {
        getGestionPartidaInstanceIfNull();
        gestorPartida.eliminar(idPartida);
    }

    private void getGestionPartidaInstanceIfNull() {
        if (gestorPartida == null) {
            gestorPartida = GestionPartida.getInstance();
        }
    }


    /* USUARIO */

    /**
     * Carga el usuario cuyo nombre de usuario es pasado por parámetro
     * @param username nombre del usuario a cargar
     * @return Usuario cuyo nombre se ha pasado por parámetro
     * @throws ExcepcionUsuarioInexistente el nombre pasado por parámetro no coincide con ningún usuario existente.
     */
    public Usuario cargarUsuario(String username) throws ExcepcionUsuarioInexistente {
        getGestionUsuarioInstanceIfNull();
        return gestorUsuario.cargar(username);
    }

    /**
     * Guarda el usuario que se ha pasado por parámetro.
     * @param user Usuario a guardar
     */
    public void guardar(Usuario user) {
        getGestionUsuarioInstanceIfNull();
        gestorUsuario.guardar(user);
    }

    /**
     * Elimina el usuario cuyo nombre de usuario es pasado por parámetro
     * @param username nombre del usuario a eliminar
     */
    public void eliminarUsuario(String username) {
        getGestionUsuarioInstanceIfNull();
        gestorUsuario.eliminar(username);
    }

    /**
     * Devuelve si existe un usuario con el nombre de usuario pasado por parámetro
     * @param username nombre del usuario a comprobar
     * @return cierto si existe; falso si no existe.
     */
    public boolean existeUsuario(String username) {
        getGestionUsuarioInstanceIfNull();
        return gestorUsuario.existe(username);
    }

    /**
     * Devuelve si hay algún usuario creado en el sistema.
     * @return cierto si existe alguno; falso si no hay ninguno.
     */
    public boolean existeAlgunUsuario() {
        getGestionUsuarioInstanceIfNull();
        return gestorUsuario.existeAlguno();
    }

    /**
     * Devuelve una lista de todos los nombres de usuarios que hay guardados en el sistema
     * @return lista de todos los nombres de usuario.
     */
    public List<String> getTodosUsuarios() {
        getGestionUsuarioInstanceIfNull();
        return gestorUsuario.getTodos();
    }

    private void getGestionUsuarioInstanceIfNull() {
        if (gestorUsuario == null) {
            gestorUsuario = GestionUsuario.getInstance();
        }
    }




}
