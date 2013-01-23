/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.dictionaries;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JYearChooser;
import database.entity.Faculty;
import database.entity.Groups;
import database.tableModal.GroupsTableModal;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.table.AbstractTableModel;
import util.SMS;

/**
 *
 * @author Aleo
 */
public class GroupsFrame extends javax.swing.JFrame {

    /**
     * Creates new form GroupsFrame
     */
    public GroupsFrame() {
        initComponents();
    }

    public void updateTable() {
        try {
            AbstractTableModel modal = new GroupsTableModal();
            jTableGroups.setModel(modal);
            if (!settings.ConfigureProgramm.isDEBAG()) {
                util.TablesUtil.hideColumn(jTableGroups, 0);
                util.TablesUtil.hideColumn(jTableGroups, 3);
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
            Faculty[] facultys = database.data.GetDataTable.getFacultys();
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
                    updateTable();
                    break;
                }
            } while (true);
        } catch (SQLException ex) {
            SMS.error(this, ex.toString());
            Logger.getLogger(GroupsFrame.class.getName()).log(Level.SEVERE, null, ex);
            this.dispose();
        } finally {
            GroupsFrame.this.setVisible(true);
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
            Faculty[] facultys = database.data.GetDataTable.getFacultys();
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
                    updateTable();
                    break;
                }
            } while (true);
        } catch (SQLException ex) {
            SMS.error(this, ex.toString());
            Logger.getLogger(GroupsFrame.class.getName()).log(Level.SEVERE, null, ex);
            this.dispose();
        } finally {
            GroupsFrame.this.setVisible(true);
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

        jCheckBoxMenuItem2 = new javax.swing.JCheckBoxMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableGroups = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuWindow = new javax.swing.JMenu();
        jCheckBoxMenuAlwaysOnTop = new javax.swing.JCheckBoxMenuItem();
        jMenuItemClose = new javax.swing.JMenuItem();
        jMenuAdd = new javax.swing.JMenu();
        jMenuEdit = new javax.swing.JMenu();

        jCheckBoxMenuItem2.setSelected(true);
        jCheckBoxMenuItem2.setText("jCheckBoxMenuItem2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Группы");
        setType(java.awt.Window.Type.POPUP);

        jScrollPane1.setBorder(null);

        jTableGroups.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Загрузка"
            }
        ));
        jScrollPane1.setViewportView(jTableGroups);

        jMenuWindow.setText("Окно");

        jCheckBoxMenuAlwaysOnTop.setText("Поверх всех окон");
        jCheckBoxMenuAlwaysOnTop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuAlwaysOnTopActionPerformed(evt);
            }
        });
        jMenuWindow.add(jCheckBoxMenuAlwaysOnTop);

        jMenuItemClose.setText("Закрыть окно");
        jMenuItemClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCloseActionPerformed(evt);
            }
        });
        jMenuWindow.add(jMenuItemClose);

        jMenuBar1.add(jMenuWindow);

        jMenuAdd.setText("Добавить");
        jMenuAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuAddMouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenuAdd);

        jMenuEdit.setText("Изменить");
        jMenuEdit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuEditMouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenuEdit);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuAddMouseClicked
        this.addGroupsFrame();
    }//GEN-LAST:event_jMenuAddMouseClicked

    private void jMenuEditMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuEditMouseClicked
        this.editGroupsFrame();
    }//GEN-LAST:event_jMenuEditMouseClicked

    private void jCheckBoxMenuAlwaysOnTopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuAlwaysOnTopActionPerformed
        this.setAlwaysOnTop(this.jCheckBoxMenuAlwaysOnTop.isSelected());
    }//GEN-LAST:event_jCheckBoxMenuAlwaysOnTopActionPerformed

    private void jMenuItemCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCloseActionPerformed
        this.dispose();
    }//GEN-LAST:event_jMenuItemCloseActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuAlwaysOnTop;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem2;
    private javax.swing.JMenu jMenuAdd;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuEdit;
    private javax.swing.JMenuItem jMenuItemClose;
    private javax.swing.JMenu jMenuWindow;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableGroups;
    // End of variables declaration//GEN-END:variables
}
