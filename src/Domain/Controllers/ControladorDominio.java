package Domain.Controllers;

import Data.ControladorPersistencia;
import Domain.*;
import Domain.Excepciones.ExcepcionNoHayPartidaActual;
import Domain.Excepciones.ExcepcionRespuestaIncorrecta;
import Domain.Excepciones.ExcepcionUsuarioExiste;
import Domain.Excepciones.ExcepcionUsuarioInexistente;
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
    //todo get pistas wey
    private Usuario usuarioCargado = null;
    private Partida partidaActual = null;
    private SistemaRanking ranking = null;
    private ControladorPersistencia persistencia = ControladorPersistencia.getInstance();

    /**
     * Creadora ControladorDominio.
     */
    public ControladorDominio(){
        persistencia = ControladorPersistencia.getInstance();
        persistencia.cargarSistemaRanking();
        ranking = SistemaRanking.getInstance();
    }

    /**
     * Crea y carga un nuevo usuario.
     * @param nombre Nombre del nuevo usuario.
     * @throws ExcepcionUsuarioExiste si el usuario ya está credo.
     */
    public void crearUsuario(String nombre) throws ExcepcionUsuarioExiste {
        if(persistencia.existeUsuario(nombre)){
            throw new ExcepcionUsuarioExiste();
        }
        guardaUsuarioActual();
        usuarioCargado = new Usuario(nombre);
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
        partidaActual = null;
    }

    public void abandonaPartidaAcutal(){
        usuarioCargado.abandonaPartidaActual();
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
     * @return Lista de los identificadores de las partidas guardadas.
     */
    public List<String> getPartidasGuardadasUsr(){
        return usuarioCargado.getPartidasGuardadas();
    }

    private void onClose(){
       guardaUsuarioActual();
       guardaRanking();
    }

    private void guardaRanking(){
        if(ranking != null){
            persistencia.guardarSistemaRanking();
        }
    }

    private void guardaUsuarioActual(){
        if(usuarioCargado != null){
            persistencia.guardar(usuarioCargado);
        }
    }

    public void guardaPartidaActual(){
        if(partidaActual != null){
            usuarioCargado.guardaPartidaActual();
            persistencia.guardar(partidaActual);
            guardaUsuarioActual();
        }
        partidaActual = null;

    }

    public List<List<List<Integer>>> getTablero(){
        return partidaActual.getTablero();
    }

    public List<Integer> getCodigoSecreto(){
       return partidaActual.getCodigoSecreto().codigo;
    }

}
