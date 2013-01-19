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
    
    private static String jdbc;
    private static String db_name;
    private static String user;
    private static String pass;
    private static String db_dir;
    
    
    private DataBaseConnect(){}
    
    /**
     * Возращает соединение с базой
     * @return Connection conn
     * @throws SQLException 
     */
    public static Connection getConnection() throws SQLException{
        if(conn == null){
            db_name = settings.ConfigureProgramm.getDB_NAME();
            jdbc = settings.ConfigureProgramm.getJDBC();
            user = settings.ConfigureProgramm.getDB_USER();
            pass = settings.ConfigureProgramm.getDB_USER_PASS();
            db_dir = settings.ConfigureProgramm.getDB_DIR();
            conn = DriverManager.getConnection(jdbc + db_dir + db_name, user, pass);
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
