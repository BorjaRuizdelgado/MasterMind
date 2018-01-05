package Presentation;

import Domain.Controllers.ControladorDominio;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.util.List;

public class Perfil {
    private JPanel panel2;
    private JPanel panel;
    private JTextArea insert_here_usernameTextArea;
    private JTable table1;
    private JPanel panelTabla;
    private ControladorDominio ctrl;

    public JPanel getPanel() {
        return panel;
    }

    public Perfil(){
        ctrl = ControladorDominio.getInstance();
        insert_here_usernameTextArea.setText(ctrl.getUsuario());
       insertarEstadisticasUsr();
    }

    private void insertarEstadisticasUsr() {
        List<Integer> estadisticas = ctrl.getEstadiscasUsuario();
        Integer partidasFinalizadasCB = estadisticas.get(0);
        Integer partidasFinalizadasCM = estadisticas.get(1);
        Integer partidasGanadasCB = estadisticas.get(2);
        Integer porcentajeGanadasCB = 0;
        if(partidasFinalizadasCB != 0) porcentajeGanadasCB = (partidasGanadasCB/partidasFinalizadasCB)*100;
        DefaultTableModel stats = new DefaultTableModel(0,2);


        String[] fila ={"Partidas totales", String.valueOf(partidasFinalizadasCB+partidasFinalizadasCM)};
        stats.addRow(fila);
        fila = new String[]{"Partidas finalizadas CodeBreaker", String.valueOf(partidasFinalizadasCB)};
        stats.addRow(fila);
        fila = new String[]{"Partidas ganadas CodeBreaker", String.valueOf(partidasGanadasCB)};
        stats.addRow(fila);
        fila = new String[]{"Porcentaje ganadas CodeBreaker", String.valueOf(porcentajeGanadasCB)+"%"};
        stats.addRow(fila);
        fila = new String[]{"Partidas finalizadas CodeMaker", String.valueOf(partidasFinalizadasCM)};
        stats.addRow(fila);
        table1.setModel(stats);
        setCellRenderer();
        table1.setCellSelectionEnabled(false);
        table1.setVisible(true);


    }
    private void setCellRenderer(){
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        cellRenderer.setVerticalAlignment(DefaultTableCellRenderer.CENTER);
        for (int columnIndex = 1; columnIndex < table1.getColumnCount(); columnIndex++) {
            table1.getColumnModel().getColumn(columnIndex).setCellRenderer(cellRenderer);
        }
        table1.getColumnModel().getColumn(0).setPreferredWidth(400);
        table1.getColumnModel().getColumn(1).setPreferredWidth(30);

    }
}
