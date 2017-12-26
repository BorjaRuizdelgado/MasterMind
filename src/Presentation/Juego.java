package Presentation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Juego {
    private JPanel panel2;
    private JPanel lateralA;
    private JPanel lateralB;
    private JPanel bg;
    private JPanel rows;
    private JPanel header;
    private JPanel tablew;
    private JPanel colors;
    private JPanel responses;
    private DefaultTableModel tableModel;

    public JPanel getPanel() {
        return panel2;
    }

    private void makeTable(){

        tablew.setLayout(new GridLayout(12, 4));
        for (int i = 0; i < 48; i++) {
            JButton jButton = new JButton(String.valueOf(i+1));
            tablew.add(jButton);
        }

        colors.setLayout(new GridLayout(1, 4));
        for (int i = 0; i < 4; i++) {
            JButton jButton = new JButton(String.valueOf(i+1));
            colors.add(jButton);
        }

        responses.setLayout(new GridLayout(12, 1));
        for (int i = 0; i < 12; i++) {
            GridLayout gridLayout = new GridLayout(2, 2);
            JPanel j = new JPanel();
            j.setLayout(gridLayout);

            for (int k = 0; k < 4; k++) {
                JButton jButton = new JButton(String.valueOf(i+1));
                j.add(jButton);
            }

            responses.add(j);
        }
    }

    public Juego() {
        makeTable();
    }
}
