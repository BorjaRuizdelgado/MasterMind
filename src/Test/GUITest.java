package Test;

import Domain.Controllers.ControladorDominio;
import Domain.Excepciones.ExcepcionUsuarioExiste;
import Presentation.Juego;
import Presentation.Principal;
import Presentation.PruebaIsa;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class GUITest {
    private static JFrame frame;
    private static void createForm(String type){
        frame = new JFrame(type);

        switch (type){
            case "Principal":
                frame.setContentPane(new Principal().getPanel());
                break;
            case "Juego":
                frame.setContentPane(new Juego().getPanel());
                break;
        }

        frame.setPreferredSize(new Dimension(850, 900));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //frame.setUndecorated(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String args[]) throws ExcepcionUsuarioExiste {
        Scanner in = new Scanner(System.in);
        System.out.println("Select option: (1 -> principal), (2 -> juego)");
        int opt = in.nextInt();

        if (opt == 1)
            createForm("Principal");
        else if (opt == 2) {
            ControladorDominio controlador = ControladorDominio.getInstance();
            //controlador.crearUsuario("Omar");

            createForm("Juego");
        }
    }
}
