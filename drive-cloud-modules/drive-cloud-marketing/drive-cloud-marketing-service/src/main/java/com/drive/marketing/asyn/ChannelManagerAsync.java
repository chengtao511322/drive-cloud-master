package com.drive.marketing.asyn;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.drive.admin.api.RemoteRecommendUserFeignService;
import com.drive.admin.pojo.vo.RecommendUserVo;
import com.drive.basics.feign.RemoteChannelAuthFeignService;
import com.drive.basics.pojo.dto.ChannelAuthEditParam;
import com.drive.common.core.biz.ResCodeEnum;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.biz.SubResultCode;
import com.drive.common.core.exception.BizException;
import com.drive.marketing.pojo.entity.ChannelManagerActivityEntity;
import com.drive.marketing.service.ChannelManagerActivityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class ChannelManagerAsync {

    @Autowired
    private RemoteRecommendUserFeignService remoteRecommendUserFeignService;

    @Autowired
    private ChannelManagerActivityService channelManagerActivityService;

    @Autowired
    private RemoteChannelAuthFeignService channelAuthFeignService;


    //@Async
    @Transactional
    public void dataAsyncSuccess(List<ChannelManagerActivityEntity> channelManagerActivityList){

        // 循环处理
        channelManagerActivityList.stream().forEach((item) ->{

            // 查询推广商
            ResObject recommendUserRes =  remoteRecommendUserFeignService.getRecommendUserByChannelManagerId(item.getChannelManagerId());
            log.info("请求推广商数据{}",recommendUserRes);
            List<RecommendUserVo> recommendUserVoList = new ArrayList<>();
            // 请求到数据
            if (recommendUserRes.getCode().equals(ResCodeEnum.SUCCESS.getCode()) && recommendUserRes.getData() != null){
                recommendUserVoList = (List<RecommendUserVo>) recommendUserRes.getData();
            }

            // 数据空 不出来
            if (recommendUserVoList.size() <= 0){
                throw new BizException("数据同步失败，该活动下没有渠道经理");
            }
            List<ChannelManagerActivityEntity> channelManagerActivityEntityList = new ArrayList<>();
            recommendUserVoList.stream().forEach((recommendUser) ->{
                // 删除数据
                QueryWrapper<ChannelManagerActivityEntity> query = new QueryWrapper<ChannelManagerActivityEntity>();
                // 活动iD
                query.eq("activity_id",item.getActivityId());
                // 之前的版型ID
                //query.eq(StrUtil.isNotEmpty(item.getProjectId()),"project_id",item.getProjectId());
                // 渠道经理ID
                query.eq("channel_manager_id",item.getChannelManagerId());
                // 默认都是推广商
                query.eq("promotion_type",2);
                query.eq("promotion_user_id",recommendUser.getId());
                Boolean delRes = channelManagerActivityService.remove(query);

                ChannelManagerActivityEntity channelManagerActivityEntity = new ChannelManagerActivityEntity();
                // 渠道经理ID
                channelManagerActivityEntity.setChannelManagerId(recommendUser.getManagerId());
                // 用户ID
                channelManagerActivityEntity.setUserId(recommendUser.getStudentId());
                // 推广用户ID
                channelManagerActivityEntity.setPromotionUserId(recommendUser.getId());
                // 设置推广商
                channelManagerActivityEntity.setPromotionType(2);
                // 设置活动
                channelManagerActivityEntity.setActivityId(item.getActivityId());
                channelManagerActivityEntityList.add(channelManagerActivityEntity);

                // 添加权限
                // 第三版本
                ChannelAuthEditParam channelAuth = new ChannelAuthEditParam();
                // 菜单ID
                channelAuth.setTenantId(recommendUser.getOperatorId());
                // 用户渠道经理ID
                channelAuth.setUserId(item.getUserId());
                // 用户推广商ID
                channelAuth.setNewUserId(recommendUser.getStudentId());
                ResObject rest = channelAuthFeignService.copyChannelAuth(channelAuth);
                log.info(this.getClass() + "请求结果{}",rest);
                if(!(rest.getCode() .equals(ResCodeEnum.SUCCESS.getCode()))){
                    throw new BizException(500, SubResultCode.SYSTEM_FAILL.subCode(),SubResultCode.SYSTEM_FAILL.subMsg());
                }
            });
            Boolean managerRes = channelManagerActivityService.saveOrUpdateBatch(channelManagerActivityEntityList);
            log.info("结果{}",managerRes);
        });
    }
}
