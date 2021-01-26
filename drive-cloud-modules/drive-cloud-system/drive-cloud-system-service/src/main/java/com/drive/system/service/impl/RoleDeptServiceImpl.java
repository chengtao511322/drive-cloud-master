package com.drive.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.drive.system.mapper.RoleDeptMapper;
import com.drive.system.pojo.entity.RoleDeptEntity;
import com.drive.system.service.RoleDeptService;
import org.springframework.stereotype.Service;

/**
 * 角色和部门关联 服务实现类
 *
 * @author DreamChan
 * @since 2020-07-15
 */
@Service
public class RoleDeptServiceImpl extends ServiceImpl<RoleDeptMapper, RoleDeptEntity> implements RoleDeptService {

}

