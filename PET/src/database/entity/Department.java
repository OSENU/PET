/*
* Класс описывает сушность в базе данных таблицы Department
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
public class Department implements Serializable {
    private Integer idDepartment;
    private String nameDepartment;
   
    public Department() {
    }

    public Department(Integer idDepartment) {
        this.idDepartment = idDepartment;
    }

    public Department(Integer idDepartment, String nameDepartment) {
        this.idDepartment = idDepartment;
        this.nameDepartment = nameDepartment;
    }

    public Integer getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(Integer idDepartment) {
        this.idDepartment = idDepartment;
    }

    public String getNameDepartment() {
        return nameDepartment;
    }

    public void setNameDepartment(String nameDepartment) {
        this.nameDepartment = nameDepartment;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDepartment != null ? idDepartment.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Department)) {
            return false;
        }
        Department other = (Department) object;
        if ((this.idDepartment == null && other.idDepartment != null) || (this.idDepartment != null && !this.idDepartment.equals(other.idDepartment))) {
            return false;
        }
        if ((this.nameDepartment == null && other.nameDepartment != null) || (this.nameDepartment != null && !this.nameDepartment.equals(other.nameDepartment))) {
            return false;
        }
        
        return true;
    }

    @Override
    public String toString() {
        //return "database.entity.Department[ idDepartment=" + idDepartment + ", nameDepartment="+nameDepartment+" ]";
        return nameDepartment;
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
        ResultSet rs = st.executeQuery("Select id_department "
                + "from Department "
                + "where name_department = '" + nameDepartment + "';");
        if (!rs.next()) {
           // Значит в базе такого значения нет
            result = st.executeUpdate("insert into department"
                + "(name_department) "
                + " values('" + nameDepartment + "');");
            conn.commit();
  
        } else {
            result = -1;
        }
        
        return result;
    }
    
    /**
     * Метод заносит измененный объект в базу данных.
     * Тоесть данный элимент в базу занесеть переданый элимент
     * @param newDepartment - объект на который надо заменить
     * @return код результата: -1 - Такие данные уже есть
     *                         -2 - Произошла вообще ошибка
     *                         Или количество добавленых записей
     * @throws SQLException 
     */
    public int updateTable(Department newDepartment) throws SQLException{
        int result;
        Connection conn = database.DataBaseConnect.getConnection();
        Statement st = conn.createStatement();
        // Формируем запрос на проверку
        ResultSet rs = st.executeQuery("Select id_teacher "
                + "from Teacher "
                + "where name = '" + newDepartment.nameDepartment +"' " 
                + "and id_department <> " + idDepartment + " ;");
        if (!rs.next()) {
           // Значит в базе такого значения нет
            String s = "update department "
                    + "set name_department = '" + newDepartment.nameDepartment + "' "
                    + " where id_department = " + idDepartment + " ;";
            result = st.executeUpdate(s);
  
        } else {
            result = -1;
        }
        
        return result;
    }

    
    
}
