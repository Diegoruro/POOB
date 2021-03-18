package domain;
import java.util.*;


/*No olviden adicionar la documentacion*/
public class AutomataCelular{
    static private int LONGITUD=30;
    private Elemento[][] automata;
    private Elemento[][] newMatrix;
    public static int poblacion;
    
    public AutomataCelular() {
        automata=new Elemento[LONGITUD][LONGITUD];
        for (int f=0;f<LONGITUD;f++){
            for (int c=0;c<LONGITUD;c++){
                automata[f][c]=null;
            }
        }
        algunosElementos();
        modifyMatrix();
    }
    
    /**Retorna la lista de los vecinos que rodean el elemento en la posicion indiciada
     * @param   int fila numero de fila de la posicion del elemento
     *          int columna numero de columna de la posicion del elemento
     * @return  
     */
    
    public Elemento[] vecinos(int fila, int columna)
    {
        Elemento[] vecinos = new Elemento[8];
        int cont = 0;
        for (int i=fila-1;i<fila+2;i++){
            for (int j=columna-1;j<columna+2;j++){
                if (i!=fila || j!=columna){
                    vecinos[cont] = this.getElementoMatrix(i+1, j+1);
                    cont++;
                }
            }
        }
        return vecinos;
    }
    
    /**Retorna el automata en el que se encuentra
    @return 
     */
    public Elemento[][] getAutomata(){
        return this.automata;
    }
    
    /**Retorna la longitud del automata 
    @return 
     */
    public int  getLongitud(){
        return LONGITUD;
    }

    /**Retorna el elemento de la posicion indicada
    @param   int fila numero de fila de la posicion del elemento
             int columna numero de columna de la posicion del elemento
    @return 
     */
    public Elemento getElemento(int f,int c){
        return automata[f][c];
    }

    /**Retorna el elemento de la posicion indicada de la copia ampliada de la matriz
    @param   int fila numero de fila de la posicion del elemento
             int columna numero de columna de la posicion del elemento
    @return 
     */
    public Elemento getElementoMatrix(int f,int c){
        return this.newMatrix[f][c];
    }
    
    /**Retorna el elemento de la posicion indicada
    @param   int fila numero de fila de la posicion del elemento
             int columna numero de columna de la posicion del elemento
             Elemento nueva elemento que se va agregar al automata
     */
    public void setElemento(int f, int c, Elemento nueva){
        automata[f][c]=nueva;
    }

    /**inicializa los elementos especificados en el codigo
     */
    public void algunosElementos(){
        /* Indiana y 007
        CelulaNormal indiana = new CelulaNormal(this,1,1);
        CelulaNormal oo7 = new CelulaNormal(this,2,2);
        */
        
        /* Agamenon y Venus
        CelulaEspecial agamenon = new CelulaEspecial(this,5,1);
        CelulaEspecial venus = new CelulaEspecial(this,8,1);
        */
        
        /* Horton y Jhon
        CelulaConway jhon = new CelulaConway(this,5,1);
        CelulaConway horton = new CelulaConway(this,5,2);
        */
       
        /* Bloque
        CelulaConway celulaBloque1 = new CelulaConway(this,28,0);
        CelulaConway celulaBloque2 = new CelulaConway(this,28,1);
        CelulaConway celulaBloque3 = new CelulaConway(this,29,0);
        CelulaConway celulaBloque4 = new CelulaConway(this,29,1);
        */
       
        /* parpadeador
        CelulaConway celulaParpadeador1 = new CelulaConway(this,28,13);
        CelulaConway celulaParpadeador2 = new CelulaConway(this,28,14);
        CelulaConway celulaParpadeador3 = new CelulaConway(this,28,15);
         */
        
    }
    
    /**Pasa un instante de tiempo y muestra el cambio de las celulas
     */
    public void ticTac(){
        for (int i=0; i<LONGITUD;i++){
            for (int j=0; j<LONGITUD; j++){
                Elemento[] vecinos =vecinos(i,j);
                if (automata[i][j] != null){
                    automata[i][j].decida(vecinos);
                }
                else{
                    int vivos=vecinosVivos(vecinos);
                    if(vivos==3){
                        CelulaConway nueva=new CelulaConway(this,i,j);
                        nueva.estado='d';
                    }
                }
                this.modifyMatrix();
            }
        }
        for (int i=0; i<LONGITUD;i++){
            for (int j=0; j<LONGITUD; j++){
                if (automata[i][j] != null){
                    automata[i][j].cambie();
                }
            }
        }
    }
    
    /**Crea una celula en una alguna casilla vacia al rededor de una celula en la posicion indicada
    @param   int fila numero de fila de la posicion de la celula
             int columna numero de columna de la posicion de la celula
    */
    public void newCelula(int fila,int columna){
        boolean flag=false;
        for (int i=fila-1;i<fila+2;i++){
            for (int j=columna-1;j<columna+2;j++){
                if (i!=fila || j!=columna){
                    if(this.getElementoMatrix(i+1,j+1)==null){
                        flag=true;
                        new CelulaNormal(this,i,j);
                        break;
                    }
                }
            }
            if (flag){
                break;
            }
        }
    }    
    
    /**copia la matriz del automata y la amplia en una fila y columna por cada lado
    */
    public void modifyMatrix(){
        Elemento[][] matrix = this.automata;
        int n = this.getLongitud()+2;
        this.newMatrix = new Elemento[n][n];
        for (int i=0; i<n;i++){
            for (int j=0; j<n; j++){
                if (i==0 || j==0 || i==n-1 || j==n-1){
                    this.newMatrix[i][j] = null;
                }else{
                    this.newMatrix[i][j] = matrix[i-1][j-1];
                }
            }
        }
        
    }
    
    /**Retorna los vecinos vivos al rededor de una celula
    @param  Elemento[] vecinos lista de los vecinos al rededor de una celula
    @return
    */
    public int vecinosVivos(Elemento[] vecinos){
        int vivos = 0;
        for (int i=0;i<vecinos.length;i++){
            if (vecinos[i]!=null && vecinos[i].isVivo()){
                vivos++;
            }
        }
        return vivos;
    }
}
