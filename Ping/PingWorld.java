import greenfoot.*;

/**
 * The Ping World is where Balls and Paddles meet to play pong.
 * 
 * @author The teachers 
 * @version 1
 */
public class PingWorld extends World
{
    //World dimensions
    private static final int WORLD_WIDTH = 400;
    private static final int WORLD_HEIGHT = 550;

    Player playerPaddle;        //Player's paddle (move using arrow keys {left; right})
    AI aiPaddle;                //Paddle that's trying to act as another player
    Random randomPaddle;        //Paddle that's going left or right (over the world edges) 
    
    Ball ball;                  //The ball
    Flame flame;                //Ball's flame
    
    private Counter score;      //Score counter
    
    BallLife health0;           //health bar
    BallLife health1;           //health bar
    BallLife health2;           //health bar
    
    GreenfootSound theme;       //sound theme

    public PingWorld(boolean gameStarted)
    {
        super(WORLD_WIDTH, WORLD_HEIGHT, 1); 
        if (gameStarted)
        {
            setBackground("game_background.png");
            resetObjects();
            spawnObjectsIntoWorld();
            theme = new GreenfootSound("Game_Music.wav");
            theme.playLoop();
        }
        else
        {
            Greenfoot.setWorld(new IntroWorld());
        }
    }

    public void act(){
        score.setValue(ball.getScore());
    }

    public void resetObjects(){
        playerPaddle = new Player(100, 12);

        randomPaddle = new Random(100, 12, 1, WORLD_WIDTH, false, 100);

        aiPaddle = new AI(100, 12);

        ball = new Ball(playerPaddle, aiPaddle);
        flame = new Flame(ball);
        
        score = new Counter();
         
        health0 = new BallLife();
        health1 = new BallLife();
        health2 = new BallLife();
    }

    public void spawnObjectsIntoWorld(){
        //add player paddle to world
        addObject(playerPaddle, WORLD_WIDTH/2, WORLD_HEIGHT-50);

        //add random paddle to world
        addObject(randomPaddle, 300, WORLD_HEIGHT/2-150);

        //add ai paddle to world
        addObject(aiPaddle, WORLD_WIDTH/2, WORLD_HEIGHT/2-150);

        //add ball to world
        addObject(ball, WORLD_WIDTH/2, WORLD_HEIGHT/2);
        addObject(flame, ball.getX(), ball.getY());
        
        //add score object to world
        addObject(score, 200, 15);

        //adds lives to world
        addObject(health0, 30, 20);
        addObject(health1, 70, 20);
        addObject(health2, 110, 20);
    }

    public int getScore(){
        return score.getValue();
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
