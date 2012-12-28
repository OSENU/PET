/*
 * Класс помогает показывать данные в виде диалога.
 * Впринципе это стандартивозаный метод доступа к JOptionPane
 */
package util;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Aleos
 */
public class SMS {
    
    private SMS(){}
    
    public static void message(JFrame parent, String message, String title){
        enableParent(parent, false);
        JOptionPane.showMessageDialog(parent, message, title, JOptionPane.INFORMATION_MESSAGE);
        enableParent(parent, true);
    }
    
    public static void message(JFrame parent, String message){
        message(parent, message, "Сообщение");
    }
    
    public static void message(String message){
        message(null, message, "Cообщение");
    }
    
    public static void message(String message, String title){
        message(null, message, title);
    }
    
    public static void error(JFrame parent, String message, String title){
        enableParent(parent, false);
        JOptionPane.showMessageDialog(parent, message, title, JOptionPane.ERROR_MESSAGE);
        enableParent(parent, true);
    }
    
    public static void error(JFrame parent, String message){
        message(parent, message, "Ошибка!");
    }
    
    public static void error(String message){
        message(null, message, "Ошибка!");
    }
    
    public static void error(String message, String title){
        message(null, message, title);
    }
        
    public static void warning(JFrame parent, String message, String title){
        enableParent(parent, false);
        JOptionPane.showMessageDialog(parent, message, title, JOptionPane.WARNING_MESSAGE);
        enableParent(parent, true);
    }
    
    public static void warning(JFrame parent, String message){
        message(parent, message, "Внимание!");
    }
    
    public static void warning(String message){
        message(null, message, "Внимание!");
    }
    
    public static void warning(String message, String title){
        message(null, message, title);
    }
        
    public static boolean query(JFrame parent, String message, String title, Object variant[]){
        boolean result = true;
        enableParent(parent, false);
        
        int resalt = JOptionPane.showOptionDialog(parent,
                message, title,
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, variant, variant[0]);

        if (resalt == 0) {
            result = true;
        } else {
            result = false;
        }
        
        enableParent(parent, true);
        return result;
    }
    
    public static boolean query(JFrame parent, String message, String title){
        return query(parent, message, title, new Object[] {"Да", "Нет"});
    }
    
    public static boolean query(JFrame parent, String message){
        return query(parent, message, "Внимание");
    }
    
    public static boolean query(String message, String title, Object variant[]){
        return query(null, message, title, variant);
    }
    
    public static boolean query(String message, String title){
        return query(null, message, title);
    }
    
    public static boolean query(String message){
        return query(null, message, "Внимание");
    }
    
    /**
     * Создаеться диалог с вопросом, и указаными кнопками
     * @param parent - Родитель которого надо блокировать
     * @param message - Сообщение с вопросом
     * @param title - Заголовок диалога
     * @param values - Значения вопросов
     * @return 
     */
    public static int bigQuery(JFrame parent, String message, String title, Object[] values){
        enableParent(parent, false);
        
        int resalt = JOptionPane.showOptionDialog(parent,
                message, title,
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, values, values[0]);

        enableParent(parent, true);
        return resalt;
    }
    
    /**
     * Создаеться диалог с вопросом, и указаными кнопками
     * @param parent - Родитель которого надо блокировать
     * @param message - Сообщение с вопросом
     * @param values - Значения вопросов
     * @return 
     */
    public static int bigQuery(JFrame parent, String message, Object[] values){
        return bigQuery(parent, message, "Сообщение", values);
    }
    
    /**
     * Создаеться диалог с вопросом, и указаными кнопками
     * @param message - Сообщение с вопросом
     * @param title - Заголовок диалога
     * @param values - Значения вопросов
     * @return 
     */
    public static int bigQuery(String message, String title, Object[] values){
        return bigQuery(null, message, title, values);
    }

    /**
     * Создаеться диалог с вопросом, и указаными кнопками
     * @param message - Сообщение с вопросом
     * @param values - Значения вопросов
     * @return 
     */
    public static int bigQuery(String message, Object[] values){
        return bigQuery(null, message, "Сообщение", values);
    }

    /**
     * Метод позволяет создать диалоговое окно с теми 
     * компонентами которые тебе нужны
     * @param parent - родитель которого надо заблокировать
     * @param title - Заголовок диалога
     * @param components - Компоненты которые должны быть в диалоге
     * @param values - Значения кнопок
     * @return - id кнопки
     */
    public static int dialog(JFrame parent, String title, 
            JComponent[] components, Object[] values){
        
        enableParent(parent, false);
        int resalt = JOptionPane.showOptionDialog(parent,
                components, title,
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, values, values[0]);

        enableParent(parent, true);
        return resalt;
    }
    
    /**
     * Метод позволяет создать диалоговое окно с теми 
     * компонентами которые тебе нужны
     * @param parent - родитель которого надо заблокировать
     * @param title - Заголовок диалога
     * @param components - Компоненты которые должны быть в диалоге
     * @return - id кнопки
     */
    public static boolean dialog(JFrame parent, String title, 
            JComponent[] components){
        
        enableParent(parent, false);
        Object[] values = new Object[] {"OK", "Отмена"};
        int resalt = JOptionPane.showOptionDialog(parent,
                components, title,
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, values, values[0]);

        enableParent(parent, true);
        if(resalt == 0){
            return true;
        } else {
            return false;
        }
    }    

   /**
     * Метод позволяет создать диалоговое окно с теми 
     * компонентами которые тебе нужны
     * @param title - Заголовок диалога
     * @param components - Компоненты которые должны быть в диалоге
     * @param values - Значения кнопок
     * @return - id кнопки
     */
    public static int dialog(String title, 
            JComponent[] components, Object[] values){
        return dialog(null, title, components, values);
    }
    
    /**
     * Метод позволяет создать диалоговое окно с теми 
     * компонентами которые тебе нужны
     * @param title - Заголовок диалога
     * @param components - Компоненты которые должны быть в диалоге
     * @return - id кнопки
     */
    public static boolean dialog(String title, 
            JComponent[] components){
        return dialog(null, title, components);
    }
    
    
    /**
     * Создаеться диалог в котром есть одно текстовое поле, возращает метод
     * строку с результатом, если строка == null, то тогда нажали отмену
     * @param parent - Родитель которого надо блокировать
     * @param message - Сообщение диалога 
     * @param title - Заголовове окна
     * @param value - Значение по умолчанию в диалоге
     * @return 
     */
    public static String input(JFrame parent, String message, String title, String value){
        enableParent(parent, false);
        JTextField text = new JTextField();
            // Создаем компоненты, для диалога
        final JComponent[] fields = {new JLabel(message), text };
        
        if(value != null){
            text.setText(value);
        }
        if(title == null){
            title = "Введите данные:";
        }
        
        boolean resalt = dialog(parent, title, fields); 
        String result;
        if(resalt){
            result = text.getText();
        } else {
            result = null;
        }
        enableParent(parent, true);
        return result;
    }
    
    /**
     * Создаеться диалог в котром есть одно текстовое поле, возращает метод
     * строку с результатом, если строка == null, то тогда нажали отмену
     * @param parent - Родитель которого надо блокировать
     * @param message - Сообщение диалога
     * @param title - заголовок диалога
     * @return 
     */
    public static String input(JFrame parent, String message, String title ){
        return input(parent, message, title, null);
    }
    
    /**
     * Создаеться диалог в котром есть одно текстовое поле, возращает метод
     * строку с результатом, если строка == null, то тогда нажали отмену
     * @param parent - Родитель которого надо блокировать
     * @param message - Сообщение диалога
     * @return 
     */
    public static String input(JFrame parent, String message){
        return input(parent, message, "Ввод данных");
    }
    
    /**
     * Создаеться диалог в котром есть одно текстовое поле, возращает метод
     * строку с результатом, если строка == null, то тогда нажали отмену
     * @param message - Сообщение диалога
     * @param title - Заголовок диалога
     * @return 
     */
    public static String input(String message, String title){
        return input(null, message, title);
    }
    
    /**
     * Создаеться диалог в котром есть одно текстовое поле, возращает метод
     * строку с результатом, если строка == null, то тогда нажали отмену
     * @param message - Сообщение диалога
     * @return 
     */
    public static String input(String message){
        return input(null, message, "Ввод данных:");
    }
    
    private static void enableParent(JFrame parent, boolean flag){
        if(parent != null){
            parent.setEnabled(flag);
        }
    }
    
    
    
    
}
