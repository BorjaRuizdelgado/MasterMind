package Presentation;

import Domain.Controllers.ControladorDominio;
import Domain.SistemaRanking;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ranking {
    private JPanel panel;
    private JPanel panel2;
    private JComboBox selector;
    private JCheckBox mostrarSoloMisPartidasCheckBox;
    private JTable table1;

    private ControladorDominio ctrl;
    private DefaultTableModel tableModel;


    Ranking() {
        ctrl = ControladorDominio.getInstance();

        onCreate();
    }

    public JPanel getPanel() {
        return panel;
    }

    private void setSelectorListener(){
        selector.addActionListener (e -> {
            int Dificultad = selector.getSelectedIndex();
            List<String> ranking = new ArrayList<String>();

            switch(Dificultad){
                case 0:
                    ranking.addAll(ctrl.getRanking("Facil"));
                    ranking.addAll((ctrl.getRanking("Medio")));
                    ranking.addAll((ctrl.getRanking("Dificil")));
                    break;
                case 1:
                    ranking.addAll(ctrl.getRanking("Facil"));
                    break;
                case 2:
                    ranking.addAll((ctrl.getRanking("Medio")));
                    break;
                case 3:
                    ranking.addAll((ctrl.getRanking("Dificil")));
                    break;
            }
            insertDataOnTable(ranking);
        });
    }

    private void testing(){
        SistemaRanking sistemaRanking = SistemaRanking.getInstance();
        sistemaRanking.addNewPuntuation("Pole", 666, "99", "Dificil");
        for (int i = 0; i < 7; i++) {
            sistemaRanking.addNewPuntuation(String.valueOf(new Random().nextInt()), i, "99", "Facil");
        }
    }

    private void setCellRenderer(){
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        cellRenderer.setVerticalAlignment(DefaultTableCellRenderer.CENTER);
        for (int columnIndex = 0; columnIndex < table1.getColumnCount(); columnIndex++)
        {
            table1.getColumnModel().getColumn(columnIndex).setCellRenderer(cellRenderer);
        }
    }

    private void insertDataOnTable(List<String> ranking){
        tableModel = new DefaultTableModel(0, 3);

        for (String aRanking : ranking) {
            tableModel.addRow(aRanking.split(" "));
        }
        table1.setModel(tableModel);

        setCellRenderer();
    }

    private void onCreate() {
        testing();
        setSelectorListener();

        List<String> ranking = new ArrayList<>();
        ranking.addAll(ctrl.getRanking("Facil"));
        ranking.addAll((ctrl.getRanking("Medio")));
        ranking.addAll((ctrl.getRanking("Dificil")));

        insertDataOnTable(ranking);
    }
}
