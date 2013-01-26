package ua.edu.odeku.pet.database.entry;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Aleo
 */
public class TypeQuestion implements EntryDataBase {

    private Integer idTypeQuestion;
    private String nameTypeQuestion;
    private String descriptionQuestionType;
    private String nameProgQuestionType;

    public TypeQuestion() {
    }

    public TypeQuestion(Integer idTypeQuestion, String nameTypeQuestion) {
        this.idTypeQuestion = idTypeQuestion;
        this.nameTypeQuestion = nameTypeQuestion;
    }

    @Override
    public String toString() {
        return nameTypeQuestion + " : " + nameProgQuestionType;
    }

    public TypeQuestion(Integer idTypeQuestion, String nameTypeQuestion, String descriptionQuestionType) {
        this.idTypeQuestion = idTypeQuestion;
        this.nameTypeQuestion = nameTypeQuestion;
        this.descriptionQuestionType = descriptionQuestionType;
    }

    public TypeQuestion(Integer idTypeQuestion, String nameTypeQuestion, String descriptionQuestionType, String nameProgQuestionType) {
        this.idTypeQuestion = idTypeQuestion;
        this.nameTypeQuestion = nameTypeQuestion;
        this.descriptionQuestionType = descriptionQuestionType;
        this.nameProgQuestionType = nameProgQuestionType;
    }

    /**
     * @return the idTypeQuestion
     */
    public Integer getIdTypeQuestion() {
        return idTypeQuestion;
    }

    /**
     * @param idTypeQuestion the idTypeQuestion to set
     */
    public void setIdTypeQuestion(Integer idTypeQuestion) {
        this.idTypeQuestion = idTypeQuestion;
    }

    /**
     * @return the nameTypeQuestion
     */
    public String getNameTypeQuestion() {
        return nameTypeQuestion;
    }

    /**
     * @param nameTypeQuestion the nameTypeQuestion to set
     */
    public void setNameTypeQuestion(String nameTypeQuestion) {
        this.nameTypeQuestion = nameTypeQuestion;
    }

    /**
     * @return the descriptionQuestionType
     */
    public String getDescriptionQuestionType() {
        return descriptionQuestionType;
    }

    /**
     * @param descriptionQuestionType the descriptionQuestionType to set
     */
    public void setDescriptionQuestionType(String descriptionQuestionType) {
        this.descriptionQuestionType = descriptionQuestionType;
    }

    /**
     * @return the nameProgQuestionType
     */
    public String getNameProgQuestionType() {
        return nameProgQuestionType;
    }

    /**
     * @param nameProgQuestionType the nameProgQuestionType to set
     */
    public void setNameProgQuestionType(String nameProgQuestionType) {
        this.nameProgQuestionType = nameProgQuestionType;
    }

    @Override
    public int insertInto() throws SQLException {
        int result = -1;
        Connection connection = ua.edu.odeku.pet.database.DataBaseConnect.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("Select id_type_question "
                + "from Question_Type "
                + "where name_prog_question_type = '" + nameProgQuestionType + "';");
        if (!resultSet.next()) {
            String sql = "insert into Question_Type(name_question_type, description_question_type, name_prog_question_type) ";
            sql += "values('" + nameTypeQuestion + "', '" + descriptionQuestionType + "','" + nameProgQuestionType + "');";

            result = statement.executeUpdate(sql);
            connection.commit();
        }
        return result;
    }

    @Override
    public int updateTable(Object object) throws SQLException {
        int result;
        if (!(object instanceof TypeQuestion)) {
            return -2;
        }
        TypeQuestion newTypeQuestion = (TypeQuestion) object;
        
        Connection conn = ua.edu.odeku.pet.database.DataBaseConnect.getConnection();
        Statement st = conn.createStatement();
        // Формируем запрос на проверку
        ResultSet rs = st.executeQuery("Select id_type_question "
                + "from Question_Type "
                + "where name_prog_question_type = '" + newTypeQuestion.nameProgQuestionType + "' "
                + "and id_type_question <> " + idTypeQuestion + " ;");
        if (!rs.next()) {
            // Значит в базе такого значения нет
            String s = "update Question_Type "
                    + "set name_prog_question_type = '" + newTypeQuestion.nameProgQuestionType + "'";
            if(newTypeQuestion.descriptionQuestionType != null){
                s+=", description_question_type = '"+newTypeQuestion.descriptionQuestionType + "'";
            }
            if(newTypeQuestion.nameTypeQuestion != null){
                s+=", name_question_type = '"+newTypeQuestion.nameTypeQuestion + "'";
            }
            s += " where id_type_question = " + idTypeQuestion + " ;";
            result = st.executeUpdate(s);
            conn.commit();
        } else {
            result = -1;
        }

        return result;
    }

    @Override
    public Integer getIdFromDataBase() throws SQLException {
        Connection connection = ua.edu.odeku.pet.database.DataBaseConnect.getConnection();
        Statement statement = connection.createStatement();
        if(nameProgQuestionType == null || nameProgQuestionType.isEmpty()){
            return null;
        } else if(nameTypeQuestion == null || nameTypeQuestion.isEmpty()){
            return null;
        }
        ResultSet rs = statement.executeQuery("Select id_type_question from Question_Type "
                + "where name_prog_question_type = '"+nameProgQuestionType+"';");
        if(rs.next()){
            Integer id = rs.getInt(1);
            return id;
        } else {
            return null;
        }
    }
}
