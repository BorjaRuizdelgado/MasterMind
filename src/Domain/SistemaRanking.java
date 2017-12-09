package Domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase SistemaRanking
 * Singleton que contiene tres listas que corresponden a los diferentes ránkings según dificultad.
 * @author Omar
 */
public class SistemaRanking implements Serializable{
    private static SistemaRanking uniqueInstance;
    private List<Info> rankingFacil;
    private List<Info> rankingMedio;
    private List<Info> rankingDificil;

    /**
     * Inicializa el singleton, obviamente el método es privado
     */
    private SistemaRanking() {
        rankingFacil = new ArrayList<>();
        rankingMedio = new ArrayList<>();
        rankingDificil = new ArrayList<>();
    }

    /**
     * Si la instancia no existe, se inicializa con su método privado
     * @return La única instancia de la clase
     */
    public static SistemaRanking getInstance() {
        if(uniqueInstance == null)
            uniqueInstance = new SistemaRanking();
        return uniqueInstance;
    }

    /**
     * Reinicializa la única instáncia de SistemRanking
     * @param sistemaRanking instáncia que será la única en SistemaRanking
     */
    public static void setInstance(SistemaRanking sistemaRanking){
        uniqueInstance.rankingFacil = sistemaRanking.rankingFacil;
        uniqueInstance.rankingMedio = sistemaRanking.rankingMedio;
        uniqueInstance.rankingDificil = sistemaRanking.rankingDificil;
    }

    /**
     * Devuelve la intersección entre la lista que contiene datos del usuario
     * @param lista Es la lista de donde saldrán las entradas
     * @param nombre Es el nombre de usuario.
     * @return Lista con solo información del usuario.
     */
    private List<Info> getInterseccionNombre(List<Info> lista, String nombre){
        List<Info> result = new ArrayList<>();
        for (Info info : lista) {
            if(info.getUsuario().equals(nombre))
                result.add(info);
        }
        return result;
    }

    /**
     * Devuelve listaRankingFacil
     * @return rankingFacil
     */
    public List<Info> getRankingFacilInfo(){
        return rankingFacil;
    }

    /**
     * Devuelve listaRankingNormal
     * @return rankingMedio
     */
    public List<Info> getRankingMedioInfo(){
        return rankingMedio;
    }

    /**
     * Devuelve listaRankingDificil
     * @return rankingDificil
     */
    public List<Info> getRankingDificilInfo(){
        return rankingDificil;
    }

    /**
     * Devuelve una lista con las puntuaciones del nombre pasado por parámetro
     * @param nombre Es el nombre del usuario
     * @return rankingFacil con entradas del usuario.
     */
    public List<Info> getRankingFacilInfo(String nombre){
        return getInterseccionNombre(rankingFacil, nombre);
    }

    /**
     * Devuelve una lista con las puntuaciones del nombre pasado por parámetro
     * @param nombre Es el nombre del usuario
     * @return rankingMedio con entradas del usuario.
     */
    public List<Info> getRankingMedioInfo(String nombre){
        return getInterseccionNombre(rankingMedio, nombre);
    }

    /**
     * Devuelve una lista con las puntuaciones del nombre pasado por parámetro
     * @param nombre Es el nombre del usuario
     * @return rankingDificil con entradas del usuario.
     */
    public List<Info> getRankingDificilInfo(String nombre){
        return getInterseccionNombre(rankingDificil, nombre);
    }


    /**
     * Actualiza listaRankingFacil
     * @param ranking Nuevo ranking fácil
     */
    public void setRankingFacil(List<Info> ranking){
        rankingFacil = new ArrayList<>(ranking);
    }

    /**
     * Actualiza listaRankingNormal
     * @param ranking Nuevo ranking normal
     */
    public void setRankingMedio(List<Info> ranking){
        rankingMedio = new ArrayList<>(ranking);
    }

    /**
     * Actualiza listaRankingDificil
     * @param ranking Nuevo ranking díficil
     */
    public void setRankingDificil(List<Info> ranking){
        rankingDificil = new ArrayList<>(ranking);
    }

    /**
     * Añade la información de una nueva partida a una lista en concreto especificada en el parámetro 'dificultad'
     * @param usuario nombre del usuario
     * @param puntuacion puntuación de la partida
     * @param fecha fecha de la partida
     * @param dificultad Especifica en que ranking se añadirá la información 'information'
     */
    public void addNewPuntuation(String usuario, int puntuacion, String fecha, String dificultad){
        Info information = new Info(usuario, puntuacion, fecha);
        switch (dificultad){
            case "Facil":
                rankingFacil.add(information);
                break;
            case "Medio":
                rankingMedio.add(information);
                break;
            case "Dificil":
                rankingDificil.add(information);
                break;
            default:
                break;
        }
    }

    /**
     * Reiniciamos los rankings.
     */
    public void clear(){
        rankingFacil.clear();
        rankingMedio.clear();
        rankingDificil.clear();
    }

    /**
     * Pasamos de una lista de tipo Info a una lista de String.
     * @param list La lista a convertir
     * @return Lista convertida
     */
    private List<String> fromInfoListToStringList(List<Info> list){
        List<String> Return = new ArrayList<>();
        for (Info info: list) {
            Return.add(info.getUsuario() + " " + info.getFecha() + " " + info.getPuntuacion());
        }
        return Return;
    }

    /**
     * Devuelve una lista con las puntuaciones del nombre pasado por parámetro
     * @param nombre Es el nombre del usuario
     * @return rankingMedio con entradas del usuario en una lista de String.
     */
    public List<String> getRankingFacil(String nombre){
        return fromInfoListToStringList(getInterseccionNombre(rankingFacil, nombre));
    }

    /**
     * Devuelve una lista con las puntuaciones del nombre pasado por parámetro
     * @param nombre Es el nombre del usuario
     * @return rankingMedio con entradas del usuario en una lista de String.
     */
    public List<String> getRankingMedio(String nombre){
        return fromInfoListToStringList(getInterseccionNombre(rankingMedio, nombre));
    }

    /**
     * Devuelve una lista con las puntuaciones del nombre pasado por parámetro
     * @param nombre Es el nombre del usuario
     * @return rankingDificil con entradas del usuario en una lista de String.
     */
    public List<String> getRankingDificil(String nombre){
        return fromInfoListToStringList(getInterseccionNombre(rankingDificil, nombre));
    }

    /**
     * Devuelve listaRankingFacil
     * @return rankingFacil en una lista de String
     */
    public List<String> getRankingFacil(){
        return fromInfoListToStringList(rankingFacil);
    }

    /**
     * Devuelve listaRankingNormal
     * @return rankingMedio en una lista de String
     */
    public List<String> getRankingMedio(){
        return fromInfoListToStringList(rankingMedio);
    }

    /**
     * Devuelve listaRankingDificil
     * @return rankingDificil en una lista de String
     */
    public List<String> getRankingDificil(){
        return fromInfoListToStringList(rankingDificil);
    }
}
