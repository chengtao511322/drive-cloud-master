package com.drive.admin.repository.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.admin.enums.StatusEnum;
import com.drive.admin.pojo.dto.ServiceItemEditParam;
import com.drive.admin.pojo.dto.ServiceItemPageQueryParam;
import com.drive.admin.pojo.entity.ServiceItemEntity;
import com.drive.admin.pojo.entity.ServiceItemPriceEntity;
import com.drive.admin.pojo.vo.ServiceItemVo;
import com.drive.admin.repository.ServiceItemRepository;
import com.drive.admin.service.ServiceItemPriceService;
import com.drive.admin.service.ServiceItemService;
import com.drive.admin.service.mapstruct.ServiceItemMapStruct;
import com.drive.common.core.base.BaseController;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.biz.SubResultCode;
import com.drive.common.core.utils.BeanConvertUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

                                
/**
 *
 * 服务项目表 服务类
 *
 * @author JoyoDuan
 */
@Slf4j
@Service
public class  ServiceItemRepositoryImpl extends BaseController<ServiceItemPageQueryParam, ServiceItemEntity>  implements ServiceItemRepository{

    @Autowired
    private ServiceItemService serviceItemService;

    @Autowired
    private ServiceItemPriceService serviceItemPriceService;
    @Autowired
    private ServiceItemMapStruct serviceItemMapStruct;

    /**
     * *服务项目表 分页列表
     **/
    @Override
    public ResObject pageList(ServiceItemPageQueryParam param) {
        log.info(this.getClass() + "pageList-方法请求参数{}",param);

        String name = param.getName();
        param.setName(null);
        QueryWrapper<ServiceItemEntity> queryWrapper =  this.getQueryWrapper(serviceItemMapStruct, param);
        // 服务类型查询
        queryWrapper.like(StrUtil.isNotEmpty(name),"name", name);

        Page<ServiceItemEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        IPage<ServiceItemEntity> pageList = serviceItemService.page(page, queryWrapper);
        Page<ServiceItemVo> serviceItemVoPage = serviceItemMapStruct.toVoList(pageList);
        log.info(this.getClass() + "pageList-方法请求结果{}",serviceItemVoPage);
        return R.success(serviceItemVoPage);
    }

    @Override
    public ResObject findList(ServiceItemPageQueryParam param) {
        log.info(this.getClass() + "findList-方法请求参数{}",param);
        // 这里判断条件进行查询
        QueryWrapper queryWrapper= this.getQueryWrapper(serviceItemMapStruct, param);
        // 如 queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"phone",param.getPhone());
        List<ServiceItemEntity> pageList = serviceItemService.list(queryWrapper);
        List<ServiceItemVo> serviceItemVoList = serviceItemMapStruct.toVoList(pageList);
        log.info(this.getClass() + "findList-方法请求结果{}",serviceItemVoList);
        if (serviceItemVoList == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        return R.success(serviceItemVoList);
    }

    /**
     * *通过ID获取服务项目表 列表
     **/
    @Override
    public ResObject getInfo(String id) {
        log.info(this.getClass() + "getInfo-方法请求参数{}",id);
        if (StrUtil.isEmpty(id)){
            return R.failure("数据空");
        }
            ServiceItemEntity serviceItem = serviceItemService.getById(id);
            ServiceItemVo serviceItemVo = BeanConvertUtils.copy(serviceItem, ServiceItemVo.class);
        log.info(this.getClass() + "getInfo-方法请求结果{}",serviceItemVo);
        if (serviceItemVo ==null){
            log.error("活动数据对象空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        return R.success(serviceItemVo);
    }

    /**
     * *保存服务项目表 信息
     **/
    @Override
    public ResObject save(ServiceItemEditParam installParam) {
        log.info(this.getClass() + "save方法请求参数{}",installParam);
        if (installParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        ServiceItemEntity serviceItem = BeanConvertUtils.copy(installParam, ServiceItemEntity.class);
        log.info(this.getClass() + "保存的数据是{}",serviceItem);
        Boolean result = serviceItemService.save(serviceItem);
        log.info(this.getClass() + "save-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *修改服务项目表 信息
     **/
    @Override
    public ResObject update(ServiceItemEditParam updateParam) {
        log.info(this.getClass() + "update方法请求参数{}",updateParam);
        if (updateParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        ServiceItemEntity serviceItem = BeanConvertUtils.copy(updateParam, ServiceItemEntity.class);
        Boolean result = serviceItemService.updateById(serviceItem);
        log.info(this.getClass() + "update-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *删除服务项目表 信息
     **/
    @Override
    public ResObject deleteByIds(String[] ids) {
        return R.toRes(serviceItemService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * *通过id删除服务项目表 信息
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
        Boolean result = serviceItemService.removeById(id);
        log.info(this.getClass() + "deleteById-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *导出服务项目表 信息
     **/
    @Override
    public ResObject exportXls(ServiceItemPageQueryParam param, HttpServletResponse response) {
        return null;
    }

    /**
     * *修改状态
     **/
    @Override
    public ResObject changeStatus(ServiceItemEditParam param) {
        log.info(this.getClass() + "changeStatus-方法请求参数{}",param);
        if (StrUtil.isEmpty(param.getId())) {
            log.error("出错");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        // DISABLE
        if (param.getStatus().equals(StatusEnum.DISABLE.getCode())){
            // 判断是否再使用
            QueryWrapper<ServiceItemPriceEntity> queryWrapper =  new QueryWrapper();
            // 服务ID
            queryWrapper.eq(StrUtil.isNotEmpty(param.getId()),"service_item_id",param.getId());
            queryWrapper.eq("is_delete",StatusEnum.NOTDELETE.getCode());
            queryWrapper.eq("status",StatusEnum.ENABLE.getCode());
            int serviceItemPriceListCount = serviceItemPriceService.count(queryWrapper);
            if (serviceItemPriceListCount > 0){
                return R.failure("不允许停用，服务项存在"+serviceItemPriceListCount +"项正在被使用");
            }
        }

        ServiceItemEntity ServiceItemEntity = new ServiceItemEntity();
        ServiceItemEntity.setId(param.getId());
        ServiceItemEntity.setStatus(param.getStatus());
        Boolean result = serviceItemService.updateById(ServiceItemEntity);
        log.info(this.getClass() +"changeStatus方法请求对象参数{}，请求结果{}",ServiceItemEntity,result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

}

