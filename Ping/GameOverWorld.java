import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class GameOverWorld extends World
{
    private static final int WORLD_WIDTH = 400;
    private static final int WORLD_HEIGHT = 550;
    
    TryAgain ta;
    MainMenu mm;
    
    public GameOverWorld(int score)
    {    
        super(WORLD_WIDTH, WORLD_HEIGHT, 1); 
        addObject(new Label("Final Score "+ score, 40), 200, 140);
        
        ta = new TryAgain();
        addObject(ta, getWidth()/2, getHeight()/2);
        mm = new MainMenu();
        addObject(mm, getWidth()/2, getHeight()/2+35);
    }
    
    
}
