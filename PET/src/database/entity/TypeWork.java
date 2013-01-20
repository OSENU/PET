/*
* Класс описывает сушность в базе данных таблицы Type_Work
 */
package database.entity;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Aleo
 */

public class TypeWork implements Serializable, EntryDataBase {
    private Integer idTypeWork;
    private String nameTypeWork;

    public TypeWork() {
    }

    public TypeWork(Integer idTypeWork) {
        this.idTypeWork = idTypeWork;
    }

    public TypeWork(Integer idTypeWork, String nameTypeWork) {
        this.idTypeWork = idTypeWork;
        this.nameTypeWork = nameTypeWork;
    }

    public Integer getIdTypeWork() {
        return idTypeWork;
    }

    public void setIdTypeWork(Integer idTypeWork) {
        this.idTypeWork = idTypeWork;
    }

    public String getNameTypeWork() {
        return nameTypeWork;
    }

    public void setNameTypeWork(String nameTypeWork) {
        this.nameTypeWork = nameTypeWork;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTypeWork != null ? idTypeWork.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TypeWork)) {
            return false;
        }
        TypeWork other = (TypeWork) object;
        if ((this.idTypeWork == null && other.idTypeWork != null) || (this.idTypeWork != null && !this.idTypeWork.equals(other.idTypeWork))) {
            return false;
        }
        if ((this.nameTypeWork == null && other.nameTypeWork != null) || (this.nameTypeWork != null && !this.nameTypeWork.equals(other.nameTypeWork))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        //return "database.entity.TypeWork[ idTypeWork=" + idTypeWork + ", nameTypeWork=" + nameTypeWork +" ]";
        return nameTypeWork;
    }
    
       
    /**
     * Метод добавляет в базу данных данный объект 
     * @return код результата: -1 - Такие данные уже есть
     *                         -2 - Произошла вообще ошибка
     *                         Или количество добавленых записей 
     * @throws SQLException 
     */
    @Override
    public int insertInto() throws SQLException{
        int result;
        Connection conn = database.DataBaseConnect.getConnection();
        Statement st = conn.createStatement();
        // Формируем запрос на проверку
        ResultSet rs = st.executeQuery("Select id_type_work "
                + "from type_work "
                + "where name_type_work = '" + nameTypeWork + "';");
        if (!rs.next()) {
           // Значит в базе такого значения нет
            result = st.executeUpdate("insert into Type_work"
                + "(name_type_work) "
                + " values('" + nameTypeWork + "');");
            conn.commit();
  
        } else {
            result = -1;
        }
        
        return result;
    }
    
    /**
     * Метод заносит измененный объект в базу данных.
     * Тоесть данный элимент в базу занесеть переданый элимент
     * @param newTypeWork - объект на который надо заменить
     * @return код результата: -1 - Такие данные уже есть
     *                         -2 - Произошла вообще ошибка
     *                         Или количество добавленых записей
     * @throws SQLException 
     */
    @Override
    public int updateTable(Object object) throws SQLException{
        int result;
        if(!(object instanceof TypeWork)){
            return -2;
        }
        TypeWork newTypeWork = (TypeWork) object;
        Connection conn = database.DataBaseConnect.getConnection();
        Statement st = conn.createStatement();
        // Формируем запрос на проверку
        ResultSet rs = st.executeQuery("Select id_type_work "
                + "from type_work "
                + "where name_type_work = '" + newTypeWork.nameTypeWork +"' " 
                + "and id_type_work <> " + idTypeWork + " ;");
        if (!rs.next()) {
           // Значит в базе такого значения нет
            String s = "update type_work "
                    + "set name_type_work = '" + newTypeWork.nameTypeWork + "' "
                    + " where id_type_work = " + idTypeWork + " ;";
            result = st.executeUpdate(s);
            conn.commit();
        } else {
            result = -1;
        }
        
        return result;
    }
    
}
