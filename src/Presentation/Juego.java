package Presentation;

import javax.swing.*;
import java.awt.*;

public class Juego {
    private JPanel panel2;
    private JPanel lateralA;
    private JPanel lateralB;
    private JPanel bg;
    private JPanel rows;
    private JPanel row;
    private JPanel header;

    public JPanel getPanel() {
        return panel2;
    }

    private void makeTable(){
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        for (int i = 0; i < 5; i++) {
            /*JPanel copied = new JPanel();
            int size = row.getComponentCount();
            for (int j = 0; j < size; j++) {
                copied.add(row.getComponent(i));
            }*/

            //rows.add(copied);

            rows.add(new JButton(String.valueOf(i)), gridBagConstraints);

            //rows.validate();
        }
    }

    public Juego() {
        makeTable();
    }
}
