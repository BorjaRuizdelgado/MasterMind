package Presentation;

import Domain.Controllers.ControladorDominio;
import Domain.Excepciones.ExcepcionNombreEscogido;
import MP3Player.Mp3Player;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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

    private String userdeletedIcon;
    private String questionIcon;
    private String estadisticasIcon;
    private String textIcon;
    private String userIcon;

    public AjustesUsuario(JFrame frame, Principal principal) {
        userdeletedIcon = System.getProperty("user.dir") + "/src/imgs/userDeleted.png";
        questionIcon = System.getProperty("user.dir") + "/src/imgs/ayuda.png";
        estadisticasIcon = System.getProperty("user.dir") + "/src/imgs/estadisticas.png";
        textIcon = System.getProperty("user.dir") + "/src/imgs/editText.png";
        userIcon = System.getProperty("user.dir") + "/src/imgs/user.png";


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
                    JOptionPane.showMessageDialog(null, "Primero rellena el campo de texto.", "Campo vacío", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(textIcon));
                }
                else {
                    try {
                        ctrl.cambiarNombreUsr(contenido);
                        JOptionPane.showMessageDialog(null, "Nombre de usuario cambiado por: "+contenido, "Nombre cambiado", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(userIcon));
                        frame.dispose();
                    } catch (ExcepcionNombreEscogido excepcionNombreEscogido) {
                        JOptionPane.showMessageDialog(null, "Nombre de usuario ya escogido, por favor escribe otro.",  "Nombre repetido", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(textIcon));
                    }
                }
            }
        });
        eliminarUsr.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);

                int dialogResult = JOptionPane.showConfirmDialog(null,
                        "Tu usuario y datos se eliminarán, ¿estás seguro?", "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, new ImageIcon(questionIcon));
                if (dialogResult == JOptionPane.YES_OPTION) {
                    ctrl.borrarUsuario();
                    JOptionPane.showMessageDialog(null, "Tu usuario se ha eliminado con éxito", "Usuario eliminado", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(userdeletedIcon));
                    frame.dispose();
                    principal.volverIncio();
                }
            }
        });

        reiniciarStats.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                int dialogResult = JOptionPane.showConfirmDialog(null,
                        "Tus estadísticas y ránkings se eliminarán, ¿estás seguro?", "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, new ImageIcon(questionIcon));
                if (dialogResult == JOptionPane.YES_OPTION) {
                    ctrl.reiniciaEstadisticas();
                    JOptionPane.showMessageDialog(null, "Estadísticas reiniciadas correctamente.", "Estadísticas reiniciadas", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(estadisticasIcon));
                }

            }
        });
    }

            public JPanel getPanel() {
                return panel;
            }
}