/*
Navicat MySQL Data Transfer

Source Server         : mysql5.0
Source Server Version : 50719
Source Host           : 125.0.8.191:3304
Source Database       : drive_cloud

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2021-06-22 15:52:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for mon_login_log
-- ----------------------------
DROP TABLE IF EXISTS `mon_login_log`;
CREATE TABLE `mon_login_log` (
  `login_log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '访问ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `user_name` varchar(50) DEFAULT '' COMMENT '用户账号',
  `status` char(1) DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
  `ipaddr` varchar(50) DEFAULT '' COMMENT '登录IP地址',
  `login_time` datetime DEFAULT NULL COMMENT '登录时间',
  `browser_name` varchar(50) DEFAULT '' COMMENT '浏览器类型',
  `os_name` varchar(50) DEFAULT '' COMMENT '操作系统',
  `message` varchar(255) DEFAULT '' COMMENT '记录信息',
  PRIMARY KEY (`login_log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COMMENT='系统访问记录';

-- ----------------------------
-- Records of mon_login_log
-- ----------------------------
INSERT INTO `mon_login_log` VALUES ('1', '1', 'admin', '0', '192.168.99.140,127.0.0.1', '2020-07-15 16:50:59', '', '', '登录成功');
INSERT INTO `mon_login_log` VALUES ('2', '1', 'admin', '0', '192.168.99.140,127.0.0.1', '2020-07-15 17:12:17', '', '', '登录成功');
INSERT INTO `mon_login_log` VALUES ('3', '1', 'admin', '0', '192.168.99.140,127.0.0.1', '2020-07-15 17:12:59', '', '', '登录成功');
INSERT INTO `mon_login_log` VALUES ('4', '1', 'admin', '0', '192.168.99.140,127.0.0.1', '2020-07-15 12:05:03', '', '', '登录成功');
INSERT INTO `mon_login_log` VALUES ('5', '1', 'admin', '0', '192.168.99.140,127.0.0.1', '2020-07-15 16:33:51', '', '', '登录成功');
INSERT INTO `mon_login_log` VALUES ('6', '1', 'admin', '0', '192.168.99.140,127.0.0.1', '2020-07-15 18:41:12', '', '', '登录成功');

-- ----------------------------
-- Table structure for mon_oper_log
-- ----------------------------
DROP TABLE IF EXISTS `mon_oper_log`;
CREATE TABLE `mon_oper_log` (
  `oper_log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `message` varchar(255) DEFAULT '' COMMENT '记录信息',
  `business_type` int(2) DEFAULT '1' COMMENT '业务类型（1-查询 2-新增 3-修改 4-删除 5-导入 6-导出）',
  `request_method` varchar(10) DEFAULT '' COMMENT '请求方式',
  `request_param` varchar(2000) DEFAULT '' COMMENT '请求参数',
  `request_date` datetime DEFAULT NULL COMMENT '访问时间',
  `request_url` varchar(500) DEFAULT NULL COMMENT '请求 URL',
  `execute_time` bigint(20) DEFAULT NULL COMMENT '执行时间',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `user_name` varchar(50) DEFAULT '' COMMENT '用户账号',
  `oper_ip` varchar(50) DEFAULT '' COMMENT '主机地址',
  `status` int(1) DEFAULT '0' COMMENT '操作状态（0正常 1异常）',
  PRIMARY KEY (`oper_log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3005 DEFAULT CHARSET=utf8mb4 COMMENT='操作日志记录';

-- ----------------------------
-- Records of mon_oper_log
-- ----------------------------
INSERT INTO `mon_oper_log` VALUES ('1', '新增终端配置表', '2', 'POST', '{}', '2020-07-15 17:46:29', '/client', '164', '1', 'admin', '192.168.99.140,127.0.0.1', '0');
INSERT INTO `mon_oper_log` VALUES ('2', '修改头像', '3', 'POST', '{}', '2020-07-15 11:07:51', '/user/profile/avatar', '410', '1', 'admin', '192.168.99.140,127.0.0.1', '0');
INSERT INTO `mon_oper_log` VALUES ('3001', '修改头像', '3', 'POST', '{}', '2020-07-15 12:09:40', '/user/profile/avatar', '674', '1', 'admin', '192.168.99.140,127.0.0.1', '0');
INSERT INTO `mon_oper_log` VALUES ('3002', '个人信息', '3', 'PUT', '{}', '2020-07-15 12:10:31', '/user/profile', '41', '1', 'admin', '192.168.99.140,127.0.0.1', '0');
INSERT INTO `mon_oper_log` VALUES ('3003', '修改头像', '3', 'POST', '{}', '2020-07-15 16:59:37', '/user/profile/avatar', '10122', '1', 'admin', '192.168.99.140,127.0.0.1', '0');
INSERT INTO `mon_oper_log` VALUES ('3004', '修改头像', '3', 'POST', '{}', '2020-07-15 17:03:08', '/user/profile/avatar', '416', '1', 'admin', '192.168.99.140,127.0.0.1', '0');

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(255) NOT NULL COMMENT '终端编号',
  `resource_ids` varchar(255) DEFAULT NULL COMMENT '资源ID标识',
  `client_secret` varchar(255) NOT NULL COMMENT '终端安全码',
  `scope` varchar(255) NOT NULL COMMENT '终端授权范围',
  `authorized_grant_types` varchar(255) NOT NULL COMMENT '终端授权类型',
  `web_server_redirect_uri` varchar(255) DEFAULT NULL COMMENT '服务器回调地址',
  `authorities` varchar(255) DEFAULT NULL COMMENT '访问资源所需权限',
  `access_token_validity` int(11) DEFAULT NULL COMMENT '设定终端的access_token的有效时间值（秒）',
  `refresh_token_validity` int(11) DEFAULT NULL COMMENT '设定终端的refresh_token的有效时间值（秒）',
  `additional_information` varchar(4096) DEFAULT NULL COMMENT '附加信息',
  `autoapprove` tinyint(4) DEFAULT NULL COMMENT '是否登录时跳过授权',
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='终端配置表';

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO `oauth_client_details` VALUES ('APP', '', '$2a$10$y2hKeELx.z3Sbz.kjQ4wmuiIsv5ZSbUQ1ov4BwFH6ccirP8Knp1uq', 'server', 'client_credentials,refresh_token', '', '', '3600', '7200', '', null);
INSERT INTO `oauth_client_details` VALUES ('fastcloud', '', '$2a$10$y2hKeELx.z3Sbz.kjQ4wmuiIsv5ZSbUQ1ov4BwFH6ccirP8Knp1uq', 'server', 'password,client_credentials,refresh_token', '', null, '7776000', '7776000', null, null);
INSERT INTO `oauth_client_details` VALUES ('web', '', '$2a$10$y2hKeELx.z3Sbz.kjQ4wmuiIsv5ZSbUQ1ov4BwFH6ccirP8Knp1uq', 'server', 'password,refresh_token', '', null, '7776000', '7776000', null, null);

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `dept_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `dept_name` varchar(30) DEFAULT '' COMMENT '部门名称',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父部门id',
  `parent_ids` varchar(500) DEFAULT NULL COMMENT '父部门ids',
  `leader` varchar(20) DEFAULT NULL COMMENT '负责人',
  `phone` varchar(11) DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `description` varchar(250) DEFAULT NULL COMMENT '描述',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `order_num` int(4) DEFAULT '0' COMMENT '排序',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人Id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新人Id',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `tenant_id` varchar(64) DEFAULT NULL COMMENT '租户ID',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=215 DEFAULT CHARSET=utf8mb4 COMMENT='部门';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('1', '学车小王子', '0', '0', '小郭', '15116101123', '1078823721@qq.com', null, null, '0', '0', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00', '8d26f5f6cae74532bf0baf37fc8ccd0f');
INSERT INTO `sys_dept` VALUES ('100', '云南昆明城市运营中心', '1', '0,1', '小郭', '15116101123', '1078823721@qq.com', null, null, '1', '0', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00', 'bbdc1bd499b241daa6fe99063e63a193');
INSERT INTO `sys_dept` VALUES ('101', '人事部', '100', '0,1,100,207', '小郭', '15116101123', '1078823721@qq.com', null, null, '1', '0', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00', 'bbdc1bd499b241daa6fe99063e63a193');
INSERT INTO `sys_dept` VALUES ('102', '研发部', '100', '0,1,100', '小郭', '15116101123', '1078823721@qq.com', null, null, '2', '0', 'admin', '2020-06-06 11:00:00', 'admin', '2020-06-06 11:00:00', 'bbdc1bd499b241daa6fe99063e63a193');
INSERT INTO `sys_dept` VALUES ('200', '云南玉溪城市运营中心', '1', '0,1', '小郭', '15116101123', '1078823721@qq.com', null, null, '0', '0', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00', 'b00a7c7b60be4c42ba37f2f66c10806a');
INSERT INTO `sys_dept` VALUES ('201', '人事部', '200', '0,1,200', '小郭', '15116101123', '1078823721@qq.com', null, null, '1', '0', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00', 'b00a7c7b60be4c42ba37f2f66c10806a');
INSERT INTO `sys_dept` VALUES ('202', '研发部', '200', '0,1,200', '小郭', '15116101123', '1078823721@qq.com', null, null, '2', '0', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00', 'b00a7c7b60be4c42ba37f2f66c10806a');
INSERT INTO `sys_dept` VALUES ('203', '运营中心', '100', '0,1,100,101', '小郭', '15116101123', '1078823721@qq.com', null, null, '1', '0', 'admin', '2021-05-06 09:32:18', 'admin', '2021-05-06 09:33:10', 'bbdc1bd499b241daa6fe99063e63a193');
INSERT INTO `sys_dept` VALUES ('204', '财务部', '100', '0,1,100', '小郭', '15116101123', '1078823721@qq.com', null, null, '3', '0', 'admin', '2021-01-21 21:16:37', 'admin', '2021-01-21 21:16:37', 'bbdc1bd499b241daa6fe99063e63a193');
INSERT INTO `sys_dept` VALUES ('205', '云南曲靖城市运营中心', '1', '0,1', '小郭', '15116101123', '1078823721@qq.com', null, null, '3', '0', 'admin', '2021-01-21 21:17:56', 'admin', '2021-01-21 21:17:56', '01b13fad909245abb042d5efc37b82ee');
INSERT INTO `sys_dept` VALUES ('206', '广西百色城市运营中心', '1', '0,1', '小郭', '15116101123', '1078823721@qq.com', null, null, '4', '0', 'admin', '2021-01-21 21:19:26', 'admin', '2021-01-21 21:19:26', 'fd9f744436a249fa919cacbbe2a58b9d');
INSERT INTO `sys_dept` VALUES ('207', '客服部', '100', '0,1,100', '小郭', '15116101123', '1078823721@qq.com', null, null, '6', '0', 'admin', '2021-01-21 21:21:25', 'admin', '2021-01-21 21:21:25', 'bbdc1bd499b241daa6fe99063e63a193');
INSERT INTO `sys_dept` VALUES ('208', '设计部', '100', '0,1,100', '小郭', '15116101123', '1078823721@qq.com', null, null, '7', '0', 'admin', '2021-01-21 21:21:55', 'admin', '2021-01-21 21:21:55', 'bbdc1bd499b241daa6fe99063e63a193');
INSERT INTO `sys_dept` VALUES ('209', '贵州贵阳城市运营中心', '1', '0,1', '小郭', '15116101123', '1078823721@qq.com', null, null, '4', '0', 'admin', '2021-04-29 17:32:04', 'admin', '2021-04-29 17:32:04', '1bc28b86a2224043b048611102c33367');
INSERT INTO `sys_dept` VALUES ('210', '云南腾冲城市运营中心', '1', '0,1', '小郭', '15116101123', '1078823721@qq.com', null, null, '5', '0', 'admin', '2021-04-29 17:32:34', 'admin', '2021-04-29 17:32:34', '365de80e14a54f9796fe2563bf4ea5ca');
INSERT INTO `sys_dept` VALUES ('211', '贵州惠水城市运营中心', '1', '0,1', '小郭', '15116101123', '1078823721@qq.com', null, null, '6', '0', 'admin', '2021-04-29 17:32:50', 'admin', '2021-04-29 17:32:50', '5a7c2a2e280c40bd8f0d845f24ed7994');
INSERT INTO `sys_dept` VALUES ('212', '贵阳市清镇市运营中心', '1', '0,1', '小郭', '15116101123', '1078823721@qq.com', null, null, '7', '0', 'admin', '2021-04-29 17:33:09', 'admin', '2021-04-29 17:33:09', '7dae4b26d1424ce88e33c2469e9b18ed');
INSERT INTO `sys_dept` VALUES ('213', '云南镇雄城市运营中心', '1', '0,1', '小郭', '15116101123', '1078823721@qq.com', null, null, '8', '0', 'admin', '2021-04-29 17:33:23', 'admin', '2021-04-29 17:33:23', 'bf3981dcfc0a4c1c9c74b4c6e4e6f5cd');
INSERT INTO `sys_dept` VALUES ('214', '湖南宁乡城市运营中心', '1', '0,1', '小郭', '15116101123', '1078823721@qq.com', null, null, '9', '0', 'admin', '2021-05-06 09:20:36', 'admin', '2021-05-06 09:20:36', '4f60d88ced9048eda9aa354d8b058ca4');

-- ----------------------------
-- Table structure for sys_dict_item
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_item`;
CREATE TABLE `sys_dict_item` (
  `dict_item_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  `dict_code` varchar(100) NOT NULL COMMENT '字典类型',
  `item_name` varchar(100) DEFAULT '' COMMENT '字典项名称',
  `item_value` varchar(100) DEFAULT '' COMMENT '字典项键值',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `parent_id` bigint(100) DEFAULT NULL COMMENT '父类',
  PRIMARY KEY (`dict_item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=111390 DEFAULT CHARSET=utf8mb4 COMMENT='字典数据';

-- ----------------------------
-- Records of sys_dict_item
-- ----------------------------
INSERT INTO `sys_dict_item` VALUES ('1', 'sys_user_sex', '男', '0', '0', null, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00', null);
INSERT INTO `sys_dict_item` VALUES ('2', 'sys_user_sex', '女', '1', '0', null, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00', null);
INSERT INTO `sys_dict_item` VALUES ('3', 'sys_user_sex', '未知', '2', '0', null, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00', null);
INSERT INTO `sys_dict_item` VALUES ('5', 'sys_show_hide', '显示', '0', '0', null, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00', null);
INSERT INTO `sys_dict_item` VALUES ('6', 'sys_show_hide', '隐藏', '1', '0', null, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00', null);
INSERT INTO `sys_dict_item` VALUES ('7', 'sys_normal_disable', '正常', '0', '0', null, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00', null);
INSERT INTO `sys_dict_item` VALUES ('8', 'sys_normal_disable', '停用', '1', '0', null, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00', null);
INSERT INTO `sys_dict_item` VALUES ('9', 'sys_common_status', '成功', '0', '0', null, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00', null);
INSERT INTO `sys_dict_item` VALUES ('10', 'sys_common_status', '失败', '1', '0', null, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00', null);
INSERT INTO `sys_dict_item` VALUES ('11', 'sys_oper_type', '查询', '1', '0', null, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00', null);
INSERT INTO `sys_dict_item` VALUES ('12', 'sys_oper_type', '新增', '2', '0', null, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00', null);
INSERT INTO `sys_dict_item` VALUES ('13', 'sys_oper_type', '修改', '3', '0', null, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00', null);
INSERT INTO `sys_dict_item` VALUES ('14', 'sys_oper_type', '删除', '4', '0', null, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00', null);
INSERT INTO `sys_dict_item` VALUES ('15', 'sys_oper_type', '导入', '5', '0', null, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00', null);
INSERT INTO `sys_dict_item` VALUES ('16', 'sys_oper_type', '导出', '6', '0', null, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00', null);
INSERT INTO `sys_dict_item` VALUES ('102', 'sys_user_initPassword', '默认密码', '123', '0', null, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00', null);
INSERT INTO `sys_dict_item` VALUES ('103', 'article_state', '已发布', '0', '0', null, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00', null);
INSERT INTO `sys_dict_item` VALUES ('104', 'article_state', '草稿', '1', '0', null, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00', null);
INSERT INTO `sys_dict_item` VALUES ('105', 'article_state', '已删除', '2', '0', null, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00', null);
INSERT INTO `sys_dict_item` VALUES ('106', 'activity_type', '学车报名', '1', '0', '学车报名', null, '2021-01-13 10:04:42', null, null, null);
INSERT INTO `sys_dict_item` VALUES ('107', 'activity_type', '考试报名', '2', '0', '考试报名', null, '2021-01-13 10:04:42', null, null, null);
INSERT INTO `sys_dict_item` VALUES ('108', 'activity_type', '常规训练', '3', '0', '常规训练', null, '2021-01-13 10:04:42', null, null, null);
INSERT INTO `sys_dict_item` VALUES ('109', 'activity_type', '考试训练', '4', '0', '考试训练', null, '2021-01-13 10:04:42', null, null, null);
INSERT INTO `sys_dict_item` VALUES ('110', 'activity_type', '大礼包活动', '5', '0', '大礼包活动', null, '2021-01-13 10:04:42', null, null, null);
INSERT INTO `sys_dict_item` VALUES ('111', 'coupon_type', '全场通用券', '0', '0', '全场通用券', null, '2021-01-13 10:04:42', null, null, null);
INSERT INTO `sys_dict_item` VALUES ('112', 'coupon_type', '会员赠券', '1', '0', '会员赠券', null, '2021-01-13 10:04:42', null, null, null);
INSERT INTO `sys_dict_item` VALUES ('113', 'coupon_type', '报名抵用券', '2', '0', '报名抵用券', null, '2021-01-13 10:04:42', null, null, null);
INSERT INTO `sys_dict_item` VALUES ('114', 'coupon_type', '注册赠券', '3', '0', '注册赠券', null, '2021-01-13 10:04:42', null, null, null);
INSERT INTO `sys_dict_item` VALUES ('115', 'coupon_type', '练车抵用券', '4', '0', '练车抵用券', null, '2021-01-13 10:04:42', null, null, null);
INSERT INTO `sys_dict_item` VALUES ('116', 'coupon_type', '考试抵用劵', '5', '0', '考试抵用劵', null, '2021-01-13 10:04:42', null, null, null);
INSERT INTO `sys_dict_item` VALUES ('117', 'delete_status', '正常', '0', '0', '正常', null, '2021-01-13 10:04:42', null, null, null);
INSERT INTO `sys_dict_item` VALUES ('118', 'delete_status', '已删除', '1', '0', '已删除', null, '2021-01-13 10:04:42', null, null, null);
INSERT INTO `sys_dict_item` VALUES ('119', 'coupon_use_status', '未领取', '1', '0', null, null, '2021-01-13 10:04:42', null, null, null);
INSERT INTO `sys_dict_item` VALUES ('120', 'coupon_use_status', '已领取', '2', '0', null, null, '2021-01-13 10:04:42', null, null, null);
INSERT INTO `sys_dict_item` VALUES ('121', 'coupon_use_status', '已使用', '3', '0', null, null, '2021-01-13 10:04:42', null, null, null);
INSERT INTO `sys_dict_item` VALUES ('122', 'coupon_use_status', '已过期', '4', '0', null, null, '2021-01-13 10:04:42', null, null, null);
INSERT INTO `sys_dict_item` VALUES ('123', 'service_item_type', '学车报名', '1', '0', 'service_item_type', null, '2021-01-13 10:04:42', null, null, null);
INSERT INTO `sys_dict_item` VALUES ('124', 'service_item_type', '科目一', '2', '0', '科目一', null, '2021-01-13 10:04:42', null, null, null);
INSERT INTO `sys_dict_item` VALUES ('125', 'service_item_type', '科目二', '3', '0', '科目二', null, '2021-01-13 10:04:42', null, null, null);
INSERT INTO `sys_dict_item` VALUES ('126', 'service_item_type', '科目三', '4', '0', '科目三', null, '2021-01-13 10:04:42', null, null, null);
INSERT INTO `sys_dict_item` VALUES ('127', 'service_item_type', '科目四', '5', '0', '科目三安全理论', null, '2021-01-13 18:04:42', null, '2021-01-20 16:26:03', null);
INSERT INTO `sys_dict_item` VALUES ('130', 'enable_status', '启用', '1', '0', null, null, '2021-01-13 10:04:42', null, '2021-05-27 19:05:01', null);
INSERT INTO `sys_dict_item` VALUES ('131', 'enable_status', '停用', '0', '0', null, null, '2021-01-13 10:04:42', null, null, null);
INSERT INTO `sys_dict_item` VALUES ('132', 'drive_type', 'c1(手动挡)', '1', '0', null, null, '2021-01-13 10:04:42', null, null, null);
INSERT INTO `sys_dict_item` VALUES ('133', 'drive_type', 'c2(自动挡)', '2', '0', null, null, '2021-01-13 10:04:42', null, null, null);
INSERT INTO `sys_dict_item` VALUES ('134', 'class_type', '自主预约', '1', '0', null, null, '2021-01-13 10:04:42', null, null, null);
INSERT INTO `sys_dict_item` VALUES ('135', 'class_type', 'VIP特训班 ', '2', '0', null, null, '2021-01-13 10:04:42', null, null, null);
INSERT INTO `sys_dict_item` VALUES ('136', 'class_type', 'VIP普通班', '3', '0', null, null, '2021-01-13 10:04:42', null, null, null);
INSERT INTO `sys_dict_item` VALUES ('137', 'class_type', ' VIP包过班', '4', '0', null, null, '2021-01-13 10:04:42', null, null, null);
INSERT INTO `sys_dict_item` VALUES ('138', 'class_type', '四人普通班', '0', '0', null, null, '2021-01-13 10:04:42', null, null, null);
INSERT INTO `sys_dict_item` VALUES ('139', 'class_type', '1对1王者班', '5', '0', null, null, '2021-01-13 10:04:42', null, null, null);
INSERT INTO `sys_dict_item` VALUES ('140', 'class_type', '1对1钻石班', '6', '0', null, null, '2021-01-13 10:04:42', null, null, null);
INSERT INTO `sys_dict_item` VALUES ('141', 'class_type', '1对1黄金班', '7', '0', null, null, '2021-01-13 10:04:42', null, null, null);
INSERT INTO `sys_dict_item` VALUES ('142', 'class_type', '1对1白金班', '8', '0', null, null, '2021-01-13 10:04:42', null, null, null);
INSERT INTO `sys_dict_item` VALUES ('143', 'enable_status_old', '启用', '1', '0', '启用', null, '2021-01-13 10:04:42', null, null, null);
INSERT INTO `sys_dict_item` VALUES ('144', 'enable_status_old', '停用', '2', '0', '停用', null, '2021-01-13 10:04:42', null, null, null);
INSERT INTO `sys_dict_item` VALUES ('145', 'yes_no', '是', '1', '0', null, null, '2021-01-13 10:04:42', null, null, null);
INSERT INTO `sys_dict_item` VALUES ('146', 'yes_no', '否', '0', '0', null, null, '2021-01-13 10:04:42', null, null, null);
INSERT INTO `sys_dict_item` VALUES ('147', 'serive_class_status', '未发布', '0', '0', null, null, '2021-01-13 10:16:16', null, '2021-01-13 10:16:16', null);
INSERT INTO `sys_dict_item` VALUES ('148', 'serive_class_status', '已发布', '1', '0', null, null, '2021-01-13 10:21:21', null, '2021-01-13 10:21:21', null);
INSERT INTO `sys_dict_item` VALUES ('149', 'serive_class_status', '已下架', '2', '0', null, null, '2021-01-13 10:21:28', null, '2021-01-13 10:21:28', null);
INSERT INTO `sys_dict_item` VALUES ('150', 'service_class_status', '未发布', '0', '0', null, null, '2021-01-13 11:23:28', null, '2021-01-13 11:23:28', null);
INSERT INTO `sys_dict_item` VALUES ('151', 'service_class_status', '发布', '1', '0', null, null, '2021-01-13 19:23:34', null, '2021-01-13 19:23:34', null);
INSERT INTO `sys_dict_item` VALUES ('152', 'service_class_status', '下架', '2', '0', null, null, '2021-01-13 19:23:40', null, '2021-01-13 19:23:40', null);
INSERT INTO `sys_dict_item` VALUES ('153', 'class_service_unit', '个', '1', '0', null, null, '2021-01-14 15:04:44', null, '2021-01-14 15:04:44', null);
INSERT INTO `sys_dict_item` VALUES ('154', 'class_service_unit', '次', '2', '0', null, null, '2021-01-14 23:05:52', null, '2021-01-14 23:05:52', null);
INSERT INTO `sys_dict_item` VALUES ('155', 'class_service_unit', '公里', '3', '0', null, null, '2021-01-14 23:05:59', null, '2021-01-14 23:05:59', null);
INSERT INTO `sys_dict_item` VALUES ('156', 'service_provide', '平台', '1', '0', null, null, '2021-01-14 15:07:27', null, '2021-01-14 15:07:27', null);
INSERT INTO `sys_dict_item` VALUES ('157', 'service_provide', '教练', '2', '0', null, null, '2021-01-14 15:07:35', null, '2021-01-14 15:07:35', null);
INSERT INTO `sys_dict_item` VALUES ('158', 'slideshow_location', '首页轮播', '0', '0', '首页', null, '2021-01-15 01:01:30', null, '2021-01-15 01:01:30', null);
INSERT INTO `sys_dict_item` VALUES ('159', 'slideshow_location', '学车报名轮播', '1', '0', '学车报名轮播', null, '2021-01-14 17:01:48', null, '2021-01-14 17:01:48', null);
INSERT INTO `sys_dict_item` VALUES ('160', 'slideshow_location', '考试报名轮播', '2', '0', '考试报名轮播', null, '2021-01-14 17:02:17', null, '2021-01-14 17:02:17', null);
INSERT INTO `sys_dict_item` VALUES ('161', 'slideshow_location', '常规训练轮播', '3', '0', '常规训练轮播', null, '2021-01-14 17:02:34', null, '2021-01-14 17:02:34', null);
INSERT INTO `sys_dict_item` VALUES ('162', 'slideshow_location', '考试训练轮播', '4', '0', '考试训练轮播', null, '2021-01-14 17:02:51', null, '2021-01-14 17:02:51', null);
INSERT INTO `sys_dict_item` VALUES ('163', 'student_enable_status_old', '正常', '1', '0', null, null, '2021-01-28 17:05:35', null, '2021-01-28 17:05:35', null);
INSERT INTO `sys_dict_item` VALUES ('164', 'student_enable_status_old', '停用', '2', '0', null, null, '2021-01-28 17:06:15', null, '2021-01-28 17:06:15', null);
INSERT INTO `sys_dict_item` VALUES ('165', 'promotion_type', '扫码', '1', '0', null, null, '2021-01-28 17:39:10', null, '2021-01-28 17:39:10', null);
INSERT INTO `sys_dict_item` VALUES ('166', 'promotion_type', '线上推广', '2', '0', null, null, '2021-01-28 17:39:17', null, '2021-01-28 17:39:17', null);
INSERT INTO `sys_dict_item` VALUES ('168', 'subject_type', '科目一', '1', '0', null, null, '2021-01-30 15:37:36', null, '2021-01-30 15:37:36', null);
INSERT INTO `sys_dict_item` VALUES ('169', 'subject_type', '科目二', '2', '0', null, null, '2021-01-30 15:37:42', null, '2021-01-30 15:37:42', null);
INSERT INTO `sys_dict_item` VALUES ('170', 'subject_type', '科目三', '3', '0', null, null, '2021-01-30 15:37:48', null, '2021-01-30 15:37:48', null);
INSERT INTO `sys_dict_item` VALUES ('171', 'subject_type', '科目四', '4', '0', null, null, '2021-01-30 15:37:55', null, '2021-01-30 15:37:55', null);
INSERT INTO `sys_dict_item` VALUES ('172', 'subject_type', '科目二/三', '5', '0', null, null, '2021-01-30 15:38:09', null, '2021-01-30 15:38:09', null);
INSERT INTO `sys_dict_item` VALUES ('173', 'service_type', '报名客服(线上客服)', '1', '0', null, null, '2021-01-30 17:23:22', null, '2021-01-30 17:23:22', null);
INSERT INTO `sys_dict_item` VALUES ('174', 'service_type', '线下客服', '2', '0', null, null, '2021-01-30 17:23:27', null, '2021-01-30 17:23:27', null);
INSERT INTO `sys_dict_item` VALUES ('175', 'service_type', '超级管理员客服', '3', '0', null, null, '2021-01-30 17:23:35', null, '2021-01-30 17:23:35', null);
INSERT INTO `sys_dict_item` VALUES ('176', 'service_type', '考试客服（线上客服）', '4', '0', null, null, '2021-01-30 17:23:41', null, '2021-01-30 17:23:41', null);
INSERT INTO `sys_dict_item` VALUES ('177', 'enroll_status', '提交报名', '1', '0', null, null, '2021-02-01 10:25:48', null, '2021-02-01 10:25:48', null);
INSERT INTO `sys_dict_item` VALUES ('178', 'enroll_status', '已联系待支付', '2', '0', null, null, '2021-02-01 10:25:55', null, '2021-02-01 10:25:55', null);
INSERT INTO `sys_dict_item` VALUES ('179', 'enroll_status', '已支付待面签', '3', '0', null, null, '2021-02-01 10:26:24', null, '2021-02-01 10:26:24', null);
INSERT INTO `sys_dict_item` VALUES ('180', 'enroll_status', '支付失败', '5', '0', null, null, '2021-02-01 10:26:59', null, '2021-02-01 10:26:59', null);
INSERT INTO `sys_dict_item` VALUES ('181', 'enroll_status', '报名完成', '6', '0', null, null, '2021-02-01 10:27:06', null, '2021-02-01 10:27:06', null);
INSERT INTO `sys_dict_item` VALUES ('182', 'enroll_status', '报名取消', '7', '0', null, null, '2021-02-01 10:27:13', null, '2021-02-01 10:27:13', null);
INSERT INTO `sys_dict_item` VALUES ('183', 'enroll_status', '报名失败', '8', '0', null, null, '2021-02-01 10:27:19', null, '2021-02-01 10:27:19', null);
INSERT INTO `sys_dict_item` VALUES ('184', 'enroll_status', '已退款', '9', '0', null, null, '2021-02-01 10:27:25', null, '2021-02-01 10:27:25', null);
INSERT INTO `sys_dict_item` VALUES ('185', 'enroll_status', '自动报名完成', '10', '0', null, null, '2021-02-01 10:27:33', null, '2021-02-01 10:27:33', null);
INSERT INTO `sys_dict_item` VALUES ('186', 'enroll_status', '自动报名待审核', '11', '0', null, null, '2021-02-01 10:27:45', null, '2021-02-01 10:27:45', null);
INSERT INTO `sys_dict_item` VALUES ('187', 'enroll_status', '已面签待审核', '12', '0', null, null, '2021-02-01 10:27:56', null, '2021-02-01 10:27:56', null);
INSERT INTO `sys_dict_item` VALUES ('188', 'enroll_status', '已提交密码待审核', '13', '0', null, null, '2021-02-01 10:28:05', null, '2021-02-01 10:28:05', null);
INSERT INTO `sys_dict_item` VALUES ('189', 'enroll_status', '退款处理中', '14', '0', null, null, '2021-02-01 11:09:21', null, '2021-02-01 11:09:21', null);
INSERT INTO `sys_dict_item` VALUES ('190', 'enroll_status', '已升班', '15', '0', null, null, '2021-02-01 11:09:32', null, '2021-02-01 11:09:32', null);
INSERT INTO `sys_dict_item` VALUES ('191', 'enroll_status', '已升班待支付', '16', '0', null, null, '2021-02-01 11:09:40', null, '2021-02-01 11:09:40', null);
INSERT INTO `sys_dict_item` VALUES ('192', 'sex_old', '女', '0', '0', null, null, '2021-02-01 16:35:34', null, '2021-02-01 16:35:34', null);
INSERT INTO `sys_dict_item` VALUES ('193', 'sex_old', '男', '1', '0', null, null, '2021-02-01 16:35:40', null, '2021-02-01 16:35:40', null);
INSERT INTO `sys_dict_item` VALUES ('194', 'is_show', '隐藏', '0', '0', '隐藏', null, '2021-02-02 09:37:50', null, '2021-02-02 09:37:50', null);
INSERT INTO `sys_dict_item` VALUES ('195', 'is_show', '显示', '1', '0', '显示', null, '2021-02-02 09:38:01', null, '2021-02-02 09:38:01', null);
INSERT INTO `sys_dict_item` VALUES ('196', 'is_open', '是', '1', '0', '开放', null, '2021-02-02 10:42:29', null, '2021-02-02 10:42:29', null);
INSERT INTO `sys_dict_item` VALUES ('197', 'is_open', '否', '0', '0', '不开放', null, '2021-02-02 10:43:00', null, '2021-02-02 10:43:00', null);
INSERT INTO `sys_dict_item` VALUES ('198', 'from_platform', '未知', '0', '0', '未知', null, '2021-02-02 11:07:59', null, '2021-02-02 11:07:59', null);
INSERT INTO `sys_dict_item` VALUES ('199', 'from_platform', '安卓', '1', '0', '安卓', null, '2021-02-02 11:09:36', null, '2021-02-02 11:09:36', null);
INSERT INTO `sys_dict_item` VALUES ('200', 'from_platform', 'ios', '2', '0', 'ios', null, '2021-02-02 11:10:07', null, '2021-02-02 11:10:07', null);
INSERT INTO `sys_dict_item` VALUES ('201', 'from_platform', 'WEB', '3', '0', 'WEB', null, '2021-02-02 11:10:43', null, '2021-02-02 11:10:43', null);
INSERT INTO `sys_dict_item` VALUES ('202', 'from_platform', '微信', '4', '0', '微信', null, '2021-02-02 11:12:28', null, '2021-02-02 11:12:28', null);
INSERT INTO `sys_dict_item` VALUES ('203', 'from_platform', 'PC-WEB', '5', '0', 'PC-WEB', null, '2021-02-02 11:13:04', null, '2021-02-02 11:13:04', null);
INSERT INTO `sys_dict_item` VALUES ('204', 'from_platform', '桌面版Linux', '6', '0', '桌面版Linux', null, '2021-02-02 11:13:29', null, '2021-02-02 11:13:29', null);
INSERT INTO `sys_dict_item` VALUES ('205', 'from_platform', '桌面macOS', '7', '0', '桌面macOS', null, '2021-02-02 11:13:50', null, '2021-02-02 11:13:50', null);
INSERT INTO `sys_dict_item` VALUES ('206', 'from_platform', '桌面Windows', '8', '0', '桌面Windows', null, '2021-02-02 11:14:13', null, '2021-02-02 11:14:13', null);
INSERT INTO `sys_dict_item` VALUES ('207', 'video_type', '科目一', '1', '0', '科目一', null, '2021-02-02 11:28:44', null, '2021-02-02 11:28:44', null);
INSERT INTO `sys_dict_item` VALUES ('208', 'video_type', '科目二', '2', '0', '科目二', null, '2021-02-02 11:29:36', null, '2021-02-02 11:29:36', null);
INSERT INTO `sys_dict_item` VALUES ('209', 'video_type', '科目三', '3', '0', '科目三', null, '2021-02-02 11:29:59', null, '2021-02-02 11:29:59', null);
INSERT INTO `sys_dict_item` VALUES ('210', 'video_type', '科目四', '4', '0', '科目四', null, '2021-02-02 11:30:57', null, '2021-02-02 11:30:57', null);
INSERT INTO `sys_dict_item` VALUES ('211', 'video_type', '所有', '0', '0', '所有', null, '2021-02-02 11:31:34', null, '2021-02-02 11:31:34', null);
INSERT INTO `sys_dict_item` VALUES ('212', 'video_delete_type', '未删除', '0', '0', '未删除', null, '2021-02-02 11:38:00', null, '2021-02-02 11:38:00', null);
INSERT INTO `sys_dict_item` VALUES ('213', 'video_delete_type', '已删除', '1', '0', '已删除', null, '2021-02-02 11:38:20', null, '2021-02-02 11:38:20', null);
INSERT INTO `sys_dict_item` VALUES ('214', 'video_send_type', '未发布', '0', '0', '未发布', null, '2021-02-02 11:40:41', null, '2021-02-02 11:40:41', null);
INSERT INTO `sys_dict_item` VALUES ('215', 'video_send_type', '已发布', '1', '0', '已发布', null, '2021-02-02 11:41:03', null, '2021-02-02 11:41:03', null);
INSERT INTO `sys_dict_item` VALUES ('216', 'coach_type', '待审', '0', '0', null, null, '2021-02-02 15:05:09', null, '2021-02-02 15:05:09', null);
INSERT INTO `sys_dict_item` VALUES ('217', 'coach_type', '驳回', '1', '0', null, null, '2021-02-02 15:05:15', null, '2021-02-02 15:05:15', null);
INSERT INTO `sys_dict_item` VALUES ('218', 'coach_type', '正常', '2', '0', null, null, '2021-02-02 15:05:21', null, '2021-02-02 15:05:21', null);
INSERT INTO `sys_dict_item` VALUES ('219', 'coach_type', '禁用', '3', '0', null, null, '2021-02-02 15:05:27', null, '2021-02-02 15:05:27', null);
INSERT INTO `sys_dict_item` VALUES ('220', 'coach_type', '未提交', '4', '0', null, null, '2021-02-02 15:05:35', null, '2021-02-02 15:05:35', null);
INSERT INTO `sys_dict_item` VALUES ('221', 'coach_type', '保险预警期', '5', '0', null, null, '2021-02-02 15:05:41', null, '2021-02-02 15:05:41', null);
INSERT INTO `sys_dict_item` VALUES ('222', 'order_type', '学车报名', '1', '0', null, null, '2021-02-02 15:09:38', null, '2021-02-02 15:09:38', null);
INSERT INTO `sys_dict_item` VALUES ('223', 'order_type', '考试报名', '2', '0', null, null, '2021-02-02 15:09:47', null, '2021-02-02 15:09:47', null);
INSERT INTO `sys_dict_item` VALUES ('224', 'order_type', '常规训练', '3', '0', null, null, '2021-02-02 15:09:53', null, '2021-02-02 15:09:53', null);
INSERT INTO `sys_dict_item` VALUES ('225', 'order_type', '考试训练', '4', '0', null, null, '2021-02-02 15:10:04', null, '2021-02-02 15:10:04', null);
INSERT INTO `sys_dict_item` VALUES ('227', 'order_type', '课时大礼包', '5', '0', null, null, '2021-02-02 15:10:14', null, '2021-02-02 15:10:14', null);
INSERT INTO `sys_dict_item` VALUES ('228', 'order_status', '待支付', '1', '0', null, null, '2021-02-02 15:13:02', null, '2021-02-02 15:13:02', null);
INSERT INTO `sys_dict_item` VALUES ('229', 'order_status', '支付成功', '2', '0', null, null, '2021-02-02 15:13:07', null, '2021-02-02 15:13:07', null);
INSERT INTO `sys_dict_item` VALUES ('230', 'order_status', '支付处理中', '3', '0', null, null, '2021-02-02 15:13:12', null, '2021-02-02 15:13:12', null);
INSERT INTO `sys_dict_item` VALUES ('231', 'order_status', '支付失败', '4', '0', null, null, '2021-02-02 15:13:17', null, '2021-02-02 15:13:17', null);
INSERT INTO `sys_dict_item` VALUES ('232', 'order_status', '已取消', '5', '0', null, null, '2021-02-02 15:13:23', null, '2021-02-02 15:13:23', null);
INSERT INTO `sys_dict_item` VALUES ('233', 'order_status', '待评价', '6', '0', null, null, '2021-02-02 15:13:29', null, '2021-02-02 15:13:29', null);
INSERT INTO `sys_dict_item` VALUES ('234', 'order_status', '已评价', '7', '0', null, null, '2021-02-02 15:13:37', null, '2021-02-02 15:13:37', null);
INSERT INTO `sys_dict_item` VALUES ('235', 'order_status', '退款成功', '8', '0', null, null, '2021-02-02 15:13:44', null, '2021-02-02 15:13:44', null);
INSERT INTO `sys_dict_item` VALUES ('236', 'order_status', '退款处理中', '9', '0', null, null, '2021-02-02 15:13:50', null, '2021-02-02 15:13:50', null);
INSERT INTO `sys_dict_item` VALUES ('237', 'pay_type', '支付宝', '1', '0', null, null, '2021-02-02 15:14:50', null, '2021-02-02 15:14:50', null);
INSERT INTO `sys_dict_item` VALUES ('238', 'pay_type', '微信', '2', '0', null, null, '2021-02-02 15:14:56', null, '2021-02-02 15:14:56', null);
INSERT INTO `sys_dict_item` VALUES ('239', 'pay_type', '优惠卷支付', '3', '0', null, null, '2021-02-02 15:15:01', null, '2021-02-02 15:15:01', null);
INSERT INTO `sys_dict_item` VALUES ('240', 'pay_type', 'vip支付', '4', '0', null, null, '2021-02-02 15:15:06', null, '2021-02-02 15:15:06', null);
INSERT INTO `sys_dict_item` VALUES ('241', 'pay_type', '0元支付', '5', '0', null, null, '2021-02-02 15:15:13', null, '2021-02-02 15:15:13', null);
INSERT INTO `sys_dict_item` VALUES ('242', 'pay_type', '公众号微信支付', '6', '0', null, null, '2021-02-02 15:15:20', null, '2021-02-02 15:15:20', null);
INSERT INTO `sys_dict_item` VALUES ('243', 'test_enroll_status', '提交预约', '1', '0', null, null, '2021-02-03 10:00:25', null, '2021-02-03 10:00:25', null);
INSERT INTO `sys_dict_item` VALUES ('244', 'test_enroll_status', '支付成功', '2', '0', null, null, '2021-02-03 10:00:31', null, '2021-02-03 10:00:31', null);
INSERT INTO `sys_dict_item` VALUES ('245', 'test_enroll_status', '支付失败', '3', '0', null, null, '2021-02-03 10:00:39', null, '2021-02-03 10:00:39', null);
INSERT INTO `sys_dict_item` VALUES ('246', 'test_enroll_status', '报名失败', '4', '0', null, null, '2021-02-03 10:00:44', null, '2021-02-03 10:00:44', null);
INSERT INTO `sys_dict_item` VALUES ('247', 'test_enroll_status', '预约成功', '5', '0', null, null, '2021-02-03 10:00:51', null, '2021-02-03 10:00:51', null);
INSERT INTO `sys_dict_item` VALUES ('248', 'test_enroll_status', '报名取消', '6', '0', null, null, '2021-02-03 10:00:57', null, '2021-02-03 10:00:57', null);
INSERT INTO `sys_dict_item` VALUES ('249', 'test_enroll_status', '考试完成', '7', '0', null, null, '2021-02-03 10:01:04', null, '2021-02-03 10:01:04', null);
INSERT INTO `sys_dict_item` VALUES ('250', 'test_enroll_status', '考试通过', '8', '0', null, null, '2021-02-03 10:01:09', null, '2021-02-03 10:01:09', null);
INSERT INTO `sys_dict_item` VALUES ('251', 'test_enroll_status', '考试不通过', '9', '0', null, null, '2021-02-03 10:01:17', null, '2021-02-03 10:01:17', null);
INSERT INTO `sys_dict_item` VALUES ('252', 'test_enroll_status', '申请中', '10', '0', null, null, '2021-02-03 10:01:23', null, '2021-02-03 10:01:23', null);
INSERT INTO `sys_dict_item` VALUES ('253', 'test_enroll_status', '退款处理中', '11', '0', null, null, '2021-02-03 10:01:28', null, '2021-02-03 10:01:28', null);
INSERT INTO `sys_dict_item` VALUES ('254', 'test_enroll_status', '退款成功', '12', '0', null, null, '2021-02-03 10:01:36', null, '2021-02-03 10:01:36', null);
INSERT INTO `sys_dict_item` VALUES ('255', 'car_type', '公车', '1', '0', '公车', null, '2021-02-03 11:25:32', null, '2021-02-03 11:25:32', null);
INSERT INTO `sys_dict_item` VALUES ('256', 'car_type', '私车', '2', '0', '私车', null, '2021-02-03 11:26:00', null, '2021-02-03 11:26:00', null);
INSERT INTO `sys_dict_item` VALUES ('257', 'auto_send_class', '是', '1', '0', '是', null, '2021-02-04 09:59:17', null, '2021-02-04 09:59:17', null);
INSERT INTO `sys_dict_item` VALUES ('258', 'auto_send_class', '否', '2', '0', '否', null, '2021-02-04 09:59:47', null, '2021-02-04 09:59:47', null);
INSERT INTO `sys_dict_item` VALUES ('259', 'vip_coach', '是', '1', '0', '是', null, '2021-02-04 10:06:27', null, '2021-02-04 10:06:27', null);
INSERT INTO `sys_dict_item` VALUES ('260', 'vip_coach', '否', '0', '0', '否', null, '2021-02-04 10:06:49', null, '2021-02-04 10:06:49', null);
INSERT INTO `sys_dict_item` VALUES ('261', 'time_status', '已发布', '1', '0', '正常', null, '2021-02-05 11:05:00', null, '2021-02-05 11:05:00', null);
INSERT INTO `sys_dict_item` VALUES ('262', 'time_status', '未发布', '2', '0', '停用', null, '2021-02-05 11:05:28', null, '2021-02-05 11:05:28', null);
INSERT INTO `sys_dict_item` VALUES ('263', 'vip_coach_status', '正常', '1', '0', '正常', null, '2021-02-20 11:10:22', null, '2021-02-20 11:10:22', null);
INSERT INTO `sys_dict_item` VALUES ('264', 'vip_coach_status', '停用', '2', '0', '停用', null, '2021-02-20 11:10:45', null, '2021-02-20 11:10:45', null);
INSERT INTO `sys_dict_item` VALUES ('265', 'channel_status', '未发表', '0', '0', '未发表', null, '2021-02-22 11:37:03', null, '2021-02-22 11:37:03', null);
INSERT INTO `sys_dict_item` VALUES ('266', 'channel_status', '已发表', '1', '0', '已发表', null, '2021-02-22 11:37:29', null, '2021-02-22 11:37:29', null);
INSERT INTO `sys_dict_item` VALUES ('267', 'channel_user_status', '正常', '1', '0', '正常', null, '2021-02-23 10:25:19', null, '2021-02-23 10:25:19', null);
INSERT INTO `sys_dict_item` VALUES ('268', 'channel_user_status', '停用', '0', '0', '停用', null, '2021-02-23 10:25:50', null, '2021-02-23 10:25:50', null);
INSERT INTO `sys_dict_item` VALUES ('269', 'use_type', '全场通用', '0', '0', '全场通用', null, '2021-02-25 13:51:50', null, '2021-02-25 13:51:50', null);
INSERT INTO `sys_dict_item` VALUES ('270', 'use_type', '指定分类', '1', '0', '指定分类', null, '2021-02-25 13:52:33', null, '2021-02-25 13:52:33', null);
INSERT INTO `sys_dict_item` VALUES ('271', 'use_type', '指定产品', '2', '0', '指定产品', null, '2021-02-25 13:52:59', null, '2021-02-25 13:52:59', null);
INSERT INTO `sys_dict_item` VALUES ('272', 'platform_type', '全部', '0', '0', '全部', null, '2021-02-25 14:08:46', null, '2021-02-25 14:08:46', null);
INSERT INTO `sys_dict_item` VALUES ('273', 'platform_type', '移动', '1', '0', '移动', null, '2021-02-25 14:09:17', null, '2021-02-25 14:09:17', null);
INSERT INTO `sys_dict_item` VALUES ('274', 'platform_type', 'PC', '2', '0', 'PC', null, '2021-02-25 14:09:35', null, '2021-02-25 14:09:35', null);
INSERT INTO `sys_dict_item` VALUES ('275', 'platform_type', '微信', '3', '0', '微信', null, '2021-02-25 14:10:02', null, '2021-02-25 14:10:02', null);
INSERT INTO `sys_dict_item` VALUES ('276', 'items_show', '显示', '1', '0', '显示', null, '2021-02-25 18:27:29', null, '2021-02-25 18:27:29', null);
INSERT INTO `sys_dict_item` VALUES ('277', 'items_show', '不显示', '0', '0', '不显示', null, '2021-02-25 18:29:17', null, '2021-02-25 18:29:17', null);
INSERT INTO `sys_dict_item` VALUES ('278', 'is_enroll', '是', '1', '0', '是', null, '2021-02-26 11:26:17', null, '2021-02-26 11:26:17', null);
INSERT INTO `sys_dict_item` VALUES ('279', 'is_enroll', '否', '0', '0', '否', null, '2021-02-26 11:26:38', null, '2021-02-26 11:26:38', null);
INSERT INTO `sys_dict_item` VALUES ('280', 'school_status', '正常', '1', '0', '正常', null, '2021-02-26 11:30:35', null, '2021-02-26 11:30:35', null);
INSERT INTO `sys_dict_item` VALUES ('281', 'school_status', '停用', '2', '0', '停用', null, '2021-02-26 11:31:20', null, '2021-02-26 11:31:20', null);
INSERT INTO `sys_dict_item` VALUES ('282', 'is_alone_settlement', '否', '0', '0', '否', null, '2021-02-26 19:51:26', null, '2021-02-26 19:51:26', null);
INSERT INTO `sys_dict_item` VALUES ('283', 'is_alone_settlement', '是', '1', '0', '是', null, '2021-02-26 11:51:46', null, '2021-02-26 11:51:46', null);
INSERT INTO `sys_dict_item` VALUES ('284', 'join_type', '全员参与', '1', '0', '全员参与', null, '2021-03-01 10:41:39', null, '2021-03-01 10:41:39', null);
INSERT INTO `sys_dict_item` VALUES ('285', 'join_type', '指定用户参与', '2', '0', '指定用户参与', null, '2021-03-01 10:41:59', null, '2021-03-01 10:41:59', null);
INSERT INTO `sys_dict_item` VALUES ('286', 'participation_way', '全员参与', '1', '0', '全员参与', null, '2021-03-01 11:15:29', null, '2021-03-01 11:15:29', null);
INSERT INTO `sys_dict_item` VALUES ('287', 'participation_way', '指定用户参与', '2', '0', '指定用户参与', null, '2021-03-01 11:15:53', null, '2021-03-01 11:15:53', null);
INSERT INTO `sys_dict_item` VALUES ('288', 'evaluate_grade', '好评（4-5星）', '1', '0', '好评', null, '2021-03-06 11:48:39', null, '2021-03-06 11:48:39', null);
INSERT INTO `sys_dict_item` VALUES ('289', 'evaluate_grade', '中评（3星）', '2', '0', '中评', null, '2021-03-06 11:49:08', null, '2021-03-06 11:49:08', null);
INSERT INTO `sys_dict_item` VALUES ('290', 'evaluate_grade', '差评（1-2星）', '3', '0', '差评', null, '2021-03-06 11:49:18', null, '2021-03-06 11:49:18', null);
INSERT INTO `sys_dict_item` VALUES ('291', 'assessment_type', '投诉', '2', '0', null, null, '2021-03-10 09:40:30', null, '2021-03-10 09:40:30', null);
INSERT INTO `sys_dict_item` VALUES ('292', 'assessment_type', '评价', '1', '0', null, null, '2021-03-10 09:40:36', null, '2021-03-10 09:40:36', null);
INSERT INTO `sys_dict_item` VALUES ('293', 'to_be_assessment_type', '线上客服', '3', '0', null, null, '2021-03-10 09:41:41', null, '2021-03-10 09:41:41', null);
INSERT INTO `sys_dict_item` VALUES ('294', 'to_be_assessment_type', '教练', '2', '0', null, null, '2021-03-10 09:41:47', null, '2021-03-10 09:41:47', null);
INSERT INTO `sys_dict_item` VALUES ('295', 'to_be_assessment_type', '线下客服', '4', '0', null, null, '2021-03-11 11:03:09', null, '2021-03-11 11:03:09', null);
INSERT INTO `sys_dict_item` VALUES ('111111', 'class_type', '白银班', '9', '0', null, null, '2021-01-27 00:21:54', null, '2021-01-27 00:21:54', null);
INSERT INTO `sys_dict_item` VALUES ('111112', 'coach_bind_status', '未绑定', '1', '0', null, null, '2021-03-17 13:49:47', null, '2021-03-17 13:49:47', null);
INSERT INTO `sys_dict_item` VALUES ('111113', 'coach_bind_status', '已绑定', '2', '0', null, null, '2021-03-17 13:49:53', null, '2021-03-17 13:49:53', null);
INSERT INTO `sys_dict_item` VALUES ('111114', 'coach_bind_status', '已解绑', '3', '0', null, null, '2021-03-17 14:00:00', null, '2021-03-17 14:00:00', null);
INSERT INTO `sys_dict_item` VALUES ('111115', 'one_fee_system_coach_student', '正常 ', '1', '0', null, null, '2021-03-17 14:15:56', null, '2021-03-17 14:15:56', null);
INSERT INTO `sys_dict_item` VALUES ('111116', 'one_fee_system_coach_student', '停用', '2', '0', null, null, '2021-03-17 14:16:08', null, '2021-03-17 14:16:08', null);
INSERT INTO `sys_dict_item` VALUES ('111117', 'channel_type', '渠道1', '1', '0', null, null, '2021-03-29 20:22:25', null, '2021-03-29 20:22:25', null);
INSERT INTO `sys_dict_item` VALUES ('111118', 'channel_type', '渠道2', '2', '0', null, null, '2021-03-29 20:22:32', null, '2021-03-29 20:22:32', null);
INSERT INTO `sys_dict_item` VALUES ('111120', 'visited_stage', '新用户回访', '0', '0', null, null, '2021-03-31 10:41:17', null, '2021-03-31 10:41:17', null);
INSERT INTO `sys_dict_item` VALUES ('111121', 'visited_stage', '售前', '01', '0', '01 代表售前', null, '2021-03-31 10:48:49', null, '2021-03-31 10:48:49', null);
INSERT INTO `sys_dict_item` VALUES ('111122', 'visited_stage', '售前', '01', '0', '01 代表售前', null, '2021-03-31 10:48:50', null, '2021-03-31 10:48:50', null);
INSERT INTO `sys_dict_item` VALUES ('111128', 'conversion_type', '平台转化', '1', '0', null, null, '2021-03-31 15:58:05', null, '2021-03-31 15:58:05', null);
INSERT INTO `sys_dict_item` VALUES ('111129', 'conversion_type', '新用户转化', '2', '0', null, null, '2021-03-31 15:58:15', null, '2021-03-31 15:58:15', null);
INSERT INTO `sys_dict_item` VALUES ('111130', 'conversion_type', '待支付转化', '3', '0', null, null, '2021-03-31 15:58:22', null, '2021-03-31 15:58:22', null);
INSERT INTO `sys_dict_item` VALUES ('111131', 'channel_type', '渠道3', '3', '0', null, null, '2021-04-02 13:33:34', null, '2021-04-02 13:33:34', null);
INSERT INTO `sys_dict_item` VALUES ('111132', 'cancel_type', '客服取消', '1', '0', null, null, '2021-04-06 15:03:52', null, '2021-04-06 15:03:52', null);
INSERT INTO `sys_dict_item` VALUES ('111133', 'cancel_type', '学员取消', '2', '0', null, null, '2021-04-06 15:03:58', null, '2021-04-06 15:03:58', null);
INSERT INTO `sys_dict_item` VALUES ('111134', 'student_test_pass', '合格', '8', '0', null, null, '2021-04-08 18:43:42', null, '2021-04-08 18:43:42', null);
INSERT INTO `sys_dict_item` VALUES ('111135', 'student_test_pass', '不合格', '9', '0', null, null, '2021-04-08 18:43:48', null, '2021-04-08 18:43:48', null);
INSERT INTO `sys_dict_item` VALUES ('111136', 'train_type', '常规练车', '1', '0', null, null, '2021-04-09 16:03:22', null, '2021-04-09 16:03:22', null);
INSERT INTO `sys_dict_item` VALUES ('111137', 'train_type', '考试练车', '2', '0', null, null, '2021-04-09 16:03:33', null, '2021-04-09 16:03:33', null);
INSERT INTO `sys_dict_item` VALUES ('111138', 'drive_class_type', '常规训练', '1', '0', null, null, '2021-04-16 11:21:21', null, '2021-04-16 11:21:21', null);
INSERT INTO `sys_dict_item` VALUES ('111139', 'drive_class_type', '考试训练', '2', '0', null, null, '2021-04-16 11:21:27', null, '2021-04-16 11:21:27', null);
INSERT INTO `sys_dict_item` VALUES ('111140', 'drive_status', '未预约', '1', '0', null, null, '2021-04-16 11:22:17', null, '2021-04-16 11:22:17', null);
INSERT INTO `sys_dict_item` VALUES ('111141', 'drive_status', '已预约', '2', '0', null, null, '2021-04-16 11:22:26', null, '2021-04-16 11:22:26', null);
INSERT INTO `sys_dict_item` VALUES ('111142', 'drive_status', '教学中', '3', '0', null, null, '2021-04-16 11:22:32', null, '2021-04-16 11:22:32', null);
INSERT INTO `sys_dict_item` VALUES ('111143', 'drive_status', '教学完成', '4', '0', null, null, '2021-04-16 11:22:39', null, '2021-04-16 11:22:39', null);
INSERT INTO `sys_dict_item` VALUES ('111144', 'drive_status', '已取消', '5', '0', null, null, '2021-04-16 11:22:46', null, '2021-04-16 11:22:46', null);
INSERT INTO `sys_dict_item` VALUES ('111145', 'drive_status', ' 接人中', '6', '0', null, null, '2021-04-16 11:22:53', null, '2021-04-16 11:22:53', null);
INSERT INTO `sys_dict_item` VALUES ('111146', 'drive_status', '已上车', '7', '0', null, null, '2021-04-16 11:22:59', null, '2021-04-16 11:22:59', null);
INSERT INTO `sys_dict_item` VALUES ('111147', 'drive_status', '超时取消', '8', '0', null, null, '2021-04-16 11:23:05', null, '2021-04-16 11:23:05', null);
INSERT INTO `sys_dict_item` VALUES ('111148', 'subject_type_select', '科目一', '1', '0', null, null, '2021-05-10 14:21:45', null, '2021-05-10 14:21:45', null);
INSERT INTO `sys_dict_item` VALUES ('111149', 'subject_type_select', '科目二', '2', '0', null, null, '2021-05-10 14:21:56', null, '2021-05-10 14:21:56', null);
INSERT INTO `sys_dict_item` VALUES ('111150', 'subject_type_select', '科目三', '3', '0', null, null, '2021-05-10 14:22:16', null, '2021-05-10 14:22:16', null);
INSERT INTO `sys_dict_item` VALUES ('111151', 'subject_type_select', '科目四', '4', '0', null, null, '2021-05-10 14:22:33', null, '2021-05-10 14:22:33', null);
INSERT INTO `sys_dict_item` VALUES ('111152', 'price_type', '学车报名', '1', '0', null, null, '2021-05-10 18:34:17', null, '2021-05-10 18:34:17', null);
INSERT INTO `sys_dict_item` VALUES ('111153', 'price_type', '考试报名', '2', '0', null, null, '2021-05-10 18:34:29', null, '2021-05-10 18:34:29', null);
INSERT INTO `sys_dict_item` VALUES ('111154', 'price_type', '常规练车', '3', '0', null, null, '2021-05-10 18:35:35', null, '2021-05-10 18:35:35', null);
INSERT INTO `sys_dict_item` VALUES ('111155', 'price_type', '考试练车', '4', '0', null, null, '2021-05-10 18:35:43', null, '2021-05-10 18:35:43', null);
INSERT INTO `sys_dict_item` VALUES ('111156', 'price_type', '推广商推荐报名提成金额', '5', '0', null, null, '2021-05-10 18:36:18', null, '2021-05-10 18:36:18', null);
INSERT INTO `sys_dict_item` VALUES ('111157', 'price_type', '推广商课时提成百分比', '6', '0', null, null, '2021-05-10 18:36:29', null, '2021-05-10 18:36:29', null);
INSERT INTO `sys_dict_item` VALUES ('111158', 'price_type', '推广商推荐新用户金额', '7', '0', null, null, '2021-05-10 18:36:38', null, '2021-05-10 18:36:38', null);
INSERT INTO `sys_dict_item` VALUES ('111159', 'price_type', '新用户填写邀请码收益金额', '8', '0', null, null, '2021-05-10 18:36:45', null, '2021-05-10 18:36:45', null);
INSERT INTO `sys_dict_item` VALUES ('111160', 'price_type', '首次推荐新用户奖励金额', '9', '0', null, null, '2021-05-10 18:36:52', null, '2021-05-10 18:36:52', null);
INSERT INTO `sys_dict_item` VALUES ('111161', 'price_type', 'VIP报名推广商推荐报名提成金额', '10', '0', null, null, '2021-05-10 18:37:00', null, '2021-05-10 18:37:00', null);
INSERT INTO `sys_dict_item` VALUES ('111162', 'teach_course_type', '未预约', '1', '0', null, null, '2021-05-11 14:24:19', null, '2021-05-11 14:24:19', null);
INSERT INTO `sys_dict_item` VALUES ('111163', 'teach_course_type', '已预约', '2', '0', null, null, '2021-05-11 14:30:39', null, '2021-05-11 14:30:39', null);
INSERT INTO `sys_dict_item` VALUES ('111164', 'teach_course_type', '教学中', '3', '0', null, null, '2021-05-11 14:31:06', null, '2021-05-11 14:31:06', null);
INSERT INTO `sys_dict_item` VALUES ('111165', 'teach_course_type', '教学完成', '4', '0', null, null, '2021-05-11 14:31:23', null, '2021-05-11 14:31:23', null);
INSERT INTO `sys_dict_item` VALUES ('111166', 'teach_course_type', '已取消', '5', '0', null, null, '2021-05-11 14:31:48', null, '2021-05-11 14:31:48', null);
INSERT INTO `sys_dict_item` VALUES ('111167', 'teach_course_type', '接人中', '6', '0', null, null, '2021-05-11 14:32:03', null, '2021-05-11 14:32:03', null);
INSERT INTO `sys_dict_item` VALUES ('111168', 'teach_course_type', '已上车', '7', '0', null, null, '2021-05-11 14:32:20', null, '2021-05-11 14:32:20', null);
INSERT INTO `sys_dict_item` VALUES ('111169', 'operator_status', '待审核', '1', '0', null, null, '2021-05-12 10:24:50', null, '2021-05-12 10:24:50', null);
INSERT INTO `sys_dict_item` VALUES ('111170', 'operator_status', '通过', '2', '0', null, null, '2021-05-12 10:24:56', null, '2021-05-12 10:24:56', null);
INSERT INTO `sys_dict_item` VALUES ('111171', 'operator_status', '驳回', '3', '0', null, null, '2021-05-12 10:25:03', null, '2021-05-12 10:25:03', null);
INSERT INTO `sys_dict_item` VALUES ('111172', 'recommend_user_status', '待审核', '1', '0', null, null, '2021-05-12 10:26:57', null, '2021-05-12 10:26:57', null);
INSERT INTO `sys_dict_item` VALUES ('111173', 'recommend_user_status', '通过', '2', '0', null, null, '2021-05-12 10:27:02', null, '2021-05-12 10:27:02', null);
INSERT INTO `sys_dict_item` VALUES ('111174', 'recommend_user_status', '驳回', '3', '0', null, null, '2021-05-12 10:27:09', null, '2021-05-12 10:27:09', null);
INSERT INTO `sys_dict_item` VALUES ('111175', 'pay_status', '待支付', '1', '0', null, null, '2021-05-12 17:31:32', null, '2021-05-12 17:31:32', null);
INSERT INTO `sys_dict_item` VALUES ('111176', 'pay_status', '支付成功', '2', '0', null, null, '2021-05-12 17:31:48', null, '2021-05-12 17:31:48', null);
INSERT INTO `sys_dict_item` VALUES ('111178', 'bind_status', '待绑定', '1', '0', null, null, '2021-05-13 09:54:59', null, '2021-05-13 09:54:59', null);
INSERT INTO `sys_dict_item` VALUES ('111179', 'bind_status', '已绑定', '2', '0', null, null, '2021-05-13 09:55:11', null, '2021-05-13 09:55:11', null);
INSERT INTO `sys_dict_item` VALUES ('111180', 'bind_status', '已解绑', '3', '0', null, null, '2021-05-13 09:55:28', null, '2021-05-13 09:55:28', null);
INSERT INTO `sys_dict_item` VALUES ('111181', 'operator_settinng_type', '每晚不可预约次日课程时间节点', '1', '0', null, null, '2021-05-13 10:07:42', null, '2021-05-13 10:07:42', null);
INSERT INTO `sys_dict_item` VALUES ('111182', 'operator_settinng_type', '可开课天数', '2', '0', null, null, '2021-05-13 10:07:47', null, '2021-05-13 10:07:47', null);
INSERT INTO `sys_dict_item` VALUES ('111183', 'operator_settinng_type', '科二考试预约需科一考试通过天数', '3', '0', null, null, '2021-05-13 10:07:53', null, '2021-05-13 10:07:53', null);
INSERT INTO `sys_dict_item` VALUES ('111184', 'operator_settinng_type', '科三考试预约需科一考试通过天数', '4', '0', null, null, '2021-05-13 10:07:58', null, '2021-05-13 10:07:58', null);
INSERT INTO `sys_dict_item` VALUES ('111185', 'operator_settinng_type', '科二考试挂科后再次约考需满天数', '5', '0', null, null, '2021-05-13 10:08:04', null, '2021-05-13 10:08:04', null);
INSERT INTO `sys_dict_item` VALUES ('111186', 'operator_settinng_type', '科三考试挂科后再次约考需满天数', '6', '0', null, null, '2021-05-13 10:08:09', null, '2021-05-13 10:08:09', null);
INSERT INTO `sys_dict_item` VALUES ('111187', 'operator_settinng_type', '未预约科二考试前可约课程数(单位：小时)', '7', '0', null, null, '2021-05-13 10:08:15', null, '2021-05-13 10:08:15', null);
INSERT INTO `sys_dict_item` VALUES ('111188', 'operator_settinng_type', '未预约科三考试前可约课程数(单位：小时)', '8', '0', null, null, '2021-05-13 10:08:20', null, '2021-05-13 10:08:20', null);
INSERT INTO `sys_dict_item` VALUES ('111189', 'operator_settinng_type', 'VIP学员-科二+科三可预课时总和 (单位：小时)', '9', '0', null, null, '2021-05-13 10:08:25', null, '2021-05-13 10:08:25', null);
INSERT INTO `sys_dict_item` VALUES ('111190', 'operator_settinng_type', '每天可预约课程数', '10', '0', null, null, '2021-05-13 10:08:32', null, '2021-05-13 10:08:32', null);
INSERT INTO `sys_dict_item` VALUES ('111191', 'operator_settinng_type', '是否签署线上报名协议', '11', '0', null, null, '2021-05-13 10:08:38', null, '2021-05-13 10:08:38', null);
INSERT INTO `sys_dict_item` VALUES ('111192', 'general_status', '正常', '1', '0', null, null, '2021-05-13 10:15:25', null, '2021-05-13 10:15:25', null);
INSERT INTO `sys_dict_item` VALUES ('111193', 'general_status', '停用', '2', '0', null, null, '2021-05-13 10:16:02', null, '2021-05-13 10:16:02', null);
INSERT INTO `sys_dict_item` VALUES ('111194', 'recommend_pos_type', '个人', '1', '0', null, null, '2021-05-13 15:07:16', null, '2021-05-13 15:07:16', null);
INSERT INTO `sys_dict_item` VALUES ('111195', 'recommend_pos_type', '商铺', '2', '0', null, null, '2021-05-13 15:07:21', null, '2021-05-13 15:07:21', null);
INSERT INTO `sys_dict_item` VALUES ('111196', 'recommend_pos_type', '企业', '3', '0', null, null, '2021-05-13 15:07:27', null, '2021-05-13 15:07:27', null);
INSERT INTO `sys_dict_item` VALUES ('111197', 'recommend_pos_type', '学校', '4', '0', null, null, '2021-05-13 15:07:43', null, '2021-05-13 15:07:43', null);
INSERT INTO `sys_dict_item` VALUES ('111198', 'wallet_type', '学员', '1', '0', null, null, '2021-05-17 15:59:18', null, '2021-05-17 15:59:18', null);
INSERT INTO `sys_dict_item` VALUES ('111199', 'wallet_type', '教练', '2', '0', null, null, '2021-05-17 15:59:29', null, '2021-05-17 15:59:29', null);
INSERT INTO `sys_dict_item` VALUES ('111200', 'wallet_type', '运维', '3', '0', null, null, '2021-05-17 15:59:47', null, '2021-05-17 15:59:47', null);
INSERT INTO `sys_dict_item` VALUES ('111202', 'wallet_type', '平台支付宝', '4', '0', null, null, '2021-05-17 16:01:01', null, '2021-05-17 16:01:01', null);
INSERT INTO `sys_dict_item` VALUES ('111203', 'wallet_type', '平台微信', '5', '0', null, null, '2021-05-17 16:01:10', null, '2021-05-17 16:01:10', null);
INSERT INTO `sys_dict_item` VALUES ('111204', 'wallet_type', '驾校', '6', '0', null, null, '2021-05-17 16:01:23', null, '2021-05-17 16:01:23', null);
INSERT INTO `sys_dict_item` VALUES ('111205', 'wallet_type', '运营商', '7', '0', null, null, '2021-05-17 16:01:32', null, '2021-05-17 16:01:32', null);
INSERT INTO `sys_dict_item` VALUES ('111206', 'user_type', '管理员', '1', '0', null, null, '2021-05-17 16:56:33', null, '2021-05-17 16:56:33', null);
INSERT INTO `sys_dict_item` VALUES ('111207', 'user_type', '普通用户', '2', '0', null, null, '2021-05-17 16:56:41', null, '2021-05-17 16:56:41', null);
INSERT INTO `sys_dict_item` VALUES ('111210', 'trade_status', '失败', '0', '0', null, null, '2021-05-18 14:22:38', null, '2021-05-18 14:22:38', null);
INSERT INTO `sys_dict_item` VALUES ('111211', 'trade_status', '成功', '1', '0', null, null, '2021-05-18 14:22:46', null, '2021-05-18 14:22:46', null);
INSERT INTO `sys_dict_item` VALUES ('111212', 'field_type', '常规场地', '1', '0', null, null, '2021-05-18 16:17:31', null, '2021-05-18 16:17:31', null);
INSERT INTO `sys_dict_item` VALUES ('111213', 'field_type', '考试场地', '2', '0', null, null, '2021-05-18 16:17:43', null, '2021-05-18 16:17:43', null);
INSERT INTO `sys_dict_item` VALUES ('111214', 'apply_status', '预约成功', '1', '0', null, null, '2021-05-19 11:52:38', null, '2021-05-19 11:52:38', null);
INSERT INTO `sys_dict_item` VALUES ('111215', 'apply_status', '预约失败', '2', '0', null, null, '2021-05-19 11:52:48', null, '2021-05-19 11:52:48', null);
INSERT INTO `sys_dict_item` VALUES ('111216', 'apply_status', '预约取消', '3', '0', null, null, '2021-05-19 11:53:01', null, '2021-05-19 11:53:01', null);
INSERT INTO `sys_dict_item` VALUES ('111217', 'apply_status', '练车中', '4', '0', null, null, '2021-05-19 11:53:18', null, '2021-05-19 11:53:18', null);
INSERT INTO `sys_dict_item` VALUES ('111218', 'apply_status', '练车完成', '5', '0', null, null, '2021-05-19 11:53:36', null, '2021-05-19 11:53:36', null);
INSERT INTO `sys_dict_item` VALUES ('111219', 'apply_status', '接您中', '6', '0', null, null, '2021-05-19 11:53:56', null, '2021-05-19 11:53:56', null);
INSERT INTO `sys_dict_item` VALUES ('111220', 'apply_status', '已上车', '7', '0', null, null, '2021-05-19 11:54:16', null, '2021-05-19 11:54:16', null);
INSERT INTO `sys_dict_item` VALUES ('111221', 'apply_status', '待支付', '0', '0', null, null, '2021-05-19 11:54:42', null, '2021-05-19 11:54:42', null);
INSERT INTO `sys_dict_item` VALUES ('111222', 'apply_status', '超时取消', '8', '0', null, null, '2021-05-19 11:55:05', null, '2021-05-19 11:55:05', null);
INSERT INTO `sys_dict_item` VALUES ('111223', 'recommend_user_type', '学员', '1', '0', null, null, '2021-05-20 13:56:54', null, '2021-05-20 13:56:54', null);
INSERT INTO `sys_dict_item` VALUES ('111224', 'recommend_user_type', '教练', '2', '0', null, null, '2021-05-20 13:57:02', null, '2021-05-20 13:57:02', null);
INSERT INTO `sys_dict_item` VALUES ('111225', 'recommend_user_type', '运维', '3', '0', null, null, '2021-05-20 13:57:07', null, '2021-05-20 13:57:07', null);
INSERT INTO `sys_dict_item` VALUES ('111229', 'app_type', '学员端', '1', '0', null, null, '2021-05-22 13:27:28', null, '2021-05-22 13:27:28', null);
INSERT INTO `sys_dict_item` VALUES ('111231', 'app_type', '教练端', '2', '0', null, null, '2021-05-22 13:27:55', null, '2021-05-22 13:27:55', null);
INSERT INTO `sys_dict_item` VALUES ('111232', 'app_type', '客服端', '3', '0', null, null, '2021-05-22 13:28:13', null, '2021-05-22 13:28:13', null);
INSERT INTO `sys_dict_item` VALUES ('111233', 'app_type', '驾校端', '4', '0', null, null, '2021-05-22 13:28:27', null, '2021-05-22 13:28:27', null);
INSERT INTO `sys_dict_item` VALUES ('111234', 'version_type', 'android', 'android', '0', null, null, '2021-05-22 14:37:22', null, '2021-05-22 14:37:22', null);
INSERT INTO `sys_dict_item` VALUES ('111235', 'version_type', 'ios', 'ios', '0', null, null, '2021-05-22 14:37:30', null, '2021-05-22 14:37:30', null);
INSERT INTO `sys_dict_item` VALUES ('111236', 'address_user_type', '学员', '1', '0', null, null, '2021-05-22 15:42:01', null, '2021-05-22 15:42:01', null);
INSERT INTO `sys_dict_item` VALUES ('111237', 'address_user_type', '教练', '2', '0', null, null, '2021-05-22 15:42:08', null, '2021-05-22 15:42:08', null);
INSERT INTO `sys_dict_item` VALUES ('111238', 'address_user_type', '客服', '3', '0', null, null, '2021-05-22 15:42:14', null, '2021-05-22 15:42:14', null);
INSERT INTO `sys_dict_item` VALUES ('111239', 'stream_type', '支付流水', '1', '0', null, null, '2021-05-25 10:47:19', null, '2021-05-25 10:47:19', null);
INSERT INTO `sys_dict_item` VALUES ('111240', 'stream_type', '退款流水', '2', '0', null, null, '2021-05-25 10:47:25', null, '2021-05-25 10:47:25', null);
INSERT INTO `sys_dict_item` VALUES ('111241', 'stream_type', '取消收入流水', '3', '0', null, null, '2021-05-25 10:47:31', null, '2021-05-25 10:47:31', null);
INSERT INTO `sys_dict_item` VALUES ('111242', 'stream_type', '取消扣款流水', '4', '0', null, null, '2021-05-25 10:47:37', null, '2021-05-25 10:47:37', null);
INSERT INTO `sys_dict_item` VALUES ('111243', 'stream_type', '优惠卷扣款流水', '5', '0', null, null, '2021-05-25 10:47:43', null, '2021-05-25 10:47:43', null);
INSERT INTO `sys_dict_item` VALUES ('111244', 'stream_type', '平台支付流水', '6', '0', null, null, '2021-05-25 10:47:49', null, '2021-05-25 10:47:49', null);
INSERT INTO `sys_dict_item` VALUES ('111245', 'business_flow_status', '待付款', '1', '0', null, null, '2021-05-25 10:53:23', null, '2021-05-25 10:53:23', null);
INSERT INTO `sys_dict_item` VALUES ('111246', 'business_flow_status', '付款处理中', '2', '0', null, null, '2021-05-25 10:53:29', null, '2021-05-25 10:53:29', null);
INSERT INTO `sys_dict_item` VALUES ('111247', 'business_flow_status', '付款成功', '3', '0', null, null, '2021-05-25 10:53:37', null, '2021-05-25 10:53:37', null);
INSERT INTO `sys_dict_item` VALUES ('111248', 'business_flow_status', '付款失败', '4', '0', null, null, '2021-05-25 10:53:45', null, '2021-05-25 10:53:45', null);
INSERT INTO `sys_dict_item` VALUES ('111249', 'business_flow_status', '退款处理中', '5', '0', null, null, '2021-05-25 10:53:51', null, '2021-05-25 10:53:51', null);
INSERT INTO `sys_dict_item` VALUES ('111250', 'business_flow_status', '退款成功', '6', '0', null, null, '2021-05-25 10:53:58', null, '2021-05-25 10:53:58', null);
INSERT INTO `sys_dict_item` VALUES ('111251', 'business_flow_status', '交易结束不可退款', '7', '0', null, null, '2021-05-25 10:54:07', null, '2021-05-25 10:54:07', null);
INSERT INTO `sys_dict_item` VALUES ('111252', 'business_flow_status', '退款失败', '8', '0', null, null, '2021-05-25 10:54:21', null, '2021-05-25 10:54:21', null);
INSERT INTO `sys_dict_item` VALUES ('111253', 'income_user_type', '学员', '1', '0', null, null, '2021-05-25 16:25:13', null, '2021-05-25 16:25:13', null);
INSERT INTO `sys_dict_item` VALUES ('111254', 'income_user_type', '教练', '2', '0', null, null, '2021-05-25 16:25:18', null, '2021-05-25 16:25:18', null);
INSERT INTO `sys_dict_item` VALUES ('111255', 'income_user_type', '运维', '3', '0', null, null, '2021-05-25 16:25:26', null, '2021-05-25 16:25:26', null);
INSERT INTO `sys_dict_item` VALUES ('111256', 'income_user_type', '平台支付宝', '4', '0', null, null, '2021-05-25 16:25:35', null, '2021-05-25 16:25:35', null);
INSERT INTO `sys_dict_item` VALUES ('111257', 'income_user_type', '平台微信', '5', '0', null, null, '2021-05-25 16:25:42', null, '2021-05-25 16:25:42', null);
INSERT INTO `sys_dict_item` VALUES ('111258', 'income_user_type', '驾校', '6', '0', null, null, '2021-05-25 16:25:49', null, '2021-05-25 16:25:49', null);
INSERT INTO `sys_dict_item` VALUES ('111259', 'input_log_type', '不记入日志', '0', '0', null, null, '2021-05-25 16:59:21', null, '2021-05-25 16:59:21', null);
INSERT INTO `sys_dict_item` VALUES ('111260', 'income_user_type', '运营商', '7', '0', null, null, '2021-05-25 17:00:12', null, '2021-05-25 17:00:12', null);
INSERT INTO `sys_dict_item` VALUES ('111261', 'input_log_type', '记入日志', '1', '0', null, null, '2021-05-25 17:00:26', null, '2021-05-25 17:00:26', null);
INSERT INTO `sys_dict_item` VALUES ('111262', 'input_log_type', '发生错误时记录日志', '2', '0', null, null, '2021-05-25 17:00:47', null, '2021-05-25 17:00:47', null);
INSERT INTO `sys_dict_item` VALUES ('111309', 'trade_type', 'VIP包过奖金扣款提成', '010307', '0', null, null, '2021-05-25 17:25:57', null, '2021-05-25 17:25:57', null);
INSERT INTO `sys_dict_item` VALUES ('111310', 'trade_type', 'VIP包过奖金', '010306', '0', null, null, '2021-05-25 17:25:57', null, '2021-05-25 17:25:57', null);
INSERT INTO `sys_dict_item` VALUES ('111311', 'trade_type', '提现', '020501', '0', null, null, '2021-05-25 17:25:57', null, '2021-05-25 17:25:57', null);
INSERT INTO `sys_dict_item` VALUES ('111312', 'trade_type', '提现', '0205', '0', null, null, '2021-05-25 17:25:57', null, '2021-05-25 17:25:57', null);
INSERT INTO `sys_dict_item` VALUES ('111313', 'trade_type', '优惠卷补贴支出', '020406', '0', null, null, '2021-05-25 17:25:57', null, '2021-05-25 17:25:57', null);
INSERT INTO `sys_dict_item` VALUES ('111314', 'trade_type', '优惠卷补贴收入', '010506', '0', null, null, '2021-05-25 17:25:57', null, '2021-05-25 17:25:57', null);
INSERT INTO `sys_dict_item` VALUES ('111315', 'trade_type', '课时大礼包收入', '010505', '0', null, null, '2021-05-25 17:25:57', null, '2021-05-25 17:25:57', null);
INSERT INTO `sys_dict_item` VALUES ('111316', 'trade_type', '论坛首次发帖奖励支出', '020405', '0', null, null, '2021-05-25 17:25:57', null, '2021-05-25 17:25:57', null);
INSERT INTO `sys_dict_item` VALUES ('111317', 'trade_type', '优惠卷支出', '020404', '0', null, null, '2021-05-25 17:25:57', null, '2021-05-25 17:25:57', null);
INSERT INTO `sys_dict_item` VALUES ('111318', 'trade_type', '推荐用户报名课时提成支出', '020403', '0', null, null, '2021-05-25 17:25:57', null, '2021-05-25 17:25:57', null);
INSERT INTO `sys_dict_item` VALUES ('111319', 'trade_type', '推荐用户报名推荐费用支出', '020402', '0', null, null, '2021-05-25 17:25:57', null, '2021-05-25 17:25:57', null);
INSERT INTO `sys_dict_item` VALUES ('111320', 'trade_type', '推荐新用户下载佣金支出', '020401', '0', null, null, '2021-05-25 17:25:57', null, '2021-05-25 17:25:57', null);
INSERT INTO `sys_dict_item` VALUES ('111321', 'trade_type', '课时绩效支出', '020305', '0', null, null, '2021-05-25 17:25:57', null, '2021-05-25 17:25:57', null);
INSERT INTO `sys_dict_item` VALUES ('111322', 'trade_type', '课时取消扣款支出', '020304', '0', null, null, '2021-05-25 17:25:57', null, '2021-05-25 17:25:57', null);
INSERT INTO `sys_dict_item` VALUES ('111323', 'trade_type', '课时取消退款支出', '020303', '0', null, null, '2021-05-25 17:25:57', null, '2021-05-25 17:25:57', null);
INSERT INTO `sys_dict_item` VALUES ('111324', 'trade_type', '课时驾校提成支出', '020302', '0', null, null, '2021-05-25 17:25:57', null, '2021-05-25 17:25:57', null);
INSERT INTO `sys_dict_item` VALUES ('111325', 'trade_type', '课时教练提成支出', '020301', '0', null, null, '2021-05-25 17:25:57', null, '2021-05-25 17:25:57', null);
INSERT INTO `sys_dict_item` VALUES ('111326', 'trade_type', '考试报名费支出', '020201', '0', null, null, '2021-05-25 17:25:57', null, '2021-05-25 17:25:57', null);
INSERT INTO `sys_dict_item` VALUES ('111327', 'trade_type', '驾校提成支出', '020101', '0', null, null, '2021-05-25 17:25:57', null, '2021-05-25 17:25:57', null);
INSERT INTO `sys_dict_item` VALUES ('111328', 'trade_type', '市场推广支出', '0204', '0', null, null, '2021-05-25 17:25:57', null, '2021-05-25 17:25:57', null);
INSERT INTO `sys_dict_item` VALUES ('111329', 'trade_type', '课时支出', '0203', '0', null, null, '2021-05-25 17:25:57', null, '2021-05-25 17:25:57', null);
INSERT INTO `sys_dict_item` VALUES ('111330', 'trade_type', '考试支出', '0202', '0', null, null, '2021-05-25 17:25:57', null, '2021-05-25 17:25:57', null);
INSERT INTO `sys_dict_item` VALUES ('111331', 'trade_type', '报名支出', '0201', '0', null, null, '2021-05-25 17:25:57', null, '2021-05-25 17:25:57', null);
INSERT INTO `sys_dict_item` VALUES ('111332', 'trade_type', '推荐用户报名课时提成收入', '010504', '0', null, null, '2021-05-25 17:25:57', null, '2021-05-25 17:25:57', null);
INSERT INTO `sys_dict_item` VALUES ('111333', 'trade_type', '推荐新用户报名佣金收益', '010503', '0', null, null, '2021-05-25 17:25:57', null, '2021-05-25 17:25:57', null);
INSERT INTO `sys_dict_item` VALUES ('111334', 'trade_type', '填写邀请码收入', '010502', '0', null, null, '2021-05-25 17:25:57', null, '2021-05-25 17:25:57', null);
INSERT INTO `sys_dict_item` VALUES ('111335', 'trade_type', '推荐新用户收入', '010501', '0', null, null, '2021-05-25 17:25:57', null, '2021-05-25 17:25:57', null);
INSERT INTO `sys_dict_item` VALUES ('111336', 'trade_type', '提现失败退还收益', '010401', '0', null, null, '2021-05-25 17:25:57', null, '2021-05-25 17:25:57', null);
INSERT INTO `sys_dict_item` VALUES ('111337', 'trade_type', '课时绩效提成', '010305', '0', null, null, '2021-05-25 17:25:57', null, '2021-05-25 17:25:57', null);
INSERT INTO `sys_dict_item` VALUES ('111338', 'trade_type', '课时取消订单提成', '010304', '0', null, null, '2021-05-25 17:25:57', null, '2021-05-25 17:25:57', null);
INSERT INTO `sys_dict_item` VALUES ('111339', 'trade_type', '考试车费用提成', '010303', '0', null, null, '2021-05-25 17:25:57', null, '2021-05-25 17:25:57', null);
INSERT INTO `sys_dict_item` VALUES ('111340', 'trade_type', '考试场地费提成', '010302', '0', null, null, '2021-05-25 17:25:57', null, '2021-05-25 17:25:57', null);
INSERT INTO `sys_dict_item` VALUES ('111341', 'trade_type', '课时费提成', '010301', '0', null, null, '2021-05-25 17:25:57', null, '2021-05-25 17:25:57', null);
INSERT INTO `sys_dict_item` VALUES ('111342', 'trade_type', '考试接送费提成', '010202', '0', null, null, '2021-05-25 17:25:57', null, '2021-05-25 17:25:57', null);
INSERT INTO `sys_dict_item` VALUES ('111343', 'trade_type', '考试费提成', '010201', '0', null, null, '2021-05-25 17:25:57', null, '2021-05-25 17:25:57', null);
INSERT INTO `sys_dict_item` VALUES ('111344', 'trade_type', '报名费提成', '010101', '0', null, null, '2021-05-25 17:25:57', null, '2021-05-25 17:25:57', null);
INSERT INTO `sys_dict_item` VALUES ('111345', 'trade_type', '市场推广收入', '0105', '0', null, null, '2021-05-25 17:25:57', null, '2021-05-25 17:25:57', null);
INSERT INTO `sys_dict_item` VALUES ('111346', 'trade_type', '其他收益', '0104', '0', null, null, '2021-05-25 17:25:57', null, '2021-05-25 17:25:57', null);
INSERT INTO `sys_dict_item` VALUES ('111347', 'trade_type', '课时收益', '0103', '0', null, null, '2021-05-25 17:25:57', null, '2021-05-25 17:25:57', null);
INSERT INTO `sys_dict_item` VALUES ('111348', 'trade_type', '考试收益', '0102', '0', null, null, '2021-05-25 17:25:57', null, '2021-05-25 17:25:57', null);
INSERT INTO `sys_dict_item` VALUES ('111349', 'trade_type', '报名收益', '0101', '0', null, null, '2021-05-25 17:25:57', null, '2021-05-25 17:25:57', null);
INSERT INTO `sys_dict_item` VALUES ('111350', 'trade_type', '提现', '3', '0', null, null, '2021-05-25 17:25:57', null, '2021-05-25 17:25:57', null);
INSERT INTO `sys_dict_item` VALUES ('111351', 'trade_type', '支出', '2', '0', null, null, '2021-05-25 17:25:57', null, '2021-05-25 17:25:57', null);
INSERT INTO `sys_dict_item` VALUES ('111352', 'trade_type', '收益', '1', '0', null, null, '2021-05-25 17:25:57', null, '2021-05-25 17:25:57', null);
INSERT INTO `sys_dict_item` VALUES ('111353', 'trade_type', '交易类型', '', '0', null, null, '2021-05-25 17:25:57', null, '2021-05-25 17:25:57', null);
INSERT INTO `sys_dict_item` VALUES ('111354', 'liquidation_status', '待清算', '1', '0', null, null, '2021-05-26 14:51:48', null, '2021-05-26 14:51:48', null);
INSERT INTO `sys_dict_item` VALUES ('111355', 'liquidation_status', '清算成功', '2', '0', null, null, '2021-05-26 14:51:57', null, '2021-05-26 14:51:57', null);
INSERT INTO `sys_dict_item` VALUES ('111356', 'liquidation_status', '清算失败', '3', '0', null, null, '2021-05-26 14:52:07', null, '2021-05-26 14:52:07', null);
INSERT INTO `sys_dict_item` VALUES ('111357', 'liquidation_status', '待审核', '0', '0', null, null, '2021-05-26 14:52:22', null, '2021-05-26 14:52:22', null);
INSERT INTO `sys_dict_item` VALUES ('111358', 'liquidation_status', '非法操作', '4', '0', null, null, '2021-05-26 14:52:27', null, '2021-05-26 14:52:27', null);
INSERT INTO `sys_dict_item` VALUES ('111359', 'withdraw_type', '支付宝', '1', '0', null, null, '2021-05-26 15:05:52', null, '2021-05-26 15:05:52', null);
INSERT INTO `sys_dict_item` VALUES ('111360', 'withdraw_type', '微信', '2', '0', null, null, '2021-05-26 15:05:58', null, '2021-05-26 15:05:58', null);
INSERT INTO `sys_dict_item` VALUES ('111361', 'withdraw_type', '银行卡', '3', '0', null, null, '2021-05-26 15:06:03', null, '2021-05-26 15:06:03', null);
INSERT INTO `sys_dict_item` VALUES ('111363', 'is_exclusive', '独享', '1', '0', '独享', null, '2021-05-28 18:54:16', null, '2021-05-28 18:54:16', null);
INSERT INTO `sys_dict_item` VALUES ('111364', 'is_exclusive', '非独享', '2', '0', '非独享', null, '2021-05-28 18:54:38', null, '2021-05-28 18:54:38', null);
INSERT INTO `sys_dict_item` VALUES ('111365', 'question_type', '争议题', '20', '0', null, null, '2021-05-31 10:05:54', null, '2021-05-31 10:05:54', null);
INSERT INTO `sys_dict_item` VALUES ('111366', 'question_type', '易错题', '19', '0', null, null, '2021-05-31 10:05:54', null, '2021-05-31 10:05:54', null);
INSERT INTO `sys_dict_item` VALUES ('111367', 'question_type', '仪表题', '18', '0', null, null, '2021-05-31 10:05:54', null, '2021-05-31 10:05:54', null);
INSERT INTO `sys_dict_item` VALUES ('111368', 'question_type', '路况题', '17', '0', null, null, '2021-05-31 10:05:54', null, '2021-05-31 10:05:54', null);
INSERT INTO `sys_dict_item` VALUES ('111369', 'question_type', '装置题', '16', '0', null, null, '2021-05-31 10:05:55', null, '2021-05-31 10:05:55', null);
INSERT INTO `sys_dict_item` VALUES ('111370', 'question_type', '灯光题', '15', '0', null, null, '2021-05-31 10:05:55', null, '2021-05-31 10:05:55', null);
INSERT INTO `sys_dict_item` VALUES ('111371', 'question_type', '标线题', '14', '0', null, null, '2021-05-31 10:05:55', null, '2021-05-31 10:05:55', null);
INSERT INTO `sys_dict_item` VALUES ('111372', 'question_type', '酒驾题', '13', '0', null, null, '2021-05-31 10:05:55', null, '2021-05-31 10:05:55', null);
INSERT INTO `sys_dict_item` VALUES ('111373', 'question_type', '记分题', '12', '0', null, null, '2021-05-31 10:05:55', null, '2021-05-31 10:05:55', null);
INSERT INTO `sys_dict_item` VALUES ('111374', 'question_type', '信号题', '11', '0', null, null, '2021-05-31 10:05:56', null, '2021-05-31 10:05:56', null);
INSERT INTO `sys_dict_item` VALUES ('111375', 'question_type', '手势题', '10', '0', null, null, '2021-05-31 10:05:56', null, '2021-05-31 10:05:56', null);
INSERT INTO `sys_dict_item` VALUES ('111376', 'question_type', '标志题', '9', '0', null, null, '2021-05-31 10:05:56', null, '2021-05-31 10:05:56', null);
INSERT INTO `sys_dict_item` VALUES ('111377', 'question_type', '罚款题', '8', '0', null, null, '2021-05-31 10:05:56', null, '2021-05-31 10:05:56', null);
INSERT INTO `sys_dict_item` VALUES ('111378', 'question_type', '距离题', '7', '0', null, null, '2021-05-31 10:05:56', null, '2021-05-31 10:05:56', null);
INSERT INTO `sys_dict_item` VALUES ('111379', 'question_type', '速度题', '6', '0', null, null, '2021-05-31 10:05:56', null, '2021-05-31 10:05:56', null);
INSERT INTO `sys_dict_item` VALUES ('111380', 'question_type', '时间题', '5', '0', null, null, '2021-05-31 10:05:56', null, '2021-05-31 10:05:56', null);
INSERT INTO `sys_dict_item` VALUES ('111381', 'question_type', '单选题', '4', '0', null, null, '2021-05-31 10:05:56', null, '2021-05-31 10:05:56', null);
INSERT INTO `sys_dict_item` VALUES ('111382', 'question_type', '判断题', '3', '0', null, null, '2021-05-31 10:05:56', null, '2021-05-31 10:05:56', null);
INSERT INTO `sys_dict_item` VALUES ('111383', 'question_type', '图片题', '2', '0', null, null, '2021-05-31 10:05:57', null, '2021-05-31 10:05:57', null);
INSERT INTO `sys_dict_item` VALUES ('111384', 'question_type', '文字题', '1', '0', null, null, '2021-05-31 10:05:57', null, '2021-05-31 10:05:57', null);
INSERT INTO `sys_dict_item` VALUES ('111386', 'chapter_type', '道路交通安全法律、法规和规章', '1', '0', null, null, '2021-06-01 14:37:56', null, '2021-06-01 14:37:56', null);
INSERT INTO `sys_dict_item` VALUES ('111387', 'chapter_type', '交通信号', '2', '0', null, null, '2021-06-01 14:38:08', null, '2021-06-01 14:38:08', null);
INSERT INTO `sys_dict_item` VALUES ('111388', 'chapter_type', '安全行车、文明驾驶基础知识', '3', '0', null, null, '2021-06-01 14:38:17', null, '2021-06-01 14:38:17', null);
INSERT INTO `sys_dict_item` VALUES ('111389', 'chapter_type', '机动车驾驶操作相关基础知识', '4', '0', null, null, '2021-06-01 14:38:25', null, '2021-06-01 14:38:25', null);

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type` (
  `dict_type_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典主键',
  `dict_name` varchar(100) DEFAULT '' COMMENT '字典名称',
  `dict_code` varchar(100) DEFAULT '' COMMENT '字典编码',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dict_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=98 DEFAULT CHARSET=utf8mb4 COMMENT='字典类型';

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
INSERT INTO `sys_dict_type` VALUES ('1', '用户性别', 'sys_user_sex', '0', null, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_dict_type` VALUES ('2', '用户初始密码', 'sys_user_initPassword', '0', null, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_dict_type` VALUES ('3', '菜单状态', 'sys_show_hide', '0', null, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_dict_type` VALUES ('4', '系统开关', 'sys_normal_disable', '0', null, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_dict_type` VALUES ('5', '登录状态', 'sys_common_status', '0', null, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_dict_type` VALUES ('6', '操作类型', 'sys_oper_type', '0', null, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_dict_type` VALUES ('7', '文章状态', 'article_state', '0', null, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_dict_type` VALUES ('8', '活动类型', 'activity_type', '0', 'activity_type', 'admin', '2021-01-13 10:04:42', 'admin', null);
INSERT INTO `sys_dict_type` VALUES ('9', '优惠券类型', 'coupon_type', '0', 'coupon_type', 'admin', '2021-01-13 10:04:42', 'admin', null);
INSERT INTO `sys_dict_type` VALUES ('10', '删除状态', 'delete_status', '0', 'delete_status-删除状态', 'admin', '2021-01-13 10:04:42', 'admin', null);
INSERT INTO `sys_dict_type` VALUES ('11', '优惠券使用状态', 'coupon_use_status', '0', null, 'admin', '2021-01-13 10:04:42', 'admin', null);
INSERT INTO `sys_dict_type` VALUES ('12', '服务项目类型', 'service_item_type', '0', '服务项目类型', 'admin', '2021-01-13 10:04:42', 'admin', null);
INSERT INTO `sys_dict_type` VALUES ('13', '启用状态', 'enable_status', '0', null, 'admin', '2021-01-13 10:04:42', 'admin', null);
INSERT INTO `sys_dict_type` VALUES ('14', '驾照类型', 'drive_type', '0', '驾照类型', 'admin', '2021-01-13 10:04:42', 'admin', null);
INSERT INTO `sys_dict_type` VALUES ('15', '一费制班型', 'class_type', '0', '一费制班型', 'admin', '2021-01-13 10:04:42', 'admin', null);
INSERT INTO `sys_dict_type` VALUES ('16', '启用状态（old）', 'enable_status_old', '0', '启用状态（old）', 'admin', '2021-01-13 10:04:42', 'admin', null);
INSERT INTO `sys_dict_type` VALUES ('17', '是否', 'yes_no', '0', '是否', 'admin', '2021-01-13 10:04:42', 'admin', null);
INSERT INTO `sys_dict_type` VALUES ('18', '服务班型状态', 'service_class_status', '0', '服务班型状态', null, '2021-01-13 18:15:22', null, '2021-01-13 18:15:22');
INSERT INTO `sys_dict_type` VALUES ('19', '班型服务项单位', 'class_service_unit', '0', '班型服务项单位', null, '2021-01-14 15:03:52', null, '2021-01-14 15:03:52');
INSERT INTO `sys_dict_type` VALUES ('20', '服务提供者', 'service_provide', '0', null, null, '2021-01-14 15:07:10', null, '2021-01-14 15:07:10');
INSERT INTO `sys_dict_type` VALUES ('21', '轮播图投放位置', 'slideshow_location', '0', '轮播图投放位置', null, '2021-01-14 17:01:01', null, '2021-01-14 17:01:01');
INSERT INTO `sys_dict_type` VALUES ('22', '学员信息状态（old）', 'student_enable_status_old', '0', null, null, '2021-01-28 17:03:36', null, '2021-01-28 17:03:36');
INSERT INTO `sys_dict_type` VALUES ('23', '推广类型', 'promotion_type', '0', '1：扫码 2：线上推广', null, '2021-01-28 17:39:00', null, '2021-01-28 17:39:00');
INSERT INTO `sys_dict_type` VALUES ('24', '科目类型', 'subject_type', '0', '科目1，科目2，科目3，科目4，科目2或3', null, '2021-01-30 15:37:11', null, '2021-01-30 15:37:11');
INSERT INTO `sys_dict_type` VALUES ('25', '客服类型', 'service_type', '0', null, null, '2021-01-30 17:23:04', null, '2021-01-30 17:23:04');
INSERT INTO `sys_dict_type` VALUES ('26', '学车报名状态', 'enroll_status', '0', '1-提交报名；2-已联系待支付；3-已支付待备案；5-支付失败 ;6-报名完成 ;7-报名取消;8-报名失败 ;9-已退款，10-自动报名完成，11-自动报名待审核，12-已备案待审核,13-密码已提交待审核）', null, '2021-02-01 10:25:27', null, '2021-02-01 10:25:27');
INSERT INTO `sys_dict_type` VALUES ('27', '性别（old）', 'sex_old', '0', '0女	\n1男', null, '2021-02-01 16:35:15', null, '2021-02-01 16:35:15');
INSERT INTO `sys_dict_type` VALUES ('28', '显示状态', 'is_show', '0', '显示状态', null, '2021-02-02 09:36:50', null, '2021-02-02 09:36:50');
INSERT INTO `sys_dict_type` VALUES ('29', '视频对外开放状态', 'is_open', '0', '视频对外开放状态：是否对外开放  0 否 1 是', null, '2021-02-02 10:39:38', null, '2021-02-02 10:39:38');
INSERT INTO `sys_dict_type` VALUES ('30', '视频来源', 'from_platform', '0', '视频来源： 0未知 1 安卓 2 iOS 3WEB 4微信 5PC WEB 6桌面版Linux 7桌面macOS 8桌面Windows', null, '2021-02-02 11:06:24', null, '2021-02-02 11:06:24');
INSERT INTO `sys_dict_type` VALUES ('31', '视频所属科目类型', 'video_type', '0', '视频所属科目类型', null, '2021-02-02 11:27:58', null, '2021-02-02 11:27:58');
INSERT INTO `sys_dict_type` VALUES ('32', '视频删除状态', 'video_delete_type', '0', '视频删除状态', null, '2021-02-02 11:37:07', null, '2021-02-02 11:37:07');
INSERT INTO `sys_dict_type` VALUES ('33', '视频发布状态', 'video_send_type', '0', '视频发布状态', null, '2021-02-02 11:40:08', null, '2021-02-02 11:40:08');
INSERT INTO `sys_dict_type` VALUES ('34', '教练状态', 'coach_type', '0', null, null, '2021-02-02 15:04:58', null, '2021-02-02 15:04:58');
INSERT INTO `sys_dict_type` VALUES ('35', '订单类型', 'order_type', '0', null, null, '2021-02-02 15:09:27', null, '2021-02-02 15:09:27');
INSERT INTO `sys_dict_type` VALUES ('36', '订单状态', 'order_status', '0', null, null, '2021-02-02 15:12:53', null, '2021-02-02 15:12:53');
INSERT INTO `sys_dict_type` VALUES ('37', '支付类型', 'pay_type', '0', null, null, '2021-02-02 15:14:39', null, '2021-02-02 15:14:39');
INSERT INTO `sys_dict_type` VALUES ('38', '考试报名状态', 'test_enroll_status', '0', null, null, '2021-02-03 09:59:22', null, '2021-02-03 09:59:22');
INSERT INTO `sys_dict_type` VALUES ('39', '车型', 'car_type', '0', '车型', null, '2021-02-03 11:24:01', null, '2021-02-03 11:24:01');
INSERT INTO `sys_dict_type` VALUES ('40', '自动发课', 'auto_send_class', '0', '自动发课', null, '2021-02-04 09:58:18', null, '2021-02-04 09:58:18');
INSERT INTO `sys_dict_type` VALUES ('41', '金牌教练', 'vip_coach', '0', '金牌教练', null, '2021-02-04 10:02:37', null, '2021-02-04 10:02:37');
INSERT INTO `sys_dict_type` VALUES ('42', '时间段状态', 'time_status', '0', '时间段状态', null, '2021-02-05 11:04:15', null, '2021-02-05 11:04:15');
INSERT INTO `sys_dict_type` VALUES ('43', 'vip教练状态', 'vip_coach_status', '0', 'vip教练状态', null, '2021-02-20 11:08:47', null, '2021-02-20 11:08:47');
INSERT INTO `sys_dict_type` VALUES ('44', '栏目状态', 'channel_status', '0', '栏目状态', null, '2021-02-22 11:35:20', null, '2021-02-22 11:35:20');
INSERT INTO `sys_dict_type` VALUES ('45', '栏目用户状态', 'channel_user_status', '0', '栏目用户状态', null, '2021-02-23 10:24:22', null, '2021-02-23 10:24:22');
INSERT INTO `sys_dict_type` VALUES ('46', '使用类型', 'use_type', '0', '使用类型', null, '2021-02-25 13:51:19', null, '2021-02-25 13:51:19');
INSERT INTO `sys_dict_type` VALUES ('47', '平台类型', 'platform_type', '0', '平台类型', null, '2021-02-25 14:08:21', null, '2021-02-25 14:08:21');
INSERT INTO `sys_dict_type` VALUES ('48', '项目状态', 'items_show', '0', '项目状态', null, '2021-02-25 18:26:46', null, '2021-02-25 18:26:46');
INSERT INTO `sys_dict_type` VALUES ('49', '是否可报名', 'is_enroll', '0', '是否可报名', null, '2021-02-26 11:25:27', null, '2021-02-26 11:25:27');
INSERT INTO `sys_dict_type` VALUES ('50', '驾校状态', 'school_status', '0', '驾校状态', null, '2021-02-26 11:29:54', null, '2021-02-26 11:29:54');
INSERT INTO `sys_dict_type` VALUES ('51', '是否独立结算', 'is_alone_settlement', '0', '是否独立结算', null, '2021-02-26 11:51:03', null, '2021-02-26 11:51:03');
INSERT INTO `sys_dict_type` VALUES ('52', '参与类型', 'participation_way', '0', '参与类型', null, '2021-03-01 10:41:03', null, '2021-03-01 10:41:03');
INSERT INTO `sys_dict_type` VALUES ('53', '评价分数', 'evaluate_grade', '0', '评价分数', null, '2021-03-06 11:48:18', null, '2021-03-06 11:48:18');
INSERT INTO `sys_dict_type` VALUES ('54', '评价类型', 'assessment_type', '0', null, null, '2021-03-10 09:38:35', null, '2021-03-10 09:38:35');
INSERT INTO `sys_dict_type` VALUES ('56', '被评价类型', 'to_be_assessment_type', '0', null, null, '2021-03-10 09:40:00', null, '2021-03-10 09:40:00');
INSERT INTO `sys_dict_type` VALUES ('57', '一费制教练绑定状态', 'coach_bind_status', '0', null, null, '2021-03-17 13:49:35', null, '2021-03-17 13:49:35');
INSERT INTO `sys_dict_type` VALUES ('58', '一费制教练学员表状态', 'one_fee_system_coach_student', '0', null, null, '2021-03-17 14:15:40', null, '2021-03-17 14:15:40');
INSERT INTO `sys_dict_type` VALUES ('60', '渠道类型', 'channel_type', '0', null, null, '2021-03-29 20:21:00', null, '2021-03-29 20:21:00');
INSERT INTO `sys_dict_type` VALUES ('62', '转化类型', 'conversion_type', '0', null, null, '2021-03-31 15:57:47', null, '2021-03-31 15:57:47');
INSERT INTO `sys_dict_type` VALUES ('63', '取消类型', 'cancel_type', '0', null, null, '2021-04-06 15:03:37', null, '2021-04-06 15:03:37');
INSERT INTO `sys_dict_type` VALUES ('64', '考试是否合格', 'student_test_pass', '0', null, null, '2021-04-08 18:43:30', null, '2021-04-08 18:43:30');
INSERT INTO `sys_dict_type` VALUES ('65', '训练类型', 'train_type', '0', null, null, '2021-04-09 16:03:07', null, '2021-04-09 16:03:07');
INSERT INTO `sys_dict_type` VALUES ('66', '练车类型', 'drive_class_type', '0', null, null, '2021-04-16 11:21:03', null, '2021-04-16 11:21:03');
INSERT INTO `sys_dict_type` VALUES ('67', '练车状态', 'drive_status', '0', null, null, '2021-04-16 11:22:06', null, '2021-04-16 11:22:06');
INSERT INTO `sys_dict_type` VALUES ('68', '科目类型选择框', 'subject_type_select', '0', null, null, '2021-05-10 14:21:02', null, '2021-05-10 14:21:02');
INSERT INTO `sys_dict_type` VALUES ('69', '价格类型', 'price_type', '0', null, null, '2021-05-10 18:34:02', null, '2021-05-10 18:34:02');
INSERT INTO `sys_dict_type` VALUES ('70', '教练课程状态', 'teach_course_type', '0', null, null, '2021-05-11 14:24:06', null, '2021-05-11 14:24:06');
INSERT INTO `sys_dict_type` VALUES ('71', '运营商状态', 'recommend_user_status', '0', null, null, '2021-05-12 10:12:29', null, '2021-05-12 10:12:29');
INSERT INTO `sys_dict_type` VALUES ('72', '支付状态', 'pay_status', '0', '支付状态', null, '2021-05-12 17:25:54', null, '2021-05-12 17:25:54');
INSERT INTO `sys_dict_type` VALUES ('74', '运营商基础配置类型', 'operator_settinng_type', '0', null, null, '2021-05-13 10:07:32', null, '2021-05-13 10:07:32');
INSERT INTO `sys_dict_type` VALUES ('75', '通用状态', 'general_status', '0', null, null, '2021-05-13 10:14:59', null, '2021-05-13 10:14:59');
INSERT INTO `sys_dict_type` VALUES ('77', '推广商类型', 'recommend_pos_type', '0', null, null, '2021-05-13 15:07:06', null, '2021-05-13 15:07:06');
INSERT INTO `sys_dict_type` VALUES ('78', '钱包类型', 'wallet_type', '0', null, null, '2021-05-17 15:58:08', null, '2021-05-17 15:58:08');
INSERT INTO `sys_dict_type` VALUES ('79', '用户类型', 'user_type', '0', null, null, '2021-05-17 16:49:30', null, '2021-05-17 16:49:30');
INSERT INTO `sys_dict_type` VALUES ('80', '交易类型', 'trade_type', '0', null, null, '2021-05-18 14:20:48', null, '2021-05-18 14:20:48');
INSERT INTO `sys_dict_type` VALUES ('81', '交易状态', 'trade_status', '0', null, null, '2021-05-18 14:22:15', null, '2021-05-18 14:22:15');
INSERT INTO `sys_dict_type` VALUES ('82', '场地类型', 'field_type', '0', null, null, '2021-05-18 16:15:36', null, '2021-05-18 16:15:36');
INSERT INTO `sys_dict_type` VALUES ('83', '学员学车预约管理', 'apply_status', '0', null, null, '2021-05-19 11:52:13', null, '2021-05-19 11:52:13');
INSERT INTO `sys_dict_type` VALUES ('85', '推荐用户类型', 'recommend_user_type', '0', null, null, '2021-05-20 13:54:27', null, '2021-05-20 13:54:27');
INSERT INTO `sys_dict_type` VALUES ('86', 'APP类型', 'app_type', '0', null, null, '2021-05-22 13:26:55', null, '2021-05-22 13:26:55');
INSERT INTO `sys_dict_type` VALUES ('87', 'APP版本类型', 'version_type', '0', null, null, '2021-05-22 14:36:37', null, '2021-05-22 14:36:37');
INSERT INTO `sys_dict_type` VALUES ('88', '地址用户类型', 'address_user_type', '0', null, null, '2021-05-22 15:41:46', null, '2021-05-22 15:41:46');
INSERT INTO `sys_dict_type` VALUES ('89', '流水类型', 'stream_type', '0', null, null, '2021-05-25 10:47:06', null, '2021-05-25 10:47:06');
INSERT INTO `sys_dict_type` VALUES ('90', '交易流水状态', 'business_flow_status', '0', null, null, '2021-05-25 10:52:37', null, '2021-05-25 10:52:37');
INSERT INTO `sys_dict_type` VALUES ('91', '受益人类型', 'income_user_type', '0', null, null, '2021-05-25 16:24:44', null, '2021-05-25 16:24:44');
INSERT INTO `sys_dict_type` VALUES ('92', '记入日志类型', 'input_log_type', '0', null, null, '2021-05-25 16:58:59', null, '2021-05-25 16:58:59');
INSERT INTO `sys_dict_type` VALUES ('93', '清算类型', 'liquidation_status', '0', null, null, '2021-05-26 14:51:39', null, '2021-05-26 14:51:39');
INSERT INTO `sys_dict_type` VALUES ('94', '提现账号类型', 'withdraw_type', '0', null, null, '2021-05-26 15:05:45', null, '2021-05-26 15:05:45');
INSERT INTO `sys_dict_type` VALUES ('95', '是否渠道经理独享', 'is_exclusive', '0', '是否渠道经理独享', null, '2021-05-28 18:53:46', null, '2021-05-28 18:53:46');
INSERT INTO `sys_dict_type` VALUES ('96', '题型', 'question_type', '0', null, null, '2021-05-31 09:55:49', null, '2021-05-31 09:55:49');
INSERT INTO `sys_dict_type` VALUES ('97', '章节类型', 'chapter_type', '0', null, null, '2021-06-01 14:37:44', null, '2021-06-01 14:37:44');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) NOT NULL COMMENT '菜单名称',
  `menu_perms` varchar(100) DEFAULT NULL COMMENT '权限标识',
  `menu_type` int(1) DEFAULT NULL COMMENT '菜单类型（1-目录 2-菜单 3-按钮）',
  `router_path` varchar(200) DEFAULT '' COMMENT '路由地址',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父菜单ID',
  `component` varchar(255) DEFAULT NULL COMMENT '组件路径',
  `is_link` int(1) DEFAULT '1' COMMENT '是否为外链（0是 1否）',
  `visible` char(1) DEFAULT '0' COMMENT '显示状态（0显示 1隐藏）',
  `status` char(1) DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
  `icon` varchar(100) DEFAULT '#' COMMENT '菜单图标',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `order_num` int(4) DEFAULT '0' COMMENT '排序',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=201418 DEFAULT CHARSET=utf8mb4 COMMENT='菜单权限';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '系统管理', '', '1', 'system', '0', null, '1', '0', '0', 'system', null, '1', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES ('2', '系统监控', '', '1', 'monitor', '0', null, '1', '0', '0', 'monitor', null, '2', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES ('1001', '用户管理', 'system:user:list', '2', 'user', '1', 'system/user/index', '1', '0', '0', 'user', null, '1', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES ('1002', '角色管理', 'system:role:list', '2', 'role', '1', 'system/role/index', '1', '0', '0', 'peoples', null, '2', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES ('1003', '菜单管理', 'system:menu:list', '2', 'menu', '1', 'system/menu/index', '1', '0', '0', 'tree-table', null, '3', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES ('1004', '部门管理', 'system:dept:list', '2', 'dept', '1', 'system/dept/index', '1', '0', '0', 'tree', null, '4', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES ('1005', '岗位管理', 'system:post:list', '2', 'post', '1', 'system/post/index', '1', '0', '0', 'post', null, '5', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES ('1006', '字典管理', 'system:dict:list', '2', 'dict', '1', 'system/dict/index', '1', '0', '0', 'dict', null, '6', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES ('1007', '终端管理', 'system:client:list', '2', 'client', '1', 'system/client/index', '1', '0', '0', 'server', null, '7', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES ('2001', '登录日志', 'monitor:loginlog:list', '2', 'loginlog', '2', 'monitor/loginlog/index', '1', '0', '0', 'form', null, '1', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES ('2002', '操作日志', 'monitor:operlog:list', '2', 'operlog', '2', 'monitor/operlog/index', '1', '0', '0', 'logininfor', null, '2', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES ('2004', '接口文档', 'monitor:swagger:list', '2', 'swagger', '2', 'monitor/swagger/index', '1', '0', '0', 'swagger', null, '4', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES ('100101', '用户查询', 'system:user:query', '3', '', '1001', '', '1', '0', '0', '#', null, '1', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES ('100102', '用户新增', 'system:user:add', '3', '', '1001', '', '1', '0', '0', '#', null, '2', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES ('100103', '用户修改', 'system:user:edit', '3', '', '1001', '', '1', '0', '0', '#', null, '3', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES ('100104', '用户删除', 'system:user:delete', '3', '', '1001', '', '1', '0', '0', '#', null, '4', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES ('100105', '用户导出', 'system:user:export', '3', '', '1001', '', '1', '0', '0', '#', null, '5', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES ('100201', '角色查询', 'system:role:query', '3', '', '1002', '', '1', '0', '0', '#', null, '1', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES ('100202', '角色新增', 'system:role:add', '3', '', '1002', '', '1', '0', '0', '#', null, '2', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES ('100203', '角色修改', 'system:role:edit', '3', '', '1002', '', '1', '0', '0', '#', null, '3', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES ('100204', '角色删除', 'system:role:delete', '3', '', '1002', '', '1', '0', '0', '#', null, '4', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES ('100205', '角色导出', 'system:role:export', '3', '', '1002', '', '1', '0', '0', '#', null, '5', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES ('100301', '菜单查询', 'system:menu:query', '3', '', '1003', '', '1', '0', '0', '#', null, '1', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES ('100302', '菜单新增', 'system:menu:add', '3', '', '1003', '', '1', '0', '0', '#', null, '2', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES ('100303', '菜单修改', 'system:menu:edit', '3', '', '1003', '', '1', '0', '0', '#', null, '3', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES ('100304', '菜单删除', 'system:menu:delete', '3', '', '1003', '', '1', '0', '0', '#', null, '4', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES ('100305', '菜单导出', 'system:menu:export', '3', '', '1003', '', '1', '0', '0', '#', null, '5', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES ('100401', '部门查询', 'system:dept:query', '3', '', '1004', '', '1', '0', '0', '#', null, '1', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES ('100402', '部门新增', 'system:dept:add', '3', '', '1004', '', '1', '0', '0', '#', null, '2', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES ('100403', '部门修改', 'system:dept:edit', '3', '', '1004', '', '1', '0', '0', '#', null, '3', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES ('100404', '部门删除', 'system:dept:delete', '3', '', '1004', '', '1', '0', '0', '#', null, '4', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES ('100405', '部门导出', 'system:dept:export', '3', '', '1004', '', '1', '0', '0', '#', null, '5', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES ('100501', '岗位查询', 'system:post:query', '3', '', '1005', '', '1', '0', '0', '#', null, '1', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES ('100502', '岗位新增', 'system:post:add', '3', '', '1005', '', '1', '0', '0', '#', null, '2', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES ('100503', '岗位修改', 'system:post:edit', '3', '', '1005', '', '1', '0', '0', '#', null, '3', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES ('100504', '岗位删除', 'system:post:delete', '3', '', '1005', '', '1', '0', '0', '#', null, '4', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES ('100505', '岗位导出', 'system:post:export', '3', '', '1005', '', '1', '0', '0', '#', null, '5', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES ('100601', '字典查询', 'system:dict:query', '3', '', '1006', '', '1', '0', '0', '#', null, '1', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES ('100602', '字典新增', 'system:dictt:add', '3', '', '1006', '', '1', '0', '0', '#', null, '2', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES ('100603', '字典修改', 'system:dict:edit', '3', '', '1006', '', '1', '0', '0', '#', null, '3', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES ('100604', '字典删除', 'system:dict:delete', '3', '', '1006', '', '1', '0', '0', '#', null, '4', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES ('100605', '字典导出', 'system:dict:export', '3', '', '1006', '', '1', '0', '0', '#', null, '5', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES ('100701', '终端查询', 'system:client:query', '3', '', '1007', '', '1', '0', '0', '#', null, '1', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES ('100702', '终端新增', 'system:client:add', '3', '', '1007', '', '1', '0', '0', '#', null, '1', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES ('100703', '终端修改', 'system:client:edit', '3', '', '1007', '', '1', '0', '0', '#', null, '1', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES ('100704', '终端删除', 'system:client:delete', '3', '', '1007', '', '1', '0', '0', '#', null, '1', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES ('200101', '登录日志查询', 'monitor:loginlog:query', '3', '', '2001', '', '1', '0', '0', '#', null, '2', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES ('200102', '登录日志新增', 'monitor:loginlog:add', '3', '', '2001', '', '1', '0', '0', '#', null, '3', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES ('200103', '登录日志修改', 'monitor:loginlog:edit', '3', '', '2001', '', '1', '0', '0', '#', null, '3', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES ('200104', '登录日志删除', 'monitor:loginlog:delete', '3', '', '2001', '', '1', '0', '0', '#', null, '4', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES ('200105', '登录日志导出', 'monitor:loginlog:export', '3', '', '2001', '', '1', '0', '0', '#', null, '5', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES ('200201', '操作日志查询', 'monitor:operlog:query', '3', '', '2002', '', '1', '0', '0', '#', null, '2', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES ('200202', '操作日志新增', 'monitor:operlog:add', '3', '', '2002', '', '1', '0', '0', '#', null, '3', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES ('200203', '操作日志修改', 'monitor:operlog:edit', '3', '', '2002', '', '1', '0', '0', '#', null, '3', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES ('200204', '操作日志删除', 'monitor:operlog:delete', '3', '', '2002', '', '1', '0', '0', '#', null, '4', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES ('200205', '操作日志导出', 'monitor:operlog:export', '3', '', '2002', '', '1', '0', '0', '#', null, '5', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES ('200206', '促销管理', null, '1', 'promotion', '0', null, '1', '0', '0', 'drag', null, '3', 'admin', '2020-11-16 14:36:55', null, null);
INSERT INTO `sys_menu` VALUES ('200207', '促销设置', 'setting', '2', 'promotionSetting', '200206', 'promotion/promotionSetting/index', '1', '1', '0', 'system', null, '2', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('200208', '活动促销', 'activity:list', '2', 'activity', '200206', 'promotion/activity/activityList', '1', '0', '0', 'rate', null, '2', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('200209', '活动促销新增', 'activity:add', '2', 'addActivity_11', '200206', 'promotion/activity/addActivity', '1', '1', '0', 'cascader', null, '2', null, null, null, '2021-05-28 16:16:05');
INSERT INTO `sys_menu` VALUES ('200210', '推广人员设置', 'deductSetting', '2', 'deductSetting', '200206', 'promotion/activity/deductSetting', '1', '1', '0', 'select', null, '3', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('200211', '优惠券管理', 'coupon', '2', 'CouponList', '200206', 'promotion/coupon/couponList', '1', '0', '0', 'job', null, '1', 'admin', '2020-06-06 11:00:00', 'admin', '2020-06-06 11:00:00');
INSERT INTO `sys_menu` VALUES ('200212', '查询', 'marketing:coupon:query', '3', '', '200211', '#', '1', '0', '0', '#', null, '1', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES ('200213', '新增', 'marketing:coupon:add', '3', '', '200211', '#', '1', '0', '0', '#', null, '1', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES ('200214', '修改', 'marketing:coupon:edit', '3', '', '200211', '#', '1', '0', '0', '#', null, '1', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES ('200215', '删除', 'marketing:coupon:delete', '3', '', '200211', '#', '1', '0', '0', '#', null, '1', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES ('200216', '导出', 'marketing:coupon:export', '3', '', '200211', '#', '1', '0', '0', '#', null, '1', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES ('200217', '优惠券添加', 'promotion:couponAdd', '2', 'couponAdd', '200206', 'promotion/coupon/couponAdd', '1', '1', '0', 'cascader', null, '2', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('200218', '优惠券发劵', 'promotion:couponTemplate', '2', 'couponTemplate', '200206', 'promotion/coupon/couponTemplate', '1', '1', '0', 'component', null, '6', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('200219', '渠道经理设置', 'channelManagerSetting', '2', 'channelManagerSetting', '200206', 'promotion/activity/channelManagerSetting', '1', '1', '0', 'chart', null, '12', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('200220', '活动参加人员', 'activityApply', '2', 'activityApply', '200206', 'promotion/activity/activityApply', '1', '1', '0', 'date', null, '3', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('200221', '基础管理', null, '1', 'baiscs', '0', null, '1', '0', '0', 'education', null, '1', null, null, null, '2021-06-05 10:06:27');
INSERT INTO `sys_menu` VALUES ('200222', '运营商信息管理', 'operator', '2', 'operator', '200221', 'basics/operator/list', '1', '1', '0', 'color', null, '1', null, null, null, '2021-06-03 11:54:16');
INSERT INTO `sys_menu` VALUES ('200223', '班型设置', 'classTypeSeting', '2', 'classTypeSeting', '200206', 'promotion/activity/classTypeSeting', '1', '1', '0', 'user', null, '13', null, null, null, '2020-12-10 15:42:08');
INSERT INTO `sys_menu` VALUES ('200224', '设置优惠卷', 'couponSetting', '2', 'couponSetting', '200206', 'promotion/activity/couponSetting', '1', '1', '0', 'edit', null, '14', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('200226', '优惠券查询', 'activityRelevanceCoupon', '2', 'activityRelevanceCoupon', '200206', 'promotion/activity/activityRelevanceCoupon', '1', '1', '0', 'list', null, '15', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('200228', '推广商列表', 'activityRelevancePromoter', '2', 'activityRelevancePromoter', '200206', 'promotion/activity/activityRelevancePromoter', '1', '1', '0', 'nested', null, '16', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('200229', '班型服务管理', null, '1', 'past-service', '0', null, '1', '0', '0', 'example', null, '1', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('200231', '服务项项目管理', 'service-item:list', '2', 'pastService/serviceItem', '200229', 'pastService/serviceItem/index', '1', '0', '0', 'row', null, '1', null, null, null, '2021-01-21 21:10:29');
INSERT INTO `sys_menu` VALUES ('200232', '服务项目价格管理', 'service-item-price:list', '2', 'pastService/serviceItem/serviceItemPrice', '200229', 'pastService/serviceItem/serviceItemPrice/index', '1', '0', '0', 'row', null, '1', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('200233', '服务班型', 'serviceClass', '2', 'pastService/serviceItem/serviceClassList/index', '200229', 'pastService/serviceItem/serviceClassList/index', '1', '0', '0', 'education', null, '1', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('200234', '新增班型', 'serviceClassAdd', '2', '/serviceItem/serviceClassAdd', '200229', 'pastService/serviceItem/serviceClassAdd/index', '1', '1', '0', '#', null, '1', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('200235', '修改班型', 'serviceEdit', '2', '/serviceItem/serviceClassEdit', '200229', 'pastService/serviceItem/serviceClassAdd/index', '1', '1', '0', '#', null, '1', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('200238', '首页管理', 'home', '1', 'basics', '200221', 'home', '1', '1', '0', 'component', null, '2', null, '2021-01-15 07:16:58', null, '2021-01-15 07:16:58');
INSERT INTO `sys_menu` VALUES ('200239', '轮播管理', 'homeBanner', '2', 'homeBanner', '200221', 'homeBanner/index.vue', '1', '0', '0', 'education', null, '2', null, '2021-01-17 15:21:16', null, '2021-01-17 15:21:16');
INSERT INTO `sys_menu` VALUES ('200240', '栏目管理', 'basics:channel:pageList', '2', 'homeChannel', '200221', 'homeChannel/index.vue', '1', '0', '0', 'chart', null, '2', null, '2021-01-15 10:56:14', null, '2021-01-15 10:56:14');
INSERT INTO `sys_menu` VALUES ('200241', '视频管理', 'video', '2', 'basics/video', '200221', 'basics/video/index', '1', '0', '0', 'email', null, '2', null, '2021-01-15 04:23:34', null, '2021-01-15 04:23:34');
INSERT INTO `sys_menu` VALUES ('200242', '修改', 'service_change_class', '3', '', '200235', null, '1', '0', '0', '#', null, '1', null, '2021-01-22 09:58:58', null, '2021-01-22 09:58:58');
INSERT INTO `sys_menu` VALUES ('200243', '新增', 'service_add_class', '3', '', '200235', null, '1', '0', '0', '#', null, '1', null, '2021-01-22 10:08:44', null, '2021-01-22 10:08:44');
INSERT INTO `sys_menu` VALUES ('200244', '轮播添加', 'bannerAdd', '2', 'homeBannerAdd', '200221', 'homeBanner/add', '1', '1', '0', 'cascader', null, '2', null, '2021-01-28 13:47:41', null, '2021-01-28 13:47:41');
INSERT INTO `sys_menu` VALUES ('200245', '学员信息管理', '', '1', 'studentStudy', '0', 'studentInfo/studentStudyEnroll.vue', '1', '1', '0', 'logininfor', null, '4', null, '2021-01-29 15:36:10', null, '2021-01-29 15:36:10');
INSERT INTO `sys_menu` VALUES ('200246', '学员信息管理', 'studentInfo', '2', 'studentInfo', '200245', 'studentInfo/studentInfo.vue', '1', '1', '0', '#', null, '0', null, '2021-01-28 15:55:09', null, '2021-01-28 15:55:09');
INSERT INTO `sys_menu` VALUES ('200247', '学员学车报名单', 'admin:studentInfo:switch', '2', 'studentStudyEnroll', '200245', 'studentInfo/studentStudyEnroll/studentStudyEnroll.vue', '1', '1', '0', '#', null, '0', null, '2021-02-01 10:14:20', null, '2021-02-01 10:14:20');
INSERT INTO `sys_menu` VALUES ('200249', '客服管理', '', '1', 'serviceInfo', '0', '', '1', '0', '0', 'server', null, '5', null, '2021-02-01 14:03:50', null, '2021-02-01 14:03:50');
INSERT INTO `sys_menu` VALUES ('200250', '客服信息管理', 'serviceInfo', '2', 'serviceInfo', '200249', 'serviceInfo/serviceInfo.vue', '1', '0', '0', '#', null, '0', null, '2021-02-01 14:06:21', null, '2021-02-01 14:06:21');
INSERT INTO `sys_menu` VALUES ('200251', '新增客服', 'serviceInfoAdd', '2', 'serviceInfoAdd', '200249', 'serviceInfo/serviceInfoAdd.vue', '1', '1', '0', '#', null, '0', null, '2021-02-01 16:18:46', null, '2021-02-01 16:18:46');
INSERT INTO `sys_menu` VALUES ('200253', '切换客服', 'studentStudyEnrollSwitchService', '2', 'studentStudyEnrollSwitchService', '200245', 'studentInfo/studentStudyEnrollSwitchService.vue', '1', '1', '0', '#', null, '0', null, '2021-02-02 10:07:50', null, '2021-02-02 10:07:50');
INSERT INTO `sys_menu` VALUES ('200254', '栏目添加', 'addChannel', '2', 'homeChannel/add', '200221', 'homeChannel/add.vue', '1', '1', '0', 'cascader', null, '2', null, '2021-02-02 10:29:26', null, '2021-02-02 10:29:26');
INSERT INTO `sys_menu` VALUES ('200255', '教练信息管理', null, '1', 'coach', '0', null, '1', '0', '0', 'logininfor', null, '6', null, '2021-02-02 15:02:47', null, '2021-02-02 15:02:47');
INSERT INTO `sys_menu` VALUES ('200256', '学员订单管理', 'admin:studentOrder:pageList', '2', 'studentOrder', '200245', 'studentInfo/studentOrder/studentOrder.vue', '1', '1', '0', 'dict', null, '6', null, '2021-02-02 15:17:03', null, '2021-02-02 15:17:03');
INSERT INTO `sys_menu` VALUES ('200257', '学员考试报名单', 'studentTestEnroll/List', '2', '/student/studentTestEnroll', '200245', 'studentTestEnroll/index', '1', '1', '0', 'bug', null, '4', null, '2021-02-02 16:31:12', null, '2021-02-02 16:31:12');
INSERT INTO `sys_menu` VALUES ('200258', '学车考试添加', 'studentTestEnroll/Add', '2', 'studentTestEnroll/add', '200245', 'studentTestEnroll/add', '1', '1', '0', 'checkbox', null, '4', null, '2021-02-02 16:37:43', null, '2021-02-02 16:37:43');
INSERT INTO `sys_menu` VALUES ('200259', '学员订单修改', 'studentOrderModification', '2', 'studentOrderModification', '200245', 'studentInfo/studentOrder/studentOrderModification.vue', '1', '1', '0', '#', null, '12', null, '2021-02-02 17:02:09', null, '2021-02-02 17:02:09');
INSERT INTO `sys_menu` VALUES ('200260', '教练信息管理', 'coachInfo', '2', 'coachInfo', '200255', 'coachInfo/index', '0', '0', '0', 'client', null, '1', null, '2021-02-03 10:07:44', null, '2021-02-03 10:07:44');
INSERT INTO `sys_menu` VALUES ('200261', '运营商管理', null, '1', 'time', '0', null, '1', '0', '0', 'education', null, '1', null, '2021-02-05 10:16:46', null, '2021-02-05 10:16:46');
INSERT INTO `sys_menu` VALUES ('200262', '运营商时间段管理', 'areaTime', '2', 'areaTime', '200261', 'areaTime/index', '0', '0', '0', 'client', null, '1', null, '2021-02-05 10:19:10', null, '2021-02-05 10:19:10');
INSERT INTO `sys_menu` VALUES ('200263', '运营商时间段新增', 'addTime', '2', 'addTime', '200261', 'areaTime/add', '0', '1', '0', 'example', null, '1', null, '2021-02-05 11:18:50', null, '2021-02-05 11:18:50');
INSERT INTO `sys_menu` VALUES ('200264', '教练授课区域管理', 'coachGiveArea', '2', 'coachGiveArea', '200255', 'coachGiveArea/index', '0', '1', '0', 'documentation', null, '2', null, '2021-02-19 11:34:18', null, '2021-02-19 11:34:18');
INSERT INTO `sys_menu` VALUES ('200265', '教练信息审核', 'coachAdd', '2', 'coachAdd', '200255', 'coachInfo/add', '0', '1', '0', 'edit', null, '3', null, '2021-02-19 13:53:24', null, '2021-02-19 13:53:24');
INSERT INTO `sys_menu` VALUES ('200266', 'VIP一费制教练', 'vipCoachList', '2', 'vipCoachList', '200255', 'vipCoachList/index', '0', '0', '0', 'druid', null, '4', null, '2021-02-20 10:06:57', null, '2021-02-20 10:06:57');
INSERT INTO `sys_menu` VALUES ('200267', '新增VIP教练', 'vipCoachAdd', '2', 'vipCoachAdd', '200255', 'vipCoachList/add', '0', '1', '0', 'edit', null, '5', null, '2021-02-20 14:07:32', null, '2021-02-20 14:07:32');
INSERT INTO `sys_menu` VALUES ('200269', '栏目用户', 'channelUser', '2', 'homeChannelUser', '200221', 'homeChannel/user', '1', '1', '0', 'color', null, '6', null, '2021-02-23 09:33:01', null, '2021-02-23 09:33:01');
INSERT INTO `sys_menu` VALUES ('200270', '平台合作驾校管理', 'admin:driveSchool:pageList', '2', 'driveSchoolManage', '200221', 'driveSchoolManage/list.vue', '1', '0', '0', 'clipboard', null, '1', null, '2021-02-26 10:50:47', null, '2021-02-26 10:50:47');
INSERT INTO `sys_menu` VALUES ('200271', '学车流程管理', null, '1', 'drvieProcess', '0', null, '1', '0', '0', 'component', null, '1', null, '2021-03-05 10:35:34', null, '2021-03-05 10:35:34');
INSERT INTO `sys_menu` VALUES ('200272', '学车流程管理', 'driveProcess', '2', 'driveProcess', '200271', 'driveProcess/index.vue', '0', '0', '0', 'dict', null, '1', null, '2021-03-05 10:38:52', null, '2021-03-05 10:38:52');
INSERT INTO `sys_menu` VALUES ('200273', '流程新增', 'DriveFlow', '2', 'DriveFlow', '200271', 'driveProcess/add.vue', '0', '1', '0', 'education', null, '1', null, '2021-03-05 11:43:24', null, '2021-03-05 11:43:24');
INSERT INTO `sys_menu` VALUES ('200274', '平台服务承诺管理', 'Service', '2', 'Service', '200271', 'driveProcess/service.vue', '0', '0', '0', 'dict', null, '1', null, '2021-03-05 14:24:24', null, '2021-03-05 14:24:24');
INSERT INTO `sys_menu` VALUES ('200275', '服务承诺新增', 'ServiceAdd', '2', 'ServiceAdd', '200271', 'driveProcess/serviceAdd.vue', '0', '1', '0', 'drag', null, '1', null, '2021-03-05 14:32:50', null, '2021-03-05 14:32:50');
INSERT INTO `sys_menu` VALUES ('200276', '评论标签管理', 'evaluateTag:list', '2', 'EvaluateTag', '200221', 'basics/evaluateTag/index', '1', '0', '0', 'chart', null, '0', null, '2021-03-06 11:29:39', null, '2021-03-06 11:29:39');
INSERT INTO `sys_menu` VALUES ('200277', '评论标签添加', '/evaluateTag/add', '2', '/evaluateTag/add', '200221', 'basics/evaluateTag/add', '1', '1', '0', 'client', null, '1', null, '2021-03-06 11:30:54', null, '2021-03-06 11:30:54');
INSERT INTO `sys_menu` VALUES ('200278', '评价管理', null, '1', 'comment', '0', null, '1', '0', '0', 'nested', null, '12', null, '2021-03-09 16:50:52', null, '2021-03-09 16:50:52');
INSERT INTO `sys_menu` VALUES ('200279', '学员教练互评管理', 'studentCoachAppraise', '2', 'studentCoachAppraise', '200278', 'studentCoachAppraise/index.vue', '1', '0', '0', '#', null, '1', null, '2021-03-09 16:58:18', null, '2021-03-09 16:58:18');
INSERT INTO `sys_menu` VALUES ('200284', '学员学车报名单修改', 'studentStudyEnrollModification:id', '2', 'studentStudyEnrollModification/:id', '200245', 'studentInfo/studentStudyEnroll/modification.vue', '1', '1', '0', '#', null, '1', null, '2021-03-15 14:17:00', null, '2021-03-15 14:17:00');
INSERT INTO `sys_menu` VALUES ('200285', '学车报名单完善信息', 'studentStudyEnrollRepair', '2', 'studentStudyEnrollRepair/:id', '200245', 'studentInfo/studentStudyEnroll/repair.vue', '1', '1', '0', '#', null, '13', null, '2021-03-15 16:24:04', null, '2021-03-15 16:24:04');
INSERT INTO `sys_menu` VALUES ('200286', '绑定教练', 'bindCoach', '2', 'bindCoach/:id', '200245', 'studentInfo/studentStudyEnroll/bindCoach.vue', '1', '1', '0', '#', null, '14', null, '2021-03-17 09:24:15', null, '2021-03-17 09:24:15');
INSERT INTO `sys_menu` VALUES ('200289', '售前管理', 'serviceStem', '1', 'serviceOperate/previous', '0', 'serviceStem', '1', '0', '0', 'people', null, '13', null, '2021-03-29 10:21:05', null, '2021-03-29 10:21:05');
INSERT INTO `sys_menu` VALUES ('200291', '新用户列表', 'newCustomerList', '2', 'newCustomerList', '200290', 'serviceOperate/previous/newCustomerList.vue', '1', '0', '0', '#', null, '1', null, '2021-03-29 10:25:59', null, '2021-03-29 10:25:59');
INSERT INTO `sys_menu` VALUES ('200292', '新用户待回访', 'admin:studentInfo:list', '2', 'newCustomerList', '200289', 'serviceOperate/previous/newCustomerList.vue', '1', '0', '0', '#', null, '1', null, '2021-03-29 11:30:48', null, '2021-03-29 11:30:48');
INSERT INTO `sys_menu` VALUES ('200294', '报名待支付待回访', 'admin:studentInfo:list', '2', 'enrollAndNoPayList', '200289', 'serviceOperate/previous/enrollAndNoPayList.vue', '1', '0', '0', '#', null, '3', null, '2021-03-29 14:57:11', null, '2021-03-29 14:57:11');
INSERT INTO `sys_menu` VALUES ('200295', '新用户已回访', 'admin:studentStudyEnroll:list', '2', 'newCustomerListVisited', '200289', 'serviceOperate/previous/newCustomerListVisited.vue', '1', '0', '0', '#', null, '2', null, '2021-03-30 11:15:55', null, '2021-03-30 11:15:55');
INSERT INTO `sys_menu` VALUES ('200296', '报名待支付已回访', 'admin:studentStudyEnroll:list', '2', 'enrollAndNoPayListVisited', '200289', 'serviceOperate/previous/enrollAndNoPayListVisited.vue', '1', '0', '0', '#', null, '4', null, '2021-03-30 11:16:53', null, '2021-03-30 11:16:53');
INSERT INTO `sys_menu` VALUES ('200297', '报名订单取消', 'enrollCancel', '2', 'enrollCancel', '200289', 'serviceOperate/previous/enrollCancel.vue', '1', '0', '0', '#', null, '6', null, '2021-03-31 13:38:42', null, '2021-03-31 13:38:42');
INSERT INTO `sys_menu` VALUES ('200298', '支付转化', 'paymentConversion', '2', 'paymentConversion', '200289', 'serviceOperate/previous/paymentConversion.vue', '1', '0', '0', '#', null, '5', null, '2021-03-31 13:41:23', null, '2021-03-31 13:41:23');
INSERT INTO `sys_menu` VALUES ('200299', '运营商列表', 'basics:operator:allList', '3', '', '200222', null, '1', '0', '0', '#', null, '2', null, '2021-04-01 14:54:41', null, '2021-04-01 14:54:41');
INSERT INTO `sys_menu` VALUES ('200300', '城市区域列表', 'admin:area:allList', '3', '', null, '', '1', '0', '0', '#', '', '2', '', '2021-04-01 14:54:41', '', '2021-04-01 14:54:41');
INSERT INTO `sys_menu` VALUES ('201300', '城市区域列表', 'admin:area:allList', '3', '', '200222', '', '1', '0', '0', '#', '', '2', '', '2021-04-01 14:54:41', '', '2021-04-01 14:54:41');
INSERT INTO `sys_menu` VALUES ('201301', '城市区域列表', 'admin:area:allList', '3', '', null, '', '1', '0', '0', '#', '', '2', '', '2021-04-01 14:54:41', '', '2021-04-01 14:54:41');
INSERT INTO `sys_menu` VALUES ('201302', '城市区域', null, null, '', '0', null, '1', '0', '0', '#', null, '0', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('201303', '城市区域', 'admin:area:allList', '3', '', '0', null, '1', '0', '0', '#', null, '2', null, '2021-04-01 14:54:41', null, '2021-04-01 14:54:41');
INSERT INTO `sys_menu` VALUES ('201304', '售后管理', null, '1', 'serviceOperate/behind', '0', null, '1', '0', '0', 'form', null, '14', null, '2021-04-06 15:52:02', null, '2021-04-06 15:52:02');
INSERT INTO `sys_menu` VALUES ('201305', '面签管理列表', 'admin:studentStudyEnroll:list', '2', 'faceSign', '201304', 'serviceOperate/behind/faceSign.vue', '1', '0', '0', '#', null, '0', null, '2021-04-06 15:53:48', null, '2021-04-06 15:53:48');
INSERT INTO `sys_menu` VALUES ('201306', '科目一管理', 'admin:studentTestEnroll:list', '2', 'subjectOne', '201304', 'serviceOperate/behind/subjectOne.vue', '1', '1', '0', '#', null, '1', null, '2021-04-06 15:54:34', null, '2021-04-06 15:54:34');
INSERT INTO `sys_menu` VALUES ('201308', '科目二管理', 'admin:studentTestEnroll:list', '2', 'subjectTwo', '201304', 'serviceOperate/behind/subjectTwo.vue', '1', '1', '0', '#', null, '2', null, '2021-04-06 15:55:17', null, '2021-04-06 15:55:17');
INSERT INTO `sys_menu` VALUES ('201309', '科目三管理', 'admin:studentTestEnroll:list', '2', 'subjectThree', '201304', 'serviceOperate/behind/subjectThree.vue', '1', '1', '0', '#', null, '3', null, '2021-04-08 20:54:29', null, '2021-04-08 20:54:29');
INSERT INTO `sys_menu` VALUES ('201310', '科目四管理', 'admin:studentTestEnroll:list', '2', 'subjectFour', '201304', 'serviceOperate/behind/subjectFour.vue', '1', '1', '0', '#', null, '4', null, '2021-04-08 20:55:06', null, '2021-04-08 20:55:06');
INSERT INTO `sys_menu` VALUES ('201311', '拿证用户列表', 'passDrivingTest', '2', 'passDrivingTest', '201304', 'serviceOperate/behind/passDrivingTest.vue', '1', '0', '0', '#', null, '9', null, '2021-04-09 09:37:05', null, '2021-04-09 09:37:05');
INSERT INTO `sys_menu` VALUES ('201312', '学员信息管理', null, '1', 'serviceOperate/customerManage', '0', null, '1', '0', '0', 'client', null, '15', null, '2021-04-15 10:29:15', null, '2021-04-15 10:29:15');
INSERT INTO `sys_menu` VALUES ('201313', '学车管理', null, '1', 'serviceOperate/driveManage', '0', null, '1', '0', '0', 'example', null, '16', null, '2021-04-15 10:34:57', null, '2021-04-15 10:34:57');
INSERT INTO `sys_menu` VALUES ('201314', '用户列表', 'allUserManage', '2', 'allUserManage', '201312', 'serviceOperate/customerManage/allUserManage.vue', '1', '0', '0', '#', null, '1', null, '2021-04-15 10:49:27', null, '2021-04-15 10:49:27');
INSERT INTO `sys_menu` VALUES ('201315', '学员管理', 'customerManage', '2', 'customerManage', '201313', 'serviceOperate/driveManage/customerManage.vue', '1', '0', '0', '#', null, '1', null, '2021-04-16 09:23:21', null, '2021-04-16 09:23:21');
INSERT INTO `sys_menu` VALUES ('201316', '课时管理', 'classManage', '2', 'classManage', '201313', 'serviceOperate/driveManage/classManage.vue', '1', '0', '0', '#', null, '2', null, '2021-04-16 09:23:52', null, '2021-04-16 09:23:52');
INSERT INTO `sys_menu` VALUES ('201317', '分配客服', 'admin:studentInfo:updateBatch', '3', '', '200292', null, '1', '0', '0', '#', null, '0', null, '2021-04-26 10:24:27', null, '2021-04-26 10:24:27');
INSERT INTO `sys_menu` VALUES ('201318', '切换客服', 'admin:studentInfo:updateBatch', '3', '', '200294', null, '1', '0', '0', '#', null, '0', null, '2021-04-26 11:22:43', null, '2021-04-26 11:22:43');
INSERT INTO `sys_menu` VALUES ('201319', '取消订单', 'admin:studentStudyEnroll:cancel', '3', '', '200295', null, '1', '0', '0', '#', null, '0', null, '2021-04-26 11:25:12', null, '2021-04-26 11:25:12');
INSERT INTO `sys_menu` VALUES ('201320', '切换客服', 'admin:studentStudyEnroll:updateBatch', '3', '', '200295', null, '1', '0', '0', '#', null, '0', null, '2021-04-26 11:25:44', null, '2021-04-26 11:25:44');
INSERT INTO `sys_menu` VALUES ('201321', '取消订单', 'admin:studentStudyEnroll:cancel', '3', '', '200296', null, '1', '0', '0', '#', null, '0', null, '2021-04-26 11:27:03', null, '2021-04-26 11:27:03');
INSERT INTO `sys_menu` VALUES ('201322', '切换客服', 'admin:studentStudyEnroll:updateBatch', '3', '', '200296', null, '1', '0', '0', '#', null, '1', null, '2021-04-26 11:27:19', null, '2021-04-26 11:27:19');
INSERT INTO `sys_menu` VALUES ('201323', '报名完成', 'admin:studentStudyEnroll:allData', '3', '', '201305', null, '1', '0', '0', '#', null, '0', null, '2021-04-27 13:41:17', null, '2021-04-27 13:41:17');
INSERT INTO `sys_menu` VALUES ('201324', '数据查询', 'admin:studentTestEnroll:list', '3', '', '201306', null, '1', '0', '0', '#', null, '0', null, '2021-04-27 15:06:29', null, '2021-04-27 15:06:29');
INSERT INTO `sys_menu` VALUES ('201325', '数据查询', 'admin:studentTestEnroll:list', '3', '', '201308', null, '1', '0', '0', '#', null, '0', null, '2021-04-27 16:39:19', null, '2021-04-27 16:39:19');
INSERT INTO `sys_menu` VALUES ('201326', '数据查询', 'admin:studentTestEnroll:list', '3', '', '201309', null, '1', '0', '0', '#', null, '0', null, '2021-04-27 16:39:35', null, '2021-04-27 16:39:35');
INSERT INTO `sys_menu` VALUES ('201327', '数据查询', 'admin:studentTestEnroll:list', '3', '', '201310', null, '1', '0', '0', '#', null, '0', null, '2021-04-27 16:39:45', null, '2021-04-27 16:39:45');
INSERT INTO `sys_menu` VALUES ('201328', '切换客服', 'admin:studentTestEnroll:toggleService', '3', '', '201306', null, '1', '0', '0', '#', null, '1', null, '2021-04-27 17:38:45', null, '2021-04-27 17:38:45');
INSERT INTO `sys_menu` VALUES ('201329', '切换客服', 'admin:studentTestEnroll:toggleService', '3', '', '201308', null, '1', '0', '0', '#', null, '1', null, '2021-04-27 17:38:59', null, '2021-04-27 17:38:59');
INSERT INTO `sys_menu` VALUES ('201330', '切换客服', 'admin:studentTestEnroll:toggleService', '3', '', '201309', null, '1', '0', '0', '#', null, '1', null, '2021-04-27 17:39:07', null, '2021-04-27 17:39:07');
INSERT INTO `sys_menu` VALUES ('201331', '切换客服', 'admin:studentTestEnroll:toggleService', '3', '', '201310', null, '1', '0', '0', '#', null, '1', null, '2021-04-27 17:39:17', null, '2021-04-27 17:39:17');
INSERT INTO `sys_menu` VALUES ('201333', '数据查询', 'admin:studentInfo:list', '3', '', '200294', null, '1', '0', '0', '#', null, '1', null, '2021-04-28 18:24:01', null, '2021-04-28 18:24:01');
INSERT INTO `sys_menu` VALUES ('201335', '数据查询', 'admin:studentStudyEnroll:list', '3', '', '201305', null, '1', '0', '0', '#', null, '1', null, '2021-04-28 18:25:39', null, '2021-04-28 18:25:39');
INSERT INTO `sys_menu` VALUES ('201336', '数据查询', 'admin:studentInfo:list', '3', '', '200292', null, '1', '0', '0', '#', null, '0', null, '2021-04-28 18:39:24', null, '2021-04-28 18:39:24');
INSERT INTO `sys_menu` VALUES ('201337', '数据查询', 'admin:studentStudyEnroll:list', '3', '', '200295', null, '1', '0', '0', '#', null, '2', null, '2021-04-28 18:40:04', null, '2021-04-28 18:40:04');
INSERT INTO `sys_menu` VALUES ('201338', '数据查询', 'admin:studentStudyEnroll:list', '3', '', '200296', null, '1', '0', '0', '#', null, '2', null, '2021-04-28 18:40:21', null, '2021-04-28 18:40:21');
INSERT INTO `sys_menu` VALUES ('201339', '驾校管理', 'admin:driveSchool:query', '1', 'admin:driveSchool:query', '0', null, '1', '1', '0', '#', null, '18', null, '2021-04-29 18:06:00', null, '2021-04-29 18:06:00');
INSERT INTO `sys_menu` VALUES ('201340', '数据查询', 'admin:driveSchool:query', '3', '', '201339', null, '1', '0', '0', '#', null, '0', null, '2021-04-29 18:06:28', null, '2021-04-29 18:06:28');
INSERT INTO `sys_menu` VALUES ('201341', '数据查询', 'admin/oneFeeSystemPrice', '3', '', '201339', null, '1', '0', '0', '#', null, '0', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('201342', '数据查询', 'admin:oneFeeSystemVipCoach:query', '3', '', '201339', null, '1', '0', '0', '#', null, '0', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('201343', '数据查询', 'admin:coachInfo:query', '3', '', '201339', null, '1', '0', '0', '#', null, '0', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('201344', '数据查询', 'admin:oneFeeSystemCoachStudent:add', '3', '', '201339', null, '1', '0', '0', '#', null, '0', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('201345', '数据查询', 'admin:oneFeeSystemCoachStudent:query', '3', '', '201339', null, '1', '0', '0', '#', null, '0', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('201346', '数据查询', 'admin:oneFeeSystemCoachStudent:query', '3', '', '201339', null, '1', '0', '0', '#', null, '0', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('201347', '平台数据查询', 'admin:coachingGrid:query', '3', '', '201339', null, '1', '0', '0', '#', null, '0', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('201348', '班型查询', 'admin:oneFeeSystemPrice:query', '3', '', '200233', null, '1', '0', '0', '#', null, '1', null, '2021-05-06 13:55:21', null, '2021-05-06 13:55:21');
INSERT INTO `sys_menu` VALUES ('201349', '班型添加', 'admin:oneFeeSystemPrice:add', '3', '', '200233', null, '1', '0', '0', '#', null, '2', null, '2021-05-06 13:56:27', null, '2021-05-06 13:56:27');
INSERT INTO `sys_menu` VALUES ('201350', '班型修改', 'admin:oneFeeSystemPrice:edit', '3', '', '200233', null, '1', '0', '0', '#', null, '3', null, '2021-05-06 13:56:47', null, '2021-05-06 13:56:47');
INSERT INTO `sys_menu` VALUES ('201351', '班型复制', 'admin:oneFeeSystemPrice:copyServicePackage', '3', '', '200233', null, '1', '0', '0', '#', null, '4', null, '2021-05-06 13:57:37', null, '2021-05-06 13:57:37');
INSERT INTO `sys_menu` VALUES ('201352', '班型删除', 'admin:oneFeeSystemPrice:delete', '3', '', '200233', null, '1', '0', '0', '#', null, '5', null, '2021-05-06 13:58:15', null, '2021-05-06 13:58:15');
INSERT INTO `sys_menu` VALUES ('201353', '发布班型', 'admin:oneFeeSystemPrice:publishServicePackage', '3', '', '200233', null, '1', '0', '0', '#', null, '6', null, '2021-05-06 13:59:32', null, '2021-05-06 13:59:32');
INSERT INTO `sys_menu` VALUES ('201354', '获取所有运营商列表', 'basics:operator:findAllList', '3', '', '200222', null, '1', '0', '0', '#', null, '3', null, '2021-05-06 16:04:00', null, '2021-05-06 16:04:00');
INSERT INTO `sys_menu` VALUES ('201355', '领取优惠券查询', 'marketing:couponGet:query', '3', '', '200211', null, '1', '0', '0', '#', null, '5', null, '2021-05-06 16:13:46', null, '2021-05-06 16:13:46');
INSERT INTO `sys_menu` VALUES ('201356', '领取优惠券修改', 'marketing:couponGet:edit', '3', '', '200211', null, '1', '0', '0', '#', null, '6', null, '2021-05-06 16:14:19', null, '2021-05-06 16:14:19');
INSERT INTO `sys_menu` VALUES ('201357', '领取优惠券删除', 'marketing:couponGet:delete', '3', '', '200211', null, '1', '0', '0', '#', null, '7', null, '2021-05-06 16:14:41', null, '2021-05-06 16:14:41');
INSERT INTO `sys_menu` VALUES ('201358', '领取优惠券导出', 'marketing:couponGet:export', '3', '', '200211', null, '1', '0', '0', '#', null, '8', null, '2021-05-06 16:15:10', null, '2021-05-06 16:15:10');
INSERT INTO `sys_menu` VALUES ('201359', '科目考试管理', 'admin:studentEnroll:list', '2', 'subjectTotal', '201304', 'serviceOperate/behind/subjectTotal.vue', '1', '0', '0', '#', null, '7', null, '2021-05-10 13:55:28', null, '2021-05-10 13:55:28');
INSERT INTO `sys_menu` VALUES ('201360', '未预约考试管理', 'admin:studentEnroll:list', '2', 'noAppointment', '201304', 'serviceOperate/behind/noAppointment.vue', '1', '0', '0', '#', null, '6', null, '2021-05-10 13:56:13', null, '2021-05-10 13:56:13');
INSERT INTO `sys_menu` VALUES ('201361', '移除学员列表', 'admin:studentEnroll:list', '2', 'removeCustomer', '201304', 'serviceOperate/behind/removeCustomer.vue', '1', '0', '0', '#', null, '8', null, '2021-05-10 13:59:38', null, '2021-05-10 13:59:38');
INSERT INTO `sys_menu` VALUES ('201362', '价格钱包管理', '/', '1', 'enrollTestDrivingPriceManage', '0', '/', '1', '0', '0', 'tool', null, '16', null, '2021-05-10 17:13:46', null, '2021-05-10 17:13:46');
INSERT INTO `sys_menu` VALUES ('201363', '报名考试练车单价管理', 'admin:testTrainPrice:pageList', '2', 'enrollTestDrivingPriceManage', '201362', 'enrollTestDrivingPriceManage/list.vue', '1', '0', '0', 'international', null, '1', null, '2021-05-10 17:27:05', null, '2021-05-10 17:27:05');
INSERT INTO `sys_menu` VALUES ('201364', '切换客服', 'admin:studentTestEnroll:toggleService', '3', '', '201359', null, '1', '0', '0', '#', null, '0', null, '2021-05-11 09:51:44', null, '2021-05-11 09:51:44');
INSERT INTO `sys_menu` VALUES ('201365', '切换客服', 'admin:studentTestEnroll:toggleService', '3', '', '201360', null, '1', '0', '0', '#', null, '0', null, '2021-05-11 09:51:55', null, '2021-05-11 09:51:55');
INSERT INTO `sys_menu` VALUES ('201366', '切换客服', 'admin:studentTestEnroll:toggleService', '3', '', '201361', null, '1', '0', '0', '#', null, '0', null, '2021-05-11 09:52:04', null, '2021-05-11 09:52:04');
INSERT INTO `sys_menu` VALUES ('201367', '修改权限', 'admin:testTrainPrice:edit', '3', '', '201363', null, '1', '0', '0', '#', null, '0', null, '2021-05-11 10:43:41', null, '2021-05-11 10:43:41');
INSERT INTO `sys_menu` VALUES ('201368', '新增数据', 'admin:testTrainPrice:add', '3', '', '201363', null, '1', '0', '0', '#', null, '1', null, '2021-05-11 10:43:55', null, '2021-05-11 10:43:55');
INSERT INTO `sys_menu` VALUES ('201369', '数据删除', 'admin:testTrainPrice:delete', '3', '', '201363', null, '1', '0', '0', '#', null, '2', null, '2021-05-11 10:47:01', null, '2021-05-11 10:47:01');
INSERT INTO `sys_menu` VALUES ('201370', '教练课程时间表', 'admin:coachTeachTime:pageList', '2', 'coachCourseTime', '200255', 'coachCourseTime/list.vue', '1', '0', '0', 'build', null, '6', null, '2021-05-11 11:30:17', null, '2021-05-11 11:30:17');
INSERT INTO `sys_menu` VALUES ('201371', '新增数据', 'admin:coachTeachTime:add', '3', '', '201370', null, '1', '0', '0', '#', null, '0', null, '2021-05-11 11:30:58', null, '2021-05-11 11:30:58');
INSERT INTO `sys_menu` VALUES ('201372', '推广管理', null, '1', 'extension', '0', null, '1', '0', '0', 'date', null, '17', null, '2021-05-11 15:27:18', null, '2021-05-11 15:27:18');
INSERT INTO `sys_menu` VALUES ('201373', '推广渠道经理管理', 'admin:recommendManager:pageList', '2', 'channelManagerManage', '201372', 'channelManagerManage/list.vue', '1', '0', '0', '#', null, '0', null, '2021-05-11 15:28:32', null, '2021-05-11 15:28:32');
INSERT INTO `sys_menu` VALUES ('201374', '修改数据', 'admin:recommendManager:update', '3', '', '201373', null, '1', '0', '0', '#', null, '0', null, '2021-05-11 15:30:38', null, '2021-05-11 15:30:38');
INSERT INTO `sys_menu` VALUES ('201376', '删除数据', 'admin:recommendManager:update', '3', '', '201373', null, '1', '0', '0', '#', null, '1', null, '2021-05-11 15:38:54', null, '2021-05-11 15:38:54');
INSERT INTO `sys_menu` VALUES ('201377', '设置提成比', 'admin:recommendManager:set', '3', '', '201373', null, '1', '0', '0', '#', null, '2', null, '2021-05-11 15:39:08', null, '2021-05-11 15:39:08');
INSERT INTO `sys_menu` VALUES ('201378', '运营商信息', 'admin:operator:pageList', '2', 'operatorBase', '200261', 'operatorBase/list.vue', '1', '0', '0', '#', null, '1', null, '2021-05-12 10:04:42', null, '2021-05-12 10:04:42');
INSERT INTO `sys_menu` VALUES ('201380', '运营商基础配置管理', 'operatorBaseOptions', '2', 'operatorBaseOptions', '200261', 'operatorBaseOptions/list.vue', '1', '0', '0', '#', null, '5', null, '2021-05-12 15:13:33', null, '2021-05-12 15:13:33');
INSERT INTO `sys_menu` VALUES ('201381', '一费制学员所属教练', 'admin:oneFeeSystemCoachStudent:pageList', '2', 'oneFeeToCoach', '200245', 'oneFeeToCoach/list.vue', '1', '1', '0', 'documentation', null, '1', null, '2021-05-12 16:44:07', null, '2021-05-12 16:44:07');
INSERT INTO `sys_menu` VALUES ('201382', '运营商加盟申请', 'operatorBaseJoin', '2', 'operatorBaseJoin', '200261', 'operatorBaseJoin/list.vue', '1', '0', '0', '#', null, '5', null, '2021-05-13 09:40:43', null, '2021-05-13 09:40:43');
INSERT INTO `sys_menu` VALUES ('201383', '班型升班管理', 'admin:oneFeeSystemUpgradeClassPrice:query', '2', '/oneFeeSystemUpgradeClassPrice/manager', '200229', 'pastService/serviceItem/upgradeClassPrice/index', '1', '0', '0', 'clipboard', null, '5', null, '2021-05-13 10:09:58', null, '2021-05-13 10:09:58');
INSERT INTO `sys_menu` VALUES ('201384', '查询', 'admin:oneFeeSystemUpgradeClassPrice:query', '3', '', '201383', null, '1', '0', '0', '#', null, '1', null, '2021-05-13 10:11:54', null, '2021-05-13 10:11:54');
INSERT INTO `sys_menu` VALUES ('201385', '新增', 'admin:oneFeeSystemUpgradeClassPrice:add', '3', '', '201383', null, '1', '0', '0', '#', null, '2', null, '2021-05-13 10:12:18', null, '2021-05-13 10:12:18');
INSERT INTO `sys_menu` VALUES ('201386', '修改', 'admin:oneFeeSystemUpgradeClassPrice:edit', '3', '', '201383', null, '1', '0', '0', '#', null, '3', null, '2021-05-13 10:12:53', null, '2021-05-13 10:12:53');
INSERT INTO `sys_menu` VALUES ('201387', '删除', 'admin:oneFeeSystemUpgradeClassPrice:delete', '3', '', '201383', null, '1', '0', '0', '#', null, '4', null, '2021-05-13 10:13:51', null, '2021-05-13 10:13:51');
INSERT INTO `sys_menu` VALUES ('201388', '导出', 'admin:oneFeeSystemUpgradeClassPrice:export', '3', '', '201383', null, '1', '0', '0', '#', null, '5', null, '2021-05-13 10:14:20', null, '2021-05-13 10:14:20');
INSERT INTO `sys_menu` VALUES ('201389', '状态启用/停用', 'admin:oneFeeSystemUpgradeClassPrice:changeStatus', '3', '', '201383', null, '1', '0', '0', '#', null, '6', null, '2021-05-13 10:14:48', null, '2021-05-13 10:14:48');
INSERT INTO `sys_menu` VALUES ('201391', '推广商管理', 'promotersManage', '2', 'promotersManage', '201372', 'promotersManage/list.vue', '1', '0', '0', '#', null, '2', null, '2021-05-13 13:37:15', null, '2021-05-13 13:37:15');
INSERT INTO `sys_menu` VALUES ('201393', '基础信息管理', null, '1', 'baseInfo', '0', null, '1', '0', '0', 'github', null, '3', null, '2021-05-17 14:41:22', null, '2021-05-17 14:41:22');
INSERT INTO `sys_menu` VALUES ('201394', '区域管理', 'admin:area:pageList', '2', 'areaManage', '201393', 'areaManage/list.vue', '1', '0', '0', '#', null, '0', null, '2021-05-17 14:44:20', null, '2021-05-17 14:44:20');
INSERT INTO `sys_menu` VALUES ('201395', '钱包管理', 'admin:platformWallet:pageList', '2', 'walletManage', '201362', 'walletManage/list.vue', '1', '0', '0', 'money', null, '9', null, '2021-05-17 15:09:19', null, '2021-05-17 15:09:19');
INSERT INTO `sys_menu` VALUES ('201396', '驾校用户管理', 'admin:schoolUser:pageList', '2', 'driveSchoolUserManage', '201393', 'driveSchoolUserManage/list.vue', '1', '0', '0', '#', null, '1', null, '2021-05-17 16:25:45', null, '2021-05-17 16:25:45');
INSERT INTO `sys_menu` VALUES ('201397', '平台合作驾校管理', 'admin:driveSchool:pageList', '2', 'driveSchoolManage', '201393', 'driveSchoolManage/list.vue', '1', '0', '0', '#', null, '3', null, '2021-05-17 17:11:19', null, '2021-05-17 17:11:19');
INSERT INTO `sys_menu` VALUES ('201398', '平台训练场地管理', ' admin:coachingGrid:pageList', '2', 'trainingFieldManage', '201393', 'trainingFieldManage/list.vue', '1', '0', '0', 'table', null, '2', null, '2021-05-18 15:17:40', null, '2021-05-18 15:17:40');
INSERT INTO `sys_menu` VALUES ('201399', '学员订单管理', 'admin:studentOrder:pageList', '2', 'studentOrder', '201312', 'studentInfo/studentOrder/studentOrder.vue', '1', '0', '0', '#', null, '1', null, '2021-05-19 10:45:28', null, '2021-05-19 10:45:28');
INSERT INTO `sys_menu` VALUES ('201400', '学员学车预约管理', ' admin:studentTrainCarApply:pageList', '2', 'studentOrderManage', '200245', 'studentOrderManage/list.vue', '1', '1', '0', 'skill', null, '2', null, '2021-05-19 11:14:26', null, '2021-05-19 11:14:26');
INSERT INTO `sys_menu` VALUES ('201401', '一费制学员所属教练', 'admin:oneFeeSystemCoachStudent:pageList', '2', 'oneFeeToCoach', '201312', 'oneFeeToCoach/list.vue', '1', '0', '0', 'documentation', null, '1', null, '2021-05-19 11:26:53', null, '2021-05-19 11:26:53');
INSERT INTO `sys_menu` VALUES ('201402', '学员学车预约管理', 'admin:studentTrainCarApply:pageList', '2', 'studentApplyManage', '201312', 'studentApplyManage/list.vue', '1', '0', '0', 'skill', null, '2', null, '2021-05-19 11:28:26', null, '2021-05-19 11:28:26');
INSERT INTO `sys_menu` VALUES ('201403', '日志管理', '', '1', 'dailyRecord', '0', '', '1', '0', '0', 'date', null, '1', null, '2021-05-22 10:25:13', null, '2021-05-22 10:25:13');
INSERT INTO `sys_menu` VALUES ('201404', '验证码管理', 'admin:message:pageList', '2', 'verificationCodeManage', '201403', 'verificationCodeManage/list.vue', '1', '0', '0', 'size', null, '1', null, '2021-05-22 10:43:05', null, '2021-05-22 10:43:05');
INSERT INTO `sys_menu` VALUES ('201405', 'APP版本管理', 'admin:appVersion:pageList', '2', 'appVersion', '201393', 'appVersion/list.vue', '1', '0', '0', 'client', null, '1', null, '2021-05-22 13:00:02', null, '2021-05-22 13:00:02');
INSERT INTO `sys_menu` VALUES ('201406', '用户常用地址管理', 'admin:userCommonlyAddress:pageList', '2', 'userAddress', '201393', 'userAddress/list.vue', '1', '0', '0', 'excel', null, '1', null, '2021-05-22 15:32:40', null, '2021-05-22 15:32:40');
INSERT INTO `sys_menu` VALUES ('201407', '账务管理', null, '1', 'assetsManage', '0', null, '1', '0', '0', 'icon', null, '3', null, '2021-05-25 10:15:56', null, '2021-05-25 10:15:56');
INSERT INTO `sys_menu` VALUES ('201408', '交易流水', 'admin:payFlowLog:pageList', '2', 'businessFlow', '201407', 'businessFlow/list.vue', '1', '0', '0', '#', null, '0', null, '2021-05-25 10:28:37', null, '2021-05-25 10:28:37');
INSERT INTO `sys_menu` VALUES ('201409', '系统参数设置', 'admin:sysParam:pageList', '2', 'systemParameterConfig', '1', 'systemParameterConfig/list.vue', '1', '0', '0', 'system', null, '1', null, '2021-05-25 10:58:35', null, '2021-05-25 10:58:35');
INSERT INTO `sys_menu` VALUES ('201410', '订单财务流水', 'admin:accountFlow:pageList', '2', 'orderFinanceFlow', '201407', 'orderFinanceFlow/list.vue', '1', '0', '0', '#', null, '1', null, '2021-05-25 11:34:46', null, '2021-05-25 11:34:46');
INSERT INTO `sys_menu` VALUES ('201412', '钱包清算汇总管理', 'walletCollectManage', '2', 'walletCollectManage', '201407', 'walletCollectManage/list.vue', '1', '0', '0', '#', null, '4', null, '2021-05-25 14:28:52', null, '2021-05-25 14:28:52');
INSERT INTO `sys_menu` VALUES ('201413', '系统任务管理', 'demo', '2', 'systemTaskManage', '1', 'systemTaskManage/list.vue', '1', '0', '0', 'log', null, '2', null, '2021-05-25 14:31:35', null, '2021-05-25 14:31:35');
INSERT INTO `sys_menu` VALUES ('201414', '平台题目选项管理', 'demo', '2', 'problemOptionsManage', '201393', 'problemOptionsManage/list.vue', '1', '0', '0', 'nested', null, '1', null, '2021-05-25 14:33:20', null, '2021-05-25 14:33:20');
INSERT INTO `sys_menu` VALUES ('201415', '平台题库管理', 'admin:questionBank:pageList', '2', 'questionBankManage', '201393', 'questionBankManage/list.vue', '1', '0', '0', 'email', null, '6', null, '2021-05-28 09:37:48', null, '2021-05-28 09:37:48');
INSERT INTO `sys_menu` VALUES ('201416', 'banner', 'basics:banner:pageList', '2', 'home/banner', '200238', 'basics/home/banner/index.vue', '1', '1', '0', '#', null, '1', null, '2021-06-04 13:27:55', null, '2021-06-04 13:27:55');
INSERT INTO `sys_menu` VALUES ('201417', 'channel', 'basics:channel:pageList', '2', 'home/channel', '200238', 'basics/home/channal', '1', '1', '0', '#', null, '3', null, '2021-06-04 13:29:27', null, '2021-06-04 13:29:27');

-- ----------------------------
-- Table structure for sys_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post` (
  `post_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
  `post_code` varchar(64) NOT NULL COMMENT '岗位编码',
  `post_name` varchar(50) NOT NULL COMMENT '岗位名称',
  `status` char(1) NOT NULL COMMENT '状态（0正常 1停用）',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `order_num` int(4) NOT NULL COMMENT '排序',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`post_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COMMENT='岗位信息';

-- ----------------------------
-- Records of sys_post
-- ----------------------------
INSERT INTO `sys_post` VALUES ('2', 'manager', '总经理', '0', null, '2', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_post` VALUES ('3', 'hr', '人事', '0', null, '3', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_post` VALUES ('4', 'finance', '财务', '0', null, '4', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_post` VALUES ('5', 'sale', '销售', '0', null, '5', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_post` VALUES ('6', 'java', '研发工程师', '0', '研发工程师', '0', null, '2021-01-21 21:24:10', null, '2021-01-21 21:24:10');
INSERT INTO `sys_post` VALUES ('7', 'cp', '产品经理', '0', '产品经理', '0', null, '2021-01-21 21:24:28', null, '2021-01-21 21:24:28');
INSERT INTO `sys_post` VALUES ('8', 'sc', '市场总监', '0', null, '0', null, '2021-01-21 21:25:48', null, '2021-01-21 21:25:48');
INSERT INTO `sys_post` VALUES ('9', 'service', '客服经理', '0', '客服经理', '1', null, '2021-03-16 17:22:47', null, '2021-03-16 17:22:47');
INSERT INTO `sys_post` VALUES ('10', '10', '客服', '0', null, '0', null, '2021-04-27 11:34:59', null, '2021-04-27 11:34:59');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) NOT NULL COMMENT '角色权限字符串',
  `data_scope` char(1) DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
  `status` char(1) NOT NULL DEFAULT '0' COMMENT '角色状态（0正常 1停用）',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `order_num` int(4) NOT NULL DEFAULT '0' COMMENT '排序',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=109 DEFAULT CHARSET=utf8mb4 COMMENT='角色信息';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '系统管理员', 'admin', '1', '0', null, '1', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_role` VALUES ('102', '运营中心', 'COO', '1', '0', '运营中心', '0', null, '2021-01-22 13:10:55', null, '2021-01-22 13:10:55');
INSERT INTO `sys_role` VALUES ('103', '客服经理', 'service', '2', '0', '客服中心', '1', null, '2021-03-18 09:23:20', null, '2021-03-18 09:23:20');
INSERT INTO `sys_role` VALUES ('104', '线上客服', 'onLine-service', '2', '0', '线上客服', '12', null, '2021-04-29 11:40:47', null, '2021-04-29 11:40:47');
INSERT INTO `sys_role` VALUES ('105', '线下客服', 'offline-service', '5', '0', '线下客服', '0', null, '2021-04-29 06:43:47', null, '2021-04-29 06:43:47');
INSERT INTO `sys_role` VALUES ('106', '考试客服', 'exam-service', '6', '0', '考试客服', '0', null, '2021-04-29 22:44:14', null, '2021-04-29 22:44:14');
INSERT INTO `sys_role` VALUES ('108', '管理员', 'manager', '1', '0', '管理员', '0', null, '2021-05-07 05:40:05', null, '2021-05-07 05:40:05');

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept` (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `dept_id` bigint(20) NOT NULL COMMENT '部门ID',
  PRIMARY KEY (`role_id`,`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色和部门关联';

-- ----------------------------
-- Records of sys_role_dept
-- ----------------------------
INSERT INTO `sys_role_dept` VALUES ('100', '1');
INSERT INTO `sys_role_dept` VALUES ('100', '100');
INSERT INTO `sys_role_dept` VALUES ('100', '101');
INSERT INTO `sys_role_dept` VALUES ('103', '1');
INSERT INTO `sys_role_dept` VALUES ('103', '100');
INSERT INTO `sys_role_dept` VALUES ('103', '205');
INSERT INTO `sys_role_dept` VALUES ('103', '207');
INSERT INTO `sys_role_dept` VALUES ('104', '1');
INSERT INTO `sys_role_dept` VALUES ('104', '100');
INSERT INTO `sys_role_dept` VALUES ('104', '200');
INSERT INTO `sys_role_dept` VALUES ('104', '201');
INSERT INTO `sys_role_dept` VALUES ('104', '202');
INSERT INTO `sys_role_dept` VALUES ('104', '205');
INSERT INTO `sys_role_dept` VALUES ('104', '206');
INSERT INTO `sys_role_dept` VALUES ('104', '207');
INSERT INTO `sys_role_dept` VALUES ('106', '1');
INSERT INTO `sys_role_dept` VALUES ('106', '100');
INSERT INTO `sys_role_dept` VALUES ('106', '101');
INSERT INTO `sys_role_dept` VALUES ('106', '102');
INSERT INTO `sys_role_dept` VALUES ('106', '200');
INSERT INTO `sys_role_dept` VALUES ('106', '201');
INSERT INTO `sys_role_dept` VALUES ('106', '202');
INSERT INTO `sys_role_dept` VALUES ('106', '203');
INSERT INTO `sys_role_dept` VALUES ('106', '204');
INSERT INTO `sys_role_dept` VALUES ('106', '205');
INSERT INTO `sys_role_dept` VALUES ('106', '207');
INSERT INTO `sys_role_dept` VALUES ('106', '208');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色和菜单关联';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('1', '1');
INSERT INTO `sys_role_menu` VALUES ('1', '2');
INSERT INTO `sys_role_menu` VALUES ('1', '1001');
INSERT INTO `sys_role_menu` VALUES ('1', '1002');
INSERT INTO `sys_role_menu` VALUES ('1', '1003');
INSERT INTO `sys_role_menu` VALUES ('1', '1004');
INSERT INTO `sys_role_menu` VALUES ('1', '1005');
INSERT INTO `sys_role_menu` VALUES ('1', '1006');
INSERT INTO `sys_role_menu` VALUES ('1', '2001');
INSERT INTO `sys_role_menu` VALUES ('1', '2002');
INSERT INTO `sys_role_menu` VALUES ('1', '2003');
INSERT INTO `sys_role_menu` VALUES ('1', '2004');
INSERT INTO `sys_role_menu` VALUES ('1', '100101');
INSERT INTO `sys_role_menu` VALUES ('1', '100102');
INSERT INTO `sys_role_menu` VALUES ('1', '100103');
INSERT INTO `sys_role_menu` VALUES ('1', '100104');
INSERT INTO `sys_role_menu` VALUES ('1', '100105');
INSERT INTO `sys_role_menu` VALUES ('1', '100201');
INSERT INTO `sys_role_menu` VALUES ('1', '100202');
INSERT INTO `sys_role_menu` VALUES ('1', '100203');
INSERT INTO `sys_role_menu` VALUES ('1', '100204');
INSERT INTO `sys_role_menu` VALUES ('1', '100205');
INSERT INTO `sys_role_menu` VALUES ('1', '100301');
INSERT INTO `sys_role_menu` VALUES ('1', '100302');
INSERT INTO `sys_role_menu` VALUES ('1', '100303');
INSERT INTO `sys_role_menu` VALUES ('1', '100304');
INSERT INTO `sys_role_menu` VALUES ('1', '100305');
INSERT INTO `sys_role_menu` VALUES ('1', '100401');
INSERT INTO `sys_role_menu` VALUES ('1', '100402');
INSERT INTO `sys_role_menu` VALUES ('1', '100403');
INSERT INTO `sys_role_menu` VALUES ('1', '100404');
INSERT INTO `sys_role_menu` VALUES ('1', '100405');
INSERT INTO `sys_role_menu` VALUES ('1', '100501');
INSERT INTO `sys_role_menu` VALUES ('1', '100502');
INSERT INTO `sys_role_menu` VALUES ('1', '100503');
INSERT INTO `sys_role_menu` VALUES ('1', '100504');
INSERT INTO `sys_role_menu` VALUES ('1', '100505');
INSERT INTO `sys_role_menu` VALUES ('1', '100601');
INSERT INTO `sys_role_menu` VALUES ('1', '100602');
INSERT INTO `sys_role_menu` VALUES ('1', '100603');
INSERT INTO `sys_role_menu` VALUES ('1', '100604');
INSERT INTO `sys_role_menu` VALUES ('1', '100605');
INSERT INTO `sys_role_menu` VALUES ('1', '200101');
INSERT INTO `sys_role_menu` VALUES ('1', '200102');
INSERT INTO `sys_role_menu` VALUES ('1', '200103');
INSERT INTO `sys_role_menu` VALUES ('1', '200104');
INSERT INTO `sys_role_menu` VALUES ('1', '200105');
INSERT INTO `sys_role_menu` VALUES ('1', '200201');
INSERT INTO `sys_role_menu` VALUES ('1', '200202');
INSERT INTO `sys_role_menu` VALUES ('1', '200203');
INSERT INTO `sys_role_menu` VALUES ('1', '200204');
INSERT INTO `sys_role_menu` VALUES ('1', '200205');
INSERT INTO `sys_role_menu` VALUES ('100', '1');
INSERT INTO `sys_role_menu` VALUES ('100', '2');
INSERT INTO `sys_role_menu` VALUES ('100', '1001');
INSERT INTO `sys_role_menu` VALUES ('100', '1002');
INSERT INTO `sys_role_menu` VALUES ('100', '1003');
INSERT INTO `sys_role_menu` VALUES ('100', '1004');
INSERT INTO `sys_role_menu` VALUES ('100', '1005');
INSERT INTO `sys_role_menu` VALUES ('100', '1006');
INSERT INTO `sys_role_menu` VALUES ('100', '2001');
INSERT INTO `sys_role_menu` VALUES ('100', '2002');
INSERT INTO `sys_role_menu` VALUES ('100', '100101');
INSERT INTO `sys_role_menu` VALUES ('100', '100102');
INSERT INTO `sys_role_menu` VALUES ('100', '100103');
INSERT INTO `sys_role_menu` VALUES ('100', '100104');
INSERT INTO `sys_role_menu` VALUES ('100', '100105');
INSERT INTO `sys_role_menu` VALUES ('100', '100201');
INSERT INTO `sys_role_menu` VALUES ('100', '100202');
INSERT INTO `sys_role_menu` VALUES ('100', '100203');
INSERT INTO `sys_role_menu` VALUES ('100', '100204');
INSERT INTO `sys_role_menu` VALUES ('100', '100205');
INSERT INTO `sys_role_menu` VALUES ('100', '100301');
INSERT INTO `sys_role_menu` VALUES ('100', '100302');
INSERT INTO `sys_role_menu` VALUES ('100', '100303');
INSERT INTO `sys_role_menu` VALUES ('100', '100304');
INSERT INTO `sys_role_menu` VALUES ('100', '100305');
INSERT INTO `sys_role_menu` VALUES ('100', '100401');
INSERT INTO `sys_role_menu` VALUES ('100', '100402');
INSERT INTO `sys_role_menu` VALUES ('100', '100403');
INSERT INTO `sys_role_menu` VALUES ('100', '100404');
INSERT INTO `sys_role_menu` VALUES ('100', '100405');
INSERT INTO `sys_role_menu` VALUES ('100', '100501');
INSERT INTO `sys_role_menu` VALUES ('100', '100502');
INSERT INTO `sys_role_menu` VALUES ('100', '100503');
INSERT INTO `sys_role_menu` VALUES ('100', '100504');
INSERT INTO `sys_role_menu` VALUES ('100', '100505');
INSERT INTO `sys_role_menu` VALUES ('100', '100601');
INSERT INTO `sys_role_menu` VALUES ('100', '100602');
INSERT INTO `sys_role_menu` VALUES ('100', '100603');
INSERT INTO `sys_role_menu` VALUES ('100', '100604');
INSERT INTO `sys_role_menu` VALUES ('100', '100605');
INSERT INTO `sys_role_menu` VALUES ('100', '200101');
INSERT INTO `sys_role_menu` VALUES ('100', '200102');
INSERT INTO `sys_role_menu` VALUES ('100', '200103');
INSERT INTO `sys_role_menu` VALUES ('100', '200104');
INSERT INTO `sys_role_menu` VALUES ('100', '200105');
INSERT INTO `sys_role_menu` VALUES ('100', '200201');
INSERT INTO `sys_role_menu` VALUES ('100', '200202');
INSERT INTO `sys_role_menu` VALUES ('100', '200203');
INSERT INTO `sys_role_menu` VALUES ('100', '200204');
INSERT INTO `sys_role_menu` VALUES ('100', '200205');
INSERT INTO `sys_role_menu` VALUES ('100', '200221');
INSERT INTO `sys_role_menu` VALUES ('100', '200222');
INSERT INTO `sys_role_menu` VALUES ('100', '200299');
INSERT INTO `sys_role_menu` VALUES ('100', '201300');
INSERT INTO `sys_role_menu` VALUES ('100', '201303');
INSERT INTO `sys_role_menu` VALUES ('101', '200206');
INSERT INTO `sys_role_menu` VALUES ('101', '200207');
INSERT INTO `sys_role_menu` VALUES ('101', '200208');
INSERT INTO `sys_role_menu` VALUES ('101', '200209');
INSERT INTO `sys_role_menu` VALUES ('101', '200210');
INSERT INTO `sys_role_menu` VALUES ('101', '200211');
INSERT INTO `sys_role_menu` VALUES ('101', '200212');
INSERT INTO `sys_role_menu` VALUES ('101', '200213');
INSERT INTO `sys_role_menu` VALUES ('101', '200214');
INSERT INTO `sys_role_menu` VALUES ('101', '200215');
INSERT INTO `sys_role_menu` VALUES ('101', '200216');
INSERT INTO `sys_role_menu` VALUES ('101', '200217');
INSERT INTO `sys_role_menu` VALUES ('101', '200218');
INSERT INTO `sys_role_menu` VALUES ('101', '200219');
INSERT INTO `sys_role_menu` VALUES ('101', '200220');
INSERT INTO `sys_role_menu` VALUES ('101', '200223');
INSERT INTO `sys_role_menu` VALUES ('101', '200224');
INSERT INTO `sys_role_menu` VALUES ('101', '200226');
INSERT INTO `sys_role_menu` VALUES ('101', '200228');
INSERT INTO `sys_role_menu` VALUES ('102', '200206');
INSERT INTO `sys_role_menu` VALUES ('102', '200207');
INSERT INTO `sys_role_menu` VALUES ('102', '200208');
INSERT INTO `sys_role_menu` VALUES ('102', '200209');
INSERT INTO `sys_role_menu` VALUES ('102', '200210');
INSERT INTO `sys_role_menu` VALUES ('102', '200211');
INSERT INTO `sys_role_menu` VALUES ('102', '200212');
INSERT INTO `sys_role_menu` VALUES ('102', '200213');
INSERT INTO `sys_role_menu` VALUES ('102', '200214');
INSERT INTO `sys_role_menu` VALUES ('102', '200215');
INSERT INTO `sys_role_menu` VALUES ('102', '200216');
INSERT INTO `sys_role_menu` VALUES ('102', '200217');
INSERT INTO `sys_role_menu` VALUES ('102', '200218');
INSERT INTO `sys_role_menu` VALUES ('102', '200219');
INSERT INTO `sys_role_menu` VALUES ('102', '200220');
INSERT INTO `sys_role_menu` VALUES ('102', '200223');
INSERT INTO `sys_role_menu` VALUES ('102', '200224');
INSERT INTO `sys_role_menu` VALUES ('102', '200226');
INSERT INTO `sys_role_menu` VALUES ('102', '200228');
INSERT INTO `sys_role_menu` VALUES ('102', '200229');
INSERT INTO `sys_role_menu` VALUES ('102', '200231');
INSERT INTO `sys_role_menu` VALUES ('102', '200232');
INSERT INTO `sys_role_menu` VALUES ('102', '200233');
INSERT INTO `sys_role_menu` VALUES ('102', '200234');
INSERT INTO `sys_role_menu` VALUES ('102', '200235');
INSERT INTO `sys_role_menu` VALUES ('102', '200242');
INSERT INTO `sys_role_menu` VALUES ('102', '200243');
INSERT INTO `sys_role_menu` VALUES ('102', '201339');
INSERT INTO `sys_role_menu` VALUES ('102', '201340');
INSERT INTO `sys_role_menu` VALUES ('103', '1');
INSERT INTO `sys_role_menu` VALUES ('103', '1001');
INSERT INTO `sys_role_menu` VALUES ('103', '1002');
INSERT INTO `sys_role_menu` VALUES ('103', '1003');
INSERT INTO `sys_role_menu` VALUES ('103', '1004');
INSERT INTO `sys_role_menu` VALUES ('103', '1005');
INSERT INTO `sys_role_menu` VALUES ('103', '1006');
INSERT INTO `sys_role_menu` VALUES ('103', '1007');
INSERT INTO `sys_role_menu` VALUES ('103', '100101');
INSERT INTO `sys_role_menu` VALUES ('103', '100102');
INSERT INTO `sys_role_menu` VALUES ('103', '100103');
INSERT INTO `sys_role_menu` VALUES ('103', '100104');
INSERT INTO `sys_role_menu` VALUES ('103', '100105');
INSERT INTO `sys_role_menu` VALUES ('103', '100201');
INSERT INTO `sys_role_menu` VALUES ('103', '100202');
INSERT INTO `sys_role_menu` VALUES ('103', '100203');
INSERT INTO `sys_role_menu` VALUES ('103', '100204');
INSERT INTO `sys_role_menu` VALUES ('103', '100205');
INSERT INTO `sys_role_menu` VALUES ('103', '100301');
INSERT INTO `sys_role_menu` VALUES ('103', '100302');
INSERT INTO `sys_role_menu` VALUES ('103', '100303');
INSERT INTO `sys_role_menu` VALUES ('103', '100304');
INSERT INTO `sys_role_menu` VALUES ('103', '100305');
INSERT INTO `sys_role_menu` VALUES ('103', '100401');
INSERT INTO `sys_role_menu` VALUES ('103', '100402');
INSERT INTO `sys_role_menu` VALUES ('103', '100403');
INSERT INTO `sys_role_menu` VALUES ('103', '100404');
INSERT INTO `sys_role_menu` VALUES ('103', '100405');
INSERT INTO `sys_role_menu` VALUES ('103', '100501');
INSERT INTO `sys_role_menu` VALUES ('103', '100502');
INSERT INTO `sys_role_menu` VALUES ('103', '100503');
INSERT INTO `sys_role_menu` VALUES ('103', '100504');
INSERT INTO `sys_role_menu` VALUES ('103', '100505');
INSERT INTO `sys_role_menu` VALUES ('103', '100601');
INSERT INTO `sys_role_menu` VALUES ('103', '100602');
INSERT INTO `sys_role_menu` VALUES ('103', '100603');
INSERT INTO `sys_role_menu` VALUES ('103', '100604');
INSERT INTO `sys_role_menu` VALUES ('103', '100605');
INSERT INTO `sys_role_menu` VALUES ('103', '100701');
INSERT INTO `sys_role_menu` VALUES ('103', '100702');
INSERT INTO `sys_role_menu` VALUES ('103', '100703');
INSERT INTO `sys_role_menu` VALUES ('103', '100704');
INSERT INTO `sys_role_menu` VALUES ('103', '200289');
INSERT INTO `sys_role_menu` VALUES ('103', '200292');
INSERT INTO `sys_role_menu` VALUES ('103', '200294');
INSERT INTO `sys_role_menu` VALUES ('103', '200295');
INSERT INTO `sys_role_menu` VALUES ('103', '200296');
INSERT INTO `sys_role_menu` VALUES ('103', '200297');
INSERT INTO `sys_role_menu` VALUES ('103', '200298');
INSERT INTO `sys_role_menu` VALUES ('103', '201304');
INSERT INTO `sys_role_menu` VALUES ('103', '201305');
INSERT INTO `sys_role_menu` VALUES ('103', '201306');
INSERT INTO `sys_role_menu` VALUES ('103', '201308');
INSERT INTO `sys_role_menu` VALUES ('103', '201309');
INSERT INTO `sys_role_menu` VALUES ('103', '201310');
INSERT INTO `sys_role_menu` VALUES ('103', '201311');
INSERT INTO `sys_role_menu` VALUES ('103', '201317');
INSERT INTO `sys_role_menu` VALUES ('103', '201318');
INSERT INTO `sys_role_menu` VALUES ('103', '201319');
INSERT INTO `sys_role_menu` VALUES ('103', '201320');
INSERT INTO `sys_role_menu` VALUES ('103', '201321');
INSERT INTO `sys_role_menu` VALUES ('103', '201322');
INSERT INTO `sys_role_menu` VALUES ('103', '201323');
INSERT INTO `sys_role_menu` VALUES ('103', '201324');
INSERT INTO `sys_role_menu` VALUES ('103', '201325');
INSERT INTO `sys_role_menu` VALUES ('103', '201326');
INSERT INTO `sys_role_menu` VALUES ('103', '201327');
INSERT INTO `sys_role_menu` VALUES ('103', '201328');
INSERT INTO `sys_role_menu` VALUES ('103', '201329');
INSERT INTO `sys_role_menu` VALUES ('103', '201330');
INSERT INTO `sys_role_menu` VALUES ('103', '201331');
INSERT INTO `sys_role_menu` VALUES ('103', '201335');
INSERT INTO `sys_role_menu` VALUES ('103', '201339');
INSERT INTO `sys_role_menu` VALUES ('103', '201340');
INSERT INTO `sys_role_menu` VALUES ('103', '201341');
INSERT INTO `sys_role_menu` VALUES ('103', '201342');
INSERT INTO `sys_role_menu` VALUES ('103', '201343');
INSERT INTO `sys_role_menu` VALUES ('103', '201344');
INSERT INTO `sys_role_menu` VALUES ('103', '201345');
INSERT INTO `sys_role_menu` VALUES ('103', '201346');
INSERT INTO `sys_role_menu` VALUES ('103', '201347');
INSERT INTO `sys_role_menu` VALUES ('104', '200289');
INSERT INTO `sys_role_menu` VALUES ('104', '200292');
INSERT INTO `sys_role_menu` VALUES ('104', '200294');
INSERT INTO `sys_role_menu` VALUES ('104', '200295');
INSERT INTO `sys_role_menu` VALUES ('104', '200296');
INSERT INTO `sys_role_menu` VALUES ('104', '201304');
INSERT INTO `sys_role_menu` VALUES ('104', '201305');
INSERT INTO `sys_role_menu` VALUES ('104', '201306');
INSERT INTO `sys_role_menu` VALUES ('104', '201308');
INSERT INTO `sys_role_menu` VALUES ('104', '201309');
INSERT INTO `sys_role_menu` VALUES ('104', '201310');
INSERT INTO `sys_role_menu` VALUES ('104', '201324');
INSERT INTO `sys_role_menu` VALUES ('104', '201325');
INSERT INTO `sys_role_menu` VALUES ('104', '201326');
INSERT INTO `sys_role_menu` VALUES ('104', '201327');
INSERT INTO `sys_role_menu` VALUES ('104', '201333');
INSERT INTO `sys_role_menu` VALUES ('104', '201335');
INSERT INTO `sys_role_menu` VALUES ('104', '201336');
INSERT INTO `sys_role_menu` VALUES ('104', '201337');
INSERT INTO `sys_role_menu` VALUES ('104', '201338');
INSERT INTO `sys_role_menu` VALUES ('104', '201339');
INSERT INTO `sys_role_menu` VALUES ('104', '201340');
INSERT INTO `sys_role_menu` VALUES ('104', '201341');
INSERT INTO `sys_role_menu` VALUES ('104', '201342');
INSERT INTO `sys_role_menu` VALUES ('104', '201343');
INSERT INTO `sys_role_menu` VALUES ('104', '201344');
INSERT INTO `sys_role_menu` VALUES ('104', '201345');
INSERT INTO `sys_role_menu` VALUES ('104', '201346');
INSERT INTO `sys_role_menu` VALUES ('104', '201347');
INSERT INTO `sys_role_menu` VALUES ('105', '200289');
INSERT INTO `sys_role_menu` VALUES ('105', '200292');
INSERT INTO `sys_role_menu` VALUES ('105', '200294');
INSERT INTO `sys_role_menu` VALUES ('105', '200295');
INSERT INTO `sys_role_menu` VALUES ('105', '200296');
INSERT INTO `sys_role_menu` VALUES ('105', '200297');
INSERT INTO `sys_role_menu` VALUES ('105', '200298');
INSERT INTO `sys_role_menu` VALUES ('105', '201304');
INSERT INTO `sys_role_menu` VALUES ('105', '201305');
INSERT INTO `sys_role_menu` VALUES ('105', '201306');
INSERT INTO `sys_role_menu` VALUES ('105', '201308');
INSERT INTO `sys_role_menu` VALUES ('105', '201309');
INSERT INTO `sys_role_menu` VALUES ('105', '201310');
INSERT INTO `sys_role_menu` VALUES ('105', '201311');
INSERT INTO `sys_role_menu` VALUES ('105', '201324');
INSERT INTO `sys_role_menu` VALUES ('105', '201325');
INSERT INTO `sys_role_menu` VALUES ('105', '201326');
INSERT INTO `sys_role_menu` VALUES ('105', '201327');
INSERT INTO `sys_role_menu` VALUES ('105', '201333');
INSERT INTO `sys_role_menu` VALUES ('105', '201335');
INSERT INTO `sys_role_menu` VALUES ('105', '201336');
INSERT INTO `sys_role_menu` VALUES ('105', '201337');
INSERT INTO `sys_role_menu` VALUES ('105', '201338');
INSERT INTO `sys_role_menu` VALUES ('105', '201339');
INSERT INTO `sys_role_menu` VALUES ('105', '201340');
INSERT INTO `sys_role_menu` VALUES ('105', '201341');
INSERT INTO `sys_role_menu` VALUES ('105', '201342');
INSERT INTO `sys_role_menu` VALUES ('105', '201343');
INSERT INTO `sys_role_menu` VALUES ('105', '201344');
INSERT INTO `sys_role_menu` VALUES ('105', '201345');
INSERT INTO `sys_role_menu` VALUES ('105', '201346');
INSERT INTO `sys_role_menu` VALUES ('105', '201347');
INSERT INTO `sys_role_menu` VALUES ('106', '200289');
INSERT INTO `sys_role_menu` VALUES ('106', '200292');
INSERT INTO `sys_role_menu` VALUES ('106', '200294');
INSERT INTO `sys_role_menu` VALUES ('106', '200295');
INSERT INTO `sys_role_menu` VALUES ('106', '200296');
INSERT INTO `sys_role_menu` VALUES ('106', '200297');
INSERT INTO `sys_role_menu` VALUES ('106', '200298');
INSERT INTO `sys_role_menu` VALUES ('106', '201304');
INSERT INTO `sys_role_menu` VALUES ('106', '201305');
INSERT INTO `sys_role_menu` VALUES ('106', '201306');
INSERT INTO `sys_role_menu` VALUES ('106', '201308');
INSERT INTO `sys_role_menu` VALUES ('106', '201309');
INSERT INTO `sys_role_menu` VALUES ('106', '201310');
INSERT INTO `sys_role_menu` VALUES ('106', '201311');
INSERT INTO `sys_role_menu` VALUES ('106', '201317');
INSERT INTO `sys_role_menu` VALUES ('106', '201318');
INSERT INTO `sys_role_menu` VALUES ('106', '201319');
INSERT INTO `sys_role_menu` VALUES ('106', '201320');
INSERT INTO `sys_role_menu` VALUES ('106', '201321');
INSERT INTO `sys_role_menu` VALUES ('106', '201322');
INSERT INTO `sys_role_menu` VALUES ('106', '201324');
INSERT INTO `sys_role_menu` VALUES ('106', '201325');
INSERT INTO `sys_role_menu` VALUES ('106', '201326');
INSERT INTO `sys_role_menu` VALUES ('106', '201327');
INSERT INTO `sys_role_menu` VALUES ('106', '201328');
INSERT INTO `sys_role_menu` VALUES ('106', '201329');
INSERT INTO `sys_role_menu` VALUES ('106', '201330');
INSERT INTO `sys_role_menu` VALUES ('106', '201331');
INSERT INTO `sys_role_menu` VALUES ('106', '201333');
INSERT INTO `sys_role_menu` VALUES ('106', '201335');
INSERT INTO `sys_role_menu` VALUES ('106', '201336');
INSERT INTO `sys_role_menu` VALUES ('106', '201337');
INSERT INTO `sys_role_menu` VALUES ('106', '201338');
INSERT INTO `sys_role_menu` VALUES ('106', '201339');
INSERT INTO `sys_role_menu` VALUES ('106', '201340');
INSERT INTO `sys_role_menu` VALUES ('106', '201341');
INSERT INTO `sys_role_menu` VALUES ('106', '201342');
INSERT INTO `sys_role_menu` VALUES ('106', '201343');
INSERT INTO `sys_role_menu` VALUES ('106', '201344');
INSERT INTO `sys_role_menu` VALUES ('106', '201345');
INSERT INTO `sys_role_menu` VALUES ('106', '201346');
INSERT INTO `sys_role_menu` VALUES ('106', '201347');
INSERT INTO `sys_role_menu` VALUES ('108', '1');
INSERT INTO `sys_role_menu` VALUES ('108', '1001');
INSERT INTO `sys_role_menu` VALUES ('108', '1002');
INSERT INTO `sys_role_menu` VALUES ('108', '1003');
INSERT INTO `sys_role_menu` VALUES ('108', '1004');
INSERT INTO `sys_role_menu` VALUES ('108', '1005');
INSERT INTO `sys_role_menu` VALUES ('108', '1006');
INSERT INTO `sys_role_menu` VALUES ('108', '1007');
INSERT INTO `sys_role_menu` VALUES ('108', '100101');
INSERT INTO `sys_role_menu` VALUES ('108', '100102');
INSERT INTO `sys_role_menu` VALUES ('108', '100103');
INSERT INTO `sys_role_menu` VALUES ('108', '100104');
INSERT INTO `sys_role_menu` VALUES ('108', '100105');
INSERT INTO `sys_role_menu` VALUES ('108', '100201');
INSERT INTO `sys_role_menu` VALUES ('108', '100202');
INSERT INTO `sys_role_menu` VALUES ('108', '100203');
INSERT INTO `sys_role_menu` VALUES ('108', '100204');
INSERT INTO `sys_role_menu` VALUES ('108', '100205');
INSERT INTO `sys_role_menu` VALUES ('108', '100301');
INSERT INTO `sys_role_menu` VALUES ('108', '100302');
INSERT INTO `sys_role_menu` VALUES ('108', '100303');
INSERT INTO `sys_role_menu` VALUES ('108', '100304');
INSERT INTO `sys_role_menu` VALUES ('108', '100305');
INSERT INTO `sys_role_menu` VALUES ('108', '100401');
INSERT INTO `sys_role_menu` VALUES ('108', '100402');
INSERT INTO `sys_role_menu` VALUES ('108', '100403');
INSERT INTO `sys_role_menu` VALUES ('108', '100404');
INSERT INTO `sys_role_menu` VALUES ('108', '100405');
INSERT INTO `sys_role_menu` VALUES ('108', '100501');
INSERT INTO `sys_role_menu` VALUES ('108', '100502');
INSERT INTO `sys_role_menu` VALUES ('108', '100503');
INSERT INTO `sys_role_menu` VALUES ('108', '100504');
INSERT INTO `sys_role_menu` VALUES ('108', '100505');
INSERT INTO `sys_role_menu` VALUES ('108', '100601');
INSERT INTO `sys_role_menu` VALUES ('108', '100602');
INSERT INTO `sys_role_menu` VALUES ('108', '100603');
INSERT INTO `sys_role_menu` VALUES ('108', '100604');
INSERT INTO `sys_role_menu` VALUES ('108', '100605');
INSERT INTO `sys_role_menu` VALUES ('108', '100701');
INSERT INTO `sys_role_menu` VALUES ('108', '100702');
INSERT INTO `sys_role_menu` VALUES ('108', '100703');
INSERT INTO `sys_role_menu` VALUES ('108', '100704');
INSERT INTO `sys_role_menu` VALUES ('108', '200206');
INSERT INTO `sys_role_menu` VALUES ('108', '200207');
INSERT INTO `sys_role_menu` VALUES ('108', '200208');
INSERT INTO `sys_role_menu` VALUES ('108', '200209');
INSERT INTO `sys_role_menu` VALUES ('108', '200210');
INSERT INTO `sys_role_menu` VALUES ('108', '200211');
INSERT INTO `sys_role_menu` VALUES ('108', '200212');
INSERT INTO `sys_role_menu` VALUES ('108', '200213');
INSERT INTO `sys_role_menu` VALUES ('108', '200214');
INSERT INTO `sys_role_menu` VALUES ('108', '200215');
INSERT INTO `sys_role_menu` VALUES ('108', '200216');
INSERT INTO `sys_role_menu` VALUES ('108', '200217');
INSERT INTO `sys_role_menu` VALUES ('108', '200218');
INSERT INTO `sys_role_menu` VALUES ('108', '200219');
INSERT INTO `sys_role_menu` VALUES ('108', '200220');
INSERT INTO `sys_role_menu` VALUES ('108', '200221');
INSERT INTO `sys_role_menu` VALUES ('108', '200222');
INSERT INTO `sys_role_menu` VALUES ('108', '200223');
INSERT INTO `sys_role_menu` VALUES ('108', '200224');
INSERT INTO `sys_role_menu` VALUES ('108', '200226');
INSERT INTO `sys_role_menu` VALUES ('108', '200228');
INSERT INTO `sys_role_menu` VALUES ('108', '200229');
INSERT INTO `sys_role_menu` VALUES ('108', '200231');
INSERT INTO `sys_role_menu` VALUES ('108', '200232');
INSERT INTO `sys_role_menu` VALUES ('108', '200233');
INSERT INTO `sys_role_menu` VALUES ('108', '200234');
INSERT INTO `sys_role_menu` VALUES ('108', '200235');
INSERT INTO `sys_role_menu` VALUES ('108', '200238');
INSERT INTO `sys_role_menu` VALUES ('108', '200239');
INSERT INTO `sys_role_menu` VALUES ('108', '200240');
INSERT INTO `sys_role_menu` VALUES ('108', '200241');
INSERT INTO `sys_role_menu` VALUES ('108', '200242');
INSERT INTO `sys_role_menu` VALUES ('108', '200243');
INSERT INTO `sys_role_menu` VALUES ('108', '200244');
INSERT INTO `sys_role_menu` VALUES ('108', '200249');
INSERT INTO `sys_role_menu` VALUES ('108', '200250');
INSERT INTO `sys_role_menu` VALUES ('108', '200251');
INSERT INTO `sys_role_menu` VALUES ('108', '200254');
INSERT INTO `sys_role_menu` VALUES ('108', '200255');
INSERT INTO `sys_role_menu` VALUES ('108', '200260');
INSERT INTO `sys_role_menu` VALUES ('108', '200261');
INSERT INTO `sys_role_menu` VALUES ('108', '200262');
INSERT INTO `sys_role_menu` VALUES ('108', '200263');
INSERT INTO `sys_role_menu` VALUES ('108', '200264');
INSERT INTO `sys_role_menu` VALUES ('108', '200265');
INSERT INTO `sys_role_menu` VALUES ('108', '200266');
INSERT INTO `sys_role_menu` VALUES ('108', '200267');
INSERT INTO `sys_role_menu` VALUES ('108', '200269');
INSERT INTO `sys_role_menu` VALUES ('108', '200270');
INSERT INTO `sys_role_menu` VALUES ('108', '200271');
INSERT INTO `sys_role_menu` VALUES ('108', '200272');
INSERT INTO `sys_role_menu` VALUES ('108', '200273');
INSERT INTO `sys_role_menu` VALUES ('108', '200274');
INSERT INTO `sys_role_menu` VALUES ('108', '200275');
INSERT INTO `sys_role_menu` VALUES ('108', '200276');
INSERT INTO `sys_role_menu` VALUES ('108', '200277');
INSERT INTO `sys_role_menu` VALUES ('108', '200278');
INSERT INTO `sys_role_menu` VALUES ('108', '200279');
INSERT INTO `sys_role_menu` VALUES ('108', '200289');
INSERT INTO `sys_role_menu` VALUES ('108', '200292');
INSERT INTO `sys_role_menu` VALUES ('108', '200294');
INSERT INTO `sys_role_menu` VALUES ('108', '200295');
INSERT INTO `sys_role_menu` VALUES ('108', '200296');
INSERT INTO `sys_role_menu` VALUES ('108', '200297');
INSERT INTO `sys_role_menu` VALUES ('108', '200298');
INSERT INTO `sys_role_menu` VALUES ('108', '200299');
INSERT INTO `sys_role_menu` VALUES ('108', '201300');
INSERT INTO `sys_role_menu` VALUES ('108', '201302');
INSERT INTO `sys_role_menu` VALUES ('108', '201303');
INSERT INTO `sys_role_menu` VALUES ('108', '201304');
INSERT INTO `sys_role_menu` VALUES ('108', '201305');
INSERT INTO `sys_role_menu` VALUES ('108', '201306');
INSERT INTO `sys_role_menu` VALUES ('108', '201308');
INSERT INTO `sys_role_menu` VALUES ('108', '201309');
INSERT INTO `sys_role_menu` VALUES ('108', '201310');
INSERT INTO `sys_role_menu` VALUES ('108', '201311');
INSERT INTO `sys_role_menu` VALUES ('108', '201312');
INSERT INTO `sys_role_menu` VALUES ('108', '201313');
INSERT INTO `sys_role_menu` VALUES ('108', '201314');
INSERT INTO `sys_role_menu` VALUES ('108', '201315');
INSERT INTO `sys_role_menu` VALUES ('108', '201316');
INSERT INTO `sys_role_menu` VALUES ('108', '201317');
INSERT INTO `sys_role_menu` VALUES ('108', '201318');
INSERT INTO `sys_role_menu` VALUES ('108', '201319');
INSERT INTO `sys_role_menu` VALUES ('108', '201320');
INSERT INTO `sys_role_menu` VALUES ('108', '201321');
INSERT INTO `sys_role_menu` VALUES ('108', '201322');
INSERT INTO `sys_role_menu` VALUES ('108', '201323');
INSERT INTO `sys_role_menu` VALUES ('108', '201324');
INSERT INTO `sys_role_menu` VALUES ('108', '201325');
INSERT INTO `sys_role_menu` VALUES ('108', '201326');
INSERT INTO `sys_role_menu` VALUES ('108', '201327');
INSERT INTO `sys_role_menu` VALUES ('108', '201328');
INSERT INTO `sys_role_menu` VALUES ('108', '201329');
INSERT INTO `sys_role_menu` VALUES ('108', '201330');
INSERT INTO `sys_role_menu` VALUES ('108', '201331');
INSERT INTO `sys_role_menu` VALUES ('108', '201333');
INSERT INTO `sys_role_menu` VALUES ('108', '201335');
INSERT INTO `sys_role_menu` VALUES ('108', '201336');
INSERT INTO `sys_role_menu` VALUES ('108', '201337');
INSERT INTO `sys_role_menu` VALUES ('108', '201338');
INSERT INTO `sys_role_menu` VALUES ('108', '201339');
INSERT INTO `sys_role_menu` VALUES ('108', '201340');
INSERT INTO `sys_role_menu` VALUES ('108', '201341');
INSERT INTO `sys_role_menu` VALUES ('108', '201342');
INSERT INTO `sys_role_menu` VALUES ('108', '201343');
INSERT INTO `sys_role_menu` VALUES ('108', '201344');
INSERT INTO `sys_role_menu` VALUES ('108', '201345');
INSERT INTO `sys_role_menu` VALUES ('108', '201346');
INSERT INTO `sys_role_menu` VALUES ('108', '201347');
INSERT INTO `sys_role_menu` VALUES ('108', '201348');
INSERT INTO `sys_role_menu` VALUES ('108', '201349');
INSERT INTO `sys_role_menu` VALUES ('108', '201350');
INSERT INTO `sys_role_menu` VALUES ('108', '201351');
INSERT INTO `sys_role_menu` VALUES ('108', '201352');
INSERT INTO `sys_role_menu` VALUES ('108', '201353');
INSERT INTO `sys_role_menu` VALUES ('108', '201354');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  `user_name` varchar(30) NOT NULL COMMENT '用户账号',
  `nick_name` varchar(30) NOT NULL COMMENT '用户昵称',
  `user_type` varchar(2) DEFAULT '00' COMMENT '用户类型（00系统用户）',
  `email` varchar(50) DEFAULT '' COMMENT '用户邮箱',
  `phone` varchar(20) DEFAULT '' COMMENT '手机号码',
  `sex` char(1) DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `avatar` varchar(100) DEFAULT '' COMMENT '头像地址',
  `password` varchar(100) DEFAULT '' COMMENT '密码',
  `status` char(1) DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `del_status` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  `login_ip` varchar(50) DEFAULT '' COMMENT '最后登陆IP',
  `login_date` datetime DEFAULT NULL COMMENT '最后登陆时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `operation_id` varchar(64) DEFAULT NULL COMMENT '操作者ID（可以是客服，运营商）',
  `tenant_id` varchar(64) DEFAULT NULL COMMENT '租户ID',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=127 DEFAULT CHARSET=utf8mb4 COMMENT='用户信息';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '102', 'admin', 'admin', '00', 'admin1@qq.com', '13910011001', '0', '', '$2a$10$NjLUrqlvQkkSdtWIypUXRe8AmHo2Qg1.qH/Hyy3kAijKTs47zoW0i', '0', '0', '192.168.99.140', '2020-07-23 12:02:13', '超级管理学', 'admin', '2020-06-06 03:00:00', null, '2021-05-12 14:36:12', '', '');
INSERT INTO `sys_user` VALUES ('116', '207', '0100', '朱彩云-客服经理', '00', '107@qq.com', '18388019063', '0', '', '$2a$10$O55Zd3n9SdjBshfNpsclV.g4KZJv.XuEohd6aRN8mj4GM4teXSHJ6', '0', '0', '', null, '朱彩云-客服经理', null, '2021-04-26 18:52:34', null, '2021-04-29 22:19:25', '828b046f709e460699aaf0c158f18409', '');
INSERT INTO `sys_user` VALUES ('122', '207', '0166', '小玉0166', '00', '10@qq.com', '19995943497', '0', '', '$2a$10$ktDkJatI5pEM4WIFDAFigeGsSxEoQ6S/URZj6LGnrS5fsXE3Wx0yW', '0', '0', '', null, '小玉0166', null, '2021-04-28 15:33:08', null, '2021-04-28 15:33:08', '5cb91c4a8e49454698a232e3974106da', null);
INSERT INTO `sys_user` VALUES ('123', '207', '0168', '小雪0168', '00', '11231@qq.com', '19995941426', '1', '', '$2a$10$N7Ef/2IoAnMQm0NrWn6HS.1OJbp2wZ3UsQ9cpTkuHj1nuV8ZBychS', '0', '0', '', null, '小雪0168', null, '2021-04-29 10:04:23', null, '2021-04-29 10:04:23', 'e82bb5bf66d541298455ec096bd24f63', null);
INSERT INTO `sys_user` VALUES ('124', '207', '0165', '范艳巧-0165', '00', '2331@qq.com', '15116101123', '1', '', '$2a$10$U7y4CXjsa3LjRXbx4jEtEemAzA.LHQqOMDgL1vjWlkiKnKpaZ3.oK', '0', '0', '', null, '范艳巧-0165', null, '2021-04-29 12:05:17', null, '2021-04-29 12:05:17', 'ffa40e24e6c445e69633b1051cb0521d', null);
INSERT INTO `sys_user` VALUES ('125', '102', 'deve', 'deve', '00', 'deve@qq.com', '15116101123', '0', '', '$2a$10$fSoOvOYrk1yovAqH3sFR0OgX/LzG7je/9uZ5ikP..7HW16gG2oKeG', '0', '0', '', null, 'deve开发专用', null, '2021-05-06 21:37:22', null, '2021-06-04 17:03:45', '828b046f709e460699aaf0c158f18409', null);
INSERT INTO `sys_user` VALUES ('126', '203', 'syx', '申云翔', '00', '111@qq.com', '18088121818', '0', '', '$2a$10$WtTyvVGBRW60tc83oz/Wve0k2moRWimNlY9zXq9HkYR76YfKK/0QG', '1', '0', '', null, '申云翔', null, '2021-05-07 13:46:34', null, '2021-06-04 17:15:12', '828b046f709e460699aaf0c158f18409', null);

-- ----------------------------
-- Table structure for sys_user_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_post`;
CREATE TABLE `sys_user_post` (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `post_id` bigint(20) NOT NULL COMMENT '岗位ID',
  PRIMARY KEY (`user_id`,`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户与岗位关联';

-- ----------------------------
-- Records of sys_user_post
-- ----------------------------
INSERT INTO `sys_user_post` VALUES ('1', '2');
INSERT INTO `sys_user_post` VALUES ('105', '3');
INSERT INTO `sys_user_post` VALUES ('106', '3');
INSERT INTO `sys_user_post` VALUES ('107', '3');
INSERT INTO `sys_user_post` VALUES ('108', '5');
INSERT INTO `sys_user_post` VALUES ('109', '5');
INSERT INTO `sys_user_post` VALUES ('110', '5');
INSERT INTO `sys_user_post` VALUES ('111', '2');
INSERT INTO `sys_user_post` VALUES ('112', '2');
INSERT INTO `sys_user_post` VALUES ('113', '8');
INSERT INTO `sys_user_post` VALUES ('114', '5');
INSERT INTO `sys_user_post` VALUES ('115', '2');
INSERT INTO `sys_user_post` VALUES ('115', '3');
INSERT INTO `sys_user_post` VALUES ('115', '4');
INSERT INTO `sys_user_post` VALUES ('115', '5');
INSERT INTO `sys_user_post` VALUES ('116', '9');
INSERT INTO `sys_user_post` VALUES ('117', '5');
INSERT INTO `sys_user_post` VALUES ('118', '3');
INSERT INTO `sys_user_post` VALUES ('120', '10');
INSERT INTO `sys_user_post` VALUES ('121', '10');
INSERT INTO `sys_user_post` VALUES ('122', '10');
INSERT INTO `sys_user_post` VALUES ('123', '10');
INSERT INTO `sys_user_post` VALUES ('124', '10');
INSERT INTO `sys_user_post` VALUES ('125', '2');
INSERT INTO `sys_user_post` VALUES ('126', '2');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户和角色关联';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1');
INSERT INTO `sys_user_role` VALUES ('105', '100');
INSERT INTO `sys_user_role` VALUES ('106', '100');
INSERT INTO `sys_user_role` VALUES ('107', '100');
INSERT INTO `sys_user_role` VALUES ('108', '100');
INSERT INTO `sys_user_role` VALUES ('109', '101');
INSERT INTO `sys_user_role` VALUES ('110', '102');
INSERT INTO `sys_user_role` VALUES ('111', '100');
INSERT INTO `sys_user_role` VALUES ('112', '102');
INSERT INTO `sys_user_role` VALUES ('114', '100');
INSERT INTO `sys_user_role` VALUES ('115', '103');
INSERT INTO `sys_user_role` VALUES ('116', '103');
INSERT INTO `sys_user_role` VALUES ('117', '103');
INSERT INTO `sys_user_role` VALUES ('118', '103');
INSERT INTO `sys_user_role` VALUES ('119', '103');
INSERT INTO `sys_user_role` VALUES ('120', '103');
INSERT INTO `sys_user_role` VALUES ('121', '104');
INSERT INTO `sys_user_role` VALUES ('122', '105');
INSERT INTO `sys_user_role` VALUES ('123', '106');
INSERT INTO `sys_user_role` VALUES ('124', '104');
INSERT INTO `sys_user_role` VALUES ('125', '108');
INSERT INTO `sys_user_role` VALUES ('126', '108');
