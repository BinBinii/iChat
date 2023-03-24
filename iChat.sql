/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80030 (8.0.30)
 Source Host           : localhost:3306
 Source Schema         : iChat

 Target Server Type    : MySQL
 Target Server Version : 80030 (8.0.30)
 File Encoding         : 65001

 Date: 24/03/2023 20:40:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_friend
-- ----------------------------
DROP TABLE IF EXISTS `tb_friend`;
CREATE TABLE `tb_friend` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `friend_id` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of tb_friend
-- ----------------------------
BEGIN;
INSERT INTO `tb_friend` (`id`, `user_id`, `friend_id`) VALUES (1, '4150901', '4150902');
INSERT INTO `tb_friend` (`id`, `user_id`, `friend_id`) VALUES (2, '4150901', '4150903');
INSERT INTO `tb_friend` (`id`, `user_id`, `friend_id`) VALUES (3, '4150901', '4150904');
INSERT INTO `tb_friend` (`id`, `user_id`, `friend_id`) VALUES (4, '4150901', '4150905');
INSERT INTO `tb_friend` (`id`, `user_id`, `friend_id`) VALUES (5, '4150901', '4150906');
INSERT INTO `tb_friend` (`id`, `user_id`, `friend_id`) VALUES (6, '4150902', '4150901');
INSERT INTO `tb_friend` (`id`, `user_id`, `friend_id`) VALUES (7, '4150902', '4150903');
INSERT INTO `tb_friend` (`id`, `user_id`, `friend_id`) VALUES (8, '4150902', '4150904');
INSERT INTO `tb_friend` (`id`, `user_id`, `friend_id`) VALUES (9, '4150902', '4150905');
INSERT INTO `tb_friend` (`id`, `user_id`, `friend_id`) VALUES (10, '4150902', '4150906');
INSERT INTO `tb_friend` (`id`, `user_id`, `friend_id`) VALUES (11, '4150903', '4150901');
INSERT INTO `tb_friend` (`id`, `user_id`, `friend_id`) VALUES (12, '4150903', '4150902');
INSERT INTO `tb_friend` (`id`, `user_id`, `friend_id`) VALUES (13, '4150903', '4150904');
INSERT INTO `tb_friend` (`id`, `user_id`, `friend_id`) VALUES (14, '4150903', '4150905');
COMMIT;

-- ----------------------------
-- Table structure for tb_group
-- ----------------------------
DROP TABLE IF EXISTS `tb_group`;
CREATE TABLE `tb_group` (
  `id` varchar(50) NOT NULL COMMENT '群ID',
  `name` varchar(30) NOT NULL COMMENT '群名称',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `admin` varchar(30) NOT NULL COMMENT '群主ID',
  `notice` varchar(200) DEFAULT NULL COMMENT '群公告'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of tb_group
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for tb_group_messages
-- ----------------------------
DROP TABLE IF EXISTS `tb_group_messages`;
CREATE TABLE `tb_group_messages` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '群消息ID',
  `group_id` varchar(50) NOT NULL COMMENT '群ID',
  `content` text NOT NULL COMMENT '消息内容',
  `from_user` varchar(30) NOT NULL COMMENT '发送者ID',
  `from_nickname` varchar(10) NOT NULL COMMENT '发送者昵称',
  `create_time` datetime NOT NULL COMMENT '发送时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of tb_group_messages
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for tb_group_to_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_group_to_user`;
CREATE TABLE `tb_group_to_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `userId` varchar(30) NOT NULL COMMENT '用户ID',
  `group_id` varchar(50) NOT NULL COMMENT '群聊ID',
  `join_time` datetime NOT NULL COMMENT '加入时间',
  `group_nickname` varchar(10) DEFAULT NULL COMMENT '群内昵称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of tb_group_to_user
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for tb_messages
-- ----------------------------
DROP TABLE IF EXISTS `tb_messages`;
CREATE TABLE `tb_messages` (
  `id` int NOT NULL AUTO_INCREMENT,
  `post_message` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '发送文本',
  `status` bit(1) NOT NULL DEFAULT b'0' COMMENT '接收状态 0:未读 1:已读',
  `send_time` datetime NOT NULL COMMENT '发送时间',
  `from_user` varchar(30) NOT NULL COMMENT '发送人ID',
  `to_user` varchar(30) NOT NULL COMMENT '接收人ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of tb_messages
-- ----------------------------
BEGIN;
INSERT INTO `tb_messages` (`id`, `post_message`, `status`, `send_time`, `from_user`, `to_user`) VALUES (1, 'Hello World!', b'0', '2023-03-24 11:19:48', '4150901', '4150902');
INSERT INTO `tb_messages` (`id`, `post_message`, `status`, `send_time`, `from_user`, `to_user`) VALUES (2, 'Hello', b'0', '2023-03-24 18:31:42', '4150902', '4150901');
COMMIT;

-- ----------------------------
-- Table structure for tb_messages_to
-- ----------------------------
DROP TABLE IF EXISTS `tb_messages_to`;
CREATE TABLE `tb_messages_to` (
  `id` int NOT NULL AUTO_INCREMENT,
  `userId` varchar(30) NOT NULL COMMENT '用户ID',
  `hand` varchar(50) NOT NULL COMMENT '指向用户或者群组',
  `status` bit(1) NOT NULL COMMENT '0:用户 1:群组',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of tb_messages_to
-- ----------------------------
BEGIN;
INSERT INTO `tb_messages_to` (`id`, `userId`, `hand`, `status`) VALUES (1, '4150901', '4150902', b'0');
COMMIT;

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_name` varchar(20) DEFAULT NULL COMMENT '角色名称',
  `role_desc` varchar(255) DEFAULT NULL COMMENT '角色描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of tb_role
-- ----------------------------
BEGIN;
INSERT INTO `tb_role` (`id`, `role_name`, `role_desc`) VALUES (1, 'ROLE_USER', '基本角色');
INSERT INTO `tb_role` (`id`, `role_name`, `role_desc`) VALUES (3, 'ROLE_ADMIN', '超级管理员');
COMMIT;

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `userId` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `sex` varchar(10) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `nickname` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `status` int DEFAULT '1',
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
BEGIN;
INSERT INTO `tb_user` (`userId`, `username`, `password`, `sex`, `image`, `nickname`, `status`) VALUES ('1', 'xiaoming', '$2a$10$CYX9OMv0yO8wR8rE19N2fOaXDJondci5uR68k2eQJm50q8ESsDMlC', '1', NULL, 'nigger', 1);
INSERT INTO `tb_user` (`userId`, `username`, `password`, `sex`, `image`, `nickname`, `status`) VALUES ('4150901', 'user1', '$2a$10$Cg7lPne6bGYRYjEmElzaouTmor7NxXdQoFiXQOZRrLe7IX2sCd6VO', '1', '', '小黄', 1);
INSERT INTO `tb_user` (`userId`, `username`, `password`, `sex`, `image`, `nickname`, `status`) VALUES ('4150902', 'user2', '$2a$10$Cg7lPne6bGYRYjEmElzaouTmor7NxXdQoFiXQOZRrLe7IX2sCd6VO', '0', '', '小刘', 1);
INSERT INTO `tb_user` (`userId`, `username`, `password`, `sex`, `image`, `nickname`, `status`) VALUES ('4150903', 'user3', '$2a$10$Cg7lPne6bGYRYjEmElzaouTmor7NxXdQoFiXQOZRrLe7IX2sCd6VO', '1', '', '小强', 1);
INSERT INTO `tb_user` (`userId`, `username`, `password`, `sex`, `image`, `nickname`, `status`) VALUES ('4150904', 'user4', '$2a$10$Cg7lPne6bGYRYjEmElzaouTmor7NxXdQoFiXQOZRrLe7IX2sCd6VO', '1', '', '铁憨憨', 1);
INSERT INTO `tb_user` (`userId`, `username`, `password`, `sex`, `image`, `nickname`, `status`) VALUES ('4150905', 'user5', '$2a$10$Cg7lPne6bGYRYjEmElzaouTmor7NxXdQoFiXQOZRrLe7IX2sCd6VO', '1', '', '小明', 1);
INSERT INTO `tb_user` (`userId`, `username`, `password`, `sex`, `image`, `nickname`, `status`) VALUES ('4150906', 'user6', '$2a$10$Cg7lPne6bGYRYjEmElzaouTmor7NxXdQoFiXQOZRrLe7IX2sCd6VO', '0', '', '小红', 1);
COMMIT;

-- ----------------------------
-- Table structure for tb_user_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_role`;
CREATE TABLE `tb_user_role` (
  `user_id` int NOT NULL,
  `role_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of tb_user_role
-- ----------------------------
BEGIN;
INSERT INTO `tb_user_role` (`user_id`, `role_id`) VALUES (1, 1);
INSERT INTO `tb_user_role` (`user_id`, `role_id`) VALUES (4150901, 1);
INSERT INTO `tb_user_role` (`user_id`, `role_id`) VALUES (4150901, 3);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
