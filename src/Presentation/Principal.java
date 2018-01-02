package Presentation;

import Domain.Controllers.ControladorDominio;
import Domain.Excepciones.ExcepcionUsuarioExiste;
import Domain.Excepciones.ExcepcionUsuarioInexistente;
import MP3Player.Mp3Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
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


        /* Efectos */
        addMouseEfect(CREARUSUARIOButton);
        addMouseEfect(CARGARUSUARIOButton);
        addMouseEfect(ACEPTARButton);
        addMouseEfect(CREARPARTIDAButton);
        addMouseEfect(CARGARPARTIDAButton);
        addMouseEfect(CODEMAKERButton);
        addMouseEfect(CODEBREAKERButton);
        addMouseEfect(facil);
        addMouseEfect(medio);
        addMouseEfect(dificil);


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

                JFrame frame = new JFrame("Ajustes");
                frame.setContentPane(new Ajustes().getPanel());
                frame.setPreferredSize(new Dimension(550, 600));
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
        b1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                super.mouseEntered(mouseEvent);
                b1.setBorderPainted(true);
            }
        });
        b1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                super.mouseExited(mouseEvent);
                b1.setBorderPainted(false);
            }
        });
        b2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                super.mouseEntered(mouseEvent);
                b2.setBorderPainted(true);
            }
        });
        b2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                super.mouseExited(mouseEvent);
                b2.setBorderPainted(false);
            }
        });
        b3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                super.mouseEntered(mouseEvent);
                b3.setBorderPainted(true);
            }
        });
        b3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                super.mouseExited(mouseEvent);
                b3.setBorderPainted(false);
            }
        });
        r1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                super.mouseEntered(mouseEvent);
                r1.setBorderPainted(true);
            }
        });
        r1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                super.mouseExited(mouseEvent);
                r1.setBorderPainted(false);
            }
        });
        r2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                super.mouseEntered(mouseEvent);
                r2.setBorderPainted(true);
            }
        });
        r2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                super.mouseExited(mouseEvent);
                r2.setBorderPainted(false);
            }
        });
        r3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                super.mouseEntered(mouseEvent);
                r3.setBorderPainted(true);
            }
        });
        r3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                super.mouseExited(mouseEvent);
                r3.setBorderPainted(false);
            }
        });
        r4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                super.mouseEntered(mouseEvent);
                r4.setBorderPainted(true);
            }
        });
        r4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                super.mouseExited(mouseEvent);
                r4.setBorderPainted(false);
            }
        });
        r5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                super.mouseEntered(mouseEvent);
                r5.setBorderPainted(true);
            }
        });
        r5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                super.mouseExited(mouseEvent);
                r5.setBorderPainted(false);
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
                main.removeAll();
                main.add(contenidoPrincipal);
                main.revalidate();
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

                //CardLayout cardLayout = (CardLayout) main.getLayout();
                //cardLayout.show(main, "contenidoPrincipal");
                main.removeAll();
                main.add(contenidoUsuario);
                main.revalidate();
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
        DefaultListModel<String > partidas = new DefaultListModel<>();
        for(int i = 0; i < partidasUsuario.size(); ++i) {
            partidas.addElement(partidasUsuario.get(i));
        }
        list1.setModel(partidas);
        list1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList)evt.getSource();
                if (evt.getClickCount() == 2) {
                    int index = list.locationToIndex(evt.getPoint());
                    cargarPartida(partidasUsuario.get(index));
                }
            }
        });
    }

    /**
     * Carga una del usuario cargado en memoria con un id = partidaACargar
     * @param partidaACargar
     */
    private void cargarPartida(String partidaACargar) {
        //TODO hacer método para cargar la partida con id s
        System.out.println("implementa que se pueda cargar una partida como la que se ha seleccionado: " + partidaACargar);
    }

    private void addMouseEfect(JButton button) {
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

    private void escogerDificultad(boolean maker) {
        main.removeAll();
        main.add(nivelDificultad);
        main.revalidate();

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
        frame.setContentPane(new Juego(dificultad, isCodeMaker, frame, this.frame).getPanel());
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

}
