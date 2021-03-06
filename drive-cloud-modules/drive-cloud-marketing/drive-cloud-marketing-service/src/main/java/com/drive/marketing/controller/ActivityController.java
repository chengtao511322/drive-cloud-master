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

@Api(tags = "????????????")
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
     * ?????? ????????????
     */
    @ApiOperation("??????????????????")
    @PostMapping(value = "/pageList")
    public ResObject pageList(@Valid @RequestBody ActivityInfoPageQueryParam param) {
        return activityInfoRepository.pageList(param);
    }

   /* @ApiOperation("??????????????????")
    @GetMapping("/{account}")
    public ResObject transfer(@Valid String account) {
        return activityRepository.transfer(account);
    }*/


    @PostMapping("/reduceInventoryRollback")
    ResObject reduceInventoryRollback(){
        return activityInfoRepository.reduceInventoryRollback();
    }


    /**
     * ??????????????????
     */
    @ApiOperation("??????????????????")
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
     * ??????????????????
     */
    @ApiOperation("??????????????????")
    @ApiImplicitParam(name = "ActivityEditParam ", value = "??????????????????", dataType = "ActivityEditParam")
    @EventLog(message = "??????????????????", businessType = EventLogEnum.CREATE)
    @PostMapping
    public ResObject save(@Valid @RequestBody ActivityEditParam activityEditParam) {
        ActivityInfoEntity activityInfoEntity = activityMapStruct.toEntity(activityEditParam);
        activityInfoEntity.setCreateTime(LocalDateTime.now());
        return R.toRes(activityInfoService.save(activityInfoEntity));
    }

    /**
     * ??????????????????
     */
    @ApiOperation("??????????????????")
    @ApiImplicitParam(name = "ActivityEditParam ", value = "??????????????????", dataType = "ActivityEditParam")
    @EventLog(message = "??????????????????", businessType = EventLogEnum.UPDATE)
    @PutMapping
    public ResObject edit(@Valid @RequestBody ActivityEditParam activityEditParam) {
        ActivityInfoEntity activityInfoEntity = activityMapStruct.toEntity(activityEditParam);
        activityInfoEntity.setUpdateTime(LocalDateTime.now());
        return R.toRes(activityInfoService.updateById(activityInfoEntity));
    }

    /**
     * ??????????????????
     */
    @ApiOperation("??????????????????")
    @ApiImplicitParam(name = "activityId", required = true, dataType = "Long", paramType = "path")
    @EventLog(message = "??????????????????", businessType = EventLogEnum.DELETE)
    @DeleteMapping("/{activityIds}")
    public ResObject delete(@PathVariable String[] activityds) {
        // ?????????admin??????????????????
        List<String> activityIdList = Arrays.asList(activityds);
        return R.toRes(activityInfoService.removeByIds(activityIdList));
    }

    /**
     * ??????????????????
     */
    @ApiOperation("??????????????????")
    @SneakyThrows
    @EventLog(message = "??????????????????", businessType = EventLogEnum.EXPORT)
    @PostMapping(value = "/exportXls")
    public void exportXls(ActivityInfoPageQueryParam param, HttpServletResponse response) {
        List<ActivityInfoEntity> list = activityInfoService.list(this.getQueryWrapper(activityMapStruct, param));
        List<ActivityInfoVo> activityInfoVoList = activityMapStruct.toVoList(list);
        ExcelUtils.exportExcel(activityInfoVoList, ActivityInfoVo.class, "????????????", new ExportParams(), response);
    }


    @PostMapping("/publishActivity")
    @Transactional
    @ApiOperation(value = "????????????", notes = "????????????????????????")
    ResObject publishActivity(@RequestBody ActivityEditParam activityEditParam) {
        log.info(this.getClass() + "------publishActivity??????????????????{}", activityEditParam);
        if (StrUtil.isEmpty(activityEditParam.getZoneName())) {
            log.error("??????????????????????????????");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(), SubResultCode.PARAMISBLANK.subMsg());
        }
        /*// ??????????????????
        // ??????DTO
        ActivityInfoEntity activityInfoEntity = activityMapStruct.toEntity(activityEditParam);
        // ????????????
        activityInfoEntity.setCreateTime(LocalDateTime.now());
        activityInfoService.save(activityInfoEntity);
        //log.info(this.getClass() + "------publishCoupon?????????????????????????????????{}",isResult);
        // ????????????
        List<CouponEditParam> couponList = activityEditParam.getCouponList();
        List<ActivityCouponRelationEditParam> newCouponList = new ArrayList<ActivityCouponRelationEditParam>();
        if (couponList != null){
            couponList.stream().forEach((item) ->{
                ActivityCouponRelationEditParam activityCouponRelation = new ActivityCouponRelationEditParam();
                Integer couponNum = item.getCouponNum();
                if (couponNum >1 ){
                    // ?????????
                    for (int i=0; i<couponNum;i++){
                        ActivityCouponRelationEditParam activityCouponRelationEntity = new ActivityCouponRelationEditParam();
                        // ??????ID
                        activityCouponRelationEntity.setActivityId(activityInfoEntity.getId());

                        activityCouponRelationEntity.setCouponId(item.getId());
                        newCouponList.add(activityCouponRelationEntity);
                    }
                }else{
                    // ??????ID
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
                // ??????ID
                activityProjectSetting.setActivityId(activityInfoEntity.getId());
                // ?????????
                activityProjectSetting.setDeductAmount(item.getDeductAmount());
                // ????????? ????????????
                activityProjectSetting.setPromotionAmount(item.getPromotionAmount());
                // ???????????? ?????????
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
    @ApiOperation(value = "????????????", notes = "????????????????????????")
    @Transactional
    ResObject updateActivity(@RequestBody ActivityEditParam activityEditParam) {
        log.info(this.getClass() + "------updateActivity??????????????????{}", activityEditParam);
        /*if (StrUtil.isEmpty(activityEditParam.getId())){
            log.error("??????????????????????????????");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        // ??????????????????
        // ??????DTO
        ActivityInfoEntity activityInfoEntity = activityMapStruct.toEntity(activityEditParam);
        activityInfoEntity.setUpdateTime(LocalDateTime.now());
        activityInfoService.updateById(activityInfoEntity);
        //log.info(this.getClass() + "------publishCoupon?????????????????????????????????{}",isResult);
        // ????????????
        List<CouponEditParam> couponList = activityEditParam.getCouponList();
        List<ActivityCouponRelationEditParam> newCouponList = new ArrayList<ActivityCouponRelationEditParam>();
        couponList.stream().forEach((item) ->{
            ActivityCouponRelationEditParam activityCouponRelation = new ActivityCouponRelationEditParam();
            Integer couponNum = item.getCouponNum();
            if (couponNum >1 ){
                // ?????????
                for (int i=0; i<couponNum;i++){
                    ActivityCouponRelationEditParam activityCouponRelationEntity = new ActivityCouponRelationEditParam();
                    // ??????ID
                    activityCouponRelationEntity.setActivityId(activityInfoEntity.getId());

                    activityCouponRelationEntity.setCouponId(item.getId());
                    newCouponList.add(activityCouponRelationEntity);
                }
            }else{
                // ??????ID
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
                // ??????ID
                activityProjectSetting.setActivityId(activityInfoEntity.getId());
                // ?????????
                activityProjectSetting.setDeductAmount(item.getDeductAmount());
                // ????????? ????????????
                activityProjectSetting.setPromotionAmount(item.getPromotionAmount());
                // ???????????? ?????????
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
     * ????????????
     */
    @PreAuthorize("hasPermission('/user',  'system:user:edit')")
    @EventLog(message = "??????????????????", businessType = EventLogEnum.UPDATE)
    @PostMapping("/changeStatus")
    public ResObject changeStatus(@RequestBody ActivityEditParam activityEditParam) {

        ActivityInfoEntity activityInfoEntity = new ActivityInfoEntity();
        activityInfoEntity.setStatus(activityEditParam.getStatus());
        activityInfoEntity.setId(activityEditParam.getId());
        return R.toRes(activityInfoService.updateById(activityInfoEntity));
    }


    /**
     * ????????????ID??????????????????????????????
     *
     * @return
     */
    @PostMapping("/getActivityCouponRelation/{activityId}")
    @ApiOperation(value = "??????ID????????????????????????", notes = "??????????????????ID??????????????????????????????{id:??????ID}")
    ResObject getActivityCouponRelation(@PathVariable("activityId") String activityId) {
        log.info(this.getClass() + "------getActivityCouponRelation??????????????????{}", activityId);
        if (StrUtil.isEmpty(activityId)) {
            return R.failure(SubResultCode.PARAMISBLANK.subCode(), SubResultCode.PARAMISBLANK.subMsg());
        }
        List<ActivityCouponRelationVo> activityCouponRelationList = activityInfoService.getActivityCouponRelation(activityId);
        if (activityCouponRelationList.isEmpty()) {
            log.error("?????????");
            return R.failure(SubResultCode.DATA_NULL.subCode(), SubResultCode.DATA_NULL.subMsg());
        }
        // do ??????
        return R.success(activityCouponRelationList);
    }

    ;

    @GetMapping("/getCouponByActivityId/{activityId}")
    @ApiOperation(value = "????????????ID?????????????????????????????????", notes = "????????????????????????ID???????????????????????????????????????{activityId:??????ID}")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "activityId", value = "key", required = true, dataType = "String"),
    })
    ResObject getCouponByActivityId(@PathVariable("activityId") String activityId) {
        return activityInfoRepository.getCouponByActivityId(activityId);
    }

    ;

    @PostMapping("/findCouponPageListByActivityId")
    @ApiOperation(value = "????????????ID???????????????????????????????????????", notes = "????????????????????????ID????????????????????????????????????????????????{activityId:??????ID;pageNum}")
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
    @ApiOperation(value = "??????????????????", notes = "??????????????????")
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
                    // ?????????
                    channelAuthEditParam.setTenantId(activityEditParam.getTenantId());
                    channelAuthEditParam.setUserId(item.getUserId());
                    channelAuthEditParam.setChannelId(activityEditParam.getChannelId());
                    channelAuthEditParam.setCreateUser("??????????????????");
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
