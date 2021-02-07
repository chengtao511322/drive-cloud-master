package com.drive.admin.repository.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.admin.enums.StatusEnum;
import com.drive.admin.pojo.dto.OneFeeSystemPriceEditParam;
import com.drive.admin.pojo.dto.OneFeeSystemPricePageQueryParam;
import com.drive.admin.pojo.dto.ServicePackageDetailEditParam;
import com.drive.admin.pojo.dto.TreeNodeCategoryDto;
import com.drive.admin.pojo.entity.OneFeeSystemPriceEntity;
import com.drive.admin.pojo.entity.ServiceItemPriceEntity;
import com.drive.admin.pojo.entity.ServicePackageDetailEntity;
import com.drive.admin.pojo.vo.OneFeeSystemPriceVo;
import com.drive.admin.pojo.vo.ServicePackageDetailVo;
import com.drive.admin.repository.OneFeeSystemPriceRepository;
import com.drive.admin.service.OneFeeSystemPriceService;
import com.drive.admin.service.ServiceItemPriceService;
import com.drive.admin.service.ServicePackageDetailService;
import com.drive.admin.service.mapstruct.OneFeeSystemPriceMapStruct;
import com.drive.common.core.base.BaseController;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResCodeEnum;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.biz.SubResultCode;
import com.drive.common.core.exception.BizException;
import com.drive.common.core.utils.BeanConvertUtils;
import com.drive.common.core.utils.IdWorkerUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;


/**
 *
 * 学车一费制定价表 服务类
 *
 * @author xiaoguo
 */
@Slf4j
@Service
public class  OneFeeSystemPriceRepositoryImpl extends BaseController<OneFeeSystemPricePageQueryParam, OneFeeSystemPriceEntity>  implements OneFeeSystemPriceRepository{

    @Autowired
    private OneFeeSystemPriceService oneFeeSystemPriceService;
    @Autowired
    private OneFeeSystemPriceMapStruct oneFeeSystemPriceMapStruct;

    @Autowired
    private ServicePackageDetailService servicePackageDetailService;

    @Autowired
    private ServiceItemPriceService serviceItemPriceService;


    /**
     * *学车一费制定价表 分页列表
     **/
    @Override
    public ResObject pageList(OneFeeSystemPricePageQueryParam param) {
        log.info(this.getClass() + "pageList-方法请求参数{}",param);
        String searchName  = param.getName();
        param.setName(null);
        Page<OneFeeSystemPriceEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        IPage<OneFeeSystemPriceEntity> pageList = oneFeeSystemPriceService.page(page, this.getQueryWrapper(oneFeeSystemPriceMapStruct, param).like(StrUtil.isNotEmpty(searchName),"name",searchName));
        Page<OneFeeSystemPriceVo> oneFeeSystemPriceVoPage = oneFeeSystemPriceMapStruct.toVoList(pageList);
        log.info(this.getClass() + "pageList-方法请求结果{}",oneFeeSystemPriceVoPage);
        return R.success(oneFeeSystemPriceVoPage);
    }

    @Override
    public ResObject findList(OneFeeSystemPricePageQueryParam param) {
        log.info(this.getClass() + "findList-方法请求参数{}",param);
        // 这里判断条件进行查询
        QueryWrapper queryWrapper= this.getQueryWrapper(oneFeeSystemPriceMapStruct, param);
        // 如 queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"phone",param.getPhone());
        List<OneFeeSystemPriceEntity> pageList = oneFeeSystemPriceService.list(queryWrapper);
        List<OneFeeSystemPriceVo> oneFeeSystemPriceVoList = oneFeeSystemPriceMapStruct.toVoList(pageList);
        log.info(this.getClass() + "findList-方法请求结果{}",oneFeeSystemPriceVoList);
        if (oneFeeSystemPriceVoList == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        return R.success(oneFeeSystemPriceVoList);
    }



    @Override
    public ResObject getInfo(OneFeeSystemPricePageQueryParam param) {
        return null;
    }

    /**
     * *通过ID获取学车一费制定价表 列表
     **/
    @Override
    public ResObject getById(String id) {
        log.info(this.getClass() + "getInfo-方法请求参数{}",id);
        if (StrUtil.isEmpty(id)){
            return R.failure("数据空");
        }
        OneFeeSystemPriceEntity oneFeeSystemPrice = oneFeeSystemPriceService.getById(id);
        OneFeeSystemPriceVo oneFeeSystemPriceVo = BeanConvertUtils.copy(oneFeeSystemPrice, OneFeeSystemPriceVo.class);
        log.info(this.getClass() + "getInfo-方法请求结果{}",oneFeeSystemPriceVo);
        if (oneFeeSystemPriceVo ==null){
            log.error("活动数据对象空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        return R.success(oneFeeSystemPriceVo);
    }

    /**
     * *保存学车一费制定价表 信息
     **/
    @Override
    public ResObject save(OneFeeSystemPriceEditParam installParam) {

        log.info(this.getClass() + "save方法请求参数{}",installParam);
        if (installParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        OneFeeSystemPriceEntity oneFeeSystemPrice = BeanConvertUtils.copy(installParam, OneFeeSystemPriceEntity.class);
        Boolean result = oneFeeSystemPriceService.save(oneFeeSystemPrice);
        log.info(this.getClass() + "save-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *修改学车一费制定价表 信息
     **/
    @Override
    public ResObject update(OneFeeSystemPriceEditParam updateParam) {
        log.info(this.getClass() + "update方法请求参数{}",updateParam);
        if (updateParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
            OneFeeSystemPriceEntity oneFeeSystemPrice = BeanConvertUtils.copy(updateParam, OneFeeSystemPriceEntity.class);
        Boolean result = oneFeeSystemPriceService.updateById(oneFeeSystemPrice);
        log.info(this.getClass() + "update-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *删除学车一费制定价表 信息
     **/
    @Override
    public ResObject deleteByIds(String[] ids) {
        return R.toRes(oneFeeSystemPriceService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * *通过id删除学车一费制定价表 信息
     **/
    @Override
    public ResObject deleteById(String id) {
        log.info(this.getClass() + "deleteById-方法请求参数{}",id);
        if(StrUtil.isEmpty(id)){
            log.error("ID数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        OneFeeSystemPriceEntity oneFeeSystemPrice = oneFeeSystemPriceService.getById(id);
        if (oneFeeSystemPrice.getStatus().equals(StatusEnum.ENABLE.getCode())){
            log.error("删除数据必须先停用");
            return R.failure(SubResultCode.DATA_MUST_DISABLE.subCode(),SubResultCode.DATA_MUST_DISABLE.subMsg());
        }
        //QueryWrapper<String> queryWrapper = new QueryWrapper<String>();
        //queryWrapper.eq(StrUtil.isNotEmpty(id),"id",id);
        Boolean result = oneFeeSystemPriceService.removeById(id);
        log.info(this.getClass() + "deleteById-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *导出学车一费制定价表 信息
     **/
    @Override
    public ResObject exportXls(OneFeeSystemPricePageQueryParam param, HttpServletResponse response) {
        return null;
    }

    /**
     * *修改状态
     **/
    @Override
    public ResObject changeStatus(OneFeeSystemPriceEditParam param) {
        log.info(this.getClass() + "changeStatus方法请求参数{}",param);
        if (param == null){
            log.error("参数空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        OneFeeSystemPriceEntity OneFeeSystemPriceEntity = new OneFeeSystemPriceEntity();
        OneFeeSystemPriceEntity.setId(param.getId());
        OneFeeSystemPriceEntity.setStatus(param.getStatus());
        Boolean result = oneFeeSystemPriceService.updateById(OneFeeSystemPriceEntity);
        log.info(this.getClass() +"changeStatus方法请求对象参数{}，请求结果{}",OneFeeSystemPriceEntity,result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    @Override
    @Transactional
    public ResObject publishServicePackage(OneFeeSystemPriceEditParam oneFeeSystemPriceEditParam) {
        log.info(this.getClass() + "publishServicePackage-方法请求参数{}",oneFeeSystemPriceEditParam);
        if (oneFeeSystemPriceEditParam == null){
            log.error("参数空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        // 查询幂等性
        // 查询幂等性
        QueryWrapper servicePackageQueryWrapper= new QueryWrapper();
        // 通过名称
        servicePackageQueryWrapper.eq("name",oneFeeSystemPriceEditParam.getName());
        // 运营商查询
        servicePackageQueryWrapper.eq("operator_id",oneFeeSystemPriceEditParam.getOperatorId());
        // 删除状态查询
        servicePackageQueryWrapper.eq("is_delete",StatusEnum.NOTDELETE.getCode());
        servicePackageQueryWrapper.eq("status",StatusEnum.ENABLE.getCode());
        OneFeeSystemPriceEntity one= oneFeeSystemPriceService.getOne(servicePackageQueryWrapper);

        if (one != null){
            return R.failure(SubResultCode.DATA_UPDATE_FAILL.subCode(),"不允许重复");
        }
        // 保存服务包
        OneFeeSystemPriceEntity oneFeeSystemPrice = BeanConvertUtils.copy(oneFeeSystemPriceEditParam, OneFeeSystemPriceEntity.class);
        Boolean result = oneFeeSystemPriceService.save(oneFeeSystemPrice);
        log.info(this.getClass() + "publishServicePackage-方法请求结果{}",result);
        if (!result){
            log.error("添加服务包错误，回滚");
            throw new BizException(ResCodeEnum.ERROR.getCode(),ResCodeEnum.ERROR.getMsg());
        }
        // 添加服务包明细

        List<ServicePackageDetailEditParam> serviceItemPriceList = oneFeeSystemPriceEditParam.getServiceItemPriceList();
        if (serviceItemPriceList !=null){
            serviceItemPriceList.stream().forEach((item) ->{
                ServiceItemPriceEntity servicePrice = serviceItemPriceService.getById(item.getServiceItemPriceId());
                log.info("servicePrice{}",servicePrice);
                if (servicePrice.getStatus().equals(StatusEnum.DISABLE.getCode())){
                    log.error("数据已经被停用，回滚");
                    throw new BizException(ResCodeEnum.ERROR.getCode(),"数据已经被停用");
                }
                item.setServicePackageId(oneFeeSystemPrice.getId());
                // 保存服务包
                ServicePackageDetailEntity servicePackageDetailEntity = BeanConvertUtils.copy(item, ServicePackageDetailEntity.class);
                // 先删除 后 添加
                QueryWrapper queryWrapper= new QueryWrapper();
                // 服务包ID
                queryWrapper.eq("service_package_id",oneFeeSystemPrice.getId());
                // 服务价格ID
                queryWrapper.eq("service_item_price_id",item.getServiceItemPriceId());
                Boolean delResult = servicePackageDetailService.remove(queryWrapper);
                log.info("删除的状态{}",delResult);
                // 设置状态
                servicePackageDetailEntity.setStatus(oneFeeSystemPriceEditParam.getStatus());
                Boolean servicePackageResult = servicePackageDetailService.save(servicePackageDetailEntity);
                log.info(this.getClass() + "publishServicePackage-方法请求结果{}",result);
                if (!servicePackageResult){
                    log.error("添加服务包关联错误，回滚");
                    throw new BizException(ResCodeEnum.ERROR.getCode(),ResCodeEnum.ERROR.getMsg());
                }
            });
        }

        return R.success("发布成功");
    }

    @Override
    @Transactional
    public ResObject updateServicePackage(OneFeeSystemPriceEditParam oneFeeSystemPriceEditParam) {
        log.info(this.getClass() + "updateServicePackage-方法请求参数{}",oneFeeSystemPriceEditParam);
        if (StrUtil.isEmpty(oneFeeSystemPriceEditParam.getId())){
            log.error("参数空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }

/*        if (oneFeeSystemPriceEditParam.getStatus().equals(StatusEnum.ENABLE.getCode())){
            log.error("修改数据必须先停用");
            return R.failure(SubResultCode.DATA_MUST_DISABLE.subCode(),SubResultCode.DATA_MUST_DISABLE.subMsg());
        }*/

        // 修改服务包
        OneFeeSystemPriceEntity oneFeeSystemPrice = BeanConvertUtils.copy(oneFeeSystemPriceEditParam, OneFeeSystemPriceEntity.class);
        // 更新时间
        oneFeeSystemPrice.setUpdateTime(LocalDateTime.now());
        Boolean result = oneFeeSystemPriceService.updateById(oneFeeSystemPrice);
        log.info(this.getClass() + "updateServicePackage-方法请求结果{}",result);
        if (!result){
            log.error("添加服务包错误，回滚");
            throw new BizException(ResCodeEnum.ERROR.getCode(),ResCodeEnum.ERROR.getMsg());
        }
        // 添加服务包明细
        List<ServicePackageDetailEditParam> serviceItemPriceList = oneFeeSystemPriceEditParam.getServiceItemPriceList();
        if (serviceItemPriceList !=null){
            // 先删除 后 添加
            QueryWrapper queryWrapper= new QueryWrapper();
            // 服务包ID
            queryWrapper.eq("service_package_id",oneFeeSystemPrice.getId());
            // 服务价格ID
            Boolean delResult = servicePackageDetailService.remove(queryWrapper);
            log.info("删除的状态{}",delResult);
            serviceItemPriceList.stream().forEach((item) ->{
                ServiceItemPriceEntity servicePrice = serviceItemPriceService.getById(item.getServiceItemPriceId());
                log.info("servicePrice{}",servicePrice);
                if (servicePrice.getStatus().equals(StatusEnum.DISABLE.getCode())){
                    log.error("数据已经被停用，回滚");
                    throw new BizException(ResCodeEnum.ERROR.getCode(),"数据已经被停用");
                }
                /*ServicePackageDetailEditParam servicePackageDetailEditParam = new ServicePackageDetailEditParam();
                // 设置服务包ID
                servicePackageDetailEditParam.setServicePackageId(oneFeeSystemPrice.getId());
                // 设置服务包价格ID
                servicePackageDetailEditParam.setServiceItemPriceId(item.get());*/
                item.setServicePackageId(oneFeeSystemPrice.getId());
                // 保存服务包
                ServicePackageDetailEntity servicePackageDetailEntity = BeanConvertUtils.copy(item, ServicePackageDetailEntity.class);
                // 设置状态
                servicePackageDetailEntity.setStatus(oneFeeSystemPriceEditParam.getStatus());
                Boolean servicePackageResult = servicePackageDetailService.save(servicePackageDetailEntity);
                log.info(this.getClass() + "publishServicePackage-方法请求结果{}",result);
                if (!servicePackageResult){
                    log.error("添加服务包关联错误，回滚");
                    throw new BizException(ResCodeEnum.ERROR.getCode(),ResCodeEnum.ERROR.getMsg());
                }
            });
        }
        return R.success("修改成功");
    }

    @Override
    @Transactional
    public ResObject copyServicePackage(OneFeeSystemPriceEditParam oneFeeSystemPriceEditParam) {
        log.info(this.getClass() + "copyServicePackage方法请求参数{}",oneFeeSystemPriceEditParam);
        if (StrUtil.isEmpty(oneFeeSystemPriceEditParam.getId())){
            log.error("参数空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }

        // 先查询出来
        OneFeeSystemPriceEntity oneFeeSystemPriceEntity = oneFeeSystemPriceService.getById(oneFeeSystemPriceEditParam.getId());
        log.info(this.getClass() + "copyServicePackage方法请求结果{}",oneFeeSystemPriceEntity);
        if (oneFeeSystemPriceEntity == null){
            log.error("数据空");
            return R.failure(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        IdWorkerUtil idWorker = new IdWorkerUtil();
        // 原ID
        String oldId = oneFeeSystemPriceEntity.getId();
        // 新ID
        String newId = String.valueOf(idWorker.nextId());
        // 复制默认0
        oneFeeSystemPriceEntity.setStatus(StatusEnum.NOTPUBLISH.getCode());
        oneFeeSystemPriceEntity.setId(newId);
        oneFeeSystemPriceEntity.setCreateTime(LocalDateTime.now());
        oneFeeSystemPriceEntity.setUpdateTime(LocalDateTime.now());
        Boolean result = oneFeeSystemPriceService.save(oneFeeSystemPriceEntity);
        log.info(this.getClass() + "publishServicePackage-方法请求结果{}",result);
        if (!result){
            log.error("添加服务包错误，回滚");
            throw new BizException(ResCodeEnum.ERROR.getCode(),ResCodeEnum.ERROR.getMsg());
        }
        // 原ID 查询
        QueryWrapper queryWrapper= new QueryWrapper();
        queryWrapper.eq("service_package_id",oldId);
        List<ServicePackageDetailEntity> servicePackageDetailEntityList = servicePackageDetailService.list(queryWrapper);
        if(servicePackageDetailEntityList != null && servicePackageDetailEntityList.size() >0){
            servicePackageDetailEntityList.stream().forEach((item) ->{
                item.setId(String.valueOf(idWorker.nextId()));
                item.setCreateTime(LocalDateTime.now());
                item.setUpdateTime(LocalDateTime.now());
                item.setServicePackageId(newId);
                item.setStatus(StatusEnum.ENABLE.getCode());
            });
            log.info(this.getClass() + "copyServicePackage-方法 提交数据{}",servicePackageDetailEntityList);
            Boolean servicePackageResult =servicePackageDetailService.saveBatch(servicePackageDetailEntityList);
            if (!servicePackageResult){
                log.error("添加服务包错误，回滚");
                throw new BizException(ResCodeEnum.ERROR.getCode(),ResCodeEnum.ERROR.getMsg());
            }
        }
        return R.success("复制成功", JSONObject.parse(newId));
    }

    @Override
    public ResObject findPageSubList(OneFeeSystemPricePageQueryParam param) {
        log.info(this.getClass() + "findPageSubList-方法请求参数{}",param);
        String searchName = param.getName();
        param.setName(null);
        Page<OneFeeSystemPriceEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        IPage<OneFeeSystemPriceEntity> pageList = oneFeeSystemPriceService.page(page, this.getQueryWrapper(oneFeeSystemPriceMapStruct, param).like(StrUtil.isNotEmpty(searchName),"name",searchName));
        Page<OneFeeSystemPriceVo> oneFeeSystemPriceVoPage = oneFeeSystemPriceMapStruct.toVoList(pageList);
        log.info(this.getClass() + "findPageSubList-方法请求结果{}",oneFeeSystemPriceVoPage);
        // 匹配版型价格去
        oneFeeSystemPriceVoPage.getRecords().stream().forEach((item) ->{
            QueryWrapper<ServicePackageDetailEntity> queryWrapper = new QueryWrapper<ServicePackageDetailEntity>();
            queryWrapper.eq("service_package_id",item.getId());
            List<ServicePackageDetailEntity> servicePackageDetailEntityList = servicePackageDetailService.list(queryWrapper);
            List<ServicePackageDetailVo> servicePackageDetailVos = BeanConvertUtils.copyList(servicePackageDetailEntityList,ServicePackageDetailVo.class);
            if (servicePackageDetailVos!=null)item.setServicePackageDetailVos(servicePackageDetailVos);
        });
        //oneFeeSystemPriceVoPage.f().lambdaQuery().eq(OperatorEntity::getId, operatorId).list();
        return R.success(oneFeeSystemPriceVoPage);
    }

    @Override
    public ResObject getServicePackageTree(String tenantId) {
        log.info(this.getClass() + "getServicePackageTree方法请求参数{}",tenantId);
        if (StrUtil.isEmpty(tenantId)){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        List<TreeNodeCategoryDto> oneFeeSystemPriceServiceServicePackageTree = oneFeeSystemPriceService.getServicePackageTree(tenantId);
        log.info("数据结果{}",oneFeeSystemPriceServiceServicePackageTree);
        return R.success(oneFeeSystemPriceServiceServicePackageTree);
    }
}

