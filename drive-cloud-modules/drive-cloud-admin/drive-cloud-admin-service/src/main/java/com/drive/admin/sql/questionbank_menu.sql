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
        '平台题库表管理',
        'admin:questionBank:list',
        2,
        'questionBank',
        1,
        'admin/questionBank/index',
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
        '平台题库表查询',
        'admin:questionBank:query',
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
        '平台题库表新增',
        'admin:questionBank:add',
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
        '平台题库表修改',
        'admin:questionBank:edit',
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
        '平台题库表删除',
        'admin:questionBank:delete',
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
        '平台题库表导出',
        'admin:questionBank:export',
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






























