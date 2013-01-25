package ua.edu.odeku.pet.database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import ua.edu.odeku.pet.util.SMS;

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
    private static Savepoint savepoint = null;
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
            db_name = ua.edu.odeku.pet.settings.ConfigureProgramm.getDB_NAME();
            jdbc = ua.edu.odeku.pet.settings.ConfigureProgramm.getJDBC();
            user = ua.edu.odeku.pet.settings.ConfigureProgramm.getDB_USER();
            pass = ua.edu.odeku.pet.settings.ConfigureProgramm.getDB_USER_PASS();
            db_dir = ua.edu.odeku.pet.settings.ConfigureProgramm.getDB_DIR();
            conn = DriverManager.getConnection(jdbc + db_dir + db_name, user, pass);
            conn.setAutoCommit(false);
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
    
    /*
     * Метод проверяет на существование главное таблицы, если ее нет то нет и базы данных
     * Так же этот метод в будущем должен проверять версию базы данных и обновлять ее если это необходимо
     */
    public static boolean checkTables(){
        boolean flag = true;
        Connection connection;
        Statement statement = null;
        ResultSet rs;
        try {
            connection = getConnection();
            statement = connection.createStatement();
        } catch (SQLException ex) {
            SMS.error(ex.toString());
            Logger.getLogger(DataBaseConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            rs = statement.executeQuery("SELECT * FROM PET_CONFIG;");
        } catch (SQLException ex) {
            if(ex.toString().indexOf("Table \"PET_CONFIG\" not found;") != -1){
                flag = false;
            } else{
                Logger.getLogger(DataBaseConnect.class.getName()).log(Level.SEVERE, null, ex);
                SMS.error(ex.toString());
            }
        }
        return flag;
    }
    
    public static void createTables(){
        // Проверяем на необходимость
        if(! checkTables()){
            SMS.warning("База данных не существует. Далее будет попытка ее создать.");
            String name_file = ua.edu.odeku.pet.settings.ConfigureProgramm.getSQL_TABLES_FILE();
            if(name_file.endsWith(".sql")){
                File file = new File(name_file);
                if(file.exists()){
                    StringBuffer sb = new StringBuffer();
                    String line;
                    BufferedReader br = null;
                    FileReader fr = null;
                    try {
                        fr = new FileReader(file);
                        br = new BufferedReader(fr);
                        // считываем данные
                        while( (line = br.readLine()) != null ){
                            sb.append(line);
                            sb.append("\r\n");
                        }
                        String script = sb.toString();
                        try {
                            execute(script);
                            SMS.message("База данных была успешно создана");
                        } catch (SQLException ex) {
                            SMS.error("Ошибка при выполенении скрипта");
                            Logger.getLogger(DataBaseConnect.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                    } catch (FileNotFoundException ex) {
                        SMS.error(ex.toString());
                        Logger.getLogger(DataBaseConnect.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        SMS.error(ex.toString());
                        Logger.getLogger(DataBaseConnect.class.getName()).log(Level.SEVERE, null, ex);
                    } finally{
                        if(fr!= null){
                            try {
                                fr.close();
                                br.close();
                            } catch (IOException ex) {
                                SMS.error(ex.toString());
                                Logger.getLogger(DataBaseConnect.class.getName()).log(Level.SEVERE, null, ex);
                                return;
                            }
                        }
                    }
                    
                    
                    
                } else {
                    SMS.error(name_file + " не существует. Не возможно создать базу данных");
                    System.exit(-1);
                }
            } else {
                SMS.warning("Не указан файл со скриптами базы данных");
                System.exit(-1);
            }
            
        }
    }
    
    /**
     * Метод выполняет в базе данных заданый скрипт
     * @param script строка, в которую записан скрипт.
     * @return int количество выполненых операций.
     * @throws SQLException 
     */
    public static boolean execute(String script) throws SQLException{
        
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        return statement.execute(script);

    }
    
    public static Savepoint getSavepoint() throws SQLException{
        Connection connection = getConnection();
        return connection.setSavepoint();
    }
    
    public static Savepoint getSavepoint(String save) throws SQLException{
        Connection connection = getConnection();
        return connection.setSavepoint(save);
    }

    public static void setStaticSavepoint() throws SQLException{
        savepoint = getSavepoint();
    }
    
    public static void rollBackStatic() throws SQLException{
        Connection connection = getConnection();
        connection.rollback(savepoint);
    }
    
    public static void rallBack(Savepoint save) throws SQLException{
        Connection connection = getConnection();
        connection.rollback(save);
    }
    
    public static Statement getStatement() throws SQLException{
        return getConnection().createStatement();
    }
}
