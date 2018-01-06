package Presentation;

import Domain.Controllers.ControladorDominio;
import Domain.Excepciones.ExcepcionUsuarioExiste;
import Domain.Excepciones.ExcepcionUsuarioInexistente;
import MP3Player.Mp3Player;
import groovy.lang.Tuple2;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class Principal {

    private JPanel panel;
    private JButton CARGARPARTIDAButton;
    private JButton CREARPARTIDAButton;
    private JButton b2;
    private JButton b1;
    private JButton b3;
    private JPanel titulo;
    private JPanel container;
    private JPanel contenidoPrincipal;
    private JPanel footer;
    private JPanel panel2;
    private JPanel top2;
    private JPanel main;
    private JPanel contenidoUsuario;
    private JPanel contenidoInicio;
    private JTextField entraTuNombreDeTextField;
    private JButton ACEPTARButton;
    private JPanel contenidoNuevaPart;
    private JButton CODEMAKERButton;
    private JButton CODEBREAKERButton;
    private JButton CREARUSUARIOButton;
    private JButton CARGARUSUARIOButton;
    private JPanel contenidoCargarPart;
    private JList list1;
    private JButton r1;
    private JButton r2;
    private JButton r3;
    private JButton r4;
    private JPanel nivelDificultad;
    private JButton facil;
    private JButton medio;
    private JButton dificil;
    private JButton r5;
    private JTable table1;
    private JScrollPane scrollpanel;
    private JButton b4;
    private Mp3Player mp3Player;

    private ControladorDominio ctrl;
    private Boolean crearUsuario;
    private JFrame frame;

    private String addTextIcon;
    private String addNewUserIcon;
    private String userIcon;
    private String alertIcon;
    private final String noUserIcon;
    private final String gameIcon;

    /**
     * Creadora de la clase donde se le pasa el Jframe que va a controlar.
     * En ella se aplican todos los listeners necesarios.
     * @param jframe Frame que controla la clase.
     */
    public Principal(JFrame jframe) {
        noUserIcon = System.getProperty("user.dir") + "/src/imgs/cargaOrCrea.png";
        addTextIcon = System.getProperty("user.dir") + "/src/imgs/editText.png";
        addNewUserIcon = System.getProperty("user.dir") + "/src/imgs/addNewUser.png";
        userIcon = System.getProperty("user.dir") + "/src/imgs/user.png";
        alertIcon = System.getProperty("user.dir") + "/src/imgs/alerta.png";
        gameIcon = System.getProperty("user.dir") + "/src/imgs/GAME_ICON.png";
        frame = jframe;
        frame.setIconImage(new ImageIcon(gameIcon).getImage());

        // Evitamos que se configure el frame.
        frame.setResizable(false);

        mp3Player = Mp3Player.getInstance();
        mp3Player.play(System.getProperty("user.dir") + "/src/MP3Player/Arcade.wav");
        mp3Player.changeVolume(-25);

        ctrl = ControladorDominio.getInstance();
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                super.windowClosing(windowEvent);
                mp3Player.close();
                ctrl.onClose();
            }
        });

        /* Efectos */
        addMouseEnterExitColorEffect(CREARUSUARIOButton);
        addMouseEnterExitColorEffect(CARGARUSUARIOButton);
        addMouseEnterExitColorEffect(ACEPTARButton);
        addMouseEnterExitColorEffect(CREARPARTIDAButton);
        addMouseEnterExitColorEffect(CARGARPARTIDAButton);
        addMouseEnterExitColorEffect(CODEMAKERButton);
        addMouseEnterExitColorEffect(CODEBREAKERButton);
        addMouseEnterExitColorEffect(facil);
        addMouseEnterExitColorEffect(medio);
        addMouseEnterExitColorEffect(dificil);

        addMouseEnterExitBorderEffect(b1);
        addMouseEnterExitBorderEffect(b2);
        addMouseEnterExitBorderEffect(b3);
        addMouseEnterExitBorderEffect(b4);
        addMouseEnterExitBorderEffect(r1);
        addMouseEnterExitBorderEffect(r2);
        addMouseEnterExitBorderEffect(r3);
        addMouseEnterExitBorderEffect(r4);
        addMouseEnterExitBorderEffect(r5);


        /* ************************* */

        b1.addMouseListener(new MouseAdapter() {
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
        b2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);

                JFrame frame = new JFrame("Ranking");
                frame.setContentPane(new Ranking().getPanel());
                frame.setPreferredSize(new Dimension(550, 600));
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
        b3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                JFrame frame;
                if(ctrl.isUsuarioCargado()){
                    frame = new JFrame("AjustesUsuario");
                    frame.setContentPane(new AjustesUsuario(frame, Principal.this).getPanel());
                }
                else {
                    frame = new JFrame("Ajustes");
                    frame.setContentPane(new Ajustes().getPanel());
                }
                frame.setPreferredSize(new Dimension(550, 600));
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });

        b4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if(!ctrl.isUsuarioCargado()){
                    showMessage("No hay usuario", "Carga o crea un usuario para ver su perfil", noUserIcon);
                }
                else {
                    super.mouseClicked(mouseEvent);

                    JFrame frame = new JFrame("Perfil");
                    frame.setContentPane(new Perfil().getPanel());
                    frame.setPreferredSize(new Dimension(550, 600));
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                }
            }
        });



        CREARUSUARIOButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                crearUsuario = true;
                main.removeAll();
                main.add(contenidoUsuario);
                main.revalidate();
            }
        });
        CARGARUSUARIOButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if(!ctrl.existeAlgunUsuario()){
                    showMessage("No hay usuario","No existen usuarios, primero crea uno.",addNewUserIcon);
                }
                else{
                    super.mouseClicked(mouseEvent);
                    crearUsuario = false;
                    main.removeAll();
                    main.add(contenidoUsuario);
                    main.revalidate();
                }

            }
        });

        entraTuNombreDeTextField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(((c >= '0') && (c <= '9')) || (c >= 'a' && c <= 'z')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();
                    showMessage("CARACTER INCORRECTO","Por favor introduce caracteres de: a-z y 0-9",null);
                }
            }
        });
        ACEPTARButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                if(entraTuNombreDeTextField.getText().equals("")) {
                    showMessage("No hay usuario","¡Entra tu usuario primero!", addTextIcon);
                }
                else {
                    Boolean exception = false;
                    if(crearUsuario) {
                        try {
                            ctrl.crearUsuario(entraTuNombreDeTextField.getText());
                        } catch (ExcepcionUsuarioExiste e) {
                            exception = true;
                            showMessage("Usuario ya existente", "¡El usuario ya existe!", userIcon);
                        }
                    }
                    else {
                        try{
                            ctrl.cargarUsuario(entraTuNombreDeTextField.getText());
                        }
                        catch (ExcepcionUsuarioInexistente e){
                            exception = true;
                            showMessage("Usuario inexistente", "¡El usuario no existe!", userIcon);
                        }
                    }
                    if (!exception) {
                        main.removeAll();
                        main.add(contenidoInicio);
                    }
                }
                main.revalidate();
            }
        });
        CREARPARTIDAButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);

                main.removeAll();
                main.add(contenidoNuevaPart);
                main.revalidate();
            }
        });
        CARGARPARTIDAButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                if(ctrl.getPartidasGuardadasUsr().size() == 0){
                    showMessage("No hay partidas guardadas", "No hay partidas guardadas.", alertIcon);
                }
                else {
                    displayPartidas();
                }
            }
        });
        CODEMAKERButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                escogerDificultad(true);
            }
        });
        CODEBREAKERButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                escogerDificultad(false);

            }
        });
        r1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                //CardLayout cardLayout = (CardLayout) main.getLayout();
                //cardLayout.show(main, "contenidoPrincipal");
               volverIncio();
            }
        });

        r2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);

                //CardLayout cardLayout = (CardLayout) main.getLayout();
                //cardLayout.show(main, "contenidoPrincipal");
                main.removeAll();
                main.add(contenidoInicio);
                main.revalidate();
            }
        });
        r3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                ctrl.quitarUsuarioCargado();
                //CardLayout cardLayout = (CardLayout) main.getLayout();
                //cardLayout.show(main, "contenidoPrincipal");
                volverIncio();
            }
        });
        r4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                //CardLayout cardLayout = (CardLayout) main.getLayout();
                //cardLayout.show(main, "contenidoPrincipal");
                main.removeAll();
                main.add(contenidoInicio);
                main.revalidate();
            }
        });

        r5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                //CardLayout cardLayout = (CardLayout) main.getLayout();
                //cardLayout.show(main, "contenidoPrincipal");
                main.removeAll();
                main.add(contenidoNuevaPart);
                main.revalidate();
            }
        });

    }

    /**
     * Carga la vista con las partidas del usuario cargado.
     */
    private void displayPartidas() {
        main.removeAll();
        main.add(contenidoCargarPart);
        main.revalidate();
        displayListaPartidas();
    }


    /**
     * Muestra las partidas del usuario gruardadas en disco, dentro de la lista deja escoger una para cargar realizando
     * un doble click.
     */
    private void displayListaPartidas(){
        List<String> partidasUsuario = ctrl.getPartidasGuardadasUsr();
        DefaultTableModel partidas = new DefaultTableModel(0,3);
        for (String aPartidasUsuario : partidasUsuario) {
            String aux[] = aPartidasUsuario.split("@");
            partidas.addRow(aux);
        }
        table1.setTableHeader(null);
        scrollpanel.setColumnHeader(null);
        table1.setModel(partidas);
        setCellRenderer();
        table1.setCellSelectionEnabled(false);

        if (table1.getMouseListeners().length > 1){
            table1.removeMouseListener(table1.getMouseListeners()[1]);
        }
        table1.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table =(JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                if (mouseEvent.getClickCount() == 2) {
                    cargarPartida(partidasUsuario.get(row));
                }
            }
        });
    }

    /**
     * Hace display del ranking en la matriz.
     */
    private void setCellRenderer(){
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        cellRenderer.setVerticalAlignment(DefaultTableCellRenderer.CENTER);
        for (int columnIndex = 0; columnIndex < table1.getColumnCount(); columnIndex++)
        {
            table1.getColumnModel().getColumn(columnIndex).setCellRenderer(cellRenderer);
        }
    }

    /**
     * Carga una del usuario cargado en memoria con un id = partidaACargar
     * @param partidaACargar
     */
    private void cargarPartida(String partidaACargar) {
        Tuple2<Boolean,String> info = ctrl.cargarPartidaUsuario(partidaACargar);
        JFrame frame = new JFrame("Mastermindo");
        Juego j = new Juego(info.getSecond(),info.getFirst(), true, frame,this.frame, Principal.this);
        frame.setContentPane(j.getPanel());
        frame.setPreferredSize(new Dimension(850, 900));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        this.frame.setVisible(false);
    }

    private void addMouseEnterExitColorEffect(JButton button) {
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

    private void addMouseEnterExitBorderEffect(JButton button) {
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                super.mouseEntered(mouseEvent);
                button.setBorderPainted(true);
            }
        });
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                super.mouseExited(mouseEvent);
                button.setBorderPainted(false);
            }
        });
    }

    /**
     * Listeners para escoger dificultad.
     * @param maker parámetro necesario para acabar de crear el tablero proviniente del contenido de escoger modo de juego.
     */
    private void escogerDificultad(boolean maker) {
        main.removeAll();
        main.add(nivelDificultad);
        main.revalidate();

        if (facil.getMouseListeners().length > 4){
            facil.removeMouseListener(facil.getMouseListeners()[4]);
            medio.removeMouseListener(medio.getMouseListeners()[4]);
            dificil.removeMouseListener(dificil.getMouseListeners()[4]);
        }

        facil.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                crearJuego("Facil", maker);
            }
        });

        medio.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                crearJuego("Medio", maker);
            }
        });
        dificil.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                crearJuego("Dificil", maker);
            }
        });



    }

    /**
     * Método que permite que la UI enseñe un tablero.
     * @param dificultad Dificultad de la partida.
     * @param isCodeMaker Modo de juego.
     */
    private void crearJuego(String dificultad, boolean isCodeMaker) {
        JFrame frame = new JFrame("Mastermindo");
        frame.setContentPane(new Juego(dificultad, isCodeMaker,false, frame, this.frame, Principal.this).getPanel());
        frame.setPreferredSize(new Dimension(850, 900));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        this.frame.setVisible(false);
    }

    /**
     * Metodo que devuelve el panel del frame principal.
     * @return Panel principal del frame
     */
    public JPanel getPanel() {
        return panel;
    }

    /**
     * Metodo que hace posible recargar las partidas que tiene el usuario guardadas.
     */
    public void revalidar() {
        displayListaPartidas();
    }

    /**
     * Metodo que retorna el frame Principal al contenido principal.
     */
    public void volverIncio(){
        entraTuNombreDeTextField.setText("");
        main.removeAll();
        main.add(contenidoPrincipal);
        main.revalidate();

    }

    /**
     * Método que acorta la llamada a JOptionPane.showMessageDialog(..) que crea un mensaje en pantalla.
     * @param title Título que contendrá la ventana creada.
     * @param message Mensaje que contendrá la ventana creada.
     * @param icon Icono que contendrá la ventana creada.
     */
    private void showMessage(String title, String message, String icon) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE, new ImageIcon(icon));
    }

}
