package Presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
    private JButton verClasificacionButton;
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
    private JButton ACEPTARButton;


    private ArrayList<JButton> colorsButtons;
    private ArrayList<JButton> solutionButtons;
    private ArrayList<ArrayList<JButton>> tableButtons;
    private ArrayList<ArrayList<JButton>> answerButtons;

    private int color;

    /*
        VACIO = 0;
        ROJO = 1;
        VERDE = 2;
        AZUL = 3;
        AMARILLO = 4;
        NARANJA = 5;
        MORADO = 6;
        BLANCO = 7;
        NEGRO = 8;
    */

    public JPanel getPanel() {
        return panel2;
    }

    private void setDesign(JButton jButton){
        jButton.setBackground(new Color(230, 230, 230));
        jButton.setFocusPainted(false);
    }

    private void setColor(JButton button, int color){
        if (color == 1)
            button.setBackground(new Color(255, 0, 0));
        else if(color == 2)
            button.setBackground(new Color(0, 191, 28));
        else if(color == 3)
            button.setBackground(new Color(23, 41, 224));
        else if(color == 4)
            button.setBackground(new Color(224, 222, 39));
        else if(color == 5)
            button.setBackground(new Color(236, 151, 48));
        else if(color == 6)
            button.setBackground(new Color(153, 44, 226));
        else if(color == 7)
            button.setBackground(new Color(255, 255, 255));
        else if(color == 8)
            button.setBackground(new Color(0, 0, 0));
    }

    private void createMainTable(){
        int row = 0;
        tableButtons.add(new ArrayList<>());
        int column = 0;

        tablew.setLayout(new GridLayout(4, 12));
        for (int i = 0; i < 48; i++) {
            JButton jButton = new JButton();
            setDesign(jButton);
            tablew.add(jButton);

            if(column == 4){
                row++;
                tableButtons.add(new ArrayList<>());
                column = 0;
            }
            else {
                column++;
            }
            tableButtons.get(row).add(jButton);
        }
    }

    private void createSolutionRow(){
        solution.setLayout(new GridLayout(1, 4));
        for (int i = 0; i < 4; i++) {
            JButton jButton = new JButton();
            setDesign(jButton);
            solution.add(jButton);

            solutionButtons.add(jButton);
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
            answerButtons.add(new ArrayList<>()); // <-
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

                answerButtons.get(answerButtons.size()-1).add(jButton3);
            }
            //
            responses.add(j);
        }
    }

    private void makeTable(){
        solutionButtons = new ArrayList<>();
        tableButtons = new ArrayList<>();
        answerButtons = new ArrayList<>();

        createMainTable();
        createSolutionRow();
        createAnswersSquare();

        setFunctionColors();
        setFunctionSolution();
        setFunctionTable();
        setFunctionsAnswers();
    }

    private void setFunctionsAnswers(){
        for (int i = 0; i < answerButtons.size(); i++) {
            for (int j = 0; j < answerButtons.get(i).size(); j++) {
                int finalI = i;
                int finalJ = j;
                answerButtons.get(i).get(j).addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent mouseEvent) {
                        super.mouseClicked(mouseEvent);

                        setColor(answerButtons.get(finalI).get(finalJ), color);
                    }
                });
            }
        }
    }

    private void setFunctionTable(){
        for (int i = 0; i < tableButtons.size(); i++) {
            for (int j = 0; j < tableButtons.get(i).size(); j++) {
                int finalI = i;
                int finalJ = j;
                tableButtons.get(i).get(j).addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent mouseEvent) {
                        super.mouseClicked(mouseEvent);

                        setColor(tableButtons.get(finalI).get(finalJ), color);
                    }
                });
            }
        }
    }

    private void setFunctionSolution(){
        for (int i = 0; i < solutionButtons.size(); i++) {
            int finalI = i;
            solutionButtons.get(i).addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent mouseEvent) {
                    super.mouseClicked(mouseEvent);

                    setColor(solutionButtons.get(finalI), color);
                }
            });
        }
    }

    private void setFunctionColors(){
        colorsButtons = new ArrayList<>();
        colorsButtons.add(button1);
        colorsButtons.add(button2);
        colorsButtons.add(button3);
        colorsButtons.add(button4);
        colorsButtons.add(button5);
        colorsButtons.add(button6);
        colorsButtons.add(button7);
        colorsButtons.add(button8);

        for (int i = 0; i < colorsButtons.size(); i++) {
            int finalI = i;
            colorsButtons.get(i).addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent mouseEvent) {
                    super.mouseClicked(mouseEvent);
                    color = finalI + 1;
                }
            });
        }
    }

    private void applyButtonsEffects(){
        ArrayList<JButton> aux = new ArrayList<>();
        aux.add(verClasificacionButton);
        aux.add(abandonarPartidaButton);
        aux.add(reiniciarPartidaButton);
        aux.add(ayudaButton);
        aux.add(guardarYSalirButton);

        for (JButton button: aux) {
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent mouseEvent) {
                    super.mouseEntered(mouseEvent);
                    button.setBackground(Color.BLACK);
                }
            });

            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseExited(MouseEvent mouseEvent) {
                    super.mouseExited(mouseEvent);
                    button.setBackground(new Color(192, 55, 55));
                }
            });
        }
    }

    public Juego() {
        makeTable();
        applyButtonsEffects();
    }

}
