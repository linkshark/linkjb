/*
Navicat MariaDB Data Transfer

Source Server         : 云主机
Source Server Version : 100036
Source Host           : linkjb.com:3306
Source Database       : mybatis

Target Server Type    : MariaDB
Target Server Version : 100036
File Encoding         : 65001

Date: 2019-01-09 16:55:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for blackUsers
-- ----------------------------
DROP TABLE IF EXISTS `blackUsers`;
CREATE TABLE `blackUsers` (
  `userId` int(255) NOT NULL COMMENT '被举报者ID',
  `reportId` int(255) NOT NULL COMMENT '举报者ID',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '更新时间',
  `reportResult` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '举报原因',
  `status` int(2) DEFAULT '1' COMMENT '状态值 0为失效 1为有效 默认为1',
  PRIMARY KEY (`userId`,`reportId`),
  KEY `reportId` (`reportId`),
  CONSTRAINT `reportId` FOREIGN KEY (`reportId`) REFERENCES `users` (`id`),
  CONSTRAINT `userId` FOREIGN KEY (`userId`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of blackUsers
-- ----------------------------
INSERT INTO `blackUsers` VALUES ('284', '285', null, null, null, '1');
INSERT INTO `blackUsers` VALUES ('284', '286', null, null, null, '1');

-- ----------------------------
-- Table structure for likes
-- ----------------------------
DROP TABLE IF EXISTS `likes`;
CREATE TABLE `likes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ownerId` int(100) DEFAULT NULL,
  `likeId` int(100) DEFAULT NULL,
  `modifyTime` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of likes
-- ----------------------------
INSERT INTO `likes` VALUES ('29', '284', '286', '2018-11-05 22:21:51');
INSERT INTO `likes` VALUES ('36', '284', '287', '2018-11-21 20:16:19');
INSERT INTO `likes` VALUES ('37', '284', '285', '2018-11-21 22:33:36');
INSERT INTO `likes` VALUES ('38', '284', '290', '2018-10-26 17:19:24');
INSERT INTO `likes` VALUES ('39', '284', '289', '2018-11-21 20:16:23');
INSERT INTO `likes` VALUES ('40', '284', '291', '2018-11-13 23:22:27');
INSERT INTO `likes` VALUES ('41', '284', '293', '2018-11-19 01:22:06');
INSERT INTO `likes` VALUES ('42', '284', '288', '2018-11-21 20:15:52');
INSERT INTO `likes` VALUES ('43', '284', '294', '2018-11-02 00:22:21');
INSERT INTO `likes` VALUES ('44', '284', '292', '2018-11-21 20:16:16');
INSERT INTO `likes` VALUES ('45', '285', '284', '2018-10-21 20:47:42');
INSERT INTO `likes` VALUES ('46', '284', '298', '2018-10-29 19:33:10');
INSERT INTO `likes` VALUES ('47', '284', '297', '2018-10-29 23:18:41');
INSERT INTO `likes` VALUES ('48', '284', '284', '2018-11-19 15:35:56');
INSERT INTO `likes` VALUES ('49', '299', '299', '2018-10-29 22:09:29');
INSERT INTO `likes` VALUES ('50', '296', '299', '2018-10-29 23:59:35');
INSERT INTO `likes` VALUES ('51', '293', '284', '2018-10-30 00:16:17');
INSERT INTO `likes` VALUES ('52', '296', '296', '2018-10-30 00:18:59');
INSERT INTO `likes` VALUES ('53', '299', '296', '2018-10-30 00:28:48');
INSERT INTO `likes` VALUES ('54', '299', '291', '2018-10-30 00:30:59');
INSERT INTO `likes` VALUES ('55', '288', '284', '2018-10-31 22:44:20');
INSERT INTO `likes` VALUES ('56', '288', '295', '2018-10-31 22:44:28');
INSERT INTO `likes` VALUES ('57', '284', '296', '2018-11-08 22:18:44');
INSERT INTO `likes` VALUES ('58', '284', null, '2018-11-04 14:16:36');
INSERT INTO `likes` VALUES ('59', '284', null, '2018-11-04 14:28:04');
INSERT INTO `likes` VALUES ('60', '284', '300', '2018-11-04 14:31:19');
INSERT INTO `likes` VALUES ('61', '300', '304', '2018-11-04 17:04:50');
INSERT INTO `likes` VALUES ('62', '300', '285', '2018-11-04 17:13:09');
INSERT INTO `likes` VALUES ('63', '300', '288', '2018-11-04 17:16:04');
INSERT INTO `likes` VALUES ('64', '300', '298', '2018-11-04 20:29:26');
INSERT INTO `likes` VALUES ('65', '306', '285', '2018-11-05 23:44:33');
INSERT INTO `likes` VALUES ('66', '284', '301', '2018-11-13 23:22:40');
INSERT INTO `likes` VALUES ('67', '284', '295', '2018-11-21 20:16:26');
INSERT INTO `likes` VALUES ('68', '284', '299', '2018-11-19 22:20:56');
INSERT INTO `likes` VALUES ('69', '1', '284', '2018-11-26 23:40:22');
INSERT INTO `likes` VALUES ('70', '308', '324', '2018-12-08 17:03:20');
INSERT INTO `likes` VALUES ('71', '315', '310', '2018-12-10 17:00:04');
INSERT INTO `likes` VALUES ('72', '312', '322', '2018-12-14 14:46:46');
INSERT INTO `likes` VALUES ('73', '312', '317', '2018-12-14 14:47:04');
INSERT INTO `likes` VALUES ('74', '312', '312', '2018-12-14 14:47:09');
INSERT INTO `likes` VALUES ('75', '312', '315', '2018-12-14 21:02:38');
INSERT INTO `likes` VALUES ('76', '312', '314', '2018-12-14 23:09:32');
INSERT INTO `likes` VALUES ('77', '326', '320', '2018-12-17 09:33:55');
INSERT INTO `likes` VALUES ('78', '326', '326', '2018-12-19 11:46:40');
INSERT INTO `likes` VALUES ('79', '336', '335', '2018-12-19 12:34:22');
INSERT INTO `likes` VALUES ('80', '326', '312', '2018-12-20 10:28:18');
INSERT INTO `likes` VALUES ('81', '326', '321', '2018-12-20 10:28:23');
INSERT INTO `likes` VALUES ('82', '326', '314', '2018-12-20 10:28:26');
INSERT INTO `likes` VALUES ('83', '326', '309', '2018-12-20 10:28:32');
INSERT INTO `likes` VALUES ('84', '326', '308', '2018-12-20 10:28:39');
INSERT INTO `likes` VALUES ('85', '326', '324', '2018-12-20 10:28:43');
INSERT INTO `likes` VALUES ('86', '327', '315', '2018-12-21 09:43:18');
INSERT INTO `likes` VALUES ('87', '327', '319', '2018-12-21 09:43:44');
INSERT INTO `likes` VALUES ('88', '327', '317', '2018-12-21 09:43:48');
INSERT INTO `likes` VALUES ('89', '339', '312', '2018-12-21 10:10:13');
INSERT INTO `likes` VALUES ('90', '339', '317', '2018-12-21 10:10:17');
INSERT INTO `likes` VALUES ('91', '339', '308', '2018-12-21 10:12:37');
INSERT INTO `likes` VALUES ('92', '340', '326', '2018-12-21 10:25:50');
INSERT INTO `likes` VALUES ('93', '340', '319', '2018-12-21 10:26:14');
INSERT INTO `likes` VALUES ('94', '340', '311', '2018-12-21 10:26:18');
INSERT INTO `likes` VALUES ('95', '340', '315', '2018-12-21 10:26:29');
INSERT INTO `likes` VALUES ('96', '340', '310', '2019-01-02 13:53:34');
INSERT INTO `likes` VALUES ('97', '340', '314', '2018-12-24 13:20:08');
INSERT INTO `likes` VALUES ('98', '340', '325', '2018-12-25 10:58:36');
INSERT INTO `likes` VALUES ('99', '340', '321', '2019-01-08 14:36:37');
INSERT INTO `likes` VALUES ('100', '340', '317', '2019-01-02 21:55:06');
INSERT INTO `likes` VALUES ('101', '340', '309', '2019-01-02 21:55:12');

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ownerId` int(255) NOT NULL COMMENT '查看人的Id',
  `passerId` int(255) NOT NULL COMMENT '被留言者的ID',
  `message` varchar(200) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ownerId` (`ownerId`),
  KEY `passerId` (`passerId`),
  CONSTRAINT `ownerId` FOREIGN KEY (`ownerId`) REFERENCES `users` (`id`),
  CONSTRAINT `passerId` FOREIGN KEY (`passerId`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('1', '284', '286', '好靓', '2018-10-17 23:34:50');
INSERT INTO `message` VALUES ('2', '284', '294', '小妹妹', '2018-10-17 23:35:36');
INSERT INTO `message` VALUES ('3', '284', '289', '你好', '2018-10-17 23:41:11');
INSERT INTO `message` VALUES ('4', '284', '291', '你好,有兴趣了解一下我吗', '2018-10-17 23:50:10');
INSERT INTO `message` VALUES ('5', '284', '286', 'x小姐姐', '2018-10-17 23:50:55');
INSERT INTO `message` VALUES ('6', '284', '286', '小姐大', '2018-10-17 23:53:41');
INSERT INTO `message` VALUES ('7', '284', '286', 'qwq', '2018-10-20 21:28:52');
INSERT INTO `message` VALUES ('8', '285', '284', '小帅哥', '2018-10-22 20:34:09');
INSERT INTO `message` VALUES ('9', '284', '287', '哈哈哈', '2018-10-22 22:38:54');
INSERT INTO `message` VALUES ('10', '284', '286', '你好', '2018-10-26 17:20:37');
INSERT INTO `message` VALUES ('11', '299', '296', '小伙子，我看你骨骼惊奇，很有前途，跟我混吧', '2018-10-30 00:28:39');
INSERT INTO `message` VALUES ('12', '288', '295', '小帅哥', '2018-10-31 22:45:29');
INSERT INTO `message` VALUES ('13', '288', '295', '小帅哥，不错哟', '2018-10-31 22:46:07');
INSERT INTO `message` VALUES ('14', '288', '284', '老哥，稳', '2018-10-31 22:46:34');
INSERT INTO `message` VALUES ('15', '289', '284', '滚', '2018-10-31 23:50:09');
INSERT INTO `message` VALUES ('16', '284', '286', '小姐姐，你好啊', '2018-11-04 12:31:42');
INSERT INTO `message` VALUES ('17', '284', '300', '1112', '2018-11-04 14:32:44');
INSERT INTO `message` VALUES ('18', '284', '292', '你好呀', '2018-11-21 19:31:28');
INSERT INTO `message` VALUES ('19', '312', '315', '111', '2018-12-14 21:02:31');
INSERT INTO `message` VALUES ('20', '336', '335', 'hello', '2018-12-19 12:35:09');
INSERT INTO `message` VALUES ('21', '312', '320', '11', '2018-12-24 22:29:35');
INSERT INTO `message` VALUES ('22', '340', '321', '阿斯蒂', '2019-01-08 14:36:56');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '用户登录账户名',
  `age` int(5) DEFAULT NULL COMMENT '用户年龄',
  `email` varchar(30) DEFAULT NULL COMMENT '邮箱地址',
  `passWord` varchar(20) DEFAULT NULL COMMENT '登录密码',
  `mobile` varchar(11) DEFAULT NULL COMMENT '电话号码',
  `gender` varchar(1) DEFAULT NULL COMMENT '1:男 2:女',
  `findGender` varchar(1) DEFAULT NULL COMMENT '1:男 2:女',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `imageUrl` varchar(100) DEFAULT NULL COMMENT '图片地址',
  `findFlag` varchar(5) DEFAULT NULL COMMENT '1:男找女 2:男找男 3:女找男 4:女找女',
  `bodyShape` varchar(5) DEFAULT NULL COMMENT '1:苗条型 2:标准型 3:丰满型 4:保密',
  `job` varchar(10) DEFAULT NULL COMMENT '职业对应ID',
  `tall` int(5) DEFAULT NULL COMMENT '身高 cm',
  `marriageFlag` varchar(10) DEFAULT '1' COMMENT '是否结过婚 0结婚过 1未曾婚',
  `education` varchar(5) DEFAULT NULL COMMENT '0未曾上过学 1小学 2中学 3高中 4大专 5本科 6硕士 7 博士',
  `weight` int(5) DEFAULT NULL COMMENT '体重',
  `money` varchar(10) DEFAULT NULL COMMENT '年收入 /万元',
  `drink` varchar(10) DEFAULT NULL COMMENT '是否喝酒',
  `smoke` varchar(10) DEFAULT NULL COMMENT '是否抽烟',
  `child` varchar(10) DEFAULT NULL COMMENT '是否要孩子',
  `sport` varchar(10) DEFAULT NULL COMMENT '是否运动',
  `detail` varchar(3000) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '个人介绍',
  `leaveMessage` varchar(3000) DEFAULT NULL,
  `heartLove` int(100) NOT NULL DEFAULT '0' COMMENT '点赞数',
  `address` varchar(100) DEFAULT NULL COMMENT '家庭住址',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifyTime` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '最近修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=342 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('308', '杰伦咯咯咯', '37', 'jjjj@126.com', 'jjj', '13544444444', '1', '2', '1981-02-03', 'http://www.linkjb.com/linkjb/images/20181204170316.jpg8547', '1', null, '5', '176', '1', '3', '59', '4', '2', '2', '2', '2', '我是个歌手', null, '2', '上海市/市辖区/闵行区', '2018-12-04 17:04:14', '2018-12-21 10:12:37');
INSERT INTO `users` VALUES ('309', '布兰妮', '31', '120884765521@qq.com', 'bln', '13565448875', '2', '1', '1987-08-04', 'http://www.linkjb.com/linkjb/images/20181207172333.jpg3286', '3', null, '4', '152', '2', '1', '42', '3', '2', '2', '2', '2', 'kawayi', null, '2', '上海市/市辖区/松江区', '2018-12-07 17:23:38', '2019-01-02 21:55:12');
INSERT INTO `users` VALUES ('310', '饭饭1', '32', '1254@qq.vom', 'ff1', '15284457744', '2', '1', '1986-10-21', 'http://www.linkjb.com/linkjb/images/20181207172710.jpg5657', '3', null, null, null, null, null, null, null, null, null, null, null, null, null, '3', null, '2018-12-07 17:27:16', '2019-01-02 13:53:34');
INSERT INTO `users` VALUES ('311', '冰冰1', '36', '冰冰@qq.com', 'bb1', '13588445551', '2', '2', '1982-03-04', 'http://www.linkjb.com/linkjb/images/20181207183007.jpg6062', '4', null, '5', '168', '2', '4', '58', '3', '2', '2', '2', '2', '1254', null, '1', '浙江省/杭州市/建德市', '2018-12-07 18:32:30', '2018-12-21 10:26:18');
INSERT INTO `users` VALUES ('312', '德玛西亚', '43', '1204588@11.com', 'dmxy', '13055448845', '1', '1', '1975-06-24', 'http://www.linkjb.com/linkjb/images/20181208164658.jpg9083', '2', null, null, null, null, null, null, null, null, null, null, null, null, null, '3', null, '2018-12-08 16:47:03', '2018-12-21 10:10:13');
INSERT INTO `users` VALUES ('314', '小妹儿米', '27', 'aaaa@126.com', 'qwe123', '13055442210', '2', '2', '1991-02-05', 'http://www.linkjb.com/linkjb/images/20181208164858.jpg8393', '4', null, '14', '159', '1', '2', '48', '3', '2', '2', '2', '2', '昂是', null, '3', '浙江省/杭州市/西湖区', '2018-12-08 16:49:01', '2018-12-24 13:20:08');
INSERT INTO `users` VALUES ('315', '东尼小木木', '37', 'qqq@qq.com', 'qwe123', '13566213146', '1', '1', '1981-02-03', 'http://www.linkjb.com/linkjb/images/20181208164953.jpg7997', '2', null, '4', '172', '2', '3', '65', '3', '2', '2', '2', '2', '你好', null, '3', '浙江省/杭州市/上城区', '2018-12-08 16:49:56', '2018-12-21 10:26:29');
INSERT INTO `users` VALUES ('316', '丽红汪', '38', 'qq@11.com', 'qwe123', '13566721112', '1', '2', '1980-01-07', 'http://www.linkjb.com/linkjb/images/20181208165101.jpg7513', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, '0', null, '2018-12-08 16:51:11', null);
INSERT INTO `users` VALUES ('317', '加藤老师11', '43', 'qqqq@126.com', 'qwe123', '13856449951', '1', '1', '1975-06-03', 'http://www.linkjb.com/linkjb/images/20181208165157.jpg2701', '2', null, '2', '178', '2', '4', '58', '4', '2', '2', '2', '2', '加藤老师', null, '4', '浙江省/杭州市/拱墅区', '2018-12-08 16:51:59', '2019-01-02 21:55:06');
INSERT INTO `users` VALUES ('319', '安倍', '57', '123@qq.com', 'qwe123', '13566221112', '1', '2', '1961-02-07', 'http://www.linkjb.com/linkjb/images/20181208165322.jpg3203', '1', null, '5', '180', '2', '4', '68', '4', '2', '2', '2', '2', '幺妹，你好', null, '2', '浙江省/杭州市/拱墅区', '2018-12-08 16:54:12', '2018-12-21 10:26:14');
INSERT INTO `users` VALUES ('320', '吉泽', '29', '125465@qq.com', 'qwe123', '13522145995', '2', '1', '1989-09-04', 'http://www.linkjb.com/linkjb/images/20181208165444.jpg3839', '3', null, '6', '165', '1', '2', '50', '1', '1', '2', '2', '2', '我是个小妹儿米', null, '1', '浙江省/杭州市/拱墅区', '2018-12-08 16:55:24', '2018-12-17 09:33:55');
INSERT INTO `users` VALUES ('321', '小妹妹', '32', 'qqw@126.com', 'qwe123', '12254654413', '2', '1', '1986-11-19', 'http://www.linkjb.com/linkjb/images/20181208165552.jpg2712', '3', null, '3', '162', '2', '4', '50', '1', '2', '2', '2', 'null', '你好呀', null, '3', '浙江省/杭州市/西湖区', '2018-12-08 16:56:40', '2019-01-08 14:36:37');
INSERT INTO `users` VALUES ('322', '许山高', '32', 'qqq@163.com', 'qwe123', '13966548854', '1', '2', '1986-09-21', 'http://www.linkjb.com/linkjb/images/20181208165657.jpg2122', '1', null, '3', '173', '2', '5', '56', '4', '2', '2', '2', '2', '我是个歌手', null, '1', '浙江省/杭州市/西湖区', '2018-12-08 16:57:42', '2018-12-14 22:47:52');
INSERT INTO `users` VALUES ('323', '哈喽', '34', 'mali@163.com', 'qwe123', '13655544995', '2', '1', '1984-05-15', 'http://www.linkjb.com/linkjb/images/20181208165824.jpg8148', '3', null, null, null, null, null, null, null, null, null, null, null, null, null, '0', null, '2018-12-08 16:58:45', null);
INSERT INTO `users` VALUES ('324', '新源', '47', 'xinyuan@qq.com', 'qwe123', '13000000000', '2', '1', '1971-06-15', 'http://www.linkjb.com/linkjb/images/20181208165907.jpg7749', '3', null, '4', '165', '2', '4', '65', '1', '2', '2', '2', '2', 'null', null, '2', '河北省/秦皇岛市/北戴河区', '2018-12-08 17:00:34', '2018-12-20 10:28:43');
INSERT INTO `users` VALUES ('325', '大姐姐', '28', 'dajie@163.com', 'qwe123', '16855442135', '2', '1', '1990-06-18', 'http://www.linkjb.com/linkjb/images/20181208170056.jpg7445', '3', null, null, null, null, null, null, null, null, null, null, null, null, null, '1', null, '2018-12-08 17:01:44', '2018-12-25 10:58:36');
INSERT INTO `users` VALUES ('326', '猪头三', '26', 'zhuzhu@qq.com', 'qwe123', '17321168665', '1', '2', '1992-03-10', 'http://www.linkjb.com/linkjb/images/20181212232808.jpg5646', '1', null, '12', '170', '2', '1', '60', '2', '2', '2', '2', '2', '其味无穷', null, '2', '浙江省/杭州市/西湖区', '2018-12-12 23:29:19', '2018-12-21 10:25:50');
INSERT INTO `users` VALUES ('327', '李丽珍', '37', 'as@qq.com', 'qwe123', '15233001154', '2', '1', '1981-02-03', 'http://www.linkjb.com/linkjb/images/201812141609018668.jpg', '3', null, '3', '159', '2', '1', '48', '2', '2', '2', '2', '2', '靓仔你好', null, '0', '浙江省/杭州市/江干区', '2018-12-14 16:09:07', '2018-12-21 09:39:24');
INSERT INTO `users` VALUES ('328', '好二', '43', 'her@126.com', 'qwe123', '12866544111', '1', '2', '1975-10-23', 'http://www.linkjb.com/linkjb/images/20181214233624161.jpg', '1', null, '4', '181', '2', '2', '69', '3', '2', '2', '2', '2', '帅不帅，天知道', null, '0', '浙江省/杭州市/滨江区', '2018-12-14 23:37:13', '2018-12-21 09:37:55');
INSERT INTO `users` VALUES ('329', '沈超琦的爸爸', '23', '724212404@qq.com', 'lujiaqi', '17826805845', '1', '2', '1995-12-20', 'http://www.linkjb.com/linkjb/images/201812150007456043.jpg', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, '0', null, '2018-12-15 00:07:50', null);
INSERT INTO `users` VALUES ('330', '沈超琦', '22', '654735670@qq.com', '123456', '13588035530', '1', '2', '1996-03-11', 'http://www.linkjb.com/linkjb/images/201812150010074160.jpg', '1', null, '6', '178', '2', '5', '56', '3', '2', '2', '2', '2', '大叔啊', null, '0', '浙江省/杭州市/余杭区', '2018-12-15 00:10:10', '2018-12-21 10:00:56');
INSERT INTO `users` VALUES ('331', '123', '20', '1660841808@qq.com', '123123', '17856942517', '2', '2', '1998-12-26', '', '4', null, null, null, null, null, null, null, null, null, null, null, null, null, '0', null, '2018-12-15 14:19:12', null);
INSERT INTO `users` VALUES ('332', '1234', '24', '429380444@qq.com', '1234', '13102108807', '1', '2', '1994-11-09', '', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, '0', null, '2018-12-16 13:24:09', null);
INSERT INTO `users` VALUES ('333', '测试221', '39', '1207@126.com', 'qwe123', '13599665541', '1', '2', '1979-10-24', 'http://www.linkjb.com/linkjb/images/201812171347236498.jpg', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, '0', null, '2018-12-17 13:47:49', null);
INSERT INTO `users` VALUES ('334', '测试112', '37', '120@qq.com', 'qwe123', '13856441125', '1', '2', '1981-07-16', 'http://www.linkjb.com/linkjb/images/201812191100274129.jpg', '1', null, '0', '160', '2', '0', '50', '0', '2', '2', '2', '2', '', null, '0', '', '2018-12-19 11:00:29', '2018-12-20 17:12:29');
INSERT INTO `users` VALUES ('335', 'cjl1234', '18', '13916632356@163.com', 'cjl1234', '13916632356', '2', '1', '2000-01-03', 'http://www.linkjb.com/linkjb/images/201812191122225528.jpg', '3', null, '5', '160', '2', '5', '48', '2', '2', '2', '2', '2', '我是个小妹嗯', null, '1', '上海市/市辖区/长宁区', '2018-12-19 11:25:06', '2018-12-21 09:33:04');
INSERT INTO `users` VALUES ('336', '111', '21', '11', '111', '11111111111', '1', '1', '1997-12-08', 'http://www.linkjb.com/linkjb/images/201812191109271260.jpg', '2', null, '3', '168', '2', '5', '55', '1', '2', '2', '2', '2', '我是个靓仔', null, '0', '上海市/市辖区/普陀区', '2018-12-19 12:29:21', '2018-12-21 09:33:58');
INSERT INTO `users` VALUES ('337', '测试111', '32', '12078@qq.copm', 'qwe123', '13566221104', '2', '1', '1986-11-20', 'http://www.linkjb.com/linkjb/images/2018122016360474.jpg', '3', null, '12', '173', '2', '5', '56', '1', '2', '2', '2', '2', 'JS\\Java\\Html\\CSS\\', null, '0', '浙江省/杭州市/滨江区', '2018-12-20 16:37:12', '2018-12-21 10:14:30');
INSERT INTO `users` VALUES ('338', '测试123', '24', 'chenjg@126.com', 'qwe123', '13856178821', '2', '1', '1994-06-07', 'http://www.linkjb.com/linkjb/images/201812201649585770.jpg', '3', null, '0', '160', '2', '0', '50', '0', '2', '2', '2', '2', '古风美少女', null, '0', '上海市/市辖区/黄浦区', '2018-12-20 16:50:06', '2018-12-21 17:06:55');
INSERT INTO `users` VALUES ('339', '刘诗诗', '30', 'liuss@126.com', 'qwe123', '13588746654', '2', '2', '1988-06-23', 'http://www.linkjb.com/linkjb/images/201812211008081162.jpg', '4', null, '4', '165', '1', '5', '50', '4', '2', '2', '2', '2', '大美女', null, '0', '浙江省/杭州市/萧山区', '2018-12-21 10:08:52', '2018-12-21 10:09:38');
INSERT INTO `users` VALUES ('340', '陈建国', '24', '1207688613@qq.com', 'qwe123', '17682318817', '1', '2', '1994-04-06', 'http://www.linkjb.com/linkjb/images/201812211018052949.JPG', '1', null, '12', '174', '2', '5', '75', '1', '2', '2', '2', '2', 'Js\\java\\Css\\Html\\Vue\\ES6', null, '0', '浙江省/杭州市/滨江区', '2018-12-21 10:18:08', '2018-12-21 10:23:42');
INSERT INTO `users` VALUES ('341', '管理员', '37', 'system@163.com', 'system123', '18855440080', '1', '2', '1981-07-17', 'http://www.linkjb.com/linkjb/images/201812261711094961.jpg', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, '0', null, '2018-12-26 17:11:12', null);
