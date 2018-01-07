package Presentation;

import Domain.Controllers.ControladorDominio;
import Domain.Excepciones.ExcepcionNombreEscogido;
import MP3Player.Mp3Player;

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
    private JButton confirmarButton;
    private JButton eliminarUserButton;
    private JButton reiniciarStatsButton;
    private Mp3Player mp3Player;
    private ControladorDominio ctrl;
    private JButton button1;
    private JButton button2;

    private String userdeletedIcon;
    private String questionIcon;
    private String estadisticasIcon;
    private String textIcon;
    private String userIcon;

    /**
     * Creadora de Ajustes usuario
     * @param frame Frame de AjustesUsuario
     * @param principal clase que nos permitirá volver al inicio una vez borrado el usuario.
     */

    public AjustesUsuario(JFrame frame, Principal principal) {
        userdeletedIcon = System.getProperty("user.dir") + "/src/imgs/userDeleted.png";
        questionIcon = System.getProperty("user.dir") + "/src/imgs/ayuda.png";
        estadisticasIcon = System.getProperty("user.dir") + "/src/imgs/estadisticas.png";
        textIcon = System.getProperty("user.dir") + "/src/imgs/editText.png";
        userIcon = System.getProperty("user.dir") + "/src/imgs/user.png";
        mp3Player = Mp3Player.getInstance();
        ctrl = ControladorDominio.getInstance();

        addMouseEnterExitColorEffect(confirmarButton);
        addMouseEnterExitColorEffect(eliminarUserButton);
        addMouseEnterExitColorEffect(reiniciarStatsButton);

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

        confirmarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                String contenido = textField1.getText();
                if (contenido.equals("")) {
                    showMessage("Campo vacío", "Primero rellena el campo de texto.", textIcon);
                }
                else {
                    try {
                        ctrl.cambiarNombreUsr(contenido);
                        showMessage("Nombre cambiado", "Nombre de usuario cambiado por: "+contenido, userIcon);
                        frame.dispose();
                    } catch (ExcepcionNombreEscogido excepcionNombreEscogido) {
                        showMessage("Nombre repetido", "Nombre de usuario ya escogido, por favor escribe otro.",  textIcon);
                    }
                }
            }
        });

        textField1.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(((c >= '0') && (c <= '9')) || (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();
                    showMessage("Carácter especial","Por favor, introduce caracteres válidos: a-z, A-Z y 0-9",textIcon);
                }
            }
        });
        eliminarUserButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);

                int dialogResult = showYesNoMessage("Atención", "Tu usuario y datos se eliminarán, ¿estás seguro?", questionIcon);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    ctrl.borrarUsuario();
                    JOptionPane.showMessageDialog(null, "Tu usuario se ha eliminado con éxito", "Usuario eliminado", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(userdeletedIcon));
                    frame.dispose();
                    principal.volverInicio();
                }
            }
        });

        reiniciarStatsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                int dialogResult = showYesNoMessage("Atención", "Tus estadísticas y ránkings se eliminarán, ¿estás seguro?", questionIcon);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    ctrl.reiniciaEstadisticas();
                    showMessage("Estadísticas reiniciadas", "Estadísticas reiniciadas correctamente.", estadisticasIcon);
                }

            }
        });
    }

    /**
     * Método para añadir los efectos a un botón.
     * @param button botón al que se le añaden los efectos.
     */
    private void addMouseEnterExitColorEffect(JButton button) {
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                super.mouseEntered(mouseEvent);
                button.setBackground(Color.BLACK);
            }
        });
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                super.mouseExited(mouseEvent);
                button.setBackground(new Color(192, 55, 55));
            }
        });

    }

    public JPanel getPanel() {
                return panel;
            }

    /**
     * Método que acorta la llamada a JOptionPane.showMessageDialog(..) que crea un mensaje en pantalla.
     * @param title Título que contendrá la ventana creada.
     * @param message Mensaje que contendrá la ventana creada.
     * @param icon Icono que contendrá la ventana creada.
     */
    private void showMessage(String title, String message, String icon) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE, new ImageIcon(icon));
    }

    /**
     * Método que acorta la llamada a JOptionPane.showMessageDialog(..) que crea un mensaje en pantalla con las opciones Yes y No
     * @param title Título que contendrá la ventana creada.
     * @param message Mensaje que contendrá la ventana creada.
     * @param icon Icono que contendrá la ventana creada.
     * @return Selección escogida
     */
    private int showYesNoMessage(String title, String message, String icon) {
        return JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, new ImageIcon(icon));
    }
}
