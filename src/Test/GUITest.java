package Test;

import Presentation.PantallaIni;

import javax.swing.*;

public class GUITest {

    private static void createForm(String type){
        JFrame frame = null;

        switch (type){
            case "PantallaIni":
                frame = new JFrame("PantallaIni");
                frame.setContentPane(new PantallaIni().getPanel());
                break;
        }
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String args[]){

        createForm("PantallaIni");
    }
}
