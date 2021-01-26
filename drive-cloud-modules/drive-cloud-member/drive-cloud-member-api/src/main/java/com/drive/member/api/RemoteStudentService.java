package com.drive.member.api;

import com.drive.common.core.biz.ResObject;
import com.drive.common.core.constant.ServiceNameConstants;
import com.drive.member.factory.StudentFallbackFactory;
import com.drive.member.pojo.vo.StudentInfoVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "RemoteStudentService", value = ServiceNameConstants.MEMBER_SERVICE, fallbackFactory = StudentFallbackFactory.class)
public interface RemoteStudentService {

    @GetMapping(value = "/student/{id}")
    ResObject<StudentInfoVo> get(@PathVariable("id") String id);
}
