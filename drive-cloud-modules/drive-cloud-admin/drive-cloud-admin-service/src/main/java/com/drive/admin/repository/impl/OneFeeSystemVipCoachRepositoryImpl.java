package com.drive.admin.repository.impl;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.admin.pojo.dto.OneFeeSystemVipCoachEditParam;
import com.drive.admin.pojo.dto.OneFeeSystemVipCoachInstallParam;
import com.drive.admin.pojo.dto.OneFeeSystemVipCoachPageQueryParam;
import com.drive.admin.pojo.entity.CoachInfoEntity;
import com.drive.admin.pojo.entity.OneFeeSystemVipCoachEntity;
import com.drive.admin.pojo.entity.StudentOrderEntity;
import com.drive.admin.pojo.vo.OneFeeSystemVipCoachVo;
import com.drive.admin.repository.OneFeeSystemVipCoachRepository;
import com.drive.admin.service.CoachInfoService;
import com.drive.admin.service.OneFeeSystemVipCoachService;
import com.drive.admin.service.mapstruct.OneFeeSystemVipCoachMapStruct;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 *
 * 一费制vip教练 服务类
 *
 * @author xiaoguo
 */
@Slf4j
@Service
public class  OneFeeSystemVipCoachRepositoryImpl extends BaseController<OneFeeSystemVipCoachPageQueryParam, OneFeeSystemVipCoachEntity>  implements OneFeeSystemVipCoachRepository{

    //  一费制vip教练 服务
    @Autowired
    private OneFeeSystemVipCoachService oneFeeSystemVipCoachService;
    //  一费制vip教练 DO-DTO转化
    @Autowired
    private OneFeeSystemVipCoachMapStruct oneFeeSystemVipCoachMapStruct;

    // 教练信息
    @Autowired
    private CoachInfoService coachInfoService;

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 一费制vip教练 分页列表
     * @date 2020/2/12 17:09
     * @param  * @param OneFeeSystemVipCoachPageQueryParam
     * @return
     */
    @Override
    public ResObject pageList(OneFeeSystemVipCoachPageQueryParam param) {
        log.info(this.getClass() + "pageList-方法请求参数{}",param);
        Page<OneFeeSystemVipCoachEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        // 条件查询
        QueryWrapper queryWrapper = this.getQueryWrapper(oneFeeSystemVipCoachMapStruct, param);
        List<CoachInfoEntity> coachInfoList = new ArrayList<>();
        if (StrUtil.isNotEmpty(param.getVagueCoachName())){
            //  模糊查询
            QueryWrapper coachQueryWrapper = new QueryWrapper();
            coachQueryWrapper.like("real_name",param.getVagueCoachName());
            coachInfoList = coachInfoService.list(coachQueryWrapper);
        }

        //  开始时间 结束时间都有才进入
        if (StrUtil.isNotEmpty(param.getBeginTime()) && StrUtil.isNotEmpty(param.getEndTime())){
            queryWrapper.between(StrUtil.isNotEmpty(param.getBeginTime()),"create_time",param.getBeginTime(),param.getEndTime());
        }
        queryWrapper.in(coachInfoList.size() > 0,"coach_id",coachInfoList.stream().map(CoachInfoEntity::getId).collect(Collectors.toList()));
        IPage<OneFeeSystemVipCoachEntity> pageList = oneFeeSystemVipCoachService.page(page, queryWrapper);
        if (pageList.getRecords().size() <= 0){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }
        Page<OneFeeSystemVipCoachVo> oneFeeSystemVipCoachVoPage = oneFeeSystemVipCoachMapStruct.toVoList(pageList);
        // 获取教练信息
        oneFeeSystemVipCoachVoPage.getRecords().stream().forEach((item) ->{
            if (StrUtil.isNotEmpty(item.getCoachId())){
                CoachInfoEntity coachInfo = coachInfoService.getById(item.getCoachId());
                // 设置教练信息
                if (coachInfo != null)item.setCoachName(coachInfo.getRealName());item.setCoachPhone(coachInfo.getPhone());
            }
        });
        log.info(this.getClass() + "pageList-方法请求结果{}",oneFeeSystemVipCoachVoPage);
        return R.success(oneFeeSystemVipCoachVoPage);
    }

    @Override
    public ResObject findList(OneFeeSystemVipCoachPageQueryParam param) {
        log.info(this.getClass() + "findList-方法请求参数{}",param);
        // 这里判断条件进行查询
        QueryWrapper queryWrapper= this.getQueryWrapper(oneFeeSystemVipCoachMapStruct, param);
        // 如 queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"phone",param.getPhone());
        List<OneFeeSystemVipCoachEntity> oneFeeSystemVipCoachList = oneFeeSystemVipCoachService.list(queryWrapper);
        if (oneFeeSystemVipCoachList == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),oneFeeSystemVipCoachList);
        }
        List<OneFeeSystemVipCoachVo> oneFeeSystemVipCoachVoList = oneFeeSystemVipCoachMapStruct.toVoList(oneFeeSystemVipCoachList);
        log.info(this.getClass() + "findList-方法请求结果{}",oneFeeSystemVipCoachVoList);
        return R.success(oneFeeSystemVipCoachVoList);
    }

    /**
    * 条件查询返回单条一费制vip教练数据
    * @param param
    * @return
    */
    @Override
    public ResObject getInfo(OneFeeSystemVipCoachPageQueryParam param) {
        log.info(this.getClass() + "getInfo-方法请求参数{}",param);
        if (param == null){
            return R.failure("数据空");
        }
        // 这里判断条件进行查询
        QueryWrapper queryWrapper = this.getQueryWrapper(oneFeeSystemVipCoachMapStruct, param);
        OneFeeSystemVipCoachEntity oneFeeSystemVipCoach = oneFeeSystemVipCoachService.getOne(queryWrapper);
        if (oneFeeSystemVipCoach == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),oneFeeSystemVipCoach);
        }
        OneFeeSystemVipCoachVo oneFeeSystemVipCoachVo = BeanConvertUtils.copy(oneFeeSystemVipCoach, OneFeeSystemVipCoachVo.class);
        log.info(this.getClass() + "getInfo-方法请求结果{}",oneFeeSystemVipCoachVo);
        return R.success(oneFeeSystemVipCoachVo);
    }

    /**
     * *通过ID获取一费制vip教练 列表
     **/
    @Override
    public ResObject getById(String id) {
        log.info(this.getClass() + "getById-方法请求参数{}",id);
        if (StrUtil.isEmpty(id)){
            return R.failure("数据空");
        }
        OneFeeSystemVipCoachEntity oneFeeSystemVipCoach = oneFeeSystemVipCoachService.getById(id);
        if (oneFeeSystemVipCoach == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),oneFeeSystemVipCoach);
        }
        OneFeeSystemVipCoachVo oneFeeSystemVipCoachVo = BeanConvertUtils.copy(oneFeeSystemVipCoach, OneFeeSystemVipCoachVo.class);
        if (StrUtil.isNotEmpty(oneFeeSystemVipCoachVo.getCoachId())){
            CoachInfoEntity coachInfo = coachInfoService.getById(oneFeeSystemVipCoachVo.getCoachId());
            // 设置教练信息
            if (coachInfo != null)oneFeeSystemVipCoachVo.setCoachName(coachInfo.getRealName());oneFeeSystemVipCoachVo.setCoachPhone(coachInfo.getPhone());
        }
        log.info(this.getClass() + "getById-方法请求结果{}",oneFeeSystemVipCoachVo);
        return R.success(oneFeeSystemVipCoachVo);
    }

    /**
     * *保存一费制vip教练 信息
     **/
    @Override
    public ResObject save(OneFeeSystemVipCoachInstallParam installParam) {
        log.info(this.getClass() + "save方法请求参数{}",installParam);
        if (installParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        OneFeeSystemVipCoachEntity oneFeeSystemVipCoach = BeanConvertUtils.copy(installParam, OneFeeSystemVipCoachEntity.class);
        Boolean result = oneFeeSystemVipCoachService.save(oneFeeSystemVipCoach);
        log.info(this.getClass() + "save-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *修改一费制vip教练 信息
     **/
    @Override
    public ResObject update(OneFeeSystemVipCoachEditParam updateParam) {
        log.info(this.getClass() + "update方法请求参数{}",updateParam);
        if (updateParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        OneFeeSystemVipCoachEntity oneFeeSystemVipCoach = BeanConvertUtils.copy(updateParam, OneFeeSystemVipCoachEntity.class);
        Boolean result = oneFeeSystemVipCoachService.updateById(oneFeeSystemVipCoach);
        log.info(this.getClass() + "update-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *删除一费制vip教练 信息
     **/
    @Override
    public ResObject deleteByIds(String[] ids) {
        if (ids.length <= 0){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        return R.toRes(oneFeeSystemVipCoachService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * *通过id删除一费制vip教练 信息
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
        Boolean result = oneFeeSystemVipCoachService.removeById(id);
        log.info(this.getClass() + "deleteById-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *导出一费制vip教练 信息
     **/
    @Override
    public ResObject exportXls(OneFeeSystemVipCoachPageQueryParam param, HttpServletResponse response)throws IOException {
        log.info(this.getClass() + "exportXls方法请求参数{}",param);
        QueryWrapper queryWrapper = this.getQueryWrapper(oneFeeSystemVipCoachMapStruct, param);
        List<OneFeeSystemVipCoachEntity> list = oneFeeSystemVipCoachService.list(queryWrapper);
        List<OneFeeSystemVipCoachVo>oneFeeSystemVipCoachList = oneFeeSystemVipCoachMapStruct.toVoList(list);
        ExcelUtils.exportExcel(oneFeeSystemVipCoachList, OneFeeSystemVipCoachVo.class, "", new ExportParams(), response);
        return R.success("导出成功");
    }

    /**
     * *修改状态
     **/
    @Override
    public ResObject changeStatus(OneFeeSystemVipCoachEditParam param) {
        log.info(this.getClass() + "changeStatus方法请求参数{}",param);
        if (StrUtil.isEmpty(param.getId())){
            log.error("ID数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        OneFeeSystemVipCoachEntity OneFeeSystemVipCoachEntity = new OneFeeSystemVipCoachEntity();
        OneFeeSystemVipCoachEntity.setId(param.getId());
        //OneFeeSystemVipCoachEntity.setStatus(param.getStatus());
        //OneFeeSystemVipCoachEntity.setUpdateTime()
        Boolean result = oneFeeSystemVipCoachService.updateById(OneFeeSystemVipCoachEntity);
        log.info(this.getClass() +"changeStatus方法请求对象参数{}，请求结果{}",OneFeeSystemVipCoachEntity,result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

}

