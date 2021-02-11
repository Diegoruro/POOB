import java.util.ArrayList;
/**
 * Write a description of class Mission here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Mission
{
    public int lenght;
    public int width;
    private static final int size=20;
    public int stolenCrates=0;
    public Rectangle[][] bodegaTop;
    public Rectangle[][] bodegaLado;
    public Rectangle[][] bodegaEntry;
    public Rectangle[][] planBodegaTop;
    public Rectangle[][] planBodegaLado;
    public Rectangle[][] planBodegaEntry;
    public ArrayList<Integer[]> robadas;
    public int[][] valores;
    /**
     * Constructor for objects of class Mission
     */
    public Mission(int largo,int ancho)
    {
        this.lenght=largo;
        this.width=ancho;
        crearBodega(this.lenght,this.width);
        this.robadas=new ArrayList();
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public void crearBodega(int largo,int ancho)
    {
        this.bodegaTop=new Rectangle[largo][ancho];
        this.bodegaLado=new Rectangle[largo][ancho];
        this.bodegaEntry=new Rectangle[largo][ancho];
        this.planBodegaTop=new Rectangle[largo][ancho];
        this.planBodegaLado=new Rectangle[largo][ancho];
        this.planBodegaEntry=new Rectangle[largo][ancho];
        this.valores=new int[largo][ancho];
        for (int i=0;i<largo;i++)
        {
            for (int j=0;j<ancho;j++)
            {   
                this.valores[i][j]=0;
                this.bodegaTop[i][j]=new Rectangle();
                this.bodegaLado[i][j]=new Rectangle();
                this.bodegaEntry[i][j]=new Rectangle();
                this.planBodegaTop[i][j]=new Rectangle();
                this.planBodegaLado[i][j]=new Rectangle();
                this.planBodegaEntry[i][j]=new Rectangle();
                
                this.bodegaTop[i][j].changeColor("green");
                this.bodegaTop[i][j].makeVisible();
                this.bodegaTop[i][j].moveVertical(i*this.size);
                this.bodegaTop[i][j].moveHorizontal(j*this.size);
                
                this.bodegaLado[i][j].changeColor("green");
                this.bodegaLado[i][j].makeVisible();
                this.bodegaLado[i][j].moveVertical(i*this.size);
                this.bodegaLado[i][j].moveHorizontal(this.width*this.size+j*this.size+25);
                
                this.bodegaEntry[i][j].changeColor("green");
                this.bodegaEntry[i][j].makeVisible();
                this.bodegaEntry[i][j].moveVertical(i*this.size);
                this.bodegaEntry[i][j].moveHorizontal((this.width*this.size)*2+j*this.size+50);
                
                this.planBodegaTop[i][j].moveVertical(this.lenght*this.size+25+i*this.size);
                this.planBodegaTop[i][j].moveHorizontal(j*this.size);
                
                this.planBodegaLado[i][j].moveVertical(this.lenght*this.size+25+i*this.size);
                this.planBodegaLado[i][j].moveHorizontal(this.width*this.size+j*this.size+25);
                
                this.planBodegaEntry[i][j].moveVertical(this.lenght*this.size+25+i*this.size);
                this.planBodegaEntry[i][j].moveHorizontal((this.width*this.size)*2+j*this.size+50);
            }
        }
    }
    
    
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y
     */
    public void store(int i, int j)
    {
        i--;
        j--;
        this.valores[i][j]+=1;
        this.bodegaTop[i][j].changeColor("blue");
        this.bodegaLado[this.width-this.valores[i][j]][i].changeColor("blue");
        this.bodegaEntry[this.lenght-this.valores[i][j]][j].changeColor("blue");
    }

    
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y
     */
    public void store(int[] crate)
    {
        this.store(crate[0],crate[1]);
    }

    
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y
     */
    public void copy()
    {   
        for (int i=0;i<this.lenght;i++)
        {
            for (int j=0;j<this.width;j++)
            {   
                int x=this.valores[i][j];
                if (x>0)
                {
                    for (int k=this.valores[i][j];k>0;k--)
                    {
                        this.planBodegaTop[i][j].changeColor("blue");
                        this.planBodegaLado[this.width-k][i].changeColor("blue");
                        this.planBodegaEntry[this.lenght-k][j].changeColor("blue");
                    }
                }
                this.planBodegaTop[i][j].makeVisible();
                this.planBodegaLado[i][j].makeVisible();
                this.planBodegaEntry[i][j].makeVisible();
            }
        }
    }
    
    
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y
     */
    public void steal(int i,int j)
    {   
        i--;
        j--;
        //haga el if pedazo de mierda :3
        Integer[] crate=new Integer[2];
        this.robadas.add(crate);
        this.stolenCrates++;
        this.planBodegaTop[i][j].changeColor("yellow");
        this.planBodegaLado[this.width-this.valores[i][j]][i].changeColor("yellow");
        this.planBodegaEntry[this.lenght-this.valores[i][j]][j].changeColor("yellow");
    }
    
    
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y
     */
    public void steal(int[] crate)
    {
        this.steal(crate[0],crate[1]);
    }

    
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y
     */
    public void returnCrate()
    {   
        //int newRow = Integer.parseInt(pos[0]);
        //int newCol = Integer.parseInt(pos[1]);
        //this.planBodegaTop[i][j].changeColor("blue");
        //this.planBodegaLado[this.width-this.valores[i][j]][i].changeColor("blue");
        //this.planBodegaEntry[this.lenght-this.valores[i][j]][j].changeColor("blue");
    }

}
