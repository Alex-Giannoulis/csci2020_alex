 package Server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Server {
    protected Socket clientSocket           = null;
    protected ServerSocket serverSocket     = null;
    protected ClientHandler thread    = null;
    protected int numClients                = 0;
    protected Vector messages               = new Vector();

    public static int SERVER_PORT = 16789;
    public static int MAX_CLIENTS = 25;

    public ObjectOutputStream output;

    public Server() {
        try {
            serverSocket = new ServerSocket(SERVER_PORT);
            System.out.println("---------------------------");
            System.out.println("Chat Server Application is running");
            System.out.println("---------------------------");
            System.out.println("Listening to port: "+SERVER_PORT);
            clientSocket = serverSocket.accept();
            System.out.println("Client connected.");
            thread = new ClientHandler(clientSocket,messages);
            thread.start();

        } catch (IOException e) {
            System.err.println("IOException while creating server connection");
        }
    }

    public void close()
    {
        thread.closeProgram();
    }

    public static void main(String[] args) {
        Server app = new Server();
    }
}