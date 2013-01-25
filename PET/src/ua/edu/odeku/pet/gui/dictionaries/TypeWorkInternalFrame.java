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
import ua.edu.odeku.pet.database.entry.TypeWork;
import ua.edu.odeku.pet.database.tableModal.TypeWorkTableModal;
import ua.edu.odeku.pet.gui.PetJInternalFrame;
import ua.edu.odeku.pet.util.SMS;

/**
 *
 * @author Aleo
 */
public class TypeWorkInternalFrame extends PetJInternalFrame {

    /**
     * Creates new form TypeWorkInternalFrame
     */
    public TypeWorkInternalFrame() {
        super();
        initComponents();
        updateTable();
    }
    
    private void updateTable(){
        TypeWorkTableModal modal;
        try {
            modal = new TypeWorkTableModal();
        } catch (SQLException ex) {
            SMS.error(this, ex.toString());
            Logger.getLogger(TypeWorkInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        jTableTypeWork.setModel(modal);
        
        if(!ua.edu.odeku.pet.settings.ConfigureProgramm.isDEBAG()){
            ua.edu.odeku.pet.util.TablesUtil.hideColumn(jTableTypeWork, 0);
        }
    }
    public boolean addTypeWorkFrame(){
        boolean ret = false;
        String name;
        JTextField text = new JTextField();
        JComponent[] components = new JComponent[]{
            new JLabel("Введите название типа работы"),
            text
        };
        do {
            boolean result = SMS.dialog(this, "Введите данные о типе работы", components);
            if(result){
                if(text.getText().trim().length() > 0){
                    try {
                        TypeWork type = new TypeWork();
                        type.setNameTypeWork(text.getText());
                        int code = type.insertInto();
                        if(code == -1){
                            result = SMS.query(this, "Такое значение уже есть\nХотите еще раз ввести данные?");
                            if(result){
                                // запускаем занового цикл
                                continue;
                            } else {
                                // значит выходим из цикла
                                break;
                            }
                        } else if( code >= 0 ){
                            // все прошло успешно
                            ret = true;
                            break;
                        }
                    } catch (SQLException ex) {
                        SMS.error(this, ex.toString());
                        Logger.getLogger(TypeWorkInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
                        break;
                    }
                } else {
                    SMS.message(this, "Вы ничего не ввели");
                }
            } else {
                // Пользователь отменил выбор
                break;
            }
        } while (true);
        return ret;
    }
    
    private boolean editTypeWorkFrame(){
        boolean ret = false;
        String name;
        int id;
        if (jTableTypeWork.getSelectedRow() == -1 ){
            // значит ничего не выбрали
            SMS.message(this, "Вы ничего не выбрали для редактирования!");
            return ret;
        }
        
        name = (String) jTableTypeWork.getValueAt(jTableTypeWork.getSelectedRow(), 1);
        id = (int) jTableTypeWork.getValueAt(jTableTypeWork.getSelectedRow(), 0);
        TypeWork type = new TypeWork(id, name);
        
        JTextField text = new JTextField();
        JComponent[] components = new JComponent[]{
            new JLabel("Введите название типа работы"),
            text
        };
        do {
            text.setText(name);
            boolean result = SMS.dialog(this, "Измените данные о типе работы", components);
            if(result){
                if(text.getText().trim().length() > 0){
                    try {
                        TypeWork newType = new TypeWork();
                        newType.setNameTypeWork(text.getText());
                        int code = type.updateTable(newType);
                        if(code == -1){
                            result = SMS.query(this, "Такое значение уже есть\nХотите еще раз ввести данные?");
                            if(result){
                                // запускаем занового цикл
                                continue;
                            } else {
                                // значит выходим из цикла
                                break;
                            }
                        } else if( code >= 0 ){
                            // все прошло успешно
                            ret = true;
                            break;
                        }
                    } catch (SQLException ex) {
                        SMS.error(this, ex.toString());
                        Logger.getLogger(TypeWorkInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
                        break;
                    }
                } else {
                    SMS.message(this, "Вы ничего не ввели");
                }
            } else {
                // Пользователь отменил выбор
                break;
            }
        } while (true);
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
        jTableTypeWork = new javax.swing.JTable();

        setTitle("Справочник типов работ");
        setMinimumSize(new java.awt.Dimension(400, 400));
        setPreferredSize(new java.awt.Dimension(400, 400));

        jButtonAdd.setText("Добавить");
        jButtonAdd.setToolTipText("");
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

        jTableTypeWork.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTableTypeWork);

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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed
        if (this.addTypeWorkFrame()){
            updateTable();
        }
    }//GEN-LAST:event_jButtonAddActionPerformed

    private void jButtonEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditActionPerformed
        if(this.editTypeWorkFrame()){
            updateTable();
        }
    }//GEN-LAST:event_jButtonEditActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonEdit;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableTypeWork;
    // End of variables declaration//GEN-END:variables
}
