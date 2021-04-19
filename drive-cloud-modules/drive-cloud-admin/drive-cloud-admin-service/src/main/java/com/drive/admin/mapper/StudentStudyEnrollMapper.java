package com.drive.admin.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.admin.pojo.dto.StudentStudyEnrollPageQueryParam;
import com.drive.admin.pojo.dto.StudyCarScheduleQueryParam;
import com.drive.admin.pojo.entity.StudentStudyEnrollEntity;
import com.drive.admin.pojo.vo.StatisticsStudentDataVo;
import com.drive.admin.pojo.vo.StudentStudyEnrollVo;
import com.drive.admin.pojo.vo.StudyCarScheduleVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 学员学车报名单 Mapper 接口
 *
 * @author xiaoguo
 */
public interface StudentStudyEnrollMapper extends BaseMapper<StudentStudyEnrollEntity> {

    /**
     * 通过报名单号修改数据
     * @param studentStudyEnrollEntity
     * @return
     */
    int updateByEnrollNo(StudentStudyEnrollEntity studentStudyEnrollEntity);

    /**
     * 分页 订单查询总数
     * @param page
     * @param ew
     * @return
     */
    IPage<StudentStudyEnrollVo> studyEnrollPageList(Page page, @Param("ew") Wrapper<StudentStudyEnrollPageQueryParam> ew);

    List<StudentStudyEnrollVo> studyEnrollList(@Param("ew") Wrapper<StudentStudyEnrollPageQueryParam> ew);

    /**
     * 数据统计
     * @param page
     * @param ew
     * @return
     */
    IPage<StatisticsStudentDataVo> statisticsStudentDataPageList(Page page, @Param("ew") Wrapper<StudentStudyEnrollPageQueryParam> ew);



    /**
     * 分页查询_学员学车进度列表
     * @param page
     * @param ew
     * @return
     */
    IPage<StudyCarScheduleVo> studyCarSchedulePageList(Page page, @Param("ew") Wrapper<StudyCarScheduleQueryParam> ew);
}

