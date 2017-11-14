package Util;

/**
 *
 * @author Omar
 */
public class Console {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    /**
     * Creadora privada, ya que se usará sin instanciar.
     */
    private Console(){

    }

    /**
     * Método que nos permite imprimir un String con color y un salto de línea, dependiendo del segundo parámetro.
     * @param text El String que se va a imprimir.
     * @param color Indica el color que tendrá el String 'text'.
     */
    public static  void println(String text, String color){
        switch(color){
            case "black":
                System.out.println(ANSI_BLACK + text + ANSI_RESET);
                break;
            case "red":
                System.out.println(ANSI_RED + text + ANSI_RESET);
                break;
            case "green":
                System.out.println(ANSI_GREEN + text + ANSI_RESET);
                break;
            case "yellow":
                System.out.println(ANSI_YELLOW + text + ANSI_RESET);
                break;
            case "blue":
                System.out.println(ANSI_BLUE + text + ANSI_RESET);
                break;
            case "purple":
                System.out.println(ANSI_PURPLE + text + ANSI_RESET);
                break;
            case "cyan":
                System.out.println(ANSI_CYAN + text + ANSI_RESET);
                break;
            case "white":
                System.out.println(ANSI_WHITE + text + ANSI_RESET);
                break;
            default:
                System.out.println(text);
        }
    }

    /**
     * Método que imprime por pantalla el String que se le pasa por parámetro.
     * @param text String que se va a imprimir.
     */
    public static  void println(String text){
        System.out.println(text);
    }

    /**
     * Método que nos permite imprimir un String con color, dependiendo del segundo parámetro.
     * @param text El String que se va a imprimir.
     * @param color Indica el color que tendrá el String 'text'.
     */
    public static  void print(String text, String color){
        switch(color){
            case "black":
                System.out.print(ANSI_BLACK + text + ANSI_RESET);
                break;
            case "red":
                System.out.print(ANSI_RED + text + ANSI_RESET);
                break;
            case "green":
                System.out.print(ANSI_GREEN + text + ANSI_RESET);
                break;
            case "yellow":
                System.out.print(ANSI_YELLOW + text + ANSI_RESET);
                break;
            case "blue":
                System.out.print(ANSI_BLUE + text + ANSI_RESET);
                break;
            case "purple":
                System.out.print(ANSI_PURPLE + text + ANSI_RESET);
                break;
            case "cyan":
                System.out.print(ANSI_CYAN + text + ANSI_RESET);
                break;
            case "white":
                System.out.print(ANSI_WHITE + text + ANSI_RESET);
                break;
        }
    }

    /**
     * Método que imprime por pantalla el String que se le pasa por parámetro.
     * @param text String que se va a imprimir.
     */
    public static  void print(String text){
        System.out.print(text);
    }
}
