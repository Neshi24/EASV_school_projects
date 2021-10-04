import greenfoot.*;
import java.util.*;

/**
 * A paddle is an object that goes back and forth. Though it would be nice if balls would bounce of it.
 * 
 * @author The teachers 
 * @version 1
 */
public class Random extends Paddle
{
    private int worldWidth;
    private int width_max;
    
    private boolean createdAnotherPaddle;
    private boolean justCreated;
    
    Random anotherPaddle;
    
    private GreenfootImage paddleImage;
    
    /**
     * Constructs a new paddle with the given dimensions.
     */
    public Random(int width, int height, int speed, int worldWidth, boolean justCreated, int width_max)
    {
        setWidth(width);
        setHeight(height);
        setSpeed(speed);
        
        this.worldWidth = worldWidth;
        this.width_max = width_max;
        
        createdAnotherPaddle = false;
        this.justCreated = justCreated;
        
        updateImage();
    }

    /**
     * Act - do whatever the Paddle wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(getSpeed() >= 1){
            movePaddleRight();
        } else {
            movePaddleLeft();
        }
    }    
    
    private void movePaddleRight(){
        //PERFORMANCE ISSUES WITH SCALE METHOD WHEN WE WANT A SMOOTH TRANSITION!!        
        double px = getX();
        double halfW = getWidth()/2;
        
        //check if is needed to create another paddle
        if(px + halfW >= this.worldWidth && !createdAnotherPaddle){
            anotherPaddle = new Random(4, 12, 1, worldWidth, true, Greenfoot.getRandomNumber(140-20)+20); //NOT IMPLEMENTED VARIOUS SIZES!
            //anotherPaddle.setImage("random_paddle.png");
            getWorld().addObject(anotherPaddle, 0, (int)Math.ceil(Greenfoot.getRandomNumber(210)+40));
            createdAnotherPaddle = true;
        }
            
        //check if paddle needs to get shrinked
        if(px >= this.worldWidth-1 && getWidth() > 3){
            setWidth(getWidth()/2);
            px -= getWidth()/2;
            paddleImage.scale(getWidth(), getHeight());
        }
        
        //check if paddle needs to get extended
        if(justCreated){
            if(px >= halfW && getWidth() < 100){
                setWidth(getWidth()*2);
                if(getWidth() > 100){
                    px += getWidth() - 100;
                    setWidth(100);
                    justCreated = false;
                }
                px -= getWidth()/2;
                
                paddleImage.scale(getWidth(), getHeight());
            }
        }
        
        //set location of paddle
        setLocation(px + getSpeed(), getY());
        
        //remove paddle if its too small
        if(getWidth() <= 3){
            getWorld().removeObject(this);
        }
    }

    private void movePaddleLeft(){
        
    }
    
    private void updateImage()
    {
        setImage("random_paddle.png");
        paddleImage = getImage();
    }
}
