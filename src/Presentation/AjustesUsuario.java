package Presentation;

import Domain.Controllers.ControladorDominio;
import Domain.Excepciones.ExcepcionNombreEscogido;
import MP3Player.Mp3Player;

import javax.naming.ldap.Control;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;

public class AjustesUsuario{
    private JPanel panel;
    private JPanel panel2;
    private JSlider slider1;
    private JCheckBox silenciarMusicaCheckBox;
    private JTextField textField1;
    private JButton aceptar;
    private JButton eliminarUsr;
    private JButton reiniciarStats;
    private Mp3Player mp3Player;
    private ControladorDominio ctrl;

    public AjustesUsuario(JFrame frame, Principal principal) {
        mp3Player = Mp3Player.getInstance();
        ctrl = ControladorDominio.getInstance();

        if (!mp3Player.isPlaying()) silenciarMusicaCheckBox.setSelected(true);

        slider1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (slider1.getValueIsAdjusting()) {
                    mp3Player.changeVolume(slider1.getValue() - 50);
                }
            }
        });

        silenciarMusicaCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (silenciarMusicaCheckBox.isSelected()) {
                    mp3Player.close();
                } else {
                    mp3Player.playAnterior();
                    mp3Player.changeVolume(slider1.getValue() - 50);
                }
            }
        });

        aceptar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                String contenido = textField1.getText();
                if (contenido.equals("")) {
                    JOptionPane.showMessageDialog(null, "Primero rellena el campo de texto.", "Campo vacío", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    try {
                        ctrl.cambiarNombreUsr(contenido);
                        JOptionPane.showMessageDialog(null, "Nombre de usuario cambiado por: "+contenido, "Nombre cambiado", JOptionPane.INFORMATION_MESSAGE);
                        frame.dispose();
                    } catch (ExcepcionNombreEscogido excepcionNombreEscogido) {
                        JOptionPane.showMessageDialog(null, "Nombre de usuario ya escogido, por favor escribe otro.",  "Nombre repetido", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
        eliminarUsr.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                ctrl.borrarUsuario();
                JOptionPane.showMessageDialog(null, "Tu usuario se ha eliminado con éxito", "Usuario eliminado", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();
                principal.volverIncio();
            }
        });

        reiniciarStats.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                ctrl.reiniciaEstadisticas();
                JOptionPane.showMessageDialog(null, "Estadísticas reiniciadas correctamente.", "Estadísticas reiniciadas", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();

            }
        });
    }

            public JPanel getPanel() {
                return panel;
            }
}