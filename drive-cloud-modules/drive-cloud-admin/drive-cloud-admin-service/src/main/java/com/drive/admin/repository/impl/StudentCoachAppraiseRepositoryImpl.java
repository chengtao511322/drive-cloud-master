package com.drive.admin.repository.impl;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.admin.enums.TypeEnum;
import com.drive.admin.pojo.dto.StudentCoachAppraiseEditParam;
import com.drive.admin.pojo.dto.StudentCoachAppraiseInstallParam;
import com.drive.admin.pojo.dto.StudentCoachAppraisePageQueryParam;
import com.drive.admin.pojo.entity.CoachInfoEntity;
import com.drive.admin.pojo.entity.ServiceInfoEntity;
import com.drive.admin.pojo.entity.StudentCoachAppraiseEntity;
import com.drive.admin.pojo.entity.StudentInfoEntity;
import com.drive.admin.pojo.vo.StudentCoachAppraiseVo;
import com.drive.admin.repository.StudentCoachAppraiseRepository;
import com.drive.admin.service.CoachInfoService;
import com.drive.admin.service.ServiceInfoService;
import com.drive.admin.service.StudentCoachAppraiseService;
import com.drive.admin.service.StudentInfoService;
import com.drive.admin.service.mapstruct.StudentCoachAppraiseMapStruct;
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
 * 学员教练互评表 服务类
 *
 * @author xiaoguo
 */
@Slf4j
@Service
public class  StudentCoachAppraiseRepositoryImpl extends BaseController<StudentCoachAppraisePageQueryParam, StudentCoachAppraiseEntity>  implements StudentCoachAppraiseRepository{

    //  学员教练互评表 服务
    @Autowired
    private StudentCoachAppraiseService studentCoachAppraiseService;
    //  学员教练互评表 DO-DTO转化
    @Autowired
    private StudentCoachAppraiseMapStruct studentCoachAppraiseMapStruct;

    // 教练服务
    @Autowired
    private CoachInfoService coachInfoService;
    //  学员服务
    @Autowired
    private StudentInfoService studentInfoService;

    @Autowired
    private ServiceInfoService serviceInfoService;

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 学员教练互评表 分页列表
     * @date 2020/2/12 17:09
     * @param  * @param StudentCoachAppraisePageQueryParam
     * @return
     */
    @Override
    public ResObject pageList(StudentCoachAppraisePageQueryParam param) {
        log.info(this.getClass() + "pageList-方法请求参数{}",param);
        Page<StudentCoachAppraiseEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        // 条件查询
        QueryWrapper queryWrapper = this.getQueryWrapper(studentCoachAppraiseMapStruct, param);

        //  模糊查询
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueEnrollNoSearch()),"enroll_no",param.getVagueEnrollNoSearch());
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueOrderNoSearch()),"order_no",param.getVagueOrderNoSearch());
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueAppraiseReplyContentSearch()),"appraise_reply_content",param.getVagueAppraiseReplyContentSearch());
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueClassIdSearch()),"class_id",param.getVagueClassIdSearch());

        // 推荐时间
        queryWrapper.apply(StrUtil.isNotBlank(param.getSearchAppraiseReplyTime()),
                "date_format (appraise_reply_time,'%Y-%m-%d') <= date_format('" + param.getSearchAppraiseReplyTime() + "','%Y-%m-%d')");
        queryWrapper.apply(StrUtil.isNotBlank(param.getSearchReplyTime()),
                "date_format (reply_time,'%Y-%m-%d') <= date_format('" + param.getSearchReplyTime() + "','%Y-%m-%d')");
        //  开始时间 结束时间都有才进入
        if (StrUtil.isNotEmpty(param.getBeginTime()) && StrUtil.isNotEmpty(param.getEndTime())){
            queryWrapper.between(StrUtil.isNotEmpty(param.getBeginTime()),"create_time",param.getBeginTime(),param.getEndTime());
        }
        IPage<StudentCoachAppraiseEntity> pageList = studentCoachAppraiseService.page(page, queryWrapper);
        if (pageList.getRecords().size() <= 0){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }
        Page<StudentCoachAppraiseVo> studentCoachAppraiseVoPage = studentCoachAppraiseMapStruct.toVoList(pageList);
        // 循环
        studentCoachAppraiseVoPage.getRecords().stream().forEach((item) ->{
            if (StrUtil.isNotEmpty(item.getStudentId())){
                StudentInfoEntity studentInfoEntity = studentInfoService.getById(item.getStudentId());
                if (studentInfoEntity != null)item.setStudentName(studentInfoEntity.getRealName());
            }
            // 教练
            if (item.getReplyType().equals(TypeEnum.COACH.getCode())){
                if (StrUtil.isNotEmpty(item.getReplyId())){
                    CoachInfoEntity coachInfo = coachInfoService.getById(item.getReplyId());
                    if(coachInfo!= null)item.setReplyName(coachInfo.getRealName());
                }
            }
            // 客服
            if (item.getReplyType().equals(TypeEnum.SERVICE.getCode())){
                if (StrUtil.isNotEmpty(item.getReplyId())){
                    ServiceInfoEntity serviceInfo = serviceInfoService.getById(item.getReplyId());
                    if(serviceInfo!= null)item.setReplyName(serviceInfo.getRealName());
                }
            }
        });
        log.info(this.getClass() + "pageList-方法请求结果{}",studentCoachAppraiseVoPage);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),studentCoachAppraiseVoPage);
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 学员教练互评表 查询列表
     * @date 2020/2/12 17:09
     * @param  * @param StudentCoachAppraisePageQueryParam
     * @return
     */
    @Override
    public ResObject findList(StudentCoachAppraisePageQueryParam param) {
        log.info(this.getClass() + "findList-方法请求参数{}",param);
        // 这里判断条件进行查询
        QueryWrapper queryWrapper= this.getQueryWrapper(studentCoachAppraiseMapStruct, param);
        // 如 queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"phone",param.getPhone());
        List<StudentCoachAppraiseEntity> studentCoachAppraiseList = studentCoachAppraiseService.list(queryWrapper);
        if (studentCoachAppraiseList == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),studentCoachAppraiseList);
        }
        List<StudentCoachAppraiseVo> studentCoachAppraiseVoList = studentCoachAppraiseMapStruct.toVoList(studentCoachAppraiseList);
        log.info(this.getClass() + "findList-方法请求结果{}",studentCoachAppraiseVoList);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),studentCoachAppraiseVoList);
    }

    /**
    * 对象条件查询返回单条学员教练互评表数据
    * @param param
    * @return
    */
    @Override
    public ResObject getInfo(StudentCoachAppraisePageQueryParam param) {
        log.info(this.getClass() + "getInfo-方法请求参数{}",param);
        if (param == null){
            return R.failure("数据空");
        }
        // 这里判断条件进行查询
        QueryWrapper queryWrapper = this.getQueryWrapper(studentCoachAppraiseMapStruct, param);
        StudentCoachAppraiseEntity studentCoachAppraise = studentCoachAppraiseService.getOne(queryWrapper);
        if (studentCoachAppraise == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),studentCoachAppraise);
        }
        StudentCoachAppraiseVo studentCoachAppraiseVo = BeanConvertUtils.copy(studentCoachAppraise, StudentCoachAppraiseVo.class);
        log.info(this.getClass() + "getInfo-方法请求结果{}",studentCoachAppraiseVo);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),studentCoachAppraiseVo);
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 通过ID获取 学员教练互评表 单条数据
     * @date 2020/2/12 17:09
     * @param  * @param StudentCoachAppraisePageQueryParam
     * @return
     */
    @Override
    public ResObject getById(String id) {
        log.info(this.getClass() + "getById-方法请求参数{}",id);
        if (StrUtil.isEmpty(id)){
            return R.failure("数据空");
        }
        // 通过ID 获取 单条数据
        StudentCoachAppraiseEntity studentCoachAppraise = studentCoachAppraiseService.getById(id);
        if (studentCoachAppraise == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),studentCoachAppraise);
        }
        StudentCoachAppraiseVo studentCoachAppraiseVo = BeanConvertUtils.copy(studentCoachAppraise, StudentCoachAppraiseVo.class);
        log.info(this.getClass() + "getById-方法请求结果{}",studentCoachAppraiseVo);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),studentCoachAppraiseVo);
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 保存学员教练互评表 数据
     * @date 2020/2/12 17:09
     * @param  * @param StudentCoachAppraisePageQueryParam
     * @return
     */
    @Override
    public ResObject save(StudentCoachAppraiseInstallParam installParam) {
        log.info(this.getClass() + "save方法请求参数{}",installParam);
        if (installParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        StudentCoachAppraiseEntity studentCoachAppraise = BeanConvertUtils.copy(installParam, StudentCoachAppraiseEntity.class);
        Boolean result = studentCoachAppraiseService.save(studentCoachAppraise);
        log.info(this.getClass() + "save-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_INSTALL_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_INSTALL_FAILL.subCode(),SubResultCode.DATA_INSTALL_FAILL.subMsg());
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description  修改学员教练互评表 数据
     * @date 2020/2/12 17:09
     * @param  * @param StudentCoachAppraisePageQueryParam
     * @return
     */
    @Override
    public ResObject update(StudentCoachAppraiseEditParam updateParam) {
        log.info(this.getClass() + "update方法请求参数{}",updateParam);
        if (updateParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        StudentCoachAppraiseEntity studentCoachAppraise = BeanConvertUtils.copy(updateParam, StudentCoachAppraiseEntity.class);
        Boolean result = studentCoachAppraiseService.updateById(studentCoachAppraise);
        log.info(this.getClass() + "update-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_UPDATE_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_UPDATE_FAILL.subCode(),SubResultCode.DATA_UPDATE_FAILL.subMsg());
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 数组删除学员教练互评表 数据
     * @date 2020/2/12 17:09
     * @param  * @param StudentCoachAppraisePageQueryParam
     * @return
     */
    @Override
    public ResObject deleteByIds(String[] ids) {
        if (ids.length <= 0){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        return R.toRes(studentCoachAppraiseService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * *通过id删除学员教练互评表 信息
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
        Boolean result = studentCoachAppraiseService.removeById(id);
        log.info(this.getClass() + "deleteById-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_DELETE_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_DELETE_FAILL.subCode(),SubResultCode.DATA_DELETE_FAILL.subMsg());
    }

    /**
     * *导出学员教练互评表 信息
     **/
    @Override
    public ResObject exportXls(StudentCoachAppraisePageQueryParam param, HttpServletResponse response)throws IOException {
        log.info(this.getClass() + "exportXls方法请求参数{}",param);
        QueryWrapper queryWrapper = this.getQueryWrapper(studentCoachAppraiseMapStruct, param);
        List<StudentCoachAppraiseEntity> list = studentCoachAppraiseService.list(queryWrapper);
        List<StudentCoachAppraiseVo>studentCoachAppraiseList = studentCoachAppraiseMapStruct.toVoList(list);
        ExcelUtils.exportExcel(studentCoachAppraiseList, StudentCoachAppraiseVo.class, "", new ExportParams(), response);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.EXPORT_DATA_SUCCESS.subMsg());
    }

    /**
     * *修改状态
     **/
    @Override
    public ResObject changeStatus(StudentCoachAppraiseEditParam param) {
        log.info(this.getClass() + "changeStatus方法请求参数{}",param);
        if (StrUtil.isEmpty(param.getId())){
            log.error("ID数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        StudentCoachAppraiseEntity StudentCoachAppraiseEntity = new StudentCoachAppraiseEntity();
        StudentCoachAppraiseEntity.setId(param.getId());
        //StudentCoachAppraiseEntity.setStatus(param.getStatus());
        //StudentCoachAppraiseEntity.setUpdateTime()
        Boolean result = studentCoachAppraiseService.updateById(StudentCoachAppraiseEntity);
        log.info(this.getClass() +"changeStatus方法请求对象参数{}，请求结果{}",StudentCoachAppraiseEntity,result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_STATUS_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_STATUS_FAILL.subCode(),SubResultCode.DATA_STATUS_FAILL.subMsg());
    }

}

