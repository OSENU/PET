/*
 * Класс описывает сушность в базе данных таблицы Subject
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
public class Subject implements Serializable {

    private Integer idSubject;
    private String nameSubject;

    public Subject() {
    }

    public Subject(Integer idSubject) {
        this.idSubject = idSubject;
    }

    public Subject(Integer idSubject, String nameSubject) {
        this.idSubject = idSubject;
        this.nameSubject = nameSubject;
    }

    public Integer getIdSubject() {
        return idSubject;
    }

    public void setIdSubject(Integer idSubject) {
        this.idSubject = idSubject;
    }

    public String getNameSubject() {
        return nameSubject;
    }

    public void setNameSubject(String nameSubject) {
        this.nameSubject = nameSubject;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSubject != null ? idSubject.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Subject)) {
            return false;
        }
        Subject other = (Subject) object;
        if ((this.idSubject == null && other.idSubject != null) || (this.idSubject != null && !this.idSubject.equals(other.idSubject))) {
            return false;
        }
        if ((this.nameSubject == null && other.nameSubject != null) || (this.nameSubject != null && !this.nameSubject.equals(other.nameSubject))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        if (nameSubject.length() > 30) {
            return nameSubject.substring(0, 30);
        }
        else {
            return nameSubject;
        }
    }
    
    /**
     * Метод добавляет в базу данных данный объект 
     * @return код результата: -1 - Такие данные уже есть
     *                         -2 - Произошла вообще ошибка
     *                         Или количество добавленых записей 
     * @throws SQLException 
     */
    public int insertInto() throws SQLException{
        int result;
        Connection conn = database.DataBaseConnect.getConnection();
        Statement st = conn.createStatement();
        // Формируем запрос на проверку
        ResultSet rs = st.executeQuery("Select id_subject "
                + "from Subject "
                + "where name_subject = '" + nameSubject + "';");
        if (!rs.next()) {
           // Значит в базе такого значения нет
            result = st.executeUpdate("insert into Subject"
                + "(name_Subject) "
                + " values('" + nameSubject + "');");
            conn.commit();
  
        } else {
            result = -1;
        }
        
        return result;
    }
    
    /**
     * Метод заносит измененный объект в базу данных.
     * Тоесть данный элимент в базу занесеть переданый элимент
     * @param newTeacher - объект на который надо заменить
     * @return код результата: -1 - Такие данные уже есть
     *                         -2 - Произошла вообще ошибка
     *                         Или количество добавленых записей
     * @throws SQLException 
     */
    public int updateTable(Subject newSubject) throws SQLException{
        int result;
        Connection conn = database.DataBaseConnect.getConnection();
        Statement st = conn.createStatement();
        // Формируем запрос на проверку
        ResultSet rs = st.executeQuery("Select id_subject "
                + "from subject "
                + "where name_subject = '" + newSubject.nameSubject +"' " 
                + "and id_subject <> " + idSubject + " ;");
        if (!rs.next()) {
           // Значит в базе такого значения нет
            String s = "update Subject "
                    + "set name_subject = '" + newSubject.nameSubject + "' "
                    + " where id_subject = " + idSubject + " ;";
            result = st.executeUpdate(s);
            conn.commit();
        } else {
            result = -1;
        }
        
        return result;
    }

}
