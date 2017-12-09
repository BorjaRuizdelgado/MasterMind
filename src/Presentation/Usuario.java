package Presentation;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Usuario {
    private JPanel panel;
    private JTextField entraTuNombreDeTextField;
    private JButton ACEPTARButton;

    public Usuario() {
        ACEPTARButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                super.mousePressed(mouseEvent);
            }
        });
    }

    public JPanel getPanel() {
        return panel;
    }
}
