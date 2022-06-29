/*
 Navicat Premium Data Transfer

 Source Server         : test
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : localhost:3306
 Source Schema         : stos

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 29/06/2022 20:59:20
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cgmessagerr
-- ----------------------------
DROP TABLE IF EXISTS `cgmessagerr`;
CREATE TABLE `cgmessagerr`  (
  `CGnumber` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '职工号',
  `CGname` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '职工名字',
  `CGtel` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系方式',
  `CGUser` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账号',
  `CGPassword` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  PRIMARY KEY (`CGnumber`) USING BTREE,
  INDEX `CGname`(`CGname`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '教材采购人员二维结构描述教材采购人员表的属性，方便进行数据库设计。' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cgmessagerr
-- ----------------------------
INSERT INTO `cgmessagerr` VALUES ('CG001', '采购人', '17185456350', 'cgr123', '123456');

-- ----------------------------
-- Table structure for dglist
-- ----------------------------
DROP TABLE IF EXISTS `dglist`;
CREATE TABLE `dglist`  (
  `DGnumber` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订购单号',
  `DGZname` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订购者姓名',
  `DGZnumber` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订购者编号',
  `Tel` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `ISBN` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '书号',
  `Author` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作者',
  `Publish` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出版社',
  `Price` int(0) NULL DEFAULT NULL COMMENT '价格',
  `Number` int(0) NULL DEFAULT NULL COMMENT '数量',
  `DGDate` datetime(0) NULL DEFAULT NULL COMMENT '日期',
  `DGALLnumber` int(0) NULL DEFAULT NULL COMMENT '购书总数',
  `DGALLprice` int(0) NULL DEFAULT NULL COMMENT '总额',
  PRIMARY KEY (`DGnumber`) USING BTREE,
  INDEX `DSZname_user`(`DGZname`) USING BTREE,
  INDEX `DSZnumber_user`(`DGZnumber`) USING BTREE,
  INDEX `Tel_user`(`Tel`) USING BTREE,
  INDEX `ISBN_dg`(`ISBN`) USING BTREE,
  INDEX `Author_dg`(`Author`) USING BTREE,
  INDEX `publish_dg`(`Publish`) USING BTREE,
  INDEX `price_dg`(`Price`) USING BTREE,
  INDEX `DGnumber`(`DGnumber`, `DGZnumber`) USING BTREE,
  INDEX `Number_dg`(`Number`) USING BTREE,
  CONSTRAINT `Author_dg` FOREIGN KEY (`Author`) REFERENCES `textmessage` (`author`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `DSZname_user` FOREIGN KEY (`DGZname`) REFERENCES `dgzuser` (`DGZname`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `DSZnumber_user` FOREIGN KEY (`DGZnumber`) REFERENCES `dgzuser` (`DGZnumber`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ISBN_dg` FOREIGN KEY (`ISBN`) REFERENCES `textmessage` (`ISBN`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `Number_dg` FOREIGN KEY (`Number`) REFERENCES `intable` (`number`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `price_dg` FOREIGN KEY (`Price`) REFERENCES `textmessage` (`price`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `publish_dg` FOREIGN KEY (`Publish`) REFERENCES `textmessage` (`publish`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `Tel_user` FOREIGN KEY (`Tel`) REFERENCES `dgzuser` (`DGZtel`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订购单二维结构描述了订购单表格的属性，方便进行数据库设计。' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dglist
-- ----------------------------
INSERT INTO `dglist` VALUES ('DG001', '张三', 'DGZ001', '16359632563', '9787100186438', '柏拉图', '商务印书馆', 34, 560, '2022-06-01 15:43:57', 1, 34);
INSERT INTO `dglist` VALUES ('DG002', '李四', 'DGZ002', '13356322356', '9787201077642', '安托万·德·圣埃克苏佩里', '果麦文化', 20, 550, '2022-06-02 15:46:53', 2, 40);

-- ----------------------------
-- Table structure for dgzuser
-- ----------------------------
DROP TABLE IF EXISTS `dgzuser`;
CREATE TABLE `dgzuser`  (
  `DGZnumber` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '编号',
  `DGZname` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `DGZclass` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属院系',
  `DGZtel` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系方式',
  `DGZUser` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账号',
  `DGZPassword` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  PRIMARY KEY (`DGZnumber`) USING BTREE,
  INDEX `name`(`DGZname`) USING BTREE,
  INDEX `tel`(`DGZtel`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '普通用户（学生、老师）信息二维结构描述了学生信息表格的属性，方便进行数据库设计。' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dgzuser
-- ----------------------------
INSERT INTO `dgzuser` VALUES ('DGZ001', '张三', '计科', '16359632563', 'za123', '123456');
INSERT INTO `dgzuser` VALUES ('DGZ002', '李四', '马原', '13356322356', 'li123', '123456');

-- ----------------------------
-- Table structure for fxmessager
-- ----------------------------
DROP TABLE IF EXISTS `fxmessager`;
CREATE TABLE `fxmessager`  (
  `FXnumber` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '职工号',
  `FXname` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '职工名字',
  `FXtel` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系方式',
  `FXUser` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账号',
  `FXPassword` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  PRIMARY KEY (`FXnumber`) USING BTREE,
  INDEX `fxname`(`FXname`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '教材发行人员二维结构描述教材发行人员表格的属性，方便进行数据库设计。' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of fxmessager
-- ----------------------------
INSERT INTO `fxmessager` VALUES ('FX001', '发行人', '14769365630', 'fxr123', '123456');

-- ----------------------------
-- Table structure for intable
-- ----------------------------
DROP TABLE IF EXISTS `intable`;
CREATE TABLE `intable`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '记录号',
  `ISBN` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '书号',
  `number` int(0) NOT NULL COMMENT '数量',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `ISBN_intable`(`ISBN`) USING BTREE,
  INDEX `number`(`number`) USING BTREE,
  CONSTRAINT `ISBN_intable` FOREIGN KEY (`ISBN`) REFERENCES `textmessage` (`ISBN`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '库存表二维结构描述库存表的属性，方便进行数据库设计。' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of intable
-- ----------------------------
INSERT INTO `intable` VALUES (1, '9787572606649', 200);
INSERT INTO `intable` VALUES (2, '9787553636467', 300);
INSERT INTO `intable` VALUES (3, '9787551123426', 400);
INSERT INTO `intable` VALUES (4, '9787544766500', 500);
INSERT INTO `intable` VALUES (5, '9787544291170', 600);
INSERT INTO `intable` VALUES (6, '9787540487645', 500);
INSERT INTO `intable` VALUES (7, '9787530221532', 100);
INSERT INTO `intable` VALUES (8, '9787521737035', 150);
INSERT INTO `intable` VALUES (9, '9787521603774', 250);
INSERT INTO `intable` VALUES (10, '9787506379786', 350);
INSERT INTO `intable` VALUES (11, '9787505745766', 450);
INSERT INTO `intable` VALUES (12, '9787500601593', 500);
INSERT INTO `intable` VALUES (13, '9787201077642', 550);
INSERT INTO `intable` VALUES (14, '9787100186438', 560);

-- ----------------------------
-- Table structure for jslist
-- ----------------------------
DROP TABLE IF EXISTS `jslist`;
CREATE TABLE `jslist`  (
  `JSnumber` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '进书编号',
  `ISBN` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '书号',
  `CGname` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '采购人姓名',
  `CGdate` datetime(0) NULL DEFAULT NULL COMMENT '采购日期',
  `CGnumber` int(0) NULL DEFAULT NULL COMMENT '采购数量',
  `CGprice` int(0) NULL DEFAULT NULL COMMENT '采购总额',
  PRIMARY KEY (`JSnumber`) USING BTREE,
  INDEX `ISBN_jslist`(`ISBN`) USING BTREE,
  INDEX `CGname_cg`(`CGname`) USING BTREE,
  CONSTRAINT `CGname_cg` FOREIGN KEY (`CGname`) REFERENCES `cgmessagerr` (`CGname`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ISBN_jslist` FOREIGN KEY (`ISBN`) REFERENCES `textmessage` (`ISBN`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '进书单二维结构描述了进书单表格的属性，方便进行数据库设计。' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of jslist
-- ----------------------------
INSERT INTO `jslist` VALUES ('JS001', '9787521737035', '采购人', '2022-06-02 15:22:49', 50, 5000);

-- ----------------------------
-- Table structure for lslist
-- ----------------------------
DROP TABLE IF EXISTS `lslist`;
CREATE TABLE `lslist`  (
  `LSnumber` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '领书单号',
  `DGZnumber` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订购者编号',
  `LSdate` datetime(0) NULL DEFAULT NULL COMMENT '领书日期',
  `DGnumber` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '购书单号',
  `DGdate` datetime(0) NULL DEFAULT NULL COMMENT '购书日期',
  `LSname` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '经办人_发行人员',
  PRIMARY KEY (`LSnumber`) USING BTREE,
  INDEX `DGZnumber_ls`(`DGZnumber`) USING BTREE,
  INDEX `DGnumber_ls`(`DGnumber`) USING BTREE,
  INDEX `LSname_ls`(`LSname`) USING BTREE,
  CONSTRAINT `DGnumber_ls` FOREIGN KEY (`DGnumber`) REFERENCES `dglist` (`DGnumber`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `DGZnumber_ls` FOREIGN KEY (`DGZnumber`) REFERENCES `dglist` (`DGZnumber`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `LSname_ls` FOREIGN KEY (`LSname`) REFERENCES `fxmessager` (`FXname`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '领书单二维结构描述了领书单表格的属性，方便进行数据库设计。' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of lslist
-- ----------------------------
INSERT INTO `lslist` VALUES ('LS001', 'DGZ001', '2022-06-03 15:44:38', 'DG001', '2022-06-01 15:43:57', '发行人');
INSERT INTO `lslist` VALUES ('LS002', 'DGZ002', '2022-06-04 15:46:53', 'DG002', '2022-06-02 15:46:53', '发行人');

-- ----------------------------
-- Table structure for qslist
-- ----------------------------
DROP TABLE IF EXISTS `qslist`;
CREATE TABLE `qslist`  (
  `QSnumber` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '缺书单号',
  `ISBN` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '书号',
  `QSALLnumber` int(0) NULL DEFAULT NULL COMMENT '数量',
  PRIMARY KEY (`QSnumber`) USING BTREE,
  INDEX `ISBN_qslist`(`ISBN`) USING BTREE,
  CONSTRAINT `ISBN_qslist` FOREIGN KEY (`ISBN`) REFERENCES `textmessage` (`ISBN`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '缺书单二维结构描述了缺书单表格的属性，方便进行数据库设计。' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qslist
-- ----------------------------
INSERT INTO `qslist` VALUES ('QS001', '9787521737035', 50);

-- ----------------------------
-- Table structure for textmessage
-- ----------------------------
DROP TABLE IF EXISTS `textmessage`;
CREATE TABLE `textmessage`  (
  `ISBN` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '书号',
  `bookname` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '书名',
  `author` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作者',
  `publish` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出版社',
  `price` int(0) NOT NULL COMMENT '单价',
  PRIMARY KEY (`ISBN`) USING BTREE,
  INDEX `ISBN`(`ISBN`) USING BTREE,
  INDEX `author`(`author`) USING BTREE,
  INDEX `publish`(`publish`) USING BTREE,
  INDEX `price`(`price`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '教材信息二维结构描述了教材信息表格的属性，方便进行数据库设计。' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of textmessage
-- ----------------------------
INSERT INTO `textmessage` VALUES ('9787100186438', '理想国', '柏拉图', '商务印书馆', 34);
INSERT INTO `textmessage` VALUES ('9787201077642', '小王子', '安托万·德·圣埃克苏佩里', '果麦文化', 20);
INSERT INTO `textmessage` VALUES ('9787500601593', '红岩', '罗广斌', '中国青年出版社', 23);
INSERT INTO `textmessage` VALUES ('9787505745766', '边城', '沈从文', '中国友谊出版公司', 16);
INSERT INTO `textmessage` VALUES ('9787506379786', '简爱', '夏洛蒂勃朗特', '作家出版社', 14);
INSERT INTO `textmessage` VALUES ('9787521603774', '圆圈正义', '罗翔', '中国法制出版社', 23);
INSERT INTO `textmessage` VALUES ('9787521737035', '中国美术五千年', '杨琪', '中信出版社', 128);
INSERT INTO `textmessage` VALUES ('9787530221532', '活着', '余华', '北京十月文艺出版社', 31);
INSERT INTO `textmessage` VALUES ('9787540487645', '云边有个小卖部', '张嘉佳', '湖南文艺出版社', 29);
INSERT INTO `textmessage` VALUES ('9787544291170', '百年孤独', '加西亚·马尔克斯', '南海出版公司', 39);
INSERT INTO `textmessage` VALUES ('9787544766500', '杀死一只知更鸟', '哈珀·李', '译林出版社', 48);
INSERT INTO `textmessage` VALUES ('9787551123426', '儒林外史', '吴敬梓', '花山文艺出版社', 12);
INSERT INTO `textmessage` VALUES ('9787553636467', '古文观止', '吴楚材', '浙江教育出版社', 25);
INSERT INTO `textmessage` VALUES ('9787572606649', '花与药', '何袜皮', '湖南文艺出版社', 27);

SET FOREIGN_KEY_CHECKS = 1;
