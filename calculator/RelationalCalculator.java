import java.util.Stack;

/** Calculator.java
 * @author ESCUELA 2021-1
 */
    
public class RelationalCalculator{

    private Stack<Table> tables;
    //Consultar en el API Java la clase Stack
    
    public RelationalCalculator(){
        this.tables = new Stack<Table>();
    }

    public void add(String [] attributes){
        Table tabla = new Table(attributes);
        this.tables.push(tabla);
    }
    
    public void add(String [] attributes, String[][] tuples){
        Table tabla = new Table(attributes);
        tabla.insert(tuples);
        this.tables.push(tabla);
    }
    
    /*Consult the top of the stack*/
    public String consult(){
        return this.tables.peek().toString();
    }
    
    public void delete(){
        this.tables.pop();
    }
    
 
    public void insert(String[][] tuples){
        Table top = this.tables.peek();
        top.insert(tuples);
    }  
    
    /*
    * set operation: 'u' (union), 'i' (intersection), 'd' (difference)
    * relational operation:  'p' (projection), 's' (selection), 'j' (natural join) , 'r' (rename)
    * To project and rename, the attributes are at the top of the stack.
    + To select, the attributes and values are at the top of the row.  
    * If the operation cannot be done, the stack is not modified.
    */
    public void calculate(char operator){
    }
    
    /*Indicates if the last action was successful*/
    public boolean ok(){
        return false;
    }
}
    



