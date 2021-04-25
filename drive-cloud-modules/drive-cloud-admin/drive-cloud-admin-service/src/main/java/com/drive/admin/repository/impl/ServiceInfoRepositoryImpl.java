package com.drive.admin.repository.impl;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.admin.pojo.dto.ServiceInfoEditParam;
import com.drive.admin.pojo.dto.ServiceInfoPageQueryParam;
import com.drive.admin.pojo.entity.ServiceInfoEntity;
import com.drive.admin.pojo.vo.ServiceInfoVo;
import com.drive.admin.repository.ServiceInfoRepository;
import com.drive.admin.service.AreaService;
import com.drive.admin.service.ServiceInfoService;
import com.drive.admin.service.mapstruct.ServiceInfoMapStruct;
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
 * 客服人员信息表 服务类
 *
 * @author xiaoguo
 */
@Slf4j
@Service
public class  ServiceInfoRepositoryImpl extends BaseController<ServiceInfoPageQueryParam, ServiceInfoEntity>  implements ServiceInfoRepository{

    @Autowired
    private ServiceInfoService serviceInfoService;
    @Autowired
    private ServiceInfoMapStruct serviceInfoMapStruct;

    @Autowired
    private AreaService areaService;

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 客服人员信息表 分页列表
     * @date 2020/2/12 17:09
     * @param  * @param ServiceInfoPageQueryParam
     * @return
     */
    @Override
    public ResObject pageList(ServiceInfoPageQueryParam param) {
        log.info(this.getClass() + "pageList-方法请求参数{}",param);
        Page<ServiceInfoEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        QueryWrapper queryWrapper = this.getQueryWrapper(serviceInfoMapStruct, param);
        // 客服姓名模糊查询
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueRealNameSearch()),"real_name",param.getVagueRealNameSearch());
        // 手机号 模糊查询
        queryWrapper.like(StrUtil.isNotEmpty(param.getVaguePhoneSearch()),"phone",param.getVaguePhoneSearch());
        // 登录账户模糊查询
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueLoginAccountSearch()),"login_account",param.getVagueLoginAccountSearch());
        //queryWrapper.apply(StrUtil.isNotBlank(param.getBeSpeakMeetTimeSearch()),
          //      "date_format (be_speak_meet_time,'%Y-%m-%d') = date_format('" + param.getBeSpeakMeetTimeSearch() + "','%Y-%m-%d')");
        //  开始时间 结束时间都有才进入
        if (StrUtil.isNotEmpty(param.getBeginTime()) && StrUtil.isNotEmpty(param.getEndTime())){
            queryWrapper.between(StrUtil.isNotEmpty(param.getBeginTime()),"create_time",param.getBeginTime(),param.getEndTime());
        }
        IPage<ServiceInfoEntity> pageList = serviceInfoService.page(page,queryWrapper);
        if (pageList.getRecords().size() <=0){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }
        Page<ServiceInfoVo> serviceInfoVoPage = serviceInfoMapStruct.toVoList(pageList);
       /* serviceInfoVoPage.getRecords().stream().forEach((item) ->{
            // 省市区
            if (StrUtil.isNotEmpty(item.getProvinceId()))item.setProvinceName(areaService.getByBaCode(item.getProvinceId()).getBaName());
            if (StrUtil.isNotEmpty(item.getCityId()))item.setCityName(areaService.getByBaCode(item.getCityId()).getBaName());
            if (StrUtil.isNotEmpty(item.getAreaId()))item.setAreaName(areaService.getByBaCode(item.getAreaId()).getBaName());
        });*/
        log.info(this.getClass() + "pageList-方法请求结果{}",serviceInfoVoPage);
        return R.success(serviceInfoVoPage);
    }

    @Override
    public ResObject findList(ServiceInfoPageQueryParam param) {
        log.info(this.getClass() + "findList-方法请求参数{}",param);
        // 这里判断条件进行查询
        QueryWrapper queryWrapper= this.getQueryWrapper(serviceInfoMapStruct, param);
        // 如 queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"phone",param.getPhone());
        List<ServiceInfoEntity> pageList = serviceInfoService.list(queryWrapper);
        if (pageList == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }
        List<ServiceInfoVo> serviceInfoVoList = serviceInfoMapStruct.toVoList(pageList);
        log.info(this.getClass() + "findList-方法请求结果{}",serviceInfoVoList);
        return R.success(serviceInfoVoList);
    }


    @Override
    public ResObject getInfo(ServiceInfoPageQueryParam param) {
        return null;
    }

    /**
     * *通过ID获取客服人员信息表 列表
     **/
    @Override
    public ResObject getById(String id) {
        log.info(this.getClass() + "getInfo-方法请求参数{}",id);
        if (StrUtil.isEmpty(id)){
            return R.failure("数据空");
        }
        ServiceInfoEntity serviceInfo = serviceInfoService.getById(id);
        ServiceInfoVo serviceInfoVo = BeanConvertUtils.copy(serviceInfo, ServiceInfoVo.class);
        log.info(this.getClass() + "getInfo-方法请求结果{}",serviceInfoVo);
        if (serviceInfoVo ==null){
            log.error("活动数据对象空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        return R.success(serviceInfoVo);
    }

    /**
     * *保存客服人员信息表 信息
     **/
    @Override
    public ResObject save(ServiceInfoEditParam installParam) {
        log.info(this.getClass() + "save方法请求参数{}",installParam);
        if (installParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        // 校验手机
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("login_account",installParam.getLoginAccount());
        queryWrapper.eq("operator_id",installParam.getOperatorId());
        int count = serviceInfoService.count(queryWrapper);
        if (count >0){
            return R.failure(SubResultCode.DATA_IDEMPOTENT.subCode(),SubResultCode.DATA_IDEMPOTENT.subMsg());
        }
        // 密码加密
        if (StrUtil.isNotEmpty(installParam.getPassword())){
            String md5Password = SecureUtil.md5(installParam.getPassword());
            installParam.setPassword(md5Password);
        }
        ServiceInfoEntity serviceInfo = BeanConvertUtils.copy(installParam, ServiceInfoEntity.class);
        Boolean result = serviceInfoService.save(serviceInfo);
        log.info(this.getClass() + "save-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *修改客服人员信息表 信息
     **/
    @Override
    public ResObject update(ServiceInfoEditParam updateParam) {
        log.info(this.getClass() + "update方法请求参数{}",updateParam);
        if (updateParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        // 密码加密
        if (StrUtil.isNotEmpty(updateParam.getPassword())){
            String md5Password = SecureUtil.md5(updateParam.getPassword());
            updateParam.setPassword(md5Password);
        }
        ServiceInfoEntity serviceInfo = BeanConvertUtils.copy(updateParam, ServiceInfoEntity.class);
        Boolean result = serviceInfoService.updateById(serviceInfo);
        log.info(this.getClass() + "update-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *删除客服人员信息表 信息
     **/
    @Override
    public ResObject deleteByIds(String[] ids) {
        if (ids.length <= 0){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        return R.toRes(serviceInfoService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * *通过id删除客服人员信息表 信息
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
        Boolean result = serviceInfoService.removeById(id);
        log.info(this.getClass() + "deleteById-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *导出客服人员信息表 信息
     **/
    @Override
    public ResObject exportXls(ServiceInfoPageQueryParam param, HttpServletResponse response)throws IOException {
        log.info(this.getClass() + "exportXls方法请求参数{}",param);
        QueryWrapper queryWrapper = this.getQueryWrapper(serviceInfoMapStruct, param);
        List<ServiceInfoEntity> list = serviceInfoService.list(queryWrapper);
        List<ServiceInfoVo>serviceInfoList = serviceInfoMapStruct.toVoList(list);
        ExcelUtils.exportExcel(serviceInfoList, ServiceInfoVo.class, "", new ExportParams(), response);
        return R.success("导出成功");
    }

    /**
     * *修改状态
     **/
    @Override
    public ResObject changeStatus(ServiceInfoEditParam param) {
        log.info(this.getClass() + "changeStatus方法请求参数{}",param);
        if (StrUtil.isEmpty(param.getId())){
            log.error("ID数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        ServiceInfoEntity ServiceInfoEntity = new ServiceInfoEntity();
        ServiceInfoEntity.setId(param.getId());
        ServiceInfoEntity.setStatus(param.getStatus());
        //ServiceInfoEntity.setUpdateTime()
        Boolean result = serviceInfoService.updateById(ServiceInfoEntity);
        log.info(this.getClass() +"changeStatus方法请求对象参数{}，请求结果{}",ServiceInfoEntity,result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    @Override
    public ResObject resetPwd(ServiceInfoEditParam serviceInfoEditParam) {
        log.info(this.getClass() + "resetPwd-方法请求参数{}",serviceInfoEditParam);
        if (StrUtil.isEmpty(serviceInfoEditParam.getId())){
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        if (StrUtil.isEmpty(serviceInfoEditParam.getPassword())){
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        String md5Password = SecureUtil.md5(serviceInfoEditParam.getPassword());
        ServiceInfoEntity serviceInfoEntity = serviceInfoMapStruct.toEntity(serviceInfoEditParam);
        serviceInfoEntity.setPassword(md5Password);
        Boolean updateResult = serviceInfoService.updateById(serviceInfoEntity);
        if (!updateResult){
            return R.failure(SubResultCode.DATA_UPDATE_FAILL.subCode(),SubResultCode.DATA_UPDATE_FAILL.subMsg());
        }
        return R.success(updateResult);
    }
}

