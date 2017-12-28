package Presentation;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class Juego {
    private JPanel panel2;
    private JPanel lateralA;
    private JPanel lateralB;
    private JPanel bg;
    private JPanel rows;
    private JPanel header;
    private JPanel tablew;
    private JPanel solution;
    private JPanel responses;
    private JButton verClasificaciónButton;
    private JButton abandonarPartidaButton;
    private JButton reiniciarPartidaButton;
    private JButton ayudaButton;
    private JButton guardarYSalirButton;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private DefaultTableModel tableModel;

    public JPanel getPanel() {
        return panel2;
    }

    private void setDesign(JButton jButton){
        jButton.setBackground(Color.white);
    }

    private void createMainTable(){
        tablew.setLayout(new GridLayout(12, 4));
        for (int i = 0; i < 48; i++) {
            JButton jButton = new JButton();
            setDesign(jButton);
            tablew.add(jButton);
        }
    }

    private void createColorsRow(){
        solution.setLayout(new GridLayout(1, 4));
        for (int i = 0; i < 4; i++) {
            JButton jButton = new JButton();
            setDesign(jButton);
            solution.add(jButton);
        }

        /* With GridBagLayout
        GridBagLayout gridBagLayout = new GridBagLayout();
        solution.setLayout(gridBagLayout);
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridy = 0;
        constraints.gridx = 0;
        constraints.weightx = 1.0;
        constraints.weighty = 0.5;
        constraints.fill = GridBagConstraints.BOTH;
        JButton jButton1 = new JButton();
        solution.add(jButton1, constraints);

        constraints.gridx = 1;
        JButton jButton2 = new JButton();
        solution.add(jButton2, constraints);

        constraints.gridx =2;
        JButton jButton3 = new JButton();
        solution.add(jButton3, constraints);

        constraints.gridx = 3;
        JButton jButton4 = new JButton();
        solution.add(jButton4, constraints);
        */
    }

    private void createAnswersSquare(){
        responses.setLayout(new GridLayout(12, 1));
        for (int i = 0; i < 12; i++) {
            JPanel j = new JPanel();
            j.setOpaque(false);
            j.setLayout(new GridBagLayout());
            //
            GridBagConstraints constraints = new GridBagConstraints();

            /*constraints.gridy = 0;
            constraints.gridx = 0;
            constraints.weighty = 1.0;
            constraints.fill = GridBagConstraints.VERTICAL;
            JButton jButton1 = new JButton(" ");
            j.add(jButton1, constraints);

            constraints.gridx = 1;
            JButton jButton2 = new JButton(" ");
            j.add(jButton2, constraints);

            constraints.gridx = 0;
            constraints.gridy = 1;
            JButton jButton3 = new JButton(" ");
            j.add(jButton3, constraints);

            constraints.gridx = 1;
            JButton jButton4 = new JButton(" ");
            j.add(jButton4, constraints);*/

            constraints.weighty = 1.0;
            constraints.fill = GridBagConstraints.VERTICAL;
            int x = 0;
            int y = 0;
            int size = 4;
            for (int k = 0; k < size; k++) {
                constraints.gridx = x;
                constraints.gridy = y;

                JButton jButton3 = new JButton(" ");
                j.add(jButton3, constraints);
                setDesign(jButton3);

                if (x == (size/2 - 1)) {
                    x = 0;
                    y++;
                }
                else x++;
            }

            //
            responses.add(j);
        }
    }

    private void makeTable(){
        createMainTable();
        createColorsRow();
        createAnswersSquare();
        setBordersOnColors();
    }

    private void setBordersOnColors(){
        ArrayList<JButton> list = new ArrayList<>();
        list.add(button1);
        list.add(button2);
        list.add(button3);
        list.add(button4);
        list.add(button5);
        list.add(button6);
        list.add(button7);
        list.add(button8);

        for (JButton button: list) {
            //button.setBorder(new LineBorder(Color.BLACK, 0));
        }
    }

    public Juego() {
        makeTable();
    }

    /*
    private void createAnswersSquare(){
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
     */
}
