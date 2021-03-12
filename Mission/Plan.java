import java.util.ArrayList;

/**
 * Write a description of class Plan here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Plan extends Bodega
{
    public int stolenCrates=0;
    public int lastStolenCrates=0;
    public ArrayList<String> robadas;

    /**
     * Constructor for objects of class Plan
     */
    public Plan(int lenght, int width)
    {
        super(lenght,width);
        this.robadas=new ArrayList();
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
        if (this.valores[i][j]>0)
        {
            
            String crate=i+"-"+j;
            this.robadas.add(crate);
            this.stolenCrates++;
            this.top[i][j].changeColor("yellow");
            this.lado[this.width-this.valores[i][j]][i].changeColor("yellow");
            this.entry[this.lenght-this.valores[i][j]][j].changeColor("yellow");
            this.valores[i][j]-=1;
            this.sePudo=true;
        }
        else
        {
            System.out.println("no hay nada que robar en esta posición");
            this.sePudo=false;
        }
        
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
        this.valores[i][j]+=1;
        this.top[i][j].changeColor("blue");
        this.lado[this.width-this.valores[i][j]][i].changeColor("blue");
        this.entry[this.lenght-this.valores[i][j]][j].changeColor("blue");
        this.sePudo=true;
    }
    
    /**
    * verifica la cantidad de cajas que hay en la bodega del plan.
    *
    * @return Devuelve la matriz de valores de la bodega del plan.
    */
    public int[][] layout()
    {
        return this.valores;
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
        this.valores[k][l]+=1;
        if (this.valores[i][j]>0 && (this.valores[k][l]<this.lenght && this.valores[k][l]<this.width))
        {
            this.top[i][j].changeColor("magenta");
            this.entry[this.lenght-this.valores[i][j]][j].changeColor("magenta");
            this.lado[this.width-this.valores[i][j]][i].changeColor("magenta");
            this.valores[i][j]-=1;
            this.top[k][l].changeColor("blue");
            this.lado[this.width-this.valores[k][l]][k].changeColor("blue");
            this.entry[this.lenght-this.valores[k][l]][l].changeColor("blue");
            this.sePudo=true;
            
        }
        else
        {
            System.out.println("no hay nada que mover en esta posición o no hay espacio en la posicion a mover");
            this.sePudo=false;
        }
        
    }
    
    /**
     * Calcula la cantidad de cajas robadas del plan anterior.
     *
     * @return la cantidad de cajas robadas del plan anterior.
     */
    public int checkStolen()
    {
        return this.lastStolenCrates;
    }
    
    /**
     * muestra en una matriz las cajas robadas
     * 
     * @return  robadas: matriz de las cajas robadas.
     */
    public ArrayList<String> toSteal()
    {
        return this.robadas;
    }
    
    /**
     * Dibuja la bodega en el tablero
     */
    public void draw(){
        for (int i=0;i<this.lenght;i++)
        {
            for (int j=0;j<this.width;j++)
            {       
                this.top[i][j].moveVertical(this.lenght*this.size+25+i*this.size);
                this.top[i][j].moveHorizontal(j*this.size);
                
                this.lado[i][j].moveVertical(this.lenght*this.size+25+i*this.size);
                this.lado[i][j].moveHorizontal(this.width*this.size+j*this.size+25);
                
                this.entry[i][j].moveVertical(this.lenght*this.size+25+i*this.size);
                this.entry[i][j].moveHorizontal((this.width*this.size)*2+j*this.size+50);
            }
        }
        this.isVisible=true;
    }
}
