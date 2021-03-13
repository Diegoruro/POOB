

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class MissionTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class MissionTest
{
    private static final int rows = 4;
    private static final int columns = 4;
    Mission mission;
    
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        mission = new Mission(this.rows, this.columns);
    }
    
    @Test
    public void crearBodegasTest(){
        assertEquals(rows, mission.warehouse().length);
        assertEquals(columns, mission.warehouse()[0].length);
        
        assertEquals(rows, mission.layout().length);
        assertEquals(columns, mission.layout()[0].length);
    }
    
    @Test
    public void storeTest(){
        int i = 2;
        int j = 2;
        int[] ij = {2,2};
        
        mission.store(i, j);
        assertEquals(1, mission.warehouse()[i-1][j-1]);
        
        mission.store(ij);
        assertEquals(2, mission.warehouse()[ij[0]-1][ij[1]-1]);
    }
    
    @Test 
    public void copyTest(){
        mission.copy();
        boolean res = true;
        for (int i=0; i < rows;i++){
            for (int j=0; j<columns;j++){
                if (mission.warehouse()[i][j] != mission.layout()[i][j]){
                    res = false;
                }
            }
        }
        assertTrue(res);
    }
        
    @Test
    public void stealTest(){
        int i = 1;
        int j = 1;
        int[] ij = {1,1};
        
        mission.store(i, j);
        mission.store(i, j);
        mission.copy();
        
        mission.steal(i, j);
        mission.steal(ij);
        
        assertEquals(0, mission.layout()[i-1][j-1]);
    }
    
    @Test
    public void returnTest(){
        int i = 1;
        int j = 1;
        mission.store(i, j);
        mission.store(i, j);
        mission.copy();
        
        mission.steal(i, j);
        mission.returnCrate();
        
        assertEquals(2, mission.layout()[i-1][j-1]);
    }
    

    @Test
    public void arrangeTest()
    {
        int[] from={1,1};
        int[] to = {2,2};
        mission.store(from);
        mission.copy();
        mission.arrange(from,to);
        
        assertEquals(1,mission.layout()[to[0]-1][to[1]-1]);
    }

    
    @Test
    public void checkStolenTest()
    {
        int[] from={1,1};
        mission.store(from);
        mission.copy();
        mission.steal(from);
        mission.copy();
        int ans=mission.checkStolen();
        
        assertEquals(1,ans);
    }
    
    
    @Test
    public void warehouseTest()
    {
        int[] from={1,1};
        mission.store(from);
        
        assertEquals(1,mission.warehouse()[from[0]-1][from[1]-1]);
    }
    
    
    @Test
    public void layoutTest()
    {
        int[] from={1,1};
        mission.store(from);
        mission.copy();
        
        assertEquals(1,mission.layout()[from[0]-1][from[1]-1]);
    }
    
    
    @Test
    public void makeVisibleTest()
    {
        mission.copy();
        mission.makeInvisible();
        mission.makeVisible();
        
        assertTrue(mission.ppal.isVisible);
        assertTrue(mission.plan.isVisible);
    }
    
    
    @Test
    public void makeInvisibleTest()
    {
        mission.copy();
        mission.makeVisible();
        mission.makeInvisible();
        
        assertFalse(mission.ppal.isVisible);
        assertFalse(mission.plan.isVisible);
    }
    
    
    @Test
    public void finishTest()
    {
        int[] from={1,1};
        mission.store(from);
        mission.copy();
        mission.steal(from);
        mission.finish();
        
        assertFalse(mission.ppal.isVisible);
        assertFalse(mission.plan.isVisible);
        assertEquals(0,mission.plan.stolenCrates);
        assertEquals(0,mission.plan.valores[from[0]-1][from[1]-1]);
        assertEquals(0,mission.ppal.valores[from[0]-1][from[1]-1]);
    }
    
    
    @Test
    public void okTest()
    {
        int[] from={3,2};
        int[] fromFalse={2,1};
        int[] to={1,1};
        mission.store(from);
        mission.copy();
        mission.arrange(from,to);
        assertFalse(mission.ok());
        mission.store(1,2);
        assertTrue(mission.ok());
    }
    
    
    @Test
    public void undoTest(){
        mission.store(2,2);
        mission.copy();
        mission.steal(2,2);
        mission.undo();
        assertEquals(1,mission.plan.valores[1][1]);
        mission.undo();
        assertFalse(mission.plan.top[0][0].isVisible);
        
        
    }
    
    @Test
    public void redoTest(){
        mission.store(3,3);
        mission.undo();
        mission.redo();
        assertEquals(1, mission.ppal.valores[2][2]);
    }
    
    @Test
    public void zoomTest(){
        mission.zoom('+');
        assertTrue(mission.ppal.size > 20);
        mission.zoom('-');
        assertTrue(mission.ppal.size<20);
    }
    
    @Test
    public void toStealTest(){
        assertEquals(0, mission.plan.robadas.size());        
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
