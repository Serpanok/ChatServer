package app;

import netUtils.MessageHandler;
import netUtils.MessageHandlerFactory;

/**
 * Created by leon2 on 30.05.2017.
 */
public class ChatMessageHandlerFactory implements MessageHandlerFactory {
    @Override
    public MessageHandler create(){
        return new ChatMessageHandler();
    }
}
