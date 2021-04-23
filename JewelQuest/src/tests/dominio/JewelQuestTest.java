package tests.dominio;

import dominio.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JewelQuestTest {
    JewelQuest jq;
    final Gema[][] tableroPrueba = {{null,null,null,null,null,null,null,null,null,null},
                      {null,null,null,null,null,null,null,null,null,null},
                      {null,null,new Gema(2),new Gema(3),new Gema(2),new Gema(2),new Gema(3),new Gema(0),null,null},
                      {null,null,new Gema(2),new Gema(3),new Gema(0),new Gema(3),new Gema(3),new Gema(4),null,null},
                      {null,null,new Gema(0),new Gema(1),new Gema(0),new Gema(2),new Gema(1),new Gema(0),null,null},
                      {null,null,new Gema(4),new Gema(4),new Gema(2),new Gema(1),new Gema(1),new Gema(0),null,null},
                      {null,null,new Gema(3),new Gema(1),new Gema(4),new Gema(4),new Gema(3),new Gema(2),null,null},
                      {null,null,new Gema(3),new Gema(3),new Gema(2),new Gema(3),new Gema(0),new Gema(2),null,null},
                      {null,null,null,null,null,null,null,null,null,null},
                      {null,null,null,null,null,null,null,null,null,null}};

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        jq = new JewelQuest(6,6);
    }

    @Test
    void tableroInicialLimpio() {
        boolean ans = jq.hayGanadores();
        assertFalse(ans);
    }

    @Test
    void puntajeInicial0Test() {
        assertEquals(0, jq.getScore());
    }

    @Test
    void movimientosIniciales0Test() {
        assertEquals(0, jq.getMovements());
    }

    @Test
    void moverseMasDe1UnidadTest() {
        jq.tablero = tableroPrueba;
        jq.doMovement(2,2,4,3);
        assertEquals(2,jq.tablero[2][2].getType());
        assertEquals(1, jq.tablero[4][3].getType());
    }

    @Test
    void moverseEnLaMismaTest() {
        jq.tablero = tableroPrueba;
        jq.doMovement(7,7,7,7);
        assertEquals(2, jq.tablero[7][7].getType());
    }

    @Test
    void moverEntre2Iguales() {
        jq.tablero = tableroPrueba;
        jq.doMovement(3,4,4,4);
        assertEquals(0, jq.tablero[3][4].getType());
        assertEquals(0, jq.tablero[4][4].getType());
    }

    @Test
    void movimientoTest(){
        jq.tablero = tableroPrueba;
        jq.doMovement(2,2,2,3);
        assertEquals(1, jq.getMovements());
        assertTrue(jq.getScore()>0);
    }

    @Test
    void movimientoTest2(){
        jq.tablero = tableroPrueba;
        jq.doMovement(2,7,3,7);
        assertEquals(1, jq.getMovements());
        assertTrue(jq.getScore()>0);
    }

}