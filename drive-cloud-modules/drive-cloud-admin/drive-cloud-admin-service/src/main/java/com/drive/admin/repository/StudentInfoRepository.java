package com.drive.admin.repository;

import com.drive.admin.pojo.dto.StudentInfoEditParam;
import com.drive.admin.pojo.dto.StudentInfoPageQueryParam;
import com.drive.admin.pojo.entity.StudentInfoEntity;
import com.drive.admin.pojo.vo.StudentInfoVo;
import com.drive.common.core.base.BaseRepository;
import com.drive.common.core.biz.ResObject;

import java.util.List;

/**
 *
 * 学员信息表 服务类
 *
 * @author xiaoguo
 */
public interface StudentInfoRepository extends BaseRepository<StudentInfoPageQueryParam, StudentInfoEditParam>{


    /**
     * 通过ID 获取
     * @param id
     * @return
     */
    ResObject<StudentInfoVo> getByIdInfo(String id);

    /**
     *
     * @return
     */
    ResObject reduceInventoryRollback();

    /**
     * 查询新用户列表  分页
     * @param param
     * @return
     */
    ResObject newStudentPageList(StudentInfoPageQueryParam param);

    /**
     * +新用户已回访
     * @param param
     * @return
     */
    ResObject newStudentReturnVisitPageList(StudentInfoPageQueryParam param);

    ResObject oneNewStudentReturnVisitPageList(StudentInfoPageQueryParam param);

    /**
     * 批量修改数据
     * @param studentInfoList
     * @return
     */
    ResObject updateBatch(List<StudentInfoEditParam> studentInfoList);
}

