public class Avion {
    private String placa;
    private boolean enAire;
    private Posicion ubicacion;
    private Marino piloto;
    private Marino copiloto;
    
    Avion(String placa, boolean enAire){
        this.placa = placa;
        this.enAire = enAire;
       }
       
    public void changeStatus(){
        if (this.enAire){
            this.enAire = false;
        }else{
            this.enAire = true;
        }
        
    }
       
    public String getPlaca(){
        return this.placa;
    }
    
    public boolean getEnAire(){
        return this.enAire;
    }
}
