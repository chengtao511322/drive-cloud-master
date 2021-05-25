package com.drive.admin.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.drive.admin.pojo.entity.SysParamEntity;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.drive.common.core.base.BaseController;
import com.drive.admin.repository.SysParamRepository;
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
import com.drive.admin.service.SysParamService;
import com.drive.common.data.utils.ExcelUtils;
import java.util.List;
import java.util.Arrays;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

                                            
/**
 *
 * 系统配置参数表 服务类
 *
 * @author chentao
 */
@Slf4j
@Service
public class  SysParamRepositoryImpl extends BaseController<SysParamPageQueryParam, SysParamEntity>  implements SysParamRepository{

    //  系统配置参数表 服务
    @Autowired
    private SysParamService sysParamService;
    //  系统配置参数表 DO-DTO转化
    @Autowired
    private SysParamMapStruct sysParamMapStruct;

    /*
     *
     *功能描述
     * @author chentao
     * @description 系统配置参数表 分页列表
     * @date 2020/2/12 17:09
     * @param  * @param SysParamPageQueryParam
     * @return
     */
    @Override
    public ResObject pageList(SysParamPageQueryParam param) {
        log.info(this.getClass() + "pageList-方法请求参数{}",param);
        Page<SysParamEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        // 条件查询
        QueryWrapper queryWrapper = this.getQueryWrapper(sysParamMapStruct, param);

        //  模糊查询
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueNameSearch()),"name",param.getVagueNameSearch());

        //参数名称模糊查询
        queryWrapper.like(StrUtil.isNotEmpty(param.getVaguePrmNameSearch()),"prm_name",param.getVaguePrmNameSearch());
        //  开始时间 结束时间都有才进入
        if (StrUtil.isNotEmpty(param.getBeginTime()) && StrUtil.isNotEmpty(param.getEndTime())){
            queryWrapper.between(StrUtil.isNotEmpty(param.getBeginTime()),"create_time",param.getBeginTime(),param.getEndTime());
        }
        IPage<SysParamEntity> pageList = sysParamService.page(page, queryWrapper);
        if (pageList.getRecords().size() <= 0){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }
        Page<SysParamVo> sysParamVoPage = sysParamMapStruct.toVoList(pageList);
        log.info(this.getClass() + "pageList-方法请求结果{}",sysParamVoPage);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),sysParamVoPage);
    }

    /*
     *
     *功能描述
     * @author chentao
     * @description 系统配置参数表 查询列表
     * @date 2020/2/12 17:09
     * @param  * @param SysParamPageQueryParam
     * @return
     */
    @Override
    public ResObject findList(SysParamPageQueryParam param) {
        log.info(this.getClass() + "findList-方法请求参数{}",param);
        // 这里判断条件进行查询
        QueryWrapper queryWrapper= this.getQueryWrapper(sysParamMapStruct, param);
        // 通过名称查询
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueNameSearch()),"name",param.getVagueNameSearch());
        // 如 queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"phone",param.getPhone());
        List<SysParamEntity> sysParamList = sysParamService.list(queryWrapper);
        if (sysParamList == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),sysParamList);
        }
        List<SysParamVo> sysParamVoList = sysParamMapStruct.toVoList(sysParamList);
        log.info(this.getClass() + "findList-方法请求结果{}",sysParamVoList);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),sysParamVoList);
    }

    /**
    * 对象条件查询返回单条系统配置参数表数据
    * @param param
    * @return
    */
    @Override
    public ResObject getInfo(SysParamPageQueryParam param) {
        log.info(this.getClass() + "getInfo-方法请求参数{}",param);
        if (param == null){
            return R.failure("数据空");
        }
        // 这里判断条件进行查询
        QueryWrapper queryWrapper = this.getQueryWrapper(sysParamMapStruct, param);
        // 这里有且只返回一条数据
        queryWrapper.last("limit 1");
        SysParamEntity sysParam = sysParamService.getOne(queryWrapper);
        if (sysParam == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),sysParam);
        }
        SysParamVo sysParamVo = BeanConvertUtils.copy(sysParam, SysParamVo.class);
        log.info(this.getClass() + "getInfo-方法请求结果{}",sysParamVo);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),sysParamVo);
    }

    /*
     *
     *功能描述
     * @author chentao
     * @description 通过ID获取 系统配置参数表 单条数据
     * @date 2020/2/12 17:09
     * @param  * @param SysParamPageQueryParam
     * @return
     */
    @Override
    public ResObject getById(String prmEnumId) {
        log.info(this.getClass() + "getById-方法请求参数{}",prmEnumId);
        if (StrUtil.isEmpty(prmEnumId)){
            return R.failure("数据空");
        }
        // 通过ID 获取 单条数据
        SysParamEntity sysParam = sysParamService.getById(prmEnumId);
        if (sysParam == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),sysParam);
        }
        SysParamVo sysParamVo = BeanConvertUtils.copy(sysParam, SysParamVo.class);
        log.info(this.getClass() + "getById-方法请求结果{}",sysParamVo);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),sysParamVo);
    }

    /*
     *
     *功能描述
     * @author chentao
     * @description 保存系统配置参数表 数据
     * @date 2020/2/12 17:09
     * @param  * @param SysParamPageQueryParam
     * @return
     */
    @Override
    public ResObject save(SysParamInstallParam installParam) {
        //枚举id是否重复
        boolean  idRepeat = false;
        List<SysParamEntity> list = sysParamService.list();
        for (SysParamEntity sysParamEntity : list) {
            if(sysParamEntity.getPrmEnumId().equals(installParam.getPrmEnumId())){
                idRepeat = true;
                log.error(this.getClass() + "新增系统参数配置时id重复");
                return R.failure(SubResultCode.PARAMISINVALID.subCode(),SubResultCode.PARAMISINVALID.subMsg());
            }
        }
        log.info(this.getClass() + "save方法请求参数{}",installParam);
        if (installParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        SysParamEntity sysParam = BeanConvertUtils.copy(installParam, SysParamEntity.class);
        Boolean result = sysParamService.save(sysParam);
        log.info(this.getClass() + "save-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_INSTALL_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_INSTALL_FAILL.subCode(),SubResultCode.DATA_INSTALL_FAILL.subMsg());
    }

    /*
     *
     *功能描述
     * @author chentao
     * @description  修改系统配置参数表 数据
     * @date 2020/2/12 17:09
     * @param  * @param SysParamPageQueryParam
     * @return
     */
    @Override
    public ResObject update(SysParamEditParam updateParam) {
        log.info(this.getClass() + "update方法请求参数{}",updateParam);
        if (updateParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        SysParamEntity sysParam = BeanConvertUtils.copy(updateParam, SysParamEntity.class);
        Boolean result = sysParamService.updateById(sysParam);
        log.info(this.getClass() + "update-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_UPDATE_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_UPDATE_FAILL.subCode(),SubResultCode.DATA_UPDATE_FAILL.subMsg());
    }

    /*
     *
     *功能描述
     * @author chentao
     * @description 数组删除系统配置参数表 数据
     * @date 2020/2/12 17:09
     * @param  * @param SysParamPageQueryParam
     * @return
     */
    @Override
    public ResObject deleteByIds(String[] prmEnumIds) {
        if (prmEnumIds.length <= 0){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        return R.toRes(sysParamService.removeByIds(Arrays.asList(prmEnumIds)));
    }

    /**
     * *通过id删除系统配置参数表 信息
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
        Boolean result = sysParamService.removeById(id);
        log.info(this.getClass() + "deleteById-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_DELETE_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_DELETE_FAILL.subCode(),SubResultCode.DATA_DELETE_FAILL.subMsg());
    }

    /**
     * *导出系统配置参数表 信息
     **/
    @Override
    public ResObject exportXls(SysParamPageQueryParam param, HttpServletResponse response)throws IOException {
        log.info(this.getClass() + "exportXls方法请求参数{}",param);
        QueryWrapper queryWrapper = this.getQueryWrapper(sysParamMapStruct, param);
        List<SysParamEntity> list = sysParamService.list(queryWrapper);
        List<SysParamVo>sysParamList = sysParamMapStruct.toVoList(list);
        ExcelUtils.exportExcel(sysParamList, SysParamVo.class, "", new ExportParams(), response);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.EXPORT_DATA_SUCCESS.subMsg());
    }

    /**
     * *修改状态
     **/
    @Override
    public ResObject changeStatus(SysParamEditParam param) {
        log.info(this.getClass() + "changeStatus方法请求参数{}",param);
        if (StrUtil.isEmpty(param.getPrmEnumId())){
            log.error("ID数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        SysParamEntity SysParamEntity = new SysParamEntity();
        SysParamEntity.setPrmEnumId(param.getPrmEnumId());
        SysParamEntity.setPrmStatus(param.getPrmStatus());
        //SysParamEntity.setUpdateTime()
        Boolean result = sysParamService.updateById(SysParamEntity);
        log.info(this.getClass() +"changeStatus方法请求对象参数{}，请求结果{}",SysParamEntity,result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_STATUS_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_STATUS_FAILL.subCode(),SubResultCode.DATA_STATUS_FAILL.subMsg());
    }

}

