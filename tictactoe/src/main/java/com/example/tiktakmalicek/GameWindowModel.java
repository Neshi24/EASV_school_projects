package com.example.tiktakmalicek;

public class GameWindowModel {

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
}
