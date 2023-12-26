CREATE TABLE `hosp_mgmt`.`users` (
    `id` int NOT NULL AUTO_INCREMENT,
    `FIRST_NAME` varchar(30) NOT NULL,
    `LAST_NAME` varchar(30) NOT NULL,
    `user_name` varchar(30) NOT NULL,
    `user_pass` varchar(255) NOT NULL,
    `EMAIL` varchar(30) NOT NULL,
    `PHONE` varchar(30) NOT NULL,
    `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ;

CREATE TABLE `hosp_mgmt`.`roles` (
    `role_id` int NOT NULL AUTO_INCREMENT,
    `name` varchar(60) NOT NULL,
    `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`role_id`),
    UNIQUE KEY `user_roles_name_unique` (`name`)
);

CREATE TABLE `hosp_mgmt`.`user_roles` (
    `user_id` int NOT NULL,
    `role_id` int NOT NULL,
    `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`user_id`,`role_id`),
    KEY `fk_user_roles_role_id` (`role_id`),
    CONSTRAINT `fk_user_roles_role_id` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`),
    CONSTRAINT `fk_user_roles_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
)