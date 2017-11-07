package Util;

import Domain.Codigo;
/**
 *
 * @author Omar
 */
public class Comparator implements java.util.Comparator<Codigo> {

    private int getOp(int size){
        String Return = "1";
        for (int i = 0; i < size-1; i++) {
            Return += "0";
        }
        return Integer.valueOf(Return);
    }

    private int toNumber(Codigo code){
        int Return = 0;
        int op = getOp(code.codigo.size());
        for (int i = 0; i < code.codigo.size(); i++) {
            Return += code.codigo.get(i)*op;
            op /= 10;
        }
        return Return;
    }

    @Override
    public int compare(Codigo o1, Codigo o2) {
        int numA = toNumber(o1);
        int numB = toNumber(o2);
        return numA - numB;
    }
}
