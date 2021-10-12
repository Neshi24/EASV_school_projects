/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Stegger
 */
public class TicTacViewController implements Initializable
{

    @FXML
    private AnchorPane anchorPaneGames;

    @FXML
    private BorderPane borderGame1, borderGame2 ,borderGame3 ,borderGame4 ,borderGame5 , borderGame6;

    private static final String TXT_PLAYER = "Player: ";

    private List<BorderPane> gameBoards;
    private List<GameBoard> games;

    @FXML
    private void handleButtonAction(ActionEvent event)
    {
        try
        {
            if(game.canDoNextTurn()){
                Integer row = GridPane.getRowIndex((Node) event.getSource());
                Integer col = GridPane.getColumnIndex((Node) event.getSource());
                int r = (row == null) ? 0 : row;
                int c = (col == null) ? 0 : col;
                int player = game.getNextPlayer();
                if (game.play(c, r))
                {
                    Button btn = (Button) event.getSource();
                    String xOrO = player == 1 ? "O" : "X";
                    btn.setText(xOrO);
                    setPlayer();
                    if (game.isGameOver())
                    {
                        int winner = game.getWinner();
                        displayWinner(winner);
                    }
                }
            }
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    private void handleNewGame(GameBoard game)
    {
        game.newGame();
        setPlayer(game);
        clearBoard();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        for (Node bps : anchorPaneGames.getChildren()) {
            if(bps instanceof BorderPane){
                BorderPane bp = (BorderPane) bps;
                gameBoards.add(bp);

                Label playerLabel = new Label();
                Button newGameButton = new Button();
                GridPane squaresGrid = new GridPane();

                for(Node node : bp.getChildren()){
                    if(node instanceof Label){
                        playerLabel = (Label) node;
                    }
                    else if(node instanceof Button){
                        newGameButton = (Button) node;
                    }
                    else if(node instanceof GridPane){
                        squaresGrid = (GridPane) node;
                    }
                }

                GameBoard game = new GameBoard(playerLabel, newGameButton, squaresGrid);
                games.add(game);
            }
        }

    }

    public static void setPlayer(Label label, GameBoard game)
    {
        label.setText(TXT_PLAYER + game.turnToPlayerNumber() + " is on turn");
    }

    public static void displayWinner(Label label, int winner)
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
        label.setText(message);
    }

    public static void clearBoard(GridPane gridPane)
    {
        for(Node n : gridPane.getChildren())
        {
            Button btn = (Button) n;
            btn.setText("");
        }
    }

}
