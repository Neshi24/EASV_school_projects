package com.example.tiktakmalicek;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class WelcomeScreenController {
    @FXML
    private TextField userNameField;

    @FXML
    private Label ipLabel;

    @FXML
    private void startGameButtonClick(ActionEvent event) throws Exception{
        String userName = userNameField.getText();
        if(userName.isBlank()){
            //user didnt provide username
            return;
        }
        //Node n = (Node) event.getSource();
        //Stage stage = (Stage) n.getScene().getWindow();

        Stage stage = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("GameWindow.fxml"));
        Parent root = loader.load();

        stage.setTitle(userName);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void startLocalServerButton(ActionEvent event) throws Exception {
        new SocketServer().start();
        ipLabel.setText("Localhost server started");
    }
}