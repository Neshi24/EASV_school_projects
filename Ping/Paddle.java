import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class Paddle extends SmoothMover
{
    private int width;
    private int height;    
    private int minX, maxX;
    private int speed;
    
    public Paddle(){
        this.minX = 50;
        this.maxX = 350;
    }
    
    public void act(){
        
    }
    
    //GETTERS
    
    //returns the speed of paddle
    public int getSpeed(){
        return this.speed;    
    }
    
    //returns the width of paddle
    public int getWidth(){
        return this.width;
    }
    
    //returns the height of paddle
    public int getHeight(){
        return this.height;
    }
    
    
    //SETTERS
    public void setSpeed(int setSpeed){
        this.speed = setSpeed;
    }
    
    public void setWidth(int setWidth){
        this.width = setWidth;
    }
    
    public void setHeight(int setHeight){
        this.height = setHeight;
    }
    
    public double limitX(double x){
       return (x < this.minX) ? this.minX : (x > this.maxX ? this.maxX : x);
    }
}
