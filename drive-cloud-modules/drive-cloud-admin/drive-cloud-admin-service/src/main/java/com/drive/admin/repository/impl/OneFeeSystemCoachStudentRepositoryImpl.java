package com.drive.admin.repository.impl;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.admin.enums.StudyEnrollEnum;
import com.drive.admin.pojo.dto.OneFeeSystemCoachStudentEditParam;
import com.drive.admin.pojo.dto.OneFeeSystemCoachStudentPageQueryParam;
import com.drive.admin.pojo.entity.CoachInfoEntity;
import com.drive.admin.pojo.entity.OneFeeSystemCoachStudentEntity;
import com.drive.admin.pojo.entity.StudentInfoEntity;
import com.drive.admin.pojo.entity.StudentOrderEntity;
import com.drive.admin.pojo.vo.OneFeeSystemCoachStudentVo;
import com.drive.admin.repository.OneFeeSystemCoachStudentRepository;
import com.drive.admin.service.CoachInfoService;
import com.drive.admin.service.OneFeeSystemCoachStudentService;
import com.drive.admin.service.StudentInfoService;
import com.drive.admin.service.StudentOrderService;
import com.drive.admin.service.mapstruct.OneFeeSystemCoachStudentMapStruct;
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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 *
 * 一费制学员教练关联表 服务类
 *
 * @author xiaoguo
 */
@Slf4j
@Service
public class  OneFeeSystemCoachStudentRepositoryImpl extends BaseController<OneFeeSystemCoachStudentPageQueryParam, OneFeeSystemCoachStudentEntity>  implements OneFeeSystemCoachStudentRepository{

    @Autowired
    private OneFeeSystemCoachStudentService oneFeeSystemCoachStudentService;
    @Autowired
    private OneFeeSystemCoachStudentMapStruct oneFeeSystemCoachStudentMapStruct;

    @Autowired
    private StudentOrderService studentOrderService;

    @Autowired
    private StudentInfoService studentInfoService;
    @Autowired
    private CoachInfoService coachInfoService;

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 一费制学员教练关联表 分页列表
     * @date 2020/2/12 17:09
     * @param  * @param OneFeeSystemCoachStudentPageQueryParam
     * @return
     */
    @Override
    public ResObject pageList(OneFeeSystemCoachStudentPageQueryParam param) {
        log.info(this.getClass() + "pageList-方法请求参数{}",param);
        Page<OneFeeSystemCoachStudentEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        QueryWrapper queryWrapper = this.getQueryWrapper(oneFeeSystemCoachStudentMapStruct, param);

        // 用户手机号查询
        if (StrUtil.isNotEmpty(param.getVagueRealNameSearch()) || StrUtil.isNotEmpty(param.getVaguePhoneSearch())){
            List<StudentInfoEntity> studentInfoList = new ArrayList<>();
            QueryWrapper studentQueryWrapper = new QueryWrapper();
            studentQueryWrapper.like(StrUtil.isNotEmpty(param.getVagueRealNameSearch()),"real_name",param.getVagueRealNameSearch());
            studentQueryWrapper.like(StrUtil.isNotEmpty(param.getVaguePhoneSearch()),"phone",param.getVaguePhoneSearch());
            studentInfoList = studentInfoService.list(studentQueryWrapper);
            if(studentInfoList.size() <= 0)return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),studentInfoList);
            queryWrapper.in("student_id",studentInfoList.stream().map(StudentInfoEntity::getId).collect(Collectors.toList()));
        }


        // 教练用户手机号查询
        if (StrUtil.isNotEmpty(param.getVagueCoachNameSearch()) || StrUtil.isNotEmpty(param.getVagueCoachPhoneSearch())){
            List<CoachInfoEntity> coachInfoList = new ArrayList<>();
            QueryWrapper studentQueryWrapper = new QueryWrapper();
            studentQueryWrapper.like(StrUtil.isNotEmpty(param.getVagueCoachNameSearch()),"real_name",param.getVagueCoachNameSearch());
            studentQueryWrapper.like(StrUtil.isNotEmpty(param.getVagueCoachPhoneSearch()),"phone",param.getVagueCoachPhoneSearch());
            coachInfoList = coachInfoService.list(studentQueryWrapper);
            if(coachInfoList.size() <= 0)return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),coachInfoList);
            queryWrapper.in("coach_id",coachInfoList.stream().map(CoachInfoEntity::getId).collect(Collectors.toList()));
        }
        IPage<OneFeeSystemCoachStudentEntity> pageList = oneFeeSystemCoachStudentService.page(page,queryWrapper);
        if (pageList.getRecords().size() <= 0){
            return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        Page<OneFeeSystemCoachStudentVo> oneFeeSystemCoachStudentVoPage = oneFeeSystemCoachStudentMapStruct.toVoList(pageList);
        oneFeeSystemCoachStudentVoPage.getRecords().stream().forEach((item) ->{
            // 学员
            if (StrUtil.isNotEmpty(item.getStudentId())){
                StudentInfoEntity studentInfo = studentInfoService.getById(item.getStudentId());
                if (studentInfo != null)item.setStudentName(studentInfo.getRealName());
            }
        });
        log.info(this.getClass() + "pageList-方法请求结果{}",oneFeeSystemCoachStudentVoPage);
        return R.success(oneFeeSystemCoachStudentVoPage);
    }

    @Override
    public ResObject findList(OneFeeSystemCoachStudentPageQueryParam param) {
        log.info(this.getClass() + "findList-方法请求参数{}",param);
        // 这里判断条件进行查询
        QueryWrapper queryWrapper= this.getQueryWrapper(oneFeeSystemCoachStudentMapStruct, param);
        // 如 queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"phone",param.getPhone());
        List<OneFeeSystemCoachStudentEntity> pageList = oneFeeSystemCoachStudentService.list(queryWrapper);
        List<OneFeeSystemCoachStudentVo> oneFeeSystemCoachStudentVoList = oneFeeSystemCoachStudentMapStruct.toVoList(pageList);
        log.info(this.getClass() + "findList-方法请求结果{}",oneFeeSystemCoachStudentVoList);
        if (oneFeeSystemCoachStudentVoList == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        return R.success(oneFeeSystemCoachStudentVoList);
    }



    @Override
    public ResObject getInfo(OneFeeSystemCoachStudentPageQueryParam param) {
        return null;
    }

    /**
     * *通过ID获取一费制学员教练关联表 列表
     **/
    @Override
    public ResObject getById(String id) {
        log.info(this.getClass() + "getInfo-方法请求参数{}",id);
        if (StrUtil.isEmpty(id)){
            return R.failure("数据空");
        }
        OneFeeSystemCoachStudentEntity oneFeeSystemCoachStudent = oneFeeSystemCoachStudentService.getById(id);
        StudentInfoEntity studentInfoEntity = studentInfoService.getById(oneFeeSystemCoachStudent.getStudentId());
        OneFeeSystemCoachStudentVo oneFeeSystemCoachStudentVo = BeanConvertUtils.copy(oneFeeSystemCoachStudent, OneFeeSystemCoachStudentVo.class);
        oneFeeSystemCoachStudentVo.setStudentName(studentInfoEntity.getRealName());
        log.info(this.getClass() + "getInfo-方法请求结果{}",oneFeeSystemCoachStudentVo);
        if (oneFeeSystemCoachStudentVo ==null){
            log.error("活动数据对象空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        return R.success(oneFeeSystemCoachStudentVo);
    }

    /**
     * *保存一费制学员教练关联表 信息
     **/
    @Override
    public ResObject save(OneFeeSystemCoachStudentEditParam installParam) {
        log.info(this.getClass() + "save方法请求参数{}",installParam);
        if (installParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        OneFeeSystemCoachStudentEntity oneFeeSystemCoachStudent = BeanConvertUtils.copy(installParam, OneFeeSystemCoachStudentEntity.class);
        Boolean result = oneFeeSystemCoachStudentService.save(oneFeeSystemCoachStudent);
        log.info(this.getClass() + "save-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *修改一费制学员教练关联表 信息
     **/
    @Override
    public ResObject update(OneFeeSystemCoachStudentEditParam updateParam) {
        log.info(this.getClass() + "update方法请求参数{}",updateParam);
        if (updateParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        OneFeeSystemCoachStudentEntity oneFeeSystemCoachStudent = BeanConvertUtils.copy(updateParam, OneFeeSystemCoachStudentEntity.class);
        Boolean result = oneFeeSystemCoachStudentService.updateById(oneFeeSystemCoachStudent);
        log.info(this.getClass() + "update-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *删除一费制学员教练关联表 信息
     **/
    @Override
    public ResObject deleteByIds(String[] ids) {
        if (ids.length <= 0){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        return R.toRes(oneFeeSystemCoachStudentService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * *通过id删除一费制学员教练关联表 信息
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
        Boolean result = oneFeeSystemCoachStudentService.removeById(id);
        log.info(this.getClass() + "deleteById-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *导出一费制学员教练关联表 信息
     **/
    @Override
    public ResObject exportXls(OneFeeSystemCoachStudentPageQueryParam param, HttpServletResponse response)throws IOException {
        log.info(this.getClass() + "exportXls方法请求参数{}",param);
        QueryWrapper queryWrapper = this.getQueryWrapper(oneFeeSystemCoachStudentMapStruct, param);
        List<OneFeeSystemCoachStudentEntity> list = oneFeeSystemCoachStudentService.list(queryWrapper);
        List<OneFeeSystemCoachStudentVo>oneFeeSystemCoachStudentList = oneFeeSystemCoachStudentMapStruct.toVoList(list);
        ExcelUtils.exportExcel(oneFeeSystemCoachStudentList, OneFeeSystemCoachStudentVo.class, "", new ExportParams(), response);
        return R.success("导出成功");
    }

    /**
     * *修改状态
     **/
    @Override
    public ResObject changeStatus(OneFeeSystemCoachStudentEditParam param) {
        log.info(this.getClass() + "changeStatus方法请求参数{}",param);
        if (StrUtil.isEmpty(param.getId())){
            log.error("ID数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        OneFeeSystemCoachStudentEntity OneFeeSystemCoachStudentEntity = new OneFeeSystemCoachStudentEntity();
        OneFeeSystemCoachStudentEntity.setId(param.getId());
        OneFeeSystemCoachStudentEntity.setStatus(param.getStatus());
        //OneFeeSystemCoachStudentEntity.setUpdateTime()
        Boolean result = oneFeeSystemCoachStudentService.updateById(OneFeeSystemCoachStudentEntity);
        log.info(this.getClass() +"changeStatus方法请求对象参数{}，请求结果{}",OneFeeSystemCoachStudentEntity,result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    @Override
    public ResObject bindingCoach(OneFeeSystemCoachStudentEditParam oneFeeSystemCoachStudentEditParam) {
        log.info(this.getClass() +"bindingCoach-方法请求参数{}",oneFeeSystemCoachStudentEditParam);
        if (oneFeeSystemCoachStudentEditParam == null){
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        if (StrUtil.isEmpty(oneFeeSystemCoachStudentEditParam.getStudentId())){
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        if (StrUtil.isEmpty(oneFeeSystemCoachStudentEditParam.getCoachId())){
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        // 查询数据幂等
        QueryWrapper queryWrapper = new QueryWrapper();
        // 订单好
        queryWrapper.eq("order_no",oneFeeSystemCoachStudentEditParam.getOrderNo());
        // 学员ID
        queryWrapper.eq("student_id",oneFeeSystemCoachStudentEditParam.getStudentId());
        // 教练ID
        queryWrapper.eq("coach_id",oneFeeSystemCoachStudentEditParam.getCoachId());
        // 绑定状态
        queryWrapper.eq("bind_status",StudyEnrollEnum.BIND_STATUS_ALREADY_BIND.getCode());
        int count = oneFeeSystemCoachStudentService.count(queryWrapper);
        if (count >0){
            log.error("数据重复");
            return R.success(SubResultCode.DATA_IDEMPOTENT.subCode(),SubResultCode.DATA_IDEMPOTENT.subMsg());
        }
        oneFeeSystemCoachStudentEditParam.setBindTime(LocalDateTime.now());
        oneFeeSystemCoachStudentEditParam.setBindStatus(Integer.parseInt(StudyEnrollEnum.BIND_STATUS_ALREADY_BIND.getCode()));
        OneFeeSystemCoachStudentEntity oneFeeSystemCoachStudent = BeanConvertUtils.copy(oneFeeSystemCoachStudentEditParam, OneFeeSystemCoachStudentEntity.class);
        Boolean result = oneFeeSystemCoachStudentService.saveOrUpdate(oneFeeSystemCoachStudent);
        log.info(this.getClass() + "save-方法请求结果{}",result);
        // 判断结果
        if (!result) {
            return R.failure(SubResultCode.DATA_INSTALL_FAILL.subCode(),SubResultCode.DATA_INSTALL_FAILL.subMsg());
        }
        return R.success(result);
    }

    @Override
    public ResObject getCoachStudentByOrderNo(String orderNo) {
        log.info(this.getClass() + "getCoachStudentByOrderNo-方法 请求参数{}",orderNo);
        if (StrUtil.isEmpty(orderNo)){
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        QueryWrapper orderQueryWrapper = new QueryWrapper();
        orderQueryWrapper.eq("study_enroll_no",orderNo);
        StudentOrderEntity studentOrder = studentOrderService.getOne(orderQueryWrapper);
        if (studentOrder == null){
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("order_no",studentOrder.getOrderNo());
        OneFeeSystemCoachStudentEntity coachStudent = oneFeeSystemCoachStudentService.getOne(queryWrapper);
        if (coachStudent == null){
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        OneFeeSystemCoachStudentVo coachStudentVo = BeanConvertUtils.copy(coachStudent,OneFeeSystemCoachStudentVo.class);
        if (StrUtil.isNotEmpty(coachStudentVo.getStudentId()))coachStudentVo.setStudentName(studentInfoService.getById(coachStudentVo.getStudentId()).getRealName());
        if (StrUtil.isNotEmpty(coachStudentVo.getCoachId()))coachStudentVo.setCoachName(coachInfoService.getById(coachStudentVo.getCoachId()).getRealName());
        return R.success(coachStudentVo);
    }


}