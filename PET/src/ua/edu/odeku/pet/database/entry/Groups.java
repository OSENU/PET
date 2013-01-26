/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.odeku.pet.database.entry;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 *
 * @author Aleo
 */
public class Groups implements Serializable, EntryDataBase {
    private Integer idGroups;
    private Integer yearSupply;
    private Integer numGroup;
    private Faculty faculty;

    public Groups() {
    }

    public Groups(Integer idGroups) {
        this.idGroups = idGroups;
    }

    public Groups(Integer idGroups, int numGroup) {
        this.idGroups = idGroups;
        this.numGroup = numGroup;
    }

    public Integer getIdGroups() {
        return idGroups;
    }

    public void setIdGroups(Integer idGroups) {
        this.idGroups = idGroups;
    }

    public Integer getYearSupply() {
        return yearSupply;
    }

    public void setYearSupply(Integer yearSupply) {
        this.yearSupply = yearSupply;
    }

    public int getNumGroup() {
        return numGroup;
    }

    public void setNumGroup(int numGroup) {
        this.numGroup = numGroup;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGroups != null ? idGroups.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Groups)) {
            return false;
        }
        Groups other = (Groups) object;
        if ((this.idGroups == null && other.idGroups != null) || (this.idGroups != null && !this.idGroups.equals(other.idGroups))) {
            return false;
        }
        if ((this.numGroup == null && other.numGroup != null)){
            return false;
        }
        if((this.yearSupply == null && other.yearSupply != null)){
            return false;
        }
        if((this.numGroup != null && !(this.numGroup.equals(other.numGroup))) && (this.yearSupply != null && !(this.yearSupply.equals(other.yearSupply)))){
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return numGroup + " : " + yearSupply + " : " + faculty.getNameFaculty();
    }

    /**
     * @return the faculty
     */
    public Faculty getFaculty() {
        return faculty;
    }

    /**
     * @param faculty the faculty to set
     */
    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
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
        Connection conn = ua.edu.odeku.pet.database.ConnectionDataBase.getConnection();
        Statement st = conn.createStatement();
        // Формируем запрос на проверку
        ResultSet rs = st.executeQuery("Select id_groups "
                + "from Groups "
                + "where num_Group = '" + numGroup +"' "
                + "and year_Supply = " + yearSupply + " "
                + "and id_Faculty = " + faculty.getIdFaculty() + " ; ") ;
        Locale.setDefault(Locale.US);
        if (!rs.next()) {
           // Значит в базе такого значения нет
            SimpleDateFormat sd = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
            result = st.executeUpdate("insert into Groups"
                + "(num_group, year_supply, id_faculty) "
                + " values(" + numGroup + ", " + yearSupply + ", "
                    + " " + faculty.getIdFaculty() + ");");
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
    @Override
    public int updateTable(Object object) throws SQLException{
        int result;
        if (!(object instanceof Groups)){
            return -2;
        }
        Groups newGroups = (Groups) object;
        Connection conn = ua.edu.odeku.pet.database.ConnectionDataBase.getConnection();
        Statement st = conn.createStatement();
        // Формируем запрос на проверку
        ResultSet rs = st.executeQuery("Select id_groups "
                + "from Groups "
                + "where num_Group = '" + newGroups.numGroup +"' "
                + "and year_Supply = " + newGroups.yearSupply + " "
                + "and id_Faculty = " + faculty.getIdFaculty() + " "
                + "and id_groups <> " + idGroups + "; ") ;
        if (!rs.next()) {
           // Значит в базе такого значения нет
            String s = "update groups "
                    + "set num_group = '" + newGroups.numGroup + "', "
                    + "year_supply = " + newGroups.yearSupply + ", "
                    + "id_faculty = " + newGroups.faculty.getIdFaculty()
                    + " where id_groups = " + idGroups + " ;";
            result = st.executeUpdate(s);
            conn.commit();
        } else {
            result = -1;
        }
        return result;
    }

    @Override
    public Integer getIdFromDataBase() throws SQLException {
        Integer id = null;
        if(numGroup == null){
            return id;
        } else if(yearSupply == null){
            return id;
        } else if (faculty == null || faculty.getIdFaculty() == null){
            return id;
        }
        Statement statement = ua.edu.odeku.pet.database.ConnectionDataBase.getStatement();
        ResultSet resultSet = statement.executeQuery("Select id_groups from groups where "
                + "num_group =" + numGroup + " "
                + "and id_faculty = " + faculty.getIdFaculty() + " "
                + "and year_supply = " + yearSupply + " ;");
        if(resultSet.next()){
            id  = resultSet.getInt(1);
        }
        return id;
    }
}
