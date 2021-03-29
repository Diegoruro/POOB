import java.util.ArrayList;

public class Tablero {
     public ArrayList<Flota> flotas;
     
     public Tablero(){
         this.flotas = new ArrayList<Flota>();
     }
     
     /**
      * Añade una flota al tablero
      *
      * @param Nombre de la flota
      */
     public void addFlota(String nombre){
         flotas.add(new Flota(nombre, this));
     }
     
     /**
      * Calcula la potencia del tablero
      *
      * @return Valor de la potencia
      */
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
