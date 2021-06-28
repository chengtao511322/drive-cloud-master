package com.drive.marketing.repository.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.admin.api.RemoteStudentFeignService;
import com.drive.admin.pojo.vo.RecommendUserVo;
import com.drive.admin.pojo.vo.StudentInfoRpcVo;
import com.drive.admin.pojo.vo.StudentInfoVo;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResCodeEnum;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.biz.SubResultCode;
import com.drive.marketing.feign.RecommendUserFeign;
import com.drive.marketing.pojo.dto.CouponGetPageQueryParam;
import com.drive.marketing.pojo.entity.ActivityInfoEntity;
import com.drive.marketing.pojo.entity.CouponGetEntity;
import com.drive.marketing.pojo.vo.ActivityInfoVo;
import com.drive.marketing.pojo.vo.CouponGetVo;
import com.drive.marketing.repository.ActivityInfoRepository;
import com.drive.marketing.repository.CouponGetRepository;
import com.drive.marketing.repository.RecommendManagertRepository;
import com.drive.marketing.service.CouponGetService;
import com.drive.marketing.service.IActivityInfoService;
import com.drive.marketing.service.mapstruct.CouponGetMapStruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class CouponGetRepositoryImpl implements CouponGetRepository {

    @Autowired
    private CouponGetService couponGetService;

    @Autowired
    private CouponGetMapStruct couponGetMapStruct;

    @Autowired
    private RemoteStudentFeignService remoteStudentFeignService;

    @Autowired
    private IActivityInfoService activityInfoService;

    @Autowired
    private RecommendManagertRepository recommendManagertRepository;

    @Autowired
    private ActivityInfoRepository activityInfoRepository;

    @Autowired
    private RecommendUserFeign recommendUserFeign;


    @Override
    public ResObject pageList(CouponGetPageQueryParam param, QueryWrapper queryWrapper) {
        log.info(this.getClass() + "方法pageList请求参数{}" , param);
        Page<CouponGetEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        IPage<CouponGetEntity> pageList = couponGetService.page(page, queryWrapper);
        if (pageList.getRecords().size() <= 0){
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }
        Page<CouponGetVo> couponGetVoPage = couponGetMapStruct.toVoList(pageList);

        //获取用户ids
        String[] studentIds = couponGetVoPage.getRecords().stream().map(CouponGetVo::getUserId).toArray(String[]::new);
        Map<String, StudentInfoRpcVo> studentInfoVos = this.getBatchStudent(studentIds);
        log.info("学员数据{}",studentInfoVos);
        //获取活动ids
        String[] activityIds = couponGetVoPage.getRecords().stream().map(CouponGetVo::getSource).toArray(String[]::new);
        Map<String, ActivityInfoVo> activityInfoVos = this.getBatchActivityInfo(activityIds);
        log.info("活动数据",activityInfoVos);
        //获取推广商ids
        String[] promoteUserIds = couponGetVoPage.getRecords().stream().map(CouponGetVo::getPromoteUserId).toArray(String[]::new);
        Map<String, RecommendUserVo> recommendUserVos = recommendUserFeign.getBatchRecommendUserVo(promoteUserIds);

        couponGetVoPage.getRecords().stream().forEach((item) ->{

        });
        List<CouponGetVo> nweCouponGetVo = new ArrayList<>();
        couponGetVoPage.getRecords().stream().forEach((item) ->{
            // 用户
            if (studentInfoVos != null && studentInfoVos.get(item.getUserId()) != null)item.setUserName(studentInfoVos.get(item.getUserId()).getUsername());
            if (studentInfoVos != null && studentInfoVos.get(item.getUserId()) != null)item.setPhone(studentInfoVos.get(item.getUserId()).getPhone());
            if (activityInfoVos != null && activityInfoVos.get(item.getSource()) != null)item.setActivity(activityInfoVos.get(item.getSource()).getZoneName());
            if (recommendUserVos != null && recommendUserVos.get(item.getPromoteUserId()) != null)item.setPromoteUserName(recommendUserVos.get(item.getPromoteUserId()).getName());
            if (recommendUserVos != null && recommendUserVos.get(item.getPromoteUserId()) != null)item.setPromoteUserPhone(recommendUserVos.get(item.getPromoteUserId()).getPhone());
            nweCouponGetVo.add(item);
        });

        return R.success(couponGetVoPage);
    }

    /**
     * 异步查学员
     * @param ids
     * @return
     */
    @Async
    Map<String, StudentInfoRpcVo> getBatchStudent(String[] ids){
        Map<String, StudentInfoRpcVo> studentInfoVos = null;
        ResObject<Map<String, StudentInfoRpcVo>> resObject = remoteStudentFeignService.batchStudent(ids);

        log.info("请求学员接口{}",resObject);
        // 判断接口是否请求成功
        if (resObject.getCode().equals(ResCodeEnum.SUCCESS.getCode()) && resObject.getData() != null){
            studentInfoVos =  resObject.getData();
        }
        return studentInfoVos;
    }

    /**
     * 异步查活动信息
     */
    @Async
    Map<String, ActivityInfoVo> getBatchActivityInfo(String[] ids){
        Map<String, ActivityInfoVo> activityInfoVos = null;
        ResObject<Map<String, ActivityInfoVo>> resObject = activityInfoRepository.batchActivityInfo(ids);
        log.info("请求活动信息接口{}",resObject);
        // 判断接口是否请求成功
        if (resObject.getCode().equals(ResCodeEnum.SUCCESS.getCode()) && resObject.getData() != null){
            activityInfoVos =  resObject.getData();
        }
        return activityInfoVos;
    }

}
