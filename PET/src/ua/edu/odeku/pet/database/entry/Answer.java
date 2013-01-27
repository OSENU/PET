/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.odeku.pet.database.entry;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Aleo
 */
public class Answer implements EntryDataBase {
    
    private Integer idAnswer;
    private Question question;
    private String nameAnswer;
    private Boolean isRightAnswer;
    
    public Answer() {
    }

    public Answer(Integer idAnswer) {
        this.idAnswer = idAnswer;
        this.isRightAnswer = false;
    }

    public Answer(Integer idAnswer, Question question) {
        this.idAnswer = idAnswer;
        this.question = question;
        this.isRightAnswer = false;
    }

    public Answer(Integer idAnswer, Question question, String nameAnswer) {
        this.idAnswer = idAnswer;
        this.question = question;
        this.nameAnswer = nameAnswer;
        this.isRightAnswer = false;
    }

    public Answer(Integer idAnswer, Question question, String nameAnswer, Boolean isRightAnswer) {
        this.idAnswer = idAnswer;
        this.question = question;
        this.nameAnswer = nameAnswer;
        this.isRightAnswer = isRightAnswer;
    }

    /**
     * @return the idAnswer
     */
    public Integer getIdAnswer() {
        return idAnswer;
    }

    /**
     * @param idAnswer the idAnswer to set
     */
    public void setIdAnswer(Integer idAnswer) {
        this.idAnswer = idAnswer;
    }

    /**
     * @return the question
     */
    public Question getQuestion() {
        return question;
    }

    /**
     * @param question the question to set
     */
    public void setQuestion(Question question) {
        this.question = question;
    }

    /**
     * @return the nameAnswer
     */
    public String getNameAnswer() {
        return nameAnswer;
    }

    /**
     * @param nameAnswer the nameAnswer to set
     */
    public void setNameAnswer(String nameAnswer) {
        this.nameAnswer = nameAnswer;
    }

    /**
     * @return the isRightAnswer
     */
    public Boolean getIsRightAnswer() {
        return isRightAnswer;
    }

    /**
     * @param isRightAnswer the isRightAnswer to set
     */
    public void setIsRightAnswer(Boolean isRightAnswer) {
        this.isRightAnswer = isRightAnswer;
    }

    @Override
    public int insertInto() throws SQLException {
        int ret = -1;
        int isRight = (isRightAnswer) ? 1 : 0;
        Statement statement = ua.edu.odeku.pet.database.ConnectionDataBase.getStatement();
        ResultSet rs = statement.executeQuery("Select id_answer "
                + "from answer "
                + "where id_question = " + question.getIdQuestion() + " "
                + "and name_answer = '"+nameAnswer+"' "
                + ";");
        if(!rs.next()){
            String sql = "insert into answer(name_answer, id_question, is_right_answer)"
                    + " values('" + nameAnswer + "'," + question.getIdQuestion() + ", " + isRight + ")";
            ret = statement.executeUpdate(sql);
        }
        return ret;
    }

    @Override
    public int updateTable(Object object) throws SQLException {
        int result;
        if (!(object instanceof Answer)) {
            return -2;
        }
        Answer newAnswer = (Answer) object;
        int isRight = (newAnswer.isRightAnswer) ? 1 : 0;
        Connection conn = ua.edu.odeku.pet.database.ConnectionDataBase.getConnection();
        Statement statement = conn.createStatement();
        // Формируем запрос на проверку
        ResultSet rs = statement.executeQuery("Select id_answer "
                + "from answer "
                + "where id_question = " + newAnswer.question.getIdQuestion() + " "
                + "and name_answer = '"+newAnswer.nameAnswer+"' "
                + "and id_answer <>" + idAnswer +";");
        if (!rs.next()) {
            // Значит в базе такого значения нет
            String s = "update Answer "
                    + "set name_answer = '" + newAnswer.nameAnswer + "' "
                    + ", id_question = "+newAnswer.question.getIdQuestion()+" "
                    + ", is_right_answer = " + isRight + " "
                    + "where id_answer = " + idAnswer + ";";
            result = statement.executeUpdate(s);
        } else {
            result = -1;
        }

        return result;
    }

    @Override
    public Integer getIdFromDataBase() throws SQLException {
        Integer id = null;
        if(nameAnswer == null || nameAnswer.isEmpty()){
            return id;
        } else if(question == null || question.getIdTest() == null){
            return id;
        }
        Statement statement = ua.edu.odeku.pet.database.ConnectionDataBase.getStatement();
        ResultSet rs = statement.executeQuery("Select id_answer "
                + "from answer "
                + "where id_question = " + question.getIdQuestion() + " "
                + "and name_answer = '"+nameAnswer+"'; ");
        if(rs.next()){
            id = rs.getInt(1);
        }
        
        return id;
    }
    
    
    
}
