package domain;
import java.awt.Color;

/*No olviden adicionar la documentacion*/
public interface Elemento{
  int REDONDA = 1;
  int CUADRADA = 2;


  default void decida(){
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
  
}
