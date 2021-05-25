package com.drive.admin.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.drive.admin.pojo.entity.CodeEntity;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.drive.common.core.base.BaseController;
import com.drive.admin.repository.CodeRepository;
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
import com.drive.admin.service.CodeService;
import com.drive.common.data.utils.ExcelUtils;
import java.util.List;
import java.util.Arrays;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

                                                    
/**
 *
 * 字典表 服务类
 *
 * @author chentao
 */
@Slf4j
@Service
public class  CodeRepositoryImpl extends BaseController<CodePageQueryParam, CodeEntity>  implements CodeRepository{

    //  字典表 服务
    @Autowired
    private CodeService codeService;
    //  字典表 DO-DTO转化
    @Autowired
    private CodeMapStruct codeMapStruct;

    /*
     *
     *功能描述
     * @author chentao
     * @description 字典表 分页列表
     * @date 2020/2/12 17:09
     * @param  * @param CodePageQueryParam
     * @return
     */
    @Override
    public ResObject pageList(CodePageQueryParam param) {
        log.info(this.getClass() + "pageList-方法请求参数{}",param);
        Page<CodeEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        // 条件查询
        QueryWrapper queryWrapper = this.getQueryWrapper(codeMapStruct, param);

        //  模糊查询
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueNameSearch()),"name",param.getVagueNameSearch());
        //  开始时间 结束时间都有才进入
        if (StrUtil.isNotEmpty(param.getBeginTime()) && StrUtil.isNotEmpty(param.getEndTime())){
            queryWrapper.between(StrUtil.isNotEmpty(param.getBeginTime()),"create_time",param.getBeginTime(),param.getEndTime());
        }
        IPage<CodeEntity> pageList = codeService.page(page, queryWrapper);
        if (pageList.getRecords().size() <= 0){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }
        Page<CodeVo> codeVoPage = codeMapStruct.toVoList(pageList);
        log.info(this.getClass() + "pageList-方法请求结果{}",codeVoPage);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),codeVoPage);
    }

    /*
     *
     *功能描述
     * @author chentao
     * @description 字典表 查询列表
     * @date 2020/2/12 17:09
     * @param  * @param CodePageQueryParam
     * @return
     */
    @Override
    public ResObject findList(CodePageQueryParam param) {
        log.info(this.getClass() + "findList-方法请求参数{}",param);
        // 这里判断条件进行查询
        QueryWrapper queryWrapper= this.getQueryWrapper(codeMapStruct, param);
        // 通过名称查询
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueNameSearch()),"name",param.getVagueNameSearch());
        // 如 queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"phone",param.getPhone());
        List<CodeEntity> codeList = codeService.list(queryWrapper);
        if (codeList == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),codeList);
        }
        List<CodeVo> codeVoList = codeMapStruct.toVoList(codeList);
        log.info(this.getClass() + "findList-方法请求结果{}",codeVoList);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),codeVoList);
    }

    /**
    * 对象条件查询返回单条字典表数据
    * @param param
    * @return
    */
    @Override
    public ResObject getInfo(CodePageQueryParam param) {
        log.info(this.getClass() + "getInfo-方法请求参数{}",param);
        if (param == null){
            return R.failure("数据空");
        }
        // 这里判断条件进行查询
        QueryWrapper queryWrapper = this.getQueryWrapper(codeMapStruct, param);
        // 这里有且只返回一条数据
        queryWrapper.last("limit 1");
        CodeEntity code = codeService.getOne(queryWrapper);
        if (code == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),code);
        }
        CodeVo codeVo = BeanConvertUtils.copy(code, CodeVo.class);
        log.info(this.getClass() + "getInfo-方法请求结果{}",codeVo);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),codeVo);
    }

    /*
     *
     *功能描述
     * @author chentao
     * @description 通过ID获取 字典表 单条数据
     * @date 2020/2/12 17:09
     * @param  * @param CodePageQueryParam
     * @return
     */
    @Override
    public ResObject getById(String id) {
        log.info(this.getClass() + "getById-方法请求参数{}",id);
        if (StrUtil.isEmpty(id)){
            return R.failure("数据空");
        }
        // 通过ID 获取 单条数据
        CodeEntity code = codeService.getById(id);
        if (code == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),code);
        }
        CodeVo codeVo = BeanConvertUtils.copy(code, CodeVo.class);
        log.info(this.getClass() + "getById-方法请求结果{}",codeVo);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),codeVo);
    }

    /*
     *
     *功能描述
     * @author chentao
     * @description 保存字典表 数据
     * @date 2020/2/12 17:09
     * @param  * @param CodePageQueryParam
     * @return
     */
    @Override
    public ResObject save(CodeInstallParam installParam) {
        log.info(this.getClass() + "save方法请求参数{}",installParam);
        if (installParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        CodeEntity code = BeanConvertUtils.copy(installParam, CodeEntity.class);
        Boolean result = codeService.save(code);
        log.info(this.getClass() + "save-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_INSTALL_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_INSTALL_FAILL.subCode(),SubResultCode.DATA_INSTALL_FAILL.subMsg());
    }

    /*
     *
     *功能描述
     * @author chentao
     * @description  修改字典表 数据
     * @date 2020/2/12 17:09
     * @param  * @param CodePageQueryParam
     * @return
     */
    @Override
    public ResObject update(CodeEditParam updateParam) {
        log.info(this.getClass() + "update方法请求参数{}",updateParam);
        if (updateParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        CodeEntity code = BeanConvertUtils.copy(updateParam, CodeEntity.class);
        Boolean result = codeService.updateById(code);
        log.info(this.getClass() + "update-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_UPDATE_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_UPDATE_FAILL.subCode(),SubResultCode.DATA_UPDATE_FAILL.subMsg());
    }

    /*
     *
     *功能描述
     * @author chentao
     * @description 数组删除字典表 数据
     * @date 2020/2/12 17:09
     * @param  * @param CodePageQueryParam
     * @return
     */
    @Override
    public ResObject deleteByIds(String[] ids) {
        if (ids.length <= 0){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        return R.toRes(codeService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * *通过id删除字典表 信息
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
        Boolean result = codeService.removeById(id);
        log.info(this.getClass() + "deleteById-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_DELETE_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_DELETE_FAILL.subCode(),SubResultCode.DATA_DELETE_FAILL.subMsg());
    }

    /**
     * *导出字典表 信息
     **/
    @Override
    public ResObject exportXls(CodePageQueryParam param, HttpServletResponse response)throws IOException {
        log.info(this.getClass() + "exportXls方法请求参数{}",param);
        QueryWrapper queryWrapper = this.getQueryWrapper(codeMapStruct, param);
        List<CodeEntity> list = codeService.list(queryWrapper);
        List<CodeVo>codeList = codeMapStruct.toVoList(list);
        ExcelUtils.exportExcel(codeList, CodeVo.class, "", new ExportParams(), response);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.EXPORT_DATA_SUCCESS.subMsg());
    }

    /**
     * *修改状态
     **/
    @Override
    public ResObject changeStatus(CodeEditParam param) {
        log.info(this.getClass() + "changeStatus方法请求参数{}",param);
        if (StrUtil.isEmpty(param.getCodeId())){
            log.error("ID数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        CodeEntity CodeEntity = new CodeEntity();
        CodeEntity.setCodeId(param.getCodeId());
        //CodeEntity.setUpdateTime()
        Boolean result = codeService.updateById(CodeEntity);
        log.info(this.getClass() +"changeStatus方法请求对象参数{}，请求结果{}",CodeEntity,result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_STATUS_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_STATUS_FAILL.subCode(),SubResultCode.DATA_STATUS_FAILL.subMsg());
    }

}

