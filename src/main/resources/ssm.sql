/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.5.40 : Database - mydb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`mydb` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `mydb`;

/*Table structure for table `course` */

DROP TABLE IF EXISTS `course`;

CREATE TABLE `course` (
  `id` int(4) NOT NULL,
  `gid` int(4) DEFAULT NULL,
  `sid` int(4) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_course` (`gid`),
  CONSTRAINT `FK_course` FOREIGN KEY (`gid`) REFERENCES `grade` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `course` */

insert  into `course`(`id`,`gid`,`sid`,`name`) values (1,1,1,'trddd'),(2,2,2,'数学');

/*Table structure for table `grade` */

DROP TABLE IF EXISTS `grade`;

CREATE TABLE `grade` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `gname` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `grade` */

insert  into `grade`(`id`,`gname`) values (1,'年级1'),(2,'年级2');

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `id` int(4) unsigned NOT NULL AUTO_INCREMENT,
  `NAME` varchar(20) DEFAULT NULL,
  `gid` int(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_student` (`gid`),
  CONSTRAINT `FK_student` FOREIGN KEY (`gid`) REFERENCES `grade` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `student` */

insert  into `student`(`id`,`NAME`,`gid`) values (1,'张三',1),(2,'李四',1),(3,'王五',1),(4,'张三2',2),(5,'李四2',2),(6,'王五2',2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
