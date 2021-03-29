package com.drive.admin.repository.impl;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.admin.pojo.dto.AppVersionEditParam;
import com.drive.admin.pojo.dto.AppVersionPageQueryParam;
import com.drive.admin.pojo.entity.AppVersionEntity;
import com.drive.admin.pojo.vo.AppVersionVo;
import com.drive.admin.repository.AppVersionRepository;
import com.drive.admin.service.AppVersionService;
import com.drive.admin.service.mapstruct.AppVersionMapStruct;
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
 * 平台应用版本表 服务类
 *
 * @author xiaoguo
 */
@Slf4j
@Service
public class  AppVersionRepositoryImpl extends BaseController<AppVersionPageQueryParam, AppVersionEntity>  implements AppVersionRepository{

    //  平台应用版本表 服务
    @Autowired
    private AppVersionService appVersionService;
    //  平台应用版本表 DO-DTO转化
    @Autowired
    private AppVersionMapStruct appVersionMapStruct;

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 平台应用版本表 分页列表
     * @date 2020/2/12 17:09
     * @param  * @param AppVersionPageQueryParam
     * @return
     */
    @Override
    public ResObject pageList(AppVersionPageQueryParam param) {
        log.info(this.getClass() + "pageList-方法请求参数{}",param);
        Page<AppVersionEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        // 条件查询
        QueryWrapper queryWrapper = this.getQueryWrapper(appVersionMapStruct, param);

        //  模糊查询
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueVersionNameSearch()),"version_name",param.getVagueVersionNameSearch());
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueVersionSearch()),"version",param.getVagueVersionSearch());
        //  开始时间 结束时间都有才进入
        if (StrUtil.isNotEmpty(param.getBeginTime()) && StrUtil.isNotEmpty(param.getEndTime())){
            queryWrapper.between(StrUtil.isNotEmpty(param.getBeginTime()),"create_time",param.getBeginTime(),param.getEndTime());
        }
        IPage<AppVersionEntity> pageList = appVersionService.page(page, queryWrapper);
        if (pageList.getRecords().size() <= 0){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }
        Page<AppVersionVo> appVersionVoPage = appVersionMapStruct.toVoList(pageList);
        log.info(this.getClass() + "pageList-方法请求结果{}",appVersionVoPage);
        return R.success(appVersionVoPage);
    }

    @Override
    public ResObject findList(AppVersionPageQueryParam param) {
        log.info(this.getClass() + "findList-方法请求参数{}",param);
        // 这里判断条件进行查询
        QueryWrapper queryWrapper= this.getQueryWrapper(appVersionMapStruct, param);
        // 如 queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"phone",param.getPhone());
        List<AppVersionEntity> appVersionList = appVersionService.list(queryWrapper);
        if (appVersionList == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),appVersionList);
        }
        List<AppVersionVo> appVersionVoList = appVersionMapStruct.toVoList(appVersionList);
        log.info(this.getClass() + "findList-方法请求结果{}",appVersionVoList);
        return R.success(appVersionVoList);
    }

    /**
    * 条件查询返回单条平台应用版本表数据
    * @param param
    * @return
    */
    @Override
    public ResObject getInfo(AppVersionPageQueryParam param) {
        log.info(this.getClass() + "getInfo-方法请求参数{}",param);
        if (param == null){
            return R.failure("数据空");
        }
        // 这里判断条件进行查询
        QueryWrapper queryWrapper = this.getQueryWrapper(appVersionMapStruct, param);
        AppVersionEntity appVersion = appVersionService.getOne(queryWrapper);
        if (appVersion == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),appVersion);
        }
        AppVersionVo appVersionVo = BeanConvertUtils.copy(appVersion, AppVersionVo.class);
        log.info(this.getClass() + "getInfo-方法请求结果{}",appVersionVo);
        return R.success(appVersionVo);
    }

    /**
     * *通过ID获取平台应用版本表 列表
     **/
    @Override
    public ResObject getById(String id) {
        log.info(this.getClass() + "getById-方法请求参数{}",id);
        if (StrUtil.isEmpty(id)){
            return R.failure("数据空");
        }
        AppVersionEntity appVersion = appVersionService.getById(id);
        if (appVersion == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),appVersion);
        }
        AppVersionVo appVersionVo = BeanConvertUtils.copy(appVersion, AppVersionVo.class);
        log.info(this.getClass() + "getInfo-方法请求结果{}",appVersionVo);
        return R.success(appVersionVo);
    }



    /**
     * *保存平台应用版本表 信息
     **/
    @Override
    public ResObject save(AppVersionEditParam installParam) {
        log.info(this.getClass() + "save方法请求参数{}",installParam);
        if (installParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        AppVersionEntity appVersion = BeanConvertUtils.copy(installParam, AppVersionEntity.class);
        Boolean result = appVersionService.save(appVersion);
        log.info(this.getClass() + "save-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *修改平台应用版本表 信息
     **/
    @Override
    public ResObject update(AppVersionEditParam updateParam) {
        log.info(this.getClass() + "update方法请求参数{}",updateParam);
        if (updateParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        AppVersionEntity appVersion = BeanConvertUtils.copy(updateParam, AppVersionEntity.class);
        Boolean result = appVersionService.updateById(appVersion);
        log.info(this.getClass() + "update-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *删除平台应用版本表 信息
     **/
    @Override
    public ResObject deleteByIds(String[] ids) {
        if (ids.length <= 0){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        return R.toRes(appVersionService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * *通过id删除平台应用版本表 信息
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
        Boolean result = appVersionService.removeById(id);
        log.info(this.getClass() + "deleteById-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *导出平台应用版本表 信息
     **/
    @Override
    public ResObject exportXls(AppVersionPageQueryParam param, HttpServletResponse response)throws IOException {
        log.info(this.getClass() + "exportXls方法请求参数{}",param);
        QueryWrapper queryWrapper = this.getQueryWrapper(appVersionMapStruct, param);
        List<AppVersionEntity> list = appVersionService.list(queryWrapper);
        List<AppVersionVo>appVersionList = appVersionMapStruct.toVoList(list);
        ExcelUtils.exportExcel(appVersionList, AppVersionVo.class, "", new ExportParams(), response);
        return R.success("导出成功");
    }

    /**
     * *修改状态
     **/
    @Override
    public ResObject changeStatus(AppVersionEditParam param) {
        log.info(this.getClass() + "changeStatus方法请求参数{}",param);
        if (StrUtil.isEmpty(param.getId())){
            log.error("ID数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        AppVersionEntity appVersionEntity = new AppVersionEntity();
        appVersionEntity.setId(param.getId());
        //AppVersionEntity.setStatus(param.getStatus());
        //AppVersionEntity.setUpdateTime()
        Boolean result = appVersionService.updateById(appVersionEntity);
        log.info(this.getClass() +"changeStatus方法请求对象参数{}，请求结果{}",appVersionEntity,result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

}

