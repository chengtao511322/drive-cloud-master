-- 菜单sql
INSERT INTO `sys_menu`(
        `menu_name`,
        `menu_perms`,
        `menu_type`,
        `router_path`,
        `parent_id`,
        `component`,
        `is_link`,
        `visible`,
        `status`,
        `icon`,
        `remark`,
        `order_num`,
        `create_by`,
        `create_time`,
        `update_by`,
        `update_time`
        )
VALUES (
        '支付交易流水信息表管理',
        'pay:mentTransaction:list',
        2,
        'mentTransaction',
        1,
        'pay/mentTransaction/index',
        1,
        '0',
        '0',
        '#',
        NULL,
        1,
        'admin',
        '2020-06-06 03:00:00',
        'admin',
        '2020-06-06 03:00:00'
);


-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
INSERT INTO `sys_menu`(
        `menu_name`,
        `menu_perms`,
        `menu_type`,
        `router_path`,
        `parent_id`,
        `component`,
        `is_link`,
        `visible`,
        `status`,
        `icon`,
        `remark`,
        `order_num`,
        `create_by`,
        `create_time`,
        `update_by`,
        `update_time`
        )
VALUES (
        '支付交易流水信息表查询',
        'pay:mentTransaction:query',
        3,
        '',
        @parentId,
        '#',
        1,
        '0',
        '0',
        '#',
        NULL,
        1,
        'admin',
        '2020-06-06 03:00:00',
        'admin',
        '2020-06-06 03:00:00'
);


INSERT INTO `sys_menu`(
        `menu_name`,
        `menu_perms`,
        `menu_type`,
        `router_path`,
        `parent_id`,
        `component`,
        `is_link`,
        `visible`,
        `status`,
        `icon`,
        `remark`,
        `order_num`,
        `create_by`,
        `create_time`,
        `update_by`,
        `update_time`
        )
VALUES (
        '支付交易流水信息表新增',
        'pay:mentTransaction:add',
        3,
        '',
        @parentId,
        '#',
        1,
        '0',
        '0',
        '#',
        NULL,
        1,
        'admin',
        '2020-06-06 03:00:00',
        'admin',
        '2020-06-06 03:00:00'
);



INSERT INTO `sys_menu`(
        `menu_name`,
        `menu_perms`,
        `menu_type`,
        `router_path`,
        `parent_id`,
        `component`,
        `is_link`,
        `visible`,
        `status`,
        `icon`,
        `remark`,
        `order_num`,
        `create_by`,
        `create_time`,
        `update_by`,
        `update_time`
        )
VALUES (
        '支付交易流水信息表修改',
        'pay:mentTransaction:edit',
        3,
        '',
        @parentId,
        '#',
        1,
        '0',
        '0',
        '#',
        NULL,
        1,
        'admin',
        '2020-06-06 03:00:00',
        'admin',
        '2020-06-06 03:00:00'
);


INSERT INTO `sys_menu`(
        `menu_name`,
        `menu_perms`,
        `menu_type`,
        `router_path`,
        `parent_id`,
        `component`,
        `is_link`,
        `visible`,
        `status`,
        `icon`,
        `remark`,
        `order_num`,
        `create_by`,
        `create_time`,
        `update_by`,
        `update_time`
        )
VALUES (
        '支付交易流水信息表删除',
        'pay:mentTransaction:delete',
        3,
        '',
        @parentId,
        '#',
        1,
        '0',
        '0',
        '#',
        NULL,
        1,
        'admin',
        '2020-06-06 03:00:00',
        'admin',
        '2020-06-06 03:00:00'
);

INSERT INTO `sys_menu`(
        `menu_name`,
        `menu_perms`,
        `menu_type`,
        `router_path`,
        `parent_id`,
        `component`,
        `is_link`,
        `visible`,
        `status`,
        `icon`,
        `remark`,
        `order_num`,
        `create_by`,
        `create_time`,
        `update_by`,
        `update_time`
        )
VALUES (
        '支付交易流水信息表导出',
        'pay:mentTransaction:export',
        3,
        '',
        @parentId,
        '#',
        1,
        '0',
        '0',
        '#',
        NULL,
        1,
        'admin',
        '2020-06-06 03:00:00',
        'admin',
        '2020-06-06 03:00:00'
);






























