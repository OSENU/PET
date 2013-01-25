package ua.edu.odeku.pet.database.tableModal;

import ua.edu.odeku.pet.database.DataBaseConnect;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import ua.edu.odeku.pet.database.entry.Department;
import ua.edu.odeku.pet.database.entry.Faculty;

/**
 * Класса описывает модель таблицы для окна DepartmentFrame
 * @author Aleo
 */
public class DepartmentTableModal extends AbstractTableModel {
    private int countRow, countColumn;
    private ArrayList<Department> departments;
    public DepartmentTableModal() throws SQLException{
        Connection conn = DataBaseConnect.getConnection();
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
        countRow = rs.getRow();
        countColumn = rs.getMetaData().getColumnCount();
        rs.beforeFirst();
        
        int id;
        String name;
        departments = new ArrayList<Department>();
        while(rs.next()){
            id = rs.getInt("ID_DEPARTMENT");
            name = rs.getString("name_department");
            Faculty faculty = new Faculty(rs.getInt("id_faculty"), 
                    rs.getString("name_faculty"));
            departments.add(new Department(id, name, faculty));
        }
        
        rs.close();
        st.close();
//      DataBaseConnect.destroyConnection();
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
        switch (columnIndex){
            case(0) : 
                return departments.get(rowIndex).getIdDepartment();
            case(1) : 
                return departments.get(rowIndex).getNameDepartment();
            case(2) : 
                return departments.get(rowIndex).getIdFaculty().getIdFaculty();
            case(3) : 
                return departments.get(rowIndex).getIdFaculty().getNameFaculty();
            default:
                return null;
            
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column){
            case(0) : 
                return "Код кафедры";
            case(1) : 
                return "Название кафедры";
            case(2) : 
                return "Код факультета";
            case(3) : 
                return "Название факультета";
            default:
                return "NULL";
            
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex){
            case(0) :
                return Integer.class;                
            case(1) :
                return String.class;
            case(2) :
                return Integer.class;                
            case(3) :
                return String.class;
            default:
                return String.class;
        }
    }
    
    
    
}
