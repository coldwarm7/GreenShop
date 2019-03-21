/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : localhost:3306
 Source Schema         : cakesshop

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 21/03/2019 18:21:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cover` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `image1` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `image2` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` float NULL DEFAULT NULL,
  `intro` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `stock` int(11) NULL DEFAULT NULL,
  `type_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_type_id_idx`(`type_id`) USING BTREE,
  CONSTRAINT `fk_type_id` FOREIGN KEY (`type_id`) REFERENCES `type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB AUTO_INCREMENT = 188 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (181, '猕猴桃', '/greenshop/picture/1552811362353.jpg', '/greenshop/picture/1552811362361.jpg', '/greenshop/picture/1552811362366.jpg', 6.01, '猕猴桃是营养价值极高的水果  被誉为“水果之王”、“维C之王”  每天吃一个  让你每天都容光焕发  变更美～  熬夜最佳补品  猕猴桃含有丰富的维生素C，可强化免疫系统，补充脑力所消耗的营养；它的低钠高钾的完美比例，可补充熬夜加班所失去的体力。', 99, 1);
INSERT INTO `goods` VALUES (182, '苹果', '/greenshop/picture/1552811387910.jpg', '/greenshop/picture/1552811387916.jpg', '/greenshop/picture/1552811387921.jpg', 5, '绿添福1斤村状元冰糖心苹果水果批 包邮 当季新鲜水果萍果红富士', 10, 1);
INSERT INTO `goods` VALUES (184, '橘子', '/greenshop/picture/1552811432034.jpg', '/greenshop/picture/1552811432041.jpg', '/greenshop/picture/1552811432047.jpg', 6.6, '广西武鸣沃柑橘子水果桔子新鲜薄皮包邮当季皇帝贡柑', 10, 1);
INSERT INTO `goods` VALUES (185, '土豆', '/greenshop/picture/1552811449095.jpg', '/greenshop/picture/1552811449101.jpg', '/greenshop/picture/1552811449108.jpg', 3, '土豆含有丰富的维生素及钙、钾等微量元素，且易于消化吸收，营养丰富。能供给人体大量的热能，人只靠马铃薯和全脂牛奶就足以维持生命和健康，在欧美国家特别是北美，土豆早就成为第二主食', 10, 2);
INSERT INTO `goods` VALUES (186, '芹菜', '/greenshop/picture/1553160949984.jpg', '/greenshop/picture/1553160949989.jpg', '/greenshop/picture/1553160949995.jpg', 6.1, '芹菜', 999, 2);

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `total` float NULL DEFAULT NULL,
  `amount` int(6) NULL DEFAULT NULL,
  `status` tinyint(1) NULL DEFAULT NULL,
  `paytype` tinyint(1) NULL DEFAULT NULL,
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `datetime` datetime(0) NULL DEFAULT NULL,
  `user_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_user_id_idx`(`user_id`) USING BTREE,
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES (6, 6.01, 1, 2, 1, '魏志林', '15848174313', '内蒙古自治区呼和浩特市赛罕区大学西路内蒙古大学东区宿舍', '2019-03-20 12:18:51', 6);
INSERT INTO `order` VALUES (7, 6.01, 1, 2, 1, '魏志林', '15848174313', '内蒙古自治区呼和浩特市赛罕区大学西路内蒙古大学东区宿舍', '2019-03-20 13:55:59', 6);
INSERT INTO `order` VALUES (18, 9, 3, 2, 1, '魏志林', '18245631746', '内蒙古自治区呼和浩特市赛罕区大学西路内蒙古大学', '2019-03-20 15:42:50', 25);
INSERT INTO `order` VALUES (20, 19.8, 3, 3, 1, '魏志林', '18245631746', '内蒙古自治区呼和浩特市赛罕区大学西路内蒙古大学', '2019-03-20 15:44:49', 25);
INSERT INTO `order` VALUES (21, 6, 2, 3, 1, '魏志林', '18245631746', '内蒙古自治区呼和浩特市赛罕区大学西路内蒙古大学', '2019-03-20 15:45:28', 25);
INSERT INTO `order` VALUES (22, 9, 3, 3, 1, '魏志林', '18245631746', '内蒙古自治区呼和浩特市赛罕区大学西路内蒙古大学', '2019-03-20 15:45:56', 25);

-- ----------------------------
-- Table structure for orderitem
-- ----------------------------
DROP TABLE IF EXISTS `orderitem`;
CREATE TABLE `orderitem`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `price` float NULL DEFAULT NULL,
  `amount` int(11) NULL DEFAULT NULL,
  `goods_id` int(11) NULL DEFAULT NULL,
  `order_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_order_id_idx`(`order_id`) USING BTREE,
  INDEX `fk_orderitem_goods_id_idx`(`goods_id`) USING BTREE,
  CONSTRAINT `fk_order_id` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_orderitem_goods_id` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orderitem
-- ----------------------------
INSERT INTO `orderitem` VALUES (6, 6.01, 1, 181, 6);
INSERT INTO `orderitem` VALUES (7, 6.01, 1, 181, 7);
INSERT INTO `orderitem` VALUES (12, 3, 3, 185, 18);
INSERT INTO `orderitem` VALUES (14, 6.6, 3, 184, 20);
INSERT INTO `orderitem` VALUES (15, 3, 2, 185, 21);
INSERT INTO `orderitem` VALUES (16, 3, 3, 185, 22);

-- ----------------------------
-- Table structure for recommend
-- ----------------------------
DROP TABLE IF EXISTS `recommend`;
CREATE TABLE `recommend`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` tinyint(1) NULL DEFAULT NULL,
  `goods_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_goods_id_idx`(`goods_id`) USING BTREE,
  CONSTRAINT `fk_goods_id` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE = InnoDB AUTO_INCREMENT = 42 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of recommend
-- ----------------------------
INSERT INTO `recommend` VALUES (29, 1, 181);
INSERT INTO `recommend` VALUES (30, 2, 181);
INSERT INTO `recommend` VALUES (31, 3, 181);
INSERT INTO `recommend` VALUES (32, 2, 182);
INSERT INTO `recommend` VALUES (36, 2, 184);
INSERT INTO `recommend` VALUES (38, 2, 185);
INSERT INTO `recommend` VALUES (40, 2, 186);
INSERT INTO `recommend` VALUES (41, 3, 186);

-- ----------------------------
-- Table structure for type
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of type
-- ----------------------------
INSERT INTO `type` VALUES (1, '绿色水果');
INSERT INTO `type` VALUES (2, '绿色蔬菜');
INSERT INTO `type` VALUES (3, '绿色保健品');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `isadmin` bit(1) NULL DEFAULT NULL,
  `isvalidate` bit(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username_UNIQUE`(`username`) USING BTREE,
  UNIQUE INDEX `email_UNIQUE`(`email`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 36 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (6, 'admin', 'admin@sikiedu.com', 'admin', 'fz', '12312312313', '西安邮电大学', b'1', b'0');
INSERT INTO `user` VALUES (24, '王磊', '0161122147@mail.imu.edu.cn', 'wl1997', '王磊', '12312313', '西安邮电大学长安校区', b'0', b'0');
INSERT INTO `user` VALUES (25, '1', '1', '1', '魏志林', '18245631746', '内蒙古自治区呼和浩特市赛罕区大学西路内蒙古大学', NULL, NULL);
INSERT INTO `user` VALUES (26, 'apitest', '123', '123', 'feizhen', '1234444', 'sadadsada', b'0', b'0');
INSERT INTO `user` VALUES (35, 'zhengchang', '888', '777', 'feizhen', '123444', 'sadadsada', b'0', b'0');

SET FOREIGN_KEY_CHECKS = 1;
