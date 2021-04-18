package com.drive.admin.repository.impl;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.admin.pojo.dto.AccountFlowEditParam;
import com.drive.admin.pojo.dto.AccountFlowInstallParam;
import com.drive.admin.pojo.dto.AccountFlowPageQueryParam;
import com.drive.admin.pojo.entity.AccountFlowEntity;
import com.drive.admin.pojo.vo.AccountFlowVo;
import com.drive.admin.repository.AccountFlowRepository;
import com.drive.admin.service.AccountFlowService;
import com.drive.admin.service.mapstruct.AccountFlowMapStruct;
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
 * 平台账务流水 服务类
 *
 * @author xiaoguo
 */
@Slf4j
@Service
public class  AccountFlowRepositoryImpl extends BaseController<AccountFlowPageQueryParam, AccountFlowEntity>  implements AccountFlowRepository{

    //  平台账务流水 服务
    @Autowired
    private AccountFlowService accountFlowService;
    //  平台账务流水 DO-DTO转化
    @Autowired
    private AccountFlowMapStruct accountFlowMapStruct;

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 平台账务流水 分页列表
     * @date 2020/2/12 17:09
     * @param  * @param AccountFlowPageQueryParam
     * @return
     */
    @Override
    public ResObject pageList(AccountFlowPageQueryParam param) {
        log.info(this.getClass() + "pageList-方法请求参数{}",param);
        Page<AccountFlowEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        // 条件查询
        QueryWrapper queryWrapper = this.getQueryWrapper(accountFlowMapStruct, param);

        //  模糊查询
        //queryWrapper.like(StrUtil.isNotEmpty(param.getVagueNameSearch()),"name",param.getVagueNameSearch());
        //  开始时间 结束时间都有才进入
        if (StrUtil.isNotEmpty(param.getBeginTime()) && StrUtil.isNotEmpty(param.getEndTime())){
            queryWrapper.between(StrUtil.isNotEmpty(param.getBeginTime()),"create_time",param.getBeginTime(),param.getEndTime());
        }
        IPage<AccountFlowEntity> pageList = accountFlowService.page(page, queryWrapper);
        if (pageList.getRecords().size() <= 0){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }
        Page<AccountFlowVo> accountFlowVoPage = accountFlowMapStruct.toVoList(pageList);
        log.info(this.getClass() + "pageList-方法请求结果{}",accountFlowVoPage);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),accountFlowVoPage);
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 平台账务流水 查询列表
     * @date 2020/2/12 17:09
     * @param  * @param AccountFlowPageQueryParam
     * @return
     */
    @Override
    public ResObject findList(AccountFlowPageQueryParam param) {
        log.info(this.getClass() + "findList-方法请求参数{}",param);
        // 这里判断条件进行查询
        QueryWrapper queryWrapper= this.getQueryWrapper(accountFlowMapStruct, param);
        // 如 queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"phone",param.getPhone());
        List<AccountFlowEntity> accountFlowList = accountFlowService.list(queryWrapper);
        if (accountFlowList == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),accountFlowList);
        }
        List<AccountFlowVo> accountFlowVoList = accountFlowMapStruct.toVoList(accountFlowList);
        log.info(this.getClass() + "findList-方法请求结果{}",accountFlowVoList);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),accountFlowVoList);
    }

    /**
    * 对象条件查询返回单条平台账务流水数据
    * @param param
    * @return
    */
    @Override
    public ResObject getInfo(AccountFlowPageQueryParam param) {
        log.info(this.getClass() + "getInfo-方法请求参数{}",param);
        if (param == null){
            return R.failure("数据空");
        }
        // 这里判断条件进行查询
        QueryWrapper queryWrapper = this.getQueryWrapper(accountFlowMapStruct, param);
        AccountFlowEntity accountFlow = accountFlowService.getOne(queryWrapper);
        if (accountFlow == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),accountFlow);
        }
        AccountFlowVo accountFlowVo = BeanConvertUtils.copy(accountFlow, AccountFlowVo.class);
        log.info(this.getClass() + "getInfo-方法请求结果{}",accountFlowVo);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),accountFlowVo);
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 通过ID获取 平台账务流水 单条数据
     * @date 2020/2/12 17:09
     * @param  * @param AccountFlowPageQueryParam
     * @return
     */
    @Override
    public ResObject getById(String id) {
        log.info(this.getClass() + "getById-方法请求参数{}",id);
        if (StrUtil.isEmpty(id)){
            return R.failure("数据空");
        }
        // 通过ID 获取 单条数据
        AccountFlowEntity accountFlow = accountFlowService.getById(id);
        if (accountFlow == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),accountFlow);
        }
        AccountFlowVo accountFlowVo = BeanConvertUtils.copy(accountFlow, AccountFlowVo.class);
        log.info(this.getClass() + "getById-方法请求结果{}",accountFlowVo);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),accountFlowVo);
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 保存平台账务流水 数据
     * @date 2020/2/12 17:09
     * @param  * @param AccountFlowPageQueryParam
     * @return
     */
    @Override
    public ResObject save(AccountFlowInstallParam installParam) {
        log.info(this.getClass() + "save方法请求参数{}",installParam);
        if (installParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        AccountFlowEntity accountFlow = BeanConvertUtils.copy(installParam, AccountFlowEntity.class);
        Boolean result = accountFlowService.save(accountFlow);
        log.info(this.getClass() + "save-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_INSTALL_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_INSTALL_FAILL.subCode(),SubResultCode.DATA_INSTALL_FAILL.subMsg());
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description  修改平台账务流水 数据
     * @date 2020/2/12 17:09
     * @param  * @param AccountFlowPageQueryParam
     * @return
     */
    @Override
    public ResObject update(AccountFlowEditParam updateParam) {
        log.info(this.getClass() + "update方法请求参数{}",updateParam);
        if (updateParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        AccountFlowEntity accountFlow = BeanConvertUtils.copy(updateParam, AccountFlowEntity.class);
        Boolean result = accountFlowService.updateById(accountFlow);
        log.info(this.getClass() + "update-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_UPDATE_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_UPDATE_FAILL.subCode(),SubResultCode.DATA_UPDATE_FAILL.subMsg());
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 数组删除平台账务流水 数据
     * @date 2020/2/12 17:09
     * @param  * @param AccountFlowPageQueryParam
     * @return
     */
    @Override
    public ResObject deleteByIds(String[] ids) {
        if (ids.length <= 0){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        return R.toRes(accountFlowService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * *通过id删除平台账务流水 信息
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
        Boolean result = accountFlowService.removeById(id);
        log.info(this.getClass() + "deleteById-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_DELETE_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_DELETE_FAILL.subCode(),SubResultCode.DATA_DELETE_FAILL.subMsg());
    }

    /**
     * *导出平台账务流水 信息
     **/
    @Override
    public ResObject exportXls(AccountFlowPageQueryParam param, HttpServletResponse response)throws IOException {
        log.info(this.getClass() + "exportXls方法请求参数{}",param);
        QueryWrapper queryWrapper = this.getQueryWrapper(accountFlowMapStruct, param);
        List<AccountFlowEntity> list = accountFlowService.list(queryWrapper);
        List<AccountFlowVo>accountFlowList = accountFlowMapStruct.toVoList(list);
        ExcelUtils.exportExcel(accountFlowList, AccountFlowVo.class, "", new ExportParams(), response);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.EXPORT_DATA_SUCCESS.subMsg());
    }

    /**
     * *修改状态
     **/
    @Override
    public ResObject changeStatus(AccountFlowEditParam param) {
        log.info(this.getClass() + "changeStatus方法请求参数{}",param);
        if (StrUtil.isEmpty(param.getId())){
            log.error("ID数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        AccountFlowEntity AccountFlowEntity = new AccountFlowEntity();
        AccountFlowEntity.setId(param.getId());
        //AccountFlowEntity.setStatus(param.getStatus());
        //AccountFlowEntity.setUpdateTime()
        Boolean result = accountFlowService.updateById(AccountFlowEntity);
        log.info(this.getClass() +"changeStatus方法请求对象参数{}，请求结果{}",AccountFlowEntity,result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_STATUS_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_STATUS_FAILL.subCode(),SubResultCode.DATA_STATUS_FAILL.subMsg());
    }

}

