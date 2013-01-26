/*
 * Класс описывает Таблицу которая описывает записи в базе данных с таблицей
 * Type_Work
 */
package ua.edu.odeku.pet.database.tableModal;

import ua.edu.odeku.pet.database.entry.TypeWork;
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
public class TypeWorkTableModal extends AbstractTableModel{
    
    int rowCount, columntCount;
    ArrayList<TypeWork> works;
    
    public TypeWorkTableModal() throws SQLException {
        Connection conn = ua.edu.odeku.pet.database.ConnectionDataBase.getConnection();
        Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = st.executeQuery("Select * from type_work;");
        rs.last();
        rowCount = rs.getRow();
        columntCount = rs.getMetaData().getColumnCount();
        rs.beforeFirst();
        works = new ArrayList<TypeWork>(rowCount);
        TypeWork work;
        while(rs.next()){
            work = new TypeWork(
                    rs.getInt("id_type_work"),
                    rs.getString("name_type_work")
                    );
            works.add(work);
        }
        rs.close();
        st.close();
        
    }

    
    
    @Override
    public int getRowCount() {
        return rowCount;
    }

    @Override
    public int getColumnCount() {
        return columntCount;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex){
            case 0 : 
                return works.get(rowIndex).getIdTypeWork();
            case 1 : 
                return works.get(rowIndex).getNameTypeWork();
            default:
                return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex){
            case 0:
                return Integer.class;
            case 1 :
                return String.class;
            default:
                return String.class;
        }
    }

    public ArrayList<TypeWork> getWorks() {
        return works;
    }

    @Override
    public String getColumnName(int column) {
        switch (column){
            case 0:
                return "Код типа работы";
            case 1:
                return "Тип работы";
            default: 
                return "NULL";
        }
    }
    
    
    
    
}
