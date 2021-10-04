import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class TryAgain extends Actor
{
    public int button;

    public TryAgain(){

    }

    public void act()
    {
        if(mouseOnObject(this)){
            setImage("try_again_button_hover.png");
        } else {
            setImage("try_again_button.png");
        }
        if(Greenfoot.mouseClicked(this)){
            Greenfoot.setWorld(new PingWorld(true));
        }
    }

    private boolean mouseOnObject(Actor actor) {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        return mouse != null && mouse.getX() > actor.getX() - actor.getImage().getWidth()/2 && mouse.getX() < actor.getX() + actor.getImage().getWidth()/2 && 
        mouse.getY() > actor.getY() - actor.getImage().getHeight()/2 && mouse.getY() < actor.getY() + actor.getImage().getHeight()/2;
    }
}
