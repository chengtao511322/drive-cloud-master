package com.drive.admin.repository.impl;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.admin.pojo.dto.AreaEditParam;
import com.drive.admin.pojo.dto.AreaPageQueryParam;
import com.drive.admin.pojo.entity.AreaEntity;
import com.drive.admin.pojo.vo.AreaVo;
import com.drive.admin.repository.AreaRepository;
import com.drive.admin.service.AreaService;
import com.drive.admin.service.mapstruct.AreaMapStruct;
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
 *  服务类
 *
 * @author xiaoguo
 */
@Slf4j
@Service
public class  AreaRepositoryImpl extends BaseController<AreaPageQueryParam, AreaEntity>  implements AreaRepository{

    @Autowired
    private AreaService areaService;
    @Autowired
    private AreaMapStruct areaMapStruct;

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description  分页列表
     * @date 2020/2/12 17:09
     * @param  * @param AreaPageQueryParam
     * @return
     */
    @Override
    public ResObject pageList(AreaPageQueryParam param) {
        log.info(this.getClass() + "pageList-方法请求参数{}",param);
        Page<AreaEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        QueryWrapper queryWrapper = this.getQueryWrapper(areaMapStruct, param);
        // code
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueBaCodeSearch()),"ba_code",param.getVagueBaCodeSearch());
        // name
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueBaNameSearch()),"ba_name",param.getVagueBaNameSearch());
        IPage<AreaEntity> pageList = areaService.page(page, queryWrapper);
        if (pageList.getRecords().isEmpty()){
            return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }
        Page<AreaVo> areaVoPage = areaMapStruct.toVoList(pageList);
        log.info(this.getClass() + "pageList-方法请求结果{}",areaVoPage);
        return R.success(areaVoPage);
    }

    @Override
    public ResObject findList(AreaPageQueryParam param) {
        log.info(this.getClass() + "findList-方法请求参数{}",param);
        // 这里判断条件进行查询
        QueryWrapper queryWrapper= this.getQueryWrapper(areaMapStruct, param);
        // 如 queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"phone",param.getPhone());
        List<AreaEntity> pageList = areaService.list(queryWrapper);
        if (pageList.isEmpty()){
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }
        List<AreaVo> areaVoList = areaMapStruct.toVoList(pageList);
        log.info(this.getClass() + "findList-方法请求结果{}",areaVoList);
        if (areaVoList == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        return R.success(areaVoList);
    }



    @Override
    public ResObject getInfo(AreaPageQueryParam param) {
        log.info(this.getClass() + "getInfo-方法请求参数{}",param);
        if (param == null){
            return R.failure("数据空");
        }
        QueryWrapper queryWrapper = this.getQueryWrapper(areaMapStruct, param);
        AreaEntity area = areaService.getOne(queryWrapper);
        if (area ==null){
            log.error("活动数据对象空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        AreaVo areaVo = BeanConvertUtils.copy(area, AreaVo.class);
        log.info(this.getClass() + "getInfo-方法请求结果{}",areaVo);
        return R.success(areaVo);
    }

    /**
     * *通过ID获取 列表
     **/
    @Override
    public ResObject getById(String baCode) {
        log.info(this.getClass() + "getInfo-方法请求参数{}",baCode);
        if (StrUtil.isEmpty(baCode)){
            return R.failure("数据空");
        }
        AreaEntity area = areaService.getById(baCode);
        AreaVo areaVo = BeanConvertUtils.copy(area, AreaVo.class);
        log.info(this.getClass() + "getInfo-方法请求结果{}",areaVo);
        if (areaVo ==null){
            log.error("活动数据对象空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        return R.success(areaVo);
    }

    /**
     * *保存 信息
     **/
    @Override
    public ResObject save(AreaEditParam installParam) {
        log.info(this.getClass() + "save方法请求参数{}",installParam);
        if (installParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        // 幂等性校验
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("ba_code",installParam.getBaCode());
        queryWrapper.last("limit 1");
        int isArea = areaService.count(queryWrapper);
        if (isArea > 0){
            return R.success(SubResultCode.DATA_IDEMPOTENT.subCode(),SubResultCode.DATA_IDEMPOTENT.subMsg());
        }
        AreaEntity area = BeanConvertUtils.copy(installParam, AreaEntity.class);
        Boolean result = areaService.saveArea(area);
        log.info(this.getClass() + "save-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *修改 信息
     **/
    @Override
    public ResObject update(AreaEditParam updateParam) {
        log.info(this.getClass() + "update方法请求参数{}",updateParam);
        if (updateParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        // 幂等性校验
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.setEntity(updateParam);
        int isArea = areaService.count(queryWrapper);
        if (isArea > 0){
            return R.success(SubResultCode.DATA_IDEMPOTENT.subCode(),SubResultCode.DATA_IDEMPOTENT.subMsg());
        }
        AreaEntity area = BeanConvertUtils.copy(updateParam, AreaEntity.class);
        Boolean result = areaService.updateById(area);
        log.info(this.getClass() + "update-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *删除 信息
     **/
    @Override
    public ResObject deleteByIds(String[] baCodes) {
        if (baCodes.length <= 0){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        return R.toRes(areaService.removeByIds(Arrays.asList(baCodes)));
    }

    /**
     * *通过id删除 信息
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
        Boolean result = areaService.removeById(id);
        log.info(this.getClass() + "deleteById-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *导出 信息
     **/
    @Override
    public ResObject exportXls(AreaPageQueryParam param, HttpServletResponse response)throws IOException {
        log.info(this.getClass() + "exportXls方法请求参数{}",param);
        QueryWrapper queryWrapper = this.getQueryWrapper(areaMapStruct, param);
        List<AreaEntity> list = areaService.list(queryWrapper);
        List<AreaVo>areaList = areaMapStruct.toVoList(list);
        ExcelUtils.exportExcel(areaList, AreaVo.class, "", new ExportParams(), response);
        return R.success("导出成功");
    }

    /**
     * *修改状态
     **/
    @Override
    public ResObject changeStatus(AreaEditParam param) {
        log.info(this.getClass() + "changeStatus方法请求参数{}",param);
        if (StrUtil.isEmpty(param.getBaCode())){
            log.error("ID数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        AreaEntity AreaEntity = new AreaEntity();
        //AreaEntity.setUpdateTime()
        Boolean result = areaService.updateById(AreaEntity);
        log.info(this.getClass() +"changeStatus方法请求对象参数{}，请求结果{}",AreaEntity,result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    @Override
    public ResObject allList() {
        log.info(this.getClass() + "allList-方法请求参数{}");
        // 这里判断条件进行查询
        // 如 queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"phone",param.getPhone());
        List<AreaEntity> allList = areaService.list();
        List<AreaVo> areaVoList = areaMapStruct.toVoList(allList);
        log.info(this.getClass() + "findList-方法请求结果{}",areaVoList);
        if (areaVoList == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        return R.success(areaVoList);
    }

    @Override
    public ResObject delAreaByCode(String code) {
        log.info(this.getClass() + "delAreaByCode-方法请求参数{}",code);
        if (StrUtil.isEmpty(code)){
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        Boolean result = areaService.delAreaByCode(code);
        if (!result)return R.failure(SubResultCode.DATA_DELETE_FAILL.subCode(),SubResultCode.DATA_DELETE_FAILL.subMsg());
        return R.success(result);
    }
}

