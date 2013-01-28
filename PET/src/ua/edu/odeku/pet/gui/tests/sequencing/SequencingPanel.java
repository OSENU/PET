/*
 * Панель для создания и редактирования тестов на определении правильной последовательности.
 */
package ua.edu.odeku.pet.gui.tests.sequencing;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import ua.edu.odeku.pet.gui.tests.Questionable;
import ua.edu.odeku.pet.util.SMS;

/**
 *
 * @author Aleo
 */
public class SequencingPanel extends javax.swing.JPanel implements Questionable{
    ItemSequencingPanel[] itemSequencingPanels;
    /**
     * Creates new form SequencingPanel
     */
    public SequencingPanel() {
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
        jTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jSpinner = new javax.swing.JSpinner();
        jButtonCreate = new javax.swing.JButton();
        jScrollPane = new javax.swing.JScrollPane();
        jPanel = new javax.swing.JPanel();

        jLabel1.setText("Задание:");

        jLabel2.setText("Количество элиментов:");

        jSpinner.setModel(new javax.swing.SpinnerNumberModel(2, 2, 10, 1));

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
            .addGap(0, 479, Short.MAX_VALUE)
        );
        jPanelLayout.setVerticalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 304, Short.MAX_VALUE)
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
                        .addComponent(jTextField))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(jTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCreate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCreateActionPerformed
        int countItem = Integer.valueOf(jSpinner.getValue().toString());
        if(itemSequencingPanels != null){
            if(!SMS.query("Данные будут потеряны!\nПродолжить?")){
                return;
            }
        }
        itemSequencingPanels = new ItemSequencingPanel[countItem];
        jPanel.removeAll();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));
        for (int i = 0; i < countItem; i++) {
            itemSequencingPanels[i]= new ItemSequencingPanel();
            // Установим надпись
            itemSequencingPanels[i].setjLabel((i+1) + " " + itemSequencingPanels[i].getjLabel());
            jPanel.add(itemSequencingPanels[i]);
        }
        itemSequencingPanels[countItem-1].setjSeparator(false);
        jScrollPane.revalidate();
        jScrollPane.setVerticalScrollBarPolicy(javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setHorizontalScrollBarPolicy(javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }//GEN-LAST:event_jButtonCreateActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCreate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JSpinner jSpinner;
    private javax.swing.JTextField jTextField;
    // End of variables declaration//GEN-END:variables

    @Override
    public String saveQuestion(Integer idTest) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public JPanel loadQuestion(long idItem) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getTask() {
        return this.jTextField.getText();
    }

    @Override
    public String getTypeQuestion() {
        return "Sequencing";
    }

    @Override
    public int getCountAnswer() {
        return jPanel.getComponentCount();
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
        if(this.getTask().trim().isEmpty()){
            warning = "Введите текст задания";
        } else if(itemSequencingPanels == null) {
            warning = "Не создана ни одна последовательность";
        } else {
            for (int i = 0; i < itemSequencingPanels.length; i++) {
                if(itemSequencingPanels[i].getjTextField().trim().isEmpty()){
                    warning = (i+1) + " участок последовательности не заполнен";
                    break;
                }
            }
        }
        return warning;
    }
}
