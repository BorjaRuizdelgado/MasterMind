package Test;

import Domain.Codigo;
import Domain.Partida;
import Domain.Respuesta;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

public class PartidaTest {

    private static Partida testMaker;
    private static Partida testBreaker;
    private static Partida testFacil;
    private static Partida testMedio;
    private static Partida testDificil;

    @org.junit.BeforeClass
    public static void setUp() throws Exception {
        testMaker = new Partida(true, "Dificil");
        testBreaker = new Partida(false, "Dificil");
        testFacil = new Partida(true, "Facil");
        testMedio = new Partida(true, "Medio");
        testDificil = new Partida(true, "Dificil");

        testFacil.generaCodigoSecretoAleatorio();
        testMedio.generaCodigoSecretoAleatorio();
        testDificil.generaCodigoSecretoAleatorio();

    }

    @org.junit.AfterClass
    public static void tearDown() throws Exception {
    }

    @org.junit.Test
    public void testIsRolMaker() throws Exception {
        assertEquals("Partida Maker es rolMaker true", testMaker.isRolMaker(), true);
        assertEquals("Partida Breaker es rolMaker false", testBreaker.isRolMaker(), false);
    }

    @org.junit.Test
    public void testGetDificultad() throws Exception {
        assertEquals("Partida Facil es Dificultad Facil", testFacil.getDificultad(), "Facil");
        assertEquals("Partida Medio es Dificultad Medio", testMedio.getDificultad(), "Medio");
        assertEquals("Partida Dificil es Dificultad Dificil", testDificil.getDificultad(), "Dificil");
    }

    @org.junit.Test
    public void testGetNumColores() throws Exception {
        assertEquals("Partida Facil tiene 4 colores", testFacil.getNumColores(), 4);
        assertEquals("Partida Medio tiene 6 colores", testMedio.getNumColores(), 6);
        assertEquals("Partida Dificil tiene 6 colores", testDificil.getNumColores(), 6);
    }

    @org.junit.Test
    public void testGetNumColumnas() throws Exception {
        assertEquals("Partida Facil tiene 4 columnas", testFacil.getNumColumnas(), 4);
        assertEquals("Partida Medio tiene 4 columnas", testMedio.getNumColumnas(), 4);
        assertEquals("Partida Dificil tiene 6 columnas", testDificil.getNumColumnas(), 6);
    }

    @org.junit.Test
    public void isGanado() throws Exception {
    }

    @org.junit.Test
    public void testSumaTiempo() throws Exception {
    }

    @org.junit.Test
    public void testGetCodigoSecreto() throws Exception {
    }

    @org.junit.Test
    public void getNumeroFilaActual() throws Exception {
    }

    @org.junit.Test
    public void generaCodigoSecretoAleatorio() throws Exception {
    }

    @org.junit.Test
    public void getSiguienteIntento() throws Exception {
    }

    @org.junit.Test
    public void generaPuntuacion() throws Exception {
    }

    @org.junit.Test
    public void imprimeInfo() throws Exception {
    }

    @org.junit.Test
    public void imprimeAllInfo() throws Exception {
    }

    @org.junit.Test
    public void generaRespuesta() throws Exception {
    }

    @org.junit.Test
    public void testSetIntento() throws Exception {
        /*Codigo cTestFacil = new Codigo(testFacil.getNumColumnas());
        Codigo cTestMedio = new Codigo(testMedio.getNumColumnas());
        Codigo cTestDificil = new Codigo(testDificil.getNumColumnas());

        for (int i = 0; i < cTestFacil.size; i++) cTestFacil.codigo.add(1);
        for (int i = 0; i < cTestMedio.size; i++) cTestMedio.codigo.add(2);
        for (int i = 0; i < cTestDificil.size; i++) cTestFacil.codigo.add(3);

        testFacil.setIntento(cTestFacil);
        testMedio.setIntento(cTestMedio);
        testDificil.setIntento(cTestDificil);

        assertEquals(testFacil.get);
        */

    }

    @org.junit.Test
    public void testSetRespuestaCorrecta() throws Exception {
        //Respuesta rTestFacil = new Respuesta(testFacil.getNumColumnas());
    }

    @org.junit.Test
    public void testSetRespuesta() throws Exception {
        /*Respuesta cTestFacil = new Respuesta(testFacil.getNumColumnas());
        Respuesta cTestMedio = new Respuesta(testMedio.getNumColumnas());
        Respuesta cTestDificil = new Respuesta(testDificil.getNumColumnas());

        for (int i = 0; i < cTestFacil.size; i++) cTestFacil.respuesta.add(1);
        for (int i = 0; i < cTestMedio.size; i++) cTestMedio.respuesta.add(2);
        for (int i = 0; i < cTestDificil.size; i++) cTestFacil.respuesta.add(3);

        testFacil.setRespuesta(cTestFacil);
        testMedio.setRespuesta(cTestMedio);
        testDificil.setRespuesta(cTestDificil);
        */


    }

    @org.junit.Test
    public void getUltimaRespuesta() throws Exception {
    }

    @org.junit.Test
    public void getPista1() throws Exception {
    }

    @org.junit.Test
    public void getPista2() throws Exception {
    }

    @org.junit.Test
    public void getPista3() throws Exception {
    }

}