/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.odeku.pet.gui.dictionaries;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import ua.edu.odeku.pet.database.data.GetDataTable;
import ua.edu.odeku.pet.database.entry.Department;
import ua.edu.odeku.pet.database.entry.Teacher;
import ua.edu.odeku.pet.database.tableModal.TeacherTableModal;
import ua.edu.odeku.pet.gui.PetJInternalFrame;
import ua.edu.odeku.pet.settings.ConfigureProgramm;
import ua.edu.odeku.pet.util.SMS;

/**
 *
 * @author Aleo
 */
public class TeacherInternalFrame extends PetJInternalFrame {

    /**
     * Creates new form TeacherInternalFrame
     */
    public TeacherInternalFrame() {
        initComponents();
    }

    private void updateTableTeacher() {

        try {
            TeacherTableModal modal = new TeacherTableModal();
            jTableTeacher.setModel(modal);
        } catch (SQLException ex) {
            SMS.error(this, ex.toString());
            Logger.getLogger(TeacherInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (!ConfigureProgramm.isDEBAG()) {
            // Если программа не врежиме отладки, то скроем колонку с id
            ua.edu.odeku.pet.util.TablesUtil.hideColumn(jTableTeacher, 0);
            ua.edu.odeku.pet.util.TablesUtil.hideColumn(jTableTeacher, 4);
        }
    }
    
    public boolean addTeacherFrame(){
        boolean ret = false;
        Teacher teacher = new Teacher();
        this.setEnabled(false);
        JTextField name = new JTextField();
        JTextField name2 = new JTextField();
        JTextField surname = new JTextField();
        Department[] departments;
        try {
            departments = GetDataTable.getDepartments();
        } catch (SQLException ex) {
            SMS.error(this, ex.toString());
            Logger.getLogger(TeacherInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
            return ret;
        }
        JComboBox<Department> boxDepartment = new JComboBox<Department>(departments);
        JComponent[] componets = new JComponent[]{
            new JLabel("Имя:"),
            name,
            new JLabel("Отчество:"),
            name2,
            new JLabel("Фамилия:"),
            surname,
            new JLabel("Кафедра:"),
            boxDepartment
        };
        boolean dialog = false;

        do { /*
             * Цикл будет продолжаться до тех пор пока не нажмут отмена
             * Или будут введены неправильно данные
             */
            dialog = SMS.dialog(this, "Введите данные о преподавателе", componets);
            if (dialog) { // Если подтвердили ввод данных
                // Проверим наличие имени
                if (dialog && name.getText().trim().length() > 0) {
                    teacher.setName(name.getText());
                } else {
                    SMS.warning(this, "Пожалуйста введите имя!");
                    continue;
                }
                // Проверим наличие отчества
                if (dialog && name2.getText().trim().length() > 0) {
                    teacher.setName2(name2.getText());
                } else {
                    SMS.warning(this, "Пожалуйста введите отчество!");
                    continue;
                }
                // Проверим наличие фамилии
                if (dialog && surname.getText().length() > 0) {
                    teacher.setSurname(surname.getText());
                } else {
                    SMS.warning(this, "Пожалуйста введите фамилию!");
                    continue;
                }
                // Проверим указаный факульет
                if (dialog && boxDepartment.getSelectedItem() != null) {
                    teacher.setIdDepartment((Department) boxDepartment.getSelectedItem());
                } else {
                    SMS.warning(this, "Пожалуйста укажите факультет!");
                    continue;
                }
                // Прошли все проверки
                try {
                    int res = teacher.insertInto();
                    if (res == -1) {
                        if (SMS.query(this, "Такое значение уже есть.\n"
                                + "Хотете еще раз ввести значение?")) {
                            dialog = true;
                        } else {
                            dialog = false;
                        }
                    } else if (res >= 0) {
                        // Все прошло нормально и мы можем выйти с цикла
                        ret = true;
                        break;
                    }
                } catch (SQLException ex) {
                    SMS.error(this, ex.toString());
                    Logger.getLogger(TeacherInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } while (dialog);
        return ret;
    }
    
    private boolean editTeacherFrame(){
        boolean ret = false;
        Teacher teacher = new Teacher();
        Teacher newTeacher = new Teacher();
        int row = jTableTeacher.getSelectedRow();
        if (row < 0) {
            SMS.warning(this, "Выберите запись, которую необходимо редактировать");
        }
        teacher.setIdTeacher((Integer) jTableTeacher.getValueAt(row, 0));
        teacher.setName((String) jTableTeacher.getValueAt(row, 1));
        teacher.setName2((String) jTableTeacher.getValueAt(row, 2));
        teacher.setSurname((String) jTableTeacher.getValueAt(row, 3));
        teacher.setIdDepartment(
                new Department((Integer) jTableTeacher.getValueAt(row, 4),
                (String) jTableTeacher.getValueAt(row, 5)));

        JTextField name = new JTextField();
        JTextField name2 = new JTextField();
        JTextField surname = new JTextField();
        Department[] departments;
        try {
            departments = GetDataTable.getDepartments();
        } catch (SQLException ex) {
            SMS.error(this, ex.toString());
            Logger.getLogger(TeacherInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
            return ret;
        }
        JComboBox<Department> boxDepartment = new JComboBox<Department>(departments);
        JComponent[] componets = new JComponent[]{
            new JLabel("Имя:"),
            name,
            new JLabel("Отчество:"),
            name2,
            new JLabel("Фамилия:"),
            surname,
            new JLabel("Кафедра:"),
            boxDepartment
        };
        boolean dialog = false;

        do { /*
             * Цикл будет продолжаться до тех пор пока не нажмут отмена
             * Или будут введены неправильно данные
             */
            name.setText(teacher.getName());
            name2.setText(teacher.getName2());
            surname.setText(teacher.getSurname());
            boxDepartment.setSelectedItem(teacher.getIdDepartment());

            dialog = SMS.dialog(this, "Введите данные о преподавателе", componets);
            if (dialog) { // Если подтвердили ввод данных
                // Проверим наличие имени
                if (dialog && name.getText().trim().length() > 0) {
                    newTeacher.setName(name.getText());
                } else {
                    SMS.warning(this, "Пожалуйста введите имя!");
                    continue;
                }
                // Проверим наличие отчества
                if (dialog && name2.getText().trim().length() > 0) {
                    newTeacher.setName2(name2.getText());
                } else {
                    SMS.warning(this, "Пожалуйста введите отчество!");
                    continue;
                }
                // Проверим наличие фамилии
                if (dialog && surname.getText().length() > 0) {
                    newTeacher.setSurname(surname.getText());
                } else {
                    SMS.warning(this, "Пожалуйста введите фамилию!");
                    continue;
                }
                // Проверим указаный факульет
                if (dialog && boxDepartment.getSelectedItem() != null) {
                    newTeacher.setIdDepartment((Department) boxDepartment.getSelectedItem());
                } else {
                    SMS.warning(this, "Пожалуйста укажите факультет!");
                    continue;
                }
                // Прошли все проверки
                try {
                    int res = teacher.updateTable(newTeacher);
                    if (res == -1) {
                        if (SMS.query(this, "Такое значение уже есть.\n"
                                + "Хотете еще раз ввести значение?")) {
                            dialog = true;
                        } else {
                            dialog = false;
                        }
                    } else if (res >= 0) {
                        // Все прошло нормально и мы можем выйти с цикла
                        ret = true;
                        break;
                    }
                } catch (SQLException ex) {
                    SMS.error(this, ex.toString());
                    Logger.getLogger(TeacherInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } while (dialog);
        return ret;
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
        jTableTeacher = new javax.swing.JTable();

        setTitle("Справочник преподавателей");
        setMinimumSize(new java.awt.Dimension(400, 400));
        setName("dictionaries_teacher"); // NOI18N
        setPreferredSize(new java.awt.Dimension(400, 400));

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

        jTableTeacher.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTableTeacher);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditActionPerformed
        if(this.editTeacherFrame()){
            updateTableTeacher();
        }
    }//GEN-LAST:event_jButtonEditActionPerformed

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed
        if(this.addTeacherFrame()){
            updateTableTeacher();
        }
    }//GEN-LAST:event_jButtonAddActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonEdit;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableTeacher;
    // End of variables declaration//GEN-END:variables
}
