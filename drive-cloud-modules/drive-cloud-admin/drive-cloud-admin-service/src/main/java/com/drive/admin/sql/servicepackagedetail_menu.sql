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
        '服务项目打包明细表管理',
        'admin:servicePackageDetail:list',
        2,
        'servicePackageDetail',
        1,
        'admin/servicePackageDetail/index',
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
        '服务项目打包明细表查询',
        'admin:servicePackageDetail:query',
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
        '服务项目打包明细表新增',
        'admin:servicePackageDetail:add',
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
        '服务项目打包明细表修改',
        'admin:servicePackageDetail:edit',
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
        '服务项目打包明细表删除',
        'admin:servicePackageDetail:delete',
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
        '服务项目打包明细表导出',
        'admin:servicePackageDetail:export',
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






























