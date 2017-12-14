package Test;

import Presentation.Juego;
import Presentation.Principal;
import Presentation.PruebaIsa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
            case "Juego":
                frame.setContentPane(new Juego().getBg());
                break;
        }

        frame.setPreferredSize(new Dimension(850, 900));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setUndecorated(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String args[]){

        //createForm("Principal");
        createForm("Juego");
    }
}
