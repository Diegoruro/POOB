package mission;

import java.util.ArrayList;

public class MissionContest
{
    public ArrayList<Integer> mr = new ArrayList<Integer>();
    public ArrayList<Integer> mc = new ArrayList<Integer>();
    public ArrayList<ArrayList<Integer>> matrix= new ArrayList<ArrayList<Integer>>();
    public int robadas;
    public ArrayList<ArrayList<Integer>> copia;
    public ArrayList<ArrayList<Integer>> coordenadas;
    public ArrayList<Integer> cuantas;
    public int rows;
    public int columns;
    public int[][] heights;
    public Mission mision;
    
    
    /**
     * Constructor for objects of class MissionContest
     */
    public MissionContest(int[][] matrix)
    {
        this.rows=matrix.length;
        this.columns=matrix[0].length;
        this.heights = matrix;
        coordenadas = new ArrayList<ArrayList<Integer>>();
        cuantas = new ArrayList<Integer>();
        for (int i=0; i<rows;i++){
            ArrayList<Integer> fila = new ArrayList<Integer>();
            this.matrix.add(fila);
            for (int j=0;j<columns;j++){
                Integer num = matrix[i][j];
                this.matrix.get(i).add(num);
            }
        }
        this.solve(this.matrix);
   
        //System.out.print(this.solve(this.matrix));
    }
    
    
    private void calcMax(ArrayList<ArrayList<Integer>> matrix,int r, int c){
        for (int i=0;i<r;i++){
            int maxr=0;
            for (int j=0;j<c;j++){
                if (matrix.get(i).get(j)>maxr){
                    maxr=matrix.get(i).get(j);
                } 
            }
            this.mr.add(maxr);
        }
        for (int i=0;i<c;i++){
            int maxc=0;
            for (int j=0;j<r;j++){
                if (matrix.get(j).get(i)>maxc) {
                    maxc=matrix.get(j).get(i);
                }
                
            }
            this.mc.add(maxc);
        }
    }

    private int contar(ArrayList<Integer> arreglo,int num){
        int cant=0;
        for (int i=0;i<arreglo.size();i++){
            if(num==arreglo.get(i)){
                cant++;
            }
        }
        return cant;
    }


    private ArrayList<ArrayList<Integer>> copy(ArrayList<ArrayList<Integer>> matrix,int rows,int columns){
        ArrayList<ArrayList<Integer>> copia = new ArrayList<ArrayList<Integer>>();
        for (int i=0; i<rows;i++){
            ArrayList<Integer> fila = new ArrayList<Integer>();
            copia.add(fila);
            for (int j=0;j<columns;j++){
                Integer num = matrix.get(i).get(j);
                copia.get(i).add(num);
            }
        }
        return copia;
    }


    public int solve(ArrayList<ArrayList<Integer>> matrix){
        int r=matrix.size();
        int c=matrix.get(0).size();
        this.robadas = 0;
        this.copia = copy(matrix,r,c);
        this.calcMax(matrix,r,c);
        boolean flagc=false;
        for (int i = 0;i<r;i++){
            boolean flagr=false;
            for (int j=0;j<c;j++){
                if (1<matrix.get(i).get(j) && matrix.get(i).get(j)<this.mr.get(i) && matrix.get(i).get(j)<this.mc.get(j)){
                    this.cuantas.add(matrix.get(i).get(j)-1);
                    this.robadas+=matrix.get(i).get(j)-1;
                    this.copia.get(i).set(j,1);
                    ArrayList<Integer> aux = new ArrayList<Integer>();
                    aux.add(i);
                    aux.add(j);
                    this.coordenadas.add(aux);
                }
                if (1<matrix.get(i).get(j) && matrix.get(i).get(j)<=this.mr.get(i) && matrix.get(i).get(j)<this.mc.get(j) && contar(matrix.get(i),mr.get(i))>1 && !flagr){
                    this.cuantas.add(matrix.get(i).get(j)-1);
                    this.robadas+=matrix.get(i).get(j)-1;
                    this.copia.get(i).set(j,1);
                    flagr=true;
                    ArrayList<Integer> aux = new ArrayList<Integer>();
                    aux.add(i);
                    aux.add(j);
                    this.coordenadas.add(aux);
                }
                if (1<matrix.get(i).get(j) && matrix.get(i).get(j)<=this.mr.get(i) && matrix.get(i).get(j)<this.mc.get(j) && !flagc ){
                    this.cuantas.add(matrix.get(i).get(j)-1);
                    this.robadas+=matrix.get(i).get(j);
                    this.copia.get(i).set(j,1);;
                    flagc=true;
                    ArrayList<Integer> aux = new ArrayList<Integer>();
                    aux.add(i);
                    aux.add(j);
                    this.coordenadas.add(aux);
                }
            }
        }
        
        ArrayList<Integer> pos1= new ArrayList<Integer>();
        ArrayList<Integer> pos2= new ArrayList<Integer>();
        boolean move = false;
        
        for (int i=0;i<r-1;i++){
            for (int j = 0; j < c; j++) {
                if (this.copia.get(i+1).contains(this.copia
                .get(i).get(j)) && (mr.contains(this.copia.get(i).get(j))||mc.contains(this.copia.get(i).get(j)))){
                    pos1.add(i);
                    pos1.add(j);
                    pos2.add(i+1);
                    pos2.add(1);
                    move = true;
                    break;
                }
                if (move){
                    break;
                }
            }
        }
        if (move){
            this.copia.get(pos2.get(0)).set(pos2.get(1),this.copia.get(pos2.get(0)).get(pos1.get(1)));
            this.copia.get(pos2.get(0)).set(pos1.get(1),this.copia.get(pos2.get(0)).get(pos2.get(1)));
            if (1<this.copia.get(pos2.get(0)).get(pos1.get(1)) && this.copia.get(pos2.get(0)).get(pos1.get(1))<=mr.get(pos2.get(0)) && this.copia.get(pos2.get(0)).get(pos1.get(1))<=mc.get(pos1.get(1))){
                this.robadas += this.copia.get(pos2.get(0)).get(pos1.get(1))-1;
                this.copia.get(pos2.get(0)).set(pos1.get(1),1);
            }
        }
        return this.robadas;
    }
    
    public void simulate(boolean slow){
        this.mision = new Mission(this.rows, this.columns, this.heights);
        this.mision.copy();
        for (int i = 0; i<coordenadas.size();i++){
            this.mision.steal(coordenadas.get(i).get(0)+1, coordenadas.get(i).get(1)+1);
        }
    }
}
