/*
 * Класс описывает окна с таблицей факультетов
 * Все действия происходят в этом класе
 */
package gui.dictionaries;

import database.entity.Department;
import database.entity.Faculty;
import database.tableModal.DepartmentTableModal;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import settings.ConfigureProgramm;
import util.SMS;

/**
 *
 * @author Aleo
 */
public class DepartmentFrame extends javax.swing.JFrame {

    /**
     * Creates new form DepartmentFrame
     */
    public DepartmentFrame() {
        initComponents();
        updateTableDepartment();
    }
    
    /**
     * Метод производить добавление в базу данных значение Факультета
     * @return 
     */
    public boolean addDepartmentFrame(){
        boolean ret = false;
        try {
            String nameDepartment;
            Department department = new Department();
            JTextField name = new JTextField();
            // Получили массив объектов для комбо-бокса
            Faculty[] facultys = database.data.GetDataTable.getFacultys();
            JComboBox<Faculty> box = new JComboBox<Faculty>(facultys);
            JComponent[] components = new JComponent[]{
              new JLabel("Введите название кафедры:"),
              name,
              new JLabel("Выберите факультет:"),
              box
            };
            // Цыкл необходим для того, что бы было несколько попыток у пользователя
            do {
                boolean flag = SMS.dialog("Укажите данные о кафедре:", components);
                if(flag){
                    nameDepartment = name.getText();
                    if (nameDepartment != null) {
                        if(box.getSelectedItem() != null){
                            if (nameDepartment.trim().length() > 0) {
                                department.setNameDepartment(nameDepartment);
                                department.setIdFaculty((Faculty)box.getSelectedItem());
                                try {
                                    // Заносим этот объект в базу
                                    int res = department.insertInto();
                                    if (res >= 0) {
                                        // все успешно
                                        ret = true;
                                        break;
                                    } else {
                                        if (SMS.query(this, "Такое значение уже есть.\n"
                                                + "Хотете еще раз ввести значение?")) {
                                            continue;
                                        } else {
                                            ret = false;
                                            break;
                                        }
                                    }
                                } catch (SQLException ex) {
                                    SMS.error(ex.toString());
                                    Logger.getLogger(DepartmentFrame.class.getName()).log(Level.SEVERE, null, ex);
                                    break;
                                }

                            } else {
                                SMS.warning(this, "Вы ничего не ввели!");
                            }
                        } else {
                            SMS.warning(this, "Укажите факультет!");
                        }
                    }
                } else {
                    // Пользователь отказался от ввода
                    break;
                }
            } while (true);
        } catch (SQLException ex) {
            SMS.error(ex.toString());
            Logger.getLogger(DepartmentFrame.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return ret;
        }
    }
    
    /**
     * Метод выводит диалоговое окно для изменения значения в таблице
     * @return true если было успешно заменено значение
     */
    private boolean editDepartmentFrame(){
        boolean ret = false;
        try {
            String nameDepartment = null, newNameDepartment = null;
            Department department = new Department(), newDepartment = new Department();
            JTextField name = new JTextField();
                // Получили массив объектов для комбо-бокса
                Faculty[] facultys = database.data.GetDataTable.getFacultys();
                JComboBox<Faculty> box = new JComboBox<Faculty>(facultys);
                JComponent[] components = new JComponent[]{
                  new JLabel("Введите название кафедры:"),
                  name,
                  new JLabel("Выберите факультет:"),
                  box
                };
                
            // Получаем количество выделеных строк в таблице
            int countSelect = jTableDepartment.getSelectedColumnCount();
            if (countSelect == 0) {
                // Значит ни одной строки не выбратно
                SMS.warning(this, "Вы не выбрали данные для редактирования");
            } else if (countSelect > 1) {
                // Значит выбрано больше одной строки
                SMS.warning(this, "Выберите только одно значение в таблице");
            } else { // Все нормально и можем показть окно ввода
                // Считываем значение из теблицы
                nameDepartment = (String) jTableDepartment.getValueAt(
                        jTableDepartment.getSelectedRow(), 1);
                department.setNameDepartment(nameDepartment);
                department.setIdDepartment((Integer) jTableDepartment.getValueAt(jTableDepartment.getSelectedRow(), 0));
                department.setIdFaculty(new Faculty(
                        (Integer)jTableDepartment.getValueAt(jTableDepartment.getSelectedRow(), 2), 
                        (String) jTableDepartment.getValueAt(jTableDepartment.getSelectedRow(), 3)));
                // Цыкл необходим для того, что бы было несколько попыток у пользователя
                do {
                    name.setText(nameDepartment);
                    box.setSelectedItem(department.getIdFaculty());
                    
                    boolean flag = SMS.dialog("Измените данные о кафедре", components);
                    if(flag){
                        newNameDepartment = name.getText();
                        Faculty f =(Faculty) box.getSelectedItem();
                        // Если выбрали ДА
                        if (newNameDepartment.trim().length() > 0) {
                            // Значит то что ввели не пустое!!!
                            if(f != null) {
                                if (!nameDepartment.equals(newNameDepartment) || !f.equals(department.getIdFaculty()) ) {
                                    // Если введенное значение отличаеться то заносим в базу данных
                                    // Соберем объект
                                    newDepartment.setNameDepartment(newNameDepartment);
                                    newDepartment.setIdFaculty(f);
                                    try {
                                        int result = department.updateTable(newDepartment);
                                        if (result >= 0) {
                                            ret = true;
                                            break;
                                        } else if(result == -1) {
                                            if (SMS.query("Такое значение уже есть.\n"
                                                    + "Хотете еще раз ввести значение?")) {
                                                continue;
                                            } else {
                                                break;
                                            }
                                        }
                                    } catch (SQLException ex) {
                                        SMS.error(this, ex.toString());
                                        Logger.getLogger(DepartmentFrame.class.getName()).log(Level.SEVERE, null, ex);
                                        break;
                                    }
                                } else {
                                    // Иначе мы игнорируем и ничего не делаем
                                    // Хотя может быть надо выводить сообщение

                                    break;
                                }
                            } else {
                                SMS.warning(this, "Укажите факульет!");
                            }
                        } else {
                            SMS.warning(this, "Вы ничего не ввели!");
                        }
                    }else { // Если Выбрали НЕТ
                            break;
                        }
                } while (true);
            }
        } catch (SQLException ex) {
            SMS.error(this, ex.toString());
            Logger.getLogger(DepartmentFrame.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            return ret;
        }
    }
    
    /**
     * Метод обновляет данные в таблице И если необходимо скарывает столбец
     */
    private void updateTableDepartment() {

        try {
            DepartmentTableModal modal = new DepartmentTableModal();
            jTableDepartment.setModel(modal);
        } catch (SQLException ex) {
            SMS.error(this, ex.toString());
            Logger.getLogger(DepartmentFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (!ConfigureProgramm.isDEBAG()) {
            // Если программа не врежиме отладки, то скроем колонку с id
            util.TablesUtil.hideColumn(jTableDepartment, 0);
            util.TablesUtil.hideColumn(jTableDepartment, 2);
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

        jMenuItem1 = new javax.swing.JMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableDepartment = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuWindow = new javax.swing.JMenu();
        jCheckBoxMenuAlwaysOnTop = new javax.swing.JCheckBoxMenuItem();
        jMenuItemClose = new javax.swing.JMenuItem();
        jMenuAdd = new javax.swing.JMenu();
        jMenuEdit = new javax.swing.JMenu();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Кафедры");
        setType(java.awt.Window.Type.POPUP);

        jScrollPane1.setBorder(null);

        jTableDepartment.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Загрузка"
            }
        ));
        jScrollPane1.setViewportView(jTableDepartment);

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
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 595, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuAddMouseClicked
        boolean ret = this.addDepartmentFrame();
        if (ret){
            this.updateTableDepartment();
        }
        DepartmentFrame.this.setVisible(true);
    }//GEN-LAST:event_jMenuAddMouseClicked

    private void jMenuEditMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuEditMouseClicked
        boolean ret = this.editDepartmentFrame();
        if (ret){
            this.updateTableDepartment();
        }
        DepartmentFrame.this.setVisible(true);
    }//GEN-LAST:event_jMenuEditMouseClicked

    private void jCheckBoxMenuAlwaysOnTopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuAlwaysOnTopActionPerformed
        this.setAlwaysOnTop(this.jCheckBoxMenuAlwaysOnTop.isSelected());    
    }//GEN-LAST:event_jCheckBoxMenuAlwaysOnTopActionPerformed

    private void jMenuItemCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCloseActionPerformed
        this.dispose();
    }//GEN-LAST:event_jMenuItemCloseActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuAlwaysOnTop;
    private javax.swing.JMenu jMenuAdd;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuEdit;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItemClose;
    private javax.swing.JMenu jMenuWindow;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableDepartment;
    // End of variables declaration//GEN-END:variables
}
