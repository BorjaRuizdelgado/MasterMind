package Presentation;

import Domain.Controllers.ControladorDominio;
import Domain.Excepciones.ExcepcionRespuestaIncorrecta;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

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

    private int numColors = 6;
    private int numColumns = 4;
    private String dificultad = "Facil";
    private int color;

    private boolean codeMaker = true;
    private boolean firstAttempt = true;
    private int actualRow = 0;

    private static Color defaultColor = new Color(230, 230 , 230);
    private static Color rojo = new Color(255, 0 , 0);
    private static Color verde = new Color(0, 191 , 28);
    private static Color azul = new Color(23, 41 , 224);
    private static Color amarillo = new Color(224, 222 , 39);
    private static Color naranja = new Color(236, 151 , 48);
    private static Color morado = new Color(153, 44 , 226);
    private static Color blanco = new Color(255, 255 , 255);
    private static Color negro = new Color(0, 0 , 0);

    private Map<Integer, Color> colorMap;

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
        jButton.setBackground(defaultColor);
        jButton.setFocusPainted(false);
    }

    private void setColor(JButton button, int color){
        button.setBackground(colorMap.get(color));
    }

    private void createMainTable(){
        int row = 0;
        tableButtons.add(new ArrayList<>());
        int column = -1;

        tablew.setLayout(new GridLayout(12, 4));
        for (int i = 0; i < 48; i++) {
            JButton jButton = new JButton();
            setDesign(jButton);
            tablew.add(jButton);

            if(column == 3){
                row++;
                tableButtons.add(new ArrayList<>());
                column = 0;
            }
            else {
                column++;
            }
            //System.out.println("Row: " + String.valueOf(row) + " Col: " + String.valueOf(column));
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
                        if (mouseEvent.getButton() != MouseEvent.BUTTON3)
                            setColor(answerButtons.get(finalI).get(finalJ), color);
                        else {
                            setColor(answerButtons.get(finalI).get(finalJ), 0);
                        }

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

                        if (mouseEvent.getButton() != MouseEvent.BUTTON3)
                            setColor(tableButtons.get(finalI).get(finalJ), color);
                        else
                            setColor(tableButtons.get(finalI).get(finalJ), 0);
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

                    if (mouseEvent.getButton() != MouseEvent.BUTTON3)
                        setColor(solutionButtons.get(finalI), color);
                    else
                        setColor(solutionButtons.get(finalI), 0);
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
        aux.add(ACEPTARButton);

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

    private int getFilledButtonsSize(ArrayList<JButton> buttons){
        int count = 0;
        for (JButton button: buttons){
            if (button.getBackground().equals(defaultColor))
                count++;
        }
        return count;
    }

    private int fromColorToInt(Color color){
        if (color.equals(rojo))
            return 1;
        else if(color.equals(verde))
            return 2;
        else if(color.equals(azul))
            return 3;
        else if(color.equals(amarillo))
            return 4;
        else if(color.equals(naranja))
            return 5;
        else if(color.equals(morado))
            return 6;
        else if(color.equals(blanco))
            return 7;
        else if(color.equals(negro))
            return 8;
        else return 0;
    }

    private ArrayList<Integer> fromJButtonListToIntList(ArrayList<JButton> Buttons){
        ArrayList<Integer> Return = new ArrayList<>();
        for (JButton button:Buttons) {
            Return.add(fromColorToInt(button.getBackground()));
        }
        return Return;
    }

    private void fillRow(int row, List<Integer> list){
        System.out.println(list.size());
        System.out.println(tableButtons.get(row).size());
        int j = 0;
        for (JButton button: tableButtons.get(row)) {
            button.setBackground(colorMap.get(list.get(j)));
            j++;
        }
    }

    private void initMap(){
        colorMap = new HashMap<>();
        colorMap.put(0, defaultColor);
        colorMap.put(1, rojo);
        colorMap.put(2, verde);
        colorMap.put(3, azul);
        colorMap.put(4, amarillo);
        colorMap.put(5, naranja);
        colorMap.put(6, morado);
        colorMap.put(7, blanco);
        colorMap.put(8, negro);
    }

    public Juego() {
        initMap();
        ControladorDominio controller = ControladorDominio.getInstance();
        makeTable();
        applyButtonsEffects();

        ACEPTARButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                super.mousePressed(mouseEvent);
                if (codeMaker && getFilledButtonsSize(solutionButtons) == 0 && firstAttempt){
                    controller.crearPartidaUsuarioCargadoRolMaker(dificultad, fromJButtonListToIntList(solutionButtons));
                    firstAttempt = false;

                    fillRow(actualRow, controller.jugarCodeMaker());
                }
                else if (codeMaker && !firstAttempt){
                    System.out.println("Pole:" + fromJButtonListToIntList(answerButtons.get(actualRow)));
                    try {
                        actualRow++;
                        fillRow(actualRow, controller.jugarCodeMaker(fromJButtonListToIntList(answerButtons.get(actualRow-1))));
                    }
                    catch (ExcepcionRespuestaIncorrecta excepcionRespuestaIncorrecta) {
                        System.out.println("ERROR");
                    }
                }
            }
        });
    }

}
