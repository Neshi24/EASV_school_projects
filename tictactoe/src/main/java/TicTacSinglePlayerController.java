/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.gui.controller;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.GridPane;
import tictactoe.bll.*;


/**
 *
 * @author Stegger
 */
public class TicTacSinglePlayerController implements Initializable
{

    boolean done = false;
    Button btn = new Button();
    private String turnMade;
    @FXML
    private Label lblPlayer;

    @FXML
    private Button btnNewGame;

    @FXML
    private GridPane gridPane;

    private static final String TXT_PLAYER = "Player: ";
    private GameBoard game;

    @FXML
    private void handleButtonAction(ActionEvent event)
    {
        try {
            if (!done){
                if (game.canDoNextTurn()) {
                    Integer row = GridPane.getRowIndex((Node) event.getSource());
                    Integer col = GridPane.getColumnIndex((Node) event.getSource());
                    int r = (row == null) ? 0 : row;
                    int c = (col == null) ? 0 : col;
                    int player = game.turnToPlayerNumber();

                        if (game.play(c, r)) {
                        Button btn = (Button) event.getSource();
                        //String xOrO = player == 1 ? "O" : "X";
                        btn.setText("O");
                        turnMade= btn.getId();
                        //if(btn.getId() == aiMove())



                        done = true;
                        if (game.isGameOver()) {
                            int winner = game.getWinner();
                            displayWinner(winner);
                        } else {
                            player = game.getNextPlayer();
                            setPlayer();

                        }

                        }
                        //aiMove();



                }
            }
            }
        catch(Exception e)
            {
                System.out.println(e.getMessage());
            }

    }

    @FXML
    private void handleNewGame(ActionEvent event)
    {
        game.newGame();
        setPlayer();
        clearBoard();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        game = new GameBoard();
        setPlayer();
    }

    private void setPlayer()
    {
        lblPlayer.setText(TXT_PLAYER + game.turnToPlayerNumber() + " is on turn");
    }

    private void displayWinner(int winner)
    {
        String message = "";
        switch (winner)
        {
            case -1:
                message = "It's a draw :-(";
                break;
            default:
                message = "Player " + winner + " wins!!!";
                break;
        }
        lblPlayer.setText(message);
    }

    private void clearBoard()
    {
        for(Node n : gridPane.getChildren())
        {
            Button btn = (Button) n;
            btn.setText("");
        }
    }
    private boolean checkSquare() {
        for (int[] i : game.squares) {
            for (int j : i) {
                if (j == 0) {
                return true;


                }
            }
        }
        return false;
    }

    public void aiMove()
    {
        if(done) {

                for (Node n : gridPane.getChildren()) {
                    Random ran = new Random();
                    String turn ="btn";
                    turn += ran.nextInt(9);
                    if(turn == turnMade){
                        turn += ran.nextInt(9);

                    }
                    else {
                        btn.setId(turn);

                        btn.setText("X");
                    }


                }



        }
        done = false;
    }

    public boolean checksq()
    {
    if(game.squares[0][0] == game.turnToPlayerNumber())
    {
        return false;
    }
    return true;
}



}
