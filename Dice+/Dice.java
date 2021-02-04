import java.util.Random;
import java.awt.*;

/**
 * Primera clase POOB.
 * 
 * @author (Juan Felipe Aguas Pulido - Diego Fernando Ruiz Rojas) 
 */
public class Dice
{
    private int value;
    private boolean isVisible;
    
    private static final int size = 150;
    private int xPosition;
    private int yPosition;
    private String color;
    private Rectangle sq;
    
    private Circle[] points = new Circle[6];
    
    

    /**
     * Constructor for objects of class Dice
     */
    public Dice()
    {
        this.xPosition = 75;
        this.yPosition = 75;
        this.isVisible = false;
        
    }


    public int getValue()
    {
        return this.value;
    }
    

    public void moveHorizontal(int distance)
    {
       delete(sq,points);
       xPosition += distance;
       draw();
    }
    

    public void roll()
    {
       Random n = new Random();
       this.value = n.nextInt(6) + 1;
       delete(sq,points);
    }
    
    public void makeVisible()
    {
           isVisible = true;
           draw();
    }
    

    public void makeInvisible()
    {
           delete(sq,points);
           isVisible = false;
    }
    
    

    private void draw()
    {
        if(isVisible) {
            switch(this.value){
                case 1:
                    sq = new Rectangle(xPosition, yPosition, size, size);
                    sq.makeVisible();
                    points[0] = new Circle(xPosition + 60,yPosition + 60,30);
                    points[0].makeVisible();
                    break;
                case 2:
                    sq = new Rectangle(xPosition, yPosition, size, size);
                    sq.makeVisible();
                    points[0] = new Circle(xPosition + 60,yPosition + 30,30);
                    points[0].makeVisible();
                    points[1] = new Circle(xPosition + 60,yPosition + 90,30);
                    points[1].makeVisible();
                    break;
                case 3:
                    sq = new Rectangle(xPosition, yPosition, size, size);
                    sq.makeVisible();
                    points[0] = new Circle(xPosition + 15,yPosition + 15,30);
                    points[0].makeVisible();
                    points[1] = new Circle(xPosition + 60,yPosition + 60,30);
                    points[1].makeVisible();
                    points[2] = new Circle(xPosition + 105,yPosition + 105,30);
                    points[2].makeVisible();
                    break;
                case 4:
                    sq = new Rectangle(xPosition, yPosition, size, size);
                    sq.makeVisible();
                    points[0] = new Circle(xPosition + 30,yPosition + 90,30);
                    points[0].makeVisible();
                    points[1] = new Circle(xPosition + 90,yPosition + 30,30);
                    points[1].makeVisible();
                    points[2] = new Circle(xPosition + 90,yPosition + 90,30);
                    points[2].makeVisible();
                    points[3] = new Circle(xPosition + 30,yPosition + 30,30);
                    points[3].makeVisible();
                    break;
                case 5:
                    sq = new Rectangle(xPosition, yPosition, size, size);
                    sq.makeVisible();
                    points[0] = new Circle(xPosition + 30,yPosition + 90,30);
                    points[0].makeVisible();
                    points[1] = new Circle(xPosition + 90,yPosition + 30,30);
                    points[1].makeVisible();
                    points[2] = new Circle(xPosition + 90,yPosition + 90,30);
                    points[2].makeVisible();
                    points[3] = new Circle(xPosition + 30,yPosition + 30,30);
                    points[3].makeVisible();
                    points[4] = new Circle(xPosition + 60,yPosition + 60,30);
                    points[4].makeVisible();
                    break;
                case 6:
                    sq = new Rectangle(xPosition, yPosition, size, size);
                    sq.makeVisible();
                    points[0] = new Circle(xPosition + 30,yPosition + 15,30);
                    points[0].makeVisible();
                    points[1] = new Circle(xPosition + 30,yPosition + 60,30);
                    points[1].makeVisible();
                    points[2] = new Circle(xPosition + 30,yPosition + 105,30);
                    points[2].makeVisible();
                    points[3] = new Circle(xPosition + 90,yPosition + 15,30);
                    points[3].makeVisible();
                    points[4] = new Circle(xPosition + 90,yPosition + 60,30);
                    points[4].makeVisible();
                    points[5] = new Circle(xPosition + 90,yPosition + 105,30);
                    points[5].makeVisible();
                    break;
                default:
                    System.out.println("Plase roll the dice");
                    break;
                    
            }
        }
    }
    
    
    private void delete(Rectangle cuadro,Circle[] points)
    {
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(cuadro);
            for (int i=0; i<6;i++)
            {
                canvas.erase(points[i]);
            }
        }
    }
}
