/*
 * Этот класс описывает сущность теста
 */
package ua.edu.odeku.pet.database.entry;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

/**
 *
 * @author Aleo
 */
public class Test implements EntryDataBase, Serializable {

    private Integer id_test;
    private Groups groups;
    private Teacher teacher;
    private TypeWork typeWork;
    private Date dateCreate;
    private Date dateLastEdit;
    private Date dateLastUsed;
    private String nameTest;
    private int count_query;
    private Subject subject;
    
    public Test(int id) {
        id_test = id;
    }

    public Test() {
    }

    public Test(int id_test, Groups groups, Teacher teacher, TypeWork typeWork, Date dateCreate, Date dateLastEdit, Date dateLastUsed, String nameTest, int count_query) {
        this.id_test = id_test;
        this.groups = groups;
        this.teacher = teacher;
        this.typeWork = typeWork;
        this.dateCreate = dateCreate;
        this.dateLastEdit = dateLastEdit;
        this.dateLastUsed = dateLastUsed;
        this.nameTest = nameTest;
        this.count_query = count_query;
    }

    public Test(Integer id_test, Groups groups, Teacher teacher, TypeWork typeWork, Date dateCreate, Date dateLastEdit, Date dateLastUsed, String nameTest, int count_query, Subject subject) {
        this.id_test = id_test;
        this.groups = groups;
        this.teacher = teacher;
        this.typeWork = typeWork;
        this.dateCreate = dateCreate;
        this.dateLastEdit = dateLastEdit;
        this.dateLastUsed = dateLastUsed;
        this.nameTest = nameTest;
        this.count_query = count_query;
        this.subject = subject;
    }

    
    
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Test)) {
            return false;
        }
        Test other = (Test) object;
        if ((this.id_test == null && other.id_test != null) || (this.id_test != null && !(this.id_test.equals(other.id_test)))) {
            return false;
        }
        if ((this.nameTest == null && other.nameTest != null) || (this.nameTest != null && !this.nameTest.equals(other.nameTest))) {
            if ((this.teacher == null && other.teacher != null) || (this.teacher != null && !this.teacher.equals(other.teacher))) {
                if ((this.groups == null && other.groups != null) || (this.groups != null && this.groups.equals(other.groups))) {
                    if ((this.typeWork == null && other.typeWork != null) || (this.typeWork != null && this.typeWork.equals(other.typeWork))) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + Objects.hashCode(this.id_test);
        hash = 11 * hash + Objects.hashCode(this.groups);
        hash = 11 * hash + Objects.hashCode(this.teacher);
        hash = 11 * hash + Objects.hashCode(this.typeWork);
        hash = 11 * hash + Objects.hashCode(this.dateCreate);
        hash = 11 * hash + Objects.hashCode(this.dateLastEdit);
        hash = 11 * hash + Objects.hashCode(this.dateLastUsed);
        hash = 11 * hash + Objects.hashCode(this.nameTest);
        hash = 11 * hash + this.count_query;
        return hash;
    }

    @Override
    public String toString() {
        return nameTest + " : "
                + typeWork.getNameTypeWork() + " : "
                + groups.toString() + " : "
                + teacher.toString();
    }

    /**
     * @return the id_test
     */
    public int getId_test() {
        return id_test;
    }

    /**
     * @param id_test the id_test to set
     */
    public void setId_test(int id_test) {
        this.id_test = id_test;
    }

    /**
     * @return the groups
     */
    public Groups getGroups() {
        return groups;
    }

    /**
     * @param groups the groups to set
     */
    public void setGroups(Groups groups) {
        this.groups = groups;
    }

    /**
     * @return the teacher
     */
    public Teacher getTeacher() {
        return teacher;
    }

    /**
     * @param teacher the teacher to set
     */
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    /**
     * @return the typeWork
     */
    public TypeWork getTypeWork() {
        return typeWork;
    }

    /**
     * @param typeWork the typeWork to set
     */
    public void setTypeWork(TypeWork typeWork) {
        this.typeWork = typeWork;
    }

    /**
     * @return the dateCreate
     */
    public Date getDateCreate() {
        return dateCreate;
    }

    /**
     * @param dateCreate the dateCreate to set
     */
    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    /**
     * @return the dateLastEdit
     */
    public Date getDateLastEdit() {
        return dateLastEdit;
    }

    /**
     * @param dateLastEdit the dateLastEdit to set
     */
    public void setDateLastEdit(Date dateLastEdit) {
        this.dateLastEdit = dateLastEdit;
    }

    /**
     * @return the dateLastUsed
     */
    public Date getDateLastUsed() {
        return dateLastUsed;
    }

    /**
     * @param dateLastUsed the dateLastUsed to set
     */
    public void setDateLastUsed(Date dateLastUsed) {
        this.dateLastUsed = dateLastUsed;
    }

    /**
     * @return the nameTest
     */
    public String getNameTest() {
        return nameTest;
    }

    /**
     * @param nameTest the nameTest to set
     */
    public void setNameTest(String nameTest) {
        this.nameTest = nameTest;
    }

    /**
     * @return the count_query
     */
    public int getCount_query() {
        return count_query;
    }

    /**
     * @param count_query the count_query to set
     */
    public void setCount_query(int count_query) {
        this.count_query = count_query;
    }

    /**
     * Метод добавляет в базу данных данный объект
     *
     * @return код результата: -1 - Такие данные уже есть -2 - Произошла вообще
     * ошибка Или количество добавленых записей
     * @throws SQLException
     */
    @Override
    public int insertInto() throws SQLException {
        int result = 0;
        Connection connection = ua.edu.odeku.pet.database.DataBaseConnect.getConnection();
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery("Select * From Test "
                + "Where name_test ='" + nameTest + "' and "
                + "id_subject = " + subject.getIdSubject() + " and "
                + "id_groups = " + groups.getIdGroups() + " and "
                + "id_teacher = " + teacher.getIdTeacher() + " and "
                + "id_type_work = " + typeWork.getIdTypeWork() + ";");
        if (!rs.next()) {
            // Создадим строки для перенесения даты
            
            
            result = st.executeUpdate("insert into Test "
                    + "(name_test, id_teacher, id_groups, id_type_work, id_subject, count_query, date_create, date_last_edit) "
                    + "values('" + nameTest + "',"
                    + " " + teacher.getIdTeacher() + ","
                    + " " + groups.getIdGroups() + ","
                    + " " + typeWork.getIdTypeWork() + ","
                    + " " + subject.getIdSubject() + ","
                    + " " + count_query + ","
                    + " CURDATE (),"
                    + " CURDATE ());");
            /* Тут это не нужно так как мы должны сделать возврат данных если далее будет ошибка
             * connection.commit();
             */
        } else {
            result = -1;
        }
        return result;
    }

    @Override
    public int updateTable(Object object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * @return the subject
     */
    public Subject getSubject() {
        return subject;
    }

    /**
     * @param subject the subject to set
     */
    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public Integer getIdFromDataBase() throws SQLException {
        Integer id = null;
        String sql = "Select id_test from test where ";
        if(nameTest == null || nameTest.isEmpty()){
            return id;
        } else if (subject == null || subject.getIdSubject() == null){
            return id;
        } else if (teacher == null || teacher.getIdTeacher() == null){
            return id;
        } else if(typeWork == null || typeWork.getIdTypeWork() == null){
            return id;
        } else if(groups == null || groups.getIdGroups() == null){
            return id;
        }
        sql += "name_test = '" + nameTest + "' ";
        sql += "and id_subject = " + subject.getIdSubject() + " ";
        sql += "and id_teacher = " + teacher.getIdTeacher() + " ";
        sql += "and id_type_work = " + typeWork.getIdTypeWork() + " ";
        sql += "and id_groups = " + groups.getIdGroups() + " ";
        
        Statement statement = ua.edu.odeku.pet.database.DataBaseConnect.getStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        if (resultSet.next()){
            id = resultSet.getInt(1);
        }
        return id;
    }
}
