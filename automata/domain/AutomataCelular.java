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
    
    public Elemento[][] getAutomata(){
        return this.automata;
    }
    
    public int  getLongitud(){
        return LONGITUD;
    }

    public Elemento getElemento(int f,int c){
        return automata[f][c];
    }

    public Elemento getElementoMatrix(int f,int c){
        return this.newMatrix[f][c];
    }
    
    public void setElemento(int f, int c, Elemento nueva){
        automata[f][c]=nueva;
    }

    public void algunosElementos(){
        /*
        CelulaNormal indiana = new CelulaNormal(this, 1,1);
        CelulaNormal OO7 = new CelulaNormal(this, 2,2);
        CelulaEspecial agamenon = new CelulaEspecial(this, 3,2);
        CelulaEspecial venus = new CelulaEspecial(this, 5,5);
        Calefactor noroeste= new Calefactor(this,0,0);
        Calefactor sureste= new Calefactor(this,29,29);
        CelulaAsustada diego=new CelulaAsustada(this,3,4);
        CelulaAsustada felipe=new CelulaAsustada(this,5,3);*/
        Poblacion poblacion1=new Poblacion(this,0,29);
        Poblacion poblacion2=new Poblacion(this,29,0);
    }
    
    public void ticTac(){
        for (int i=0; i<LONGITUD;i++){
            for (int j=0; j<LONGITUD; j++){
                if (automata[i][j] != null){
                    automata[i][j].cambie();
                    Elemento[] vecinos =vecinos(i,j);
                    automata[i][j].decida(vecinos);
                    this.modifyMatrix();
                }
            }
        }
    }
    
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
}
