package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by leon2 on 25.05.2017.
 */
public class ConnectionDB {
    public static Connection conn;
    public static Statement statmt;
    public static ResultSet resSet;

    public static void connect() throws ClassNotFoundException, SQLException
    {
        conn = null;
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:ChatDB.s3db");
    }

    public static void createDB() throws ClassNotFoundException, SQLException
    {
        statmt = conn.createStatement();
        statmt.execute("CREATE TABLE if not exists 'messages' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'username' text, 'message' text);");
    }

    public static boolean execute( String query ) throws SQLException
    {
        return statmt.execute(query);
    }

    public static ResultSet executeQuery( String sql ) throws ClassNotFoundException, SQLException
    {
        return statmt.executeQuery( sql );
    }

    // --------Закрытие--------
    public static void closeDB() throws SQLException
    {
        conn.close();
        statmt.close();
        resSet.close();
    }

    public static int getLastId() throws ClassNotFoundException, SQLException
    {
        resSet = statmt.executeQuery("SELECT * FROM messages ORDER BY id DESC LIMIT 1");

        resSet.next();
        return resSet.getInt("id");
    }

}