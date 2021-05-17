package com.drive.admin.repository.impl;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.admin.enums.StatusEnum;
import com.drive.admin.pojo.dto.*;
import com.drive.admin.pojo.entity.OperatorAreaEntity;
import com.drive.admin.pojo.entity.OperatorEntity;
import com.drive.admin.pojo.entity.RecommendManagerEntity;
import com.drive.admin.pojo.vo.OperatorAreaVo;
import com.drive.admin.pojo.vo.OperatorVo;
import com.drive.admin.repository.OperatorRepository;
import com.drive.admin.service.OperatorAreaService;
import com.drive.admin.service.OperatorService;
import com.drive.admin.service.mapstruct.OperatorMapStruct;
import com.drive.basics.feign.RemoteOperatorFeignService;
import com.drive.common.core.base.BaseController;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.biz.SubResultCode;
import com.drive.common.core.utils.BeanConvertUtils;
import com.drive.common.data.utils.ExcelUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

                                                
/**
 *
 * 运营商基础信息 服务类
 *
 * @author xiaoguo
 */
@Slf4j
@Service
public class  OperatorRepositoryImpl extends BaseController<OperatorPageQueryParam, OperatorEntity>  implements OperatorRepository{

    //  运营商基础信息 服务
    @Autowired
    private OperatorService operatorService;
    //  运营商基础信息 DO-DTO转化
    @Autowired
    private OperatorMapStruct operatorMapStruct;

    @Autowired
    private OperatorAreaService operatorAreaService;

    @Autowired
    private RemoteOperatorFeignService remoteOperatorFeignService;

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 运营商基础信息 分页列表
     * @date 2020/2/12 17:09
     * @param  * @param OperatorPageQueryParam
     * @return
     */
    @Override
    public ResObject pageList(OperatorPageQueryParam param) {
        log.info(this.getClass() + "pageList-方法请求参数{}",param);
        Page<OperatorEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        // 条件查询
        QueryWrapper queryWrapper = this.getQueryWrapper(operatorMapStruct, param);

        //  模糊查询
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueNameSearch()),"name",param.getVagueNameSearch());
        //  开始时间 结束时间都有才进入
        if (StrUtil.isNotEmpty(param.getBeginTime()) && StrUtil.isNotEmpty(param.getEndTime())){
            queryWrapper.between(StrUtil.isNotEmpty(param.getBeginTime()),"create_time",param.getBeginTime(),param.getEndTime());
        }

        if (StrUtil.isNotEmpty(param.getCreateDateTimeSearchArr()[0]) && StrUtil.isNotEmpty(param.getCreateDateTimeSearchArr()[1])){
            queryWrapper.between("create_time",param.getCreateDateTimeSearchArr()[0],param.getCreateDateTimeSearchArr()[1]);
        }
        IPage<OperatorEntity> pageList = operatorService.page(page, queryWrapper);
        if (pageList.getRecords().size() <= 0){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }
        Page<OperatorVo> operatorVoPage = operatorMapStruct.toVoList(pageList);
        log.info(this.getClass() + "pageList-方法请求结果{}",operatorVoPage);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),operatorVoPage);
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 运营商基础信息 查询列表
     * @date 2020/2/12 17:09
     * @param  * @param OperatorPageQueryParam
     * @return
     */
    @Override
    public ResObject findList(OperatorPageQueryParam param) {
        log.info(this.getClass() + "findList-方法请求参数{}",param);
        // 这里判断条件进行查询
        QueryWrapper queryWrapper= this.getQueryWrapper(operatorMapStruct, param);
        // 如 queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"phone",param.getPhone());
        List<OperatorEntity> operatorList = operatorService.list(queryWrapper);
        if (operatorList == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),operatorList);
        }
        List<OperatorVo> operatorVoList = operatorMapStruct.toVoList(operatorList);
        log.info(this.getClass() + "findList-方法请求结果{}",operatorVoList);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),operatorVoList);
    }

    /**
    * 对象条件查询返回单条运营商基础信息数据
    * @param param
    * @return
    */
    @Override
    public ResObject getInfo(OperatorPageQueryParam param) {
        log.info(this.getClass() + "getInfo-方法请求参数{}",param);
        if (param == null){
            return R.failure("数据空");
        }
        // 这里判断条件进行查询
        QueryWrapper queryWrapper = this.getQueryWrapper(operatorMapStruct, param);
        OperatorEntity operator = operatorService.getOne(queryWrapper);
        if (operator == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),operator);
        }
        OperatorVo operatorVo = BeanConvertUtils.copy(operator, OperatorVo.class);
        log.info(this.getClass() + "getInfo-方法请求结果{}",operatorVo);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),operatorVo);
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 通过ID获取 运营商基础信息 单条数据
     * @date 2020/2/12 17:09
     * @param  * @param OperatorPageQueryParam
     * @return
     */
    @Override
    public ResObject getById(String id) {
        log.info(this.getClass() + "getById-方法请求参数{}",id);
        if (StrUtil.isEmpty(id)){
            return R.failure("数据空");
        }
        // 通过ID 获取 单条数据
        OperatorEntity operator = operatorService.getById(id);
        if (operator == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),operator);
        }
        OperatorVo operatorVo = BeanConvertUtils.copy(operator, OperatorVo.class);
        log.info(this.getClass() + "getById-方法请求结果{}",operatorVo);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),operatorVo);
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 保存运营商基础信息 数据
     * @date 2020/2/12 17:09
     * @param  * @param OperatorPageQueryParam
     * @return
     */
    @Override
    public ResObject save(OperatorInstallParam installParam) {
        log.info(this.getClass() + "save方法请求参数{}",installParam);
        if (installParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        // 幂等性查询
        QueryWrapper queryWrapper = new QueryWrapper();
        // 运营商名称
        queryWrapper.eq(StrUtil.isNotEmpty(installParam.getName()),"name",installParam.getName());
        queryWrapper.last("limit 1");
        int isOperator = operatorService.count(queryWrapper);
        if (isOperator > 0){
            return R.success(SubResultCode.DATA_IDEMPOTENT.subCode(),SubResultCode.DATA_IDEMPOTENT.subMsg());
        }
        OperatorEntity operator = BeanConvertUtils.copy(installParam, OperatorEntity.class);
        Boolean result = operatorService.save(operator);
        log.info(this.getClass() + "save-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_INSTALL_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_INSTALL_FAILL.subCode(),SubResultCode.DATA_INSTALL_FAILL.subMsg());
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description  修改运营商基础信息 数据
     * @date 2020/2/12 17:09
     * @param  * @param OperatorPageQueryParam
     * @return
     */
    @Override
    public ResObject update(OperatorEditParam updateParam) {
        log.info(this.getClass() + "update方法请求参数{}",updateParam);
        if (updateParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        // 幂等性查询
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.setEntity(updateParam);
        // 运营商名称
        queryWrapper.last("limit 1");
        int isOperator = operatorService.count(queryWrapper);
        if (isOperator > 0){
            return R.success(SubResultCode.DATA_IDEMPOTENT.subCode(),SubResultCode.DATA_IDEMPOTENT.subMsg());
        }
        OperatorEntity operator = BeanConvertUtils.copy(updateParam, OperatorEntity.class);
        Boolean result = operatorService.updateById(operator);
        log.info(this.getClass() + "update-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_UPDATE_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_UPDATE_FAILL.subCode(),SubResultCode.DATA_UPDATE_FAILL.subMsg());
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 数组删除运营商基础信息 数据
     * @date 2020/2/12 17:09
     * @param  * @param OperatorPageQueryParam
     * @return
     */
    @Override
    public ResObject deleteByIds(String[] ids) {
        if (ids.length <= 0){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        return R.toRes(operatorService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * *通过id删除运营商基础信息 信息
     **/
    @Override
    public ResObject deleteById(String id) {
        log.info(this.getClass() + "deleteById-方法请求参数{}",id);
        if(StrUtil.isEmpty(id)){
            log.error("ID数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        //QueryWrapper<String> queryWrapper = new QueryWrapper<String>();
        //queryWrapper.eq(StrUtil.isNotEmpty(id),"id",id);
        Boolean result = operatorService.removeById(id);
        log.info(this.getClass() + "deleteById-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_DELETE_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_DELETE_FAILL.subCode(),SubResultCode.DATA_DELETE_FAILL.subMsg());
    }

    /**
     * *导出运营商基础信息 信息
     **/
    @Override
    public ResObject exportXls(OperatorPageQueryParam param, HttpServletResponse response)throws IOException {
        log.info(this.getClass() + "exportXls方法请求参数{}",param);
        QueryWrapper queryWrapper = this.getQueryWrapper(operatorMapStruct, param);
        List<OperatorEntity> list = operatorService.list(queryWrapper);
        List<OperatorVo>operatorList = operatorMapStruct.toVoList(list);
        ExcelUtils.exportExcel(operatorList, OperatorVo.class, "", new ExportParams(), response);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.EXPORT_DATA_SUCCESS.subMsg());
    }

    /**
     * *修改状态
     **/
    @Override
    public ResObject changeStatus(OperatorEditParam param) {
        log.info(this.getClass() + "changeStatus方法请求参数{}",param);
        if (StrUtil.isEmpty(param.getId())){
            log.error("ID数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        OperatorEntity OperatorEntity = new OperatorEntity();
        OperatorEntity.setId(param.getId());
        OperatorEntity.setStatus(param.getStatus());
        //OperatorEntity.setUpdateTime()
        Boolean result = operatorService.updateById(OperatorEntity);
        log.info(this.getClass() +"changeStatus方法请求对象参数{}，请求结果{}",OperatorEntity,result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_STATUS_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_STATUS_FAILL.subCode(),SubResultCode.DATA_STATUS_FAILL.subMsg());
    }

    @Override
    public ResObject installOperator(OperatorInstallParam operatorEditParam) {
        log.info(this.getClass() + "installOperator-方法请求参数{}",operatorEditParam);
        log.info(this.getClass() + "save方法请求参数{}",operatorEditParam);
        if (operatorEditParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        OperatorEntity operator = BeanConvertUtils.copy(operatorEditParam, OperatorEntity.class);
        operator.setStatus(StatusEnum.ENABLE.getCode());
        Boolean result = operatorService.save(operator);
        log.info(this.getClass() + "save-方法请求结果{}",result);

        // 区域处理
        List<OperatorAreaInstallParam> operatorAreaInstallParams = operatorEditParam.getOperatorAreaInstallParams();
        if (operatorAreaInstallParams.size() > 0){
            // 添加区域
            List<OperatorAreaEntity> operatorArea = BeanConvertUtils.copyList(operatorAreaInstallParams, OperatorAreaEntity.class);
            operatorArea.stream().forEach((item) ->{
                item.setOperatorId(operator.getId());
            });
            Boolean res = operatorAreaService.saveOrUpdateBatch(operatorArea);
            if (!res)return R.failure(SubResultCode.DATA_INSTALL_FAILL.subCode(),SubResultCode.DATA_INSTALL_FAILL.subMsg());
        }

        // 添加微服务运营商数据
        com.drive.basics.pojo.dto.OperatorEditParam operatorEdit =BeanConvertUtils.copy(operatorEditParam, com.drive.basics.pojo.dto.OperatorEditParam.class);
        operatorEdit.setOperatorAreaList(BeanConvertUtils.copyList(operatorAreaInstallParams, com.drive.basics.pojo.dto.OperatorAreaInstallParam.class));
        ResObject resObject = remoteOperatorFeignService.saveOperator(operatorEdit);
        log.info("请求结果resObject{}",resObject);
        return R.success();
    }

    @Override
    public ResObject updateOperator(OperatorEditParam operatorEditParam) {
        if (StrUtil.isEmpty(operatorEditParam.getId())){
            return R.failure();
        }
        log.info(this.getClass() + "update方法请求参数{}",operatorEditParam);
        if (operatorEditParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        OperatorEntity operator = BeanConvertUtils.copy(operatorEditParam, OperatorEntity.class);
        Boolean result = operatorService.updateById(operator);
        log.info(this.getClass() + "update-方法请求结果{}",result);
        // 区域处理
        List<OperatorAreaInstallParam> operatorAreaInstallParams = operatorEditParam.getOperatorAreaInstallParams();
        if (operatorAreaInstallParams.size() > 0){
            // 删除数据
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("operator_id",operatorEditParam.getId());
            operatorAreaService.remove(queryWrapper);
            // 添加区域
            List<OperatorAreaEntity> operatorArea = BeanConvertUtils.copyList(operatorAreaInstallParams, OperatorAreaEntity.class);
            operatorArea.stream().forEach((item) ->{
                item.setOperatorId(operator.getId());
            });
            Boolean res = operatorAreaService.saveOrUpdateBatch(operatorArea);
            if (!res)return R.failure(SubResultCode.DATA_INSTALL_FAILL.subCode(),SubResultCode.DATA_INSTALL_FAILL.subMsg());
        }
        return R.success();
    }

    @Override
    public ResObject getOperatingArea(String operatorId) {
        if (StrUtil.isEmpty(operatorId)){
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("operator_id",operatorId);
        List<OperatorAreaEntity> operatorAreaList = operatorAreaService.list(queryWrapper);
        if (operatorAreaList.size() <= 0){
            return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_NULL.subMsg(),operatorAreaList);
        }
        return R.success(BeanConvertUtils.copyList(operatorAreaList, OperatorAreaVo.class));
    }
}

