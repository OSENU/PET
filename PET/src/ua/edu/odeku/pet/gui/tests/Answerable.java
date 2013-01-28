/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.odeku.pet.gui.tests;

import ua.edu.odeku.pet.database.entry.Question;

/**
 *
 * @author Aleo
 */
public interface Answerable {
    
    /**
     * Метод должен сохранить ответ, если есть ошибки то вернуть строку с ошибкой
     * @param idQuestion - код вопроса к которому приписываеться ответ
     * @return возражения и ошибки
     */
    public String saveAnswer(Question question);
    
    /**
     * Загрузка ответа по указаному id в базе данных
     * @param idAnswer
     * @return 
     */
    public String loadAnswer(Integer idAnswer);
    
    /**
     * Текст ответа
     * @return 
     */
    public String getTextAnswer();
    
    /**
     * Удаляет данный ответ из базы данных
     * @return String возражения к сохранению
     */
    public String removeAnswer();
    
    /**
     * Проверка ответа на заполненость и готовоность к сохранению
     * @return String возражения
     */
    public String checkToPrepareAnswer();
    
    /**
     * 
     * @return True если это верный ответ
     */
    public boolean isRightAnswer();
}
