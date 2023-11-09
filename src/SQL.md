CREATE TABLE `hotel` (
`id` int NOT NULL AUTO_INCREMENT,
`name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`star` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`property` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
`phone` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`email` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci

CREATE TABLE `reservation_info` (
`id` int NOT NULL AUTO_INCREMENT,
`client_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
`client_phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
`client_email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
`client_note` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
`room_id` int NOT NULL,
`check_in` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`check_out` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`adult_numb` int NOT NULL,
`child_numb` int NOT NULL,
`total_price` int NOT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci

CREATE TABLE `room` (
`id` int NOT NULL AUTO_INCREMENT,
`room_type` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`stock` int NOT NULL,
`season_id` int NOT NULL,
`adult_price` int NOT NULL,
`child_price` int NOT NULL,
`hotel_type_id` int NOT NULL,
`hotel_id` int NOT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci

CREATE TABLE `room_properties` (
`id` int NOT NULL AUTO_INCREMENT,
`property` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`room_id` int NOT NULL,
`bed` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`area` int NOT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci

CREATE TABLE `season` (
`id` int NOT NULL AUTO_INCREMENT,
`season_start` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`season_end` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`hotel_id` int NOT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci

CREATE TABLE `type_hotel` (
`id` int NOT NULL AUTO_INCREMENT,
`type` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`hotel_id` int NOT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci

CREATE TABLE `user` (
`id` int NOT NULL AUTO_INCREMENT,
`name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`uname` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`pass` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`type` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci