
sqlplus sys as sysdba

grant all privileges to HOSPITAL_MGMT ;


CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `user_name` varchar(30) NOT NULL,
  `user_pass` varchar(255) NOT NULL,
  `EMAIL` varchar(30) NOT NULL,
  `PHONE` varchar(30) NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`)
);

CREATE TABLE `roles` (
  `role_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(60) NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `user_roles_name_unique` (`name`)
);

CREATE TABLE `user_roles` (
  `user_id` int NOT NULL,
  `role_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `fk_user_roles_role_id` (`role_id`),
  CONSTRAINT `fk_user_roles_role_id` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`),
  CONSTRAINT `fk_user_roles_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
);


CREATE TABLE `PATIENT_DETAILS` (
  `PATIENT_ID` int NOT NULL AUTO_INCREMENT,
  `PATIENT_NAME` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `age` int DEFAULT NULL,
  `email` varchar(40) DEFAULT NULL,
  `gender` varchar(6) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `CREATED_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATED_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `USER_ID` int DEFAULT NULL,
  PRIMARY KEY (`PATIENT_ID`),
  KEY `FK_PATIENT_USER_ID` (`USER_ID`),
  CONSTRAINT `FK_PATIENT_USER_ID` FOREIGN KEY (`USER_ID`) REFERENCES `USERS` (`user_id`)
);

CREATE TABLE `DOCTOR_DETAILS` (
  `DOCTOR_ID` int NOT NULL AUTO_INCREMENT,
  `DOCTOR_NAME` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `specialist` varchar(20) DEFAULT NULL,
  `experience` varchar(20) DEFAULT NULL,
  `age` int DEFAULT NULL,
  `gender` varchar(6) DEFAULT NULL,
  `BLOOD_GROUP` varchar(6) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `city` varchar(20) DEFAULT NULL,
  `pincode` int DEFAULT NULL,
  `CREATED_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATED_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `USER_ID` int DEFAULT NULL,
  PRIMARY KEY (`DOCTOR_ID`),
  KEY `FK_DOCTOR_USER_ID` (`USER_ID`),
  CONSTRAINT `FK_DOCTOR_USER_ID` FOREIGN KEY (`USER_ID`) REFERENCES `USERS` (`user_id`)
);


CREATE TABLE `ADMIN_DETAILS` (
  `ADMIN_ID` int NOT NULL AUTO_INCREMENT,
  `ADMIN_NAME` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `age` int DEFAULT NULL,
  `email` varchar(40) DEFAULT NULL,
  `gender` varchar(6) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `CREATED_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATED_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `USER_ID` int DEFAULT NULL,
  PRIMARY KEY (`ADMIN_ID`),
  KEY `FK_ADMIN_USER_ID` (`USER_ID`),
  CONSTRAINT `FK_ADMIN_USER_ID` FOREIGN KEY (`USER_ID`) REFERENCES `USERS` (`user_id`)
);

CREATE TABLE HOSP_MGMT.PATIENT_DETAILS(
    id int NOT NULL AUTO_INCREMENT,
    first_Name VARCHAR(20) NOT NULL,
    last_Name VARCHAR(20),
    username VARCHAR(20) NOT NULL,
    password VARCHAR(100) NOT NULL,
    contact bigint,
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

CREATE TABLE HOSP_MGMT.DOCTOR_DETAILS(
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

CREATE TABLE `address` (
  `ADDR_ID` int NOT NULL,
  STREET varchar(30) NOT NULL,
  CITY varchar(30) NOT NULL,
  STATE varchar(30) NOT NULL,
  ZIP varchar(30) NOT NULL,
  PATIENT_ID INT,
  DOCTOR_ID INT,
  ADMIN_ID INT,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ADDR_ID`),
  CONSTRAINT `FK_ADDR_PATIENT_ID` FOREIGN KEY (`PATIENT_ID`) REFERENCES `PATIENT_DETAILS` (`PATIENT_ID`),
  CONSTRAINT `FK_ADDR_DOCTOR_ID` FOREIGN KEY (`DOCTOR_ID`) REFERENCES `DOCTOR_DETAILS` (`DOCTOR_ID`),
  CONSTRAINT `FK_ADDR_ADMIN_ID` FOREIGN KEY (`ADMIN_ID`) REFERENCES `ADMIN_DETAILS` (`ADMIN_ID`)
);

CREATE TABLE HOSP_MGMT.APPOINTMENT_DETAILS(
    id int NOT NULL AUTO_INCREMENT,
    PATIENT_ID int,
    DOCTOR_ID int,
    CONSULTATION int,
    DESCRIPTION VARCHAR(200),
    CREATED_DATE TIMESTAMP DEFAULT NOW(),
    UPDATED_DATE TIMESTAMP DEFAULT NOW(),
    APPOINTMENT_DATE TIMESTAMP DEFAULT NOW(),
    CONSTRAINT APPOINTMENT_DETAILS_id_PK PRIMARY KEY(id),
    CONSTRAINT fk_app_patient_id FOREIGN KEY (PATIENT_ID) REFERENCES HOSP_MGMT.PATIENT_DETAILS (ID),
    CONSTRAINT fk_app_doctor_id FOREIGN KEY (DOCTOR_ID) REFERENCES HOSP_MGMT.DOCTOR_DETAILS (ID)
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