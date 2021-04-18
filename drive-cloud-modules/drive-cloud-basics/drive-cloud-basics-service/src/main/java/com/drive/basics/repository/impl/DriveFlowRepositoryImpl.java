package com.drive.basics.repository.impl;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.basics.pojo.dto.DriveFlowEditParam;
import com.drive.basics.pojo.dto.DriveFlowInstallParam;
import com.drive.basics.pojo.dto.DriveFlowPageQueryParam;
import com.drive.basics.pojo.entity.ChannelEntity;
import com.drive.basics.pojo.entity.DriveFlowEntity;
import com.drive.basics.pojo.vo.DriveFlowVo;
import com.drive.basics.repository.DriveFlowRepository;
import com.drive.basics.service.ChannelService;
import com.drive.basics.service.DriveFlowService;
import com.drive.basics.service.mapstruct.DriveFlowMapStruct;
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
 * 流程信息管理 服务类
 *
 * @author xiaoguo
 */
@Slf4j
@Service
public class  DriveFlowRepositoryImpl extends BaseController<DriveFlowPageQueryParam, DriveFlowEntity>  implements DriveFlowRepository{

    //  流程信息管理 服务
    @Autowired
    private DriveFlowService driveFlowService;
    //  流程信息管理 DO-DTO转化
    @Autowired
    private DriveFlowMapStruct driveFlowMapStruct;
    // 栏目菜单
    @Autowired
    private ChannelService channelService;

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 流程信息管理 分页列表
     * @date 2020/2/12 17:09
     * @param  * @param DriveFlowPageQueryParam
     * @return
     */
    @Override
    public ResObject pageList(DriveFlowPageQueryParam param) {
        log.info(this.getClass() + "pageList-方法请求参数{}",param);
        Page<DriveFlowEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        // 条件查询
        QueryWrapper queryWrapper = this.getQueryWrapper(driveFlowMapStruct, param);

        //  模糊查询
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueTitleSearch()),"title",param.getVagueTitleSearch());
        //  开始时间 结束时间都有才进入
        if (StrUtil.isNotEmpty(param.getBeginTime()) && StrUtil.isNotEmpty(param.getEndTime())){
            queryWrapper.between(StrUtil.isNotEmpty(param.getBeginTime()),"create_time",param.getBeginTime(),param.getEndTime());
        }
        IPage<DriveFlowEntity> pageList = driveFlowService.page(page, queryWrapper);
        if (pageList.getRecords().size() <= 0){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }
        Page<DriveFlowVo> driveFlowVoPage = driveFlowMapStruct.toVoList(pageList);

        // 循环
        driveFlowVoPage.getRecords().stream().forEach((item) ->{
            // 查询出 对应的菜单
            ChannelEntity channelDo = channelService.getById(item.getChannelId());
            if (channelDo != null){
                item.setChannelName(channelDo.getName());
            }
        });
        log.info(this.getClass() + "pageList-方法请求结果{}",driveFlowVoPage);
        return R.success(SubResultCode.DATA_SEARCH_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),driveFlowVoPage);
    }

    @Override
    public ResObject findList(DriveFlowPageQueryParam param) {
        log.info(this.getClass() + "findList-方法请求参数{}",param);
        // 这里判断条件进行查询
        QueryWrapper queryWrapper= this.getQueryWrapper(driveFlowMapStruct, param);
        // 如 queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"phone",param.getPhone());
        List<DriveFlowEntity> driveFlowList = driveFlowService.list(queryWrapper);
        if (driveFlowList .size() <= 0){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),driveFlowList);
        }
        List<DriveFlowVo> driveFlowVoList = driveFlowMapStruct.toVoList(driveFlowList);
        log.info(this.getClass() + "findList-方法请求结果{}",driveFlowVoList);
        return R.success(SubResultCode.DATA_SEARCH_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),driveFlowVoList);
    }

    /**
    * 条件查询返回单条流程信息管理数据
    * @param param
    * @return
    */
    @Override
    public ResObject getInfo(DriveFlowPageQueryParam param) {
        log.info(this.getClass() + "getInfo-方法请求参数{}",param);
        if (param == null){
            return R.failure("数据空");
        }
        // 这里判断条件进行查询
        QueryWrapper queryWrapper = this.getQueryWrapper(driveFlowMapStruct, param);
        DriveFlowEntity driveFlow = driveFlowService.getOne(queryWrapper);
        if (driveFlow == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        DriveFlowVo driveFlowVo = BeanConvertUtils.copy(driveFlow, DriveFlowVo.class);
        log.info(this.getClass() + "getInfo-方法请求结果{}",driveFlowVo);
        return R.success(SubResultCode.DATA_SEARCH_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),driveFlowVo);
    }

    /**
     * *通过ID获取流程信息管理 列表
     **/
    @Override
    public ResObject getById(String id) {
        log.info(this.getClass() + "getById-方法请求参数{}",id);
        if (StrUtil.isEmpty(id)){
            return R.failure("数据空");
        }
        DriveFlowEntity driveFlow = driveFlowService.getById(id);
        if (driveFlow == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),driveFlow);
        }
        DriveFlowVo driveFlowVo = BeanConvertUtils.copy(driveFlow, DriveFlowVo.class);
        log.info(this.getClass() + "getById-方法请求结果{}",driveFlowVo);
        return R.success(SubResultCode.DATA_SEARCH_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),driveFlowVo);
    }

    /**
     * *保存流程信息管理 信息
     **/
    @Override
    public ResObject save(DriveFlowInstallParam installParam) {
        log.info(this.getClass() + "save方法请求参数{}",installParam);
        if (installParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        DriveFlowEntity driveFlow = BeanConvertUtils.copy(installParam, DriveFlowEntity.class);
        Boolean result = driveFlowService.saveOrUpdate(driveFlow);
        log.info(this.getClass() + "save-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.DATA_INSTALL_SUCCESS.subCode(),SubResultCode.DATA_INSTALL_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_INSTALL_FAILL.subCode(),SubResultCode.DATA_INSTALL_FAILL.subMsg());
    }

    /**
     * *修改流程信息管理 信息
     **/
    @Override
    public ResObject update(DriveFlowEditParam updateParam) {
        log.info(this.getClass() + "update方法请求参数{}",updateParam);
        if (updateParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        DriveFlowEntity driveFlow = BeanConvertUtils.copy(updateParam, DriveFlowEntity.class);
        Boolean result = driveFlowService.updateById(driveFlow);
        log.info(this.getClass() + "update-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.DATA_UPDATE_SUCCESS.subCode(),SubResultCode.DATA_UPDATE_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_UPDATE_FAILL.subCode(),SubResultCode.DATA_UPDATE_FAILL.subMsg());
    }

    /**
     * *删除流程信息管理 信息
     **/
    @Override
    public ResObject deleteByIds(String[] ids) {
        if (ids.length <= 0){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        return R.toRes(driveFlowService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * *通过id删除流程信息管理 信息
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
        Boolean result = driveFlowService.removeById(id);
        log.info(this.getClass() + "deleteById-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.DATA_DELETE_SUCCESS.subCode(),SubResultCode.DATA_DELETE_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_DELETE_FAILL.subCode(),SubResultCode.DATA_DELETE_FAILL.subMsg());
    }

    /**
     * *导出流程信息管理 信息
     **/
    @Override
    public ResObject exportXls(DriveFlowPageQueryParam param, HttpServletResponse response)throws IOException {
        log.info(this.getClass() + "exportXls方法请求参数{}",param);
        QueryWrapper queryWrapper = this.getQueryWrapper(driveFlowMapStruct, param);
        List<DriveFlowEntity> list = driveFlowService.list(queryWrapper);
        List<DriveFlowVo>driveFlowList = driveFlowMapStruct.toVoList(list);
        ExcelUtils.exportExcel(driveFlowList, DriveFlowVo.class, "", new ExportParams(), response);
        return R.success(SubResultCode.EXPORT_DATA_SUCCESS.subCode(),SubResultCode.EXPORT_DATA_SUCCESS.subMsg());
    }

    /**
     * *修改状态
     **/
    @Override
    public ResObject changeStatus(DriveFlowEditParam param) {
        log.info(this.getClass() + "changeStatus方法请求参数{}",param);
        if (StrUtil.isEmpty(param.getId())){
            log.error("ID数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        DriveFlowEntity DriveFlowEntity = new DriveFlowEntity();
        DriveFlowEntity.setId(param.getId());
        DriveFlowEntity.setStatus(param.getStatus());
        //DriveFlowEntity.setUpdateTime()
        Boolean result = driveFlowService.updateById(DriveFlowEntity);
        log.info(this.getClass() +"changeStatus方法请求对象参数{}，请求结果{}",DriveFlowEntity,result);
        // 判断结果
        return result ?R.success(SubResultCode.DATA_STATUS_SUCCESS.subCode(),SubResultCode.DATA_STATUS_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_STATUS_FAILL.subCode(),SubResultCode.DATA_STATUS_FAILL.subMsg());
    }

}

