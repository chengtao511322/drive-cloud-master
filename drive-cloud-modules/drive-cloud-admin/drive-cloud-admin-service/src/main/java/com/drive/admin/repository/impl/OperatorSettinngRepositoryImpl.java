package com.drive.admin.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.drive.admin.pojo.entity.OperatorSettinngEntity;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.drive.admin.service.TestTrainPriceService;
import com.drive.common.core.base.BaseController;
import com.drive.admin.repository.OperatorSettinngRepository;
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
import com.drive.admin.service.OperatorSettinngService;
import com.drive.common.data.utils.ExcelUtils;
import java.util.List;
import java.util.Arrays;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

                                        
/**
 *
 * 运营商参数设置表 服务类
 *
 * @author xiaoguo
 */
@Slf4j
@Service
public class  OperatorSettinngRepositoryImpl extends BaseController<OperatorSettinngPageQueryParam, OperatorSettinngEntity>  implements OperatorSettinngRepository{

    //  运营商参数设置表 服务
    @Autowired
    private OperatorSettinngService operatorSettinngService;
    //  运营商参数设置表 DO-DTO转化
    @Autowired
    private OperatorSettinngMapStruct operatorSettinngMapStruct;

    @Autowired
    private TestTrainPriceService testTrainPriceService;

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 运营商参数设置表 分页列表
     * @date 2020/2/12 17:09
     * @param  * @param OperatorSettinngPageQueryParam
     * @return
     */
    @Override
    public ResObject pageList(OperatorSettinngPageQueryParam param) {
        log.info(this.getClass() + "pageList-方法请求参数{}",param);
        Page<OperatorSettinngEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        // 条件查询
        QueryWrapper queryWrapper = this.getQueryWrapper(operatorSettinngMapStruct, param);

        //  模糊查询
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueNumberDescribeSearch()),"number_describe",param.getVagueNumberDescribeSearch());
        //  开始时间 结束时间都有才进入
        if (StrUtil.isNotEmpty(param.getBeginTime()) && StrUtil.isNotEmpty(param.getEndTime())){
            queryWrapper.between(StrUtil.isNotEmpty(param.getBeginTime()),"create_time",param.getBeginTime(),param.getEndTime());
        }

        if (StrUtil.isNotEmpty(param.getCreateDateTimeSearchArr()[0]) && StrUtil.isNotEmpty(param.getCreateDateTimeSearchArr()[1])){
            queryWrapper.between("create_time",param.getCreateDateTimeSearchArr()[0],param.getCreateDateTimeSearchArr()[1]);
        }
        IPage<OperatorSettinngEntity> pageList = operatorSettinngService.page(page, queryWrapper);
        if (pageList.getRecords().size() <= 0){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }
        Page<OperatorSettinngVo> operatorSettinngVoPage = operatorSettinngMapStruct.toVoList(pageList);
        log.info(this.getClass() + "pageList-方法请求结果{}",operatorSettinngVoPage);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),operatorSettinngVoPage);
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 运营商参数设置表 查询列表
     * @date 2020/2/12 17:09
     * @param  * @param OperatorSettinngPageQueryParam
     * @return
     */
    @Override
    public ResObject findList(OperatorSettinngPageQueryParam param) {
        log.info(this.getClass() + "findList-方法请求参数{}",param);
        // 这里判断条件进行查询
        QueryWrapper queryWrapper= this.getQueryWrapper(operatorSettinngMapStruct, param);
        // 如 queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"phone",param.getPhone());
        List<OperatorSettinngEntity> operatorSettinngList = operatorSettinngService.list(queryWrapper);
        if (operatorSettinngList == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),operatorSettinngList);
        }
        List<OperatorSettinngVo> operatorSettinngVoList = operatorSettinngMapStruct.toVoList(operatorSettinngList);
        log.info(this.getClass() + "findList-方法请求结果{}",operatorSettinngVoList);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),operatorSettinngVoList);
    }

    /**
    * 对象条件查询返回单条运营商参数设置表数据
    * @param param
    * @return
    */
    @Override
    public ResObject getInfo(OperatorSettinngPageQueryParam param) {
        log.info(this.getClass() + "getInfo-方法请求参数{}",param);
        if (param == null){
            return R.failure("数据空");
        }
        // 这里判断条件进行查询
        QueryWrapper queryWrapper = this.getQueryWrapper(operatorSettinngMapStruct, param);
        OperatorSettinngEntity operatorSettinng = operatorSettinngService.getOne(queryWrapper);
        if (operatorSettinng == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),operatorSettinng);
        }
        OperatorSettinngVo operatorSettinngVo = BeanConvertUtils.copy(operatorSettinng, OperatorSettinngVo.class);
        log.info(this.getClass() + "getInfo-方法请求结果{}",operatorSettinngVo);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),operatorSettinngVo);
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 通过ID获取 运营商参数设置表 单条数据
     * @date 2020/2/12 17:09
     * @param  * @param OperatorSettinngPageQueryParam
     * @return
     */
    @Override
    public ResObject getById(String id) {
        log.info(this.getClass() + "getById-方法请求参数{}",id);
        if (StrUtil.isEmpty(id)){
            return R.failure("数据空");
        }
        // 通过ID 获取 单条数据
        OperatorSettinngEntity operatorSettinng = operatorSettinngService.getById(id);
        if (operatorSettinng == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),operatorSettinng);
        }
        OperatorSettinngVo operatorSettinngVo = BeanConvertUtils.copy(operatorSettinng, OperatorSettinngVo.class);
        log.info(this.getClass() + "getById-方法请求结果{}",operatorSettinngVo);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),operatorSettinngVo);
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 保存运营商参数设置表 数据
     * @date 2020/2/12 17:09
     * @param  * @param OperatorSettinngPageQueryParam
     * @return
     */
    @Override
    public ResObject save(OperatorSettinngInstallParam installParam) {
        log.info(this.getClass() + "save方法请求参数{}",installParam);
        if (installParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        // 幂等性查询
        QueryWrapper queryWrapper = new QueryWrapper();
        // 运营商名称
        queryWrapper.eq(StrUtil.isNotEmpty(installParam.getNumber()),"number",installParam.getNumber());
        queryWrapper.eq(StrUtil.isNotEmpty(installParam.getOperatorId()),"operator_id",installParam.getOperatorId());
        queryWrapper.last("limit 1");
        OperatorSettinngEntity isOperatorSettinng = operatorSettinngService.getOne(queryWrapper);
        if (isOperatorSettinng != null){
            return R.success(SubResultCode.DATA_IDEMPOTENT.subCode(),SubResultCode.DATA_IDEMPOTENT.subMsg());
        }
        OperatorSettinngEntity operatorSettinng = BeanConvertUtils.copy(installParam, OperatorSettinngEntity.class);
        Boolean result = operatorSettinngService.save(operatorSettinng);
        log.info(this.getClass() + "save-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_INSTALL_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_INSTALL_FAILL.subCode(),SubResultCode.DATA_INSTALL_FAILL.subMsg());
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description  修改运营商参数设置表 数据
     * @date 2020/2/12 17:09
     * @param  * @param OperatorSettinngPageQueryParam
     * @return
     */
    @Override
    public ResObject update(OperatorSettinngEditParam updateParam) {
        log.info(this.getClass() + "update方法请求参数{}",updateParam);
        if (updateParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        OperatorSettinngEntity operatorSettinng = BeanConvertUtils.copy(updateParam, OperatorSettinngEntity.class);
        Boolean result = operatorSettinngService.updateById(operatorSettinng);
        log.info(this.getClass() + "update-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_UPDATE_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_UPDATE_FAILL.subCode(),SubResultCode.DATA_UPDATE_FAILL.subMsg());
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 数组删除运营商参数设置表 数据
     * @date 2020/2/12 17:09
     * @param  * @param OperatorSettinngPageQueryParam
     * @return
     */
    @Override
    public ResObject deleteByIds(String[] ids) {
        if (ids.length <= 0){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        return R.toRes(operatorSettinngService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * *通过id删除运营商参数设置表 信息
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
        Boolean result = operatorSettinngService.removeById(id);
        log.info(this.getClass() + "deleteById-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_DELETE_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_DELETE_FAILL.subCode(),SubResultCode.DATA_DELETE_FAILL.subMsg());
    }

    /**
     * *导出运营商参数设置表 信息
     **/
    @Override
    public ResObject exportXls(OperatorSettinngPageQueryParam param, HttpServletResponse response)throws IOException {
        log.info(this.getClass() + "exportXls方法请求参数{}",param);
        QueryWrapper queryWrapper = this.getQueryWrapper(operatorSettinngMapStruct, param);
        List<OperatorSettinngEntity> list = operatorSettinngService.list(queryWrapper);
        List<OperatorSettinngVo>operatorSettinngList = operatorSettinngMapStruct.toVoList(list);
        ExcelUtils.exportExcel(operatorSettinngList, OperatorSettinngVo.class, "", new ExportParams(), response);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.EXPORT_DATA_SUCCESS.subMsg());
    }

    /**
     * *修改状态
     **/
    @Override
    public ResObject changeStatus(OperatorSettinngEditParam param) {
        log.info(this.getClass() + "changeStatus方法请求参数{}",param);
        if (StrUtil.isEmpty(param.getId())){
            log.error("ID数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        OperatorSettinngEntity OperatorSettinngEntity = new OperatorSettinngEntity();
        OperatorSettinngEntity.setId(param.getId());
        OperatorSettinngEntity.setStatus(param.getStatus());
        //OperatorSettinngEntity.setUpdateTime()
        Boolean result = operatorSettinngService.updateById(OperatorSettinngEntity);
        log.info(this.getClass() +"changeStatus方法请求对象参数{}，请求结果{}",OperatorSettinngEntity,result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_STATUS_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_STATUS_FAILL.subCode(),SubResultCode.DATA_STATUS_FAILL.subMsg());
    }

}

