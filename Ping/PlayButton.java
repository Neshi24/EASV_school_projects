import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PlayButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PlayButton extends Actor
{
    public int button;
    /**
     * Act - do whatever the PlayButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */

    public PlayButton(){

    }

    public void act()
    {
        if(mouseOnObject(this)){
            setImage("play_button_hover.png");
        } else {
            setImage("play_button.png");
        }
        if(Greenfoot.mouseClicked(this)){
            Greenfoot.setWorld(new InstructionsWorld());
        }
    }

    private boolean mouseOnObject(Actor actor) {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        return mouse != null && mouse.getX() > actor.getX() - actor.getImage().getWidth()/2 && mouse.getX() < actor.getX() + actor.getImage().getWidth()/2 && 
        mouse.getY() > actor.getY() - actor.getImage().getHeight()/2 && mouse.getY() < actor.getY() + actor.getImage().getHeight()/2;
    }
}
