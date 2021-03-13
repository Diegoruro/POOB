

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class ContestTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class ContestTest
{
    /**
     * Default constructor for test class ContestTest
     */
    public ContestTest()
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
    public void solveTest(){
        int[][] matriz = {{1,4,0,5,2},{2,1,2,0,1},{0,2,3,4,4},{0,3,0,3,1},{1,2,2,1,1}};
        MissionContest solve = new MissionContest(matriz);
        assertEquals(9, solve.solve(solve.matrix));
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
