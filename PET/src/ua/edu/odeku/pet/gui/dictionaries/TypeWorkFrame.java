/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.odeku.pet.gui.dictionaries;

import ua.edu.odeku.pet.database.entry.TypeWork;
import ua.edu.odeku.pet.database.tableModal.TypeWorkTableModal;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import ua.edu.odeku.pet.util.SMS;

/**
 *
 * @author Aleo
 */
public class TypeWorkFrame extends javax.swing.JFrame {

    /**
     * Creates new form TypeWorkFrame
     */
    public TypeWorkFrame() {
        initComponents();
    }
    
    public void updateTable(){
        TypeWorkTableModal modal;
        try {
            modal = new TypeWorkTableModal();
        } catch (SQLException ex) {
            SMS.error(this, ex.toString());
            Logger.getLogger(TypeWorkFrame.class.getName()).log(Level.SEVERE, null, ex);
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
                        Logger.getLogger(TypeWorkFrame.class.getName()).log(Level.SEVERE, null, ex);
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
                        Logger.getLogger(TypeWorkFrame.class.getName()).log(Level.SEVERE, null, ex);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableTypeWork = new javax.swing.JTable();
        jButtonAdd = new javax.swing.JButton();
        jButtonEdit = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuWindow = new javax.swing.JMenu();
        jCheckBoxAlwaysOnTop = new javax.swing.JCheckBoxMenuItem();
        jMenuClose = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Типы работ");

        jTableTypeWork.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Загрузка...."
            }
        ));
        jScrollPane1.setViewportView(jTableTypeWork);

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

        jMenuWindow.setText("Окно");

        jCheckBoxAlwaysOnTop.setText("Поверх других окон");
        jCheckBoxAlwaysOnTop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxAlwaysOnTopActionPerformed(evt);
            }
        });
        jMenuWindow.add(jCheckBoxAlwaysOnTop);

        jMenuClose.setText("Закрыть окно");
        jMenuClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuCloseActionPerformed(evt);
            }
        });
        jMenuWindow.add(jMenuClose);

        jMenuBar1.add(jMenuWindow);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed
        if (addTypeWorkFrame()){
            updateTable();
        }
        TypeWorkFrame.this.setVisible(true);
    }//GEN-LAST:event_jButtonAddActionPerformed

    private void jButtonEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditActionPerformed
        if(editTypeWorkFrame()){
            updateTable();
        }
        TypeWorkFrame.this.setVisible(true);
    }//GEN-LAST:event_jButtonEditActionPerformed

    private void jCheckBoxAlwaysOnTopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxAlwaysOnTopActionPerformed
        TypeWorkFrame.this.setAlwaysOnTop(jCheckBoxAlwaysOnTop.isSelected());
    }//GEN-LAST:event_jCheckBoxAlwaysOnTopActionPerformed

    private void jMenuCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuCloseActionPerformed
        TypeWorkFrame.this.dispose();
    }//GEN-LAST:event_jMenuCloseActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonEdit;
    private javax.swing.JCheckBoxMenuItem jCheckBoxAlwaysOnTop;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuClose;
    private javax.swing.JMenu jMenuWindow;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableTypeWork;
    // End of variables declaration//GEN-END:variables
}
