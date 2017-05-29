package app;

import concurrentUtils.Channel;
import concurrentUtils.Dispatcher;
import concurrentUtils.ThreadPool;
import netUtils.Host;
import netUtils.MessageHandlerFactory;
import netUtils.Session;

import java.io.Serializable;

/**
 * Created by leon2 on 25.05.2017.
*/

public class Server implements Runnable, Serializable {
    private static final int DEFAULT_CHANNEL_SIZE = 16;

    private static final Object lock = new Object ();
    private static int maxThreadCount;
    private final int port;

    public static MessagesCache messagesCache;

    private ThreadPool threadPool;
    private Dispatcher dispatcher;
    private Host host;
    private MessageHandlerFactory classMHF;

    public Server (int port, int maxThreadCount, Class classMHF) {
        this.port = port;
        Server.maxThreadCount = maxThreadCount;
        try {
            this.classMHF = (MessageHandlerFactory) classMHF.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace ();
        }
    }

    public void stop(){
        System.out.println("Server's stopped");

        host.stop();
        dispatcher.stop();
        threadPool.stop();
    }

    public void run (){
        System.out.println("Server has started. Sessions limit: " + maxThreadCount);

        MessagesDB messagesDB = new MessagesDB(10);
        messagesCache = new MessagesCache(10);
        messagesCache.cacheDB();

        Channel<Session> channel = new Channel<>(DEFAULT_CHANNEL_SIZE);

        threadPool = new ThreadPool (maxThreadCount);
        dispatcher = new Dispatcher (channel, threadPool);

        new Thread(dispatcher).start();

        host = new Host (port, threadPool, classMHF);
        host.run();
    }
}