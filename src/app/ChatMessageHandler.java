package app;

import netUtils.MessageHandler;

import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Created by leon2 on 30.05.2017.
 */
public class ChatMessageHandler implements MessageHandler {

    @Override
    public void handle (Request request, ObjectOutputStream outputStream){
        if( request.type == 0 )
        {
            try {
                //outputStream.writeObject( new Request(MessagesDB.getMessages(request.lastId)) );
                outputStream.writeObject( new Request( Server.messagesCache.getMessages(request.lastId) ) );
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if( request.type == 1 )
        {
            Server.messagesCache.cacheNewMessage(MessagesDB.insertMessage(request.sendMessage.getUsername(), request.sendMessage.getMessage()));
        }
    }
}
