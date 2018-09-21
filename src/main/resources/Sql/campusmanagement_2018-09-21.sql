# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.21)
# Database: campusmanagement
# Generation Time: 2018-09-21 01:50:54 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table t_account
# ------------------------------------------------------------

DROP TABLE IF EXISTS `t_account`;

CREATE TABLE `t_account` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL DEFAULT '',
  `password` varchar(20) NOT NULL DEFAULT '',
  `lastlogintime` datetime NOT NULL,
  `realname` varchar(20) DEFAULT '',
  `headimage` varchar(200) DEFAULT '',
  `isdeleted` int(1) NOT NULL DEFAULT '0',
  `deletetime` datetime DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `modifytime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `t_account` WRITE;
/*!40000 ALTER TABLE `t_account` DISABLE KEYS */;

INSERT INTO `t_account` (`id`, `username`, `password`, `lastlogintime`, `realname`, `headimage`, `isdeleted`, `deletetime`, `createtime`, `modifytime`)
VALUES
	(1,'admin','admin','2018-08-17 00:00:00','常州信息','/static/fileUpload/headImages/1537492946875.jpg',0,NULL,NULL,NULL),
	(2,'842988376@qq.com','123456','1990-01-01 00:00:00','常州信息2333','/static/fileUpload/headImages/defaultHeadImage.png',0,NULL,'2018-08-22 15:32:54','2018-09-21 09:16:06'),
	(4,'1123','123456','1990-01-01 00:00:00','222','/static/fileUpload/headImages/defaultHeadImage.png',0,NULL,'2018-08-22 16:26:29',NULL),
	(5,'1','123456','1990-01-01 00:00:00','2','/static/fileUpload/headImages/defaultHeadImage.png',0,NULL,'2018-08-22 16:26:47',NULL),
	(6,'asd','123456','1990-01-01 00:00:00','sd','/static/fileUpload/headImages/defaultHeadImage.png',0,NULL,'2018-08-22 16:27:23',NULL),
	(7,'asd','123456','1990-01-01 00:00:00','223','/static/fileUpload/headImages/defaultHeadImage.png',0,NULL,'2018-08-22 16:27:26',NULL),
	(8,'123','123456','1990-01-01 00:00:00','sd','/static/fileUpload/headImages/defaultHeadImage.png',0,NULL,'2018-08-22 16:27:30',NULL),
	(9,'123213','123456','1990-01-01 00:00:00','423','/static/fileUpload/headImages/defaultHeadImage.png',0,NULL,'2018-08-22 16:27:34',NULL),
	(10,'123123','123456','1990-01-01 00:00:00','44','/static/fileUpload/headImages/defaultHeadImage.png',0,NULL,'2018-08-22 16:27:37',NULL),
	(11,'12323','123456','1990-01-01 00:00:00','111','/static/fileUpload/headImages/defaultHeadImage.png',0,NULL,'2018-08-22 16:27:40',NULL),
	(12,'2323','123456','1990-01-01 00:00:00','111','/static/fileUpload/headImages/defaultHeadImage.png',0,NULL,'2018-08-22 16:27:44',NULL),
	(13,'12312111','123456','1990-01-01 00:00:00','223','/static/fileUpload/headImages/defaultHeadImage.png',0,NULL,'2018-08-22 16:27:58',NULL),
	(14,'23','123456','1990-01-01 00:00:00','4','/static/fileUpload/headImages/defaultHeadImage.png',0,NULL,'2018-08-22 16:30:47',NULL),
	(15,'232323','123456','1990-01-01 00:00:00','111','/static/fileUpload/headImages/defaultHeadImage.png',0,NULL,'2018-08-23 15:16:04',NULL),
	(16,'222223','123456','1990-01-01 00:00:00','3333334','/static/fileUpload/headImages/defaultHeadImage.png',0,NULL,'2018-08-23 15:16:09','2018-09-14 17:21:47'),
	(17,'1111','123456','1990-01-01 00:00:00','222','/static/fileUpload/headImages/defaultHeadImage.png',0,NULL,'2018-09-21 08:56:23',NULL);

/*!40000 ALTER TABLE `t_account` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table t_calltheroll
# ------------------------------------------------------------

DROP TABLE IF EXISTS `t_calltheroll`;

CREATE TABLE `t_calltheroll` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(200) DEFAULT '',
  `isdeleted` int(11) DEFAULT NULL,
  `deletetime` datetime DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `modifytime` datetime DEFAULT NULL,
  `classid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `t_calltheroll` WRITE;
/*!40000 ALTER TABLE `t_calltheroll` DISABLE KEYS */;

INSERT INTO `t_calltheroll` (`id`, `title`, `isdeleted`, `deletetime`, `createtime`, `modifytime`, `classid`)
VALUES
	(6,'hhh',0,NULL,'2018-09-07 09:39:27',NULL,1),
	(7,'23123',0,NULL,'2018-09-14 17:22:06',NULL,1),
	(8,'test',0,NULL,'2018-09-21 08:56:45',NULL,3);

/*!40000 ALTER TABLE `t_calltheroll` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table t_calltherolldetail
# ------------------------------------------------------------

DROP TABLE IF EXISTS `t_calltherolldetail`;

CREATE TABLE `t_calltherolldetail` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `studentid` int(11) DEFAULT NULL,
  `calltherollid` int(11) DEFAULT NULL,
  `confirm` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `t_calltherolldetail` WRITE;
/*!40000 ALTER TABLE `t_calltherolldetail` DISABLE KEYS */;

INSERT INTO `t_calltherolldetail` (`id`, `studentid`, `calltherollid`, `confirm`)
VALUES
	(1,1,5,1),
	(2,3,5,0),
	(3,1,6,0),
	(4,3,6,1),
	(5,1,7,1),
	(6,3,7,1),
	(7,2,8,1),
	(8,4,8,1);

/*!40000 ALTER TABLE `t_calltherolldetail` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table t_grade
# ------------------------------------------------------------

DROP TABLE IF EXISTS `t_grade`;

CREATE TABLE `t_grade` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `gradename` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `t_grade` WRITE;
/*!40000 ALTER TABLE `t_grade` DISABLE KEYS */;

INSERT INTO `t_grade` (`id`, `gradename`)
VALUES
	(1,'大一'),
	(2,'大二'),
	(3,'大三');

/*!40000 ALTER TABLE `t_grade` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table t_schoolclass
# ------------------------------------------------------------

DROP TABLE IF EXISTS `t_schoolclass`;

CREATE TABLE `t_schoolclass` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `classname` varchar(50) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `isdeleted` int(11) DEFAULT NULL,
  `deletetime` datetime DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `modifytime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `t_schoolclass` WRITE;
/*!40000 ALTER TABLE `t_schoolclass` DISABLE KEYS */;

INSERT INTO `t_schoolclass` (`id`, `classname`, `description`, `isdeleted`, `deletetime`, `createtime`, `modifytime`)
VALUES
	(1,'班级081','这里是班级描述,哈哈',0,NULL,'2018-08-23 10:34:25',NULL),
	(3,'班级082','123',0,NULL,'2018-08-23 15:16:37',NULL);

/*!40000 ALTER TABLE `t_schoolclass` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table t_student
# ------------------------------------------------------------

DROP TABLE IF EXISTS `t_student`;

CREATE TABLE `t_student` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `studentname` varchar(50) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `classid` int(11) DEFAULT NULL,
  `headimage` varchar(200) DEFAULT NULL,
  `gradeid` int(11) DEFAULT NULL,
  `isdeleted` int(11) DEFAULT NULL,
  `deletetime` datetime DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `modifytime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `t_student` WRITE;
/*!40000 ALTER TABLE `t_student` DISABLE KEYS */;

INSERT INTO `t_student` (`id`, `studentname`, `sex`, `age`, `classid`, `headimage`, `gradeid`, `isdeleted`, `deletetime`, `createtime`, `modifytime`)
VALUES
	(1,'测试23',2,12,1,'/static/fileUpload/headImages/defaultHeadImage.png',3,0,NULL,'2018-08-23 11:09:40',NULL),
	(2,'sasd',2,12,3,'/static/fileUpload/headImages/defaultHeadImage.png',2,0,NULL,'2018-08-23 15:19:02',NULL),
	(3,'asd',1,2,1,'/static/fileUpload/headImages/defaultHeadImage.png',2,0,NULL,'2018-08-23 16:14:40',NULL),
	(4,'测试23',1,12,3,'/static/fileUpload/headImages/defaultHeadImage.png',1,0,NULL,'2018-09-14 17:22:27',NULL);

/*!40000 ALTER TABLE `t_student` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
