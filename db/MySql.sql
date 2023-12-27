
sqlplus sys as sysdba

grant all privileges to HOSPITAL_MGMT ;

CREATE TABLE HOSPITAL_MGMT.PATIENT_DETAILS(
    id int NOT NULL AUTO_INCREMENT,
    first_Name VARCHAR(20) NOT NULL,
    last_Name VARCHAR(20),
    username VARCHAR(20) NOT NULL,
    password VARCHAR(100) NOT NULL,
    contact int,
    age int,
    email VARCHAR(40),
    gender VARCHAR(6),
    BLOOD_GROUP VARCHAR(6),
    address VARCHAR(100),
    city VARCHAR(20),
    pincode int,
    CREATED_DATE TIMESTAMP DEFAULT NOW(),
    UPDATED_DATE TIMESTAMP DEFAULT NOW(),
    CONSTRAINT PATIENT_DETAILS_id_PK PRIMARY KEY(id)
);

CREATE TABLE HOSPITAL_MGMT.DOCTOR_DETAILS(
    id int NOT NULL AUTO_INCREMENT,
    first_Name VARCHAR(20) NOT NULL,
    last_Name VARCHAR(20),
    username VARCHAR(20) NOT NULL,
    password VARCHAR(100) NOT NULL,
    specialist VARCHAR(20),
    experience VARCHAR(20),
    contact int,
    age int,
    email VARCHAR(40),
    gender VARCHAR(6),
    BLOOD_GROUP VARCHAR(6),
    address VARCHAR(100),
    city VARCHAR(20),
    pincode int,
    CREATED_DATE TIMESTAMP DEFAULT NOW(),
    UPDATED_DATE TIMESTAMP DEFAULT NOW(),
    CONSTRAINT DOCTOR_DETAILS_id_PK PRIMARY KEY(id)
);

CREATE TABLE HOSPITAL_MGMT.ADMIN_DETAILS(
    id int NOT NULL AUTO_INCREMENT,
    first_Name VARCHAR(20) NOT NULL,
    last_Name VARCHAR(20),
    username VARCHAR(20) NOT NULL,
    password VARCHAR(100) NOT NULL,
    contact int,
    age int,
    email VARCHAR(40),
    gender VARCHAR(6),
    BLOOD_GROUP VARCHAR(6),
    address VARCHAR(100),
    city VARCHAR(20),
    pincode int,
    CREATED_DATE TIMESTAMP DEFAULT NOW(),
    UPDATED_DATE TIMESTAMP DEFAULT NOW(),
    CONSTRAINT ADMIN_DETAILS_id_PK PRIMARY KEY(id)
);

CREATE TABLE HOSPITAL_MGMT.APPOINTMENT_DETAILS(
    id int NOT NULL AUTO_INCREMENT,
    FULL_NAME VARCHAR(20) NOT NULL,
    EMAIL VARCHAR(40),
    GENDER VARCHAR(6),
    CONTACT int,
    AGE int,
    PROBLEM VARCHAR(100),
    BLOOD_GROUP VARCHAR(6),
    ADDRESS VARCHAR(100),
    DOCTOR_NAME VARCHAR(20),
    PRESCRIPTION VARCHAR(100),
    STATUS VARCHAR(20),
    PRICE int,
    CREATED_DATE TIMESTAMP DEFAULT NOW(),
    UPDATED_DATE TIMESTAMP DEFAULT NOW(),
    CONSTRAINT APPOINTMENT_DETAILS_id_PK PRIMARY KEY(id)
);

CREATE TABLE HOSPITAL_MGMT.hosptal_users (
    user_id int NOT NULL AUTO_INCREMENT,
    username varchar(15) NOT NULL,
    email varchar(40) NOT NULL,
    password varchar(100) NOT NULL,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW(),
    CONSTRAINT hosptal_users_user_id_PK PRIMARY KEY (user_id),
    CONSTRAINT hosptal_users_username_unique UNIQUE (username),
    CONSTRAINT hosptal_users_email_unique UNIQUE (email)
);

CREATE TABLE HOSPITAL_MGMT.hosptal_roles (
    role_id int NOT NULL AUTO_INCREMENT,
    name varchar(60) NOT NULL,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW(),
    CONSTRAINT hosptal_roles_id_PK PRIMARY KEY (role_id),
    CONSTRAINT hosptal_roles_name_unique UNIQUE (name)
);

CREATE TABLE HOSPITAL_MGMT.hosptal_user_roles (
    user_id int,
    role_id int,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW(),
    CONSTRAINT hosptal_user_roles_id_pk PRIMARY KEY (user_id,role_id),
    -- KEY fk_user_roles_role_id (role_id),
    CONSTRAINT fk_user_roles_role_id FOREIGN KEY (role_id) REFERENCES HOSPITAL_MGMT.hosptal_roles (role_id),
    CONSTRAINT fk_user_roles_user_id FOREIGN KEY (user_id) REFERENCES HOSPITAL_MGMT.hosptal_users (user_id)
);

INSERT INTO HOSPITAL_MGMT.hosptal_roles(name) VALUES('ROLE_USER');
INSERT INTO HOSPITAL_MGMT.hosptal_roles(name) VALUES('ROLE_ADMIN');
INSERT INTO HOSPITAL_MGMT.hosptal_roles(name) VALUES('ROLE_PATIENT');
INSERT INTO HOSPITAL_MGMT.hosptal_roles(name) VALUES('ROLE_DOCTOR');

SELECT * FROM HOSPITAL_MGMT.hosptal_users;
SELECT * FROM HOSPITAL_MGMT.PATIENT_DETAILS;
SELECT * FROM HOSPITAL_MGMT.DOCTOR_DETAILS;
SELECT * FROM HOSPITAL_MGMT.ADMIN_DETAILS;
SELECT * FROM HOSPITAL_MGMT.APPOINTMENT_DETAILS;
SELECT * FROM HOSPITAL_MGMT.hosptal_roles;
SELECT * FROM HOSPITAL_MGMT.hosptal_user_roles;

select sequence_owner, sequence_name from USER_SEQUENCES;

DBA_SEQUENCES -- all sequences that exist
ALL_SEQUENCES  -- all sequences that you have permission to see
USER_SEQUENCES  -- all sequences that you own

--ALTER TABLE HOSPITAL_MGMT.PATIENT_DETAILS ADD CREATED_DATE DATE NULL;
--ALTER TABLE HOSPITAL_MGMT.PATIENT_DETAILS ADD UPDATED_DATE DATE NULL;
--ALTER TABLE HOSPITAL_MGMT.PATIENT_DETAILS RENAME COLUMN BLOODGROUP TO BLOOD_GROUP;
--ALTER TABLE HOSPITAL_MGMT.PATIENT_DETAILS MODIFY email VARCHAR2(40) NOT NULL;