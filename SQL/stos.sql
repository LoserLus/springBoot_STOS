/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 80011
 Source Host           : localhost:3306
 Source Schema         : stos

 Target Server Type    : MySQL
 Target Server Version : 80011
 File Encoding         : 65001

 Date: 04/07/2022 11:19:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cg_messagerr
-- ----------------------------
DROP TABLE IF EXISTS `cg_messagerr`;
CREATE TABLE `cg_messagerr`  (
  `cg_username` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账号',
  `cg_password` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `cg_name` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '职工名字',
  `cg_tel` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系方式',
  PRIMARY KEY (`cg_username`) USING BTREE,
  INDEX `CGname`(`cg_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '教材采购人员二维结构描述教材采购人员表的属性，方便进行数据库设计。' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cg_messagerr
-- ----------------------------
INSERT INTO `cg_messagerr` VALUES ('CG001', '123456', '采购人', '17185456350');

-- ----------------------------
-- Table structure for dg_list
-- ----------------------------
DROP TABLE IF EXISTS `dg_list`;
CREATE TABLE `dg_list`  (
  `dg_id` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订购单号',
  `dgz_username` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订购者账号',
  `isbn` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '书号',
  `dg_date` datetime NULL DEFAULT NULL COMMENT '日期',
  `dg_total` int(11) NULL DEFAULT NULL COMMENT '购书总数',
  `dg_amount` int(11) NULL DEFAULT NULL COMMENT '总额',
  PRIMARY KEY (`dg_id`) USING BTREE,
  INDEX `DSZnumber_user`(`dgz_username`) USING BTREE,
  INDEX `ISBN_dg`(`isbn`) USING BTREE,
  INDEX `DGnumber`(`dg_id`, `dgz_username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订购单二维结构描述了订购单表格的属性，方便进行数据库设计。' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of dg_list
-- ----------------------------
INSERT INTO `dg_list` VALUES ('DG002', 'DGZ002', '9787201077642', '2022-06-02 15:46:53', 560, 22400);

-- ----------------------------
-- Table structure for dgz_user
-- ----------------------------
DROP TABLE IF EXISTS `dgz_user`;
CREATE TABLE `dgz_user`  (
  `dgz_username` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账号',
  `dgz_password` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `dgz_name` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `dgz_dept` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属院系',
  `dgz_tel` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系方式',
  PRIMARY KEY (`dgz_username`) USING BTREE,
  INDEX `name`(`dgz_name`) USING BTREE,
  INDEX `tel`(`dgz_tel`) USING BTREE,
  INDEX `dgz_user`(`dgz_username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '普通用户（学生、老师）信息二维结构描述了学生信息表格的属性，方便进行数据库设计。' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of dgz_user
-- ----------------------------
INSERT INTO `dgz_user` VALUES ('DGZ001', '123456', '张三', '计科', '16359632563');
INSERT INTO `dgz_user` VALUES ('DGZ002', '123456', '李四', '马原', '13356322356');

-- ----------------------------
-- Table structure for fx_messager
-- ----------------------------
DROP TABLE IF EXISTS `fx_messager`;
CREATE TABLE `fx_messager`  (
  `fx_username` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账号',
  `fx_password` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `fx_name` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '职工名字',
  `fx_tel` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系方式',
  PRIMARY KEY (`fx_username`) USING BTREE,
  INDEX `fxname`(`fx_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '教材发行人员二维结构描述教材发行人员表格的属性，方便进行数据库设计。' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of fx_messager
-- ----------------------------
INSERT INTO `fx_messager` VALUES ('FX001', '123456', '发行人', '14769365630');

-- ----------------------------
-- Table structure for in_table
-- ----------------------------
DROP TABLE IF EXISTS `in_table`;
CREATE TABLE `in_table`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `isbn` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '书号',
  `stock` int(11) NOT NULL COMMENT '库存数量',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `ISBN_intable`(`isbn`) USING BTREE,
  INDEX `number`(`stock`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '库存表二维结构描述库存表的属性，方便进行数据库设计。' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of in_table
-- ----------------------------
INSERT INTO `in_table` VALUES (1, '9787572606649', 200);
INSERT INTO `in_table` VALUES (2, '9787553636467', 300);
INSERT INTO `in_table` VALUES (3, '9787551123426', 400);
INSERT INTO `in_table` VALUES (4, '9787544766500', 500);
INSERT INTO `in_table` VALUES (5, '9787544291170', 600);
INSERT INTO `in_table` VALUES (6, '9787540487645', 500);
INSERT INTO `in_table` VALUES (7, '9787530221532', 100);
INSERT INTO `in_table` VALUES (8, '9787521737035', 150);
INSERT INTO `in_table` VALUES (9, '9787521603774', 250);
INSERT INTO `in_table` VALUES (10, '9787506379786', 350);
INSERT INTO `in_table` VALUES (11, '9787505745766', 450);
INSERT INTO `in_table` VALUES (12, '9787500601593', 500);
INSERT INTO `in_table` VALUES (13, '9787201077642', 550);
INSERT INTO `in_table` VALUES (14, '9787100186438', 559);

-- ----------------------------
-- Table structure for js_list
-- ----------------------------
DROP TABLE IF EXISTS `js_list`;
CREATE TABLE `js_list`  (
  `js_id` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '进书编号',
  `isbn` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '书号',
  `cg_username` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '采购人账号',
  `cg_date` datetime NULL DEFAULT NULL COMMENT '采购日期',
  `cg_total` int(11) NULL DEFAULT NULL COMMENT '采购总数',
  `cg_amount` int(11) NOT NULL COMMENT '采购总额',
  `cg_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '采购标记',
  PRIMARY KEY (`js_id`) USING BTREE,
  INDEX `ISBN_jslist`(`isbn`) USING BTREE,
  INDEX `cg_username_js`(`cg_username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '进书单二维结构描述了进书单表格的属性，方便进行数据库设计。' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of js_list
-- ----------------------------
INSERT INTO `js_list` VALUES ('JS001', '9787521737035', NULL, '2022-06-02 15:22:49', NULL, 5000, 0);

-- ----------------------------
-- Table structure for ls_list
-- ----------------------------
DROP TABLE IF EXISTS `ls_list`;
CREATE TABLE `ls_list`  (
  `ls_id` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '领书单号',
  `dg_id` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订购单号',
  `ls_date` datetime NULL DEFAULT NULL COMMENT '领书日期',
  `ls_username` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '经办人',
  PRIMARY KEY (`ls_id`) USING BTREE,
  INDEX `DGnumber_ls`(`dg_id`) USING BTREE,
  INDEX `ls_username`(`ls_username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '领书单二维结构描述了领书单表格的属性，方便进行数据库设计。' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ls_list
-- ----------------------------
INSERT INTO `ls_list` VALUES ('LS001', 'DG001', '2022-06-03 15:44:38', 'FX001');
INSERT INTO `ls_list` VALUES ('LS002', 'DG002', '2022-06-04 15:46:53', 'FX001');
INSERT INTO `ls_list` VALUES ('LS1656904559757', 'DG001', '2022-07-04 11:16:01', 'FX001');

-- ----------------------------
-- Table structure for qs_list
-- ----------------------------
DROP TABLE IF EXISTS `qs_list`;
CREATE TABLE `qs_list`  (
  `qs_id` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '缺书单号',
  `isbn` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '书号',
  `qs_total` int(11) NULL DEFAULT NULL COMMENT '数量',
  `qs_username` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '经办人',
  PRIMARY KEY (`qs_id`) USING BTREE,
  INDEX `ISBN_qslist`(`isbn`) USING BTREE,
  INDEX `qs_username`(`qs_username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '缺书单二维结构描述了缺书单表格的属性，方便进行数据库设计。' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qs_list
-- ----------------------------
INSERT INTO `qs_list` VALUES ('QS001', '9787521737035', 50, NULL);

-- ----------------------------
-- Table structure for text_message
-- ----------------------------
DROP TABLE IF EXISTS `text_message`;
CREATE TABLE `text_message`  (
  `isbn` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '书号',
  `book_name` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '书名',
  `author` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作者',
  `publish` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出版社',
  `price` int(11) NOT NULL COMMENT '单价',
  PRIMARY KEY (`isbn`) USING BTREE,
  INDEX `ISBN`(`isbn`) USING BTREE,
  INDEX `author`(`author`) USING BTREE,
  INDEX `publish`(`publish`) USING BTREE,
  INDEX `price`(`price`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '教材信息二维结构描述了教材信息表格的属性，方便进行数据库设计。' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of text_message
-- ----------------------------
INSERT INTO `text_message` VALUES ('9787100186438', '理想国', '柏拉图', '商务印书馆', 34);
INSERT INTO `text_message` VALUES ('9787201077642', '小王子', '安托万·德·圣埃克苏佩里', '果麦文化', 20);
INSERT INTO `text_message` VALUES ('9787500601593', '红岩', '罗广斌', '中国青年出版社', 23);
INSERT INTO `text_message` VALUES ('9787505745766', '边城', '沈从文', '中国友谊出版公司', 16);
INSERT INTO `text_message` VALUES ('9787506379786', '简爱', '夏洛蒂勃朗特', '作家出版社', 14);
INSERT INTO `text_message` VALUES ('9787521603774', '圆圈正义', '罗翔', '中国法制出版社', 23);
INSERT INTO `text_message` VALUES ('9787521737035', '中国美术五千年', '杨琪', '中信出版社', 100);
INSERT INTO `text_message` VALUES ('9787530221532', '活着', '余华', '北京十月文艺出版社', 31);
INSERT INTO `text_message` VALUES ('9787540487645', '云边有个小卖部', '张嘉佳', '湖南文艺出版社', 29);
INSERT INTO `text_message` VALUES ('9787544291170', '百年孤独', '加西亚·马尔克斯', '南海出版公司', 39);
INSERT INTO `text_message` VALUES ('9787544766500', '杀死一只知更鸟', '哈珀·李', '译林出版社', 48);
INSERT INTO `text_message` VALUES ('9787551123426', '儒林外史', '吴敬梓', '花山文艺出版社', 12);
INSERT INTO `text_message` VALUES ('9787553636467', '古文观止', '吴楚材', '浙江教育出版社', 25);
INSERT INTO `text_message` VALUES ('9787572606649', '花与药', '何袜皮', '湖南文艺出版社', 27);

SET FOREIGN_KEY_CHECKS = 1;
