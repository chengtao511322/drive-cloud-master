package com.drive.admin.api;


import com.drive.admin.factory.StudentFallbackFactory;
import com.drive.admin.pojo.vo.StudentInfoVo;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.constant.ServiceNameConstants;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * 用户服务
 * @author DreamChan
 */
@FeignClient(contextId = "RemoteStudentFeignService", value = ServiceNameConstants.ADMIN_SERVICE,fallbackFactory = StudentFallbackFactory.class)
public interface RemoteStudentFeignService {

    /**
     * 通过ID获取数据
     * @param id
     * @return
     */

    @GetMapping(value = "/studentInfo/{id}")
    ResObject<StudentInfoVo> get(@PathVariable String id);


    /**
     * 事务回滚
     * @return
     */
    @PostMapping("/activity/reduceInventoryRollback")
    ResObject reduceInventoryRollback();


    @ApiOperation("通过ID获取学员信息表")
    @GetMapping("studentInfo/getByIdInfo/{id}")
    ResObject<StudentInfoVo> getByIdInfo(@PathVariable("id") String id);

}
