/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.odeku.pet.database.tableModal;

import ua.edu.odeku.pet.database.DataBaseConnect;
import ua.edu.odeku.pet.database.entry.Faculty;
import ua.edu.odeku.pet.database.entry.Subject;
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
public class SubjectTableModal extends AbstractTableModel{
    private int countRow, countColumn;
    private ArrayList<Subject> subjects;
    
    public SubjectTableModal() throws SQLException {
        Connection conn = DataBaseConnect.getConnection();
        Statement st;
        st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = st.executeQuery("select * from Subject;");
        rs.last();
        countRow = rs.getRow();
        countColumn = rs.getMetaData().getColumnCount();
        rs.beforeFirst();
        
        int id;
        String name;
        subjects = new ArrayList<Subject>(countRow);
        while(rs.next()){
            id = rs.getInt("id_Subject");
            name = rs.getString("name_Subject");
            subjects.add(new Subject(id, name));
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
            case 0: 
                return subjects.get(rowIndex).getIdSubject();
            case 1:
                return subjects.get(rowIndex).getNameSubject();
            default:
                return null;
        }
        
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex){
            case 0 : 
                return Integer.class;
            case 1 :
                return String.class;
            default: 
                return null;
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex){
            case 0 : 
                return "Код предмета";
            case 1 :
                return "Название предмета";
            default: 
                return null;
        }
    }
    
    
    
}
