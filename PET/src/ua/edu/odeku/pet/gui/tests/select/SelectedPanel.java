/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.odeku.pet.gui.tests.select;

import ua.edu.odeku.pet.gui.tests.select.ItemSelectedPanel;
import ua.edu.odeku.pet.gui.tests.Questionable;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import ua.edu.odeku.pet.database.entry.TypeQuestion;
import ua.edu.odeku.pet.util.SMS;

/**
 *
 * @author Aleo
 */
public class SelectedPanel extends javax.swing.JPanel implements Questionable{
    ItemSelectedPanel[] selectVariantPanels;
    /**
     * Creates new form SelectedTest
     */
    public SelectedPanel() {
        initComponents();
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.JScrollPane.VERTICAL_SCROLLBAR_NEVER);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSpinnerCountVariant = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        jButtonApply = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldAsk = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();

        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Формирование тестового задания", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, java.awt.Color.black));
        setMinimumSize(new java.awt.Dimension(100, 100));

        jSpinnerCountVariant.setModel(new javax.swing.SpinnerNumberModel(1, 1, 100, 1));

        jLabel1.setText("Количество варинтов:");

        jButtonApply.setText("Применить");
        jButtonApply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonApplyActionPerformed(evt);
            }
        });

        jLabel2.setText("Вопрос:");

        jScrollPane1.setBorder(null);
        jScrollPane1.setForeground(new java.awt.Color(240, 240, 240));

        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 571, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 414, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldAsk)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSpinnerCountVariant, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonApply, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldAsk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinnerCountVariant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonApply)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonApplyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonApplyActionPerformed
        int countVariant = Integer.valueOf(jSpinnerCountVariant.getValue().toString());
        if(selectVariantPanels != null){
          if(!SMS.query("Все варианты будут потеряны\nПродолжить?")){
              return;
          }
        } 
        selectVariantPanels = new ItemSelectedPanel[countVariant];
        jPanel2.removeAll();
        jPanel2.setLayout(new BoxLayout(jPanel2, BoxLayout.PAGE_AXIS));

        for (int i = 0; i < countVariant; i++) {
            selectVariantPanels[i] = new ItemSelectedPanel();
            selectVariantPanels[i].setBorder(new TitledBorder("Вариант : " + (i+1)));
            
            jPanel2.add(selectVariantPanels[i]);
        }
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.updateUI();
    }//GEN-LAST:event_jButtonApplyActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonApply;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinnerCountVariant;
    private javax.swing.JTextField jTextFieldAsk;
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
        return this.jTextFieldAsk.getText();
    }

    @Override
    public TypeQuestion getTypeQuestion() {
        TypeQuestion typeQuestion = new TypeQuestion();
        typeQuestion.setIdTypeQuestion(1);
        typeQuestion.setNameProgQuestionType("Selected");
        return typeQuestion;
    }

    @Override
    public int getCountAnswer() {
        return jPanel2.getComponentCount();
    }

    @Override
    public int getCountRightAnswer() {
        int count = 0;
        for (ItemSelectedPanel itemSelectedPanel : selectVariantPanels) {
            if(itemSelectedPanel.isRightAnswer()){
                count++;
            }
        }
        return count;
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
        // Проверим заполненость всех текстовых полей
        if(selectVariantPanels == null){
            warning = "Создайте более одного варианта ответа, на данное задание";
            return warning;
        }
        if(this.getTask().trim().isEmpty()){
            warning = "Не задан вопрос!";
            return warning;
        }
        
        for (int i = 0; i < selectVariantPanels.length; i++) {
            if(selectVariantPanels[i].getFieldText().trim().isEmpty()){
                warning = "Внимание! " + (i+1) + " вариант ответа не заполнен!";
                return warning;
            }
        }
        int countRight = this.getCountRightAnswer();
        if(countRight < 1){
            warning = "Должен быть хотя бы один правильный ответ!";
        } else if(countRight == this.getCountAnswer()){
            warning = "Все варианты не могут быть правильными!";
        }
        return warning;
        
    }
}
