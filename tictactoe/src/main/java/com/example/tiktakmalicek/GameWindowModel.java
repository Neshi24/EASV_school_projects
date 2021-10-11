package com.example.tiktakmalicek;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class GameWindowModel {

    private SocketClient sc;
    private GraphicsContext context;
    private Canvas canvas;
    private static final int blockSize = 50;
    private int nRows;
    private int nCols;
    private Move[] moves;

    public GameWindowModel(){
        establishConnection();
    }

    private void drawCanvas(){
        nRows = (int)canvas.getHeight() / blockSize;
        nCols = (int)canvas.getWidth() / blockSize;
        context.setFill(Color.LIGHTGRAY);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());


        for(int i = 0; i <= nRows; i++){ //hor
            context.setStroke(Color.RED);
            context.setLineWidth(2);
            context.strokeLine(0, i*blockSize, canvas.getWidth(), i*blockSize);
            if (i%3 == 0 ){
                context.setLineWidth(5);
                context.strokeLine(0, i*blockSize, canvas.getWidth(), i*blockSize);
            }

        }
        for(int i = 0; i <= nCols  ; i++){ //ver
            context.setLineWidth(1);
            context.strokeLine(i*blockSize, 0, i*blockSize, canvas.getHeight());
            if (i%3 == 0 ){
                context.setLineWidth(5);
                context.strokeLine(i*blockSize, 0, i*blockSize, canvas.getHeight());
            }
        }
    }

    private void establishConnection(){
        sc = new SocketClient("127.0.0.1", 8080);
        sc.start();
        System.out.println("client connected to server");
    }

    public void setCanvas(Canvas canvas){
        this.canvas = canvas;
        this.context = canvas.getGraphicsContext2D();
        drawCanvas();
    }


    public void canvasClicked(MouseEvent event) throws Exception{
        //user clicked on canvas
        double mX = event.getSceneX();
        double mY = event.getSceneY();
        Move move = Move.mousePointToMove(mX, mY, blockSize, "X");
        //System.out.println(move.toString());
        moveToSign(move);
        sc.sendData(move.toString());
    }

    public void validConnection(){
        if(sc.isDisconnected()){
            establishConnection();
        }
    }
    public void moveToSign(Move move){
        int cX = (move.getRow() * blockSize + 10);
        int cY = (move.getCol() * blockSize + 40);
        context.setFill(Color.BLUE);
        context.setFont(Font.font("Arial", 42.4));
        context.fillText(move.getSign(), cX, cY);
    }
}
