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
import com.drive.admin.service.AreaService;
import com.drive.admin.service.RecommendUserService;
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
import java.time.LocalDate;
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
    private AreaService areaService;
    @Autowired
    private StudentInfoMapStruct studentInfoMapStruct;

    @Autowired
    private RecommendUserService recommendUserService;

    /**
    * *学员信息表 分页列表
    **/
    @Override
    public ResObject pageList(StudentInfoPageQueryParam param) {
        log.info(this.getClass() + "pageList-方法请求参数{}",param);
        Page<StudentInfoEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        QueryWrapper queryWrapper = this.getQueryWrapper(studentInfoMapStruct, param);
        queryWrapper.like(StrUtil.isNotEmpty(param.getVaguePhoneSearch()),"phone",param.getVaguePhoneSearch());
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueUserNameSearch()),"username",param.getVagueUserNameSearch());
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueRealNameSearch()),"real_name",param.getVagueRealNameSearch());
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueEmailSearch()),"email",param.getVagueEmailSearch());
        // 登录时间
        queryWrapper.apply(StrUtil.isNotBlank(param.getSearchLoginDate()),
                "date_format (login_time,'%Y-%m-%d') = date_format('" + param.getSearchLoginDate() + "','%Y-%m-%d')");
        // 推荐时间
        queryWrapper.apply(StrUtil.isNotBlank(param.getSearchRecommendDate()),
                "date_format (recommend_date,'%Y-%m-%d') <= date_format('" + LocalDate.now().toString() + "','%Y-%m-%d')");
                /*.apply(StrUtil.isNotBlank(end_date),
                        "date_format (optime,'%Y-%m-%d') <= date_format('" + end_date + "','%Y-%m-%d')")*/
        // 没有开始时间 默认查询
        if (StrUtil.isEmpty(param.getBeginTime())){
            queryWrapper.apply(StrUtil.isNotBlank(param.getSearchRecommendDate()),
                    "date_format (create_time,'%Y-%m-%d') <= date_format('" + LocalDate.now().toString() + "','%Y-%m-%d')");
        }
        // 没有结束时间
        if (StrUtil.isEmpty(param.getEndTime())){
            queryWrapper.between(StrUtil.isNotEmpty(param.getBeginTime()),"create_time",param.getBeginTime(),LocalDate.now().toString());
        }
        //  开始时间 结束时间都有才进入
        if (StrUtil.isNotEmpty(param.getBeginTime()) && StrUtil.isNotEmpty(param.getEndTime())){
            queryWrapper.between(StrUtil.isNotEmpty(param.getBeginTime()),"create_time",param.getBeginTime(),param.getEndTime());
        }

        IPage<StudentInfoEntity> pageList = studentInfoService.page(page,queryWrapper);
        if (pageList.getRecords().size() <= 0){
            log.error(this.getClass() +"数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        Page<StudentInfoVo> studentInfoVoPage = studentInfoMapStruct.toVoList(pageList);
        // List<Problem> problemList = problemByExample.stream().filter(problem -> "空调制冷".equals(problem.getProTitle()) || "李一一的难题1".equals(problem.getProTitle())).collect(Collectors.toList());
        studentInfoVoPage.getRecords().stream().forEach(item ->{
           if (StrUtil.isNotEmpty(item.getProvinceId()))item.setProvinceName(areaService.getByBaCode(item.getProvinceId()).getBaName());
           if (StrUtil.isNotEmpty(item.getCityId()))item.setCityName(areaService.getByBaCode(item.getCityId()).getBaName());
           if (StrUtil.isNotEmpty(item.getAreaId()))item.setAreaName(areaService.getByBaCode(item.getAreaId()).getBaName());
            // 查询推广人员
            if (StrUtil.isNotEmpty(item.getRecommendUserId())){
                StudentInfoEntity studentInfo = studentInfoService.getById(item.getRecommendUserId());
                if (studentInfo != null && StrUtil.isNotEmpty(studentInfo.getRealName()))item.setRecommendUserName(studentInfo.getRealName());
                if (studentInfo != null && StrUtil.isNotEmpty(studentInfo.getPhone()))item.setRecommendUserPhone(studentInfo.getPhone());
            }
        });
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
    public ResObject<StudentInfoVo> getById(String id) {
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

    @Override
    public ResObject getInfo(StudentInfoPageQueryParam param) {
        log.info(this.getClass() + "getInfo-方法请求参数{}",param);
        QueryWrapper queryWrapper = this.getQueryWrapper(studentInfoMapStruct, param);
        StudentInfoEntity studentInfo = studentInfoService.getOne(queryWrapper);
        StudentInfoVo studentInfoVo = BeanConvertUtils.convertBean(studentInfo,StudentInfoVo.class);
        // 空
        if(studentInfoVo == null){
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),studentInfoVo);
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
        log.info(this.getClass() +"exportXls-方法请求参数");
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

