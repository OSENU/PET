/*
 * Класс описываем модель теблицы Группы
 */
package database.tableModal;

import database.entity.Faculty;
import database.entity.Groups;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Aleo
 */
public class GroupsTableModal extends AbstractTableModel{
    
    private int rowCount, columnCount;
    private ArrayList<Groups> groups;
    
    public GroupsTableModal() throws SQLException {
        Connection conn = database.DataBaseConnect.getConnection();
        Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
                ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = st.executeQuery(
           "select g.ID_GROUPS, g.NUM_GROUP, g.YEAR_SUPPLY, g.ID_FACULTY, f.NAME_FACULTY\n" +
           "from GROUPS g inner join FACULTY f on g.ID_FACULTY = f.ID_FACULTY");
        
        rs.last();
        rowCount = rs.getRow();
        rs.beforeFirst();
        columnCount = rs.getMetaData().getColumnCount();
        groups = new ArrayList<Groups>(rowCount);
        Groups g;
        Faculty f;
        while(rs.next()){
            g = new Groups();
            g.setIdGroups((Integer) rs.getInt("id_groups"));
            g.setNumGroup((Integer) rs.getInt("num_group"));
            g.setYearSupply((Date) rs.getDate("year_supply"));
            f = new Faculty();
            f.setIdFaculty((Integer) rs.getInt("id_faculty"));
            f.setNameFaculty((String) rs.getString("name_faculty"));
            g.setFaculty(f);
            groups.add(g);
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
        return columnCount;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return groups.get(rowIndex).getIdGroups();
            case 1:
                return groups.get(rowIndex).getNumGroup();
            case 2:
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(groups.get(rowIndex).getYearSupply());
                return calendar.get(Calendar.YEAR);
            case 3:
                return groups.get(rowIndex).getFaculty().getIdFaculty();
            case 4:
                return groups.get(rowIndex).getFaculty().getNameFaculty();
            default:
                return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
         switch (columnIndex) {
            case 0:
                return Integer.class;
            case 1:
                return Integer.class;
            case 2:
                return Integer.class;
            case 3:
                return Integer.class;
            case 4:
                return String.class;
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Код группы";
            case 1:
                return "Номер группы";
            case 2:
                return "Год поступления";
            case 3:
                return "Код факультета";
            case 4:
                return "Факультет";
            default:
                return null;
        }
    }
    
    
}
