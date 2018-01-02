package Domain.Controllers;

import Data.ControladorPersistencia;
import Domain.*;
import Domain.Excepciones.*;
import groovy.lang.Tuple2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author borja
 * Clase para controlar el dominio de la aplicacion.
 */
public class ControladorDominio {
    private static ControladorDominio uniqueInstance;
    private Usuario usuarioCargado = null;
    private Partida partidaActual = null;
    private SistemaRanking ranking = null;
    private ControladorPersistencia persistencia;


    /**
     * Creadora ControladorDominio.
     */
    private ControladorDominio(){
        persistencia = ControladorPersistencia.getInstance();
        persistencia.cargarSistemaRanking();
        ranking = SistemaRanking.getInstance();
    }
    /** Si la instancia no existe, se inicializa con su método privado
     * @return La única instancia de la clase
     */
    public static ControladorDominio getInstance() {
        if(uniqueInstance == null)
            uniqueInstance = new ControladorDominio();
        return uniqueInstance;
    }

    /**
     * Crea y carga un nuevo usuario.
     * @param nombre Nombre del nuevo usuario.
     * @throws ExcepcionUsuarioExiste si el usuario ya está credo.
     */
    public void crearUsuario(String nombre) throws ExcepcionUsuarioExiste {
        if(persistencia.existeUsuario(nombre)){
            throw new ExcepcionUsuarioExiste("Ya existe un usuario con este nombre.");
        }
        guardaUsuarioActual();
        usuarioCargado = new Usuario(nombre);
        guardaUsuarioActual();
    }

    /**
     * Carga un usuario en memoria. Si ya habia uno cargado manda guardarlo y todas sus partidas.
     * @param nombre carga el usuario con id = nombre.
     * @throws ExcepcionUsuarioInexistente si el usuario con ese identificador no existe.
     */
    public void cargarUsuario(String nombre) throws ExcepcionUsuarioInexistente{
        guardaUsuarioActual();
        usuarioCargado = persistencia.cargarUsuario(nombre);
    }

    /**
     * Crea una partida de tipo usuario CodeMaker con una dificultad determinada y un codigo secreto.
     * @param dificultad Determina el grado de dificultad de la partidad.
     * @param codigoSecreto Determina el codigo secreto elegido por el usuario.
     */
    public void crearPartidaUsuarioCargadoRolMaker(String dificultad, ArrayList<Integer> codigoSecreto){
        guardaPartidaActual();
        usuarioCargado.creaPartidaActual(true,dificultad);
        partidaActual = usuarioCargado.getPartidaActual();
        Codigo secreto = new Codigo(codigoSecreto.size());
        secreto.codigo = new ArrayList<>(codigoSecreto);
        partidaActual.setCodigoSecreto(secreto);
    }

    /**
     * Crea una partida de tipo usuario CodeBreaker con una dificultad determinada.
     * @param dificultad Determina el grado de dificultad de la partida.
     * @return Codigo secreto aleatorio generado por la maquina.
     */
    public List<Integer> crearPartidaUsuarioCargadoRolBreaker(String dificultad){
        guardaPartidaActual();
        usuarioCargado.creaPartidaActual(false, dificultad);
        partidaActual = usuarioCargado.getPartidaActual();
        return partidaActual.getCodigoSecreto().codigo;

    }

    /**
     * Carga una partida en memoria si hay una ya cargada la guarda en disco primero.
     * @param idPartida Determina que partida hay que cargar de usuario.
     * @return Retorna el rol del usuario en esa partida y la dificultad de la misma.
     */
    public Tuple2<Boolean,String> cargarPartidaUsuario(String idPartida){
        guardaPartidaActual();
        partidaActual = persistencia.cargarPartida(idPartida);
        usuarioCargado.cargaPartida(partidaActual);
        return new Tuple2<>(partidaActual.isRolMaker(),partidaActual.getDificultad());
    }

    /**
     * Asigna la respuesta del usuario al utlimo guess y devuelve el siguiente intento generado por la maquina.
     * @param respuesta Devuelve la respuesta pensada por la maquina
     * @return Retorna el codigo de respuesta.
     * @throws ExcepcionRespuestaIncorrecta Si la respuesta no es correcta.
     */
    public List<Integer> jugarCodeMaker(ArrayList<Integer> respuesta) throws ExcepcionRespuestaIncorrecta {
        Respuesta res = new Respuesta(respuesta.size());
        res.respuesta = new ArrayList<>(respuesta);
        partidaActual.setRespuesta(res);
        return partidaActual.generaSiguienteIntento().codigo;
    }

    public List<Integer> jugarCodeMaker() { // Lo necesito para el primer intento que juego como codeMaker
        return partidaActual.generaSiguienteIntento().codigo;
    }

    /**
     * El usuario es capaz de poner un codigo y la maquina le devuelve una respuesta sobre su partida.
     * @param codigo Pensado por la maquina.
     * @param tiempoTardado Tiempo que ha tardado el usuario en decidor el codigo.
     * @return Respuesta pensada por la maquina.
     */
    public List<Integer> juegaCodeBreaker(ArrayList<Integer> codigo, float tiempoTardado){
        Codigo code = new Codigo(codigo.size());
        code.codigo = new ArrayList<>(codigo);
        partidaActual.setIntento(code);
        partidaActual.generaRespuesta();
        partidaActual.sumaTiempo(tiempoTardado);
        return partidaActual.getUltimaRespuesta().respuesta;
    }

    /**
     * Finaliza la partida que esta siendo utilizada utilizada en este momento y la borra de memoria y la desasigna al
     * usuario.
     * @param ganada Booleano que indica si el usuario ha ganado una partida o no.
     */
    public void terminaPartidaActual(boolean ganada){
        usuarioCargado.finalizarPartidaActual(ganada);
        persistencia.eliminarPartida(partidaActual.getId());
        partidaActual = null;
    }

    /**
     * Método para abandonar la partida actual.
     */
    public void abandonaPartidaAcutal(){
        usuarioCargado.abandonaPartidaActual();
        persistencia.eliminarPartida(partidaActual.getId());
        partidaActual = null;
    }

    /**
     * Actualiza el ranking según la partida actual.
     */
    public void actualizaRanking(){
        int puntuacion = partidaActual.generaPuntuacion();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate localDate = LocalDate.now();
        String fecha = dtf.format(localDate);
        ranking.addNewPuntuation(usuarioCargado.getNombre(),partidaActual.generaPuntuacion(),fecha,partidaActual.getDificultad());
    }

    /**
     * Devuelve los rankings dependiendo de la dificultad
     * @param dificultad Dificultad en forma de string
     * @return Los rankings
     */
    public List<String> getRanking(String dificultad){
        switch (dificultad){
            case "Facil":
                return ranking.getRankingFacil();
            case "Medio":
                return ranking.getRankingMedio();
            case "Dificil":
                return ranking.getRankingDificil();
        }
        return null;
    }

    /**
     * Devuelve los rankings de un usuario dependiendo de la dificultad.
     * @param nombreUsr Nombre del usuario sobre el que se desea hacer la consulta.
     * @param dificultad Nivel de dificultad en forma de String (Facil, Medio, Dificil)
     * @return Los rankings.
     */
    public List<String> getRankingNombreUsrDificultad(String nombreUsr, String dificultad){
        switch (dificultad){
            case "Facil":
                return ranking.getRankingFacil(nombreUsr);
            case "Medio":
                return ranking.getRankingMedio(nombreUsr);
            case "Dificil":
                return ranking.getRankingDificil(nombreUsr);
        }
        return null;
    }

    /**
     * Devuelve las partidas que el usuario cargado ha guardado
     * @return Lista de los identificadores de las partidas guardadas.
     */
    public List<String> getPartidasGuardadasUsr(){
        return usuarioCargado.getPartidasGuardadas();
    }

    /**
     * Metodo que guarda el usuario actual y el ranking en disco cuando se invoca al cerrar.
     */
    public void onClose(){
       guardaUsuarioActual();
       guardaRanking();
    }

    /**
     * Guarda el sistema de ranking en disco.
     */
    private void guardaRanking(){
        if(ranking != null){
            persistencia.guardarSistemaRanking();
        }
    }

    /**
     * Guarda el usuario que tiene en memoria en disco.
     */
    private void guardaUsuarioActual(){
        if(usuarioCargado != null){
            persistencia.guardar(usuarioCargado);
        }
    }

    /**
     * Guarda en disco la partida actual.
     */
    public void guardaPartidaActual(){
        if(partidaActual != null){
            usuarioCargado.guardaPartidaActual();
            persistencia.guardar(partidaActual);
            guardaUsuarioActual();
        }
        partidaActual = null;

    }

    /**
     * Método de consulta del tablero de la partida actual.
     * @return "Matriz de integer donde la segunda lista interna hay dos listas correspondientes 1 al intendo de la fila y 2 a la respuesta.
     */
    public List<List<List<Integer>>> getTablero(){
        return partidaActual.getTablero();
    }

    /**
     * Devuelve el codigo secreto.
     * @return Una lista equivalente al Codigo establecido como codigo secreto.
     */
    public List<Integer> getCodigoSecreto(){
       return partidaActual.getCodigoSecreto().codigo;
    }

    /**
     * Devuelve una pista de nivel 3 que da un color en una posición.
     * Solo se puede pedir una vez por partida. Si se accede una segunda vez, lanza una excepción.
     * @return Un Lista con una unica posición no vacia que indica el color y la posición de uno de los colores del
     * @throws ExcepcionPistaUsada si se accede a la función por segunda vez.
     * codigo secreto
     */
    public List<Integer> getPista3() throws ExcepcionPistaUsada{
        return partidaActual.getPista3();
    }

    /**
     * Devuelve una pista de nivel 2 que da los colores que no se encuentran en el código secreto.
     * Solo se puede pedir una vez por partida. Si se accede una segunda vez, lanza una excepción.
     * @return Devuelve los colores que no estan en el codigo secreto.
     * @throws ExcepcionPistaUsada si se accede a la función por segunda vez.
     * @throws ExcepcionNoHayColoresSinUsar si no hay ningún color que no esté en el código secreto.
     */
    public ArrayList<Integer> getPista2() throws ExcepcionPistaUsada,ExcepcionNoHayColoresSinUsar{
        return partidaActual.getPista2();
    }

    /**
     * Devuelve una pista de nivel 1 que da un color que no se encuentra en el código secreto.
     * Solo se puede pedir una vez por partida.
     * @return Devuelve un color que no se encuentra en el código secreto.
     * @throws ExcepcionNoHayColoresSinUsar si no hay ningún color que no esté en el código secreto.
     * @throws ExcepcionPistaUsada si se accede a la función por segunda vez.
     */
    public Integer getPista1() throws ExcepcionPistaUsada, ExcepcionNoHayColoresSinUsar{
        return partidaActual.getPista1();
    }

    /**
     * Da el nombre del usuario cargado actualmente
     * @return usuarioCargado.getNombre()
     */
    public String getUsuario(){
        return usuarioCargado.getNombre();
    }

    /**
     * Devuelve la lista de todos los usuarios guardados en disco.
     * @return persistencia.getTodosUsuarios()
     */
    public List<String> getTodosUsuarios() {
        return persistencia.getTodosUsuarios();
    }

    /**
     * Crea una punetuación a partir de un usuario, fecha y dificultad
     * @param usuario
     * @param puntuacion
     * @param fecha
     * @param dificultad
     */
    public void creaPuntuacion(String usuario, int puntuacion, String fecha, String dificultad) {
        ranking.addNewPuntuation(usuario, puntuacion, fecha, dificultad);
    }

    /**
     * Determina si existe algún usuario guardado en disco.
     * @return
     */
    public boolean existeAlgunUsuario(){
        return persistencia.existeAlgunUsuario();
    }

    /**
     * borra el usuario cargado actualmente, sus partidas y rankings.
     */
    public void borrarUsuario() {
        borrarPartidasGuardadas();
        borrarRankings();
        persistencia.eliminarUsuario(usuarioCargado.getNombre());
        usuarioCargado = null;
    }

    /**
     * borra las partidas guardadas del usuario cargado en memoria.
     */
    public void borrarPartidasGuardadas() {
        List<String> partidas = getPartidasGuardadasUsr();
        for (String partida : partidas) {
            persistencia.eliminarPartida(partida);
        }
        usuarioCargado.borrarPartidasGuardadas();
        persistencia.guardar(usuarioCargado);
    }

    /**
     * borra los rankings del usuario cargado en memoria.
     */
    public void borrarRankings() {
        List<Info> rankingFacil = ranking.getRankingFacilInfo(usuarioCargado.getNombre());
        ranking.eliminaRankingFacil(rankingFacil);
        List<Info> rankingMedio = ranking.getRankingMedioInfo(usuarioCargado.getNombre());
        ranking.eliminaRankingMedio(rankingMedio);
        List<Info> rankingDificil = ranking.getRankingDificilInfo(usuarioCargado.getNombre());
        ranking.eliminaRankingDificil(rankingDificil);
        persistencia.guardarSistemaRanking();
    }

    /**
     * devuelve si una partida está ganada o no
     * @return partidaActual.isGanado()
     */
    public boolean isPartidaGanada(){
        return partidaActual.isGanado();
    }
}
