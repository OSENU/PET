/*
 * Класс описывает Таблицу которая описывает записи в базе данных с таблицей
 * Teacher
 */
package ua.edu.odeku.pet.database.tableModal;

import ua.edu.odeku.pet.database.DataBaseConnect;
import ua.edu.odeku.pet.database.entry.Faculty;
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
public class FacultyTableModal extends AbstractTableModel{
    private int countRow, countColumn;
    private ArrayList<Faculty> facultys;

    public FacultyTableModal() throws SQLException {
        Connection conn = DataBaseConnect.getConnection();
        Statement st;
        st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = st.executeQuery("select * from Faculty;");
        rs.last();
        countRow = rs.getRow();
        countColumn = rs.getMetaData().getColumnCount();
        rs.beforeFirst();
        
        int id;
        String name;
        facultys = new ArrayList<Faculty>(countRow);
        while(rs.next()){
            id = rs.getInt("id_Faculty");
            name = rs.getString("name_Faculty");
            facultys.add(new Faculty(id, name));
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
        switch (columnIndex){
            case(0) : 
                return facultys.get(rowIndex).getIdFaculty();
            default:
                return facultys.get(rowIndex).getNameFaculty();
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column){
            case(0) : 
                return "Код факультета";
            case(1) : 
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
            default:
                return String.class;
        }
    }
    
    
}
