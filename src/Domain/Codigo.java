package Domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Clase Codigo
 * Estructura de datos para los codigos de los intentos
 * @author borja
 */
public class Codigo implements Serializable{
    public List<Integer> codigo;
    public int size = 0;

    /**
     * Creadora Codigo.
     * Crea un vector de tamaño pasado por parámetro
     * @param size numero de columnas del tablero
     */
    public Codigo(int size){
        codigo = new ArrayList<>(size);
        this.size = size;
    }

    /**
     * Se compara con el codigo a corregir y devuelve la respuesta.
     * @param codigoACorregir codigo a comparar
     * @return respuesta de la comparación
     */
    public Respuesta getRespuesta(Codigo codigoACorregir){
        int negras = 0;
        int blancas = 0;

        for(int i = 0; i < size; ++i){
            if(codigoACorregir.codigo.get(i) == this.codigo.get(i)){
                negras++;
            }
        }

        ArrayList<Integer> yaProcesados = new ArrayList<>();
        for(int color : this.codigo){
            for(int i = 0; i < size; ++i){
                if(color == codigoACorregir.codigo.get(i) && !yaProcesados.contains(i)){
                    yaProcesados.add(i);
                    ++blancas;
                    break;
                }
            }
        }

        blancas -= negras;
        Respuesta res = new Respuesta(size);

        for(int i = 0; i < size;++i){
            if(negras != 0){
                res.respuesta.add(8);
                negras--;
            }
            else if(blancas != 0){
                res.respuesta.add(7);
                blancas--;
            }
            else res.respuesta.add(0);
        }
        return res;
    }

    /**
     * Comprueba si dos Codigos son iguales
     * @param obj Codigo a comprobar.
     * @return Retorna cierto si son iguales y falso si no lo son.
     */
    public boolean equals(Codigo obj) {
        return codigo.equals(obj.codigo);
    }
}