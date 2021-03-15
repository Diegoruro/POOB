package domain;
import java.util.*;


/*No olviden adicionar la documentacion*/
public class AutomataCelular{
    static private int LONGITUD=30;
    private Elemento[][] automata;
    private Elemento[][] newMatrix;
    
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
    
    public int vecinos(int fila, int columna)
    {
        Elemento[] vecinos = new Elemento[8];
        int cont = 0;
        for (int i=fila-1;i<fila+2;i++){
            for (int j=columna-1;j<columna+2;j++){
                if (i!=fila || j!=columna){
                    vecinos[cont] = this.getElemento(i+1, j+1);
                    cont++;
                }
            }
        }

        int vecinosVivos = 0;
        for (Elemento vecino: vecinos){
            if (vecino != null && vecino.isVivo()){
                vecinosVivos++;
            }
        }
        return vecinosVivos;
    }
    
    public Elemento[][] getAutomata(){
        return this.automata;
    }
    
    public Elemento[][] getMatrix(){
        return this.newMatrix;
    }
    
    public int  getLongitud(){
        return LONGITUD;
    }

    public Elemento getElemento(int f,int c){
        return automata[f][c];
    }

    public void setElemento(int f, int c, Elemento nueva){
        automata[f][c]=nueva;
    }

    public void algunosElementos(){
        Celula indiana = new Celula(this, 1,1);
        Celula OO7 = new Celula(this, 2,2);
    }
    
    public void ticTac(){
        for (int i=0; i<LONGITUD;i++){
            for (int j=0; j<LONGITUD; j++){
                if (automata[i][j] != null){
                    int vivos = automata[i][j].getVecinosVivos();
                    automata[i][j].decida();
                    automata[i][j].cambie();
                }
            }
        }
    }
    
    public void modifyMatrix(){
        Elemento[][] matrix = this.automata;
        int n = this.getLongitud()+2;
        newMatrix = new Elemento[n][n];
        for (int i=0; i<n;i++){
            for (int j=0; j<n; j++){
                if (i==0 || j==0 || i==n-1 || j==n-1){
                    newMatrix[i][j] = null;
                }else{
                    newMatrix[i][j] = matrix[i-1][j-1];
                }
            }
        }
        
    }

}
