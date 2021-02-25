

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
