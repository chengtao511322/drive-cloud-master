package com.drive.admin.repository.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.admin.enums.StatusEnum;
import com.drive.admin.pojo.dto.DeductSettingInstallParam;
import com.drive.admin.pojo.dto.RecommendManagerEditParam;
import com.drive.admin.pojo.dto.RecommendManagerPageQueryParam;
import com.drive.admin.pojo.entity.DeductSettingEntity;
import com.drive.admin.pojo.entity.RecommendManagerEntity;
import com.drive.admin.pojo.entity.RecommendUserEntity;
import com.drive.admin.pojo.entity.StudentInfoEntity;
import com.drive.admin.pojo.vo.DeductSettingVo;
import com.drive.admin.pojo.vo.RecommendManagerVo;
import com.drive.admin.pojo.vo.RecommendUserVo;
import com.drive.admin.pojo.vo.StudentInfoRpcVo;
import com.drive.admin.repository.RecommendManagerRepository;
import com.drive.admin.repository.StudentInfoRepository;
import com.drive.admin.service.DeductSettingService;
import com.drive.admin.service.RecommendManagerService;
import com.drive.admin.service.StudentInfoService;
import com.drive.admin.service.mapstruct.RecommendManagerMapStruct;
import com.drive.common.core.base.BaseController;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResCodeEnum;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.biz.SubResultCode;
import com.drive.common.core.exception.BizException;
import com.drive.common.core.utils.BeanConvertUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;


/**
 *
 * 推广渠道经理 服务类
 *
 * @author xiaoguo
 */
@Slf4j
@Service
public class  RecommendManagerRepositoryImpl extends BaseController<RecommendManagerPageQueryParam, RecommendManagerEntity>  implements RecommendManagerRepository{

    @Autowired
    private RecommendManagerService recommendManagerService;
    @Autowired
    private RecommendManagerMapStruct recommendManagerMapStruct;

    @Autowired
    private StudentInfoService studentInfoService;

    @Autowired
    private DeductSettingService deductSettingService;

    @Autowired
    private StudentInfoRepository studentInfoRepository;


    /**
    * *推广渠道经理 分页列表
    **/
    @Override
    public ResObject pageList(RecommendManagerPageQueryParam param) {
        log.info(this.getClass() + "pageList-方法请求参数{}",param);
        Page<RecommendManagerEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        QueryWrapper queryWrapper = this.getQueryWrapper(recommendManagerMapStruct, param);
        // 模糊查询
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueRemarksSearch()),"remarks",param.getVagueRemarksSearch());
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

        IPage<RecommendManagerEntity> pageList = recommendManagerService.page(page, queryWrapper);
        if (pageList.getRecords().size() <= 0){
            return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),"数据空");
        }
        Page<RecommendManagerVo> recommendManagerVoPage = recommendManagerMapStruct.toVoList(pageList);
        recommendManagerVoPage.getRecords().stream().forEach((item) ->{
            // 学员
            if (StrUtil.isNotEmpty(item.getStudentId())){
                StudentInfoEntity studentInfo = studentInfoService.getById(item.getStudentId());
                if (studentInfo != null){
                    item.setStudentRealName(studentInfo.getRealName());
                    item.setStudentName(studentInfo.getUsername());
                    item.setStudentPhone(studentInfo.getPhone());
                }
            }
        });
        log.info(this.getClass() + "pageList-方法请求结果{}",recommendManagerVoPage);
        return R.success(recommendManagerVoPage);
    }

    @Override
    public ResObject findList(RecommendManagerPageQueryParam param) {
        log.info(this.getClass() + "findList-方法请求参数{}",param);
        // 这里判断条件进行查询
        QueryWrapper queryWrapper= this.getQueryWrapper(recommendManagerMapStruct, param);
        // 如 queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"phone",param.getPhone());
        List<RecommendManagerEntity> pageList = recommendManagerService.list(queryWrapper);
        List<RecommendManagerVo> recommendManagerVoList = recommendManagerMapStruct.toVoList(pageList);
        log.info(this.getClass() + "findList-方法请求结果{}",recommendManagerVoList);
        if (recommendManagerVoList == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        return R.success(recommendManagerVoList);
    }



    @Override
    public ResObject getInfo(RecommendManagerPageQueryParam param) {
        return null;
    }

    /**
     * *通过ID获取推广渠道经理 列表
     **/
    @Override
    public ResObject getById(String id) {
        log.info(this.getClass() + "getInfo-方法请求参数{}",id);
        if (StrUtil.isEmpty(id)){
            return R.failure("数据空");
        }
        RecommendManagerEntity recommendManager = recommendManagerService.getById(id);
        RecommendManagerVo recommendManagerVo = BeanConvertUtils.copy(recommendManager, RecommendManagerVo.class);
        log.info(this.getClass() + "getInfo-方法请求结果{}",recommendManagerVo);
        if (recommendManagerVo ==null){
            log.error("活动数据对象空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        // 查询手机
        StudentInfoEntity studentInfoEntity = studentInfoService.getById(recommendManagerVo.getStudentId());
        if (studentInfoEntity != null){
            // 设置手机
            recommendManagerVo.setPhone(studentInfoEntity.getPhone());
            // 设置姓名
            recommendManagerVo.setRealName(studentInfoEntity.getRealName());
            //
            recommendManagerVo.setStudentName(studentInfoEntity.getUsername());

            // 设置手机
            recommendManagerVo.setStudentPhone(studentInfoEntity.getPhone());
            // 设置姓名
            recommendManagerVo.setStudentRealName(studentInfoEntity.getRealName());

            recommendManagerVo.setStudentName(studentInfoEntity.getUsername());
        }

        // 这里判断条件进行查询
        QueryWrapper queryWrapper= new QueryWrapper();
        queryWrapper.eq("recommend_manager_id",id);
        List<DeductSettingEntity> deductSettingList = deductSettingService.list(queryWrapper);
        recommendManagerVo.setDeductSettingList(BeanConvertUtils.copyList(deductSettingList,DeductSettingVo.class));
        return R.success(recommendManagerVo);
    }

    /**
     * *保存推广渠道经理 信息
     **/
    @Override
    public ResObject save(RecommendManagerEditParam installParam) {

        log.info(this.getClass() + "save方法请求参数{}",installParam);
        if (installParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        // 幂等性查询
        QueryWrapper queryWrapper = new QueryWrapper();
        // 学员id
        queryWrapper.eq(StrUtil.isNotEmpty(installParam.getStudentId()),"student_id",installParam.getStudentId());
        queryWrapper.eq(StrUtil.isNotEmpty(installParam.getOperatorId()),"operator_id",installParam.getOperatorId());
        queryWrapper.last("limit 1");
        int isRecommendManager = recommendManagerService.count(queryWrapper);
        if (isRecommendManager > 0){
            return R.success(SubResultCode.DATA_IDEMPOTENT.subCode(),SubResultCode.DATA_IDEMPOTENT.subMsg());
        }
        RecommendManagerEntity recommendManager = BeanConvertUtils.copy(installParam, RecommendManagerEntity.class);
        // 待审
        recommendManager.setStatus(StatusEnum.ENABLE.getCode());
        Boolean result = recommendManagerService.save(recommendManager);
        log.info(this.getClass() + "save-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *修改推广渠道经理 信息
     **/
    @Override
    public ResObject update(RecommendManagerEditParam updateParam) {
        log.info(this.getClass() + "update方法请求参数{}",updateParam);
        if (updateParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        // 幂等性查询
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.setEntity(updateParam);
        queryWrapper.last("limit 1");
        int isRecommendManager = recommendManagerService.count(queryWrapper);
        if (isRecommendManager > 0){
            return R.success(SubResultCode.DATA_IDEMPOTENT.subCode(),SubResultCode.DATA_IDEMPOTENT.subMsg());
        }
        RecommendManagerEntity recommendManager = BeanConvertUtils.copy(updateParam, RecommendManagerEntity.class);
        Boolean result = recommendManagerService.updateById(recommendManager);
        log.info(this.getClass() + "update-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *删除推广渠道经理 信息
     **/
    @Override
    public ResObject deleteByIds(String[] ids) {
        Boolean res = recommendManagerService.removeByIds(Arrays.asList(ids));
        if (!res){
            return R.failure(SubResultCode.DATA_DELETE_FAILL.subCode(),SubResultCode.DATA_DELETE_FAILL.subMsg());
        }
        return R.success(res);
    }

    /**
     * *通过id删除推广渠道经理 信息
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
        Boolean result = recommendManagerService.removeById(id);
        log.info(this.getClass() + "deleteById-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *导出推广渠道经理 信息
     **/
    @Override
    public ResObject exportXls(RecommendManagerPageQueryParam param, HttpServletResponse response) {
        return null;
    }

    /**
     * *修改状态
     **/
    @Override
    public ResObject changeStatus(RecommendManagerEditParam param) {

        RecommendManagerEntity RecommendManagerEntity = new RecommendManagerEntity();
        RecommendManagerEntity.setId(param.getId());
        RecommendManagerEntity.setStatus(param.getStatus());
        Boolean result = recommendManagerService.updateById(RecommendManagerEntity);
        log.info(this.getClass() +"changeStatus方法请求对象参数{}，请求结果{}",RecommendManagerEntity,result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    @Transactional
    @Override
    public ResObject saveRecommendManager(RecommendManagerEditParam recommendManagerEditParam) {
        log.info(this.getClass() + "saveRecommendManager方法请求参数{}",recommendManagerEditParam);
        if (recommendManagerEditParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        // 幂等性查询
        QueryWrapper queryWrapper = new QueryWrapper();
        // 学员id
        queryWrapper.eq(StrUtil.isNotEmpty(recommendManagerEditParam.getStudentId()),"student_id",recommendManagerEditParam.getStudentId());
        queryWrapper.eq(StrUtil.isNotEmpty(recommendManagerEditParam.getOperatorId()),"operator_id",recommendManagerEditParam.getOperatorId());
        queryWrapper.last("limit 1");
        RecommendManagerEntity isRecommendManager = recommendManagerService.getOne(queryWrapper);
        if (isRecommendManager != null){
            return R.success(SubResultCode.DATA_IDEMPOTENT.subCode(),SubResultCode.DATA_IDEMPOTENT.subMsg());
        }
        RecommendManagerEntity recommendManager = BeanConvertUtils.copy(recommendManagerEditParam, RecommendManagerEntity.class);
        // 待审
        recommendManager.setStatus(StatusEnum.ENABLE.getCode());
        Boolean result = recommendManagerService.save(recommendManager);
        log.info(this.getClass() + "save-方法请求结果{}",result);
        // 判断结果
        if (!result){
            throw new BizException(500,SubResultCode.DATA_INSTALL_SUCCESS.subCode(),SubResultCode.DATA_INSTALL_SUCCESS.subMsg());
        }
        // 批量处理提成
        List<DeductSettingInstallParam> deductSettingInstallParamList = recommendManagerEditParam.getDeductSettingList();
        log.info(this.getClass() + "changeStatus方法请求参数{}",deductSettingInstallParamList);
        if (deductSettingInstallParamList !=null && deductSettingInstallParamList.size() > 0){
            List<DeductSettingEntity> deductSettingList = BeanConvertUtils.copyList(deductSettingInstallParamList,DeductSettingEntity.class);
            // 先删除 数据
            QueryWrapper delQueryWrapper = new  QueryWrapper();
            delQueryWrapper.eq("recommend_manager_id",recommendManager.getId());
            deductSettingService.remove(delQueryWrapper);
            //DeductSettingEntity.setUpdateTime()
            deductSettingList.stream().forEach((item) ->{
                item.setRecommendManagerId(recommendManager.getId());
            });
            Boolean deductSettingResult = deductSettingService.saveOrUpdateBatch(deductSettingList);
            log.info(this.getClass() +"changeStatus方法请求对象参数{}，请求结果{}",deductSettingResult);
            if (!deductSettingResult){
                throw new BizException(500,SubResultCode.DATA_INSTALL_SUCCESS.subCode(),SubResultCode.DATA_INSTALL_SUCCESS.subMsg());
            }
        }
        return R.success();
    }
    @Transactional
    @Override
    public ResObject updateRecommendManager(RecommendManagerEditParam recommendManagerEditParam) {
        log.info(this.getClass() + "saveRecommendManager方法请求参数{}",recommendManagerEditParam);
        if (StrUtil.isEmpty(recommendManagerEditParam.getId())){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
       /* // 幂等性查询
        QueryWrapper queryWrapper = new QueryWrapper();
        // 学员id
        queryWrapper.eq(StrUtil.isNotEmpty(recommendManagerEditParam.getStudentId()),"student_id",recommendManagerEditParam.getStudentId());
        queryWrapper.eq(StrUtil.isNotEmpty(recommendManagerEditParam.getOperatorId()),"operator_id",recommendManagerEditParam.getOperatorId());
        queryWrapper.last("limit 1");
        RecommendManagerEntity isRecommendManager = recommendManagerService.getOne(queryWrapper);
        if (isRecommendManager != null){
            return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_IDEMPOTENT.subMsg());
        }*/
        RecommendManagerEntity recommendManager = BeanConvertUtils.copy(recommendManagerEditParam, RecommendManagerEntity.class);
        // 待审
        Boolean result = recommendManagerService.updateById(recommendManager);
        log.info(this.getClass() + "save-方法请求结果{}",result);
        // 判断结果
        if (!result){
            throw new BizException(500,SubResultCode.DATA_INSTALL_SUCCESS.subCode(),SubResultCode.DATA_INSTALL_SUCCESS.subMsg());
        }
        // 批量处理提成
        List<DeductSettingInstallParam> deductSettingInstallParamList = recommendManagerEditParam.getDeductSettingList();
        log.info(this.getClass() + "changeStatus方法请求参数{}",deductSettingInstallParamList);
        if (deductSettingInstallParamList.size() > 0){
            List<DeductSettingEntity> deductSettingList = BeanConvertUtils.copyList(deductSettingInstallParamList,DeductSettingEntity.class);
            // 先删除 数据
            QueryWrapper delQueryWrapper = new  QueryWrapper();
            delQueryWrapper.eq("recommend_manager_id",recommendManager.getId());
            deductSettingService.remove(delQueryWrapper);
            //DeductSettingEntity.setUpdateTime()
            deductSettingList.stream().forEach((item) ->{
                item.setRecommendManagerId(recommendManager.getId());
            });
            Boolean deductSettingResult = deductSettingService.saveOrUpdateBatch(deductSettingList);
            log.info(this.getClass() +"changeStatus方法请求对象参数{}，请求结果{}",deductSettingResult);
            if (!deductSettingResult){
                throw new BizException(500,SubResultCode.DATA_INSTALL_SUCCESS.subCode(),SubResultCode.DATA_INSTALL_SUCCESS.subMsg());
            }
        }
        return R.success();
    }

    @Override
    public ResObject<Map<String, RecommendManagerVo>> batchRecommendManager(String[] ids) {
        log.info("-batchRecommendManager-方法请求参数{}",ids);
        if (ids.length <=0){
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        List<RecommendManagerEntity> recommendManagerEntityList = recommendManagerService.listByIds(Arrays.asList(ids));
        if (recommendManagerEntityList.isEmpty())return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        List<RecommendManagerVo> recommendManagerVoList = BeanConvertUtils.copyList(recommendManagerEntityList,RecommendManagerVo.class);
        String[] studentIds = recommendManagerVoList.stream().map(RecommendManagerVo::getStudentId).toArray(String[]::new);
        ResObject<Map<String, StudentInfoRpcVo>> studentResult = studentInfoRepository.batchStudent(studentIds);
        if (studentResult.getCode().equals(ResCodeEnum.SUCCESS.getCode()) && studentResult.getData() != null){
            Map<String, StudentInfoRpcVo>  studentInfoRpcVoMap = studentResult.getData();
            recommendManagerVoList.stream().forEach((item) ->{
               item.setPhone(studentInfoRpcVoMap.get(item.getStudentId()).getPhone());
            });
        }

        Map<String, RecommendManagerVo> appleMap = recommendManagerVoList.stream().collect(Collectors.toMap(RecommendManagerVo::getId, a -> a,(k1, k2)->k1));
        return R.success(appleMap);
    }
}

