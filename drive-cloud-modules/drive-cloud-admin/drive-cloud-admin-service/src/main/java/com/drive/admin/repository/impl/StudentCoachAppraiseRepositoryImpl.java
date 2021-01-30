package com.drive.admin.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.drive.admin.pojo.entity.StudentCoachAppraiseEntity;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.drive.common.core.base.BaseController;
import com.drive.admin.repository.StudentCoachAppraiseRepository;
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
import com.drive.admin.service.StudentCoachAppraiseService;
import com.drive.common.data.utils.ExcelUtils;
import java.util.List;
import java.util.Arrays;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

                                                                    
/**
 *
 * 学员教练互评表 服务类
 *
 * @author xiaoguo
 */
@Slf4j
@Service
public class  StudentCoachAppraiseRepositoryImpl extends BaseController<StudentCoachAppraisePageQueryParam, StudentCoachAppraiseEntity>  implements StudentCoachAppraiseRepository{

    @Autowired
    private StudentCoachAppraiseService studentCoachAppraiseService;
    @Autowired
    private StudentCoachAppraiseMapStruct studentCoachAppraiseMapStruct;

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
        IPage<StudentCoachAppraiseEntity> pageList = studentCoachAppraiseService.page(page, this.getQueryWrapper(studentCoachAppraiseMapStruct, param));
        Page<StudentCoachAppraiseVo> studentCoachAppraiseVoPage = studentCoachAppraiseMapStruct.toVoList(pageList);
        log.info(this.getClass() + "pageList-方法请求结果{}",studentCoachAppraiseVoPage);
        return R.success(studentCoachAppraiseVoPage);
    }

    @Override
    public ResObject findList(StudentCoachAppraisePageQueryParam param) {
        log.info(this.getClass() + "findList-方法请求参数{}",param);
        // 这里判断条件进行查询
        QueryWrapper queryWrapper= this.getQueryWrapper(studentCoachAppraiseMapStruct, param);
        // 如 queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"phone",param.getPhone());
        List<StudentCoachAppraiseEntity> pageList = studentCoachAppraiseService.list(queryWrapper);
        List<StudentCoachAppraiseVo> studentCoachAppraiseVoList = studentCoachAppraiseMapStruct.toVoList(pageList);
        log.info(this.getClass() + "findList-方法请求结果{}",studentCoachAppraiseVoList);
        if (studentCoachAppraiseVoList == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        return R.success(studentCoachAppraiseVoList);
    }

    /**
     * *通过ID获取学员教练互评表 列表
     **/
    @Override
    public ResObject getInfo(String id) {
        log.info(this.getClass() + "getInfo-方法请求参数{}",id);
        if (StrUtil.isEmpty(id)){
            return R.failure("数据空");
        }
        StudentCoachAppraiseEntity studentCoachAppraise = studentCoachAppraiseService.getById(id);
        StudentCoachAppraiseVo studentCoachAppraiseVo = BeanConvertUtils.copy(studentCoachAppraise, StudentCoachAppraiseVo.class);
        log.info(this.getClass() + "getInfo-方法请求结果{}",studentCoachAppraiseVo);
        if (studentCoachAppraiseVo ==null){
            log.error("活动数据对象空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        return R.success(studentCoachAppraiseVo);
    }

    /**
     * *保存学员教练互评表 信息
     **/
    @Override
    public ResObject save(StudentCoachAppraiseEditParam installParam) {
        log.info(this.getClass() + "save方法请求参数{}",installParam);
        if (installParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        StudentCoachAppraiseEntity studentCoachAppraise = BeanConvertUtils.copy(installParam, StudentCoachAppraiseEntity.class);
        Boolean result = studentCoachAppraiseService.save(studentCoachAppraise);
        log.info(this.getClass() + "save-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *修改学员教练互评表 信息
     **/
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
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *删除学员教练互评表 信息
     **/
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
        return result ?R.success(result):R.failure(result);
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
        return R.success("导出成功");
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
        return result ?R.success(result):R.failure(result);
    }

}

