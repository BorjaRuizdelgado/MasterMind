package Presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Principal {
    private JPanel panel;
    private JButton CREARUSUARIOButton;
    private JButton CARGARUSUARIOButton;
    private JButton b2;
    private JButton b1;
    private JButton b3;
    private JPanel titulo;
    private JPanel container;
    private JPanel contenido;
    private JPanel footer;
    private JPanel panel2;
    private JPanel top2;
    private JPanel main;

    public Principal() {
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
                main.add(new Usuario().getPanel());
                main.revalidate();
            }
        });
        CARGARUSUARIOButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                super.mousePressed(mouseEvent);

                main.removeAll();
                main.add(new Usuario().getPanel());
                main.revalidate();
            }
        });
        b1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                super.mousePressed(mouseEvent);

                JFrame frame = new JFrame("Ayuda");
                frame.setContentPane(new Ayuda().getPanel());
                frame.setPreferredSize(new Dimension(550, 600));
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
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
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }

    public JPanel getPanel() {
        return panel;
    }
}
