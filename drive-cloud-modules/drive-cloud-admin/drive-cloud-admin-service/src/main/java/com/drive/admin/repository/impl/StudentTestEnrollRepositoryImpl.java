package com.drive.admin.repository.impl;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.admin.pojo.dto.StudentTestEnrollEditParam;
import com.drive.admin.pojo.dto.StudentTestEnrollPageQueryParam;
import com.drive.admin.pojo.entity.CoachingGridEntity;
import com.drive.admin.pojo.entity.StudentTestEnrollEntity;
import com.drive.admin.pojo.vo.StudentTestEnrollVo;
import com.drive.admin.repository.StudentTestEnrollRepository;
import com.drive.admin.service.*;
import com.drive.admin.service.mapstruct.StudentTestEnrollMapStruct;
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
 * 学员考试报名表 服务类
 *
 * @author xiaoguo
 */
@Slf4j
@Service
public class  StudentTestEnrollRepositoryImpl extends BaseController<StudentTestEnrollPageQueryParam, StudentTestEnrollEntity>  implements StudentTestEnrollRepository{

    //  学员考试报名表 服务
    @Autowired
    private StudentTestEnrollService studentTestEnrollService;
    //  学员考试报名表 DO-DTO转化
    @Autowired
    private StudentTestEnrollMapStruct studentTestEnrollMapStruct;

    //  平台训练场地表 服务
    @Autowired
    private CoachingGridService coachingGridService;


    @Autowired
    private AreaService areaService;

    @Autowired
    private StudentInfoService studentInfoService;

    @Autowired
    private ServiceInfoService serviceInfoService;

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 学员考试报名表 分页列表
     * @date 2020/2/12 17:09
     * @param  * @param StudentTestEnrollPageQueryParam
     * @return
     */
    @Override
    public ResObject pageList(StudentTestEnrollPageQueryParam param) {
        log.info(this.getClass() + "pageList-方法请求参数{}",param);
        Page<StudentTestEnrollEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        // 条件查询
        QueryWrapper queryWrapper = this.getQueryWrapper(studentTestEnrollMapStruct, param);
        // 报名单号 模糊查询
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueTestEnrollNoSearch()),"test_enroll_no",param.getVagueTestEnrollNoSearch());
        // 预约见面时间
        queryWrapper.apply(StrUtil.isNotEmpty(param.getTestActualTimeSearch()),
                "date_format (test_actual_time,'%Y-%m-%d') = date_format('" + param.getTestActualTimeSearch() + "','%Y-%m-%d')");
        // 意向报名时间
        queryWrapper.apply(StrUtil.isNotBlank(param.getTestHopeTimeSearch()),
                "date_format (test_hope_time,'%Y-%m-%d') = date_format('" + param.getTestHopeTimeSearch() + "','%Y-%m-%d')");
        // 报名单号 模糊查询
        //queryWrapper.like(StrUtil.isNotEmpty(param.getVagueStudyEnrollNoSearch()),"study_enroll_no",param.getVagueStudyEnrollNoSearch());
        //  开始时间 结束时间都有才进入
        if (StrUtil.isNotEmpty(param.getBeginTime()) && StrUtil.isNotEmpty(param.getEndTime())){
            queryWrapper.between(StrUtil.isNotEmpty(param.getBeginTime()),"create_time",param.getBeginTime(),param.getEndTime());
        }
        IPage<StudentTestEnrollEntity> pageList = studentTestEnrollService.page(page, queryWrapper);
        if (pageList.getRecords().size() <= 0){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        Page<StudentTestEnrollVo> studentTestEnrollVoPage = studentTestEnrollMapStruct.toVoList(pageList);
        studentTestEnrollVoPage.getRecords().stream().forEach((item) ->{
            if (StrUtil.isNotEmpty(item.getStudentId()))item.setStudentName(studentInfoService.getById(item.getStudentId()).getRealName());
            if (StrUtil.isNotEmpty(item.getUserId()))item.setOnlineServiceName(serviceInfoService.getById(item.getUserId()).getRealName());
            if (StrUtil.isNotEmpty(item.getLineUnderUserId()))item.setLineServiceName(serviceInfoService.getById(item.getLineUnderUserId()).getRealName());
            // 省市区
            if (StrUtil.isNotEmpty(item.getProvinceId()))item.setProvinceName(areaService.getByBaCode(item.getProvinceId()).getBaName());
            if (StrUtil.isNotEmpty(item.getCityId()))item.setCityName(areaService.getByBaCode(item.getCityId()).getBaName());
            if (StrUtil.isNotEmpty(item.getAreaId()))item.setAreaName(areaService.getByBaCode(item.getAreaId()).getBaName());
            // 平台训练场地
            if (StrUtil.isNotEmpty(item.getTestActualCoachingGridId())){
                CoachingGridEntity coachingGridEntity =coachingGridService.getById(item.getTestActualCoachingGridId());
                if(coachingGridEntity != null)item.setTestActualCoachingGridName(coachingGridEntity.getName());
            };
            if (StrUtil.isNotEmpty(item.getTestHopeCoachingGridId())){
                CoachingGridEntity coachingGrid = coachingGridService.getById(item.getTestHopeCoachingGridId());
                if(coachingGrid != null)item.setTestHopeCoachingGridName(coachingGrid.getName());
            }

        });
        log.info(this.getClass() + "pageList-方法请求结果{}",studentTestEnrollVoPage);
        return R.success(studentTestEnrollVoPage);
    }

    @Override
    public ResObject findList(StudentTestEnrollPageQueryParam param) {
        log.info(this.getClass() + "findList-方法请求参数{}",param);
        // 这里判断条件进行查询
        QueryWrapper queryWrapper= this.getQueryWrapper(studentTestEnrollMapStruct, param);
        // 如 queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"phone",param.getPhone());
        List<StudentTestEnrollEntity> studentTestEnrollList = studentTestEnrollService.list(queryWrapper);
        if (studentTestEnrollList == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        List<StudentTestEnrollVo> studentTestEnrollVoList = studentTestEnrollMapStruct.toVoList(studentTestEnrollList);
        log.info(this.getClass() + "findList-方法请求结果{}",studentTestEnrollVoList);
        return R.success(studentTestEnrollVoList);
    }


    @Override
    public ResObject getInfo(StudentTestEnrollPageQueryParam param) {
        return null;
    }

    /**
     * *通过ID获取学员考试报名表 列表
     **/
    @Override
    public ResObject getById(String testEnrollNo) {
        log.info(this.getClass() + "getInfo-方法请求参数{}",testEnrollNo);
        if (StrUtil.isEmpty(testEnrollNo)){
            return R.failure("数据空");
        }
        StudentTestEnrollEntity studentTestEnroll = studentTestEnrollService.getById(testEnrollNo);
        if (studentTestEnroll == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        StudentTestEnrollVo studentTestEnrollVo = BeanConvertUtils.copy(studentTestEnroll, StudentTestEnrollVo.class);
        log.info(this.getClass() + "getInfo-方法请求结果{}",studentTestEnrollVo);
        return R.success(studentTestEnrollVo);
    }

    /**
     * *保存学员考试报名表 信息
     **/
    @Override
    public ResObject save(StudentTestEnrollEditParam installParam) {
        log.info(this.getClass() + "save方法请求参数{}",installParam);
        if (installParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        StudentTestEnrollEntity studentTestEnroll = BeanConvertUtils.copy(installParam, StudentTestEnrollEntity.class);
        Boolean result = studentTestEnrollService.save(studentTestEnroll);
        log.info(this.getClass() + "save-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *修改学员考试报名表 信息
     **/
    @Override
    public ResObject update(StudentTestEnrollEditParam updateParam) {
        log.info(this.getClass() + "update方法请求参数{}",updateParam);
        if (updateParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        StudentTestEnrollEntity studentTestEnroll = BeanConvertUtils.copy(updateParam, StudentTestEnrollEntity.class);
        Boolean result = studentTestEnrollService.updateById(studentTestEnroll);
        log.info(this.getClass() + "update-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *删除学员考试报名表 信息
     **/
    @Override
    public ResObject deleteByIds(String[] ids) {
        if (ids.length <= 0){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        return R.toRes(studentTestEnrollService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * *通过id删除学员考试报名表 信息
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
        Boolean result = studentTestEnrollService.removeById(id);
        log.info(this.getClass() + "deleteById-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *导出学员考试报名表 信息
     **/
    @Override
    public ResObject exportXls(StudentTestEnrollPageQueryParam param, HttpServletResponse response)throws IOException {
        log.info(this.getClass() + "exportXls方法请求参数{}",param);
        QueryWrapper queryWrapper = this.getQueryWrapper(studentTestEnrollMapStruct, param);
        List<StudentTestEnrollEntity> list = studentTestEnrollService.list(queryWrapper);
        List<StudentTestEnrollVo>studentTestEnrollList = studentTestEnrollMapStruct.toVoList(list);
        ExcelUtils.exportExcel(studentTestEnrollList, StudentTestEnrollVo.class, "", new ExportParams(), response);
        return R.success("导出成功");
    }

    /**
     * *修改状态
     **/
    @Override
    public ResObject changeStatus(StudentTestEnrollEditParam param) {
        log.info(this.getClass() + "changeStatus方法请求参数{}",param);
        if (StrUtil.isEmpty(param.getTestEnrollNo())){
            log.error("ID数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        StudentTestEnrollEntity StudentTestEnrollEntity = new StudentTestEnrollEntity();
        StudentTestEnrollEntity.setTestEnrollNo(param.getTestEnrollNo());
        //StudentTestEnrollEntity.setStatus(param.getStatus());
        //StudentTestEnrollEntity.setUpdateTime()
        Boolean result = studentTestEnrollService.updateById(StudentTestEnrollEntity);
        log.info(this.getClass() +"changeStatus方法请求对象参数{}，请求结果{}",StudentTestEnrollEntity,result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

}

