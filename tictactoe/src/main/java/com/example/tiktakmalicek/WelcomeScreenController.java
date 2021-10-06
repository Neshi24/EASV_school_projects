package com.example.tiktakmalicek;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class WelcomeScreenController {
    @FXML
    private Button startGameButton;

    @FXML
    protected void startGameButtonClick(ActionEvent event) throws Exception{
        Node n = (Node) event.getSource();
        Stage stage = (Stage) n.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("GameWindow.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        stage.setScene(scene);
    }
}