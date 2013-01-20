/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.tests;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.SMS;

/**
 *
 * @author Aleo
 */
public class TestsFrame extends javax.swing.JFrame {
    SelectedTypeAskPanel[] selectedTypeAskPanels;
    /**
     * Creates new form TestsFrame
     */
    public TestsFrame() {
        initComponents();
        jTabbedPane1.setVisible(false);
        try {
            registTestPanel1.init();
        } catch (SQLException ex) {
            SMS.error(ex.getMessage());
            Logger.getLogger(TestsFrame.class.getName()).log(Level.SEVERE, null, ex);
            this.dispose();
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

        registTestPanel1 = new gui.tests.RegistTestPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jButtonBegin = new javax.swing.JButton();
        jButtonSave = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jButtonBegin.setText("Сгенирировать поля");
        jButtonBegin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBeginActionPerformed(evt);
            }
        });

        jButtonSave.setText("Сохранить");
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(registTestPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonBegin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonBegin)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonSave))
                    .addComponent(registTestPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonBeginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBeginActionPerformed
        if (selectedTypeAskPanels != null){
            if(!SMS.query("Все тесты будут потеряны.\nПродолжить?")){
                return;
            }
        }
        int countTest = registTestPanel1.getCountTest();
        selectedTypeAskPanels = new SelectedTypeAskPanel[countTest];
        jTabbedPane1.removeAll();
        for (int i = 0; i < countTest; i++) {
            selectedTypeAskPanels[i] = new SelectedTypeAskPanel();
            jTabbedPane1.add(selectedTypeAskPanels[i]);
            jTabbedPane1.setTitleAt(i, "Задание " + (i+1) );
        }
        jTabbedPane1.setVisible(true);
    }//GEN-LAST:event_jButtonBeginActionPerformed

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        if(selectedTypeAskPanels != null){
            // Соберем все в один массив
            ItemTest[] itemTests = new ItemTest[registTestPanel1.getCountTest()];
            String warning = null;
            for (int i = 0; i < selectedTypeAskPanels.length; i++) {
                // Заполним масив который будет сохраняться.
                itemTests[i] = selectedTypeAskPanels[i].returnItemTest();
                if(itemTests[i] == null){
                    SMS.message(this, (i+1) + " задание еще не создано!");
                    this.setVisible(true);
                    this.jTabbedPane1.setSelectedIndex(i);
                    return;
                }
                // Каждый элимент проверим на его заполненость.
                warning = itemTests[i].checkToPrepare();
                if(warning != null && ! warning.trim().isEmpty() ){
                    SMS.warning(this, (i+1) + " задание:\n" + warning);
                    this.jTabbedPane1.setSelectedIndex(i);
                    this.setVisible(true);
                    return;
                }
            }
            // Cохраним сам тест
            warning = registTestPanel1.saveTest();
            if(warning == null || warning.trim().isEmpty()){
                // Получим код теста
//                Long idTest = registTestPanel1.getIdRand();
//                // Пройдемся по всем заданиям и сохраним их
//                for (int i = 0; i < itemTests.length; i++) {
//                    warning = itemTests[i].saveItemTest(idTest);
//                    // Если были ошибки то прервем сохранение
//                    if(!warning.trim().isEmpty()){
//                        SMS.warning(this, "В задании " + (i + 1) + " : " + warning);
//                        // Тут по идее откат....
//                        return;
//                    }
//                }
                SMS.message("OK");
            } else {
                SMS.warning(this, warning);
                // Тут откат необходимо сделать
            }
        } else {
            SMS.message(this, "Сначало вам необходимо создать тесты.");
        }
        this.setVisible(true);
        
    }//GEN-LAST:event_jButtonSaveActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TestsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TestsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TestsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TestsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TestsFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBegin;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JTabbedPane jTabbedPane1;
    private gui.tests.RegistTestPanel registTestPanel1;
    // End of variables declaration//GEN-END:variables
}
