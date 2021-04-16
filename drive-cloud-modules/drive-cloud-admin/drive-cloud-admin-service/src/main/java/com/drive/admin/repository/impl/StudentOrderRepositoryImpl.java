package com.drive.admin.repository.impl;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.admin.enums.OperationTypeEnum;
import com.drive.admin.enums.StudyEnrollEnum;
import com.drive.admin.pojo.dto.StudentOrderEditParam;
import com.drive.admin.pojo.dto.StudentOrderPageQueryParam;
import com.drive.admin.pojo.entity.StudentOrderEntity;
import com.drive.admin.pojo.entity.StudentStudyEnrollEntity;
import com.drive.admin.pojo.vo.StudentOrderVo;
import com.drive.admin.pojo.vo.StudentStudyEnrollVo;
import com.drive.admin.repository.StudentOrderRepository;
import com.drive.admin.service.StudentInfoService;
import com.drive.admin.service.StudentOrderService;
import com.drive.admin.service.StudentStudyEnrollService;
import com.drive.admin.service.mapstruct.StudentOrderMapStruct;
import com.drive.common.core.base.BaseController;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.biz.SubResultCode;
import com.drive.common.core.exception.BizException;
import com.drive.common.core.utils.BeanConvertUtils;
import com.drive.common.data.utils.ExcelUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;


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
            if (StrUtil.isNotEmpty(item.getStudentId()))item.setStudentName(studentInfoService.getById(item.getStudentId()).getRealName());
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
}
