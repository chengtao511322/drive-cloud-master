package com.drive.admin.repository.impl;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.admin.pojo.dto.StudentOrderEditParam;
import com.drive.admin.pojo.dto.StudentOrderPageQueryParam;
import com.drive.admin.pojo.entity.StudentOrderEntity;
import com.drive.admin.pojo.vo.StudentOrderVo;
import com.drive.admin.repository.StudentOrderRepository;
import com.drive.admin.service.StudentInfoService;
import com.drive.admin.service.StudentOrderService;
import com.drive.admin.service.mapstruct.StudentOrderMapStruct;
import com.drive.common.core.base.BaseController;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.biz.SubResultCode;
import com.drive.common.core.utils.BeanConvertUtils;
import com.drive.common.data.utils.ExcelUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

}

