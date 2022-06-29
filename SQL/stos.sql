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

 Date: 29/06/2022 10:14:33
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
  PRIMARY KEY (`CGnumber`) USING BTREE,
  INDEX `CGname`(`CGname`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '教材采购人员二维结构描述教材采购人员表的属性，方便进行数据库设计。' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cgmessagerr
-- ----------------------------

-- ----------------------------
-- Table structure for dglist
-- ----------------------------
DROP TABLE IF EXISTS `dglist`;
CREATE TABLE `dglist`  (
  `DGnumber` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订购单号',
  `DGZname` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订购者姓名',
  `DGZnumber` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订购者编号',
  `Tel` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `ISBN` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '书籍名称',
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
  CONSTRAINT `Author_dg` FOREIGN KEY (`Author`) REFERENCES `textmessage` (`author`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `DSZname_user` FOREIGN KEY (`DGZname`) REFERENCES `user` (`name`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `DSZnumber_user` FOREIGN KEY (`DGZnumber`) REFERENCES `user` (`number`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ISBN_dg` FOREIGN KEY (`ISBN`) REFERENCES `textmessage` (`ISBN`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `price_dg` FOREIGN KEY (`Price`) REFERENCES `textmessage` (`price`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `publish_dg` FOREIGN KEY (`Publish`) REFERENCES `textmessage` (`publish`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `Tel_user` FOREIGN KEY (`Tel`) REFERENCES `user` (`tel`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订购单二维结构描述了订购单表格的属性，方便进行数据库设计。' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dglist
-- ----------------------------

-- ----------------------------
-- Table structure for fxmessager
-- ----------------------------
DROP TABLE IF EXISTS `fxmessager`;
CREATE TABLE `fxmessager`  (
  `fxnumber` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '职工号',
  `fxname` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '职工名字',
  `fxtel` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系方式',
  PRIMARY KEY (`fxnumber`) USING BTREE,
  INDEX `fxname`(`fxname`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '教材发行人员二维结构描述教材发行人员表格的属性，方便进行数据库设计。' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of fxmessager
-- ----------------------------

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
  CONSTRAINT `ISBN_intable` FOREIGN KEY (`ISBN`) REFERENCES `textmessage` (`ISBN`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '库存表二维结构描述库存表的属性，方便进行数据库设计。' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of intable
-- ----------------------------

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

-- ----------------------------
-- Table structure for login
-- ----------------------------
DROP TABLE IF EXISTS `login`;
CREATE TABLE `login`  (
  `Username` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `Password` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `Permission` int(0) NOT NULL COMMENT '权限1：一般用户（学生、教师）权限2：采购人员；权限3：教材发行人员；',
  PRIMARY KEY (`Username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '前台用户登录数据' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of login
-- ----------------------------

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
  CONSTRAINT `LSname_ls` FOREIGN KEY (`LSname`) REFERENCES `fxmessager` (`fxname`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '领书单二维结构描述了领书单表格的属性，方便进行数据库设计。' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of lslist
-- ----------------------------

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

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `number` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '编号',
  `name` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `class` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属院系',
  `tel` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系方式',
  PRIMARY KEY (`number`) USING BTREE,
  INDEX `name`(`name`) USING BTREE,
  INDEX `tel`(`tel`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '学生、老师信息二维结构描述了学生信息表格的属性，方便进行数据库设计。' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
