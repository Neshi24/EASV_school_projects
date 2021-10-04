import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;  // List, ArrayList

/**
 * Write a description of class AIPaddle here.
 * @version a0.1: chasing the ball on turn
 * 
 * @author Roman Masár
 * @version a0.1
 */

public class AI extends Paddle
{    
    private final int AI_SPEED = 3;
    
    private boolean isOnTurn;
    
    public AI(int width, int height){
        setWidth(width);
        setHeight(height);
        setSpeed(AI_SPEED);
        setOnTurn(true);
        setImage("ai_paddle.png");
    }
    
    public void act()
    {
        if(isOnTurn){
            //chase (*predict*) ball
            Ball ball = getWorld().getObjects(Ball.class).get(0);
            if(ball != null){
                if(isMoreToLeft(ball)){
                    super.setLocation(limitX(getX()+getSpeed()), getY());
                } else {
                    setLocation(limitX(getX()-getSpeed()), getY());
                }
            }
        } else {
            //move towards center
            if(isMoreToLeft()){
                setLocation(limitX(getX()+getSpeed()), getY());
            } else {
                setLocation(limitX(getX()-getSpeed()), getY());
            }
            
        }
    }
    
    public void setOnTurn(boolean turn){
        this.isOnTurn = turn;
    }
    
    private boolean isMoreToLeft(){
        return (getExactX() < getWorld().getWidth()/2);
    }    
    
    private boolean isMoreToLeft(Ball ball){
        return (getExactX() < ball.getExactX());
    }
}
