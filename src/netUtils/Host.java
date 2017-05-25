package netUtils;

import concurrentUtils.ThreadPool;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by leon2 on 25.05.2017.
 */

public class Host {
    private int port;
    private static final Object lock = new Object ();
    private ThreadPool threadPool;
    private MessageHandler classMH;
    private boolean isAlive;

    public Host (int port, ThreadPool threadPool, MessageHandlerFactory classMHF){
        this.port = port;
        this.threadPool = threadPool;
        classMH = classMHF.create();
    }

    public void run(){
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            isAlive = true;
            int sessionID = -1;

            while (isAlive)
                try {
                    Socket socket = serverSocket.accept();

                    synchronized (lock) {
                        Session session = new Session(socket, ++sessionID, classMH);
                        threadPool.execute(session);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void stop () {
        isAlive = false;
    }
}
