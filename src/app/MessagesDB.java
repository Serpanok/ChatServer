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

    public static Message insertMessage( String username, String message )
    {
        try {
            connection.execute("INSERT INTO 'messages' ('username', 'message') VALUES ( '" + username + "', '" + message + "' );");

            Message msg = new Message(username, message, connection.getLastId());

            return msg;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public static ResultSet getAllMessages( )
    {
         try {
           return connection.executeQuery("SELECT * FROM messages");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public static ResultSet getResultQuery( int lastId )
    {
        try {
            ResultSet resultSet = connection.executeQuery("ads");


            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        return null;
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
