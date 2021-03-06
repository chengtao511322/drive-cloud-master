package com.drive.admin.repository.impl;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.admin.pojo.dto.CoachGiveAreaEditParam;
import com.drive.admin.pojo.dto.CoachGiveAreaPageQueryParam;
import com.drive.admin.pojo.entity.CoachGiveAreaEntity;
import com.drive.admin.pojo.vo.CoachGiveAreaVo;
import com.drive.admin.repository.CoachGiveAreaRepository;
import com.drive.admin.service.AreaService;
import com.drive.admin.service.CoachGiveAreaService;
import com.drive.admin.service.mapstruct.CoachGiveAreaMapStruct;
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
 * 教练授课区域表 服务类
 *
 * @author xiaoguo
 */
@Slf4j
@Service
public class  CoachGiveAreaRepositoryImpl extends BaseController<CoachGiveAreaPageQueryParam, CoachGiveAreaEntity>  implements CoachGiveAreaRepository{

    //  教练授课区域表 服务
    @Autowired
    private CoachGiveAreaService coachGiveAreaService;
    //  教练授课区域表 DO-DTO转化
    @Autowired
    private CoachGiveAreaMapStruct coachGiveAreaMapStruct;

    @Autowired
    private AreaService areaService;

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 教练授课区域表 分页列表
     * @date 2020/2/12 17:09
     * @param  * @param CoachGiveAreaPageQueryParam
     * @return
     */
    @Override
    public ResObject pageList(CoachGiveAreaPageQueryParam param) {
        log.info(this.getClass() + "pageList-方法请求参数{}",param);
        Page<CoachGiveAreaEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        // 条件查询
        QueryWrapper queryWrapper = this.getQueryWrapper(coachGiveAreaMapStruct, param);

        //  模糊查询
        //queryWrapper.like(StrUtil.isNotEmpty(param.getVagueNameSearch()),"name",param.getVagueNameSearch());
        //  开始时间 结束时间都有才进入
        if (StrUtil.isNotEmpty(param.getBeginTime()) && StrUtil.isNotEmpty(param.getEndTime())){
            queryWrapper.between(StrUtil.isNotEmpty(param.getBeginTime()),"create_time",param.getBeginTime(),param.getEndTime());
        }
        IPage<CoachGiveAreaEntity> pageList = coachGiveAreaService.page(page, queryWrapper);
        if (pageList.getRecords().size() <= 0){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }
        Page<CoachGiveAreaVo> coachGiveAreaVoPage = coachGiveAreaMapStruct.toVoList(pageList);
        log.info(this.getClass() + "pageList-方法请求结果{}",coachGiveAreaVoPage);
        // 循环拿数据
        coachGiveAreaVoPage.getRecords().stream().forEach((item) ->{
            // 省市区
            if (StrUtil.isNotEmpty(item.getProvinceId()))item.setProvinceName(areaService.getByBaCode(item.getProvinceId()).getBaName());
            if (StrUtil.isNotEmpty(item.getCityId()))item.setCityName(areaService.getByBaCode(item.getCityId()).getBaName());
            if (StrUtil.isNotEmpty(item.getAreaId()))item.setAreaName(areaService.getByBaCode(item.getAreaId()).getBaName());
        });
        return R.success(coachGiveAreaVoPage);
    }

    @Override
    public ResObject findList(CoachGiveAreaPageQueryParam param) {
        log.info(this.getClass() + "findList-方法请求参数{}",param);
        // 这里判断条件进行查询
        QueryWrapper queryWrapper= this.getQueryWrapper(coachGiveAreaMapStruct, param);
        // 如 queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"phone",param.getPhone());
        List<CoachGiveAreaEntity> coachGiveAreaList = coachGiveAreaService.list(queryWrapper);
        if (coachGiveAreaList == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),coachGiveAreaList);
        }
        List<CoachGiveAreaVo> coachGiveAreaVoList = coachGiveAreaMapStruct.toVoList(coachGiveAreaList);
        log.info(this.getClass() + "findList-方法请求结果{}",coachGiveAreaVoList);
        return R.success(coachGiveAreaVoList);
    }

    /**
    * 条件查询返回单条教练授课区域表数据
    * @param param
    * @return
    */
    @Override
    public ResObject getInfo(CoachGiveAreaPageQueryParam param) {
        log.info(this.getClass() + "getInfo-方法请求参数{}",param);
        if (param == null){
            return R.failure("数据空");
        }
        // 这里判断条件进行查询
        QueryWrapper queryWrapper = this.getQueryWrapper(coachGiveAreaMapStruct, param);
        CoachGiveAreaEntity coachGiveArea = coachGiveAreaService.getOne(queryWrapper);
        if (coachGiveArea == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),coachGiveArea);
        }
        CoachGiveAreaVo coachGiveAreaVo = BeanConvertUtils.copy(coachGiveArea, CoachGiveAreaVo.class);
        log.info(this.getClass() + "getInfo-方法请求结果{}",coachGiveAreaVo);
        return R.success(coachGiveAreaVo);
    }

    /**
     * *通过ID获取教练授课区域表 列表
     **/
    @Override
    public ResObject getById(String id) {
        log.info(this.getClass() + "getById-方法请求参数{}",id);
        if (StrUtil.isEmpty(id)){
            return R.failure("数据空");
        }
        CoachGiveAreaEntity coachGiveArea = coachGiveAreaService.getById(id);
        if (coachGiveArea == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),coachGiveArea);
        }
        CoachGiveAreaVo coachGiveAreaVo = BeanConvertUtils.copy(coachGiveArea, CoachGiveAreaVo.class);
        log.info(this.getClass() + "getById-方法请求结果{}",coachGiveAreaVo);
        return R.success(coachGiveAreaVo);
    }

    /**
     * *保存教练授课区域表 信息
     **/
    @Override
    public ResObject save(CoachGiveAreaEditParam installParam) {
        log.info(this.getClass() + "save方法请求参数{}",installParam);
        if (installParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        CoachGiveAreaEntity coachGiveArea = BeanConvertUtils.copy(installParam, CoachGiveAreaEntity.class);
        Boolean result = coachGiveAreaService.save(coachGiveArea);
        log.info(this.getClass() + "save-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *修改教练授课区域表 信息
     **/
    @Override
    public ResObject update(CoachGiveAreaEditParam updateParam) {
        log.info(this.getClass() + "update方法请求参数{}",updateParam);
        if (updateParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        CoachGiveAreaEntity coachGiveArea = BeanConvertUtils.copy(updateParam, CoachGiveAreaEntity.class);
        Boolean result = coachGiveAreaService.updateById(coachGiveArea);
        log.info(this.getClass() + "update-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *删除教练授课区域表 信息
     **/
    @Override
    public ResObject deleteByIds(String[] ids) {
        if (ids.length <= 0){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        return R.toRes(coachGiveAreaService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * *通过id删除教练授课区域表 信息
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
        Boolean result = coachGiveAreaService.removeById(id);
        log.info(this.getClass() + "deleteById-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *导出教练授课区域表 信息
     **/
    @Override
    public ResObject exportXls(CoachGiveAreaPageQueryParam param, HttpServletResponse response)throws IOException {
        log.info(this.getClass() + "exportXls方法请求参数{}",param);
        QueryWrapper queryWrapper = this.getQueryWrapper(coachGiveAreaMapStruct, param);
        List<CoachGiveAreaEntity> list = coachGiveAreaService.list(queryWrapper);
        List<CoachGiveAreaVo>coachGiveAreaList = coachGiveAreaMapStruct.toVoList(list);
        ExcelUtils.exportExcel(coachGiveAreaList, CoachGiveAreaVo.class, "", new ExportParams(), response);
        return R.success("导出成功");
    }

    /**
     * *修改状态
     **/
    @Override
    public ResObject changeStatus(CoachGiveAreaEditParam param) {
        log.info(this.getClass() + "changeStatus方法请求参数{}",param);
        if (StrUtil.isEmpty(param.getId())){
            log.error("ID数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        CoachGiveAreaEntity CoachGiveAreaEntity = new CoachGiveAreaEntity();
        CoachGiveAreaEntity.setId(param.getId());
        //CoachGiveAreaEntity.setStatus(param.getStatus());
        //CoachGiveAreaEntity.setUpdateTime()
        Boolean result = coachGiveAreaService.updateById(CoachGiveAreaEntity);
        log.info(this.getClass() +"changeStatus方法请求对象参数{}，请求结果{}",CoachGiveAreaEntity,result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

}

