package com.drive.admin.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.drive.admin.enums.ExamEnrollEnum;
import com.drive.admin.pojo.entity.StudentStudyProgressHistoryEntity;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.drive.common.core.base.BaseController;
import com.drive.admin.repository.StudentStudyProgressHistoryRepository;
import com.drive.admin.pojo.entity.*;
import com.drive.admin.pojo.vo.*;
import com.drive.admin.pojo.dto.*;
import com.drive.admin.service.mapstruct.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.hutool.core.util.StrUtil;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.SubResultCode;
import com.drive.common.core.utils.BeanConvertUtils;
import lombok.extern.slf4j.Slf4j;
import com.drive.common.core.biz.ResObject;
import org.springframework.beans.factory.annotation.Autowired;
import com.drive.admin.service.StudentStudyProgressHistoryService;
import com.drive.common.data.utils.ExcelUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Arrays;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Service;

                                
/**
 *
 * 学员学车报名单 服务类
 *
 * @author xiaoguo
 */
@Slf4j
@Service
public class  StudentStudyProgressHistoryRepositoryImpl extends BaseController<StudentStudyProgressHistoryPageQueryParam, StudentStudyProgressHistoryEntity>  implements StudentStudyProgressHistoryRepository{

    //  学员学车报名单 服务
    @Autowired
    private StudentStudyProgressHistoryService studentStudyProgressHistoryService;
    //  学员学车报名单 DO-DTO转化
    @Autowired
    private StudentStudyProgressHistoryMapStruct studentStudyProgressHistoryMapStruct;

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 学员学车报名单 分页列表
     * @date 2020/2/12 17:09
     * @param  * @param StudentStudyProgressHistoryPageQueryParam
     * @return
     */
    @Override
    public ResObject pageList(StudentStudyProgressHistoryPageQueryParam param) {
        log.info(this.getClass() + "pageList-方法请求参数{}",param);
        Page<StudentStudyProgressHistoryEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        // 条件查询
        QueryWrapper queryWrapper = this.getQueryWrapper(studentStudyProgressHistoryMapStruct, param);

        //  模糊查询
        //queryWrapper.like(StrUtil.isNotEmpty(param.getVagueNameSearch()),"name",param.getVagueNameSearch());
        //  开始时间 结束时间都有才进入
        if (StrUtil.isNotEmpty(param.getBeginTime()) && StrUtil.isNotEmpty(param.getEndTime())){
            queryWrapper.between(StrUtil.isNotEmpty(param.getBeginTime()),"create_time",param.getBeginTime(),param.getEndTime());
        }
        IPage<StudentStudyProgressHistoryEntity> pageList = studentStudyProgressHistoryService.page(page, queryWrapper);
        if (pageList.getRecords().size() <= 0){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }
        Page<StudentStudyProgressHistoryVo> studentStudyProgressHistoryVoPage = studentStudyProgressHistoryMapStruct.toVoList(pageList);
        log.info(this.getClass() + "pageList-方法请求结果{}",studentStudyProgressHistoryVoPage);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),studentStudyProgressHistoryVoPage);
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 学员学车报名单 查询列表
     * @date 2020/2/12 17:09
     * @param  * @param StudentStudyProgressHistoryPageQueryParam
     * @return
     */
    @Override
    public ResObject findList(StudentStudyProgressHistoryPageQueryParam param) {
        log.info(this.getClass() + "findList-方法请求参数{}",param);
        // 这里判断条件进行查询
        QueryWrapper queryWrapper= this.getQueryWrapper(studentStudyProgressHistoryMapStruct, param);
        // 如 queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"phone",param.getPhone());
        List<StudentStudyProgressHistoryEntity> studentStudyProgressHistoryList = studentStudyProgressHistoryService.list(queryWrapper);
        if (studentStudyProgressHistoryList == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),studentStudyProgressHistoryList);
        }
        List<StudentStudyProgressHistoryVo> studentStudyProgressHistoryVoList = studentStudyProgressHistoryMapStruct.toVoList(studentStudyProgressHistoryList);
        log.info(this.getClass() + "findList-方法请求结果{}",studentStudyProgressHistoryVoList);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),studentStudyProgressHistoryVoList);
    }

    /**
    * 对象条件查询返回单条学员学车报名单数据
    * @param param
    * @return
    */
    @Override
    public ResObject getInfo(StudentStudyProgressHistoryPageQueryParam param) {
        log.info(this.getClass() + "getInfo-方法请求参数{}",param);
        if (param == null){
            return R.failure("数据空");
        }
        // 这里判断条件进行查询
        QueryWrapper queryWrapper = this.getQueryWrapper(studentStudyProgressHistoryMapStruct, param);
        StudentStudyProgressHistoryEntity studentStudyProgressHistory = studentStudyProgressHistoryService.getOne(queryWrapper);
        if (studentStudyProgressHistory == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),studentStudyProgressHistory);
        }
        StudentStudyProgressHistoryVo studentStudyProgressHistoryVo = BeanConvertUtils.copy(studentStudyProgressHistory, StudentStudyProgressHistoryVo.class);
        log.info(this.getClass() + "getInfo-方法请求结果{}",studentStudyProgressHistoryVo);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),studentStudyProgressHistoryVo);
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 通过ID获取 学员学车报名单 单条数据
     * @date 2020/2/12 17:09
     * @param  * @param StudentStudyProgressHistoryPageQueryParam
     * @return
     */
    @Override
    public ResObject getById(String id) {
        log.info(this.getClass() + "getById-方法请求参数{}",id);
        if (StrUtil.isEmpty(id)){
            return R.failure("数据空");
        }
        // 通过ID 获取 单条数据
        StudentStudyProgressHistoryEntity studentStudyProgressHistory = studentStudyProgressHistoryService.getById(id);
        if (studentStudyProgressHistory == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),studentStudyProgressHistory);
        }
        StudentStudyProgressHistoryVo studentStudyProgressHistoryVo = BeanConvertUtils.copy(studentStudyProgressHistory, StudentStudyProgressHistoryVo.class);
        log.info(this.getClass() + "getById-方法请求结果{}",studentStudyProgressHistoryVo);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),studentStudyProgressHistoryVo);
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 保存学员学车报名单 数据
     * @date 2020/2/12 17:09
     * @param  * @param StudentStudyProgressHistoryPageQueryParam
     * @return
     */
    @Override
    public ResObject save(StudentStudyProgressHistoryInstallParam installParam) {
        log.info(this.getClass() + "save方法请求参数{}",installParam);
        if (installParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        StudentStudyProgressHistoryEntity studentStudyProgressHistory = BeanConvertUtils.copy(installParam, StudentStudyProgressHistoryEntity.class);
        Boolean result = studentStudyProgressHistoryService.save(studentStudyProgressHistory);
        log.info(this.getClass() + "save-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_INSTALL_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_INSTALL_FAILL.subCode(),SubResultCode.DATA_INSTALL_FAILL.subMsg());
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description  修改学员学车报名单 数据
     * @date 2020/2/12 17:09
     * @param  * @param StudentStudyProgressHistoryPageQueryParam
     * @return
     */
    @Override
    public ResObject update(StudentStudyProgressHistoryEditParam updateParam) {
        log.info(this.getClass() + "update方法请求参数{}",updateParam);
        if (updateParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        StudentStudyProgressHistoryEntity studentStudyProgressHistory = BeanConvertUtils.copy(updateParam, StudentStudyProgressHistoryEntity.class);
        Boolean result = studentStudyProgressHistoryService.updateById(studentStudyProgressHistory);
        log.info(this.getClass() + "update-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_UPDATE_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_UPDATE_FAILL.subCode(),SubResultCode.DATA_UPDATE_FAILL.subMsg());
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 数组删除学员学车报名单 数据
     * @date 2020/2/12 17:09
     * @param  * @param StudentStudyProgressHistoryPageQueryParam
     * @return
     */
    @Override
    public ResObject deleteByIds(String[] ids) {
        if (ids.length <= 0){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        return R.toRes(studentStudyProgressHistoryService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * *通过id删除学员学车报名单 信息
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
        Boolean result = studentStudyProgressHistoryService.removeById(id);
        log.info(this.getClass() + "deleteById-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_DELETE_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_DELETE_FAILL.subCode(),SubResultCode.DATA_DELETE_FAILL.subMsg());
    }

    /**
     * *导出学员学车报名单 信息
     **/
    @Override
    public ResObject exportXls(StudentStudyProgressHistoryPageQueryParam param, HttpServletResponse response)throws IOException {
        log.info(this.getClass() + "exportXls方法请求参数{}",param);
        QueryWrapper queryWrapper = this.getQueryWrapper(studentStudyProgressHistoryMapStruct, param);
        List<StudentStudyProgressHistoryEntity> list = studentStudyProgressHistoryService.list(queryWrapper);
        List<StudentStudyProgressHistoryVo>studentStudyProgressHistoryList = studentStudyProgressHistoryMapStruct.toVoList(list);
        ExcelUtils.exportExcel(studentStudyProgressHistoryList, StudentStudyProgressHistoryVo.class, "", new ExportParams(), response);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.EXPORT_DATA_SUCCESS.subMsg());
    }

    /**
     * *修改状态
     **/
    @Override
    public ResObject changeStatus(StudentStudyProgressHistoryEditParam param) {
        log.info(this.getClass() + "changeStatus方法请求参数{}",param);
        if (StrUtil.isEmpty(param.getId())){
            log.error("ID数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        StudentStudyProgressHistoryEntity StudentStudyProgressHistoryEntity = new StudentStudyProgressHistoryEntity();
        StudentStudyProgressHistoryEntity.setId(param.getId());
        //StudentStudyProgressHistoryEntity.setStatus(param.getStatus());
        //StudentStudyProgressHistoryEntity.setUpdateTime()
        Boolean result = studentStudyProgressHistoryService.updateById(StudentStudyProgressHistoryEntity);
        log.info(this.getClass() +"changeStatus方法请求对象参数{}，请求结果{}",StudentStudyProgressHistoryEntity,result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_STATUS_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_STATUS_FAILL.subCode(),SubResultCode.DATA_STATUS_FAILL.subMsg());
    }

    @Override
    public ResObject addStudyProgressHisstory(@Valid StudentStudyProgressHistoryInstallParam studentStudyProgressHistoryInstallParam) {
        log.info(this.getClass() + "addStudyProgressHisstory-方法请求参数{}",studentStudyProgressHistoryInstallParam);

        Boolean isTime = studentStudyProgressHistoryInstallParam.getTestResultType().equals(ExamEnrollEnum.PAY_SUCCESS.getCode()) || studentStudyProgressHistoryInstallParam.getTestResultType().equals(ExamEnrollEnum.TEST_RESULT_STATUS_NOPASS.getCode());
        //记录类型未考试通过与不通过时，时间履历显示实际考试时间
        if(!isTime) {
            studentStudyProgressHistoryInstallParam.setCreateTime(LocalDateTime.now());
        }
        StudentStudyProgressHistoryEntity studentStudyProgressHistory = BeanConvertUtils.copy(studentStudyProgressHistoryInstallParam,StudentStudyProgressHistoryEntity.class);
        Boolean result = studentStudyProgressHistoryService.saveOrUpdate(studentStudyProgressHistory);
        if (!result){
            return R.failure(SubResultCode.DATA_INSTALL_FAILL.subCode(),SubResultCode.DATA_INSTALL_FAILL.subMsg());
        }
        return R.success("执行成功");
    }
}

