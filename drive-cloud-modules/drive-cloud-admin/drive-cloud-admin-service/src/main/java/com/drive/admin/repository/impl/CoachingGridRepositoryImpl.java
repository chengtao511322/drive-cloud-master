package com.drive.admin.repository.impl;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.admin.pojo.dto.CoachingGridEditParam;
import com.drive.admin.pojo.dto.CoachingGridInstallParam;
import com.drive.admin.pojo.dto.CoachingGridPageQueryParam;
import com.drive.admin.pojo.entity.CoachingGridEntity;
import com.drive.admin.pojo.vo.CoachingGridVo;
import com.drive.admin.repository.CoachingGridRepository;
import com.drive.admin.service.CoachingGridService;
import com.drive.admin.service.mapstruct.CoachingGridMapStruct;
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
 * 平台训练场地表 服务类
 *
 * @author xiaoguo
 */
@Slf4j
@Service
public class  CoachingGridRepositoryImpl extends BaseController<CoachingGridPageQueryParam, CoachingGridEntity>  implements CoachingGridRepository{

    //  平台训练场地表 服务
    @Autowired
    private CoachingGridService coachingGridService;
    //  平台训练场地表 DO-DTO转化
    @Autowired
    private CoachingGridMapStruct coachingGridMapStruct;

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 平台训练场地表 分页列表
     * @date 2020/2/12 17:09
     * @param  * @param CoachingGridPageQueryParam
     * @return
     */
    @Override
    public ResObject pageList(CoachingGridPageQueryParam param) {
        log.info(this.getClass() + "pageList-方法请求参数{}",param);
        Page<CoachingGridEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        // 条件查询
        QueryWrapper queryWrapper = this.getQueryWrapper(coachingGridMapStruct, param);

        //  模糊查询
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueNameSearch()),"name",param.getVagueNameSearch());
        queryWrapper.like(StrUtil.isNotEmpty(param.getVaguePhoneSearch()),"phone",param.getVaguePhoneSearch());
        //  开始时间 结束时间都有才进入
        if (StrUtil.isNotEmpty(param.getBeginTime()) && StrUtil.isNotEmpty(param.getEndTime())){
            queryWrapper.between(StrUtil.isNotEmpty(param.getBeginTime()),"create_time",param.getBeginTime(),param.getEndTime());
        }
        IPage<CoachingGridEntity> pageList = coachingGridService.page(page, queryWrapper);
        if (pageList.getRecords().size() <= 0){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }
        Page<CoachingGridVo> coachingGridVoPage = coachingGridMapStruct.toVoList(pageList);
        log.info(this.getClass() + "pageList-方法请求结果{}",coachingGridVoPage);
        return R.success(coachingGridVoPage);
    }

    @Override
    public ResObject findList(CoachingGridPageQueryParam param) {
        log.info(this.getClass() + "findList-方法请求参数{}",param);
        // 这里判断条件进行查询
        QueryWrapper queryWrapper= this.getQueryWrapper(coachingGridMapStruct, param);
        // 如 queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"phone",param.getPhone());
        List<CoachingGridEntity> coachingGridList = coachingGridService.list(queryWrapper);
        if (coachingGridList == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        List<CoachingGridVo> coachingGridVoList = coachingGridMapStruct.toVoList(coachingGridList);
        log.info(this.getClass() + "findList-方法请求结果{}",coachingGridVoList);
        return R.success(coachingGridVoList);
    }



    @Override
    public ResObject getInfo(CoachingGridPageQueryParam param) {
        return null;
    }

    /**
     * *通过ID获取平台训练场地表 列表
     **/
    @Override
    public ResObject getById(String id) {
        log.info(this.getClass() + "getInfo-方法请求参数{}",id);
        if (StrUtil.isEmpty(id)){
            return R.failure("数据空");
        }
        CoachingGridEntity coachingGrid = coachingGridService.getById(id);
        if (coachingGrid == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        CoachingGridVo coachingGridVo = BeanConvertUtils.copy(coachingGrid, CoachingGridVo.class);
        log.info(this.getClass() + "getInfo-方法请求结果{}",coachingGridVo);
        return R.success(coachingGridVo);
    }

    /**
     * *保存平台训练场地表 信息
     **/
    @Override
    public ResObject save(CoachingGridInstallParam installParam) {
        log.info(this.getClass() + "save方法请求参数{}",installParam);
        if (installParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        CoachingGridEntity coachingGrid = BeanConvertUtils.copy(installParam, CoachingGridEntity.class);
        Boolean result = coachingGridService.save(coachingGrid);
        log.info(this.getClass() + "save-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *修改平台训练场地表 信息
     **/
    @Override
    public ResObject update(CoachingGridEditParam updateParam) {
        log.info(this.getClass() + "update方法请求参数{}",updateParam);
        if (updateParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        CoachingGridEntity coachingGrid = BeanConvertUtils.copy(updateParam, CoachingGridEntity.class);
        Boolean result = coachingGridService.updateById(coachingGrid);
        log.info(this.getClass() + "update-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *删除平台训练场地表 信息
     **/
    @Override
    public ResObject deleteByIds(String[] ids) {
        if (ids.length <= 0){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        return R.toRes(coachingGridService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * *通过id删除平台训练场地表 信息
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
        Boolean result = coachingGridService.removeById(id);
        log.info(this.getClass() + "deleteById-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *导出平台训练场地表 信息
     **/
    @Override
    public ResObject exportXls(CoachingGridPageQueryParam param, HttpServletResponse response)throws IOException {
        log.info(this.getClass() + "exportXls方法请求参数{}",param);
        QueryWrapper queryWrapper = this.getQueryWrapper(coachingGridMapStruct, param);
        List<CoachingGridEntity> list = coachingGridService.list(queryWrapper);
        List<CoachingGridVo>coachingGridList = coachingGridMapStruct.toVoList(list);
        ExcelUtils.exportExcel(coachingGridList, CoachingGridVo.class, "", new ExportParams(), response);
        return R.success("导出成功");
    }

    /**
     * *修改状态
     **/
    @Override
    public ResObject changeStatus(CoachingGridEditParam param) {
        log.info(this.getClass() + "changeStatus方法请求参数{}",param);
        if (StrUtil.isEmpty(param.getId())){
            log.error("ID数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        CoachingGridEntity CoachingGridEntity = new CoachingGridEntity();
        CoachingGridEntity.setId(param.getId());
        CoachingGridEntity.setStatus(param.getStatus());
        //CoachingGridEntity.setUpdateTime()
        Boolean result = coachingGridService.updateById(CoachingGridEntity);
        log.info(this.getClass() +"changeStatus方法请求对象参数{}，请求结果{}",CoachingGridEntity,result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

}

