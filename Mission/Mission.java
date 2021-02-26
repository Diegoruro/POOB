import java.util.ArrayList;
import java.util.Stack;
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
    public int size=20;
    public int stolenCrates=0;
    public int lastStolenCrates=0;
    public Rectangle[][] bodegaTop;
    public Rectangle[][] bodegaLado;
    public Rectangle[][] bodegaEntry;
    public Rectangle[][] planBodegaTop;
    public Rectangle[][] planBodegaLado;
    public Rectangle[][] planBodegaEntry;
    public ArrayList<String> robadas;
    public ArrayList<Integer[]> coordenadas = new ArrayList<Integer[]>();
    public ArrayList<Integer[]> undoCoor = new ArrayList<Integer[]>();
    public Stack<String> ultimaAccion;
    public Stack<String> undo;
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
        this.ultimaAccion=new Stack();
        this.undo=new Stack();
    }
    
    /**
     * Este metodo crea las bodegas, tanto la bodega de la camara como la bodega del plan con las 3 camaras
     * 
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
                this.bodegaTop[i][j].changeColor("blue");
                this.bodegaLado[this.width-this.valores[i][j]][i].changeColor("blue");
                this.bodegaEntry[this.lenght-this.valores[i][j]][j].changeColor("blue");
                this.sePudo=true;
                loadUndo("store",(Integer) i, (Integer) j, (Integer) 0, (Integer) 0);
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
        else
        {
          System.out.println("Error:Coordenadas negativas o iguales a 0");  
          this.sePudo=false;
        }
        
    } 
    
    
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y
     */
    private void ceros(String bodega,String plan)
    {
        for (int i=0;i<this.lenght;i++)
       {
           for (int j=0;j<this.width;j++)
           {
               if (bodega=="yes"){
                   this.valores[i][j]=0;
               }
               else if (plan=="yes"){
                   this.planValores[i][j]=0;
               }
           } 
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
     * Hace una copia de la bodega desde sus 3 puntos de vista
     */
    public void copy()
    {  
       for (int i=0;i<this.lenght;i++)
       {
           for (int j=0;j<this.width;j++)
           {
                  this.planBodegaTop[i][j].changeColor("magenta");
                  this.planBodegaLado[i][j].changeColor("magenta");
                  this.planBodegaEntry[i][j].changeColor("magenta");
           } 
       }
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
        this.loadUndo("copy",(Integer) 0, (Integer) 0, (Integer) 0, (Integer) 0);
    }
    
    
    /**
     * Roba una caja de la bodega en el plan.
     *
     * @param  i,j 1,1
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
            this.loadUndo("steal",(Integer) i, (Integer) j, (Integer) 0, (Integer) 0);
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
     * @param Posicion en i y en j de la caja a robar [i,j] {2,3}
     */
    public void steal(int[] crate)
    {
        this.steal(crate[0],crate[1]);
    }
    
    
    /**
     * Devuelve la ultima caja a su posicion original.
     */
    public void returnCrate()
    {   
        String lastCrate = this.robadas.get(this.robadas.size() - 1);
        String coordenada[] = lastCrate.split("-");
    
        int i = Integer.parseInt(coordenada[0]);
        int j = Integer.parseInt(coordenada[1]);
        this.planValores[i][j]+=1;
        this.planBodegaTop[i][j].changeColor("blue");
        this.planBodegaLado[this.width-this.valores[i][j]][i].changeColor("blue");
        this.planBodegaEntry[this.lenght-this.valores[i][j]][j].changeColor("blue");
        this.sePudo=true;
        this.loadUndo("return",(Integer) i, (Integer) j, (Integer) 0, (Integer) 0);
        this.colorDifferent();
    }
    
    
    /**
     * Mueve una caja de una posicion a otra si hay espacio en el lugar a mover.
     *
     * @param  from[] to[] {1,1} {1,2} 
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
        this.planValores[k][l]+=1;
        if (this.planValores[i][j]>0 && (this.planValores[k][l]<this.lenght && this.planValores[k][l]<this.width))
        {
            this.planBodegaTop[i][j].changeColor("magenta");
            this.planBodegaEntry[this.lenght-this.planValores[i][j]][j].changeColor("magenta");
            this.planBodegaLado[this.width-this.planValores[i][j]][i].changeColor("magenta");
            this.planValores[i][j]-=1;
            this.planBodegaTop[k][l].changeColor("blue");
            this.planBodegaLado[this.width-this.planValores[k][l]][k].changeColor("blue");
            this.planBodegaEntry[this.lenght-this.planValores[k][l]][l].changeColor("blue");
            this.sePudo=true;
            this.loadUndo("arrange",(Integer) i+1, (Integer) j+1, (Integer) k+1, (Integer) l+1);
        }
        else
        {
            System.out.println("no hay nada que mover en esta posición o no hay espacio en la posicion a mover");
            this.sePudo=false;
        }
        this.colorDifferent();
    }
    
    
    /**
     * Calcula la cantidad de cajas robadas del plan anterior.
     *
     * @return la cantidad de cajas robadas del plan anterior.
     */
    public int stolen()
    {
        return this.lastStolenCrates;
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
     * verifica la cantidad de cajas que hay en la bodega del plan.
     *
     * @return Devuelve la matriz de valores de la bodega del plan.
     */
    public int[][] layout()
    {
        return this.planValores;
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
              this.bodegaTop[i][j].makeVisible();
              this.bodegaLado[i][j].makeVisible();
              this.bodegaEntry[i][j].makeVisible();
              this.planBodegaTop[i][j].makeVisible();
              this.planBodegaLado[i][j].makeVisible();
              this.planBodegaEntry[i][j].makeVisible();
              this.loadUndo("makeVisible",(Integer) 0, (Integer) 0, (Integer) 0, (Integer) 0);
           } 
        }
    }
    
    
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y
     */
    private void makeInvisible(String bodega,String plan)
    {
        for (int i=0;i<this.lenght;i++)
        {
           for (int j=0;j<this.width;j++)
           {
              if (bodega=="yes"){
                  this.bodegaTop[i][j].makeInvisible();
                  this.bodegaLado[i][j].makeInvisible();
                  this.bodegaEntry[i][j].makeInvisible();
              }
              else if (plan=="yes"){
                  this.planBodegaTop[i][j].makeInvisible();
                  this.planBodegaLado[i][j].makeInvisible();
                  this.planBodegaEntry[i][j].makeInvisible();
              }
           } 
        }
    }
    
    
    
    /**
     * Oculta todas las camaras y planos de la bodega.
     *
     */
    public void makeInvisible()
    {
        makeInvisible("yes","yes");
        this.loadUndo("makeInvisible",(Integer) 0, (Integer) 0, (Integer) 0, (Integer) 0);
    }
    
    
    /**
     * Acaba con el simulador y reincia todos los datos.
     *
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
              this.bodegaEntry[i][j].changeColor("green");
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
     * @return Devuelve un valor booleano dependiendo si se pudo realizar la accion.
     */
    public boolean ok()
    {
        return this.sePudo;
    }
    
    
    /**
     * Verifica si la bodega original y la del plan son iguales
     *
     * @return      Booleano según si son iguales o no
     */
    private boolean areEqual()
    {
        for (int i = 0; i < this.lenght; i++){
            for (int j = 0; j < this.width; j++){
                if (this.valores[i][j] != this.planValores[i][j]){
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
                  if(this.planBodegaTop[i][j].color=="magenta")
                  {
                      this.planBodegaTop[i][j].changeColor("red");
                  }
                  if(this.planBodegaLado[i][j].color=="magenta")
                  {
                      this.planBodegaLado[i][j].changeColor("red");
                  }
                  if(this.planBodegaEntry[i][j].color=="magenta")
                  {
                      this.planBodegaEntry[i][j].changeColor("red");
                  }
               } 
            }
        }else{
           this.copy();
        }
    }

    
        /**
     * An example of a method - replace this comment with your own
     *
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y
     */
    private void loadUndo(String accion,Integer i, Integer j, Integer k, Integer l)
    {
        this.ultimaAccion.push(accion);
        Integer[] temp = new Integer[4];
        temp[0] = i;
        temp[1] = j;
        temp[2] = k;
        temp[3] = l;
        this.coordenadas.add(temp);
    }

    
    /**
     * deshace la ultima accion realizada
     */
    public void undo()
    {
        int i, j, k, l;
        Integer[] values = coordenadas.get(coordenadas.size()-1);
        i = (int) values[0];
        j = (int) values[1];
        k = (int) values[2];
        l = (int) values[3];
        switch(ultimaAccion.peek()){
            case "copy":
                ceros("no","yes");
                makeInvisible("no","yes");
                break;
            case "store":
                k=this.valores[i][j];
                this.bodegaTop[i][j].changeColor("green");
                this.bodegaLado[this.width-k][i].changeColor("green");
                this.bodegaEntry[this.lenght-k][j].changeColor("green");
                this.valores[i][j]-=1;
                break;
            case "steal":
                returnCrate();
                break;
            case "arrange":
                int[] from = new int[2];
                from[0] = k;
                from[1] = l;
                int[] to = new int[2];
                to[0] = i;
                to[1] = j;
                arrange(from,to);
                break;
            case "return":
                steal(i,j);
                break;
            case "makeVisible":
                makeInvisible("yes", "yes");
                break;
            case "makeInvisible":
                makeVisible();
                break;
        }
        this.undo.push(ultimaAccion.peek());
        this.ultimaAccion.pop();
        this.undoCoor.add(this.coordenadas.get(this.coordenadas.size()-1));
        this.coordenadas.remove(this.coordenadas.size()-1);
    }
    
    
    /**
     * rehace la accion que se deshizo en undo()
     */
    public void redo()
    {
        int i, j, k, l;
        Integer[] values = this.undoCoor.get(this.undoCoor.size()-1);
        i = (int) values[0];
        j = (int) values[1];
        k = (int) values[2];
        l = (int) values[3];
        switch(undo.peek()){
            case "copy":
                copy();
                break;
            case "store":
                store(i,j);
                break;
            case "steal":
                steal(i,j);
                break;
            case "arrange":
                int[] from = new int[2];
                from[0] = i;
                from[1] = j;
                int[] to = new int[2];
                to[0] = k;
                to[1] = l;
                arrange(from,to);
                break;
            case "return":
                returnCrate();
                break;
            case "makeVisible":
                makeInvisible("yes", "yes");
                break;
            case "makeInvisible":
                makeVisible();
                break;
        }
        this.undo.pop();
        this.undoCoor.remove(this.undoCoor.size()-1);
    }
    
    
    /**
     * Hace zoom en el canvas
     */
    public void zoom(char z)
    {
        if(z == '-'){
            this.size -= this.size*0.1;
        }
        else{
            this.size += this.size*0.1;
        }
        for (int i=0;i<this.lenght;i++)
        {
           for (int j=0;j<this.width;j++)
           {
               this.bodegaTop[i][j].zoom(z);
               this.bodegaLado[i][j].zoom(z);
               this.bodegaEntry[i][j].zoom(z);
               this.planBodegaTop[i][j].zoom(z);
               this.planBodegaLado[i][j].zoom(z);
               this.planBodegaEntry[i][j].zoom(z);
               this.bodegaTop[i][j].moveVertical(i*this.size);
               this.bodegaTop[i][j].moveHorizontal(j*this.size);
               this.bodegaLado[i][j].moveVertical(i*this.size);
               this.bodegaLado[i][j].moveHorizontal(this.width*this.size+j*this.size+25);
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
     * muestra en una matriz las cajas robadas
     * 
     * @return  robadas: matriz de las cajas robadas.
     */
    public ArrayList<String> check()
    {
        return this.robadas;
    }

}
