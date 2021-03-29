package com.drive.admin.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.admin.pojo.dto.ServiceItemPricePageQueryParam;
import com.drive.admin.pojo.dto.StudentInfoPageQueryParam;
import com.drive.admin.pojo.entity.StudentInfoEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 学员信息表 Mapper 接口
 *
 * @author xiaoguo
 */
public interface StudentInfoMapper extends BaseMapper<StudentInfoEntity> {

    IPage newStudentPageList(Page page, @Param("ew") Wrapper<StudentInfoPageQueryParam> ew);

}

