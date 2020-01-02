/*
SQLyog Ultimate
MySQL - 8.0.15 : Database - learn-jooq
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`learn-jooq` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `learn-jooq`;

/*Table structure for table `s1_user` */

CREATE TABLE `s1_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `email` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `address` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=254 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='USER TABLE';

/*Data for the table `s1_user` */

insert  into `s1_user`(`id`,`username`,`email`,`address`,`create_time`,`update_time`) values 
(1,'demo1','demo1@diamondfds.com','China Guangdong Shenzhen','2019-12-27 16:41:42','2019-12-27 16:41:42'),
(2,'admin1','admin1@diamondfsd@gmail.com','China Guanddong Guangzhou','2019-12-27 16:41:42','2019-12-27 16:41:42');

/*Table structure for table `s2_user_message` */

CREATE TABLE `s2_user_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `message_title` varchar(60) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '消息标题',
  `message_content` varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '消息内容',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户消息内容';

/*Data for the table `s2_user_message` */

insert  into `s2_user_message`(`id`,`user_id`,`message_title`,`message_content`,`create_time`,`update_time`) values 
(1,1,'demo1','demo-message-content','2019-12-31 17:07:29','2019-12-31 17:08:18'),
(2,1,'demo2','demo-message-content','2019-12-31 17:07:32','2019-12-31 17:08:17'),
(3,2,'user-2-demo1','demo-message-content','2019-12-31 17:08:01','2019-12-31 17:08:19'),
(4,2,'user-2-demo2','demo-message-content','2019-12-31 17:08:09','2019-12-31 17:08:19');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
