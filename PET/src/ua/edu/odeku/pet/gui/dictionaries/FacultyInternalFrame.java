/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.odeku.pet.gui.dictionaries;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import ua.edu.odeku.pet.database.entry.Faculty;
import ua.edu.odeku.pet.database.tableModal.FacultyTableModal;
import ua.edu.odeku.pet.settings.ConfigureProgramm;
import ua.edu.odeku.pet.util.SMS;

/**
 *
 * @author Aleo
 */
public class FacultyInternalFrame extends ua.edu.odeku.pet.gui.PetJInternalFrame {

    /**
     * Creates new form FacultyInternalFrame
     */
    public FacultyInternalFrame() {
        super();
        initComponents();
        updateTable();
    }
    
    private void updateTable() {

        try {
            FacultyTableModal modal = new FacultyTableModal();
            jTableFaculty.setModel(modal);
        } catch (SQLException ex) {
            SMS.error(ex.toString());
            Logger.getLogger(DepartmentFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (!ConfigureProgramm.isDEBAG()) {
            // Если программа не врежиме отладки, то скроем колонку с id
            ua.edu.odeku.pet.util.TablesUtil.hideColumn(jTableFaculty, 0);
        }
    }
    
        private boolean addFaultyFrame() {
        String name;
        boolean ret = false;
        Faculty faculty = new Faculty();
        // Цыкл необходим для того, что бы было несколько попыток у пользователя
        do {
            name = SMS.input(this, "Введите название факультета:");
            if (name != null) {
                if (name.trim().length() > 0) {
                    faculty.setNameFaculty(name);
                    try {
                        // Заносим этот объект в базу
                        int res = faculty.insertInto();
                        if (res >= 0) {
                            // все успешно
                            updateTable();
                            ret = true;
                            break;
                        } else {
                            if (SMS.query(this, "Такое значение уже есть.\n"
                                    + "Хотете еще раз ввести значение?")) {
                                continue;
                            } else {
                                break;
                            }
                        }
                    } catch (SQLException ex) {
                        SMS.error(ex.toString());
                        Logger.getLogger(FacultyFrame.class.getName()).log(Level.SEVERE, null, ex);
                        break;
                    }
                } else {
                    SMS.warning(this, "Вы ничего не ввели");
                }
            }
        } while (name != null);
        return ret;
    }

    private boolean editFacultyFrame() {
        String name = null;
        boolean ret = false;
        Faculty faculty = new Faculty(), newFaculty = new Faculty();

        // Получаем количество выделеных строк в таблице
        int countSelect = jTableFaculty.getSelectedColumnCount();
        if (countSelect == 0) {
            // Значит ни одной строки не выбратно
            SMS.warning(this, "Вы не выбрали данные для редактирования");
        } else if (countSelect > 1) {
            // Значит выбрано больше одной строки
            SMS.warning(this, "Выберите только одно значение в таблице");
        } else { // Все нормально и можем показть окно ввода
            // Считываем значение из теблицы
            name = (String) jTableFaculty.getValueAt(
                    jTableFaculty.getSelectedRow(), 1);
            faculty.setNameFaculty(name);
            faculty.setIdFaculty((Integer) jTableFaculty.getValueAt(jTableFaculty.getSelectedRow(), 0));
            // Цыкл необходим для того, что бы было несколько попыток у пользователя
            JTextField text = new JTextField();
            JComponent[] components = new JComponent[]{
                new JLabel("Введите новое название факультета:"),
                text
            };
            do {
                text.setText(name);
                SMS.dialog(this, "Введите данные:", components);
                String newFacultys = text.getText();

                // Если выбрали ДА
                if (newFacultys != null) {
                    if (newFacultys.trim().length() > 0) {
                        // Значит то что ввели не пустое!!!
                        if (!name.equals(newFacultys)) {
                            // Если введенное значение отличаеться то заносим в базу данных
                            newFaculty.setNameFaculty(newFacultys);
                            try {
                                int result = faculty.updateTable(newFaculty);
                                if (result >= 0) {
                                    ret = true;
                                    updateTable();
                                    break;
                                } else if (result == -1) {
                                    if (SMS.query(this, "Такое значение уже есть.\n"
                                            + "Хотете еще раз ввести значение?")) {
                                        continue;
                                    } else {
                                        break;
                                    }
                                }
                            } catch (SQLException ex) {
                                SMS.error(ex.toString());
                                Logger.getLogger(FacultyFrame.class.getName()).log(Level.SEVERE, null, ex);
                                break;
                            }
                        } else {
                            // Иначе мы игнорируем и ничего не делаем
                            // Хотя может быть надо выводить сообщение

                            break;
                        }
                    } else {
                        SMS.warning(this, "Вы ничего не ввели");
                    }
                } else { // Если Выбрали НЕТ
                    break;
                }
            } while (true);
        }
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
        jTableFaculty = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();

        setTitle("Справочник факультетов");

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

        jTableFaculty.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTableFaculty);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonEdit)
                        .addGap(0, 391, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed
        if (this.addFaultyFrame()){
            updateTable();
            updateUI();
        }
        this.setVisible(true);
    }//GEN-LAST:event_jButtonAddActionPerformed

    private void jButtonEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditActionPerformed
        if (this.editFacultyFrame()){
            updateTable();
            updateUI();
        }
        this.setVisible(true);
    }//GEN-LAST:event_jButtonEditActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonEdit;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableFaculty;
    // End of variables declaration//GEN-END:variables
}
