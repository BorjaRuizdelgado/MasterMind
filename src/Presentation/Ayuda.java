package Presentation;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

public class Ayuda {

    private JPanel panel;
    private JPanel panel2;
    private JTabbedPane tabbedPane1;
    private JTextPane textGeneral;
    private JTextPane textCB;
    private JTextPane textCM;
    private JTextPane textAjustes;
    private JScrollPane scrollGeneral;
    private JTextPane textAbout;

    private String pathicon;
    private String nl = "\n";

    public JPanel getPanel() {
        return panel;
    }

    public Ayuda() {
        pathicon = System.getProperty("user.dir") + "/src/imgs/delete-red.png";
        createTextPane(textGeneral,getTextGeneral(),getStylesGeneral());
        createTextPane(textCB,getTextCB(),getStylesCB());
        createTextPane(textCM,getTextCM(),getStylesCM());
        createTextPane(textAjustes,getTextAjustes(),getStylesAjustes());
        createTextPane(textAbout,getTextAbout(),getStylesAbout());

    }

    private void createTextPane(JTextPane textPane, String[] text, String[] styles) {
        StyledDocument doc = textPane.getStyledDocument();
        addStylesToDocument(doc);

        try {
            for (int i = 0; i < text.length; i++) {
                doc.insertString(doc.getLength(), text[i], doc.getStyle(styles[i]));
            }
        } catch (BadLocationException ble) {
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

        s = doc.addStyle("title", regular);
        StyleConstants.setBold(s, true);
        StyleConstants.setForeground(s,new Color(192,55,55));
        StyleConstants.setFontSize(s,16);

        s = doc.addStyle("pic", regular);
        StyleConstants.setAlignment(s, StyleConstants.ALIGN_CENTER);
        ImageIcon pigIcon = createImageIcon(pathicon,
                "nice pic m8");
        StyleConstants.setIcon(s, pigIcon);


    }

    private static ImageIcon createImageIcon(String path, String description) {
        return new ImageIcon(path, description);
    }






    /* GENERAL */

    private String[] getTextGeneral() {
        return new String[]{
                "Información General" + nl,
                "bla bla blucho"
        };
    }

    private String[] getStylesGeneral() {
        return new String[]{
                "title",
                "italic"
        };
    }


    /* CODEBREAKER */

    private String[] getTextCB() {
        return new String[]{
                "Cómo jugar a CodeBreaker" + nl,
                "primerito texto."
        };
    }

    private String[] getStylesCB() {
        return new String[]{
                "title",
                "italic"
        };
    }


    /* CODEMAKER */

    private String[] getTextCM() {
        return new String[]{
                "Cómo jugar a CodeMaker" + nl,                 //bold
                "primerito texto."      //italic
        };
    }

    private String[] getStylesCM() {
        return new String[]{
                "title",
                "italic"
        };
    }


    /* AJUSTES */

    private String[] getTextAjustes() {
        return new String[]{
                "Modificar ajustes" + nl,                 //bold
                "primerito texto."      //italic
        };
    }

    private String[] getStylesAjustes() {
        return new String[]{
                "title",
                "italic"
        };
    }


    /* ABOUT */

    private String[] getTextAbout() {
        return new String[]{
                "Sobre Nosotros" + nl,
                "isa" + nl,
                "omar" + nl,
                "borja" + nl,
                nl
        };
    }

    private String[] getStylesAbout() {
        return new String[]{
                "title",
                "regular",
                "regular",
                "regular",
                "pic"
        };
    }



}
