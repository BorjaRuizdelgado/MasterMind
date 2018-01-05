package Presentation;

import Domain.Controllers.ControladorDominio;
import Domain.Excepciones.ExcepcionNoHayColoresSinUsar;
import Domain.Excepciones.ExcepcionPistaUsada;
import Domain.Excepciones.ExcepcionRespuestaIncorrecta;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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
    private JPanel naranjaVioletPanel;
    private JPanel blancoNegroPanel;
    private JPanel rojoVerdePanel;
    private JPanel azulAmariPanel;

    private JFrame frame;
    private JFrame oldFrame;
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
    private long startTime;
    private long endTime;
    private boolean loadedBoard = false;
    private Principal principal;

    private static Color hide = new Color(160, 160, 160);
    private static Color defaultColor = new Color(238, 238, 238);
    private static Color rojo = new Color(255, 0, 0);
    private static Color verde = new Color(0, 191, 28);
    private static Color azul = new Color(23, 41, 224);
    private static Color amarillo = new Color(224, 222, 39);
    private static Color naranja = new Color(236, 151, 48);
    private static Color morado = new Color(153, 44, 226);
    private static Color blanco = new Color(255, 255, 255);
    private static Color negro = new Color(0, 0, 0);


    private Map<Integer, Color> colorMap;

    /**
     * Devuelve el panel principal.
     * @return El panel de la clase.
     */
    public JPanel getPanel() {
        return panel2;
    }

    /**
     * Pone el diseño por defecto elegido al botón que está pasado por parámetro.
     * @param jButton El botón que va a cambiar.
     */
    private void setDesign(JButton jButton) {
        jButton.setBackground(defaultColor);
        jButton.setFocusPainted(false);
    }

    /**
     * Cambia el fondo del botón al que indica el segundo parámetro.
     * @param button El botó que va a cambiar.
     * @param color El color al que se cambiará.
     */
    private void setColor(JButton button, int color) {
        button.setBackground(colorMap.get(color));
    }

    /**
     * Crea la tabla principal según el número de columnas establecido en la clase.
     */
    private void createMainTable() {
        int row = 0;
        tableButtons.add(new ArrayList<>());
        int column = -1;

        tablew.setLayout(new GridLayout(12, numColumns));
        for (int i = 0; i < 12 * numColumns; i++) {
            JButton jButton = new JButton();
            setDesign(jButton);
            tablew.add(jButton);

            if (column == numColumns - 1) {
                row++;
                tableButtons.add(new ArrayList<>());
                column = 0;
            } else {
                column++;
            }
            tableButtons.get(row).add(jButton);
        }
    }

    /**
     * Crea la fila donde se introduce el código del juego.
     */
    private void createSolutionRow() {
        solution.setLayout(new GridLayout(1, numColumns));
        for (int i = 0; i < numColumns; i++) {
            JButton jButton = new JButton();
            setDesign(jButton);
            solution.add(jButton);

            solutionButtons.add(jButton);
        }
    }

    /**
     * Crea los cuadrados que contienen cuatro espacios para poner la corrección del código introducido por el
     * codeBreaker y los añade a su espacio correspondiente.
     */
    private void createAnswersSquare() {
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

                if (x == (numColumns / 2 - 1)) {
                    x = 0;
                    y++;
                } else x++;

                answerButtons.get(answerButtons.size() - 1).add(jButton3);
            }
            //
            responses.add(j);
        }
    }

    /**
     * Método que ejecuta las creaciones de las tablas e inicializa unas listas que contendrán todos los botones para
     * mayor control sobre ellos.
     */
    private void makeTables() {
        solutionButtons = new ArrayList<>();
        tableButtons = new ArrayList<>();
        answerButtons = new ArrayList<>();

        createMainTable();
        createSolutionRow();
        createAnswersSquare();

        setFunctionColors();
    }

    /**
     * Añade el listener al botón pasado por parámetro. El listener indica que si haces click al botón, se ponga el
     * fondo igual al atributo 'color' de la clase. En caso de click derecho, se cambia al color por defecto.
     * @param buttons
     */
    private void setButtonsEnabled(ArrayList<JButton> buttons) {
        for (JButton button : buttons) {
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

    /**
     * Añade los listeners a los botones de colores. Cuando se haga click sobre ellos, se pondrá el atributo 'color' a
     * su color correspondiente.
     */
    private void setFunctionColors() {
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

    /**
     * Aplica un pequeño efecto a los botones de la ventana. Cuando el mouse está por encima, se cambia el fondo a color
     * negro, mientras que si el mouse no está por encima, se cambia el fondo a color rojo.
     */
    private void applyButtonsEffects() {
        ArrayList<JButton> aux = new ArrayList<>();
        aux.add(pistaButton);
        aux.add(abandonarPartidaButton);
        aux.add(reiniciarPartidaButton);
        aux.add(ayudaButton);
        aux.add(guardarYSalirButton);
        aux.add(ACEPTARButton);

        for (JButton button : aux) {
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

    /**
     * Devuelve la la cantidad de botones que tienen el fondo como el color por defecto, es decir, en los que están
     * simbólicamente interpretados como vacíos.
     * @param buttons Lista de botones sobre el que se aplicará la operación.
     * @return El número de botones vacíos.
     */
    private int getEmptyButtonsSize(ArrayList<JButton> buttons) {
        int count = 0;
        for (JButton button : buttons) {
            if (button.getBackground().equals(defaultColor))
                count++;
        }
        return count;
    }

    /**
     * Pasa de un color a su código establecido en int.
     * @param color El color a pasar.
     * @return Pasa un int que equivale al color (establecida por el proyecto).
     */
    private int fromColorToInt(Color color) {
        if (color.equals(rojo))
            return 1;
        else if (color.equals(verde))
            return 2;
        else if (color.equals(azul))
            return 3;
        else if (color.equals(amarillo))
            return 4;
        else if (color.equals(naranja))
            return 5;
        else if (color.equals(morado))
            return 6;
        else if (color.equals(blanco))
            return 7;
        else if (color.equals(negro))
            return 8;
        else return 0;
    }

    /**
     * Crea una lista de ints que equivale a la lista de los colores de fondo de los botones.
     * @param Buttons Lista de botones a partir de la que se crearán la lista de int.
     * @return Devuelve una lista de tamaño igual al de los botones y en el mismo orden con cada uno de sus colores de
     * fondo con la definición establecida por el proyecto.
     */
    private ArrayList<Integer> fromJButtonListToIntList(ArrayList<JButton> Buttons) {
        ArrayList<Integer> Return = new ArrayList<>();
        for (JButton button : Buttons) {
            Return.add(fromColorToInt(button.getBackground()));
        }
        return Return;
    }

    /**
     * Cambia el fondo de los botones buttons por el del color en int pasado por primer parámetro (se respeta el orden
     * de la posición además de asimilar que són del mismo tamaño).
     * @param list Lista que contiene los colores en int que tendrán que tener los botones.
     * @param buttons Los botones que van a cambiar el fondo.
     */
    private void fillRow(List<Integer> list, ArrayList<JButton> buttons) {
        int j = 0;
        for (JButton button : buttons) {
            int aux = list.get(j);
            button.setBackground(colorMap.get(aux));
            j++;
        }
    }

    /**
     * Inicializa el Map que contiene los colores. La llave es el número (color en int), mientras que el valor es el
     * color de la clase Color.
     */
    private void initMap() {
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

    /**
     * Quita los listeners de los botones pasados por parámetro.
     * @param buttons Botones a los que se quitarán todos los listeners.
     */
    private void setButtonsDisabled(ArrayList<JButton> buttons) {
        for (JButton button : buttons) {
            for (MouseListener listener : button.getMouseListeners()) {
                button.removeMouseListener(listener);
            }
        }
    }

    /**
     * Método que acorta la llamada a JOptionPane.showMessageDialog(..) que crea un mensaje en pantalla.
     * @param title Título que contendrá la ventana creada.
     * @param message Mensaje que contendrá la ventana creada.
     */
    private void showMessage(String title, String message) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Método que acorta la llamada a JOptionPane.showMessageDialog(..) que crea un mensaje en pantalla con el título
     * 'Mastermindo'.
     * @param message Mensaje que contendrá la ventana creada.
     */
    private void showMessage(String message){
        JOptionPane.showMessageDialog(null, message, "Mastermindo", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Método que prepara el terreno en caso de que se inicie el juego y no se cargue partida.
     * En caso de que sea codeMaker pide el código para que la IA lo resuelva.
     * En cambio, en el caso de ser CodeBreaker, se oculta el código secreto con un color gris para mostrarlo si se gana
     * el juego.
     */
    private void initGame() {
        if (!codeMaker) {
            controller.crearPartidaUsuarioCargadoRolBreaker(dificultad);
            List<Integer> aux = new ArrayList<>();
            for (int i = 0; i < numColumns; i++) {
                aux.add(-1);
            }
            fillRow(aux, solutionButtons);

            setButtonsDisabled(solutionButtons);

            setButtonsEnabled(tableButtons.get(actualRow));
        } else {
            setButtonsEnabled(solutionButtons);
        }
    }

    /**
     * Se le añade al botón aceptar la funcion que varía según sea codeMaker o codeBreaker. Cada vez que se le da a
     * aceptar, mira si el código está puesto o si la fila inserta en codeBreaker está llena, además de varias
     * condiciones que establecen la lógica del juego.
     */
    private void setAcceptButtonFunction() {
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

                            eliminarColores();
                            blancoNegroPanel.setVisible(true);
                        } else {
                            showMessage("Error", "¡No puede existir un hueco vacío en tu solución!");
                        }
                    } else if (codeMaker && !firstAttempt) {
                        try {
                            actualRow++;
                            List<Integer> aux = controller.jugarCodeMaker(fromJButtonListToIntList(answerButtons.get(actualRow - 1)));
                            if (!controller.isPartidaGanada()) fillRow(aux, tableButtons.get(actualRow));
                        } catch (ExcepcionRespuestaIncorrecta excepcionRespuestaIncorrecta) {
                            actualRow--;
                            showMessage("Error", "Corrección incorrecta!");
                        }
                    }
                    if (codeMaker) {
                        if (actualRow != 0) setButtonsDisabled(answerButtons.get(actualRow - 1));
                        if (!controller.isPartidaGanada()) setButtonsEnabled(answerButtons.get(actualRow));
                    }

                    // CodeBreaker
                    if (!codeMaker) {
                        if (getEmptyButtonsSize(tableButtons.get(actualRow)) == 0) {
                            // Time is calculated
                            endTime = System.currentTimeMillis();
                            long tiempoTotal = (endTime - startTime)/1000;
                            startTime = System.currentTimeMillis();
                            List<Integer> response = controller.juegaCodeBreaker(fromJButtonListToIntList(tableButtons.get(actualRow)), tiempoTotal);
                            fillRow(response, answerButtons.get(actualRow));
                            actualRow++;
                            if (!controller.isPartidaGanada() && actualRow < 12)
                                setButtonsEnabled(tableButtons.get(actualRow));
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
                            controller.actualizaRanking();
                            controller.terminaPartidaActual(true);
                            close();

                        } else {
                            showMessage("Mastermindo", "¡Creo que te he ganado!");
                            controller.terminaPartidaActual(false);
                            close();
                        }
                    }

                    if (!finished && !controller.isPartidaGanada() && actualRow == 12) {
                        finished = true;
                        if (codeMaker) {
                            showMessage("Mastermindo", "¡Has ganado a Mastermindo!");
                            controller.terminaPartidaActual(true);
                            close();
                        } else if (!codeMaker) {
                            showMessage("Mastermindo", "¡Has perdido!, no tienes más intentos");
                            controller.actualizaRanking();
                            controller.terminaPartidaActual(false);
                            close();
                        }
                    }
                }
            }
        });
    }

    /**
     * Cierra la ventana Juego y restaura la principal.
     */
    private void close(){
        frame.dispose();
        oldFrame.setVisible(true);
        this.principal.revalidar();
    }

    /**
     * Método invocado que crea los listeners que tienen las funcionalidades de los botones laterales excepto en el
     * botón 'Aceptar'.
     */
    private void setLateralButtonsFunctions(){
        /*
           Añade el listener que hace que cuando se le de click, abandone la partida si no ha tenido el primer intento.
           Ya que si no tiene el código secreto, la partida no existe y saltaría una excepción.
         */
        abandonarPartidaButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                int n = JOptionPane.showConfirmDialog(
                        null, "Si aceptas no habrá vuelta atrás.",
                        "ABANDONAR PARTIDA",
                        JOptionPane.YES_NO_OPTION);
                if (n == JOptionPane.YES_OPTION) {
                    abandonarPartida();
                }

            }
        });

        /*
          Añade el listener que hace que cuando se le de click, guarde la partida si no ha tenido el primer intento.
           Ya que no se puede guardar una partida sin código secreto.
         */
        guardarYSalirButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);

                if (codeMaker && firstAttempt && getEmptyButtonsSize(solutionButtons) != 0){
                    showMessage("Error", "Antes de guardar la partida tiene que rellenar el código secreto y darle a Aceptar.");
                }
                else {
                    controller.guardaPartidaActual();
                    frame.dispose();
                    oldFrame.setVisible(true);
                }

            }
        });

        /*
            Cuándo se le dé click al botón de ayuda, saltará la ventana de ayuda de la aplicación.
         */
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

        /*
            Se pedirán las pistas en caso de que sea codeBreaker. Si no saltará un mensaje por defecto.
         */
        pistaButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                if(codeMaker) {
                    showMessage("Probablemente no estes haciendo una corrección correcta, prueba de nuevo.");
                }
                else{
                    confirmarYPedirPista();
                }
            }
        });

        /*
            Al darle click al botón de reiniciar, se abandonará la partida si no es codeMaker o codeMaker que haya
            puesto el código secreto. Después se creará una ventana nueva del juego.
         */
        reiniciarPartidaButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                int n = JOptionPane.showConfirmDialog(
                        null, "Se borrará todo tu progreso.",
                        "REINICIAR PARTIDA",
                        JOptionPane.YES_NO_OPTION);
                if (n == JOptionPane.YES_OPTION) {
                    reiniciarPartida();
                }

            }
        });
    }

    private void abandonarPartida() {
        if (!firstAttempt) controller.abandonaPartidaAcutal();
        frame.dispose();
        oldFrame.setVisible(true);
        Juego.this.principal.revalidar();
    }

    private void reiniciarPartida() {
        if (!codeMaker || (codeMaker && !firstAttempt)) controller.abandonaPartidaAcutal();
        frame.dispose();
        JFrame frame = new JFrame("Mastermindo");
        frame.setContentPane(new Juego(dificultad, codeMaker, false, frame, oldFrame,principal).getPanel());
        frame.setPreferredSize(new Dimension(850, 900));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Elimina los rolores Rojo, verde, azul, amarillo (y naranja/violeta si es modo difícil) del panel vertical que
     * contiene los colores.
     */
    private void eliminarColores(){
        coloresPanel.remove(rojoVerdePanel);
        coloresPanel.remove(azulAmariPanel);
        if (dificultad.equals("Dificil")){
            coloresPanel.remove(naranjaVioletPanel);
        }
    }

    /**
     * Creadora de la clase que inicializa las variables de la instáncia, además de colocar el juego correctamente si
     * el controlador tiene una partida guardada.
     * Depende si el usuario es codeMaker o codeBreaker coloca/habilita/deshabilita/oculta/muestra tablas y filas de
     * botones.
     * @param dificultad Indica la dificultad del juego.
     * @param isCodeMaker Indica si el jugador de codeMaker o codeBreaker.
     * @param cargarTablero Indica si se ha de cargar el tablero, es decir, continuar partida o empezar de nuevo.
     * @param frame Frame actual de la ventana donde se muestra el juego.
     * @param oldFrame Frame de la clase Principal.
     * @param principal Instáncia de la clase principal.
     */
    public Juego(String dificultad, boolean isCodeMaker, boolean cargarTablero, JFrame frame, JFrame oldFrame, Principal principal) {
        this.principal = principal;
        frame.setUndecorated(true);
        frame.setMaximumSize(new Dimension(850, 900));
        frame.setResizable(false);
        this.frame = frame;
        this.oldFrame = oldFrame;
        this.codeMaker = isCodeMaker;
        this.dificultad = dificultad;
        if (dificultad.equals("Facil")) {
            numColumns = 4;
            numColors = 4;

            coloresPanel.remove(naranjaVioletPanel);
        } else if (dificultad.equals("Medio")) {
            numColumns = 4;
            numColors = 6;
        } else {
            numColumns = 6;
            numColors = 6;
        }

        initMap();
        makeTables();
        applyButtonsEffects();
        if (!cargarTablero) initGame();
        else cargarTablero();
        setAcceptButtonFunction();
        setLateralButtonsFunctions();

        if (!codeMaker) {
            startTime = System.currentTimeMillis();
            blancoNegroPanel.setVisible(false);
        }
        else{
            if (!firstAttempt){
                eliminarColores();
            }
            else{
                blancoNegroPanel.setVisible(false);
            }
        }
    }

    /**
     * Enseña un diálogo donde advierte sobre el uso de las pistas. En el caso de que se acepte, se pide la pista. En
     * el caso de que no, se cierra el diálogo.
     */
    private void confirmarYPedirPista() {
        int n = JOptionPane.showConfirmDialog(
               null, "¡Esto podría perjudicar tu puntuación!",
                "¿Necesitas una pista?",
                JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.YES_OPTION) {
            pistas();
        }
    }

    /**
     * Intenta pedir pistas de manera incremental. Si no se puede la pista 1, intentanto con la 2 y así hasta la tres.
     * Finalmente si no se puede pedir ninguna , muestra un mensaje donde indica que no quedan.
     */
    private void pistas() {
        try {
            getPista1();
        }
        catch(Exception e){
            try {
                getPista2();
            } catch (Exception es) {
                try {
                    getPista3();
                } catch (ExcepcionPistaUsada excepcionPistaUsada) {
                    showMessage("No quedan pistas por utilizar...");
                }
            }
        }
    }

    /**
     * Enseña por una nueva ventana la pista 1.
     * @throws ExcepcionPistaUsada En caso de que se vuelva a pedir la pista 1.
     * @throws ExcepcionNoHayColoresSinUsar Cuándo no hay colores que no se usen.
     */
    private void getPista1() throws ExcepcionPistaUsada, ExcepcionNoHayColoresSinUsar {
        int i = controller.getPista1();
        showMessage("Uno de los colores que no se encuentran en el codigo secreto es: " + numberToColorString(i));
    }

    /**
     * Enseña por una nueva ventana la pista 2.
     * @throws ExcepcionPistaUsada En caso de que se vuelva a pedir la pista 2.
     * @throws ExcepcionNoHayColoresSinUsar Cuándo no hay colores que no se usen.
     */
    private void getPista2() throws ExcepcionPistaUsada, ExcepcionNoHayColoresSinUsar {
        ArrayList<Integer> pista2 = controller.getPista2();
        String colores = "";
        for (Integer aPista2 : pista2) {
            colores += numberToColorString(aPista2) + " ";
        }
        showMessage("Los colores que no están en el codigo secreto són:" + colores);
    }

    /**
     * Enseña por una nueva ventana la pista 3.
     * @throws ExcepcionPistaUsada En caso de que se vuelva a pedir la pista 3.
     */
    private void getPista3() throws ExcepcionPistaUsada {
        List<Integer> pista3 = controller.getPista3();
        for(int i = 0; i < pista3.size(); ++i){
            if(pista3.get(i) != 0){
                showMessage("El color del codigo secreto: "
                        + numberToColorString(pista3.get(i))+ " y se encuentra en la posición: " + (i+1));
            }
        }
    }

    /**
     * Dado un color en int, te devuelve el nombre del color en String.
     * @param i es el código del color en int.
     * @return Devuelve el respectivo nombre en String.
     */
    private String numberToColorString(int i) {
        if(i == 1) return "Rojo";
        else if(i == 2) return "Verde";
        else if(i == 3) return "Azul";
        else if(i == 4) return "Amarillo";
        else if(i == 5) return "Naranja";
        else if(i == 6) return "Morado";
        else return null;
    }

    /**
     * Prepara el tablero y el juego. Actualiza la lógica de la clase para que se pueda continuar jugando a partir del
     * punto donde se sitúe. Lo hace tanto para codeMaker como para codeBreaker.
     */
    private void cargarTablero() {
        loadedBoard = true;
        firstAttempt = false;
        actualRow = controller.getNumeroFilaActual();
        List<List<List<Integer>>> tablero = controller.getTablero();
        List<Integer> secreto = controller.getCodigoSecreto();

        for(int i = 0; i < tablero.size(); i++){
            if (tablero.get(i).get(0).size() != 0) fillRow(tablero.get(i).get(0), tableButtons.get(i));
            if (tablero.get(i).get(1).size() != 0) fillRow(tablero.get(i).get(1), answerButtons.get(i));
        }
        fillRow(secreto, solutionButtons);

        if (codeMaker){
            setButtonsDisabled(solutionButtons);
            setButtonsEnabled(answerButtons.get(actualRow));
        }
        else {
            List<Integer> aux = new ArrayList<>();
            for (int i = 0; i < numColumns; i++) {
                aux.add(-1);
            }
            fillRow(aux, solutionButtons);
            setButtonsDisabled(solutionButtons);
            setButtonsEnabled(tableButtons.get(actualRow));
        }
    }

}
