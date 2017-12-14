package Presentation;

import Domain.Controllers.ControladorDominio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Ranking {
    private JPanel panel;
    private JPanel panel2;
    private JComboBox comboBox1;
    private JCheckBox mostrarSoloMisPartidasCheckBox;
    private JList list1;
    private ControladorDominio ctrl;
    private DefaultListModel<String> m;

    public Ranking() {
        onload();

    }

    public JPanel getPanel() {

        return panel;
    }



    public void onload() {
    ctrl = ctrl.getInstance();
    comboBox1.addActionListener (new ActionListener () {
        public void actionPerformed(ActionEvent e) {
            int Dificultad = comboBox1.getSelectedIndex();
            List<String> ranking = new ArrayList<String>();
            switch(Dificultad){
                case 0:
                    ranking.addAll(ctrl.getRanking("Facil"));
                    ranking.addAll((ctrl.getRanking("Medio")));
                    ranking.addAll((ctrl.getRanking("Dificil")));
                    break;
                case 1:

                    break;
                case 2:
                    break;
                case 3:
                    break;
            }
        }
    });
    }

}
