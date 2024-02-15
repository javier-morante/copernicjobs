
SHOW DATABASES;

DROP DATABASE IF EXISTS abp_project_2;

CREATE DATABASE abp_project_2;

USE abp_project_2;

/* ---------- Degree Table ---------- */

CREATE TABLE degree(
    degree_id                                   INT                  AUTO_INCREMENT,
    name                                        VARCHAR(50),
    PRIMARY KEY(degree_id)
);

/* ---------- User Tables ---------- */

CREATE TABLE user(
	user_id				                        INT				PRIMARY KEY		AUTO_INCREMENT,
    nif											VARCHAR(50)		NOT NULL		UNIQUE,
    name				                        VARCHAR(50)		NOT NULL,
    email		       		                    VARCHAR(50)		NOT NULL		UNIQUE,
    phone				                        VARCHAR(50)		,
    password			                        TEXT		NOT NULL,
    name_role                                   ENUM('STUDENT','COMPANY','ADMINISTRATOR'),
    last_login			                        DATE,
    is_enabled			                        BOOLEAN
);


/* ---------- Student Tables ---------- */

CREATE TABLE student(
	user_id							            INT,
	surname							            VARCHAR(50)		NOT NULL,
    
    /* Privacity Settings */
    public_email								BOOLEAN			DEFAULT true,
    public_phone					            BOOLEAN			DEFAULT true,
    public_linkedin_url				            BOOLEAN			DEFAULT false,
    public_portfolio_url			            BOOLEAN			DEFAULT false,
    public_youtube_url				            BOOLEAN			DEFAULT	false,
    public_github_url				            BOOLEAN			DEFAULT false,
    
    /* Notifications Settings */
    new_offer_notification			            BOOLEAN			DEFAULT true,
    new_inscription_notification	            BOOLEAN			DEFAULT true,
    
    PRIMARY KEY(user_id),
	CONSTRAINT fk_student_user_user_id FOREIGN KEY(user_id) REFERENCES user(user_id) 	ON UPDATE CASCADE	ON DELETE CASCADE
);

CREATE TABLE student_professional_profile(
	student_professional_profile_id		        INT				PRIMARY KEY		AUTO_INCREMENT,
    curriculum_path						        TEXT,
    linkedin_url						        TEXT,
    portfolio_url						        TEXT,
    youtube_url							        TEXT,
    github_url							        TEXT,
    student_id							        INT             NOT NULL,
    degree_id                                   INT,
    CONSTRAINT fk_studentPP_degree_degree_id FOREIGN KEY(degree_id) REFERENCES degree(degree_id) ON UPDATE CASCADE	ON DELETE CASCADE,
	CONSTRAINT fk_student_professional_profile_student_student_id FOREIGN KEY(student_id) REFERENCES student(user_id)	ON UPDATE CASCADE	ON DELETE CASCADE
);

/* ---------- Company Tables ---------- */

CREATE TABLE company(
	user_id									    INT,
    description									TEXT,
    icon_path									TEXT,
    web_page_url								TEXT,
    
    /* Notification Settings */
    resolved_incidence_notification				boolean			DEFAULT true,
    
    PRIMARY KEY(user_id),
   	CONSTRAINT fk_company_user_user_id FOREIGN KEY(user_id) REFERENCES user(user_id)	ON UPDATE CASCADE	ON DELETE CASCADE
);
      
INSERT INTO degree(name) VALUES
	("DAM"),
    ("DAW"),
    ("ASIX"),
    ("SMIX"),
    ("OTHER");

/* ----- Offers Tables -----*/

CREATE TABLE offer(
	offer_id							        INT											PRIMARY KEY		AUTO_INCREMENT,
    title								        VARCHAR(80)									NOT NULL,
    location							        TEXT,
    short_description					        VARCHAR(255)								NOT NULL,
    requirements_description			        TEXT										NOT NULL,
    functions_description				        TEXT,           
    technologies_description			        TEXT										NOT NULL,
    urgency								        ENUM('HIGH', 'MEDIUM', 'LOW')			    DEFAULT 'LOW',
    workday								        ENUM('FULL', 'HALF', 'FLEXIBLE', 'MORNING', 'AFTERNOON', 'NIGHT'),
    salary_interval						        VARCHAR(50),
    vacancies_number					        INT,
    publishment_date					        DATE,
    is_enabled							        boolean,
    creation_date						        DATE,
    degree_id									INT,
    user_id                                     INT,
	CONSTRAINT fk_offer_company_company_id FOREIGN KEY(user_id) REFERENCES user(user_id)	ON UPDATE CASCADE	ON DELETE CASCADE,
	CONSTRAINT fk_offer_degree_degree_id FOREIGN KEY(degree_id) REFERENCES degree(degree_id) ON UPDATE CASCADE	ON DELETE CASCADE
);

CREATE TABLE enrolled_students_offer(
    student_id						            INT,
    offer_id						            INT,
    enrollment_date					            DATE,

    PRIMARY KEY(student_id,offer_id),
	CONSTRAINT fk_enrolled_students_offer_student_student_id FOREIGN KEY(student_id) REFERENCES student(user_id)	ON UPDATE CASCADE	ON DELETE CASCADE,
	CONSTRAINT fk_enrolled_students_offer_offer_offer_id FOREIGN KEY(offer_id) REFERENCES offer(offer_id)		ON UPDATE CASCADE	ON DELETE CASCADE
); 


/* ---------- Administrator Tables ---------- */

CREATE TABLE administrator(
    user_id									    INT,
    new_access_request_notification			    BOOLEAN		DEFAULT true,
    new_report_notification					    BOOLEAN		DEFAULT true,
    PRIMARY KEY(user_id),
	CONSTRAINT fk_administrator_user_user_id FOREIGN KEY(user_id) REFERENCES user(user_id)	ON UPDATE CASCADE	ON DELETE CASCADE
);

/* ---------- Register and password recovery Request System Cache ---------- */

/* ----- Company Cache ----- */
CREATE TABLE register_request(
	request_id				    				INT												PRIMARY KEY				AUTO_INCREMENT,
    company_name								VARCHAR(50)										NOT NULL,
	email									    VARCHAR(50)										NOT NULL,
	contact_phone	 						    VARCHAR(50)										NOT NULL,
    nif										    VARCHAR(50)										NOT NULL,
    password									TEXT											NOT NULL,
    status									    ENUM('VALIDATED', 'WAITING' ,'REJECTED')		DEFAULT 'WAITING'
);

SELECT * FROM register_request;

/* ---------- Password Recovery ---------- */

CREATE TABLE user_password_reset_request(
	user_password_reset_request_id			    INT					PRIMARY KEY			AUTO_INCREMENT,
    email									    VARCHAR(50),
    code                                        VARCHAR(10),
    status									    ENUM('VALIDATED', 'WAITING'),
    request_date								DATE
);

SELECT * FROM user_password_reset_request;


/* ---------- Role Management ---------- */

CREATE TABLE permissions(
    permission_id 		                        INT						AUTO_INCREMENT,
    permission_name		                        VARCHAR(50),
    /*
		Company Modules => MY_OFFERS , CREATE_OFFER, INCIDENCES, OFFER_REPORTS
        Student Modules => LABORAL_INFORMATION, OFFERS, MY_INSCRIPTIONS
        Administrator Modules => REQUESTS, OFFERS, CREATE_OFFER, ROLES, USERS, REPORTS, APP_REPORTS
    */
    role_name			                        ENUM('STUDENT','COMPANY','ADMINISTRATOR'),
    is_enabled			                        BOOLEAN,
    
    PRIMARY KEY(permission_id)
);


CREATE TABLE incidence (
	incidence_id									INT						PRIMARY KEY			AUTO_INCREMENT,
    title										VARCHAR(50),
    description									TEXT,
    category									ENUM("ERROR", "USER", "SERVICE", "UI"),
    status                                      ENUM("PENDING","SOLVED"),
    creation_date                               DATE,
    user_id										INT,
    CONSTRAINT fk_user_incidence_user_id FOREIGN KEY(user_id) REFERENCES user(user_id)		ON UPDATE CASCADE 		ON DELETE CASCADE
)

-- INSERT INTO permissions VALUE(null,'si','STUDENT',false);

-- INSERT INTO user VALUE (NULL,'juan','asdassd','asdas','asdasdda','STUDENT',NULL,FALSE);

-- SELECT * FROM abp_project_2.user e INNER JOIN permissions a ON e.name_role = a.role_name;




/*
Procedure for deleting all the registers of the table user_password_reset_request that 
have past the expiration date
*/
DELIMITER //

CREATE PROCEDURE delete_expired_entries()
BEGIN
    DECLARE limit_date date;
    SET limit_date = NOW() - INTERVAL 30 SECOND;
    DELETE FROM user_password_reset_request WHERE request_date < limit_date;
END //

-- Trigger for the expired entries inside of the
CREATE TRIGGER delete_expired_password_recovery_entries
AFTER INSERT ON user_password_reset_request
FOR EACH ROW
BEGIN
	-- Call to the procedure
    CALL delete_expired_entries();
END;

DELIMITER ;

/* User 1 Generation -------------------------------------------------- */

-- Insertar datos de prueba en la tabla 'user'
INSERT INTO user (name, nif, email, phone, password, name_role, last_login, is_enabled)
VALUES ('Pedro', "1", "test1@gmail.com", '123456789', '$2a$10$RZWaelw7HlgPDX6pbOU0ieQHT2TfN8nnCYhOrynQhw2jZsCkzU6nK', 'STUDENT', '2024-01-16', true);


SELECT * FROM user;
SELECT * FROM student;
INSERT INTO student(user_id, surname) VALUES (1, "Jimenez");

/* User 2 Generation -------------------------------------------------- */

INSERT INTO user (name, nif, email, phone, password, name_role, last_login, is_enabled)
VALUES ('Jose', "2", 'test2@gmail.com', '123456789', '$2a$10$RZWaelw7HlgPDX6pbOU0ieQHT2TfN8nnCYhOrynQhw2jZsCkzU6nK', 'ADMINISTRATOR', '2024-01-16', true);

INSERT INTO administrator(user_id) VALUES (2);

SELECT * FROM user;
SELECT * FROM administrator;

/* User 3 Generation -------------------------------------------------- */

INSERT INTO user (name, nif, email, phone, password, name_role, last_login, is_enabled)
VALUES ('Juanjo', "3", 'test3@gmail.com', '123456789', '$2a$10$RZWaelw7HlgPDX6pbOU0ieQHT2TfN8nnCYhOrynQhw2jZsCkzU6nK', 'COMPANY', '2024-01-16', true);

INSERT INTO company(user_id, icon_path) VALUES (3, "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAOEAAADhCAMAAAAJbSJIAAAAVFBMVEX////yUCJ/ugAApO//uQDxOgBytADyShT2j3v/+/T/tAAAnO74+/R7uACpz3T0+f50wPTxMgD1hW//rgDwHABnrwDxQwD4s6ejzGgAle0Amu5ovPNVGgEPAAABAklEQVR4nO3biQnCQBRF0bhkceKWxGjU/vsUHKzgMyDh3AIenAJeVUmSJEmSJEmSJEmSJEmSpH/tVocaxu/KcW5CzQWF9SZU22Vhsw3VEBISEhISEhISEhISEhISEhISEhISEhISEhISEhISEhISEhISEhISEhISEhISEhISEhISEhKuSDi0oe4/4SlUSeHYxXpk4XIOtRQUSlL5LtHyzCFYQeGzD/V6Z+CUQk0Fhf0u1jUL0z5UIiQkJCQkJCQkJCQkJCQkJCQkJCQkJCQkJCQkJCQkJCQkJCQkJCQkJCQkJCQkJCQkJCQkJCRckXD97zxJkiRJkiRJkiRJkiRJkhTrA29gzkcc3y0uAAAAAElFTkSuQmCC");

SELECT * FROM user;
SELECT * FROM company;




INSERT INTO permissions(permission_name, role_name, is_enabled) VALUES 
    ("myOffers",           "STUDENT", false),
    ("createOffer",        "STUDENT", false),
    ("incidents",          "STUDENT", false),
    ("laboralInformation", "STUDENT", true),
    ("offers",              "STUDENT", true),
    ("myInscriptions",     "STUDENT", true);


INSERT INTO permissions(permission_name, role_name, is_enabled) VALUES 
    ("myOffers",           "COMPANY", true),
    ("createOffer",        "COMPANY", true),
    ("incidents",          "COMPANY", true),
    ("offers",              "COMPANY", false);



INSERT INTO permissions(permission_name, role_name, is_enabled) VALUES 
    ("myOffers",           "ADMINISTRATOR", false),
    ("createOffer",        "ADMINISTRATOR", true),
    ("incidents",          "ADMINISTRATOR", true),
    ("offers",              "ADMINISTRATOR", true),
    ("requests",            "ADMINISTRATOR", true),
    ("roles",               "ADMINISTRATOR", true),
    ("users",               "ADMINISTRATOR", true),
    ("reports",             "ADMINISTRATOR", true);
    
UPDATE permissions SET is_enabled = true WHERE permission_name = "INCIDENCES" AND role_name = "STUDENT";

SELECT * FROM permissions;


SELECT * FROM permissions;