package app;

import netUtils.MessageHandler;

/**
 * Created by leon2 on 25.05.2017.
 */
public class PrintMessageHandler implements MessageHandler {
    @Override
    public void handle (String message){
        System.out.println(message);
    }
}
