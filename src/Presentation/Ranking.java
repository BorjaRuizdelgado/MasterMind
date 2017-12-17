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
    private JScrollPane scrollPane;

    private ControladorDominio ctrl;
    private DefaultTableModel tableModel;


    Ranking() {
        ctrl = ControladorDominio.getInstance();
        tableModel = new DefaultTableModel(0, 3);

        onCreate();
    }

    public JPanel getPanel() {
        return panel;
    }

    private void setSelectorListener(){
        selector.addActionListener (e -> {
            tableModel = new DefaultTableModel(0, 3);
            int Dificultad = selector.getSelectedIndex();

            if (Dificultad == 0) {
                insertDataOnTable(new ArrayList<>(ctrl.getRanking("Facil")), "Fácil");
                insertDataOnTable(new ArrayList<>(ctrl.getRanking("Medio")), "Medio");
                insertDataOnTable(new ArrayList<>(ctrl.getRanking("Dificil")), "Difícil");
            } else if (Dificultad == 1) {
                insertDataOnTable(new ArrayList<>(ctrl.getRanking("Facil")), "Fácil");

            } else if (Dificultad == 2) {
                insertDataOnTable(new ArrayList<>(ctrl.getRanking("Medio")), "Medio");
            } else if (Dificultad == 3) {
                insertDataOnTable(new ArrayList<>(ctrl.getRanking("Dificil")), "Difícil");
            }

            table1.setModel(tableModel);
            setCellRenderer();
        });
    }

    private void testing(){
        SistemaRanking sistemaRanking = SistemaRanking.getInstance();
        sistemaRanking.addNewPuntuation("Pole", 666, "99", "Dificil");
        for (int i = 0; i < 30; i++) {
            sistemaRanking.addNewPuntuation(String.valueOf(new Random().nextInt(i+1)), i, "99", "Facil");
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

    private void insertDataOnTable(List<String> ranking, String dificultad){

        for (String aRanking : ranking) {
            String aux[] = {aRanking.split(" ")[0], dificultad, aRanking.split(" ")[2]};
            tableModel.addRow(aux);
        }
    }

    private void onCreate() {
        testing();
        setSelectorListener();
        table1.setTableHeader(null);

        insertDataOnTable(new ArrayList<>(ctrl.getRanking("Facil")), "Fácil");
        insertDataOnTable(new ArrayList<>(ctrl.getRanking("Medio")), "Medio");
        insertDataOnTable(new ArrayList<>(ctrl.getRanking("Dificil")), "Difícil");

        table1.setModel(tableModel);
        setCellRenderer();
    }
}
