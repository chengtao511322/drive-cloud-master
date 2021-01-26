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
        '学员信息表管理',
        'admin:studentInfo:list',
        2,
        'studentInfo',
        1,
        'admin/studentInfo/index',
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
        '学员信息表查询',
        'admin:studentInfo:query',
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
        '学员信息表新增',
        'admin:studentInfo:add',
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
        '学员信息表修改',
        'admin:studentInfo:edit',
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
        '学员信息表删除',
        'admin:studentInfo:delete',
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
        '学员信息表导出',
        'admin:studentInfo:export',
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






























