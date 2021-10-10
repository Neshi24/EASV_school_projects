package com.example.tiktakmalicek;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

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

        context.setStroke(Color.GRAY);
        context.setLineWidth(2);
        for(int i = 0; i <= nRows; i++){ //hor
            context.strokeLine(0, i*blockSize, canvas.getWidth(), i*blockSize);
        }
        for(int i = 0; i <= nCols; i++){ //ver
            context.strokeLine(i*blockSize, 0, i*blockSize, canvas.getHeight());
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
        Move move = mousePointToMove(mX, mY);
        //System.out.println(move.toString());
        sc.sendData(move.toString());
    }

    public void validConnection(){
        if(sc.isDisconnected()){
            establishConnection();
        }
    }

    public Move mousePointToMove(double mX, double mY){
        int col = (int) mX / blockSize;
        int row = (int) mY / blockSize;
        //System.out.println(col + " " + row);
        return new Move(col, row, "x");
    }

}
