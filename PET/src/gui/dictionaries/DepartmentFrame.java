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
//            jTableDepartment.getColumnModel().getColumn(0).setMaxWidth(0);
//            jTableDepartment.getColumnModel().getColumn(0).setMinWidth(0);
//            jTableDepartment.getColumnModel().getColumn(0).setPreferredWidth(0);
//            jTableDepartment.getColumnModel().getColumn(0).setResizable(false);
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableDepartment = new javax.swing.JTable();
        jButtonAdd = new javax.swing.JButton();
        jButtonEdit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Кафедры");
        setAlwaysOnTop(true);
        setType(java.awt.Window.Type.POPUP);

        jTableDepartment.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Загрузка"
            }
        ));
        jScrollPane1.setViewportView(jTableDepartment);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
        );

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonEdit)
                        .addGap(0, 172, Short.MAX_VALUE)))
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
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Событие нажатия кнопки добавления кафедры Необходимо показать окно, а
     * наше окно сделать не активным
     *
     * @param evt
     */
    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed
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
                boolean flag = SMS.dialog(this, "Укажите данные о кафедре:", components);
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
                                        updateTableDepartment();
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
            SMS.error(this, ex.toString());
            Logger.getLogger(DepartmentFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonAddActionPerformed

    /**
     * Событие нажатия кнопки редактирования значения в таблице
     *
     * @param evt
     */
    private void jButtonEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditActionPerformed
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
                    
                    boolean flag = SMS.dialog(this, "Измените данные о кафедре", components);
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
                                        int ret = department.updateTable(newDepartment);
                                        if (ret >= 0) {
                                            updateTableDepartment();
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
        }
    }//GEN-LAST:event_jButtonEditActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonEdit;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableDepartment;
    // End of variables declaration//GEN-END:variables
}
