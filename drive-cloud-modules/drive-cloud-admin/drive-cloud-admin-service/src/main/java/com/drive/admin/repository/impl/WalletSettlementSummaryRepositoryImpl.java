package com.drive.admin.repository.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayFundTransToaccountTransferRequest;
import com.alipay.api.response.AlipayFundTransToaccountTransferResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.drive.admin.design.context.ContextState;
import com.drive.admin.enums.AliStatusEnum;
import com.drive.admin.enums.ExamEnrollEnum;
import com.drive.admin.enums.StatusEnum;
import com.drive.admin.enums.StudyEnrollEnum;
import com.drive.admin.pojo.entity.WalletSettlementSummaryEntity;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.drive.admin.service.*;
import com.drive.admin.strategy.StudyEnrollStrategy;
import com.drive.admin.strategy.context.SpringContextUtil;
import com.drive.admin.util.AdminCacheUtil;
import com.drive.common.core.base.BaseController;
import com.drive.admin.repository.WalletSettlementSummaryRepository;
import com.drive.admin.pojo.entity.*;
import com.drive.admin.pojo.vo.*;
import com.drive.admin.pojo.dto.*;
import com.drive.admin.service.mapstruct.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.hutool.core.util.StrUtil;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.SubResultCode;
import com.drive.common.core.constant.FlowTypeConstant;
import com.drive.common.core.exception.BizException;
import com.drive.common.core.utils.BeanConvertUtils;
import com.drive.common.core.utils.GsonUtil;
import com.drive.common.core.utils.IdWorker;
import com.drive.common.core.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import com.drive.common.core.biz.ResObject;
import org.springframework.beans.factory.annotation.Autowired;
import com.drive.common.data.utils.ExcelUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 *  钱包清算汇总服务类
 *
 * @author chentao
 */
@Slf4j
@Service
public class  WalletSettlementSummaryRepositoryImpl extends BaseController<WalletSettlementSummaryPageQueryParam, WalletSettlementSummaryEntity>  implements WalletSettlementSummaryRepository{

    //   服务
    @Autowired
    private WalletSettlementSummaryService walletSettlementSummaryService;
    //   DO-DTO转化
    @Autowired
    private WalletSettlementSummaryMapStruct walletSettlementSummaryMapStruct;

    @Autowired
    private WalletSettlementDetailService walletSettlementDetailService;

    @Autowired
    private StudentInfoService studentInfoService;

    @Autowired
    private CoachInfoService coachInfoService;


    @Autowired
    private PlatformWalletService platformWalletService;
    @Autowired
    private PlatformWalletDetailService platformWalletDetailService;

    // 支付宝
    @Value("${pay.ali.publicKey}")
    private String aliPublicKey;
    @Value("${pay.ali.privateKey}")
    private String aliPrivateKey;
    @Value("${pay.ali.appId}")
    private String aliAppId;
    // 阿里网关
    @Value("${pay.ali.gateway}")
    private String aliGateway;

    // 微信
    @Value("${pay.weChat.appId}")
    private String weChatAppId;
    @Value("${pay.weChat.tencentAppId}")

    private String weChatTencentAppId;
    @Value("${pay.weChat.noLimitMchId}")
    private String weChatNoLimitMchId;
    @Value("${pay.weChat.mchId}")
    private String weChatMchId;

    // 网关
    @Value("${pay.weChat.refuneUrl}")
    private String weChatRefuneUrl;
    @Value("${pay.weChat.signKey}")
    private String weChatSignKey;
    @Value("${pay.weChat.cert.path.prefix}")
    private String weChatPrefix;
    @Value("${pay.weChat.cert.path.suffix}")
    private String weChatSuffix;

    /*
     *
     *功能描述
     * @author chentao
     * @description  分页列表
     * @date 2020/2/12 17:09
     * @param  * @param WalletSettlementSummaryPageQueryParam
     * @return
     */
    @Override
    public ResObject pageList(WalletSettlementSummaryPageQueryParam param) {
        log.info(this.getClass() + "pageList-方法请求参数{}",param);
        Page<WalletSettlementSummaryEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        // 条件查询
        QueryWrapper queryWrapper = this.getQueryWrapper(walletSettlementSummaryMapStruct, param);

        //  模糊查询
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueNameSearch()),"name",param.getVagueNameSearch());
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueSubmitAccountSearch()),"submit_account",param.getVagueSubmitAccountSearch());
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueSubmitRealNameSearch()),"submit_real_name",param.getVagueSubmitRealNameSearch());
        //  开始时间 结束时间都有才进入
        if (StrUtil.isNotEmpty(param.getBeginTime()) && StrUtil.isNotEmpty(param.getEndTime())){
            queryWrapper.between(StrUtil.isNotEmpty(param.getBeginTime()),"create_time",param.getBeginTime(),param.getEndTime());
        }
        // 时间
        if (param.getExamineTimeArrSearch() != null && param.getExamineTimeArrSearch().length > 0){
            queryWrapper.between("examine_time",param.getExamineTimeArrSearch()[0],param.getExamineTimeArrSearch()[1]);
        }
        if (param.getSettleAccountsApplyTimeArrSearch() != null && param.getSettleAccountsApplyTimeArrSearch().length > 0){
            queryWrapper.between("settle_accounts_apply_time",param.getSettleAccountsApplyTimeArrSearch()[0],param.getSettleAccountsApplyTimeArrSearch()[1]);
        }
        if (param.getSettleAccountsCompleteTimeArrSearch() != null && param.getSettleAccountsCompleteTimeArrSearch().length > 0 ){
            queryWrapper.between("settle_accounts_complete_time",param.getSettleAccountsCompleteTimeArrSearch()[0],param.getSettleAccountsCompleteTimeArrSearch()[1]);
        }
        queryWrapper.orderByDesc("settle_accounts_apply_time");
        IPage<WalletSettlementSummaryEntity> pageList = walletSettlementSummaryService.page(page, queryWrapper);
        if (pageList.getRecords().isEmpty()){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }
        Page<WalletSettlementSummaryVo> walletSettlementSummaryVoPage = walletSettlementSummaryMapStruct.toVoList(pageList);
        walletSettlementSummaryVoPage.getRecords().stream().forEach((item) ->{
            // 学员 jiaol
            Optional.ofNullable(studentInfoService.getById(item.getUserId())).ifPresent(u ->{item.setUserName(u.getRealName());item.setStudentPhone(u.getPhone());});
            item.setCoachName(AdminCacheUtil.getCoachName(item.getUserId()));
            // 获取教练缓存
            CoachInfoVo coachInfoVo = AdminCacheUtil.getCoachInfo(item.getUserId());
            if (coachInfoVo != null){
                item.setCoachPhone(coachInfoVo.getPhone());
                item.setUserName(coachInfoVo.getRealName());
            }

        });
        log.info(this.getClass() + "pageList-方法请求结果{}",walletSettlementSummaryVoPage);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),walletSettlementSummaryVoPage);
    }

    /*
     *
     *功能描述
     * @author chentao
     * @description  查询列表
     * @date 2020/2/12 17:09
     * @param  * @param WalletSettlementSummaryPageQueryParam
     * @return
     */
    @Override
    public ResObject findList(WalletSettlementSummaryPageQueryParam param) {
        log.info(this.getClass() + "findList-方法请求参数{}",param);
        // 这里判断条件进行查询
        QueryWrapper queryWrapper= this.getQueryWrapper(walletSettlementSummaryMapStruct, param);
        // 通过名称查询
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueNameSearch()),"name",param.getVagueNameSearch());
        // 如 queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"phone",param.getPhone());
        List<WalletSettlementSummaryEntity> walletSettlementSummaryList = walletSettlementSummaryService.list(queryWrapper);
        if (walletSettlementSummaryList == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),walletSettlementSummaryList);
        }
        List<WalletSettlementSummaryVo> walletSettlementSummaryVoList = walletSettlementSummaryMapStruct.toVoList(walletSettlementSummaryList);
        log.info(this.getClass() + "findList-方法请求结果{}",walletSettlementSummaryVoList);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),walletSettlementSummaryVoList);
    }

    /**
    * 对象条件查询返回单条数据
    * @param param
    * @return
    */
    @Override
    public ResObject getInfo(WalletSettlementSummaryPageQueryParam param) {
        log.info(this.getClass() + "getInfo-方法请求参数{}",param);
        if (param == null){
            return R.failure("数据空");
        }
        // 这里判断条件进行查询
        QueryWrapper queryWrapper = this.getQueryWrapper(walletSettlementSummaryMapStruct, param);
        // 这里有且只返回一条数据
        queryWrapper.last("limit 1");
        WalletSettlementSummaryEntity walletSettlementSummary = walletSettlementSummaryService.getOne(queryWrapper);
        if (walletSettlementSummary == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),walletSettlementSummary);
        }
        WalletSettlementSummaryVo walletSettlementSummaryVo = BeanConvertUtils.copy(walletSettlementSummary, WalletSettlementSummaryVo.class);
        log.info(this.getClass() + "getInfo-方法请求结果{}",walletSettlementSummaryVo);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),walletSettlementSummaryVo);
    }

    /*
     *
     *功能描述
     * @author chentao
     * @description 通过ID获取  单条数据
     * @date 2020/2/12 17:09
     * @param  * @param WalletSettlementSummaryPageQueryParam
     * @return
     */
    @Override
    public ResObject getById(String id) {
        log.info(this.getClass() + "getById-方法请求参数{}",id);
        if (StrUtil.isEmpty(id)){
            return R.failure("数据空");
        }
        // 通过ID 获取 单条数据
        WalletSettlementSummaryEntity walletSettlementSummary = walletSettlementSummaryService.getById(id);
        if (walletSettlementSummary == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),walletSettlementSummary);
        }
        WalletSettlementSummaryVo walletSettlementSummaryVo = BeanConvertUtils.copy(walletSettlementSummary, WalletSettlementSummaryVo.class);
        log.info(this.getClass() + "getById-方法请求结果{}",walletSettlementSummaryVo);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),walletSettlementSummaryVo);
    }

    /*
     *
     *功能描述
     * @author chentao
     * @description 保存 数据
     * @date 2020/2/12 17:09
     * @param  * @param WalletSettlementSummaryPageQueryParam
     * @return
     */
    @Override
    public ResObject save(WalletSettlementSummaryInstallParam installParam) {
        log.info(this.getClass() + "save方法请求参数{}",installParam);
        if (installParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        WalletSettlementSummaryEntity walletSettlementSummary = BeanConvertUtils.copy(installParam, WalletSettlementSummaryEntity.class);
        Boolean result = walletSettlementSummaryService.save(walletSettlementSummary);
        log.info(this.getClass() + "save-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_INSTALL_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_INSTALL_FAILL.subCode(),SubResultCode.DATA_INSTALL_FAILL.subMsg());
    }

    /*
     *
     *功能描述
     * @author chentao
     * @description  修改 数据
     * @date 2020/2/12 17:09
     * @param  * @param WalletSettlementSummaryPageQueryParam
     * @return
     */
    @Override
    public ResObject update(WalletSettlementSummaryEditParam updateParam) {
        log.info(this.getClass() + "update方法请求参数{}",updateParam);
        if (updateParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        WalletSettlementSummaryEntity walletSettlementSummary = BeanConvertUtils.copy(updateParam, WalletSettlementSummaryEntity.class);
        Boolean result = walletSettlementSummaryService.updateById(walletSettlementSummary);
        log.info(this.getClass() + "update-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_UPDATE_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_UPDATE_FAILL.subCode(),SubResultCode.DATA_UPDATE_FAILL.subMsg());
    }

    /*
     *
     *功能描述
     * @author chentao
     * @description 数组删除 数据
     * @date 2020/2/12 17:09
     * @param  * @param WalletSettlementSummaryPageQueryParam
     * @return
     */
    @Override
    public ResObject deleteByIds(String[] ids) {
        if (ids.length <= 0){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        return R.toRes(walletSettlementSummaryService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * *通过id删除 信息
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
        Boolean result = walletSettlementSummaryService.removeById(id);
        log.info(this.getClass() + "deleteById-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_DELETE_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_DELETE_FAILL.subCode(),SubResultCode.DATA_DELETE_FAILL.subMsg());
    }

    /**
     * *导出 信息
     **/
    @Override
    public ResObject exportXls(WalletSettlementSummaryPageQueryParam param, HttpServletResponse response)throws IOException {
        log.info(this.getClass() + "exportXls方法请求参数{}",param);
        QueryWrapper queryWrapper = this.getQueryWrapper(walletSettlementSummaryMapStruct, param);
        List<WalletSettlementSummaryEntity> list = walletSettlementSummaryService.list(queryWrapper);
        List<WalletSettlementSummaryVo>walletSettlementSummaryList = walletSettlementSummaryMapStruct.toVoList(list);
        ExcelUtils.exportExcel(walletSettlementSummaryList, WalletSettlementSummaryVo.class, "", new ExportParams(), response);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.EXPORT_DATA_SUCCESS.subMsg());
    }

    /**
     * *修改状态
     **/
    @Override
    public ResObject changeStatus(WalletSettlementSummaryEditParam param) {
        log.info(this.getClass() + "changeStatus方法请求参数{}",param);
        if (StrUtil.isEmpty(param.getId())){
            log.error("ID数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        WalletSettlementSummaryEntity WalletSettlementSummaryEntity = new WalletSettlementSummaryEntity();
        WalletSettlementSummaryEntity.setId(param.getId());
        WalletSettlementSummaryEntity.setStatus(param.getStatus());
        //WalletSettlementSummaryEntity.setUpdateTime()
        Boolean result = walletSettlementSummaryService.updateById(WalletSettlementSummaryEntity);
        log.info(this.getClass() +"changeStatus方法请求对象参数{}，请求结果{}",WalletSettlementSummaryEntity,result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_STATUS_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_STATUS_FAILL.subCode(),SubResultCode.DATA_STATUS_FAILL.subMsg());
    }

    @Override
    public ResObject examine(String id) {
        log.info(this.getClass() + "examine-方法请求参数{}",id);
        if (StrUtil.isEmpty(id)){
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        // 查询数据
        WalletSettlementSummaryEntity walletSettlementSummaryDo =walletSettlementSummaryService.getById(id);
        Optional.ofNullable(walletSettlementSummaryDo).orElseThrow(()-> new BizException(200,"数据空",SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg()));
        // 只有待审的可以
        if (!(walletSettlementSummaryDo.getStatus().equals(StatusEnum.STAY_EXAMINE.getCode()))) {
            return R.failure(SubResultCode.DATA_STATUS_SUCCESS.subCode(),"只有状态为待审的可以进行该操作");
        }
        walletSettlementSummaryDo.setStatus(StatusEnum.LIQUIDATION_STATUS_WAIT.getCode());
        walletSettlementSummaryDo.setExamineTime(LocalDateTime.now());
        Boolean result = walletSettlementSummaryService.updateById(walletSettlementSummaryDo);
        if (!result){
            return R.failure(SubResultCode.DATA_UPDATE_FAILL.subCode(),SubResultCode.DATA_UPDATE_FAILL.subMsg());
        }
        return R.success("审核成功");
    }

    @Override
    public ResObject unusual(WalletSettlementSummaryEditParam walletSettlementSummaryEditParam) {
        log.info(this.getClass() + "examine-方法请求参数{}",walletSettlementSummaryEditParam);
        if (StrUtil.isEmpty(walletSettlementSummaryEditParam.getId())){
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        if(StrUtil.isEmpty(walletSettlementSummaryEditParam.getCause())){
            return  R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        // 查询数据
        WalletSettlementSummaryEntity walletSettlementSummaryDo =walletSettlementSummaryService.getById(walletSettlementSummaryEditParam.getId());
        Optional.ofNullable(walletSettlementSummaryDo).orElseThrow(()-> new BizException(200,"数据空",SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg()));
        //状态验证 0-待审核  1.待清算  2-清算成功  3.清算失败  4.非法操作
        //清算成功，清算失败，非法操作不可驳回
        if (walletSettlementSummaryDo.getStatus().equals(StatusEnum.LIQUIDATION_STATUS_SUCCESS.getCode())
            ||
            walletSettlementSummaryDo.getStatus().equals(StatusEnum.LIQUIDATION_STATUS_FAIL.getCode())
            ||
            walletSettlementSummaryDo.getStatus().equals(StatusEnum.LIQUIDATION_STATUS_ILLEGAL_OPERATION.getCode())
        ){
            return R.failure(SubResultCode.DATA_STATUS_FAILL.subCode(),"该数据不可以驳回");
        }
        // 设置状态
        //非法操作
        walletSettlementSummaryDo.setStatus(StatusEnum.LIQUIDATION_STATUS_ILLEGAL_OPERATION.getCode());
        // 异常原因
        walletSettlementSummaryDo.setRemarks(walletSettlementSummaryEditParam.getCause());
        // 更新清算数据
        Boolean result = walletSettlementSummaryService.updateById(walletSettlementSummaryDo);
        if (!result){
            throw new BizException(500,SubResultCode.DATA_UPDATE_FAILL.subCode(),SubResultCode.DATA_UPDATE_FAILL.subMsg());
        }
        //查询出钱包明细
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("settle_accounts_id",walletSettlementSummaryDo.getId());
        WalletSettlementDetailEntity walletSettlementDetailDo = walletSettlementDetailService.getOne(queryWrapper);
        Optional.ofNullable(walletSettlementDetailDo).orElseThrow(()-> new BizException(200,"数据空",SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg()));
        // 查询钱包
        QueryWrapper platformWalletQueryWrapper = new QueryWrapper();
        platformWalletQueryWrapper.eq("user_id",walletSettlementSummaryDo.getUserId());
        PlatformWalletEntity platformWalletDo = platformWalletService.getOne(platformWalletQueryWrapper);
        Optional.ofNullable(platformWalletDo).orElseThrow(()-> new BizException(200,"数据空",SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg()));

        // 新余额
        BigDecimal walletAmount = platformWalletDo.getWalletAmount().subtract(walletSettlementSummaryDo.getSettleAccountsSum());
        // 修改钱包明细状态
        // 设置钱包ID
        // 失败
        PlatformWalletDetailEntity walletDetail = new PlatformWalletDetailEntity();
        // 设置钱包ID
        walletDetail.setId(walletSettlementDetailDo.getWalletDetailId());
        // 失败
        walletDetail.setStatus(StatusEnum.DISABLE.getCode());
        // 余额加回来
        walletDetail.setBalance(walletAmount);
        // 修改钱包明细
        Boolean walletRes = platformWalletDetailService.updateById(walletDetail);
        if (!walletRes){
            throw new BizException(500,SubResultCode.DATA_UPDATE_FAILL.subCode(),SubResultCode.DATA_UPDATE_FAILL.subMsg());
        }
        // 重新赋值钱包余额
        platformWalletDo.setWalletAmount(walletAmount);
        // 修改钱包总表
        Boolean res= platformWalletService.updateById(platformWalletDo);
        if (!res){
            throw new BizException(500,SubResultCode.DATA_UPDATE_FAILL.subCode(),SubResultCode.DATA_UPDATE_FAILL.subMsg());
        }
        return R.success("驳回成功");
    }

    @Override
    public ResObject operationStatus(WalletSettlementSummaryEditParam walletSettlementSummaryEditParam) {
        log.info(this.getClass() + "operationStatus-方法请求参数{}",walletSettlementSummaryEditParam);
        String strategyValue = StatusEnum.getStrategyValueByCode(walletSettlementSummaryEditParam.getStatus());
        if (StrUtil.isEmpty(strategyValue)){
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL);
        }
        ContextState contextState = SpringContextUtil.getBean(strategyValue,ContextState.class);
        return contextState.switchStateStatus();
    }

    @Override
    public ResObject executeSingleSettlement(WalletSettlementSummaryEditParam walletSettlementSummaryEditParam) throws Exception {
        if (StrUtil.isEmpty(walletSettlementSummaryEditParam.getId())){
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        // 查询
        // 查询数据
        WalletSettlementSummaryEntity walletSettlementSummaryDo =walletSettlementSummaryService.getById(walletSettlementSummaryEditParam.getId());
        Optional.ofNullable(walletSettlementSummaryDo).orElseThrow(()-> new BizException(200,"数据空",SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg()));
        // 状态判断
        if (walletSettlementSummaryDo.getStatus().equals(StatusEnum.LIQUIDATION_STATUS_SUCCESS.getCode())){
            return R.failure(SubResultCode.STATUS_NO_OPERATION.subCode(),"不满足清算条件，请注意检查状态");
        }
        // 调用清算方法 //单笔清算方法
        return this.singleSettleAccounts(walletSettlementSummaryDo);
    }

    @Transactional
    ResObject singleSettleAccounts(WalletSettlementSummaryEntity walletSettlementSummaryDo) throws BizException, AlipayApiException {
        //商户转账唯一订单号
        String out_biz_no = StringUtils.generateSequenceNo();
        //待清算--直接使用新的“转账唯一订单号”
        if(walletSettlementSummaryDo.getStatus().equals(StatusEnum.LIQUIDATION_STATUS_FAIL.getCode())){
            out_biz_no = walletSettlementSummaryDo.getOutBizNo();
        }

        //2.修改清算总表中的“清算完成时间”，“清算状态”，记录转账唯一订单号
        walletSettlementSummaryDo.setOutBizNo(out_biz_no);

        //4.修改钱包明细表中的“提现明细的状态”
        QueryWrapper platformWalletQueryWrapper = new QueryWrapper();
        platformWalletQueryWrapper.eq("account_detail_id", walletSettlementSummaryDo.getId());
        PlatformWalletDetailEntity platformWalletDetailDo = platformWalletDetailService.getOne(platformWalletQueryWrapper);
        Optional.ofNullable(platformWalletDetailDo).orElseThrow(()-> new BizException(200,"数据空",SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg()));
        //5.根据不同的提现方式调用不同的转账接口
        ResObject resObject = new ResObject();
        // 支付宝
        if(walletSettlementSummaryDo.getSettleType().equals(StudyEnrollEnum.PAY_TYPE_ALI.getCode())){
            resObject = this.aliTransferAccounts(walletSettlementSummaryDo);
        }
        if(walletSettlementSummaryDo.getSettleType().equals(StudyEnrollEnum.PAY_TYPE_WECHAT.getCode())){
            resObject = this.wechatTransferAccounts(walletSettlementSummaryDo);
        }
        return resObject;
    }


    /**
     * 支付宝转账接口
     * @return  Map  code 返回 0000 表示转账成功 ;  code 返回 1111 转账失败，msg-为具体失败原因
     */
    @Transactional
    ResObject aliTransferAccounts(WalletSettlementSummaryEntity walletSettlementSummaryDo) throws BizException, AlipayApiException {

        //查询转账的支付宝账户
        QueryWrapper platformWalletQueryWrapper = new QueryWrapper();
        platformWalletQueryWrapper.eq("user_id", walletSettlementSummaryDo.getUserId());
        PlatformWalletEntity tPlatformWalletDo  = platformWalletService.getOne(platformWalletQueryWrapper);
        Optional.ofNullable(tPlatformWalletDo).orElseThrow(()-> new BizException(500,"数据空",SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg()));
        // 支付账户
        String submitAccount = tPlatformWalletDo.getAliAccount();
        // 支付宝账号真实名称
        String submitRealName = tPlatformWalletDo.getAliRealName();
        // 钱包里有支付账户 赋值操作
        if(StrUtil.isNotEmpty(walletSettlementSummaryDo.getSubmitAccount()) && StrUtil.isNotEmpty(walletSettlementSummaryDo.getSubmitRealName())){
            submitAccount = walletSettlementSummaryDo.getSubmitAccount();
            submitRealName =walletSettlementSummaryDo.getSubmitRealName();
        }


        java.security.Security.addProvider(
                new org.bouncycastle.jce.provider.BouncyCastleProvider()
        );
        // 支付宝对象
        AlipayClient alipayClient = new DefaultAlipayClient(aliGateway,aliAppId,aliPrivateKey,"json","UTF-8",aliPublicKey,"RSA2");
        // 退款对象
        AlipayFundTransToaccountTransferRequest request = new AlipayFundTransToaccountTransferRequest();
        request.setBizContent("{" +
                "\"out_biz_no\":\""+walletSettlementSummaryDo.getOutBizNo()+"\"," +   //商户转账唯一订单号
                "\"payee_type\":\"ALIPAY_LOGONID\"," +   //收款方账户类型  1、ALIPAY_USERID：支付宝账号对应的支付宝唯一用户号。以2088开头的16位纯数字组成。  2、ALIPAY_LOGONID：支付宝登录号，支持邮箱和手机号格式。
                "\"payee_account\":\""+submitAccount+"\"," +  //收款方账户  (与payee_type配合使用。付款方和收款方不能是同一个账户)
                "\"amount\":\""+String.valueOf(walletSettlementSummaryDo.getSettleAccountsSum())+"\"," + //转账金额
                "\"payer_show_name\":\""+"学车小王子"+"\"," +  //付款方姓名（最长支持100个英文/50个汉字）。显示在收款方的账单详情页。如果该字段不传，则默认显示付款方的支付宝认证姓名或单位名称。
                "\"payee_real_name\":\""+submitRealName+"\"," +   // 收款方真实姓名（最长支持100个英文/50个汉字）。  如果本参数不为空，则会校验该账户在支付宝登记的实名是否与收款方真实姓名一致。
                "\"remark\":\"学车小王子提现\"" + //备注
                " }");

        AlipayFundTransToaccountTransferResponse response = alipayClient.execute(request);
        log.info("支付接口结果{}",response);
        // 网关返回码
        String code = response.getCode();
        // 	网关返回码描述
        String msg = response.getMsg();
        // 业务返回码
        String subCode = response.getSubCode();
        // 业务返回码描述
        String subMsg = response.getSubMsg();
        // 成功执行的代码
        if (code.equals(AliStatusEnum.SUCCESS.getCode())){
            Map<String, String> resultMap= GsonUtil.toMap(response.getBody());
            String content =  resultMap.get("alipay_fund_trans_toaccount_transfer_response");
            String  sign = resultMap.get("sign");

            Map<String, String> orderMap=GsonUtil.toMap(content);
            boolean flag = AlipaySignature.rsaCheck(content, sign, aliPublicKey, "UTF-8", "RSA2");

            log.info("=======转账验签结果:" + flag);
            String payDate = orderMap.get("pay_date");
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime transferPayDate = LocalDateTime.parse(payDate,df);
            // 调用接口改数据
            TransferOperationDto transferOperationDto = new TransferOperationDto();
            transferOperationDto.setWalletSettlementSummaryId(walletSettlementSummaryDo.getId());
            transferOperationDto.setPayDate(transferPayDate);
            transferOperationDto.setSettleAccountsSum(walletSettlementSummaryDo.getSettleAccountsSum());
            transferOperationDto.setRemark(orderMap.get("msg"));
            transferOperationDto.setPlatformWalletEditParam(BeanConvertUtils.copy(tPlatformWalletDo,PlatformWalletEditParam.class));
            // 验签，和 商户转账唯一订单号 是否相同
            if(response.isSuccess() && flag && walletSettlementSummaryDo.getOutBizNo().equals(orderMap.get("out_biz_no"))){
                log.info("=============支付宝转账调用成功==================");
                // 时间
                transferOperationDto.setTransferCode(StatusEnum.TRANSFER_SUCCESS.getCode());
                this.transferSuccess(transferOperationDto);
                return R.success("转账成功");

                //转账支付宝账号，或名称不存在
            }else if("PAYEE_NOT_EXIST".equals(orderMap.get("sub_code")) || "PAYEE_USER_INFO_ERROR".equals(orderMap.get("sub_code"))){
                transferOperationDto.setTransferCode(StatusEnum.ACCOUNT_ERROR.getCode());
                this.transferSuccess(transferOperationDto);
                return R.failure(SubResultCode.DATA_UPDATE_FAILL.subCode(),response.getSubMsg());
            }else{
                transferOperationDto.setTransferCode(StatusEnum.TRANSFER_ERROR.getCode());
                this.transferSuccess(transferOperationDto);
                return R.failure(SubResultCode.DATA_UPDATE_FAILL.subCode(),response.getSubMsg());
            }
        }
        return R.success(subCode,subMsg);
    }

    /**
     * 微信转账接口
     * @return  成功-false  失败-true
     */
    ResObject  wechatTransferAccounts(WalletSettlementSummaryEntity walletSettlementSummaryDo) throws BizException {
        log.info("微信不可转账");
        return R.success("微信不可转账");
    }

    /**
     * 转账成功操作逻辑
     * @return
     */
    @Transactional
    ResObject transferSuccess(TransferOperationDto transferOperationDto){
        WalletSettlementSummaryEntity walletSettlementSummaryDo = new WalletSettlementSummaryEntity();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("account_detail_id", transferOperationDto.getWalletSettlementSummaryId());
        PlatformWalletDetailEntity platformWalletDetailDo = platformWalletDetailService.getOne(queryWrapper);
        Optional.ofNullable(platformWalletDetailDo).orElseThrow(()-> new BizException(500,"数据空",SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg()));
        walletSettlementSummaryDo.setId(transferOperationDto.getWalletSettlementSummaryId());
        // 转账成功
        if (transferOperationDto.getTransferCode().equals(StatusEnum.TRANSFER_SUCCESS.getCode())){
            //清算完成时间
            walletSettlementSummaryDo.setSettleAccountsCompleteTime(transferOperationDto.getPayDate());
            //转账失败原因（支付吧，微信，银行转账失败原因）
            walletSettlementSummaryDo.setRemarks(transferOperationDto.getRemark());
            //清算成功
            walletSettlementSummaryDo.setStatus(StatusEnum.LIQUIDATION_STATUS_SUCCESS.getCode());
            Boolean walletSettlementSummaryRes = walletSettlementSummaryService.updateById(walletSettlementSummaryDo); //更新清算汇总表
            if (!walletSettlementSummaryRes){
                throw new BizException(500,SubResultCode.DATA_UPDATE_FAILL.subCode(),"操作失败");
            }

            platformWalletDetailDo.setStatus(StatusEnum.TRADE_TYPE_SUCCESS.getCode()); //交易状态（成功）
            Boolean platformWalletDetailRes = platformWalletDetailService.updateById(platformWalletDetailDo); //更新钱包明细表
            if (!platformWalletDetailRes){
                throw new BizException(500,SubResultCode.DATA_UPDATE_FAILL.subCode(),"操作失败");
            }
        }
        // 账号密码错误
        if (transferOperationDto.getTransferCode().equals(StatusEnum.ACCOUNT_ERROR.getCode())){
            //清算完成时间
            walletSettlementSummaryDo.setSettleAccountsCompleteTime(transferOperationDto.getPayDate());
            //转账失败原因（支付吧，微信，银行转账失败原因）
            walletSettlementSummaryDo.setRemarks(transferOperationDto.getRemark());
            //清算失败
            walletSettlementSummaryDo.setStatus(StatusEnum.LIQUIDATION_STATUS_FAIL.getCode());
            Boolean walletSettlementSummaryRes = walletSettlementSummaryService.updateById(walletSettlementSummaryDo); //更新清算汇总表
            if (!walletSettlementSummaryRes){
                throw new BizException(500,SubResultCode.DATA_UPDATE_FAILL.subCode(),"操作失败");
            }
            // 交易状态（失败）
            platformWalletDetailDo.setStatus(StatusEnum.TRADE_TYPE_FAIL.getCode());
            //更新钱包明细表
            Boolean platformWalletDetailRes = platformWalletDetailService.updateById(platformWalletDetailDo);
            if (!platformWalletDetailRes){
                throw new BizException(500,SubResultCode.DATA_UPDATE_FAILL.subCode(),"操作失败");
            }
            PlatformWalletEditParam  platformWalletDto = transferOperationDto.getPlatformWalletEditParam();
            // 数据ID
            platformWalletDto.setWalletAmount(platformWalletDto.getWalletAmount().subtract(transferOperationDto.getSettleAccountsSum()));
            Boolean platformWalletRes = platformWalletService.updateById(BeanConvertUtils.copy(platformWalletDto,PlatformWalletEntity.class));
            if (!platformWalletRes){
                throw new BizException(500,SubResultCode.DATA_UPDATE_FAILL.subCode(),"操作失败");
            }

            IdWorker idWorker = new IdWorker();
            PlatformWalletDetailEntity platformWalletDetail = new PlatformWalletDetailEntity();
            //用户id
            platformWalletDetail.setUserId(transferOperationDto.getUserId());
            //账务流水明细id
            platformWalletDetail.setAccountDetailId(transferOperationDto.getWalletSettlementSummaryId());
            //交易金额
            platformWalletDetail.setTradeAmount(transferOperationDto.getSettleAccountsSum());
            // 交易类型（收益）
            platformWalletDetail.setTradeType(StudyEnrollEnum.DRIVER_WALLET_INCOME.getCode());
            //提现
            platformWalletDetail.setWalletDetailName("提现账号或姓名错误,金额退还");
            // 成功
            platformWalletDetail.setStatus(StatusEnum.TRADE_TYPE_SUCCESS.getCode());
            // 余额
            platformWalletDetail.setBalance(platformWalletDto.getWalletAmount());
            // 提现支出
            platformWalletDetail.setTradeSubject(FlowTypeConstant.OTHER_INCOME.getCode());
            // 提现失败退还
            platformWalletDetail.setTradeSubjectItems(FlowTypeConstant.OTHER_INCOME.getItemsMap("CASH_OUT_FAIL_BACK"));
            platformWalletDetail.setDataMsValue(idWorker.nextId());
            Boolean saveRes = platformWalletDetailService.save(platformWalletDetail);
            if (!saveRes){
                throw new BizException(500,SubResultCode.DATA_UPDATE_FAILL.subCode(),"操作失败");
            }
        }
        // 错误
        if (transferOperationDto.getTransferCode().equals(StatusEnum.TRANSFER_ERROR.getCode())){
            //清算完成时间
            //清算失败
            walletSettlementSummaryDo.setStatus(StatusEnum.LIQUIDATION_STATUS_FAIL.getCode());
            //转账失败原因（支付吧，微信，银行转账失败原因）
            walletSettlementSummaryDo.setRemarks(transferOperationDto.getRemark());

            Boolean walletSettlementSummaryRes = walletSettlementSummaryService.updateById(walletSettlementSummaryDo);
            if (!walletSettlementSummaryRes){
                throw new BizException(500,SubResultCode.DATA_UPDATE_FAILL.subCode(),"操作失败");
            }
            //还原 钱包明细
            platformWalletDetailDo.setStatus(StatusEnum.TRADE_TYPE_HAVE_IN_HAND.getCode()); //交易状态（成功）
            Boolean platformWalletDetailRes = platformWalletDetailService.updateById(platformWalletDetailDo); //更新钱包明细表
            if (!platformWalletDetailRes){
                throw new BizException(500,SubResultCode.DATA_UPDATE_FAILL.subCode(),"操作失败");
            }
        }
        return  R.success("操作成功");
    }

}

