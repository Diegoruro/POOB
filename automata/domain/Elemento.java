package domain;
import java.awt.Color;

/**
* Funcionalidades de cualquier elemento
*/
public interface Elemento{
  int REDONDA = 1;
  int CUADRADA = 2;


  default void decida(Elemento[] vecinos){
  };
   
  default void cambie(){
  };
  
  default int forma(){
      return REDONDA;
  }
  
  default Color getColor(){
      return Color.black;
  };
  
  default boolean isVivo(){
      return false;
  }
  
  default int getVecinosVivos(){
      return 0;
  }
  
}
