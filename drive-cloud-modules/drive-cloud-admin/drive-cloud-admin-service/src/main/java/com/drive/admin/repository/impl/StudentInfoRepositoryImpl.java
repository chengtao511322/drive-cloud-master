package com.drive.admin.repository.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.admin.pojo.dto.StudentInfoEditParam;
import com.drive.admin.pojo.dto.StudentInfoPageQueryParam;
import com.drive.admin.pojo.entity.StudentInfoEntity;
import com.drive.admin.pojo.vo.StudentInfoVo;
import com.drive.admin.repository.StudentInfoRepository;
import com.drive.admin.service.StudentInfoService;
import com.drive.admin.service.mapstruct.StudentInfoMapStruct;
import com.drive.common.core.base.BaseController;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.biz.SubResultCode;
import com.drive.common.core.utils.BeanConvertUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

                                                                                                                                            
/**
 *
 * 学员信息表 服务类
 *
 * @author xiaoguo
 */
@Slf4j
@Service
public class  StudentInfoRepositoryImpl extends BaseController<StudentInfoPageQueryParam, StudentInfoEntity>  implements StudentInfoRepository{

    @Autowired
    private StudentInfoService studentInfoService;
    @Autowired
    private StudentInfoMapStruct studentInfoMapStruct;

    /**
    * *学员信息表 分页列表
    **/
    @Override
    public ResObject pageList(StudentInfoPageQueryParam param) {
        log.info(this.getClass() + "pageList-方法请求参数{}",param);
        Page<StudentInfoEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        IPage<StudentInfoEntity> pageList = studentInfoService.page(page, this.getQueryWrapper(studentInfoMapStruct, param));
        Page<StudentInfoVo> studentInfoVoPage = studentInfoMapStruct.toVoList(pageList);
        log.info(this.getClass() + "pageList-方法请求结果{}",studentInfoVoPage);
        return R.success(studentInfoVoPage);
    }

    @Override
    public ResObject findList(StudentInfoPageQueryParam param) {
        log.info(this.getClass() + "findList-方法请求参数{}",param);
        // 这里判断条件进行查询
        QueryWrapper queryWrapper= this.getQueryWrapper(studentInfoMapStruct, param);
        // 如 queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"phone",param.getPhone());
        List<StudentInfoEntity> pageList = studentInfoService.list(queryWrapper);
        List<StudentInfoVo> studentInfoVoList = studentInfoMapStruct.toVoList(pageList);
        log.info(this.getClass() + "findList-方法请求结果{}",studentInfoVoList);
        if (studentInfoVoList == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        return R.success(studentInfoVoList);
    }

    /**
     * *通过ID获取学员信息表 列表
     **/
    @Override
    public ResObject<StudentInfoVo> getInfo(String id) {
        log.info(this.getClass() + "getInfo-方法请求参数{}",id);
        if (StrUtil.isEmpty(id)){
            return R.failure("数据空");
        }
        StudentInfoEntity studentInfo = studentInfoService.getById(id);
        StudentInfoVo studentInfoVo = BeanConvertUtils.copy(studentInfo, StudentInfoVo.class);
        log.info(this.getClass() + "getInfo-方法请求结果{}",studentInfoVo);
        if (studentInfoVo ==null){
            log.error("活动数据对象空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        return R.success(studentInfoVo);
    }

    /**
     * *保存学员信息表 信息
     **/
    @Override
    public ResObject save(StudentInfoEditParam installParam) {

        log.info(this.getClass() + "save方法请求参数{}",installParam);
        if (installParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        StudentInfoEntity studentInfo = BeanConvertUtils.copy(installParam, StudentInfoEntity.class);
        Boolean result = studentInfoService.save(studentInfo);
        log.info(this.getClass() + "save-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *修改学员信息表 信息
     **/
    @Override
    public ResObject update(StudentInfoEditParam updateParam) {
        log.info(this.getClass() + "update方法请求参数{}",updateParam);
        if (updateParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        StudentInfoEntity studentInfo = BeanConvertUtils.copy(updateParam, StudentInfoEntity.class);
        Boolean result = studentInfoService.updateById(studentInfo);
        log.info(this.getClass() + "update-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *删除学员信息表 信息
     **/
    @Override
    public ResObject deleteByIds(String[] ids) {
        return R.toRes(studentInfoService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * *通过id删除学员信息表 信息
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
        Boolean result = studentInfoService.removeById(id);
        log.info(this.getClass() + "deleteById-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *导出学员信息表 信息
     **/
    @Override
    public ResObject exportXls(StudentInfoPageQueryParam param, HttpServletResponse response) {
        return null;
    }

    /**
     * *修改状态
     **/
    @Override
    public ResObject changeStatus(StudentInfoEditParam param) {

        StudentInfoEntity StudentInfoEntity = new StudentInfoEntity();
        StudentInfoEntity.setId(param.getId());
        StudentInfoEntity.setStatus(param.getStatus());
        Boolean result = studentInfoService.updateById(StudentInfoEntity);
        log.info(this.getClass() +"changeStatus方法请求对象参数{}，请求结果{}",StudentInfoEntity,result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

}

