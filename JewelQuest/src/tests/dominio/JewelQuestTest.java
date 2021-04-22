package tests.dominio;

import dominio.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JewelQuestTest {
    JewelQuest jq;
    final Gema[][] tableroPrueba = {{null,null,null,null,null,null,null,null},
                      {null,null,new Gema(2),new Gema(3),new Gema(2),new Gema(2),new Gema(3),new Gema(0),null,null},
                      {null,null,new Gema(2),new Gema(3),new Gema(0),new Gema(3),new Gema(3),new Gema(4),null,null},
                      {null,null,new Gema(0),new Gema(1),new Gema(0),new Gema(2),new Gema(1),new Gema(0),null,null},
                      {null,null,new Gema(4),new Gema(4),new Gema(2),new Gema(1),new Gema(1),new Gema(0),null,null},
                      {null,null,new Gema(3),new Gema(1),new Gema(4),new Gema(4),new Gema(3),new Gema(2),null,null},
                      {null,null,new Gema(3),new Gema(3),new Gema(2),new Gema(3),new Gema(0),new Gema(2),null,null},
                      {null,null,null,null,null,null,null,null},
                      {null,null,null,null,null,null,null,null}};

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

    }

    @Test
    void moverseEnLaMismaTest() {
        jq.tablero = tableroPrueba;

    }

    @Test
    void moverEntre2Iguales() {
        jq.tablero = tableroPrueba;

    }

    @Test
    void moverHorTest(){
        jq.tablero = tableroPrueba;
    }

    @Test
    void moverVerTest(){
        jq.tablero = tableroPrueba;
    }

    @Test
    void moverDiag1Test(){
        jq.tablero = tableroPrueba;
    }

    @Test
    void moverDiag2Test(){
        jq.tablero = tableroPrueba;
    }
}