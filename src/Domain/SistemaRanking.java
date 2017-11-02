package Domain;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Omar
 */
public class SistemaRanking {
    /**
     * Atributos
     */
    private static SistemaRanking uniqueInstance;

    private List<Info> rankingFacil;
    private List<Info> rankingNormal;
    private List<Info> rankingDificil;

    /**
     * Inicializa el singleton, obviamente el método es provado
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
     * Devolvemos la intersección entre la lista que contiene datos del usuario
     * @param lista Es la lista de donde saldrán las entradas
     * @param nombre El nombre del usuario
     * @return
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
     * Devolvemos listaRankingFacil
     * @return rankingFacil
     */
    public List<Info> getRankingFacil(){
        return rankingFacil;
    }

    /**
     * Devolvemos listaRankingNormal
     * @return rankingNormal
     */
    public List<Info> getRankingNormal(){
        return rankingNormal;
    }

    /**
     * Devolvemos listaRankingDificil
     * @return rankingDificil
     */
    public List<Info> getRankingDificil(){
        return rankingDificil;
    }

    /**
     * Devolvemos una lista con las puntuacions del nombre pasado por parámetro
     * @param nombre Es el nombre del usuario
     * @return rankingFacil con entradas del usuario.
     */
    public List<Info> getRankingFacil(String nombre){
        return getInterseccionNombre(rankingFacil, nombre);
    }

    /**
     * Devolvemos una lista con las puntuacions del nombre pasado por parámetro
     * @param nombre Es el nombre del usuario
     * @return rankingNormal con entradas del usuario.
     */
    public List<Info> getRankingNormal(String nombre){
        return getInterseccionNombre(rankingNormal, nombre);
    }

    /**
     * Devolvemos una lista con las puntuacions del nombre pasado por parámetro
     * @param nombre Es el nombre del usuario
     * @return rankingDificil con entradas del usuario.
     */
    public List<Info> getRankingDificil(String nombre){
        return getInterseccionNombre(rankingDificil, nombre);
    }
}
