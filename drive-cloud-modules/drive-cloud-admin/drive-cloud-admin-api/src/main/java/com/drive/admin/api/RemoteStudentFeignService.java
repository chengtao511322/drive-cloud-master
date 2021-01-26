package com.drive.admin.api;


import com.drive.admin.factory.StudentFallbackFactory;
import com.drive.admin.pojo.vo.StudentInfoVo;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.constant.ServiceNameConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping(value = "/studentInfo/{id}",method = RequestMethod.GET)
    ResObject<StudentInfoVo> get(@PathVariable(value = "id") String id);

}
