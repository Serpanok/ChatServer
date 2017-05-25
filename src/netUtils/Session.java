package netUtils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

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
            DataInputStream inputStream = new DataInputStream(this.socket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(this.socket.getOutputStream());
            String inMsg;

            while (true) {
                inMsg = inputStream.readUTF();
                classMH.handle("Client #" + this.id + ": " + inMsg);
                outputStream.writeUTF("Done");
                outputStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
