package com.drive.basics.repository.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.drive.basics.pojo.dto.OperatorAreaInstallParam;
import com.drive.basics.pojo.dto.OperatorEditParam;
import com.drive.basics.pojo.dto.OperatorPageQueryParam;
import com.drive.basics.pojo.entity.OperatorAreaEntity;
import com.drive.basics.pojo.entity.OperatorEntity;
import com.drive.basics.pojo.vo.OperatorItemVo;
import com.drive.basics.repository.OperatorRepository;
import com.drive.basics.service.OperatorAreaService;
import com.drive.basics.service.OperatorService;
import com.drive.common.core.base.BaseController;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.biz.SubResultCode;
import com.drive.common.core.enums.StatusEnum;
import com.drive.common.core.utils.BeanConvertUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class OperatorRepositoryImpl extends BaseController<OperatorPageQueryParam, OperatorEntity> implements OperatorRepository {

    @Autowired
    private OperatorService operatorService;

    @Autowired
    private OperatorAreaService operatorAreaService;

    @Override
    public ResObject pageList(OperatorPageQueryParam param) {
        return null;
    }

    @Override
    public ResObject findList(OperatorPageQueryParam param) {
        return null;
    }



    @Override
    public ResObject getInfo(OperatorPageQueryParam param) {
        return null;
    }

    @Override
    public ResObject getById(String id) {
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
        log.info(this.getClass() + "getOperatorByOperatorId-??????????????????{}",operatorId);
        if (StrUtil.isNotEmpty(operatorId)){
            log.error("?????????");
            return R.failure();
        }
        operatorService.getOperatorByOperatorId(operatorId);
        return null;
    }

    @Override
    public ResObject findAllList() {
        log.info(this.getClass() + "findAllList-??????????????????{}");
        List<OperatorItemVo> operatorItemVos =operatorService.findAllList();
        log.info(this.getClass() + "findAllList-??????????????????{}",operatorItemVos);
        return R.success(operatorItemVos);
    }

    @LcnTransaction //?????????????????????
    @Transactional
    @Override
    public ResObject saveOperator(@Valid OperatorEditParam operatorEditParam) {
        log.info(this.getClass() + "save??????????????????{}",operatorEditParam);
        if (operatorEditParam == null){
            log.error("?????????");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        OperatorEntity operator = BeanConvertUtils.copy(operatorEditParam, OperatorEntity.class);
        operator.setStatus(StatusEnum.ENABLE.getCode());
        Boolean result = operatorService.save(operator);
        log.info(this.getClass() + "save-??????????????????{}",result);

        // ????????????
        List<OperatorAreaInstallParam> operatorAreaInstallParams = operatorEditParam.getOperatorAreaList();
        if (operatorAreaInstallParams.size() > 0){
            // ????????????
            List<OperatorAreaEntity> operatorArea = BeanConvertUtils.copyList(operatorAreaInstallParams, OperatorAreaEntity.class);
            operatorArea.stream().forEach((item) ->{
                item.setOperatorId(operator.getId());
            });
            Boolean res = operatorAreaService.saveOrUpdateBatch(operatorArea);
            if (!res)return R.failure(SubResultCode.DATA_INSTALL_FAILL.subCode(),SubResultCode.DATA_INSTALL_FAILL.subMsg());
        }
        return R.success();
    }

    @Transactional
    @Override
    public ResObject updateOperator(OperatorEditParam operatorEditParam) {
        log.info(this.getClass() + "save??????????????????{}",operatorEditParam);
        if (operatorEditParam == null){
            log.error("?????????");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        OperatorEntity operator = BeanConvertUtils.copy(operatorEditParam, OperatorEntity.class);
        // ????????????
        operator.setUpdateTime(LocalDateTime.now());
        Boolean result = operatorService.updateById(operator);
        log.info(this.getClass() + "update??????????????????{}",result);

        // ????????????
        List<OperatorAreaInstallParam> operatorAreaInstallParams = operatorEditParam.getOperatorAreaList();
        if (operatorAreaInstallParams.size() > 0){
            // ????????????
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("operator_id",operatorEditParam.getId());
            operatorAreaService.remove(queryWrapper);
            // ????????????
            List<OperatorAreaEntity> operatorArea = BeanConvertUtils.copyList(operatorAreaInstallParams, OperatorAreaEntity.class);
            operatorArea.stream().forEach((item) ->{
                item.setOperatorId(operator.getId());
            });
            Boolean res = operatorAreaService.saveOrUpdateBatch(operatorArea);
            if (!res)return R.failure(SubResultCode.DATA_INSTALL_FAILL.subCode(),SubResultCode.DATA_INSTALL_FAILL.subMsg());
        }
        return R.success();
    }

    @Transactional
    @Override
    public ResObject delOperator(OperatorEditParam operatorEditParam) {
        log.info(this.getClass() + "save??????????????????{}",operatorEditParam);
        if (operatorEditParam == null){
            log.error("?????????");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        OperatorEntity operator = BeanConvertUtils.copy(operatorEditParam, OperatorEntity.class);
        operator.setStatus(StatusEnum.NO.getCode());
        // ????????????
        operator.setUpdateTime(LocalDateTime.now());
        Boolean result = operatorService.updateById(operator);
        log.info(this.getClass() + "update??????????????????{}",result);
        // ????????????
        // ????????????
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("operator_id",operatorEditParam.getId());
        operatorAreaService.remove(queryWrapper);
        return R.success();
    }
}
