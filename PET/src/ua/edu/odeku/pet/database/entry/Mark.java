/*
 * Класс описывает сушность в базе данных таблицы Mark
 */
package ua.edu.odeku.pet.database.entry;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Aleo
 */
public class Mark implements Serializable, EntryDataBase {
    private Integer idMark = 0;
    private String nameMark;
    private Integer minPersent = 0;
    private Integer maxPersent = 0;

    public Mark() {
    }

    public Mark(Integer idMark) {
        this.idMark = idMark;
    }

    public Mark(Integer idMark, String nameMark) {
        this.idMark = idMark;
        this.nameMark = nameMark;
    }

    public Integer getIdMark() {
        return idMark;
    }

    public void setIdMark(Integer idMark) {
        this.idMark = idMark;
    }

    public String getNameMark() {
        return nameMark;
    }

    public void setNameMark(String nameMark) {
        this.nameMark = nameMark;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMark != null ? idMark.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mark)) {
            return false;
        }
        Mark other = (Mark) object;
        if ((this.idMark == null && other.idMark != null) || (this.idMark != null && !this.idMark.equals(other.idMark))) {
            return false;
        }
        if ((this.nameMark == null && other.nameMark != null) || (this.nameMark != null && !this.nameMark.equals(other.nameMark))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        //return "database.entity.Mark[ idMark=" + idMark + ", nameMark="+ nameMark +" ]";
        return nameMark;
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
        Connection conn = ua.edu.odeku.pet.database.DataBaseConnect.getConnection();
        Statement st = conn.createStatement();
        // Формируем запрос на проверку
        ResultSet rs = st.executeQuery("Select id_mark "
                + "from Mark "
                + "where name_mark = '" + nameMark + "';");
        if (!rs.next()) {
           // Значит в базе такого значения нет
            result = st.executeUpdate("insert into mark"
                + "(name_mark, min_persent, max_persent) "
                + " values('" + nameMark + "', "+ minPersent +", "
                + " " + maxPersent + ");");
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
        if(!(object instanceof Mark)){
            return -2;
        }
        Mark newMark = (Mark) object;
        Connection conn = ua.edu.odeku.pet.database.DataBaseConnect.getConnection();
        Statement st = conn.createStatement();
        // Формируем запрос на проверку
        ResultSet rs = st.executeQuery("Select id_mark "
                + "from Mark "
                + "where name_mark = '" + newMark.nameMark +"' " 
                + "and id_Mark <> " + idMark + " ;");
        if (!rs.next()) {
           // Значит в базе такого значения нет
            String s = "update Mark "
                    + "set name_mark = '" + newMark.nameMark + "', "
                    + "min_persent = " + newMark.minPersent + ", "
                    + "max_persent = " + newMark.maxPersent + " "
                    + " where id_Mark = " + idMark + " ;";
            result = st.executeUpdate(s);
            conn.commit();
        } else {
            result = -1;
        }
        
        return result;
    }

    /**
     * @return the minPersent
     */
    public Integer getMinPersent() {
        return minPersent;
    }

    /**
     * @param minPersent the minPersent to set
     */
    public void setMinPersent(Integer minPersent) {
        this.minPersent = minPersent;
    }

    /**
     * @return the maxPersent
     */
    public Integer getMaxPersent() {
        return maxPersent;
    }

    /**
     * @param maxPersent the maxPersent to set
     */
    public void setMaxPersent(Integer maxPersent) {
        this.maxPersent = maxPersent;
    }

    @Override
    public Integer getIdFromDataBase() throws SQLException {
        if (nameMark == null || nameMark.isEmpty()) {
            return null;
        }
        Statement statement = ua.edu.odeku.pet.database.DataBaseConnect.getStatement();
        ResultSet resultSet = statement.executeQuery("Select id_mark from Mark "
                + "where name_markt='" + nameMark + "';");
        Integer id = null;
        if (resultSet.next()) {
            id = resultSet.getInt(1);
        }
        return id;
    }

}
