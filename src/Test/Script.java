package Test;

import Domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Clase Script que calcula el tiempo e intentos de las inteligencias
 * @author Omar
 */

public class Script {

    private enum modo{
        facil, medio, dificil;
    }

    private enum tipoInteligencia{
        cerebro, MasterCerebro;
    }

    private static List<Double> calculate(int numeroIntentos, modo modeUsado, tipoInteligencia tipoInteligencia){
        List<Double> Result = new ArrayList<>();
        Inteligencia ia = null;
        int numColores, numColumnas;
        if (modeUsado == modo.facil) {
            numColores = 4;
            numColumnas = 4;
        }
        else if(modeUsado == modo.medio) {
            numColores = 6;
            numColumnas = 4;
        }
        else {
            numColores = 6;
            numColumnas = 6;
        }

        long tiempoTotal = 0;
        double intentosTotales = 0;
        for (int i = 0; i < numeroIntentos; i++) {
            if (tipoInteligencia == Script.tipoInteligencia.cerebro) ia = new Cerebro(numColores, numColumnas);
            else ia = new MasterCerebro(numColores, numColumnas);

            Codigo codigoSecreto = new Codigo(numColumnas);
            List<Integer> aux = new ArrayList<>();
            for (int j = 0; j < numColumnas; j++) {
                aux.add(new Random().nextInt(numColores) + 1);
            }
            codigoSecreto.codigo = aux;
            //System.out.println(aux + "--");

            boolean first = true;
            Codigo last = null;
            long start = System.currentTimeMillis();
            int intentosPartida = 0;
            while (first || !last.equals(codigoSecreto)){
                if (first){
                    last = ia.getIntentoInicial();
                }
                else{
                    Fila ultimoIntento = new Fila(numColumnas);
                    ultimoIntento.setColores(last);
                    ultimoIntento.setRespuestas(codigoSecreto.getRespuesta(last));

                    last = ia.getSiguienteIntento(ultimoIntento);
                }
                //System.out.println(last.codigo);
                first = false;
                intentosPartida++;
            }
            long end = System.currentTimeMillis();
            tiempoTotal += (end - start);
            //System.out.println(tiempoTotal);
            intentosTotales += intentosPartida;
        }
        Result.add(Long.valueOf(tiempoTotal).doubleValue()/numeroIntentos);
        Result.add(intentosTotales/numeroIntentos);
        return Result;
    }

    private static void showResult(List<Double> result, modo modoUsado){
        System.out.println("Media en modo " + modoUsado.toString() + ":");
        System.out.println("Tiempo: " + result.get(0));
        System.out.println("Intentos: " + Double.valueOf(result.get(1)));
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int intentosFacil;
        int intentosMedio;
        int intentosDificil;
        System.out.print("Numero de intentos modo fácil: ");
        intentosFacil = in.nextInt();
        System.out.print("Numero de intentos modo medio: ");
        intentosMedio = in.nextInt();
        System.out.print("Numero de intentos modo difícil: ");
        intentosDificil = in.nextInt();

        System.out.println("CEREBRO");
        List<Double> resultadoFacilCerebro = calculate(intentosFacil, modo.facil, tipoInteligencia.cerebro);
        showResult(resultadoFacilCerebro, modo.facil);

        List<Double> resultadoMedioCerebro = calculate(intentosMedio, modo.medio, tipoInteligencia.cerebro);
        showResult(resultadoMedioCerebro, modo.medio);

        List<Double> resultadoDificilCerebro = calculate(intentosDificil, modo.dificil, tipoInteligencia.cerebro);
        showResult(resultadoDificilCerebro, modo.dificil);

        System.out.println("MASTERCEREBRO");
        List<Double> facilMasterCerebro = calculate(intentosFacil, modo.facil, tipoInteligencia.MasterCerebro);
        showResult(facilMasterCerebro, modo.facil);

        List<Double> medioMasterCerebro = calculate(intentosMedio, modo.medio, tipoInteligencia.MasterCerebro);
        showResult(medioMasterCerebro, modo.medio);

        List<Double> dificilMasterCerebro = calculate(intentosDificil, modo.dificil, tipoInteligencia.MasterCerebro);
        showResult(dificilMasterCerebro, modo.dificil);
    }
}
