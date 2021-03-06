package com.drive.admin.repository.impl;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.admin.enums.OperatorEnum;
import com.drive.admin.enums.StatusEnum;
import com.drive.admin.pojo.dto.CoachHourSettingDetailEditParam;
import com.drive.admin.pojo.dto.CoachHourSettingEditParam;
import com.drive.admin.pojo.dto.CoachHourSettingPageQueryParam;
import com.drive.admin.pojo.entity.CoachHourSettingDetailEntity;
import com.drive.admin.pojo.entity.CoachHourSettingEntity;
import com.drive.admin.pojo.entity.OperatorSettinngEntity;
import com.drive.admin.pojo.vo.CoachHourSettingVo;
import com.drive.admin.repository.CoachHourSettingRepository;
import com.drive.admin.service.CoachHourSettingDetailService;
import com.drive.admin.service.CoachHourSettingService;
import com.drive.admin.service.OperatorSettinngService;
import com.drive.admin.service.mapstruct.CoachHourSettingMapStruct;
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
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;


/**
 *
 * ?????????????????? ?????????
 *
 * @author xiaoguo
 */
@Slf4j
@Service
public class  CoachHourSettingRepositoryImpl extends BaseController<CoachHourSettingPageQueryParam, CoachHourSettingEntity>  implements CoachHourSettingRepository{


    // ???????????????
    private final String KM_OPERATOR_ID = "bbdc1bd499b241daa6fe99063e63a193";

    //  ?????????????????? ??????
    @Autowired
    private CoachHourSettingService coachHourSettingService;
    @Autowired
    private CoachHourSettingDetailService coachHourSettingDetailService;
    //  ?????????????????? DO-DTO??????
    @Autowired
    private CoachHourSettingMapStruct coachHourSettingMapStruct;

    // ???????????????
    @Autowired
    private OperatorSettinngService operatorSettinngService;

    /*
     *
     *????????????
     * @author xiaoguo
     * @description ?????????????????? ????????????
     * @date 2020/2/12 17:09
     * @param  * @param CoachHourSettingPageQueryParam
     * @return
     */
    @Override
    public ResObject pageList(CoachHourSettingPageQueryParam param) {
        log.info(this.getClass() + "pageList-??????????????????{}",param);
        Page<CoachHourSettingEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        // ????????????
        QueryWrapper queryWrapper = this.getQueryWrapper(coachHourSettingMapStruct, param);

        queryWrapper.apply(StrUtil.isNotEmpty(param.getEffectiveTimeSearch()),
                "date_format (effective_time,'%Y-%m-%d') = date_format('" + param.getEffectiveTimeSearch() + "','%Y-%m-%d')");
        //  ????????????
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueNameSearch()),"name",param.getVagueNameSearch());
        //  ???????????? ???????????????????????????
        if (StrUtil.isNotEmpty(param.getBeginTime()) && StrUtil.isNotEmpty(param.getEndTime())){
            queryWrapper.between(StrUtil.isNotEmpty(param.getBeginTime()),"effective_time",param.getBeginTime(),param.getEndTime());
        }
        IPage<CoachHourSettingEntity> pageList = coachHourSettingService.page(page, queryWrapper);
        if (pageList.getRecords().size() <= 0){
            log.error("?????????");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }
        Page<CoachHourSettingVo> coachHourSettingVoPage = coachHourSettingMapStruct.toVoList(pageList);
        log.info(this.getClass() + "pageList-??????????????????{}",coachHourSettingVoPage);
        return R.success(coachHourSettingVoPage);
    }

    @Override
    public ResObject findList(CoachHourSettingPageQueryParam param) {
        log.info(this.getClass() + "findList-??????????????????{}",param);
        // ??????????????????????????????
        QueryWrapper queryWrapper= this.getQueryWrapper(coachHourSettingMapStruct, param);
        // ??? queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"phone",param.getPhone());
        List<CoachHourSettingEntity> coachHourSettingList = coachHourSettingService.list(queryWrapper);
        if (coachHourSettingList == null){
            log.error("?????????");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),coachHourSettingList);
        }
        List<CoachHourSettingVo> coachHourSettingVoList = coachHourSettingMapStruct.toVoList(coachHourSettingList);
        log.info(this.getClass() + "findList-??????????????????{}",coachHourSettingVoList);
        return R.success(coachHourSettingVoList);
    }

    /**
    * ????????????????????????????????????????????????
    * @param param
    * @return
    */
    @Override
    public ResObject getInfo(CoachHourSettingPageQueryParam param) {
        log.info(this.getClass() + "getInfo-??????????????????{}",param);
        if (param == null){
            return R.failure("?????????");
        }
        // ??????????????????????????????
        QueryWrapper queryWrapper = this.getQueryWrapper(coachHourSettingMapStruct, param);
        CoachHourSettingEntity coachHourSetting = coachHourSettingService.getOne(queryWrapper);
        if (coachHourSetting == null){
            log.error("?????????");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),coachHourSetting);
        }
        CoachHourSettingVo coachHourSettingVo = BeanConvertUtils.copy(coachHourSetting, CoachHourSettingVo.class);
        log.info(this.getClass() + "getInfo-??????????????????{}",coachHourSettingVo);
        return R.success(coachHourSettingVo);
    }

    /**
     * *??????ID???????????????????????? ??????
     **/
    @Override
    public ResObject getById(String id) {
        log.info(this.getClass() + "getById-??????????????????{}",id);
        if (StrUtil.isEmpty(id)){
            return R.failure("?????????");
        }
        CoachHourSettingEntity coachHourSetting = coachHourSettingService.getById(id);
        if (coachHourSetting == null){
            log.error("?????????");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),coachHourSetting);
        }
        CoachHourSettingVo coachHourSettingVo = BeanConvertUtils.copy(coachHourSetting, CoachHourSettingVo.class);
        log.info(this.getClass() + "getById-??????????????????{}",coachHourSettingVo);
        return R.success(coachHourSettingVo);
    }

    /**
     * *???????????????????????? ??????
     **/
    @Override
    public ResObject save(CoachHourSettingEditParam installParam) {
        log.info(this.getClass() + "save??????????????????{}",installParam);
        if (installParam == null){
            log.error("?????????");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        CoachHourSettingEntity coachHourSetting = BeanConvertUtils.copy(installParam, CoachHourSettingEntity.class);
        Boolean result = coachHourSettingService.save(coachHourSetting);
        log.info(this.getClass() + "save-??????????????????{}",result);
        // ????????????
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *???????????????????????? ??????
     **/
    @Override
    public ResObject update(CoachHourSettingEditParam updateParam) {
        log.info(this.getClass() + "update??????????????????{}",updateParam);
        if (updateParam == null){
            log.error("?????????");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        CoachHourSettingEntity coachHourSetting = BeanConvertUtils.copy(updateParam, CoachHourSettingEntity.class);
        Boolean result = coachHourSettingService.updateById(coachHourSetting);
        log.info(this.getClass() + "update-??????????????????{}",result);
        // ????????????
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *???????????????????????? ??????
     **/
    @Override
    public ResObject deleteByIds(String[] ids) {
        if (ids.length <= 0){
            log.error("?????????");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        return R.toRes(coachHourSettingService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * *??????id???????????????????????? ??????
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
        Boolean result = coachHourSettingService.removeById(id);
        log.info(this.getClass() + "deleteById-??????????????????{}",result);
        // ????????????
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *???????????????????????? ??????
     **/
    @Override
    public ResObject exportXls(CoachHourSettingPageQueryParam param, HttpServletResponse response)throws IOException {
        log.info(this.getClass() + "exportXls??????????????????{}",param);
        QueryWrapper queryWrapper = this.getQueryWrapper(coachHourSettingMapStruct, param);
        List<CoachHourSettingEntity> list = coachHourSettingService.list(queryWrapper);
        List<CoachHourSettingVo>coachHourSettingList = coachHourSettingMapStruct.toVoList(list);
        ExcelUtils.exportExcel(coachHourSettingList, CoachHourSettingVo.class, "", new ExportParams(), response);
        return R.success("????????????");
    }

    /**
     * *????????????
     **/
    @Override
    public ResObject changeStatus(CoachHourSettingEditParam param) {
        log.info(this.getClass() + "changeStatus??????????????????{}",param);
        if (StrUtil.isEmpty(param.getId())){
            log.error("ID?????????");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        CoachHourSettingEntity coachHourSettingEntity = coachHourSettingService.getById(param.getId());
        // null error ????????????????????????
        if (coachHourSettingEntity.getStatus() .equals(StatusEnum.ENABLE.getCode())){
            return R.failure(SubResultCode.PARAMNOTCOMPLETE.subCode(),"???????????????");
        }
        // ???????????????????????????
        // ??????????????????????????????
        QueryWrapper queryWrapper = new QueryWrapper();
        // ???????????????
        queryWrapper.eq("operator_id",param.getOperatorId());
        // 2 ?????? ???????????????
        queryWrapper.eq("number",2);
        OperatorSettinngEntity operatorSettinng = operatorSettinngService.getOne(queryWrapper);
        if (operatorSettinng == null){
            log.error("?????????");
            return R.success(SubResultCode.DATA_NULL.subCode(),"??????????????????????????????????????????",operatorSettinng);
        }
        // ????????????+5???
        String dayNum = operatorSettinng.getNumberValue();
        // ???????????????
        DateTime effectiveDateTime = DateUtil.offset(new Date(), DateField.DAY_OF_MONTH, Integer.parseInt(dayNum));

        /**
         * Date1.after(Date2),???Date1??????Date2????????????TRUE??????????????????????????????false???
         *
         * Date1.before(Date2)??????Date1??????Date2????????????TRUE??????????????????????????????false???
         *
         * ????????????Date2?????????Date1????????????????????????????????? !Date1.after(Date2)
         */
        // ??????????????????
        log.info("??????{}",DateUtil.parse(effectiveDateTime.toString(), DatePattern.NORM_DATE_FORMAT));
        Boolean isBefore= asDate(param.getEffectiveTime()).before(DateUtil.parse(effectiveDateTime.toString(), DatePattern.NORM_DATE_FORMAT));
        if (isBefore){
            log.error("???????????????");
            return R.success(SubResultCode.ANALYSIS_PARAMS_ERROR.subCode(),"????????????????????????????????????????????????????????????");
        }
        QueryWrapper coachHourSettingQueryWrapper = new QueryWrapper();
        // ???????????????
        coachHourSettingQueryWrapper.eq("operator_id",param.getOperatorId());
        coachHourSettingQueryWrapper.eq("effective_time",param.getEffectiveTime());
        int coachHourSettingCount = coachHourSettingService.count(coachHourSettingQueryWrapper);
        if (coachHourSettingCount > 0){
            log.error("???????????????");
            return R.success(SubResultCode.DATA_IDEMPOTENT.subCode(),"??????????????????????????????????????????");
        }
        CoachHourSettingEntity CoachHourSettingEntity = new CoachHourSettingEntity();
        CoachHourSettingEntity.setId(param.getId());
        CoachHourSettingEntity.setStatus(param.getStatus());
        // ????????????
        CoachHourSettingEntity.setEffectiveTime(param.getEffectiveTime());
        //CoachHourSettingEntity.setUpdateTime()
        Boolean result = coachHourSettingService.updateById(CoachHourSettingEntity);
        log.info(this.getClass() +"changeStatus????????????????????????{}???????????????{}",CoachHourSettingEntity,result);
        // ????????????
        return result ?R.success(result):R.failure(result);
    }

    @Override
    @Transactional
    public ResObject publish(CoachHourSettingEditParam installParam) {
        log.info(this.getClass() + "publish??????????????????{}",installParam);
        if (installParam == null){
            log.error("?????????");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        // ???????????????????????????
        // ??????????????????????????????
   /*     QueryWrapper queryWrapper = new QueryWrapper();
        // ???????????????
        queryWrapper.eq("operator_id",installParam.getOperatorId());
        // 2 ?????? ???????????????
        queryWrapper.eq("number",2);
        OperatorSettinngEntity operatorSettinng = operatorSettinngService.getOne(queryWrapper);
        if (operatorSettinng == null){
            log.error("?????????");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),operatorSettinng);
        }
        // ????????????+5???
        String dayNum = operatorSettinng.getNumberValue();
        // ???????????????
        DateTime effectiveDateTime = DateUtil.offset(new Date(), DateField.DAY_OF_MONTH, Integer.parseInt(dayNum));
        // DateTime ??? LocalDateTime
        Instant instant = effectiveDateTime.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();*/
        CoachHourSettingEntity coachHourSetting = BeanConvertUtils.copy(installParam, CoachHourSettingEntity.class);
        // coachHourSetting.setEffectiveTime(localDateTime);
        coachHourSetting.setStatus(StatusEnum.SOLD.getCode());
        Boolean result = coachHourSettingService.save(coachHourSetting);
        log.info(this.getClass() + "publish-??????????????????{}",result);
        if (!result){
            // ????????????
            return  R.failure(SubResultCode.DATA_INSTALL_FAILL.subCode(),SubResultCode.DATA_INSTALL_FAILL.subMsg());
        }
        // ??????
        List<CoachHourSettingDetailEditParam> coachHourSettingDetailList = installParam.getCoachHourSettingDetailList();
        if (coachHourSettingDetailList.size() > 0){
            // ???????????? ?????????
            coachHourSettingDetailList.stream().forEach((item) ->{
                QueryWrapper coachHourSettingDetillQueryWrapper = new QueryWrapper();
                // ???????????????
                coachHourSettingDetillQueryWrapper.eq("operator_id",installParam.getOperatorId());
                coachHourSettingDetillQueryWrapper.eq(StrUtil.isNotEmpty(item.getStartTime()),"start_time",item.getStartTime());
                coachHourSettingDetillQueryWrapper.eq(StrUtil.isNotEmpty(item.getEndTime()),"end_time",item.getEndTime());
                int coachHourCount = coachHourSettingDetailService.count(coachHourSettingDetillQueryWrapper);
                if (coachHourCount >0 ){
                    log.error("????????????");
                    throw new BizException(500,SubResultCode.DATA_IDEMPOTENT.subCode(),SubResultCode.DATA_IDEMPOTENT.subMsg());
                    //return R.failure(SubResultCode.DATA_IDEMPOTENT.subCode(),SubResultCode.DATA_IDEMPOTENT.subMsg());
                }
                // ????????????Id
                item.setHourSettingId(coachHourSetting.getId());
            });
            log.info("??????????????????{}",coachHourSettingDetailService);
            //????????????
           Boolean batchResult  = coachHourSettingDetailService.saveBatch(BeanConvertUtils.copyList(coachHourSettingDetailList,CoachHourSettingDetailEntity.class));
           // ????????????
           if (!batchResult){
                // ????????????
               return  R.failure(SubResultCode.DATA_INSTALL_FAILL.subCode(),SubResultCode.DATA_INSTALL_FAILL.subMsg());
           }
        }
        // ????????????
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_INSTALL_SUCCESS.subMsg());
    }

    /**
     * ??????
     * @param coachHourSettingEditParam
     * @return
     */
    @Override
    @Transactional
    public ResObject updateCoachHourSetting(CoachHourSettingEditParam coachHourSettingEditParam) {
        log.info(this.getClass() + "publish??????????????????{}",coachHourSettingEditParam);
        if (coachHourSettingEditParam == null){
            log.error("?????????");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        // ???????????????????????????
        // ??????????????????????????????
        QueryWrapper queryWrapper = new QueryWrapper();
        // ???????????????
        queryWrapper.eq("operator_id",coachHourSettingEditParam.getOperatorId());
        // 2 ?????? ???????????????
        queryWrapper.eq("number",2);
        OperatorSettinngEntity operatorSettinng = operatorSettinngService.getOne(queryWrapper);
        if (operatorSettinng == null){
            log.error("?????????");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),operatorSettinng);
        }
        // ????????????+5???
        String dayNum = operatorSettinng.getNumberValue();
        // ???????????????
        DateTime effectiveDateTime = DateUtil.offset(new Date(), DateField.DAY_OF_MONTH, Integer.parseInt(dayNum));
        // DateTime ??? LocalDateTime
        Instant instant = effectiveDateTime.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
        CoachHourSettingEntity coachHourSetting = BeanConvertUtils.copy(coachHourSettingEditParam, CoachHourSettingEntity.class);
        coachHourSetting.setEffectiveTime(localDateTime);
        Boolean result = coachHourSettingService.updateById(coachHourSetting);
        log.info(this.getClass() + "publish-??????????????????{}",result);
        if (!result){
            // ????????????
            return  R.failure(SubResultCode.DATA_INSTALL_FAILL.subCode(),SubResultCode.DATA_INSTALL_FAILL.subMsg());
        }
        // ?????? ??????
        List<CoachHourSettingDetailEditParam> coachHourSettingDetailList = coachHourSettingEditParam.getCoachHourSettingDetailList();
        if (coachHourSettingDetailList.size() > 0){
            log.info("??????????????????{}",coachHourSettingDetailService);
            //????????????
            Boolean batchResult  = coachHourSettingDetailService.updateBatchById(BeanConvertUtils.copyList(coachHourSettingDetailList,CoachHourSettingDetailEntity.class));
            // ????????????
            if (!batchResult){
                // ????????????
                return  R.failure(SubResultCode.DATA_INSTALL_FAILL.subCode(),SubResultCode.DATA_INSTALL_FAILL.subMsg());
            }
        }
        // ????????????
        return R.success(SubResultCode.DATA_INSTALL_SUCCESS.subCode(),SubResultCode.DATA_INSTALL_SUCCESS.subMsg());

    }

    @Override
    public ResObject updateCoachHourSettingDetail(List<CoachHourSettingDetailEditParam> coachHourSettingDetailList) {
        log.info(this.getClass() + "updateCoachHourSettingDetill-??????????????????{}",coachHourSettingDetailList);
        if (coachHourSettingDetailList.size() <=0) {
            log.error("????????????");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        String hourSettingId = coachHourSettingDetailList.get(0).getHourSettingId();
        if (StrUtil.isEmpty(hourSettingId)){
            log.error("????????????");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
/*        coachHourSettingDetailList.stream().forEach((item) ->{
            QueryWrapper coachHourSettingDetillQueryWrapper = new QueryWrapper();
            // ???????????????
            coachHourSettingDetillQueryWrapper.eq("operator_id",item.getOperatorId());
            coachHourSettingDetillQueryWrapper.eq(StrUtil.isNotEmpty(item.getStartTime()),"start_time",item.getStartTime());
            coachHourSettingDetillQueryWrapper.eq(StrUtil.isNotEmpty(item.getEndTime()),"end_time",item.getEndTime());
            coachHourSettingDetillQueryWrapper.eq(StrUtil.isNotEmpty(item.getEndTime()),"hour_setting_id",item.getHourSettingId());
            int coachHourCount = coachHourSettingDetailService.count(coachHourSettingDetillQueryWrapper);
            if (coachHourCount >0){
                log.error("????????????");
                return;
                //return R.failure(SubResultCode.DATA_IDEMPOTENT.subCode(),SubResultCode.DATA_IDEMPOTENT.subMsg());
            }
        });*/
        log.info("???????????????{}",coachHourSettingDetailList);
        Boolean result = coachHourSettingDetailService.saveOrUpdateBatch(BeanConvertUtils.copyList(coachHourSettingDetailList,CoachHourSettingDetailEntity.class));
        if (!result){
            return R.failure(SubResultCode.DATA_INSTALL_FAILL.subCode(),SubResultCode.DATA_INSTALL_FAILL.subMsg());
        }
        return R.success();
    }

    @Override
    public ResObject getEffectiveDateTime(String operatorId) {
        log.info(this.getClass() + "getEffectiveDateTime??????????????????{}",operatorId);
        if (StrUtil.isEmpty(operatorId)){
            log.error("?????????");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        // ???????????????????????????
        // ??????????????????????????????
        QueryWrapper queryWrapper = new QueryWrapper();
        // ???????????????
        queryWrapper.eq("operator_id",operatorId);
        // ??????????????? ???????????? ??????????????????
        queryWrapper.eq("number", OperatorEnum.DAY.getCode());
        OperatorSettinngEntity operatorSettinng = operatorSettinngService.getOne(queryWrapper);
        if (operatorSettinng == null){
            log.error("?????????");
            //return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),operatorSettinng);

            QueryWrapper defaultQueryWrapper = new QueryWrapper();
            // ?????????????????????
            defaultQueryWrapper.eq("operator_id",KM_OPERATOR_ID);
            // ??????????????? ???????????? ??????????????????
            defaultQueryWrapper.eq("number", OperatorEnum.DAY.getCode());
            // ????????????????????????????????????
            operatorSettinng = operatorSettinngService.getOne(defaultQueryWrapper);
            // ???????????????????????????
            if (operatorSettinng == null){
                log.error("???????????????");
                return R.failure(SubResultCode.DATA_NULL.subCode(),"?????????????????????????????????????????????");
            }
        }
        // ????????????+5???
        String dayNum = operatorSettinng.getNumberValue();
        // ???????????????
        DateTime effectiveDateTime = DateUtil.offset(new Date(), DateField.DAY_OF_MONTH, Integer.parseInt(dayNum));
        // DateTime ??? LocalDateTime
        JSONObject jsonObject = new JSONObject();
        // ????????????
        jsonObject.put("effectiveDateTime",DateUtil.parse(effectiveDateTime.toString(), DatePattern.NORM_DATE_FORMAT));
        // ????????????
        jsonObject.put("courseDay",operatorSettinng.getNumberValue());
        return R.success(jsonObject);
    }


    //LocalDate -> Date
    public static Date asDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    //LocalDateTime -> Date
    public static Date asDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    //Date -> LocalDate
    public static LocalDate asLocalDate(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    //Date -> LocalDateTime
    public static LocalDateTime asLocalDateTime(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}

