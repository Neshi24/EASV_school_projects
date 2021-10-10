package com.example.tiktakmalicek;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseButton;


import java.net.*;
import java.util.*;


public class GameWindowController implements Initializable{

    @FXML
    private Canvas gameCanvas;

    private GameWindowModel gm;

    public GameWindowController(){
        gm = new GameWindowModel();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        gm.setCanvas(gameCanvas);
        gameCanvas.setOnMouseClicked(m -> {
            if(m.getButton() == MouseButton.PRIMARY){
                try {
                    gm.canvasClicked(m);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
