import java.util.ArrayList;

public class Tablero {
     public ArrayList<Flota> flotas;
     
     public Tablero(){
         this.flotas = new ArrayList<Flota>();
     }
     
     public void addFlota(String nombre){
         flotas.add(new Flota(nombre, this));
     }
     
     public int potencia() throws BatallaNavalException{
         int potencia = 0;
         int problemas = 0;
         
         for (Flota flota: flotas){
             try{
                 potencia += flota.potencia();
                }
             catch(BatallaNavalException e){
                 problemas++;
                }
             if ((flotas.size()/2) < problemas){
                 throw new BatallaNavalException(BatallaNavalException.POTENCIATABLERO);
             }
         }
         
         return potencia;
     }
}
