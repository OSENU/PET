/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.tests.alternativeChoice;

import gui.tests.ItemTest;
import javax.swing.JPanel;

/**
 *
 * @author Aleo
 */
public class AlternativeChoicePanel extends javax.swing.JPanel implements ItemTest{

    /**
     * Creates new form AlternativeChoicePanel
     */
    public AlternativeChoicePanel() {
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

        buttonGroup = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldAsk = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();

        jLabel1.setText("Утверждение:");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Оно правильное?"));

        buttonGroup.add(jRadioButton1);
        jRadioButton1.setText("Да");

        buttonGroup.add(jRadioButton2);
        jRadioButton2.setText("Нет");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jRadioButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jRadioButton1)
                .addComponent(jRadioButton2))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 162, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldAsk)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldAsk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JTextField jTextFieldAsk;
    // End of variables declaration//GEN-END:variables

    @Override
    public String saveItemTest(Long idTest) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public JPanel loadItemTest(long idItem) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getTask() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getTypeTask() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getCountVariant() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getCountRightVariant() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void removeItemTest() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void removeVariant(int idVariant) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String checkToPrepare() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
