import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class MainMenu extends Actor
{
    public int button;
    /**
     * Act - do whatever the PlayButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */

    public MainMenu(){

    }
    
    public void act()
    {
        if(mouseOnObject(this)){
            setImage("main_menu_button_hover.png");
        } else {
            setImage("main_menu_button.png");
        }
        if(Greenfoot.mouseClicked(this)){
            Greenfoot.setWorld(new IntroWorld());
        }
    }

    private boolean mouseOnObject(Actor actor) {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        return mouse != null && mouse.getX() > actor.getX() - actor.getImage().getWidth()/2 && mouse.getX() < actor.getX() + actor.getImage().getWidth()/2 && 
        mouse.getY() > actor.getY() - actor.getImage().getHeight()/2 && mouse.getY() < actor.getY() + actor.getImage().getHeight()/2;
}
}
