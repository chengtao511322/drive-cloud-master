package com.drive.admin.repository.impl;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.admin.enums.StatusEnum;
import com.drive.admin.pojo.dto.RecommendUserEditParam;
import com.drive.admin.pojo.dto.RecommendUserPageQueryParam;
import com.drive.admin.pojo.entity.RecommendManagerEntity;
import com.drive.admin.pojo.entity.RecommendUserEntity;
import com.drive.admin.pojo.entity.StudentInfoEntity;
import com.drive.admin.pojo.entity.TestTrainPriceEntity;
import com.drive.admin.pojo.vo.RecommendUserVo;
import com.drive.admin.repository.RecommendManagerRepository;
import com.drive.admin.repository.RecommendUserRepository;
import com.drive.admin.service.RecommendManagerService;
import com.drive.admin.service.RecommendUserService;
import com.drive.admin.service.StudentInfoService;
import com.drive.admin.service.mapstruct.RecommendUserMapStruct;
import com.drive.common.core.base.BaseController;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.biz.SubResultCode;
import com.drive.common.core.exception.BizException;
import com.drive.common.core.utils.BeanConvertUtils;
import com.drive.common.data.utils.ExcelUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 *
 * 推广人员信息表 服务类
 *
 * @author xiaoguo
 */
@Slf4j
@Service
public class  RecommendUserRepositoryImpl extends BaseController<RecommendUserPageQueryParam, RecommendUserEntity>  implements RecommendUserRepository{

    @Autowired
    private RecommendUserService recommendUserService;
    @Autowired
    private RecommendUserMapStruct recommendUserMapStruct;

    @Autowired
    private RecommendManagerService recommendManagerService;

    @Autowired
    private StudentInfoService studentInfoService;

    /*
     *功能描述
     * @author xiaoguo
     * @description 推广人员信息表 分页列表
     * @date 2020/2/12 17:09
     * @param  * @param RecommendUserPageQueryParam
     * @return
     */
    @Override
    public ResObject pageList(RecommendUserPageQueryParam param) {
        log.info(this.getClass() + "pageList-方法请求参数{}",param);
        Page<RecommendUserEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        QueryWrapper queryWrapper = this.getQueryWrapper(recommendUserMapStruct, param);
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueUserNameSearch()),"name",param.getVagueUserNameSearch());

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

        //  时间区域查询
        if (param.getCreateDateTimeSearchArr() != null && param.getCreateDateTimeSearchArr().length > 0 ){
            if (StrUtil.isNotEmpty(param.getCreateDateTimeSearchArr()[0]) && StrUtil.isNotEmpty(param.getCreateDateTimeSearchArr()[1])){
                queryWrapper.between("create_time",param.getCreateDateTimeSearchArr()[0],param.getCreateDateTimeSearchArr()[1]);
            }
        }

        IPage<RecommendUserEntity> pageList = recommendUserService.page(page,queryWrapper );
        if (pageList.getRecords().size() <= 0){
            return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }
        Page<RecommendUserVo> recommendUserVoPage = recommendUserMapStruct.toVoList(pageList);
        recommendUserVoPage.getRecords().stream().forEach((item) ->{
            // 学员
        /*    item.setStudentName(Optional.ofNullable(studentInfoService.getById(item.getStudentId()))
                    .map(u-> u.getRealName())
                    .orElseThrow(()->new BizException("学员获取数据空")));*/

            if (StrUtil.isNotEmpty(item.getStudentId())){
                StudentInfoEntity studentInfo = studentInfoService.getById(item.getStudentId());
                if (studentInfo != null){
                    item.setUserName(studentInfo.getUsername());
                    item.setPhone(studentInfo.getPhone());
                }
            }
            // 渠道经理
           /* item.setManagerName(Optional.ofNullable(recommendManagerService.getById(item.getManagerId()))
                    .map(u-> u.getRemarks())
                    .orElseThrow(()->new BizException("渠道经理获取数据空")));
*/
            if (StrUtil.isNotEmpty(item.getManagerId())){
                RecommendManagerEntity manager = recommendManagerService.getById(item.getStudentId());
                if (manager != null)item.setStudentName(manager.getRemarks());
            }
        });
        log.info(this.getClass() + "pageList-方法请求结果{}",recommendUserVoPage);
        return R.success(recommendUserVoPage);
    }

    @Override
    public ResObject findList(RecommendUserPageQueryParam param) {
        log.info(this.getClass() + "findList-方法请求参数{}",param);
        // 这里判断条件进行查询
        QueryWrapper queryWrapper= this.getQueryWrapper(recommendUserMapStruct, param);
        // 如 queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"phone",param.getPhone());
        List<RecommendUserEntity> pageList = recommendUserService.list(queryWrapper);
        List<RecommendUserVo> recommendUserVoList = recommendUserMapStruct.toVoList(pageList);
        log.info(this.getClass() + "findList-方法请求结果{}",recommendUserVoList);
        if (recommendUserVoList == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        return R.success(recommendUserVoList);
    }



    @Override
    public ResObject getInfo(RecommendUserPageQueryParam param) {
        log.info(this.getClass() + "getInfo-方法请求参数{}",param);
        if (param == null){
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        QueryWrapper queryWrapper= this.getQueryWrapper(recommendUserMapStruct, param);
        queryWrapper.last("limit 1");
        RecommendUserEntity recommendUserEntity = recommendUserService.getOne(queryWrapper);
        if (recommendUserEntity == null){
            return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        //  do - vo 转化
        RecommendUserVo recommendUserVo = BeanConvertUtils.copy(recommendUserEntity,RecommendUserVo.class);
        return R.success(recommendUserVo);
    }

    /**
     * *通过ID获取推广人员信息表 列表
     **/
    @Override
    public ResObject getById(String id) {
        log.info(this.getClass() + "getInfo-方法请求参数{}",id);
        if (StrUtil.isEmpty(id)){
            return R.failure("数据空");
        }
        RecommendUserEntity recommendUser = recommendUserService.getById(id);
        if (recommendUser ==null){
            log.error("活动数据对象空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        RecommendUserVo recommendUserVo = BeanConvertUtils.copy(recommendUser, RecommendUserVo.class);
        StudentInfoEntity studentInfo = studentInfoService.getById(recommendUserVo.getStudentId());
        if (studentInfo != null){
            recommendUserVo.setPhone(studentInfo.getPhone());
            recommendUserVo.setStudentName(studentInfo.getUsername());
        }
        // 查询渠道经理
        RecommendManagerEntity recommendManager = recommendManagerService.getById(recommendUserVo.getManagerId());
        // 不加{}时，就近原则，只控制第一句 性能上面有所提升
        if (recommendManager != null)recommendUserVo.setManagerName(recommendManager.getRemarks());
        log.info(this.getClass() + "getInfo-方法请求结果{}",recommendUserVo);
        return R.success(recommendUserVo);
    }

    /**
     * *保存推广人员信息表 信息
     **/
    @Override
    public ResObject save(RecommendUserEditParam installParam) {
        log.info(this.getClass() + "save方法请求参数{}",installParam);
        if (installParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        // 幂等性查询
        QueryWrapper queryWrapper = new QueryWrapper();
        // 学员id
        queryWrapper.eq(StrUtil.isNotEmpty(installParam.getStudentId()),"student_id",installParam.getStudentId());
        // 推广商查询
        queryWrapper.eq(StrUtil.isNotEmpty(installParam.getName()),"name",installParam.getName());
        queryWrapper.eq(StrUtil.isNotEmpty(installParam.getOperatorId()),"operator_id",installParam.getOperatorId());
        queryWrapper.last("limit 1");
        RecommendUserEntity isRecommendUser = recommendUserService.getOne(queryWrapper);
        if (isRecommendUser != null){
            return R.success(SubResultCode.DATA_IDEMPOTENT.subCode(),SubResultCode.DATA_IDEMPOTENT.subMsg());
        }
        RecommendUserEntity recommendUser = BeanConvertUtils.copy(installParam, RecommendUserEntity.class);
        // 待审
        recommendUser.setStatus(StatusEnum.ENABLE.getCode());
        Boolean result = recommendUserService.save(recommendUser);
        log.info(this.getClass() + "save-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *修改推广人员信息表 信息
     **/
    @Override
    public ResObject update(RecommendUserEditParam updateParam) {
        log.info(this.getClass() + "update方法请求参数{}",updateParam);
        if (updateParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
      /*  // 幂等性查询
        QueryWrapper queryWrapper = new QueryWrapper();
        // 学员id
        queryWrapper.eq(StrUtil.isNotEmpty(updateParam.getStudentId()),"student_id",updateParam.getStudentId());
        // 推广商查询
        queryWrapper.eq(StrUtil.isNotEmpty(updateParam.getName()),"name",updateParam.getName());
        queryWrapper.eq(StrUtil.isNotEmpty(updateParam.getOperatorId()),"operator_id",updateParam.getOperatorId());
        queryWrapper.last("limit 1");
        RecommendUserEntity isRecommendUser = recommendUserService.getOne(queryWrapper);
        if (isRecommendUser != null){
            return R.failure(SubResultCode.DATA_IDEMPOTENT.subCode(),SubResultCode.DATA_IDEMPOTENT.subMsg());
        }*/
        RecommendUserEntity recommendUser = BeanConvertUtils.copy(updateParam, RecommendUserEntity.class);
        Boolean result = recommendUserService.updateById(recommendUser);
        log.info(this.getClass() + "update-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *删除推广人员信息表 信息
     **/
    @Override
    public ResObject deleteByIds(String[] ids) {
        if (ids.length <= 0){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        return R.toRes(recommendUserService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * *通过id删除推广人员信息表 信息
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
        Boolean result = recommendUserService.removeById(id);
        log.info(this.getClass() + "deleteById-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *导出推广人员信息表 信息
     **/
    @Override
    public ResObject exportXls(RecommendUserPageQueryParam param, HttpServletResponse response)throws IOException {
        log.info(this.getClass() + "exportXls方法请求参数{}",param);
        QueryWrapper queryWrapper = this.getQueryWrapper(recommendUserMapStruct, param);
        List<RecommendUserEntity> list = recommendUserService.list(queryWrapper);
        List<RecommendUserVo>recommendUserList = recommendUserMapStruct.toVoList(list);
        ExcelUtils.exportExcel(recommendUserList, RecommendUserVo.class, "", new ExportParams(), response);
        return R.success("导出成功");
    }

    /**
     * *修改状态
     **/
    @Override
    public ResObject changeStatus(RecommendUserEditParam param) {
        log.info(this.getClass() + "changeStatus方法请求参数{}",param);
        if (StrUtil.isEmpty(param.getId())){
            log.error("ID数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        RecommendUserEntity RecommendUserEntity = new RecommendUserEntity();
        RecommendUserEntity.setId(param.getId());
        RecommendUserEntity.setStatus(param.getStatus());
        //RecommendUserEntity.setUpdateTime()
        Boolean result = recommendUserService.updateById(RecommendUserEntity);
        log.info(this.getClass() +"changeStatus方法请求对象参数{}，请求结果{}",RecommendUserEntity,result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

}

