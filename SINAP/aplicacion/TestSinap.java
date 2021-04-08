package aplicacion;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
