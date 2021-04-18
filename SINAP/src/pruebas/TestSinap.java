package src.pruebas;
import src.aplicacion.*;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class TestSinap {

        Sinap sinap;

        /**
         * Default constructor for test class MissionTestC2
         */
        public TestSinap()
        {
        }

        /**
         * Sets up the test fixture.
         *
         * Called before every test case method.
         */
        @Before
        public void setUp()
        {
            sinap = new Sinap();
        }

        @Test
        public void adicioneTest() throws SINAPExcepcion {
            sinap.adicione("Monserrate", "Monserrate", "Bogotá", "200.000", "Cerro tradicional de Bogotá");

            assertEquals(1, sinap.getAreas().size());
        }

        @Test
        public void adicioneSinIntTest(){
            try {
                sinap.adicione("Monserrate", "", "Bogotá", "200.000", "Cerro tradicional de Bogotá");
            }catch (SINAPExcepcion e){
                assertEquals(SINAPExcepcion.SIN_NOMBRE_INT, e.getMessage());
            }
        }

        @Test
        public void adicioneRepetidoTest(){
            sinap.adicioneCinco();
            try {
                sinap.adicione("Nevado del Huila", "Nevado del Huila", "Huila", "500.000",
                        "Uno de los nevados más importantes del país");
            }catch (SINAPExcepcion e){
                assertEquals(SINAPExcepcion.NOMBRE_REPETIDO, e.getMessage());
            }
        }

        @Test
        public void adicioneSinUbiTest(){
            try {
                sinap.adicione("Monserrate", "Monserrate", "", "200.000", "Cerro tradicional de Bogotá");
            }catch (SINAPExcepcion e){
                assertEquals(SINAPExcepcion.SIN_UBICACION, e.getMessage());
            }
        }

        @Test
        public void adicioneMuchoTextoTest(){
            try {
                sinap.adicione("Monserrate", "Monserrate", "Bogotá", "200.000",
                        "Cerro tradicional de Bogotá Far far away, behind the word mountains, far from the" +
                                " countries Vokalia and Consonantia, there live the blind texts. Separated they live in" +
                                " Bookmarksgrove right at the coast of the Semantics, a large language ocean. A small " +
                                "river named Duden flows by their place and supplies it with the necessary regelialia. " +
                                "It is a paradisematic country, in which roasted parts of sentences fly into your mouth." +
                                " Even the all-powerful Pointing has no control about the blind texts it is an almost " +
                                "unorthographic life One day however a small line of blind text by the name of Lorem" +
                                " Ipsum decided to leave for the far World of Grammar. The Big Oxmox advised her not to " +
                                "do so, because there were thousands of bad Commas, wild Question Marks and devious" +
                                " Semikoli, but the Little Blind Text didn’t listen. She packed her seven versalia, " +
                                "put her initial into the belt and made herself on the way. When she reached the first" +
                                " hills of the Italic Mountains, she had a last view back on the skyline of her" +
                                " hometown Bookmarksg");

            }catch (SINAPExcepcion e){
                assertEquals(SINAPExcepcion.DESCRIPCION_MUY_LARGA, e.getMessage());
            }
        }

        @Test
        public void adicioneDetallesTest() throws SINAPExcepcion {

            Area nueva = new Area("Monserrate", "Monserrate", "Bogotá", "200.000", "Cerro tradicional de Bogotá");
            sinap.adicioneDetalles(nueva);

            assertEquals("Monserrate", sinap.getAreas().peek().getNombre());
            assertEquals("Monserrate", sinap.getAreas().peek().getName());
            assertEquals("Bogotá", sinap.getAreas().peek().getUbicacion());
            assertEquals("200.000", sinap.getAreas().peek().getArea());
            assertEquals("Cerro tradicional de Bogotá", sinap.getAreas().peek().getDescripcion());
        }

        @Test
        public void toStringTest() throws SINAPExcepcion {
                sinap.adicione("Monserrate", "Monserrate", "Bogotá", "200.000", "Cerro tradicional de Bogotá");
                assertEquals("Monserrate\nMonserrate\nCerro tradicional de Bogotá\n\n", sinap.toString());

        }

        @Test
        public void buscarTest() throws SINAPExcepcion {
            sinap.adicioneCinco();
            ArrayList<Area> resultados = this.sinap.busque("Mor");
            assertEquals(1, resultados.size());

            sinap.adicione("Monserrate", "Monserrate", "Bogotá", "200.000", "Cerro tradicional de Bogotá");
            resultados = this.sinap.busque("Mo");
            assertEquals(2, resultados.size());
        }

        @Test
        public void notFoundTest(){
            sinap.adicioneCinco();
            ArrayList<Area> resultados = this.sinap.busque("T");
            assertEquals(0, resultados.size());
        }

        /**
         * Tears down the test fixture.
         *
         * Called after every test case method.
         */
        @After
        public void tearDown()
        {
        }

}
