CREATE DATABASE IF NOT EXISTS`cms_prod` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE cms_prod;

CREATE USER 'cms_user'@'localhost' IDENTIFIED BY 'password';

GRANT SELECT ON cms_prod.* to 'cms_user'@'localhost';
GRANT INSERT ON cms_prod.* to 'cms_user'@'localhost';
GRANT DELETE ON cms_prod.* to 'cms_user'@'localhost';
GRANT UPDATE ON cms_prod.* to 'cms_user'@'localhost';

CREATE TABLE `admin` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
)

 ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `image` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `file_name` varchar(255) DEFAULT NULL,
  `file_type` varchar(255) DEFAULT NULL,
  `image` longblob,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_pj9j7c7t7w7esa80o8jixhi6w` (`file_name`)
)

 ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `article` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `image_id` bigint DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `content` tinytext,
  PRIMARY KEY (`id`),
  KEY `FKa8st57l43fmam691umn5bw37u` (`image_id`),
  CONSTRAINT `FKa8st57l43fmam691umn5bw37u` FOREIGN KEY (`image_id`) REFERENCES `image` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `admin` (`id`,`password`,`username`) VALUES (1,'$2a$10$vzWcViz.60ogaXrsUbEj6eCXGz4QMgUsbBDHjlt80ZamcL5Y0iNIC','admin');