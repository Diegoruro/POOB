package pruebas;
import domain.*;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.awt.Color;

/**
 */
public class TestCelulas
{
    /**
     * Default constructor for test class TestCelulas
     */
    public TestCelulas()
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
        
    }
    
    @Test
    public void normalTest(){
        AutomataCelular automata = new AutomataCelular();
        
        //Caso 1
        CelulaNormal indiana = new CelulaNormal(automata,1,1);
        CelulaNormal oo7 = new CelulaNormal(automata,2,2);
        automata.ticTac();
        
        assertTrue(indiana.isVivo());
        assertTrue(oo7.isVivo());
        
        //Caso 2
        CelulaNormal cel1 = new CelulaNormal(automata,5,5);
        CelulaNormal cel2 = new CelulaNormal(automata,3,3);
        automata.ticTac();
        automata.ticTac();
        automata.ticTac();
        automata.ticTac();
        
        assertFalse(cel1.isVivo());
        assertFalse(cel2.isVivo());
    }
    
    @Test
    public void especialTest(){
        AutomataCelular automata = new AutomataCelular();
        
        //Caso 1
        CelulaEspecial agamenon = new CelulaEspecial(automata,5,1);
        CelulaEspecial venus = new CelulaEspecial(automata,8,1);
        automata.ticTac();
        
        assertTrue(agamenon.isVivo());
        assertTrue(venus.isVivo());
        
        //Caso 2
        automata.ticTac();
        
        assertTrue(automata.getElemento(4,0).isVivo());              
        assertTrue(automata.getElemento(7,0).isVivo());
    }
    
    @Test
    public void calefactorTest(){
        AutomataCelular automata = new AutomataCelular();
        
        //Caso 1
        Calefactor suroeste = new Calefactor(automata,29,29);
        assertEquals(Color.red, suroeste.color);
        automata.ticTac();
        assertEquals(Color.yellow, suroeste.color);
        
        //Caso 2
        Calefactor noreste = new Calefactor(automata,29,29);
        assertEquals(Color.red, noreste.color);
        automata.ticTac();
        assertEquals(Color.yellow, noreste.color);
        
    }
    
    @Test
    public void asustadaTest(){
        AutomataCelular automata = new AutomataCelular();
        
        CelulaAsustada diego = new CelulaAsustada(automata,4,3);
        CelulaAsustada felipe = new CelulaAsustada(automata,3,5);
        CelulaNormal cel1 = new CelulaNormal(automata,4,6);
        CelulaNormal cel2 = new CelulaNormal(automata,4,4);
        CelulaNormal cel3 = new CelulaNormal(automata,3,4);
        CelulaNormal cel4 = new CelulaNormal(automata,3,3);
        CelulaNormal cel5 = new CelulaNormal(automata,3,2);
        CelulaNormal cel6 = new CelulaNormal(automata,4,5);
        CelulaNormal cel7 = new CelulaNormal(automata,2,4);
        CelulaNormal cel8 = new CelulaNormal(automata,5,4);
        automata.ticTac();
        
        //Caso 1
        assertTrue(diego.isVivo());
        assertTrue(felipe.isVivo());
        
        //Caso 2
        automata.ticTac();
        automata.ticTac();
        automata.ticTac();
        automata.ticTac();
        automata.ticTac();
        assertFalse(diego.isVivo());
        assertFalse(felipe.isVivo());
    }
    
    @Test
    public void poblacionTest(){
        AutomataCelular automata = new AutomataCelular();
        
        Poblacion pob = new Poblacion(automata,0,0);
        CelulaNormal cel1 = new CelulaNormal(automata,4,6);
        
        //Caso 1
        automata.ticTac();
        assertEquals(Color.green, pob.color);
        
        for (int i=1;i<17;i++){
            new CelulaNormal(automata,i,i);
            new CelulaNormal(automata,i,i+1);
        }
        
        
        //Caso 2
        automata.ticTac();
        assertEquals(Color.orange, pob.color);
        
        
    }
    
    @Test
    public void conwayTest(){
        AutomataCelular automata = new AutomataCelular();
        
        //Bloque
        CelulaConway c1 = new CelulaConway(automata, 4,4);
        CelulaConway c2 = new CelulaConway(automata, 5,4);
        CelulaConway c3 = new CelulaConway(automata, 4,5);
        CelulaConway c4 = new CelulaConway(automata, 5,5);
        
        automata.ticTac();
        assertTrue(c1.isVivo());
        assertTrue(c2.isVivo());
        assertTrue(c3.isVivo());
        assertTrue(c4.isVivo());
        
        automata.ticTac();
        assertTrue(c1.isVivo());
        assertTrue(c2.isVivo());
        assertTrue(c3.isVivo());
        assertTrue(c4.isVivo());
        
        //Parpadeador
        CelulaConway celulaParpadeador1 = new CelulaConway(automata,28,13);
        CelulaConway celulaParpadeador2 = new CelulaConway(automata,28,14);
        CelulaConway celulaParpadeador3 = new CelulaConway(automata,28,15);
        automata.ticTac();
        
        assertTrue(automata.getElemento(27,14).isVivo());
        assertTrue(automata.getElemento(27,14).isVivo());
        
        automata.ticTac();
        
        assertTrue(automata.getElemento(28,13).isVivo());
        assertTrue(automata.getElemento(28,15).isVivo());
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
