import java.net.*;
import java.io.*;
import java.util.Arrays;

public class WebSocketServer {

    private ServerSocket serverSocket;

    public void start(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server listening on port :" + port);
            while (true){
                new ClientHandler(serverSocket.accept()).start();
                System.out.println("Incoming connection: ");
            }
        } catch (Exception e){
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    public void stop(){
        try{
            serverSocket.close();
        } catch (Exception e){
            System.out.println(Arrays.toString(e.getStackTrace()));
        }

    }


    private static class ClientHandler extends Thread {
        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;

        public ClientHandler(Socket socket){
            this.clientSocket = socket;
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
                    out.println(inputLine);
                }

                in.close();
                out.close();
                clientSocket.close();
            } catch (Exception e){
                System.out.println(Arrays.toString(e.getStackTrace()));
            }

        }
    }


    public static void main(String[] args){
        WebSocketServer server = new WebSocketServer();
        try{
            server.start(80);
        } catch (Exception e){
            System.out.println(Arrays.toString(e.getStackTrace()));
        }

    }
}

