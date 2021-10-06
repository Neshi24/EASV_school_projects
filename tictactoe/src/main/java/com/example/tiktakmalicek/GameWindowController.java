package com.example.tiktakmalicek;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class GameWindowController {

    @FXML
    Label dataReceived, connectionStatus;

    @FXML
    TextField inputField;

    GameWindowModel gm;

    public GameWindowController(){
        gm = new GameWindowModel();
        setupCanvas();
    }

    private void setupCanvas(){

    }

    @FXML
    protected void sendData(ActionEvent event){
        String dataString = inputField.getText();

        setupConnection();
    }

    private void setupConnection(){




        String receivedData = "";
        dataReceived.setText(receivedData);
    }
}
