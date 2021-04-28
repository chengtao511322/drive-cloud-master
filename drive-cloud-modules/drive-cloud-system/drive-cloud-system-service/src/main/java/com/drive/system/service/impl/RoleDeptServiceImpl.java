package com.drive.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.drive.common.core.constant.CacheConstants;
import com.drive.common.core.utils.BeanConvertUtils;
import com.drive.system.mapper.RoleDeptMapper;
import com.drive.system.pojo.entity.DeptEntity;
import com.drive.system.pojo.entity.RoleDeptEntity;
import com.drive.system.pojo.vo.DeptVo;
import com.drive.system.pojo.vo.RoleDeptVo;
import com.drive.system.service.DeptService;
import com.drive.system.service.RoleDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 角色和部门关联 服务实现类
 *
 * @author xiaoguo
 * @since 2020-07-15
 */
@Service
public class RoleDeptServiceImpl extends ServiceImpl<RoleDeptMapper, RoleDeptEntity> implements RoleDeptService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @PostConstruct
    public void init(){
        List<RoleDeptEntity> deptList = this.getBaseMapper().getAllDept();
        List<RoleDeptVo> deptVoList = BeanConvertUtils.copyList(deptList,RoleDeptVo.class);
        deptVoList.stream().forEach((item ->{
            stringRedisTemplate.opsForValue().set(CacheConstants.REDIS_ROLE_DEPT_KEY + item.getRoleId(), item.getTenantIds());
        }));
    }
}

