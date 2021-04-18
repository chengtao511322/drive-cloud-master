package com.drive.marketing.repository;

import com.drive.common.core.biz.ResObject;
import com.drive.common.core.exception.BizException;
import com.drive.marketing.pojo.dto.ChannelManagerActivityEditParam;
import com.drive.marketing.pojo.dto.ChannelManagerActivityPageQueryParam;
import com.drive.marketing.pojo.vo.ChannelManagerActivityVo;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * 渠道经理
 */
public interface ChannelManagerActivityRepository {

    /**
     * 渠道经理所有版型启用 停用
     * @param channelManagerActivityEditParam
     * @return
     */
    ResObject changeChannelManagerStatus(ChannelManagerActivityEditParam channelManagerActivityEditParam);

    /**
     * 推广商状态修改
     * @param channelManagerActivityEditParam
     * @return
     */
    ResObject changePromotionUserStatus(ChannelManagerActivityEditParam channelManagerActivityEditParam);

    /**
     * 查询渠道经理
     * @param param
     * @return
     */
    ResObject pageChannelManagerList(ChannelManagerActivityPageQueryParam param);

    /**
     * 条件查询数据
     * @param param
     * @return
     */
    ResObject findList(ChannelManagerActivityPageQueryParam param);

    /**
     * 推广商列表数据查询
     * @param param
     * @return
     */
    ResObject findPromotionPageList(ChannelManagerActivityPageQueryParam param);


    ResObject findPromotionPageListByManagerId(ChannelManagerActivityPageQueryParam param);

    /**
     * 获取渠道经理 和推广商信息
     * @param param
     * @return
     */
    ResObject findChannelManagerOrPromotionPageList(ChannelManagerActivityPageQueryParam param);

    /**
     * 查询推广商推广了多少用户
     * @param param
     * @return
     */
    ResObject findPromotionUserPageList(ChannelManagerActivityPageQueryParam param);

    /**
     * 查看渠道经理 推广的 推广商
     * @param param
     * @return
     */
    ResObject findChannelManagerPromotionUserPageList(ChannelManagerActivityPageQueryParam param);


    /**
     * 通过活动ID ，用户ID 查询可推广班型
     * @param param
     * @return
     */
    ResObject findActivityProjectPageList(ChannelManagerActivityPageQueryParam param);


    /**
     * 保存推广人员
     * @param channelManagerActivityEditParam
     * @return
     */

    ResObject addActivityPromotion(List<ChannelManagerActivityEditParam> channelManagerActivityEditParamList) throws BizException;


    /**
     *保存渠道经理
     * @param channelManagerActivityEditParam
     * @return
     */
    ResObject saveBatch(List<ChannelManagerActivityEditParam> channelManagerActivityEditParam);

    /**
     * 发布渠道经理
     * @param channelManagerActivityEditParam
     * @return
     */
    ResObject publishChannelManager(ChannelManagerActivityEditParam channelManagerActivityEditParam);

    /**
     * 数据同步
     * @param activityId
     * @return
     */
    ResObject synData(String activityId) throws ExecutionException, InterruptedException;

    /**
     * 导出
     * @param channelManagerActivityPageQueryParam
     * @return
     */
    List<ChannelManagerActivityVo> exportXls(ChannelManagerActivityPageQueryParam channelManagerActivityPageQueryParam) throws IOException;
}
