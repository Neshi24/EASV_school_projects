package com.example.tiktakmalicek;


import java.net.*;
import java.io.*;
import java.util.*;

public class SocketServer extends Thread{

    private ServerSocket serverSocket;
    private final int PORT = 8080;
    private List<Move> moves;
    private List<DataOutputStream> clients;

    public SocketServer(){
        moves = new ArrayList<>();
        clients = new ArrayList<>();
    }

    public void run() {
        try {
            serverSocket = new ServerSocket(PORT, 50);
            System.out.println("Server listening on port :" + PORT);
            while (true){
                Socket socket = serverSocket.accept();
                System.out.println("Incoming connection");

                DataOutputStream remoteOut = new DataOutputStream(socket.getOutputStream());
                DataInputStream remoteIn = new DataInputStream(socket.getInputStream());
                String user = remoteIn.readUTF();
                clients.add(remoteOut);
                new ClientHandler(socket, remoteOut, user, this).start();
            }
            //stopServer();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void stopServer(){
        try{
            serverSocket.close();
            System.out.println("Server stop");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    synchronized void addMove(Move move){
        this.moves.add(move);
    }

    synchronized List getMoves(){
        return this.moves;
    }

    synchronized List getClients() { return this.clients; }

    synchronized void removeFromClients(DataOutputStream remoteOut) { clients.remove(remoteOut); }

    private static class ClientHandler extends Thread {
        private Socket clientSocket;
        private DataOutputStream remoteOut;
        private SocketServer server;
        private DataInputStream remoteIn;
        private String user;

        public ClientHandler(Socket client, DataOutputStream remoteOut, String user, SocketServer server) throws IOException {
            this.clientSocket = client;
            this.remoteOut = remoteOut;
            this.server = server;
            this.user = user;
            remoteIn = new DataInputStream(clientSocket.getInputStream());
            broadcast("User " + user + " connected");
        }

        public void run(){
            String s;
            broadcast(Move.solveMoves(server.getMoves()));
            try{
                while(true){
                    s = remoteIn.readUTF();
                    server.addMove(Move.stringToMove(s));
                    broadcast(Move.solveMoves(server.getMoves()));
                }
            } catch (IOException e){
                broadcast("User " + user + " disconnected");
                server.removeFromClients(remoteOut);
            } finally {
                try{
                    cleanUp();
                } catch (IOException x){

                }
            }
        }

        private void broadcast(String s){
            List clients = server.getClients();
            DataOutputStream dataOut = null;

            for(Iterator i = clients.iterator(); i.hasNext(); ){
                dataOut = (DataOutputStream) (i.next());
                try{
                    dataOut.writeUTF(s);
                } catch (IOException x){
                    System.out.println(x.getMessage() + ": Failed to broadcast to client.");
                    server.removeFromClients(dataOut);
                }
            }
        }

        private void cleanUp() throws IOException {
            if(remoteOut != null){
                server.removeFromClients(remoteOut);
                remoteOut.close();
            }
            if(remoteIn != null){
                remoteIn.close();
            }
            if(clientSocket != null){
                clientSocket.close();
            }
        }
    }
}