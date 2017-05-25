package app;

import netUtils.MessageHandler;

import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Created by leon2 on 25.05.2017.
 */
public class PrintMessageHandler implements MessageHandler {

    @Override
    public void handle (Request request, ObjectOutputStream outputStream){
        if( request.type == 0 )
        {
            try {
                outputStream.writeObject( new Request(MessagesDB.getMessages(request.lastId)) );
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if( request.type == 1 )
        {
            MessagesDB.insertMessage(request.sendMessage.getUsername(), request.sendMessage.getMessage());
        }
    }
}
