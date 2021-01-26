package com.drive.admin.repository.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.admin.enums.StatusEnum;
import com.drive.admin.pojo.dto.ServiceItemPriceEditParam;
import com.drive.admin.pojo.dto.ServiceItemPricePageQueryParam;
import com.drive.admin.pojo.entity.ServiceItemEntity;
import com.drive.admin.pojo.entity.ServiceItemPriceEntity;
import com.drive.admin.pojo.vo.ServiceItemPriceVo;
import com.drive.admin.repository.ServiceItemPriceRepository;
import com.drive.admin.service.ServiceItemPriceService;
import com.drive.admin.service.ServiceItemService;
import com.drive.admin.service.ServicePackageDetailService;
import com.drive.admin.service.mapstruct.ServiceItemPriceMapStruct;
import com.drive.common.core.base.BaseController;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.biz.SubResultCode;
import com.drive.common.core.utils.BeanConvertUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

                                        
/**
 *
 * 服务项目价格表 服务类
 *
 * @author JoyoDuan
 */
@Slf4j
@Service
public class  ServiceItemPriceRepositoryImpl extends BaseController<ServiceItemPricePageQueryParam, ServiceItemPriceEntity>  implements ServiceItemPriceRepository{

    @Autowired
    private ServiceItemPriceService serviceItemPriceService;
    @Autowired
    private ServiceItemPriceMapStruct serviceItemPriceMapStruct;

    @Autowired
    private ServiceItemService serviceItemService;

    @Autowired
    private ServicePackageDetailService servicePackageDetailService;

    /**
     * *服务项目价格表 分页列表
     **/
    @Override
    public ResObject pageList(ServiceItemPricePageQueryParam param) {
        log.info(this.getClass() + "pageList-方法请求参数{}",param);
        Page<ServiceItemPriceEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        IPage<ServiceItemPriceEntity> pageList = serviceItemPriceService.page(page, this.getQueryWrapper(serviceItemPriceMapStruct, param));
        Page<ServiceItemPriceVo> serviceItemPriceVoPage = serviceItemPriceMapStruct.toVoList(pageList);
        log.info(this.getClass() + "pageList-方法请求结果{}",serviceItemPriceVoPage);
        return R.success(serviceItemPriceVoPage);
    }

    @Override
    public ResObject findList(ServiceItemPricePageQueryParam param) {
        log.info(this.getClass() + "findList-方法请求参数{}",param);
        // 这里判断条件进行查询
        QueryWrapper queryWrapper= this.getQueryWrapper(serviceItemPriceMapStruct, param);
        // 如 queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"phone",param.getPhone());
        List<ServiceItemPriceEntity> pageList = serviceItemPriceService.list(queryWrapper);
        List<ServiceItemPriceVo> serviceItemPriceVoList = serviceItemPriceMapStruct.toVoList(pageList);
        log.info(this.getClass() + "findList-方法请求结果{}",serviceItemPriceVoList);
        if (serviceItemPriceVoList == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        return R.success(serviceItemPriceVoList);
    }

    /**
     * *通过ID获取服务项目价格表 列表
     **/
    @Override
    public ResObject getInfo(String id) {
        log.info(this.getClass() + "getInfo-方法请求参数{}",id);
        if (StrUtil.isEmpty(id)){
            return R.failure("数据空");
        }
        ServiceItemPriceEntity serviceItemPrice = serviceItemPriceService.getById(id);
        ServiceItemPriceVo serviceItemPriceVo = BeanConvertUtils.copy(serviceItemPrice, ServiceItemPriceVo.class);
        log.info(this.getClass() + "getInfo-方法请求结果{}",serviceItemPriceVo);
        if (serviceItemPriceVo ==null){
            log.error("活动数据对象空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        return R.success(serviceItemPriceVo);
    }

    /**
     * *保存服务项目价格表 信息
     **/
    @Override
    public ResObject save(ServiceItemPriceEditParam installParam) {
        log.info(this.getClass() + "save方法请求参数{}",installParam);
        if (installParam == null){
            log.error("数据空");
            // ,SubResultCode.PARAMISBLANK.subMsg()
            return R.failure(SubResultCode.PARAMISBLANK.subCode());
        }
        // 幂等性
        QueryWrapper<ServiceItemPriceEntity> queryWrapper = new QueryWrapper<ServiceItemPriceEntity>();
        queryWrapper.eq("service_item_id",installParam.getServiceItemId());
        queryWrapper.eq("operator_id",installParam.getOperatorId());
        queryWrapper.eq("status",StatusEnum.ENABLE.getCode());
        int count = serviceItemPriceService.count(queryWrapper);
        if (count > 0){
            log.error("数据不允许重复");
            return R.failure(SubResultCode.DATA_IDEMPOTENT.subCode(),SubResultCode.DATA_IDEMPOTENT.subMsg());
        }
        ServiceItemPriceEntity serviceItemPrice = BeanConvertUtils.copy(installParam, ServiceItemPriceEntity.class);
        Boolean result = serviceItemPriceService.save(serviceItemPrice);
        log.info(this.getClass() + "save-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *修改服务项目价格表 信息
     **/
    @Override
    public ResObject update(ServiceItemPriceEditParam updateParam) {
        log.info(this.getClass() + "update方法请求参数{}",updateParam);
        if (updateParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
            ServiceItemPriceEntity serviceItemPrice = BeanConvertUtils.copy(updateParam, ServiceItemPriceEntity.class);
        Boolean result = serviceItemPriceService.updateById(serviceItemPrice);
        log.info(this.getClass() + "update-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *删除服务项目价格表 信息
     *
     */
    @Override
    public ResObject deleteByIds(String[] ids) {
        return R.toRes(serviceItemPriceService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * *通过id删除服务项目价格表 信息
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
        Boolean result = serviceItemPriceService.removeById(id);
        log.info(this.getClass() + "deleteById-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *导出服务项目价格表 信息
     **/
    @Override
    public ResObject exportXls(ServiceItemPricePageQueryParam param, HttpServletResponse response) {
        return null;
    }

    /**
     * *修改状态
     **/
    @Override
    public ResObject changeStatus(ServiceItemPriceEditParam param) {
// DISABLE
        if (param.getStatus().equals(StatusEnum.DISABLE.getCode())){
            // 判断是否再使用
      /*      QueryWrapper<ServicePackageDetailEntity> queryWrapper =  new QueryWrapper();
            // 服务ID
            queryWrapper.eq(StrUtil.isNotEmpty(param.getId()),"service_item_price_id",param.getId());
            queryWrapper.eq("is_delete","0");
            queryWrapper.eq("status","1");*/
            int servicePackageResultCount = servicePackageDetailService.getPublishStatusCount(param.getId());
            if (servicePackageResultCount > 0){
                return R.failure("不允许停用，目前存在"+ servicePackageResultCount +"项 服务项正在被使用");
            }
        }else{
            // 验证同样名称是否在
            // 判断是否再使用
            QueryWrapper<ServiceItemPriceEntity> queryWrapper =  new QueryWrapper();
            // 服务ID
            queryWrapper.eq(StrUtil.isNotEmpty(param.getServiceItemId()),"service_item_id",param.getServiceItemId());
            queryWrapper.eq("status",StatusEnum.ENABLE.getCode());
            int serviceItemPriceListCount = serviceItemPriceService.count(queryWrapper);
            if (serviceItemPriceListCount > 0){
                return R.failure("不允许启用，服务项存在"+serviceItemPriceListCount +"项名称相同");
            }

            // 验证服务项停用状态

            // 验证同样名称是否在
            // 判断是否再使用
            QueryWrapper<ServiceItemEntity> serviceItemQueryWrapper =  new QueryWrapper<ServiceItemEntity>();
            // 服务ID
            serviceItemQueryWrapper.eq(StrUtil.isNotEmpty(param.getServiceItemId()),"id",param.getServiceItemId());
            serviceItemQueryWrapper.eq("status",StatusEnum.DISABLE.getCode());
            int serviceItemCount = serviceItemService.count(serviceItemQueryWrapper);
            if (serviceItemCount > 0){
                return R.failure("不允许启用，服务项存在被停用状态");
            }
        }
        ServiceItemPriceEntity ServiceItemPriceEntity = new ServiceItemPriceEntity();
        ServiceItemPriceEntity.setId(param.getId());
        ServiceItemPriceEntity.setStatus(param.getStatus());
        Boolean result = serviceItemPriceService.updateById(ServiceItemPriceEntity);
        log.info(this.getClass() +"changeStatus方法请求对象参数{}，请求结果{}",ServiceItemPriceEntity,result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    @Override
    public ResObject queryList(ServiceItemPricePageQueryParam param) {
        log.info(this.getClass() +"queryList-方法请求参数{}",param);
        // 查询对象
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("tsip.is_delete",StatusEnum.NOTDELETE.getCode());
        queryWrapper.eq("tsi.is_delete",StatusEnum.NOTDELETE.getCode());
        queryWrapper.eq(StrUtil.isNotEmpty(param.getStatus()),"tsip.status",param.getStatus());

        // 运营商查询
        queryWrapper.eq(StrUtil.isNotEmpty(param.getOperatorId()),"tsip.operator_Id",param.getOperatorId());

        queryWrapper.orderByDesc("tsip.create_time");
        List<ServiceItemPriceVo>  serviceItemPriceVoList = serviceItemPriceService.queryList(queryWrapper);
        log.info(this.getClass() +"queryList-方法请求结果{}",serviceItemPriceVoList);
        if (serviceItemPriceVoList == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        return R.success(serviceItemPriceVoList);
    }

    @Override
    public ResObject queryPageList(ServiceItemPricePageQueryParam param) {
        log.info(this.getClass() +"queryPageList-方法请求参数{}",param);
        // 查询对象
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("tsip.is_delete",StatusEnum.NOTDELETE.getCode());
        queryWrapper.eq("tsi.is_delete",StatusEnum.NOTDELETE.getCode());
        queryWrapper.eq(StrUtil.isNotEmpty(param.getStatus()),"tsip.status",param.getStatus());
        // 运营商查询
        queryWrapper.eq(StrUtil.isNotEmpty(param.getOperatorId()),"tsip.operator_Id",param.getOperatorId());
        queryWrapper.eq(StrUtil.isNotEmpty(param.getServiceType()),"tsip.service_type",param.getServiceType());

     /*   String sortColumn = param.getSortColumn();
        String underSortColumn = StringUtils.lowerCamelToLowerUnderscore(sortColumn);
        if (param.getIsAsc()) {





        } else {
            queryWrapper.orderByDesc("tsip."+underSortColumn);
        }*/

        queryWrapper.orderByDesc("tsip.create_time");
        Page<ServiceItemPriceEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        IPage<ServiceItemPriceVo>  serviceItemPriceVoIPage = serviceItemPriceService.queryPageList(page,queryWrapper);
        log.info(this.getClass() +"queryPageList-方法请求结果{}",serviceItemPriceVoIPage.getRecords());
        return R.success(serviceItemPriceVoIPage);
    }

    @Override
    public ResObject getQueryListById(ServiceItemPricePageQueryParam param) {
        log.info(this.getClass() +"getQueryListById-方法请求参数{}",param);
        // 查询对象
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("tsp.is_delete",StatusEnum.NOTDELETE.getCode());
        //queryWrapper.eq("tsp.status",StatusEnum.ENABLE.getCode());
        // 运营商查询
        queryWrapper.eq(StrUtil.isNotEmpty(param.getOperatorId()),"tsip.operator_Id",param.getOperatorId());
        // 班型iD
        queryWrapper.eq(StrUtil.isNotEmpty(param.getServicePackageId()),"tsp.service_package_id",param.getServicePackageId());

        queryWrapper.orderByDesc("tsip.create_time");
        List<ServiceItemPriceVo>  serviceItemPriceVoList = serviceItemPriceService.getQueryListById(queryWrapper);
        log.info(this.getClass() +"getQueryListById-方法请求结果{}",serviceItemPriceVoList);
        if (serviceItemPriceVoList == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        return R.success(serviceItemPriceVoList);
    }

    @Override
    public ResObject findListContrast(ServiceItemPricePageQueryParam param) {
        log.info(this.getClass() + "findListContrast-方法请求参数{}",param);
        // 这里判断条件进行查询
        QueryWrapper queryWrapper= this.getQueryWrapper(serviceItemPriceMapStruct, param);
        // 如 queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"phone",param.getPhone());
        List<ServiceItemPriceEntity> pageList = serviceItemPriceService.list(queryWrapper);
        List<ServiceItemPriceVo> serviceItemPriceVoList = serviceItemPriceMapStruct.toVoList(pageList);
        log.info(this.getClass() + "findList-方法请求结果{}",serviceItemPriceVoList);
        if (serviceItemPriceVoList == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        QueryWrapper<ServiceItemEntity> serviceItemQueryWrapper = new QueryWrapper<ServiceItemEntity>();
        serviceItemQueryWrapper.eq("status",StatusEnum.ENABLE.getCode());
        serviceItemQueryWrapper.eq("service_type",param.getServiceType());
        List<ServiceItemEntity> serviceItemList = serviceItemService.list(serviceItemQueryWrapper);
        if (serviceItemList.size() <=0){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        List<ServiceItemEntity> newServiceItemList = new ArrayList<>();
        // 循环取数据
       /* serviceItemPriceVoList.stream().forEach((item) -> {
            serviceItemList.stream().forEach((service) -> {
                // 相同退出
                Boolean isTrue = item.getServiceItemId().equals(service.getId());
                if (!isTrue){
                    log.info("过滤数据操作<==============================>");
                    newServiceItemList.add(service);
                }
            });
        });
*/
        serviceItemList.stream().
                forEach(item -> serviceItemPriceVoList.stream().anyMatch(service -> {
                    boolean judge = false;
                    // 相同退出
                    Boolean isTrue = item.getId().equals(service.getServiceItemId());
                    if (!isTrue) {
                        log.info("过滤数据操作<==============================>");
                        newServiceItemList.add(item);
                        judge = true;
                    }
                    return judge;
        }));
        log.info("新的数组{}",newServiceItemList);
        return R.success(newServiceItemList);
    }
}

