package com.drive.admin.repository.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.admin.pojo.dto.CoachGiveAreaEditParam;
import com.drive.admin.pojo.dto.CoachInfoDataEditParam;
import com.drive.admin.pojo.dto.CoachInfoEditParam;
import com.drive.admin.pojo.dto.CoachInfoPageQueryParam;
import com.drive.admin.pojo.entity.CoachGiveAreaEntity;
import com.drive.admin.pojo.entity.CoachInfoEntity;
import com.drive.admin.pojo.vo.CoachInfoVo;
import com.drive.admin.repository.CoachInfoRepository;
import com.drive.admin.service.AreaService;
import com.drive.admin.service.CoachGiveAreaService;
import com.drive.admin.service.CoachInfoService;
import com.drive.admin.service.DriveSchoolService;
import com.drive.admin.service.mapstruct.CoachInfoMapStruct;
import com.drive.common.core.base.BaseController;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.biz.SubResultCode;
import com.drive.common.core.utils.BeanConvertUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;


/**
 *
 * 教练信息表 服务类
 *
 * @author xiaoguo
 */
@Slf4j
@Service
public class  CoachInfoRepositoryImpl extends BaseController<CoachInfoPageQueryParam, CoachInfoEntity>  implements CoachInfoRepository{

    @Autowired
    private CoachInfoService coachInfoService;
    @Autowired
    private CoachInfoMapStruct coachInfoMapStruct;

    @Autowired
    private AreaService areaService;

    @Autowired
    private DriveSchoolService driveSchoolService;

    @Autowired
    private CoachGiveAreaService coachGiveAreaService;

    /**
    * *教练信息表 分页列表
    **/
    @Override
    public ResObject pageList(CoachInfoPageQueryParam param) {
        Page<CoachInfoEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        // 查询条件
        QueryWrapper queryWrapper = this.getQueryWrapper(coachInfoMapStruct, param);
        // 手机号模糊查询
        queryWrapper.like(StrUtil.isNotEmpty(param.getVaguePhoneSearch()),"phone",param.getVaguePhoneSearch());
        // 真实姓名模糊查询
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueRealNameSearch()),"real_name",param.getVagueRealNameSearch());
        // 推荐时间时间
        queryWrapper.apply(StrUtil.isNotBlank(param.getRecommendDateSearch()),
                "date_format (recommend_date,'%Y-%m-%d') = date_format('" + param.getRecommendDateSearch() + "','%Y-%m-%d')");
        // 意向报名时间
        //  开始时间 结束时间都有才进入
        if (StrUtil.isNotEmpty(param.getBeginTime()) && StrUtil.isNotEmpty(param.getEndTime())){
            queryWrapper.between(StrUtil.isNotEmpty(param.getBeginTime()),"create_time",param.getBeginTime(),param.getEndTime());
        }
        IPage<CoachInfoEntity> pageList = coachInfoService.page(page, queryWrapper);
        // CollectionsUtil.isNotEmpty(list)&& list.size()>1
        if(pageList.getRecords().size() <= 0){
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }
        Page<CoachInfoVo> coachInfoVoPage = coachInfoMapStruct.toVoList(pageList);
        return R.success(coachInfoVoPage);
    }

    @Override
    public ResObject findList(CoachInfoPageQueryParam param) {
        return null;
    }




    /**
     * 通过ID获取教练信息表 列表
     **/
    @Override
    public ResObject getById(String id) {
        if (StrUtil.isEmpty(id)){
            return R.failure("数据空");
        }
        CoachInfoEntity coachInfo = coachInfoService.getById(id);
        CoachInfoVo coachInfoVo = BeanConvertUtils.copy(coachInfo, CoachInfoVo.class);
        if (coachInfoVo ==null){
            log.error("活动数据对象空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        if (StrUtil.isNotEmpty(coachInfoVo.getCarSchoolId()))coachInfoVo.setCarSchoolName(driveSchoolService.getById(coachInfoVo.getCarSchoolId()).getSchoolName());
        return R.success(coachInfoVo);
    }

    @Override
    public ResObject getInfo(CoachInfoPageQueryParam param) {
        if (param == null){
            return R.failure("数据空");
        }
        QueryWrapper queryWrapper = this.getQueryWrapper(coachInfoMapStruct, param);
        CoachInfoEntity coachInfo = coachInfoService.getOne(queryWrapper);
        if (coachInfo ==null){
            log.error("活动数据对象空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),coachInfo);
        }
        CoachInfoVo coachInfoVo = BeanConvertUtils.copy(coachInfo, CoachInfoVo.class);
        return R.success(coachInfoVo);
    }

    /**
     * *保存教练信息表 信息
     **/
    @Override
    public ResObject save(CoachInfoEditParam installParam) {
        log.info(this.getClass() + "save方法请求参数{}",installParam);
        if (installParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        CoachInfoEntity coachInfo = BeanConvertUtils.copy(installParam, CoachInfoEntity.class);
        Boolean result = coachInfoService.save(coachInfo);
        log.info(this.getClass() + "save-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *修改教练信息表 信息
     **/
    @Override
    public ResObject update(CoachInfoEditParam updateParam) {
        log.info(this.getClass() + "update方法请求参数{}",updateParam);
        if (updateParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        CoachInfoEntity coachInfo = BeanConvertUtils.copy(updateParam, CoachInfoEntity.class);
        Boolean result = coachInfoService.updateById(coachInfo);
        log.info(this.getClass() + "update-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *删除教练信息表 信息
     **/
    @Override
    public ResObject deleteByIds(String[] ids) {
        return R.toRes(coachInfoService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * *通过id删除教练信息表 信息
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
        Boolean result = coachInfoService.removeById(id);
        log.info(this.getClass() + "deleteById-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *导出教练信息表 信息
     **/
    @Override
    public ResObject exportXls(CoachInfoPageQueryParam param, HttpServletResponse response) {
        return null;
    }

    /**
     * *修改状态
     **/
    @Override
    public ResObject changeStatus(CoachInfoEditParam param) {
        return null;
    }

    @Override
    @Transactional
    public ResObject updateCoachInfo(CoachInfoDataEditParam updateParam) {
        log.info(this.getClass() + "方法请求参数{}",updateParam);
        // 先修改教练信息
        if (updateParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        CoachInfoEntity coachInfo = BeanConvertUtils.copy(updateParam, CoachInfoEntity.class);
        Boolean result = coachInfoService.saveOrUpdate(coachInfo);
        log.info(this.getClass() + "update-方法请求结果{}",result);
        // 判断结果
        if (!result){
            return R.failure(SubResultCode.DATA_INSTALL_FAILL.subCode(),SubResultCode.DATA_INSTALL_FAILL.subMsg());
        }
        // 变量接受城市数据
        List<CoachGiveAreaEditParam> teachAreaList = updateParam.getTeachArea();
        if (teachAreaList.size() > 0){
            // 循环处理数据
            teachAreaList.stream().forEach((item) ->{
                // 设置教练ID
                item.setCoachId(updateParam.getId());
            });
            // 保存数据
            Boolean coachGiveAreaResult = coachGiveAreaService.saveOrUpdateBatch(BeanConvertUtils.copyList(teachAreaList, CoachGiveAreaEntity.class));
            // 判断结果
            if (!coachGiveAreaResult){
                return R.failure(SubResultCode.DATA_INSTALL_FAILL.subCode(),SubResultCode.DATA_INSTALL_FAILL.subMsg());
            }
        }
        return R.success();
    }
}

