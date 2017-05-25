package app;

import java.io.FileNotFoundException;

/**
 * Created by leon2 on 25.05.2017.
 */
public class Runner {
    public static void main(String[] args) throws IllegalArgumentException, ClassNotFoundException, FileNotFoundException {
        int port = Integer.parseInt(args[0]);
        int maxThreadCount = Integer.parseInt(args[1]);
        Class classMHF = Class.forName(args[2]);
        try {

            if (port < 0 || port > 65535)
                throw new IllegalArgumentException(args[0]);
            if (maxThreadCount < 1)
                throw new IllegalArgumentException(args[1]);

            Server server = new Server(port, maxThreadCount, classMHF);
            server.run();

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                server.stop();
                System.out.println("Bye");
            }));
        } catch (IllegalArgumentException str){
            System.err.print("Illegal port");
        }
    }
}
