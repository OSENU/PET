package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Aleo
 */
public class DataBaseConnect {
    /**
     * Этот класс сингелтон он дает обїект в эденичном экземпляре
     */
    private static Connection conn = null;
    private static int countConnection = 0;
    
    private static String jdbc = "jdbc:h2:~/test";
    private static String user = "sa";
    private static String pass = "";
    
    private DataBaseConnect(){}
    
    /**
     * Возращает соединение с базой
     * @return Connection conn
     * @throws SQLException 
     */
    public static Connection getConnection() throws SQLException{
        if(conn == null){
            conn = DriverManager.getConnection(jdbc, user, pass);
            countConnection = 0;
        }
        countConnection++;
        return conn;
    }
    /**
     * Если запрошеных несколько то закроем когда останеться один
     * @throws SQLException 
     */
    public static void destroyConnection() throws SQLException{
        countConnection--;
        if(countConnection <= 0){
            conn.close();
            conn = null;
        }
    }
    public static void destroyAllConnection() throws SQLException{
        countConnection = 0;
        conn.close();
    }
    
}
