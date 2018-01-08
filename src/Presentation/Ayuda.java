package Presentation;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

public class Ayuda {

    private final String pathGifCB;
    private final String pathPicCM;
    private JPanel panel;
    private JPanel panel2;
    private JTabbedPane tabbedPane1;
    private JTextPane textGeneral;
    private JTextPane textCB;
    private JTextPane textCM;
    private JTextPane textAjustes;
    private JScrollPane scrollGeneral;
    private JTextPane textAbout;

    private String nl = "\n";

    public JPanel getPanel() {
        return panel;
    }

    public Ayuda() {
        pathGifCB = System.getProperty("user.dir") + "/src/imgs/picGifCB.gif";
        pathPicCM = System.getProperty("user.dir") + "/src/imgs/picCM.png";
        createTextPane(textGeneral,getTextGeneral(),getStylesGeneral());
        createTextPane(textCB,getTextCB(),getStylesCB());
        createTextPane(textCM,getTextCM(),getStylesCM());
        createTextPane(textAjustes,getTextAjustes(),getStylesAjustes());
        createTextPane(textAbout,getTextAbout(),getStylesAbout());

    }

    private void createTextPane(JTextPane textPane, String[] texto, String[] estilos) {
        StyledDocument doc = textPane.getStyledDocument();
        addStylesToDocument(doc);

        try {
            for (int i = 0; i < texto.length; i++) {
                doc.insertString(doc.getLength(), texto[i], doc.getStyle(estilos[i]));
            }
        } catch (BadLocationException e) {
            System.err.println("Couldn't insert initial text into text pane.");
        }
    }

    private void addStylesToDocument(StyledDocument doc) {
        Style def = StyleContext.getDefaultStyleContext().
                getStyle(StyleContext.DEFAULT_STYLE);

        Style regular = doc.addStyle("regular", def);
        StyleConstants.setFontFamily(def, "SansSerif");
        StyleConstants.setForeground(def,Color.BLACK);
        StyleConstants.setFontSize(def,14);

        Style s = doc.addStyle("italic", regular);
        StyleConstants.setItalic(s, true);

        s = doc.addStyle("bold", regular);
        StyleConstants.setBold(s, true);

        s = doc.addStyle("underline", regular);
        StyleConstants.setUnderline(s, true);

        s = doc.addStyle("title", regular);
        StyleConstants.setBold(s, true);
        StyleConstants.setForeground(s,new Color(192,55,55));
        StyleConstants.setFontSize(s,16);

        s = doc.addStyle("gifCB", regular);
        StyleConstants.setAlignment(s, StyleConstants.ALIGN_CENTER);
        ImageIcon gifCB = createImageIcon(pathGifCB,
                "CodeBreaker");
        StyleConstants.setIcon(s, gifCB);

        s = doc.addStyle("picCM", regular);
        StyleConstants.setAlignment(s, StyleConstants.ALIGN_CENTER);
        ImageIcon picCM = createImageIcon(pathPicCM,
                "CodeMaker");
        StyleConstants.setIcon(s, picCM);


    }

    private static ImageIcon createImageIcon(String path, String description) {
        return new ImageIcon(path, description);
    }






    /* GENERAL */

    private String[] getTextGeneral() {
        return new String[]{
                "Información General" + nl,     //title
                "MasterMindo ",                 //bold
                "es un juego de ingenio y reflexión para un jugador en el que se juega contra la máquina para descubrir su ",   //regular
                "código secreto ",              //italic
                "(CodeBreaker) o que ella descubra el tuyo (CodeMaker)." + nl
                        +"Se juega en un tablero de 12 filas, partido en dos partes: intentos y respuestas." + nl  +"Cada ",     //regular
                "intento ",                     //italic
                "es una nueva prueba de adivinar el código secreto que recibe una ",                                            //regular
                "respuesta ",                    //italic
                "que compara el código secreto con el intento y aporta información sobre si se ha acertado un color en su posición (color negro) "
                        +"o si hay un color bien pero no en su posición (color blanco)."+nl
                        +"Si no se consigue descubrir el código en los 12 intentos, se termina la partida."+nl
                        +"Existen tres tipos de dificultad:"+nl+"   · ",                                                         //regular
                "Fácil",                        //underline
                ": con 4 columnas y 4 colores."+nl+"   · ",                                          //regular
                "Medio",                        //underline
                ": con 4 columnas y 6 colores."+nl+"   · ",                                          //regular
                "Difícil",                        //underline
                ": con 6 columnas y 6 colores."+nl+nl,                                          //regular
                "Se pueden crear usuarios que se guardarán en el sistema y con los que podrás volver a jugar cada vez que desees."+nl
                        +"Puedes crear y guardar partidas para continuar en otro momento."+nl,                                    //regular
                "Si ganas partidas te posicionarás en el ránking y podrás visualizarlo cuando quieras, al igual que tus estadísticas en tu perfil. "
                        +"Cada partida se puede abandonar, reiniciar o guardar. Y si te quedas atascado puedes pedir pistas."     //regular
                +nl+nl+"Si te equivocas colocando un color, pulsa botón derecho para eliminarlo."
        };
    }

    private String[] getStylesGeneral() {
        return new String[]{
                "title",
                "bold",
                "regular",
                "bold",
                "regular",
                "bold",
                "regular",
                "bold",
                "regular",
                "underline",
                "regular",
                "underline",
                "regular",
                "underline",
                "regular",
                "regular",
                "regular"
        };
    }


    /* CODEBREAKER */

    private String[] getTextCB() {
        return new String[]{
                "Cómo jugar a CodeBreaker" + nl,        //title
                "Debes intentar descubrir el ",           //regular
                "código secreto",       //bold
                " de la máquina. Tienes ",          //regular
                "12 intentos",      //bold
                " para adivinarlo."+nl
                        +"Selecciona el color que quieras y pulsa en la casilla que desees rellenar de ese color."+nl+nl,           //regular
                nl, //picGifCB
                nl + "A la derecha encontrarás la respuesta a tu intento según el código secreto."+nl+" · Si recibes una ficha ",           //regular
                "negra",        //bold
                " es que tienes un color bien colocado."+nl+" · Si recibes una ficha ",          //regular
                "blanca",       //bold
                " es que tienes un color mal colocado." +nl+" · No recibes nada si el color es incorrecto."          //regular
        };
    }

    private String[] getStylesCB() {
        return new String[]{
                "title",
                "regular",
                "bold",
                "regular",
                "bold",
                "regular",
                "gifCB",
                "regular",
                "bold",
                "regular",
                "bold",
                "regular"
        };
    }


    /* CODEMAKER */

    private String[] getTextCM() {
        return new String[]{
                "Cómo jugar a CodeMaker" + nl,        //title
                "La máquina intentará descubrir tu ",           //regular
                "código secreto",       //bold
                ". Tiene ",          //regular
                "12 intentos",      //bold
                " para adivinarlo."+nl
                        +"Deberás dar respuesta a sus intentos con fichas negras y blancas."+nl
                        +"Selecciona el color que quieras y pulsa en la casilla que desees rellenar de ese color."+nl+nl,           //regular
                nl, //picCM
                nl+" · Si colocas una ficha ",           //regular
                "negra",        //bold
                " es porque tiene un color bien colocado."+nl+" · Si colocas una ficha ",          //regular
                "blanca",       //bold
                " es porque tiene un color mal colocado."+nl+" · No colocas nada si el color es incorrecto."          //regular

        };
    }

    private String[] getStylesCM() {
        return new String[]{
                "title",
                "regular",
                "bold",
                "regular",
                "bold",
                "regular",
                "picCM",
                "regular",
                "bold",
                "regular",
                "bold",
                "regular"
        };
    }


    /* AJUSTES */

    private String[] getTextAjustes() {
        return new String[]{
                "Modificar ajustes" + nl,
                "En el botón de ajustes puedes modificar el volumen de la música o silenciarla por completo."+nl
                +"También puedes modificar tu nombre de usuario. Esto afectará también a tus puntuaciones en el ránking."+nl
                +"Es posible también reiniciar las estadísticas, borrando todos los datos de tu perfil y puntuaciones excepto tus partidas guardadas."+nl
                +"Finalmente, si deseas dejar de jugar, puedes borrar tu usuario y todos sus datos."
        };
    }

    private String[] getStylesAjustes() {
        return new String[]{
                "title",
                "regular"
        };
    }


    /* ABOUT */

    private String[] getTextAbout() {
        return new String[]{
                "Sobre Nosotros" + nl,
                "Mastermindo es un juego interactivo " +
                        "creado por tres estudiantes de PROP de la FIB " +
                        "con el fin de sacar un excelente en la asignatura." + nl + nl,
                "Equipo:" + nl,
                "· Isabel Codina García" + nl,
                "· Borja Fernández Ruizdelgado" + nl,
                "· Omar Aníbal García Ríos"
        };
    }

    private String[] getStylesAbout() {
        return new String[]{
                "title",
                "regular",
                "bold",
                "regular",
                "regular",
                "regular"
        };
    }



}
