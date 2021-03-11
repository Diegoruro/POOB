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
    public Bodega ppal;
    public Bodega plan;
    public ArrayList<Accion> acciones;
    public ArrayList<Accion> undos;
    public boolean sePudo;
    
    /**
     * Constructor for objects of class Mission
     */
    public Mission(int largo,int ancho)
    {
        this.ppal = new Bodega(largo, ancho);
        this.plan = new Bodega(largo, ancho);
        this.acciones = new ArrayList<Accion>();
        this.undos = new ArrayList<Accion>();
        crearBodega(largo,ancho);
    }
    
    
    /**
     * Constructor for objects of class Mission
     */
    public Mission(int largo,int ancho, int[][] heights)
    {
        this.lenght=largo;
        this.width=ancho;
        crearBodega(this.lenght,this.width);
        this.robadas=new ArrayList();
        this.ultimaAccion=new Stack();
        this.undo=new Stack();
        this.ppal.valores = heights;
        for (int i=0;i<this.lenght;i++)
       {
            for (int j=0;j<this.width;j++)
            {   
                int x=this.valores[i][j];
                if (x>0)
                {
                    for (int k=this.valores[i][j];k>0;k--)
                    {
                        this.bodegaTop[i][j].changeColor("blue");
                        this.bodegaLado[this.width-k][i].changeColor("blue");
                        this.bodegaEntry[this.lenght-k][j].changeColor("blue");
                    }
                }
                this.bodegaTop[i][j].makeVisible();
                this.bodegaLado[i][j].makeVisible();
                this.bodegaEntry[i][j].makeVisible();
            }
       }
    }
    
    
    /**
     * Este metodo crea las bodegas, tanto la bodega de la camara como la bodega del plan con las 3 camaras
     * 
     */
    private void crearBodega(int largo,int ancho)
    {
        /*
        for (int i=0;i<largo;i++)
        {
            for (int j=0;j<ancho;j++)
            {   
                
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
        this.isVisibleBodega=true;
        this.isVisiblePlanBodega=false;
        */
    }  

    
    
    
    /**
     * Hace una copia de la bodega desde sus 3 puntos de vista
     */
    private void copy2()
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
     * Pinta el plan de rojo si las bodegas son diferentes
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
           this.copy2();
        }
    }

    
     /**
     * agrega la información para el metodo undo
     *
     * @param  accion:ultima accion realizada por el usuario
     *         los siguientes parametros son solo si el usuario realizo una accion que tenga que ver con la manipulacion 
     *         de las ubicaciones de las cajas o las creaciones de estas mismas, de lo contrario estas son 0.
     *         i:fila incial
     *         j:columna incial
     *         los siguientes parametros son si se efectua un arrange, de lo contrario estos son 0.
     *         k:fila final
     *         l:columna final
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
                this.ceros("no","yes");
                this.makeInvisible("no","yes");
                break;
            case "store":
                k=this.valores[i][j];
                this.bodegaTop[i][j].changeColor("green");
                this.bodegaLado[this.width-k][i].changeColor("green");
                this.bodegaEntry[this.lenght-k][j].changeColor("green");
                this.valores[i][j]-=1;
                values[0]++;
                values[1]++;
                break;
            case "steal":
                this.planValores[i][j]+=1;
                k=this.planValores[i][j];
                this.planBodegaTop[i][j].changeColor("blue");
                this.planBodegaLado[this.width-k][i].changeColor("blue");
                this.planBodegaEntry[this.lenght-k][j].changeColor("blue");
                values[0]++;
                values[1]++;
                this.colorDifferent();
                break;
            case "arrange":
                int[] from = new int[2];
                from[0] = k;
                from[1] = l;
                int[] to = new int[2];
                to[0] = i;
                to[1] = j;
                arrange(from,to);
                this.ultimaAccion.pop();
                break;
            case "return":
                steal(i,j);
                break;
            case "makeVisible":
                makeInvisible("yes", "yes");
                break;
            case "makeInvisible":
                makeVisible();
                this.ultimaAccion.pop();
                break;
            case "-":
                zoom('+');
                this.ultimaAccion.pop();
                break;
            case "+":
                zoom('-');
                this.ultimaAccion.pop();
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
                from[0] = k;
                from[1] = l;
                int[] to = new int[2];
                to[0] = i;
                to[1] = j;
                arrange(from,to);
                break;
            case "return":
                returnCrate();
                break;
            case "makeVisible":
                makeVisible();
                break;
            case "makeInvisible":
                makeInvisible();
                break;
            case "-":
                zoom('-');
                break;
            case "+":
                zoom('+');
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
            this.loadUndo("-",(Integer) 0, (Integer) 0, (Integer) 0, (Integer) 0);
            this.size -= this.size*0.1;
        }
        else{
            this.loadUndo("+",(Integer) 0, (Integer) 0, (Integer) 0, (Integer) 0);
            this.size += this.size*0.1;
        }
        this.restorePosition();
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
     * reestablece la posicion inicial de todos los rectangulos
     */
    private void restorePosition()
    {
        for (int i=0;i<this.lenght;i++)
        {
           for (int j=0;j<this.width;j++)
           {
               this.bodegaTop[i][j].setXPosition(70);
               this.bodegaTop[i][j].setYPosition(15);
               this.bodegaLado[i][j].setXPosition(70);
               this.bodegaLado[i][j].setYPosition(15);
               this.bodegaEntry[i][j].setXPosition(70);
               this.bodegaEntry[i][j].setYPosition(15);
               this.planBodegaTop[i][j].setXPosition(70);
               this.planBodegaTop[i][j].setYPosition(15);
               this.planBodegaLado[i][j].setXPosition(70);
               this.planBodegaLado[i][j].setYPosition(15);
               this.planBodegaEntry[i][j].setXPosition(70);
               this.planBodegaEntry[i][j].setYPosition(15);
           } 
        }
    }

}
