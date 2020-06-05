-- --------------------------------------------------------
-- 主机:                           192.168.2.110
-- 服务器版本:                        8.0.20 - MySQL Community Server - GPL
-- 服务器OS:                        Linux
-- HeidiSQL 版本:                  10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for Meeting
CREATE DATABASE IF NOT EXISTS `Meeting` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `Meeting`;

-- Dumping structure for table Meeting.dictionary
CREATE TABLE IF NOT EXISTS `dictionary` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `parentId` bigint unsigned NOT NULL DEFAULT '0',
  `title` varchar(100) NOT NULL DEFAULT '',
  `titleE` varchar(100) NOT NULL DEFAULT '',
  `value` varchar(100) NOT NULL DEFAULT '',
  `type` smallint unsigned NOT NULL DEFAULT '0',
  `sort` smallint unsigned NOT NULL DEFAULT '0',
  `status` bigint unsigned NOT NULL DEFAULT '0',
  `image` varchar(200) NOT NULL DEFAULT '',
  `url` varchar(200) NOT NULL DEFAULT '',
  `cssClass` varchar(100) NOT NULL DEFAULT '',
  `remark` varchar(200) NOT NULL DEFAULT '',
  `insertTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `status` (`status`),
  KEY `parentId` (`parentId`),
  KEY `type` (`type`,`sort`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table Meeting.dictionary: ~2 rows (大约)
DELETE FROM `dictionary`;
/*!40000 ALTER TABLE `dictionary` DISABLE KEYS */;
INSERT INTO `dictionary` (`id`, `parentId`, `title`, `titleE`, `value`, `type`, `sort`, `status`, `image`, `url`, `cssClass`, `remark`, `insertTime`, `updateTime`) VALUES
	(1, 0, '正常', 'normal', '1', 1, 0, 1, '', '', '', '通用状态: 正常', '2019-08-28 16:42:25', '2019-11-29 06:43:42'),
	(2, 0, '禁用', 'disable', '0', 1, 1, 1, '', '', '', '通用状态: 禁用', '2019-08-28 16:44:26', '2019-11-29 06:44:18');
/*!40000 ALTER TABLE `dictionary` ENABLE KEYS */;

-- Dumping structure for table Meeting.dictionaryType
CREATE TABLE IF NOT EXISTS `dictionaryType` (
  `id` smallint unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(32) NOT NULL DEFAULT '',
  `remark` varchar(200) NOT NULL DEFAULT '',
  `sort` smallint unsigned NOT NULL DEFAULT '0',
  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `insertTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `sort` (`sort`)
) ENGINE=InnoDB AUTO_INCREMENT=129 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table Meeting.dictionaryType: ~1 rows (大约)
DELETE FROM `dictionaryType`;
/*!40000 ALTER TABLE `dictionaryType` DISABLE KEYS */;
INSERT INTO `dictionaryType` (`id`, `title`, `remark`, `sort`, `updateTime`, `insertTime`) VALUES
	(1, '通用状态', 'status字段', 0, '2019-09-03 23:35:59', '2019-08-19 11:54:40');
/*!40000 ALTER TABLE `dictionaryType` ENABLE KEYS */;

-- Dumping structure for table Meeting.grade
CREATE TABLE IF NOT EXISTS `grade` (
  `id` smallint unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table Meeting.grade: ~0 rows (大约)
DELETE FROM `grade`;
/*!40000 ALTER TABLE `grade` DISABLE KEYS */;
INSERT INTO `grade` (`id`, `name`) VALUES
	(1, '1级');
/*!40000 ALTER TABLE `grade` ENABLE KEYS */;

-- Dumping structure for table Meeting.role
CREATE TABLE IF NOT EXISTS `role` (
  `id` smallint unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(12) NOT NULL DEFAULT '',
  `description` varchar(200) NOT NULL DEFAULT '',
  `sort` smallint unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table Meeting.role: ~3 rows (大约)
DELETE FROM `role`;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` (`id`, `name`, `description`, `sort`) VALUES
	(1, 'admin', '管理员', 0),
	(2, 'user', '普通用户', 0),
	(3, 'unverified', '未验证用户', 0);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;

-- Dumping structure for table Meeting.selection
CREATE TABLE IF NOT EXISTS `selection` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `type` bigint unsigned NOT NULL DEFAULT '0',
  `mainId` bigint unsigned NOT NULL DEFAULT '0',
  `associatedId` bigint unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=152 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table Meeting.selection: ~0 rows (大约)
DELETE FROM `selection`;
/*!40000 ALTER TABLE `selection` DISABLE KEYS */;
/*!40000 ALTER TABLE `selection` ENABLE KEYS */;

-- Dumping structure for table Meeting.user
CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `nickname` varchar(16) NOT NULL DEFAULT '',
  `username` varchar(16) NOT NULL DEFAULT '',
  `password` varchar(64) NOT NULL DEFAULT '',
  `email` varchar(64) NOT NULL DEFAULT '',
  `grade` smallint unsigned NOT NULL DEFAULT '1',
  `openId` varchar(50) NOT NULL DEFAULT '',
  `status` bigint unsigned NOT NULL DEFAULT '0',
  `remark` varchar(200) NOT NULL DEFAULT '',
  `insertTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `loginName` (`username`),
  UNIQUE KEY `email` (`email`),
  KEY `grade` (`grade`),
  KEY `status` (`status`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- Dumping data for table Meeting.user: ~3 rows (大约)
DELETE FROM `user`;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `nickname`, `username`, `password`, `email`, `grade`, `openId`, `status`, `remark`, `insertTime`, `updateTime`) VALUES
	(1, '系统管理员', 'admin', '$2a$10$ajTvGEPr5fJoo5fudt0U3.SFFOQF9CwDZZ9xvTeGpp2bfmkFoC6U.', '1041836312@qq.com', 1, '', 1, '管理员的账号啊', '2019-07-23 23:54:43', '2019-08-01 10:26:41'),
	(15, '牛楠楠', 'nnnnn', '$2a$10$95.Afkpss2O/yfWoZybCQOVxkFwebSWJGiYqc08v0DIvYBp09iTae', 'test@qq.com', 1, '', 0, '', '2020-06-05 03:33:30', '2020-06-05 04:50:43');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

-- Dumping structure for table Meeting.userRole
CREATE TABLE IF NOT EXISTS `userRole` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `userId` bigint unsigned NOT NULL DEFAULT '0',
  `roleId` smallint unsigned NOT NULL DEFAULT '0',
  `insertTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  KEY `roleId` (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table Meeting.userRole: ~5 rows (大约)
DELETE FROM `userRole`;
/*!40000 ALTER TABLE `userRole` DISABLE KEYS */;
INSERT INTO `userRole` (`id`, `userId`, `roleId`, `insertTime`) VALUES
	(1, 1, 1, '2019-08-15 16:37:36'),
	(2, 1, 2, '2019-08-15 16:37:36'),
	(3, 1, 3, '2019-08-15 16:37:36'),
	(13, 15, 2, '2020-06-05 03:33:30');
/*!40000 ALTER TABLE `userRole` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
