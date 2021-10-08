package com.example.tiktakmalicek;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class GameWindowModel implements IGameModel {

    private SocketConnection sc;
    private GraphicsContext context;
    private Canvas canvas;
    private final int blockSize = 100;
    private final int nRows = 3;
    private final int nCols = 3;

    public GameWindowModel(){
        establishConnection();
    }

    private void startGame(){
        context.setFill(Color.LIGHTGRAY);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        context.setStroke(Color.GRAY);
        context.setLineWidth(2);
        for(int i = 1; i < nRows; i++){ //hor
            context.strokeLine(0, i*blockSize, canvas.getWidth(), i*blockSize);
        }
        for(int i = 1; i < nCols; i++){ //ver
            context.strokeLine(i*blockSize, 0, i*blockSize, canvas.getHeight());
        }
    }

    public void setCanvas(Canvas canvas){
        this.canvas = canvas;
        this.context = canvas.getGraphicsContext2D();
        startGame();
    }

    public SocketConnection connection(){
        if(this.sc.isDisconnected()){
            establishConnection();
        }
        return this.sc;
    }

    private void establishConnection(){
        sc = new SocketConnection("127.0.0.1", 80);
    }

    @Override
    public int getNextPlayer() {
        return 0;
    }

    @Override
    public boolean play(int col, int row) {
        return false;
    }

    @Override
    public boolean isGameOver() {
        return false;
    }

    @Override
    public int getWinner() {
        return 0;
    }

    @Override
    public void newGame() {

    }
}
