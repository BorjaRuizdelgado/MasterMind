package Domain;

import java.util.ArrayList;
import java.util.List;

/**
 * @author borja
 */

/**
 * Estructura de datos para los intentos de Mastermind
 */
public class Codigo {
    public List<Integer> codigo;
    public int size = 0;

    /**
     * Creadora.
     * @param size
     */
    public Codigo(int size){
        codigo = new ArrayList<>(size);
        this.size = size;
    }

    /**
     * Dado Codigo se compara con el mismo y devuelve la respuesta.
     * @param codigoACorregir
     * @return respuesta
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
     * Comprueba si dos objetos de tipo Codigo son iguales
     * @param obj Codigo a comprobar.
     * @return Retorna cierto si son iguales y falso si no lo son.
     */
    public boolean equals(Codigo obj) {
        return codigo.equals(obj.codigo);
    }
}