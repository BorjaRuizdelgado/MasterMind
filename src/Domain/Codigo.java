package Domain;

import java.util.ArrayList;
import java.util.List;

public class Codigo implements Cloneable{
    public List<Integer> codigo;
    public int size = 0;

    /**
     * Creadora
     * @param size
     */
    public Codigo(int size){
        codigo = new ArrayList<Integer>(size);
        this.size = size;
    }

    /**
     * dado Codigo se compara con el mismo y devuelve la respuesta
     * @param codigoACorregir
     * @return respuesta
     */
    public Respuesta corregir(Codigo codigoACorregir){
        int negras = 0;
        int blancas = 0;
        for(int i = 0; i < size; ++i){
            if(codigoACorregir.codigo.get(i) == this.codigo.get(i)){
                negras++;
            }
        }

        ArrayList<Integer> yaProcesados = new ArrayList<Integer>();
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
                res.respuesta.set(i,8);
                negras--;
            }
            else if(blancas != 0){
                res.respuesta.set(i,7);
                blancas--;
            }
            res.respuesta.set(i,0);
        }
        return res;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}