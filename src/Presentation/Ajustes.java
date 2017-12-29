package Presentation;

import MP3Player.Mp3Player;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Ajustes {
    private JPanel panel;
    private JPanel panel2;
    private JSlider slider1;
    private JCheckBox silenciarMúsicaCheckBox;

    private Mp3Player mp3Player;
    public Ajustes(){
        mp3Player = Mp3Player.getInstance();

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

        silenciarMúsicaCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(silenciarMúsicaCheckBox.isSelected()){
                    mp3Player.close();
                }
                else{
                    mp3Player.playAnterior();
                    mp3Player.changeVolume(slider1.getValue() - 50);
                }
            }
        });
    }
    public JPanel getPanel() {
        return panel;
    }
}
