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
public class Question implements EntryDataBase{
    // код вопроса
    private Integer idQuestion;
    // код типа вопроса
    private TypeQuestion typeQuestion;
    // код теста к которому относиться вопрос 
    private Test test;
    // Сам вопрос
    private String nameQuestion;

    public Question() {
    }

    public Question(Integer idQuestion, TypeQuestion typeQuestion, Test test, String nameQuestion) {
        this.idQuestion = idQuestion;
        this.typeQuestion = typeQuestion;
        this.test = test;
        this.nameQuestion = nameQuestion;
    }

    
    /**
     * @return the idQuestion
     */
    public Integer getIdQuestion() {
        return idQuestion;
    }

    /**
     * @param idQuestion the idQuestion to set
     */
    public void setIdQuestion(Integer idQuestion) {
        this.idQuestion = idQuestion;
    }

    /**
     * @return the typeQuestion
     */
    public TypeQuestion getTypeQuestion() {
        return typeQuestion;
    }

    /**
     * @param typeQuestion the typeQuestion to set
     */
    public void setTypeQuestion(TypeQuestion typeQuestion) {
        this.typeQuestion = typeQuestion;
    }

    /**
     * @return the idTest
     */
    public Test getIdTest() {
        return test;
    }

    /**
     * @param idTest the idTest to set
     */
    public void setIdTest(Test idTest) {
        this.test = idTest;
    }

    /**
     * @return the nameQuestion
     */
    public String getNameQuestion() {
        return nameQuestion;
    }

    /**
     * @param nameQuestion the nameQuestion to set
     */
    public void setNameQuestion(String nameQuestion) {
        this.nameQuestion = nameQuestion;
    }

    @Override
    public int insertInto() throws SQLException {
        int result = -1;
        Connection connection = ua.edu.odeku.pet.database.ConnectionDataBase.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("Select id_question "
                + "from Question "
                + "where name_question = '" + nameQuestion + "' "
                + "and id_test =" + test.getId_test() + " "
                + "and id_type_question = " + typeQuestion.getIdTypeQuestion() + ";");
        
        if (!resultSet.next()) {
            String sql = "insert into Question(name_question, id_test, id_type_question) ";
            sql += "values('" + nameQuestion + "', " + test.getId_test() + ", " + typeQuestion.getIdTypeQuestion() + ");";
            result = statement.executeUpdate(sql);
        }
        return result;
    }

    @Override
    public int updateTable(Object object) throws SQLException {
        int result;
        if (!(object instanceof Question)) {
            return -2;
        }
        Question newQuestion = (Question) object;
        
        Connection conn = ua.edu.odeku.pet.database.ConnectionDataBase.getConnection();
        Statement st = conn.createStatement();
        // Формируем запрос на проверку
        ResultSet rs = st.executeQuery("Select id__question "
                + "from Question "
                + "where name_question = '" + newQuestion.nameQuestion + "' "
                + "and id_test = " + newQuestion.getIdTest().getId_test() + " "
                + "and id_type_question = " + newQuestion.getTypeQuestion() + " "
                + "and id_question <> " + idQuestion + " ;");
        if (!rs.next()) {
            // Значит в базе такого значения нет
            String s = "update Question "
                    + "set name_question = '" + newQuestion.getNameQuestion() + "', "
                    + "id_test = "+newQuestion.getIdTest() + ", "
                    + "id_type_question = " + newQuestion.getTypeQuestion().getIdTypeQuestion();
            
            s += " where id_question = " + idQuestion + " ;";
            result = st.executeUpdate(s);
        } else {
            result = -1;
        }

        return result;
    }

    @Override
    public Integer getIdFromDataBase() throws SQLException {
        Statement statement = ua.edu.odeku.pet.database.ConnectionDataBase.getStatement();
        if(nameQuestion == null || nameQuestion.isEmpty()){
            return null;
        } else if(test == null || test.getId_test() == null){
            return null;
        } else if (typeQuestion == null || typeQuestion.getIdTypeQuestion() == null){
            return null;
        }
        ResultSet resultSet = statement.executeQuery("Select ID_QUESTION  "
                + "from question "
                + "where name_question = '"+nameQuestion+"' "
                + "and id_test = " + test.getId_test() + " "
                + "and id_type_question = " + typeQuestion.getIdTypeQuestion() + "; ");
        if(resultSet.next()){
            Integer id = resultSet.getInt(1);
            statement.close();
            return id;
        } else {
            return null;
        }
    }
    
    
}
