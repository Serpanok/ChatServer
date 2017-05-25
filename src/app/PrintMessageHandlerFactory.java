package app;

import netUtils.MessageHandler;
import netUtils.MessageHandlerFactory;

/**
 * Created by leon2 on 25.05.2017.
 */

public class PrintMessageHandlerFactory implements MessageHandlerFactory {
    @Override
    public MessageHandler create(){
        return new PrintMessageHandler();
    }
}
