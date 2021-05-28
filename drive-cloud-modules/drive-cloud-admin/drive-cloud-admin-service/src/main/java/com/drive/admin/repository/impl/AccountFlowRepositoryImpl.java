package com.drive.admin.repository.impl;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.admin.enums.*;
import com.drive.admin.pojo.dto.*;
import com.drive.admin.pojo.entity.*;
import com.drive.admin.pojo.vo.AccountFlowDetailVo;
import com.drive.admin.pojo.vo.AccountFlowVo;
import com.drive.admin.repository.AccountFlowRepository;
import com.drive.admin.service.*;
import com.drive.admin.service.mapstruct.AccountFlowMapStruct;
import com.drive.common.core.base.BaseController;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.biz.SubResultCode;
import com.drive.common.core.constant.FlowTypeConstant;
import com.drive.common.core.enums.WalletEnum;
import com.drive.common.core.exception.BizException;
import com.drive.common.core.utils.ArithUtil;
import com.drive.common.core.utils.BeanConvertUtils;
import com.drive.common.data.utils.ExcelUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

                                                        
/**
 *
 * 平台账务流水 服务类
 *
 * @author xiaoguo
 */
@Slf4j
@Service
public class  AccountFlowRepositoryImpl extends BaseController<AccountFlowPageQueryParam, AccountFlowEntity>  implements AccountFlowRepository{


    @Autowired
    private PlatformWalletDetailService platformWalletDetailService;
    //  平台账务流水 服务
    @Autowired
    private AccountFlowService accountFlowService;
    //  平台账务流水 DO-DTO转化
    @Autowired
    private AccountFlowMapStruct accountFlowMapStruct;

    @Autowired
    private StudentStudyEnrollService studentStudyEnrollService;

    @Autowired
    private StudentOrderService orderService;

    @Autowired
    private OneFeeSystemCoachStudentService feeSystemCoachStudentService;

    @Autowired
    private CoachInfoService coachInfoService;

    @Autowired
    private CoachTeachTimeService coachTeachTimeService;

    @Autowired
    private OneFeeSystemVipCoachService oneFeeSystemVipCoachService;

    @Autowired
    private StudentTrainCarApplyService studentTrainCarApplyService;

    @Autowired
    private AccountFlowDetailService accountFlowDetailService;

    @Autowired
    private StudentTestEnrollService studentTestEnrollService;

    @Autowired
    private PlatformWalletService platformWalletService;

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 平台账务流水 分页列表
     * @date 2020/2/12 17:09
     * @param  * @param AccountFlowPageQueryParam
     * @return
     */
    @Override
    public ResObject pageList(AccountFlowPageQueryParam param) {
        log.info(this.getClass() + "pageList-方法请求参数{}",param);
        Page<AccountFlowEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        // 条件查询
        QueryWrapper queryWrapper = this.getQueryWrapper(accountFlowMapStruct, param);

        //  模糊查询
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueOrderNoSearch()),"order_no",param.getVagueOrderNoSearch());
        //  开始时间 结束时间都有才进入
        if (StrUtil.isNotEmpty(param.getBeginTime()) && StrUtil.isNotEmpty(param.getEndTime())){
            queryWrapper.between(StrUtil.isNotEmpty(param.getBeginTime()),"create_time",param.getBeginTime(),param.getEndTime());
        }

        // 创建时间
        if (param.getCreateArrSearch() != null && param.getCreateArrSearch().length > 0){
            queryWrapper.between("create_time",param.getCreateArrSearch()[0],param.getCreateArrSearch()[1]);
        }
        // 支付时间
        if (param.getPayTimeArrSearch() != null && param.getPayTimeArrSearch().length > 0){
            queryWrapper.between("pay_time",param.getPayTimeArrSearch()[0],param.getPayTimeArrSearch()[1]);
        }

        IPage<AccountFlowEntity> pageList = accountFlowService.page(page, queryWrapper);
        if (pageList.getRecords().size() <= 0){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }
        Page<AccountFlowVo> accountFlowVoPage = accountFlowMapStruct.toVoList(pageList);
        log.info(this.getClass() + "pageList-方法请求结果{}",accountFlowVoPage);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),accountFlowVoPage);
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 平台账务流水 查询列表
     * @date 2020/2/12 17:09
     * @param  * @param AccountFlowPageQueryParam
     * @return
     */
    @Override
    public ResObject findList(AccountFlowPageQueryParam param) {
        log.info(this.getClass() + "findList-方法请求参数{}",param);
        // 这里判断条件进行查询
        QueryWrapper queryWrapper= this.getQueryWrapper(accountFlowMapStruct, param);
        // 如 queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"phone",param.getPhone());
        List<AccountFlowEntity> accountFlowList = accountFlowService.list(queryWrapper);
        if (accountFlowList == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),accountFlowList);
        }
        List<AccountFlowVo> accountFlowVoList = accountFlowMapStruct.toVoList(accountFlowList);
        log.info(this.getClass() + "findList-方法请求结果{}",accountFlowVoList);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),accountFlowVoList);
    }

    /**
    * 对象条件查询返回单条平台账务流水数据
    * @param param
    * @return
    */
    @Override
    public ResObject getInfo(AccountFlowPageQueryParam param) {
        log.info(this.getClass() + "getInfo-方法请求参数{}",param);
        if (param == null){
            return R.failure("数据空");
        }
        // 这里判断条件进行查询
        QueryWrapper queryWrapper = this.getQueryWrapper(accountFlowMapStruct, param);
        AccountFlowEntity accountFlow = accountFlowService.getOne(queryWrapper);
        if (accountFlow == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),accountFlow);
        }
        AccountFlowVo accountFlowVo = BeanConvertUtils.copy(accountFlow, AccountFlowVo.class);
        log.info(this.getClass() + "getInfo-方法请求结果{}",accountFlowVo);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),accountFlowVo);
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 通过ID获取 平台账务流水 单条数据
     * @date 2020/2/12 17:09
     * @param  * @param AccountFlowPageQueryParam
     * @return
     */
    @Override
    public ResObject getById(String id) {
        log.info(this.getClass() + "getById-方法请求参数{}",id);
        if (StrUtil.isEmpty(id)){
            return R.failure("数据空");
        }
        // 通过ID 获取 单条数据
        AccountFlowEntity accountFlow = accountFlowService.getById(id);
        if (accountFlow == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),accountFlow);
        }
        AccountFlowVo accountFlowVo = BeanConvertUtils.copy(accountFlow, AccountFlowVo.class);
        log.info(this.getClass() + "getById-方法请求结果{}",accountFlowVo);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),accountFlowVo);
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 保存平台账务流水 数据
     * @date 2020/2/12 17:09
     * @param  * @param AccountFlowPageQueryParam
     * @return
     */
    @Override
    public ResObject save(AccountFlowInstallParam installParam) {
        log.info(this.getClass() + "save方法请求参数{}",installParam);
        if (installParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        AccountFlowEntity accountFlow = BeanConvertUtils.copy(installParam, AccountFlowEntity.class);
        Boolean result = accountFlowService.save(accountFlow);
        log.info(this.getClass() + "save-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_INSTALL_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_INSTALL_FAILL.subCode(),SubResultCode.DATA_INSTALL_FAILL.subMsg());
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description  修改平台账务流水 数据
     * @date 2020/2/12 17:09
     * @param  * @param AccountFlowPageQueryParam
     * @return
     */
    @Override
    public ResObject update(AccountFlowEditParam updateParam) {
        log.info(this.getClass() + "update方法请求参数{}",updateParam);
        if (updateParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        AccountFlowEntity accountFlow = BeanConvertUtils.copy(updateParam, AccountFlowEntity.class);
        Boolean result = accountFlowService.updateById(accountFlow);
        log.info(this.getClass() + "update-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_UPDATE_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_UPDATE_FAILL.subCode(),SubResultCode.DATA_UPDATE_FAILL.subMsg());
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 数组删除平台账务流水 数据
     * @date 2020/2/12 17:09
     * @param  * @param AccountFlowPageQueryParam
     * @return
     */
    @Override
    public ResObject deleteByIds(String[] ids) {
        if (ids.length <= 0){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        return R.toRes(accountFlowService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * *通过id删除平台账务流水 信息
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
        Boolean result = accountFlowService.removeById(id);
        log.info(this.getClass() + "deleteById-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_DELETE_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_DELETE_FAILL.subCode(),SubResultCode.DATA_DELETE_FAILL.subMsg());
    }

    /**
     * *导出平台账务流水 信息
     **/
    @Override
    public ResObject exportXls(AccountFlowPageQueryParam param, HttpServletResponse response)throws IOException {
        log.info(this.getClass() + "exportXls方法请求参数{}",param);
        QueryWrapper queryWrapper = this.getQueryWrapper(accountFlowMapStruct, param);
        List<AccountFlowEntity> list = accountFlowService.list(queryWrapper);
        List<AccountFlowVo>accountFlowList = accountFlowMapStruct.toVoList(list);
        ExcelUtils.exportExcel(accountFlowList, AccountFlowVo.class, "", new ExportParams(), response);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.EXPORT_DATA_SUCCESS.subMsg());
    }

    /**
     * *修改状态
     **/
    @Override
    public ResObject changeStatus(AccountFlowEditParam param) {
        log.info(this.getClass() + "changeStatus方法请求参数{}",param);
        if (StrUtil.isEmpty(param.getId())){
            log.error("ID数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        AccountFlowEntity AccountFlowEntity = new AccountFlowEntity();
        AccountFlowEntity.setId(param.getId());
        //AccountFlowEntity.setStatus(param.getStatus());
        //AccountFlowEntity.setUpdateTime()
        Boolean result = accountFlowService.updateById(AccountFlowEntity);
        log.info(this.getClass() +"changeStatus方法请求对象参数{}，请求结果{}",AccountFlowEntity,result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_STATUS_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_STATUS_FAILL.subCode(),SubResultCode.DATA_STATUS_FAILL.subMsg());
    }

    @Transactional
    @Override
    public ResObject<List<AccountFlowDetailVo>> createTestPassVIPCoachFlowDetail(StudentTestEnrollEditParam studentTestEnrollEditParam) {
        if (StrUtil.isEmpty(studentTestEnrollEditParam.getStudentId())) {
            //return R.failure(SubResultCode.PARAMISBLANK.subCode(), SubResultCode.PARAMISBLANK.subMsg());
            throw new BizException(500, SubResultCode.PARAMISBLANK.subCode(), "学员ID不能为空");
        }
        if (StrUtil.isEmpty(studentTestEnrollEditParam.getDriveType())) {
            // return R.failure(SubResultCode.PARAMISBLANK.subCode(), SubResultCode.PARAMISBLANK.subMsg());
            throw new BizException(500, SubResultCode.PARAMISBLANK.subCode(), SubResultCode.PARAMISBLANK.subMsg());
        }

        List<AccountFlowDetailVo> returnList = new ArrayList<>();

        //获取学员学车报名单
        QueryWrapper queryWrapper = new QueryWrapper();
        // 学员
        queryWrapper.eq("student_id", studentTestEnrollEditParam.getStudentId());
        // 驾照类型
        queryWrapper.eq("drive_type", studentTestEnrollEditParam.getDriveType());
        // 报名完成
        queryWrapper.eq("enroll_status", StudyEnrollEnum.ENROLL_STATUS_ENROLL_COMPLETE.getCode());
        StudentStudyEnrollEntity studentStudyEnroll = studentStudyEnrollService.getOne(queryWrapper);
        if (studentStudyEnroll == null) {
            // return R.failure(SubResultCode.DATA_NULL.subCode(), "报名单不存在");
            throw new BizException(500, SubResultCode.PARAMISBLANK.subCode(), "报名单不存在");
        }

        //判断报名单是否为
        if (EnrollStatusEnum.DECIDE_CLASS.getCode().equals(studentStudyEnroll.getEnrollType())) {
            // return R.success(SubResultCode.DATA_IDEMPOTENT.subCode(), "自主模式,不可操作");
            throw new BizException(500, SubResultCode.DATA_NULL.subCode(),"自主模式,不可操作");
        }

        QueryWrapper orderQueryWrapper = new QueryWrapper();
        // 报名单号查找
        orderQueryWrapper.eq("study_enroll_no", studentStudyEnroll.getStudyEnrollNo());
        StudentOrderEntity studentOrder = orderService.getOne(orderQueryWrapper);
        if (studentOrder == null) {
            //R.failure(SubResultCode.DATA_NULL.subCode(), "订单信息不存在");
            throw new BizException(500, SubResultCode.DATA_NULL.subCode(),"订单信息不存在");
        }


        // 判断是否参加包过模式（一费制学员教练关联表，状态为已支付,已绑定）
        QueryWrapper feeSystemCoachStudentQueryWrapper = new QueryWrapper();
        // 订单号
        feeSystemCoachStudentQueryWrapper.eq("order_no", studentOrder.getOrderNo());
        // 绑定状态（已绑定）
        feeSystemCoachStudentQueryWrapper.eq("bind_status", OperatorEnum.BINDING.getCode());
        // 支付状态（支付成）
        feeSystemCoachStudentQueryWrapper.eq("pay_status", OperatorEnum.YET_PAY_SUCCESS.getCode());
        feeSystemCoachStudentQueryWrapper.eq("drive_type", OperatorEnum.C1.getCode());
        OneFeeSystemCoachStudentEntity feeSystemCoachStudent = feeSystemCoachStudentService.getOne(feeSystemCoachStudentQueryWrapper);
        if (feeSystemCoachStudent == null) {
            //return R.failure(SubResultCode.DATA_NULL.subCode(), "没有绑定教练，无法操作");
            throw new BizException(500, SubResultCode.DATA_NULL.subCode(),"没有绑定教练，无法操作");
        }
        QueryWrapper accountFlowQueryWrapper = new QueryWrapper();
        // 订单号
        accountFlowQueryWrapper.eq("order_no", studentOrder.getOrderNo());
        // 支付流水
        accountFlowQueryWrapper.eq("flow_type", OperatorEnum.FLOW_TYPE_PAY.getCode());
        AccountFlowEntity accountFlow = accountFlowService.getOne(accountFlowQueryWrapper);
        if (accountFlow == null) {
            //return R.failure(SubResultCode.DATA_NULL.subCode(), "没有流水，无法操作");
            throw new BizException(500, SubResultCode.DATA_INSTALL_FAILL.subCode(),"没有流水，无法操作");
        }
        // 查询教练
        CoachInfoEntity coachInfo = coachInfoService.getById(feeSystemCoachStudent.getCoachId());
        if (coachInfo != null && StatusEnum.NORMAL.getCode().equals(coachInfo.getStatus())) {
            // 生成教练科目二/三 包过模式一费制收入 流水明细
            BigDecimal coachIncome = SubjectTypeEnum.SUBJECT_TWO.getCode().equals(studentTestEnrollEditParam.getSubjectType()) ? feeSystemCoachStudent.getCoachSubjectType2() : feeSystemCoachStudent.getCoachSubjectType3();
            //教练剩余未结算金额
            BigDecimal coachSurplusIncome = new BigDecimal(0);
            //驾校剩余未结算金额
            BigDecimal carSurplusIncome = new BigDecimal(0);
            QueryWrapper coachTeachTimeQueryWrapper = new QueryWrapper();
            //------------------------------- 判断是否存在未完成的课时 start -------------------------------
            // 教练ID
            coachTeachTimeQueryWrapper.eq("coach_id", coachInfo.getId());
            // 学员ID
            coachTeachTimeQueryWrapper.eq("student_id", feeSystemCoachStudent.getStudentId());
            // 驾照类型
            coachTeachTimeQueryWrapper.eq("drive_type", feeSystemCoachStudent.getDriveType());
            // 科目类型
            coachTeachTimeQueryWrapper.eq("subject_type", studentTestEnrollEditParam.getSubjectType());
            //课程状态(2-已预约,3-教学中,6-接人中,7-已上车)
            String[] arr = {
                    StudyEnrollEnum.YET_APPOINTMENT.getCode(),
                    StudyEnrollEnum.TEACHING_LOADING.getCode(),
                    StudyEnrollEnum.PICK_SOMEBODY_UP.getCode(),
                    StudyEnrollEnum.YET_GET_ON.getCode(),
            };
            coachTeachTimeQueryWrapper.in("status", arr);
            int isCoachTeachTime = coachTeachTimeService.count(coachTeachTimeQueryWrapper);
            if (isCoachTeachTime > 0) {
                throw new BizException(500, SubResultCode.DATA_INSTALL_FAILL.subCode(), "教练存在未完成课时，请联系教练先完成课时");
            }
            //------------------------------- 判断是否存在未完成的课时 end -------------------------------


            //------------------------------- 计算教练剩余未结算收益 start -------------------------------
            /**
             * 1.教练收入 = （科二/三教练提成总价格 * 科二/三教练提成百分比） - 教练实际已经收入的预收入金额 ；
             * 2.驾校收入 = （科二/三教练提成总价格 * 科二/三驾校提成百分比）- 驾校实际已经收入的预收入金额 ；
             */
            QueryWrapper oneFeeSystemVipCoachQueryWrapper = new QueryWrapper();
            // 教练ID
            oneFeeSystemVipCoachQueryWrapper.eq("coach_id", coachInfo.getId());
            //状态
            oneFeeSystemVipCoachQueryWrapper.eq("stutas", StatusEnum.ENABLE.getCode());
            OneFeeSystemVipCoachEntity oneFeeSystemVipCoach = oneFeeSystemVipCoachService.getOne(oneFeeSystemVipCoachQueryWrapper);
            if (oneFeeSystemVipCoach != null) {
                //教练提成百分比(保留4位小数，防止用户设置的值为 55.55)
                BigDecimal coachChargePercent = ArithUtil.divDown(oneFeeSystemVipCoach.getCoachChargePercent(), new BigDecimal(100), 4);
                //教练理论收入总金额 精确的乘法运算
                BigDecimal coachSumIncome = ArithUtil.mulDown(coachIncome, coachChargePercent, 2);
                //教练实际历史收入总金额
                CoachTeachTimePageQueryParam coachTeachTimePageQueryParam = new CoachTeachTimePageQueryParam();
                //						params.put("coachId", tCoachInfo.getId()); //教练id
                // 学员ID
                coachTeachTimePageQueryParam.setStudentId(studentTestEnrollEditParam.getStudentId());
                // 科目类型
                coachTeachTimePageQueryParam.setSubjectType(studentTestEnrollEditParam.getSubjectType());
                // 驾照类型
                coachTeachTimePageQueryParam.setDriveType(studentTestEnrollEditParam.getDriveType());
                // 是否为预收入
                coachTeachTimePageQueryParam.setIsExpect(StatusEnum.ENABLE.getCode());
                BigDecimal expectCoachIncomeSum = coachTeachTimeService.selectExpectCoachIncomeSum(coachTeachTimePageQueryParam);
                expectCoachIncomeSum = expectCoachIncomeSum == null ? new BigDecimal(0) : expectCoachIncomeSum;
                //教练剩余应结算金额（理论收入总金额 -实际历史收入总金额）
                coachSurplusIncome = ArithUtil.sub(coachSumIncome, expectCoachIncomeSum);
                //------------------------------- 计算教练剩余未结算收益 end -------------------------------


                //------------------------------- 计算公车剩余未结算收益 start -------------------------------
                /**
                 * 驾校收入 = （科二/三教练提成总价格 * 科二/三驾校提成百分比）- 驾校实际已经收入的预收入金额 ；
                 */
                if (oneFeeSystemVipCoach.getCarChargePercent().compareTo(BigDecimal.ZERO) > 0) {
                    //公车提成百分比(保留4位小数，防止用户设置的值为 55.55)
                    BigDecimal carChargePercent = ArithUtil.divDown(oneFeeSystemVipCoach.getCarChargePercent(), new BigDecimal(100), 4);
                    //驾校理论收入总金额
                    BigDecimal carSumIncome = ArithUtil.mulDown(coachIncome, carChargePercent, 2);
                    //驾校实际历史收入总金额
                    CoachTeachTimePageQueryParam coachTeachTimeParam = new CoachTeachTimePageQueryParam();
                    // 教练ID
                    coachTeachTimeParam.setCoachId(coachInfo.getId());
                    // 学员ID
                    coachTeachTimeParam.setStudentId(studentTestEnrollEditParam.getStudentId());
                    // 科目类型
                    coachTeachTimeParam.setSubjectType(studentTestEnrollEditParam.getSubjectType());
                    // 驾照类型
                    coachTeachTimeParam.setDriveType(studentTestEnrollEditParam.getDriveType());
                    // 是否为预收入
                    coachTeachTimeParam.setIsExpect(StatusEnum.ENABLE.getCode());
                    BigDecimal expectCarIncomeSum = coachTeachTimeService.selectExpectSchoolIncomeSum(coachTeachTimeParam);
                    expectCarIncomeSum = expectCarIncomeSum == null ? new BigDecimal(0) : expectCarIncomeSum;
                    //教练剩余应结算金额（理论收入总金额 -实际历史收入总金额）
                    carSurplusIncome = ArithUtil.sub(carSumIncome, expectCarIncomeSum);
                }
                //------------------------------- 计算公车剩余未结算收益 start -------------------------------
            }

            //------------------------------- 创建教练账务流水明细 start -------------------------------
            //判断该 vip教练，是否带过该学员的课，若带过至少一节费用归教练，若没有带过则费用归学车小王子
            StudentTrainCarApplyPageQueryParam studentTrainCarApplyPageQueryParam = new StudentTrainCarApplyPageQueryParam();
            // 学员OD
            studentTrainCarApplyPageQueryParam.setStudentId(studentTestEnrollEditParam.getStudentId());
            // 教练ID
            studentTrainCarApplyPageQueryParam.setCoachId(coachInfo.getId());
            // 学员ID
            // 科目类型
            studentTrainCarApplyPageQueryParam.setSubjectType(studentTestEnrollEditParam.getSubjectType());
            // 查询课时
            int teachingNumber = studentTrainCarApplyService.selectVipTeachingNumber(studentTrainCarApplyPageQueryParam);

            AccountFlowDetailEntity accountFlowDetail = new AccountFlowDetailEntity();
            // //订单号
            accountFlowDetail.setOrderNo(studentOrder.getOrderNo());
            ////账务流水id
            accountFlowDetail.setAccountId(accountFlow.getId());
            // //是否对账
            accountFlowDetail.setIsReconciled(StatusEnum.NOTDELETE.getCode());
            // // 支付类型
            accountFlowDetail.setPayType(accountFlow.getPayType());
            accountFlowDetail.setItemType(OperatorEnum.AFD_COACH_TIEM_ROYALTY_COST.getCode());
            // //项目名称
            accountFlowDetail.setItemName(studentStudyEnroll.getRealName() + "-" + SubjectTypeEnum.getSubjectValueByCode(studentTestEnrollEditParam.getSubjectType()) + "-VIP课时费提成");
            //教练提成
            accountFlowDetail.setItemAmount(coachSurplusIncome);
            // 课时收益
            accountFlowDetail.setTradeSubject(FlowTypeConstant.CLASS_TIME_INCOME.getCode());
            // 课时费提成
            accountFlowDetail.setTradeSubjectItems(FlowTypeConstant.CLASS_TIME_INCOME.getItemsMap("CLASS_TIME_SHARE"));
            //运营商id
            accountFlowDetail.setOperatorId(coachInfo.getOperatorId());
            if (teachingNumber > 0) {
                //收益人类型(教练)
                accountFlowDetail.setIncomeUserType(OperatorEnum.INCOME_USER_TYPE_COACH.getCode());
                //收益人id
                accountFlowDetail.setIncomeUserId(coachInfo.getId());
            } else {
                //收益人类型(运营商)
                accountFlowDetail.setIncomeUserType(OperatorEnum.INCOME_USER_TYPE_TOPERATOR.getCode());
                accountFlowDetail.setIncomeUserId(OperatorEnum.DRIVERPRINCE_OPERATORID.getCode()); //收益人id （学车小王子）
            }


            //判断账务流水是否已经生成
            QueryWrapper accountFlowDetailQueryWrapper = new QueryWrapper();
            // 订单号
            accountFlowDetailQueryWrapper.eq("order_no", accountFlow.getOrderNo());
            // 支付类型
            accountFlowDetailQueryWrapper.eq("pay_type", accountFlow.getPayType());
            accountFlowDetailQueryWrapper.eq("item_amount", accountFlowDetail.getItemAmount());
            accountFlowDetailQueryWrapper.eq("income_user_id", accountFlowDetail.getIncomeUserId());
            int detailCount = accountFlowDetailService.count(accountFlowDetailQueryWrapper);
            if (detailCount <= 0) {
                accountFlowDetailService.save(accountFlowDetail);
                returnList.add(BeanConvertUtils.copy(accountFlowDetail, AccountFlowDetailVo.class));
            }
            //------------------------------- 创建教练账务流水明细 end ---------------------------------


            //------------------------------- 创建教驾校账务流水明细 start -------------------------------
            //剩余未结算金额大于 0
            if (carSurplusIncome.compareTo(BigDecimal.ZERO) > 0) {
                AccountFlowDetailEntity drivingSchoolAccountFlowDetail = new AccountFlowDetailEntity();
                drivingSchoolAccountFlowDetail.setOrderNo(studentOrder.getOrderNo());  //订单号
                drivingSchoolAccountFlowDetail.setAccountId(accountFlow.getId()); //账务流水id
                drivingSchoolAccountFlowDetail.setIsReconciled(StatusEnum.NOTDELETE.getCode()); //是否对账
                drivingSchoolAccountFlowDetail.setPayType(accountFlow.getPayType());  // 支付类型
                drivingSchoolAccountFlowDetail.setItemType(OperatorEnum.PUBLIC_CAR_ROYALTY_COST.getCode());
                drivingSchoolAccountFlowDetail.setItemName(studentStudyEnroll.getRealName() + "-" + SubjectTypeEnum.getSubjectValueByCode(studentTestEnrollEditParam.getSubjectType()) + "-VIP课时费提成"); //项目名称
                drivingSchoolAccountFlowDetail.setItemAmount(carSurplusIncome); //教练提成
                drivingSchoolAccountFlowDetail.setTradeSubject(FlowTypeConstant.CLASS_TIME_INCOME.getCode());  // 课时收益
                drivingSchoolAccountFlowDetail.setTradeSubjectItems(FlowTypeConstant.CLASS_TIME_INCOME.getItemsMap("CLASS_TIME_SHARE")); // 课时费提成
                drivingSchoolAccountFlowDetail.setOperatorId(coachInfo.getOperatorId()); //运营商id

                if (teachingNumber > 0) {
                    drivingSchoolAccountFlowDetail.setIncomeUserType(OperatorEnum.INCOME_USER_TYPE_SCHOOL.getCode()); //收益人类型(运驾校)
                    drivingSchoolAccountFlowDetail.setIncomeUserId(coachInfo.getCarSchoolId()); //收益人id （驾校）
                } else {
                    drivingSchoolAccountFlowDetail.setIncomeUserType(OperatorEnum.INCOME_USER_TYPE_TOPERATOR.getCode()); //收益人类型(运营商)
                    drivingSchoolAccountFlowDetail.setIncomeUserId(OperatorEnum.DRIVERPRINCE_OPERATORID.getCode()); //收益人id （学车小王子）
                }

                //判断账务流水是否已经生成
                QueryWrapper isAccountFlowDetail = new QueryWrapper();
                // 订单号
                isAccountFlowDetail.eq("order_no", accountFlow.getOrderNo());
                // 支付类型
                isAccountFlowDetail.eq("pay_type", accountFlow.getPayType());

                isAccountFlowDetail.eq("item_amount", accountFlowDetail.getItemAmount());
                isAccountFlowDetail.eq("income_user_id", accountFlowDetail.getIncomeUserId());
                detailCount = accountFlowDetailService.count(isAccountFlowDetail);
                if (detailCount <= 0) {
                    accountFlowDetailService.save(accountFlowDetail);
                    returnList.add(BeanConvertUtils.copy(accountFlowDetail, AccountFlowDetailVo.class));
                }
            }
            //------------------------------- 创建教驾校账务流水明细 end ---------------------------------


            // --------------------------------------------------------- 计算奖金 start ------------------------------------------------------------
            /**
             *  1.奖金计算，科目二，科目三考试都已经通过，才计算奖金
             *  教练收入奖金  = 奖金总额 - (考试挂科次数  * 每次挂科扣款金额)
             */
            QueryWrapper studentTestEnrollQueryWrapper = new QueryWrapper();
            // 学员ID
            studentTestEnrollQueryWrapper.eq("student_id", studentTestEnrollEditParam.getStudentId());
            // 驾照类型
            studentTestEnrollQueryWrapper.eq("drive_type", studentTestEnrollEditParam.getDriveType());
            // 科目类型
            studentTestEnrollQueryWrapper.eq("enroll_status", ExamEnrollEnum.EXAM_PASS.getCode());
            studentTestEnrollQueryWrapper.eq("subject_type", studentTestEnrollEditParam.getSubjectType().equals(SubjectTypeEnum.SUBJECT_TWO.getCode()) ? SubjectTypeEnum.SUBJECT_THREE.getCode() : SubjectTypeEnum.SUBJECT_TWO.getCode());
            // 查看考试报名单
            int testPassCount = studentTestEnrollService.count(studentTestEnrollQueryWrapper);
            // 考试结果 科目2 科目三都通过才进行结算奖金操作
            if (testPassCount <= 0){
                return R.success(returnList);
            }
            QueryWrapper studentTestEnrollNotQueryWrapper = new QueryWrapper();
            studentTestEnrollNotQueryWrapper.apply("exam_type  != 1");
            studentTestEnrollNotQueryWrapper.in("subject_type", SubjectTypeEnum.SUBJECT_TWO.getCode(), SubjectTypeEnum.SUBJECT_THREE.getCode());
            studentTestEnrollNotQueryWrapper.eq("enroll_status", ExamEnrollEnum.EXAM_NO_PASS.getCode());
            studentTestEnrollNotQueryWrapper.eq("student_id", studentTestEnrollEditParam.getStudentId());
            // 查询挂科购买VIP 之后,挂科次数
            int count = studentTestEnrollService.count(studentTestEnrollNotQueryWrapper);
            //考试挂科扣款金额
            BigDecimal testFailWithholdMoney = ArithUtil.mulDown(new BigDecimal(String.valueOf(count)), new BigDecimal(String.valueOf(feeSystemCoachStudent.getTestNotPassWithholdMoney())), 2);
            //剩余奖金  = 班型总奖金  - 扣除奖金
            BigDecimal surplusBonus = ArithUtil.sub(new BigDecimal(String.valueOf(feeSystemCoachStudent.getBonus())), testFailWithholdMoney);

            if (oneFeeSystemVipCoach != null && surplusBonus.floatValue() > 0) {
                //--------------------------------------  计算教练奖金 start  -----------------
                //教练提成百分比(保留4位小数，防止用户设置的值为 55.55)
                BigDecimal coachChargePercent = ArithUtil.divDown(oneFeeSystemVipCoach.getCoachChargePercent(), new BigDecimal(100), 4);
                //教练奖金  = 剩余奖金  * 教练提成比例
                BigDecimal coachBonus = ArithUtil.mulDown(surplusBonus, coachChargePercent, 2);

                AccountFlowDetailEntity coachBonusTafd = new AccountFlowDetailEntity();
                coachBonusTafd.setOrderNo(feeSystemCoachStudent.getOrderNo());  //订单号
                coachBonusTafd.setAccountId(accountFlow.getId()); //账务流水id
                coachBonusTafd.setIsReconciled(StatusEnum.NOTDELETE.getCode()); //是否对账
                coachBonusTafd.setPayType(accountFlow.getPayType());  // 支付类型
                coachBonusTafd.setItemType(OperatorEnum.AFD_COACH_TIEM_ROYALTY_COST.getCode());
                coachBonusTafd.setItemName(studentStudyEnroll.getRealName() + "-VIP课时奖金"); //项目名称
                coachBonusTafd.setItemAmount(coachBonus); // 教练最终奖金
                coachBonusTafd.setTradeSubject(FlowTypeConstant.CLASS_TIME_INCOME.getCode());  // 课时收益
                coachBonusTafd.setTradeSubjectItems(FlowTypeConstant.CLASS_TIME_INCOME.getItemsMap("CLASS_BONUS")); // VIP课时奖金
                coachBonusTafd.setOperatorId(coachInfo.getOperatorId()); //运营商id
                coachBonusTafd.setIncomeUserType(OperatorEnum.INCOME_USER_TYPE_COACH.getCode()); //收益人类型(教练)
                coachBonusTafd.setIncomeUserId(feeSystemCoachStudent.getCoachId()); //收益人id

                //判断账务流水是否已经生成
                QueryWrapper accountQueryWrapper = new QueryWrapper();
                // 订单号
                accountQueryWrapper.eq("order_no", accountFlow.getOrderNo());
                //支付类型
                accountQueryWrapper.eq("pay_type", accountFlow.getPayType());
                accountQueryWrapper.eq("item_amount", coachBonusTafd.getItemAmount());
                accountQueryWrapper.eq("income_user_id", coachBonusTafd.getIncomeUserId());
                accountQueryWrapper.eq("trade_subject_items", FlowTypeConstant.CLASS_TIME_INCOME.getItemsMap("CLASS_BONUS"));
                detailCount = accountFlowDetailService.count(accountQueryWrapper);
                if (detailCount <= 0) {
                    accountFlowDetailService.save(coachBonusTafd);
                    returnList.add(BeanConvertUtils.copy(coachBonusTafd, AccountFlowDetailVo.class));
                }
                //--------------------------------------  计算教练奖金 end   -----------------


                //--------------------------------------  计算驾校奖金  start -----------------
                //驾校奖金  = 剩余奖金  - 教练奖金
                BigDecimal schoolBonus = ArithUtil.sub(surplusBonus, coachBonus);
                AccountFlowDetailEntity coachBonusTafdEntity = new AccountFlowDetailEntity();
                coachBonusTafdEntity.setOrderNo(feeSystemCoachStudent.getOrderNo());  //订单号
                coachBonusTafdEntity.setAccountId(accountFlow.getId()); //账务流水id
                coachBonusTafdEntity.setIsReconciled(StatusEnum.NOTDELETE.getCode()); //是否对账
                coachBonusTafdEntity.setPayType(accountFlow.getPayType());  // 支付类型
                coachBonusTafdEntity.setItemType(OperatorEnum.AFD_COACH_TIEM_ROYALTY_COST.getCode());
                coachBonusTafdEntity.setItemName(studentStudyEnroll.getRealName() + "-VIP课时奖金"); //项目名称
                coachBonusTafdEntity.setItemAmount(schoolBonus); // 驾校最终奖金
                coachBonusTafdEntity.setTradeSubject(FlowTypeConstant.CLASS_TIME_INCOME.getCode());  // 课时收益
                coachBonusTafdEntity.setTradeSubjectItems(FlowTypeConstant.CLASS_TIME_INCOME.getItemsMap("CLASS_BONUS")); // VIP课时奖金
                coachBonusTafdEntity.setOperatorId(coachInfo.getOperatorId()); //运营商id
                coachBonusTafdEntity.setIncomeUserType(OperatorEnum.INCOME_USER_TYPE_SCHOOL.getCode()); //收益人类型(教练)
                coachBonusTafdEntity.setIncomeUserId(coachInfo.getCarSchoolId()); //收益人id

                //判断账务流水是否已经生成
                QueryWrapper AccountFlowQueryWrapper = new QueryWrapper();
                // 订单好
                AccountFlowQueryWrapper.eq("order_no", accountFlow.getOrderNo());
                AccountFlowQueryWrapper.eq("pay_type", accountFlow.getPayType());
                AccountFlowQueryWrapper.eq("item_amount", coachBonusTafd.getItemAmount());
                AccountFlowQueryWrapper.eq("income_user_id", coachBonusTafd.getIncomeUserId());
                AccountFlowQueryWrapper.eq("trade_subject_items", FlowTypeConstant.CLASS_TIME_INCOME.getItemsMap("CLASS_BONUS"));
                detailCount = accountFlowDetailService.count(AccountFlowQueryWrapper);
                if (detailCount <= 0) {
                    accountFlowDetailService.save(coachBonusTafd);
                    returnList.add(BeanConvertUtils.copy(coachBonusTafd, AccountFlowDetailVo.class));
                }
                //--------------------------------------  计算驾校奖金  end -----------------
            }

            // ----------------------------------------------------- 计算奖金 end -----------------------------------------------------------


            // ------------------------------------ 被扣除的奖金（归给教练所属运营商） start ------------------------------------------
//						BigDecimal withholdBonus = ArithUtil.sub(ofscs.getBonus().floatValue(),testFailWithholdMoney);

            AccountFlowDetailEntity withholdBonusTafd = new AccountFlowDetailEntity();
            withholdBonusTafd.setOrderNo(feeSystemCoachStudent.getOrderNo());  //订单号
            withholdBonusTafd.setAccountId(accountFlow.getId()); //账务流水id
            withholdBonusTafd.setIsReconciled(StatusEnum.NOTDELETE.getCode()); //是否对账
            withholdBonusTafd.setPayType(accountFlow.getPayType());  // 支付类型
            withholdBonusTafd.setItemType(OperatorEnum.AFD_COACH_TIEM_ROYALTY_COST.getCode());
            withholdBonusTafd.setTradeSubject(FlowTypeConstant.CLASS_TIME_INCOME.getCode());  // 课时收益
            withholdBonusTafd.setOperatorId(coachInfo.getOperatorId()); //运营商id
            withholdBonusTafd.setIncomeUserType(OperatorEnum.INCOME_USER_TYPE_COACH.getCode()); //收益人类型(教练)
            withholdBonusTafd.setItemName(studentStudyEnroll.getRealName() + "-" + SubjectTypeEnum.getSubjectValueByCode(studentTestEnrollEditParam.getSubjectType()) + "-VIP挂科扣除奖金"); //项目名称
            withholdBonusTafd.setItemAmount(testFailWithholdMoney); // VIP包过奖金扣款提成
            withholdBonusTafd.setIncomeUserId(coachInfo.getOperatorId()); //收益人id (运营商)
            withholdBonusTafd.setTradeSubjectItems(FlowTypeConstant.CLASS_TIME_INCOME.getItemsMap("CLASS_WITHHOLD_BONUS")); // VIP包过奖金扣款提成

            //判断账务流水是否已经生成
            QueryWrapper newAccountFlowQueryWrapper = new QueryWrapper();
            newAccountFlowQueryWrapper.eq("order_no", accountFlow.getOrderNo());
            newAccountFlowQueryWrapper.eq("pay_type", accountFlow.getPayType());
            newAccountFlowQueryWrapper.eq("item_amount", withholdBonusTafd.getItemAmount());
            newAccountFlowQueryWrapper.eq("income_user_id", withholdBonusTafd.getIncomeUserId());
            newAccountFlowQueryWrapper.eq("trade_subject_items", FlowTypeConstant.CLASS_TIME_INCOME.getItemsMap("CLASS_WITHHOLD_BONUS"));
            detailCount = accountFlowDetailService.count(newAccountFlowQueryWrapper);
            if (detailCount <= 0) {
                accountFlowDetailService.save(withholdBonusTafd);
                returnList.add(BeanConvertUtils.copy(withholdBonusTafd, AccountFlowDetailVo.class));
            }
            // --------------------------------------------------- 被扣除的奖金（归给教练所属运营商） end ---------------------------------------------------------

        }
        return R.success(returnList);
    }

    @Override
    public ResObject settlementByFlowDetailList(List<AccountFlowDetailEditParam> accountFlowDetailEditParams) {
        if(accountFlowDetailEditParams.size() <=0){
            return R.failure(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }

        accountFlowDetailEditParams.stream().forEach((item) ->{
            //判断是否已经结算（防止重复结算）
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("account_detail_id",item.getId());
            int count = platformWalletDetailService.count(queryWrapper);
            if(count <= 0){
                try {
                    this.settlementToWallet(item);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return R.success();
    }

    /**
     * 结算到钱包，公共方法
     * @throws Exception
     */
    @Transactional
    public void  settlementToWallet(AccountFlowDetailEditParam accountFlowDetailEditParam) throws Exception{
        //结算相关用户的钱包金额，以及记录相关流水
        QueryWrapper queryWrapper = new QueryWrapper();
        // 用户ID
        queryWrapper.eq("user_id",accountFlowDetailEditParam.getIncomeUserId());
        PlatformWalletEntity platformWallet = platformWalletService.getOne(queryWrapper);
        platformWallet.setWalletAmount(platformWallet.getWalletAmount().subtract(accountFlowDetailEditParam.getItemAmount()));
        Boolean res = platformWalletService.updateById(platformWallet);
        if (!res){
            throw new BizException(500,SubResultCode.DATA_INSTALL_FAILL.subCode(),SubResultCode.DATA_INSTALL_FAILL.subMsg());
        }
        //记录钱包流水明细
        PlatformWalletDetailEntity platformWalletDetail = new PlatformWalletDetailEntity();
        platformWalletDetail.setUserId(platformWallet.getUserId());
        platformWalletDetail.setTradeAmount(accountFlowDetailEditParam.getItemAmount());
        platformWalletDetail.setTradeSubject(accountFlowDetailEditParam.getTradeSubject());
        platformWalletDetail.setTradeSubjectItems(accountFlowDetailEditParam.getTradeSubjectItems());
        if(accountFlowDetailEditParam.getItemAmount().doubleValue() < 0){
            platformWalletDetail.setTradeType(WalletEnum.DRIVER_WALLET_SUBMIT_CASH.getCode()); //负数（支出）
        }else{
            platformWalletDetail.setTradeType(WalletEnum.DRIVER_WALLET_INCOME.getCode()); //正数（收益）
        }
        platformWalletDetail.setWalletDetailName(accountFlowDetailEditParam.getItemName());
        platformWalletDetail.setCreateTime(LocalDateTime.now());
        platformWalletDetail.setAccountDetailId(accountFlowDetailEditParam.getId());
        platformWalletDetail.setBalance(platformWallet.getWalletAmount()); //账户余额
        platformWalletDetail.setOperatorId(platformWallet.getOperatorId());  // 运营商
        platformWalletDetailService.save(platformWalletDetail);

    }
}

