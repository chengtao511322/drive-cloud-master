package com.drive.admin.repository.impl;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.admin.pojo.dto.AccountFlowDetailEditParam;
import com.drive.admin.pojo.dto.AccountFlowDetailInstallParam;
import com.drive.admin.pojo.dto.AccountFlowDetailPageQueryParam;
import com.drive.admin.pojo.entity.AccountFlowDetailEntity;
import com.drive.admin.pojo.vo.AccountFlowDetailVo;
import com.drive.admin.repository.AccountFlowDetailRepository;
import com.drive.admin.service.AccountFlowDetailService;
import com.drive.admin.service.mapstruct.AccountFlowDetailMapStruct;
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
 * 平台账务流水明细 服务类
 *
 * @author xiaoguo
 */
@Slf4j
@Service
public class  AccountFlowDetailRepositoryImpl extends BaseController<AccountFlowDetailPageQueryParam, AccountFlowDetailEntity>  implements AccountFlowDetailRepository{

    //  平台账务流水明细 服务
    @Autowired
    private AccountFlowDetailService accountFlowDetailService;
    //  平台账务流水明细 DO-DTO转化
    @Autowired
    private AccountFlowDetailMapStruct accountFlowDetailMapStruct;

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 平台账务流水明细 分页列表
     * @date 2020/2/12 17:09
     * @param  * @param AccountFlowDetailPageQueryParam
     * @return
     */
    @Override
    public ResObject pageList(AccountFlowDetailPageQueryParam param) {
        log.info(this.getClass() + "pageList-方法请求参数{}",param);
        Page<AccountFlowDetailEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        // 条件查询
        QueryWrapper queryWrapper = this.getQueryWrapper(accountFlowDetailMapStruct, param);

        //  模糊查询
        //queryWrapper.like(StrUtil.isNotEmpty(param.getVagueNameSearch()),"name",param.getVagueNameSearch());
        //  开始时间 结束时间都有才进入
        if (StrUtil.isNotEmpty(param.getBeginTime()) && StrUtil.isNotEmpty(param.getEndTime())){
            queryWrapper.between(StrUtil.isNotEmpty(param.getBeginTime()),"create_time",param.getBeginTime(),param.getEndTime());
        }
        IPage<AccountFlowDetailEntity> pageList = accountFlowDetailService.page(page, queryWrapper);
        if (pageList.getRecords().size() <= 0){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }
        Page<AccountFlowDetailVo> accountFlowDetailVoPage = accountFlowDetailMapStruct.toVoList(pageList);
        log.info(this.getClass() + "pageList-方法请求结果{}",accountFlowDetailVoPage);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),accountFlowDetailVoPage);
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 平台账务流水明细 查询列表
     * @date 2020/2/12 17:09
     * @param  * @param AccountFlowDetailPageQueryParam
     * @return
     */
    @Override
    public ResObject findList(AccountFlowDetailPageQueryParam param) {
        log.info(this.getClass() + "findList-方法请求参数{}",param);
        // 这里判断条件进行查询
        QueryWrapper queryWrapper= this.getQueryWrapper(accountFlowDetailMapStruct, param);
        // 如 queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"phone",param.getPhone());
        List<AccountFlowDetailEntity> accountFlowDetailList = accountFlowDetailService.list(queryWrapper);
        if (accountFlowDetailList == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),accountFlowDetailList);
        }
        List<AccountFlowDetailVo> accountFlowDetailVoList = accountFlowDetailMapStruct.toVoList(accountFlowDetailList);
        log.info(this.getClass() + "findList-方法请求结果{}",accountFlowDetailVoList);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),accountFlowDetailVoList);
    }

    /**
    * 对象条件查询返回单条平台账务流水明细数据
    * @param param
    * @return
    */
    @Override
    public ResObject getInfo(AccountFlowDetailPageQueryParam param) {
        log.info(this.getClass() + "getInfo-方法请求参数{}",param);
        if (param == null){
            return R.failure("数据空");
        }
        // 这里判断条件进行查询
        QueryWrapper queryWrapper = this.getQueryWrapper(accountFlowDetailMapStruct, param);
        AccountFlowDetailEntity accountFlowDetail = accountFlowDetailService.getOne(queryWrapper);
        if (accountFlowDetail == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),accountFlowDetail);
        }
        AccountFlowDetailVo accountFlowDetailVo = BeanConvertUtils.copy(accountFlowDetail, AccountFlowDetailVo.class);
        log.info(this.getClass() + "getInfo-方法请求结果{}",accountFlowDetailVo);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),accountFlowDetailVo);
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 通过ID获取 平台账务流水明细 单条数据
     * @date 2020/2/12 17:09
     * @param  * @param AccountFlowDetailPageQueryParam
     * @return
     */
    @Override
    public ResObject getById(String id) {
        log.info(this.getClass() + "getById-方法请求参数{}",id);
        if (StrUtil.isEmpty(id)){
            return R.failure("数据空");
        }
        // 通过ID 获取 单条数据
        AccountFlowDetailEntity accountFlowDetail = accountFlowDetailService.getById(id);
        if (accountFlowDetail == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),accountFlowDetail);
        }
        AccountFlowDetailVo accountFlowDetailVo = BeanConvertUtils.copy(accountFlowDetail, AccountFlowDetailVo.class);
        log.info(this.getClass() + "getById-方法请求结果{}",accountFlowDetailVo);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),accountFlowDetailVo);
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 保存平台账务流水明细 数据
     * @date 2020/2/12 17:09
     * @param  * @param AccountFlowDetailPageQueryParam
     * @return
     */
    @Override
    public ResObject save(AccountFlowDetailInstallParam installParam) {
        log.info(this.getClass() + "save方法请求参数{}",installParam);
        if (installParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        AccountFlowDetailEntity accountFlowDetail = BeanConvertUtils.copy(installParam, AccountFlowDetailEntity.class);
        Boolean result = accountFlowDetailService.save(accountFlowDetail);
        log.info(this.getClass() + "save-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_INSTALL_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_INSTALL_FAILL.subCode(),SubResultCode.DATA_INSTALL_FAILL.subMsg());
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description  修改平台账务流水明细 数据
     * @date 2020/2/12 17:09
     * @param  * @param AccountFlowDetailPageQueryParam
     * @return
     */
    @Override
    public ResObject update(AccountFlowDetailEditParam updateParam) {
        log.info(this.getClass() + "update方法请求参数{}",updateParam);
        if (updateParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        AccountFlowDetailEntity accountFlowDetail = BeanConvertUtils.copy(updateParam, AccountFlowDetailEntity.class);
        Boolean result = accountFlowDetailService.updateById(accountFlowDetail);
        log.info(this.getClass() + "update-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_UPDATE_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_UPDATE_FAILL.subCode(),SubResultCode.DATA_UPDATE_FAILL.subMsg());
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 数组删除平台账务流水明细 数据
     * @date 2020/2/12 17:09
     * @param  * @param AccountFlowDetailPageQueryParam
     * @return
     */
    @Override
    public ResObject deleteByIds(String[] ids) {
        if (ids.length <= 0){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        return R.toRes(accountFlowDetailService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * *通过id删除平台账务流水明细 信息
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
        Boolean result = accountFlowDetailService.removeById(id);
        log.info(this.getClass() + "deleteById-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_DELETE_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_DELETE_FAILL.subCode(),SubResultCode.DATA_DELETE_FAILL.subMsg());
    }

    /**
     * *导出平台账务流水明细 信息
     **/
    @Override
    public ResObject exportXls(AccountFlowDetailPageQueryParam param, HttpServletResponse response)throws IOException {
        log.info(this.getClass() + "exportXls方法请求参数{}",param);
        QueryWrapper queryWrapper = this.getQueryWrapper(accountFlowDetailMapStruct, param);
        List<AccountFlowDetailEntity> list = accountFlowDetailService.list(queryWrapper);
        List<AccountFlowDetailVo>accountFlowDetailList = accountFlowDetailMapStruct.toVoList(list);
        ExcelUtils.exportExcel(accountFlowDetailList, AccountFlowDetailVo.class, "", new ExportParams(), response);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.EXPORT_DATA_SUCCESS.subMsg());
    }

    /**
     * *修改状态
     **/
    @Override
    public ResObject changeStatus(AccountFlowDetailEditParam param) {
        log.info(this.getClass() + "changeStatus方法请求参数{}",param);
        if (StrUtil.isEmpty(param.getId())){
            log.error("ID数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        AccountFlowDetailEntity AccountFlowDetailEntity = new AccountFlowDetailEntity();
        AccountFlowDetailEntity.setId(param.getId());
        //AccountFlowDetailEntity.setStatus(param.getStatus());
        //AccountFlowDetailEntity.setUpdateTime()
        Boolean result = accountFlowDetailService.updateById(AccountFlowDetailEntity);
        log.info(this.getClass() +"changeStatus方法请求对象参数{}，请求结果{}",AccountFlowDetailEntity,result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_STATUS_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_STATUS_FAILL.subCode(),SubResultCode.DATA_STATUS_FAILL.subMsg());
    }

}

