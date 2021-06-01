package com.drive.admin.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.drive.admin.pojo.entity.QuestionOptionsEntity;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.drive.admin.service.QuestionBankService;
import com.drive.common.core.base.BaseController;
import com.drive.admin.repository.QuestionOptionsRepository;
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
import com.drive.admin.service.QuestionOptionsService;
import com.drive.common.data.utils.ExcelUtils;
import java.util.List;
import java.util.Arrays;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

                                        
/**
 *
 * 平台题目选项 服务类
 *
 * @author chentao
 */
@Slf4j
@Service
public class  QuestionOptionsRepositoryImpl extends BaseController<QuestionOptionsPageQueryParam, QuestionOptionsEntity>  implements QuestionOptionsRepository{

    //  平台题目选项 服务
    @Autowired
    private QuestionOptionsService questionOptionsService;
    //  平台题目选项 DO-DTO转化
    @Autowired
    private QuestionOptionsMapStruct questionOptionsMapStruct;
    @Autowired
    private QuestionBankService questionBankService;

    /*
     *
     *功能描述
     * @author chentao
     * @description 平台题目选项 分页列表
     * @date 2020/2/12 17:09
     * @param  * @param QuestionOptionsPageQueryParam
     * @return
     */
    @Override
    public ResObject pageList(QuestionOptionsPageQueryParam param) {
        log.info(this.getClass() + "pageList-方法请求参数{}",param);
        Page<QuestionOptionsEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        // 条件查询
        QueryWrapper queryWrapper = this.getQueryWrapper(questionOptionsMapStruct, param);

        //  模糊查询
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueNameSearch()),"name",param.getVagueNameSearch());
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueQuestionBankIdSearch()),"question_bank_id",param.getVagueQuestionBankIdSearch());
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueOptionContentSearch()),"option_content",param.getVagueOptionContentSearch());
        //  开始时间 结束时间都有才进入
        if (StrUtil.isNotEmpty(param.getBeginTime()) && StrUtil.isNotEmpty(param.getEndTime())){
            queryWrapper.between(StrUtil.isNotEmpty(param.getBeginTime()),"create_time",param.getBeginTime(),param.getEndTime());
        }
        IPage<QuestionOptionsEntity> pageList = questionOptionsService.page(page, queryWrapper);
        if (pageList.getRecords().size() <= 0){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }

        Page<QuestionOptionsVo> questionOptionsVoPage = questionOptionsMapStruct.toVoList(pageList);
        List<QuestionOptionsVo> records = questionOptionsVoPage.getRecords();
        for (QuestionOptionsVo record : records) {
            QuestionBankEntity questionBankEntity = questionBankService.getById(record.getQuestionBankId());
            record.setQuestionNo(questionBankEntity.getQuestionNo().toString());
        }
        log.info(this.getClass() + "pageList-方法请求结果{}",questionOptionsVoPage);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),questionOptionsVoPage);
    }

    /*
     *
     *功能描述
     * @author chentao
     * @description 平台题目选项 查询列表
     * @date 2020/2/12 17:09
     * @param  * @param QuestionOptionsPageQueryParam
     * @return
     */
    @Override
    public ResObject findList(QuestionOptionsPageQueryParam param) {
        log.info(this.getClass() + "findList-方法请求参数{}",param);
        // 这里判断条件进行查询
        QueryWrapper queryWrapper= this.getQueryWrapper(questionOptionsMapStruct, param);
        // 通过名称查询
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueNameSearch()),"name",param.getVagueNameSearch());
        // 如 queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"phone",param.getPhone());
        List<QuestionOptionsEntity> questionOptionsList = questionOptionsService.list(queryWrapper);
        if (questionOptionsList == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),questionOptionsList);
        }
        List<QuestionOptionsVo> questionOptionsVoList = questionOptionsMapStruct.toVoList(questionOptionsList);
        log.info(this.getClass() + "findList-方法请求结果{}",questionOptionsVoList);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),questionOptionsVoList);
    }

    /**
    * 对象条件查询返回单条平台题目选项数据
    * @param param
    * @return
    */
    @Override
    public ResObject getInfo(QuestionOptionsPageQueryParam param) {
        log.info(this.getClass() + "getInfo-方法请求参数{}",param);
        if (param == null){
            return R.failure("数据空");
        }
        // 这里判断条件进行查询
        QueryWrapper queryWrapper = this.getQueryWrapper(questionOptionsMapStruct, param);
        // 这里有且只返回一条数据
        queryWrapper.last("limit 1");
        QuestionOptionsEntity questionOptions = questionOptionsService.getOne(queryWrapper);
        if (questionOptions == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),questionOptions);
        }
        QuestionOptionsVo questionOptionsVo = BeanConvertUtils.copy(questionOptions, QuestionOptionsVo.class);
        log.info(this.getClass() + "getInfo-方法请求结果{}",questionOptionsVo);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),questionOptionsVo);
    }

    /*
     *
     *功能描述
     * @author chentao
     * @description 通过ID获取 平台题目选项 单条数据
     * @date 2020/2/12 17:09
     * @param  * @param QuestionOptionsPageQueryParam
     * @return
     */
    @Override
    public ResObject getById(String id) {
        log.info(this.getClass() + "getById-方法请求参数{}",id);
        if (StrUtil.isEmpty(id)){
            return R.failure("数据空");
        }
        // 通过ID 获取 单条数据
        QuestionOptionsEntity questionOptions = questionOptionsService.getById(id);
        if (questionOptions == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),questionOptions);
        }
        QuestionOptionsVo questionOptionsVo = BeanConvertUtils.copy(questionOptions, QuestionOptionsVo.class);
        QuestionBankEntity questionBankEntity = questionBankService.getById(questionOptionsVo.getQuestionBankId());
        questionOptionsVo.setQuestionNo(questionBankEntity.getQuestionNo().toString());
        log.info(this.getClass() + "getById-方法请求结果{}",questionOptionsVo);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),questionOptionsVo);
    }

    /*
     *
     *功能描述
     * @author chentao
     * @description 保存平台题目选项 数据
     * @date 2020/2/12 17:09
     * @param  * @param QuestionOptionsPageQueryParam
     * @return
     */
    @Override
    public ResObject save(QuestionOptionsInstallParam installParam) {
        log.info(this.getClass() + "save方法请求参数{}",installParam);
        if (installParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        QuestionOptionsEntity questionOptions = BeanConvertUtils.copy(installParam, QuestionOptionsEntity.class);

        Boolean result = questionOptionsService.save(questionOptions);
        log.info(this.getClass() + "save-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_INSTALL_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_INSTALL_FAILL.subCode(),SubResultCode.DATA_INSTALL_FAILL.subMsg());
    }

    /*
     *
     *功能描述
     * @author chentao
     * @description  修改平台题目选项 数据
     * @date 2020/2/12 17:09
     * @param  * @param QuestionOptionsPageQueryParam
     * @return
     */
    @Override
    public ResObject update(QuestionOptionsEditParam updateParam) {
        log.info(this.getClass() + "update方法请求参数{}",updateParam);
        if (updateParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        QuestionOptionsEntity questionOptions = BeanConvertUtils.copy(updateParam, QuestionOptionsEntity.class);
        Boolean result = questionOptionsService.updateById(questionOptions);
        log.info(this.getClass() + "update-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_UPDATE_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_UPDATE_FAILL.subCode(),SubResultCode.DATA_UPDATE_FAILL.subMsg());
    }

    /*
     *
     *功能描述
     * @author chentao
     * @description 数组删除平台题目选项 数据
     * @date 2020/2/12 17:09
     * @param  * @param QuestionOptionsPageQueryParam
     * @return
     */
    @Override
    public ResObject deleteByIds(String[] ids) {
        if (ids.length <= 0){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        return R.toRes(questionOptionsService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * *通过id删除平台题目选项 信息
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
        Boolean result = questionOptionsService.removeById(id);
        log.info(this.getClass() + "deleteById-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_DELETE_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_DELETE_FAILL.subCode(),SubResultCode.DATA_DELETE_FAILL.subMsg());
    }

    /**
     * *导出平台题目选项 信息
     **/
    @Override
    public ResObject exportXls(QuestionOptionsPageQueryParam param, HttpServletResponse response)throws IOException {
        log.info(this.getClass() + "exportXls方法请求参数{}",param);
        QueryWrapper queryWrapper = this.getQueryWrapper(questionOptionsMapStruct, param);
        List<QuestionOptionsEntity> list = questionOptionsService.list(queryWrapper);
        List<QuestionOptionsVo>questionOptionsList = questionOptionsMapStruct.toVoList(list);
        ExcelUtils.exportExcel(questionOptionsList, QuestionOptionsVo.class, "", new ExportParams(), response);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.EXPORT_DATA_SUCCESS.subMsg());
    }

    /**
     * *修改状态
     **/
    @Override
    public ResObject changeStatus(QuestionOptionsEditParam param) {
        log.info(this.getClass() + "changeStatus方法请求参数{}",param);
        if (StrUtil.isEmpty(param.getId())){
            log.error("ID数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        QuestionOptionsEntity QuestionOptionsEntity = new QuestionOptionsEntity();
        QuestionOptionsEntity.setId(param.getId());
        //QuestionOptionsEntity.setStatus(param.getStatus());
        //QuestionOptionsEntity.setUpdateTime()
        Boolean result = questionOptionsService.updateById(QuestionOptionsEntity);
        log.info(this.getClass() +"changeStatus方法请求对象参数{}，请求结果{}",QuestionOptionsEntity,result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_STATUS_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_STATUS_FAILL.subCode(),SubResultCode.DATA_STATUS_FAILL.subMsg());
    }

}

