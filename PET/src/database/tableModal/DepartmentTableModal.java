package database.tableModal;

import database.DataBaseConnect;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import database.entity.Department;

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
        ResultSet rs = st.executeQuery("select * from Department;");
        rs.last();
        countRow = rs.getRow();
        countColumn = rs.getMetaData().getColumnCount();
        rs.beforeFirst();
        
        int id;
        String name;
        departments = new ArrayList<Department>();
        while(rs.next()){
            id = rs.getInt("id_department");
            name = rs.getString("name_department");
            departments.add(new Department(id, name));
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
            default:
                return departments.get(rowIndex).getNameDepartment();
            
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column){
            case(0) : 
                return "Код кафедры";
            default:
                return "Название кафедры";
            
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex){
            case(0) :
                return Integer.class;                
            default:
                return String.class;
        }
    }
    
    
    
}
