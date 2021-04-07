package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Vector;

public class ClientHandler extends Thread
{
    protected Socket socket       = null;
    protected PrintWriter out     = null;
    protected BufferedReader in   = null;

    protected String Password = "Password Protected";

    protected Vector text     = null;

    public ClientHandler(Socket socket, Vector text) {
        super();
        this.socket = socket;
        this.text = text;
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            System.err.println("IOEXception Error caused while opening a read/write connection");
        }
    }


    public void closeProgram()
    {
        try {
            socket.close();
            out.close();
            in.close();
            System.exit(0);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {

        boolean endOfSession = false;
        while(!endOfSession) {
            endOfSession = CommandHandler();
        }
        closeProgram();
    }

    protected boolean CommandHandler()
    {
        try
        {
            String texts;

            texts = in.readLine();
            if(texts.equals("Exit"))
            {
                return true;
            }
            MainServer.board.appendText(texts + "\n");
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        return false;
    }

}