/*
 * Класс используеться для того что бы выбрать данные из определеных таблиц
 * поторые будет очень часто использоваться
 */
package ua.edu.odeku.pet.database.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import ua.edu.odeku.pet.database.ConnectionDataBase;
import ua.edu.odeku.pet.database.entry.Department;
import ua.edu.odeku.pet.database.entry.Faculty;
import ua.edu.odeku.pet.database.entry.Groups;
import ua.edu.odeku.pet.database.entry.Subject;
import ua.edu.odeku.pet.database.entry.Teacher;
import ua.edu.odeku.pet.database.entry.TypeWork;

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
        Connection conn = ConnectionDataBase.getConnection();
        Statement st;
        st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = st.executeQuery(
                "select d.ID_DEPARTMENT, "
                + "d.NAME_DEPARTMENT, "
                + "d.ID_FACULTY, "
                + "f.NAME_FACULTY "
                + "from Department d inner join Faculty f "
                + "on d.id_faculty = f.id_faculty;");
        rs.last();
        int countRow = rs.getRow();
        rs.beforeFirst();
        
        int id;
        String name;
        
        Department[] departments = new Department[countRow+1];
        for(int i = 1;rs.next(); i++){
            id = rs.getInt("ID_DEPARTMENT");
            name = rs.getString("name_department");
            Faculty faculty = new Faculty(rs.getInt("id_faculty"), 
                    rs.getString("name_faculty"));
            departments[i] = new Department(id, name, faculty);
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
        Connection conn = ConnectionDataBase.getConnection();
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
        for(int i = 1; rs.next(); i++){
            id = rs.getInt("id_teacher"); // 1
            name = rs.getString("name"); // 2
            name2 = rs.getString("name2"); // 3
            surname = rs.getString("surname"); // 4
            idDepartment = rs.getInt("id_department"); // 5
            nameDepartment = rs.getString(6); // 6
            
            teacher = new Teacher(id, name, name2, surname);
            teacher.setIdDepartment(new Department(idDepartment, nameDepartment));
            
            teachers[i] = teacher;
        }
        
        rs.close();
        st.close();
        
        return teachers;
    }
    
    public static Faculty[] getFacultys() throws SQLException{
        Connection conn = ConnectionDataBase.getConnection();
        Statement st;
        st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = st.executeQuery(
                "select *" +
                "from Faculty;"
                );
        // Переходим на последнюю строчку что бы узнать количество записей
        rs.last();
        int countRow = rs.getRow();
        // Перешли снова на первую запись
        rs.beforeFirst();
        int id;
        String name;
        Faculty[] facultys = new Faculty[countRow+1];

        for(int i = 1; rs.next(); i++){
            id = rs.getInt("id_faculty");
            name = rs.getString("name_faculty");
            
            facultys[i] = new Faculty(id, name);
        }
        
        rs.close();
        st.close();
        
        return facultys;
    }
    
    public static Subject[] getSubjects() throws SQLException{
        Connection conn = ConnectionDataBase.getConnection();
        Statement st;
        st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = st.executeQuery(
                "select *" +
                "from Subject;"
                );
        // Переходим на последнюю строчку что бы узнать количество записей
        rs.last();
        int countRow = rs.getRow();
        // Перешли снова на первую запись
        rs.beforeFirst();
        int id;
        String name;
        Subject[] subjects = new Subject[countRow+1];

        for(int i = 1; rs.next(); i++){
            id = rs.getInt("id_subject");
            name = rs.getString("name_subject");
            
            subjects[i] = new Subject(id, name);
        }
        
        rs.close();
        st.close();
        
        return subjects;
    }
    
    public static TypeWork[] getTypeWork() throws SQLException{
        Connection conn = ConnectionDataBase.getConnection();
        Statement st;
        st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = st.executeQuery(
                "select *" +
                "from Type_work;"
                );
        // Переходим на последнюю строчку что бы узнать количество записей
        rs.last();
        int countRow = rs.getRow();
        // Перешли снова на первую запись
        rs.beforeFirst();
        int id;
        String name;
        TypeWork[] typeWorks = new TypeWork[countRow+1];

        for(int i = 1; rs.next(); i++){
            id = rs.getInt("id_type_work");
            name = rs.getString("name_type_work");
            
            typeWorks[i] = new TypeWork(id, name);
        }
        
        rs.close();
        st.close();
        
        return typeWorks;
    }
    
    public static Groups[] getGroupses() throws SQLException{
        Connection conn = ConnectionDataBase.getConnection();
        Statement st;
        st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = st.executeQuery(
                "SELECT "
                + "G.ID_GROUPS, "
                + "G.YEAR_SUPPLY, "
                + "G.NUM_GROUP, "
                + "F.ID_fACULTY, "
                + "F.NAME_FACULTY "
                + "FROM GROUPS G INNER JOIN FACULTY F "
                + "ON F.ID_FACULTY = G.ID_FACULTY "
                );
        // Переходим на последнюю строчку что бы узнать количество записей
        rs.last();
        int countRow = rs.getRow();
        // Перешли снова на первую запись
        rs.beforeFirst();
        int id, year, idF, num;
        String name;
        Groups[] groupses = new Groups[countRow+1];

        for(int i = 1; rs.next(); i++){
            id = rs.getInt(1);
            year = rs.getInt(2);
            num = rs.getInt(3);
            idF = rs.getInt(4);
            name = rs.getString(5);
            
            groupses[i] = new Groups(id);
            groupses[i].setYearSupply(year);
            groupses[i].setNumGroup(num);
            groupses[i].setFaculty(new Faculty(idF, name));
        }
        
        rs.close();
        st.close();
        
        return groupses;
    }
    
}
