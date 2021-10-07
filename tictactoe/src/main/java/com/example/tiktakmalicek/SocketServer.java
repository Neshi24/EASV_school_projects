package com.example.tiktakmalicek;

import java.net.*;
import java.io.*;
import java.util.*;

public class SocketServer extends Thread{

    private ServerSocket serverSocket;
    private List<ClientHandler> handlers = new ArrayList<>();
    private final int port = 80;

    public void run() {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server listening on port :" + port);
            while (true){
                ClientHandler clientHandler = new ClientHandler(serverSocket.accept());
                this.handlers.add(clientHandler);
                clientHandler.start();
                System.out.println("Incoming connection: ");
            }
        } catch (Exception e){
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    public void stopServer(){
        try{
            serverSocket.close();
            System.out.println("Server stop");
        } catch (Exception e){
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }


    private static class ClientHandler extends Thread {
        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;

        public ClientHandler(Socket client){
            this.clientSocket = client;
        }

        public void run() {
            try{
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                String inputLine;
                while((inputLine = in.readLine()) != null){
                    System.out.println("\t" + inputLine);
                    if(".".equals(inputLine)){
                        out.println("bye");
                        System.out.println("Client disconnected: " + clientSocket.getRemoteSocketAddress());
                        break;
                    }
                    //out.println(inputLine);
                }

                in.close();
                out.close();
                clientSocket.close();
            } catch (Exception e){
                System.out.println(Arrays.toString(e.getStackTrace()));
            }

        }
    }
}

