package com.drive.admin.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.drive.admin.pojo.entity.QuestionBankEntity;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.drive.common.core.base.BaseController;
import com.drive.admin.repository.QuestionBankRepository;
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
import com.drive.admin.service.QuestionBankService;
import com.drive.common.data.utils.ExcelUtils;
import java.util.List;
import java.util.Arrays;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

                                                                    
/**
 *
 * 平台题库表 服务类
 *
 * @author chentao
 */
@Slf4j
@Service
public class  QuestionBankRepositoryImpl extends BaseController<QuestionBankPageQueryParam, QuestionBankEntity>  implements QuestionBankRepository{

    //  平台题库表 服务
    @Autowired
    private QuestionBankService questionBankService;
    //  平台题库表 DO-DTO转化
    @Autowired
    private QuestionBankMapStruct questionBankMapStruct;

    /*
     *
     *功能描述
     * @author chentao
     * @description 平台题库表 分页列表
     * @date 2020/2/12 17:09
     * @param  * @param QuestionBankPageQueryParam
     * @return
     */
    @Override
    public ResObject pageList(QuestionBankPageQueryParam param) {
        log.info(this.getClass() + "pageList-方法请求参数{}",param);
        Page<QuestionBankEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        // 条件查询
        QueryWrapper queryWrapper = this.getQueryWrapper(questionBankMapStruct, param);

        //  模糊查询
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueNameSearch()),"name",param.getVagueNameSearch());
        //  开始时间 结束时间都有才进入
        if (StrUtil.isNotEmpty(param.getBeginTime()) && StrUtil.isNotEmpty(param.getEndTime())){
            queryWrapper.between(StrUtil.isNotEmpty(param.getBeginTime()),"create_time",param.getBeginTime(),param.getEndTime());
        }
        IPage<QuestionBankEntity> pageList = questionBankService.page(page, queryWrapper);
        if (pageList.getRecords().size() <= 0){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }
        Page<QuestionBankVo> questionBankVoPage = questionBankMapStruct.toVoList(pageList);
        log.info(this.getClass() + "pageList-方法请求结果{}",questionBankVoPage);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),questionBankVoPage);
    }

    /*
     *
     *功能描述
     * @author chentao
     * @description 平台题库表 查询列表
     * @date 2020/2/12 17:09
     * @param  * @param QuestionBankPageQueryParam
     * @return
     */
    @Override
    public ResObject findList(QuestionBankPageQueryParam param) {
        log.info(this.getClass() + "findList-方法请求参数{}",param);
        // 这里判断条件进行查询
        QueryWrapper queryWrapper= this.getQueryWrapper(questionBankMapStruct, param);
        // 通过名称查询
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueNameSearch()),"name",param.getVagueNameSearch());
        // 如 queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"phone",param.getPhone());
        List<QuestionBankEntity> questionBankList = questionBankService.list(queryWrapper);
        if (questionBankList == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),questionBankList);
        }
        List<QuestionBankVo> questionBankVoList = questionBankMapStruct.toVoList(questionBankList);
        log.info(this.getClass() + "findList-方法请求结果{}",questionBankVoList);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),questionBankVoList);
    }

    /**
    * 对象条件查询返回单条平台题库表数据
    * @param param
    * @return
    */
    @Override
    public ResObject getInfo(QuestionBankPageQueryParam param) {
        log.info(this.getClass() + "getInfo-方法请求参数{}",param);
        if (param == null){
            return R.failure("数据空");
        }
        // 这里判断条件进行查询
        QueryWrapper queryWrapper = this.getQueryWrapper(questionBankMapStruct, param);
        // 这里有且只返回一条数据
        queryWrapper.last("limit 1");
        QuestionBankEntity questionBank = questionBankService.getOne(queryWrapper);
        if (questionBank == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),questionBank);
        }
        QuestionBankVo questionBankVo = BeanConvertUtils.copy(questionBank, QuestionBankVo.class);
        log.info(this.getClass() + "getInfo-方法请求结果{}",questionBankVo);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),questionBankVo);
    }

    /*
     *
     *功能描述
     * @author chentao
     * @description 通过ID获取 平台题库表 单条数据
     * @date 2020/2/12 17:09
     * @param  * @param QuestionBankPageQueryParam
     * @return
     */
    @Override
    public ResObject getById(String id) {
        log.info(this.getClass() + "getById-方法请求参数{}",id);
        if (StrUtil.isEmpty(id)){
            return R.failure("数据空");
        }
        // 通过ID 获取 单条数据
        QuestionBankEntity questionBank = questionBankService.getById(id);
        if (questionBank == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),questionBank);
        }
        QuestionBankVo questionBankVo = BeanConvertUtils.copy(questionBank, QuestionBankVo.class);
        log.info(this.getClass() + "getById-方法请求结果{}",questionBankVo);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),questionBankVo);
    }

    /*
     *
     *功能描述
     * @author chentao
     * @description 保存平台题库表 数据
     * @date 2020/2/12 17:09
     * @param  * @param QuestionBankPageQueryParam
     * @return
     */
    @Override
    public ResObject save(QuestionBankInstallParam installParam) {
        log.info(this.getClass() + "save方法请求参数{}",installParam);
        if (installParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        QuestionBankEntity questionBank = BeanConvertUtils.copy(installParam, QuestionBankEntity.class);
        Boolean result = questionBankService.save(questionBank);
        log.info(this.getClass() + "save-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_INSTALL_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_INSTALL_FAILL.subCode(),SubResultCode.DATA_INSTALL_FAILL.subMsg());
    }

    /*
     *
     *功能描述
     * @author chentao
     * @description  修改平台题库表 数据
     * @date 2020/2/12 17:09
     * @param  * @param QuestionBankPageQueryParam
     * @return
     */
    @Override
    public ResObject update(QuestionBankEditParam updateParam) {
        log.info(this.getClass() + "update方法请求参数{}",updateParam);
        if (updateParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        QuestionBankEntity questionBank = BeanConvertUtils.copy(updateParam, QuestionBankEntity.class);
        Boolean result = questionBankService.updateById(questionBank);
        log.info(this.getClass() + "update-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_UPDATE_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_UPDATE_FAILL.subCode(),SubResultCode.DATA_UPDATE_FAILL.subMsg());
    }

    /*
     *
     *功能描述
     * @author chentao
     * @description 数组删除平台题库表 数据
     * @date 2020/2/12 17:09
     * @param  * @param QuestionBankPageQueryParam
     * @return
     */
    @Override
    public ResObject deleteByIds(String[] ids) {
        if (ids.length <= 0){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        return R.toRes(questionBankService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * *通过id删除平台题库表 信息
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
        Boolean result = questionBankService.removeById(id);
        log.info(this.getClass() + "deleteById-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_DELETE_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_DELETE_FAILL.subCode(),SubResultCode.DATA_DELETE_FAILL.subMsg());
    }

    /**
     * *导出平台题库表 信息
     **/
    @Override
    public ResObject exportXls(QuestionBankPageQueryParam param, HttpServletResponse response)throws IOException {
        log.info(this.getClass() + "exportXls方法请求参数{}",param);
        QueryWrapper queryWrapper = this.getQueryWrapper(questionBankMapStruct, param);
        List<QuestionBankEntity> list = questionBankService.list(queryWrapper);
        List<QuestionBankVo>questionBankList = questionBankMapStruct.toVoList(list);
        ExcelUtils.exportExcel(questionBankList, QuestionBankVo.class, "", new ExportParams(), response);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.EXPORT_DATA_SUCCESS.subMsg());
    }

    /**
     * *修改状态
     **/
    @Override
    public ResObject changeStatus(QuestionBankEditParam param) {
        log.info(this.getClass() + "changeStatus方法请求参数{}",param);
        if (StrUtil.isEmpty(param.getId())){
            log.error("ID数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        QuestionBankEntity QuestionBankEntity = new QuestionBankEntity();
        QuestionBankEntity.setId(param.getId());
        //QuestionBankEntity.setUpdateTime()
        Boolean result = questionBankService.updateById(QuestionBankEntity);
        log.info(this.getClass() +"changeStatus方法请求对象参数{}，请求结果{}",QuestionBankEntity,result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_STATUS_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_STATUS_FAILL.subCode(),SubResultCode.DATA_STATUS_FAILL.subMsg());
    }

}

