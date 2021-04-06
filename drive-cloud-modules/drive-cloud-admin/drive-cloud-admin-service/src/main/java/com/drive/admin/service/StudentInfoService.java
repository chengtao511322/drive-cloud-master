package com.drive.admin.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.drive.admin.pojo.dto.ServiceItemPricePageQueryParam;
import com.drive.admin.pojo.dto.StudentInfoPageQueryParam;
import com.drive.admin.pojo.entity.StudentInfoEntity;
import com.drive.admin.pojo.vo.StudentInfoVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * 学员信息表 服务类
 *
 * @author xiaoguo
 */
public interface StudentInfoService extends IService<StudentInfoEntity>{


//    newStudentPageList

    IPage<StudentInfoVo> newStudentPageList(Page page, Wrapper<StudentInfoPageQueryParam> ew);

    /**
     *
     * @param page
     * @param ew
     * @return
     */
    IPage<StudentInfoVo> newStudentReturnVisitPageList(Page page, Wrapper<StudentInfoPageQueryParam> ew);

}

