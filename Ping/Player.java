import greenfoot.*;

public class Player extends Paddle
{
    private final int PLAYER_SPEED = 5;
    
    public Player(int width, int height)
    {
        setWidth(width);
        setHeight(height);
        setSpeed(PLAYER_SPEED);
        
        setImage("player_paddle.png");
    }

    public void act() 
    { 
        checkInput();
    }    
    
    private void checkInput(){
        if(Greenfoot.isKeyDown("left")){
            setLocation(limitX(getExactX()-getSpeed()), getY());
        } else if(Greenfoot.isKeyDown("right")){
            setLocation(limitX(getExactX()+getSpeed()), getY());
        }
    }
}
