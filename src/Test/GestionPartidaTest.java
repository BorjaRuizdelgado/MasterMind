package Test;

import Data.GestionPartida;
import Domain.Partida;

public class GestionPartidaTest {
    public static void main(String[] args){
        GestionPartida gp = GestionPartida.getInstance();

        Partida partidaTestA = new Partida(true, "Facil");
        System.out.println(partidaTestA.getId());

        gp.guardarPartida(partidaTestA);

        Partida partidaTestB = gp.cargarPartida(partidaTestA.getId());
        System.out.println(partidaTestB.getId());

        gp.guardarPartida(partidaTestA);


    }

}