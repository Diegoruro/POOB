package mission;

import java.util.ArrayList;

/**
 * El boceto de la bodega principal con todas las cajas que se movieron por ultima vez
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
    public void steal(int i,int j) throws MissionException
    {
        i--;
        j--;
        if (this.valores[i][j]>0)
        {
            if(this.cajas[i][j].getTipo() == "safe"){
                this.sePudo=false;
                throw new MissionException(MissionException.INVALIDSTEAL);
            }else{
                String crate=i+"-"+j;
                this.robadas.add(crate);
                this.stolenCrates++;
                this.top[i][j].changeColor("yellow");
                this.lado[this.lenght-this.valores[i][j]][i].changeColor("yellow");
                this.entry[this.lenght-this.valores[i][j]][j].changeColor("yellow");
                this.valores[i][j]-=1;
                this.sePudo=true;
            }
        }
        else
        {
            this.sePudo=false;
            throw new MissionException(MissionException.INVALIDSTEAL);
        }
        
    }
    
    /**
     * Roba una caja de la bodega en el plan.
     *
     * @param Posicion en i y en j de la caja a robar [i,j] {2,3}
     */
    public void steal(int[] crate)
    {
        try{
            this.steal(crate[0],crate[1]);
        }catch(MissionException e){
            System.out.println(MissionException.INVALIDSTEAL);
        }
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
        this.lado[this.lenght-this.valores[i][j]][i].changeColor("blue");
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
        try{
            isPosible(this.cajas[i][j].getTipo(), k+1,l+1);
            
            if (this.valores[i][j]>0 && (this.valores[k][l]+1<this.lenght && this.valores[k][l]+1<this.width))
            {
                this.valores[k][l]+=1;
                this.top[i][j].changeColor("magenta");
                this.entry[this.lenght-this.valores[i][j]][j].changeColor("magenta");
                this.lado[this.lenght-this.valores[i][j]][i].changeColor("magenta");
                this.valores[i][j]-=1;
                String newColor = this.cajas[i][j].color;
                this.top[k][l].changeColor(newColor);
                this.lado[this.lenght-this.valores[k][l]][k].changeColor(newColor);
                this.entry[this.lenght-this.valores[k][l]][l].changeColor(newColor);
                this.sePudo=true;
                
            }
            else
            {
                this.sePudo=false;
                throw new MissionException(MissionException.INVALIDARRANGE);
            }
        }catch(MissionException e){
            System.out.println(MissionException.INVALIDARRANGE);
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
