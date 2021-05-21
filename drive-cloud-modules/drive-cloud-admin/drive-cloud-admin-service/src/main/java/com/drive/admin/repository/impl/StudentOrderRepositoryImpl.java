package com.drive.admin.repository.impl;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.admin.enums.OperationTypeEnum;
import com.drive.admin.enums.StudyEnrollEnum;
import com.drive.admin.pojo.dto.StudentOrderEditParam;
import com.drive.admin.pojo.dto.StudentOrderPageQueryParam;
import com.drive.admin.pojo.entity.*;
import com.drive.admin.pojo.vo.StudentOrderVo;
import com.drive.admin.pojo.vo.StudentStudyEnrollVo;
import com.drive.admin.repository.StudentOrderRepository;
import com.drive.admin.service.*;
import com.drive.admin.service.mapstruct.StudentOrderMapStruct;
import com.drive.admin.util.SignUtils;
import com.drive.admin.util.XmlUtil;
import com.drive.common.core.base.BaseController;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.biz.SubResultCode;
import com.drive.common.core.exception.BizException;
import com.drive.common.core.utils.ArithUtil;
import com.drive.common.core.utils.BeanConvertUtils;
import com.drive.common.core.utils.GsonUtil;
import com.drive.common.core.utils.StringUtils;
import com.drive.common.data.utils.ExcelUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.net.ssl.SSLContext;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.*;
import java.security.cert.CertificateException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.alipay.api.AlipayConstants.CHARSET;


/**
 *
 * 学员订单表 服务类
 *
 * @author xiaoguo
 */
@Slf4j
@Service
public class  StudentOrderRepositoryImpl extends BaseController<StudentOrderPageQueryParam, StudentOrderEntity>  implements StudentOrderRepository{

    //  学员订单表 服务
    @Autowired
    private StudentOrderService studentOrderService;
    //  学员订单表 DO-DTO转化
    @Autowired
    private StudentOrderMapStruct studentOrderMapStruct;

    @Autowired
    private StudentInfoService studentInfoService;

    @Autowired
    private StudentStudyEnrollService studentStudyEnrollService;

    @Autowired
    private AccountFlowService accountFlowService;

    @Autowired
    private PayFlowLogService payFlowLogService;

    @Autowired
    private PlatformWalletDetailService platformWalletDetailService;

    @Autowired
    private PlatformWalletService platformWalletService;

    @Autowired
    private AccountFlowDetailService accountFlowDetailService;

    @Autowired
    private OneFeeSystemCoachStudentService oneFeeSystemCoachStudentService;

    @Autowired
    private StudentTestEnrollService studentTestEnrollService;

    @Autowired
    private StudentTrainCarApplyService studentTrainCarApplyService;

    @Autowired
    private CoachTeachTimeService coachTeachTimeService;
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
     * @author xiaoguo
     * @description 学员订单表 分页列表
     * @date 2020/2/12 17:09
     * @param  * @param StudentOrderPageQueryParam
     * @return
     */
    @Override
    public ResObject pageList(StudentOrderPageQueryParam param) {
        log.info(this.getClass() + "pageList-方法请求参数{}",param);
        Page<StudentOrderEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        // 条件查询
        QueryWrapper queryWrapper = this.getQueryWrapper(studentOrderMapStruct, param);

        // 报名单号 模糊查询
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueStudyEnrollNoSearch()),"study_enroll_no",param.getVagueStudyEnrollNoSearch());
        // 订单号模糊查询
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueOrderNoSearch()),"order_no",param.getVagueOrderNoSearch());
        // 考试报名单号模糊查询
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueTestEnrollNoSearch()),"test_enroll_no",param.getVagueTestEnrollNoSearch());
        // 预约单号模糊查询
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueTrainApplyNoSearch()),"train_apply_no",param.getVagueTrainApplyNoSearch());
        // 支付时间查询
        queryWrapper.apply(StrUtil.isNotEmpty(param.getPayTimeSearch()),
                "date_format (pay_time,'%Y-%m-%d') = date_format('" + param.getPayTimeSearch() + "','%Y-%m-%d')");

        //  开始时间 结束时间都有才进入
        if (StrUtil.isNotEmpty(param.getBeginTime()) && StrUtil.isNotEmpty(param.getEndTime())){
            queryWrapper.between(StrUtil.isNotEmpty(param.getBeginTime()),"create_time",param.getBeginTime(),param.getEndTime());
        }
        IPage<StudentOrderEntity> pageList = studentOrderService.page(page, queryWrapper);
        if (pageList.getRecords().size() <= 0){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }
        Page<StudentOrderVo> studentOrderVoPage = studentOrderMapStruct.toVoList(pageList);
        studentOrderVoPage.getRecords().stream().forEach((item) ->{
            if (StrUtil.isNotEmpty(item.getStudyEnrollNo())){
                QueryWrapper studentStudyQueryWrapper = new QueryWrapper();
                studentStudyQueryWrapper.eq("study_enroll_no",item.getStudyEnrollNo());
                studentStudyQueryWrapper.last("limit 1");
                Optional.ofNullable(studentStudyEnrollService.getOne(studentStudyQueryWrapper)).ifPresent(u ->{item.setStudentName(u.getRealName());});
            }
        });
        log.info(this.getClass() + "pageList-方法请求结果{}",studentOrderVoPage);
        return R.success(studentOrderVoPage);
    }

    @Override
    public ResObject findList(StudentOrderPageQueryParam param) {
        log.info(this.getClass() + "findList-方法请求参数{}",param);
        // 这里判断条件进行查询
        QueryWrapper queryWrapper= this.getQueryWrapper(studentOrderMapStruct, param);
        // 如 queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"phone",param.getPhone());
        List<StudentOrderEntity> studentOrderList = studentOrderService.list(queryWrapper);
        if (studentOrderList == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        List<StudentOrderVo> studentOrderVoList = studentOrderMapStruct.toVoList(studentOrderList);
        log.info(this.getClass() + "findList-方法请求结果{}",studentOrderVoList);
        return R.success(studentOrderVoList);
    }



    @Override
    public ResObject getInfo(StudentOrderPageQueryParam param) {
        return null;
    }

    /**
     * *通过ID获取学员订单表 列表
     **/
    @Override
    public ResObject getById(String orderNo) {
        log.info(this.getClass() + "getInfo-方法请求参数{}",orderNo);
        if (StrUtil.isEmpty(orderNo)){
            return R.failure("数据空");
        }
        StudentOrderEntity studentOrder = studentOrderService.getById(orderNo);
        if (studentOrder == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        StudentOrderVo studentOrderVo = BeanConvertUtils.copy(studentOrder, StudentOrderVo.class);
        log.info(this.getClass() + "getInfo-方法请求结果{}",studentOrderVo);
        return R.success(studentOrderVo);
    }

    /**
     * *保存学员订单表 信息
     **/
    @Override
    public ResObject save(StudentOrderEditParam installParam) {
        log.info(this.getClass() + "save方法请求参数{}",installParam);
        if (installParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        StudentOrderEntity studentOrder = BeanConvertUtils.copy(installParam, StudentOrderEntity.class);
        Boolean result = studentOrderService.save(studentOrder);
        log.info(this.getClass() + "save-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *修改学员订单表 信息
     **/
    @Override
    public ResObject update(StudentOrderEditParam updateParam) {
        log.info(this.getClass() + "update方法请求参数{}",updateParam);
        if (updateParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        StudentOrderEntity studentOrder = BeanConvertUtils.copy(updateParam, StudentOrderEntity.class);
        Boolean result = studentOrderService.updateById(studentOrder);
        log.info(this.getClass() + "update-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *删除学员订单表 信息
     **/
    @Override
    public ResObject deleteByIds(String[] ids) {
        if (ids.length <= 0){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        return R.toRes(studentOrderService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * *通过id删除学员订单表 信息
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
        Boolean result = studentOrderService.removeById(id);
        log.info(this.getClass() + "deleteById-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *导出学员订单表 信息
     **/
    @Override
    public ResObject exportXls(StudentOrderPageQueryParam param, HttpServletResponse response)throws IOException {
        log.info(this.getClass() + "exportXls方法请求参数{}",param);
        QueryWrapper queryWrapper = this.getQueryWrapper(studentOrderMapStruct, param);
        List<StudentOrderEntity> list = studentOrderService.list(queryWrapper);
        List<StudentOrderVo>studentOrderList = studentOrderMapStruct.toVoList(list);
        ExcelUtils.exportExcel(studentOrderList, StudentOrderVo.class, "", new ExportParams(), response);
        return R.success("导出成功");
    }

    /**
     * *修改状态
     **/
    @Override
    public ResObject changeStatus(StudentOrderEditParam param) {
        log.info(this.getClass() + "changeStatus方法请求参数{}",param);
        if (StrUtil.isEmpty(param.getOrderNo())){
            log.error("ID数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        StudentOrderEntity StudentOrderEntity = new StudentOrderEntity();
        StudentOrderEntity.setOrderNo(param.getOrderNo());
        StudentOrderEntity.setStatus(param.getStatus());
        //StudentOrderEntity.setUpdateTime()
        Boolean result = studentOrderService.updateById(StudentOrderEntity);
        log.info(this.getClass() +"changeStatus方法请求对象参数{}，请求结果{}",StudentOrderEntity,result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    @Override
    public ResObject getOrderByStudentId(String studentId) {
        log.info(this.getClass()+ "getOrderByStudentId-方法请求参数",studentId);
        if (StrUtil.isEmpty(studentId)){
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        // 条件查询
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("t2.student_id",studentId);
        // 状态值
      /* List<String> arr = new ArrayList<String>();
        arr.add(StudyEnrollEnum.STAT_PAY.getCode());
        arr.add(StudyEnrollEnum.PAY_WAIT_PUT.getCode());
        arr.add(StudyEnrollEnum.ENROLL_SUCCESS.getCode());
        arr.add(StudyEnrollEnum.AUTO_ENROLL_SUCCESS.getCode());
        arr.add(StudyEnrollEnum.PUT_WAIT_AUDIT.getCode());
        arr.add(StudyEnrollEnum.PASSWORD_SUBMIT_WAIT_AUDIT.getCode());*/
        queryWrapper.eq("t1.status",StudyEnrollEnum.STAT_PAY.getCode());
        List<StudentStudyEnrollVo> studentOrderVos= studentStudyEnrollService.studyEnrollList(queryWrapper);
        if (studentOrderVos.size() <=0){
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }

        // .collect(Collectors.toList())
        //List<StudentStudyEnrollVo> studentOrderVo= studentOrderVos.stream().filter(order -> arr.contains(order.getOrderStatus())).collect(Collectors.toList());
        StudentStudyEnrollVo studyEnrollVo = new StudentStudyEnrollVo();
      /*  if (studentOrderVo.size() <=0){
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),studentOrderVo);
        }*/
        QueryWrapper queryWrapperCount = new QueryWrapper();
        queryWrapperCount.eq("student_id",studentId);
        queryWrapperCount.eq("status", StudyEnrollEnum.CANCEL_ORDER.getCode());
        int cancelNum= studentOrderService.count(queryWrapperCount);
        //studentOrderVos.stream().mapToDouble(StudentStudyEnrollVo::getOrderStatus).sum()
        // 取消次数
        //IntSummaryStatistics sumcc = studentOrderVos.stream().collect(Collectors.summarizingInt(e->Integer.valueOf(String.valueOf(e.getOrderStatus()=="5"))));
        // 取消订单次数
        studyEnrollVo.setCancelNum(cancelNum);
        // 这里只可能出现一条数据 应该多个状态筛选后
        studyEnrollVo.setStudentOrderNo(studentOrderVos.get(0).getStudentOrderNo());
        // 订单时间
        studyEnrollVo.setOrderTime(studentOrderVos.get(0).getCreateTime());
        return R.success(studyEnrollVo);
    }

    @Transactional
    @Override
    public ResObject cancelOrder(StudentOrderEditParam studentOrderEditParam) {
        log.info(this.getClass()+"cancelOrder-方法请求参数");
        if (studentOrderEditParam == null){
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        // 通过订单查询订单信息
        StudentOrderEntity studentOrder  =studentOrderService.getById(studentOrderEditParam.getOrderNo());
        if (studentOrder == null){
            return R.success(SubResultCode.NOT_ORDER_OPERATION.subCode(),SubResultCode.NOT_ORDER_OPERATION.subMsg(),false);
        }
        //订单状态判断
    /*    if (!(studentOrder.getStatus().equals(StudyEnrollEnum.STAT_PAY.getCode()))){
            return R.success(SubResultCode.ORDER_STATUS_NOT_OPERATION.subCode(),SubResultCode.ORDER_STATUS_NOT_OPERATION.subMsg(),false);
        }*/
        // 取消时间
        studentOrder.setUpdateTime(LocalDateTime.now());
        studentOrder.setStatus(StudyEnrollEnum.CANCEL_ORDER.getCode());
        Boolean result = studentOrderService.updateById(studentOrder);
        if (!result){
            // 操作失败
            throw new BizException(500,SubResultCode.DATA_INSTALL_FAILL.subCode(),SubResultCode.DATA_INSTALL_FAILL.subMsg());
        }
        // 查询报名单
        //QueryWrapper queryWrapper = new QueryWrapper();
        StudentStudyEnrollEntity studentStudyEnroll = studentStudyEnrollService.getById(studentOrder.getStudyEnrollNo());
        if (studentStudyEnroll == null){
            throw new BizException(500,SubResultCode.OPERATION_ERROR.subCode(),SubResultCode.OPERATION_ERROR.subMsg(),false);
        }
        //
        studentStudyEnroll.setOperationType(OperationTypeEnum.BACK_SERVICE.getCode());
        studentStudyEnroll.setCancelReason("后台操作取消");
        studentStudyEnroll.setCancelTime(LocalDateTime.now());
        studentStudyEnroll.setEnrollStatus(StudyEnrollEnum.ENROLL_STATUS_CANCEL.getCode());
        Boolean studyResult = studentStudyEnrollService.updateById(studentStudyEnroll);
        if (!studyResult){
            // 操作失败
            throw new BizException(500,SubResultCode.DATA_INSTALL_FAILL.subCode(),SubResultCode.DATA_INSTALL_FAILL.subMsg());
        }
        return R.success("操作成功");
    }

    @Transactional
    @Override
    public ResObject orderRefund(StudentOrderEditParam studentOrderEditParam) throws Exception {
        log.info(this.getClass() + "orderRefund-方法请求参数{}",studentOrderEditParam);
        // 查询对应的订单信息
        StudentOrderEntity studentOrder = studentOrderService.getById(studentOrderEditParam.getOrderNo());
        if (studentOrder == null){
            return R.failure(SubResultCode.DATA_NULL.subCode(),"系统没有查询到对应订单");
        }
        // 退款订单号
        String outRequestNo = StringUtils.generateSequenceNo();
        // 目前订单状态
        String orderStatus =studentOrder.getStatus();
        //支付成功才可退款
        if(!(StudyEnrollEnum.PAY_SUCCESS.getCode().equals(studentOrder.getStatus()))){
            return R.failure("该订单不可退款");
        }

        //判断是否已经生成退款流水
        QueryWrapper refundQueryWrapper = new QueryWrapper();
        refundQueryWrapper.eq("order_no",studentOrder.getOrderNo());
        refundQueryWrapper.eq("flow_type",StudyEnrollEnum.FLOW_TYPE_REFUND.getCode());
        int count = accountFlowService.count(refundQueryWrapper);
        if(count > 0){
            return R.success(SubResultCode.ORDER_YET_REFUND.subCode(),SubResultCode.ORDER_YET_REFUND.subMsg());
        }
        //学车报名单退款，已升班 的需先退上一笔订单
        if(studentOrder.getOrderType().equals(StudyEnrollEnum.ORDER_TYPE_STUDY_ENROLL.getCode())){
            StudentStudyEnrollEntity studentStudyEnroll = studentStudyEnrollService.getById(studentOrder.getStudyEnrollNo());
            if(studentStudyEnroll == null){
                return R.failure("该订单的明细单不存在,请联系管理员");
            }
            if(studentStudyEnroll.getEnrollStatus().equals(StudyEnrollEnum.YET_ENROLL_UPGRADE_CLASS.getCode())){
                return R.failure("已升班订单不可取消");
            }
        }

        //查询支付成功的交易流水
        QueryWrapper payFlowQueryWrapper = new QueryWrapper();
        // 订单号
        payFlowQueryWrapper.eq("order_no",studentOrder.getOrderNo());
        // 支付流水
        payFlowQueryWrapper.eq("tran_type",StudyEnrollEnum.TRADE_FLOW_STATUS_PAY.getCode());
        // 支付类型（微信）
        payFlowQueryWrapper.eq("pay_type",studentOrder.getPayType());
        payFlowQueryWrapper.orderByDesc("create_time");
        payFlowQueryWrapper.last("limit 1");
        // 支付流水
        PayFlowLogEntity payFlowLog = payFlowLogService.getOne(payFlowQueryWrapper);
        if(!studentOrder.getPayType().equals(StudyEnrollEnum.PAY_TYPE_VIP.getCode())){
            this.createRefundAccountFlow(studentOrder,outRequestNo); //生成退款流水
        }
        //更新订单状态 orderStatus记录目前订单的状态 如果升班才能还原状态
        this.updateOrderStutas(studentOrder,orderStatus);
        ResObject returnVal = new ResObject();
        // 支付宝
        if (studentOrder.getPayType().equals(StudyEnrollEnum.PAY_TYPE_ALI.getCode())){
            return this.aliRefund(studentOrder,outRequestNo,payFlowLog);
        }
        // 微信
        if (studentOrder.getPayType().equals(StudyEnrollEnum.PAY_TYPE_WECHAT.getCode())){
            return this.weChatRefund(StudyEnrollEnum.PAY_TYPE_WECHAT.getCode(),outRequestNo,studentOrder,payFlowLog);
        }
        // 微信公众号
        if (studentOrder.getPayType().equals(StudyEnrollEnum.PAY_TYPE_WECHAT_PUBLIC.getCode())){
            return this.weChatRefund(StudyEnrollEnum.PAY_TYPE_WECHAT.getCode(),outRequestNo,studentOrder,payFlowLog);
        }
        // 优惠券支付
        if (studentOrder.getPayType().equals(StudyEnrollEnum.PAY_TYPE_COUPON.getCode())){
            studentOrder.setStatus(StudyEnrollEnum.ORDER_TYPE_REFUND_SUCCESS.getCode()); //退款成功
            studentOrderService.updateById(studentOrder);
            // 退还优惠卷
            //tCouponService.updateCouponNotUse(tStudentOrder);
            return R.success(StudyEnrollEnum.PAY_TYPE_COUPON.getCode());
        }
        /*switch (studentOrder.getPayType()) {
            case StudyEnrollEnum.PAY_TYPE_ALI.getCode():
                returnVal = this.aliRefund(studentOrder,outRequestNo,payFlowLog);
                break;

            case StudyEnrollEnum.PAY_TYPE_WECHAT.getCode():
                returnVal = this.weChatRefund(StudyEnrollEnum.PAY_TYPE_WECHAT.getCode(),outRequestNo,studentOrder,payFlowLog);
                break;

            case StudyEnrollEnum.PAY_TYPE_WECHAT_PUBLIC.getCode():
                returnVal = this.weChatRefund(StudyEnrollEnum.PAY_TYPE_WECHAT_PUBLIC.getCode(),outRequestNo,studentOrder,payFlowLog);
                break;

            case StudyEnrollEnum.PAY_TYPE_COUPON.getCode():
                studentOrder.setStatus(StudyEnrollEnum.ORDER_TYPE_REFUND_SUCCESS.getCode()); //退款成功
                studentOrderService.updateById(studentOrder);
                // 退还优惠卷
                //tCouponService.updateCouponNotUse(tStudentOrder);
                break;


            case StudyEnrollEnum.PAY_TYPE_VIP.getCode():
                //订单取消
                studentOrder.setStatus(StudyEnrollEnum.CANCEL_ORDER.getCode());
                studentOrderService.updateById(studentOrder);
                break;

            default:
                returnVal.setCode(500);
                returnVal.setMsg("该订单支付类型不存在，无法退款");
                break;
        }*/
        return  R.success(returnVal);
    }


    /**
     * 生成退款流水
     * @throws Exception
     */
    private  void createRefundAccountFlow(StudentOrderEntity studentOrderEntity,String outRequestNo) throws BizException {
        // 异步处理
        new Thread(() ->{
//还原上一笔（升班过程是，新订单存老订单）
            StudentOrderEntity originalStudentOrder = studentOrderService.getById(studentOrderEntity.getNewOrderNo());
            if(originalStudentOrder != null){
                originalStudentOrder.setStatus(studentOrderEntity.getStatus());
           /* Boolean originalRes = studentOrderService.updateById(originalStudentOrder);
            log.info("原订单更新结果{}",originalRes);*/
            }

            //更新订单状态
            studentOrderEntity.setStatus(StudyEnrollEnum.REFUND_LOADING.getCode());//退款处理中
            studentOrderEntity.setAliOutRefundNo(outRequestNo); //支付宝，退款请求号
            studentOrderService.updateById(studentOrderEntity);

            //生成退款交易流水
            PayFlowLogEntity payFlowLogDo = new PayFlowLogEntity();
            //订单号
            payFlowLogDo.setOrderNo(studentOrderEntity.getOrderNo());
            //流水类型(退款流水)
            payFlowLogDo.setTranType(StudyEnrollEnum.FLOW_TYPE_REFUND.getCode());
            //支付类型
            payFlowLogDo.setPayType(studentOrderEntity.getPayType());
            //订单应付金额
            payFlowLogDo.setPayableAmount(studentOrderEntity.getPayableAmount());
            //状态（退款处理中）
            payFlowLogDo.setStatus(StudyEnrollEnum.FLOW_TYPE_REFUND_IN_HAND.getCode());
            payFlowLogDo.setThirdOrderNo(outRequestNo); //第三方订单号
            payFlowLogDo.setCreateTime(LocalDateTime.now()); //创建时间
            //payFlowLogDo.setCommitParameter("");//提交参数
            payFlowLogDo.setOperatorId(studentOrderEntity.getOperatorId());
            Boolean payFlowRes = payFlowLogService.save(payFlowLogDo);
            if (!payFlowRes){
                throw new BizException(500,SubResultCode.DATA_INSTALL_FAILL.subCode(),SubResultCode.DATA_INSTALL_FAILL.subMsg());
            }
            QueryWrapper accountFlowQueryWrapper = new QueryWrapper();
            // 订单号
            accountFlowQueryWrapper.eq("order_no",studentOrderEntity.getOrderNo());
            // 生成账务流水-退款流水
            AccountFlowEntity accountFlowDo = accountFlowService.getOne(accountFlowQueryWrapper);
            accountFlowDo.setId(IdWorker.getIdStr());
            //流水类型（退款流水）
            accountFlowDo.setFlowType(StudyEnrollEnum.FLOW_TYPE_REFUND_IN_HAND.getCode());
            //退款类型（全额退款）
            accountFlowDo.setRefundType(StudyEnrollEnum.REFUND_TYPE_WHOLE.getCode());
            accountFlowDo.setRefundTime(LocalDateTime.now());  //退款时间
            Boolean accountFlowRes = accountFlowService.save(accountFlowDo);
            if (!accountFlowRes){
                throw new BizException(500,SubResultCode.DATA_INSTALL_FAILL.subCode(),SubResultCode.DATA_INSTALL_FAILL.subMsg());
            }
            // 查询所有支付流水明细（生成退款流水明细）
            QueryWrapper newAccountFlowQueryWrapper = new QueryWrapper();
            newAccountFlowQueryWrapper.eq("order_no", studentOrderEntity.getOrderNo());
            List<AccountFlowDetailEntity> accountFlowDetailList = accountFlowDetailService.list(newAccountFlowQueryWrapper);
            if (accountFlowDetailList.isEmpty()){
                //throw new BizException(500,SubResultCode.DATA_NULL.subCode(),"没有流水");
                return ;
            }
            accountFlowDetailList.stream().forEach((item) ->{
                //若费用已经结算到钱包，则需要扣除
                QueryWrapper platformWalletDetailQueryWrapper = new QueryWrapper();
                platformWalletDetailQueryWrapper.eq("account_detail_id", item.getId());
                int count = platformWalletDetailService.count(platformWalletDetailQueryWrapper);
                item.setId(IdWorker.getIdStr());
                item.setAccountId(accountFlowDo.getId());
                item.setItemName(item.getItemName() + "-订单取消");
                item.setItemAmount(item.getItemAmount().negate());
                item.setCreateTime(LocalDateTime.now());
                Boolean res = accountFlowDetailService.save(item);
                if(count > 0){
                    this.settlementToWallet(item);
                }
            });
        }).start();
    }

    /**
     * 结算到钱包，公共方法
     * @throws Exception
     */
    private void  settlementToWallet(AccountFlowDetailEntity accountFlowDetail) throws BizException{
        // 异步处理
        new Thread(() ->{
            //结算相关用户的钱包金额，以及记录相关流水
            QueryWrapper accountFlowDetailQueryWrapper = new QueryWrapper();
            // 用户DI
            accountFlowDetailQueryWrapper.eq("user_id", accountFlowDetail.getIncomeUserId());
            PlatformWalletEntity platformWalletDo = platformWalletService.getOne(accountFlowDetailQueryWrapper);
            if (platformWalletDo == null){
                throw new BizException(500,SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
            }
            platformWalletDo.setWalletAmount(platformWalletDo.getWalletAmount().subtract(accountFlowDetail.getItemAmount()));
            Boolean platformWalletRes = platformWalletService.updateById(platformWalletDo);
            log.info("更新钱包结果{}",platformWalletRes);
            if (!platformWalletRes) {
                throw new BizException(500,SubResultCode.DATA_INSTALL_FAILL.subCode(),SubResultCode.DATA_INSTALL_FAILL.subMsg());
            }
            //记录钱包流水明细
            PlatformWalletDetailEntity platformWalletDetailDo = new PlatformWalletDetailEntity();
            platformWalletDetailDo.setUserId(platformWalletDo.getUserId());
            platformWalletDetailDo.setTradeAmount(accountFlowDetail.getItemAmount());
            platformWalletDetailDo.setTradeSubject(accountFlowDetail.getTradeSubject());
            platformWalletDetailDo.setTradeSubjectItems(accountFlowDetail.getTradeSubjectItems());
            if(accountFlowDetail.getItemAmount() == null){
                //负数（支出）
                platformWalletDetailDo.setTradeType(StudyEnrollEnum.DRIVER_WALLET_SUBMIT_CASH.getCode());
            }
            //正数（收益）
            platformWalletDetailDo.setTradeType(StudyEnrollEnum.DRIVER_WALLET_INCOME.getCode());
            platformWalletDetailDo.setWalletDetailName(accountFlowDetail.getItemName());
            platformWalletDetailDo.setCreateTime(LocalDateTime.now());
            platformWalletDetailDo.setAccountDetailId(accountFlowDetail.getId());
            platformWalletDetailDo.setBalance(platformWalletDo.getWalletAmount()); //账户余额
            platformWalletDetailDo.setOperatorId(platformWalletDo.getOperatorId());  // 运营商
            platformWalletDetailDo.setDataMsValue(System.currentTimeMillis());
            platformWalletDetailService.save(platformWalletDetailDo);
        }).start();
    }


    /**
     * 更新订单状态
     * @throws Exception
     */
    @Transactional
     void  updateOrderStutas(StudentOrderEntity studentOrderDo,String nowOrderStatus) throws BizException {
        new Thread(() ->{
//学车报名
            if (studentOrderDo.getOrderType().equals(StudyEnrollEnum.ORDER_TYPE_STUDY_ENROLL.getCode())) {
                if (studentOrderDo.getOrderType().equals(StudyEnrollEnum.ORDER_TYPE_STUDY_ENROLL.getCode())) {

                    StudentStudyEnrollEntity studentStudyEnrollDo = studentStudyEnrollService.getById(studentOrderDo.getStudyEnrollNo());
                    Optional.ofNullable(studentStudyEnrollDo).orElseThrow(() -> new BizException(500, SubResultCode.DATA_NULL.subCode(), "报名单不存在"));
                    String oldStudyEnrollStatus = studentStudyEnrollDo.getEnrollStatus();
                    //退款处理中
                    studentStudyEnrollDo.setEnrollStatus(StudyEnrollEnum.ENROLL_STATUS_REFUND.getCode());
                    Boolean studentStudyEnrollRes = studentStudyEnrollService.updateById(studentStudyEnrollDo);
                    if (!studentStudyEnrollRes) {
                        throw new BizException(500, SubResultCode.DATA_UPDATE_FAILL.subCode(), SubResultCode.DATA_UPDATE_FAILL.subMsg());
                    }

                    //逻辑删除一费制逻辑绑定关系
                    QueryWrapper coachStudentQueryWrapper = new QueryWrapper();
                    coachStudentQueryWrapper.eq("order_no", studentOrderDo.getOrderNo());
                    Boolean coachStudentRes = oneFeeSystemCoachStudentService.remove(coachStudentQueryWrapper);
                    log.info("删除结果{}", coachStudentRes);
                    //还原---老订单状态
                    StudentOrderEntity originalOrder = studentOrderService.getById(studentOrderDo.getNewOrderNo());
                    if (originalOrder != null) {
                        originalOrder.setStatus(nowOrderStatus);
                        Boolean originalOrderRes = studentOrderService.updateById(originalOrder);
                        if (!originalOrderRes) {
                            throw new BizException(500, SubResultCode.DATA_UPDATE_FAILL.subCode(), SubResultCode.DATA_UPDATE_FAILL.subMsg());
                        }
                        //还原--老学车报名单号状态
                        StudentStudyEnrollEntity originalStudyEnroll = studentStudyEnrollService.getById(originalOrder.getStudyEnrollNo());
                        originalStudyEnroll.setEnrollStatus(oldStudyEnrollStatus);
                        Boolean originalStudyEnrollRes = studentStudyEnrollService.updateById(originalStudyEnroll);
                        if (!originalStudyEnrollRes) {
                            throw new BizException(500, SubResultCode.DATA_UPDATE_FAILL.subCode(), "报名单更新异常");
                        }
                    }

                }
                //  考试报名
                else if (studentOrderDo.getOrderType().equals(StudyEnrollEnum.ORDER_TYPE_TEST_ENROLL.getCode())) {
                    //更新考试报名单状态
                    //考试报名单
                    StudentTestEnrollEntity studentTestEnrollDo = studentTestEnrollService.getById(studentOrderDo.getTestEnrollNo());
                    Optional.ofNullable(studentTestEnrollDo).orElseThrow(() -> new BizException(500, SubResultCode.DATA_NULL.subCode(), "考试单不存在"));
                    //退款处理中
                    studentTestEnrollDo.setEnrollStatus(StudyEnrollEnum.TEST_ENROLL_REFUND_SUCCESS.getCode());
                    Boolean testEnrollRes = studentTestEnrollService.updateById(studentTestEnrollDo);
                    if (!testEnrollRes) {
                        throw new BizException(500, SubResultCode.DATA_UPDATE_FAILL.subCode(), "报名单更新异常");
                    }

                }

                // 常规训练 或者 考试训练
                else if (studentOrderDo.getOrderType().equals(StudyEnrollEnum.ORDER_TYPE_COMMONLY_TRAIN.getCode()) || studentOrderDo.getOrderType().equals(StudyEnrollEnum.ORDER_TYPE_TEST_TRAIN.getCode())) {
                    // 查询预约订单
                    StudentTrainCarApplyEntity studentTrainCarApplyDo = studentTrainCarApplyService.getById(studentOrderDo.getTrainApplyNo());
                    Optional.ofNullable(studentTrainCarApplyDo).orElseThrow(() -> new BizException(500, SubResultCode.DATA_NULL.subCode(), "常规训练预约单不存在"));
                    //预约取消
                    studentTrainCarApplyDo.setApplyStatus(StudyEnrollEnum.APPLY_STATUS_CANCEL.getCode());
                    //取消时间
                    studentTrainCarApplyDo.setCancelTiem(LocalDateTime.now());
                    //取消原因
                    studentTrainCarApplyDo.setCancelReason("后台管理员手动取消");
                    //退款金额
                    studentTrainCarApplyDo.setCancelRefundMoney(studentOrderDo.getPayableAmount());
                    Boolean trainCarApplyRes = studentTrainCarApplyService.updateById(studentTrainCarApplyDo);
                    if (!trainCarApplyRes) {
                        throw new BizException(500, SubResultCode.DATA_UPDATE_FAILL.subCode(), "常规训练预约单更新异常");
                    }
                    // 查询课时
                    CoachTeachTimeEntity coachTeachTimeDo = coachTeachTimeService.getById(studentTrainCarApplyDo.getClassId());
                    Optional.ofNullable(coachTeachTimeDo).orElseThrow(() -> new BizException(500, SubResultCode.DATA_NULL.subCode(), "课时不存在"));
                    //课程剩余课时
                    int surplusClassHours = coachTeachTimeDo.getSurplusClassHours();
                    //普通训练
                    if (coachTeachTimeDo.getClassType().equals((StudyEnrollEnum.CLASS_TYPE_COMMONLY_TRAIN.getCode()))) {
                        //修改课程状态为未预约
                        //未预约
                        coachTeachTimeDo.setStatus(StudyEnrollEnum.NO_APPOINTMENT.getCode());
                        /** 原代码 有问题
                         * tCoachTeachTime.setStatus(WebConstant.CLASS_STATUS_NOT_BESPOKE); //未预约
                         .setStatus(WebConstant.CLASS_STATUS_CANCEL);
                         */
                        coachTeachTimeDo.setStatus(StudyEnrollEnum.CLASS_STATUS_CANCEL.getCode());
                        coachTeachTimeDo.setRemarks("系统管理人员发起退款");
                        coachTeachTimeDo.setSurplusClassHours(0);
                        coachTeachTimeDo.setStudentId("");
                        Boolean coachTeachTimeRes = coachTeachTimeService.updateById(coachTeachTimeDo);  //更新课时状态
                        if (!coachTeachTimeRes) {
                            throw new BizException(500, SubResultCode.DATA_UPDATE_FAILL.subCode(), "常规训练预约单更新异常");
                        }
                    } else {
                        //获取当前取消订单关联的教练课程信息
                        //如果已被预约的课时数等于该笔订单取消的课时数跟新课程状态为未预约
                        if ((coachTeachTimeDo.getClassHours() - surplusClassHours - studentTrainCarApplyDo.getClassHours()) == 0) {
                            //修改课程状态为未预约
                            //已取消
                            coachTeachTimeDo.setStatus(StudyEnrollEnum.CLASS_STATUS_CANCEL.getCode());
                            coachTeachTimeDo.setSurplusClassHours(0);
                            //课时总价这个数字用途在哪里确认好，不要就删除
                            coachTeachTimeDo.setSumCharge(new BigDecimal(0));
                            //取消后平台服务费没了，教练收入也没了
                            coachTeachTimeDo.setServiceCharge(new BigDecimal(0));
                            coachTeachTimeDo.setCoaceCharge(new BigDecimal(0));

                            //取消订单的课时数不等于已被预约的课时数，说明还有别的人已预约，此时只更新剩余课时数(取消的课时数加回去);扣除课时中的教练收入,课时总费用,平台收入
                        } else {
                            coachTeachTimeDo.setSurplusClassHours(surplusClassHours + studentTrainCarApplyDo.getClassHours());
                            //课时总价这个数字用途在哪里确认好，不要就删除
                            BigDecimal sumCharge = coachTeachTimeDo.getSumCharge().subtract(studentTrainCarApplyDo.getSumPrice().multiply(new BigDecimal(studentTrainCarApplyDo.getClassHours())));
                            coachTeachTimeDo.setSumCharge(sumCharge);
                            coachTeachTimeDo.setServiceCharge(coachTeachTimeDo.getServiceCharge().subtract(studentTrainCarApplyDo.getServiceCharge()));
                            coachTeachTimeDo.setCoaceCharge(coachTeachTimeDo.getCoaceCharge().subtract(studentTrainCarApplyDo.getCoachCharge()));
                        }
                        Boolean res = coachTeachTimeService.updateById(coachTeachTimeDo);  //更新课时状态
                        if (!res) {
                            throw new BizException(500, SubResultCode.DATA_UPDATE_FAILL.subCode(), "常规训练预约单更新异常");
                        }
                    }
                }
            }
        }).start();
    }

    /**
     * 支付宝退款方法
     * @throws Exception
     */
    @Transactional
      ResObject aliRefund(StudentOrderEntity studentOrderEntity,String outRequestNo,PayFlowLogEntity payFlowLogDo) throws BizException, AlipayApiException {

        //调用支付宝退款
        Map<String,Object> params = new HashMap<String,Object>();
        //平台订单号
        params.put("out_trade_no", studentOrderEntity.getOrderNo());
        //退款金额（应付金额）
        params.put("refund_amount", studentOrderEntity.getPayableAmount());
        params.put("out_request_no", outRequestNo); //退款请求号(退款查询必填项)

        JSONObject content = BeanConvertUtils.mapToObject(params,JSONObject.class);
        java.security.Security.addProvider(
                new org.bouncycastle.jce.provider.BouncyCastleProvider()
        );

        //调用退款接口
        AlipayClient alipayClient = new DefaultAlipayClient(aliGateway,aliAppId,aliPrivateKey,"json","UTF-8",aliPublicKey,"RSA2");

        AlipayTradeRefundRequest aliRequest = new AlipayTradeRefundRequest();
        aliRequest.setBizContent(JSONObject.toJSONString(content));

        AlipayTradeRefundResponse aliResponse = alipayClient.execute(aliRequest);
        log.info("请求数据结构{}",aliResponse);
        if(aliResponse.isSuccess()){
            String bodyData = aliResponse.getBody();
            Map<String,String> data =  GsonUtil.toMap(bodyData);
            log.info("支付宝退款数据{}",data);
            String sign = data.get("sign");
            String alipay_trade_refund_response = data.get("alipay_trade_refund_response");
            boolean falg = AlipaySignature.rsaCheck(alipay_trade_refund_response, sign, aliPublicKey, "UTF-8", "RSA2");
            if(falg) {
                Map<String,String> refundData = GsonUtil.toMap(alipay_trade_refund_response);
                //平台订单号
                String out_trade_no = refundData.get("out_trade_no");
                //退款总金额
                BigDecimal refundFee = new BigDecimal(refundData.get("refund_fee"));
                //退款时间
                String gmtRefundPay = refundData.get("gmt_refund_pay");
                DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime newGmtRefundPay = LocalDateTime.parse(gmtRefundPay,df);
                //校验订单号
                if(studentOrderEntity.getOrderNo().equals(out_trade_no)){
                    //校验金额  bd1.compareTo(bd2);
                    BigDecimal refundAmount = refundFee.subtract(studentOrderEntity.getPayableAmount());
                    // 判断是否等于0
                    //int isAmount = refundAmount.compareTo(new BigDecimal(0));
                    //if(isAmount == -1) {
//						//更新交易流水状态
                        return this.refundSuccess(JSONObject.toJSONString(content),bodyData,refundFee, newGmtRefundPay,payFlowLogDo,studentOrderEntity);
                    //}
                }
            }else{
                throw new BizException("退款异常");
            }
        } else {
            throw  new BizException("退款接口调用异常:"+aliResponse.getSubMsg());
        }
        return R.failure();
    }

    /**
     *  退款成功，统一处理方法
     * @param submitParams  提交第三方参数
     * @param returnParam   第三方返回参数
     * @param refund_fee    第三方订单金额
     * @param gmt_refund_pay 第三方退款时间
     * @throws Exception
     */
    @Transactional
    ResObject refundSuccess(String submitParams,String returnParam,BigDecimal refundFee,LocalDateTime gmt_refund_pay,PayFlowLogEntity payFlowLogDo,StudentOrderEntity studentOrderDo) throws BizException{
        //更新交易流水状态
        payFlowLogDo.setThirdReturnTime(gmt_refund_pay); //第三方处理时间
        //状态（退款处理中）
        payFlowLogDo.setStatus(StudyEnrollEnum.FLOW_TYPE_REFUND_SUCCESS.getCode());
        //提交参数
        payFlowLogDo.setCommitParameter(submitParams);
        //返回参数
        payFlowLogDo.setReturnParameter(returnParam);
        //第三方订单金额
        payFlowLogDo.setThirdOrderAmount(refundFee);
        Boolean payFlowRes = payFlowLogService.updateById(payFlowLogDo);
        if (!payFlowRes){
            throw new BizException(500,SubResultCode.DATA_UPDATE_FAILL.subCode(),"更新支付流水日志报错");
        }
        //更新订单订单状态
        //退款成功
        studentOrderDo.setStatus(StudyEnrollEnum.ORDER_TYPE_REFUND_SUCCESS.getCode());
        Boolean orderRes = studentOrderService.updateById(studentOrderDo);
        if (!orderRes){
            throw new BizException(500,SubResultCode.DATA_UPDATE_FAILL.subCode(),"更新订单报错");
        }
        QueryWrapper accountFlowQueryWrapper = new QueryWrapper();
        // 订单号
        accountFlowQueryWrapper.eq("order_no",studentOrderDo.getOrderNo());
        // 退款流水
        accountFlowQueryWrapper.eq("flow_type",StudyEnrollEnum.TRADE_FLOW_REFUND.getCode());
        accountFlowQueryWrapper.eq("pay_type",studentOrderDo.getPayType());
        // 生成账务流水-退款流水
        AccountFlowEntity accountFlowDo = accountFlowService.getOne(accountFlowQueryWrapper);
        //更新账务流水状态
        accountFlowDo.setRefundTime(gmt_refund_pay);  //退款时间
        Boolean accountFlowRes = accountFlowService.updateById(accountFlowDo);
        if (!accountFlowRes){
            throw new BizException(500,SubResultCode.DATA_UPDATE_FAILL.subCode(),"更新流水报错");
        }
        return  R.success("退款成功");
    }

    /**
     * 微信退款方法
     * @throws Exception
     */
    @Transactional
     ResObject weChatRefund(String payType,String outRequestNo,StudentOrderEntity studentOrderDo,PayFlowLogEntity payFlowLogDo) throws Exception {

        Map<String,String> signMap = new LinkedHashMap<String,String>();
        String nonceStr = RandomUtil.getSecureRandom().getAlgorithm();
        if(StudyEnrollEnum.PAY_TYPE_WECHAT.getCode().equals(payType)){
            //appid
            signMap.put("appid", weChatAppId);
        }
        if(StudyEnrollEnum.PAY_TYPE_WECHAT_PUBLIC.getCode().equals(payType)){
            //微信公众号appid
            signMap.put("appid", weChatTencentAppId);
        }

        if(studentOrderDo.getPayableAmount().compareTo(new BigDecimal(5999)) ==1){
            signMap.put("mch_id", weChatNoLimitMchId) ; //商户号(不限额商户号)
        }else{
            signMap.put("mch_id", weChatMchId); //商户号
        }
        //随机字符串
        signMap.put("nonce_str", nonceStr);
        //商户订单号=交易流水id
        signMap.put("out_trade_no", payFlowLogDo.getId());
        //商户退款单号
        signMap.put("out_refund_no", outRequestNo);
        BigDecimal payAmount = ArithUtil.mulDown(studentOrderDo.getPayableAmount(),new BigDecimal(100),0);
        BigDecimal refundAmount = ArithUtil.mulDown(studentOrderDo.getPayableAmount(),new BigDecimal(100),0);
        //订单金额（为订单表的  应付金额） 单位:分
        signMap.put("total_fee", payAmount.toPlainString());
        //退款金额（为订单表的应付金额）
        signMap.put("refund_fee", refundAmount.toPlainString());
        String sign= SignUtils.createSign(signMap, null, weChatSignKey, false);
        signMap.put("sign", sign);
        String  sendXml ="<xml>" +
                "<appid>"+signMap.get("appid")+"</appid>" +
                "<mch_id>"+signMap.get("mch_id")+"</mch_id>"+
                "<nonce_str>"+nonceStr+"</nonce_str>" +
                "<out_refund_no>"+outRequestNo+"</out_refund_no>" +
                "<out_trade_no>"+payFlowLogDo.getId()+"</out_trade_no>" +
                "<refund_fee>"+payAmount+"</refund_fee>" +
                "<total_fee>"+refundAmount+"</total_fee>" +
                "<sign>"+sign+"</sign>" +
                "</xml>";
        FileInputStream fis=null;
            KeyStore keyStore = KeyStore.getInstance("PKCS12");

            //服务器证书地址
            String wechatCertPath = this.getWechatCertPath(studentOrderDo.getMchId());

            fis = new FileInputStream(new File(wechatCertPath));
            keyStore.load(fis, signMap.get("mch_id").toCharArray());//设置密码，默认MCHID

            SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, signMap.get("mch_id").toCharArray()).build();// 这里也是写密码的
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" }, null,
            SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
            CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
                HttpPost httpost = new HttpPost(weChatRefuneUrl); // 设置响应头信息
                httpost.addHeader("Connection", "keep-alive");
                httpost.addHeader("Accept", "*/*");
                httpost.addHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
                httpost.addHeader("Host", "api.mch.weixin.qq.com");
                httpost.addHeader("X-Requested-With", "XMLHttpRequest");
                httpost.addHeader("Cache-Control", "max-age=0");
                httpost.addHeader("User-Agent","Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");
                httpost.setEntity(new StringEntity(sendXml, "UTF-8"));
                CloseableHttpResponse response = httpclient.execute(httpost);
                    String jsonStr = EntityUtils.toString(response.getEntity(), "UTF-8");
                    JSONObject json= XmlUtil.xml2JSONStr(jsonStr);
                    //封装返回前端的数据
                    JSONObject xml = json.getJSONObject("xml");
                    if("SUCCESS".equals(xml.getString("return_code"))) {
                        if("SUCCESS".equals(xml.getString("result_code"))){ //SUCCESS退款申请接收成功，结果通过退款查询接口查询

                            String mch_id= xml.getString("mch_id");//商户号
                            String out_trade_no= xml.getString("out_trade_no");//商户订单号
                            String refundFee = xml.getString("refund_fee");//退款金额
                            //Math.abs((studentOrderDo.getPayableAmount()*100)-refund_fee)<0.0000001

                        if(signMap.get("mch_id").equals(mch_id) && payFlowLogDo.getId().equals(out_trade_no)){
                            return this.refundSuccess(sendXml,json.toJSONString(),new BigDecimal(refundFee),LocalDateTime.now(),payFlowLogDo,studentOrderDo);
                        }
                        }else {
                            throw new BizException("微信退款方法调用异常："+xml.getString("err_code_des"));
                        }
                    }else{
                        throw new BizException("微信退款方法调用异常："+xml.getString("return_msg"));
                    }
                    fis.close();
                    httpclient.close();
                    response.close();
                    return R.failure();
    }


    public  String getWechatCertPath(String mchId) {
        String WECHAT_CERT_PATH_PREFIX = weChatPrefix;
        String WECHAT_CERT_PATH_SUFFIX = weChatSuffix;
        // 若没有数据，则使用默认最早商户号
        if(StrUtil.isEmpty(mchId)){
            mchId = weChatMchId;
        }
        return WECHAT_CERT_PATH_PREFIX+mchId+WECHAT_CERT_PATH_SUFFIX ;
    }
}
