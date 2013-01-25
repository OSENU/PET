/*
 * Интерфейс позволяющий объеденить все сущности базы данных
 * данный интерфейс говорит о том, что любая сущность должна 
 * себя добавить в базу, или обновить
 */
package ua.edu.odeku.pet.database.entry;

import java.io.Serializable;
import java.sql.SQLException;

/**
 *
 * @author Aleo
 */
public interface EntryDataBase extends Serializable{
    
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
    
    /**
     * Метод вернет код данного объекта из базы данных 
     * @return Код объекта, если данного объекта не существует, то вернет null
     */
    public Integer getIdFromDataBase() throws SQLException;
}
