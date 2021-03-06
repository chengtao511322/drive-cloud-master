package com.drive.admin.repository.impl;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.admin.enums.PlatformWalletEnum;
import com.drive.admin.enums.StatusEnum;
import com.drive.admin.enums.StudyEnrollEnum;
import com.drive.admin.enums.SubjectTypeEnum;
import com.drive.admin.pojo.dto.AccountFlowDetailInstallParam;
import com.drive.admin.pojo.dto.PlatformWalletEditParam;
import com.drive.admin.pojo.dto.PlatformWalletInstallParam;
import com.drive.admin.pojo.dto.PlatformWalletPageQueryParam;
import com.drive.admin.pojo.entity.AccountFlowDetailEntity;
import com.drive.admin.pojo.entity.PlatformWalletDetailEntity;
import com.drive.admin.pojo.entity.PlatformWalletEntity;
import com.drive.admin.pojo.entity.StudentInfoEntity;
import com.drive.admin.pojo.vo.PlatformWalletVo;
import com.drive.admin.repository.PlatformWalletRepository;
import com.drive.admin.service.*;
import com.drive.admin.service.mapstruct.PlatformWalletMapStruct;
import com.drive.admin.strategy.context.SpringContextUtil;
import com.drive.common.core.base.BaseController;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.biz.SubResultCode;
import com.drive.common.core.enums.WalletEnum;
import com.drive.common.core.exception.BizException;
import com.drive.common.core.utils.BeanConvertUtils;
import com.drive.common.core.utils.SpringUtils;
import com.drive.common.data.utils.ExcelUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;


/**
 *
 * 教练钱包表 服务类
 *
 * @author xiaoguo
 */
@Slf4j
@Service
public class  PlatformWalletRepositoryImpl extends BaseController<PlatformWalletPageQueryParam, PlatformWalletEntity>  implements PlatformWalletRepository{

    //  教练钱包表 服务
    @Autowired
    private PlatformWalletService platformWalletService;

    @Autowired
    private PlatformWalletDetailService platformWalletDetailService;

    //  教练钱包表 DO-DTO转化
    @Autowired
    private PlatformWalletMapStruct platformWalletMapStruct;

    @Autowired
    private AccountFlowDetailService accountFlowDetailService;

    @Autowired
    private AccountFlowService accountFlowService;

    @Autowired
    private StudentInfoService studentInfoService;
    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 教练钱包表 分页列表
     * @date 2020/2/12 17:09
     * @param  * @param PlatformWalletPageQueryParam
     * @return
     */
    @Override
    public ResObject pageList(PlatformWalletPageQueryParam param) {
        log.info(this.getClass() + "pageList-方法请求参数{}",param);
        Page<PlatformWalletEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        // 条件查询
        QueryWrapper queryWrapper = this.getQueryWrapper(platformWalletMapStruct, param);
        //钱包类型为支付宝和微信时，直接返回结果

        if(StrUtil.isNotEmpty(param.getWalletType()) && (param.getWalletType().equals("4") || param.getWalletType().equals("5"))){
            queryWrapper.eq(true,"wallet_type",param.getWalletType());
           IPage<PlatformWalletEntity> pageList =  platformWalletService.page(page,queryWrapper);
            Page<PlatformWalletVo> platformWalletVoPage = platformWalletMapStruct.toVoList(pageList);
            return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),platformWalletVoPage);
        }

        //  模糊查询
        //通过用户名模糊查询时,必须指定钱包类型
        if(StrUtil.isNotEmpty(param.getVagueUserNameSearch()) && StrUtil.isNotEmpty(param.getWalletType())){
            String[] userEntityIds = getUserEntityId(param.getVagueUserNameSearch(), param.getWalletType());
            if(userEntityIds != null){
                queryWrapper.in("user_id",userEntityIds);
            }else {
                return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
            }
        }

        //  开始时间 结束时间都有才进入
        if (StrUtil.isNotEmpty(param.getBeginTime()) && StrUtil.isNotEmpty(param.getEndTime())){
            queryWrapper.between(StrUtil.isNotEmpty(param.getBeginTime()),"create_time",param.getBeginTime(),param.getEndTime());
        }
        IPage<PlatformWalletEntity> pageList = platformWalletService.page(page, queryWrapper);

        if (pageList.getRecords().size() <= 0){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }

        Page<PlatformWalletVo> platformWalletVoPage = platformWalletMapStruct.toVoList(pageList);
        List<PlatformWalletVo> records = platformWalletVoPage.getRecords();
        //查询用户名
        for (PlatformWalletVo record : records) {
            String userName = getUserNameByType(record.getUserId(), record.getWalletType());
            if(userName != null){
                record.setUserName(userName);
            }
        }

        log.info(this.getClass() + "pageList-方法请求结果{}",platformWalletVoPage);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),platformWalletVoPage);
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 教练钱包表 查询列表
     * @date 2020/2/12 17:09
     * @param  * @param PlatformWalletPageQueryParam
     * @return
     */
    @Override
    public ResObject findList(PlatformWalletPageQueryParam param) {
        log.info(this.getClass() + "findList-方法请求参数{}",param);
        // 这里判断条件进行查询
        QueryWrapper queryWrapper= this.getQueryWrapper(platformWalletMapStruct, param);
        // 如 queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"phone",param.getPhone());
        List<PlatformWalletEntity> platformWalletList = platformWalletService.list(queryWrapper);
        if (platformWalletList == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),platformWalletList);
        }
        List<PlatformWalletVo> platformWalletVoList = platformWalletMapStruct.toVoList(platformWalletList);
        log.info(this.getClass() + "findList-方法请求结果{}",platformWalletVoList);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),platformWalletVoList);
    }

    /**
    * 对象条件查询返回单条教练钱包表数据
    * @param param
    * @return
    */
    @Override
    public ResObject getInfo(PlatformWalletPageQueryParam param) {
        log.info(this.getClass() + "getInfo-方法请求参数{}",param);
        if (param == null){
            return R.failure("数据空");
        }
        // 这里判断条件进行查询
        QueryWrapper queryWrapper = this.getQueryWrapper(platformWalletMapStruct, param);
        PlatformWalletEntity platformWallet = platformWalletService.getOne(queryWrapper);
        if (platformWallet == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),platformWallet);
        }
        PlatformWalletVo platformWalletVo = BeanConvertUtils.copy(platformWallet, PlatformWalletVo.class);
        log.info(this.getClass() + "getInfo-方法请求结果{}",platformWalletVo);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),platformWalletVo);
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 通过ID获取 教练钱包表 单条数据
     * @date 2020/2/12 17:09
     * @param  * @param PlatformWalletPageQueryParam
     * @return
     */
    @Override
    public ResObject getById(String id) {
        log.info(this.getClass() + "getById-方法请求参数{}",id);
        if (StrUtil.isEmpty(id)){
            return R.failure("数据空");
        }
        // 通过ID 获取 单条数据
        PlatformWalletEntity platformWallet = platformWalletService.getById(id);
        if (platformWallet == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),platformWallet);
        }
        PlatformWalletVo platformWalletVo = BeanConvertUtils.copy(platformWallet, PlatformWalletVo.class);
        log.info(this.getClass() + "getById-方法请求结果{}",platformWalletVo);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),platformWalletVo);
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 保存教练钱包表 数据
     * @date 2020/2/12 17:09
     * @param  * @param PlatformWalletPageQueryParam
     * @return
     */
    @Override
    public ResObject save(PlatformWalletInstallParam installParam) {
        log.info(this.getClass() + "save方法请求参数{}",installParam);
        if (installParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        PlatformWalletEntity platformWallet = BeanConvertUtils.copy(installParam, PlatformWalletEntity.class);
        Boolean result = platformWalletService.save(platformWallet);
        log.info(this.getClass() + "save-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_INSTALL_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_INSTALL_FAILL.subCode(),SubResultCode.DATA_INSTALL_FAILL.subMsg());
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description  修改教练钱包表 数据
     * @date 2020/2/12 17:09
     * @param  * @param PlatformWalletPageQueryParam
     * @return
     */
    @Override
    public ResObject update(PlatformWalletEditParam updateParam) {
        log.info(this.getClass() + "update方法请求参数{}",updateParam);
        if (updateParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        PlatformWalletEntity platformWallet = BeanConvertUtils.copy(updateParam, PlatformWalletEntity.class);
        Boolean result = platformWalletService.updateById(platformWallet);
        log.info(this.getClass() + "update-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_UPDATE_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_UPDATE_FAILL.subCode(),SubResultCode.DATA_UPDATE_FAILL.subMsg());
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 数组删除教练钱包表 数据
     * @date 2020/2/12 17:09
     * @param  * @param PlatformWalletPageQueryParam
     * @return
     */
    @Override
    public ResObject deleteByIds(String[] ids) {
        if (ids.length <= 0){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        return R.toRes(platformWalletService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * *通过id删除教练钱包表 信息
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
        Boolean result = platformWalletService.removeById(id);
        log.info(this.getClass() + "deleteById-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_DELETE_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_DELETE_FAILL.subCode(),SubResultCode.DATA_DELETE_FAILL.subMsg());
    }

    /**
     * *导出教练钱包表 信息
     **/
    @Override
    public ResObject exportXls(PlatformWalletPageQueryParam param, HttpServletResponse response)throws IOException {
        log.info(this.getClass() + "exportXls方法请求参数{}",param);
        QueryWrapper queryWrapper = this.getQueryWrapper(platformWalletMapStruct, param);
        List<PlatformWalletEntity> list = platformWalletService.list(queryWrapper);
        List<PlatformWalletVo>platformWalletList = platformWalletMapStruct.toVoList(list);
        ExcelUtils.exportExcel(platformWalletList, PlatformWalletVo.class, "", new ExportParams(), response);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.EXPORT_DATA_SUCCESS.subMsg());
    }

    /**
     * *修改状态
     **/
    @Override
    public ResObject changeStatus(PlatformWalletEditParam param) {
        log.info(this.getClass() + "changeStatus方法请求参数{}",param);
        if (StrUtil.isEmpty(param.getId())){
            log.error("ID数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        PlatformWalletEntity PlatformWalletEntity = new PlatformWalletEntity();
        PlatformWalletEntity.setId(param.getId());
        //PlatformWalletEntity.setStatus(param.getStatus());
        //PlatformWalletEntity.setUpdateTime()
        Boolean result = platformWalletService.updateById(PlatformWalletEntity);
        log.info(this.getClass() +"changeStatus方法请求对象参数{}，请求结果{}",PlatformWalletEntity,result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_STATUS_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_STATUS_FAILL.subCode(),SubResultCode.DATA_STATUS_FAILL.subMsg());
    }

    @Override
    @Transactional
    public ResObject walletSettle(String orderNo) {
        log.info(this.getClass()+"walletSettle-方法请求参数{}",orderNo);
        if (StrUtil.isEmpty(orderNo)){
            throw new BizException(500,SubResultCode.PARAMISBLANK.subCode(),"订单号不能为空");
        }
        // 订单号查询 钱包明细
        QueryWrapper accountFlowQueryWrapper = new QueryWrapper();
        accountFlowQueryWrapper.eq("order_no",orderNo);
        List<AccountFlowDetailEntity> accountFlowDetailList = accountFlowDetailService.list(accountFlowQueryWrapper);
        if (accountFlowDetailList.size() <=0){
            return R.failure(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        // 开始给各个  钱包结算
        accountFlowDetailList.stream().forEach((item) ->{{
            // //判断该账务明细是否已经结算
            QueryWrapper platformWalletDetailQueryWrapper = new QueryWrapper();
            platformWalletDetailQueryWrapper.eq("account_detail_id",item.getId());
            int count = platformWalletDetailService.count(platformWalletDetailQueryWrapper);
            // 未结算状态才进行结算
            if (count <= 0){
                QueryWrapper platformWalletQueryWrapper = new QueryWrapper();
                platformWalletQueryWrapper.eq("user_id",item.getIncomeUserId());
                PlatformWalletEntity platformWallet = platformWalletService.getOne(platformWalletQueryWrapper);
                if (platformWallet == null) throw new BizException(500,SubResultCode.DATA_UPDATE_FAILL.subCode(),"该账户未设置钱包账户");
                // 相加
                platformWallet.setWalletAmount(platformWallet.getWalletAmount().subtract(item.getItemAmount()));
                // 修改钱包金额
                Boolean platformWalletResult =platformWalletService.updateById(platformWallet);
                if (!platformWalletResult) throw new BizException(500,SubResultCode.DATA_UPDATE_FAILL.subCode(),SubResultCode.DATA_UPDATE_FAILL.subMsg());
                //记录钱包流水明细
                PlatformWalletDetailEntity platformWalletDetail = new PlatformWalletDetailEntity();
                platformWalletDetail.setUserId(platformWallet.getUserId());
                platformWalletDetail.setTradeAmount(item.getItemAmount());
                // 交易类型（支出）
                platformWalletDetail.setTradeType(StudyEnrollEnum.DRIVER_WALLET_SUBMIT_CASH.getCode());
                if(item.getItemAmount() != null){
                    // 交易类型（收益）
                    platformWalletDetail.setTradeType(StudyEnrollEnum.DRIVER_WALLET_INCOME.getCode());
                }
                platformWalletDetail.setWalletDetailName(item.getItemName());
                platformWalletDetail.setTradeSubject(item.getTradeSubject());  // 交易类型科目
                platformWalletDetail.setTradeSubjectItems(item.getTradeSubjectItems()); //交易类型科目明细
                platformWalletDetail.setAccountDetailId(item.getId());
                platformWalletDetail.setBalance(platformWallet.getWalletAmount()); //余额
                platformWalletDetail.setOperatorId(platformWallet.getOperatorId()); // 运营商id
                //
                platformWalletDetail.setDataMsValue(idWorker.nextId());
                Boolean platformWalletDetailResult = platformWalletDetailService.save(platformWalletDetail);
                if (!platformWalletResult) throw new BizException(500,SubResultCode.DATA_UPDATE_FAILL.subCode(),SubResultCode.DATA_UPDATE_FAILL.subMsg());
            }
        }});
        return R.success(SubResultCode.WALLET_SETTLE_SUCCESS.subCode(),SubResultCode.WALLET_SETTLE_SUCCESS.subMsg());
    }
    /** 根据订单结算司机，平台，推荐人等相关费用，并且记录结算流水
     * @param orderNo 订单号
     * @throws Exception
     */
    @Transactional
    @Override
    public ResObject settlementByOrder(String orderNo) {
        if (StrUtil.isEmpty(orderNo)){
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),"订单号不能为空");
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("order_no",orderNo);
        //查询出该订单的账务流水明细
        List<AccountFlowDetailEntity> accountFlowDetailList = accountFlowDetailService.list(queryWrapper);
        if (accountFlowDetailList.size() <= 0){
            return R.success();
            //throw new BizException(500,SubResultCode.OPERATION_ERROR.subCode(),"数据异常不可操作");
        }
        List<AccountFlowDetailInstallParam> accountFlowDetailInstall = BeanConvertUtils.copyList(accountFlowDetailList,AccountFlowDetailInstallParam.class);
        accountFlowDetailInstall.stream().forEach((item) ->{
            // 循环 插入数据
            this.settlementToWallet(item);
        });
        return R.success("执行成功");
    }

    @Transactional
    @Override
    public ResObject settlementToWallet(AccountFlowDetailInstallParam accountFlowDetailInstallParam) {
        log.info(this.getClass() + "settlementToWallet-方法请求参数{}",accountFlowDetailInstallParam);
        //1.先查询用户钱包
        // 条件查询
        QueryWrapper queryWrapper = new QueryWrapper();
        // 钱包用户ID
        queryWrapper.eq("user_id",accountFlowDetailInstallParam.getIncomeUserId());
        PlatformWalletEntity platformWallet = platformWalletService.getOne(queryWrapper);
        if (platformWallet == null){
            throw new BizException(500,SubResultCode.DATA_INSTALL_FAILL.subCode(),"数据异常");
        }
        // 钱包计算
        platformWallet.setWalletAmount(platformWallet.getWalletAmount().add(accountFlowDetailInstallParam.getItemAmount()));
        // 更新数据
        Boolean platformWalletRes = platformWalletService.updateById(platformWallet);
        if (!platformWalletRes){
            throw new BizException(500,SubResultCode.DATA_UPDATE_FAILL.subCode(),SubResultCode.DATA_UPDATE_FAILL.subMsg());
        }
        //2 记录钱包流水明细
        PlatformWalletDetailEntity platformWalletDetail = new PlatformWalletDetailEntity();
        // 用户ID
        platformWalletDetail.setUserId(platformWallet.getUserId());
        // 交易金额
        platformWalletDetail.setTradeAmount(accountFlowDetailInstallParam.getItemAmount());
        // 交易类型科目
        platformWalletDetail.setTradeSubject(accountFlowDetailInstallParam.getTradeSubject());
        // 交易类型科目明细
        platformWalletDetail.setTradeSubjectItems(accountFlowDetailInstallParam.getTradeSubjectItems());
        // 默认正数 正数（收益）
        platformWalletDetail.setTradeType(WalletEnum.DRIVER_WALLET_INCOME.getCode());
        if((accountFlowDetailInstallParam.getItemAmount().compareTo(BigDecimal.ZERO)) == -1){
            //负数（支出）
            platformWalletDetail.setTradeType(WalletEnum.DRIVER_WALLET_SUBMIT_CASH.getCode());
        }
        // 收入/支出名称
        platformWalletDetail.setWalletDetailName(accountFlowDetailInstallParam.getItemName());
        // 数据时间
        platformWalletDetail.setSetUpDate(LocalDateTime.now());
        // 状态 成功
        platformWalletDetail.setStatus(StatusEnum.ENABLE.getCode());
        // 创建时间
        platformWalletDetail.setCreateTime(LocalDateTime.now());
        //  账务流水明细id/提现时为清算记录id
        platformWalletDetail.setAccountDetailId(accountFlowDetailInstallParam.getId());
        // 余额(进账，处长之前的余额)
        platformWalletDetail.setBalance(platformWallet.getWalletAmount()); //账户余额
        platformWalletDetail.setOperatorId(platformWallet.getOperatorId());  // 运营商
        // 数据插入ID
        platformWalletDetail.setDataMsValue(idWorker.nextId());
        Boolean platformWalletDetailRes = platformWalletDetailService.save(platformWalletDetail);
        if (!platformWalletDetailRes){
            throw new BizException(500,SubResultCode.DATA_UPDATE_FAILL.subCode(),SubResultCode.DATA_UPDATE_FAILL.subMsg());
        }
        return R.success("执行成功");
    }

    /**
     * 钱包对账
     * @param walletId 钱包id
     * @return
     */
    @Override
    @Transactional
    public ResObject walletReconciliation(String walletId) {
        if(StringUtils.isEmpty(walletId)){
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),"用户钱包id不能为空");
        }

        //查询用户钱包id
        PlatformWalletEntity wallet = platformWalletService.getById(walletId);
        //查询条件
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id",wallet.getUserId());
        queryWrapper.orderByAsc("create_time");
        List<PlatformWalletDetailEntity> platformWalletDetailEntityList = platformWalletDetailService.list(queryWrapper);
        //没有明细直接返回
        if(platformWalletDetailEntityList.isEmpty() && platformWalletDetailEntityList == null){
            return R.success("钱包对账成功");
        }

        //新余额
        BigDecimal newBalance = new BigDecimal(0);
        for (PlatformWalletDetailEntity detailEntity : platformWalletDetailEntityList) {
                //交易成功的明细
                if(!"2".equals(detailEntity.getStatus())){
                    //交易类型
                    String tradeType = detailEntity.getTradeType();
                    //余额
                    BigDecimal balance = detailEntity.getBalance();
                    //交易金额
                    BigDecimal tradeAmount = detailEntity.getTradeAmount();
                    //交易类型为收益
                    if("1".equals(tradeType)){
                       newBalance = newBalance.add(tradeAmount);
                    }
                    //交易类型为支出
                    if("2".equals(tradeType)){
                        if(tradeAmount.compareTo(BigDecimal.ZERO) != -1){
                            newBalance = newBalance.subtract(tradeAmount);
                        }else {
                            newBalance = newBalance.add(tradeAmount);
                        }
                    }
                    //新余额与该条数据余额相等跳出
                    if(newBalance.compareTo(balance) == 0){
                        continue;
                    }
                    detailEntity.setBalance(newBalance);
                    //更新该明细的余额记录
                    platformWalletDetailService.updateById(detailEntity);
                }
        }
        //设置钱包的余额
        wallet.setWalletAmount(newBalance);
        boolean flag = platformWalletService.updateById(wallet);
        return flag == true ? R.success("对账成功") : R.failure("对账失败");
    }

    /**
     * 根据钱包类型和userId,查询用户
     * @param userId
     * @param walletType
     * @return
     */
    private String getUserNameByType(String userId,String walletType) {
        try {
            for (PlatformWalletEnum walletEnum : PlatformWalletEnum.values()) {
                if(walletType.equals(walletEnum.getCode())){
                    Object serviceBean = SpringContextUtil.getBean(walletEnum.getServiceClass());
                    Method getByIdMethod = serviceBean.getClass().getMethod("getById", Serializable.class);
                    Object entityObject = getByIdMethod.invoke(serviceBean, userId);
                    return PlatformWalletEnum.getEntityName(entityObject,walletEnum);
                }
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据钱包类型和用户名查询钱包用户对象id
     * @return
     */
    public String[] getUserEntityId(String vaugeUserName,String walletType){
        QueryWrapper<Object> objectQueryWrapper = new QueryWrapper<>();
        ArrayList<String> idStr = new ArrayList<>();
        try {
            for (PlatformWalletEnum walletEnum : PlatformWalletEnum.values()) {
                if(walletType.equals(walletEnum.getCode())){
                    Object serviceBean = SpringContextUtil.getBean(walletEnum.getServiceClass());
                    objectQueryWrapper.like(true,walletEnum.getNameField(),vaugeUserName);
                    Method listMethod = serviceBean.getClass().getMethod("list", Wrapper.class);
                    List resultList = (List) listMethod.invoke(serviceBean, objectQueryWrapper);
                    if(resultList != null && resultList.size() > 0){
                        for(Object obj : resultList){
                            String entityId = PlatformWalletEnum.getEntityId(obj);
                            idStr.add(entityId);
                        }
                    }
                }
            }
            if(CollectionUtils.isNotEmpty(idStr)){
                return idStr.toArray(new String[0]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}