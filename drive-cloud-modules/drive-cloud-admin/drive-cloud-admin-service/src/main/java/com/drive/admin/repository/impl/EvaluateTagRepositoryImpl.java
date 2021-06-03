package com.drive.admin.repository.impl;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.admin.enums.StatusEnum;
import com.drive.admin.pojo.dto.EvaluateTagEditParam;
import com.drive.admin.pojo.dto.EvaluateTagInstallParam;
import com.drive.admin.pojo.dto.EvaluateTagPageQueryParam;
import com.drive.admin.pojo.entity.EvaluateTagEntity;
import com.drive.admin.pojo.vo.EvaluateTagVo;
import com.drive.admin.repository.EvaluateTagRepository;
import com.drive.admin.service.EvaluateTagService;
import com.drive.admin.service.mapstruct.EvaluateTagMapStruct;
import com.drive.common.core.base.BaseController;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.biz.SubResultCode;
import com.drive.common.core.utils.BeanConvertUtils;
import com.drive.common.data.utils.ExcelUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

                                    
/**
 *
 * 评价标签表 服务类
 *
 * @author xiaoguo
 */
@Slf4j
@Service
public class  EvaluateTagRepositoryImpl extends BaseController<EvaluateTagPageQueryParam, EvaluateTagEntity>  implements EvaluateTagRepository{

    //  评价标签表 服务
    @Autowired
    private EvaluateTagService evaluateTagService;
    //  评价标签表 DO-DTO转化
    @Autowired
    private EvaluateTagMapStruct evaluateTagMapStruct;

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 评价标签表 分页列表
     * @date 2020/2/12 17:09
     * @param  * @param EvaluateTagPageQueryParam
     * @return
     */
    @Override
    public ResObject pageList(EvaluateTagPageQueryParam param) {
        log.info(this.getClass() + "pageList-方法请求参数{}",param);
        Page<EvaluateTagEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        // 条件查询
        QueryWrapper queryWrapper = this.getQueryWrapper(evaluateTagMapStruct, param);

        //  模糊查询
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueNameSearch()),"name",param.getVagueNameSearch());
        //  开始时间 结束时间都有才进入
        if (StrUtil.isNotEmpty(param.getBeginTime()) && StrUtil.isNotEmpty(param.getEndTime())){
            queryWrapper.between(StrUtil.isNotEmpty(param.getBeginTime()),"create_time",param.getBeginTime(),param.getEndTime());
        }
        IPage<EvaluateTagEntity> pageList = evaluateTagService.page(page, queryWrapper);
        if (pageList.getRecords().isEmpty()){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }
        Page<EvaluateTagVo> evaluateTagVoPage = evaluateTagMapStruct.toVoList(pageList);
        log.info(this.getClass() + "pageList-方法请求结果{}",evaluateTagVoPage);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),evaluateTagVoPage);
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 评价标签表 查询列表
     * @date 2020/2/12 17:09
     * @param  * @param EvaluateTagPageQueryParam
     * @return
     */
    @Override
    public ResObject findList(EvaluateTagPageQueryParam param) {
        log.info(this.getClass() + "findList-方法请求参数{}",param);
        // 这里判断条件进行查询
        QueryWrapper queryWrapper= this.getQueryWrapper(evaluateTagMapStruct, param);
        // 如 queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"phone",param.getPhone());
        List<EvaluateTagEntity> evaluateTagList = evaluateTagService.list(queryWrapper);
        if (evaluateTagList == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),evaluateTagList);
        }
        List<EvaluateTagVo> evaluateTagVoList = evaluateTagMapStruct.toVoList(evaluateTagList);
        log.info(this.getClass() + "findList-方法请求结果{}",evaluateTagVoList);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),evaluateTagVoList);
    }

    /**
    * 对象条件查询返回单条评价标签表数据
    * @param param
    * @return
    */
    @Override
    public ResObject getInfo(EvaluateTagPageQueryParam param) {
        log.info(this.getClass() + "getInfo-方法请求参数{}",param);
        if (param == null){
            return R.failure("数据空");
        }
        // 这里判断条件进行查询
        QueryWrapper queryWrapper = this.getQueryWrapper(evaluateTagMapStruct, param);
        EvaluateTagEntity evaluateTag = evaluateTagService.getOne(queryWrapper);
        if (evaluateTag == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),evaluateTag);
        }
        EvaluateTagVo evaluateTagVo = BeanConvertUtils.copy(evaluateTag, EvaluateTagVo.class);
        log.info(this.getClass() + "getInfo-方法请求结果{}",evaluateTagVo);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),evaluateTagVo);
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 通过ID获取 评价标签表 单条数据
     * @date 2020/2/12 17:09
     * @param  * @param EvaluateTagPageQueryParam
     * @return
     */
    @Override
    public ResObject getById(String id) {
        log.info(this.getClass() + "getById-方法请求参数{}",id);
        if (StrUtil.isEmpty(id)){
            return R.failure("数据空");
        }
        // 通过ID 获取 单条数据
        EvaluateTagEntity evaluateTag = evaluateTagService.getById(id);
        if (evaluateTag == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),evaluateTag);
        }
        EvaluateTagVo evaluateTagVo = BeanConvertUtils.copy(evaluateTag, EvaluateTagVo.class);
        log.info(this.getClass() + "getById-方法请求结果{}",evaluateTagVo);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),evaluateTagVo);
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 保存评价标签表 数据
     * @date 2020/2/12 17:09
     * @param  * @param EvaluateTagPageQueryParam
     * @return
     */
    @Override
    public ResObject save(EvaluateTagInstallParam installParam) {
        log.info(this.getClass() + "save方法请求参数{}",installParam);
        if (installParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        // 数据幂等性校验
        QueryWrapper queryWrapper = new QueryWrapper();
        // 名称
        queryWrapper.eq(StrUtil.isNotEmpty(installParam.getName()),"name",installParam.getName());
        // 订单类型
        queryWrapper.eq(StrUtil.isNotEmpty(installParam.getOrderType()),"order_type",installParam.getOrderType());
        // 好评
        queryWrapper.eq(StrUtil.isNotEmpty(installParam.getFractionParagraph()),"fraction_paragraph",installParam.getFractionParagraph());
        // 状态正常的
        queryWrapper.eq("status", StatusEnum.ENABLE.getCode());
        int count = evaluateTagService.count(queryWrapper);
        if (count > 0){
            // 数据重复
            log.error("数据重复");
            return R.success(SubResultCode.DATA_IDEMPOTENT.subCode(),SubResultCode.DATA_IDEMPOTENT.subMsg());
        }
        EvaluateTagEntity evaluateTag = BeanConvertUtils.copy(installParam, EvaluateTagEntity.class);
        Boolean result = evaluateTagService.save(evaluateTag);
        log.info(this.getClass() + "save-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_INSTALL_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_INSTALL_FAILL.subCode(),SubResultCode.DATA_INSTALL_FAILL.subMsg());
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description  修改评价标签表 数据
     * @date 2020/2/12 17:09
     * @param  * @param EvaluateTagPageQueryParam
     * @return
     */
    @Override
    public ResObject update(EvaluateTagEditParam updateParam) {
        log.info(this.getClass() + "update方法请求参数{}",updateParam);
        if (updateParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        EvaluateTagEntity evaluateTag = BeanConvertUtils.copy(updateParam, EvaluateTagEntity.class);
        Boolean result = evaluateTagService.updateById(evaluateTag);
        log.info(this.getClass() + "update-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_UPDATE_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_UPDATE_FAILL.subCode(),SubResultCode.DATA_UPDATE_FAILL.subMsg());
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 数组删除评价标签表 数据
     * @date 2020/2/12 17:09
     * @param  * @param EvaluateTagPageQueryParam
     * @return
     */
    @Override
    public ResObject deleteByIds(String[] ids) {
        if (ids.length <= 0){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        return R.toRes(evaluateTagService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * *通过id删除评价标签表 信息
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
        Boolean result = evaluateTagService.removeById(id);
        log.info(this.getClass() + "deleteById-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_DELETE_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_DELETE_FAILL.subCode(),SubResultCode.DATA_DELETE_FAILL.subMsg());
    }

    /**
     * *导出评价标签表 信息
     **/
    @Override
    public ResObject exportXls(EvaluateTagPageQueryParam param, HttpServletResponse response)throws IOException {
        log.info(this.getClass() + "exportXls方法请求参数{}",param);
        QueryWrapper queryWrapper = this.getQueryWrapper(evaluateTagMapStruct, param);
        List<EvaluateTagEntity> list = evaluateTagService.list(queryWrapper);
        List<EvaluateTagVo>evaluateTagList = evaluateTagMapStruct.toVoList(list);
        ExcelUtils.exportExcel(evaluateTagList, EvaluateTagVo.class, "", new ExportParams(), response);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.EXPORT_DATA_SUCCESS.subMsg());
    }

    /**
     * *修改状态
     **/
    @Override
    public ResObject changeStatus(EvaluateTagEditParam param) {
        log.info(this.getClass() + "changeStatus方法请求参数{}",param);
        if (StrUtil.isEmpty(param.getId())){
            log.error("ID数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        EvaluateTagEntity EvaluateTagEntity = new EvaluateTagEntity();
        EvaluateTagEntity.setId(param.getId());
        EvaluateTagEntity.setStatus(param.getStatus());
        //EvaluateTagEntity.setUpdateTime()
        Boolean result = evaluateTagService.updateById(EvaluateTagEntity);
        log.info(this.getClass() +"changeStatus方法请求对象参数{}，请求结果{}",EvaluateTagEntity,result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_STATUS_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_STATUS_FAILL.subCode(),SubResultCode.DATA_STATUS_FAILL.subMsg());
    }

}

