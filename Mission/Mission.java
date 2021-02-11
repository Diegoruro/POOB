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
    public Rectangle[][] bodegaTop;
    public Rectangle[][] bodegaLado;
    public Rectangle[][] bodegaEntry;
    public Rectangle[][] planBodegaTop;
    public Rectangle[][] planBodegaLado;
    public Rectangle[][] planBodegaEntry;
    /**
     * Constructor for objects of class Mission
     */
    public Mission(int largo,int ancho)
    {
        this.lenght=largo;
        this.width=ancho;
        crearBodega(this.lenght,this.width);
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
        for (int i=0;i<largo;i++)
        {
            for (int j=0;j<ancho;j++)
            {
                this.bodegaTop[i][j]=new Rectangle();
                this.bodegaLado[i][j]=new Rectangle();
                this.bodegaEntry[i][j]=new Rectangle();
                this.planBodegaTop[i][j]=new Rectangle();
                this.planBodegaLado[i][j]=new Rectangle();
                this.planBodegaEntry[i][j]=new Rectangle();
                
                this.bodegaTop[i][j].changeColor("green");
                this.bodegaTop[i][j].makeVisible();
                this.bodegaTop[i][j].moveVertical(i*45);
                this.bodegaTop[i][j].moveHorizontal(j*45);
                this.bodegaLado[i][j].changeColor("green");
                this.bodegaLado[i][j].makeVisible();
                this.bodegaLado[i][j].moveVertical(i*45);
                this.bodegaLado[i][j].moveHorizontal(j*45+150);
                this.bodegaEntry[i][j].changeColor("green");
                this.bodegaEntry[i][j].makeVisible();
                this.bodegaEntry[i][j].moveVertical(i*45);
                this.bodegaEntry[i][j].moveHorizontal(j*45+300);
                this.planBodegaTop[i][j].moveVertical(i*45+150);
                this.planBodegaTop[i][j].moveHorizontal(j*45);
                this.planBodegaLado[i][j].moveVertical(i*45+150);
                this.planBodegaLado[i][j].moveHorizontal(j*45+150);
                this.planBodegaEntry[i][j].moveVertical(i*45+150);
                this.planBodegaEntry[i][j].moveHorizontal(j*45+300);
            }
        }
        
        
    }
}
