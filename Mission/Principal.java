
/**
 * Write a description of class Principal here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Principal extends Bodega
{
    

    /**
     * Constructor for objects of class Principal
     */
    public Principal(int lenght, int width)
    {
        super(lenght,width);
    }

   /**
   * Guarda una caja en la bodega real
   *
   * @param  i,j   i>0 j>0
   */
    public void store(int i, int j)
    {
        i--;
        j--;
        if(i>=0&&j>=0)
        {
            if (this.valores[i][j]<this.lenght||this.valores[i][j]<this.width)
            {
                this.valores[i][j]+=1;
                this.top[i][j].changeColor("blue");
                this.lado[this.width-this.valores[i][j]][i].changeColor("blue");
                this.entry[this.lenght-this.valores[i][j]][j].changeColor("blue");
                this.sePudo=true;
                //loadUndo("store",(Integer) i, (Integer) j, (Integer) 0, (Integer) 0);
            }   
            else
            {
                System.out.println("no hay espacio para guardar esta caja");
                this.sePudo=false;
            }
        
            if (this.top[0][0].isVisible){
                //this.colorDifferent();
            }
        }
        else
        {
          System.out.println("Error:Coordenadas negativas o iguales a 0");  
          this.sePudo=false;
        }
    } 
    
    
    /**
     * Guarda una caja en la bodega real
     *
     * @param  i,j   1,1
     */
    public void store(int[] crate)
    {
        this.store(crate[0],crate[1]);
    }
    
    /**
     * verifica la cantidad de cajas que hay en la bodega real.
     *
     * @return Devuelve la matriz de valores de la bodega real.
     */
    public int[][] warehouse()
    {
        return this.valores;
    }
    
    /**
     * Dibuja la bodega en el tablero
     */
    public void draw(){
        for (int i=0;i<this.lenght;i++)
        {
            for (int j=0;j<this.width;j++)
            {   
                
                this.top[i][j].changeColor("green");
                this.top[i][j].makeVisible();
                this.top[i][j].moveVertical(i*this.size);
                this.top[i][j].moveHorizontal(j*this.size);
                
                this.lado[i][j].changeColor("green");
                this.lado[i][j].makeVisible();
                this.lado[i][j].moveVertical(i*this.size);
                this.lado[i][j].moveHorizontal(this.width*this.size+j*this.size+25);
                
                this.entry[i][j].changeColor("green");
                this.entry[i][j].makeVisible();
                this.entry[i][j].moveVertical(i*this.size);
                this.entry[i][j].moveHorizontal((this.width*this.size)*2+j*this.size+50);
            }
        }
        this.isVisible=true;
    }
    
}
