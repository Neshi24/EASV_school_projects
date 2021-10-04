import greenfoot.*;
import java.lang.Math;
import java.util.*;

/**
 * A Ball is a thing that bounces of walls and paddles (or at least i should).
 * 
 * @author The teachers 
 * @version 1
 */
public class Ball extends SmoothMover
{
    Player playerPaddle;
    AI aiPaddle;
    Flame flame;

    GreenfootImage ballImage;

    //Ball variables
    private static final int BALL_DIAMETER = 16;
    private static final int BALL_RADIUS = BALL_DIAMETER/2;
    private static final int DELAY_TIME = 50;
    private static final int ADD_SCORE_AMOUNT = 10;

    private int speed = 5;
    private double xSpeed, ySpeed;
    private int delay;

    //Player score
    private int playerScore;
    private int reachScore = 50;
    private final int maxScore = 250;

    //Ball rotation
    private double ball_angle;
    private int rotationDegrees;

    //Player's lives
    int livesLeft;

    public Ball(Player playerPaddle, AI aiPaddle)
    {
        createImage();
        this.delay = DELAY_TIME;
        this.playerPaddle = playerPaddle;
        this.aiPaddle = aiPaddle;
        this.livesLeft = 3;
    }

    public void act() 
    {
        if(livesLeft > 0){
            if(this.flame == null){
                this.flame = getWorld().getObjects(Flame.class).get(0);
            }

            if (delay > 0)
            {
                delay--;
                reset();
            }
            else
            {   
                checkPlayerPaddleCollision();

                List<AI> aiPaddles = getWorld().getObjects(AI.class);
                for(AI aiPaddle : aiPaddles){
                    checkAiPaddleCollision();
                }

                List<Random> randomPaddles = getWorld().getObjects(Random.class);
                for(Random randomPaddle : randomPaddles){
                    checkRandomPaddleCollision(randomPaddle);
                }

                increaseSpeed();
                checkEdges();
                setLocation(getExactX()+xSpeed, getExactY()+ySpeed);
            }
        } else {
            getWorld().stopped();
            Greenfoot.setWorld(new GameOverWorld(playerScore));
        }

    }    

    private void reset()
    {      
        aiPaddle.setOnTurn(false);

        double angle = Math.toRadians(Greenfoot.getRandomNumber(180-40)+20);

        xSpeed = speed * Math.cos(angle);
        ySpeed = speed * Math.sin(angle);

        ball_angle = angle;

        setLocation(getWorld().getWidth()/2, getWorld().getHeight()/2);
    }

    private void checkPlayerPaddleCollision(){
        //EXACT REFERS TO MIDDLE OF THE OBJECT!!
        //This took me briefly 4 hours to figure out if someone wondered
        if(getY() + BALL_RADIUS > playerPaddle.getY() - playerPaddle.getHeight()/2){
            if(getX() + BALL_RADIUS > playerPaddle.getX() - playerPaddle.getWidth()/2){
                if(getX() - BALL_RADIUS < playerPaddle.getX() + playerPaddle.getWidth()/2){
                    if(getY() < playerPaddle.getY()){
                        double diff = getX() - (playerPaddle.getX() - playerPaddle.getWidth()/2);
                        double angle = mapAngle(diff, 0, playerPaddle.getWidth(), Math.toRadians(140), Math.toRadians(40));
                        xSpeed = speed * Math.cos(angle);
                        ySpeed = speed * Math.sin(angle);
                        //setLocation(getExactX(), playerPaddle.getExactY() - playerPaddle.getHeight() - BALL_RADIUS);
                        ySpeed *= -1;

                        //ADD socre
                        playerScore += ADD_SCORE_AMOUNT;

                        //set AI paddle to move
                        aiPaddle.setOnTurn(true);

                        ball_angle = -angle;

                        Greenfoot.playSound("Hit_sound.wav");
                    }
                }
            }
        }

    }

    private void checkAiPaddleCollision(){
        if(getY() - BALL_RADIUS < aiPaddle.getY() + aiPaddle.getHeight()/2 && ySpeed < 0){
            if(getX() + BALL_RADIUS > aiPaddle.getX() - aiPaddle.getWidth()/2){
                if(getX() - BALL_RADIUS < aiPaddle.getX() + aiPaddle.getWidth()/2){
                    if(getY() > aiPaddle.getY() && ball_angle < Math.PI){
                        double diff = getX() - (aiPaddle.getX() - aiPaddle.getWidth()/2);
                        double angle = mapAngle(diff, 0, aiPaddle.getWidth(), Math.toRadians(220), Math.toRadians(320));
                        xSpeed = speed * Math.cos(angle);
                        ySpeed = speed * Math.sin(angle);
                        //setLocation(getExactX(), playerPaddle.getExactY() - playerPaddle.getHeight() - BALL_RADIUS);
                        ySpeed *= -1;

                        //ADD socre
                        //playerScore += ADD_SCORE_AMOUNT;

                        //set AI paddle to move
                        aiPaddle.setOnTurn(false);

                        ball_angle = -angle;

                        Greenfoot.playSound("Hit_sound.wav");
                    }
                }
            }
        }
    }

    private void checkRandomPaddleCollision(Random randomPaddle){
        if(getY() - BALL_RADIUS < randomPaddle.getY() + randomPaddle.getHeight()/2 && ySpeed < 0){
            if(getX() + BALL_RADIUS > randomPaddle.getX() - randomPaddle.getWidth()/2){
                if(getX() - BALL_RADIUS < randomPaddle.getX() + randomPaddle.getWidth()/2){
                    if(getY() > randomPaddle.getY()){
                        double diff = getX() - (randomPaddle.getX() - randomPaddle.getWidth()/2);
                        double angle = mapAngle(diff, 0, randomPaddle.getWidth(), Math.toRadians(220), Math.toRadians(320));
                        xSpeed = speed * Math.cos(angle);
                        ySpeed = speed * Math.sin(angle);
                        //setLocation(getExactX(), playerPaddle.getExactY() - playerPaddle.getHeight() - BALL_RADIUS);
                        ySpeed *= -1;

                        //ADD socre
                        //playerScore += ADD_SCORE_AMOUNT;

                        //set AI paddle to move
                        aiPaddle.setOnTurn(false);

                        ball_angle = -angle;

                        Greenfoot.playSound("Hit_sound.wav");
                    }
                }
            }
        }
    }

    private void checkEdges(){
        //LEFT & RIGHT EDGES
        if(getExactX() < BALL_RADIUS || getExactX() > getWorld().getWidth()){
            xSpeed *= -1;
            ball_angle = -(ball_angle + Math.PI);
        }

        //BOTTOM EDGE
        if(getExactY() > getWorld().getHeight()){
            delay = DELAY_TIME;
            livesLeft--;
            getWorld().removeObject(getWorld().getObjects(BallLife.class).get(livesLeft));
            reset();
        }

        //TOP EDGE
        if(getExactY() < BALL_RADIUS){
            ySpeed *= -1;
            ball_angle = -ball_angle;
        }        
    }

    private void increaseSpeed(){
        if(playerScore >= reachScore){
            if(reachScore < maxScore){
                speed++;
                reachScore += 50;
                playerPaddle.setSpeed(speed);
                flame.makeBigger();
            }
        }
    }

    public int getScore(){
        return playerScore;
    }

    private void createImage()
    {
        ballImage = new GreenfootImage(BALL_DIAMETER, BALL_DIAMETER);
        ballImage.setColor(Color.RED);
        ballImage.fillOval(0, 0, BALL_DIAMETER, BALL_DIAMETER);
        setImage(ballImage);
    }

    private double mapAngle(double value, double istart, double istop, double ostart, double ostop) {
        return ostart + (ostop - ostart) * ((value - istart) / (istop - istart));
    }

    public double getXspeed(){
        return xSpeed;  
    }

    public double getYspeed(){
        return ySpeed;
    }

    public int getRadius(){
        return  BALL_DIAMETER;
    }

    public double getAngle(){
        return ball_angle;
    }

    public int getLivesLeft(){
        return livesLeft;
    }
}
