/*
 * Класс занимается сохранением конфигураций
 */
package settings;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.SMS;

/**
 *
 * @author Aleos
 */
public class ConfigureProgramm {
    // Файл где будет храниться настройки
    final private static String CONFIG_FILE = "pet.properties";
    // Имя базы данных которая будет использоваться
    private static String DB_NAME;
    // Используемая база данных
    private static String JDBC;
    // Пользователь базы данных
    private static String DB_USER;
    // Пароль базы данных
    private static String DB_USER_PASS;
    // Флаг на то что программа в режиме отладки
    private static String DB_DIR;
    
    private static boolean DEBAG = false;
    
    /*
     * В начале работы программы дожен вызываться этот метод, 
     * он загрузить данные настроек
     */
    public static void loadConfig(){
        Properties properties = new Properties();
        try {
            
            properties.load(new FileInputStream(CONFIG_FILE));
            DB_NAME = properties.getProperty("DB_NAME", "test");
            JDBC = properties.getProperty("JDBC", "jdbc:h2:");
            DB_USER = properties.getProperty("DB_USER", "sa");
            DB_USER_PASS = properties.getProperty("DB_USER_PASS", "");
            DB_DIR = properties.getProperty("DB_DIR", "");
            if(DB_DIR.trim().isEmpty()){
                // Если путь к базе не указан, 
                //то пусть будет в папке пользователя
                DB_DIR = "~/";
            }
            DEBAG = Boolean.getBoolean(properties.getProperty("DEBAG", "false"));
            
        } catch ( FileNotFoundException ex) {
            SMS.error(ex.getMessage());
            Logger.getLogger(ConfigureProgramm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex){
            SMS.error(ex.getMessage());
            Logger.getLogger(ConfigureProgramm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * После каких либо изменений в настройках необходимо их сохнанить в файл,
     * для этого используем этот метод.
     */
    public static void saveConfig(){
        Properties properties = new Properties();
        
        properties.setProperty("DB_NAME", getDB_NAME());
        properties.setProperty("JDBC", getJDBC());
        properties.setProperty("DB_USER", getDB_USER());
        properties.setProperty("DB_USER_PASS", getDB_USER_PASS());
        properties.setProperty("DEBAG", Boolean.toString(DEBAG));
        properties.setProperty("DB_FILE", ((DB_DIR.equals("~/"))? "" : DB_DIR));
        
        Calendar calendar = Calendar.getInstance();
        try {
            properties.store(new FileOutputStream(CONFIG_FILE), calendar.getTime().toString());
        } catch (FileNotFoundException ex) {
            SMS.error(ex.getMessage());
            Logger.getLogger(ConfigureProgramm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex){
            SMS.error(ex.getMessage());
            Logger.getLogger(ConfigureProgramm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /** Проверяет находиться ли программа в режиме отладки
     * @return the DEBAG
     */
    public static boolean isDEBAG() {
        return DEBAG;
    }

    /** Устанавливает режим работі программы, 
     * по умолчанию она находиться в не режиме отладки
     * @param aDEBAG the DEBAG to set
     */
    public static void setDEBAG(boolean aDEBAG) {
        DEBAG = aDEBAG;
    }

    /**
     * @return the DB_NAME
     */
    public static String getDB_NAME() {
        return DB_NAME;
    }

    /**
     * @return the JDBC
     */
    public static String getJDBC() {
        return JDBC;
    }

    /**
     * @return the DB_USER
     */
    public static String getDB_USER() {
        return DB_USER;
    }

    /**
     * @return the DB_USER_PASS
     */
    public static String getDB_USER_PASS() {
        return DB_USER_PASS;
    }

    /**
     * @return the DB_DIR
     */
    public static String getDB_DIR() {
        return DB_DIR;
    }
    
    private ConfigureProgramm(){}
    
    
}
