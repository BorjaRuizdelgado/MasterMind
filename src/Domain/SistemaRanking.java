package Domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase SistemaRanking
 * Singleton que contiene tres listas que corresponden a los diferentes ránkings según dificultad.
 * @author Omar
 */
public class SistemaRanking {

    private static SistemaRanking uniqueInstance;
    private List<Info> rankingFacil;
    private List<Info> rankingNormal;
    private List<Info> rankingDificil;

    /**
     * Inicializa el singleton, obviamente el método es privado
     */
    private SistemaRanking() {
        rankingFacil = new ArrayList<>();
        rankingNormal = new ArrayList<>();
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
     * Devuelve la intersección entre la lista que contiene datos del usuario
     * @param lista Es la lista de donde saldrán las entradas
     * @param nombre El nombre del usuario
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
    public List<Info> getRankingFacil(){
        return rankingFacil;
    }

    /**
     * Devuelve listaRankingNormal
     * @return rankingNormal
     */
    public List<Info> getRankingNormal(){
        return rankingNormal;
    }

    /**
     * Devuelve listaRankingDificil
     * @return rankingDificil
     */
    public List<Info> getRankingDificil(){
        return rankingDificil;
    }

    /**
     * Devuelve una lista con las puntuaciones del nombre pasado por parámetro
     * @param nombre Es el nombre del usuario
     * @return rankingFacil con entradas del usuario.
     */
    public List<Info> getRankingFacil(String nombre){
        return getInterseccionNombre(rankingFacil, nombre);
    }

    /**
     * Devuelve una lista con las puntuaciones del nombre pasado por parámetro
     * @param nombre Es el nombre del usuario
     * @return rankingNormal con entradas del usuario.
     */
    public List<Info> getRankingNormal(String nombre){
        return getInterseccionNombre(rankingNormal, nombre);
    }

    /**
     * Devuelve una lista con las puntuaciones del nombre pasado por parámetro
     * @param nombre Es el nombre del usuario
     * @return rankingDificil con entradas del usuario.
     */
    public List<Info> getRankingDificil(String nombre){
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
    public void setRankingNormal(List<Info> ranking){
        rankingNormal = new ArrayList<>(ranking);
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
     * @param information Contiene la información de la partida. Es la info. que se añadirá a uno de los rankings.
     * @param dificultad Especifica en que ranking se añadirá la información 'information'
     */
    public void addNewPuntuation(Info information, String dificultad){
        switch (dificultad){
            case "Facil":
                rankingFacil.add(information);
                break;
            case "Medio":
                rankingNormal.add(information);
                break;
            case "Dificil":
                rankingDificil.add(information);
                break;
            default:
                break;
        }
        //todo se debe pasar por parámetro info básica, y crear aqui el Info
    }
}
