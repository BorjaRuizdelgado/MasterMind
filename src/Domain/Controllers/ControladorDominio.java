package Domain.Controllers;

import Data.ControladorPersistencia;
import Domain.*;
import Domain.Excepciones.ExcepcionNoHayPartidaActual;
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

    private Usuario usuarioCargado = null;
    private Partida partidaActual = null;
    private SistemaRanking ranking = null;
    private ControladorPersistencia persistencia = null;

    /**
     * Creadora ControladorDominio.
     */
    public ControladorDominio(){
        try{
            ranking = persitencia.cargarSistemaRanking();
        }
        catch(){
            ranking = SistemaRanking.getInstance();

        }
        persistencia = new ControladorPersistencia().getInstance();
    }

    /**
     * Crea un nuevo usuario.
     * @param nombre Nombre del nuevo usuario.
     * @throws Exception si el usuario ya está credo.
     */
    public void crearUsuario(String nombre) throws Exception{
        if(persistencia.existeUsuario(nombre)){
            throw new Exception();
        }
        guardaUsuarioActual();

        usuarioCargado = new Usuario(nombre);

    }

    /**
     * Carga un usuario en memoria. Si ya habia uno cargado manda guardarlo y todas sus partidas.
     * @param nombre carga el usuario con id = nombre.
     * @throws Exception si el usuario con ese identificador no existe.
     */
    public void cargarUsuario(String nombre) throws Exception{
        guardaUsuarioActual();

        try{
            usuarioCargado = persitencia.cargarUsuario(nombre);
        }
        catch(){

        }
    }

    /**
     * Crea una partida de tipo usuario CodeMaker con una dificultad determinada y un codgigo secreto.
     * @param dificultad Determina el grado de dificualtad de la partidad.
     * @param codigoSecreto Determina el codigo secreto elegido por el usuario.
     */
    public void crearPartidaUsuarioCargadoRolMaker(String dificultad, ArrayList<Integer> codigoSecreto){
        guardaPartidaActual();
        usuarioCargado.creaPartidaActual(true,dificultad);
        partidaActual = usuarioCargado.getPartidaActual();
        Codigo secreto = new Codigo(codigoSecreto.size());
        secreto.codigo = new ArrayList<Integer>(codigoSecreto);
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
     * @throws Exception Si la partida con ese id no existe.
     */
    public Tuple2<Boolean,String> cargarPartidaUsuario(String idPartida) throws Exception{
        try{
            partidaActual = persistencia.cargarPartida(idPartida);
        }
        catch(){
            throw new Exception();
        }
        return new Tuple2<>(partidaActual.isRolMaker(),partidaActual.getDificultad());
    }

    /**
     * Asigna la respuesta del usuario al utlimo guess y devuelve el siguiente intento generado por la maquina.
     * @param respuesta Devuelve la respuesta pensada por la maquina
     * @return Retorna el codigo de respuesta.
     * @throws Exception Si la respuesta no es correcta.
     */
    public List<Integer> jugarCodeMaker(ArrayList<Integer> respuesta) throws Exception{
        Respuesta res = new Respuesta(respuesta.size());
        res.respuesta = new ArrayList<>(respuesta);
        partidaActual.setRespuesta(res);
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
        partidaActual.setCodigoSecreto(code);
        partidaActual.generaRespuesta();
        partidaActual.sumaTiempo(tiempoTardado);
        return partidaActual.getUltimaRespuesta().respuesta;
    }

    /**
     * Finaliza la partida que esta siendo utilizada utilizada en este momento y la borra de memoria y la desasigna al
     * usuario.
     */
    public void terminaPartidaActual(boolean ganada){
        try {
            usuarioCargado.finalizarPartidaActual(ganada);
        }
        catch (ExcepcionNoHayPartidaActual e){

        }
        partidaActual = null;
    }

    public void abandonaPartidaAcutal(){
        try {
            usuarioCargado.abandonaPartidaActual();
        }
        catch(ExcepcionNoHayPartidaActual e){

        }
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
    public List<Info> getRanking(String dificultad){
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
    public List<Info> getRankingNombreUsrDificultad(String nombreUsr, String dificultad){
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
     * @return Lista de las partidas guardadas en forma de String.
     */
    public List<String> getPartidasGuardadasUsr(){
        return null;
    }

    private void onClose(){
       guardaUsuarioActual();
       guardaRanking();
    }

    private void guardaRanking(){
        if(ranking != null){
            try {
                persistencia.guarda(ranking);
            }
            catch(){}
        }
    }
    private void guardaUsuarioActual(){

        if(usuarioCargado != null){
            try {
                persistencia.guardar(usuarioCargado);
            }
            catch(){

            }
            usuarioCargado = null;
        }
    }

    private void guardaPartidaActual(){
        if(partidaActual == null){
            usuarioCargado.guardaPartidaActual();
        }
        persistencia.guardar(partidaActual);
    }

}
