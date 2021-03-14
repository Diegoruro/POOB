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
    }
    
    public void ticTac(){
        
    }
    
    public void modifyMatrix(){
        Elemento[][] matrix = this.automata;
        int n = this.getLongitud()+2;
        newMatrix = new Elemento[n][n];
        for (int i=0; i<n;i++){
            for (int j=0; i<n; i++){
                if (i==0 || j==0 || i==n-1 || j==n-1){
                    newMatrix[i][j] = null;
                }else{
                    System.out.println("changing");
                    newMatrix[i][j] = matrix[i-1][j-1];
                }
            }
        }
        
    }

}
