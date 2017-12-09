package Presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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

    public Principal() {
        /* Efectos */
        CREARUSUARIOButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                super.mouseEntered(mouseEvent);
                CREARUSUARIOButton.setBackground(Color.BLACK);
            }
        });
        CREARUSUARIOButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                super.mouseExited(mouseEvent);
                CREARUSUARIOButton.setBackground(new Color(192, 55, 55));
            }
        });
        CARGARUSUARIOButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                super.mouseEntered(mouseEvent);
                CARGARUSUARIOButton.setBackground(Color.BLACK);
            }
        });
        CARGARUSUARIOButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                super.mouseExited(mouseEvent);
                CARGARUSUARIOButton.setBackground(new Color(192, 55, 55));
            }
        });
        ACEPTARButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                super.mouseEntered(mouseEvent);
                ACEPTARButton.setBackground(Color.BLACK);
            }
        });
        ACEPTARButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                super.mouseExited(mouseEvent);
                ACEPTARButton.setBackground(new Color(192, 55, 55));
            }
        });
        CREARPARTIDAButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                super.mouseEntered(mouseEvent);
                CREARPARTIDAButton.setBackground(Color.BLACK);
            }
        });
        CREARPARTIDAButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                super.mouseExited(mouseEvent);
                CREARPARTIDAButton.setBackground(new Color(192, 55, 55));
            }
        });
        CARGARPARTIDAButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                super.mouseEntered(mouseEvent);
                CARGARPARTIDAButton.setBackground(Color.BLACK);
            }
        });
        CARGARPARTIDAButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                super.mouseExited(mouseEvent);
                CARGARPARTIDAButton.setBackground(new Color(192, 55, 55));
            }
        });
        CODEMAKERButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                super.mouseEntered(mouseEvent);
                CODEMAKERButton.setBackground(Color.BLACK);
            }
        });
        CODEMAKERButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                super.mouseExited(mouseEvent);
                CODEMAKERButton.setBackground(new Color(192, 55, 55));
            }
        });
        CODEBREAKERButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                super.mouseEntered(mouseEvent);
                CODEBREAKERButton.setBackground(Color.BLACK);
            }
        });
        CODEBREAKERButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                super.mouseExited(mouseEvent);
                CODEBREAKERButton.setBackground(new Color(192, 55, 55));
            }
        });
        /* ************************* */

        b1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                super.mousePressed(mouseEvent);

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
            public void mousePressed(MouseEvent mouseEvent) {
                super.mousePressed(mouseEvent);

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
            public void mousePressed(MouseEvent mouseEvent) {
                super.mousePressed(mouseEvent);

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
        CREARUSUARIOButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                super.mousePressed(mouseEvent);

                main.removeAll();
                main.add(contenidoUsuario);
                main.revalidate();

            }
        });
        CARGARUSUARIOButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                super.mousePressed(mouseEvent);

                main.removeAll();
                main.add(contenidoUsuario);
                main.revalidate();
            }
        });
        ACEPTARButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                super.mousePressed(mouseEvent);

                main.removeAll();
                main.add(contenidoInicio);
                main.revalidate();
            }
        });
        CREARPARTIDAButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                super.mousePressed(mouseEvent);

                main.removeAll();
                main.add(contenidoNuevaPart);
                main.revalidate();
            }
        });
        CARGARPARTIDAButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                super.mousePressed(mouseEvent);

                main.removeAll();
                main.add(contenidoCargarPart);
                main.revalidate();
            }
        });
        CODEMAKERButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);

                JFrame frame = new JFrame("Juego");
                frame.setContentPane(new Juego().getPanel());
                frame.setPreferredSize(new Dimension(850, 900));
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
        CODEBREAKERButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                super.mousePressed(mouseEvent);

                JFrame frame = new JFrame("Juego");
                frame.setContentPane(new Juego().getPanel());
                frame.setPreferredSize(new Dimension(850, 900));
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public JPanel getPanel() {
        return panel;
    }
}
