package tests;
import mission.*;

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
public class MissionTestC4

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
    
    // Tests for storing different types of crates 
    
    @Test
    public void storeNormalTest(){
        mission.store("normal",3,3);
        assertEquals(mission.ppal.top[2][2].color, "blue");
    }
    
    @Test
    public void storeDelicateTest(){
        mission.store("delicate",1,3);
        assertEquals(mission.ppal.top[0][2].color, "gray");
    }
    
    @Test
    public void storeRebelTest(){
        mission.store("rebel",3,2);
        assertEquals(mission.ppal.top[1][2].color, "orange");
    }
    
    @Test
    public void storeFrostTest(){
        mission.store("frost",4,3);
        assertEquals(mission.ppal.top[3][2].color, "cyan");
    }
    
    @Test
    public void storeSafeTest(){
        mission.store("safe",4,4);
        assertEquals(mission.ppal.top[3][3].color, "pink");
    }
    
    @Test
    public void storeHeavyTest(){
        mission.store("heavy",2,2);
        assertEquals(mission.ppal.top[1][1].color, "black");
    }
    
    // Tests for stealing different types of crates
    
    @Test
    public void stealNormalTest(){
        mission.store("normal",3,3);
        mission.copy();
        mission.steal(3,3);
        assertEquals(mission.plan.top[2][2].color, "yellow");
    }
    
    @Test
    public void stealDelicateTest(){
        mission.store("delicate",2,2);
        mission.copy();
        mission.steal(2,2);
        assertEquals(mission.plan.top[1][1].color, "yellow");
    }
    
    @Test
    public void stealRebelTest(){
        mission.store("rebel",3,2);
        mission.copy();
        mission.steal(2,3);
        assertEquals(mission.plan.top[1][2].color, "yellow");
    }
    
    @Test
    public void stealFrostTest(){
        mission.store("frost",1,1);
        mission.copy();
        mission.steal(1,1);
        assertEquals(mission.plan.top[0][0].color, "yellow");
    }
    
    @Test
    public void stealSafeTest(){
        mission.store("safe",3,3);
        mission.copy();
        mission.steal(3,3);
        assertEquals(mission.plan.top[2][2].color, "pink");
    }
    
    @Test
    public void stealHeavyTest(){
        mission.store("heavy",4,4);
        mission.copy();
        mission.steal(4,4);
        assertEquals(mission.plan.top[3][3].color, "yellow");
    }
    
    //Tests for the other modified methods

    @Test
    public void arrangeHeavyTest(){
        mission.store("heavy",4,4);
        mission.store("normal",3,3);
        mission.copy();
        int[] from = {4,4};
        int[] to = {3,3};
        mission.arrange(from,to);
        assertEquals(mission.plan.top[3][3].color,"black");
    }
    
    @Test
    public void arrangeDelicateTest(){
        mission.store("delicate",2,2);
        mission.store("normal",3,3);
        mission.copy();
        int[] from = {3,3};
        int[] to = {2,2};
        mission.arrange(from,to);
        assertEquals(mission.plan.top[1][1].color,"gray");
    }
    
    @Test
    public void arrangeTest(){
        mission.store("delicate",2,2);
        mission.store("normal",3,3);
        mission.copy();
        int[] from = {2,2};
        int[] to = {3,3};
        mission.arrange(from,to);
        assertEquals(mission.plan.top[2][2].color,"gray");
    }
    
    @Test
    public void undoTest(){
        mission.store("heavy", 2, 2);
        mission.copy();
        mission.undo();
        assertFalse(mission.plan.isVisible);
        mission.undo();
        assertEquals(mission.ppal.top[1][1].color, "green");
        
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
