-- phpMyAdmin SQL Dump
-- version phpStudy 2014
-- http://www.phpmyadmin.net
--
-- 主机: localhost
-- 生成日期: 2018 年 05 月 13 日 07:06
-- 服务器版本: 5.5.53-log
-- PHP 版本: 5.4.45

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- 数据库: `test_db`
--

-- --------------------------------------------------------

--
-- 表的结构 `permission`
--

CREATE TABLE IF NOT EXISTS `permission` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL COMMENT '权限名',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8mb4 AUTO_INCREMENT=3 ;

--
-- 转存表中的数据 `permission`
--

INSERT INTO `permission` (`id`, `name`, `create_time`, `update_time`) VALUES
(1, 'user:create', '2018-05-11 16:00:00', '2018-05-12 05:13:09'),
(2, 'user:view', '2018-05-11 16:00:00', '2018-05-12 05:13:09');

-- --------------------------------------------------------

--
-- 表的结构 `role`
--

CREATE TABLE IF NOT EXISTS `role` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL COMMENT '角色名',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8mb4 AUTO_INCREMENT=3 ;

--
-- 转存表中的数据 `role`
--

INSERT INTO `role` (`id`, `name`, `create_time`, `update_time`) VALUES
(1, 'admin', '2018-05-12 05:00:00', '2018-05-12 05:11:11'),
(2, 'user', '2018-05-11 16:00:00', '2018-05-12 05:10:37');

-- --------------------------------------------------------

--
-- 表的结构 `role_permission`
--

CREATE TABLE IF NOT EXISTS `role_permission` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `role_id` int(11) unsigned NOT NULL COMMENT '用户id',
  `permission_id` int(11) unsigned NOT NULL COMMENT '权限id',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8mb4 AUTO_INCREMENT=4 ;

--
-- 转存表中的数据 `role_permission`
--

INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES
(1, 1, 1),
(2, 1, 2),
(3, 2, 2);

-- --------------------------------------------------------

--
-- 表的结构 `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `phone_num` varchar(100) DEFAULT NULL COMMENT '手机号',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `credit` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '积分',
  `avatar` varchar(200) DEFAULT NULL COMMENT '头像url',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态 0正常1封禁',
  `salt2` varchar(50) NOT NULL COMMENT '盐2',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8mb4 AUTO_INCREMENT=6 ;

--
-- 转存表中的数据 `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `email`, `phone_num`, `create_time`, `update_time`, `credit`, `avatar`, `status`, `salt2`) VALUES
(2, 'withstar', '752aa427b3d1d8250f5ed2ade73f6bdb', NULL, NULL, '2018-05-10 14:53:11', '2018-05-12 07:19:35', 0, NULL, 0, '9b1bef2e77a15aaf9a1d06f0c7c03f1f'),
(3, 'withstars', '232dfaf9e0636c0bbf339217ce13c23c', NULL, NULL, '2018-05-11 01:27:50', '2018-05-12 07:19:40', 0, NULL, 1, '834b1d3cb0e477afd9f3ac5dd9d15e3f'),
(4, 'value', '9a8d32f68864929f0d2ed8485b26c38e', NULL, NULL, '2018-05-12 07:25:49', '2018-05-12 07:25:50', 0, NULL, 0, '8b9d207d176abd6525c68db05f9d5b78'),
(5, 'mike', '417ef97516f6d4ee4b58ce0511133b93', NULL, NULL, '2018-05-12 11:09:43', '2018-05-12 11:09:43', 0, NULL, 0, 'ff6f7eec3db419f5752c421331185f38');

-- --------------------------------------------------------

--
-- 表的结构 `user_role`
--

CREATE TABLE IF NOT EXISTS `user_role` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) unsigned NOT NULL COMMENT '用户id',
  `role_id` int(11) unsigned NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8mb4 AUTO_INCREMENT=5 ;

--
-- 转存表中的数据 `user_role`
--

INSERT INTO `user_role` (`id`, `user_id`, `role_id`) VALUES
(1, 2, 2),
(2, 3, 1),
(3, 4, 1),
(4, 5, 2);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
