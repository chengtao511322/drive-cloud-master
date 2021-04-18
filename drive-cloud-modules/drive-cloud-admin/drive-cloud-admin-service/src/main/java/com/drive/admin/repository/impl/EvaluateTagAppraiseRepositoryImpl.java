package com.drive.admin.repository.impl;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.admin.pojo.dto.EvaluateTagAppraiseEditParam;
import com.drive.admin.pojo.dto.EvaluateTagAppraiseInstallParam;
import com.drive.admin.pojo.dto.EvaluateTagAppraisePageQueryParam;
import com.drive.admin.pojo.entity.EvaluateTagAppraiseEntity;
import com.drive.admin.pojo.entity.EvaluateTagEntity;
import com.drive.admin.pojo.vo.EvaluateTagAppraiseVo;
import com.drive.admin.repository.EvaluateTagAppraiseRepository;
import com.drive.admin.service.EvaluateTagAppraiseService;
import com.drive.admin.service.EvaluateTagService;
import com.drive.admin.service.mapstruct.EvaluateTagAppraiseMapStruct;
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
 * 学员教练评价明细表 服务类
 *
 * @author xiaoguo
 */
@Slf4j
@Service
public class  EvaluateTagAppraiseRepositoryImpl extends BaseController<EvaluateTagAppraisePageQueryParam, EvaluateTagAppraiseEntity>  implements EvaluateTagAppraiseRepository{

    //  学员教练评价明细表 服务
    @Autowired
    private EvaluateTagAppraiseService evaluateTagAppraiseService;
    //  学员教练评价明细表 DO-DTO转化
    @Autowired
    private EvaluateTagAppraiseMapStruct evaluateTagAppraiseMapStruct;

    // 评价标签
    @Autowired
    private EvaluateTagService evaluateTagService;

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 学员教练评价明细表 分页列表
     * @date 2020/2/12 17:09
     * @param  * @param EvaluateTagAppraisePageQueryParam
     * @return
     */
    @Override
    public ResObject pageList(EvaluateTagAppraisePageQueryParam param) {
        log.info(this.getClass() + "pageList-方法请求参数{}",param);
        Page<EvaluateTagAppraiseEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        // 条件查询
        QueryWrapper queryWrapper = this.getQueryWrapper(evaluateTagAppraiseMapStruct, param);

        //  模糊查询
        //queryWrapper.like(StrUtil.isNotEmpty(param.getVagueNameSearch()),"name",param.getVagueNameSearch());
        //  开始时间 结束时间都有才进入
        if (StrUtil.isNotEmpty(param.getBeginTime()) && StrUtil.isNotEmpty(param.getEndTime())){
            queryWrapper.between(StrUtil.isNotEmpty(param.getBeginTime()),"create_time",param.getBeginTime(),param.getEndTime());
        }
        IPage<EvaluateTagAppraiseEntity> pageList = evaluateTagAppraiseService.page(page, queryWrapper);
        if (pageList.getRecords().size() <= 0){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }
        Page<EvaluateTagAppraiseVo> evaluateTagAppraiseVoPage = evaluateTagAppraiseMapStruct.toVoList(pageList);
        // 循环
        evaluateTagAppraiseVoPage.getRecords().stream().forEach((item) ->{
            if (StrUtil.isNotEmpty(item.getEvaluateTagId())){
                EvaluateTagEntity evaluateTag= evaluateTagService.getById(item.getEvaluateTagId());
                if (evaluateTag != null)item.setEvaluateTagName(evaluateTag.getName());
            }
        });
        log.info(this.getClass() + "pageList-方法请求结果{}",evaluateTagAppraiseVoPage);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),evaluateTagAppraiseVoPage);
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 学员教练评价明细表 查询列表
     * @date 2020/2/12 17:09
     * @param  * @param EvaluateTagAppraisePageQueryParam
     * @return
     */
    @Override
    public ResObject findList(EvaluateTagAppraisePageQueryParam param) {
        log.info(this.getClass() + "findList-方法请求参数{}",param);
        // 这里判断条件进行查询
        QueryWrapper queryWrapper= this.getQueryWrapper(evaluateTagAppraiseMapStruct, param);
        // 如 queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"phone",param.getPhone());
        List<EvaluateTagAppraiseEntity> evaluateTagAppraiseList = evaluateTagAppraiseService.list(queryWrapper);
        if (evaluateTagAppraiseList == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),evaluateTagAppraiseList);
        }
        List<EvaluateTagAppraiseVo> evaluateTagAppraiseVoList = evaluateTagAppraiseMapStruct.toVoList(evaluateTagAppraiseList);
        // 循环
        evaluateTagAppraiseVoList.stream().forEach((item) ->{
            if (StrUtil.isNotEmpty(item.getEvaluateTagId())){
                EvaluateTagEntity evaluateTag= evaluateTagService.getById(item.getEvaluateTagId());
                if (evaluateTag != null)item.setEvaluateTagName(evaluateTag.getName());
            }
        });
        log.info(this.getClass() + "findList-方法请求结果{}",evaluateTagAppraiseVoList);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),evaluateTagAppraiseVoList);
    }

    /**
    * 对象条件查询返回单条学员教练评价明细表数据
    * @param param
    * @return
    */
    @Override
    public ResObject getInfo(EvaluateTagAppraisePageQueryParam param) {
        log.info(this.getClass() + "getInfo-方法请求参数{}",param);
        if (param == null){
            return R.failure("数据空");
        }
        // 这里判断条件进行查询
        QueryWrapper queryWrapper = this.getQueryWrapper(evaluateTagAppraiseMapStruct, param);
        EvaluateTagAppraiseEntity evaluateTagAppraise = evaluateTagAppraiseService.getOne(queryWrapper);
        if (evaluateTagAppraise == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),evaluateTagAppraise);
        }
        EvaluateTagAppraiseVo evaluateTagAppraiseVo = BeanConvertUtils.copy(evaluateTagAppraise, EvaluateTagAppraiseVo.class);
        log.info(this.getClass() + "getInfo-方法请求结果{}",evaluateTagAppraiseVo);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),evaluateTagAppraiseVo);
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 通过ID获取 学员教练评价明细表 单条数据
     * @date 2020/2/12 17:09
     * @param  * @param EvaluateTagAppraisePageQueryParam
     * @return
     */
    @Override
    public ResObject getById(String id) {
        log.info(this.getClass() + "getById-方法请求参数{}",id);
        if (StrUtil.isEmpty(id)){
            return R.failure("数据空");
        }
        // 通过ID 获取 单条数据
        EvaluateTagAppraiseEntity evaluateTagAppraise = evaluateTagAppraiseService.getById(id);
        if (evaluateTagAppraise == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),evaluateTagAppraise);
        }
        EvaluateTagAppraiseVo evaluateTagAppraiseVo = BeanConvertUtils.copy(evaluateTagAppraise, EvaluateTagAppraiseVo.class);
        log.info(this.getClass() + "getById-方法请求结果{}",evaluateTagAppraiseVo);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),evaluateTagAppraiseVo);
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 保存学员教练评价明细表 数据
     * @date 2020/2/12 17:09
     * @param  * @param EvaluateTagAppraisePageQueryParam
     * @return
     */
    @Override
    public ResObject save(EvaluateTagAppraiseInstallParam installParam) {
        log.info(this.getClass() + "save方法请求参数{}",installParam);
        if (installParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        EvaluateTagAppraiseEntity evaluateTagAppraise = BeanConvertUtils.copy(installParam, EvaluateTagAppraiseEntity.class);
        Boolean result = evaluateTagAppraiseService.save(evaluateTagAppraise);
        log.info(this.getClass() + "save-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_INSTALL_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_INSTALL_FAILL.subCode(),SubResultCode.DATA_INSTALL_FAILL.subMsg());
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description  修改学员教练评价明细表 数据
     * @date 2020/2/12 17:09
     * @param  * @param EvaluateTagAppraisePageQueryParam
     * @return
     */
    @Override
    public ResObject update(EvaluateTagAppraiseEditParam updateParam) {
        log.info(this.getClass() + "update方法请求参数{}",updateParam);
        if (updateParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        EvaluateTagAppraiseEntity evaluateTagAppraise = BeanConvertUtils.copy(updateParam, EvaluateTagAppraiseEntity.class);
        Boolean result = evaluateTagAppraiseService.updateById(evaluateTagAppraise);
        log.info(this.getClass() + "update-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_UPDATE_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_UPDATE_FAILL.subCode(),SubResultCode.DATA_UPDATE_FAILL.subMsg());
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 数组删除学员教练评价明细表 数据
     * @date 2020/2/12 17:09
     * @param  * @param EvaluateTagAppraisePageQueryParam
     * @return
     */
    @Override
    public ResObject deleteByIds(String[] ids) {
        if (ids.length <= 0){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        return R.toRes(evaluateTagAppraiseService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * *通过id删除学员教练评价明细表 信息
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
        Boolean result = evaluateTagAppraiseService.removeById(id);
        log.info(this.getClass() + "deleteById-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_DELETE_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_DELETE_FAILL.subCode(),SubResultCode.DATA_DELETE_FAILL.subMsg());
    }

    /**
     * *导出学员教练评价明细表 信息
     **/
    @Override
    public ResObject exportXls(EvaluateTagAppraisePageQueryParam param, HttpServletResponse response)throws IOException {
        log.info(this.getClass() + "exportXls方法请求参数{}",param);
        QueryWrapper queryWrapper = this.getQueryWrapper(evaluateTagAppraiseMapStruct, param);
        List<EvaluateTagAppraiseEntity> list = evaluateTagAppraiseService.list(queryWrapper);
        List<EvaluateTagAppraiseVo>evaluateTagAppraiseList = evaluateTagAppraiseMapStruct.toVoList(list);
        ExcelUtils.exportExcel(evaluateTagAppraiseList, EvaluateTagAppraiseVo.class, "", new ExportParams(), response);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.EXPORT_DATA_SUCCESS.subMsg());
    }

    /**
     * *修改状态
     **/
    @Override
    public ResObject changeStatus(EvaluateTagAppraiseEditParam param) {
        log.info(this.getClass() + "changeStatus方法请求参数{}",param);
        if (StrUtil.isEmpty(param.getId())){
            log.error("ID数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        EvaluateTagAppraiseEntity EvaluateTagAppraiseEntity = new EvaluateTagAppraiseEntity();
        EvaluateTagAppraiseEntity.setId(param.getId());
        //EvaluateTagAppraiseEntity.setStatus(param.getStatus());
        //EvaluateTagAppraiseEntity.setUpdateTime()
        Boolean result = evaluateTagAppraiseService.updateById(EvaluateTagAppraiseEntity);
        log.info(this.getClass() +"changeStatus方法请求对象参数{}，请求结果{}",EvaluateTagAppraiseEntity,result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_STATUS_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_STATUS_FAILL.subCode(),SubResultCode.DATA_STATUS_FAILL.subMsg());
    }

}

