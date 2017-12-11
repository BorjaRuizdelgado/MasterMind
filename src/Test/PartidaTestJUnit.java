package Test;

import Domain.Codigo;
import Domain.Excepciones.ExcepcionNoHayColoresSinUsar;
import Domain.Excepciones.ExcepcionPistaUsada;
import Domain.Partida;
import Domain.Respuesta;
import org.junit.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import static org.junit.Assert.*;

public class PartidaTestJUnit {

    private static Partida testMaker;
    private static Partida testBreaker;
    private static Partida testFacil;
    private static Partida testMedio;
    private static Partida testDificil;

    @Before
    public  void setUp() throws Exception {
        testMaker = new Partida(true, "Dificil");
        testBreaker = new Partida(false, "Facil");
        testFacil = new Partida(true, "Facil");
        testMedio = new Partida(true, "Medio");
        testDificil = new Partida(true, "Dificil");
    }

    @Test
    public void testGetId() throws Exception {
        String id = "idPartida";
        testFacil.setId(id);
        assertEquals(id,testFacil.getId());
    }

    @Test
    public void testIsRolMaker() throws Exception {
        assertEquals("Partida Maker es rolMaker true", testMaker.isRolMaker(), true);
        assertEquals("Partida Breaker es rolMaker false", testBreaker.isRolMaker(), false);
    }

    @Test
    public void testGetDificultad() throws Exception {
        assertEquals("Partida Facil es Dificultad Facil", testFacil.getDificultad(), "Facil");
        assertEquals("Partida Medio es Dificultad Medio", testMedio.getDificultad(), "Medio");
        assertEquals("Partida Dificil es Dificultad Dificil", testDificil.getDificultad(), "Dificil");
    }

    @Test
    public void testGetNumColores() throws Exception {
        assertEquals("Partida Facil tiene 4 colores", testFacil.getNumColores(), 4);
        assertEquals("Partida Medio tiene 6 colores", testMedio.getNumColores(), 6);
        assertEquals("Partida Dificil tiene 6 colores", testDificil.getNumColores(), 6);
    }

    @Test
    public void testGetNumColumnas() throws Exception {
        assertEquals("Partida Facil tiene 4 columnas", testFacil.getNumColumnas(), 4);
        assertEquals("Partida Medio tiene 4 columnas", testMedio.getNumColumnas(), 4);
        assertEquals("Partida Dificil tiene 6 columnas", testDificil.getNumColumnas(), 6);
    }

    @Test
    public void testIsGanado() throws Exception {
        assertFalse(testFacil.isGanado());
        Codigo codigoSecreto = new Codigo(testFacil.getNumColumnas());
        Random rn = new Random();
        for (int i = 0; i < testFacil.getNumColumnas(); i++)
            codigoSecreto.codigo.add(rn.nextInt(testFacil.getNumColores()) + 1);
        testFacil.setCodigoSecreto(codigoSecreto);
        while (!testFacil.isGanado() && testFacil.getNumeroFilaActual() < 15) {
            //hacemos que encuentre el código secreto
            testFacil.generaSiguienteIntento();
            testFacil.generaRespuesta();
        }
        assertTrue("Ha encontrado la respuesta porque el código era posible",testFacil.isGanado());


        assertFalse(testMedio.isGanado());
        codigoSecreto = new Codigo(testMedio.getNumColumnas());
        for (int i = 0; i < testMedio.getNumColumnas(); i++)
            codigoSecreto.codigo.add(7);
        testMedio.setCodigoSecreto(codigoSecreto);
        while (!testMedio.isGanado() && testMedio.getNumeroFilaActual() < 15) {
            //hacemos no que encuentre el código secreto porque tiene un codigo no posible.
            testMedio.generaSiguienteIntento();
            testMedio.generaRespuesta();
        }
        assertFalse("No ha encontrado la respuesta porque el código no era posible",testMedio.isGanado());

    }

    @Test
    public void testGetNumeroFilaActual() throws Exception {
        assertEquals(testMaker.getNumeroFilaActual(),0);
        Codigo codigoSecreto = new Codigo(testMaker.getNumColumnas());
        Random rn = new Random();
        for (int i = 0; i < testMaker.getNumColumnas(); i++)
            codigoSecreto.codigo.add(rn.nextInt(testMaker.getNumColores()) + 1);
        testMaker.setCodigoSecreto(codigoSecreto);
        testMaker.generaSiguienteIntento();
        testMaker.generaRespuesta();
        assertEquals(testMaker.getNumeroFilaActual(),1);
    }

    @Test
    public void testSetyGetCodigoSecreto() throws Exception {
        Codigo codigoSecreto = new Codigo(testBreaker.getNumColumnas());
        for (int i = 0; i < testBreaker.getNumColumnas(); i++)
            codigoSecreto.codigo.add(7);
        testBreaker.setCodigoSecreto(codigoSecreto);
        assertEquals(testBreaker.getCodigoSecreto().codigo,codigoSecreto.codigo);
    }


    @Test
    public void testGetPista1() throws Exception {
        Codigo secretcode = testBreaker.getCodigoSecreto();
            try {
                int a = testBreaker.getPista1();
                for (int i = 0; i < testBreaker.getNumColumnas(); i++) {
                    assertFalse( a == secretcode.codigo.get(i));
                }

                try {
                    testBreaker.getPista1();
                    fail("No ha lanzado la excepción");
                } catch (ExcepcionPistaUsada e) {
                    // ha lanzado excepcion
                }
            } catch (ExcepcionNoHayColoresSinUsar e) {
                //comprobamos que se usan todos los colores
                if (!testBreaker.getDificultad().equals("Medio")) {
                    Codigo comprueba = new Codigo(testBreaker.getNumColumnas());
                    for (int i = 0; i < testBreaker.getNumColumnas(); i++) {
                        comprueba.codigo.add(i + 1);
                    }
                    assertTrue(testBreaker.getCodigoSecreto().codigo.containsAll(comprueba.codigo));
                }
                //el medio nunca usará todos los colores
            }

    }

    @Test
    public void testGetPista2() throws Exception {
        Codigo secretcode = testBreaker.getCodigoSecreto();
        try {
            ArrayList<Integer> a = testBreaker.getPista2();
            for (int i = 0; i < testBreaker.getNumColumnas(); i++) {
                for (int j = 0; j < a.size(); j++)
                    assertFalse(a.get(j).equals(secretcode.codigo.get(i)));
            }

            try {
                testBreaker.getPista2();
                fail("No ha lanzado la excepción");
            } catch (ExcepcionPistaUsada e) {
                // ha lanzado excepcion
            }
        }
        catch (ExcepcionNoHayColoresSinUsar e) {
            //comprobamos que se usan todos los colores
            if (!testBreaker.getDificultad().equals("Medio")) {
                Codigo comprueba = new Codigo(testBreaker.getNumColumnas());
                for (int i = 0; i < testBreaker.getNumColumnas(); i++) {
                    comprueba.codigo.add(i + 1);
                }
                assertTrue(testBreaker.getCodigoSecreto().codigo.containsAll(comprueba.codigo));
            }
            //el medio nunca usará todos los colores
        }
    }

    @Test
    public void testGetPista3() throws Exception {
        Codigo secretcode = testBreaker.getCodigoSecreto();
        /*Codigo a = testBreaker.getPista3();
        for (int i = 0; i < testBreaker.getNumColumnas(); i++) {
            if (a.codigo.get(i) != 0)
                assertEquals(a.codigo.get(i), secretcode.codigo.get(i));
        }*/

        try {
            testBreaker.getPista3();
            fail("No ha lanzado la excepción");
        } catch (ExcepcionPistaUsada e) {
            // ha lanzado excepcion
        }
    }

    @Test
    public void testGeneraPuntuacion() throws Exception {
        // todo probar que si hago una pista es 0, y de otra manera es >0. O si la gano. etc.


        Codigo codigoSecreto = new Codigo(testFacil.getNumColumnas());
        Random rn = new Random();
        for (int i = 0; i < testFacil.getNumColumnas(); i++)
            codigoSecreto.codigo.add(rn.nextInt(testFacil.getNumColores()) + 1);
        testFacil.setCodigoSecreto(codigoSecreto);
        while (!testFacil.isGanado() && testFacil.getNumeroFilaActual() < 15) {
            //hacemos que encuentre el código secreto
            testFacil.generaSiguienteIntento();
            testFacil.generaRespuesta();
            testFacil.sumaTiempo(4.0f);
        }
        assertTrue("Como ha ganado, tendrá puntuación.", testFacil.generaPuntuacion()>0);



        codigoSecreto = new Codigo(testMedio.getNumColumnas());
        for (int i = 0; i < testMedio.getNumColumnas(); i++)
            codigoSecreto.codigo.add(7);
        testMedio.setCodigoSecreto(codigoSecreto);
        while (!testMedio.isGanado() && testMedio.getNumeroFilaActual() < 15) {
            //hacemos no que encuentre el código secreto porque tiene un codigo no posible.
            testMedio.generaSiguienteIntento();
            testMedio.generaRespuesta();
        }
        assertEquals("No ha encontrado la respuesta así que tendrá que ser 0",testMedio.generaPuntuacion(), 0);

        codigoSecreto = new Codigo(testDificil.getNumColumnas());
        for (int i = 0; i < testDificil.getNumColumnas(); i++)
            codigoSecreto.codigo.add(1);
        testDificil.setCodigoSecreto(codigoSecreto);
        testDificil.getPista2();
        while (!testDificil.isGanado() && testDificil.getNumeroFilaActual() < 15) {
            //hacemos no que encuentre el código secreto porque tiene un codigo no posible.
            testDificil.generaSiguienteIntento();
            testDificil.generaRespuesta();
        }
        assertEquals("Ha usado una pista así que debe ser 0", testDificil.generaPuntuacion(), 0);
    }

    @Test
    public void testSumayGetTiempo() throws Exception {
        assertEquals(testBreaker.getTiempo(), 0.0f, 0.00);
        testBreaker.sumaTiempo(4.0f);
        assertEquals(testBreaker.getTiempo(), 4.0f, 0.00);
    }

    @Test
    public void testSetyGetRespuesta() throws Exception {
        Codigo secretcode = testBreaker.getCodigoSecreto();
        Codigo code = new Codigo(testBreaker.getNumColumnas());
        for (int i = 0; i < testBreaker.getNumColumnas(); i++)
            code.codigo.add(2);
        Respuesta r = code.getRespuesta(secretcode);
        testBreaker.setIntento(code);
        testBreaker.setRespuesta(r);
        //si no lanza excepción es que efectivamente la respuesta generada es correcta
        assertEquals(testBreaker.getUltimaRespuesta().respuesta, r.respuesta);
    }

}