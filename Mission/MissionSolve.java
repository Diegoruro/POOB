import java.util.ArrayList;

public class MissionSolve
{
    public ArrayList<Integer> mr;
    public ArrayList<Integer> mc;
    public ArrayList<ArrayList<Integer>> matrix= new ArrayList<ArrayList<Integer>>();
    public int robadas;
    ArrayList<ArrayList<Integer>> copia;
    
    
    /**
     * Constructor for objects of class MissionSolve
     */
    public MissionSolve(int[][] matrix)
    {
        int rows=matrix.length;
        int columns=matrix[0].length;
        for (int i=0; i<rows;i++){
            for (int j=0;j<columns;j++){
                Integer num = matrix[i][j];
                this.matrix.get(i).add(num);
            }
        }
    }
    
    
    private void calcMax(ArrayList<ArrayList<Integer>> matrix,int c, int r){
        for (int i=0;i<r;i++){
            int maxr=0;
            for (int j=0;j<c;j++){
                if (matrix.get(i).get(j)>maxr) {
                    maxr=matrix.get(i).get(j);
                }
                this.mr.add(maxr);
            }

        }
        for (int i=0;i<c;i++){
            int maxc=0;
            for (int j=0;j<r;j++){
                if (matrix.get(j).get(i)>maxc) {
                    maxc=matrix.get(j).get(i);
                }
                this.mc.add(maxc);
            }
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
        System.out.print(String.valueOf(this.copia));
        calcMax(matrix,r,c);
        boolean flagc=false;
        for (int i = 0;i<r;i++){
            boolean flagr=false;
            for (int j=0;j<c;j++){
                if (1<matrix.get(i).get(j) && matrix.get(i).get(j)<this.mr.get(i) && matrix.get(i).get(j)<this.mc.get(j)){
                    this.robadas+=matrix.get(i).get(j)-1;
                }
                if (1<matrix.get(i).get(j) && matrix.get(i).get(j)<=this.mr.get(i) && matrix.get(i).get(j)<this.mc.get(j) && contar(matrix.get(i),mr.get(i))>1 && !flagr){
                    this.robadas+=matrix.get(i).get(j)-1;
                    this.copia.get(i).set(j,1);
                    flagr=true;
                }
                if (1<matrix.get(i).get(j) && matrix.get(i).get(j)<=this.mr.get(i) && matrix.get(i).get(j)<this.mc.get(j) && !flagc ){
                    this.robadas+=matrix.get(i).get(j);
                    this.copia.get(i).set(j,1);;
                    flagc=true;
                }
            }
        }
        for (ArrayList<Integer> array : this.copia){
            System.out.print(array);
        }
        System.out.println(this.robadas);
        ArrayList<Integer> pos1=null;
        ArrayList<Integer> pos2=null;

        for (int i=0;i<r-1;i++){
            for (int j = 0; j < c; j++) {
                if (this.copia.get(i+1).contains(this.copia.get(i).get(j)) && (mr.contains(this.copia.get(i).get(j))||mc.contains(this.copia.get(i).get(j)))){
                    pos1.add(i);
                    pos1.add(j);
                    pos2.add(i+1);
                    pos2.add(1);
                    break;
                }
                if (pos1 != null){
                    break;
                }
            }
        }
        if (pos1!=null){
            this.copia.get(pos2.get(0)).set(pos2.get(1),this.copia.get(pos2.get(0)).get(pos1.get(1)));
            this.copia.get(pos2.get(0)).set(pos1.get(1),this.copia.get(pos2.get(0)).get(pos2.get(1)));
            if (1<this.copia.get(pos2.get(0)).get(pos1.get(1)) && this.copia.get(pos2.get(0)).get(pos1.get(1))<=mr.get(pos2.get(0)) && this.copia.get(pos2.get(0)).get(pos1.get(1))<=mc.get(pos1.get(1))){
                this.robadas += this.copia.get(pos2.get(0)).get(pos1.get(1))-1;
                this.copia.get(pos2.get(0)).set(pos1.get(1),1);
            }
        }
        for (ArrayList<Integer> array :this.copia){
            System.out.println(array);
        }
        return this.robadas;
    }
}
