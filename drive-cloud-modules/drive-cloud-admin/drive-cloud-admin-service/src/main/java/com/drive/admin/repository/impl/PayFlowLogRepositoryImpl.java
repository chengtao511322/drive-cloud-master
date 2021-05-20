package com.drive.admin.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.drive.admin.pojo.entity.PayFlowLogEntity;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.drive.common.core.base.BaseController;
import com.drive.admin.repository.PayFlowLogRepository;
import com.drive.admin.pojo.entity.*;
import com.drive.admin.pojo.vo.*;
import com.drive.admin.pojo.dto.*;
import com.drive.admin.service.mapstruct.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.hutool.core.util.StrUtil;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.SubResultCode;
import com.drive.common.core.utils.BeanConvertUtils;
import lombok.extern.slf4j.Slf4j;
import com.drive.common.core.biz.ResObject;
import org.springframework.beans.factory.annotation.Autowired;
import com.drive.admin.service.PayFlowLogService;
import com.drive.common.data.utils.ExcelUtils;
import java.util.List;
import java.util.Arrays;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

                                                                        
/**
 *
 * 平台交易流水日志 服务类
 *
 * @author xiaoguo
 */
@Slf4j
@Service
public class  PayFlowLogRepositoryImpl extends BaseController<PayFlowLogPageQueryParam, PayFlowLogEntity>  implements PayFlowLogRepository{

    //  平台交易流水日志 服务
    @Autowired
    private PayFlowLogService payFlowLogService;
    //  平台交易流水日志 DO-DTO转化
    @Autowired
    private PayFlowLogMapStruct payFlowLogMapStruct;

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 平台交易流水日志 分页列表
     * @date 2020/2/12 17:09
     * @param  * @param PayFlowLogPageQueryParam
     * @return
     */
    @Override
    public ResObject pageList(PayFlowLogPageQueryParam param) {
        log.info(this.getClass() + "pageList-方法请求参数{}",param);
        Page<PayFlowLogEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        // 条件查询
        QueryWrapper queryWrapper = this.getQueryWrapper(payFlowLogMapStruct, param);

        //  模糊查询
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueNameSearch()),"name",param.getVagueNameSearch());
        //  开始时间 结束时间都有才进入
        if (StrUtil.isNotEmpty(param.getBeginTime()) && StrUtil.isNotEmpty(param.getEndTime())){
            queryWrapper.between(StrUtil.isNotEmpty(param.getBeginTime()),"create_time",param.getBeginTime(),param.getEndTime());
        }
        IPage<PayFlowLogEntity> pageList = payFlowLogService.page(page, queryWrapper);
        if (pageList.getRecords().size() <= 0){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }
        Page<PayFlowLogVo> payFlowLogVoPage = payFlowLogMapStruct.toVoList(pageList);
        log.info(this.getClass() + "pageList-方法请求结果{}",payFlowLogVoPage);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),payFlowLogVoPage);
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 平台交易流水日志 查询列表
     * @date 2020/2/12 17:09
     * @param  * @param PayFlowLogPageQueryParam
     * @return
     */
    @Override
    public ResObject findList(PayFlowLogPageQueryParam param) {
        log.info(this.getClass() + "findList-方法请求参数{}",param);
        // 这里判断条件进行查询
        QueryWrapper queryWrapper= this.getQueryWrapper(payFlowLogMapStruct, param);
        // 通过名称查询
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueNameSearch()),"name",param.getVagueNameSearch());
        // 如 queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"phone",param.getPhone());
        List<PayFlowLogEntity> payFlowLogList = payFlowLogService.list(queryWrapper);
        if (payFlowLogList == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),payFlowLogList);
        }
        List<PayFlowLogVo> payFlowLogVoList = payFlowLogMapStruct.toVoList(payFlowLogList);
        log.info(this.getClass() + "findList-方法请求结果{}",payFlowLogVoList);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),payFlowLogVoList);
    }

    /**
    * 对象条件查询返回单条平台交易流水日志数据
    * @param param
    * @return
    */
    @Override
    public ResObject getInfo(PayFlowLogPageQueryParam param) {
        log.info(this.getClass() + "getInfo-方法请求参数{}",param);
        if (param == null){
            return R.failure("数据空");
        }
        // 这里判断条件进行查询
        QueryWrapper queryWrapper = this.getQueryWrapper(payFlowLogMapStruct, param);
        // 这里有且只返回一条数据
        queryWrapper.last("limit 1");
        PayFlowLogEntity payFlowLog = payFlowLogService.getOne(queryWrapper);
        if (payFlowLog == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),payFlowLog);
        }
        PayFlowLogVo payFlowLogVo = BeanConvertUtils.copy(payFlowLog, PayFlowLogVo.class);
        log.info(this.getClass() + "getInfo-方法请求结果{}",payFlowLogVo);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),payFlowLogVo);
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 通过ID获取 平台交易流水日志 单条数据
     * @date 2020/2/12 17:09
     * @param  * @param PayFlowLogPageQueryParam
     * @return
     */
    @Override
    public ResObject getById(String id) {
        log.info(this.getClass() + "getById-方法请求参数{}",id);
        if (StrUtil.isEmpty(id)){
            return R.failure("数据空");
        }
        // 通过ID 获取 单条数据
        PayFlowLogEntity payFlowLog = payFlowLogService.getById(id);
        if (payFlowLog == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),payFlowLog);
        }
        PayFlowLogVo payFlowLogVo = BeanConvertUtils.copy(payFlowLog, PayFlowLogVo.class);
        log.info(this.getClass() + "getById-方法请求结果{}",payFlowLogVo);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),payFlowLogVo);
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 保存平台交易流水日志 数据
     * @date 2020/2/12 17:09
     * @param  * @param PayFlowLogPageQueryParam
     * @return
     */
    @Override
    public ResObject save(PayFlowLogInstallParam installParam) {
        log.info(this.getClass() + "save方法请求参数{}",installParam);
        if (installParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        PayFlowLogEntity payFlowLog = BeanConvertUtils.copy(installParam, PayFlowLogEntity.class);
        Boolean result = payFlowLogService.save(payFlowLog);
        log.info(this.getClass() + "save-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_INSTALL_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_INSTALL_FAILL.subCode(),SubResultCode.DATA_INSTALL_FAILL.subMsg());
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description  修改平台交易流水日志 数据
     * @date 2020/2/12 17:09
     * @param  * @param PayFlowLogPageQueryParam
     * @return
     */
    @Override
    public ResObject update(PayFlowLogEditParam updateParam) {
        log.info(this.getClass() + "update方法请求参数{}",updateParam);
        if (updateParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        PayFlowLogEntity payFlowLog = BeanConvertUtils.copy(updateParam, PayFlowLogEntity.class);
        Boolean result = payFlowLogService.updateById(payFlowLog);
        log.info(this.getClass() + "update-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_UPDATE_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_UPDATE_FAILL.subCode(),SubResultCode.DATA_UPDATE_FAILL.subMsg());
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 数组删除平台交易流水日志 数据
     * @date 2020/2/12 17:09
     * @param  * @param PayFlowLogPageQueryParam
     * @return
     */
    @Override
    public ResObject deleteByIds(String[] ids) {
        if (ids.length <= 0){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        return R.toRes(payFlowLogService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * *通过id删除平台交易流水日志 信息
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
        Boolean result = payFlowLogService.removeById(id);
        log.info(this.getClass() + "deleteById-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_DELETE_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_DELETE_FAILL.subCode(),SubResultCode.DATA_DELETE_FAILL.subMsg());
    }

    /**
     * *导出平台交易流水日志 信息
     **/
    @Override
    public ResObject exportXls(PayFlowLogPageQueryParam param, HttpServletResponse response)throws IOException {
        log.info(this.getClass() + "exportXls方法请求参数{}",param);
        QueryWrapper queryWrapper = this.getQueryWrapper(payFlowLogMapStruct, param);
        List<PayFlowLogEntity> list = payFlowLogService.list(queryWrapper);
        List<PayFlowLogVo>payFlowLogList = payFlowLogMapStruct.toVoList(list);
        ExcelUtils.exportExcel(payFlowLogList, PayFlowLogVo.class, "", new ExportParams(), response);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.EXPORT_DATA_SUCCESS.subMsg());
    }

    /**
     * *修改状态
     **/
    @Override
    public ResObject changeStatus(PayFlowLogEditParam param) {
        log.info(this.getClass() + "changeStatus方法请求参数{}",param);
        if (StrUtil.isEmpty(param.getId())){
            log.error("ID数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        PayFlowLogEntity PayFlowLogEntity = new PayFlowLogEntity();
        PayFlowLogEntity.setId(param.getId());
        PayFlowLogEntity.setStatus(param.getStatus());
        //PayFlowLogEntity.setUpdateTime()
        Boolean result = payFlowLogService.updateById(PayFlowLogEntity);
        log.info(this.getClass() +"changeStatus方法请求对象参数{}，请求结果{}",PayFlowLogEntity,result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_STATUS_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_STATUS_FAILL.subCode(),SubResultCode.DATA_STATUS_FAILL.subMsg());
    }

}

