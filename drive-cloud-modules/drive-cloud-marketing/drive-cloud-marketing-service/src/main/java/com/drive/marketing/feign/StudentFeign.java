package com.drive.marketing.feign;

import com.drive.admin.api.RemoteStudentFeignService;
import com.drive.admin.pojo.vo.StudentInfoRpcVo;
import com.drive.common.core.biz.ResCodeEnum;
import com.drive.common.core.biz.ResObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author 小郭
 * @Description //TODO
 * @Date $ $
 * @Param $
 * @return $
 **/
@Component
@Slf4j
public class StudentFeign {

    @Autowired
    private RemoteStudentFeignService remoteStudentFeignService;

    /**
     * 异步查学员
     * @param id
     * @return
     */
    public Map<String, StudentInfoRpcVo> getBatchStudent(String[] ids){
        Map<String, StudentInfoRpcVo> studentInfoVos = null;
        ResObject<Map<String, StudentInfoRpcVo>> resObject = remoteStudentFeignService.batchStudent(ids);

        log.info("请求学员接口{}",resObject);
        // 判断接口是否请求成功
        if (resObject.getCode().equals(ResCodeEnum.SUCCESS.getCode()) && resObject.getData() != null){
            studentInfoVos =  resObject.getData();
        }
        return studentInfoVos;

    }
}
