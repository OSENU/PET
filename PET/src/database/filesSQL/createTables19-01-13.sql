
drop all objects;

CREATE TABLE PET_CONFIG(
	version			CHAR(100),
	name_version 		CHAR(100)
);

CREATE TABLE Department
(
	id_department        INTEGER NOT NULL AUTO_INCREMENT(1) ,
	name_department      CHAR(100) NOT NULL ,
	id_faculty           INTEGER NOT NULL 
);



CREATE UNIQUE INDEX XPKDepartment ON Department
(id_department   ASC);



ALTER TABLE Department
	ADD CONSTRAINT  XPKDepartment PRIMARY KEY (id_department);



CREATE TABLE Faculty
(
	id_faculty           INTEGER NOT NULL AUTO_INCREMENT(1) ,
	name_faculty         CHAR(100) NOT NULL 
);



CREATE UNIQUE INDEX XPKFaculty ON Faculty
(id_faculty   ASC);



ALTER TABLE Faculty
	ADD CONSTRAINT  XPKFaculty PRIMARY KEY (id_faculty);



CREATE TABLE Groups
(
	id_groups            INTEGER NOT NULL AUTO_INCREMENT(1) ,
	year_supply          DATE NULL ,
	num_group            INTEGER NOT NULL ,
	id_faculty           INTEGER NOT NULL 
);



CREATE UNIQUE INDEX XPKGroups ON Groups
(id_groups   ASC);



ALTER TABLE Groups
	ADD CONSTRAINT  XPKGroups PRIMARY KEY (id_groups);



CREATE TABLE Journal
(
	id_journal           INTEGER NOT NULL AUTO_INCREMENT(1) ,
	id_teacher           INTEGER NOT NULL ,
	id_subject           INTEGER NOT NULL ,
	id_student           INTEGER NOT NULL ,
	id_mark              INTEGER NOT NULL ,
	date_mark            DATE NULL ,
	id_type_work         INTEGER NOT NULL 
);



CREATE UNIQUE INDEX XPKJournal ON Journal
(id_journal   ASC,id_subject   ASC,id_teacher   ASC,id_student   ASC,id_mark   ASC,id_type_work   ASC);



ALTER TABLE Journal
	ADD CONSTRAINT  XPKJournal PRIMARY KEY (id_journal,id_subject,id_teacher,id_student,id_mark,id_type_work);



CREATE TABLE Mark
(
	id_mark              INTEGER NOT NULL AUTO_INCREMENT(1) ,
	name_mark            CHAR(100) NOT NULL ,
	min_persent          INTEGER NOT NULL ,
	max_persent          INTEGER NOT NULL 
);



CREATE UNIQUE INDEX XPKMark ON Mark
(id_mark   ASC);



ALTER TABLE Mark
	ADD CONSTRAINT  XPKMark PRIMARY KEY (id_mark);



CREATE TABLE Question
(
	id_question          INTEGER NOT NULL AUTO_INCREMENT(1) ,
	name_question        CHAR(100) NOT NULL ,
	id_test              INTEGER NOT NULL ,
	id_type_question     INTEGER NOT NULL ,
	from_question        CHAR(100) NOT NULL 
);



CREATE UNIQUE INDEX XPKQuestion ON Question
(id_question   ASC,id_test   ASC,id_type_question   ASC);



ALTER TABLE Question
	ADD CONSTRAINT  XPKQuestion PRIMARY KEY (id_question,id_test,id_type_question);



CREATE TABLE Question_Type
(
	id_type_question     INTEGER NOT NULL AUTO_INCREMENT(1) ,
	name_question_type   CHAR(100) NOT NULL ,
	description_question_type CHAR(100) NOT NULL ,
	name_prog_question_type CHAR(100) NOT NULL 
);



CREATE UNIQUE INDEX XPKQuestion_Type ON Question_Type
(id_type_question   ASC);



ALTER TABLE Question_Type
	ADD CONSTRAINT  XPKQuestion_Type PRIMARY KEY (id_type_question);



CREATE TABLE Student
(
	id_student           INTEGER NOT NULL AUTO_INCREMENT(1) ,
	id_faculty           INTEGER NOT NULL ,
	id_groups            INTEGER NOT NULL ,
	name_student         CHAR(100) NOT NULL ,
	name2_student        CHAR(100) NOT NULL ,
	birthday             DATE NULL ,
	surname_student      CHAR(100) NOT NULL 
);



CREATE UNIQUE INDEX XPKStudent ON Student
(id_student   ASC);



ALTER TABLE Student
	ADD CONSTRAINT  XPKStudent PRIMARY KEY (id_student);



CREATE TABLE Subject
(
	id_subject           INTEGER NOT NULL AUTO_INCREMENT(1) ,
	name_subject         CHAR(100) NOT NULL 
);



CREATE UNIQUE INDEX XPKSubject ON Subject
(id_subject   ASC);



ALTER TABLE Subject
	ADD CONSTRAINT  XPKSubject PRIMARY KEY (id_subject);



CREATE TABLE Teacher
(
	id_teacher           INTEGER NOT NULL AUTO_INCREMENT(1) ,
	name                 CHAR(100) NOT NULL ,
	name2                CHAR(100) NOT NULL ,
	surname              CHAR(100) NOT NULL ,
	id_department        INTEGER NOT NULL 
);



CREATE UNIQUE INDEX XPKTeacher ON Teacher
(id_teacher   ASC);



ALTER TABLE Teacher
	ADD CONSTRAINT  XPKTeacher PRIMARY KEY (id_teacher);



CREATE TABLE Tescher_Subject_Group
(
	id_teacher           INTEGER NOT NULL ,
	id_subject           INTEGER NOT NULL ,
	id_groups            INTEGER NOT NULL 
);



CREATE UNIQUE INDEX XPKTescher_Subject_Group ON Tescher_Subject_Group
(id_teacher   ASC,id_subject   ASC,id_groups   ASC);



ALTER TABLE Tescher_Subject_Group
	ADD CONSTRAINT  XPKTescher_Subject_Group PRIMARY KEY (id_teacher,id_subject,id_groups);



CREATE TABLE Test
(
	id_test              INTEGER NOT NULL AUTO_INCREMENT(1) ,
	id_teacher           INTEGER NOT NULL ,
	id_type_work         INTEGER NOT NULL ,
	id_subject           INTEGER NOT NULL ,
	id_groups            INTEGER NOT NULL ,
	date_create          DATE NULL ,
	date_last_edit       DATE NULL ,
	date_last_used       DATE NULL ,
	name_test            CHAR(100) NOT NULL ,
	count_query          INTEGER NOT NULL 
);



CREATE UNIQUE INDEX XPKTest ON Test
(id_test   ASC);



ALTER TABLE Test
	ADD CONSTRAINT  XPKTest PRIMARY KEY (id_test);



CREATE TABLE Type_Work
(
	id_type_work         INTEGER NOT NULL AUTO_INCREMENT(1) ,
	name_type_work       CHAR(100) NOT NULL 
);



CREATE UNIQUE INDEX XPKType_Work ON Type_Work
(id_type_work   ASC);



ALTER TABLE Type_Work
	ADD CONSTRAINT  XPKType_Work PRIMARY KEY (id_type_work);



CREATE TABLE Answer
(
	id_answer            INTEGER NOT NULL AUTO_INCREMENT(1) ,
	name_answer          CHAR(100) NOT NULL ,
	is_Picture           INTEGER NOT NULL ,
	picture              BLOB NULL ,
	is_right_answer      INTEGER NOT NULL ,
	id_question          INTEGER NOT NULL ,
	id_test              INTEGER NOT NULL ,
	id_type_question     INTEGER NOT NULL 
);



CREATE UNIQUE INDEX XPKAnswer ON Answer
(id_answer   ASC,id_question   ASC,id_test   ASC,id_type_question   ASC);



ALTER TABLE Answer
	ADD CONSTRAINT  XPKAnswer PRIMARY KEY (id_answer,id_question,id_test,id_type_question);



ALTER TABLE Department
	ADD CONSTRAINT R_23 FOREIGN KEY (id_faculty) REFERENCES Faculty (id_faculty);



ALTER TABLE Groups
	ADD CONSTRAINT R_19 FOREIGN KEY (id_faculty) REFERENCES Faculty (id_faculty);



ALTER TABLE Journal
	ADD CONSTRAINT R_4 FOREIGN KEY (id_teacher) REFERENCES Teacher (id_teacher);



ALTER TABLE Journal
	ADD CONSTRAINT R_5 FOREIGN KEY (id_subject) REFERENCES Subject (id_subject);



ALTER TABLE Journal
	ADD CONSTRAINT R_6 FOREIGN KEY (id_student) REFERENCES Student (id_student);



ALTER TABLE Journal
	ADD CONSTRAINT R_7 FOREIGN KEY (id_mark) REFERENCES Mark (id_mark);



ALTER TABLE Journal
	ADD CONSTRAINT R_8 FOREIGN KEY (id_type_work) REFERENCES Type_Work (id_type_work);



ALTER TABLE Question
	ADD CONSTRAINT R_20 FOREIGN KEY (id_test) REFERENCES Test (id_test);



ALTER TABLE Question
	ADD CONSTRAINT R_21 FOREIGN KEY (id_type_question) REFERENCES Question_Type (id_type_question);



ALTER TABLE Student
	ADD CONSTRAINT R_2 FOREIGN KEY (id_faculty) REFERENCES Faculty (id_faculty);



ALTER TABLE Student
	ADD CONSTRAINT R_3 FOREIGN KEY (id_groups) REFERENCES Groups (id_groups);



ALTER TABLE Teacher
	ADD CONSTRAINT R_18 FOREIGN KEY (id_department) REFERENCES Department (id_department);



ALTER TABLE Tescher_Subject_Group
	ADD CONSTRAINT R_10 FOREIGN KEY (id_teacher) REFERENCES Teacher (id_teacher);



ALTER TABLE Tescher_Subject_Group
	ADD CONSTRAINT R_11 FOREIGN KEY (id_subject) REFERENCES Subject (id_subject);



ALTER TABLE Tescher_Subject_Group
	ADD CONSTRAINT R_12 FOREIGN KEY (id_groups) REFERENCES Groups (id_groups);



ALTER TABLE Test
	ADD CONSTRAINT R_13 FOREIGN KEY (id_teacher) REFERENCES Teacher (id_teacher);



ALTER TABLE Test
	ADD CONSTRAINT R_14 FOREIGN KEY (id_type_work) REFERENCES Type_Work (id_type_work);



ALTER TABLE Test
	ADD CONSTRAINT R_15 FOREIGN KEY (id_subject) REFERENCES Subject (id_subject);



ALTER TABLE Test
	ADD CONSTRAINT R_16 FOREIGN KEY (id_groups) REFERENCES Groups (id_groups);



ALTER TABLE Answer
	ADD CONSTRAINT R_22 FOREIGN KEY (id_question, id_test, id_type_question) REFERENCES Question (id_question, id_test, id_type_question);


