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
        '管理',
        'admin:area:list',
        2,
        'area',
        1,
        'admin/area/index',
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
        '查询',
        'admin:area:query',
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
        '新增',
        'admin:area:add',
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
        '修改',
        'admin:area:edit',
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
        '删除',
        'admin:area:delete',
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
        '导出',
        'admin:area:export',
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






























