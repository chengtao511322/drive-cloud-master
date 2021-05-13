package com.drive.admin.repository.impl;

import cn.hutool.core.codec.Base64;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.drive.admin.pojo.entity.OperatorRecruitEntity;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.drive.common.core.base.BaseController;
import com.drive.admin.repository.OperatorRecruitRepository;
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
import com.drive.admin.service.OperatorRecruitService;
import com.drive.common.data.utils.ExcelUtils;
import java.util.List;
import java.util.Arrays;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

                                                    
/**
 *
 * 运营商加盟申请 服务类
 *
 * @author xiaoguo
 */
@Slf4j
@Service
public class  OperatorRecruitRepositoryImpl extends BaseController<OperatorRecruitPageQueryParam, OperatorRecruitEntity>  implements OperatorRecruitRepository{

    //  运营商加盟申请 服务
    @Autowired
    private OperatorRecruitService operatorRecruitService;
    //  运营商加盟申请 DO-DTO转化
    @Autowired
    private OperatorRecruitMapStruct operatorRecruitMapStruct;

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 运营商加盟申请 分页列表
     * @date 2020/2/12 17:09
     * @param  * @param OperatorRecruitPageQueryParam
     * @return
     */
    @Override
    public ResObject pageList(OperatorRecruitPageQueryParam param) {
        log.info(this.getClass() + "pageList-方法请求参数{}",param);
        Page<OperatorRecruitEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        // 条件查询
        QueryWrapper queryWrapper = this.getQueryWrapper(operatorRecruitMapStruct, param);

        //  模糊查询
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueRealNameSearch()),"real_name",param.getVagueRealNameSearch());
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueCorporationNameSearch()),"corporation_name",param.getVagueCorporationNameSearch());
        queryWrapper.like(StrUtil.isNotEmpty(param.getVaguePhoneSearch()),"phone",param.getVaguePhoneSearch());
        //  开始时间 结束时间都有才进入
        if (StrUtil.isNotEmpty(param.getBeginTime()) && StrUtil.isNotEmpty(param.getEndTime())){
            queryWrapper.between(StrUtil.isNotEmpty(param.getBeginTime()),"create_time",param.getBeginTime(),param.getEndTime());
        }
        if (param.getCreateDateTimeSearchArr() != null && param.getCreateDateTimeSearchArr().length > 0 ){
            queryWrapper.between("create_time",param.getCreateDateTimeSearchArr()[0],param.getCreateDateTimeSearchArr()[1]);
        }
        IPage<OperatorRecruitEntity> pageList = operatorRecruitService.page(page, queryWrapper);
        if (pageList.getRecords().size() <= 0){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }
        Page<OperatorRecruitVo> operatorRecruitVoPage = operatorRecruitMapStruct.toVoList(pageList);
        log.info(this.getClass() + "pageList-方法请求结果{}",operatorRecruitVoPage);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),operatorRecruitVoPage);
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 运营商加盟申请 查询列表
     * @date 2020/2/12 17:09
     * @param  * @param OperatorRecruitPageQueryParam
     * @return
     */
    @Override
    public ResObject findList(OperatorRecruitPageQueryParam param) {
        log.info(this.getClass() + "findList-方法请求参数{}",param);
        // 这里判断条件进行查询
        QueryWrapper queryWrapper= this.getQueryWrapper(operatorRecruitMapStruct, param);
        // 如 queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"phone",param.getPhone());
        List<OperatorRecruitEntity> operatorRecruitList = operatorRecruitService.list(queryWrapper);
        if (operatorRecruitList == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),operatorRecruitList);
        }
        List<OperatorRecruitVo> operatorRecruitVoList = operatorRecruitMapStruct.toVoList(operatorRecruitList);
        log.info(this.getClass() + "findList-方法请求结果{}",operatorRecruitVoList);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),operatorRecruitVoList);
    }

    /**
    * 对象条件查询返回单条运营商加盟申请数据
    * @param param
    * @return
    */
    @Override
    public ResObject getInfo(OperatorRecruitPageQueryParam param) {
        log.info(this.getClass() + "getInfo-方法请求参数{}",param);
        if (param == null){
            return R.failure("数据空");
        }
        // 这里判断条件进行查询
        QueryWrapper queryWrapper = this.getQueryWrapper(operatorRecruitMapStruct, param);
        OperatorRecruitEntity operatorRecruit = operatorRecruitService.getOne(queryWrapper);
        if (operatorRecruit == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),operatorRecruit);
        }
        OperatorRecruitVo operatorRecruitVo = BeanConvertUtils.copy(operatorRecruit, OperatorRecruitVo.class);
        log.info(this.getClass() + "getInfo-方法请求结果{}",operatorRecruitVo);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),operatorRecruitVo);
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 通过ID获取 运营商加盟申请 单条数据
     * @date 2020/2/12 17:09
     * @param  * @param OperatorRecruitPageQueryParam
     * @return
     */
    @Override
    public ResObject getById(String id) {
        log.info(this.getClass() + "getById-方法请求参数{}",id);
        if (StrUtil.isEmpty(id)){
            return R.failure("数据空");
        }
        // 通过ID 获取 单条数据
        OperatorRecruitEntity operatorRecruit = operatorRecruitService.getById(id);
        if (operatorRecruit == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),operatorRecruit);
        }
        OperatorRecruitVo operatorRecruitVo = BeanConvertUtils.copy(operatorRecruit, OperatorRecruitVo.class);
        log.info(this.getClass() + "getById-方法请求结果{}",operatorRecruitVo);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),operatorRecruitVo);
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 保存运营商加盟申请 数据
     * @date 2020/2/12 17:09
     * @param  * @param OperatorRecruitPageQueryParam
     * @return
     */
    @Override
    public ResObject save(OperatorRecruitInstallParam installParam) {
        log.info(this.getClass() + "save方法请求参数{}",installParam);
        if (installParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        OperatorRecruitEntity operatorRecruit = BeanConvertUtils.copy(installParam, OperatorRecruitEntity.class);
        Boolean result = operatorRecruitService.save(operatorRecruit);
        log.info(this.getClass() + "save-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_INSTALL_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_INSTALL_FAILL.subCode(),SubResultCode.DATA_INSTALL_FAILL.subMsg());
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description  修改运营商加盟申请 数据
     * @date 2020/2/12 17:09
     * @param  * @param OperatorRecruitPageQueryParam
     * @return
     */
    @Override
    public ResObject update(OperatorRecruitEditParam updateParam) {
        log.info(this.getClass() + "update方法请求参数{}",updateParam);
        if (updateParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        OperatorRecruitEntity operatorRecruit = BeanConvertUtils.copy(updateParam, OperatorRecruitEntity.class);
        Boolean result = operatorRecruitService.updateById(operatorRecruit);
        log.info(this.getClass() + "update-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_UPDATE_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_UPDATE_FAILL.subCode(),SubResultCode.DATA_UPDATE_FAILL.subMsg());
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 数组删除运营商加盟申请 数据
     * @date 2020/2/12 17:09
     * @param  * @param OperatorRecruitPageQueryParam
     * @return
     */
    @Override
    public ResObject deleteByIds(String[] ids) {
        if (ids.length <= 0){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        return R.toRes(operatorRecruitService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * *通过id删除运营商加盟申请 信息
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
        Boolean result = operatorRecruitService.removeById(id);
        log.info(this.getClass() + "deleteById-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_DELETE_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_DELETE_FAILL.subCode(),SubResultCode.DATA_DELETE_FAILL.subMsg());
    }

    /**
     * *导出运营商加盟申请 信息
     **/
    @Override
    public ResObject exportXls(OperatorRecruitPageQueryParam param, HttpServletResponse response)throws IOException {
        log.info(this.getClass() + "exportXls方法请求参数{}",param);
        QueryWrapper queryWrapper = this.getQueryWrapper(operatorRecruitMapStruct, param);
        List<OperatorRecruitEntity> list = operatorRecruitService.list(queryWrapper);
        List<OperatorRecruitVo>operatorRecruitList = operatorRecruitMapStruct.toVoList(list);
        ExcelUtils.exportExcel(operatorRecruitList, OperatorRecruitVo.class, "", new ExportParams(), response);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.EXPORT_DATA_SUCCESS.subMsg());
    }

    /**
     * *修改状态
     **/
    @Override
    public ResObject changeStatus(OperatorRecruitEditParam param) {
        log.info(this.getClass() + "changeStatus方法请求参数{}",param);
        if (StrUtil.isEmpty(param.getId())){
            log.error("ID数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        OperatorRecruitEntity OperatorRecruitEntity = new OperatorRecruitEntity();
        OperatorRecruitEntity.setId(param.getId());
        //OperatorRecruitEntity.setStatus(param.getStatus());
        //OperatorRecruitEntity.setUpdateTime()
        Boolean result = operatorRecruitService.updateById(OperatorRecruitEntity);
        log.info(this.getClass() +"changeStatus方法请求对象参数{}，请求结果{}",OperatorRecruitEntity,result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_STATUS_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_STATUS_FAILL.subCode(),SubResultCode.DATA_STATUS_FAILL.subMsg());
    }

}

