/*
 * Класс описывает Таблицу которая описывает записи в базе данных с таблицей
 * Teacher
 */
package ua.edu.odeku.pet.database.tableModal;

import ua.edu.odeku.pet.database.DataBaseConnect;
import ua.edu.odeku.pet.database.entry.Department;
import ua.edu.odeku.pet.database.entry.Teacher;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Aleo
 */
public class TeacherTableModal extends AbstractTableModel{
    private int countRow, countColumn;
    private ArrayList<Teacher> teachers;

    public TeacherTableModal() throws SQLException {
        
        Connection conn = DataBaseConnect.getConnection();
        Statement st;
        st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = st.executeQuery(
                "select t.id_teacher, t.name, t.NAME2, t.SURNAME, t.ID_DEPARTMENT, d.NAME_DEPARTMENT \n" +
                "from Teacher t inner join Department d on t.id_department = d.id_department;"
                );
        // Переходим на последнюю строчку что бы узнать количество записей
        rs.last();
        countRow = rs.getRow();
        countColumn = rs.getMetaData().getColumnCount();
        // Перешли снова на первую запись
        rs.beforeFirst();
        int id, idDepartment;
        String name, name2, surname, nameDepartment;
        teachers = new ArrayList<Teacher>(countRow);
        Teacher teacher;
        while(rs.next()){
            id = rs.getInt("id_teacher");
            name = rs.getString("name");
            name2 = rs.getString("name2");
            surname = rs.getString("surname");
            idDepartment = rs.getInt("id_department");
            nameDepartment = rs.getString("name_department");
            
            teacher = new Teacher(id, name, name2, surname);
            teacher.setIdDepartment(new Department(idDepartment, nameDepartment));
            
            teachers.add(teacher);
        }
        
        rs.close();
        st.close();
    }
    
    
    
    @Override
    public int getRowCount() {
        return countRow;
    }

    @Override
    public int getColumnCount() {
        return countColumn;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0: 
               return teachers.get(rowIndex).getIdTeacher(); 
            case 1: 
               return teachers.get(rowIndex).getName();
            case 2: 
               return teachers.get(rowIndex).getName2();
            case 3:
                return teachers.get(rowIndex).getSurname();
            case 4:
                return teachers.get(rowIndex).getIdDepartment().getIdDepartment();
            case 5:
                return teachers.get(rowIndex).getIdDepartment().getNameDepartment();
            default:
                return null;
        }
        
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0 :
                return Integer.class;
            case 1 :
                return String.class;
            case 2 :
                return String.class;
            case 3 :
                return String.class;
            case 4 :
                return Integer.class;
            case 5 :
                return String.class;
            default:
                return Object.class;
        }
    }

    @Override
    public String getColumnName(int column) {
                switch (column) {
            case 0 :
                return "Код преподавателя";
            case 1 :
                return "Имя";
            case 2 :
                return "Отчество";
            case 3 :
                return "Фамилия";
            case 4 :
                return "Код факультета";
            case 5 :
                return "Название факультета";
            default:
                return "NULL";
        }
    }
    
    
    
}
