/*
 * Класс описывает сушность в базе данных таблицы Teacher
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

public class Teacher implements Serializable, EntryDataBase {
    private Integer idTeacher;
    private String name;
    private String name2;
    private String surname;
    private Department idDepartment;

    public Teacher() {
    }

    public Teacher(Integer idTeacher) {
        this.idTeacher = idTeacher;
    }

    public Teacher(Integer idTeacher, String name, String name2, String surname) {
        this.idTeacher = idTeacher;
        this.name = name;
        this.name2 = name2;
        this.surname = surname;
    }

    public Integer getIdTeacher() {
        return idTeacher;
    }

    public void setIdTeacher(Integer idTeacher) {
        this.idTeacher = idTeacher;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Department getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(Department idDepartment) {
        this.idDepartment = idDepartment;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTeacher != null ? idTeacher.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Teacher)) {
            return false;
        }
        Teacher other = (Teacher) object;
        if ((this.idTeacher == null && other.idTeacher != null) || (this.idTeacher != null && !this.idTeacher.equals(other.idTeacher))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return surname + " " + name.charAt(0) + "."+ name2.charAt(0) + " : "+idDepartment.getNameDepartment();
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
        ResultSet rs = st.executeQuery("Select id_teacher "
                + "from Teacher "
                + "where name = '" + name + "' and "
                + "name2 = '" + name2 + "' and "
                + "surname = '" + surname + "' and "
                + "id_department = " + idDepartment.getIdDepartment() + ";");
        if (!rs.next()) {
           // Значит в базе такого значения нет
            result = st.executeUpdate("insert into teacher"
                + "(name, name2, surname, id_department) "
                + " values('" + name + "', '" + name2 + "', '" + surname + "', "
                    + idDepartment.getIdDepartment() + ");");
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
    @Override
    public int updateTable(Object object) throws SQLException{
        int result;
        if(! (object instanceof Teacher)){
            return -2;
        }
        Teacher newTeacher = (Teacher) object;
        Connection conn = database.DataBaseConnect.getConnection();
        Statement st = conn.createStatement();
        // Формируем запрос на проверку
        ResultSet rs = st.executeQuery("Select id_teacher "
                + "from Teacher "
                + "where name = '" + newTeacher.name + "' and "
                + "name2 = '" + newTeacher.name2 + "' and "
                + "surname = '" + newTeacher.surname + "' and "
                + "id_department = " + newTeacher.idDepartment.getIdDepartment() + ""
                + "and id_teacher <> " + idTeacher + " ;");
        if (!rs.next()) {
           // Значит в базе такого значения нет
            String s = "update teacher "
                    + "set name = '" + newTeacher.name + "', "
                    + "name2 = '" + newTeacher.name2 +"', "
                    + "surname = '" + newTeacher.surname + "', "
                    + "id_department = " + newTeacher.idDepartment.getIdDepartment()
                    + " where id_teacher = " + idTeacher + " ;";
            result = st.executeUpdate(s);
            conn.commit();
        } else {
            result = -1;
        }
        
        return result;
    }
    
}
