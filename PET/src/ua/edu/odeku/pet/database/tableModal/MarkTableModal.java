/*
 * Модель для таблицы с оценками
 */
package ua.edu.odeku.pet.database.tableModal;

import ua.edu.odeku.pet.database.entry.Mark;
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
public class MarkTableModal extends AbstractTableModel {
    private int countRow, countColumn;
    private ArrayList<Mark> marks;
    
    public MarkTableModal() throws SQLException {
        Connection conn = ua.edu.odeku.pet.database.DataBaseConnect.getConnection();
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
        
        int id, min, max;
        String name;
        marks = new ArrayList<Mark>(countRow);
        while(rs.next()){
            id = rs.getInt("ID_mark");
            name = rs.getString("name_mark ");
            min = rs.getInt("min_persent");
            max = rs.getInt("max_persent");
            Mark mark = new Mark(id, name);
            mark.setMinPersent(min);
            mark.setMaxPersent(max);
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
        switch(columnIndex){
            case 0 :
                return marks.get(rowIndex).getIdMark();
            case 1 :
                return marks.get(rowIndex).getNameMark();
            case 2 : 
                return marks.get(rowIndex).getMinPersent();
            case 3 :
                return marks.get(rowIndex).getMaxPersent();
            default:
                return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch(columnIndex){
            case 0 :
                return Integer.class;
            case 1 :
                return String.class;
            case 2 : 
                return Integer.class;
            case 3 :
                return Integer.class;
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch(columnIndex){
            case 0 :
                return "Код оценки";
            case 1 :
                return "Название оценки";
            case 2 : 
                return "Минимальный порог оценки";
            case 3 :
                return "Максимальный порог оценки";
            default:
                return null;
        }
    }
    
    
    
    
}
