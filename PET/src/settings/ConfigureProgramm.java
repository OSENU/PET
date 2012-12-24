/*
 * Класс занимается сохранением конфигураций
 * На данный помент он проверяет 
 * на запуск программы в режиме отладки
 */
package settings;

/**
 *
 * @author Aleos
 */
public class ConfigureProgramm {
   
    private static boolean DEBAG = false;

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
    
    private ConfigureProgramm(){}
    
    
}
