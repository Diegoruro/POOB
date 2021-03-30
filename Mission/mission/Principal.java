package mission;

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
    
   @Override
   /**
   * Guarda una caja en la bodega real
   *
   * @param  i,j   i>0 j>0
   */
    public void store(int i, int j) throws MissionException
    {
        i--;
        j--;
        if(i>=0&&j>=0)
        {
            if (this.valores[i][j]<this.lenght||this.valores[i][j]<this.width)
            {
                this.valores[i][j]+=1;
                
                String newColor = this.cajas[i][j].color;
                this.top[i][j].changeColor(newColor);
                this.lado[this.lenght-this.valores[i][j]][i].changeColor(newColor);
                this.entry[this.lenght-this.valores[i][j]][j].changeColor(newColor);
                this.sePudo=true;
            }   
            else
            {
                this.sePudo=false;
                throw new MissionException(MissionException.INVALIDSTORE);
            }
        }
        else
        { 
          this.sePudo=false;
          throw new MissionException(MissionException.INVALIDSTORE);
        }
    } 
    
    /**
     * Guarda una caja en la bodega real
     *
     * @param  i,j   1,1
     */
    public void store(int[] crate)
    {   
        try{
            this.store(crate[0],crate[1]);
        }catch(MissionException e){
            System.out.println(MissionException.INVALIDSTORE);
        }
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
