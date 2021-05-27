package com.drive.admin.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.drive.admin.pojo.entity.SysTaskEntity;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.drive.common.core.base.BaseController;
import com.drive.admin.repository.SysTaskRepository;
import com.drive.admin.pojo.entity.*;
import com.drive.admin.pojo.vo.*;
import com.drive.admin.pojo.dto.*;
import com.drive.admin.service.mapstruct.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.hutool.core.util.StrUtil;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.SubResultCode;
import com.drive.admin.job.JobManager;
import com.drive.common.core.utils.BeanConvertUtils;
import com.drive.common.security.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import com.drive.common.core.biz.ResObject;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.mockito.internal.util.collections.ListUtil;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import com.drive.admin.service.SysTaskService;
import com.drive.common.data.utils.ExcelUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Arrays;
import java.io.IOException;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

                                                
/**
 *
 * 系统任务表 服务类
 *
 * @author chentao
 */
@Slf4j
@Service
public class  SysTaskRepositoryImpl extends BaseController<SysTaskPageQueryParam, SysTaskEntity>  implements SysTaskRepository{

    //  系统任务表 服务
    @Autowired
    private SysTaskService sysTaskService;
    //  系统任务表 DO-DTO转化
    @Autowired
    private SysTaskMapStruct sysTaskMapStruct;

    /*
     *
     *功能描述
     * @author chentao
     * @description 系统任务表 分页列表
     * @date 2020/2/12 17:09
     * @param  * @param SysTaskPageQueryParam
     * @return
     */
    @Override
    public ResObject pageList(SysTaskPageQueryParam param) {
        log.info(this.getClass() + "pageList-方法请求参数{}",param);
        Page<SysTaskEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        // 条件查询
        QueryWrapper queryWrapper = this.getQueryWrapper(sysTaskMapStruct, param);
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueTaskIdSearch()),"st_task_id",param.getVagueTaskIdSearch());
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueTaskNameSearch()),"st_task_name",param.getVagueTaskNameSearch());
        //  模糊查询
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueNameSearch()),"name",param.getVagueNameSearch());
        //  开始时间 结束时间都有才进入
        if (StrUtil.isNotEmpty(param.getBeginTime()) && StrUtil.isNotEmpty(param.getEndTime())){
            queryWrapper.between(StrUtil.isNotEmpty(param.getBeginTime()),"create_time",param.getBeginTime(),param.getEndTime());
        }
        IPage<SysTaskEntity> pageList = sysTaskService.page(page, queryWrapper);
        if (pageList.getRecords().size() <= 0){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }
        try {
            //从任务调度器当中获取任务执行状态
            List<SysTaskEntity> taskList = pageList.getRecords();
            //获取任务调度器scheduler
            Scheduler scheduler = JobManager.getScheduler();
            for (SysTaskEntity sysTaskEntity : taskList) {
                if(sysTaskEntity != null){
                    String taskId = sysTaskEntity.getStTaskId();
                    //获取jobKey name就是taskId
                    JobKey jobKey = JobManager.getJobKey(taskId);
                    List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
                    if(CollectionUtils.isNotEmpty(triggers)){
                        scheduler.getTriggerState(triggers.get(0).getKey()).toString();
                    }
                }
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        Page<SysTaskVo> sysTaskVoPage = sysTaskMapStruct.toVoList(pageList);
        log.info(this.getClass() + "pageList-方法请求结果{}",sysTaskVoPage);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),sysTaskVoPage);
    }

    /*
     *
     *功能描述
     * @author chentao
     * @description 系统任务表 查询列表
     * @date 2020/2/12 17:09
     * @param  * @param SysTaskPageQueryParam
     * @return
     */
    @Override
    public ResObject findList(SysTaskPageQueryParam param) {
        log.info(this.getClass() + "findList-方法请求参数{}",param);
        // 这里判断条件进行查询
        QueryWrapper queryWrapper= this.getQueryWrapper(sysTaskMapStruct, param);
        // 通过名称查询
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueNameSearch()),"name",param.getVagueNameSearch());
        // 如 queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"phone",param.getPhone());
        List<SysTaskEntity> sysTaskList = sysTaskService.list(queryWrapper);
        if (sysTaskList == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),sysTaskList);
        }
        List<SysTaskVo> sysTaskVoList = sysTaskMapStruct.toVoList(sysTaskList);
        log.info(this.getClass() + "findList-方法请求结果{}",sysTaskVoList);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),sysTaskVoList);
    }

    /**
    * 对象条件查询返回单条系统任务表数据
    * @param param
    * @return
    */
    @Override
    public ResObject getInfo(SysTaskPageQueryParam param) {
        log.info(this.getClass() + "getInfo-方法请求参数{}",param);
        if (param == null){
            return R.failure("数据空");
        }
        // 这里判断条件进行查询
        QueryWrapper queryWrapper = this.getQueryWrapper(sysTaskMapStruct, param);
        // 这里有且只返回一条数据
        queryWrapper.last("limit 1");
        SysTaskEntity sysTask = sysTaskService.getOne(queryWrapper);
        if (sysTask == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),sysTask);
        }
        SysTaskVo sysTaskVo = BeanConvertUtils.copy(sysTask, SysTaskVo.class);
        log.info(this.getClass() + "getInfo-方法请求结果{}",sysTaskVo);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),sysTaskVo);
    }

    /*
     *
     *功能描述
     * @author chentao
     * @description 通过ID获取 系统任务表 单条数据
     * @date 2020/2/12 17:09
     * @param  * @param SysTaskPageQueryParam
     * @return
     */
    @Override
    public ResObject getById(String stTaskId) {
        log.info(this.getClass() + "getById-方法请求参数{}",stTaskId);
        if (StrUtil.isEmpty(stTaskId)){
            return R.failure("数据空");
        }
        // 通过ID 获取 单条数据
        SysTaskEntity sysTask = sysTaskService.getById(stTaskId);
        if (sysTask == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),sysTask);
        }
        SysTaskVo sysTaskVo = BeanConvertUtils.copy(sysTask, SysTaskVo.class);
        log.info(this.getClass() + "getById-方法请求结果{}",sysTaskVo);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),sysTaskVo);
    }

    /*
     *
     *功能描述
     * @author chentao
     * @description 保存系统任务表 数据,同时往任务管理器中添加一个任务
     * @date 2020/2/12 17:09
     * @param  * @param SysTaskPageQueryParam
     * @return
     */
    @Override
    public ResObject save(SysTaskInstallParam installParam) {
        Class<?> jobClass = null;
        try {
            jobClass = Class.forName(installParam.getStJobClass());
        } catch (ClassNotFoundException e) {
            log.error("添加定时任务的类出错:找不到类{}",installParam.getStJobClass());
            return R.failure(SubResultCode.PARAMISINVALID.subCode(),"定时任务类错误,找不到该类");
        }
        if(jobClass != null && !Job.class.isAssignableFrom(jobClass)){
            log.error("添加定时任务的类出错:{} 定时任务类必须实现Job接口",installParam.getStJobClass());
            return R.failure(SubResultCode.PARAMISINVALID.subCode(),"定时任务类错误,类没有继承Job接口");
        }
        installParam.setCreateUser(SecurityUtils.getUsername());
        log.info(this.getClass() + "save方法请求参数{}",installParam);
        if (installParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        SysTaskEntity sysTask = BeanConvertUtils.copy(installParam, SysTaskEntity.class);
        Boolean result = sysTaskService.save(sysTask);
        log.info(this.getClass() + "save-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_INSTALL_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_INSTALL_FAILL.subCode(),SubResultCode.DATA_INSTALL_FAILL.subMsg());
    }

    /*
     *
     *功能描述
     * @author chentao
     * @description  修改系统任务表 数据
     * @date 2020/2/12 17:09
     * @param  * @param SysTaskPageQueryParam
     * @return
     */
    @Override
    public ResObject update(SysTaskEditParam updateParam) {
        updateParam.setUpdateUser(SecurityUtils.getUsername());
        log.info(this.getClass() + "update方法请求参数{}",updateParam);
        if (updateParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        SysTaskEntity sysTask = BeanConvertUtils.copy(updateParam, SysTaskEntity.class);
        Boolean result = sysTaskService.updateById(sysTask);
        log.info(this.getClass() + "update-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_UPDATE_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_UPDATE_FAILL.subCode(),SubResultCode.DATA_UPDATE_FAILL.subMsg());
    }

    /*
     *
     *功能描述
     * @author chentao
     * @description 数组删除系统任务表 数据
     * @date 2020/2/12 17:09
     * @param  * @param SysTaskPageQueryParam
     * @return
     */
    @Override
    public ResObject deleteByIds(String[] ids) {
        if (ids.length <= 0){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        return R.toRes(sysTaskService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * *通过id删除系统任务表 信息
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
        Boolean result = sysTaskService.removeById(id);
        log.info(this.getClass() + "deleteById-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_DELETE_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_DELETE_FAILL.subCode(),SubResultCode.DATA_DELETE_FAILL.subMsg());
    }

    /**
     * *导出系统任务表 信息
     **/
    @Override
    public ResObject exportXls(SysTaskPageQueryParam param, HttpServletResponse response)throws IOException {
        log.info(this.getClass() + "exportXls方法请求参数{}",param);
        QueryWrapper queryWrapper = this.getQueryWrapper(sysTaskMapStruct, param);
        List<SysTaskEntity> list = sysTaskService.list(queryWrapper);
        List<SysTaskVo>sysTaskList = sysTaskMapStruct.toVoList(list);
        ExcelUtils.exportExcel(sysTaskList, SysTaskVo.class, "", new ExportParams(), response);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.EXPORT_DATA_SUCCESS.subMsg());
    }

    /**
     * *修改状态
     **/
    @Override
    public ResObject changeStatus(SysTaskEditParam param) {
        log.info(this.getClass() + "changeStatus方法请求参数{}",param);
        if (StrUtil.isEmpty(param.getStTaskId())){
            log.error("ID数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        SysTaskEntity SysTaskEntity = new SysTaskEntity();
        SysTaskEntity.setStTaskId(param.getStTaskId());
        SysTaskEntity.setStState(param.getStState());
        //SysTaskEntity.setUpdateTime()
        Boolean result = sysTaskService.updateById(SysTaskEntity);
        log.info(this.getClass() +"changeStatus方法请求对象参数{}，请求结果{}",SysTaskEntity,result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_STATUS_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_STATUS_FAILL.subCode(),SubResultCode.DATA_STATUS_FAILL.subMsg());
    }

    /**
     * 开启定时任务,如果已经存在就恢复执行,不存在则加入job
     * @param taskId 任务id编号
     * @return
     */
    @Override
    public ResObject startTask(String taskId) {
        Class<?> obj = null;
        SysTaskEntity sysTask = sysTaskService.getById(taskId);
        try {
            List<Map<String, Object>> allJobs = JobManager.getAllJobs();
            JobKey jobKey = JobManager.getJobKey(taskId);
            //任务存在则恢复执行
            if(jobKey != null){
                JobManager.resumeJob(taskId);
                return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),"恢复执行");
            }
            obj = Class.forName(sysTask.getStJobClass());
            JobManager.addJob(taskId,taskId,sysTask.getStCronExpression(),(Class<? extends Job>) obj);
            return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_STATUS_SUCCESS.subMsg());
        } catch (Exception e) {
            log.error("表达式错误或者"+obj.getName()+"没有继承Job接口");
        }
        return null;
    }

    /**
     * 关闭定时任务
     * @param taskId 定时任务id编号
     * @return
     */
    @Override
    public ResObject endTask(String taskId) {
        try {
            JobManager.pauseJob(taskId);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),"暂停了定时任务执行");
    }
}

