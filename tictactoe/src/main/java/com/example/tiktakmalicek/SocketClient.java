package com.example.tiktakmalicek;

import java.io.*;
import java.net.*;
import java.util.*;

public class SocketClient extends Thread{

    private Socket clientSocket;
    private String ip;
    private int port;
    private DataOutputStream out;
    private DataInputStream in;
    private List<Move> moves;

    public SocketClient(String ip, int port){
        this.ip = ip;
        this.port = port;
        this.moves = new ArrayList<>();
    }

    public void run(){
        try{
            clientSocket = new Socket(this.ip, this.port);
            out = new DataOutputStream(clientSocket.getOutputStream());
            in = new DataInputStream(clientSocket.getInputStream());
            new ServerListener(in, clientSocket, this).start();
            sendData("player");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void sendData(String data){
        try{
            out.writeUTF(data);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public synchronized List<Move> getMoves(){
        return this.moves;
    }

    public synchronized void setMoves(List<Move> moves){
        this.moves = moves;
    }

    public boolean isDisconnected(){
        return clientSocket.isClosed();
    }

    private static class ServerListener extends Thread{

        private DataInputStream in;
        private Socket socket;
        private SocketClient client;

        public ServerListener(DataInputStream in, Socket socket, SocketClient client){
            this.in = in;
            this.socket = socket;
            this.client = client;
        }

        public void run(){
            try{
                String received;
                while((received = in.readUTF()) != null ){
                    //System.out.println(received);
                    client.setMoves(Move.solveMoves(received));
                    System.out.println(client.getMoves().toString());
                }
            } catch (IOException e){
                stopConnection();
            }
        }

        private void stopConnection(){
            try{
                in.close();
                socket.close();
                System.out.println("Connection ended");
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
