/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.tests;

import database.entity.Groups;
import database.entity.Subject;
import database.entity.Teacher;
import database.entity.Test;
import database.entity.TypeWork;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.SMS;

/**
 *
 * @author Aleo
 */
public class RegistTestPanel extends javax.swing.JPanel {
    
    private Integer id = null;
    private int count = 0;
    private Test oldTest = null;
    
    /**
     * Creates new form RegistTestPanel
     */
    public RegistTestPanel() {
        initComponents();
    }
    
    public RegistTestPanel(Integer id){
        this();
        this.id = id;
    }
    public RegistTestPanel(Test oldTest){
        this.oldTest = oldTest;
    }
    
    public int getCountTest(){
        count = Integer.valueOf(jSpinnerAsk.getValue().toString());
        return count;
    }
    
    public void init() throws SQLException{
        jTextFieldNameTest.setText("");
        
        Subject[] subjects = database.data.GetDataTable.getSubjects();
        jComboBoxSubject.setEnabled(false);
        for (Subject subject : subjects) {
            jComboBoxSubject.addItem(subject);
        }
        jComboBoxSubject.setEnabled(true);
        
        Teacher[] teachers = database.data.GetDataTable.getTeachers();
        jComboBoxTeacher.setEnabled(false);
        for (Teacher teacher : teachers) {
            jComboBoxTeacher.addItem(teacher);
        }
        jComboBoxTeacher.setEnabled(true);
        
        TypeWork[] typeWorks = database.data.GetDataTable.getTypeWork();
        jComboBoxTypeWork.setEnabled(false);
        for (TypeWork typeWork : typeWorks) {
            jComboBoxTypeWork.addItem(typeWork);
        }
        jComboBoxTypeWork.setEnabled(true);
        
        Groups[] groupses = database.data.GetDataTable.getGroupses();
        jComboBoxGroup.setEnabled(false);
        for (Groups groups : groupses) {
            jComboBoxGroup.addItem(groups);
        }
        jComboBoxGroup.setEnabled(true);
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jComboBoxSubject = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jComboBoxTeacher = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jComboBoxTypeWork = new javax.swing.JComboBox();
        jSpinnerAsk = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        jComboBoxGroup = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldNameTest = new javax.swing.JTextField();

        jLabel1.setText("Предмет:");

        jComboBoxSubject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxSubjectActionPerformed(evt);
            }
        });

        jLabel2.setText("Перподаватель:");

        jLabel3.setText("Тип работы:");

        jSpinnerAsk.setModel(new javax.swing.SpinnerNumberModel(Byte.valueOf((byte)2), Byte.valueOf((byte)2), Byte.valueOf((byte)100), Byte.valueOf((byte)1)));

        jLabel4.setText("Количество вопросов:");

        jLabel5.setText("Для Группы:");

        jLabel6.setText("Название теста:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jComboBoxSubject, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextFieldNameTest, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinnerAsk, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBoxTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxGroup, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxTypeWork, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldNameTest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jComboBoxTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxSubject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBoxGroup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxTypeWork, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jSpinnerAsk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxSubjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxSubjectActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxSubjectActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox jComboBoxGroup;
    private javax.swing.JComboBox jComboBoxSubject;
    private javax.swing.JComboBox jComboBoxTeacher;
    private javax.swing.JComboBox jComboBoxTypeWork;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JSpinner jSpinnerAsk;
    private javax.swing.JTextField jTextFieldNameTest;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the idRand
     */
    public Integer getIdRand() {
        return id;
    }
    
    public String saveTest(){
        String warning = null;
        
        Test test = new Test();
        test.setCount_query(count);
        test.setNameTest(jTextFieldNameTest.getText());
        test.setTeacher((Teacher)jComboBoxTeacher.getSelectedItem());
        test.setGroups((Groups)jComboBoxGroup.getSelectedItem());
        test.setTypeWork((TypeWork)jComboBoxTypeWork.getSelectedItem());
        test.setSubject((Subject)jComboBoxSubject.getSelectedItem());
        Date date = new Date(System.currentTimeMillis());
        if(id == null){ // Значит тест создаеться, необходима дата создания
            test.setDateCreate(date);
            test.setDateLastEdit(date);
        } else{
            // значит мы только редактируем
            test.setDateLastEdit(date);
        }
        
        if(test.getNameTest().trim().isEmpty()){
            warning = "Име теста не заполнено!";
        } else if(test.getGroups() == null){
            warning = "Укажите группу, для которой создан тест";
        } else if(test.getSubject() == null){
            warning = "Укажите по какому предмету этот тест";
        } else if(test.getTeacher() == null){
            warning = "Укажите создателя теста";
        } else if(test.getTypeWork() == null){
            warning = "Укажте тип работы";
        }
        
        // Проверим, бы ли возражения
        if(warning == null || warning.trim().isEmpty()){
            // значит возражений небыло, и можно сохранять
            if(id == null){
                try {
                    // Значит необходимо создать новую запись
                    int ret = test.insertInto();
                    if(ret == -1){
                        warning = "Такое значение уже есть";
                    } else if(ret == -2){
                        warning = "Ошибка не соответствия типов";
                    }
                } catch (SQLException ex) {
                    SMS.error(ex.toString());
                    Logger.getLogger(RegistTestPanel.class.getName()).log(Level.SEVERE, null, ex);
                    warning = ex.toString();
                }
            } else {
                // значит надо бновить запись
                if(oldTest == null){
                    oldTest = new Test(id);
                }
                //oldTest.updateTable(test);
            }
        }
        return warning;
    }
    
}
