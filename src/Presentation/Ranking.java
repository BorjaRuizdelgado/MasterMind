package Presentation;

import Domain.Controllers.ControladorDominio;
import Domain.SistemaRanking;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Comparator;
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
       onCreate();
    }

    public JPanel getPanel() {
        return panel;
    }

    /**
     * Hace posible seleccionar el ranking por diversos niveles de dificultad con un listener en el selector
     */
    private void setSelectorListener(){
        selector.addActionListener (e -> {
            filtrar(mostrarSoloMisPartidasCheckBox.isSelected());
        });
    }

    /**
     * Actualiza la vista de los ranking de pendiendo si se quiere por el propio usuario cargado o no.
     * @param usuario variable booleana que indica si se quiere el filtrado por el usario actual cargado o no.
     */
    private void filtrar(Boolean usuario){
        tableModel = new DefaultTableModel(0, 3);
        int Dificultad = selector.getSelectedIndex();
        if (Dificultad == 0 && !usuario) {
            insertDataOnTable(new ArrayList<>(ctrl.getRanking("Facil")), "Fácil");
            insertDataOnTable(new ArrayList<>(ctrl.getRanking("Medio")), "Medio");
            insertDataOnTable(new ArrayList<>(ctrl.getRanking("Dificil")), "Difícil");
        } else if (Dificultad == 1 && !usuario) {
            insertDataOnTable(new ArrayList<>(ctrl.getRanking("Facil")), "Fácil");

        } else if (Dificultad == 2 && !usuario) {
            insertDataOnTable(new ArrayList<>(ctrl.getRanking("Medio")), "Medio");
        } else if (Dificultad == 3 && !usuario) {
            insertDataOnTable(new ArrayList<>(ctrl.getRanking("Dificil")), "Difícil");
        }
        else if(Dificultad == 0){

            insertDataOnTable(new ArrayList<>(ctrl.getRankingNombreUsrDificultad(ctrl.getUsuario(),"Facil")), "Fácil");
            insertDataOnTable(new ArrayList<>(ctrl.getRankingNombreUsrDificultad(ctrl.getUsuario(), "Medio")), "Medio");
            insertDataOnTable(new ArrayList<>(ctrl.getRankingNombreUsrDificultad(ctrl.getUsuario(),"Dificil")), "Difícil");
        }
        else if(Dificultad == 1){
            insertDataOnTable(new ArrayList<>(ctrl.getRankingNombreUsrDificultad(ctrl.getUsuario(),"Facil")), "Fácil");
        }
        else if(Dificultad == 2){
            insertDataOnTable(new ArrayList<>(ctrl.getRankingNombreUsrDificultad(ctrl.getUsuario(), "Medio")), "Medio");
        }
        else if (Dificultad == 3){
            insertDataOnTable(new ArrayList<>(ctrl.getRankingNombreUsrDificultad(ctrl.getUsuario(),"Dificil")), "Difícil");

        }

        table1.setModel(tableModel);
        setCellRenderer();
    }

    /**
     * Hace display del ranking en la matriz.
     */
    private void setCellRenderer(){
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        cellRenderer.setVerticalAlignment(DefaultTableCellRenderer.CENTER);
        for (int columnIndex = 0; columnIndex < table1.getColumnCount(); columnIndex++)
        {
            table1.getColumnModel().getColumn(columnIndex).setCellRenderer(cellRenderer);
        }
    }

    /**
     * Añade una lista de strings a la lista qu ese hará display.
     * @param ranking
     * @param dificultad
     */
    private void insertDataOnTable(List<String> ranking, String dificultad){
        List<List<String>> orderedList = new ArrayList<>();
        for (int i = 0; i < ranking.size(); i++) {
            orderedList.add(new ArrayList<>());
            orderedList.get(i).add(ranking.get(i).split(" ")[0]);
            orderedList.get(i).add(dificultad);
            orderedList.get(i).add(ranking.get(i).split(" ")[2]);
        }
        orderedList.sort(new Comparator<List<String>>() {
            @Override
            public int compare(List<String> a, List<String> b) {
                int puntuacionA =Integer.valueOf(a.get(2));
                int puntuacionB = Integer.valueOf(b.get(2));
                return puntuacionB - puntuacionA;
            }
        });

        for (List<String> anOrderedList : orderedList) {
            String aux[] = {anOrderedList.get(0), anOrderedList.get(1), anOrderedList.get(2)};
            tableModel.addRow(aux);
        }
    }

    /**
     * Mira si el usuario quiere sus propios rankings o no y manda a hacer diplay
     */
    private void setCheckboxListener(){

        mostrarSoloMisPartidasCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

                if(e.getStateChange() == ItemEvent.SELECTED) {
                    if(!ctrl.isUsuarioCargado()){
                        JOptionPane.showMessageDialog(new Frame(), "Carga o crea un usuario para ver su perfil.");
                    }
                    else {
                        filtrar(true);
                    }
                } else {
                    filtrar(false);

                }
            }
        });

    }

    /**
     * Acciones por defecto al crear la vista del ranking.
     */
    private void onCreate() {
        ctrl = ControladorDominio.getInstance();
        tableModel = new DefaultTableModel(0, 3);
        setSelectorListener();
        setCheckboxListener();
        table1.setTableHeader(null);

        insertDataOnTable(new ArrayList<>(ctrl.getRanking("Facil")), "Fácil");
        insertDataOnTable(new ArrayList<>(ctrl.getRanking("Medio")), "Medio");
        insertDataOnTable(new ArrayList<>(ctrl.getRanking("Dificil")), "Difícil");

        table1.setModel(tableModel);
        setCellRenderer();
    }
}
