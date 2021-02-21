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
    public int lastStolenCrates=0;
    public Rectangle[][] bodegaTop;
    public Rectangle[][] bodegaLado;
    public Rectangle[][] bodegaEntry;
    public Rectangle[][] planBodegaTop;
    public Rectangle[][] planBodegaLado;
    public Rectangle[][] planBodegaEntry;
    public ArrayList<String> robadas;
    public boolean sePudo;
    public int[][] valores;
    public int[][] planValores;
    
    
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
     * Este metodo crea las bodegas, tanto la bodega de la camara como la bodega del plan con las 3 camaras
     * 
     * @param  void
     * @return void
     */
    private void crearBodega(int largo,int ancho)
    {
        this.lenght=largo;
        this.width=ancho;
        this.bodegaTop=new Rectangle[largo][ancho];
        this.bodegaLado=new Rectangle[largo][ancho];
        this.bodegaEntry=new Rectangle[largo][ancho];
        this.planBodegaTop=new Rectangle[largo][ancho];
        this.planBodegaLado=new Rectangle[largo][ancho];
        this.planBodegaEntry=new Rectangle[largo][ancho];
        this.valores=new int[largo][ancho];
        this.planValores=new int[largo][ancho];
        for (int i=0;i<largo;i++)
        {
            for (int j=0;j<ancho;j++)
            {   
                this.valores[i][j]=0;
                this.planValores[i][j]=0;
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
     * Guarda una caja en la bodega real
     *
     * @param  i,j   1,1
     * @return     void
     */
    public void store(int i, int j)
    {
        i--;
        j--;
        if (this.valores[i][j]<this.lenght||this.valores[i][j]<this.width)
        {
            this.valores[i][j]+=1;
            this.bodegaTop[i][j].changeColor("blue");
            this.bodegaLado[this.width-this.valores[i][j]][i].changeColor("blue");
            this.bodegaEntry[this.lenght-this.valores[i][j]][j].changeColor("blue");
            this.sePudo=true;
        }   
        else
        {
            System.out.println("no hay espacio para guardar esta caja");
            this.sePudo=false;
        }
        if (this.planBodegaTop[0][0].isVisible){
            this.colorDifferent();
        }
    } 

    
    /**
     * Guarda una caja en la bodega real
     *
     * @param  i,j   1,1
     * @return     void
     */
    public void store(int[] crate)
    {
        this.store(crate[0],crate[1]);
    }

    
    /**
     * Hace una copia de la bodega desde sus 3 puntos de vista
     *
     * @param  void
     * @return void
     */
    public void copy()
    {   
        
        for (int i=0;i<this.lenght;i++)
        {
            for (int j=0;j<this.width;j++)
            {   
                int x=this.valores[i][j];
                this.planValores[i][j]=this.valores[i][j];
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
        this.lastStolenCrates=this.stolenCrates;
        this.stolenCrates=0;
        this.sePudo=true;
        this.colorDifferent();
    }
    
    
    /**
     * Roba una caja de la bodega en el plan.
     *
     * @param  i,j 1,1
     * @return void
     */
    public void steal(int i,int j)
    {
        i--;
        j--;
        if (this.planValores[i][j]>0)
        {
            
            String crate=i+"-"+j;
            this.robadas.add(crate);
            this.stolenCrates++;
            this.planBodegaTop[i][j].changeColor("yellow");
            this.planBodegaLado[this.width-this.planValores[i][j]][i].changeColor("yellow");
            this.planBodegaEntry[this.lenght-this.planValores[i][j]][j].changeColor("yellow");
            this.planValores[i][j]-=1;
            this.sePudo=true;
        }
        else
        {
            System.out.println("no hay nada que robar en esta posición");
            this.sePudo=false;
        }
        this.colorDifferent();
    }
    
    
    /**
     * Roba una caja de la bodega en el plan.
     *
     * @param  [i,j] {2,3}
     * @return void
     */
    public void steal(int[] crate)
    {
        this.steal(crate[0],crate[1]);
    }

    
    /**
     * Devuelve la ultima caja a su posicion original.
     *
     * @param  void
     * @return void
     */
    public void returnCrate()
    {   
        String lastCrate = this.robadas.get(this.robadas.size() - 1);
        String coordenada[] = lastCrate.split("-");

        int i = Integer.parseInt(coordenada[0]);
        int j = Integer.parseInt(coordenada[1]);
        this.planValores[i][j]+=1;
        if (this.planValores[i][j]>1)
        {
            this.planBodegaTop[i][j].changeColor("blue");
            this.planBodegaLado[this.width-this.planValores[i][j]][i].changeColor("blue");
            this.planBodegaEntry[this.lenght-this.planValores[i][j]][j].changeColor("blue");
            this.sePudo=true;
        }
        else
        {
            this.planBodegaLado[this.width-this.planValores[i][j]][i].changeColor("blue");
            this.planBodegaEntry[this.lenght-this.planValores[i][j]][j].changeColor("blue");
            this.sePudo=true; 
        }
        this.colorDifferent();
    }

    
    /**
     * Mueve una caja de una posicion a otra si hay espacio en el lugar a mover.
     *
     * @param  from[] to[] {1,1} {1,2} 
     * @return void
     */
    public void arrange(int[] from,int[] to)
    {
        int i=from[0];
        int j=from[1];
        int k=to[0];
        int l=to[1];
        i--;
        j--;
        k--;
        l--;
        this.planValores[i][j]+=1;
        if (this.planValores[i][j]>1 && (this.planValores[k][l]<this.lenght && this.planValores[k][l]<this.width))
        {
            this.planBodegaTop[i][j].changeColor("magenta");
            this.planBodegaLado[this.width-this.planValores[i][j]][i].changeColor("magenta");
            this.planBodegaEntry[this.lenght-this.planValores[i][j]][j].changeColor("magenta");
            this.planValores[i][j]-=1;
            this.planBodegaTop[k][l].changeColor("blue");
            this.planBodegaLado[this.width-this.planValores[k][l]][k].changeColor("blue");
            this.planBodegaEntry[this.lenght-this.planValores[k][l]][l].changeColor("blue");
            this.sePudo=true;
        }
        else
        {
            System.out.println("no hay nada que mover en esta posición o no hay espacio en la posicion a mover");
            this.sePudo=false;
        }
        this.colorDifferent();
    }

    
    /**
     * Calcula la cantidad de cajas robadas.
     *
     * @param  void
     * @return la cantidad de cajas robadas
     */
    public int stolen()
    {
        return this.lastStolenCrates;
    }
    
    
    /**
     * verifica la cantidad de cajas que hay en la bodega real.
     *
     * @param  void
     * @return Devuelve la matriz de valores de la bodega real.
     */
    public int[][] warehouse()
    {
        return this.valores;
    }
    
    
    /**
     * verifica la cantidad de cajas que hay en la bodega del plan.
     *
     * @param  void
     * @return Devuelve la matriz de valores de la bodega del plan.
     */
    public int[][] layout()
    {
        return this.planValores;
    }

    
    /**
     * Hace visible todas las camaras y los planos de la bodega.
     *
     * @param  void
     * @return void
     */
    public void makeVisible()
    {
        for (int i=0;i<this.lenght;i++)
        {
           for (int j=0;j<this.width;j++)
           {
              this.bodegaTop[i][j].makeVisible();
              this.bodegaLado[i][j].makeVisible();
              this.bodegaEntry[i][j].makeVisible();
              this.planBodegaTop[i][j].makeVisible();
              this.planBodegaLado[i][j].makeVisible();
              this.planBodegaEntry[i][j].makeVisible();
           } 
        }
    }

    
    /**
     * Oculta todas las camaras y planos de la bodega.
     *
     * @param  void
     * @return void
     */
    public void makeInvisible()
    {
        for (int i=0;i<this.lenght;i++)
        {
           for (int j=0;j<this.width;j++)
           {
              this.bodegaTop[i][j].makeInvisible();
              this.bodegaLado[i][j].makeInvisible();
              this.bodegaEntry[i][j].makeInvisible();
              this.planBodegaTop[i][j].makeInvisible();
              this.planBodegaLado[i][j].makeInvisible();
              this.planBodegaEntry[i][j].makeInvisible();
           } 
        }
    }

    
    /**
     * Acaba con el simulador y reincia todos los datos.
     *
     * @param  void
     * @return void
     */
    public void finish()
    {
      this.makeInvisible();
      this.stolenCrates=0;
      this.lastStolenCrates=0;
      this.robadas.clear();
      for (int i=0;i<this.lenght;i++)
        {
           for (int j=0;j<this.width;j++)
           {
              this.bodegaTop[i][j].changeColor("green");
              this.bodegaLado[i][j].changeColor("green");
              this.bodegaEntry[i][j].changeColor("green");;
              this.planBodegaTop[i][j].changeColor("magenta");
              this.planBodegaLado[i][j].changeColor("magenta");
              this.planBodegaEntry[i][j].changeColor("magenta");
              this.planValores[i][j]=0;
              this.valores[i][j]=0;
           } 
      }
    }
    
    /**
     * Verifica si la ultima accion se pudo realizar
     *
     * @param  void
     * @return Devuelve un valor booleano dependiendo si se pudo realizar la accion.
     */
    public boolean ok()
    {
        return this.sePudo;
    }

    
    /**
     * Verifica si la bodega original y la del plan son iguales
     *
     * @param       void
     * @return      Booleano según si son iguales o no
     */
    private boolean areEqual()
    {
        for (int i = 0; i < this.lenght; i++){
            for (int j = 0; j < this.width; j++){
                if (this.valores[i][j] != this.planValores[i][j]){
                    System.out.println(this.valores[i][j]);
                    System.out.println(this.planValores[i][j]);
                    return false;
                }
            }
        }
        return true;
    }
    
    
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y
     */
    private void colorDifferent()
    {
        if (this.areEqual() == false){
            for (int i=0;i<this.lenght;i++)
            {
                for (int j=0;j<this.width;j++)
                {
                  this.planBodegaTop[i][j].changeColor("red");
                  this.planBodegaLado[i][j].changeColor("red");
                  this.planBodegaEntry[i][j].changeColor("red");
               } 
          }
        }
    }

}
