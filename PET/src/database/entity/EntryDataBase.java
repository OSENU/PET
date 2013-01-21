/*
 * Интерфейс позволяющий объеденить все сущности базы данных
 * данный интерфейс говорит о том, что любая сущность должна 
 * себя добавить в базу, или обновить
 */
package database.entity;

import java.sql.SQLException;

/**
 *
 * @author Aleo
 */
public interface EntryDataBase {
    
    /**
     * Метод добавляет в базу данных данный объект 
     * @return код результата: -1 - Такие данные уже есть
     *                         -2 - Произошла вообще ошибка
     *                         Или количество добавленых записей 
     * @throws SQLException 
     */
    public int insertInto() throws SQLException;
    
    /**
     * Метод заносит измененный объект в базу данных.
     * Тоесть данный элимент в базу занесеть переданый элимент
     * @param объект на который надо заменить
     * @return код результата: -1 - Такие данные уже есть
     *                         -2 - Произошла вообще ошибка
     *                         Или количество добавленых записей
     * @throws SQLException 
     */
    public int updateTable(Object object) throws SQLException;
}
