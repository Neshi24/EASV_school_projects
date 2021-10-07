package com.example.tiktakmalicek;

public class GameWindowModel implements IGameModel {

    private SocketConnection sc;

    public GameWindowModel(){
        establishConnection();
    }

    public SocketConnection connection(){
        if(this.sc.isDisconnected()){
            establishConnection();
        }
        return this.sc;
    }

    private void establishConnection(){
        sc = new SocketConnection("127.0.0.1", 80);
    }

    @Override
    public int getNextPlayer() {
        return 0;
    }

    @Override
    public boolean play(int col, int row) {
        return false;
    }

    @Override
    public boolean isGameOver() {
        return false;
    }

    @Override
    public int getWinner() {
        return 0;
    }

    @Override
    public void newGame() {

    }
}
