package Test;

import Domain.Controllers.ControladorDominio;
import Domain.Excepciones.ExcepcionUsuarioExiste;
import Domain.Excepciones.ExcepcionUsuarioInexistente;
import Domain.Tablero;
import Presentation.Juego;
import Presentation.Perfil;
import Presentation.Principal;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.Scanner;

public class GUITest {
    private static JFrame frame;
    private static void createForm(String type){
        frame = new JFrame(type);

        switch (type){
            case "Principal":
                frame.setContentPane(new Principal(frame).getPanel());
                break;
            case "Juego":
                frame.setContentPane(new Juego("Facil", true, false,null,null,null).getPanel());
                break;
            case "Stats":
                frame.setContentPane(new Perfil().getPanel());
                frame.setPreferredSize(new Dimension(550, 600));
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                break;

        }

        frame.setPreferredSize(new Dimension(850, 900));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
            controlador.crearUsuario(String.valueOf(new Random().nextInt()));

            createForm("Juego");
        }
        else if(opt == 3){
            ControladorDominio ctrl = ControladorDominio.getInstance();
            try {
                ctrl.cargarUsuario("1");
            } catch (ExcepcionUsuarioInexistente excepcionUsuarioInexistente) {
                excepcionUsuarioInexistente.printStackTrace();
            }
            createForm("Stats");
        }
    }
}
