/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
public class Faculty implements Serializable {
    private Integer idFaculty;
    private String nameFaculty;
    private Department idDepartment;

    public Faculty() {
    }

    public Faculty(Integer idFaculty) {
        this.idFaculty = idFaculty;
    }

    public Faculty(Integer idFaculty, String nameFaculty) {
        this.idFaculty = idFaculty;
        this.nameFaculty = nameFaculty;
    }

    public Integer getIdFaculty() {
        return idFaculty;
    }

    public void setIdFaculty(Integer idFaculty) {
        this.idFaculty = idFaculty;
    }

    public String getNameFaculty() {
        return nameFaculty;
    }

    public void setNameFaculty(String nameFaculty) {
        this.nameFaculty = nameFaculty;
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
        hash += (idFaculty != null ? idFaculty.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Faculty)) {
            return false;
        }
        Faculty other = (Faculty) object;
        if ((this.idFaculty == null && other.idFaculty != null) || (this.idFaculty != null && !this.idFaculty.equals(other.idFaculty))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "database.entity.Faculty[ idFaculty=" + idFaculty + " ]";
    }
        /**
     * Метод заносит измененный объект в базу данных.
     * Тоесть данный элимент в базу занесеть переданый элимент
     * @param newFaculty - объект на который надо заменить
     * @return код результата: -1 - Такие данные уже есть
     *                         -2 - Произошла вообще ошибка
     *                         Или количество добавленых записей
     * @throws SQLException 
     */
    public int updateTable(Faculty newFaculty) throws SQLException{
        int result;
        Connection conn = database.DataBaseConnect.getConnection();
        Statement st = conn.createStatement();
        // Формируем запрос на проверку
        ResultSet rs = st.executeQuery("Select id_Faculty "
                + "from Faculty "
                + "where name_Faculty = '" + newFaculty.nameFaculty +"' "
                + "and id_department = " + newFaculty.idDepartment.getIdDepartment()
                + "and id_Faculty <> " + idFaculty + " ;");
        if (!rs.next()) {
           // Значит в базе такого значения нет
            String s = "update Faculty "
                    + "set name_Faculty = '" + newFaculty.nameFaculty + "', "
                    + "id_department = " + newFaculty.idDepartment.getIdDepartment()
                    + " where id_Faculty = " + idFaculty + " ;";
            result = st.executeUpdate(s);
  
        } else {
            result = -1;
        }
        
        return result;
    }
    
    public int insertInto() throws SQLException{
        int result;
        Connection conn = database.DataBaseConnect.getConnection();
        Statement st = conn.createStatement();
        // Формируем запрос на проверку
        ResultSet rs = st.executeQuery("Select id_Faculty "
                + "from Faculty "
                + "where name_Faculty = '" + nameFaculty + "' "
                + "and id_department = " + idDepartment.getIdDepartment() + ";");
        if (!rs.next()) {
           // Значит в базе такого значения нет
            result = st.executeUpdate("insert into Faculty "
                + "(name_Faculty, id_Department) "
                + " values('" + nameFaculty + "', "+ idDepartment.getIdDepartment() +" );");
            conn.commit();
  
        } else {
            result = -1;
        }
        
        return result;
    }
}
