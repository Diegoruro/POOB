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
    public int width;
    public int lenght;
    public Principal ppal;
    public Plan plan;
    public Stack<Accion> acciones;
    public Stack<Accion> undos;
    public boolean sePudo;
    
    /**
     * Constructor for objects of class Mission
     */
    public Mission(int largo,int ancho)
    {
        this.lenght = largo;
        this.width = ancho;
        this.acciones = new Stack<Accion>();
        this.undos = new Stack<Accion>();
        crearBodega(largo,ancho);
    }
    
    
    /**
     * Constructor for objects of class Mission
     */
    public Mission(int largo,int ancho, int[][] heights)
    {
        this.lenght = largo;
        this.width = ancho;
        crearBodega(largo,ancho);
        this.acciones = new Stack<Accion>();
        this.undos = new Stack<Accion>();
        this.ppal.valores = heights;
        for (int i=0;i<largo;i++)
       {
            for (int j=0;j<ancho;j++)
            {   
                int x=this.ppal.valores[i][j];
                if (x>0)
                {
                    for (int k=this.ppal.valores[i][j];k>0;k--)
                    {
                        this.ppal.top[i][j].changeColor("blue");
                        this.ppal.lado[ancho-k][i].changeColor("blue");
                        this.ppal.entry[largo-k][j].changeColor("blue");
                    }
                }
                this.ppal.top[i][j].makeVisible();
                this.ppal.lado[i][j].makeVisible();
                this.ppal.entry[i][j].makeVisible();
            }
       }
    }    
    
    
    /**
     * Crea ambas bodegas y dibuja la bodega principal
     *
     */
    private void crearBodega(int largo, int ancho){
        this.ppal = new Principal(largo, ancho);
        this.plan = new Plan(largo, ancho);
        this.ppal.draw();
        this.plan.draw();
    }
    
    /**
     * Guarda una caja en la bodega real
     *
     * @param  i,j   1,1
     */
    public void store(int i, int j){
        this.ppal.store(i, j);
        this.loadUndo("store",i,j,null,null);
        if (this.plan.isVisible){
            this.colorDifferent();
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
     * Roba una caja de la bodega en el plan.
     *
     * @param  i,j 1,1
     */
    public void steal(int i,int j){
        this.plan.steal(i, j);
        this.loadUndo("steal",(Integer) i, (Integer) j, (Integer) 0, (Integer) 0);
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
        String lastCrate = this.plan.robadas.get(this.plan.robadas.size() - 1);
        String coordenada[] = lastCrate.split("-");
    
        int i = Integer.parseInt(coordenada[0]);
        int j = Integer.parseInt(coordenada[1]);
        
        this.plan.returnCrate();
        
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
        this.plan.arrange(from, to);
        int i=from[0];
        int j=from[1];
        int k=to[0];
        int l=to[1];
        i--;
        j--;
        k--;
        l--;
        this.loadUndo("arrange",(Integer) i+1, (Integer) j+1, (Integer) k+1, (Integer) l+1);
        this.colorDifferent();
    }
    
    /**
     * muestra en una matriz las cajas robadas
     * 
     * @return  robadas: matriz de las cajas robadas.
     */
    public ArrayList<String> toSteal()
    {
        return this.plan.toSteal();
    }
    
    /**
     * Calcula la cantidad de cajas robadas del plan anterior.
     *
     * @return la cantidad de cajas robadas del plan anterior.
     */
    public int checkStolen()
    {
        return this.plan.checkStolen();
    }
    
    /**
    * verifica la cantidad de cajas que hay en la bodega del plan.
    *
    * @return Devuelve la matriz de valores de la bodega del plan.
    */
    public int[][] layout()
    {
        return this.plan.layout();
    }
    
    /**
     * verifica la cantidad de cajas que hay en la bodega real.
     *
     * @return Devuelve la matriz de valores de la bodega real.
     */
    public int[][] warehouse()
    {
        return this.ppal.warehouse();
    }
    
    /**
     * Hace una copia de la bodega desde sus 3 puntos de vista
     */
    private void copy2()
    {  
       this.plan.valores = this.ppal.valores;
        for (int i=0;i<this.lenght;i++)
       {
            for (int j=0;j<this.width;j++)
            {   
                int x=this.ppal.valores[i][j];
                if (x>0)
                {
                    for (int k=this.ppal.valores[i][j];k>0;k--)
                    {
                        this.plan.top[i][j].changeColor("blue");
                        this.plan.lado[this.width-k][i].changeColor("blue");
                        this.plan.entry[this.lenght-k][j].changeColor("blue");
                    }
                }
                this.plan.top[i][j].makeVisible();
                this.plan.lado[i][j].makeVisible();
                this.plan.entry[i][j].makeVisible();
            }
       }
        this.plan.lastStolenCrates=this.plan.stolenCrates;
        this.plan.stolenCrates=0;
        this.sePudo=true;
    }
    
    
    /**
     * Hace una copia de la bodega desde sus 3 puntos de vista
     */
    public void copy()
    {  
       this.plan.valores = this.ppal.valores;
       for (int i=0;i<this.lenght;i++)
       {
           for (int j=0;j<this.width;j++)
           {
                  this.plan.top[i][j].changeColor("magenta");
                  this.plan.lado[i][j].changeColor("magenta");
                  this.plan.entry[i][j].changeColor("magenta");
           } 
       }
        for (int i=0;i<this.lenght;i++)
       {
            for (int j=0;j<this.width;j++)
            {   
                int x=this.ppal.valores[i][j];
                if (x>0)
                {
                    for (int k=this.ppal.valores[i][j];k>0;k--)
                    {
                        this.plan.top[i][j].changeColor("blue");
                        this.plan.lado[this.width-k][i].changeColor("blue");
                        this.plan.entry[this.lenght-k][j].changeColor("blue");
                    }
                }
                this.plan.top[i][j].makeVisible();
                this.plan.lado[i][j].makeVisible();
                this.plan.entry[i][j].makeVisible();
            }
       }
        this.plan.lastStolenCrates=this.plan.stolenCrates;
        this.plan.stolenCrates=0;
        this.sePudo=true;
        this.loadUndo("copy",(Integer) 0, (Integer) 0, (Integer) 0, (Integer) 0);
    }    
    
    
    /**
     * Hace Visibles ambas bodegas
     */
    public void makeVisible(){
        this.plan.makeVisible();
        this.ppal.makeInvisible();
        this.loadUndo("makeVisible",(Integer) 0, (Integer) 0, (Integer) 0, (Integer) 0);
    }
    
    
    /**
     * Hace invisibles ambas bodegas
     */
    public void makeInvisible(){
        this.plan.makeInvisible();
        this.ppal.makeInvisible();
        this.loadUndo("makeInvisible",(Integer) 0, (Integer) 0, (Integer) 0, (Integer) 0);
    }
    
    
    /**
     * Acaba con el simulador y reinicia todos los datos.
     *
     */
    public void finish()
    {
      this.makeInvisible();
      this.plan.stolenCrates=0;
      this.plan.lastStolenCrates=0;
      this.plan.robadas.clear();
      for (int i=0;i<this.lenght;i++)
        {
           for (int j=0;j<this.width;j++)
           {
              this.ppal.top[i][j].changeColor("green");
              this.ppal.lado[i][j].changeColor("green");
              this.ppal.entry[i][j].changeColor("green");
              this.plan.top[i][j].changeColor("magenta");
              this.plan.lado[i][j].changeColor("magenta");
              this.plan.entry[i][j].changeColor("magenta");
              this.plan.valores[i][j]=0;
              this.ppal.valores[i][j]=0;
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
    public boolean areEqual()
    {
        for (int i = 0; i < this.lenght; i++){
            for (int j = 0; j < this.width; j++){
                if (this.ppal.valores[i][j] != this.plan.valores[i][j]){
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
                  if(this.plan.top[i][j].color=="magenta")
                  {
                      this.plan.top[i][j].changeColor("red");
                  }
                  if(this.plan.lado[i][j].color=="magenta")
                  {
                      this.plan.lado[i][j].changeColor("red");
                  }
                  if(this.plan.entry[i][j].color=="magenta")
                  {
                      this.plan.entry[i][j].changeColor("red");
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
        Integer[] temp = new Integer[4];
        temp[0] = i;
        temp[1] = j;
        temp[2] = k;
        temp[3] = l;
        Accion action = new Accion(accion, temp);
        acciones.push(action);
    }

    
    /**
     * deshace la ultima accion realizada
     */
    public void undo()
    {
        int i, j, k, l;
        Integer[] values = acciones.peek().coordenadas;
        i = (int) values[0];
        j = (int) values[1];
        k = (int) values[2];
        l = (int) values[3];
        switch(acciones.peek().action){
            case "copy":
                this.plan.ceros();
                this.plan.makeInvisible();
                break;
            case "store":
                k=this.ppal.valores[i][j];
                this.ppal.top[i][j].changeColor("green");
                this.ppal.lado[this.width-k][i].changeColor("green");
                this.ppal.entry[this.lenght-k][j].changeColor("green");
                this.ppal.valores[i][j]-=1;
                values[0]++;
                values[1]++;
                break;
            case "steal":
                this.plan.valores[i][j]+=1;
                k=this.plan.valores[i][j];
                this.plan.top[i][j].changeColor("blue");
                this.plan.lado[this.width-k][i].changeColor("blue");
                this.plan.entry[this.lenght-k][j].changeColor("blue");
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
                this.plan.arrange(from,to);
                this.acciones.pop();
                break;
            case "return":
                this.plan.steal(i,j);
                break;
            case "makeVisible":
                this.makeInvisible();
                break;
            case "makeInvisible":
                this.makeVisible();
                this.acciones.pop();
                break;
            case "-":
                zoom('+');
                this.acciones.pop();
                break;
            case "+":
                zoom('-');
                this.acciones.pop();
                break;
        }
        
        this.undos.push(acciones.peek());
        this.acciones.pop();
    }
    
    
    /**
     * rehace la accion que se deshizo en undo()
     */
    public void redo()
    {
        int i, j, k, l;
        Integer[] values = this.undos.peek().coordenadas;
        i = (int) values[0];
        j = (int) values[1];
        k = (int) values[2];
        l = (int) values[3];
        switch(undos.peek().action){
            case "copy":
                copy();
                break;
            case "store":
                this.ppal.store(i,j);
                break;
            case "steal":
                this.plan.steal(i,j);
                break;
            case "arrange":
                int[] from = new int[2];
                from[0] = k;
                from[1] = l;
                int[] to = new int[2];
                to[0] = i;
                to[1] = j;
                this.plan.arrange(from,to);
                break;
            case "return":
                this.plan.returnCrate();
                break;
            case "makeVisible":
                this.makeVisible();
                break;
            case "makeInvisible":
                this.makeInvisible();
                break;
            case "-":
                zoom('-');
                break;
            case "+":
                zoom('+');
                break;
        }
        this.undos.pop();
    }
    
    
    /**
     * Hace zoom en el canvas
     */
    public void zoom(char z)
    {
        if(z == '-'){
            this.loadUndo("-",(Integer) 0, (Integer) 0, (Integer) 0, (Integer) 0);
            this.ppal.size -= this.ppal.size*0.1;
        }
        else{
            this.loadUndo("+",(Integer) 0, (Integer) 0, (Integer) 0, (Integer) 0);
            this.ppal.size += this.ppal.size*0.1;
        }
        this.ppal.restorePosition();
        this.plan.restorePosition();
        for (int i=0;i<this.lenght;i++)
        {
           for (int j=0;j<this.width;j++)
           {
               this.ppal.top[i][j].zoom(z);
               this.ppal.lado[i][j].zoom(z);
               this.ppal.entry[i][j].zoom(z);
               this.plan.top[i][j].zoom(z);
               this.plan.lado[i][j].zoom(z);
               this.plan.entry[i][j].zoom(z);
               this.ppal.top[i][j].moveVertical(i*this.ppal.size);
               this.ppal.top[i][j].moveHorizontal(j*this.ppal.size);
               this.ppal.lado[i][j].moveVertical(i*this.ppal.size);
               this.ppal.lado[i][j].moveHorizontal(this.width*this.ppal.size+j*this.ppal.size+25);
               this.ppal.entry[i][j].moveVertical(i*this.ppal.size);
               this.ppal.entry[i][j].moveHorizontal((this.width*this.ppal.size)*2+j*this.ppal.size+50);
               this.plan.top[i][j].moveVertical(this.lenght*this.ppal.size+25+i*this.ppal.size);
               this.plan.top[i][j].moveHorizontal(j*this.ppal.size);
               this.plan.lado[i][j].moveVertical(this.lenght*this.ppal.size+25+i*this.ppal.size);
               this.plan.lado[i][j].moveHorizontal(this.width*this.ppal.size+j*this.ppal.size+25);
               this.plan.entry[i][j].moveVertical(this.lenght*this.ppal.size+25+i*this.ppal.size);
               this.plan.entry[i][j].moveHorizontal((this.width*this.ppal.size)*2+j*this.ppal.size+50);
           } 
        }
    }
}
    
    
