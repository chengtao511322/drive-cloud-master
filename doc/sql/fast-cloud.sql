/*
Navicat MySQL Data Transfer

Source Server         : mysql5.0
Source Server Version : 50719
Source Host           : 125.0.8.191:3304
Source Database       : fast-cloud

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2021-02-07 09:26:19
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
INSERT INTO `oauth_client_details` VALUES ('fastcloud', '', '$2a$10$y2hKeELx.z3Sbz.kjQ4wmuiIsv5ZSbUQ1ov4BwFH6ccirP8Knp1uq', 'server', 'password,client_credentials,refresh_token', '', null, '3', '7', null, null);
INSERT INTO `oauth_client_details` VALUES ('web', '', '$2a$10$y2hKeELx.z3Sbz.kjQ4wmuiIsv5ZSbUQ1ov4BwFH6ccirP8Knp1uq', 'server', 'password,refresh_token', '', null, '3600', '7200', null, null);

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
) ENGINE=InnoDB AUTO_INCREMENT=204 DEFAULT CHARSET=utf8mb4 COMMENT='部门';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('1', '学车小王子', '0', '0', null, null, null, null, null, '0', '0', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00', '8d26f5f6cae74532bf0baf37fc8ccd0f');
INSERT INTO `sys_dept` VALUES ('100', '云南昆明公司', '1', '0,1', null, null, null, null, null, '1', '0', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00', 'bbdc1bd499b241daa6fe99063e63a193');
INSERT INTO `sys_dept` VALUES ('101', '人事部', '100', '0,1,100', null, null, null, null, null, '1', '0', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00', null);
INSERT INTO `sys_dept` VALUES ('102', '研发部', '100', '0,1,100', null, null, null, null, null, '2', '0', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00', null);
INSERT INTO `sys_dept` VALUES ('200', '玉溪分公司', '1', '0,1', null, null, null, null, null, '0', '0', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00', null);
INSERT INTO `sys_dept` VALUES ('201', '人事部', '200', '0,1,200', null, null, null, null, null, '1', '0', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00', null);
INSERT INTO `sys_dept` VALUES ('202', '研发部', '200', '0,1,200', null, null, null, null, null, '2', '0', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00', null);
INSERT INTO `sys_dept` VALUES ('203', '运营中心', '100', '0,1,100', '闵志强 ', '18888888888', null, null, null, '1', '0', null, null, null, null, null);

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
  PRIMARY KEY (`dict_item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=263 DEFAULT CHARSET=utf8mb4 COMMENT='字典数据';

-- ----------------------------
-- Records of sys_dict_item
-- ----------------------------
INSERT INTO `sys_dict_item` VALUES ('1', 'sys_user_sex', '男', '0', '0', null, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_dict_item` VALUES ('2', 'sys_user_sex', '女', '1', '0', null, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_dict_item` VALUES ('3', 'sys_user_sex', '未知', '2', '0', null, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_dict_item` VALUES ('5', 'sys_show_hide', '显示', '0', '0', null, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_dict_item` VALUES ('6', 'sys_show_hide', '隐藏', '1', '0', null, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_dict_item` VALUES ('7', 'sys_normal_disable', '正常', '0', '0', null, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_dict_item` VALUES ('8', 'sys_normal_disable', '停用', '1', '0', null, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_dict_item` VALUES ('9', 'sys_common_status', '成功', '0', '0', null, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_dict_item` VALUES ('10', 'sys_common_status', '失败', '1', '0', null, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_dict_item` VALUES ('11', 'sys_oper_type', '查询', '1', '0', null, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_dict_item` VALUES ('12', 'sys_oper_type', '新增', '2', '0', null, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_dict_item` VALUES ('13', 'sys_oper_type', '修改', '3', '0', null, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_dict_item` VALUES ('14', 'sys_oper_type', '删除', '4', '0', null, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_dict_item` VALUES ('15', 'sys_oper_type', '导入', '5', '0', null, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_dict_item` VALUES ('16', 'sys_oper_type', '导出', '6', '0', null, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_dict_item` VALUES ('102', 'sys_user_initPassword', '默认密码', '123', '0', null, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_dict_item` VALUES ('103', 'article_state', '已发布', '0', '0', null, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_dict_item` VALUES ('104', 'article_state', '草稿', '1', '0', null, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_dict_item` VALUES ('105', 'article_state', '已删除', '2', '0', null, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_dict_item` VALUES ('106', 'activity_type', '学车报名', '1', '0', '学车报名', null, '2021-01-13 10:04:42', null, null);
INSERT INTO `sys_dict_item` VALUES ('107', 'activity_type', '考试报名', '2', '0', '考试报名', null, '2021-01-13 10:04:42', null, null);
INSERT INTO `sys_dict_item` VALUES ('108', 'activity_type', '常规训练', '3', '0', '常规训练', null, '2021-01-13 10:04:42', null, null);
INSERT INTO `sys_dict_item` VALUES ('109', 'activity_type', '考试训练', '4', '0', '考试训练', null, '2021-01-13 10:04:42', null, null);
INSERT INTO `sys_dict_item` VALUES ('110', 'activity_type', '大礼包活动', '5', '0', '大礼包活动', null, '2021-01-13 10:04:42', null, null);
INSERT INTO `sys_dict_item` VALUES ('111', 'coupon_type', '全场通用券', '0', '0', '全场通用券', null, '2021-01-13 10:04:42', null, null);
INSERT INTO `sys_dict_item` VALUES ('112', 'coupon_type', '会员赠券', '1', '0', '会员赠券', null, '2021-01-13 10:04:42', null, null);
INSERT INTO `sys_dict_item` VALUES ('113', 'coupon_type', '报名抵用券', '2', '0', '报名抵用券', null, '2021-01-13 10:04:42', null, null);
INSERT INTO `sys_dict_item` VALUES ('114', 'coupon_type', '注册赠券', '3', '0', '注册赠券', null, '2021-01-13 10:04:42', null, null);
INSERT INTO `sys_dict_item` VALUES ('115', 'coupon_type', '练车抵用券', '4', '0', '练车抵用券', null, '2021-01-13 10:04:42', null, null);
INSERT INTO `sys_dict_item` VALUES ('116', 'coupon_type', '考试抵用劵', '5', '0', '考试抵用劵', null, '2021-01-13 10:04:42', null, null);
INSERT INTO `sys_dict_item` VALUES ('117', 'delete_status', '正常', '0', '0', '正常', null, '2021-01-13 10:04:42', null, null);
INSERT INTO `sys_dict_item` VALUES ('118', 'delete_status', '已删除', '1', '0', '已删除', null, '2021-01-13 10:04:42', null, null);
INSERT INTO `sys_dict_item` VALUES ('119', 'coupon_use_status', '未领取', '1', '0', null, null, '2021-01-13 10:04:42', null, null);
INSERT INTO `sys_dict_item` VALUES ('120', 'coupon_use_status', '已领取', '2', '0', null, null, '2021-01-13 10:04:42', null, null);
INSERT INTO `sys_dict_item` VALUES ('121', 'coupon_use_status', '已使用', '3', '0', null, null, '2021-01-13 10:04:42', null, null);
INSERT INTO `sys_dict_item` VALUES ('122', 'coupon_use_status', '已过期', '4', '0', null, null, '2021-01-13 10:04:42', null, null);
INSERT INTO `sys_dict_item` VALUES ('123', 'service_item_type', '学车报名', '1', '0', 'service_item_type', null, '2021-01-13 10:04:42', null, null);
INSERT INTO `sys_dict_item` VALUES ('124', 'service_item_type', '科目一', '2', '0', '科目一', null, '2021-01-13 10:04:42', null, null);
INSERT INTO `sys_dict_item` VALUES ('125', 'service_item_type', '科目二', '3', '0', '科目二', null, '2021-01-13 10:04:42', null, null);
INSERT INTO `sys_dict_item` VALUES ('126', 'service_item_type', '科目三', '4', '0', '科目三', null, '2021-01-13 10:04:42', null, null);
INSERT INTO `sys_dict_item` VALUES ('127', 'service_item_type', '科目四', '5', '0', '科目三安全理论', null, '2021-01-13 18:04:42', null, '2021-01-20 16:26:03');
INSERT INTO `sys_dict_item` VALUES ('130', 'enable_status', '启用', '1', '0', null, null, '2021-01-13 10:04:42', null, null);
INSERT INTO `sys_dict_item` VALUES ('131', 'enable_status', '停用', '0', '0', null, null, '2021-01-13 10:04:42', null, null);
INSERT INTO `sys_dict_item` VALUES ('132', 'drive_type', 'c1(手动挡)', '1', '0', null, null, '2021-01-13 10:04:42', null, null);
INSERT INTO `sys_dict_item` VALUES ('133', 'drive_type', 'c2(自动挡)', '2', '0', null, null, '2021-01-13 10:04:42', null, null);
INSERT INTO `sys_dict_item` VALUES ('134', 'class_type', '自主预约', '1', '0', null, null, '2021-01-13 10:04:42', null, null);
INSERT INTO `sys_dict_item` VALUES ('135', 'class_type', 'VIP特训班 ', '2', '0', null, null, '2021-01-13 10:04:42', null, null);
INSERT INTO `sys_dict_item` VALUES ('136', 'class_type', 'VIP普通班', '3', '0', null, null, '2021-01-13 10:04:42', null, null);
INSERT INTO `sys_dict_item` VALUES ('137', 'class_type', ' VIP包过班', '4', '0', null, null, '2021-01-13 10:04:42', null, null);
INSERT INTO `sys_dict_item` VALUES ('138', 'class_type', '四人普通班', '0', '0', null, null, '2021-01-13 10:04:42', null, null);
INSERT INTO `sys_dict_item` VALUES ('139', 'class_type', '1对1王者班', '5', '0', null, null, '2021-01-13 10:04:42', null, null);
INSERT INTO `sys_dict_item` VALUES ('140', 'class_type', '1对1钻石班', '6', '0', null, null, '2021-01-13 10:04:42', null, null);
INSERT INTO `sys_dict_item` VALUES ('141', 'class_type', '1对1黄金班', '7', '0', null, null, '2021-01-13 10:04:42', null, null);
INSERT INTO `sys_dict_item` VALUES ('142', 'class_type', '1对1白金班', '8', '0', null, null, '2021-01-13 10:04:42', null, null);
INSERT INTO `sys_dict_item` VALUES ('143', 'enable_status_old', '启用', '1', '0', '启用', null, '2021-01-13 10:04:42', null, null);
INSERT INTO `sys_dict_item` VALUES ('144', 'enable_status_old', '停用', '2', '0', '停用', null, '2021-01-13 10:04:42', null, null);
INSERT INTO `sys_dict_item` VALUES ('145', 'yes_no', '是', '1', '0', null, null, '2021-01-13 10:04:42', null, null);
INSERT INTO `sys_dict_item` VALUES ('146', 'yes_no', '否', '0', '0', null, null, '2021-01-13 10:04:42', null, null);
INSERT INTO `sys_dict_item` VALUES ('147', 'serive_class_status', '未发布', '0', '0', null, null, '2021-01-13 10:16:16', null, '2021-01-13 10:16:16');
INSERT INTO `sys_dict_item` VALUES ('148', 'serive_class_status', '已发布', '1', '0', null, null, '2021-01-13 10:21:21', null, '2021-01-13 10:21:21');
INSERT INTO `sys_dict_item` VALUES ('149', 'serive_class_status', '已下架', '2', '0', null, null, '2021-01-13 10:21:28', null, '2021-01-13 10:21:28');
INSERT INTO `sys_dict_item` VALUES ('150', 'service_class_status', '未发布', '0', '0', null, null, '2021-01-13 11:23:28', null, '2021-01-13 11:23:28');
INSERT INTO `sys_dict_item` VALUES ('151', 'service_class_status', '发布', '1', '0', null, null, '2021-01-13 19:23:34', null, '2021-01-13 19:23:34');
INSERT INTO `sys_dict_item` VALUES ('152', 'service_class_status', '下架', '2', '0', null, null, '2021-01-13 19:23:40', null, '2021-01-13 19:23:40');
INSERT INTO `sys_dict_item` VALUES ('153', 'class_service_unit', '个', '1', '0', null, null, '2021-01-14 15:04:44', null, '2021-01-14 15:04:44');
INSERT INTO `sys_dict_item` VALUES ('154', 'class_service_unit', '次', '2', '0', null, null, '2021-01-14 23:05:52', null, '2021-01-14 23:05:52');
INSERT INTO `sys_dict_item` VALUES ('155', 'class_service_unit', '公里', '3', '0', null, null, '2021-01-14 23:05:59', null, '2021-01-14 23:05:59');
INSERT INTO `sys_dict_item` VALUES ('156', 'service_provide', '平台', '1', '0', null, null, '2021-01-14 15:07:27', null, '2021-01-14 15:07:27');
INSERT INTO `sys_dict_item` VALUES ('157', 'service_provide', '教练', '2', '0', null, null, '2021-01-14 15:07:35', null, '2021-01-14 15:07:35');
INSERT INTO `sys_dict_item` VALUES ('158', 'slideshow_location', '首页轮播', '0', '0', '首页', null, '2021-01-15 01:01:30', null, '2021-01-15 01:01:30');
INSERT INTO `sys_dict_item` VALUES ('159', 'slideshow_location', '学车报名轮播', '1', '0', '学车报名轮播', null, '2021-01-14 17:01:48', null, '2021-01-14 17:01:48');
INSERT INTO `sys_dict_item` VALUES ('160', 'slideshow_location', '考试报名轮播', '2', '0', '考试报名轮播', null, '2021-01-14 17:02:17', null, '2021-01-14 17:02:17');
INSERT INTO `sys_dict_item` VALUES ('161', 'slideshow_location', '常规训练轮播', '3', '0', '常规训练轮播', null, '2021-01-14 17:02:34', null, '2021-01-14 17:02:34');
INSERT INTO `sys_dict_item` VALUES ('162', 'slideshow_location', '考试训练轮播', '4', '0', '考试训练轮播', null, '2021-01-14 17:02:51', null, '2021-01-14 17:02:51');
INSERT INTO `sys_dict_item` VALUES ('163', 'student_enable_status_old', '正常', '1', '0', null, null, '2021-01-28 17:05:35', null, '2021-01-28 17:05:35');
INSERT INTO `sys_dict_item` VALUES ('164', 'student_enable_status_old', '停用', '2', '0', null, null, '2021-01-28 17:06:15', null, '2021-01-28 17:06:15');
INSERT INTO `sys_dict_item` VALUES ('165', 'promotion_type', '扫码', '1', '0', null, null, '2021-01-28 17:39:10', null, '2021-01-28 17:39:10');
INSERT INTO `sys_dict_item` VALUES ('166', 'promotion_type', '线上推广', '2', '0', null, null, '2021-01-28 17:39:17', null, '2021-01-28 17:39:17');
INSERT INTO `sys_dict_item` VALUES ('168', 'subject_type', '科目一', '1', '0', null, null, '2021-01-30 15:37:36', null, '2021-01-30 15:37:36');
INSERT INTO `sys_dict_item` VALUES ('169', 'subject_type', '科目二', '2', '0', null, null, '2021-01-30 15:37:42', null, '2021-01-30 15:37:42');
INSERT INTO `sys_dict_item` VALUES ('170', 'subject_type', '科目三', '3', '0', null, null, '2021-01-30 15:37:48', null, '2021-01-30 15:37:48');
INSERT INTO `sys_dict_item` VALUES ('171', 'subject_type', '科目四', '4', '0', null, null, '2021-01-30 15:37:55', null, '2021-01-30 15:37:55');
INSERT INTO `sys_dict_item` VALUES ('172', 'subject_type', '科目二/三', '5', '0', null, null, '2021-01-30 15:38:09', null, '2021-01-30 15:38:09');
INSERT INTO `sys_dict_item` VALUES ('173', 'service_type', '报名客服(线上客服)', '1', '0', null, null, '2021-01-30 17:23:22', null, '2021-01-30 17:23:22');
INSERT INTO `sys_dict_item` VALUES ('174', 'service_type', '线下客服', '2', '0', null, null, '2021-01-30 17:23:27', null, '2021-01-30 17:23:27');
INSERT INTO `sys_dict_item` VALUES ('175', 'service_type', '超级管理员客服', '3', '0', null, null, '2021-01-30 17:23:35', null, '2021-01-30 17:23:35');
INSERT INTO `sys_dict_item` VALUES ('176', 'service_type', '考试客服（线上客服）', '4', '0', null, null, '2021-01-30 17:23:41', null, '2021-01-30 17:23:41');
INSERT INTO `sys_dict_item` VALUES ('177', 'enroll_status', '提交报名', '1', '0', null, null, '2021-02-01 10:25:48', null, '2021-02-01 10:25:48');
INSERT INTO `sys_dict_item` VALUES ('178', 'enroll_status', '已联系待支付', '2', '0', null, null, '2021-02-01 10:25:55', null, '2021-02-01 10:25:55');
INSERT INTO `sys_dict_item` VALUES ('179', 'enroll_status', '已支付待备案', '3', '0', null, null, '2021-02-01 10:26:24', null, '2021-02-01 10:26:24');
INSERT INTO `sys_dict_item` VALUES ('180', 'enroll_status', '支付失败', '5', '0', null, null, '2021-02-01 10:26:59', null, '2021-02-01 10:26:59');
INSERT INTO `sys_dict_item` VALUES ('181', 'enroll_status', '报名完成', '6', '0', null, null, '2021-02-01 10:27:06', null, '2021-02-01 10:27:06');
INSERT INTO `sys_dict_item` VALUES ('182', 'enroll_status', '报名取消', '7', '0', null, null, '2021-02-01 10:27:13', null, '2021-02-01 10:27:13');
INSERT INTO `sys_dict_item` VALUES ('183', 'enroll_status', '报名失败', '8', '0', null, null, '2021-02-01 10:27:19', null, '2021-02-01 10:27:19');
INSERT INTO `sys_dict_item` VALUES ('184', 'enroll_status', '已退款', '9', '0', null, null, '2021-02-01 10:27:25', null, '2021-02-01 10:27:25');
INSERT INTO `sys_dict_item` VALUES ('185', 'enroll_status', '自动报名完成', '10', '0', null, null, '2021-02-01 10:27:33', null, '2021-02-01 10:27:33');
INSERT INTO `sys_dict_item` VALUES ('186', 'enroll_status', '自动报名待审核', '11', '0', null, null, '2021-02-01 10:27:45', null, '2021-02-01 10:27:45');
INSERT INTO `sys_dict_item` VALUES ('187', 'enroll_status', '已备案待审核', '12', '0', null, null, '2021-02-01 10:27:56', null, '2021-02-01 10:27:56');
INSERT INTO `sys_dict_item` VALUES ('188', 'enroll_status', '密码已提交待审核', '13', '0', null, null, '2021-02-01 10:28:05', null, '2021-02-01 10:28:05');
INSERT INTO `sys_dict_item` VALUES ('189', 'enroll_status', '退款处理中', '14', '0', null, null, '2021-02-01 11:09:21', null, '2021-02-01 11:09:21');
INSERT INTO `sys_dict_item` VALUES ('190', 'enroll_status', '已升班', '15', '0', null, null, '2021-02-01 11:09:32', null, '2021-02-01 11:09:32');
INSERT INTO `sys_dict_item` VALUES ('191', 'enroll_status', '已升班待支付', '16', '0', null, null, '2021-02-01 11:09:40', null, '2021-02-01 11:09:40');
INSERT INTO `sys_dict_item` VALUES ('192', 'sex_old', '女', '0', '0', null, null, '2021-02-01 16:35:34', null, '2021-02-01 16:35:34');
INSERT INTO `sys_dict_item` VALUES ('193', 'sex_old', '男', '1', '0', null, null, '2021-02-01 16:35:40', null, '2021-02-01 16:35:40');
INSERT INTO `sys_dict_item` VALUES ('194', 'is_show', '隐藏', '0', '0', '隐藏', null, '2021-02-02 09:37:50', null, '2021-02-02 09:37:50');
INSERT INTO `sys_dict_item` VALUES ('195', 'is_show', '显示', '1', '0', '显示', null, '2021-02-02 09:38:01', null, '2021-02-02 09:38:01');
INSERT INTO `sys_dict_item` VALUES ('196', 'is_open', '是', '1', '0', '开放', null, '2021-02-02 10:42:29', null, '2021-02-02 10:42:29');
INSERT INTO `sys_dict_item` VALUES ('197', 'is_open', '否', '0', '0', '不开放', null, '2021-02-02 10:43:00', null, '2021-02-02 10:43:00');
INSERT INTO `sys_dict_item` VALUES ('198', 'from_platform', '未知', '0', '0', '未知', null, '2021-02-02 11:07:59', null, '2021-02-02 11:07:59');
INSERT INTO `sys_dict_item` VALUES ('199', 'from_platform', '安卓', '1', '0', '安卓', null, '2021-02-02 11:09:36', null, '2021-02-02 11:09:36');
INSERT INTO `sys_dict_item` VALUES ('200', 'from_platform', 'ios', '2', '0', 'ios', null, '2021-02-02 11:10:07', null, '2021-02-02 11:10:07');
INSERT INTO `sys_dict_item` VALUES ('201', 'from_platform', 'WEB', '3', '0', 'WEB', null, '2021-02-02 11:10:43', null, '2021-02-02 11:10:43');
INSERT INTO `sys_dict_item` VALUES ('202', 'from_platform', '微信', '4', '0', '微信', null, '2021-02-02 11:12:28', null, '2021-02-02 11:12:28');
INSERT INTO `sys_dict_item` VALUES ('203', 'from_platform', 'PC-WEB', '5', '0', 'PC-WEB', null, '2021-02-02 11:13:04', null, '2021-02-02 11:13:04');
INSERT INTO `sys_dict_item` VALUES ('204', 'from_platform', '桌面版Linux', '6', '0', '桌面版Linux', null, '2021-02-02 11:13:29', null, '2021-02-02 11:13:29');
INSERT INTO `sys_dict_item` VALUES ('205', 'from_platform', '桌面macOS', '7', '0', '桌面macOS', null, '2021-02-02 11:13:50', null, '2021-02-02 11:13:50');
INSERT INTO `sys_dict_item` VALUES ('206', 'from_platform', '桌面Windows', '8', '0', '桌面Windows', null, '2021-02-02 11:14:13', null, '2021-02-02 11:14:13');
INSERT INTO `sys_dict_item` VALUES ('207', 'video_type', '科目一', '1', '0', '科目一', null, '2021-02-02 11:28:44', null, '2021-02-02 11:28:44');
INSERT INTO `sys_dict_item` VALUES ('208', 'video_type', '科目二', '2', '0', '科目二', null, '2021-02-02 11:29:36', null, '2021-02-02 11:29:36');
INSERT INTO `sys_dict_item` VALUES ('209', 'video_type', '科目三', '3', '0', '科目三', null, '2021-02-02 11:29:59', null, '2021-02-02 11:29:59');
INSERT INTO `sys_dict_item` VALUES ('210', 'video_type', '科目四', '4', '0', '科目四', null, '2021-02-02 11:30:57', null, '2021-02-02 11:30:57');
INSERT INTO `sys_dict_item` VALUES ('211', 'video_type', '所有', '0', '0', '所有', null, '2021-02-02 11:31:34', null, '2021-02-02 11:31:34');
INSERT INTO `sys_dict_item` VALUES ('212', 'video_delete_type', '未删除', '0', '0', '未删除', null, '2021-02-02 11:38:00', null, '2021-02-02 11:38:00');
INSERT INTO `sys_dict_item` VALUES ('213', 'video_delete_type', '已删除', '1', '0', '已删除', null, '2021-02-02 11:38:20', null, '2021-02-02 11:38:20');
INSERT INTO `sys_dict_item` VALUES ('214', 'video_send_type', '未发布', '0', '0', '未发布', null, '2021-02-02 11:40:41', null, '2021-02-02 11:40:41');
INSERT INTO `sys_dict_item` VALUES ('215', 'video_send_type', '已发布', '1', '0', '已发布', null, '2021-02-02 11:41:03', null, '2021-02-02 11:41:03');
INSERT INTO `sys_dict_item` VALUES ('216', 'coach_type', '待审', '0', '0', null, null, '2021-02-02 15:05:09', null, '2021-02-02 15:05:09');
INSERT INTO `sys_dict_item` VALUES ('217', 'coach_type', '驳回', '1', '0', null, null, '2021-02-02 15:05:15', null, '2021-02-02 15:05:15');
INSERT INTO `sys_dict_item` VALUES ('218', 'coach_type', '正常', '2', '0', null, null, '2021-02-02 15:05:21', null, '2021-02-02 15:05:21');
INSERT INTO `sys_dict_item` VALUES ('219', 'coach_type', '禁用', '3', '0', null, null, '2021-02-02 15:05:27', null, '2021-02-02 15:05:27');
INSERT INTO `sys_dict_item` VALUES ('220', 'coach_type', '未提交', '4', '0', null, null, '2021-02-02 15:05:35', null, '2021-02-02 15:05:35');
INSERT INTO `sys_dict_item` VALUES ('221', 'coach_type', '保险预警期', '5', '0', null, null, '2021-02-02 15:05:41', null, '2021-02-02 15:05:41');
INSERT INTO `sys_dict_item` VALUES ('222', 'order_type', '学车报名', '1', '0', null, null, '2021-02-02 15:09:38', null, '2021-02-02 15:09:38');
INSERT INTO `sys_dict_item` VALUES ('223', 'order_type', '考试报名', '2', '0', null, null, '2021-02-02 15:09:47', null, '2021-02-02 15:09:47');
INSERT INTO `sys_dict_item` VALUES ('224', 'order_type', '常规训练', '3', '0', null, null, '2021-02-02 15:09:53', null, '2021-02-02 15:09:53');
INSERT INTO `sys_dict_item` VALUES ('225', 'order_type', '考试训练', '4', '0', null, null, '2021-02-02 15:10:04', null, '2021-02-02 15:10:04');
INSERT INTO `sys_dict_item` VALUES ('227', 'order_type', '课时大礼包', '5', '0', null, null, '2021-02-02 15:10:14', null, '2021-02-02 15:10:14');
INSERT INTO `sys_dict_item` VALUES ('228', 'order_status', '待支付', '1', '0', null, null, '2021-02-02 15:13:02', null, '2021-02-02 15:13:02');
INSERT INTO `sys_dict_item` VALUES ('229', 'order_status', '支付成功', '2', '0', null, null, '2021-02-02 15:13:07', null, '2021-02-02 15:13:07');
INSERT INTO `sys_dict_item` VALUES ('230', 'order_status', '支付处理中', '3', '0', null, null, '2021-02-02 15:13:12', null, '2021-02-02 15:13:12');
INSERT INTO `sys_dict_item` VALUES ('231', 'order_status', '支付失败', '4', '0', null, null, '2021-02-02 15:13:17', null, '2021-02-02 15:13:17');
INSERT INTO `sys_dict_item` VALUES ('232', 'order_status', '已取消', '5', '0', null, null, '2021-02-02 15:13:23', null, '2021-02-02 15:13:23');
INSERT INTO `sys_dict_item` VALUES ('233', 'order_status', '待评价', '6', '0', null, null, '2021-02-02 15:13:29', null, '2021-02-02 15:13:29');
INSERT INTO `sys_dict_item` VALUES ('234', 'order_status', '已评价', '7', '0', null, null, '2021-02-02 15:13:37', null, '2021-02-02 15:13:37');
INSERT INTO `sys_dict_item` VALUES ('235', 'order_status', '退款成功', '8', '0', null, null, '2021-02-02 15:13:44', null, '2021-02-02 15:13:44');
INSERT INTO `sys_dict_item` VALUES ('236', 'order_status', '退款处理中', '9', '0', null, null, '2021-02-02 15:13:50', null, '2021-02-02 15:13:50');
INSERT INTO `sys_dict_item` VALUES ('237', 'pay_type', '支付宝', '1', '0', null, null, '2021-02-02 15:14:50', null, '2021-02-02 15:14:50');
INSERT INTO `sys_dict_item` VALUES ('238', 'pay_type', '微信', '2', '0', null, null, '2021-02-02 15:14:56', null, '2021-02-02 15:14:56');
INSERT INTO `sys_dict_item` VALUES ('239', 'pay_type', '优惠卷支付', '3', '0', null, null, '2021-02-02 15:15:01', null, '2021-02-02 15:15:01');
INSERT INTO `sys_dict_item` VALUES ('240', 'pay_type', 'vip支付', '4', '0', null, null, '2021-02-02 15:15:06', null, '2021-02-02 15:15:06');
INSERT INTO `sys_dict_item` VALUES ('241', 'pay_type', '0元支付', '5', '0', null, null, '2021-02-02 15:15:13', null, '2021-02-02 15:15:13');
INSERT INTO `sys_dict_item` VALUES ('242', 'pay_type', '公众号微信支付', '6', '0', null, null, '2021-02-02 15:15:20', null, '2021-02-02 15:15:20');
INSERT INTO `sys_dict_item` VALUES ('243', 'test_enroll_status', '提交预约', '1', '0', null, null, '2021-02-03 10:00:25', null, '2021-02-03 10:00:25');
INSERT INTO `sys_dict_item` VALUES ('244', 'test_enroll_status', '支付成功', '2', '0', null, null, '2021-02-03 10:00:31', null, '2021-02-03 10:00:31');
INSERT INTO `sys_dict_item` VALUES ('245', 'test_enroll_status', '支付失败', '3', '0', null, null, '2021-02-03 10:00:39', null, '2021-02-03 10:00:39');
INSERT INTO `sys_dict_item` VALUES ('246', 'test_enroll_status', '报名失败', '4', '0', null, null, '2021-02-03 10:00:44', null, '2021-02-03 10:00:44');
INSERT INTO `sys_dict_item` VALUES ('247', 'test_enroll_status', '预约成功', '5', '0', null, null, '2021-02-03 10:00:51', null, '2021-02-03 10:00:51');
INSERT INTO `sys_dict_item` VALUES ('248', 'test_enroll_status', '报名取消', '6', '0', null, null, '2021-02-03 10:00:57', null, '2021-02-03 10:00:57');
INSERT INTO `sys_dict_item` VALUES ('249', 'test_enroll_status', '考试完成', '7', '0', null, null, '2021-02-03 10:01:04', null, '2021-02-03 10:01:04');
INSERT INTO `sys_dict_item` VALUES ('250', 'test_enroll_status', '考试通过', '8', '0', null, null, '2021-02-03 10:01:09', null, '2021-02-03 10:01:09');
INSERT INTO `sys_dict_item` VALUES ('251', 'test_enroll_status', '考试不通过', '9', '0', null, null, '2021-02-03 10:01:17', null, '2021-02-03 10:01:17');
INSERT INTO `sys_dict_item` VALUES ('252', 'test_enroll_status', '申请中', '10', '0', null, null, '2021-02-03 10:01:23', null, '2021-02-03 10:01:23');
INSERT INTO `sys_dict_item` VALUES ('253', 'test_enroll_status', '退款处理中', '11', '0', null, null, '2021-02-03 10:01:28', null, '2021-02-03 10:01:28');
INSERT INTO `sys_dict_item` VALUES ('254', 'test_enroll_status', '退款成功', '12', '0', null, null, '2021-02-03 10:01:36', null, '2021-02-03 10:01:36');
INSERT INTO `sys_dict_item` VALUES ('255', 'car_type', '公车', '1', '0', '公车', null, '2021-02-03 11:25:32', null, '2021-02-03 11:25:32');
INSERT INTO `sys_dict_item` VALUES ('256', 'car_type', '私车', '2', '0', '私车', null, '2021-02-03 11:26:00', null, '2021-02-03 11:26:00');
INSERT INTO `sys_dict_item` VALUES ('257', 'auto_send_class', '是', '1', '0', '是', null, '2021-02-04 09:59:17', null, '2021-02-04 09:59:17');
INSERT INTO `sys_dict_item` VALUES ('258', 'auto_send_class', '否', '2', '0', '否', null, '2021-02-04 09:59:47', null, '2021-02-04 09:59:47');
INSERT INTO `sys_dict_item` VALUES ('259', 'vip_coach', '是', '1', '0', '是', null, '2021-02-04 10:06:27', null, '2021-02-04 10:06:27');
INSERT INTO `sys_dict_item` VALUES ('260', 'vip_coach', '否', '0', '0', '否', null, '2021-02-04 10:06:49', null, '2021-02-04 10:06:49');
INSERT INTO `sys_dict_item` VALUES ('261', 'time_status', '已发布', '1', '0', '正常', null, '2021-02-05 11:05:00', null, '2021-02-05 11:05:00');
INSERT INTO `sys_dict_item` VALUES ('262', 'time_status', '未发布', '2', '0', '停用', null, '2021-02-05 11:05:28', null, '2021-02-05 11:05:28');

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
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COMMENT='字典类型';

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
) ENGINE=InnoDB AUTO_INCREMENT=200264 DEFAULT CHARSET=utf8mb4 COMMENT='菜单权限';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '系统管理', '', '1', 'system', '0', null, '1', '0', '0', 'system', null, '1', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES ('2', '系统监控', 'marketing:coupon:query', '1', 'monitor', '0', null, '1', '0', '0', 'monitor', null, '2', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
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
INSERT INTO `sys_menu` VALUES ('200209', '活动促销新增', 'activity:add', '2', 'addActivity', '200206', 'promotion/activity/addActivity', '1', '1', '0', 'cascader', null, '2', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('200210', '推广人员设置', 'deductSetting', '2', 'deductSetting', '200206', 'promotion/activity/deductSetting', '1', '1', '0', 'select', null, '3', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('200211', '优惠券管理', 'coupon', '2', 'coupon', '200206', 'promotion/coupon/couponList', '1', '0', '0', 'job', null, '1', 'admin', '2020-06-06 11:00:00', 'admin', '2020-06-06 11:00:00');
INSERT INTO `sys_menu` VALUES ('200212', '查询', 'marketing:coupon:query', '3', '', '200211', '#', '1', '0', '0', '#', null, '1', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES ('200213', '新增', 'marketing:coupon:add', '3', '', '200211', '#', '1', '0', '0', '#', null, '1', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES ('200214', '修改', 'marketing:coupon:edit', '3', '', '200211', '#', '1', '0', '0', '#', null, '1', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES ('200215', '删除', 'marketing:coupon:delete', '3', '', '200211', '#', '1', '0', '0', '#', null, '1', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES ('200216', '导出', 'marketing:coupon:export', '3', '', '200211', '#', '1', '0', '0', '#', null, '1', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES ('200217', '优惠券添加', 'promotion:couponAdd', '2', 'couponAdd', '200206', 'promotion/coupon/couponAdd', '1', '1', '0', 'cascader', null, '2', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('200218', '优惠券发劵', 'promotion:couponTemplate', '2', 'couponTemplate', '200206', 'promotion/coupon/couponTemplate', '1', '1', '0', 'component', null, '6', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('200219', '渠道经理设置', 'channelManagerSetting', '2', 'channelManagerSetting', '200206', 'promotion/activity/channelManagerSetting', '1', '1', '0', 'chart', null, '12', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('200220', '活动参加人员', 'activityApply', '2', 'activityApply', '200206', 'promotion/activity/activityApply', '1', '1', '0', 'date', null, '3', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('200221', '基础管理', null, '1', '', '0', null, '1', '0', '0', 'education', null, '1', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('200222', '运营商信息管理', 'operator', '2', 'operator', '200221', 'basics/operator/list', '1', '1', '0', 'color', null, '1', null, null, null, '2002-01-01 08:36:31');
INSERT INTO `sys_menu` VALUES ('200223', '班型设置', 'classTypeSeting', '2', 'classTypeSeting', '200206', 'promotion/activity/classTypeSeting', '1', '1', '0', 'user', null, '13', null, null, null, '2020-12-10 15:42:08');
INSERT INTO `sys_menu` VALUES ('200224', '设置优惠卷', 'couponSetting', '2', 'couponSetting', '200206', 'promotion/activity/couponSetting', '1', '1', '0', 'edit', null, '14', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('200226', '优惠券查询', 'activityRelevanceCoupon', '2', 'activityRelevanceCoupon', '200206', 'promotion/activity/activityRelevanceCoupon', '1', '1', '0', 'list', null, '15', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('200228', '推广商列表', 'activityRelevancePromoter', '2', 'activityRelevancePromoter', '200206', 'promotion/activity/activityRelevancePromoter', '1', '1', '0', 'nested', null, '16', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('200229', '班型服务管理', null, '1', 'past-service', '0', null, '1', '0', '0', 'example', null, '1', null, null, null, '2021-01-20 10:32:05');
INSERT INTO `sys_menu` VALUES ('200230', '服务项项目管理', 'service-item:list', '2', 'ServiceItem', '200229', 'pastService/serviceItem/index', '1', '0', '0', 'row', null, '1', null, null, null, '2002-01-01 09:30:34');
INSERT INTO `sys_menu` VALUES ('200231', '推广详情', 'activityRelevancePromoterDetail', '2', 'activityRelevancePromoterDetail', '200206', 'promotion/activity/activityRelevancePromoterDetail', '1', '1', '0', '#', null, '17', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('200233', '服务项目价格管理', 'service-item-price:list', '2', 'ServiceItemPrice', '200229', 'pastService/serviceItem/serviceItemPrice/index', '1', '0', '0', 'row', null, '1', null, null, null, '2002-01-01 17:30:27');
INSERT INTO `sys_menu` VALUES ('200234', '平台优惠卷管理', 'platformCouponList', '2', 'platformCouponList', '200206', 'promotion/platformcoupon/platformCouponList', '1', '1', '0', 'documentation', null, '2', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('200235', '服务班型', 'admin:oneFeeSystemPrice:query', '2', 'serviceClassList', '200229', 'pastService/serviceItem/serviceClassList/index', '1', '0', '0', 'education', null, '1', null, null, null, '2021-01-23 02:12:30');
INSERT INTO `sys_menu` VALUES ('200236', '新增班型', 'serviceClassAdd', '2', '/serviceItem/serviceClassAdd', '200229', 'pastService/serviceItem/serviceClassAdd/index', '1', '1', '0', '#', null, '1', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('200237', '修改班型', 'serviceEdit', '2', '/serviceItem/serviceClassEdit', '200229', 'pastService/serviceItem/serviceClassAdd/index', '1', '1', '0', '#', null, '1', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('200238', '首页管理', 'home', '1', 'basics', '200221', 'home', '1', '0', '0', 'component', null, '2', null, '2021-01-15 07:16:58', null, '2021-01-15 07:16:58');
INSERT INTO `sys_menu` VALUES ('200239', '轮播管理', 'banner', '2', 'basics/home/banner', '200221', 'basics/home/banner/index', '1', '0', '0', 'education', null, '2', null, '2021-01-17 15:21:16', null, '2021-01-17 15:21:16');
INSERT INTO `sys_menu` VALUES ('200240', '栏目管理', 'channel', '2', 'basics/home/channel', '200221', 'basics/home/channel/index', '1', '0', '0', 'chart', null, '2', null, '2021-01-15 10:56:14', null, '2021-01-15 10:56:14');
INSERT INTO `sys_menu` VALUES ('200241', '视频管理', 'video', '2', 'basics/video', '200221', 'basics/video/index', '1', '0', '0', 'email', null, '2', null, '2021-01-15 04:23:34', null, '2021-01-15 04:23:34');
INSERT INTO `sys_menu` VALUES ('200242', '修改', 'service_change_class', '3', '', '200235', null, '1', '0', '0', '#', null, '1', null, '2021-01-22 09:58:58', null, '2021-01-22 09:58:58');
INSERT INTO `sys_menu` VALUES ('200243', '新增', 'service_add_class', '3', '', '200235', null, '1', '0', '0', '#', null, '1', null, '2021-01-22 10:08:44', null, '2021-01-22 10:08:44');
INSERT INTO `sys_menu` VALUES ('200244', '轮播添加', 'bannerAdd', '2', 'basics/home/bannerAdd', '200221', 'basics/home/banner/add', '1', '0', '0', 'cascader', null, '2', null, '2021-01-28 13:47:41', null, '2021-01-28 13:47:41');
INSERT INTO `sys_menu` VALUES ('200245', '学员信息管理', '', '1', 'studentStudy', '0', 'studentInfo/studentStudyEnroll.vue', '1', '0', '0', 'logininfor', null, '4', null, '2021-01-29 07:36:10', null, '2021-01-29 07:36:10');
INSERT INTO `sys_menu` VALUES ('200246', '学员信息管理', 'aaa', '2', 'studentInfo', '200245', 'studentInfo/studentInfo.vue', '1', '0', '0', '#', null, '0', null, '2021-01-28 15:55:09', null, '2021-01-28 15:55:09');
INSERT INTO `sys_menu` VALUES ('200247', '学员学车报名单', 'aaa', '2', 'studentStudyEnroll', '200245', 'studentInfo/studentStudyEnroll.vue', '1', '0', '0', '#', null, '0', null, '2021-02-01 10:14:20', null, '2021-02-01 10:14:20');
INSERT INTO `sys_menu` VALUES ('200249', '客服管理', '', '1', 'serviceInfo', '0', '', '1', '0', '0', 'server', null, '5', null, '2021-02-01 14:03:50', null, '2021-02-01 14:03:50');
INSERT INTO `sys_menu` VALUES ('200250', '客服信息管理', 'serviceInfo', '2', 'serviceInfo', '200249', 'serviceInfo/serviceInfo.vue', '1', '0', '0', '#', null, '0', null, '2021-02-01 14:06:21', null, '2021-02-01 14:06:21');
INSERT INTO `sys_menu` VALUES ('200251', '新增客服', 'serviceInfoAdd', '2', 'serviceInfoAdd', '200249', 'serviceInfo/serviceInfoAdd.vue', '1', '1', '0', '#', null, '0', null, '2021-02-01 16:18:46', null, '2021-02-01 16:18:46');
INSERT INTO `sys_menu` VALUES ('200253', '切换客服', 'studentStudyEnrollSwitchService', '2', 'studentStudyEnrollSwitchService', '200245', 'studentInfo/studentStudyEnrollSwitchService.vue', '1', '1', '0', '#', null, '0', null, '2021-02-02 10:07:50', null, '2021-02-02 10:07:50');
INSERT INTO `sys_menu` VALUES ('200254', '栏目添加', 'addChannel', '2', 'basics/home/channel/add', '200221', 'basics/home/channel/add', '1', '0', '0', 'cascader', null, '2', null, '2021-02-02 10:29:26', null, '2021-02-02 10:29:26');
INSERT INTO `sys_menu` VALUES ('200255', '教练信息管理', null, '1', 'coach', '0', null, '1', '0', '0', 'logininfor', null, '6', null, '2021-02-02 15:02:47', null, '2021-02-02 15:02:47');
INSERT INTO `sys_menu` VALUES ('200256', '学员订单管理', 'studentOrder', '2', 'studentOrder', '200245', 'studentInfo/studentOrder/studentOrder.vue', '1', '0', '0', '#', null, '6', null, '2021-02-02 15:17:03', null, '2021-02-02 15:17:03');
INSERT INTO `sys_menu` VALUES ('200257', '学员考试报名单', 'studentTestEnroll/List', '2', '/student/studentTestEnroll', '200245', 'studentTestEnroll/index', '1', '0', '0', 'bug', null, '4', null, '2021-02-02 16:31:12', null, '2021-02-02 16:31:12');
INSERT INTO `sys_menu` VALUES ('200258', '学车考试添加', 'studentTestEnroll/Add', '2', 'studentTestEnroll/add', '200245', 'studentTestEnroll/add', '1', '1', '0', 'checkbox', null, '4', null, '2021-02-02 16:37:43', null, '2021-02-02 16:37:43');
INSERT INTO `sys_menu` VALUES ('200259', '学员订单修改', 'studentOrderModification', '2', 'studentOrderModification', '200245', 'studentInfo/studentOrder/studentOrderModification.vue', '1', '1', '0', '#', null, '12', null, '2021-02-02 17:02:09', null, '2021-02-02 17:02:09');
INSERT INTO `sys_menu` VALUES ('200260', '教练信息管理', 'coachInfo', '2', 'coachInfo', '200255', 'coachInfo/index', '0', '0', '0', 'client', null, '1', null, '2021-02-03 10:07:44', null, '2021-02-03 10:07:44');
INSERT INTO `sys_menu` VALUES ('200261', '运营商时间段管理', null, '1', 'time', '0', null, '1', '0', '0', 'education', null, '1', null, '2021-02-05 10:16:46', null, '2021-02-05 10:16:46');
INSERT INTO `sys_menu` VALUES ('200262', '运营商时间段管理', 'areaTime', '2', 'areaTime', '200261', 'areaTime/index', '0', '0', '0', 'client', null, '1', null, '2021-02-05 10:19:10', null, '2021-02-05 10:19:10');
INSERT INTO `sys_menu` VALUES ('200263', '运营商时间段新增', 'addTime', '2', 'addTime', '200261', 'areaTime/add', '0', '1', '0', 'example', null, '1', null, '2021-02-05 11:18:50', null, '2021-02-05 11:18:50');

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='岗位信息';

-- ----------------------------
-- Records of sys_post
-- ----------------------------
INSERT INTO `sys_post` VALUES ('2', 'manager', '总经理', '0', null, '2', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_post` VALUES ('3', 'hr', '人事', '0', null, '3', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_post` VALUES ('4', 'finance', '财务', '0', null, '4', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_post` VALUES ('5', 'sale', '销售', '0', null, '5', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');

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
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=utf8mb4 COMMENT='角色信息';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '系统管理员', 'admin', '1', '0', null, '1', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_role` VALUES ('100', '测试', 'ceshi', '2', '0', null, '2', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_role` VALUES ('101', '运营中心', 'coo', '1', '0', '运营中心', '0', null, null, null, '2021-01-22 18:07:03');
INSERT INTO `sys_role` VALUES ('102', '研发工程师', 'java', '1', '0', '研发工程师', '0', null, '2021-01-27 17:59:24', null, '2021-01-27 17:59:24');
INSERT INTO `sys_role` VALUES ('103', '研发总监', 'java_manager', '1', '0', '研发总监', '0', null, '2021-01-27 18:00:24', null, '2021-01-27 18:00:24');

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
INSERT INTO `sys_role_dept` VALUES ('102', '202');

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
INSERT INTO `sys_role_menu` VALUES ('100', '2003');
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
INSERT INTO `sys_role_menu` VALUES ('100', '200301');
INSERT INTO `sys_role_menu` VALUES ('100', '200302');
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
INSERT INTO `sys_role_menu` VALUES ('101', '200229');
INSERT INTO `sys_role_menu` VALUES ('101', '200230');
INSERT INTO `sys_role_menu` VALUES ('101', '200233');
INSERT INTO `sys_role_menu` VALUES ('101', '200235');
INSERT INTO `sys_role_menu` VALUES ('101', '200236');
INSERT INTO `sys_role_menu` VALUES ('101', '200237');
INSERT INTO `sys_role_menu` VALUES ('101', '200243');
INSERT INTO `sys_role_menu` VALUES ('102', '1');
INSERT INTO `sys_role_menu` VALUES ('102', '1001');
INSERT INTO `sys_role_menu` VALUES ('102', '1002');
INSERT INTO `sys_role_menu` VALUES ('102', '1003');
INSERT INTO `sys_role_menu` VALUES ('102', '1004');
INSERT INTO `sys_role_menu` VALUES ('102', '1005');
INSERT INTO `sys_role_menu` VALUES ('102', '1006');
INSERT INTO `sys_role_menu` VALUES ('102', '1007');
INSERT INTO `sys_role_menu` VALUES ('102', '100101');
INSERT INTO `sys_role_menu` VALUES ('102', '100102');
INSERT INTO `sys_role_menu` VALUES ('102', '100103');
INSERT INTO `sys_role_menu` VALUES ('102', '100104');
INSERT INTO `sys_role_menu` VALUES ('102', '100105');
INSERT INTO `sys_role_menu` VALUES ('102', '100201');
INSERT INTO `sys_role_menu` VALUES ('102', '100202');
INSERT INTO `sys_role_menu` VALUES ('102', '100203');
INSERT INTO `sys_role_menu` VALUES ('102', '100204');
INSERT INTO `sys_role_menu` VALUES ('102', '100205');
INSERT INTO `sys_role_menu` VALUES ('102', '100301');
INSERT INTO `sys_role_menu` VALUES ('102', '100302');
INSERT INTO `sys_role_menu` VALUES ('102', '100303');
INSERT INTO `sys_role_menu` VALUES ('102', '100304');
INSERT INTO `sys_role_menu` VALUES ('102', '100305');
INSERT INTO `sys_role_menu` VALUES ('102', '100401');
INSERT INTO `sys_role_menu` VALUES ('102', '100402');
INSERT INTO `sys_role_menu` VALUES ('102', '100403');
INSERT INTO `sys_role_menu` VALUES ('102', '100404');
INSERT INTO `sys_role_menu` VALUES ('102', '100405');
INSERT INTO `sys_role_menu` VALUES ('102', '100501');
INSERT INTO `sys_role_menu` VALUES ('102', '100502');
INSERT INTO `sys_role_menu` VALUES ('102', '100503');
INSERT INTO `sys_role_menu` VALUES ('102', '100504');
INSERT INTO `sys_role_menu` VALUES ('102', '100505');
INSERT INTO `sys_role_menu` VALUES ('102', '100601');
INSERT INTO `sys_role_menu` VALUES ('102', '100602');
INSERT INTO `sys_role_menu` VALUES ('102', '100603');
INSERT INTO `sys_role_menu` VALUES ('102', '100604');
INSERT INTO `sys_role_menu` VALUES ('102', '100605');
INSERT INTO `sys_role_menu` VALUES ('102', '100701');
INSERT INTO `sys_role_menu` VALUES ('102', '100702');
INSERT INTO `sys_role_menu` VALUES ('102', '100703');
INSERT INTO `sys_role_menu` VALUES ('102', '100704');
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
INSERT INTO `sys_role_menu` VALUES ('103', '200221');
INSERT INTO `sys_role_menu` VALUES ('103', '200222');
INSERT INTO `sys_role_menu` VALUES ('103', '200238');
INSERT INTO `sys_role_menu` VALUES ('103', '200239');
INSERT INTO `sys_role_menu` VALUES ('103', '200240');
INSERT INTO `sys_role_menu` VALUES ('103', '200241');

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
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=112 DEFAULT CHARSET=utf8mb4 COMMENT='用户信息';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '102', 'admin', 'admin', '00', 'admin1@qq.com', '13910011001', '0', '/pro/\\headImg\\2021-01-11\\1610350678492.png', '$2a$10$wB/CLoEv/ic4ca9r/ATFReier32QA0FZwNd8yE0Mlm3VmyHT6AAuu', '0', '0', '192.168.99.140', '2020-07-23 12:02:13', null, 'admin', '2020-06-06 03:00:00', null, null);
INSERT INTO `sys_user` VALUES ('105', '102', 'ceshi', 'ceshi', '00', 'ceshi@qq.com', '13310011001', '0', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '192.168.99.103', '2020-07-21 13:43:18', null, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_user` VALUES ('106', '101', '1', '1', '00', '1@qq.com', '13110011000', '0', '', '$2a$10$ErIDLtoMqu6wa1zQhaC5muyLOn.sfcI7zEqwL5l18uhafCwifLAYW', '0', '0', '', null, null, 'admin', '2020-06-06 03:00:00', null, null);
INSERT INTO `sys_user` VALUES ('107', '101', '2', '2', '00', '2@qq.com', '13120002000', '0', '', '$2a$10$qUajjZCSXyx3BZ4hBKmYreVHfD3ovSfxKWcBZW3dyz6f7D6BxaMNm', '0', '0', '', null, null, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_user` VALUES ('109', '203', 'yyzx', '闵志强', '00', '123@123.com', '18888888888', '0', '', '$2a$10$/bpJFdRaRUUGEAYlONGcG.IDruFI6XSpDG38nmLtTUigcaPMmnQJG', '0', '0', '', null, null, null, null, null, null);
INSERT INTO `sys_user` VALUES ('110', '203', 'test', '测试', '00', '123@qq.com', '15116101123', '0', '', '$2a$10$DHE13B.rmTPLQbyxQ7D0WeJnfNz26LEEvzFh2hGunhsJzT6wDmLvC', '0', '0', '', null, '测试', null, '2021-01-12 17:43:52', null, '2021-01-12 17:43:52');
INSERT INTO `sys_user` VALUES ('111', '102', 'ybbghdiu', '小郭', '00', '111@qq.com', '15116101123', '0', '', '$2a$10$I5YtH3l8LE4DEV9ql.JSreaCwB4RO9LXMgItwpGbD6kZZg9yALUou', '0', '0', '', null, null, null, '2021-01-27 18:02:02', null, '2021-01-27 18:02:02');

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
INSERT INTO `sys_user_post` VALUES ('110', '4');
INSERT INTO `sys_user_post` VALUES ('111', '4');

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
INSERT INTO `sys_user_role` VALUES ('110', '100');
INSERT INTO `sys_user_role` VALUES ('111', '103');
