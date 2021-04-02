-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: 139.224.234.126    Database: falsework
-- ------------------------------------------------------
-- Server version	5.7.18-0ubuntu0.16.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `sys_menu`
--

DROP TABLE IF EXISTS `sys_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `name` varchar(32) NOT NULL COMMENT '菜单名称',
  `description` varchar(32) DEFAULT NULL COMMENT '菜单描述',
  `url` varchar(64) DEFAULT NULL,
  `permission` varchar(32) DEFAULT NULL COMMENT '权限标志',
  `parent_id` int(11) DEFAULT NULL COMMENT '父菜单ID',
  `sort` int(11) DEFAULT NULL COMMENT '排序值',
  `opened` char(5) DEFAULT '0' COMMENT '0关闭，1打开',
  `status` char(1) DEFAULT '1' COMMENT '1正常0删除9不可用',
  `type` char(1) NOT NULL COMMENT '菜单类型 （1菜单 2按钮）',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8 COMMENT='菜单权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_menu`
--

LOCK TABLES `sys_menu` WRITE;
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
INSERT INTO `sys_menu` VALUES (1,'系统管理',NULL,NULL,'system',0,1,'1','1','1','2017-07-16 13:09:17'),(71,'用户管理',NULL,NULL,'system:user',1,1,'1','1','1','2017-07-16 13:09:17'),(72,'平台管理',NULL,NULL,'system:platform',1,1,'1','1','1','2017-07-16 13:09:17'),(73,'角色管理',NULL,NULL,'system:role',1,1,'1','1','1','2017-07-16 13:09:17'),(74,'菜单管理',NULL,NULL,'system:menu',1,1,'1','1','1','2017-07-16 13:09:17'),(75,'添加用户',NULL,NULL,'system:user:add',71,1,'1','1','2','2017-07-16 13:28:10'),(76,'编辑用户',NULL,NULL,'system:user:edit',71,1,'1','1','2','2017-07-16 13:28:10'),(77,'删除用户',NULL,NULL,'system:user:delete',71,1,'1','1','2','2017-07-16 13:28:10'),(78,'添加平台',NULL,NULL,'system:platform:add',72,1,'1','1','2','2017-07-16 13:51:57'),(79,'编辑平台',NULL,NULL,'system:platform:edit',72,1,'1','1','2','2017-07-16 13:51:57'),(80,'删除平台',NULL,NULL,'system:platform:delete',72,1,'1','1','2','2017-07-16 13:51:57'),(81,'添加角色',NULL,NULL,'system:role:add',73,1,'1','1','2','2017-07-16 13:51:57'),(82,'编辑角色',NULL,NULL,'system:role:edit',73,1,'1','1','2','2017-07-16 13:51:57'),(83,'删除角色',NULL,NULL,'system:role:delete',73,1,'1','1','2','2017-07-16 13:51:57'),(84,'添加菜单',NULL,NULL,'system:menu:add',74,1,'1','1','2','2017-07-16 13:51:57'),(85,'编辑菜单',NULL,NULL,'system:menu:edit',74,1,'1','1','2','2017-07-16 13:51:57'),(86,'删除菜单',NULL,NULL,'system:menu:delete',74,1,'1','1','2','2017-07-16 13:51:57');
/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_platform`
--

DROP TABLE IF EXISTS `sys_platform`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_platform` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL COMMENT '公众号名称',
  `status` int(11) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='平台表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_platform`
--

LOCK TABLES `sys_platform` WRITE;
/*!40000 ALTER TABLE `sys_platform` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_platform` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `name` varchar(32) NOT NULL COMMENT '角色名称',
  `description` varchar(32) DEFAULT NULL COMMENT '角色描述',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间（默认当前时间）',
  `status` char(1) DEFAULT '1' COMMENT '1--正常 0--删除',
  `create_user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` VALUES (1,'总管理员','所有权限','2017-07-16 13:01:48','1',1);
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_menu`
--

DROP TABLE IF EXISTS `sys_role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `menu_id` int(11) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=231 DEFAULT CHARSET=utf8 COMMENT='角色菜单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_menu`
--

LOCK TABLES `sys_role_menu` WRITE;
/*!40000 ALTER TABLE `sys_role_menu` DISABLE KEYS */;
INSERT INTO `sys_role_menu` VALUES (214,1,1),(215,1,71),(216,1,72),(217,1,73),(218,1,74),(219,1,75),(220,1,76),(221,1,77),(222,1,78),(223,1,79),(224,1,80),(225,1,81),(226,1,82),(227,1,83),(228,1,84),(229,1,85),(230,1,86);
/*!40000 ALTER TABLE `sys_role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(145) NOT NULL,
  `password` varchar(145) NOT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `status` char(1) DEFAULT '1' COMMENT '用户状态 1-正常  0-删除',
  `parent_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES (1,'admin','$shiro1$SHA-256$500000$UwTOCOVzJbuDCDI1zZHvXw==$+iBc5wtRVSfv6HTTxwl0jThUspgMC3i8masw12UjZXo=','2017-06-29 15:34:20','1',0);
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_platform`
--

DROP TABLE IF EXISTS `sys_user_platform`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user_platform` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `platform_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_platform`
--

LOCK TABLES `sys_user_platform` WRITE;
/*!40000 ALTER TABLE `sys_user_platform` DISABLE KEYS */;
INSERT INTO `sys_user_platform` VALUES (1,1,4),(2,1,5),(3,1,6),(4,1,7),(5,1,8);
/*!40000 ALTER TABLE `sys_user_platform` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_role`
--

DROP TABLE IF EXISTS `sys_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COMMENT='用户角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_role`
--

LOCK TABLES `sys_user_role` WRITE;
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;
INSERT INTO `sys_user_role` VALUES (29,1,1);
/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-07-21 10:25:06
