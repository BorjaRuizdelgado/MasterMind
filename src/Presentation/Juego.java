package Presentation;

import javax.swing.*;
import java.awt.*;

public class Juego {
    private JPanel panel2;
    private JPanel lateralA;
    private JPanel lateralB;
    private JPanel bg;
    private JPanel rows;
    private JPanel header;
    private JPanel row;
    private JButton button1;

    public JPanel getPanel() {
        return panel2;
    }

    private void makeTable(){
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        //gridBagConstraints.gridx = 1;

        rows.add(new JPanel().add(new JButton("ASD")), gridBagConstraints);
    }

    public Juego() {
        makeTable();
    }
}
