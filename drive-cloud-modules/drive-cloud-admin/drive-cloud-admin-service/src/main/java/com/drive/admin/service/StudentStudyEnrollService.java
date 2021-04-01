package com.drive.admin.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.drive.admin.pojo.dto.StudentStudyEnrollPageQueryParam;
import com.drive.admin.pojo.entity.StudentStudyEnrollEntity;
import com.drive.admin.pojo.vo.StudentStudyEnrollVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * 学员学车报名单 服务类
 *
 * @author xiaoguo
 */
public interface StudentStudyEnrollService extends IService<StudentStudyEnrollEntity>{

    /**
     * 通过报名单号修改数据
     * @param studentStudyEnrollEntity
     * @return
     */
    Boolean updateByEnrollNo(StudentStudyEnrollEntity studentStudyEnrollEntity);

    /**
     * 分页查询总数
     * @param page
     * @param ew
     * @return
     */
    IPage<StudentStudyEnrollVo> studyEnrollPageList(Page page, Wrapper<StudentStudyEnrollPageQueryParam> ew);
    // 不分页查询报名信息
    List<StudentStudyEnrollVo> studyEnrollList(Wrapper<StudentStudyEnrollPageQueryParam> ew);
}

