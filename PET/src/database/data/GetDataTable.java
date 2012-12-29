/*
 * Класс используеться для того что бы выбрать данные из определеных таблиц
 * поторые будет очень часто использоваться
 */
package database.data;

import database.DataBaseConnect;
import database.entity.Department;
import database.entity.Teacher;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Aleo
 */
public class GetDataTable {

    private GetDataTable() {
    }
    
    /**
     * Метод вернет массив объектов данной таблицы
     * [0] = null
     * @return
     * @throws SQLException 
     */
    public static Department[] getDepartments() throws SQLException{
        Connection conn = DataBaseConnect.getConnection();
        Statement st;
        st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = st.executeQuery("select * from Department;");
        rs.last();
        int countRow = rs.getRow();
        int countColumn = rs.getMetaData().getColumnCount();
        rs.beforeFirst();
        
        int id;
        String name;
        
        Department[] departments = new Department[countRow+1];
        for(int i = 1;rs.next(); i++){
            id = rs.getInt("id_department");
            name = rs.getString("name_department");
            departments[i] = new Department(id, name);
        }
        
        rs.close();
        st.close();
        return departments;
    }
    
   /**
     * Метод вернет массив объектов данной таблицы
     * [0] = null
     * @return
     * @throws SQLException 
     */
    public static Teacher[] getTeachers() throws SQLException{
        Connection conn = DataBaseConnect.getConnection();
        Statement st;
        st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = st.executeQuery(
                "select t.id_teacher, t.name, t.NAME2, t.SURNAME, t.ID_DEPARTMENT, d.NAME_DEPARTMENT \n" +
                "from Teacher t inner join Department d on t.id_department = d.id_department;"
                );
        // Переходим на последнюю строчку что бы узнать количество записей
        rs.last();
        int countRow = rs.getRow();
        int countColumn = rs.getMetaData().getColumnCount();
        // Перешли снова на первую запись
        rs.beforeFirst();
        int id, idDepartment;
        String name, name2, surname, nameDepartment;
        Teacher[] teachers = new Teacher[countRow+1];
        
        Teacher teacher;
        for(int i = 0; rs.next(); i++){
            id = rs.getInt("id_teacher");
            name = rs.getString("name");
            name2 = rs.getString("name2");
            surname = rs.getString("surname");
            idDepartment = rs.getInt("id_department");
            nameDepartment = rs.getString("name_deparmnet");
            
            teacher = new Teacher(idDepartment, name, name2, surname);
            teacher.setIdDepartment(new Department(idDepartment, nameDepartment));
            
            teachers[i] = teacher;
        }
        
        rs.close();
        st.close();
        
        return teachers;
    }
}
