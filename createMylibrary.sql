/*
SQLyog Ultimate v10.00 Beta1
MySQL - 8.0.23 : Database - mylibrary
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`mylibrary` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `mylibrary`;

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `admin_id` int NOT NULL AUTO_INCREMENT,
  `admin_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '管理员账号',
  `admin_nick_name` varchar(255) DEFAULT NULL COMMENT '昵称',
  `admin_password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '管理员密码',
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `admin` */

insert  into `admin`(`admin_id`,`admin_name`,`admin_nick_name`,`admin_password`) values (1,'1','王五','aaaaaa'),(2,'2','李四','aaaaaa');

/*Table structure for table `book` */

DROP TABLE IF EXISTS `book`;

CREATE TABLE `book` (
  `book_id` int NOT NULL AUTO_INCREMENT,
  `book_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `author` varchar(255) DEFAULT NULL,
  `describe` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`book_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `book` */

insert  into `book`(`book_id`,`book_name`,`author`,`describe`) values (2,'《a》','a','a');

/*Table structure for table `borrow` */

DROP TABLE IF EXISTS `borrow`;

CREATE TABLE `borrow` (
  `borrow_id` int NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `book_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `borrow_date` varchar(255) DEFAULT NULL COMMENT '借阅日期',
  `return_date` varchar(255) DEFAULT NULL COMMENT '归还日期',
  PRIMARY KEY (`borrow_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

/*Data for the table `borrow` */

insert  into `borrow`(`borrow_id`,`user_name`,`book_name`,`borrow_date`,`return_date`) values (11,'张三','《人生的智慧》','2022-01-03 08:00:00','2022-01-12 08:00:00'),(12,'李四','《关于我转生成为史莱姆这件事》','2022-01-03 08:00:00','2022-01-19 08:00:00'),(13,'赵九','《金字塔原理》','2022-01-03 08:00:00','2022-01-05 08:00:00'),(16,'法外狂徒','《人生一串》','2022-01-04 08:00:00','2022-01-13 08:00:00'),(17,'a','《a》','2022-01-04 08:00:00','2022-01-06 08:00:00'),(18,'nnn','《nn》','2022-01-02 08:00:00','2022-01-07 08:00:00'),(19,'v','《v》','2022-01-03 08:00:00','2022-01-06 08:00:00'),(20,'m','《m》','2022-01-03 08:00:00','2022-01-06 08:00:00'),(21,'王五','《明朝那些事儿》','2022-01-05 08:00:00','2022-01-07 08:00:00'),(22,'nnn','《aaa》','2022-01-04 08:00:00','2022-01-07 08:00:00'),(23,'麻子','《精彩人生》','2022-01-05 08:00:00',NULL);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户名',
  `age` int DEFAULT NULL COMMENT '年龄',
  `sex` varchar(255) DEFAULT NULL COMMENT '性别',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '地址',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`user_id`,`user_name`,`age`,`sex`,`address`) values (12,'a',6,'男','海湾旅游区'),(13,'b',3,'男','海湾旅游区'),(14,'c',13,'男','海湾旅游区'),(15,'a',3,'男','海湾旅游区'),(16,'a',4,'男','海湾旅游区'),(17,'n',3,'男','海湾旅游区'),(18,'5',666,'男','海湾旅游区'),(19,'a',13,'男','海湾旅游区'),(20,'a',3,'男','海湾旅游区');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
