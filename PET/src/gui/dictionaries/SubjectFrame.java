/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.dictionaries;

import database.entity.Faculty;
import database.entity.Subject;
import database.tableModal.SubjectTableModal;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import settings.ConfigureProgramm;
import util.SMS;

/**
 *
 * @author Aleo
 */
public class SubjectFrame extends javax.swing.JFrame {

    /**
     * Creates new form SubjectFrame
     */
    public SubjectFrame() {
        initComponents();
    }
    
    public void updateTable() {

        try {
            SubjectTableModal modal = new SubjectTableModal();
            jTableSubject.setModel(modal);
        } catch (SQLException ex) {
            SMS.error(this, ex.toString());
            Logger.getLogger(DepartmentFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (!ConfigureProgramm.isDEBAG()) {
            // Если программа не врежиме отладки, то скроем колонку с id
            util.TablesUtil.hideColumn(jTableSubject, 0);
        }
    }
    
    private boolean addSubjectFrame(){
        boolean ret = false;
        String name;
        Subject subject = new Subject();
        // Цыкл необходим для того, что бы было несколько попыток у пользователя
        do {
            name = SMS.input(this, "Введите название предмета:");
            if (name != null) {
                if (name.trim().length() > 0) {
                    subject.setNameSubject(name);
                    try {
                        // Заносим этот объект в базу
                        int res = subject.insertInto();
                        if (res >= 0) {
                            // все успешно
                            ret = true;
                            updateTable();
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
    
    private boolean editSubjectFrame(){
        boolean ret = false;
        String name = null;
        Subject subject = new Subject(), newSubject = new Subject();

        // Получаем количество выделеных строк в таблице
        int countSelect = jTableSubject.getSelectedColumnCount();
        if (countSelect == 0) {
            // Значит ни одной строки не выбратно
            SMS.warning(this, "Вы не выбрали данные для редактирования");
        } else if (countSelect > 1) {
            // Значит выбрано больше одной строки
            SMS.warning(this, "Выберите только одно значение в таблице");
        } else { // Все нормально и можем показть окно ввода
            // Считываем значение из теблицы
            name = (String) jTableSubject.getValueAt(
                    jTableSubject.getSelectedRow(), 1);
            subject.setNameSubject(name);
            subject.setIdSubject((Integer) jTableSubject.getValueAt(jTableSubject.getSelectedRow(), 0));
            // Цыкл необходим для того, что бы было несколько попыток у пользователя
            do {

                String newSubjects =
                        SMS.input(this,
                        "Введите новое название предмета:",
                        null,
                        name);

                // Если выбрали ДА
                if (newSubjects != null) {
                    if (newSubjects.trim().length() > 0) {
                        // Значит то что ввели не пустое!!!
                        if (!name.equals(newSubjects)) {
                            // Если введенное значение отличаеться то заносим в базу данных
                            newSubject.setNameSubject(newSubjects);
                            try {
                                int result = subject.updateTable(newSubject);
                                if (result >= 0) {
                                    ret = true;
                                    updateTable();
                                    break;
                                } else if(result == -1) {
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableSubject = new javax.swing.JTable();
        jButtonAdd = new javax.swing.JButton();
        jButtonEdit = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jCheckBoxMenuAlwaysOnTop = new javax.swing.JCheckBoxMenuItem();
        jMenuItemClose = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Предметы");

        jTableSubject.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Загрузка..."
            }
        ));
        jScrollPane1.setViewportView(jTableSubject);

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

        jMenu1.setText("Окно");

        jCheckBoxMenuAlwaysOnTop.setText("Поверх всех окон");
        jCheckBoxMenuAlwaysOnTop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuAlwaysOnTopActionPerformed(evt);
            }
        });
        jMenu1.add(jCheckBoxMenuAlwaysOnTop);

        jMenuItemClose.setText("Закрыть окно");
        jMenuItemClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCloseActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemClose);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonEdit)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAdd)
                    .addComponent(jButtonEdit))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBoxMenuAlwaysOnTopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuAlwaysOnTopActionPerformed
       this.setAlwaysOnTop(this.jCheckBoxMenuAlwaysOnTop.isSelected());
               
    }//GEN-LAST:event_jCheckBoxMenuAlwaysOnTopActionPerformed

    private void jMenuItemCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCloseActionPerformed
        this.dispose();
    }//GEN-LAST:event_jMenuItemCloseActionPerformed

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed
        if (addSubjectFrame()){
            updateTable();
        }
        SubjectFrame.this.setVisible(true);
    }//GEN-LAST:event_jButtonAddActionPerformed

    private void jButtonEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditActionPerformed
        if (editSubjectFrame()){
            updateTable();
        }
        SubjectFrame.this.setVisible(true);
    }//GEN-LAST:event_jButtonEditActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonEdit;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuAlwaysOnTop;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItemClose;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableSubject;
    // End of variables declaration//GEN-END:variables
}
