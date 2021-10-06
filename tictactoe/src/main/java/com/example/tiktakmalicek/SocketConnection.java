package com.example.tiktakmalicek;


import java.io.*;
import java.net.*;
import java.util.Arrays;

public class SocketConnection {

    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public SocketConnection(String ip, int port){
        startConnection(ip, port);
    }

    private void startConnection(String ip, int port){
        try{
            clientSocket = new Socket(ip, port);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        } catch (Exception e){
            System.out.println(Arrays.toString(e.getStackTrace()));
            System.out.println("Couldn't connect!");
        }
    }

    public String sendMessage(String msg){
        try{
            out.println(msg);
            String resp = in.readLine();
            if("bye".equals(resp)){
                System.out.println("Server disconnected!");
                stopConnection();
            }
            return resp;
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            System.out.println("Couldn't send message: " + msg);
            return "";
        }
    }

    private void stopConnection(){
        try{
            in.close();
            out.close();
            clientSocket.close();
        } catch (Exception e){
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    public boolean isDisconnected(){
        return clientSocket.isClosed();
    }

    public boolean isConnected(){
        return clientSocket.isConnected();
    }
}
