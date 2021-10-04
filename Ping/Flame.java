import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class flame here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Flame extends SmoothMover
{
    private int width = 30;
    private int height = 30;
    
    GifImage gifImage = new GifImage("flame_gif.gif");
    Ball ball;
    
    public Flame(Ball ball){
        this.ball = ball;
    }
    
    public void act()
    {
        GreenfootImage img = gifImage.getCurrentImage();
        img.scale(this.width, this.height);
        
        double rotation = Math.toDegrees(ball.getAngle());
        
        setRotation((int)rotation);
        
        double x = ball.getX()-ball.getRadius()*Math.cos(ball.getAngle());
        double y = ball.getY()-ball.getRadius()*Math.sin(ball.getAngle());
        
        setLocation(x, y);
        setImage(img);
    }
    
    public void makeBigger(){
        width += 3;
        height += 3;
    }
}
