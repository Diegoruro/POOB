/**
 * Write a description of class Dicese here.
 * 
 * @author (Felipe Aguas-Diego Ruiz) 
 * @version (First Version)
 */
public class Dicese
{
    // instance variables - replace the example below with your own
    private int rows, columns;
    private int dices;
    private boolean win;
    private int winningTimes;
    private int timesPlayed;
    public  Dice diceseV1[];
    private Dice diceseV2[][];

    /**
     * Constructor for objects of class DiceseV1
     */
    public Dicese(int dices)
    {
        rows=1;
        this.dices=dices;
        columns=dices;
        diceseV1=new Dice[dices];
        for (int i=0; i<dices;i++)
        {
            diceseV1[i]= new Dice();
        }
    }
    
    
    /**
     * Constructor for objects of class DiceseV2
     */
    public Dicese(int n, int m)
    {
        rows=n;
        this.dices=n*m;
        columns=m;
        diceseV2=new Dice[n][m];
        for (int i=0; i<n;i++)
        {
            for (int j=0; j < m;j++)
            {
                diceseV2 = new Dice[i][j];
            }
        }
    }

    
    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public void play()
    {
        Dice[] array=this.diceseV1;
        this.timesPlayed++;
        for (int i=0; i<this.dices;i++)
        {
            array[i].roll();
            array[i].moveHorizontal(i*155);
            array[i].makeVisible();
        }
    }
    
    
    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public boolean isWinningState()
    {
        Dice[] array=this.diceseV1;
        int dice1=0;
        int dice2=0;
        for (int i=0; i<this.dices-1;i++)
        {
            dice1=array[i].getValue();
            dice2=array[i+1].getValue();
            if (dice2-dice1==1||dice2-dice1==0)
            {
                this.win=true;
                this.winningTimes++;
            }
            else
            {
                this.win=false;
            }
        }
        return this.win;
    }
    
    
     /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public void play(int times)
    {
        Dice[] array=this.diceseV1;
        this.timesPlayed+=times;
        for (int i=0; i<times;i++)
        {
            
        
            for (int j=0; j<this.dices;j++)
            {
                array[j].roll();
                array[j].moveHorizontal(j*155);
                array[j].makeVisible();
            }
            for (int j=0; j<this.dices;j++)
            {
                array[j].makeInvisible();
                array[j].moveHorizontal(j*-155);
            }
            System.out.println(isWinningState());
        }
    }
   
    
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y
     */
    public void percentagesOfWinningStates()
    {
       if (this.timesPlayed>0){
           int percentage=(this.winningTimes*100)/this.timesPlayed;
           System.out.println(percentage+"%");
       }
       else
       {
           System.out.println("No has jugado");
       }
    }
    
    
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y
     */
    public void reset()
    {
        Dice[] array=this.diceseV1;
        this.timesPlayed=0;
        this.winningTimes=0;
        for (int j=0; j<this.dices;j++)
            {
                array[j].makeInvisible();
                array[j].moveHorizontal(j*-155);
            }
    }
    
    
    
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y
     */
    public void playV2()
    {
        Dice[][] array=this.diceseV2;
        this.timesPlayed++;
        for (int i=0; i<this.rows;i++)
        {
            for (int j=0; j < this.columns; j++)
            {
                array[i][j].roll();
                array[i][j].moveHorizontal(i*155);
                array[i][j].moveVertical(j*155);
                array[i][j].makeVisible();
            }
        }
    }

    
    
    
}