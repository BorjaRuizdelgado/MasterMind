package Presentation;

import Domain.Controllers.ControladorDominio;
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
    private Mp3Player mp3Player;
    private ControladorDominio ctrl;

    public AjustesUsuario(){
        mp3Player = Mp3Player.getInstance();
        ctrl = ControladorDominio.getInstance();

        if(!mp3Player.isPlaying()) silenciarMusicaCheckBox.setSelected(true);

        slider1.addChangeListener(new ChangeListener()
        {
            @Override public void stateChanged(ChangeEvent e)
            {
                if (slider1.getValueIsAdjusting())
                {
                    mp3Player.changeVolume(slider1.getValue()-50);
                }
            }
        });

        silenciarMusicaCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(silenciarMusicaCheckBox.isSelected()){
                    mp3Player.close();
                }
                else{
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
                if(contenido.equals("")){
                    JOptionPane.showMessageDialog(new Frame(), "Primero rellena el campo de texto");
                }
                //else ctrl.cambiarNombreUsr(contenido);
            }
        });
    }
    public JPanel getPanel() {
        return panel;
    }
}
