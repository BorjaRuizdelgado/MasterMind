package Test;

import Data.GestionUsuario;
import Domain.Excepciones.ExcepcionUsuarioInexistente;
import Domain.Usuario;

public class GestionUsuarioTest {
    public static void main(String[] args){
        Usuario usuarioTestA = new Usuario("TestB");
        GestionUsuario gu = GestionUsuario.getInstance();


        try {
            gu.cargar("TestA");
        } catch (ExcepcionUsuarioInexistente e) {
            System.out.println("Usuario no existe.");
        }

        gu.guardar(usuarioTestA);



        try {
            Usuario testA = gu.cargar("TestA");
            Usuario testB = gu.cargar("TestB");
            System.out.println(testA.getNombre());
            System.out.println(testB.getNombre());
        } catch (ExcepcionUsuarioInexistente e) {
            System.out.println("Prueba con otro nombre. Este usuario no existe.");
        }





    }

}
