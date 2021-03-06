/*
 * Этот интерфейс необходим для того что все Jpanel описывающие одно задание
 * могли иметь один общий интерфейс, с помощью которого можно было бы сохранять
 * или доставать эти данные из базы
 */
package ua.edu.odeku.pet.gui.tests;

import javax.swing.JPanel;
import ua.edu.odeku.pet.database.entry.TypeQuestion;

/**
 *
 * @author Aleo
 */
public interface Questionable {
    
    /**
     * Метод должен вызываться для сохранения этого задания в базу данных.
     * Для сохранения необходимо передать код теста которому будет приписан 
     * этот вопрос.
     * @param test код теста к которому будет приписан вопрос
     * @return вернет строку с возражениями, если она пуста, то все ОК
     */
    public String saveQuestion(ua.edu.odeku.pet.database.entry.Test test);
    
    /**
     * Метод должен загрузить с базы данные, по переданому ему коду вопроса
     * И вернуть объект Jpanel который потом будет вставлен в форму.
     * @param idItem код вопроса который будет восоздаваться
     * @return JPanel который будет вставлен в форму
     */
    public JPanel loadQuestion(long idItem);
    
    /**
     * Метод должен вернуть текст задания этого теста
     * @return String текст задания для данного теста
     */
    public String getTask();
    
    /**
     * Метод вернет строку которая содержит тип данного теста
     * @return String тип данного теста
     */
    public TypeQuestion getTypeQuestion();
    
    /**
     * Метод вернет количество вариантов для данного теста
     * @return int количевство вариантов ответа для данного теста
     */
    public int getCountAnswer();
    
    /**
     * Метод вернет количество правильных вариантов ответов для данного теста
     * @return int количество правильных вариантов ответа для данного теста
     */
    public int getCountRightAnswer();
    
    /**
     * Метод удалит из базы данный тест
     */
    public void removeQuestion();
    
    /**
     * Метод удалит один из вариантов из базы и из JPanel
     * @param idVariant int номер варианта ответа который необходио удалить
     */
    public void removeAnswer(int idVariant);
    
    /**
     * Метод проверяем готовность задания теста. Заполнен ли он или нет.
     * @return Возращает строку с возражение, 
     *         если вернет пустую строку, то возражений нет
     */
    public String checkToPrepareQuestion();
}
