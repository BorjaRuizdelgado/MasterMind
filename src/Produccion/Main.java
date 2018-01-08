package Produccion;


import Presentation.Principal;

import javax.swing.*;
import java.awt.*;


public class Main {


    public static void main(String args[]) {
        if (args.length > 1) {
            System.out.println("Modo de uso: java -jar MasterMindo.jar [nombreJuegoPrueba]");
        }
        else {
            String juegoPrueba = "normal";
            if (args.length != 0)  juegoPrueba = args[0];
            JFrame frame = new JFrame("Principal");
            frame.setContentPane(new Principal(frame,juegoPrueba).getPanel());
            frame.setPreferredSize(new Dimension(850, 900));
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }
    }
}
