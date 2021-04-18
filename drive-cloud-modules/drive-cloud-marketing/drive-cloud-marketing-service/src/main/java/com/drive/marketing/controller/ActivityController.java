package com.drive.marketing.controller;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.drive.basics.feign.RemoteChannelAuthFeignService;
import com.drive.basics.pojo.dto.ChannelAuthEditParam;
import com.drive.common.core.base.BaseController;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.biz.SubResultCode;
import com.drive.common.core.enums.EventLogEnum;
import com.drive.common.data.utils.ExcelUtils;
import com.drive.common.log.annotation.EventLog;
import com.drive.marketing.pojo.dto.ActivityEditParam;
import com.drive.marketing.pojo.dto.ActivityInfoPageQueryParam;
import com.drive.marketing.pojo.dto.CouponGetPageQueryParam;
import com.drive.marketing.pojo.entity.ActivityInfoEntity;
import com.drive.marketing.pojo.entity.ChannelManagerActivityEntity;
import com.drive.marketing.pojo.vo.ActivityCouponRelationVo;
import com.drive.marketing.pojo.vo.ActivityInfoVo;
import com.drive.marketing.repository.ActivityInfoRepository;
import com.drive.marketing.repository.ActivityRepository;
import com.drive.marketing.service.ActivityCouponRelationService;
import com.drive.marketing.service.ActivityProjectSettingService;
import com.drive.marketing.service.ChannelManagerActivityService;
import com.drive.marketing.service.IActivityInfoService;
import com.drive.marketing.service.mapstruct.ActivityMapStruct;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.Lock;

@Api(tags = "活动管理")
@Slf4j
@RestController
@RequestMapping("/activity")
public class ActivityController extends BaseController<ActivityInfoPageQueryParam, ActivityInfoEntity> {

    @Autowired
    private IActivityInfoService activityInfoService;

    @Autowired
    private ActivityMapStruct activityMapStruct;

    @Autowired
    private ActivityCouponRelationService activityCouponRelationService;


    @Autowired
    private ActivityProjectSettingService activityProjectSettingService;

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private ActivityInfoRepository activityInfoRepository;

    @Autowired
    private ChannelManagerActivityService channelManagerActivityService;

    @Autowired
    private RemoteChannelAuthFeignService channelAuthFeignService;

    @Autowired
    private RedisLockRegistry redisLockRegistry;

    /**
     * 部门 分页列表
     */
    @ApiOperation("活动分页列表")
    @GetMapping(value = "/pageList")
    public ResObject pageList(@Valid ActivityInfoPageQueryParam param) {
        return activityInfoRepository.pageList(param);
    }

   /* @ApiOperation("活动分页列表")
    @GetMapping("/{account}")
    public ResObject transfer(@Valid String account) {
        return activityRepository.transfer(account);
    }*/


    @PostMapping("/reduceInventoryRollback")
    ResObject reduceInventoryRollback(){
        return activityInfoRepository.reduceInventoryRollback();
    }


    /**
     * 获取用户信息
     */
    @ApiOperation("获取用户信息")
    @ApiImplicitParam(name = "activityId", required = true, dataType = "String", paramType = "path")
    @GetMapping(value = {"/", "/{activityId}"})
    public ResObject getInfo(@PathVariable(value = "activityId", required = false) String activityId) {
        if (StrUtil.isEmpty(activityId)) {
            return R.failure(SubResultCode.PARAMISBLANK.subCode(), SubResultCode.PARAMISBLANK.subMsg());
        }
        ActivityInfoEntity activityInfoEntity = activityInfoService.getById(activityId);
        ActivityInfoVo activityInfoVo = activityMapStruct.toVo(activityInfoEntity);
        return R.success(activityInfoVo);
    }

    /**
     * 新增活动信息
     */
    @ApiOperation("新增活动信息")
    @ApiImplicitParam(name = "ActivityEditParam ", value = "新增活动信息", dataType = "ActivityEditParam")
    @EventLog(message = "新增活动信息", businessType = EventLogEnum.CREATE)
    @PostMapping
    public ResObject save(@Valid @RequestBody ActivityEditParam activityEditParam) {
        ActivityInfoEntity activityInfoEntity = activityMapStruct.toEntity(activityEditParam);
        activityInfoEntity.setCreateTime(LocalDateTime.now());
        return R.toRes(activityInfoService.save(activityInfoEntity));
    }

    /**
     * 修改活动信息
     */
    @ApiOperation("修改活动信息")
    @ApiImplicitParam(name = "ActivityEditParam ", value = "修改活动信息", dataType = "ActivityEditParam")
    @EventLog(message = "修改活动信息", businessType = EventLogEnum.UPDATE)
    @PutMapping
    public ResObject edit(@Valid @RequestBody ActivityEditParam activityEditParam) {
        ActivityInfoEntity activityInfoEntity = activityMapStruct.toEntity(activityEditParam);
        activityInfoEntity.setUpdateTime(LocalDateTime.now());
        return R.toRes(activityInfoService.updateById(activityInfoEntity));
    }

    /**
     * 删除用户信息
     */
    @ApiOperation("删除活动信息")
    @ApiImplicitParam(name = "activityId", required = true, dataType = "Long", paramType = "path")
    @EventLog(message = "删除活动信息", businessType = EventLogEnum.DELETE)
    @DeleteMapping("/{activityIds}")
    public ResObject delete(@PathVariable String[] activityds) {
        // 管理员admin账号不能删除
        List<String> activityIdList = Arrays.asList(activityds);
        return R.toRes(activityInfoService.removeByIds(activityIdList));
    }

    /**
     * 导出用户信息
     */
    @ApiOperation("导出用户信息")
    @SneakyThrows
    @EventLog(message = "导出活动信息", businessType = EventLogEnum.EXPORT)
    @PostMapping(value = "/exportXls")
    public void exportXls(ActivityInfoPageQueryParam param, HttpServletResponse response) {
        List<ActivityInfoEntity> list = activityInfoService.list(this.getQueryWrapper(activityMapStruct, param));
        List<ActivityInfoVo> activityInfoVoList = activityMapStruct.toVoList(list);
        ExcelUtils.exportExcel(activityInfoVoList, ActivityInfoVo.class, "活动信息", new ExportParams(), response);
    }


    @PostMapping("/publishActivity")
    @Transactional
    @ApiOperation(value = "发布活动", notes = "远程调用发布活动")
    ResObject publishActivity(@RequestBody ActivityEditParam activityEditParam) {
        log.info(this.getClass() + "------publishActivity方法请求参数{}", activityEditParam);
        if (StrUtil.isEmpty(activityEditParam.getZoneName())) {
            log.error("发布活动出错，参数空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(), SubResultCode.PARAMISBLANK.subMsg());
        }
        /*// 先保存优惠券
        // 转化DTO
        ActivityInfoEntity activityInfoEntity = activityMapStruct.toEntity(activityEditParam);
        // 修改时间
        activityInfoEntity.setCreateTime(LocalDateTime.now());
        activityInfoService.save(activityInfoEntity);
        //log.info(this.getClass() + "------publishCoupon方法保存优惠券请求结果{}",isResult);
        // 发行数量
        List<CouponEditParam> couponList = activityEditParam.getCouponList();
        List<ActivityCouponRelationEditParam> newCouponList = new ArrayList<ActivityCouponRelationEditParam>();
        if (couponList != null){
            couponList.stream().forEach((item) ->{
                ActivityCouponRelationEditParam activityCouponRelation = new ActivityCouponRelationEditParam();
                Integer couponNum = item.getCouponNum();
                if (couponNum >1 ){
                    // 循环劵
                    for (int i=0; i<couponNum;i++){
                        ActivityCouponRelationEditParam activityCouponRelationEntity = new ActivityCouponRelationEditParam();
                        // 活动ID
                        activityCouponRelationEntity.setActivityId(activityInfoEntity.getId());

                        activityCouponRelationEntity.setCouponId(item.getId());
                        newCouponList.add(activityCouponRelationEntity);
                    }
                }else{
                    // 活动ID
                    activityCouponRelation.setActivityId(activityInfoEntity.getId());
                    activityCouponRelation.setCouponId(item.getId());
                    newCouponList.add(activityCouponRelation);
                }

            });
        }
        List<ActivityCouponRelationEntity> activityCouponRelationEntities = BeanConvertUtils.copyList(newCouponList, ActivityCouponRelationEntity.class);
        Boolean batchResult = activityCouponRelationService.saveBatch(activityCouponRelationEntities);

        List<ActivityProjectSettingEditParam> activityProjectSettingList = activityEditParam.getActivityProjectSettingList();
        List<ActivityProjectSettingEntity> activityProjectSettings = new ArrayList<ActivityProjectSettingEntity>();
        if (activityProjectSettingList != null){
            activityProjectSettingList.stream().forEach((item) -> {
                ActivityProjectSettingEntity activityProjectSetting = new ActivityProjectSettingEntity();
                // 活动ID
                activityProjectSetting.setActivityId(activityInfoEntity.getId());
                // 总金额
                activityProjectSetting.setDeductAmount(item.getDeductAmount());
                // 推广商 获得金额
                activityProjectSetting.setPromotionAmount(item.getPromotionAmount());
                // 渠道经理 获得金
                activityProjectSetting.setChannelManagerAmount(item.getChannelManagerAmount());
                activityProjectSetting.setProjectName(item.getProjectName());
                activityProjectSetting.setProjectId(item.getId());
                activityProjectSetting.setTenantId(item.getTenantId());
                activityProjectSetting.setCreateTime(LocalDateTime.now());
                activityProjectSetting.setProjectAmount(item.getProjectAmount());
                activityProjectSettings.add(activityProjectSetting);
            });
        }
        Boolean activityProjectSettingResult = activityProjectSettingService.saveBatch(activityProjectSettings);
        return R.success(batchResult);*/
        return activityRepository.publishActivity(activityEditParam);
    }


    @PostMapping("/updateActivity")
    @ApiOperation(value = "修改活动", notes = "远程调用修改活动")
    @Transactional
    ResObject updateActivity(@RequestBody ActivityEditParam activityEditParam) {
        log.info(this.getClass() + "------updateActivity方法请求参数{}", activityEditParam);
        /*if (StrUtil.isEmpty(activityEditParam.getId())){
            log.error("发布活动出错，参数空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        // 先保存优惠券
        // 转化DTO
        ActivityInfoEntity activityInfoEntity = activityMapStruct.toEntity(activityEditParam);
        activityInfoEntity.setUpdateTime(LocalDateTime.now());
        activityInfoService.updateById(activityInfoEntity);
        //log.info(this.getClass() + "------publishCoupon方法保存优惠券请求结果{}",isResult);
        // 发行数量
        List<CouponEditParam> couponList = activityEditParam.getCouponList();
        List<ActivityCouponRelationEditParam> newCouponList = new ArrayList<ActivityCouponRelationEditParam>();
        couponList.stream().forEach((item) ->{
            ActivityCouponRelationEditParam activityCouponRelation = new ActivityCouponRelationEditParam();
            Integer couponNum = item.getCouponNum();
            if (couponNum >1 ){
                // 循环劵
                for (int i=0; i<couponNum;i++){
                    ActivityCouponRelationEditParam activityCouponRelationEntity = new ActivityCouponRelationEditParam();
                    // 活动ID
                    activityCouponRelationEntity.setActivityId(activityInfoEntity.getId());

                    activityCouponRelationEntity.setCouponId(item.getId());
                    newCouponList.add(activityCouponRelationEntity);
                }
            }else{
                // 活动ID
                activityCouponRelation.setActivityId(activityInfoEntity.getId());
                activityCouponRelation.setCouponId(item.getId());
                newCouponList.add(activityCouponRelation);
            }

        });
        List<ActivityCouponRelationEntity> activityCouponRelationEntities = BeanConvertUtils.copyList(newCouponList, ActivityCouponRelationEntity.class);
        QueryWrapper<ActivityCouponRelationEntity> queryWrapper = new QueryWrapper<ActivityCouponRelationEntity>();
        queryWrapper.eq("activity_id",activityEditParam.getId());
        //activityCouponRelationService.remove(queryWrapper);
        Boolean batchResult = activityCouponRelationService.saveBatch(activityCouponRelationEntities);



        List<ActivityProjectSettingEditParam> activityProjectSettingList = activityEditParam.getActivityProjectSettingList();
        List<ActivityProjectSettingEntity> activityProjectSettings = new ArrayList<ActivityProjectSettingEntity>();
        if (!activityProjectSettingList.isEmpty()){
            activityProjectSettingList.stream().forEach((item) -> {
                ActivityProjectSettingEntity activityProjectSetting = new ActivityProjectSettingEntity();
                // 活动ID
                activityProjectSetting.setActivityId(activityInfoEntity.getId());
                // 总金额
                activityProjectSetting.setDeductAmount(item.getDeductAmount());
                // 推广商 获得金额
                activityProjectSetting.setPromotionAmount(item.getPromotionAmount());
                // 渠道经理 获得金
                activityProjectSetting.setChannelManagerAmount(item.getChannelManagerAmount());
                activityProjectSetting.setProjectName(item.getProjectName());
                activityProjectSetting.setProjectId(item.getId());
                activityProjectSetting.setTenantId(item.getTenantId());
                activityProjectSetting.setCreateTime(LocalDateTime.now());
                activityProjectSetting.setProjectAmount(item.getProjectAmount());
                activityProjectSettings.add(activityProjectSetting);
            });
        }
        QueryWrapper<ActivityProjectSettingEntity> queryActivityProjectSettingWrapper = new QueryWrapper<ActivityProjectSettingEntity>();
        queryWrapper.eq("activity_id",activityEditParam.getId());
        //activityProjectSettingService.remove(queryActivityProjectSettingWrapper);
        Boolean activityProjectSettingResult = activityProjectSettingService.saveBatch(activityProjectSettings);
        return R.success(batchResult);*/
        return activityRepository.updateActivity(activityEditParam);
    }


    /**
     * 状态修改
     */
    @PreAuthorize("hasPermission('/user',  'system:user:edit')")
    @EventLog(message = "修改用户信息", businessType = EventLogEnum.UPDATE)
    @PostMapping("/changeStatus")
    public ResObject changeStatus(@RequestBody ActivityEditParam activityEditParam) {

        ActivityInfoEntity activityInfoEntity = new ActivityInfoEntity();
        activityInfoEntity.setStatus(activityEditParam.getStatus());
        activityInfoEntity.setId(activityEditParam.getId());
        return R.toRes(activityInfoService.updateById(activityInfoEntity));
    }


    /**
     * 通过活动ID获取活动关联的优惠券
     *
     * @return
     */
    @PostMapping("/getActivityCouponRelation/{activityId}")
    @ApiOperation(value = "通过ID获取查询单个信息", notes = "远程调用通过ID获取查询单个信息参数{id:数据ID}")
    ResObject getActivityCouponRelation(@PathVariable("activityId") String activityId) {
        log.info(this.getClass() + "------getActivityCouponRelation方法请求参数{}", activityId);
        if (StrUtil.isEmpty(activityId)) {
            return R.failure(SubResultCode.PARAMISBLANK.subCode(), SubResultCode.PARAMISBLANK.subMsg());
        }
        List<ActivityCouponRelationVo> activityCouponRelationList = activityInfoService.getActivityCouponRelation(activityId);
        if (activityCouponRelationList.isEmpty()) {
            log.error("数据空");
            return R.failure(SubResultCode.DATA_NULL.subCode(), SubResultCode.DATA_NULL.subMsg());
        }
        // do 转化
        return R.success(activityCouponRelationList);
    }

    ;

    @GetMapping("/getCouponByActivityId/{activityId}")
    @ApiOperation(value = "通过活动ID获取活动关联优惠券信息", notes = "远程调用通过活动ID获取活动关联优惠券信息参数{activityId:活动ID}")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "activityId", value = "key", required = true, dataType = "String"),
    })
    ResObject getCouponByActivityId(@PathVariable("activityId") String activityId) {
        return activityInfoRepository.getCouponByActivityId(activityId);
    }

    ;

    @PostMapping("/findCouponPageListByActivityId")
    @ApiOperation(value = "通过活动ID分页获取活动关联优惠券信息", notes = "远程调用通过活动ID分页获取活动关联优惠券信息；参数{activityId:活动ID;pageNum}")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "activityId", value = "key", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "status", value = "key", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "pageNum", value = "0", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "pageSize", value = "10", required = true, dataType = "String"),
    })
    ResObject findCouponPageListByActivityId(@RequestBody CouponGetPageQueryParam couponGetPageQueryParam) {
        return activityInfoRepository.findCouponPageListByActivityId(couponGetPageQueryParam);
    }

    ;

    @PostMapping("/synData")
    @ApiOperation(value = "数据同步测试", notes = "数据同步测试")
    ResObject synData(@RequestBody ActivityEditParam activityEditParam) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("activity_id", activityEditParam.getId());
        queryWrapper.groupBy("user_id");
        List<ChannelManagerActivityEntity> channelManagerActivityList = channelManagerActivityService.list(queryWrapper);

        if (channelManagerActivityList.size() > 0) {
            channelManagerActivityList.stream().forEach((item) -> {
                String lockKey = "activity:" + item.getUserId();

                Lock lock = redisLockRegistry.obtain(lockKey);
                try {
                    lock.lock();
                    ChannelAuthEditParam channelAuthEditParam = new ChannelAuthEditParam();
                    // 运营商
                    channelAuthEditParam.setTenantId(activityEditParam.getTenantId());
                    channelAuthEditParam.setUserId(item.getUserId());
                    channelAuthEditParam.setChannelId(activityEditParam.getChannelId());
                    channelAuthEditParam.setCreateUser("系统数据同步");
                    channelAuthFeignService.updateChannelAuth(channelAuthEditParam);
                    //todo

                } finally {
                    lock.unlock();
                }
            });

        }
    return R.success();
    }

}
