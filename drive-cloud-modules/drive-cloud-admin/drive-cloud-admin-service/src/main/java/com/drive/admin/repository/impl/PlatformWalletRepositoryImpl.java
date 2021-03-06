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
 * ??????????????? ?????????
 *
 * @author xiaoguo
 */
@Slf4j
@Service
public class  PlatformWalletRepositoryImpl extends BaseController<PlatformWalletPageQueryParam, PlatformWalletEntity>  implements PlatformWalletRepository{

    //  ??????????????? ??????
    @Autowired
    private PlatformWalletService platformWalletService;

    @Autowired
    private PlatformWalletDetailService platformWalletDetailService;

    //  ??????????????? DO-DTO??????
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
     *????????????
     * @author xiaoguo
     * @description ??????????????? ????????????
     * @date 2020/2/12 17:09
     * @param  * @param PlatformWalletPageQueryParam
     * @return
     */
    @Override
    public ResObject pageList(PlatformWalletPageQueryParam param) {
        log.info(this.getClass() + "pageList-??????????????????{}",param);
        Page<PlatformWalletEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        // ????????????
        QueryWrapper queryWrapper = this.getQueryWrapper(platformWalletMapStruct, param);
        //?????????????????????????????????????????????????????????

        if(StrUtil.isNotEmpty(param.getWalletType()) && (param.getWalletType().equals("4") || param.getWalletType().equals("5"))){
            queryWrapper.eq(true,"wallet_type",param.getWalletType());
           IPage<PlatformWalletEntity> pageList =  platformWalletService.page(page,queryWrapper);
            Page<PlatformWalletVo> platformWalletVoPage = platformWalletMapStruct.toVoList(pageList);
            return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),platformWalletVoPage);
        }

        //  ????????????
        //??????????????????????????????,????????????????????????
        if(StrUtil.isNotEmpty(param.getVagueUserNameSearch()) && StrUtil.isNotEmpty(param.getWalletType())){
            String[] userEntityIds = getUserEntityId(param.getVagueUserNameSearch(), param.getWalletType());
            if(userEntityIds != null){
                queryWrapper.in("user_id",userEntityIds);
            }else {
                return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
            }
        }

        //  ???????????? ???????????????????????????
        if (StrUtil.isNotEmpty(param.getBeginTime()) && StrUtil.isNotEmpty(param.getEndTime())){
            queryWrapper.between(StrUtil.isNotEmpty(param.getBeginTime()),"create_time",param.getBeginTime(),param.getEndTime());
        }
        IPage<PlatformWalletEntity> pageList = platformWalletService.page(page, queryWrapper);

        if (pageList.getRecords().size() <= 0){
            log.error("?????????");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }

        Page<PlatformWalletVo> platformWalletVoPage = platformWalletMapStruct.toVoList(pageList);
        List<PlatformWalletVo> records = platformWalletVoPage.getRecords();
        //???????????????
        for (PlatformWalletVo record : records) {
            String userName = getUserNameByType(record.getUserId(), record.getWalletType());
            if(userName != null){
                record.setUserName(userName);
            }
        }

        log.info(this.getClass() + "pageList-??????????????????{}",platformWalletVoPage);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),platformWalletVoPage);
    }

    /*
     *
     *????????????
     * @author xiaoguo
     * @description ??????????????? ????????????
     * @date 2020/2/12 17:09
     * @param  * @param PlatformWalletPageQueryParam
     * @return
     */
    @Override
    public ResObject findList(PlatformWalletPageQueryParam param) {
        log.info(this.getClass() + "findList-??????????????????{}",param);
        // ??????????????????????????????
        QueryWrapper queryWrapper= this.getQueryWrapper(platformWalletMapStruct, param);
        // ??? queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"phone",param.getPhone());
        List<PlatformWalletEntity> platformWalletList = platformWalletService.list(queryWrapper);
        if (platformWalletList == null){
            log.error("?????????");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),platformWalletList);
        }
        List<PlatformWalletVo> platformWalletVoList = platformWalletMapStruct.toVoList(platformWalletList);
        log.info(this.getClass() + "findList-??????????????????{}",platformWalletVoList);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),platformWalletVoList);
    }

    /**
    * ???????????????????????????????????????????????????
    * @param param
    * @return
    */
    @Override
    public ResObject getInfo(PlatformWalletPageQueryParam param) {
        log.info(this.getClass() + "getInfo-??????????????????{}",param);
        if (param == null){
            return R.failure("?????????");
        }
        // ??????????????????????????????
        QueryWrapper queryWrapper = this.getQueryWrapper(platformWalletMapStruct, param);
        PlatformWalletEntity platformWallet = platformWalletService.getOne(queryWrapper);
        if (platformWallet == null){
            log.error("?????????");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),platformWallet);
        }
        PlatformWalletVo platformWalletVo = BeanConvertUtils.copy(platformWallet, PlatformWalletVo.class);
        log.info(this.getClass() + "getInfo-??????????????????{}",platformWalletVo);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),platformWalletVo);
    }

    /*
     *
     *????????????
     * @author xiaoguo
     * @description ??????ID?????? ??????????????? ????????????
     * @date 2020/2/12 17:09
     * @param  * @param PlatformWalletPageQueryParam
     * @return
     */
    @Override
    public ResObject getById(String id) {
        log.info(this.getClass() + "getById-??????????????????{}",id);
        if (StrUtil.isEmpty(id)){
            return R.failure("?????????");
        }
        // ??????ID ?????? ????????????
        PlatformWalletEntity platformWallet = platformWalletService.getById(id);
        if (platformWallet == null){
            log.error("?????????");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),platformWallet);
        }
        PlatformWalletVo platformWalletVo = BeanConvertUtils.copy(platformWallet, PlatformWalletVo.class);
        log.info(this.getClass() + "getById-??????????????????{}",platformWalletVo);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),platformWalletVo);
    }

    /*
     *
     *????????????
     * @author xiaoguo
     * @description ????????????????????? ??????
     * @date 2020/2/12 17:09
     * @param  * @param PlatformWalletPageQueryParam
     * @return
     */
    @Override
    public ResObject save(PlatformWalletInstallParam installParam) {
        log.info(this.getClass() + "save??????????????????{}",installParam);
        if (installParam == null){
            log.error("?????????");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        PlatformWalletEntity platformWallet = BeanConvertUtils.copy(installParam, PlatformWalletEntity.class);
        Boolean result = platformWalletService.save(platformWallet);
        log.info(this.getClass() + "save-??????????????????{}",result);
        // ????????????
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_INSTALL_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_INSTALL_FAILL.subCode(),SubResultCode.DATA_INSTALL_FAILL.subMsg());
    }

    /*
     *
     *????????????
     * @author xiaoguo
     * @description  ????????????????????? ??????
     * @date 2020/2/12 17:09
     * @param  * @param PlatformWalletPageQueryParam
     * @return
     */
    @Override
    public ResObject update(PlatformWalletEditParam updateParam) {
        log.info(this.getClass() + "update??????????????????{}",updateParam);
        if (updateParam == null){
            log.error("?????????");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        PlatformWalletEntity platformWallet = BeanConvertUtils.copy(updateParam, PlatformWalletEntity.class);
        Boolean result = platformWalletService.updateById(platformWallet);
        log.info(this.getClass() + "update-??????????????????{}",result);
        // ????????????
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_UPDATE_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_UPDATE_FAILL.subCode(),SubResultCode.DATA_UPDATE_FAILL.subMsg());
    }

    /*
     *
     *????????????
     * @author xiaoguo
     * @description ??????????????????????????? ??????
     * @date 2020/2/12 17:09
     * @param  * @param PlatformWalletPageQueryParam
     * @return
     */
    @Override
    public ResObject deleteByIds(String[] ids) {
        if (ids.length <= 0){
            log.error("?????????");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        return R.toRes(platformWalletService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * *??????id????????????????????? ??????
     **/
    @Override
    public ResObject deleteById(String id) {
        log.info(this.getClass() + "deleteById-??????????????????{}",id);
        if(StrUtil.isEmpty(id)){
            log.error("ID?????????");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        //QueryWrapper<String> queryWrapper = new QueryWrapper<String>();
        //queryWrapper.eq(StrUtil.isNotEmpty(id),"id",id);
        Boolean result = platformWalletService.removeById(id);
        log.info(this.getClass() + "deleteById-??????????????????{}",result);
        // ????????????
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_DELETE_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_DELETE_FAILL.subCode(),SubResultCode.DATA_DELETE_FAILL.subMsg());
    }

    /**
     * *????????????????????? ??????
     **/
    @Override
    public ResObject exportXls(PlatformWalletPageQueryParam param, HttpServletResponse response)throws IOException {
        log.info(this.getClass() + "exportXls??????????????????{}",param);
        QueryWrapper queryWrapper = this.getQueryWrapper(platformWalletMapStruct, param);
        List<PlatformWalletEntity> list = platformWalletService.list(queryWrapper);
        List<PlatformWalletVo>platformWalletList = platformWalletMapStruct.toVoList(list);
        ExcelUtils.exportExcel(platformWalletList, PlatformWalletVo.class, "", new ExportParams(), response);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.EXPORT_DATA_SUCCESS.subMsg());
    }

    /**
     * *????????????
     **/
    @Override
    public ResObject changeStatus(PlatformWalletEditParam param) {
        log.info(this.getClass() + "changeStatus??????????????????{}",param);
        if (StrUtil.isEmpty(param.getId())){
            log.error("ID?????????");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        PlatformWalletEntity PlatformWalletEntity = new PlatformWalletEntity();
        PlatformWalletEntity.setId(param.getId());
        //PlatformWalletEntity.setStatus(param.getStatus());
        //PlatformWalletEntity.setUpdateTime()
        Boolean result = platformWalletService.updateById(PlatformWalletEntity);
        log.info(this.getClass() +"changeStatus????????????????????????{}???????????????{}",PlatformWalletEntity,result);
        // ????????????
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_STATUS_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_STATUS_FAILL.subCode(),SubResultCode.DATA_STATUS_FAILL.subMsg());
    }

    @Override
    @Transactional
    public ResObject walletSettle(String orderNo) {
        log.info(this.getClass()+"walletSettle-??????????????????{}",orderNo);
        if (StrUtil.isEmpty(orderNo)){
            throw new BizException(500,SubResultCode.PARAMISBLANK.subCode(),"?????????????????????");
        }
        // ??????????????? ????????????
        QueryWrapper accountFlowQueryWrapper = new QueryWrapper();
        accountFlowQueryWrapper.eq("order_no",orderNo);
        List<AccountFlowDetailEntity> accountFlowDetailList = accountFlowDetailService.list(accountFlowQueryWrapper);
        if (accountFlowDetailList.size() <=0){
            return R.failure(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        // ???????????????  ????????????
        accountFlowDetailList.stream().forEach((item) ->{{
            // //???????????????????????????????????????
            QueryWrapper platformWalletDetailQueryWrapper = new QueryWrapper();
            platformWalletDetailQueryWrapper.eq("account_detail_id",item.getId());
            int count = platformWalletDetailService.count(platformWalletDetailQueryWrapper);
            // ??????????????????????????????
            if (count <= 0){
                QueryWrapper platformWalletQueryWrapper = new QueryWrapper();
                platformWalletQueryWrapper.eq("user_id",item.getIncomeUserId());
                PlatformWalletEntity platformWallet = platformWalletService.getOne(platformWalletQueryWrapper);
                if (platformWallet == null) throw new BizException(500,SubResultCode.DATA_UPDATE_FAILL.subCode(),"??????????????????????????????");
                // ??????
                platformWallet.setWalletAmount(platformWallet.getWalletAmount().subtract(item.getItemAmount()));
                // ??????????????????
                Boolean platformWalletResult =platformWalletService.updateById(platformWallet);
                if (!platformWalletResult) throw new BizException(500,SubResultCode.DATA_UPDATE_FAILL.subCode(),SubResultCode.DATA_UPDATE_FAILL.subMsg());
                //????????????????????????
                PlatformWalletDetailEntity platformWalletDetail = new PlatformWalletDetailEntity();
                platformWalletDetail.setUserId(platformWallet.getUserId());
                platformWalletDetail.setTradeAmount(item.getItemAmount());
                // ????????????????????????
                platformWalletDetail.setTradeType(StudyEnrollEnum.DRIVER_WALLET_SUBMIT_CASH.getCode());
                if(item.getItemAmount() != null){
                    // ????????????????????????
                    platformWalletDetail.setTradeType(StudyEnrollEnum.DRIVER_WALLET_INCOME.getCode());
                }
                platformWalletDetail.setWalletDetailName(item.getItemName());
                platformWalletDetail.setTradeSubject(item.getTradeSubject());  // ??????????????????
                platformWalletDetail.setTradeSubjectItems(item.getTradeSubjectItems()); //????????????????????????
                platformWalletDetail.setAccountDetailId(item.getId());
                platformWalletDetail.setBalance(platformWallet.getWalletAmount()); //??????
                platformWalletDetail.setOperatorId(platformWallet.getOperatorId()); // ?????????id
                //
                platformWalletDetail.setDataMsValue(idWorker.nextId());
                Boolean platformWalletDetailResult = platformWalletDetailService.save(platformWalletDetail);
                if (!platformWalletResult) throw new BizException(500,SubResultCode.DATA_UPDATE_FAILL.subCode(),SubResultCode.DATA_UPDATE_FAILL.subMsg());
            }
        }});
        return R.success(SubResultCode.WALLET_SETTLE_SUCCESS.subCode(),SubResultCode.WALLET_SETTLE_SUCCESS.subMsg());
    }
    /** ???????????????????????????????????????????????????????????????????????????????????????
     * @param orderNo ?????????
     * @throws Exception
     */
    @Transactional
    @Override
    public ResObject settlementByOrder(String orderNo) {
        if (StrUtil.isEmpty(orderNo)){
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),"?????????????????????");
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("order_no",orderNo);
        //???????????????????????????????????????
        List<AccountFlowDetailEntity> accountFlowDetailList = accountFlowDetailService.list(queryWrapper);
        if (accountFlowDetailList.size() <= 0){
            return R.success();
            //throw new BizException(500,SubResultCode.OPERATION_ERROR.subCode(),"????????????????????????");
        }
        List<AccountFlowDetailInstallParam> accountFlowDetailInstall = BeanConvertUtils.copyList(accountFlowDetailList,AccountFlowDetailInstallParam.class);
        accountFlowDetailInstall.stream().forEach((item) ->{
            // ?????? ????????????
            this.settlementToWallet(item);
        });
        return R.success("????????????");
    }

    @Transactional
    @Override
    public ResObject settlementToWallet(AccountFlowDetailInstallParam accountFlowDetailInstallParam) {
        log.info(this.getClass() + "settlementToWallet-??????????????????{}",accountFlowDetailInstallParam);
        //1.?????????????????????
        // ????????????
        QueryWrapper queryWrapper = new QueryWrapper();
        // ????????????ID
        queryWrapper.eq("user_id",accountFlowDetailInstallParam.getIncomeUserId());
        PlatformWalletEntity platformWallet = platformWalletService.getOne(queryWrapper);
        if (platformWallet == null){
            throw new BizException(500,SubResultCode.DATA_INSTALL_FAILL.subCode(),"????????????");
        }
        // ????????????
        platformWallet.setWalletAmount(platformWallet.getWalletAmount().add(accountFlowDetailInstallParam.getItemAmount()));
        // ????????????
        Boolean platformWalletRes = platformWalletService.updateById(platformWallet);
        if (!platformWalletRes){
            throw new BizException(500,SubResultCode.DATA_UPDATE_FAILL.subCode(),SubResultCode.DATA_UPDATE_FAILL.subMsg());
        }
        //2 ????????????????????????
        PlatformWalletDetailEntity platformWalletDetail = new PlatformWalletDetailEntity();
        // ??????ID
        platformWalletDetail.setUserId(platformWallet.getUserId());
        // ????????????
        platformWalletDetail.setTradeAmount(accountFlowDetailInstallParam.getItemAmount());
        // ??????????????????
        platformWalletDetail.setTradeSubject(accountFlowDetailInstallParam.getTradeSubject());
        // ????????????????????????
        platformWalletDetail.setTradeSubjectItems(accountFlowDetailInstallParam.getTradeSubjectItems());
        // ???????????? ??????????????????
        platformWalletDetail.setTradeType(WalletEnum.DRIVER_WALLET_INCOME.getCode());
        if((accountFlowDetailInstallParam.getItemAmount().compareTo(BigDecimal.ZERO)) == -1){
            //??????????????????
            platformWalletDetail.setTradeType(WalletEnum.DRIVER_WALLET_SUBMIT_CASH.getCode());
        }
        // ??????/????????????
        platformWalletDetail.setWalletDetailName(accountFlowDetailInstallParam.getItemName());
        // ????????????
        platformWalletDetail.setSetUpDate(LocalDateTime.now());
        // ?????? ??????
        platformWalletDetail.setStatus(StatusEnum.ENABLE.getCode());
        // ????????????
        platformWalletDetail.setCreateTime(LocalDateTime.now());
        //  ??????????????????id/????????????????????????id
        platformWalletDetail.setAccountDetailId(accountFlowDetailInstallParam.getId());
        // ??????(??????????????????????????????)
        platformWalletDetail.setBalance(platformWallet.getWalletAmount()); //????????????
        platformWalletDetail.setOperatorId(platformWallet.getOperatorId());  // ?????????
        // ????????????ID
        platformWalletDetail.setDataMsValue(idWorker.nextId());
        Boolean platformWalletDetailRes = platformWalletDetailService.save(platformWalletDetail);
        if (!platformWalletDetailRes){
            throw new BizException(500,SubResultCode.DATA_UPDATE_FAILL.subCode(),SubResultCode.DATA_UPDATE_FAILL.subMsg());
        }
        return R.success("????????????");
    }

    /**
     * ????????????
     * @param walletId ??????id
     * @return
     */
    @Override
    @Transactional
    public ResObject walletReconciliation(String walletId) {
        if(StringUtils.isEmpty(walletId)){
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),"????????????id????????????");
        }

        //??????????????????id
        PlatformWalletEntity wallet = platformWalletService.getById(walletId);
        //????????????
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id",wallet.getUserId());
        queryWrapper.orderByAsc("create_time");
        List<PlatformWalletDetailEntity> platformWalletDetailEntityList = platformWalletDetailService.list(queryWrapper);
        //????????????????????????
        if(platformWalletDetailEntityList.isEmpty() && platformWalletDetailEntityList == null){
            return R.success("??????????????????");
        }

        //?????????
        BigDecimal newBalance = new BigDecimal(0);
        for (PlatformWalletDetailEntity detailEntity : platformWalletDetailEntityList) {
                //?????????????????????
                if(!"2".equals(detailEntity.getStatus())){
                    //????????????
                    String tradeType = detailEntity.getTradeType();
                    //??????
                    BigDecimal balance = detailEntity.getBalance();
                    //????????????
                    BigDecimal tradeAmount = detailEntity.getTradeAmount();
                    //?????????????????????
                    if("1".equals(tradeType)){
                       newBalance = newBalance.add(tradeAmount);
                    }
                    //?????????????????????
                    if("2".equals(tradeType)){
                        if(tradeAmount.compareTo(BigDecimal.ZERO) != -1){
                            newBalance = newBalance.subtract(tradeAmount);
                        }else {
                            newBalance = newBalance.add(tradeAmount);
                        }
                    }
                    //??????????????????????????????????????????
                    if(newBalance.compareTo(balance) == 0){
                        continue;
                    }
                    detailEntity.setBalance(newBalance);
                    //??????????????????????????????
                    platformWalletDetailService.updateById(detailEntity);
                }
        }
        //?????????????????????
        wallet.setWalletAmount(newBalance);
        boolean flag = platformWalletService.updateById(wallet);
        return flag == true ? R.success("????????????") : R.failure("????????????");
    }

    /**
     * ?????????????????????userId,????????????
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
     * ??????????????????????????????????????????????????????id
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