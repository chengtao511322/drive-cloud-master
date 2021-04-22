package com.drive.admin.repository.impl;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.nosql.redis.RedisDS;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.admin.enums.*;
import com.drive.admin.pojo.dto.CompleteStudyEnrollParam;
import com.drive.admin.pojo.dto.StudentTestEnrollEditParam;
import com.drive.admin.pojo.dto.StudentTestEnrollPageQueryParam;
import com.drive.admin.pojo.entity.*;
import com.drive.admin.pojo.vo.OneFeeSystemCoachStudentVo;
import com.drive.admin.pojo.vo.StudentStudyEnrollVo;
import com.drive.admin.pojo.vo.StudentTestEnrollVo;
import com.drive.admin.pojo.vo.StudentTrainCarApplyVo;
import com.drive.admin.repository.StudentTestEnrollRepository;
import com.drive.admin.service.*;
import com.drive.admin.service.mapstruct.StudentStudyEnrollMapStruct;
import com.drive.admin.service.mapstruct.StudentTestEnrollMapStruct;
import com.drive.admin.strategy.StudyEnrollStrategy;
import com.drive.admin.strategy.context.SpringContextUtil;
import com.drive.common.core.base.BaseController;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.biz.SubResultCode;
import com.drive.common.core.constant.CacheConstants;
import com.drive.common.core.utils.BeanConvertUtils;
import com.drive.common.data.utils.ExcelUtils;
import com.drive.common.redis.util.CacheUtils;
import com.drive.common.redis.util.JedisConnect;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private StudentInfoService studentInfoService;

    @Autowired
    private ServiceInfoService serviceInfoService;

    @Autowired
    private StudentStudyEnrollService studentStudyEnrollService;

    @Autowired
    private StudentStudyEnrollMapStruct studentStudyEnrollMapStruct;

    @Autowired
    private DriveSchoolService driveSchoolService;

    @Autowired
    private CoachTeachTimeService coachTeachTimeService;

    @Autowired
    private StudentTrainCarApplyService studentTrainCarApplyService;

    @Autowired
    private StudentOrderService studentOrderService;

    @Autowired
    private OneFeeSystemCoachStudentService oneFeeSystemCoachStudentService;


    @Autowired
    private OneFeeSystemPriceService oneFeeSystemPriceService;

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

        if (StrUtil.isNotEmpty(param.getExamStatus()) && param.getExamStatus().equals(ExamEnrollEnum.EXAM_LOADING.getCode())){
            queryWrapper.eq("enroll_status",ExamEnrollEnum.BOOK_SUCCESS.getCode());
            queryWrapper.apply("date_format (test_actual_time,'%Y-%m-%d') <= date_format(now(),'%Y-%m-%d')");
        }

        // 预约见面时间
        if (StrUtil.isNotEmpty(param.getEnrollStatus()) && param.getEnrollStatus().equals(ExamEnrollEnum.BOOK_SUCCESS.getCode())){
            queryWrapper.apply("date_format (test_actual_time,'%Y-%m-%d') > date_format(now(),'%Y-%m-%d')");
        }else{
            queryWrapper.apply(StrUtil.isNotEmpty(param.getTestActualTimeSearch()),
                    "date_format (test_actual_time,'%Y-%m-%d') = date_format('" + param.getTestActualTimeSearch() + "','%Y-%m-%d')");
        }

        // 意向报名时间 testActualTime
        queryWrapper.apply(StrUtil.isNotBlank(param.getTestHopeTimeSearch()),
                "date_format (test_hope_time,'%Y-%m-%d') = date_format('" + param.getTestHopeTimeSearch() + "','%Y-%m-%d')");
        // 报名单号 模糊查询
        //queryWrapper.like(StrUtil.isNotEmpty(param.getVagueStudyEnrollNoSearch()),"study_enroll_no",param.getVagueStudyEnrollNoSearch());
        //  开始时间 结束时间都有才进入
        if (StrUtil.isNotEmpty(param.getBeginTime()) && StrUtil.isNotEmpty(param.getEndTime())){
            queryWrapper.between(StrUtil.isNotEmpty(param.getBeginTime()),"create_time",param.getBeginTime(),param.getEndTime());
        }
        // queryWrapper.orderByDesc("create_time");
        IPage<StudentTestEnrollEntity> pageList = studentTestEnrollService.page(page, queryWrapper);
        if (pageList.getRecords().size() <= 0){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }
        Page<StudentTestEnrollVo> studentTestEnrollVoPage = studentTestEnrollMapStruct.toVoList(pageList);
        // 循环数据
        studentTestEnrollVoPage.getRecords().stream().forEach((item) ->{
            if (StrUtil.isNotEmpty(item.getStudentId())){
                StudentInfoEntity studentInfo = studentInfoService.getById(item.getStudentId());
                if (studentInfo != null)item.setStudentName(studentInfo.getRealName());
                if (studentInfo != null)item.setPhone(studentInfo.getPhone());
            }
            if (StrUtil.isNotEmpty(item.getUserId()))item.setOnlineServiceName(serviceInfoService.getById(item.getUserId()).getRealName());
            if (StrUtil.isNotEmpty(item.getLineUnderUserId()))item.setLineServiceName(serviceInfoService.getById(item.getLineUnderUserId()).getRealName());

            // 平台训练场地
            if (StrUtil.isNotEmpty(item.getTestActualCoachingGridId())){
                CoachingGridEntity coachingGridEntity =coachingGridService.getById(item.getTestActualCoachingGridId());
                if(coachingGridEntity != null)item.setTestActualCoachingGridName(coachingGridEntity.getName());
            };
            if (StrUtil.isNotEmpty(item.getTestHopeCoachingGridId())){
                CoachingGridEntity coachingGrid = coachingGridService.getById(item.getTestHopeCoachingGridId());
                if(coachingGrid != null)item.setTestHopeCoachingGridName(coachingGrid.getName());
            }
            // 查询次数
            QueryWrapper testQueryWrapper = new QueryWrapper();
            testQueryWrapper.eq("student_id",item.getStudentId());
            testQueryWrapper.eq("subject_type",item.getSubjectType());
            String[] arr = {
                                StudyEnrollEnum.PAY_SUCCESS.getCode(),
                                StudyEnrollEnum.EXAM_ACCOMPLISH.getCode(),
                                StudyEnrollEnum.EXAM_PASS.getCode(),
                                StudyEnrollEnum.EXAM_NO_PASS.getCode(),
                                StudyEnrollEnum.APPLY_LOADING.getCode(),
                                StudyEnrollEnum.REFUND_DISPOSE_LOADING.getCode(),
                                StudyEnrollEnum.EXAM_REFUND_SUCCESS.getCode()
                            };
            // 支付成功
            testQueryWrapper.in("enroll_status", arr);
            testQueryWrapper.eq("operator_id", item.getOperatorId());
            // 考试次数
            int examNumber = studentTestEnrollService.count(testQueryWrapper);
            item.setExamNumber(examNumber);

            // 查询报名单号
            QueryWrapper studyQueryWrapper = new QueryWrapper();
            studyQueryWrapper.eq("student_id",item.getStudentId());
            studyQueryWrapper.eq("enroll_status",StudyEnrollEnum.ENROLL_STATUS_ENROLL_COMPLETE.getCode());
            StudentStudyEnrollEntity studentStudyEnroll = studentStudyEnrollService.getOne(studyQueryWrapper);
            if (studentStudyEnroll != null){
                item.setStudentStudyEnrollVo(BeanConvertUtils.copy(studentStudyEnroll,StudentStudyEnrollVo.class));
            }
            QueryWrapper systemCoachStudentQueryWrapper = new QueryWrapper();
            // 学员ID
            systemCoachStudentQueryWrapper.eq("student_id",item.getStudentId());
            systemCoachStudentQueryWrapper.eq("bind_status",StatusEnum.NORMAL.getCode());
            OneFeeSystemCoachStudentEntity systemCoachStudent = oneFeeSystemCoachStudentService.getOne(systemCoachStudentQueryWrapper);
            if (systemCoachStudent != null){
                JSONObject jsonObject = JedisConnect.get(CacheConstants.REDIS_CACHE_COACH_KEY + systemCoachStudent.getCoachId());
                if (jsonObject != null)item.setBindCoach(jsonObject.getString("realName"));
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
        log.info(this.getClass()+"getInfo-方法请求参数{}",param);
        if (param == null){
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }

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

        if (StrUtil.isNotEmpty(studentTestEnrollVo.getStudentId()))studentTestEnrollVo.setStudentName(studentInfoService.getById(studentTestEnrollVo.getStudentId()).getRealName());
        if (StrUtil.isNotEmpty(studentTestEnrollVo.getUserId()))studentTestEnrollVo.setOnlineServiceName(serviceInfoService.getById(studentTestEnrollVo.getUserId()).getRealName());
        if (StrUtil.isNotEmpty(studentTestEnrollVo.getLineUnderUserId()))studentTestEnrollVo.setLineServiceName(serviceInfoService.getById(studentTestEnrollVo.getLineUnderUserId()).getRealName());
        // 平台训练场地
        if (StrUtil.isNotEmpty(studentTestEnrollVo.getTestActualCoachingGridId())){
            CoachingGridEntity coachingGridEntity =coachingGridService.getById(studentTestEnrollVo.getTestActualCoachingGridId());
            if(coachingGridEntity != null)studentTestEnrollVo.setTestActualCoachingGridName(coachingGridEntity.getName());
        };
        if (StrUtil.isNotEmpty(studentTestEnrollVo.getTestHopeCoachingGridId())){
            CoachingGridEntity coachingGrid = coachingGridService.getById(studentTestEnrollVo.getTestHopeCoachingGridId());
            if(coachingGrid != null)studentTestEnrollVo.setTestHopeCoachingGridName(coachingGrid.getName());
        }
        QueryWrapper queryWrapper= new QueryWrapper();
        queryWrapper.eq("enroll_status",StudyEnrollEnum.ENROLL_STATUS_ENROLL_COMPLETE.getCode());
        queryWrapper.eq("student_id",studentTestEnrollVo.getStudentId());
        StudentStudyEnrollEntity studentStudyEnroll = studentStudyEnrollService.getOne(queryWrapper);
        if (studentStudyEnroll != null){
            studentTestEnrollVo.setStudentStudyEnrollVo(BeanConvertUtils.copy(studentStudyEnroll,StudentStudyEnrollVo.class));
        }
        // 手机号
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
        StudentTestEnrollEntity studentTestEnroll = BeanConvertUtils.copy(updateParam,StudentTestEnrollEntity.class);
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

    @Override
    public ResObject noSubscribeSubjectOneExamPageList(StudentTestEnrollPageQueryParam studentTestEnrollPageQueryParam) {
        log.info(this.getClass()+"noSubscribeSubjectOneExamPageList-方法请求参数{}",studentTestEnrollPageQueryParam);
        Page<StudentStudyEnrollEntity> page = new Page<>(studentTestEnrollPageQueryParam.getPageNum(), studentTestEnrollPageQueryParam.getPageSize());
        // 学车报名单 包名完成
        //2.没有预约过科目一考试

        //3. 考试挂科满10天
        // 条件查询
        QueryWrapper<StudentStudyEnrollEntity> queryWrapper = new QueryWrapper();
        // 运营商查询
        queryWrapper.eq(StrUtil.isNotEmpty(studentTestEnrollPageQueryParam.getOperatorId()),"operator_id",studentTestEnrollPageQueryParam.getOperatorId());
        // 报名完成
        queryWrapper.eq("enroll_status",StudyEnrollEnum.ENROLL_STATUS_ENROLL_COMPLETE.getCode());
        // 拼接SQL 没有预约过科目一考试
        //queryWrapper.le("(SELECT COUNT(1) FROM t_student_test_enroll WHERE t_student_study_enroll.student_id = t_student_test_enroll.student_id)",0);
        // 拼接sql 考试挂科满10天
        /**
         * LambdaQueryWrapper<Task> queryWrapper2 = new QueryWrapper<Task>().lambda();
         *  queryWrapper2
         *   .eq(Task::getUserId, "15")
         *   .and(wrapper -> wrapper.eq(Task::getStatus, 2).or().eq(Task::getFileSize, 3251544304L));
         */

        queryWrapper.and(wrapper ->{
            wrapper.and(nameAgeQueryWrapper ->{
             /*   nameAgeQueryWrapper.or(itemWrapper ->{
                    itemWrapper.eq("(SELECT COUNT(1) FROM t_service_return_visit_history WHERE t_service_return_visit_history.student_id = t_student_study_enroll.student_id )",0);
                });*/
                // 考试挂科满10
                nameAgeQueryWrapper.or(itemWrapper ->{
                    itemWrapper.apply("(SELECT COUNT(1) FROM t_student_test_enroll WHERE t_student_test_enroll.student_id = t_student_study_enroll.student_id AND DATE_ADD(t_student_test_enroll.test_actual_time,INTERVAL 10 DAY) <= NOW() AND t_student_test_enroll.enroll_status=9) <=0");
                });
                // 没有预约过科目一考试
                nameAgeQueryWrapper.or(itemWrapper ->{
                    itemWrapper.apply("(SELECT COUNT(1) FROM t_student_test_enroll WHERE t_student_test_enroll.student_id = t_student_study_enroll.student_id and t_student_test_enroll.subject_type=1) <=0");
                });
            });
        });
        // 状态变速
        queryWrapper.apply("(SELECT COUNT(1) FROM t_student_test_enroll WHERE  t_student_test_enroll.student_id = t_student_study_enroll.student_id and t_student_test_enroll.enroll_status in(1,2,5,7,8,10)) <= 0");
        //queryWrapper.le("(SELECT COUNT(1) FROM t_student_test_enroll WHERE DATE_ADD(t_student_test_enroll.test_actual_time,INTERVAL 10 DAY) > NOW() AND t_student_test_enroll.student_id = t_student_study_enroll.student_id)",0);
        // queryWrapper .eq(“name”,“测试”).or().eq(“sim”,“2”);
        queryWrapper.orderByDesc("create_time");
        IPage<StudentStudyEnrollEntity> studentStudyEnrollPageList = studentStudyEnrollService.page(page,queryWrapper);
        if (studentStudyEnrollPageList.getRecords().size() <=0){
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),studentStudyEnrollPageList);
        }
        IPage<StudentStudyEnrollVo> studentStudyEnrollVos = studentStudyEnrollMapStruct.toVoList(studentStudyEnrollPageList);
        List<StudentStudyEnrollVo> newStudentStudyEnrollVos = new ArrayList<>();
        studentStudyEnrollVos.getRecords().stream().forEach((item) ->{
            // 查询报名单号
           QueryWrapper studyQueryWrapper = new QueryWrapper();
            studyQueryWrapper.eq("student_id",item.getStudentId());
            studyQueryWrapper.eq("subject_type",SubjectTypeEnum.SUBJECT_ONE.getCode());
            // studyQueryWrapper.eq("enroll_status",StudyEnrollEnum.ENROLL_STATUS_ENROLL_COMPLETE.getCode());
            studyQueryWrapper.orderByDesc("test_actual_time");
            studyQueryWrapper.last("limit 1");
            StudentTestEnrollEntity studentTestEnroll = studentTestEnrollService.getOne(studyQueryWrapper);
            if (studentTestEnroll != null){
                // 考试单号
                item.setTestEnrollNo(studentTestEnroll.getTestEnrollNo());
                item.setExamStatus(studentTestEnroll.getEnrollStatus());
                // 正常
            }
            if (StrUtil.isNotEmpty(item.getUserId()))item.setOnlineServiceName(serviceInfoService.getById(item.getUserId()).getRealName());
            if (StrUtil.isNotEmpty(item.getLineUnderUserId()))item.setLineServiceName(serviceInfoService.getById(item.getLineUnderUserId()).getRealName());
            if (StrUtil.isNotEmpty(item.getDriveSchoolId())){
                DriveSchoolEntity driveSchoolEntity =driveSchoolService.getById(item.getLineUnderUserId());
                if (driveSchoolEntity != null)item.setLineServiceName(driveSchoolEntity.getSchoolName());
            }


            // 查询次数
            QueryWrapper testQueryWrapper = new QueryWrapper();
            testQueryWrapper.eq("student_id",item.getStudentId());
            testQueryWrapper.eq("subject_type","1");
            String[] arr = {
                    StudyEnrollEnum.PAY_SUCCESS.getCode(),
                    StudyEnrollEnum.EXAM_ACCOMPLISH.getCode(),
                    StudyEnrollEnum.EXAM_PASS.getCode(),
                    StudyEnrollEnum.EXAM_NO_PASS.getCode(),
                    StudyEnrollEnum.APPLY_LOADING.getCode(),
                    StudyEnrollEnum.REFUND_DISPOSE_LOADING.getCode(),
                    StudyEnrollEnum.EXAM_REFUND_SUCCESS.getCode()
            };
            // 支付成功
            testQueryWrapper.in("enroll_status", arr);
            testQueryWrapper.eq("operator_id", item.getOperatorId());
            // 考试次数
            int examNumber = studentTestEnrollService.count(testQueryWrapper);
            item.setExamNumber(examNumber + 1);


            QueryWrapper systemCoachStudentQueryWrapper = new QueryWrapper();
            // 学员ID
            systemCoachStudentQueryWrapper.eq("student_id",item.getStudentId());
            systemCoachStudentQueryWrapper.eq("bind_status",StatusEnum.NORMAL.getCode());
            OneFeeSystemCoachStudentEntity systemCoachStudent = oneFeeSystemCoachStudentService.getOne(systemCoachStudentQueryWrapper);
            if (systemCoachStudent != null){
                JSONObject jsonObject = JedisConnect.get(CacheConstants.REDIS_CACHE_COACH_KEY + systemCoachStudent.getCoachId());
                if (jsonObject != null)item.setBindCoach(jsonObject.getString("realName"));
            }
            //newStudentStudyEnrollVos.add(item);
        });
        return R.success(studentStudyEnrollVos);
    }

    @Override
    public ResObject findPassingList(String studentId) {
        if (StrUtil.isEmpty(studentId)){
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        // 条件查询
        QueryWrapper queryWrapper = new QueryWrapper();
        String[] arr ={
                ExamEnrollEnum.SUBMIT_AWAIT_PAY.getCode(),
                ExamEnrollEnum.PAY_SUCCESS.getCode(),
                ExamEnrollEnum.BOOK_SUCCESS.getCode(),
                ExamEnrollEnum.EXAM_PASS.getCode(),
                ExamEnrollEnum.APPLY_LOADING.getCode(),
        };
        queryWrapper.in("enroll_status",arr);
        // 学员ID
        queryWrapper.eq("student_id",studentId);
        List<StudentTestEnrollEntity> studentTestEnrollList = studentTestEnrollService.list(queryWrapper);
        if (studentTestEnrollList.size() <=0){
            return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_NULL.subMsg(),studentTestEnrollList);
        }
        List<StudentTestEnrollVo> studentTestEnrollVoList = BeanConvertUtils.copyList(studentTestEnrollList,StudentTestEnrollVo.class);
        studentTestEnrollVoList.stream().forEach((item) ->{
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
        return R.success(studentTestEnrollVoList);
    }

    @Override
    public ResObject getDrivingPassing(String studentId) {
        log.info(this.getClass() + "getDrivingPassing-方法请求参数{}",studentId);
        if (StrUtil.isEmpty(studentId)){
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        // 条件查询
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("student_id",studentId);
        queryWrapper.groupBy("subject_type");
        List<StudentTrainCarApplyEntity> studentTrainCarApplyList = studentTrainCarApplyService.list(queryWrapper);
        if (studentTrainCarApplyList.size() <=0 ){
            return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_NULL.subMsg(),studentTrainCarApplyList);
        }
        List<StudentTrainCarApplyVo> studentTrainCarApplyVos = BeanConvertUtils.copyList(studentTrainCarApplyList, StudentTrainCarApplyVo.class);
        // double sum = studentTrainCarApplyVos.stream().filter((CoachTeachTimeVo coachTeachTime) -> ((StudentTrainCarApplyVo) coachTeachTime).getApplyStatus().equals(DriveStatusEnum.)).mapToDouble(CoachTeachTimeVo::getClassHours).sum();
        // 查询学员总课时 DriveStatusEnum  int sum = personList.stream().mapToInt(person -> person.getAge()).sum();
        //studentTrainCarApplyVos.stream().filter(coachTeachTime -> coachTeachTime.getApplyStatus().equals(DriveStatusEnum.DRIVING_SUCCESS.getCode())).mapToInt(train -> train.getClassHours()).sum();
        studentTrainCarApplyVos.stream().forEach((item) ->{
            // 统计
            if (item.getApplyStatus().equals(DriveStatusEnum.DRIVING_SUCCESS.getCode())){
                item.setClassHourTotal(studentTrainCarApplyVos.stream().mapToInt(train -> train.getClassHours()).sum());
            }
        });
        return R.success(studentTrainCarApplyVos);
    }

    @Override
    public ResObject noSubscribeSubjectTwoExamPageList(StudentTestEnrollPageQueryParam studentTestEnrollPageQueryParam) {
        log.info(this.getClass()+"noSubscribeSubjectOneExamPageList-方法请求参数{}",studentTestEnrollPageQueryParam);
        Page<StudentStudyEnrollEntity> page = new Page<>(studentTestEnrollPageQueryParam.getPageNum(), studentTestEnrollPageQueryParam.getPageSize());
        // 报名状态是报名的
        // 1.科目一考试合格10天的学员
        //2.科目二考试不合格10天的学员
        //3. 考试状态是取消
        // 条件查询
        QueryWrapper<StudentStudyEnrollEntity> queryWrapper = new QueryWrapper();
        // 运营商查询
        queryWrapper.eq(StrUtil.isNotEmpty(studentTestEnrollPageQueryParam.getOperatorId()),"operator_id",studentTestEnrollPageQueryParam.getOperatorId());
        // 报名完成
        queryWrapper.eq("enroll_status",StudyEnrollEnum.ENROLL_STATUS_ENROLL_COMPLETE.getCode());
        //queryWrapper.eq("enroll_status",StudyEnrollEnum.ENROLL_STATUS_ENROLL_COMPLETE.getCode());
        // 拼接sql 科目一考试合格10天的学员
        // 科目二考试不合格10天的学员

        queryWrapper.and(wrapper ->{
            wrapper.and(nameAgeQueryWrapper ->{
                nameAgeQueryWrapper.or(itemWrapper ->{
                    itemWrapper.gt("(SELECT COUNT(1) FROM t_student_test_enroll WHERE t_student_test_enroll.subject_type=1 AND DATE_ADD(t_student_test_enroll.test_actual_time,INTERVAL 10 DAY) <= NOW() AND t_student_test_enroll.student_id = t_student_study_enroll.student_id AND t_student_test_enroll.enroll_status=8)",0);
                });
                nameAgeQueryWrapper.or(itemWrapper ->{
                    itemWrapper.apply("(SELECT COUNT(1) FROM t_student_test_enroll WHERE t_student_test_enroll.subject_type=2 AND DATE_ADD(t_student_test_enroll.test_actual_time,INTERVAL 10 DAY) <= NOW() AND t_student_test_enroll.student_id = t_student_study_enroll.student_id AND t_student_test_enroll.enroll_status=9) <=0");

                });
                nameAgeQueryWrapper.or(itemWrapper ->{
                    itemWrapper.apply("(SELECT COUNT(1) FROM t_student_test_enroll WHERE t_student_test_enroll.subject_type = 2 AND t_student_test_enroll.student_id = t_student_study_enroll.student_id AND t_student_test_enroll.enroll_status = 6) > 0");

                });
            });
        });

        // 考试状态是取消
        // queryWrapper.gt("(SELECT COUNT(1) FROM t_student_test_enroll WHERE t_student_test_enroll.subject_type=2 AND t_student_test_enroll.student_id = t_student_study_enroll.student_id AND t_student_test_enroll.enroll_status=6)",0);
        queryWrapper.apply("(SELECT COUNT(1) FROM t_student_test_enroll WHERE t_student_test_enroll.subject_type=2 AND t_student_test_enroll.student_id = t_student_study_enroll.student_id AND t_student_test_enroll.enroll_status in(1,2,5,7,8,10)) <= 0");
        //
        queryWrapper.orderByDesc("create_time");
        IPage<StudentStudyEnrollEntity> studentStudyEnrollPageList = studentStudyEnrollService.page(page,queryWrapper);
        if (studentStudyEnrollPageList.getRecords().size() <=0){
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),studentStudyEnrollPageList);
        }
        IPage<StudentStudyEnrollVo> studentStudyEnrollVos = studentStudyEnrollMapStruct.toVoList(studentStudyEnrollPageList);
        studentStudyEnrollVos.getRecords().stream().forEach((item) ->{
            if (StrUtil.isNotEmpty(item.getUserId()))item.setOnlineServiceName(serviceInfoService.getById(item.getUserId()).getRealName());
            if (StrUtil.isNotEmpty(item.getLineUnderUserId()))item.setLineServiceName(serviceInfoService.getById(item.getLineUnderUserId()).getRealName());
            if (StrUtil.isNotEmpty(item.getDriveSchoolId())){
                DriveSchoolEntity driveSchoolEntity =driveSchoolService.getById(item.getLineUnderUserId());
                if (driveSchoolEntity != null)item.setLineServiceName(driveSchoolEntity.getSchoolName());
            }
            // 查询考试
            QueryWrapper examQueryWrapper = new QueryWrapper();
            examQueryWrapper.eq("student_id",item.getStudentId());
            examQueryWrapper.eq("subject_type",SubjectTypeEnum.SUBJECT_TWO.getCode());
            // studyQueryWrapper.eq("enroll_status",StudyEnrollEnum.ENROLL_STATUS_ENROLL_COMPLETE.getCode());
            examQueryWrapper.orderByDesc("test_actual_time");
            examQueryWrapper.last("limit 1");
            StudentTestEnrollEntity studentTestEnroll = studentTestEnrollService.getOne(examQueryWrapper);
            if (studentTestEnroll != null){
                // 考试单号
                item.setTestEnrollNo(studentTestEnroll.getTestEnrollNo());
                item.setStudentTestEnrollVo(BeanConvertUtils.copy(studentTestEnroll,StudentTestEnrollVo.class));
                item.setExamStatus(studentTestEnroll.getEnrollStatus());
                // 正常
            }

            // 查询次数
            QueryWrapper testQueryWrapper = new QueryWrapper();
            testQueryWrapper.eq("student_id",item.getStudentId());
            testQueryWrapper.eq("subject_type",SubjectTypeEnum.SUBJECT_TWO.getCode());
            String[] arr = {
                    StudyEnrollEnum.PAY_SUCCESS.getCode(),
                    StudyEnrollEnum.EXAM_ACCOMPLISH.getCode(),
                    StudyEnrollEnum.EXAM_PASS.getCode(),
                    StudyEnrollEnum.EXAM_NO_PASS.getCode(),
                    StudyEnrollEnum.APPLY_LOADING.getCode(),
                    StudyEnrollEnum.REFUND_DISPOSE_LOADING.getCode(),
                    StudyEnrollEnum.EXAM_REFUND_SUCCESS.getCode()
            };
            // 支付成功
            testQueryWrapper.in("enroll_status", arr);
            testQueryWrapper.eq("operator_id", item.getOperatorId());
            // 考试次数
            int examNumber = studentTestEnrollService.count(testQueryWrapper);
            item.setExamNumber(examNumber);
            QueryWrapper systemCoachStudentQueryWrapper = new QueryWrapper();
            // 学员ID
            systemCoachStudentQueryWrapper.eq("student_id",item.getStudentId());
            systemCoachStudentQueryWrapper.eq("bind_status",StatusEnum.NORMAL.getCode());
            OneFeeSystemCoachStudentEntity systemCoachStudent = oneFeeSystemCoachStudentService.getOne(systemCoachStudentQueryWrapper);
            if (systemCoachStudent != null){
                JSONObject jsonObject = JedisConnect.get(CacheConstants.REDIS_CACHE_COACH_KEY + systemCoachStudent.getCoachId());
                if (jsonObject != null)item.setBindCoach(jsonObject.getString("realName"));
            }
        });
        return R.success(studentStudyEnrollVos);
    }

    @Override
    public ResObject noSubscribeSubjectThreeExamPageList(StudentTestEnrollPageQueryParam studentTestEnrollPageQueryParam) {
        log.info(this.getClass()+"noSubscribeSubjectOneExamPageList-方法请求参数{}",studentTestEnrollPageQueryParam);
        Page<StudentStudyEnrollEntity> page = new Page<>(studentTestEnrollPageQueryParam.getPageNum(), studentTestEnrollPageQueryParam.getPageSize());
        // 学车报名单 包名完成
        //1.科目一考试满30天
        // 科目三考试不通过满10
        //科目三考试取消的
        QueryWrapper<StudentStudyEnrollEntity> queryWrapper = new QueryWrapper();
        // 运营商查询
        queryWrapper.eq(StrUtil.isNotEmpty(studentTestEnrollPageQueryParam.getOperatorId()),"operator_id",studentTestEnrollPageQueryParam.getOperatorId());
        // 报名完成
        queryWrapper.eq("enroll_status",StudyEnrollEnum.ENROLL_STATUS_ENROLL_COMPLETE.getCode());
        // 拼接SQL 没有预约过科目一考试
        // 拼接sql 考试挂科满10天
        //queryWrapper.gt("(SELECT COUNT(1) FROM t_student_test_enroll WHERE t_student_test_enroll.subject_type=1 AND DATE_ADD(t_student_test_enroll.test_actual_time,INTERVAL 30 DAY) > NOW() AND t_student_test_enroll.student_id = t_student_study_enroll.student_id)",0);
        //

        queryWrapper.and(wrapper ->{
            wrapper.and(nameAgeQueryWrapper ->{
                nameAgeQueryWrapper.or(itemWrapper ->{
                    itemWrapper.gt("(SELECT COUNT(1) FROM t_student_test_enroll WHERE t_student_test_enroll.subject_type=1 AND DATE_ADD(t_student_test_enroll.test_actual_time,INTERVAL 30 DAY) <= NOW() AND t_student_test_enroll.student_id = t_student_study_enroll.student_id AND t_student_test_enroll.enroll_status=8)",0);
                });
                nameAgeQueryWrapper.or(itemWrapper ->{
                    itemWrapper.apply("(SELECT COUNT(1) FROM t_student_test_enroll WHERE t_student_test_enroll.subject_type=3 AND DATE_ADD(t_student_test_enroll.test_actual_time,INTERVAL 10 DAY) <= NOW() AND t_student_test_enroll.student_id = t_student_study_enroll.student_id AND t_student_test_enroll.enroll_status=9) <=0");

                });
            });
        });
        // 考试状态是取消
        queryWrapper.gt("(SELECT COUNT(1) FROM t_student_test_enroll WHERE t_student_test_enroll.subject_type=3 AND t_student_test_enroll.student_id = t_student_study_enroll.student_id AND t_student_test_enroll.enroll_status=6)",0);
        queryWrapper.apply("(SELECT COUNT(1) FROM t_student_test_enroll WHERE t_student_test_enroll.subject_type=3 AND t_student_test_enroll.student_id = t_student_study_enroll.student_id AND t_student_test_enroll.enroll_status in(1,2,5,7,8,10)) <= 0");
        queryWrapper.orderByDesc("create_time");
        IPage<StudentStudyEnrollEntity> studentStudyEnrollPageList = studentStudyEnrollService.page(page,queryWrapper);
        if (studentStudyEnrollPageList.getRecords().size() <=0){
            return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_NULL.subMsg(),studentStudyEnrollPageList);
        }
        IPage<StudentStudyEnrollVo> studentStudyEnrollVos = studentStudyEnrollMapStruct.toVoList(studentStudyEnrollPageList);
        studentStudyEnrollVos.getRecords().stream().forEach((item) ->{
            if (StrUtil.isNotEmpty(item.getUserId()))item.setOnlineServiceName(serviceInfoService.getById(item.getUserId()).getRealName());
            if (StrUtil.isNotEmpty(item.getLineUnderUserId()))item.setLineServiceName(serviceInfoService.getById(item.getLineUnderUserId()).getRealName());
            if (StrUtil.isNotEmpty(item.getDriveSchoolId())){
                DriveSchoolEntity driveSchoolEntity =driveSchoolService.getById(item.getLineUnderUserId());
                if (driveSchoolEntity != null)item.setLineServiceName(driveSchoolEntity.getSchoolName());
            }

            // 查询报名单号
  /*          QueryWrapper studyQueryWrapper = new QueryWrapper();
            studyQueryWrapper.eq("student_id",item.getStudentId());
            studyQueryWrapper.eq("enroll_status",StudyEnrollEnum.ENROLL_STATUS_ENROLL_COMPLETE.getCode());
            StudentStudyEnrollEntity studentStudyEnroll = studentStudyEnrollService.getOne(studyQueryWrapper);
            if (studentStudyEnroll != null){
                item.setStudentStudyEnrollVo(BeanConvertUtils.copy(studentStudyEnroll,StudentStudyEnrollVo.class));
            }*/

            // 查询考试
            QueryWrapper examQueryWrapper = new QueryWrapper();
            examQueryWrapper.eq("student_id",item.getStudentId());
            examQueryWrapper.eq("subject_type",SubjectTypeEnum.SUBJECT_THREE.getCode());
            // studyQueryWrapper.eq("enroll_status",StudyEnrollEnum.ENROLL_STATUS_ENROLL_COMPLETE.getCode());
            examQueryWrapper.orderByDesc("test_actual_time");
            examQueryWrapper.last("limit 1");
            StudentTestEnrollEntity studentTestEnroll = studentTestEnrollService.getOne(examQueryWrapper);
            if (studentTestEnroll != null){
                // 考试单号
                item.setTestEnrollNo(studentTestEnroll.getTestEnrollNo());
                item.setStudentTestEnrollVo(BeanConvertUtils.copy(studentTestEnroll,StudentTestEnrollVo.class));
                item.setExamStatus(studentTestEnroll.getEnrollStatus());
                // 正常
            }

            // 查询次数
            QueryWrapper testQueryWrapper = new QueryWrapper();
            testQueryWrapper.eq("student_id",item.getStudentId());
            testQueryWrapper.eq("subject_type",SubjectTypeEnum.SUBJECT_THREE.getCode());
            String[] arr = {
                    StudyEnrollEnum.PAY_SUCCESS.getCode(),
                    StudyEnrollEnum.EXAM_ACCOMPLISH.getCode(),
                    StudyEnrollEnum.EXAM_PASS.getCode(),
                    StudyEnrollEnum.EXAM_NO_PASS.getCode(),
                    StudyEnrollEnum.APPLY_LOADING.getCode(),
                    StudyEnrollEnum.REFUND_DISPOSE_LOADING.getCode(),
                    StudyEnrollEnum.EXAM_REFUND_SUCCESS.getCode()
            };
            // 支付成功
            testQueryWrapper.in("enroll_status", arr);
            testQueryWrapper.eq("operator_id", item.getOperatorId());
            // 考试次数
            int examNumber = studentTestEnrollService.count(testQueryWrapper);
            item.setExamNumber(examNumber);
            QueryWrapper systemCoachStudentQueryWrapper = new QueryWrapper();
            // 学员ID
            systemCoachStudentQueryWrapper.eq("student_id",item.getStudentId());
            systemCoachStudentQueryWrapper.eq("bind_status",StatusEnum.NORMAL.getCode());
            OneFeeSystemCoachStudentEntity systemCoachStudent = oneFeeSystemCoachStudentService.getOne(systemCoachStudentQueryWrapper);
            if (systemCoachStudent != null){
                JSONObject jsonObject = JedisConnect.get(CacheConstants.REDIS_CACHE_COACH_KEY + systemCoachStudent.getCoachId());
                if (jsonObject != null)item.setBindCoach(jsonObject.getString("realName"));
            }
        });
        return R.success(studentStudyEnrollVos);
    }

    @Override
    public ResObject examPassStatisticsPageList(StudentTestEnrollPageQueryParam param) {
        log.info("examPassStatistics-方法请求参数{}",param);
        Page<StudentTestEnrollEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        // 条件查询
        QueryWrapper queryWrapper = this.getQueryWrapper(studentTestEnrollMapStruct, param);
        // 报名单号 模糊查询
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueTestEnrollNoSearch()),"test_enroll_no",param.getVagueTestEnrollNoSearch());
        // 科目类型 科目四
        queryWrapper.eq("subject_type", SubjectTypeEnum.SUBJECT_FOUR.getCode());
        // 状态
        queryWrapper.eq("enroll_status",StudyEnrollEnum.EXAM_PASS.getCode());

        // 意向报名时间 testActualTime
        queryWrapper.apply(StrUtil.isNotBlank(param.getTestHopeTimeSearch()),
                "date_format (test_hope_time,'%Y-%m-%d') = date_format('" + param.getTestHopeTimeSearch() + "','%Y-%m-%d')");
        // 报名单号 模糊查询
        //queryWrapper.like(StrUtil.isNotEmpty(param.getVagueStudyEnrollNoSearch()),"study_enroll_no",param.getVagueStudyEnrollNoSearch());
        //  开始时间 结束时间都有才进入
        if (StrUtil.isNotEmpty(param.getBeginTime()) && StrUtil.isNotEmpty(param.getEndTime())){
            queryWrapper.between(StrUtil.isNotEmpty(param.getBeginTime()),"create_time",param.getBeginTime(),param.getEndTime());
        }
        if (param.getExamPassTimeArr() != null && param.getExamPassTimeArr().length > 0){
            queryWrapper.between("date_format (test_actual_time,'%Y-%m-%d')",param.getExamPassTimeArr()[0],param.getExamPassTimeArr()[1]);
        }
        IPage<StudentTestEnrollEntity> pageList = studentTestEnrollService.page(page, queryWrapper);
        if (pageList.getRecords().size() <= 0){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }
        Page<StudentTestEnrollVo> studentTestEnrollVoPage = studentTestEnrollMapStruct.toVoList(pageList);
        // 循环数据
        studentTestEnrollVoPage.getRecords().stream().forEach((item) ->{
            if (StrUtil.isNotEmpty(item.getStudentId())){
                StudentInfoEntity studentInfo = studentInfoService.getById(item.getStudentId());
                if (studentInfo != null)item.setStudentName(studentInfo.getRealName());
                if (studentInfo != null)item.setPhone(studentInfo.getPhone());
            }
            if (StrUtil.isNotEmpty(item.getUserId()))item.setOnlineServiceName(serviceInfoService.getById(item.getUserId()).getRealName());
            if (StrUtil.isNotEmpty(item.getLineUnderUserId()))item.setLineServiceName(serviceInfoService.getById(item.getLineUnderUserId()).getRealName());
            // 平台训练场地
            if (StrUtil.isNotEmpty(item.getTestActualCoachingGridId())){
                CoachingGridEntity coachingGridEntity =coachingGridService.getById(item.getTestActualCoachingGridId());
                if(coachingGridEntity != null)item.setTestActualCoachingGridName(coachingGridEntity.getName());
            };
            if (StrUtil.isNotEmpty(item.getTestHopeCoachingGridId())){
                CoachingGridEntity coachingGrid = coachingGridService.getById(item.getTestHopeCoachingGridId());
                if(coachingGrid != null)item.setTestHopeCoachingGridName(coachingGrid.getName());
            }

            // 查询报名单号
            QueryWrapper studyQueryWrapper = new QueryWrapper();
            studyQueryWrapper.eq("student_id",item.getStudentId());
            studyQueryWrapper.eq("enroll_status",StudyEnrollEnum.ENROLL_STATUS_ENROLL_COMPLETE.getCode());
            StudentStudyEnrollEntity studentStudyEnroll = studentStudyEnrollService.getOne(studyQueryWrapper);
            if (studentStudyEnroll != null){
                item.setStudentStudyEnrollVo(BeanConvertUtils.copy(studentStudyEnroll,StudentStudyEnrollVo.class));
            }
            QueryWrapper feeSystemCoachStudentQueryWrapper = new QueryWrapper();
            // 学员ID
            feeSystemCoachStudentQueryWrapper.eq("student_id",item.getStudentId());
            feeSystemCoachStudentQueryWrapper.eq("bind_status", StatusEnum.NORMAL.getCode());
            OneFeeSystemCoachStudentEntity oneFeeSystemCoachStudent = oneFeeSystemCoachStudentService.getOne(feeSystemCoachStudentQueryWrapper);
            if (oneFeeSystemCoachStudent != null) {
                OneFeeSystemCoachStudentVo feeSystemCoachStudent = BeanConvertUtils.copy(oneFeeSystemCoachStudent,OneFeeSystemCoachStudentVo.class);
                log.info("教练绑定数据{}",feeSystemCoachStudent);
                JSONObject jsonObject = JedisConnect.get(CacheConstants.REDIS_CACHE_COACH_KEY+feeSystemCoachStudent.getCoachId());
                if (jsonObject != null)item.setCoachName(jsonObject.getString("realName"));
                OneFeeSystemPriceEntity oneFeeSystemPrice = oneFeeSystemPriceService.getById(feeSystemCoachStudent.getClassId());
                if (oneFeeSystemPrice != null)item.setClassName(oneFeeSystemPrice.getName());
            }
            // 订单
            QueryWrapper orderQueryWrapper = new QueryWrapper();
            String[] arr = {
                    StudyEnrollEnum.PAY_SUCCESS.getCode(),
                    StudyEnrollEnum.STAT_EVALUATE.getCode(),
                    StudyEnrollEnum.EVALUATE_SUCCESS.getCode(),
            };
            orderQueryWrapper.eq("student_id",item.getStudentId());
            orderQueryWrapper.in("status",arr);
            List<StudentOrderEntity> studentOrderList = studentOrderService.list(orderQueryWrapper);

            //支付金额求和
            BigDecimal totalAmount = studentOrderList.stream().map(StudentOrderEntity::getPayableAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
            item.setTotalAmount(totalAmount);
        });
        log.info(this.getClass() + "pageList-方法请求结果{}",studentTestEnrollVoPage);
        return R.success(studentTestEnrollVoPage);
    }

    @Override
    public ResObject noSubscribeSubjectFourExamPageList(StudentTestEnrollPageQueryParam studentTestEnrollPageQueryParam) {
        log.info(this.getClass()+"noSubscribeSubjectOneExamPageList-方法请求参数{}",studentTestEnrollPageQueryParam);
        Page<StudentStudyEnrollEntity> page = new Page<>(studentTestEnrollPageQueryParam.getPageNum(), studentTestEnrollPageQueryParam.getPageSize());
        // 学车报名单 包名完成
        //2.科目二考试通过

        //3.科目三考试通过
        // 条件查询
        QueryWrapper queryWrapper = new QueryWrapper();
        // 运营商查询
        queryWrapper.eq(StrUtil.isNotEmpty(studentTestEnrollPageQueryParam.getOperatorId()),"operator_id",studentTestEnrollPageQueryParam.getOperatorId());
        // 报名完成
        queryWrapper.eq("enroll_status",StudyEnrollEnum.ENROLL_STATUS_ENROLL_COMPLETE.getCode());
        // 拼接SQL 没有预约过科目一考试
        // 拼接sql 考试挂科满10天
        queryWrapper.gt("(SELECT COUNT(1) FROM t_student_test_enroll WHERE t_student_test_enroll.subject_type=1 AND t_student_test_enroll.enroll_status=8 AND t_student_test_enroll.student_id = t_student_study_enroll.student_id)",0);
        queryWrapper.gt("(SELECT COUNT(1) FROM t_student_test_enroll WHERE t_student_test_enroll.subject_type=2 AND t_student_test_enroll.enroll_status=8 AND t_student_test_enroll.student_id = t_student_study_enroll.student_id)",0);
        queryWrapper.gt("(SELECT COUNT(1) FROM t_student_test_enroll WHERE t_student_test_enroll.subject_type=3 AND t_student_test_enroll.enroll_status=8 AND t_student_test_enroll.student_id = t_student_study_enroll.student_id)",0);
        //
        queryWrapper.apply("(SELECT COUNT(1) FROM t_student_test_enroll WHERE t_student_test_enroll.subject_type=4 AND t_student_test_enroll.student_id = t_student_study_enroll.student_id AND t_student_test_enroll.enroll_status in(1,2,5,7,8,10)) <= 0");
        queryWrapper.orderByDesc("create_time");
        IPage<StudentStudyEnrollEntity> studentStudyEnrollPageList = studentStudyEnrollService.page(page,queryWrapper);
        if (studentStudyEnrollPageList.getRecords().size() <=0){
            return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_NULL.subMsg(),studentStudyEnrollPageList);
        }
        IPage<StudentStudyEnrollVo> studentStudyEnrollVos = studentStudyEnrollMapStruct.toVoList(studentStudyEnrollPageList);
        studentStudyEnrollVos.getRecords().stream().forEach((item) ->{
            if (StrUtil.isNotEmpty(item.getUserId()))item.setOnlineServiceName(serviceInfoService.getById(item.getUserId()).getRealName());
            if (StrUtil.isNotEmpty(item.getLineUnderUserId()))item.setLineServiceName(serviceInfoService.getById(item.getLineUnderUserId()).getRealName());
            if (StrUtil.isNotEmpty(item.getDriveSchoolId())){
                DriveSchoolEntity driveSchoolEntity =driveSchoolService.getById(item.getLineUnderUserId());
                if (driveSchoolEntity != null)item.setLineServiceName(driveSchoolEntity.getSchoolName());
            }

            // 查询考试
            QueryWrapper examQueryWrapper = new QueryWrapper();
            examQueryWrapper.eq("student_id",item.getStudentId());
            examQueryWrapper.eq("subject_type",SubjectTypeEnum.SUBJECT_FOUR.getCode());
            // studyQueryWrapper.eq("enroll_status",StudyEnrollEnum.ENROLL_STATUS_ENROLL_COMPLETE.getCode());
            examQueryWrapper.orderByDesc("test_actual_time");
            examQueryWrapper.last("limit 1");
            StudentTestEnrollEntity studentTestEnroll = studentTestEnrollService.getOne(examQueryWrapper);
            if (studentTestEnroll != null){
                item.setStudentTestEnrollVo(BeanConvertUtils.copy(studentTestEnroll,StudentTestEnrollVo.class));
                // 考试单号
                item.setTestEnrollNo(studentTestEnroll.getTestEnrollNo());
                item.setExamStatus(studentTestEnroll.getEnrollStatus());
                // 正常
            }

            // 查询次数
            QueryWrapper testQueryWrapper = new QueryWrapper();
            testQueryWrapper.eq("student_id",item.getStudentId());
            testQueryWrapper.eq("subject_type","1");
            String[] arr = {
                    StudyEnrollEnum.PAY_SUCCESS.getCode(),
                    StudyEnrollEnum.EXAM_ACCOMPLISH.getCode(),
                    StudyEnrollEnum.EXAM_PASS.getCode(),
                    StudyEnrollEnum.EXAM_NO_PASS.getCode(),
                    StudyEnrollEnum.APPLY_LOADING.getCode(),
                    StudyEnrollEnum.REFUND_DISPOSE_LOADING.getCode(),
                    StudyEnrollEnum.EXAM_REFUND_SUCCESS.getCode()
            };
            // 支付成功
            testQueryWrapper.in("enroll_status", arr);
            testQueryWrapper.eq("operator_id", item.getOperatorId());
            // 考试次数
            int examNumber = studentTestEnrollService.count(testQueryWrapper);
            item.setExamNumber(examNumber);
            QueryWrapper systemCoachStudentQueryWrapper = new QueryWrapper();
            // 学员ID
            systemCoachStudentQueryWrapper.eq("student_id",item.getStudentId());
            systemCoachStudentQueryWrapper.eq("bind_status",StatusEnum.NORMAL.getCode());
            OneFeeSystemCoachStudentEntity systemCoachStudent = oneFeeSystemCoachStudentService.getOne(systemCoachStudentQueryWrapper);
            if (systemCoachStudent != null){
                JSONObject jsonObject = JedisConnect.get(CacheConstants.REDIS_CACHE_COACH_KEY + systemCoachStudent.getCoachId());
                if (jsonObject != null)item.setBindCoach(jsonObject.getString("realName"));
            }
        });
        return R.success(studentStudyEnrollVos);
    }

    @Override
    public ResObject examLoadingPageList(StudentTestEnrollPageQueryParam studentTestEnrollPageQueryParam) {
        Page<StudentTestEnrollEntity> page = new Page<>(studentTestEnrollPageQueryParam.getPageNum(), studentTestEnrollPageQueryParam.getPageSize());
        //  考试中 考试报名单状态预约成功
        //  考试中 考实际考试时间 今天
        QueryWrapper queryWrapper = new QueryWrapper();
        // 运营商查询
        queryWrapper.eq(StrUtil.isNotEmpty(studentTestEnrollPageQueryParam.getOperatorId()),"operator_id",studentTestEnrollPageQueryParam.getOperatorId());
        // 考试报名单状态预约成功
        queryWrapper.eq("enroll_status",StudyEnrollEnum.BOOK_SUCCESS.getCode());
        // 科目类型查询
        queryWrapper.eq("subject_type",studentTestEnrollPageQueryParam.getSubjectType());
        // 考实际考试时间 今天
        queryWrapper.apply("date_format (test_hope_time,'%Y-%m-%d') <= date_format(now(),'%Y-%m-%d')");
        IPage<StudentTestEnrollEntity> studentTestEnrollPageList = studentTestEnrollService.page(page,queryWrapper);
        if (studentTestEnrollPageList.getRecords().size() <=0){
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),studentTestEnrollPageList);
        }
        IPage<StudentTestEnrollVo> studentTestEnrollVoPageList = studentTestEnrollMapStruct.toVoList(studentTestEnrollPageList);
        studentTestEnrollVoPageList.getRecords().stream().forEach((item) ->{
            if (StrUtil.isNotEmpty(item.getStudentId()))item.setStudentName(studentInfoService.getById(item.getStudentId()).getRealName());
            if (StrUtil.isNotEmpty(item.getUserId()))item.setOnlineServiceName(serviceInfoService.getById(item.getUserId()).getRealName());
            if (StrUtil.isNotEmpty(item.getLineUnderUserId()))item.setLineServiceName(serviceInfoService.getById(item.getLineUnderUserId()).getRealName());
            // 省市区
            // 平台训练场地
            if (StrUtil.isNotEmpty(item.getTestActualCoachingGridId())){
                CoachingGridEntity coachingGridEntity =coachingGridService.getById(item.getTestActualCoachingGridId());
                if(coachingGridEntity != null)item.setTestActualCoachingGridName(coachingGridEntity.getName());
            };
            if (StrUtil.isNotEmpty(item.getTestHopeCoachingGridId())){
                CoachingGridEntity coachingGrid = coachingGridService.getById(item.getTestHopeCoachingGridId());
                if(coachingGrid != null)item.setTestHopeCoachingGridName(coachingGrid.getName());
            }

            // 查询报名单号
            QueryWrapper studyQueryWrapper = new QueryWrapper();
            studyQueryWrapper.eq("student_id",item.getStudentId());
            studyQueryWrapper.eq("enroll_status",StudyEnrollEnum.ENROLL_STATUS_ENROLL_COMPLETE.getCode());
            StudentStudyEnrollEntity studentStudyEnroll = studentStudyEnrollService.getOne(studyQueryWrapper);
            if (studentStudyEnroll != null){
                item.setStudentStudyEnrollVo(BeanConvertUtils.copy(studentStudyEnroll,StudentStudyEnrollVo.class));
            }

            QueryWrapper systemCoachStudentQueryWrapper = new QueryWrapper();
            // 学员ID
            systemCoachStudentQueryWrapper.eq("student_id",item.getStudentId());
            systemCoachStudentQueryWrapper.eq("bind_status",StatusEnum.NORMAL.getCode());
            OneFeeSystemCoachStudentEntity systemCoachStudent = oneFeeSystemCoachStudentService.getOne(systemCoachStudentQueryWrapper);
            if (systemCoachStudent != null){
                JSONObject jsonObject = JedisConnect.get(CacheConstants.REDIS_CACHE_COACH_KEY + systemCoachStudent.getCoachId());
                if (jsonObject != null)item.setBindCoach(jsonObject.getString("realName"));
            }

        });
        return R.success(studentTestEnrollVoPageList);
    }

    @Override
    public ResObject completeExamEnroll(CompleteStudyEnrollParam completeStudyEnrollParam) {
        log.info(this.getClass() + "completeStudyEnroll-方法请求参数{}",completeStudyEnrollParam);
        if (completeStudyEnrollParam == null){
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        String strategyValue = ExamEnrollEnum.getStrategyValueByCode(completeStudyEnrollParam.getEnrollStatus());
        if (StrUtil.isEmpty(strategyValue)){
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL);
        }
        StudyEnrollStrategy studyEnrollStrategy = SpringContextUtil.getBean(strategyValue,StudyEnrollStrategy.class);
        return studyEnrollStrategy.completeExamEnroll(completeStudyEnrollParam);
    }


}

