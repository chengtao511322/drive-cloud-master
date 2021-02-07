package com.drive.admin.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.drive.admin.pojo.entity.ServiceReturnVisitHistoryEntity;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.drive.common.core.base.BaseController;
import com.drive.admin.repository.ServiceReturnVisitHistoryRepository;
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
import com.drive.admin.service.ServiceReturnVisitHistoryService;
import com.drive.common.data.utils.ExcelUtils;
import java.util.List;
import java.util.Arrays;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

                                                
/**
 *
 * 客服回访记录 服务类
 *
 * @author xiaoguo
 */
@Slf4j
@Service
public class  ServiceReturnVisitHistoryRepositoryImpl extends BaseController<ServiceReturnVisitHistoryPageQueryParam, ServiceReturnVisitHistoryEntity>  implements ServiceReturnVisitHistoryRepository{

    @Autowired
    private ServiceReturnVisitHistoryService serviceReturnVisitHistoryService;
    @Autowired
    private ServiceReturnVisitHistoryMapStruct serviceReturnVisitHistoryMapStruct;

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 客服回访记录 分页列表
     * @date 2020/2/12 17:09
     * @param  * @param ServiceReturnVisitHistoryPageQueryParam
     * @return
     */
    @Override
    public ResObject pageList(ServiceReturnVisitHistoryPageQueryParam param) {
        log.info(this.getClass() + "pageList-方法请求参数{}",param);
        Page<ServiceReturnVisitHistoryEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        IPage<ServiceReturnVisitHistoryEntity> pageList = serviceReturnVisitHistoryService.page(page, this.getQueryWrapper(serviceReturnVisitHistoryMapStruct, param));
        Page<ServiceReturnVisitHistoryVo> serviceReturnVisitHistoryVoPage = serviceReturnVisitHistoryMapStruct.toVoList(pageList);
        log.info(this.getClass() + "pageList-方法请求结果{}",serviceReturnVisitHistoryVoPage);
        return R.success(serviceReturnVisitHistoryVoPage);
    }

    @Override
    public ResObject findList(ServiceReturnVisitHistoryPageQueryParam param) {
        log.info(this.getClass() + "findList-方法请求参数{}",param);
        // 这里判断条件进行查询
        QueryWrapper queryWrapper= this.getQueryWrapper(serviceReturnVisitHistoryMapStruct, param);
        // 如 queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"phone",param.getPhone());
        List<ServiceReturnVisitHistoryEntity> pageList = serviceReturnVisitHistoryService.list(queryWrapper);
        List<ServiceReturnVisitHistoryVo> serviceReturnVisitHistoryVoList = serviceReturnVisitHistoryMapStruct.toVoList(pageList);
        log.info(this.getClass() + "findList-方法请求结果{}",serviceReturnVisitHistoryVoList);
        if (serviceReturnVisitHistoryVoList == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        return R.success(serviceReturnVisitHistoryVoList);
    }



    @Override
    public ResObject getInfo(ServiceReturnVisitHistoryPageQueryParam param) {
        return null;
    }

    /**
     * *通过ID获取客服回访记录 列表
     **/
    @Override
    public ResObject getById(String id) {
        log.info(this.getClass() + "getInfo-方法请求参数{}",id);
        if (StrUtil.isEmpty(id)){
            return R.failure("数据空");
        }
        ServiceReturnVisitHistoryEntity serviceReturnVisitHistory = serviceReturnVisitHistoryService.getById(id);
        ServiceReturnVisitHistoryVo serviceReturnVisitHistoryVo = BeanConvertUtils.copy(serviceReturnVisitHistory, ServiceReturnVisitHistoryVo.class);
        log.info(this.getClass() + "getInfo-方法请求结果{}",serviceReturnVisitHistoryVo);
        if (serviceReturnVisitHistoryVo ==null){
            log.error("活动数据对象空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        return R.success(serviceReturnVisitHistoryVo);
    }

    /**
     * *保存客服回访记录 信息
     **/
    @Override
    public ResObject save(ServiceReturnVisitHistoryEditParam installParam) {
        log.info(this.getClass() + "save方法请求参数{}",installParam);
        if (installParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        ServiceReturnVisitHistoryEntity serviceReturnVisitHistory = BeanConvertUtils.copy(installParam, ServiceReturnVisitHistoryEntity.class);
        Boolean result = serviceReturnVisitHistoryService.save(serviceReturnVisitHistory);
        log.info(this.getClass() + "save-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *修改客服回访记录 信息
     **/
    @Override
    public ResObject update(ServiceReturnVisitHistoryEditParam updateParam) {
        log.info(this.getClass() + "update方法请求参数{}",updateParam);
        if (updateParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        ServiceReturnVisitHistoryEntity serviceReturnVisitHistory = BeanConvertUtils.copy(updateParam, ServiceReturnVisitHistoryEntity.class);
        Boolean result = serviceReturnVisitHistoryService.updateById(serviceReturnVisitHistory);
        log.info(this.getClass() + "update-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *删除客服回访记录 信息
     **/
    @Override
    public ResObject deleteByIds(String[] ids) {
        if (ids.length <= 0){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        return R.toRes(serviceReturnVisitHistoryService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * *通过id删除客服回访记录 信息
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
        Boolean result = serviceReturnVisitHistoryService.removeById(id);
        log.info(this.getClass() + "deleteById-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *导出客服回访记录 信息
     **/
    @Override
    public ResObject exportXls(ServiceReturnVisitHistoryPageQueryParam param, HttpServletResponse response)throws IOException {
        log.info(this.getClass() + "exportXls方法请求参数{}",param);
        QueryWrapper queryWrapper = this.getQueryWrapper(serviceReturnVisitHistoryMapStruct, param);
        List<ServiceReturnVisitHistoryEntity> list = serviceReturnVisitHistoryService.list(queryWrapper);
        List<ServiceReturnVisitHistoryVo>serviceReturnVisitHistoryList = serviceReturnVisitHistoryMapStruct.toVoList(list);
        ExcelUtils.exportExcel(serviceReturnVisitHistoryList, ServiceReturnVisitHistoryVo.class, "", new ExportParams(), response);
        return R.success("导出成功");
    }

    /**
     * *修改状态
     **/
    @Override
    public ResObject changeStatus(ServiceReturnVisitHistoryEditParam param) {
        log.info(this.getClass() + "changeStatus方法请求参数{}",param);
        if (StrUtil.isEmpty(param.getId())){
            log.error("ID数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        ServiceReturnVisitHistoryEntity ServiceReturnVisitHistoryEntity = new ServiceReturnVisitHistoryEntity();
        ServiceReturnVisitHistoryEntity.setId(param.getId());
        //ServiceReturnVisitHistoryEntity.setStatus(param.getStatus());
        //ServiceReturnVisitHistoryEntity.setUpdateTime()
        Boolean result = serviceReturnVisitHistoryService.updateById(ServiceReturnVisitHistoryEntity);
        log.info(this.getClass() +"changeStatus方法请求对象参数{}，请求结果{}",ServiceReturnVisitHistoryEntity,result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

}

