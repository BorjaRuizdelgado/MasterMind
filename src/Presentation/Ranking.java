package Presentation;

import Domain.Controllers.ControladorDominio;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Ranking {
    private JPanel panel;
    private JPanel panel2;
    private JComboBox selector;
    private JCheckBox mostrarSoloMisPartidasCheckBox;
    private JTable table1;
    private JScrollPane scrollPane;

    private ControladorDominio ctrl;
    private DefaultTableModel tableModel;

    private String noUserIcon;


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
     * Actualiza la vista de los ranking dependiendo si se quiere por el propio usuario cargado o no.
     * @param usuario Booleano que indica si se quiere el filtrado por el usario actual cargado o no.
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

        order();
        table1.setModel(tableModel);
        setCellRenderer();
    }

    /**
     * Hace display del ranking en la tabla.
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
     * Añade una lista de strings a la lista que ese hará display.
     * @param ranking
     * @param dificultad
     */
    private void insertDataOnTable(List<String> ranking, String dificultad){
        List<List<String>> List = new ArrayList<>();
        for (int i = 0; i < ranking.size(); i++) {
            List.add(new ArrayList<>());
            List.get(i).add(ranking.get(i).split(" ")[0]);
            List.get(i).add(dificultad);
            List.get(i).add(ranking.get(i).split(" ")[2]);
        }


        for (List<String> anOrderedList : List) {
            String aux[] = {anOrderedList.get(0), anOrderedList.get(1), anOrderedList.get(2)};
            tableModel.addRow(aux);
        }
    }

    /**
     * Mira si el usuario quiere sus propios rankings o no y manda a hacer display
     */
    private void setCheckboxListener(){

        mostrarSoloMisPartidasCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

                if(e.getStateChange() == ItemEvent.SELECTED) {
                    if(!ctrl.isUsuarioCargado()){
                        JOptionPane.showMessageDialog(null,
                                "Carga o crea un usuario para ver sus ránkings.", "No hay usuario", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(noUserIcon));
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
     * Método que ordena la tabla de ranking
     */
    private void order(){
        List<List<String>> aux = new ArrayList<>();
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            aux.add(new ArrayList<>());
            aux.get(i).add((String) tableModel.getValueAt(i, 0));
            aux.get(i).add((String) tableModel.getValueAt(i, 1));
            aux.get(i).add((String) tableModel.getValueAt(i, 2));
        }
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            tableModel.removeRow(i);
            i--;
        }

        aux.sort(new Comparator<List<String>>() {
            @Override
            public int compare(List<String> a, List<String> b) {
                int puntuacionA =Integer.valueOf(a.get(2));
                int puntuacionB = Integer.valueOf(b.get(2));
                return puntuacionB - puntuacionA;
            }
        });

        for (List<String> anOrderedList : aux) {
            String aux2[] = {anOrderedList.get(0), anOrderedList.get(1), anOrderedList.get(2)};
            tableModel.addRow(aux2);
        }
    }

    /**
     * Acciones por defecto al crear la vista del ranking.
     */
    private void onCreate() {
        noUserIcon = System.getProperty("user.dir") + "/src/imgs/cargaOrCrea.png";
        ctrl = ControladorDominio.getInstance("normal");
        tableModel = new DefaultTableModel(0, 3);
        setSelectorListener();
        setCheckboxListener();
        table1.setTableHeader(null);

        insertDataOnTable(new ArrayList<>(ctrl.getRanking("Facil")), "Fácil");
        insertDataOnTable(new ArrayList<>(ctrl.getRanking("Medio")), "Medio");
        insertDataOnTable(new ArrayList<>(ctrl.getRanking("Dificil")), "Difícil");

        order();

        table1.setModel(tableModel);
        setCellRenderer();
    }
}
