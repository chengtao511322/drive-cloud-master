package com.drive.admin.repository.impl;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.admin.pojo.dto.ServicePackageDetailEditParam;
import com.drive.admin.pojo.dto.ServicePackageDetailPageQueryParam;
import com.drive.admin.pojo.entity.ServicePackageDetailEntity;
import com.drive.admin.pojo.vo.ServicePackageDetailVo;
import com.drive.admin.repository.ServicePackageDetailRepository;
import com.drive.admin.service.ServicePackageDetailService;
import com.drive.admin.service.mapstruct.ServicePackageDetailMapStruct;
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
 * 服务项目打包明细表 服务类
 *
 * @author xiaoguo
 */
@Slf4j
@Service
public class  ServicePackageDetailRepositoryImpl extends BaseController<ServicePackageDetailPageQueryParam, ServicePackageDetailEntity>  implements ServicePackageDetailRepository{

    @Autowired
    private ServicePackageDetailService servicePackageDetailService;
    @Autowired
    private ServicePackageDetailMapStruct servicePackageDetailMapStruct;

    /**
     * *服务项目打包明细表 分页列表
     **/
    @Override
    public ResObject pageList(ServicePackageDetailPageQueryParam param) {
        log.info(this.getClass() + "pageList-方法请求参数{}",param);
        Page<ServicePackageDetailEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        IPage<ServicePackageDetailEntity> pageList = servicePackageDetailService.page(page, this.getQueryWrapper(servicePackageDetailMapStruct, param));
        Page<ServicePackageDetailVo> servicePackageDetailVoPage = servicePackageDetailMapStruct.toVoList(pageList);
        log.info(this.getClass() + "pageList-方法请求结果{}",servicePackageDetailVoPage);
        return R.success(servicePackageDetailVoPage);
    }

    @Override
    public ResObject findList(ServicePackageDetailPageQueryParam param) {
        log.info(this.getClass() + "findList-方法请求参数{}",param);
        // 这里判断条件进行查询
        QueryWrapper queryWrapper= this.getQueryWrapper(servicePackageDetailMapStruct, param);
        // 如 queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"phone",param.getPhone());
        List<ServicePackageDetailEntity> pageList = servicePackageDetailService.list(queryWrapper);
        List<ServicePackageDetailVo> servicePackageDetailVoList = servicePackageDetailMapStruct.toVoList(pageList);
        log.info(this.getClass() + "findList-方法请求结果{}",servicePackageDetailVoList);
        if (servicePackageDetailVoList == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        return R.success(servicePackageDetailVoList);
    }

    /**
     * *通过ID获取服务项目打包明细表 列表
     **/
    @Override
    public ResObject getInfo(String id) {
        log.info(this.getClass() + "getInfo-方法请求参数{}",id);
        if (StrUtil.isEmpty(id)){
            return R.failure("数据空");
        }
        ServicePackageDetailEntity servicePackageDetail = servicePackageDetailService.getById(id);
        ServicePackageDetailVo servicePackageDetailVo = BeanConvertUtils.copy(servicePackageDetail, ServicePackageDetailVo.class);
        log.info(this.getClass() + "getInfo-方法请求结果{}",servicePackageDetailVo);
        if (servicePackageDetailVo ==null){
            log.error("活动数据对象空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        return R.success(servicePackageDetailVo);
    }

    /**
     * *保存服务项目打包明细表 信息
     **/
    @Override
    public ResObject save(ServicePackageDetailEditParam installParam) {

        log.info(this.getClass() + "save方法请求参数{}",installParam);
        if (installParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        ServicePackageDetailEntity servicePackageDetail = BeanConvertUtils.copy(installParam, ServicePackageDetailEntity.class);
        Boolean result = servicePackageDetailService.save(servicePackageDetail);
        log.info(this.getClass() + "save-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *修改服务项目打包明细表 信息
     **/
    @Override
    public ResObject update(ServicePackageDetailEditParam updateParam) {
        log.info(this.getClass() + "update方法请求参数{}",updateParam);
        if (updateParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        ServicePackageDetailEntity servicePackageDetail = BeanConvertUtils.copy(updateParam, ServicePackageDetailEntity.class);
        Boolean result = servicePackageDetailService.updateById(servicePackageDetail);
        log.info(this.getClass() + "update-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *删除服务项目打包明细表 信息
     **/
    @Override
    public ResObject deleteByIds(String[] ids) {
        return R.toRes(servicePackageDetailService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * *通过id删除服务项目打包明细表 信息
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
        Boolean result = servicePackageDetailService.removeById(id);
        log.info(this.getClass() + "deleteById-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *导出服务项目打包明细表 信息
     **/
    @Override
    public ResObject exportXls(ServicePackageDetailPageQueryParam param, HttpServletResponse response) throws IOException {
        List<ServicePackageDetailEntity> list = servicePackageDetailService.list(this.getQueryWrapper(servicePackageDetailMapStruct, param));
        List<ServicePackageDetailVo> couponVoList = servicePackageDetailMapStruct.toVoList(list);
        ExcelUtils.exportExcel(couponVoList, ServicePackageDetailVo.class, "", new ExportParams(), response);
        return R.success("导出成功");
    }

    /**
     * *修改状态
     **/
    @Override
    public ResObject changeStatus(ServicePackageDetailEditParam param) {
        ServicePackageDetailEntity ServicePackageDetailEntity = new ServicePackageDetailEntity();
        ServicePackageDetailEntity.setId(param.getId());
        ServicePackageDetailEntity.setStatus(param.getStatus());
        Boolean result = servicePackageDetailService.updateById(ServicePackageDetailEntity);
        log.info(this.getClass() +"changeStatus方法请求对象参数{}，请求结果{}",ServicePackageDetailEntity,result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    @Override
    public ResObject updateBatch(List<ServicePackageDetailEditParam> servicePackageDetailEditParamList) {
        log.info(this.getClass() + "updateBatch-方法请求参数{}",servicePackageDetailEditParamList);
        if (servicePackageDetailEditParamList.size() <= 0){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        List<ServicePackageDetailEntity> servicePackageDetailList = BeanConvertUtils.copyList(servicePackageDetailEditParamList, ServicePackageDetailEntity.class);
        Boolean result = servicePackageDetailService.updateBatchById(servicePackageDetailList);
        log.info(this.getClass() + "结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }
}

