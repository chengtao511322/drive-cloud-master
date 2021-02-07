package com.drive.admin.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.drive.admin.pojo.entity.CoachHourSettingDetailEntity;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.drive.common.core.base.BaseController;
import com.drive.admin.repository.CoachHourSettingDetailRepository;
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
import com.drive.admin.service.CoachHourSettingDetailService;
import com.drive.common.data.utils.ExcelUtils;
import java.util.List;
import java.util.Arrays;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

                                        
/**
 *
 * 运营商教练课时设置表 服务类
 *
 * @author xiaoguo
 */
@Slf4j
@Service
public class  CoachHourSettingDetailRepositoryImpl extends BaseController<CoachHourSettingDetailPageQueryParam, CoachHourSettingDetailEntity>  implements CoachHourSettingDetailRepository{

    //  运营商教练课时设置表 服务
    @Autowired
    private CoachHourSettingDetailService coachHourSettingDetailService;
    //  运营商教练课时设置表 DO-DTO转化
    @Autowired
    private CoachHourSettingDetailMapStruct coachHourSettingDetailMapStruct;

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 运营商教练课时设置表 分页列表
     * @date 2020/2/12 17:09
     * @param  * @param CoachHourSettingDetailPageQueryParam
     * @return
     */
    @Override
    public ResObject pageList(CoachHourSettingDetailPageQueryParam param) {
        log.info(this.getClass() + "pageList-方法请求参数{}",param);
        Page<CoachHourSettingDetailEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        // 条件查询
        QueryWrapper queryWrapper = this.getQueryWrapper(coachHourSettingDetailMapStruct, param);

        //  模糊查询
        //queryWrapper.like(StrUtil.isNotEmpty(param.getVagueNameSearch()),"name",param.getVagueNameSearch());
        //  开始时间 结束时间都有才进入
        if (StrUtil.isNotEmpty(param.getBeginTime()) && StrUtil.isNotEmpty(param.getEndTime())){
            queryWrapper.between(StrUtil.isNotEmpty(param.getBeginTime()),"create_time",param.getBeginTime(),param.getEndTime());
        }
        IPage<CoachHourSettingDetailEntity> pageList = coachHourSettingDetailService.page(page, queryWrapper);
        if (pageList.getRecords().size() <= 0){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }
        Page<CoachHourSettingDetailVo> coachHourSettingDetailVoPage = coachHourSettingDetailMapStruct.toVoList(pageList);
        log.info(this.getClass() + "pageList-方法请求结果{}",coachHourSettingDetailVoPage);
        return R.success(coachHourSettingDetailVoPage);
    }

    @Override
    public ResObject findList(CoachHourSettingDetailPageQueryParam param) {
        log.info(this.getClass() + "findList-方法请求参数{}",param);
        // 这里判断条件进行查询
        QueryWrapper queryWrapper= this.getQueryWrapper(coachHourSettingDetailMapStruct, param);
        // 如 queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"phone",param.getPhone());
        List<CoachHourSettingDetailEntity> coachHourSettingDetailList = coachHourSettingDetailService.list(queryWrapper);
        if (coachHourSettingDetailList == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),coachHourSettingDetailList);
        }
        List<CoachHourSettingDetailVo> coachHourSettingDetailVoList = coachHourSettingDetailMapStruct.toVoList(coachHourSettingDetailList);
        log.info(this.getClass() + "findList-方法请求结果{}",coachHourSettingDetailVoList);
        return R.success(coachHourSettingDetailVoList);
    }
    /**
    * 条件查询返回单条运营商教练课时设置表数据
    * @param param
    * @return
    */
    @Override
    public ResObject getInfo(CoachHourSettingDetailPageQueryParam param) {
        log.info(this.getClass() + "getInfo-方法请求参数{}",param);
        if (param == null){
            return R.failure("数据空");
        }
        // 这里判断条件进行查询
        QueryWrapper queryWrapper = this.getQueryWrapper(coachHourSettingDetailMapStruct, param);
        CoachHourSettingDetailEntity coachHourSettingDetail = coachHourSettingDetailService.getOne(queryWrapper);
        if (coachHourSettingDetail == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),coachHourSettingDetail);
        }
        CoachHourSettingDetailVo coachHourSettingDetailVo = BeanConvertUtils.copy(coachHourSettingDetail, CoachHourSettingDetailVo.class);
        log.info(this.getClass() + "getInfo-方法请求结果{}",coachHourSettingDetailVo);
        return R.success(coachHourSettingDetailVo);
    }

    /**
     * *通过ID获取运营商教练课时设置表 列表
     **/
    @Override
    public ResObject getById(String id) {
        log.info(this.getClass() + "getById-方法请求参数{}",id);
        if (StrUtil.isEmpty(id)){
            return R.failure("数据空");
        }
        CoachHourSettingDetailEntity coachHourSettingDetail = coachHourSettingDetailService.getById(id);
        if (coachHourSettingDetail == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),coachHourSettingDetail);
        }
        CoachHourSettingDetailVo coachHourSettingDetailVo = BeanConvertUtils.copy(coachHourSettingDetail, CoachHourSettingDetailVo.class);
        log.info(this.getClass() + "getById-方法请求结果{}",coachHourSettingDetailVo);
        return R.success(coachHourSettingDetailVo);
    }

    /**
     * *保存运营商教练课时设置表 信息
     **/
    @Override
    public ResObject save(CoachHourSettingDetailEditParam installParam) {
        log.info(this.getClass() + "save方法请求参数{}",installParam);
        if (installParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        CoachHourSettingDetailEntity coachHourSettingDetail = BeanConvertUtils.copy(installParam, CoachHourSettingDetailEntity.class);
        Boolean result = coachHourSettingDetailService.save(coachHourSettingDetail);
        log.info(this.getClass() + "save-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *修改运营商教练课时设置表 信息
     **/
    @Override
    public ResObject update(CoachHourSettingDetailEditParam updateParam) {
        log.info(this.getClass() + "update方法请求参数{}",updateParam);
        if (updateParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        CoachHourSettingDetailEntity coachHourSettingDetail = BeanConvertUtils.copy(updateParam, CoachHourSettingDetailEntity.class);
        Boolean result = coachHourSettingDetailService.updateById(coachHourSettingDetail);
        log.info(this.getClass() + "update-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *删除运营商教练课时设置表 信息
     **/
    @Override
    public ResObject deleteByIds(String[] ids) {
        if (ids.length <= 0){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        return R.toRes(coachHourSettingDetailService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * *通过id删除运营商教练课时设置表 信息
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
        Boolean result = coachHourSettingDetailService.removeById(id);
        log.info(this.getClass() + "deleteById-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *导出运营商教练课时设置表 信息
     **/
    @Override
    public ResObject exportXls(CoachHourSettingDetailPageQueryParam param, HttpServletResponse response)throws IOException {
        log.info(this.getClass() + "exportXls方法请求参数{}",param);
        QueryWrapper queryWrapper = this.getQueryWrapper(coachHourSettingDetailMapStruct, param);
        List<CoachHourSettingDetailEntity> list = coachHourSettingDetailService.list(queryWrapper);
        List<CoachHourSettingDetailVo>coachHourSettingDetailList = coachHourSettingDetailMapStruct.toVoList(list);
        ExcelUtils.exportExcel(coachHourSettingDetailList, CoachHourSettingDetailVo.class, "", new ExportParams(), response);
        return R.success("导出成功");
    }

    /**
     * *修改状态
     **/
    @Override
    public ResObject changeStatus(CoachHourSettingDetailEditParam param) {
        log.info(this.getClass() + "changeStatus方法请求参数{}",param);
        if (StrUtil.isEmpty(param.getId())){
            log.error("ID数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        CoachHourSettingDetailEntity coachHourSettingDetailEntity = new CoachHourSettingDetailEntity();
        coachHourSettingDetailEntity.setId(param.getId());
        //CoachHourSettingDetailEntity.setStatus(param.getStatus());
        //CoachHourSettingDetailEntity.setUpdateTime()
        Boolean result = coachHourSettingDetailService.updateById(coachHourSettingDetailEntity);
        log.info(this.getClass() +"changeStatus方法请求对象参数{}，请求结果{}",coachHourSettingDetailEntity,result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

}

