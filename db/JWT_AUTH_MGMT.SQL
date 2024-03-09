CREATE TABLE `users` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `email` varchar(50) DEFAULT NULL,
    `password` varchar(120) DEFAULT NULL,
    `username` varchar(20) DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`),
    UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`)
);

CREATE TABLE `roles` (
    `id` int NOT NULL AUTO_INCREMENT,
    `name` enum('ROLE_ADMIN','ROLE_MODERATOR','ROLE_USER') DEFAULT NULL,
    PRIMARY KEY (`id`)
) ;

CREATE TABLE `user_roles` (
    `user_id` bigint NOT NULL,
    `role_id` int NOT NULL,
    PRIMARY KEY (`user_id`,`role_id`),
    KEY `FKh8ciramu9cc9q3qcqiv4ue8a6` (`role_id`),
    CONSTRAINT `FKh8ciramu9cc9q3qcqiv4ue8a6` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
    CONSTRAINT `FKhfh9dx7w3ubf1co1vdev94g3f` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
);
