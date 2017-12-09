package Test;

import Presentation.Principal;
import Presentation.PruebaIsa;
import Presentation.Usuario;

import javax.swing.*;
import java.awt.*;

public class GUITest {
    private static JFrame frame;
    private static void createForm(String type){
        frame = new JFrame(type);
        switch (type){
            case "Principal":
                frame.setContentPane(new Principal().getPanel());
                break;
            case "PruebaIsa":
                frame.setContentPane(new PruebaIsa().getPanel());
                break;
            case "Usuario":
                frame.setContentPane(new Usuario().getPanel());
                break;
        }
        frame.setPreferredSize(new Dimension(850, 900));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String args[]){

        createForm("Principal");
        //createForm("Usuario");
    }
}
