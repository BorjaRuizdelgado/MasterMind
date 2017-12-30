package Test;

import Data.GestionPartida;
import Domain.Partida;

public class GestionPartidaTest {
    public static void main(String[] args){
        GestionPartida gp = GestionPartida.getInstance();

        Partida partidaTestA = new Partida(true, "Facil");
        System.out.println(partidaTestA.getId());

        gp.guardar(partidaTestA);

        Partida partidaTestB = gp.cargar(partidaTestA.getId());
        System.out.println(partidaTestB.getId());

        gp.guardar(partidaTestA);


    }

}
