/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.odeku.pet.gui.tests.alternativeChoice;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import ua.edu.odeku.pet.database.entry.Answer;
import ua.edu.odeku.pet.database.entry.Question;
import ua.edu.odeku.pet.database.entry.TypeQuestion;
import ua.edu.odeku.pet.gui.tests.Answerable;
import ua.edu.odeku.pet.gui.tests.Questionable;

/**
 *
 * @author Aleo
 */
public class AlternativePanel extends javax.swing.JPanel implements Questionable, Answerable{
    Question question;
    Answer answer;
    /**
     * Creates new form AlternativePanel
     */
    public AlternativePanel() {
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldAsk = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jRadioButtonYes = new javax.swing.JRadioButton();
        jRadioButtonNo = new javax.swing.JRadioButton();

        jLabel1.setText("Утверждение:");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Это истина?"));

        buttonGroup1.add(jRadioButtonYes);
        jRadioButtonYes.setSelected(true);
        jRadioButtonYes.setText("Да");

        buttonGroup1.add(jRadioButtonNo);
        jRadioButtonNo.setText("Нет");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jRadioButtonYes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButtonNo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jRadioButtonYes)
                .addComponent(jRadioButtonNo))
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
                        .addGap(0, 163, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldAsk)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldAsk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButtonNo;
    private javax.swing.JRadioButton jRadioButtonYes;
    private javax.swing.JTextField jTextFieldAsk;
    // End of variables declaration//GEN-END:variables

    @Override
    public String saveQuestion(ua.edu.odeku.pet.database.entry.Test test) {
        if (test == null || test.getId_test() == null){
            return "Для сохранения вопроса необходимо передать объект теста";
        }
        question = new Question();
        question.setIdTest(test);
        question.setNameQuestion(this.getTask());
        question.setTypeQuestion(this.getTypeQuestion());
        try {
            // Сохраним тест
            int ret = question.insertInto();
            if (ret == -1){
                return "Такое задание для этого теста уже есть в базе";
            }
        } catch (SQLException ex) {
            Logger.getLogger(AlternativePanel.class.getName()).log(Level.SEVERE, null, ex);
            return ex.toString();
        }
        try {
            // Получим код теста
            question.setIdQuestion(question.getIdFromDataBase());
        } catch (SQLException ex) {
            Logger.getLogger(AlternativePanel.class.getName()).log(Level.SEVERE, null, ex);
            return ex.toString();
        }
        return this.saveAnswer(question);
    }

    @Override
    public JPanel loadQuestion(long idItem) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getTask() {
        return jTextFieldAsk.getText();
    }

    @Override
    public TypeQuestion getTypeQuestion() {
        TypeQuestion typeQuestion = new TypeQuestion();
        typeQuestion.setIdTypeQuestion(2);
        typeQuestion.setNameProgQuestionType("AlternativeChoice");
        return typeQuestion;
    }

    @Override
    public int getCountAnswer() {
        return 2;
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
        this.removeAnswer();
    }

    @Override
    public String checkToPrepareQuestion() {
        String warning = null;
        if(this.getTask().trim().isEmpty()){
            warning = "Укажите утверждение!";
        }
        return warning;
    }

    @Override
    public String saveAnswer(Question question) {
        if(question == null){
            return "Не передан объект вопроса";
        }
        if (question.getIdTest() == null){
            return "В переданом объекте отсутствует ссылка на тест";
        }
        if (question.getIdQuestion() == null){
            return "В переданом объекте вопроса нет его кода";
        }
        answer = new Answer();
        answer.setQuestion(question);
        answer.setIsRightAnswer(this.isRightAnswer());
        answer.setNameAnswer(this.getTextAnswer());
        try {
            int ret = answer.insertInto();
            if (ret == -1) {
                return "Такой Ответ на вопрос уже есть";
            }
        } catch (SQLException ex) {
            return ex.toString();
        }
        try {
            answer.setIdAnswer(answer.getIdFromDataBase());
        } catch (SQLException ex) {
            Logger.getLogger(AlternativePanel.class.getName()).log(Level.SEVERE, null, ex);
            return ex.toString();
        }
        return null;
    }

    @Override
    public String loadAnswer(Integer idAnswer) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getTextAnswer() {
        if (isRightAnswer()){
            return "Да";
        } else {
            return "Нет";
        }
    }

    @Override
    public String removeAnswer() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String checkToPrepareAnswer() {
        return null;
    }

    @Override
    public boolean isRightAnswer() {
        return jRadioButtonYes.isSelected();
    }
}
