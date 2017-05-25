package app;

import java.sql.SQLException;
import java.sql.ResultSet;

/**
 * Created by leon2 on 25.05.2017.
 */
public class MessagesDB {

    private static ConnectionDB connection;
    private static int packageLimit;

    public MessagesDB( int PackageLimit )
    {
        try {
            connection.connect();
            connection.createDB();

            this.packageLimit = PackageLimit;

            //MessagePackage messages = getMessages(0);

            //System.out.println(messages.getSize());
            //System.out.println(messages.getMessage(0).getMessage());

            //int id = insertMessage("Serpanok", "Hello, world!");
            //System.out.println("Insert id: " + id);

            //connection.closeDB();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public static int insertMessage( String username, String message )
    {
        try {
            connection.execute("INSERT INTO 'messages' ('username', 'message') VALUES ( '" + username + "', '" + message + "' );");

            return connection.getLastId();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return -1;
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
            return -1;
        }
    }

    public static MessagePackage getMessages( int lastId )
    {
        MessagePackage messagePackage = new MessagePackage( packageLimit );

        try {
            ResultSet resultSet = connection.executeQuery("SELECT * FROM messages WHERE id > " + lastId + " LIMIT " + packageLimit);

            while(resultSet.next())
            {
                messagePackage.insert(new Message( resultSet.getString("username"), resultSet.getString("message"), resultSet.getInt("id") ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        return messagePackage;
    }

    public void close()
    {
        try {
            connection.closeDB();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

}
