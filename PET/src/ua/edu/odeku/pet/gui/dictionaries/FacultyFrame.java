/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.odeku.pet.gui.dictionaries;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import ua.edu.odeku.pet.database.entry.Faculty;
import ua.edu.odeku.pet.database.tableModal.FacultyTableModal;
import ua.edu.odeku.pet.settings.ConfigureProgramm;
import ua.edu.odeku.pet.util.SMS;

/**
 *
 * @author Aleo
 */
public class FacultyFrame extends javax.swing.JFrame {

    /**
     * Creates new form FacultyFrame
     */
    public FacultyFrame() {
        initComponents();
        updateTable();
    }

    private void updateTable() {

        try {
            FacultyTableModal modal = new FacultyTableModal();
            jTableFaculty.setModel(modal);
        } catch (SQLException ex) {
            SMS.error(this, ex.toString());
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
                        SMS.error(this, ex.toString());
                        Logger.getLogger(FacultyFrame.class.getName()).log(Level.SEVERE, null, ex);
                        break;
                    }
                } else {
                    SMS.warning(this, "Вы ничего не ввели");
                }
            }
        } while (name != null);
        FacultyFrame.this.setVisible(true);
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
            do {

                String newFacultys =
                        SMS.input(this,
                        "Введите новое название факультета:",
                        null,
                        name);

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
                                SMS.error(this, ex.toString());
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
            FacultyFrame.this.setVisible(true);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableFaculty = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuWindow = new javax.swing.JMenu();
        jCheckBoxMenuAlwaysOnTop = new javax.swing.JCheckBoxMenuItem();
        jMenuItemClose = new javax.swing.JMenuItem();
        jMenuAdd = new javax.swing.JMenu();
        jMenuEdit = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Факультеты");

        jTableFaculty.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Загрузка..."
            }
        ));
        jScrollPane1.setViewportView(jTableFaculty);

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
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuAddMouseClicked
        this.addFaultyFrame();
    }//GEN-LAST:event_jMenuAddMouseClicked

    private void jMenuEditMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuEditMouseClicked
        this.editFacultyFrame();
    }//GEN-LAST:event_jMenuEditMouseClicked

    private void jCheckBoxMenuAlwaysOnTopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuAlwaysOnTopActionPerformed
        this.setAlwaysOnTop(jCheckBoxMenuAlwaysOnTop.isSelected());
    }//GEN-LAST:event_jCheckBoxMenuAlwaysOnTopActionPerformed

    private void jMenuItemCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCloseActionPerformed
        this.dispose();
    }//GEN-LAST:event_jMenuItemCloseActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuAlwaysOnTop;
    private javax.swing.JMenu jMenuAdd;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuEdit;
    private javax.swing.JMenuItem jMenuItemClose;
    private javax.swing.JMenu jMenuWindow;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableFaculty;
    // End of variables declaration//GEN-END:variables
}
