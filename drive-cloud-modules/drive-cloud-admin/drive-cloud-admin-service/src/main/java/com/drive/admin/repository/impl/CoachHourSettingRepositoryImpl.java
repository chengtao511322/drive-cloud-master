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


/**
 *
 * 教练发课设置 服务类
 *
 * @author xiaoguo
 */
@Slf4j
@Service
public class  CoachHourSettingRepositoryImpl extends BaseController<CoachHourSettingPageQueryParam, CoachHourSettingEntity>  implements CoachHourSettingRepository{


    // 昆明运营商
    private final String KM_OPERATOR_ID = "bbdc1bd499b241daa6fe99063e63a193";

    //  教练发课设置 服务
    @Autowired
    private CoachHourSettingService coachHourSettingService;
    @Autowired
    private CoachHourSettingDetailService coachHourSettingDetailService;
    //  教练发课设置 DO-DTO转化
    @Autowired
    private CoachHourSettingMapStruct coachHourSettingMapStruct;

    // 运营商设置
    @Autowired
    private OperatorSettinngService operatorSettinngService;

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 教练发课设置 分页列表
     * @date 2020/2/12 17:09
     * @param  * @param CoachHourSettingPageQueryParam
     * @return
     */
    @Override
    public ResObject pageList(CoachHourSettingPageQueryParam param) {
        log.info(this.getClass() + "pageList-方法请求参数{}",param);
        Page<CoachHourSettingEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        // 条件查询
        QueryWrapper queryWrapper = this.getQueryWrapper(coachHourSettingMapStruct, param);

        queryWrapper.apply(StrUtil.isNotEmpty(param.getEffectiveTimeSearch()),
                "date_format (effective_time,'%Y-%m-%d') = date_format('" + param.getEffectiveTimeSearch() + "','%Y-%m-%d')");
        //  模糊查询
        //queryWrapper.like(StrUtil.isNotEmpty(param.getVagueNameSearch()),"name",param.getVagueNameSearch());
        //  开始时间 结束时间都有才进入
        if (StrUtil.isNotEmpty(param.getBeginTime()) && StrUtil.isNotEmpty(param.getEndTime())){
            queryWrapper.between(StrUtil.isNotEmpty(param.getBeginTime()),"effective_time",param.getBeginTime(),param.getEndTime());
        }
        IPage<CoachHourSettingEntity> pageList = coachHourSettingService.page(page, queryWrapper);
        if (pageList.getRecords().size() <= 0){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }
        Page<CoachHourSettingVo> coachHourSettingVoPage = coachHourSettingMapStruct.toVoList(pageList);
        log.info(this.getClass() + "pageList-方法请求结果{}",coachHourSettingVoPage);
        return R.success(coachHourSettingVoPage);
    }

    @Override
    public ResObject findList(CoachHourSettingPageQueryParam param) {
        log.info(this.getClass() + "findList-方法请求参数{}",param);
        // 这里判断条件进行查询
        QueryWrapper queryWrapper= this.getQueryWrapper(coachHourSettingMapStruct, param);
        // 如 queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"phone",param.getPhone());
        List<CoachHourSettingEntity> coachHourSettingList = coachHourSettingService.list(queryWrapper);
        if (coachHourSettingList == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),coachHourSettingList);
        }
        List<CoachHourSettingVo> coachHourSettingVoList = coachHourSettingMapStruct.toVoList(coachHourSettingList);
        log.info(this.getClass() + "findList-方法请求结果{}",coachHourSettingVoList);
        return R.success(coachHourSettingVoList);
    }

    /**
    * 条件查询返回单条教练发课设置数据
    * @param param
    * @return
    */
    @Override
    public ResObject getInfo(CoachHourSettingPageQueryParam param) {
        log.info(this.getClass() + "getInfo-方法请求参数{}",param);
        if (param == null){
            return R.failure("数据空");
        }
        // 这里判断条件进行查询
        QueryWrapper queryWrapper = this.getQueryWrapper(coachHourSettingMapStruct, param);
        CoachHourSettingEntity coachHourSetting = coachHourSettingService.getOne(queryWrapper);
        if (coachHourSetting == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),coachHourSetting);
        }
        CoachHourSettingVo coachHourSettingVo = BeanConvertUtils.copy(coachHourSetting, CoachHourSettingVo.class);
        log.info(this.getClass() + "getInfo-方法请求结果{}",coachHourSettingVo);
        return R.success(coachHourSettingVo);
    }

    /**
     * *通过ID获取教练发课设置 列表
     **/
    @Override
    public ResObject getById(String id) {
        log.info(this.getClass() + "getById-方法请求参数{}",id);
        if (StrUtil.isEmpty(id)){
            return R.failure("数据空");
        }
        CoachHourSettingEntity coachHourSetting = coachHourSettingService.getById(id);
        if (coachHourSetting == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),coachHourSetting);
        }
        CoachHourSettingVo coachHourSettingVo = BeanConvertUtils.copy(coachHourSetting, CoachHourSettingVo.class);
        log.info(this.getClass() + "getById-方法请求结果{}",coachHourSettingVo);
        return R.success(coachHourSettingVo);
    }

    /**
     * *保存教练发课设置 信息
     **/
    @Override
    public ResObject save(CoachHourSettingEditParam installParam) {
        log.info(this.getClass() + "save方法请求参数{}",installParam);
        if (installParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        CoachHourSettingEntity coachHourSetting = BeanConvertUtils.copy(installParam, CoachHourSettingEntity.class);
        Boolean result = coachHourSettingService.save(coachHourSetting);
        log.info(this.getClass() + "save-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *修改教练发课设置 信息
     **/
    @Override
    public ResObject update(CoachHourSettingEditParam updateParam) {
        log.info(this.getClass() + "update方法请求参数{}",updateParam);
        if (updateParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        CoachHourSettingEntity coachHourSetting = BeanConvertUtils.copy(updateParam, CoachHourSettingEntity.class);
        Boolean result = coachHourSettingService.updateById(coachHourSetting);
        log.info(this.getClass() + "update-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *删除教练发课设置 信息
     **/
    @Override
    public ResObject deleteByIds(String[] ids) {
        if (ids.length <= 0){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        return R.toRes(coachHourSettingService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * *通过id删除教练发课设置 信息
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
        Boolean result = coachHourSettingService.removeById(id);
        log.info(this.getClass() + "deleteById-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *导出教练发课设置 信息
     **/
    @Override
    public ResObject exportXls(CoachHourSettingPageQueryParam param, HttpServletResponse response)throws IOException {
        log.info(this.getClass() + "exportXls方法请求参数{}",param);
        QueryWrapper queryWrapper = this.getQueryWrapper(coachHourSettingMapStruct, param);
        List<CoachHourSettingEntity> list = coachHourSettingService.list(queryWrapper);
        List<CoachHourSettingVo>coachHourSettingList = coachHourSettingMapStruct.toVoList(list);
        ExcelUtils.exportExcel(coachHourSettingList, CoachHourSettingVo.class, "", new ExportParams(), response);
        return R.success("导出成功");
    }

    /**
     * *修改状态
     **/
    @Override
    public ResObject changeStatus(CoachHourSettingEditParam param) {
        log.info(this.getClass() + "changeStatus方法请求参数{}",param);
        if (StrUtil.isEmpty(param.getId())){
            log.error("ID数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        CoachHourSettingEntity coachHourSettingEntity = coachHourSettingService.getById(param.getId());
        // null error 启用的不允许停用
        if (coachHourSettingEntity.getStatus() .equals(StatusEnum.ENABLE.getCode())){
            return R.failure(SubResultCode.PARAMNOTCOMPLETE.subCode(),"不允许启用");
        }
        // 查询运营商设置天数
        // 这里判断条件进行查询
        QueryWrapper queryWrapper = new QueryWrapper();
        // 运营商查询
        queryWrapper.eq("operator_id",param.getOperatorId());
        // 2 表示 运营商发科
        queryWrapper.eq("number",2);
        OperatorSettinngEntity operatorSettinng = operatorSettinngService.getOne(queryWrapper);
        if (operatorSettinng == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),"你还未设置该运营商的发课天数",operatorSettinng);
        }
        // 当前时间+5天
        String dayNum = operatorSettinng.getNumberValue();
        // 可能出现空
        DateTime effectiveDateTime = DateUtil.offset(new Date(), DateField.DAY_OF_MONTH, Integer.parseInt(dayNum));

        /**
         * Date1.after(Date2),当Date1大于Date2时，返回TRUE，当小于等于时，返回false；
         *
         * Date1.before(Date2)，当Date1小于Date2时，返回TRUE，当大于等于时，返回false；
         *
         * 若要校验Date2必须比Date1小，且不能相等时，使用 !Date1.after(Date2)
         */
        // 时间比较大小
        log.info("时间{}",DateUtil.parse(effectiveDateTime.toString(), DatePattern.NORM_DATE_FORMAT));
        Boolean isBefore= asDate(param.getEffectiveTime()).before(DateUtil.parse(effectiveDateTime.toString(), DatePattern.NORM_DATE_FORMAT));
        if (isBefore){
            log.error("不允许操作");
            return R.success(SubResultCode.ANALYSIS_PARAMS_ERROR.subCode(),"传入的生效时间不在运营商设置的时间区域内");
        }
        QueryWrapper coachHourSettingQueryWrapper = new QueryWrapper();
        // 运营商查询
        coachHourSettingQueryWrapper.eq("operator_id",param.getOperatorId());
        coachHourSettingQueryWrapper.eq("effective_time",param.getEffectiveTime());
        int coachHourSettingCount = coachHourSettingService.count(coachHourSettingQueryWrapper);
        if (coachHourSettingCount > 0){
            log.error("不允许操作");
            return R.success(SubResultCode.DATA_IDEMPOTENT.subCode(),"生效时间存在相同，不允许操作");
        }
        CoachHourSettingEntity CoachHourSettingEntity = new CoachHourSettingEntity();
        CoachHourSettingEntity.setId(param.getId());
        CoachHourSettingEntity.setStatus(param.getStatus());
        // 生效时间
        CoachHourSettingEntity.setEffectiveTime(param.getEffectiveTime());
        //CoachHourSettingEntity.setUpdateTime()
        Boolean result = coachHourSettingService.updateById(CoachHourSettingEntity);
        log.info(this.getClass() +"changeStatus方法请求对象参数{}，请求结果{}",CoachHourSettingEntity,result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    @Override
    @Transactional
    public ResObject publish(CoachHourSettingEditParam installParam) {
        log.info(this.getClass() + "publish方法请求参数{}",installParam);
        if (installParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        // 查询运营商设置天数
        // 这里判断条件进行查询
   /*     QueryWrapper queryWrapper = new QueryWrapper();
        // 运营商查询
        queryWrapper.eq("operator_id",installParam.getOperatorId());
        // 2 表示 运营商发科
        queryWrapper.eq("number",2);
        OperatorSettinngEntity operatorSettinng = operatorSettinngService.getOne(queryWrapper);
        if (operatorSettinng == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),operatorSettinng);
        }
        // 当前时间+5天
        String dayNum = operatorSettinng.getNumberValue();
        // 可能出现空
        DateTime effectiveDateTime = DateUtil.offset(new Date(), DateField.DAY_OF_MONTH, Integer.parseInt(dayNum));
        // DateTime 转 LocalDateTime
        Instant instant = effectiveDateTime.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();*/
        CoachHourSettingEntity coachHourSetting = BeanConvertUtils.copy(installParam, CoachHourSettingEntity.class);
        // coachHourSetting.setEffectiveTime(localDateTime);
        coachHourSetting.setStatus(StatusEnum.SOLD.getCode());
        Boolean result = coachHourSettingService.save(coachHourSetting);
        log.info(this.getClass() + "publish-方法请求结果{}",result);
        if (!result){
            // 添加失败
            return  R.failure(SubResultCode.DATA_INSTALL_FAILL.subCode(),SubResultCode.DATA_INSTALL_FAILL.subMsg());
        }
        // 字表
        List<CoachHourSettingDetailEditParam> coachHourSettingDetailList = installParam.getCoachHourSettingDetailList();
        if (coachHourSettingDetailList.size() > 0){
            // 匹配数据 正确性
            coachHourSettingDetailList.stream().forEach((item) ->{
                QueryWrapper coachHourSettingDetillQueryWrapper = new QueryWrapper();
                // 运营商查询
                coachHourSettingDetillQueryWrapper.eq("operator_id",installParam.getOperatorId());
                coachHourSettingDetillQueryWrapper.eq(StrUtil.isNotEmpty(item.getStartTime()),"start_time",item.getStartTime());
                coachHourSettingDetillQueryWrapper.eq(StrUtil.isNotEmpty(item.getEndTime()),"end_time",item.getEndTime());
                int coachHourCount = coachHourSettingDetailService.count(coachHourSettingDetillQueryWrapper);
                if (coachHourCount >0 ){
                    log.error("数据重复");
                    throw new BizException(500,SubResultCode.DATA_IDEMPOTENT.subCode(),SubResultCode.DATA_IDEMPOTENT.subMsg());
                    //return R.failure(SubResultCode.DATA_IDEMPOTENT.subCode(),SubResultCode.DATA_IDEMPOTENT.subMsg());
                }
                // 设置主表Id
                item.setHourSettingId(coachHourSetting.getId());
            });
            log.info("插入的数据是{}",coachHourSettingDetailService);
            //批量插入
           Boolean batchResult  = coachHourSettingDetailService.saveBatch(BeanConvertUtils.copyList(coachHourSettingDetailList,CoachHourSettingDetailEntity.class));
           // 插入失败
           if (!batchResult){
                // 添加失败
               return  R.failure(SubResultCode.DATA_INSTALL_FAILL.subCode(),SubResultCode.DATA_INSTALL_FAILL.subMsg());
           }
        }
        // 判断结果
        return R.success(SubResultCode.DATA_INSTALL_SUCCESS.subCode(),SubResultCode.DATA_INSTALL_SUCCESS.subMsg());
    }

    /**
     * 修改
     * @param coachHourSettingEditParam
     * @return
     */
    @Override
    @Transactional
    public ResObject updateCoachHourSetting(CoachHourSettingEditParam coachHourSettingEditParam) {
        log.info(this.getClass() + "publish方法请求参数{}",coachHourSettingEditParam);
        if (coachHourSettingEditParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        // 查询运营商设置天数
        // 这里判断条件进行查询
        QueryWrapper queryWrapper = new QueryWrapper();
        // 运营商查询
        queryWrapper.eq("operator_id",coachHourSettingEditParam.getOperatorId());
        // 2 表示 运营商发科
        queryWrapper.eq("number",2);
        OperatorSettinngEntity operatorSettinng = operatorSettinngService.getOne(queryWrapper);
        if (operatorSettinng == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),operatorSettinng);
        }
        // 当前时间+5天
        String dayNum = operatorSettinng.getNumberValue();
        // 可能出现空
        DateTime effectiveDateTime = DateUtil.offset(new Date(), DateField.DAY_OF_MONTH, Integer.parseInt(dayNum));
        // DateTime 转 LocalDateTime
        Instant instant = effectiveDateTime.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
        CoachHourSettingEntity coachHourSetting = BeanConvertUtils.copy(coachHourSettingEditParam, CoachHourSettingEntity.class);
        coachHourSetting.setEffectiveTime(localDateTime);
        Boolean result = coachHourSettingService.updateById(coachHourSetting);
        log.info(this.getClass() + "publish-方法请求结果{}",result);
        if (!result){
            // 添加失败
            return  R.failure(SubResultCode.DATA_INSTALL_FAILL.subCode(),SubResultCode.DATA_INSTALL_FAILL.subMsg());
        }
        // 字表 数据
        List<CoachHourSettingDetailEditParam> coachHourSettingDetailList = coachHourSettingEditParam.getCoachHourSettingDetailList();
        if (coachHourSettingDetailList.size() > 0){
            log.info("插入的数据是{}",coachHourSettingDetailService);
            //批量插入
            Boolean batchResult  = coachHourSettingDetailService.updateBatchById(BeanConvertUtils.copyList(coachHourSettingDetailList,CoachHourSettingDetailEntity.class));
            // 插入失败
            if (!batchResult){
                // 添加失败
                return  R.failure(SubResultCode.DATA_INSTALL_FAILL.subCode(),SubResultCode.DATA_INSTALL_FAILL.subMsg());
            }
        }
        // 判断结果
        return R.success(SubResultCode.DATA_INSTALL_SUCCESS.subCode(),SubResultCode.DATA_INSTALL_SUCCESS.subMsg());

    }

    @Override
    public ResObject updateCoachHourSettingDetail(List<CoachHourSettingDetailEditParam> coachHourSettingDetailList) {
        log.info(this.getClass() + "updateCoachHourSettingDetill-方法请求参数{}",coachHourSettingDetailList);
        if (coachHourSettingDetailList.size() <=0) {
            log.error("数据空吗");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        String hourSettingId = coachHourSettingDetailList.get(0).getHourSettingId();
        if (StrUtil.isEmpty(hourSettingId)){
            log.error("数据空吗");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
/*        coachHourSettingDetailList.stream().forEach((item) ->{
            QueryWrapper coachHourSettingDetillQueryWrapper = new QueryWrapper();
            // 运营商查询
            coachHourSettingDetillQueryWrapper.eq("operator_id",item.getOperatorId());
            coachHourSettingDetillQueryWrapper.eq(StrUtil.isNotEmpty(item.getStartTime()),"start_time",item.getStartTime());
            coachHourSettingDetillQueryWrapper.eq(StrUtil.isNotEmpty(item.getEndTime()),"end_time",item.getEndTime());
            coachHourSettingDetillQueryWrapper.eq(StrUtil.isNotEmpty(item.getEndTime()),"hour_setting_id",item.getHourSettingId());
            int coachHourCount = coachHourSettingDetailService.count(coachHourSettingDetillQueryWrapper);
            if (coachHourCount >0){
                log.error("数据重复");
                return;
                //return R.failure(SubResultCode.DATA_IDEMPOTENT.subCode(),SubResultCode.DATA_IDEMPOTENT.subMsg());
            }
        });*/
        log.info("添加的数据{}",coachHourSettingDetailList);
        Boolean result = coachHourSettingDetailService.saveOrUpdateBatch(BeanConvertUtils.copyList(coachHourSettingDetailList,CoachHourSettingDetailEntity.class));
        if (!result){
            return R.failure(SubResultCode.DATA_INSTALL_FAILL.subCode(),SubResultCode.DATA_INSTALL_FAILL.subMsg());
        }
        return R.success();
    }

    @Override
    public ResObject getEffectiveDateTime(String operatorId) {
        log.info(this.getClass() + "getEffectiveDateTime方法请求参数{}",operatorId);
        if (StrUtil.isEmpty(operatorId)){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        // 查询运营商设置天数
        // 这里判断条件进行查询
        QueryWrapper queryWrapper = new QueryWrapper();
        // 运营商查询
        queryWrapper.eq("operator_id",operatorId);
        // 这个值就是 固定查询 运营商天数的
        queryWrapper.eq("number", OperatorEnum.DAY.getCode());
        OperatorSettinngEntity operatorSettinng = operatorSettinngService.getOne(queryWrapper);
        if (operatorSettinng == null){
            log.error("数据空");
            //return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),operatorSettinng);

            QueryWrapper defaultQueryWrapper = new QueryWrapper();
            // 昆明运营商查询
            defaultQueryWrapper.eq("operator_id",KM_OPERATOR_ID);
            // 这个值就是 固定查询 运营商天数的
            defaultQueryWrapper.eq("number", OperatorEnum.DAY.getCode());
            // 查询昆明运营商设置的数据
            operatorSettinng = operatorSettinngService.getOne(defaultQueryWrapper);
            // 如果昆明还没有的话
            if (operatorSettinng == null){
                log.error("昆明数据空");
                return R.failure(SubResultCode.DATA_NULL.subCode(),"请联系管理员设置运营商开课数据");
            }
        }
        // 当前时间+5天
        String dayNum = operatorSettinng.getNumberValue();
        // 可能出现空
        DateTime effectiveDateTime = DateUtil.offset(new Date(), DateField.DAY_OF_MONTH, Integer.parseInt(dayNum));
        // DateTime 转 LocalDateTime
        JSONObject jsonObject = new JSONObject();
        // 有效时间
        jsonObject.put("effectiveDateTime",DateUtil.parse(effectiveDateTime.toString(), DatePattern.NORM_DATE_FORMAT));
        // 开课天数
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

