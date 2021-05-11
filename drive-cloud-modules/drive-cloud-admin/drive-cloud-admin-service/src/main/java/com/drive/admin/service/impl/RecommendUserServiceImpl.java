package com.drive.admin.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.drive.admin.mapper.RecommendUserMapper;
import com.drive.admin.pojo.entity.RecommendUserEntity;
import com.drive.admin.service.RecommendUserService;
import com.drive.common.core.base.BaseService;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.SubResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 推广人员信息表 服务实现类
 *
 * @author xiaoguo
 */
@Service
public class RecommendUserServiceImpl extends BaseService<RecommendUserMapper, RecommendUserEntity> implements RecommendUserService {


    @Autowired
    private RecommendUserMapper recommendUserMapper;

    @Override
    public RecommendUserEntity getRecommendUserByStudentId(String studentId) {
        if (StrUtil.isEmpty(studentId)){
            return null;
        }
        QueryWrapper<RecommendUserEntity> queryWrapper = new QueryWrapper<RecommendUserEntity>();
        queryWrapper.eq("student_id",studentId);
        return recommendUserMapper.selectOne(queryWrapper);
    }
}

