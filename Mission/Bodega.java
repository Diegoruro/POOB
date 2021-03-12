
/**
 * Write a description of class Bodega here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bodega
{
    public int lenght;
    public int width;
    public int size=20;
    public Rectangle[][] top;
    public Rectangle[][] lado;
    public Rectangle[][] entry;
    public boolean isVisible;
    public int[][] valores;
    public boolean sePudo;
    
    
    /**
     * Constructor for objects of class Bodega
     */
    public Bodega(int lenght, int width)
    {
        this.lenght = lenght;
        this.width = width;
        this.top=new Rectangle[lenght][width];
        this.lado=new Rectangle[lenght][width];
        this.entry=new Rectangle[lenght][width];
        this.valores=new int[lenght][width];
        for (int i=0;i<lenght;i++)
        {
            for (int j=0;j<width;j++)
            {   
                this.valores[i][j]=0;
                this.top[i][j]=new Rectangle();
                this.lado[i][j]=new Rectangle();
                this.entry[i][j]=new Rectangle();
            }
        }
    }

    /**
     * rellena de ceros la matriz de valores de la bodega
     *
     */
    public void ceros()
    {
        for (int i=0;i<this.lenght;i++)
       {
           for (int j=0;j<this.width;j++)
           {
               this.valores[i][j]=0;
           } 
       }
    }
    
    /**
    * oculta las camaras de la bodega
    *
    */
    public void makeInvisible()
    {
        for (int i=0;i<this.lenght;i++)
        {
           for (int j=0;j<this.width;j++)
           {
                  this.top[i][j].makeInvisible();
                  this.lado[i][j].makeInvisible();
                  this.entry[i][j].makeInvisible();
           } 
        }
        this.isVisible=false;
    }
    
    /**
    * Hace visible todas las camaras y los planos de la bodega.
    *
    */
    public void makeVisible()
    {
        for (int i=0;i<this.lenght;i++)
        {
           for (int j=0;j<this.width;j++)
           {
              this.top[i][j].makeVisible();
              this.lado[i][j].makeVisible();
              this.entry[i][j].makeVisible();
           } 
        }
        this.isVisible=true;
    }
    
    /**
     * reestablece la posicion inicial de todos los rectangulos
     */
    public void restorePosition()
    {
        for (int i=0;i<this.lenght;i++)
        {
           for (int j=0;j<this.width;j++)
           {
               this.top[i][j].setXPosition(70);
               this.top[i][j].setYPosition(15);
               this.lado[i][j].setXPosition(70);
               this.lado[i][j].setYPosition(15);
               this.entry[i][j].setXPosition(70);
               this.entry[i][j].setYPosition(15);
            }
        }
    }
}
