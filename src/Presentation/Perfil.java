package Presentation;

import Domain.Controllers.ControladorDominio;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class Perfil {
    private JPanel panel2;
    private JPanel panel;
    private JTextArea insert_here_usernameTextArea;
    private JTable table1;
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

        String[] fila ={"PARTIDAS TOTALES", String.valueOf(partidasFinalizadasCB+partidasFinalizadasCM)};
        stats.addRow(fila);
        fila = new String[]{"PARTIDAS CODE BREAKER", String.valueOf(partidasFinalizadasCB)};
        stats.addRow(fila);
        fila = new String[]{"PARTIDAS GANADAS CODE BREAKER", String.valueOf(partidasGanadasCB)};
        stats.addRow(fila);
        fila = new String[]{"G/P RATIO EN CODE BREAKER", String.valueOf(porcentajeGanadasCB)};
        stats.addRow(fila);
        fila = new String[]{"PARTIDAS CODE MAKER", String.valueOf(partidasFinalizadasCM)};
        stats.addRow(fila);

        table1.setModel(stats);
        table1.setCellSelectionEnabled(false);
        table1.setVisible(true);


    }
}
