package com.drive.admin.repository.impl;

import com.drive.admin.pojo.dto.ServiceInfoEditParam;
import com.drive.admin.pojo.dto.ServiceInfoPageQueryParam;
import com.drive.admin.repository.ServiceInfoRepository;
import com.drive.admin.service.ServiceInfoService;
import com.drive.common.core.biz.ResObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

@Service
@Slf4j
public class ServiceInfoRepositoryImpl  implements ServiceInfoRepository {

    @Autowired
    private ServiceInfoService serviceInfoService;

    @Override
    public ResObject pageList(ServiceInfoPageQueryParam param) {
        log.info(this.getClass() + "pageList方法请求参数{}",param);

        return null;
    }

    @Override
    public ResObject findList(ServiceInfoPageQueryParam param) {
        return null;
    }

    @Override
    public ResObject getInfo(String id) {
        return null;
    }

    @Override
    public ResObject save(ServiceInfoEditParam installParam) {
        return null;
    }

    @Override
    public ResObject update(ServiceInfoEditParam updateParam) {
        return null;
    }

    @Override
    public ResObject deleteByIds(String[] ids) {
        return null;
    }

    @Override
    public ResObject deleteById(String id) {
        return null;
    }

    @Override
    public ResObject exportXls(ServiceInfoPageQueryParam param, HttpServletResponse response) {
        return null;
    }

    @Override
    public ResObject changeStatus(ServiceInfoEditParam param) {
        return null;
    }
}
