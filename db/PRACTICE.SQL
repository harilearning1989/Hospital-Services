CREATE TABLE `COURSES` (
    `C_ID` int NOT NULL AUTO_INCREMENT,
    `C_NAME` varchar(100) DEFAULT NULL,
    `C_DESCRIPTION` varchar(100) DEFAULT NULL,
    PRIMARY KEY (`C_ID`)
) ;

CREATE TABLE `Employee` (
    `EMP_ID` int NOT NULL,
    `EMP_NAME` varchar(50) DEFAULT NULL,
    `SALARY` mediumtext,
    `MANAGER_ID` int DEFAULT NULL,
    PRIMARY KEY (`EMP_ID`)
);

CREATE TABLE `ORDER_DETAILS` (
    `ORDER_ID` int NOT NULL AUTO_INCREMENT,
    `ORDER_NAME` varchar(30) NOT NULL,
    `DATE_CREATED` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    `LAST_UPDATED` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`ORDER_ID`)
) ;

CREATE TABLE `ORDER_ITEMS` (
    `ID` int NOT NULL AUTO_INCREMENT,
    `ORDER_ID` int DEFAULT NULL,
    `QUANTITY` int DEFAULT NULL,
    `DATE_CREATED` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    `LAST_UPDATED` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`ID`),
    KEY `fk_ORDER_ID_ORDER_DETAILS` (`ORDER_ID`),
    CONSTRAINT `fk_ORDER_ID_ORDER_DETAILS` FOREIGN KEY (`ORDER_ID`) REFERENCES `ORDER_DETAILS` (`ORDER_ID`)
);

CREATE TABLE `persons` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `name` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `STUDENT_COURSES` (
    `S_ID` int NOT NULL,
    `C_ID` int NOT NULL,
    PRIMARY KEY (`S_ID`,`C_ID`),
    KEY `FK_COURSE_ID` (`C_ID`),
    CONSTRAINT `FK_COURSE_ID` FOREIGN KEY (`C_ID`) REFERENCES `COURSES` (`C_ID`),
    CONSTRAINT `FK_STUDENT_ID` FOREIGN KEY (`S_ID`) REFERENCES `STUDENTS` (`STUDENT_ID`)
);

CREATE TABLE `STUDENTS` (
    `STUDENT_ID` int NOT NULL AUTO_INCREMENT,
    `STUDENT_NAME` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `FATHER_NAME` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `GENDER` varchar(30) NOT NULL,
    `MOBILE` mediumtext,
    `CATEGORY` varchar(30) NOT NULL,
    `TELUGU` int DEFAULT NULL,
    `HINDI` int DEFAULT NULL,
    `ENGLISH` int DEFAULT NULL,
    `MATHS` int DEFAULT NULL,
    `SCIENCE` int DEFAULT NULL,
    `SOCIAL` int DEFAULT NULL,
    `TOTAL` int DEFAULT NULL,
    PRIMARY KEY (`STUDENT_ID`)
) ;
