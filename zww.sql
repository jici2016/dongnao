/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.5.40 : Database - zww
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`zww` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `zww`;

/*Table structure for table `order` */

DROP TABLE IF EXISTS `order`;

CREATE TABLE `order` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ORDERID` int(11) DEFAULT NULL COMMENT '订单号',
  `USERID` int(11) DEFAULT NULL COMMENT '用户id',
  `POINTS` int(11) DEFAULT NULL COMMENT '所需积分',
  `NUM` int(11) DEFAULT NULL COMMENT '获得游戏次数',
  `INTIME` datetime DEFAULT NULL COMMENT '购买时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `order` */

/*Table structure for table `prize` */

DROP TABLE IF EXISTS `prize`;

CREATE TABLE `prize` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PRIZEID` varchar(50) DEFAULT NULL COMMENT '奖品id',
  `TYPEID` int(11) DEFAULT NULL COMMENT '奖品类型id',
  `PRIZENAME` varchar(50) DEFAULT NULL COMMENT '奖品名称',
  `CARDNO` varchar(255) DEFAULT NULL COMMENT '卡密号码',
  `CARDPWD` varchar(255) DEFAULT NULL COMMENT '卡密密码',
  `STATE` int(11) DEFAULT '1' COMMENT '库存状态(1:库存,0:已领取)',
  `INTIME` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `prize` */

/*Table structure for table `prizetype` */

DROP TABLE IF EXISTS `prizetype`;

CREATE TABLE `prizetype` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `TYPEID` int(11) DEFAULT NULL COMMENT '奖品类别ID',
  `TYPENAME` varchar(20) DEFAULT NULL COMMENT '类别名称',
  `PRICE` float DEFAULT NULL COMMENT '单价',
  `RATE` int(11) DEFAULT NULL COMMENT '中奖率',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `prizetype` */

/*Table structure for table `touchlog` */

DROP TABLE IF EXISTS `touchlog`;

CREATE TABLE `touchlog` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `UID` int(11) DEFAULT NULL COMMENT '用户id',
  `NUMS` int(11) DEFAULT NULL COMMENT '剩余次数',
  `PRIZEID` int(11) DEFAULT '-1' COMMENT '抽奖结果(奖品ID)',
  `TOUCHTIME` datetime DEFAULT NULL COMMENT '抽奖时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `touchlog` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `UID` int(10) unsigned NOT NULL COMMENT '用户id',
  `MOBILE` varchar(11) NOT NULL COMMENT '手机号',
  `UNAME` varchar(20) DEFAULT NULL COMMENT '用户名',
  `JIFEN` int(11) DEFAULT NULL COMMENT '当前有效积分',
  `NUM` int(11) DEFAULT NULL COMMENT '当前剩余次数',
  `REGTIME` datetime DEFAULT NULL COMMENT '注册时间',
  `LOGTIME` datetime DEFAULT NULL COMMENT '最后登陆时间',
  PRIMARY KEY (`UID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

/*Data for the table `user` */

/*Table structure for table `userprize` */

DROP TABLE IF EXISTS `userprize`;

CREATE TABLE `userprize` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `USERID` int(11) DEFAULT NULL COMMENT '用户id',
  `MOBILE` varchar(20) DEFAULT NULL COMMENT '手机号',
  `PRIZEID` int(11) DEFAULT NULL COMMENT '奖品id',
  `PRIZENAME` varchar(50) DEFAULT NULL COMMENT '奖品名称',
  `STATE` int(11) DEFAULT NULL COMMENT '兑换状态(1:已兑换,0：未兑换)',
  `TOUCHTIME` datetime DEFAULT NULL COMMENT '中奖时间',
  `EXCHANGETIME` datetime DEFAULT NULL COMMENT '兑换时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `userprize` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
