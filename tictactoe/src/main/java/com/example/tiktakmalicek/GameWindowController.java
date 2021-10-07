package com.example.tiktakmalicek;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.*;
import java.util.ResourceBundle;


public class GameWindowController {

    @FXML
    private Label dataReceived;

    @FXML
    private TextField inputField;

    private GameWindowModel gm;
    private String id;

    public GameWindowController(){
        gm = new GameWindowModel();
    }

    @FXML
    protected void sendData(ActionEvent event){
        String send = inputField.getText();
        String resp = gm.connection().sendMessage(send, this.id);
        dataReceived.setText(resp);
    }

    public void setId(String id){
        this.id = id;

    }
}
