package Presentation;

import Domain.Controllers.ControladorDominio;
import Domain.Excepciones.ExcepcionRespuestaIncorrecta;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import java.util.List;

/**
 * Clase Juego que conecta el controlador y la interfaz gráfica
 * @author Omar
 */

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
    private JButton pistaButton;
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
    private JPanel coloresPanel;
    private JPanel narvioPanel;


    private ArrayList<JButton> solutionButtons;
    private ArrayList<ArrayList<JButton>> tableButtons;
    private ArrayList<ArrayList<JButton>> answerButtons;

    private int numColors = 4;
    private int numColumns = 4;
    private String dificultad = "Facil";
    private int color;

    private ControladorDominio controller = ControladorDominio.getInstance();
    private boolean codeMaker = false;
    private boolean firstAttempt = true;
    private int actualRow = 0;
    private boolean finished = false;

    private static Color hide = new Color(160, 160, 160);
    private static Color defaultColor = new Color(238, 238 , 238);
    private static Color rojo = new Color(255, 0 , 0);
    private static Color verde = new Color(0, 191 , 28);
    private static Color azul = new Color(23, 41 , 224);
    private static Color amarillo = new Color(224, 222 , 39);
    private static Color naranja = new Color(236, 151 , 48);
    private static Color morado = new Color(153, 44 , 226);
    private static Color blanco = new Color(255, 255 , 255);
    private static Color negro = new Color(0, 0 , 0);

    private ControladorDominio ctrl;

    private Map<Integer, Color> colorMap;

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

        tablew.setLayout(new GridLayout(12, numColumns));
        for (int i = 0; i < 12*numColumns; i++) {
            JButton jButton = new JButton();
            setDesign(jButton);
            tablew.add(jButton);

            if(column == numColumns-1){
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
        solution.setLayout(new GridLayout(1, numColumns));
        for (int i = 0; i < numColumns; i++) {
            JButton jButton = new JButton();
            setDesign(jButton);
            solution.add(jButton);

            solutionButtons.add(jButton);
        }
    }

    private void createAnswersSquare(){
        responses.setLayout(new GridLayout(12, 1));
        for (int i = 0; i < 12; i++) {
            JPanel j = new JPanel();
            j.setOpaque(false);
            j.setLayout(new GridBagLayout());
            GridBagConstraints constraints = new GridBagConstraints();

            constraints.weighty = 1.0;
            constraints.fill = GridBagConstraints.VERTICAL;
            int x = 0;
            int y = 0;
            answerButtons.add(new ArrayList<>()); // <-
            for (int k = 0; k < numColumns; k++) {
                constraints.gridx = x;
                constraints.gridy = y;

                JButton jButton3 = new JButton(" ");
                j.add(jButton3, constraints);
                setDesign(jButton3);

                if (x == (numColumns/2 - 1)) {
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

    private void makeTables(){
        solutionButtons = new ArrayList<>();
        tableButtons = new ArrayList<>();
        answerButtons = new ArrayList<>();

        createMainTable();
        createSolutionRow();
        createAnswersSquare();

        setFunctionColors();
    }

    private void setButtonsEnabled(ArrayList<JButton> buttons){
        for (JButton button: buttons) {
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent mouseEvent) {
                    super.mouseClicked(mouseEvent);
                    if (mouseEvent.getButton() != MouseEvent.BUTTON3)
                        setColor(button, color);
                    else {
                        setColor(button, 0);
                    }
                }
            });
        }
    }

    private void setFunctionColors(){
        ArrayList<JButton> colorsButtons = new ArrayList<>();
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
        aux.add(pistaButton);
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

    private int getEmptyButtonsSize(ArrayList<JButton> buttons){
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

    private void fillRow(List<Integer> list, ArrayList<JButton> buttons){
        int j = 0;
        for (JButton button: buttons) {
            button.setBackground(colorMap.get(list.get(j)));
            j++;
        }
    }

    private void initMap(){
        colorMap = new HashMap<>();
        colorMap.put(-1, hide);
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

    private void setButtonsDisabled(ArrayList<JButton> buttons){
        for (JButton button: buttons) {
            for (MouseListener listener:button.getMouseListeners()) {
                button.removeMouseListener(listener);
            }
        }
    }

    private void showMessage(String title, String message){
        JOptionPane.showMessageDialog(null,message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    private void initGame(){
        if (!codeMaker){
            List<Integer> solCode = controller.crearPartidaUsuarioCargadoRolBreaker(dificultad);

            List <Integer> aux = new ArrayList<>();
            for (int i = 0; i < numColumns; i++) {
                aux.add(-1);
            }
            fillRow(aux, solutionButtons);

            setButtonsDisabled(solutionButtons);

            setButtonsEnabled(tableButtons.get(actualRow));
        }
        else{
            setButtonsEnabled(solutionButtons);
        }
    }

    private void setAcceptButtonFunction(){
        ACEPTARButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                super.mousePressed(mouseEvent);

                if (!finished) {
                    //CodeMaker
                    if (codeMaker && firstAttempt) {
                        if (getEmptyButtonsSize(solutionButtons) == 0) {
                            controller.crearPartidaUsuarioCargadoRolMaker(dificultad, fromJButtonListToIntList(solutionButtons));
                            firstAttempt = false;

                            fillRow(controller.jugarCodeMaker(), tableButtons.get(actualRow));
                            setButtonsDisabled(solutionButtons);
                        } else {
                            showMessage("Error", "¡No puede existir un hueco vacío en tu solución!");
                        }
                    } else if (codeMaker && !firstAttempt) {
                        try {
                            actualRow++;
                            List<Integer> aux = controller.jugarCodeMaker(fromJButtonListToIntList(answerButtons.get(actualRow - 1)));
                            if (!controller.isPartidaGanada())fillRow(aux, tableButtons.get(actualRow));
                        } catch (ExcepcionRespuestaIncorrecta excepcionRespuestaIncorrecta) {
                            actualRow--;
                            showMessage("Error", "Corrección incorrecta!");
                        }
                    }
                    if (codeMaker) {
                        if (actualRow != 0) setButtonsDisabled(answerButtons.get(actualRow - 1));
                        if (!controller.isPartidaGanada())setButtonsEnabled(answerButtons.get(actualRow));
                    }

                    // CodeBreaker
                    if (!codeMaker) {
                        if (getEmptyButtonsSize(tableButtons.get(actualRow)) == 0) {
                            List<Integer> response = controller.juegaCodeBreaker(fromJButtonListToIntList(tableButtons.get(actualRow)), 0);
                            fillRow(response, answerButtons.get(actualRow));
                            actualRow++;
                            if (!controller.isPartidaGanada() && actualRow < 12) setButtonsEnabled(tableButtons.get(actualRow));
                            if (actualRow != 0) setButtonsDisabled(tableButtons.get(actualRow - 1));
                        } else {
                            showMessage("Error", "Tienes que rellenar todos los huecos de la fila " + String.valueOf(actualRow + 1));
                        }
                    }

                    if (controller.isPartidaGanada()) {
                        finished = true;
                        if (!codeMaker) {
                            fillRow(controller.getCodigoSecreto(), solutionButtons);
                            showMessage("Mastermindo", "¡Enhorabuena! Has descubierto el código secreto!");
                        }
                        else{
                            showMessage("Mastermindo", "¡Creo que te he ganado!");
                        }
                    }

                    if (!controller.isPartidaGanada() && actualRow == 12){
                        finished = true;
                        if (codeMaker){
                            showMessage("Mastermindo", "¡Has ganado a Mastermindo!");
                        }
                        else if (!codeMaker){
                            showMessage("Mastermindo", "¡Has perdido!, no tienes más intentos");
                        }
                    }
                }
            }
        });
    }

    public Juego(String dificultad, boolean isCodeMaker, JFrame frame, JFrame oldFrame) {
        ctrl = ControladorDominio.getInstance();
        this.codeMaker = isCodeMaker;
        this.dificultad = dificultad;
        if(dificultad == "Facil") {
            numColumns = 4;
            numColors = 4;

            coloresPanel.remove(narvioPanel);
        }
        else if(dificultad == "Medio"){
            numColumns = 4;
            numColors = 6;
        }
        else{
            numColumns = 6;
            numColors = 6;
        }

        initMap();
        makeTables();
        applyButtonsEffects();
        initGame();
        setAcceptButtonFunction();

        abandonarPartidaButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                frame.dispose();
                oldFrame.setVisible(true);
            }
        });

        guardarYSalirButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);

                controller.guardaPartidaActual();
                frame.dispose();
                oldFrame.setVisible(true);

            }
        });

        ayudaButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);

                JFrame frame = new JFrame("Ayuda");
                frame.setContentPane(new Ayuda().getPanel());
                frame.setPreferredSize(new Dimension(550, 600));
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });

        pistaButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);

                System.out.println();
            }
        });

        reiniciarPartidaButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);

                frame.dispose();
                JFrame frame = new JFrame("Mastermindo");
                frame.setContentPane(new Juego(dificultad, codeMaker, frame, oldFrame).getPanel());
                frame.setPreferredSize(new Dimension(850, 900));
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });

    }


    public void cargarTablero() {
        List<List<List<Integer>>> tablero = ctrl.getTablero();
        List<Integer> secreto = ctrl.getCodigoSecreto();
        //TODO hay que rellenar el tablero con esto si es code breaker el usuario obviamente sin el codigo.

    }
}
