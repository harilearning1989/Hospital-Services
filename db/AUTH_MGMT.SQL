CREATE TABLE `user` (
    `id` int unsigned NOT NULL AUTO_INCREMENT,
    `FIRST_NAME` varchar(30) NOT NULL,
    `LAST_NAME` varchar(30) NOT NULL,
    `user_name` varchar(30) NOT NULL,
    `user_pass` varchar(255) NOT NULL,
    `EMAIL` varchar(30) NOT NULL,
    `PHONE` varchar(30) NOT NULL,
    PRIMARY KEY (`id`)
) ;

CREATE TABLE `user_role` (
    `id` int unsigned NOT NULL AUTO_INCREMENT,
    `role` varchar(15) NOT NULL,
    `user_id` int unsigned NOT NULL,
    PRIMARY KEY (`id`),
    KEY `user_id` (`user_id`),
    CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
);

CREATE TABLE `user_roles` (
    `role_id` int NOT NULL,
    `user_id` bigint NOT NULL,
    PRIMARY KEY (`role_id`,`user_id`),
    KEY `user_id_fk` (`user_id`),
    CONSTRAINT `role_id_fk` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
    CONSTRAINT `user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
);

CREATE TABLE `users` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `username` varchar(20) DEFAULT NULL,
    `email` varchar(50) DEFAULT NULL,
    `password` varchar(120) DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `username_uc` (`username`),
    UNIQUE KEY `email_uc` (`email`)
);

