import greenfoot.*;

/**
 * Write a description of class IntroWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class IntroWorld extends World
{
    private static final int WORLD_WIDTH = 400;
    private static final int WORLD_HEIGHT = 550;
    
    PlayButton pb;
    SettingsButton sb;
    GreenfootSound theme;
    
    /**
     * Constructor for objects of class IntroWorld.
     */
    public IntroWorld()
    {
        super(WORLD_WIDTH, WORLD_HEIGHT, 1); 
        GreenfootImage background = getBackground();
        GreenfootImage ping_background = new GreenfootImage("main_background.png");
        background.drawImage(ping_background, 0, 0);
        theme = new GreenfootSound("Ping_Pong_Music.wav");
        
        //ADDING BUTTONS
        pb = new PlayButton();
        addObject(pb, getWidth()/2, getHeight()/2);
        
        /*sb = new SettingsButton();
        addObject(sb, getWidth()/2, getHeight()/2+70);*/
    }
    
    public void act()
    {
        /*String key = Greenfoot.getKey();
        if (key != null && key.equals("enter"))
        {
            Greenfoot.setWorld(new PingWorld(true));
        }*/
        if(Greenfoot.mouseClicked(pb)){
            theme.stop();
        }
    }
    
    public void started(){
        if(!theme.isPlaying()){
            theme.playLoop();
        }
    }
    
    public void stopped(){
        if(theme.isPlaying()){
            theme.stop();
        }
    }
}
