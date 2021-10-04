import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class InstructionsWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class InstructionsWorld extends World
{
    private static final int WORLD_WIDTH = 400;
    private static final int WORLD_HEIGHT = 550;

    public InstructionsWorld()
    {    
        super(WORLD_WIDTH, WORLD_HEIGHT, 1); 
    }

    public void act()
    {
        if(Greenfoot.isKeyDown("enter")){
            Greenfoot.setWorld(new PingWorld(true));
        }
    }
}
