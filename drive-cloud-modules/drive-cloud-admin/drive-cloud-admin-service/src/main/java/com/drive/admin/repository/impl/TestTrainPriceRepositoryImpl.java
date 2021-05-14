package com.drive.admin.repository.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.drive.admin.enums.PriceTypeEnum;
import com.drive.admin.pojo.entity.TestTrainPriceEntity;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.drive.common.core.base.BaseController;
import com.drive.admin.repository.TestTrainPriceRepository;
import com.drive.admin.pojo.entity.*;
import com.drive.admin.pojo.vo.*;
import com.drive.admin.pojo.dto.*;
import com.drive.admin.service.mapstruct.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.hutool.core.util.StrUtil;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.SubResultCode;
import com.drive.common.core.exception.BizException;
import com.drive.common.core.utils.BeanConvertUtils;
import lombok.extern.slf4j.Slf4j;
import com.drive.common.core.biz.ResObject;
import org.springframework.beans.factory.annotation.Autowired;
import com.drive.admin.service.TestTrainPriceService;
import com.drive.common.data.utils.ExcelUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Arrays;
import java.io.IOException;
import java.util.Optional;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

                                                            
/**
 *
 * 平台报名考试练车单价表 服务类
 *
 * @author xiaoguo
 */
@Slf4j
@Service
public class  TestTrainPriceRepositoryImpl extends BaseController<TestTrainPricePageQueryParam, TestTrainPriceEntity>  implements TestTrainPriceRepository{

    //  平台报名考试练车单价表 服务
    @Autowired
    private TestTrainPriceService testTrainPriceService;
    //  平台报名考试练车单价表 DO-DTO转化
    @Autowired
    private TestTrainPriceMapStruct testTrainPriceMapStruct;

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 平台报名考试练车单价表 分页列表
     * @date 2020/2/12 17:09
     * @param  * @param TestTrainPricePageQueryParam
     * @return
     */
    @Override
    public ResObject pageList(TestTrainPricePageQueryParam param) {
        log.info(this.getClass() + "pageList-方法请求参数{}",param);
        Page<TestTrainPriceEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        // 条件查询
        QueryWrapper queryWrapper = this.getQueryWrapper(testTrainPriceMapStruct, param);

        //  模糊查询
        //queryWrapper.like(StrUtil.isNotEmpty(param.getVagueNameSearch()),"name",param.getVagueNameSearch());
        //  开始时间 结束时间都有才进入
        if (StrUtil.isNotEmpty(param.getBeginTime()) && StrUtil.isNotEmpty(param.getEndTime())){
            queryWrapper.between(StrUtil.isNotEmpty(param.getBeginTime()),"create_time",param.getBeginTime(),param.getEndTime());
        }
        IPage<TestTrainPriceEntity> pageList = testTrainPriceService.page(page, queryWrapper);
        if (pageList.getRecords().size() <= 0){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }
        Page<TestTrainPriceVo> testTrainPriceVoPage = testTrainPriceMapStruct.toVoList(pageList);
        log.info(this.getClass() + "pageList-方法请求结果{}",testTrainPriceVoPage);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),testTrainPriceVoPage);
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 平台报名考试练车单价表 查询列表
     * @date 2020/2/12 17:09
     * @param  * @param TestTrainPricePageQueryParam
     * @return
     */
    @Override
    public ResObject findList(TestTrainPricePageQueryParam param) {
        log.info(this.getClass() + "findList-方法请求参数{}",param);
        // 这里判断条件进行查询
        QueryWrapper queryWrapper= this.getQueryWrapper(testTrainPriceMapStruct, param);
        // 如 queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"phone",param.getPhone());
        List<TestTrainPriceEntity> testTrainPriceList = testTrainPriceService.list(queryWrapper);
        if (testTrainPriceList == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),testTrainPriceList);
        }
        List<TestTrainPriceVo> testTrainPriceVoList = testTrainPriceMapStruct.toVoList(testTrainPriceList);
        log.info(this.getClass() + "findList-方法请求结果{}",testTrainPriceVoList);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),testTrainPriceVoList);
    }

    /**
    * 对象条件查询返回单条平台报名考试练车单价表数据
    * @param param
    * @return
    */
    @Override
    public ResObject getInfo(TestTrainPricePageQueryParam param) {
        log.info(this.getClass() + "getInfo-方法请求参数{}",param);
        if (param == null){
            return R.failure("数据空");
        }
        // 这里判断条件进行查询
        QueryWrapper queryWrapper = this.getQueryWrapper(testTrainPriceMapStruct, param);
        TestTrainPriceEntity testTrainPrice = testTrainPriceService.getOne(queryWrapper);
        if (testTrainPrice == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),testTrainPrice);
        }
        queryWrapper.last("limit 1");
        TestTrainPriceVo testTrainPriceVo = BeanConvertUtils.copy(testTrainPrice, TestTrainPriceVo.class);
        log.info(this.getClass() + "getInfo-方法请求结果{}",testTrainPriceVo);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),testTrainPriceVo);
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 通过ID获取 平台报名考试练车单价表 单条数据
     * @date 2020/2/12 17:09
     * @param  * @param TestTrainPricePageQueryParam
     * @return
     */
    @Override
    public ResObject getById(String id) {
        log.info(this.getClass() + "getById-方法请求参数{}",id);
        if (StrUtil.isEmpty(id)){
            return R.failure("数据空");
        }
        // 通过ID 获取 单条数据
        TestTrainPriceEntity testTrainPrice = testTrainPriceService.getById(id);
        if (testTrainPrice == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),testTrainPrice);
        }
        TestTrainPriceVo testTrainPriceVo = BeanConvertUtils.copy(testTrainPrice, TestTrainPriceVo.class);
        log.info(this.getClass() + "getById-方法请求结果{}",testTrainPriceVo);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),testTrainPriceVo);
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 保存平台报名考试练车单价表 数据
     * @date 2020/2/12 17:09
     * @param  * @param TestTrainPricePageQueryParam
     * @return
     */
    @Override
    public ResObject save(TestTrainPriceInstallParam installParam) {
        log.info(this.getClass() + "save方法请求参数{}",installParam);
        if (installParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        // 幂等性查询
        QueryWrapper queryWrapper = new QueryWrapper();
        // 驾照类型
        queryWrapper.eq(StrUtil.isNotEmpty(installParam.getDriveType()),"drive_type",installParam.getDriveType());
        // 科目类型
        queryWrapper.eq(StrUtil.isNotEmpty(installParam.getSubjectType()),"subject_type",installParam.getSubjectType());
        // 价格类型
        queryWrapper.eq(StrUtil.isNotEmpty(installParam.getPriceType()),"price_type",installParam.getPriceType());
        queryWrapper.eq(StrUtil.isNotEmpty(installParam.getOperatorId()),"operator_id",installParam.getOperatorId());
        queryWrapper.last("limit 1");
        TestTrainPriceEntity isTestTrainPrice = testTrainPriceService.getOne(queryWrapper);
        if (isTestTrainPrice != null){
            return R.success(SubResultCode.DATA_IDEMPOTENT.subCode(),SubResultCode.DATA_IDEMPOTENT.subMsg());
        }
        TestTrainPriceEntity testTrainPrice = BeanConvertUtils.copy(installParam, TestTrainPriceEntity.class);
        Boolean result = testTrainPriceService.save(testTrainPrice);
        log.info(this.getClass() + "save-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_INSTALL_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_INSTALL_FAILL.subCode(),SubResultCode.DATA_INSTALL_FAILL.subMsg());
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description  修改平台报名考试练车单价表 数据
     * @date 2020/2/12 17:09
     * @param  * @param TestTrainPricePageQueryParam
     * @return
     */
    @Override
    public ResObject update(TestTrainPriceEditParam updateParam) {
        log.info(this.getClass() + "update方法请求参数{}",updateParam);
        if (updateParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }

        // 幂等性查询
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.setEntity(updateParam);
        // 驾照类型
        queryWrapper.last("limit 1");
        int isTestTrainPriceCount = testTrainPriceService.count(queryWrapper);
        if (isTestTrainPriceCount  > 0){
            return R.success(SubResultCode.DATA_IDEMPOTENT.subCode(),SubResultCode.DATA_IDEMPOTENT.subMsg());
        }
        TestTrainPriceEntity testTrainPrice = BeanConvertUtils.copy(updateParam, TestTrainPriceEntity.class);
        Boolean result = testTrainPriceService.updateById(testTrainPrice);
        log.info(this.getClass() + "update-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_UPDATE_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_UPDATE_FAILL.subCode(),SubResultCode.DATA_UPDATE_FAILL.subMsg());
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 数组删除平台报名考试练车单价表 数据
     * @date 2020/2/12 17:09
     * @param  * @param TestTrainPricePageQueryParam
     * @return
     */
    @Override
    public ResObject deleteByIds(String[] ids) {
        if (ids.length <= 0){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        return R.toRes(testTrainPriceService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * *通过id删除平台报名考试练车单价表 信息
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
        Boolean result = testTrainPriceService.removeById(id);
        log.info(this.getClass() + "deleteById-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_DELETE_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_DELETE_FAILL.subCode(),SubResultCode.DATA_DELETE_FAILL.subMsg());
    }

    /**
     * *导出平台报名考试练车单价表 信息
     **/
    @Override
    public ResObject exportXls(TestTrainPricePageQueryParam param, HttpServletResponse response)throws IOException {
        log.info(this.getClass() + "exportXls方法请求参数{}",param);
        QueryWrapper queryWrapper = this.getQueryWrapper(testTrainPriceMapStruct, param);
        List<TestTrainPriceEntity> list = testTrainPriceService.list(queryWrapper);
        List<TestTrainPriceVo>testTrainPriceList = testTrainPriceMapStruct.toVoList(list);
        ExcelUtils.exportExcel(testTrainPriceList, TestTrainPriceVo.class, "", new ExportParams(), response);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.EXPORT_DATA_SUCCESS.subMsg());
    }

    /**
     * *修改状态
     **/
    @Override
    public ResObject changeStatus(TestTrainPriceEditParam param) {
        log.info(this.getClass() + "changeStatus方法请求参数{}",param);
        if (StrUtil.isEmpty(param.getId())){
            log.error("ID数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        TestTrainPriceEntity TestTrainPriceEntity = new TestTrainPriceEntity();
        TestTrainPriceEntity.setId(param.getId());
        //TestTrainPriceEntity.setStatus(param.getStatus());
        //TestTrainPriceEntity.setUpdateTime()
        Boolean result = testTrainPriceService.updateById(TestTrainPriceEntity);
        log.info(this.getClass() +"changeStatus方法请求对象参数{}，请求结果{}",TestTrainPriceEntity,result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_STATUS_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_STATUS_FAILL.subCode(),SubResultCode.DATA_STATUS_FAILL.subMsg());
    }

    @Override
    public ResObject getOperatorDeduct(String operatorId) {
        log.info("getOperatorDeduct-方法请求参数{}",operatorId);
        if (StrUtil.isEmpty(operatorId)){
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        JSONObject jsonObject = new JSONObject();
        // 5-推广商推荐报名提成金额,6-推广商课时提成百分比
        // 推广商课时提成百分比
        QueryWrapper classDeductAmountQueryWrapper = new QueryWrapper();
        classDeductAmountQueryWrapper.eq("price_type", PriceTypeEnum.CLASS_DEDUCT_AMOUNT.getCode());
        classDeductAmountQueryWrapper.eq("operator_id",operatorId);
        TestTrainPriceEntity classDeductDo= testTrainPriceService.getOne(classDeductAmountQueryWrapper);
        if (classDeductDo != null) {
            BigDecimal classDeductAmount = classDeductDo.getPrice();
            // 设置课时金额
            jsonObject.put("classDeductAmount",classDeductAmount);
        }
        // BigDecimal classDeductAmount = Optional.ofNullable(testTrainPriceService.getOne(classDeductAmountQueryWrapper)).map(u -> u.getPrice()).get();
    // Object object2 = optional2.orElseThrow(() -> {
        //                    System.out.println("执行逻辑，然后抛出异常");
        //                    return new RuntimeException("抛出异常");
        //                }
        QueryWrapper classDeductPercentQueryWrapper = new QueryWrapper();
        classDeductPercentQueryWrapper.eq("price_type", PriceTypeEnum.CLASS_DEDUCT_PERCENT.getCode());
        classDeductPercentQueryWrapper.eq("operator_id",operatorId);
        //BigDecimal classDeductPercent = Optional.ofNullable(testTrainPriceService.getOne(classDeductPercentQueryWrapper)).map(u -> u.getPrice()).get();
        TestTrainPriceEntity classDeductPercentDo= testTrainPriceService.getOne(classDeductPercentQueryWrapper);
        if (classDeductPercentDo != null) {
            BigDecimal classDeductPercent = classDeductPercentDo.getPrice();
            // 设置课时金额
            jsonObject.put("classDeductPercent",classDeductPercent);
        }
        return R.success(jsonObject);
    }
}

