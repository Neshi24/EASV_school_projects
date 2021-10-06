package com.example.tiktakmalicek;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.*;


public class GameWindowController {

    @FXML
    private Label dataReceived;

    @FXML
    private TextField inputField;

    private GameWindowModel gm;

    public GameWindowController(){
        gm = new GameWindowModel();
    }

    @FXML
    protected void sendData(ActionEvent event){
        String send = inputField.getText();
        String resp = gm.connection().sendMessage(send);
        dataReceived.setText(resp);
    }
}
