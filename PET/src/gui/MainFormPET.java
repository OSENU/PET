/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import database.entity.Subject;
import gui.dictionaries.DepartmentFrame;
import gui.dictionaries.FacultyFrame;
import gui.dictionaries.GroupsFrame;
import gui.dictionaries.SubjectFrame;
import gui.dictionaries.TeacherFrame;
import gui.dictionaries.TypeWorkFrame;
import javax.swing.JFrame;
import settings.ConfigureProgramm;

/**
 *
 * @author Aleo
 */
public class MainFormPET extends javax.swing.JFrame {

    /**
     * Creates new form MainFormPET
     */
    public MainFormPET() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuDictionaries = new javax.swing.JMenu();
        jMenuItemDepartment = new javax.swing.JMenuItem();
        jMenuItemFaculty = new javax.swing.JMenuItem();
        jMenuItemSubject = new javax.swing.JMenuItem();
        jMenuItemTeachers = new javax.swing.JMenuItem();
        jMenuItemMark = new javax.swing.JMenuItem();
        jMenuItemTypeWork = new javax.swing.JMenuItem();
        jMenuItemGroup = new javax.swing.JMenuItem();
        jMenuItemStudent = new javax.swing.JMenuItem();
        jMenuSettings = new javax.swing.JMenu();
        jCheckBoxMenuItemIsDebag = new javax.swing.JCheckBoxMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PET");
        setAutoRequestFocus(false);
        setLocationByPlatform(true);
        setMinimumSize(new java.awt.Dimension(40, 40));
        setModalExclusionType(java.awt.Dialog.ModalExclusionType.TOOLKIT_EXCLUDE);

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenuDictionaries.setText("Словари");

        jMenuItemDepartment.setText("Кафедры");
        jMenuItemDepartment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemDepartmentActionPerformed(evt);
            }
        });
        jMenuDictionaries.add(jMenuItemDepartment);

        jMenuItemFaculty.setText("Факультет");
        jMenuItemFaculty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemFacultyActionPerformed(evt);
            }
        });
        jMenuDictionaries.add(jMenuItemFaculty);

        jMenuItemSubject.setText("Предметы");
        jMenuItemSubject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSubjectActionPerformed(evt);
            }
        });
        jMenuDictionaries.add(jMenuItemSubject);

        jMenuItemTeachers.setText("Преподаватели");
        jMenuItemTeachers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemTeachersActionPerformed(evt);
            }
        });
        jMenuDictionaries.add(jMenuItemTeachers);

        jMenuItemMark.setText("Оценки");
        jMenuDictionaries.add(jMenuItemMark);

        jMenuItemTypeWork.setText("Типы работ");
        jMenuItemTypeWork.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemTypeWorkActionPerformed(evt);
            }
        });
        jMenuDictionaries.add(jMenuItemTypeWork);

        jMenuItemGroup.setText("Группы");
        jMenuItemGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemGroupActionPerformed(evt);
            }
        });
        jMenuDictionaries.add(jMenuItemGroup);

        jMenuItemStudent.setText("Студенты");
        jMenuDictionaries.add(jMenuItemStudent);

        jMenuBar1.add(jMenuDictionaries);

        jMenuSettings.setText("Настройки");

        jCheckBoxMenuItemIsDebag.setSelected(true);
        jCheckBoxMenuItemIsDebag.setText("Режим отладки");
        jCheckBoxMenuItemIsDebag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItemIsDebagActionPerformed(evt);
            }
        });
        jMenuSettings.add(jCheckBoxMenuItemIsDebag);

        jMenuBar1.add(jMenuSettings);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 279, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemDepartmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemDepartmentActionPerformed
        DepartmentFrame df = new DepartmentFrame();
        df.setLocationByPlatform(true);
        df.setVisible(true);
    }//GEN-LAST:event_jMenuItemDepartmentActionPerformed

    private void jMenuItemTeachersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemTeachersActionPerformed
        TeacherFrame tf = new TeacherFrame();
        tf.setVisible(true);
    }//GEN-LAST:event_jMenuItemTeachersActionPerformed

    private void jCheckBoxMenuItemIsDebagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemIsDebagActionPerformed
        ConfigureProgramm.setDEBAG(jCheckBoxMenuItemIsDebag.getState());
    }//GEN-LAST:event_jCheckBoxMenuItemIsDebagActionPerformed

    private void jMenuItemFacultyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemFacultyActionPerformed
        FacultyFrame ff = new FacultyFrame();
        ff.setVisible(true);
    }//GEN-LAST:event_jMenuItemFacultyActionPerformed

    private void jMenuItemGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemGroupActionPerformed
        GroupsFrame frame = new GroupsFrame();
        frame.setVisible(true);
        frame.updateTable();
    }//GEN-LAST:event_jMenuItemGroupActionPerformed

    private void jMenuItemTypeWorkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemTypeWorkActionPerformed
        TypeWorkFrame frame = new TypeWorkFrame();
        frame.setVisible(true);
        frame.updateTable();
    }//GEN-LAST:event_jMenuItemTypeWorkActionPerformed

    private void jMenuItemSubjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSubjectActionPerformed
        SubjectFrame frame = new SubjectFrame();
        frame.setVisible(true);
        frame.updateTable();
    }//GEN-LAST:event_jMenuItemSubjectActionPerformed

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
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFormPET.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /**
         * Установка программы в режим отладки
         * В этом режиме необходимо показывать код объектов в табилце
         */
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MainFormPET mf = new MainFormPET();
                mf.setExtendedState(JFrame.MAXIMIZED_BOTH);
                
                mf.setVisible(true);
                mf.jCheckBoxMenuItemIsDebag.setState(false);
                ConfigureProgramm.setDEBAG(false);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemIsDebag;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuDictionaries;
    private javax.swing.JMenuItem jMenuItemDepartment;
    private javax.swing.JMenuItem jMenuItemFaculty;
    private javax.swing.JMenuItem jMenuItemGroup;
    private javax.swing.JMenuItem jMenuItemMark;
    private javax.swing.JMenuItem jMenuItemStudent;
    private javax.swing.JMenuItem jMenuItemSubject;
    private javax.swing.JMenuItem jMenuItemTeachers;
    private javax.swing.JMenuItem jMenuItemTypeWork;
    private javax.swing.JMenu jMenuSettings;
    // End of variables declaration//GEN-END:variables
}
