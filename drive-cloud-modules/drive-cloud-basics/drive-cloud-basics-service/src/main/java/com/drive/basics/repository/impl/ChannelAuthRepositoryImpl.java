package com.drive.basics.repository.impl;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.basics.pojo.dto.ChannelAuthEditParam;
import com.drive.basics.pojo.dto.ChannelAuthInstallParam;
import com.drive.basics.pojo.dto.ChannelAuthPageQueryParam;
import com.drive.basics.pojo.entity.ChannelAuthEntity;
import com.drive.basics.pojo.vo.ChannelAuthVo;
import com.drive.basics.repository.ChannelAuthRepository;
import com.drive.basics.service.ChannelAuthService;
import com.drive.basics.service.mapstruct.ChannelAuthMapStruct;
import com.drive.common.core.base.BaseController;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.biz.SubResultCode;
import com.drive.common.core.utils.BeanConvertUtils;
import com.drive.common.data.utils.ExcelUtils;
import com.drive.common.security.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


/**
 *
 * 菜单 按钮 用户拥有权限管理 服务类
 *
 * @author xiaoguo
 */
@Slf4j
@Service
public class  ChannelAuthRepositoryImpl extends BaseController<ChannelAuthPageQueryParam, ChannelAuthEntity>  implements ChannelAuthRepository{

    //  菜单 按钮 用户拥有权限管理 服务
    @Autowired
    private ChannelAuthService channelAuthService;
    //  菜单 按钮 用户拥有权限管理 DO-DTO转化
    @Autowired
    private ChannelAuthMapStruct channelAuthMapStruct;

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 菜单 按钮 用户拥有权限管理 分页列表
     * @date 2020/2/12 17:09
     * @param  * @param ChannelAuthPageQueryParam
     * @return
     */
    @Override
    public ResObject pageList(ChannelAuthPageQueryParam param) {
        log.info(this.getClass() + "pageList-方法请求参数{}",param);
        Page<ChannelAuthEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        // 条件查询
        QueryWrapper queryWrapper = this.getQueryWrapper(channelAuthMapStruct, param);

        //  模糊查询
        //queryWrapper.like(StrUtil.isNotEmpty(param.getVagueNameSearch()),"name",param.getVagueNameSearch());
        //  开始时间 结束时间都有才进入
        if (StrUtil.isNotEmpty(param.getBeginTime()) && StrUtil.isNotEmpty(param.getEndTime())){
            queryWrapper.between(StrUtil.isNotEmpty(param.getBeginTime()),"create_time",param.getBeginTime(),param.getEndTime());
        }
        IPage<ChannelAuthEntity> pageList = channelAuthService.page(page, queryWrapper);
        if (pageList.getRecords().size() <= 0){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }
        Page<ChannelAuthVo> channelAuthVoPage = channelAuthMapStruct.toVoList(pageList);
        log.info(this.getClass() + "pageList-方法请求结果{}",channelAuthVoPage);
        return R.success(channelAuthVoPage);
    }

    @Override
    public ResObject findList(ChannelAuthPageQueryParam param) {
        log.info(this.getClass() + "findList-方法请求参数{}",param);
        // 这里判断条件进行查询
        QueryWrapper queryWrapper= this.getQueryWrapper(channelAuthMapStruct, param);
        // 如 queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"phone",param.getPhone());
        List<ChannelAuthEntity> channelAuthList = channelAuthService.list(queryWrapper);
        if (channelAuthList == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),channelAuthList);
        }
        List<ChannelAuthVo> channelAuthVoList = channelAuthMapStruct.toVoList(channelAuthList);
        log.info(this.getClass() + "findList-方法请求结果{}",channelAuthVoList);
        return R.success(channelAuthVoList);
    }

    /**
    * 条件查询返回单条菜单 按钮 用户拥有权限管理数据
    * @param param
    * @return
    */
    @Override
    public ResObject getInfo(ChannelAuthPageQueryParam param) {
        log.info(this.getClass() + "getInfo-方法请求参数{}",param);
        if (param == null){
            return R.failure("数据空");
        }
        // 这里判断条件进行查询
        QueryWrapper queryWrapper = this.getQueryWrapper(channelAuthMapStruct, param);
        ChannelAuthEntity channelAuth = channelAuthService.getOne(queryWrapper);
        if (channelAuth == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),channelAuth);
        }
        ChannelAuthVo channelAuthVo = BeanConvertUtils.copy(channelAuth, ChannelAuthVo.class);
        log.info(this.getClass() + "getInfo-方法请求结果{}",channelAuthVo);
        return R.success(channelAuthVo);
    }

    /**
     * *通过ID获取菜单 按钮 用户拥有权限管理 列表
     **/
    @Override
    public ResObject getById(String id) {
        log.info(this.getClass() + "getById-方法请求参数{}",id);
        if (StrUtil.isEmpty(id)){
            return R.failure("数据空");
        }
        ChannelAuthEntity channelAuth = channelAuthService.getById(id);
        if (channelAuth == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),channelAuth);
        }
        ChannelAuthVo channelAuthVo = BeanConvertUtils.copy(channelAuth, ChannelAuthVo.class);
        log.info(this.getClass() + "getById-方法请求结果{}",channelAuthVo);
        return R.success(channelAuthVo);
    }

    /**
     * *保存菜单 按钮 用户拥有权限管理 信息
     **/
    @Override
    public ResObject save(ChannelAuthInstallParam installParam) {
        log.info(this.getClass() + "save方法请求参数{}",installParam);
        if (installParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        ChannelAuthEntity channelAuth = BeanConvertUtils.copy(installParam, ChannelAuthEntity.class);
        Boolean result = channelAuthService.save(channelAuth);
        log.info(this.getClass() + "save-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *修改菜单 按钮 用户拥有权限管理 信息
     **/
    @Override
    public ResObject update(ChannelAuthEditParam updateParam) {
        log.info(this.getClass() + "update方法请求参数{}",updateParam);
        if (updateParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        ChannelAuthEntity channelAuth = BeanConvertUtils.copy(updateParam, ChannelAuthEntity.class);
        Boolean result = channelAuthService.updateById(channelAuth);
        log.info(this.getClass() + "update-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *删除菜单 按钮 用户拥有权限管理 信息
     **/
    @Override
    public ResObject deleteByIds(String[] ids) {
        if (ids.length <= 0){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        return R.toRes(channelAuthService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * *通过id删除菜单 按钮 用户拥有权限管理 信息
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
        Boolean result = channelAuthService.removeById(id);
        log.info(this.getClass() + "deleteById-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *导出菜单 按钮 用户拥有权限管理 信息
     **/
    @Override
    public ResObject exportXls(ChannelAuthPageQueryParam param, HttpServletResponse response)throws IOException {
        log.info(this.getClass() + "exportXls方法请求参数{}",param);
        QueryWrapper queryWrapper = this.getQueryWrapper(channelAuthMapStruct, param);
        List<ChannelAuthEntity> list = channelAuthService.list(queryWrapper);
        List<ChannelAuthVo>channelAuthList = channelAuthMapStruct.toVoList(list);
        ExcelUtils.exportExcel(channelAuthList, ChannelAuthVo.class, "", new ExportParams(), response);
        return R.success("导出成功");
    }

    /**
     * *修改状态
     **/
    @Override
    public ResObject changeStatus(ChannelAuthEditParam param) {
        log.info(this.getClass() + "changeStatus方法请求参数{}",param);

        ChannelAuthEntity ChannelAuthEntity = new ChannelAuthEntity();
        //ChannelAuthEntity.setId(param.getId());
        //ChannelAuthEntity.setStatus(param.getStatus());
        //ChannelAuthEntity.setUpdateTime()
        Boolean result = channelAuthService.updateById(ChannelAuthEntity);
        log.info(this.getClass() +"changeStatus方法请求对象参数{}，请求结果{}",ChannelAuthEntity,result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    @Override
    @Transactional
    public ResObject updateChannelAuth(ChannelAuthEditParam channelAuthEditParam) {
        log.info(this.getClass() + "updateChannelAuth-方法请求参数{}",channelAuthEditParam);
       /* // 删除
        QueryWrapper queryWrapper = new QueryWrapper();
        // 用户
        queryWrapper.eq("user_id",channelAuthEditParam.getUserId());
        // 菜单
        queryWrapper.eq("channel_id",channelAuthEditParam.getChannelId());
        // 运营商
        queryWrapper.eq("tenant_id",channelAuthEditParam.getTenantId());*/
        // 先删除数据
        Boolean del = channelAuthService.deleteCondition(channelAuthEditParam);
        log.info("删除数据{}",del);
        // 转化 do 对象
        ChannelAuthEntity channelAuthEntity = BeanConvertUtils.copy(channelAuthEditParam,ChannelAuthEntity.class);
        // 用户
        channelAuthEntity.setCreateUser(SecurityUtils.getUsername());
        // 添加数据
        Boolean result = channelAuthService.saveOrUpdate(channelAuthEntity);
        // 结果判断
        return result ? R.success(result) : R.failure(SubResultCode.DATA_INSTALL_FAILL.subCode(),SubResultCode.DATA_INSTALL_FAILL.subMsg());
    }

    @Override
    @Transactional
    public ResObject copyChannelAuth(ChannelAuthEditParam channelAuthEditParam) {
        log.info(this.getClass() + "copyChannelAuth-方法请求参数{}",channelAuthEditParam);
        if (StrUtil.isEmpty(channelAuthEditParam.getUserId())){
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        if (StrUtil.isEmpty(channelAuthEditParam.getTenantId())){
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        // 先查询出来用户权限
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id",channelAuthEditParam.getUserId());
        queryWrapper.eq("tenant_id",channelAuthEditParam.getTenantId());
        ChannelAuthEntity channelAuthEntity = channelAuthService.getOne(queryWrapper);
        // 数据空
        if (channelAuthEntity == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        ChannelAuthEditParam channelAuthEdit = new ChannelAuthEditParam();
        // 用户ID
        channelAuthEdit.setUserId(channelAuthEditParam.getNewUserId());
        // 运营商DI
        channelAuthEdit.setTenantId(channelAuthEditParam.getTenantId());
        // 菜单ID
        channelAuthEdit.setChannelId(channelAuthEntity.getChannelId());
        // 删除
        Boolean del = channelAuthService.deleteCondition(channelAuthEdit);
        log.info("删除数据{}",del);
        // 转化 do 对象
        ChannelAuthEntity channelAuthDo = new ChannelAuthEntity();
        // 用户
        channelAuthDo.setCreateUser(SecurityUtils.getUsername());
        channelAuthDo.setUserId(channelAuthEditParam.getNewUserId());
        channelAuthDo.setTenantId(channelAuthEditParam.getTenantId());
        channelAuthDo.setChannelId(channelAuthEntity.getChannelId());
        // 添加数据
        Boolean result = channelAuthService.saveOrUpdate(channelAuthDo);
        // 结果判断
        return result ? R.success(result) : R.failure(SubResultCode.DATA_INSTALL_FAILL.subCode(),SubResultCode.DATA_INSTALL_FAILL.subMsg());
    }
}

