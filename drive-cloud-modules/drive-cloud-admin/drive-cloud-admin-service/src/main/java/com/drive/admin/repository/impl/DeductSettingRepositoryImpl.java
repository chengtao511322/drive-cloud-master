package com.drive.admin.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.drive.admin.pojo.entity.DeductSettingEntity;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.drive.common.core.base.BaseController;
import com.drive.admin.repository.DeductSettingRepository;
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
import com.drive.admin.service.DeductSettingService;
import com.drive.common.data.utils.ExcelUtils;
import java.util.List;
import java.util.Arrays;
import java.io.IOException;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

                                    
/**
 *
 * 提成设置表 服务类
 *
 * @author xiaoguo
 */
@Slf4j
@Service
public class  DeductSettingRepositoryImpl extends BaseController<DeductSettingPageQueryParam, DeductSettingEntity>  implements DeductSettingRepository{

    //  提成设置表 服务
    @Autowired
    private DeductSettingService deductSettingService;
    //  提成设置表 DO-DTO转化
    @Autowired
    private DeductSettingMapStruct deductSettingMapStruct;

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 提成设置表 分页列表
     * @date 2020/2/12 17:09
     * @param  * @param DeductSettingPageQueryParam
     * @return
     */
    @Override
    public ResObject pageList(DeductSettingPageQueryParam param) {
        log.info(this.getClass() + "pageList-方法请求参数{}",param);
        Page<DeductSettingEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        // 条件查询
        QueryWrapper queryWrapper = this.getQueryWrapper(deductSettingMapStruct, param);

        //  模糊查询
        //queryWrapper.like(StrUtil.isNotEmpty(param.getVagueNameSearch()),"name",param.getVagueNameSearch());
        //  开始时间 结束时间都有才进入
        if (StrUtil.isNotEmpty(param.getBeginTime()) && StrUtil.isNotEmpty(param.getEndTime())){
            queryWrapper.between(StrUtil.isNotEmpty(param.getBeginTime()),"create_time",param.getBeginTime(),param.getEndTime());
        }
        IPage<DeductSettingEntity> pageList = deductSettingService.page(page, queryWrapper);
        if (pageList.getRecords().size() <= 0){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }
        Page<DeductSettingVo> deductSettingVoPage = deductSettingMapStruct.toVoList(pageList);
        log.info(this.getClass() + "pageList-方法请求结果{}",deductSettingVoPage);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),deductSettingVoPage);
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 提成设置表 查询列表
     * @date 2020/2/12 17:09
     * @param  * @param DeductSettingPageQueryParam
     * @return
     */
    @Override
    public ResObject findList(DeductSettingPageQueryParam param) {
        log.info(this.getClass() + "findList-方法请求参数{}",param);
        // 这里判断条件进行查询
        QueryWrapper queryWrapper= this.getQueryWrapper(deductSettingMapStruct, param);
        // 如 queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"phone",param.getPhone());
        List<DeductSettingEntity> deductSettingList = deductSettingService.list(queryWrapper);
        if (deductSettingList == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),deductSettingList);
        }
        List<DeductSettingVo> deductSettingVoList = deductSettingMapStruct.toVoList(deductSettingList);
        log.info(this.getClass() + "findList-方法请求结果{}",deductSettingVoList);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),deductSettingVoList);
    }

    /**
    * 对象条件查询返回单条提成设置表数据
    * @param param
    * @return
    */
    @Override
    public ResObject getInfo(DeductSettingPageQueryParam param) {
        log.info(this.getClass() + "getInfo-方法请求参数{}",param);
        if (param == null){
            return R.failure("数据空");
        }
        // 这里判断条件进行查询
        QueryWrapper queryWrapper = this.getQueryWrapper(deductSettingMapStruct, param);
        DeductSettingEntity deductSetting = deductSettingService.getOne(queryWrapper);
        if (deductSetting == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),deductSetting);
        }
        DeductSettingVo deductSettingVo = BeanConvertUtils.copy(deductSetting, DeductSettingVo.class);
        log.info(this.getClass() + "getInfo-方法请求结果{}",deductSettingVo);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),deductSettingVo);
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 通过ID获取 提成设置表 单条数据
     * @date 2020/2/12 17:09
     * @param  * @param DeductSettingPageQueryParam
     * @return
     */
    @Override
    public ResObject getById(String id) {
        log.info(this.getClass() + "getById-方法请求参数{}",id);
        if (StrUtil.isEmpty(id)){
            return R.failure("数据空");
        }
        // 通过ID 获取 单条数据
        DeductSettingEntity deductSetting = deductSettingService.getById(id);
        if (deductSetting == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),deductSetting);
        }
        DeductSettingVo deductSettingVo = BeanConvertUtils.copy(deductSetting, DeductSettingVo.class);
        log.info(this.getClass() + "getById-方法请求结果{}",deductSettingVo);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),deductSettingVo);
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 保存提成设置表 数据
     * @date 2020/2/12 17:09
     * @param  * @param DeductSettingPageQueryParam
     * @return
     */
    @Override
    public ResObject save(DeductSettingInstallParam installParam) {
        log.info(this.getClass() + "save方法请求参数{}",installParam);
        if (installParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        DeductSettingEntity deductSetting = BeanConvertUtils.copy(installParam, DeductSettingEntity.class);
        Boolean result = deductSettingService.save(deductSetting);
        log.info(this.getClass() + "save-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_INSTALL_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_INSTALL_FAILL.subCode(),SubResultCode.DATA_INSTALL_FAILL.subMsg());
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description  修改提成设置表 数据
     * @date 2020/2/12 17:09
     * @param  * @param DeductSettingPageQueryParam
     * @return
     */
    @Override
    public ResObject update(DeductSettingEditParam updateParam) {
        log.info(this.getClass() + "update方法请求参数{}",updateParam);
        if (updateParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        DeductSettingEntity deductSetting = BeanConvertUtils.copy(updateParam, DeductSettingEntity.class);
        Boolean result = deductSettingService.updateById(deductSetting);
        log.info(this.getClass() + "update-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_UPDATE_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_UPDATE_FAILL.subCode(),SubResultCode.DATA_UPDATE_FAILL.subMsg());
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 数组删除提成设置表 数据
     * @date 2020/2/12 17:09
     * @param  * @param DeductSettingPageQueryParam
     * @return
     */
    @Override
    public ResObject deleteByIds(String[] ids) {
        if (ids.length <= 0){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        return R.toRes(deductSettingService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * *通过id删除提成设置表 信息
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
        Boolean result = deductSettingService.removeById(id);
        log.info(this.getClass() + "deleteById-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_DELETE_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_DELETE_FAILL.subCode(),SubResultCode.DATA_DELETE_FAILL.subMsg());
    }

    /**
     * *导出提成设置表 信息
     **/
    @Override
    public ResObject exportXls(DeductSettingPageQueryParam param, HttpServletResponse response)throws IOException {
        log.info(this.getClass() + "exportXls方法请求参数{}",param);
        QueryWrapper queryWrapper = this.getQueryWrapper(deductSettingMapStruct, param);
        List<DeductSettingEntity> list = deductSettingService.list(queryWrapper);
        List<DeductSettingVo>deductSettingList = deductSettingMapStruct.toVoList(list);
        ExcelUtils.exportExcel(deductSettingList, DeductSettingVo.class, "", new ExportParams(), response);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.EXPORT_DATA_SUCCESS.subMsg());
    }

    /**
     * *修改状态
     **/
    @Override
    public ResObject changeStatus(DeductSettingEditParam param) {
        log.info(this.getClass() + "changeStatus方法请求参数{}",param);

        DeductSettingEntity DeductSettingEntity = new DeductSettingEntity();
        //DeductSettingEntity.setUpdateTime()
        Boolean result = deductSettingService.updateById(DeductSettingEntity);
        log.info(this.getClass() +"changeStatus方法请求对象参数{}，请求结果{}",DeductSettingEntity,result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_STATUS_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_STATUS_FAILL.subCode(),SubResultCode.DATA_STATUS_FAILL.subMsg());
    }
    @Override
    public ResObject saveBatch(List<DeductSettingInstallParam> deductSettingInstallParamList) {
        log.info(this.getClass() + "changeStatus方法请求参数{}",deductSettingInstallParamList);
        List<DeductSettingEntity> deductSettingList = BeanConvertUtils.copyList(deductSettingInstallParamList,DeductSettingEntity.class);
        // 先删除 数据
        QueryWrapper delQueryWrapper = new  QueryWrapper();
        delQueryWrapper.eq("recommend_manager_id",deductSettingList.stream().map(DeductSettingEntity::getRecommendManagerId).collect(Collectors.joining()));
        deductSettingService.remove(delQueryWrapper);
        //DeductSettingEntity.setUpdateTime()
        Boolean result = deductSettingService.saveOrUpdateBatch(deductSettingList);
        log.info(this.getClass() +"changeStatus方法请求对象参数{}，请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_STATUS_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_STATUS_FAILL.subCode(),SubResultCode.DATA_STATUS_FAILL.subMsg());
    }

}

