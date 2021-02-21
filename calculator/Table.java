import java.util.Arrays;
import java.util.ArrayList;

/**
 * @author ECI, 2021-1
 *
 */
public class Table{


    private String[] attributes;
    private ArrayList<String[]> tuples;
    /*
     * The tables must remain 
     * (i) with the attribute names in uppercase 
     * (ii) without repeating tuples*

    /**
     * Constructs a new, empty table, with the specified attribute names.
     * @param names, 
    **/
    public Table(String attributes[]){
        for (int i=0; i<attributes.length;i++)
        {
            attributes[i]=attributes[i].toUpperCase();
        }
        this.attributes=attributes;
        this.tuples=new ArrayList<String[]>();
    }    
    
    
    /**
     * Inserts the specified tuples to this table 
     * @param tuples, 
    **/
    public void insert(String tuples[][]) {
        for (int i=0;i<tuples.length;i++)
        {
            this.tuples.add(tuples[i]);
        }
    }

    /**
     * Método size
     *
     * @return El valor de retorno
     */
    public int size(){
        return this.tuples.size();
    }
    
    public String[] attributes(){
        return this.attributes;
    }
    
    public String[] tuple(int n){
        return this.tuples.get(n);
    }
    
<<<<<<< HEAD
    /**
     * Verifies if the parameter is in the table
     *
     * @param tuple 
     * @return Boolean
     */
=======
    
>>>>>>> 3caa4aee9b97f9329ec2d55dde10755dffe0f1a8
    public boolean in(String tuple[]){
        boolean res=true;
        for (int i=0;i<this.tuples.size();i++)
          {
            res=true;
            boolean[] res2=new boolean[this.tuples.get(i).length];
            for(int j=0;j<this.tuples.get(i).length;j++)
            {
                if (tuple[j]==this.tuples.get(i)[j])
                {
                    res2[j]=true;
                }
                else
                {
                    res2[j]=false;
                }
            }
            
            for(int k=0;k<res2.length;k++)
            {
                if (res2[k]==false)
                {
                    res=false;
                } 
            }
            
            if (res){
                break;
            }
          }
        return res;
    }    

    /*
     * Relational operations: proyection, seleccion, natural join, rename
     */
    
    public Table proyection(String attributes[]){
        Table tabla = new Table(attributes);
        
        for (String a: attributes){
            for (int i=0; i<this.attributes.length; i++){
                if (a == this.attributes[i]){
                    this.analizeColumn(a);
                }
                
            }
        }
    }
    
    private String[]

    public Table selection(String attribute, String value){
        return null;
    }    
 
    public Table naturalJoin(Table t){
        return null;
    }

    
    public Table rename(String [] newAttributes){
        return null;
    }
    
    /*
     * Set operators
     * The two relations involved must be union-compatible—that is, the two relations must have the same set of attributes.
     */
    public Table union(Table t){
        return null;
    }

    public Table intersection(Table t){
       return null;
    }  
    
    public Table difference(Table t){
       return null;        
    }
    

    /**
     * Indicates whether some other table is "equal to" this one.
     * @param t the table with which to compare.
     */
    private boolean equals (Table t) {
        return false;
    }

 
    @Override
    public boolean equals (Object o) {
            return this.equals ((Table) o);
    }
 
    @Override
    public String toString () {
          String s = "";
          s += Arrays.toString(this.attributes)+"\n";
          for (int i=0;i<this.tuples.size();i++)
          {
            s+=Arrays.toString(this.tuples.get(i))+"\n";
          }
          s = s.replace('[', '(');
          s = s.replace(']', ')');
          s = s.replace(" ", "");
          return s;
    }

}
