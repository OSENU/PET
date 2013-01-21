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
public class Faculty implements Serializable, EntryDataBase {
    private Integer idFaculty;
    private String nameFaculty;

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
        //return "database.entity.Faculty[ idFaculty=" + idFaculty + " ]";
        return nameFaculty;
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
    @Override
    public int updateTable(Object object) throws SQLException{
        int result;
        if(!(object instanceof Faculty)){
            return -2;
        }
        Faculty newFaculty = (Faculty) object;
        Connection conn = database.DataBaseConnect.getConnection();
        Statement st = conn.createStatement();
        // Формируем запрос на проверку
        ResultSet rs = st.executeQuery("Select id_Faculty "
                + "from Faculty "
                + "where name_Faculty = '" + newFaculty.nameFaculty +"' "
                + "and id_Faculty <> " + idFaculty + " ;");
        if (!rs.next()) {
           // Значит в базе такого значения нет
            String s = "update Faculty "
                    + "set name_Faculty = '" + newFaculty.nameFaculty + "' "
                    + " where id_Faculty = " + idFaculty + " ;";
            result = st.executeUpdate(s);
            conn.commit();
        } else {
            result = -1;
        }
        
        return result;
    }
    
    @Override
    public int insertInto() throws SQLException{
        int result;
        Connection conn = database.DataBaseConnect.getConnection();
        Statement st = conn.createStatement();
        // Формируем запрос на проверку
        ResultSet rs = st.executeQuery("Select id_Faculty "
                + "from Faculty "
                + "where name_Faculty = '" + nameFaculty + "' "
                + ";");
        if (!rs.next()) {
           // Значит в базе такого значения нет
            result = st.executeUpdate("insert into Faculty "
                + "(name_Faculty) "
                + " values('" + nameFaculty + "' );");
            conn.commit();
  
        } else {
            result = -1;
        }
        
        return result;
    }

    @Override
    public Integer getIdFromDataBase() throws SQLException {
        if(nameFaculty == null || nameFaculty.isEmpty()){
            return null;
        }
        Statement statement = database.DataBaseConnect.getStatement();
        ResultSet resultSet = statement.executeQuery("Select id_faculty from Faculty "
                + "where name_faculty='"+nameFaculty+"';");
        Integer id = null;
        if(resultSet.next()){
           id = resultSet.getInt(1);
        } 
        return id;
    }
}
