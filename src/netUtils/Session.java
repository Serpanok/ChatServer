package netUtils;

import app.Request;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

/**
 * Created by leon2 on 25.05.2017.
 */

public class Session implements Runnable {
    private final Socket socket;
    private final int id;
    private MessageHandler classMH;

    public Session(Socket socket, int id, MessageHandler classMH) {
        this.socket = socket;
        this.id = id;
        this.classMH = classMH;
    }

    @Override
    public void run() {
        try {
            //DataInputStream inputStream = new DataInputStream(this.socket.getInputStream());
            //DataOutputStream outputStream = new DataOutputStream(this.socket.getOutputStream());

            ObjectInputStream inputStream = new ObjectInputStream(this.socket.getInputStream());
            ObjectOutputStream outputStream = new ObjectOutputStream(this.socket.getOutputStream());

            while (true)
            {
                Request request = (Request) inputStream.readObject();
                classMH.handle( request, outputStream);
                //outputStream.writeUTF("Done");
                //outputStream.flush();
            }
        } catch (IOException e) {
            System.out.println("client disconnect!");
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}
