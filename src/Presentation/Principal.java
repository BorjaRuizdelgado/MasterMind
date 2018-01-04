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

    public Principal(JFrame jframe) {
        frame = jframe;
        mp3Player = Mp3Player.getInstance();
        mp3Player.play(System.getProperty("user.dir") + "/src/MP3Player/Skyrim.wav");
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
                    JOptionPane.showMessageDialog(new Frame(), "Carga o crea un usuario para ver su perfil.");
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
                    JOptionPane.showMessageDialog(new Frame(), "No existen usuarios, primero crea uno.");
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
        ACEPTARButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                if(entraTuNombreDeTextField.getText().equals("")){
                    JOptionPane.showMessageDialog(new Frame(), "¡Entra tu usuario primero!");
                }
                else {
                    Boolean exception = false;
                    if(crearUsuario) {
                        try {
                            ctrl.crearUsuario(entraTuNombreDeTextField.getText());
                        } catch (ExcepcionUsuarioExiste e) {
                            exception = true;
                            JOptionPane.showMessageDialog(new Frame(), "¡El usuario ya existe!");
                        }
                    }
                    else if(!crearUsuario){
                        try{
                            ctrl.cargarUsuario(entraTuNombreDeTextField.getText());
                        }
                        catch (ExcepcionUsuarioInexistente e){
                            exception = true;
                            JOptionPane.showMessageDialog(new Frame(), "¡El usuario no existe!");
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
                main.removeAll();
                main.add(contenidoCargarPart);
                main.revalidate();
                displayListaPartidas();
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
                entraTuNombreDeTextField.setText(null);
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
                entraTuNombreDeTextField.setText(null);
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

    public JPanel getPanel() {
        return panel;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    public void revalidar() {
        displayListaPartidas();
    }

    public void volverIncio(){
        main.removeAll();
        main.add(contenidoPrincipal);
        main.revalidate();

    }
}
