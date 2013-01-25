/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.odeku.pet.gui.dictionaries;

import com.toedter.calendar.JYearChooser;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import ua.edu.odeku.pet.database.entry.Faculty;
import ua.edu.odeku.pet.database.entry.Groups;
import ua.edu.odeku.pet.database.tableModal.GroupsTableModal;
import ua.edu.odeku.pet.gui.PetJInternalFrame;
import ua.edu.odeku.pet.util.SMS;

/**
 *
 * @author Aleo
 */
public class GroupsInternalFrame extends PetJInternalFrame {

    /**
     * Creates new form GroupsInternalFrame
     */
    public GroupsInternalFrame(){
        super();
        initComponents();
        updateTable();
    }
    
    private void updateTable() {
        try {
            AbstractTableModel modal = new GroupsTableModal();
            jTableGroups.setModel(modal);
            if (!ua.edu.odeku.pet.settings.ConfigureProgramm.isDEBAG()) {
                ua.edu.odeku.pet.util.TablesUtil.hideColumn(jTableGroups, 0);
                ua.edu.odeku.pet.util.TablesUtil.hideColumn(jTableGroups, 3);
            }
        } catch (SQLException ex) {
            SMS.error(this, ex.toString());
            Logger.getLogger(GroupsFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    private boolean addGroupsFrame(){
        boolean ret = false;
        try {
            // Создадим поле ввода
            JTextField num = new JTextField();
            // загрузим данные для комбобокса
            Faculty[] facultys = ua.edu.odeku.pet.database.data.GetDataTable.getFacultys();
            // создадим комбобокс
            JComboBox<Faculty> box = new JComboBox<Faculty>(facultys);
            // Создаем штуку для ввода времени
            JYearChooser year = new JYearChooser();
            Calendar calendar = Calendar.getInstance();
            // Получим текущий год
            int y = calendar.get(Calendar.YEAR);
            year.setStartYear(y - 10);
            year.setEndYear(y + 2);
            // Собираем компоненты
            JComponent[] component = new JComponent[]{
                new JLabel("Номер группы:"),
                num,
                new JLabel("Год поступления:"),
                year,
                new JLabel("Факультет:"),
                box
            };

            do {
                boolean flag = SMS.dialog(this, "Введите данные о группе:", component);
                if (!flag) {
                    break;
                }
                String number = num.getText();
                if (number.trim().length() <= 0) {
                    SMS.warning(this, "Введите номер группы!");
                    continue;
                }
                int numb;
                try {
                    numb = Integer.parseInt(number);
                } catch (NumberFormatException ex) {
                    SMS.message(this, "Номер группы должен быть числом\nили ввели слишком большое число!");
                    continue;
                }
                int inputYear = year.getYear();
                if (box.getSelectedItem() == null) {
                    SMS.message(this, "Укажите факультет!");
                    continue;
                }
                // Все проверки прошли
                // Собираем объект
                Groups groups = new Groups();
                groups.setNumGroup(numb);
                groups.setYearSupply(inputYear);
                groups.setFaculty((Faculty) box.getSelectedItem());

                int res = groups.insertInto();
                if (res == -1) {
                    if (SMS.query(this, "Такое значение уже есть,\nХотите другие данные ввести?")) {
                        continue;
                    } else {
                        break;
                    }
                } else if (res >= 0) {
                    ret = true;
                    break;
                }
            } while (true);
        } catch (SQLException ex) {
            SMS.error(this, ex.toString());
            Logger.getLogger(GroupsFrame.class.getName()).log(Level.SEVERE, null, ex);
            this.dispose();
        } finally {
            return ret;
        }
    }
    
    private boolean editGroupsFrame(){
        boolean ret = false;
        try {
            int row = jTableGroups.getSelectedRow();
            if (row == -1) {
                SMS.warning(this, "Выберите данные для редактирования!");
                return ret;
            }
            // Создадим поле ввода
            JTextField num = new JTextField();
            String oldNum = String.valueOf((Integer) jTableGroups.getValueAt(row, 1));
            // загрузим данные для комбобокса
            Faculty[] facultys = ua.edu.odeku.pet.database.data.GetDataTable.getFacultys();
            // создадим комбобокс
            JComboBox<Faculty> box = new JComboBox<Faculty>(facultys);
            Faculty f = new Faculty(
                    (int) jTableGroups.getValueAt(row, 3),
                    (String) jTableGroups.getValueAt(row, 4));
            // Создаем штуку для ввода времени
            JYearChooser year = new JYearChooser();
            Calendar calendar = Calendar.getInstance();
            // Получим текущий год
            int y = calendar.get(Calendar.YEAR);
            year.setStartYear(y - 10);
            year.setEndYear(y + 2);
            y = (int) jTableGroups.getValueAt(row, 2);
            // Собираем компоненты
            JComponent[] component = new JComponent[]{
                new JLabel("Номер группы:"),
                num,
                new JLabel("Год поступления:"),
                year,
                new JLabel("Факультет:"),
                box
            };
            // Соберем объект для обновления
            Groups oldGroups = new Groups();
            oldGroups.setIdGroups((int) jTableGroups.getValueAt(row, 0));
            oldGroups.setNumGroup(Integer.valueOf(oldNum));
            oldGroups.setYearSupply(y);
            oldGroups.setFaculty(f);
            do {
                num.setText(oldNum);
                year.setYear(y);
                box.setSelectedItem(f);

                boolean flag = SMS.dialog(this, "Измените данные о группе:", component);
                if (!flag) {
                    break;
                }
                String number = num.getText();
                if (number.trim().length() <= 0) {
                    SMS.warning(this, "Измените номер группы!");
                    continue;
                }
                int numb;
                try {
                    numb = Integer.parseInt(number);
                } catch (NumberFormatException ex) {
                    SMS.message(this, "Номер группы должен быть числом\nили ввели слишком большое число!");
                    continue;
                }
                int inputYear = year.getYear();
                if (box.getSelectedItem() == null) {
                    SMS.message(this, "Укажите факультет!");
                    continue;
                }
                // Все проверки прошли
                // Собираем объект
                Groups groups = new Groups();
                groups.setNumGroup(numb);
                groups.setYearSupply(inputYear);
                groups.setFaculty((Faculty) box.getSelectedItem());

                int res = oldGroups.updateTable(groups);
                if (res == -1) {
                    if (SMS.query(this, "Такое значение уже есть,\nХотите другие данные ввести?")) {
                        continue;
                    } else {
                        break;
                    }
                } else if (res >= 0) {
                    ret = true;
                    break;
                }
            } while (true);
        } catch (SQLException ex) {
            SMS.error(this, ex.toString());
            Logger.getLogger(GroupsFrame.class.getName()).log(Level.SEVERE, null, ex);
            this.dispose();
        } finally {
            return ret;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonAdd = new javax.swing.JButton();
        jButtonEdit = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableGroups = new javax.swing.JTable();

        setTitle("Справочник групп");

        jButtonAdd.setText("Добавить");
        jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddActionPerformed(evt);
            }
        });

        jButtonEdit.setText("Изменить");
        jButtonEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditActionPerformed(evt);
            }
        });

        jTableGroups.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTableGroups);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonEdit)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAdd)
                    .addComponent(jButtonEdit))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed
        if(this.addGroupsFrame()){
            updateTable();
        }
    }//GEN-LAST:event_jButtonAddActionPerformed

    private void jButtonEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditActionPerformed
        if(this.editGroupsFrame()){
            updateTable();
        }
    }//GEN-LAST:event_jButtonEditActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonEdit;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableGroups;
    // End of variables declaration//GEN-END:variables
}
