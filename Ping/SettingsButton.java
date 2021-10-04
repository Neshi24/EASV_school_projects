import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SettingsButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SettingsButton extends Actor
{
    /**
     * Act - do whatever the SettingsButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if(mouseOnObject(this)){
            setImage("settings_button_hover.png");
        } else {
            setImage("settings_button.png");
        }
        if(Greenfoot.mouseClicked(this)){
            //Greenfoot.setWorld(new PingWorld(true));
        }
    }

    private boolean mouseOnObject(Actor actor) {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        return mouse != null && mouse.getX() > actor.getX() - actor.getImage().getWidth()/2 && mouse.getX() < actor.getX() + actor.getImage().getWidth()/2 && 
        mouse.getY() > actor.getY() - actor.getImage().getHeight()/2 && mouse.getY() < actor.getY() + actor.getImage().getHeight()/2;
    }
}
