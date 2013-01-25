/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.odeku.pet.gui;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.UnsupportedLookAndFeelException;
import ua.edu.odeku.pet.gui.dictionaries.DepartmentInternalFrame;
import ua.edu.odeku.pet.gui.dictionaries.FacultyInternalFrame;
import ua.edu.odeku.pet.gui.dictionaries.GroupsInternalFrame;
import ua.edu.odeku.pet.gui.dictionaries.SubjectInternalFrame;
import ua.edu.odeku.pet.gui.dictionaries.TeacherInternalFrame;
import ua.edu.odeku.pet.gui.dictionaries.TypeWorkFrame;
import ua.edu.odeku.pet.gui.dictionaries.TypeWorkInternalFrame;
import ua.edu.odeku.pet.gui.tests.AddTestInternalFrame;
import ua.edu.odeku.pet.gui.tests.TestsFrame;
import ua.edu.odeku.pet.settings.ConfigureProgramm;
import ua.edu.odeku.pet.util.SMS;

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

        jMenu2 = new javax.swing.JMenu();
        jToolBar1 = new javax.swing.JToolBar();
        jButtonNewTest = new javax.swing.JButton();
        jDesktopPane1 = new javax.swing.JDesktopPane();
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
        jMenuChangeTheLookAndFeel = new javax.swing.JMenu();
        jMenuItemStandart = new javax.swing.JMenuItem();
        jMenuItemMetal = new javax.swing.JMenuItem();
        jMenuItemNimbus = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();

        jMenu2.setText("jMenu2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PET");
        setAutoRequestFocus(false);
        setMinimumSize(new java.awt.Dimension(40, 40));
        setModalExclusionType(java.awt.Dialog.ModalExclusionType.TOOLKIT_EXCLUDE);

        jToolBar1.setRollover(true);

        jButtonNewTest.setText("Новый тест");
        jButtonNewTest.setFocusable(false);
        jButtonNewTest.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonNewTest.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonNewTest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNewTestActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonNewTest);

        jDesktopPane1.setBackground(new java.awt.Color(240, 240, 240));

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
        jMenuItemMark.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemMarkActionPerformed(evt);
            }
        });
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

        jMenuChangeTheLookAndFeel.setText("Оформление");

        jMenuItemStandart.setText("Система");
        jMenuItemStandart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemStandartActionPerformed(evt);
            }
        });
        jMenuChangeTheLookAndFeel.add(jMenuItemStandart);

        jMenuItemMetal.setText("Метал");
        jMenuItemMetal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemMetalActionPerformed(evt);
            }
        });
        jMenuChangeTheLookAndFeel.add(jMenuItemMetal);

        jMenuItemNimbus.setText("Nimbus");
        jMenuItemNimbus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemNimbusActionPerformed(evt);
            }
        });
        jMenuChangeTheLookAndFeel.add(jMenuItemNimbus);

        jMenuSettings.add(jMenuChangeTheLookAndFeel);

        jMenuItem1.setText("Родитель");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenuSettings.add(jMenuItem1);

        jMenuBar1.add(jMenuSettings);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 623, Short.MAX_VALUE)
            .addComponent(jDesktopPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemDepartmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemDepartmentActionPerformed
        DepartmentInternalFrame departmentInternalFrame = new DepartmentInternalFrame();
        jDesktopPane1.add(departmentInternalFrame);
        departmentInternalFrame.setVisible(true);
    }//GEN-LAST:event_jMenuItemDepartmentActionPerformed

    private void jMenuItemTeachersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemTeachersActionPerformed
        TeacherInternalFrame teacherInternalFrame = new TeacherInternalFrame();
        jDesktopPane1.add(teacherInternalFrame);
        teacherInternalFrame.setVisible(true);
    }//GEN-LAST:event_jMenuItemTeachersActionPerformed

    private void jCheckBoxMenuItemIsDebagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemIsDebagActionPerformed
        ConfigureProgramm.setDEBAG(jCheckBoxMenuItemIsDebag.getState());
    }//GEN-LAST:event_jCheckBoxMenuItemIsDebagActionPerformed

    private void jMenuItemFacultyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemFacultyActionPerformed
        FacultyInternalFrame facultyInternalFrame = new FacultyInternalFrame();
        jDesktopPane1.add(facultyInternalFrame);
        facultyInternalFrame.setVisible(true);
        
    }//GEN-LAST:event_jMenuItemFacultyActionPerformed

    private void jMenuItemGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemGroupActionPerformed
        GroupsInternalFrame groupsInternalFrame = new GroupsInternalFrame();
        jDesktopPane1.add(groupsInternalFrame);
        groupsInternalFrame.setVisible(true);
    }//GEN-LAST:event_jMenuItemGroupActionPerformed

    private void jMenuItemTypeWorkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemTypeWorkActionPerformed
        TypeWorkInternalFrame typeWorkInternalFrame = new TypeWorkInternalFrame();
        jDesktopPane1.add(typeWorkInternalFrame);
        typeWorkInternalFrame.setVisible(true);
    }//GEN-LAST:event_jMenuItemTypeWorkActionPerformed

    private void jMenuItemSubjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSubjectActionPerformed
        SubjectInternalFrame subjectInternalFrame = new SubjectInternalFrame();
        jDesktopPane1.add(subjectInternalFrame);
        subjectInternalFrame.setVisible(true);
    }//GEN-LAST:event_jMenuItemSubjectActionPerformed

    private void jButtonNewTestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNewTestActionPerformed
        AddTestInternalFrame addTestInternalFrame = new AddTestInternalFrame();
        jDesktopPane1.add(addTestInternalFrame);
        addTestInternalFrame.setVisible(true);
    }//GEN-LAST:event_jButtonNewTestActionPerformed

    private void jMenuItemMarkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemMarkActionPerformed

    }//GEN-LAST:event_jMenuItemMarkActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        PetJInternalFrame petJInternalFrame = new DepartmentInternalFrame();
        jDesktopPane1.add(petJInternalFrame);
        petJInternalFrame.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItemStandartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemStandartActionPerformed
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
            SwingUtilities.updateComponentTreeUI(ConfigureProgramm.getProgFrame());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(MainFormPET.class.getName()).log(Level.SEVERE, null, ex);
            SMS.error("Не получилось применить!");
        }
    }//GEN-LAST:event_jMenuItemStandartActionPerformed

    private void jMenuItemMetalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemMetalActionPerformed
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    SwingUtilities.updateComponentTreeUI(ConfigureProgramm.getProgFrame());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFormPET.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            SMS.error("Не получилось применить!");
        }
    }//GEN-LAST:event_jMenuItemMetalActionPerformed

    private void jMenuItemNimbusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemNimbusActionPerformed
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    SwingUtilities.updateComponentTreeUI(ConfigureProgramm.getProgFrame());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            SMS.error("Не получилось применить!");
            java.util.logging.Logger.getLogger(MainFormPET.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItemNimbusActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        ua.edu.odeku.pet.settings.ConfigureProgramm.loadConfig();
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFormPET.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        ua.edu.odeku.pet.database.DataBaseConnect.createTables();
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MainFormPET mf = ConfigureProgramm.getProgFrame();
                SwingUtilities.updateComponentTreeUI(mf);
                mf.setExtendedState(MAXIMIZED_BOTH);
                mf.setVisible(true);
                mf.jCheckBoxMenuItemIsDebag.setState(false);
                ConfigureProgramm.setDEBAG(false);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonNewTest;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemIsDebag;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuChangeTheLookAndFeel;
    private javax.swing.JMenu jMenuDictionaries;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItemDepartment;
    private javax.swing.JMenuItem jMenuItemFaculty;
    private javax.swing.JMenuItem jMenuItemGroup;
    private javax.swing.JMenuItem jMenuItemMark;
    private javax.swing.JMenuItem jMenuItemMetal;
    private javax.swing.JMenuItem jMenuItemNimbus;
    private javax.swing.JMenuItem jMenuItemStandart;
    private javax.swing.JMenuItem jMenuItemStudent;
    private javax.swing.JMenuItem jMenuItemSubject;
    private javax.swing.JMenuItem jMenuItemTeachers;
    private javax.swing.JMenuItem jMenuItemTypeWork;
    private javax.swing.JMenu jMenuSettings;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables
}
