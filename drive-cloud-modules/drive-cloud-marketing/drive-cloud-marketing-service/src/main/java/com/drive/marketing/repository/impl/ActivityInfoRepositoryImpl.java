package com.drive.marketing.repository.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.admin.api.RemoteRecommendUserFeignService;
import com.drive.admin.api.RemoteStudentFeignService;
import com.drive.admin.pojo.dto.RecommendUserPageQueryParam;
import com.drive.admin.pojo.vo.RecommendUserVo;
import com.drive.admin.pojo.vo.StudentInfoVo;
import com.drive.basics.feign.RemoteOperatorFeignService;
import com.drive.basics.pojo.vo.OperatorVo;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResCodeEnum;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.biz.SubResultCode;
import com.drive.common.core.enums.StatusEnum;
import com.drive.marketing.pojo.dto.ActivityCouponRelationEditParam;
import com.drive.marketing.pojo.dto.ActivityInfoPageQueryParam;
import com.drive.marketing.pojo.dto.CouponGetPageQueryParam;
import com.drive.marketing.pojo.entity.ActivityInfoEntity;
import com.drive.marketing.pojo.vo.ActivityCouponGetVo;
import com.drive.marketing.pojo.vo.ActivityCouponRelationVo;
import com.drive.marketing.pojo.vo.ActivityInfoVo;
import com.drive.marketing.repository.ActivityInfoRepository;
import com.drive.marketing.repository.RecommendManagertRepository;
import com.drive.marketing.service.CouponGetService;
import com.drive.marketing.service.IActivityInfoService;
import com.drive.marketing.service.mapstruct.ActivityMapStruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
@Slf4j
public class ActivityInfoRepositoryImpl implements ActivityInfoRepository {

    @Autowired
    private IActivityInfoService activityInfoService;

    @Autowired
    private ActivityMapStruct activityMapStruct;

    @Autowired
    @Lazy
    private RemoteStudentFeignService remoteStudentFeignService;

    @Autowired
    private CouponGetService couponGetService;

    @Autowired
    @Lazy
    private RemoteOperatorFeignService remoteOperatorFeignService;

    @Autowired
    @Lazy
    private RemoteRecommendUserFeignService remoteRecommendUserFeignService;

    @Autowired
    @Lazy
    private RecommendManagertRepository recommendManagertRepository;

    private Lock lock = new ReentrantLock(true);

    @Override
    public ResObject reduceInventoryRollback() {
        log.info(this.getClass() + "reduceInventoryRollback-方法请求参数{}");
        ActivityInfoEntity activityInfoEntity = new ActivityInfoEntity();
        activityInfoEntity.setId("1215185452315754496");
        activityInfoEntity.setDescription("测试回滚");
        activityInfoService.updateById(activityInfoEntity);
        //模拟异常
        int i = 1 / 0;
        return R.success();
    }

    @Override
    public ResObject getActivityCouponRelationByCondition(ActivityCouponRelationEditParam activityCouponRelationEditParam) {
        log.info(this.getClass() + "getActivityCouponRelationByCondition-方法请求参数{}",activityCouponRelationEditParam);
        if (StrUtil.isEmpty(activityCouponRelationEditParam.getActivityId())){
            log.error("活动数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        ActivityCouponRelationVo activityCouponRelationVo = activityInfoService.getActivityCouponRelationByCondition(activityCouponRelationEditParam);
        if (activityCouponRelationVo == null) {
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        return R.success(activityCouponRelationVo);
    }

    @Override
    public ResObject getCouponByActivityId(String activityId) {
        log.info(this.getClass() + "getCouponByActivityId-方法请求参数{}",activityId);
        if (StrUtil.isEmpty(activityId)){
            log.error("活动ID-数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }

        List<ActivityCouponRelationVo> activityCouponRelationVos = activityInfoService.getActivityCouponRelation(activityId);
        if (activityCouponRelationVos == null){
            log.error("查询数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        return R.success(activityCouponRelationVos);
    }

    @Override
    public ResObject findCouponPageListByActivityId(CouponGetPageQueryParam activityEditParam) {
        log.info(this.getClass() + "findCouponPageListByActivityId-方法请求参数{}",activityEditParam);
        if (StrUtil.isEmpty(activityEditParam.getActivityId())){
            log.error("活动ID-数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        // 查询条件建设
        QueryWrapper<CouponGetPageQueryParam> wrapper = new QueryWrapper<CouponGetPageQueryParam>();
        // 分页对象
        Page<ActivityCouponGetVo> page = new Page<ActivityCouponGetVo>(activityEditParam.getPageNum(), activityEditParam.getPageSize());
        // 删除状态
        wrapper.eq("t2.is_delete", StatusEnum.ENABLE);
        // 优惠券状态
        wrapper.eq("t2.status",1);
        // 活动ID 查询
        wrapper.eq("t1.source",activityEditParam.getActivityId());
        // 优惠券领取状态  1 未领取 2 已领取 3：已使用 4 已过
        wrapper.eq(StrUtil.isNotEmpty(activityEditParam.getStatus()),"t1.status",activityEditParam.getStatus());
        // 优惠券名称
        wrapper.like(StrUtil.isNotEmpty(activityEditParam.getCouponName()),"t2.name",activityEditParam.getCouponName());
        //用户手机号查询
        wrapper.like(StrUtil.isNotEmpty(activityEditParam.getUserName()),"t1.phone",activityEditParam.getPhone());
        //推广商手机号查询
        if(StrUtil.isNotEmpty(activityEditParam.getPromoteUserPhone())){
            ResObject<RecommendUserVo> res = remoteRecommendUserFeignService.getRecommendUserByPhone(activityEditParam.getPromoteUserPhone());
            if(res.getSubCode().equalsIgnoreCase("success") && res.getCode() == 200 &&res.getData() != null){
                RecommendUserVo rcVo = res.getData();
                wrapper.eq("promote_user_id",rcVo.getId());
            }else {
                //手机号未查到
                return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
            }
        }
        // 优惠券领取时间
        wrapper.between(StrUtil.isNotEmpty(activityEditParam.getBeginTime()),"get_time",activityEditParam.getBeginTime(),activityEditParam.getEndTime());
        wrapper.eq(StrUtil.isNotEmpty(activityEditParam.getTenantId()),"t1.tenant_id",activityEditParam.getTenantId());
        //wrapper.eq(StrUtil.isNotEmpty(activityEditParam.getPromoteUserId()),"promote_user_id",activityEditParam.getPromoteUserId());
        //优惠券使用时间范围
        wrapper.ge(StrUtil.isNotEmpty(activityEditParam.getUseBeginTime()),"t2.start_time",activityEditParam.getUseBeginTime());
        wrapper.le(StrUtil.isNotEmpty(activityEditParam.getUseEndTime()),"t2.end_time",activityEditParam.getUseEndTime());
        // 查询数据
        wrapper.orderByDesc("t1.create_time");
        IPage<ActivityCouponGetVo> pageList = couponGetService.findCouponPageListByActivityId(page, wrapper);
        if (pageList.getRecords().size() <= 0){
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        /*ResObject res = remoteRecommendUserFeignService.getByIdInfo("83eaf614fc8c4632b99d");
        log.info("请求学员接口{}",res);*/
        // 循环瞬间
        pageList.getRecords().forEach((item) ->{
            StudentInfoVo studentInfoVo = this.getStudent(item.getUserId());
            OperatorVo operatorVo = this.getOperatorVo(item.getTenantId());
            RecommendUserVo recommendUserVo = this.getRecommendUserVo(item.getPromoteUserId());
            log.info("学员数据{}",studentInfoVo);
            log.info("转化好的学员对象{}",studentInfoVo);
            if (studentInfoVo!= null && StrUtil.isNotEmpty(studentInfoVo.getUsername()))item.setUserName(studentInfoVo.getUsername());
            if (studentInfoVo!= null && StrUtil.isNotEmpty(studentInfoVo.getPhone()))item.setPhone(studentInfoVo.getPhone());
            if (operatorVo!= null && StrUtil.isNotEmpty(operatorVo.getName()))item.setTenantName(operatorVo.getName());
            if (recommendUserVo!= null )item.setPromoteUser(recommendUserVo);

        });
        return R.success(pageList);
    }

    @Override
    public ResObject pageList(ActivityInfoPageQueryParam param) {
        log.info(this.getClass() + "pageList-方法请求参数{}",param);
        QueryWrapper<ActivityInfoEntity> queryWrapper = new QueryWrapper<ActivityInfoEntity>();
        queryWrapper.like(StrUtil.isNotEmpty(param.getZoneName()),"zone_name",param.getZoneName());
        queryWrapper.eq(StrUtil.isNotEmpty(param.getTenantId()),"tenant_id",param.getTenantId());
        queryWrapper.eq(StrUtil.isNotEmpty(param.getStatus()),"status",param.getStatus());
        queryWrapper.eq(StrUtil.isNotEmpty(param.getType()),"type",param.getType());
        Page<ActivityInfoEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        queryWrapper.orderByDesc("create_time");
        IPage<ActivityInfoEntity> pageList = activityInfoService.page(page, queryWrapper);
        Page<ActivityInfoVo> deptVoPage = activityMapStruct.toVoList(pageList);
       /* deptVoPage.getRecords().stream().forEach((item) ->{
            OperatorVo operatorVo = null;
            // 查询运营商
            ResObject<OperatorVo> operatorVoResObject =remoteOperatorFeignService.get(item.getTenantId());
            log.info("请求运营商接口数据{}",operatorVoResObject);
            if (operatorVoResObject.getCode().equals(ResCodeEnum.SUCCESS.getCode()) && operatorVoResObject.getData() != null){
                operatorVo = operatorVoResObject.getData();
            }
            log.info("转化后的运营商数据{}",operatorVo);
            if (operatorVo!= null && StrUtil.isNotEmpty(operatorVo.getName()))item.setTenantName(operatorVo.getName());
        });*/
        log.info(this.getClass() + "方法请求结果{}",deptVoPage.getRecords());
        return R.success(deptVoPage);
    }

    /**
     * 异步查学员
     * @param id
     * @return
     */
    @Async
    StudentInfoVo getStudent(String id){
       StudentInfoVo studentInfoVo = null;
       ResObject resObject = remoteStudentFeignService.getByIdInfo(id);

       log.info("请求学员接口{}",resObject);
       // 判断接口是否请求成功
       if (resObject.getCode().equals(ResCodeEnum.SUCCESS.getCode()) && resObject.getData() != null){
           studentInfoVo = (StudentInfoVo) resObject.getData();
       }
       return studentInfoVo;

    }

    /**
     * 异步查运营商
     * @param id
     * @return
     */
    @Async
    OperatorVo getOperatorVo(String tenantId){
        OperatorVo operatorVo = null;
        // 查询运营商
        ResObject<OperatorVo> operatorVoResObject =remoteOperatorFeignService.get(tenantId);
        log.info("请求运营商接口数据{}",operatorVoResObject);
        if (operatorVoResObject.getCode().equals(ResCodeEnum.SUCCESS.getCode()) && operatorVoResObject.getData() != null){
            operatorVo = operatorVoResObject.getData();
        }
        log.info("转化后的运营商数据{}",operatorVo);
        return operatorVo;
    }

    /**
     * 查询推广商
     * @param promoteUserId
     * @return
     */
    @Async
    RecommendUserVo getRecommendUserVo(String promoteUserId){
        RecommendUserVo recommendUserVo = null;
        // 查询推广商
        ResObject<RecommendUserVo>  promoteUser = remoteRecommendUserFeignService.get(promoteUserId);
        if (promoteUser.getCode().equals(ResCodeEnum.SUCCESS.getCode()) && promoteUser.getData() != null){
            recommendUserVo = promoteUser.getData();
        }
        log.info("转化后的运营商数据{}",recommendUserVo);
        return recommendUserVo;
    }
}
