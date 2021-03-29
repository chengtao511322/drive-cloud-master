package com.drive.admin.strategy.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.drive.admin.enums.StudyEnrollEnum;
import com.drive.admin.pojo.entity.*;
import com.drive.admin.repository.AccountFlowRepository;
import com.drive.admin.repository.PlatformWalletRepository;
import com.drive.admin.service.*;
import com.drive.common.core.biz.R;
import com.drive.admin.pojo.dto.CompleteStudyEnrollParam;
import com.drive.admin.strategy.StudyEnrollStrategy;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.biz.SubResultCode;
import com.drive.common.core.constant.FlowTypeConstant;
import com.drive.common.core.enums.StatusEnum;
import com.drive.common.core.exception.BizException;
import com.drive.common.core.utils.ArithUtil;
import com.drive.common.core.utils.BeanConvertUtils;
import com.drive.common.core.utils.MathMoney;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * @Author 小郭
 * @Description //报名完成
 * @Date $ $
 * @Param $
 * @return $
 **/
@Slf4j
@Service
public class EnrollSuccessStrategy implements StudyEnrollStrategy {

    // 报名状态
    @Autowired
    private StudentStudyEnrollService studentStudyEnrollService;

    // 订单
    @Autowired
    private StudentOrderService studentOrderService;

    @Autowired
    private OneFeeSystemCoachStudentService oneFeeSystemCoachStudentService;

    @Autowired
    private AccountFlowRepository accountFlowRepository;

    @Autowired
    private AccountFlowDetailService accountFlowDetailService;

    @Autowired
    private OperatorService operatorService;

    @Autowired
    private DriveSchoolService driveSchoolService;

    @Autowired
    private AccountFlowService accountFlowService;

    @Autowired
    private PlatformWalletRepository platformWalletRepository;

    @Transactional
    @Override
    public ResObject completeStudyEnroll(CompleteStudyEnrollParam studentStudyEnrollEditParam) {
        if (studentStudyEnrollEditParam == null){
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        // 查询该报名单的状态
        StudentStudyEnrollEntity studentStudyEnroll = studentStudyEnrollService.getById(studentStudyEnrollEditParam.getStudyEnrollNo());
        log.error("学员未报名");
        if (studentStudyEnroll == null)return R.failure(SubResultCode.STUDENT_NO_ENROLL.subCode(),SubResultCode.STUDENT_NO_ENROLL.subMsg());
        if (!(studentStudyEnroll.getEnrollStatus().equals(StudyEnrollEnum.ENROLL_STATUS_PASSWORD_EXAMINE.getCode())))return R.failure(SubResultCode.STATUS_NO_OPERATION.subCode(),SubResultCode.STATUS_NO_OPERATION.subMsg());
        // 修改报名信息
        StudentStudyEnrollEntity studyEnroll = BeanConvertUtils.copy(studentStudyEnrollEditParam, StudentStudyEnrollEntity.class);
        // 修改状态 报名完成
        studyEnroll.setEnrollStatus(StudyEnrollEnum.ENROLL_STATUS_ENROLL_COMPLETE.getCode());
        Boolean studyEnrollResult = studentStudyEnrollService.updateById(studyEnroll);
        // 判断结果
        if (!studyEnrollResult)return R.failure(SubResultCode.DATA_UPDATE_FAILL.subCode(),SubResultCode.DATA_UPDATE_FAILL.subMsg());
        // 查询订单信息
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("study_enroll_no",studentStudyEnrollEditParam.getStudyEnrollNo());
        StudentOrderEntity studentOrder = studentOrderService.getOne(queryWrapper);
        // 没有查询到订单 失败 回滚
        if (studentOrder == null)throw new BizException(500,SubResultCode.STUDENT_NO_ENROLL.subCode(),SubResultCode.STUDENT_NO_ENROLL.subMsg());
        QueryWrapper accountFlowQueryWrapper = new QueryWrapper();
        accountFlowQueryWrapper.eq("order_no",studentOrder.getOrderNo());
        AccountFlowEntity accountFlow = accountFlowService.getOne(accountFlowQueryWrapper);
        if (studentOrder == null)throw new BizException(500,SubResultCode.STUDENT_NO_ENROLL.subCode(),SubResultCode.STUDENT_NO_ENROLL.subMsg());
        // 验证 是否已绑定教练
        QueryWrapper coachStudentQueryWrapper = new QueryWrapper();
        coachStudentQueryWrapper.eq("class_id",studentOrder.getProductId());
        coachStudentQueryWrapper.eq("student_id",studentOrder.getStudentId());
        coachStudentQueryWrapper.eq("order_no",studentOrder.getOrderNo());
        coachStudentQueryWrapper.eq("bind_status",StudyEnrollEnum.BIND_STATUS_ALREADY_BIND.getCode());
        int count = oneFeeSystemCoachStudentService.count(coachStudentQueryWrapper);
        if (count <=0 )throw new BizException(500,"请先为学员绑定教练",SubResultCode.NO_BINDING_COACH.subCode(),"请先为学员绑定教练");
        //创建包过模式-报名完成账务流水
        this.createVIPStudyEnrollCompleteAccountFlow(studyEnroll,studentOrder,accountFlow,true,false);
        return R.success("执行成功");
    }


    void createVIPStudyEnrollCompleteAccountFlow(StudentStudyEnrollEntity studyEnroll, StudentOrderEntity studentOrder, AccountFlowEntity accountFlow,boolean isSettlementSchool,boolean isSubtract){
        //判断是否为VIP报名单,若为自主预约，则直接退出
        if(StudyEnrollEnum.ENROLL_TYPE_AUTONOMOUSLY.getCode().equals(studyEnroll.getEnrollType())){
            throw new BizException(500,"该订单自主预约,无法操作",SubResultCode.SYSTEM_FAILL.subCode(),"该订单自主预约,无法操作");
        }
        //判断是否已经生成过流水
        // 订单号查询
        QueryWrapper accountFlowDetailQueryWrapper = new QueryWrapper();
        accountFlowDetailQueryWrapper.eq("order_no",studentOrder.getOrderNo());
        List<AccountFlowDetailEntity> accountFlowDetailList = accountFlowDetailService.list(accountFlowDetailQueryWrapper);
        // 求和
        BigDecimal money = accountFlowDetailList
                .stream()
                .map(item -> item.getItemAmount() == null ? BigDecimal.ZERO : item.getItemAmount())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        log.info( "总和",money);

        // 若已经生成流水，则直接返回
        if(money.compareTo(studyEnroll.getPrice()) == 0){
            throw new BizException(500,"已经生成流水",SubResultCode.SYSTEM_FAILL.subCode(),"已经生成流水,无法操作");
        }

        // 查询教练学员关联表，获取运营商提成金额
        QueryWrapper coachStudentQueryWrapper = new QueryWrapper();
        // 班型ID
        coachStudentQueryWrapper.eq("class_id",studentOrder.getProductId());
        // 学员ID
        coachStudentQueryWrapper.eq("student_id",studentOrder.getStudentId());
        // 订单号
        coachStudentQueryWrapper.eq("order_no",studentOrder.getOrderNo());
        // 绑定状态
        coachStudentQueryWrapper.eq("bind_status",StudyEnrollEnum.BIND_STATUS_ALREADY_BIND.getCode());
        OneFeeSystemCoachStudentEntity oneFeeSystemCoachStudent = oneFeeSystemCoachStudentService.getOne(coachStudentQueryWrapper);
        if(oneFeeSystemCoachStudent == null)throw  new BizException(500,"该学员未绑定教练，无法计算提成",SubResultCode.DATA_NULL.subCode(),"该学员未绑定教练，无法计算提成");

        //计算最底层运营商收益
        AccountFlowDetailEntity accountFlowDetail = new AccountFlowDetailEntity();
        accountFlowDetail.setAccountId(accountFlow.getId());
        accountFlowDetail.setOrderNo(accountFlow.getOrderNo());
        // 缓存取数据
        accountFlowDetail.setItemName(operatorService.getById(oneFeeSystemCoachStudent.getOperatorId()).getName() + "提成费用");
        //收益金额
        accountFlowDetail.setItemAmount(oneFeeSystemCoachStudent.getOperatorChangeMoney());
        //运营商id
        accountFlowDetail.setOperatorId(oneFeeSystemCoachStudent.getOperatorId());
        //是否对账
        accountFlowDetail.setIsReconciled(StatusEnum.NO.getCode());
        //是否提现
        accountFlowDetail.setPutForward(StatusEnum.NO.getCode());
        // 运营商收入
        accountFlowDetail.setItemType(StudyEnrollEnum.PLATFORM_WALLET_OPERATOR.getCode());
        accountFlowDetail.setPayType(accountFlow.getPayType());
        accountFlowDetail.setTradeSubject(FlowTypeConstant.ENROLL_INCOME.getCode());  //报名收入
        accountFlowDetail.setTradeSubjectItems(FlowTypeConstant.ENROLL_INCOME.getItemsMap("ENROLL_SHARE")); // 报名提成
        accountFlowDetail.setIncomeUserType(StudyEnrollEnum.INCOME_USER_TYPE_TOPERATOR.getCode()); // 收益人类型
        accountFlowDetail.setIncomeUserId(oneFeeSystemCoachStudent.getOperatorId()); // 收益人id
        Boolean accountFlowDetailResult = accountFlowDetailService.save(accountFlowDetail);
        if (!accountFlowDetailResult)throw new BizException(500,"执行失败",SubResultCode.DATA_INSTALL_FAILL.subCode(),"执行失败");

        //--------------------------------- 计算上级运营商收益 start  ---------------------------------
        BigDecimal operatorChangeMoney =  oneFeeSystemCoachStudent.getServiceChangeMoney(); //平台提成金额(所有上级运营提成金额)
        BigDecimal downOperatorChargePercent ;
        BigDecimal upOperatorChargePercent;
        OperatorEntity  upTOperator;
        BigDecimal surplusMoney = operatorChangeMoney; //剩余金额
        OperatorEntity downOperator = operatorService.getById(oneFeeSystemCoachStudent.getOperatorId());
        while (StrUtil.isNotEmpty(downOperator.getParentId())) {
            upTOperator = operatorService.getById(downOperator.getParentId());  //上级运营商
            downOperatorChargePercent = downOperator.getPlatformChargePercent(); //下级运营商提成百分比
            upOperatorChargePercent = upTOperator.getPlatformChargePercent();   //上级运营商提成百分比
            //当前运营商收益  = （（下级运营商提成百分比 - 上级运营商提成百分比） * 平台提成金额） / 下级运营商提成百分比
            //分子
            BigDecimal molecule = (downOperatorChargePercent.subtract(upOperatorChargePercent)).multiply(operatorChangeMoney);
            //分母
            BigDecimal currentOperatorIncome = new BigDecimal(0);
            if(downOperatorChargePercent.floatValue() > 0){
                //分母
                currentOperatorIncome = ArithUtil.divDown(ArithUtil.divDown(molecule, downOperatorChargePercent,2),new BigDecimal(100),2) ;
            }


            surplusMoney = surplusMoney.subtract(currentOperatorIncome);

            //判断是否还有上一级运营商,是最后一个运营商，则为学车小王子，则所有剩余费用归 最顶层运营商（学车小王子）
            if(StrUtil.isEmpty(upTOperator.getParentId())){
                currentOperatorIncome = surplusMoney.add(currentOperatorIncome);
            }
            accountFlowDetail.setId(IdWorker.getIdStr());
            accountFlowDetail.setItemName(upTOperator.getName() + "提成费用");
            accountFlowDetail.setItemAmount(currentOperatorIncome); //收益金额
            accountFlowDetail.setOperatorId(upTOperator.getId()); //运营商id
            accountFlowDetail.setItemType(StudyEnrollEnum.PLATFORM_WALLET_OPERATOR.getCode()); //
            accountFlowDetail.setTradeSubject(FlowTypeConstant.ENROLL_INCOME.getCode());  //报名收入
            accountFlowDetail.setTradeSubjectItems(FlowTypeConstant.ENROLL_INCOME.getItemsMap("ENROLL_SHARE")); // 报名提成
            accountFlowDetail.setIncomeUserType(StudyEnrollEnum.INCOME_USER_TYPE_TOPERATOR.getCode()); // 收益人类型
            accountFlowDetail.setIncomeUserId(upTOperator.getId()); // 收益人id
            Boolean newAccountFlowResult = accountFlowDetailService.save(accountFlowDetail);
            if (!newAccountFlowResult) throw new BizException(500,SubResultCode.DATA_INSTALL_FAILL.subCode(),SubResultCode.DATA_INSTALL_FAILL.subMsg());
            downOperator = upTOperator;
        }
        //--------------------------------- 计算上级运营商收益 end  ---------------------------------

        //--------------------------------- 计算驾校提成 start  ---------------------------------
        //若已经升班-则驾校提成金额需要 减去之前已经结算的报名提成费用
        DriveSchoolEntity driveSchool = driveSchoolService.getById(studyEnroll.getDriveSchoolId());
        if(driveSchool != null && StatusEnum.YES.getCode().equals(driveSchool.getIsAloneSettlement()) && driveSchool.getVipEnrollPrice().doubleValue() > 0 && isSettlementSchool){

            BigDecimal vipEnrollPrice = driveSchool.getVipEnrollPrice();
            if(isSubtract){
                // isSubtract 为true，则认为是报名完成之后升班的

                vipEnrollPrice = vipEnrollPrice.subtract(driveSchool.getEnrollPrice());
            }

            if(vipEnrollPrice != null){
                accountFlowDetail.setId(IdWorker.getIdStr());
                accountFlowDetail.setItemType(StudyEnrollEnum.AFD_SCHOOL_ENROLL_COST.getCode());
                accountFlowDetail.setItemName(studyEnroll.getRealName() + "报名,驾校提成"); //项目名称
                accountFlowDetail.setItemAmount(vipEnrollPrice); //金额
                accountFlowDetail.setIncomeUserType(StudyEnrollEnum.INCOME_USER_TYPE_SCHOOL.getCode()); //收益人类型
                accountFlowDetail.setIncomeUserId(studyEnroll.getDriveSchoolId()); //收益人id(驾校)
                accountFlowDetail.setTradeSubject(FlowTypeConstant.ENROLL_INCOME.getCode());  //报名收入
                accountFlowDetail.setTradeSubjectItems(FlowTypeConstant.ENROLL_INCOME.getItemsMap("ENROLL_SHARE")); // 报名提成
                accountFlowDetail.setOperatorId(driveSchool.getOperatorId()); //运营商id
                Boolean  threeAccountFlowDetailResult = accountFlowDetailService.save(accountFlowDetail);
                if (!threeAccountFlowDetailResult)throw new BizException(500,SubResultCode.DATA_INSTALL_FAILL.subCode(),SubResultCode.DATA_INSTALL_FAILL.subMsg());
                // 最底层运营商支出
                accountFlowDetail.setId(IdWorker.getIdStr());
                accountFlowDetail.setItemType(StudyEnrollEnum.PLATFORM_WALLET_OPERATOR_PAY.getCode());
                accountFlowDetail.setItemName(studyEnroll.getRealName() + "报名," + driveSchool.getSchoolName()+"报名费"); //项目名称
                accountFlowDetail.setItemAmount(new BigDecimal(-vipEnrollPrice.floatValue())); //金额
                accountFlowDetail.setIncomeUserType(StudyEnrollEnum.INCOME_USER_TYPE_TOPERATOR.getCode()); //收益人类型 == 运营商id
                accountFlowDetail.setIncomeUserId(driveSchool.getOperatorId()); //收益人id(平台支付宝钱包用户id  )
                accountFlowDetail.setTradeSubject(FlowTypeConstant.ENROLL_PAY.getCode());  //报名支出
                accountFlowDetail.setTradeSubjectItems(FlowTypeConstant.ENROLL_PAY.getItemsMap("SCHOOL_PAY")); // 驾校提成支出
                accountFlowDetail.setOperatorId(driveSchool.getOperatorId()); //运营商id
                Boolean  fourAccountFlowDetailResult = accountFlowDetailService.save(accountFlowDetail);
                if (!fourAccountFlowDetailResult)throw new BizException(500,SubResultCode.DATA_INSTALL_FAILL.subCode(),SubResultCode.DATA_INSTALL_FAILL.subMsg());
            }
        }
        //--------------------------------- 计算驾校提成 end  ---------------------------------

        //生成优惠卷-账务流水明细
        // this.createCouponAccountFlowDetail(tStudentOrder,tAccountFlow);
        // 优惠券支付的话 执行该逻辑
        if (StrUtil.isNotEmpty(studentOrder.getColumcouponId())){
            log.info("执行优惠券逻辑");
        }
        //给钱包结算费用
        platformWalletRepository.walletSettle(studentOrder.getOrderNo());
    }


    public void createCouponAccountFlowDetail(StudentOrderEntity tStudentOrder,AccountFlowEntity tAccountFlow) throws Exception {
        if(StrUtil.isNotEmpty(tStudentOrder.getColumcouponId())) {
            // 创建运营商支出流水
            AccountFlowDetailEntity tAccountFlowDetail = new AccountFlowDetailEntity();
            tAccountFlowDetail.setOrderNo(tStudentOrder.getOrderNo());  //订单号
            tAccountFlowDetail.setAccountId(tAccountFlow.getId()); //账务流水id
            tAccountFlowDetail.setIsReconciled(StatusEnum.NO.getCode()); //是否对账
            tAccountFlowDetail.setPutForward(StatusEnum.NO.getCode()); //是否提现
            tAccountFlowDetail.setPayType(tAccountFlow.getPayType()); // 支付类型
            tAccountFlowDetail.setItemType(StudyEnrollEnum.AFD_SYSTEM_COUPON_PAY.getCode());
            tAccountFlowDetail.setItemName("运营商优惠卷支出"); //项目名称
            tAccountFlowDetail.setItemAmount(new BigDecimal(-tStudentOrder.getCouponAmount().floatValue())); //金额
            tAccountFlowDetail.setTradeSubject(FlowTypeConstant.EXTENSION_PAY.getCode());  // 市场推广支出
            tAccountFlowDetail.setTradeSubjectItems(FlowTypeConstant.EXTENSION_PAY.getItemsMap("COUPON_PAY")); // 优惠卷支出
            tAccountFlowDetail.setIncomeUserType(StudyEnrollEnum.INCOME_USER_TYPE_TOPERATOR.getCode()); //收益人类型 == 运营商id
            tAccountFlowDetail.setIncomeUserId(tStudentOrder.getOperatorId()); //收益人id(只有运营商下就能使用)
            tAccountFlowDetail.setOperatorId(tStudentOrder.getOperatorId());
            // 若是石林运营商，优惠卷费用归昆明运营商
            if("fe06a40ae1164f0298530b7659c8727c".equals(tStudentOrder.getOperatorId())){
                tAccountFlowDetail.setIncomeUserId("bbdc1bd499b241daa6fe99063e63a193"); //昆明运营商
                tAccountFlowDetail.setOperatorId("bbdc1bd499b241daa6fe99063e63a193");//昆明运营商
            }
            Boolean accountFlowDetailResult = accountFlowDetailService.save(tAccountFlowDetail);
            // 执行失败
            if (!accountFlowDetailResult) throw new BizException(500,SubResultCode.DATA_INSTALL_FAILL.subCode(),SubResultCode.DATA_INSTALL_FAILL.subMsg());
            /*GetCoupon getCoupon = tCouponService.selectMicroserviceGetCoupon(tStudentOrder.getCouponUseId());
            if(getCoupon != null && WebConstant.ACTIVITY_TYPE_CLASS_PACKAGE.equals(getCoupon.getType())){

                AccountFlowDetailEntity accountFlowDetail = new AccountFlowDetailEntity();
                accountFlowDetail.setId(IdWorker.getIdStr());
                accountFlowDetail.setOrderNo(tStudentOrder.getOrderNo());  //订单号
                accountFlowDetail.setAccountId(tAccountFlow.getId()); //账务流水id
                accountFlowDetail.setIsReconciled(StatusEnum.NO.getCode()); //是否对账
                accountFlowDetail.setPutForward(StatusEnum.NO.getCode()); //是否提现
                accountFlowDetail.setPayType(tAccountFlow.getPayType()); // 支付类型
                accountFlowDetail.setItemType(StudyEnrollEnum.AFD_SYSTEM_COUPON_PAY.getCode());
                accountFlowDetail.setItemName("优惠卷补贴收益"); //项目名称
                accountFlowDetail.setItemAmount(getCoupon.getSubsidyAmount().floatValue()); //补贴金额
                accountFlowDetail.setTradeSubject(FlowTypeConstant.EXTENSION_INCOME.getCode());  // 市场推广收入
                accountFlowDetail.setTradeSubjectItems(FlowTypeConstant.EXTENSION_INCOME.getItemsMap("EXTENSION_NEW_COUPON_SUBSIDY")); // 平台补贴运营商优惠卷支出
                accountFlowDetail.setIncomeUserType(WebConstant.INCOME_USER_TYPE_TOPERATOR); //收益人类型 == 运营商id
                accountFlowDetail.setIncomeUserId(tStudentOrder.getOperatorId()); //收益人id
                accountFlowDetail.setOperatorId(tStudentOrder.getOperatorId());
                // 若是石林运营商，优惠卷补贴给昆明运营商
                if("fe06a40ae1164f0298530b7659c8727c".equals(tStudentOrder.getOperatorId())){
                    accountFlowDetail.setIncomeUserId("bbdc1bd499b241daa6fe99063e63a193"); //收益人id
                    accountFlowDetail.setOperatorId("bbdc1bd499b241daa6fe99063e63a193");
                }
                accountFlowDetailService.save(accountFlowDetail);


                AccountFlowDetailEntity accountFlowDetailTwo = new AccountFlowDetailEntity();
                accountFlowDetailTwo.setId(IdWorker.getIdStr());
                accountFlowDetailTwo.setOrderNo(tStudentOrder.getOrderNo());  //订单号
                accountFlowDetailTwo.setAccountId(tAccountFlow.getId()); //账务流水id
                accountFlowDetailTwo.setIsReconciled(StatusEnum.NO.getCode()); //是否对账
                accountFlowDetailTwo.setPutForward(StatusEnum.NO.getCode()); //是否提现
                accountFlowDetailTwo.setPayType(tAccountFlow.getPayType()); // 支付类型
                accountFlowDetailTwo.setItemType(StudyEnrollEnum.AFD_SYSTEM_COUPON_PAY.getCode());
                accountFlowDetailTwo.setItemName("优惠卷补贴运营商-支出"); //项目名称
                accountFlowDetailTwo.setItemAmount(new BigDecimal(-getCoupon.getSubsidyAmount().floatValue())); //运营商补贴金额
                accountFlowDetailTwo.setTradeSubject(FlowTypeConstant.EXTENSION_PAY.getCode());  // 市场推广收入
                accountFlowDetailTwo.setTradeSubjectItems(FlowTypeConstant.EXTENSION_PAY.getItemsMap("EXTENSION_NEW_COUPON_SUBSIDY_PAY")); // 优惠卷补贴支出
                accountFlowDetailTwo.setIncomeUserType(StudyEnrollEnum.INCOME_USER_TYPE_TOPERATOR.getCode()); //收益人类型 == 运营商id
                accountFlowDetailTwo.setIncomeUserId(DRIVERPRINCE_OPERATORID); //收益人id(学车小王子)
                accountFlowDetailTwo.setOperatorId(DRIVERPRINCE_OPERATORID);  //学车小王子
                accountFlowDetailService.save(tAccountFlowDetail);
            }*/
        }


        /*public GetCoupon getMicroserviceGetCoupon(String couponGetId) {
            if(StringUtil.isEmpty(couponGetId)){
                return null;
            }

            Map<String,Object> params = new HashMap<String,Object>();
            params.put("id", couponGetId);  //优惠卷领取表id
            Map<String, Object> signMap = RpcSignUtil.getRpcSign(params);
            restTemplate.setErrorHandler(new CustomResponseErrorHandler());
            JSONObject result = restTemplate.postForObject(RPC_URL_DOMAIN+"/marketing/coupon/getByIdCouponGet", signMap, JSONObject.class);
            GetCoupon getCoupon = null;
            if(WebConstant.MICRO_SERVICE_SUCCESS.equals(result.getString("code"))
                    && WebConstant.MICRO_SERVICE_DATA_SUCCESS.equals(result.get("subCode"))) {
                result = result.getJSONObject("data");

                if(StringUtil.isNotEmpty(StringUtil.getString(result))){
                    JSONObject activityInfo = result.getJSONObject("activityInfo");
                    JSONObject couponEntity = result.getJSONObject("couponEntity");
                    getCoupon = new GetCoupon();
                    getCoupon.setType(activityInfo.getString("type")); //活动类型
                    getCoupon.setActivityPrice(activityInfo.getBigDecimal("activityPrice")); //折扣价格
                    getCoupon.setOriginalCost(activityInfo.getBigDecimal("originalCost")); //原价
                    getCoupon.setCouponAmount(couponEntity.getBigDecimal("amount")); // 优惠卷金额
                    if(WebConstant.ACTIVITY_TYPE_CLASS_PACKAGE.equals(getCoupon.getType())){
                        getCoupon.setProportion(ArithUtil.divDown(getCoupon.getActivityPrice(), getCoupon.getOriginalCost(), 2));
                    }
                }
            }else {
//			throw new Exception("无法获取优惠卷信息");
                return null;
            }
            return getCoupon;
        }*/
    }
}
