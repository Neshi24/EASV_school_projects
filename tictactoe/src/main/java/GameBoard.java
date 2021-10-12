/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Stegger
 */

public class GameBoard implements IGameModel
{

    private boolean turn;
    private int[][] squares;
    private final int player1id = 1;
    private final int player2id = 2;
    private boolean canDoNextTurn;

    public GameBoard(){
        newGame();
    }

    /**
     * Returns 0 for player 0, 1 for player 1.
     *
     * @return int Id of the next player.
     */
    public int getNextPlayer()
    {
        turn = !turn;
        return turnToPlayerNumber();
    }

    /**
     * Attempts to let the current player play at the given coordinates. It the
     * attempt is succesfull the current player has ended his turn and it is the
     * next players turn.
     *
     * @param col column to place a marker in.
     * @param row row to place a marker in.
     * @return true if the move is accepted, otherwise false. If gameOver == true
     * this method will always return false.
     */
    public boolean play(int col, int row)
    {
        //0 means the square is not used
        //squares are identified with players' id
        if(squares[row][col] == 0){
            squares[row][col] = turnToPlayerNumber();
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @return true if player has won or there are no squares left
     */
    public boolean isGameOver()
    {
        //[x,y] [r,c]

        //ver
        if(squares[0][0] == turnToPlayerNumber() && squares[0][1] == turnToPlayerNumber() && squares[0][2] == turnToPlayerNumber()){
            return true;
        }
        if (squares[1][0] == turnToPlayerNumber() && squares[1][1] == turnToPlayerNumber() && squares[1][2] == turnToPlayerNumber()){
            return true;
        }
        if (squares[2][0] == turnToPlayerNumber() && squares[2][1] == turnToPlayerNumber() && squares[2][2] == turnToPlayerNumber()){
            return true;
        }

        //hor
        if(squares[0][0] == turnToPlayerNumber() && squares[1][0] == turnToPlayerNumber() && squares[2][0] == turnToPlayerNumber()){
            return true;
        }
        if (squares[0][1] == turnToPlayerNumber() && squares[1][1] == turnToPlayerNumber() && squares[2][1] == turnToPlayerNumber()){
            return true;
        }
        if (squares[0][2] == turnToPlayerNumber() && squares[1][2] == turnToPlayerNumber() && squares[2][2] == turnToPlayerNumber()){
            return true;
        }

        //diag
        if(squares[0][0] == turnToPlayerNumber() && squares[1][1] == turnToPlayerNumber() && squares[2][2] == turnToPlayerNumber()){
            return true;
        }
        if(squares[0][2] == turnToPlayerNumber() && squares[1][1] == turnToPlayerNumber() && squares[2][0] == turnToPlayerNumber()){
            return true;
        }

        //check if there's any square left to be clicked
        if(!checkAvailableSquare()){
            return true;
        }

        //otherwise return false
        return false;
    }

    /**
     * Gets the id of the winner, -1 if its a draw.
     *
     * @return int id of winner, or -1 if draw.
     */
    public int getWinner()
    {
        //check if there's any square left to be clicked
        //if not, it's a draw
        //otherwise return player's id that won the game
        canDoNextTurn = false;
        if(!checkAvailableSquare()){
            return -1;
        }
        return turnToPlayerNumber();
    }

    /**
     * Resets the game to a new game state.
     */
    public void newGame()
    {
        squares = new int[3][3];
        turn = true;
        canDoNextTurn = true;
    }


    /**
     *
     * @return can a player do next turn?
     */
    public boolean canDoNextTurn(){
        return canDoNextTurn;
    }

    /**
     *
     * @return player's id based on current turn
     */
    public int turnToPlayerNumber(){
        return (turn ? player1id : player2id);
    }

    /**
     *
     * @return true if there's a square a player can click on / false if none
     */
    private boolean checkAvailableSquare(){
        for (int[] i : squares) {
            for (int j : i) {
                if(j == 0){
                    return true;
                }
            }
        }
        return false;
    }
}
