package com.drive.admin.repository.impl;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.admin.pojo.dto.DriveSchoolEditParam;
import com.drive.admin.pojo.dto.DriveSchoolPageQueryParam;
import com.drive.admin.pojo.entity.DriveSchoolEntity;
import com.drive.admin.pojo.vo.DriveSchoolVo;
import com.drive.admin.repository.DriveSchoolRepository;
import com.drive.admin.service.AreaService;
import com.drive.admin.service.DriveSchoolService;
import com.drive.admin.service.mapstruct.DriveSchoolMapStruct;
import com.drive.common.core.base.BaseController;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.biz.SubResultCode;
import com.drive.common.core.utils.BeanConvertUtils;
import com.drive.common.core.utils.StringUtils;
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
 * 平台为合作驾校开通账号后，将其驾校信息统一维护到该表中。 服务类
 *
 * @author xiaoguo
 */
@Slf4j
@Service
public class  DriveSchoolRepositoryImpl extends BaseController<DriveSchoolPageQueryParam, DriveSchoolEntity>  implements DriveSchoolRepository{

    //  平台为合作驾校开通账号后，将其驾校信息统一维护到该表中。 服务
    @Autowired
    private DriveSchoolService driveSchoolService;
    //  平台为合作驾校开通账号后，将其驾校信息统一维护到该表中。 DO-DTO转化
    @Autowired
    private DriveSchoolMapStruct driveSchoolMapStruct;

    @Autowired
    private AreaService areaService;

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 平台为合作驾校开通账号后，将其驾校信息统一维护到该表中。 分页列表
     * @date 2020/2/12 17:09
     * @param  * @param DriveSchoolPageQueryParam
     * @return
     */
    @Override
    public ResObject pageList(DriveSchoolPageQueryParam param) {
        log.info(this.getClass() + "pageList-方法请求参数{}",param);
        Page<DriveSchoolEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        QueryWrapper queryWrapper = this.getQueryWrapper(driveSchoolMapStruct, param);
        queryWrapper.like(StringUtils.isNotBlank(param.getVagueSchoolName()),"school_name",param.getVagueSchoolName());
        // 登录时间
        //queryWrapper.apply(StrUtil.isNotBlank(param.getSearchLoginDate()),
                //"date_format (login_time,'%Y-%m-%d') = date_format('" + param.getSearchLoginDate() + "','%Y-%m-%d')");
        // 推荐时间
        //queryWrapper.apply(StrUtil.isNotBlank(param.getSearchRecommendDate()),
                //"date_format (recommend_date,'%Y-%m-%d') <= date_format('" + LocalDate.now().toString() + "','%Y-%m-%d')");
                /*.apply(StrUtil.isNotBlank(end_date),
                        "date_format (optime,'%Y-%m-%d') <= date_format('" + end_date + "','%Y-%m-%d')")*/
        // 没有开始时间 默认查询
/*        if (StrUtil.isEmpty(param.getBeginTime())){
            queryWrapper.apply(StrUtil.isNotBlank(param.getSearchRecommendDate()),
                    "date_format (create_time,'%Y-%m-%d') <= date_format('" + LocalDate.now().toString() + "','%Y-%m-%d')");
        }
        // 没有结束时间
        if (StrUtil.isEmpty(param.getEndTime())){
            queryWrapper.between(StrUtil.isNotEmpty(param.getBeginTime()),"create_time",param.getBeginTime(),LocalDate.now().toString());
        }*/
        //  开始时间 结束时间都有才进入
        if (StrUtil.isNotEmpty(param.getBeginTime()) && StrUtil.isNotEmpty(param.getEndTime())){
            queryWrapper.between(StrUtil.isNotEmpty(param.getBeginTime()),"create_time",param.getBeginTime(),param.getEndTime());
        }
        IPage<DriveSchoolEntity> pageList = driveSchoolService.page(page, queryWrapper);
        if (pageList.getRecords().size() <= 0){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }
        Page<DriveSchoolVo> driveSchoolVoPage = driveSchoolMapStruct.toVoList(pageList);
        log.info(this.getClass() + "pageList-方法请求结果{}",driveSchoolVoPage);
        return R.success(driveSchoolVoPage);
    }

    @Override
    public ResObject findList(DriveSchoolPageQueryParam param) {
        log.info(this.getClass() + "findList-方法请求参数{}",param);
        // 这里判断条件进行查询
        QueryWrapper queryWrapper= this.getQueryWrapper(driveSchoolMapStruct, param);
        // 如 queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"phone",param.getPhone());
        List<DriveSchoolEntity> driveSchoolList = driveSchoolService.list(queryWrapper);
        if (driveSchoolList == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        List<DriveSchoolVo> driveSchoolVoList = driveSchoolMapStruct.toVoList(driveSchoolList);
        driveSchoolVoList.stream().forEach((item) ->{
            // 省市区
            if (StrUtil.isNotEmpty(item.getProvinceId()))item.setProvinceName(areaService.getByBaCode(item.getProvinceId()).getBaName());
            if (StrUtil.isNotEmpty(item.getCityId()))item.setCityName(areaService.getByBaCode(item.getCityId()).getBaName());
            if (StrUtil.isNotEmpty(item.getAreaId()))item.setAreaName(areaService.getByBaCode(item.getAreaId()).getBaName());
        });
        log.info(this.getClass() + "findList-方法请求结果{}",driveSchoolVoList);
        return R.success(driveSchoolVoList);
    }



    @Override
    public ResObject getInfo(DriveSchoolPageQueryParam param) {
        return null;
    }

    /**
     * *通过ID获取平台为合作驾校开通账号后，将其驾校信息统一维护到该表中。 列表
     **/
    @Override
    public ResObject getById(String id) {
        log.info(this.getClass() + "getInfo-方法请求参数{}",id);
        if (StrUtil.isEmpty(id)){
            return R.failure("数据空");
        }
        DriveSchoolEntity driveSchool = driveSchoolService.getById(id);
        if (driveSchool == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        DriveSchoolVo driveSchoolVo = BeanConvertUtils.copy(driveSchool, DriveSchoolVo.class);
        log.info(this.getClass() + "getInfo-方法请求结果{}",driveSchoolVo);
        return R.success(driveSchoolVo);
    }

    /**
     * *保存平台为合作驾校开通账号后，将其驾校信息统一维护到该表中。 信息
     **/
    @Override
    public ResObject save(DriveSchoolEditParam installParam) {
        log.info(this.getClass() + "save方法请求参数{}",installParam);
        if (installParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        // 幂等性处理
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("province_id",installParam.getProvinceId());
        queryWrapper.eq("city_id",installParam.getCityId());
        queryWrapper.eq("area_id",installParam.getAreaId());
        int isDriveSchool = driveSchoolService.count(queryWrapper);
        if (isDriveSchool > 0){
            return R.success(SubResultCode.DATA_IDEMPOTENT.subCode(),SubResultCode.DATA_IDEMPOTENT.subMsg());
        }
        DriveSchoolEntity driveSchool = BeanConvertUtils.copy(installParam, DriveSchoolEntity.class);
        Boolean result = driveSchoolService.save(driveSchool);
        log.info(this.getClass() + "save-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *修改平台为合作驾校开通账号后，将其驾校信息统一维护到该表中。 信息
     **/
    @Override
    public ResObject update(DriveSchoolEditParam updateParam) {
        log.info(this.getClass() + "update方法请求参数{}",updateParam);
        if (updateParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        // 幂等性处理
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.setEntity(updateParam);
        int isDriveSchool = driveSchoolService.count(queryWrapper);
        if (isDriveSchool > 0){
            return R.success(SubResultCode.DATA_IDEMPOTENT.subCode(),SubResultCode.DATA_IDEMPOTENT.subMsg());
        }
        DriveSchoolEntity driveSchool = BeanConvertUtils.copy(updateParam, DriveSchoolEntity.class);
        Boolean result = driveSchoolService.updateById(driveSchool);
        log.info(this.getClass() + "update-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *删除平台为合作驾校开通账号后，将其驾校信息统一维护到该表中。 信息
     **/
    @Override
    public ResObject deleteByIds(String[] ids) {
        if (ids.length <= 0){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        return R.toRes(driveSchoolService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * *通过id删除平台为合作驾校开通账号后，将其驾校信息统一维护到该表中。 信息
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
        Boolean result = driveSchoolService.removeById(id);
        log.info(this.getClass() + "deleteById-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *导出平台为合作驾校开通账号后，将其驾校信息统一维护到该表中。 信息
     **/
    @Override
    public ResObject exportXls(DriveSchoolPageQueryParam param, HttpServletResponse response)throws IOException {
        log.info(this.getClass() + "exportXls方法请求参数{}",param);
        QueryWrapper queryWrapper = this.getQueryWrapper(driveSchoolMapStruct, param);
        List<DriveSchoolEntity> list = driveSchoolService.list(queryWrapper);
        List<DriveSchoolVo>driveSchoolList = driveSchoolMapStruct.toVoList(list);
        ExcelUtils.exportExcel(driveSchoolList, DriveSchoolVo.class, "", new ExportParams(), response);
        return R.success("导出成功");
    }

    /**
     * *修改状态
     **/
    @Override
    public ResObject changeStatus(DriveSchoolEditParam param) {
        log.info(this.getClass() + "changeStatus方法请求参数{}",param);
        if (StrUtil.isEmpty(param.getId())){
            log.error("ID数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        DriveSchoolEntity DriveSchoolEntity = new DriveSchoolEntity();
        DriveSchoolEntity.setId(param.getId());
        DriveSchoolEntity.setStatus(param.getStatus());
        //DriveSchoolEntity.setUpdateTime()
        Boolean result = driveSchoolService.updateById(DriveSchoolEntity);
        log.info(this.getClass() +"changeStatus方法请求对象参数{}，请求结果{}",DriveSchoolEntity,result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

}

