package com.drive.admin.repository.impl;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.admin.enums.StatusEnum;
import com.drive.admin.pojo.dto.RecommendUserEditParam;
import com.drive.admin.pojo.dto.RecommendUserPageQueryParam;
import com.drive.admin.pojo.dto.StudentInfoPageQueryParam;
import com.drive.admin.pojo.entity.RecommendManagerEntity;
import com.drive.admin.pojo.entity.RecommendUserEntity;
import com.drive.admin.pojo.entity.StudentInfoEntity;
import com.drive.admin.pojo.entity.TestTrainPriceEntity;
import com.drive.admin.pojo.vo.RecommendManagerVo;
import com.drive.admin.pojo.vo.RecommendUserVo;
import com.drive.admin.pojo.vo.StudentInfoRpcVo;
import com.drive.admin.pojo.vo.StudentInfoVo;
import com.drive.admin.pojo.vo.StudentInfoVo;
import com.drive.admin.repository.RecommendManagerRepository;
import com.drive.admin.repository.RecommendUserRepository;
import com.drive.admin.repository.StudentInfoRepository;
import com.drive.admin.service.RecommendManagerService;
import com.drive.admin.service.RecommendUserService;
import com.drive.admin.service.StudentInfoService;
import com.drive.admin.service.mapstruct.RecommendUserMapStruct;
import com.drive.common.core.base.BaseController;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResCodeEnum;
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
import java.util.*;
import java.util.stream.Collectors;


/**
 *
 * ????????????????????? ?????????
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

    @Autowired
    private StudentInfoRepository studentInfoRepository;

    /*
     *????????????
     * @author xiaoguo
     * @description ????????????????????? ????????????
     * @date 2020/2/12 17:09
     * @param  * @param RecommendUserPageQueryParam
     * @return
     */
    @Override
    public ResObject pageList(RecommendUserPageQueryParam param) {
        log.info(this.getClass() + "pageList-??????????????????{}",param);
        Page<RecommendUserEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        QueryWrapper queryWrapper = this.getQueryWrapper(recommendUserMapStruct, param);
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueUserNameSearch()),"name",param.getVagueUserNameSearch());

        // ?????????????????????
        if (StrUtil.isNotEmpty(param.getVagueRealNameSearch()) || StrUtil.isNotEmpty(param.getVaguePhoneSearch())){
            List<StudentInfoEntity> studentInfoList = new ArrayList<>();
            QueryWrapper studentQueryWrapper = new QueryWrapper();
            studentQueryWrapper.like(StrUtil.isNotEmpty(param.getVagueRealNameSearch()),"real_name",param.getVagueRealNameSearch());
            studentQueryWrapper.like(StrUtil.isNotEmpty(param.getVaguePhoneSearch()),"phone",param.getVaguePhoneSearch());
            studentInfoList = studentInfoService.list(studentQueryWrapper);
            if(studentInfoList.size() <= 0)return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),studentInfoList);
            queryWrapper.in("student_id",studentInfoList.stream().map(StudentInfoEntity::getId).collect(Collectors.toList()));
        }

        //  ??????????????????
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
            // ??????
        /*    item.setStudentName(Optional.ofNullable(studentInfoService.getById(item.getStudentId()))
                    .map(u-> u.getRealName())
                    .orElseThrow(()->new BizException("?????????????????????")));*/

            if (StrUtil.isNotEmpty(item.getStudentId())){
                StudentInfoEntity studentInfo = studentInfoService.getById(item.getStudentId());
                if (studentInfo != null){
                    item.setUserName(studentInfo.getUsername());
                    item.setPhone(studentInfo.getPhone());
                }
            }
            // ????????????
           /* item.setManagerName(Optional.ofNullable(recommendManagerService.getById(item.getManagerId()))
                    .map(u-> u.getRemarks())
                    .orElseThrow(()->new BizException("???????????????????????????")));
*/

            if (StrUtil.isNotEmpty(item.getManagerId())){
                RecommendManagerEntity manager = recommendManagerService.getById(item.getStudentId());
                if (manager != null)item.setStudentName(manager.getRemarks());
            }
        });
        log.info(this.getClass() + "pageList-??????????????????{}",recommendUserVoPage);
        return R.success(recommendUserVoPage);
    }

    @Override
    public ResObject findList(RecommendUserPageQueryParam param) {
        log.info(this.getClass() + "findList-??????????????????{}",param);
        // ??????????????????????????????
        QueryWrapper queryWrapper= this.getQueryWrapper(recommendUserMapStruct, param);
        // ??? queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"phone",param.getPhone());
        List<RecommendUserEntity> pageList = recommendUserService.list(queryWrapper);
        List<RecommendUserVo> recommendUserVoList = recommendUserMapStruct.toVoList(pageList);
        log.info(this.getClass() + "findList-??????????????????{}",recommendUserVoList);
        if (recommendUserVoList == null){
            log.error("?????????");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        return R.success(recommendUserVoList);
    }



    @Override
    public ResObject getInfo(RecommendUserPageQueryParam param) {
        log.info(this.getClass() + "getInfo-??????????????????{}",param);
        if (param == null){
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        QueryWrapper queryWrapper= this.getQueryWrapper(recommendUserMapStruct, param);
        queryWrapper.last("limit 1");
        RecommendUserEntity recommendUserEntity = recommendUserService.getOne(queryWrapper);
        if (recommendUserEntity == null){
            return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        //  do - vo ??????
        RecommendUserVo recommendUserVo = BeanConvertUtils.copy(recommendUserEntity,RecommendUserVo.class);
        return R.success(recommendUserVo);
    }

    /**
     * *??????ID??????????????????????????? ??????
     **/
    @Override
    public ResObject getById(String id) {
        log.info(this.getClass() + "getInfo-??????????????????{}",id);
        if (StrUtil.isEmpty(id)){
            return R.failure("?????????");
        }
        RecommendUserEntity recommendUser = recommendUserService.getById(id);
        if (recommendUser ==null){
            log.error("?????????????????????");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        RecommendUserVo recommendUserVo = BeanConvertUtils.copy(recommendUser, RecommendUserVo.class);
        StudentInfoEntity studentInfo = studentInfoService.getById(recommendUserVo.getStudentId());
        if (studentInfo != null){
            recommendUserVo.setPhone(studentInfo.getPhone());
            recommendUserVo.setStudentName(studentInfo.getUsername());
        }
        // ??????????????????
        RecommendManagerEntity recommendManager = recommendManagerService.getById(recommendUserVo.getManagerId());
        // ??????{}??????????????????????????????????????? ????????????????????????
        if (recommendManager != null)recommendUserVo.setManagerName(recommendManager.getRemarks());
        log.info(this.getClass() + "getInfo-??????????????????{}",recommendUserVo);
        return R.success(recommendUserVo);
    }

    /**
     * *??????????????????????????? ??????
     **/
    @Override
    public ResObject save(RecommendUserEditParam installParam) {
        log.info(this.getClass() + "save??????????????????{}",installParam);
        if (installParam == null){
            log.error("?????????");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        // ???????????????
        QueryWrapper queryWrapper = new QueryWrapper();
        // ??????id
        queryWrapper.eq(StrUtil.isNotEmpty(installParam.getStudentId()),"student_id",installParam.getStudentId());
        // ???????????????
        queryWrapper.eq(StrUtil.isNotEmpty(installParam.getName()),"name",installParam.getName());
        queryWrapper.eq(StrUtil.isNotEmpty(installParam.getOperatorId()),"operator_id",installParam.getOperatorId());
        queryWrapper.last("limit 1");
        int isRecommendUser = recommendUserService.count(queryWrapper);
        if (isRecommendUser > 0){
            return R.success(SubResultCode.DATA_IDEMPOTENT.subCode(),SubResultCode.DATA_IDEMPOTENT.subMsg());
        }
        RecommendUserEntity recommendUser = BeanConvertUtils.copy(installParam, RecommendUserEntity.class);
        // ??????
        recommendUser.setStatus(StatusEnum.ENABLE.getCode());
        Boolean result = recommendUserService.save(recommendUser);
        log.info(this.getClass() + "save-??????????????????{}",result);
        // ????????????
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *??????????????????????????? ??????
     **/
    @Override
    public ResObject update(RecommendUserEditParam updateParam) {
        log.info(this.getClass() + "update??????????????????{}",updateParam);
        if (updateParam == null){
            log.error("?????????");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }

        // ???????????????
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.setEntity(updateParam);
        queryWrapper.last("limit 1");
        int isRecommendUser = recommendUserService.count(queryWrapper);
        if (isRecommendUser > 0){
            return R.success(SubResultCode.DATA_IDEMPOTENT.subCode(),SubResultCode.DATA_IDEMPOTENT.subMsg());
        }
        RecommendUserEntity recommendUser = BeanConvertUtils.copy(updateParam, RecommendUserEntity.class);
        Boolean result = recommendUserService.updateById(recommendUser);
        log.info(this.getClass() + "update-??????????????????{}",result);
        // ????????????
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *??????????????????????????? ??????
     **/
    @Override
    public ResObject deleteByIds(String[] ids) {
        if (ids.length <= 0){
            log.error("?????????");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        return R.toRes(recommendUserService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * *??????id??????????????????????????? ??????
     **/
    @Override
    public ResObject deleteById(String id) {
        log.info(this.getClass() + "deleteById-??????????????????{}",id);
        if(StrUtil.isEmpty(id)){
            log.error("ID?????????");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        //QueryWrapper<String> queryWrapper = new QueryWrapper<String>();
        //queryWrapper.eq(StrUtil.isNotEmpty(id),"id",id);
        Boolean result = recommendUserService.removeById(id);
        log.info(this.getClass() + "deleteById-??????????????????{}",result);
        // ????????????
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *??????????????????????????? ??????
     **/
    @Override
    public ResObject exportXls(RecommendUserPageQueryParam param, HttpServletResponse response)throws IOException {
        log.info(this.getClass() + "exportXls??????????????????{}",param);
        QueryWrapper queryWrapper = this.getQueryWrapper(recommendUserMapStruct, param);
        List<RecommendUserEntity> list = recommendUserService.list(queryWrapper);
        List<RecommendUserVo>recommendUserList = recommendUserMapStruct.toVoList(list);
        ExcelUtils.exportExcel(recommendUserList, RecommendUserVo.class, "", new ExportParams(), response);
        return R.success("????????????");
    }

    /**
     * *????????????
     **/
    @Override
    public ResObject changeStatus(RecommendUserEditParam param) {
        log.info(this.getClass() + "changeStatus??????????????????{}",param);
        if (StrUtil.isEmpty(param.getId())){
            log.error("ID?????????");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        RecommendUserEntity RecommendUserEntity = new RecommendUserEntity();
        RecommendUserEntity.setId(param.getId());
        RecommendUserEntity.setStatus(param.getStatus());
        //RecommendUserEntity.setUpdateTime()
        Boolean result = recommendUserService.updateById(RecommendUserEntity);
        log.info(this.getClass() +"changeStatus????????????????????????{}???????????????{}",RecommendUserEntity,result);
        // ????????????
        return result ?R.success(result):R.failure(result);
    }

    /**
     * ????????????????????????????????????
     */
    @Override
    public ResObject getRecommendUserInfoByPhone(String phone){
        log.info(this.getClass() + "??????????????????????????????{}",phone);
        if (StrUtil.isEmpty(phone)){
            log.error("?????????");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        StudentInfoPageQueryParam studentParam = new StudentInfoPageQueryParam();
        studentParam.setPhone(phone);
        ResObject studentRes = studentInfoRepository.getInfo(studentParam);
        //???????????????
        if(studentRes.getCode() == 200 && studentRes.getData() != null){
            //??????????????????id
            StudentInfoVo studentInfoVo = (StudentInfoVo) studentRes.getData();
            RecommendUserPageQueryParam recommendUserPageQueryParam = new RecommendUserPageQueryParam();
            recommendUserPageQueryParam.setStudentId(studentInfoVo.getId());
            ResObject recommendRes = this.getInfo(recommendUserPageQueryParam);
            if(recommendRes.getCode() == 200 && recommendRes.getData() != null){
                return recommendRes;
            }else {
                return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
            }
        }else {
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
    }

    @Override
    public ResObject<Map<String, RecommendUserVo>> batchRecommendUserVo(String[] ids) {
        log.info("-batchRecommendUserVo-??????????????????{}",ids);
        if (ids.length <=0){
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        List<RecommendUserEntity> recommendUserEntityList = recommendUserService.listByIds(Arrays.asList(ids));
        if (recommendUserEntityList.isEmpty())return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        List<RecommendUserVo> recommendUserVoList = BeanConvertUtils.copyList(recommendUserEntityList,RecommendUserVo.class);
        String[] studentIds = recommendUserVoList.stream().map(RecommendUserVo::getStudentId).toArray(String[]::new);
        ResObject<Map<String, StudentInfoRpcVo>> studentResult = studentInfoRepository.batchStudent(studentIds);
        if (studentResult.getCode().equals(ResCodeEnum.SUCCESS.getCode()) && studentResult.getData() != null){
            Map<String, StudentInfoRpcVo>  studentInfoRpcVoMap = studentResult.getData();
            recommendUserVoList.stream().forEach((item) ->{
                item.setPhone(studentInfoRpcVoMap.get(item.getStudentId()).getPhone());
            });
        }
        Map<String, RecommendUserVo> appleMap = recommendUserVoList.stream().collect(Collectors.toMap(RecommendUserVo::getId, a -> a,(k1, k2)->k1));
        return R.success(appleMap);
    }
}

