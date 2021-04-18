package com.drive.admin.repository;

import com.drive.admin.pojo.dto.StudentInfoEditParam;
import com.drive.admin.pojo.dto.StudentInfoPageQueryParam;
import com.drive.common.core.base.BaseRepository;
import com.drive.common.core.biz.ResObject;

/**
 *
 * 学员信息表 服务类
 *
 * @author xiaoguo
 */
public interface StudentInfoRepository extends BaseRepository<StudentInfoPageQueryParam, StudentInfoEditParam>{


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
}

