package app;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.Map;
import java.util.HashMap;

/**
 * Created by leon2 on 30.05.2017.
 */
public class MessagesCache {
    Vector<Message> messagesDB = new Vector<Message>();
    int lastId  = -1;

    private int packageLimit;

    public MessagePackage getMessages( int queryFromId )
    {
        MessagePackage resultPackage = new MessagePackage(packageLimit);

        if( this.lastId > queryFromId )
        {
            for (int i=0; i < messagesDB.size(); i++)
            {
                Message temp = messagesDB.get(i);
                if( temp.getID() > queryFromId )
                {
                    if(!resultPackage.insert(temp))
                    {
                        break;
                    }
                }
            }
        }

        return resultPackage;
    }

    public void cacheDB()
    {
        try {
            ResultSet resultSet = MessagesDB.getAllMessages();

            while(resultSet.next())
            {
                this.lastId = resultSet.getInt("id");
                messagesDB.add(new Message( resultSet.getString("username"), resultSet.getString("message"), this.lastId ));
                System.out.println("cache msg #" + this.lastId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void cacheNewMessage( Message msg )
    {
        if( msg != null ) {
            messagesDB.add(msg);
            this.lastId = msg.getID();
            System.out.println("cache!");
        }
    }

    public MessagesCache( int packageLimit )
    {
        this.packageLimit = packageLimit;
    }
}
