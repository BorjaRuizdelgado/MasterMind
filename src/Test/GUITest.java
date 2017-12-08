package Test;

import Presentation.Inicio;
import Presentation.PruebaIsa;

import javax.swing.*;

public class GUITest {

    private static void createForm(String type){
        JFrame frame = new JFrame(type);
        switch (type){
            case "Inicio":
                frame.setContentPane(new Inicio().getPanel());
                break;
            case "PruebaIsa":
                frame.setContentPane(new PruebaIsa().getPanel());
                break;
        }
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String args[]){

        createForm("Inicio");
        //createForm("PruebaIsa");
    }
}
