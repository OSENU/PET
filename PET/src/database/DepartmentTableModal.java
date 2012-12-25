package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 * Класса описывает модель таблицы для окна DepartmentFrame
 * @author Aleo
 */
public class DepartmentTableModal extends AbstractTableModel {
    private int countRow;
    private ArrayList<Department> departments;
    public DepartmentTableModal() throws SQLException{
        Connection conn = DataBaseConnect.getConnection();
        Statement st;
        st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = st.executeQuery("select * from Department;");
        rs.last();
        countRow = rs.getRow();

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
        // Возврат количество столбцов которые будут в таблице
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex){
            case(0) : 
                // Возможно надо добавить поддержку отладки
                return (departments.get(rowIndex).id);
            case(1) : 
                return departments.get(rowIndex).name;
            default:
                return 0;
            
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column){
            case(0) : 
                return "Код кафедры";
            case(1) : 
                return "Название кафедры";
            default:
                return "";
            
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
