/*
 * Класс содержить методы, которые помогут с операциями таблиц JTable
 */
package util;

import javax.swing.JTable;

public class TablesUtil {
    
    /**
     * Метод скрывает заданые колонки, но они остаются в таблице
     * @param table ссылка на таблицу
     * @param column колонка которую необходимо спрятать
     */
    public static void hideColumn(JTable table, int column){
        if(table.getColumnCount() >= column){
            // Если такая колонка существует
            table.getColumnModel().getColumn(0).setMaxWidth(0);
            table.getColumnModel().getColumn(0).setMinWidth(0);
            table.getColumnModel().getColumn(0).setPreferredWidth(0);
            table.getColumnModel().getColumn(0).setResizable(false);
        }
    }
    
    /** 
     * НЕОБХОДИМА ДОРОБОТКА
     * Метод показывает заданые колонки, но они остаются в таблице
     * @param table ссылка на таблицу
     * @param column колонка которую необходимо показать
     */
    public static void showColumn(JTable table, int column){
        if(table.getColumnCount() >= column){
            // Если такая колонка существует
            table.getColumnModel().getColumn(0).setMaxWidth(10000);
            table.getColumnModel().getColumn(0).setMinWidth(0);
            table.getColumnModel().getColumn(0).setPreferredWidth(100);
            table.getColumnModel().getColumn(0).setResizable(true);
        }
    }
}
