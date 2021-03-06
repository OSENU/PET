/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.odeku.pet.gui.tests.matched;

import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import ua.edu.odeku.pet.database.entry.TypeQuestion;
import ua.edu.odeku.pet.gui.tests.Questionable;
import ua.edu.odeku.pet.util.SMS;

/**
 *
 * @author Aleo
 */
public class MatchedPanel extends javax.swing.JPanel implements Questionable{
    private ItemMatchedPanel[] itemA , itemB;
    /**
     * Creates new form MatchedPanel
     */
    public MatchedPanel() {
        initComponents();
        jScrollPane.setHorizontalScrollBarPolicy(javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane.setVerticalScrollBarPolicy(javax.swing.JScrollPane.VERTICAL_SCROLLBAR_NEVER);
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
        jTextFieldTask = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        jButtonCreate = new javax.swing.JButton();
        jScrollPane = new javax.swing.JScrollPane();
        jPanel = new javax.swing.JPanel();

        jLabel1.setText("Задание:");

        jLabel2.setText("Количество соотверствий:");

        jSpinner1.setModel(new javax.swing.SpinnerNumberModel(2, 2, 10, 1));

        jButtonCreate.setText("Создать");
        jButtonCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCreateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelLayout = new javax.swing.GroupLayout(jPanel);
        jPanel.setLayout(jPanelLayout);
        jPanelLayout.setHorizontalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 378, Short.MAX_VALUE)
        );
        jPanelLayout.setVerticalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 154, Short.MAX_VALUE)
        );

        jScrollPane.setViewportView(jPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldTask))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonCreate)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldTask, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCreate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCreateActionPerformed
        if(itemA != null || itemB != null){
            if(!SMS.query("Это приведет к потере существующих данных!\nПродолжить?")){
                return;
            }
        }
        int count = Integer.valueOf(jSpinner1.getValue().toString());
        itemA = new ItemMatchedPanel[count];
        itemB = new ItemMatchedPanel[count];
        
        jPanel.removeAll();
        jPanel.setLayout(new BorderLayout());
        
        javax.swing.JPanel panelA = new javax.swing.JPanel();
        javax.swing.JPanel panelB = new javax.swing.JPanel();
        
        panelA.setLayout(new BoxLayout(panelA, BoxLayout.Y_AXIS));
        panelB.setLayout(new BoxLayout(panelB, BoxLayout.Y_AXIS));
        jPanel.add(panelA, BorderLayout.WEST);
        jPanel.add(panelB, BorderLayout.EAST);
        
        for (int i = 0; i < count; i++) {
            itemA[i] = new ItemMatchedPanel();
            itemB[i] = new ItemMatchedPanel();
            panelA.add(itemA[i]);
            panelB.add(itemB[i]);
        }
        jScrollPane.setVerticalScrollBarPolicy(jScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.revalidate();
        
    }//GEN-LAST:event_jButtonCreateActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCreate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTextField jTextFieldTask;
    // End of variables declaration//GEN-END:variables

    @Override
    public String saveQuestion(ua.edu.odeku.pet.database.entry.Test idTest) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public JPanel loadQuestion(long idItem) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getTask() {
        return this.jTextFieldTask.getText();
    }

    @Override
    public TypeQuestion getTypeQuestion() {
        TypeQuestion typeQuestion = new TypeQuestion();
        typeQuestion.setIdTypeQuestion(4);
        typeQuestion.setNameProgQuestionType("Matched");
        return typeQuestion;
    }

    @Override
    public int getCountAnswer() {
        if(this.itemA != null){
            return itemA.length;
        } else {
            return 0;
        }
    }

    @Override
    public int getCountRightAnswer() {
        return 1;
    }

    @Override
    public void removeQuestion() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void removeAnswer(int idVariant) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String checkToPrepareQuestion() {
        String warning = null;
        if(itemA == null || itemB == null){
            warning = "Создайте соответствия!";
        } else if(this.getTask().trim().isEmpty()){
            warning = "Введите задание!";
        } else {
            for (int i = 0; i < itemA.length; i++) {
                if(itemA[i].getjTextField1().trim().isEmpty()){
                    warning =(i+1) + " соответствие не заполнено";
                    break;
                }
                if(itemB[i].getjTextField1().trim().isEmpty()){
                    warning =(i+1) + " соответствие не заполнено";
                    break;
                }
            }
        }
        return warning;
    }
}
