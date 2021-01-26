package com.drive.basics.repository.impl;

import cn.hutool.core.util.StrUtil;
import com.drive.basics.pojo.dto.OperatorEditParam;
import com.drive.basics.pojo.dto.OperatorPageQueryParam;
import com.drive.basics.pojo.entity.OperatorEntity;
import com.drive.basics.pojo.vo.OperatorItemVo;
import com.drive.basics.repository.OperatorRepository;
import com.drive.basics.service.OperatorService;
import com.drive.common.core.base.BaseController;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
@Slf4j
public class OperatorRepositoryImpl extends BaseController<OperatorPageQueryParam, OperatorEntity> implements OperatorRepository {

    @Autowired
    private OperatorService operatorService;

    @Override
    public ResObject pageList(OperatorPageQueryParam param) {
        return null;
    }

    @Override
    public ResObject findList(OperatorPageQueryParam param) {
        return null;
    }

    @Override
    public ResObject getInfo(String id) {
        return null;
    }

    @Override
    public ResObject save(OperatorEditParam installParam) {
        return null;
    }

    @Override
    public ResObject update(OperatorEditParam updateParam) {
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
    public ResObject exportXls(OperatorPageQueryParam param, HttpServletResponse response) {
        return null;
    }

    @Override
    public ResObject changeStatus(OperatorEditParam param) {
        return null;
    }

    @Override
    public ResObject getOperatorByOperatorId(String operatorId) {
        log.info(this.getClass() + "getOperatorByOperatorId-方法請求參數{}",operatorId);
        if (StrUtil.isNotEmpty(operatorId)){
            log.error("數據空");
            return R.failure();
        }
        operatorService.getOperatorByOperatorId(operatorId);
        return null;
    }

    @Override
    public ResObject findAllList() {
        log.info(this.getClass() + "findAllList-方法请求参数{}");
        List<OperatorItemVo> operatorItemVos =operatorService.findAllList();
        log.info(this.getClass() + "findAllList-方法请求结果{}",operatorItemVos);
        return R.success(operatorItemVos);
    }
}
