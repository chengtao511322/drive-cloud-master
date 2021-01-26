package com.drive.marketing.repository.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.common.core.base.BaseController;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.biz.SubResultCode;
import com.drive.common.core.utils.BeanConvertUtils;
import com.drive.marketing.pojo.dto.ActivityPromoteAuthEditParam;
import com.drive.marketing.pojo.dto.ActivityPromoteAuthPageQueryParam;
import com.drive.marketing.pojo.entity.ActivityPromoteAuthEntity;
import com.drive.marketing.pojo.vo.ActivityPromoteAuthVo;
import com.drive.marketing.repository.ActivityPromoteAuthRepository;
import com.drive.marketing.service.ActivityPromoteAuthService;
import com.drive.marketing.service.mapstruct.ActivityPromoteAuthMapStruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

            
/**
 *
 * 活动推广权限配置 服务类
 *
 * @author xiaoguo
 */
@Slf4j
@Service
public class  ActivityPromoteAuthRepositoryImpl extends BaseController<ActivityPromoteAuthPageQueryParam, ActivityPromoteAuthEntity>  implements ActivityPromoteAuthRepository{

    @Autowired
    private ActivityPromoteAuthService activityPromoteAuthService;
    @Autowired
    private ActivityPromoteAuthMapStruct activityPromoteAuthMapStruct;

    /**
     * *活动推广权限配置 分页列表
     **/
    @Override
    public ResObject pageList(ActivityPromoteAuthPageQueryParam param) {
        log.info(this.getClass() + "pageList-方法请求参数{}",param);
        Page<ActivityPromoteAuthEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        IPage<ActivityPromoteAuthEntity> pageList = activityPromoteAuthService.page(page, this.getQueryWrapper(activityPromoteAuthMapStruct, param));
        Page<ActivityPromoteAuthVo> activityPromoteAuthVoPage = activityPromoteAuthMapStruct.toVoList(pageList);
        log.info(this.getClass() + "pageList-方法请求结果{}",activityPromoteAuthVoPage);
        return R.success(activityPromoteAuthVoPage);
    }

    @Override
    public ResObject findList(ActivityPromoteAuthPageQueryParam param) {
        log.info(this.getClass() + "findList-方法请求参数{}",param);
        // 这里判断条件进行查询
        QueryWrapper queryWrapper= this.getQueryWrapper(activityPromoteAuthMapStruct, param);
        // 如 queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"phone",param.getPhone());
        List<ActivityPromoteAuthEntity> pageList = activityPromoteAuthService.list(queryWrapper);
        List<ActivityPromoteAuthVo> activityPromoteAuthVoList = activityPromoteAuthMapStruct.toVoList(pageList);
        log.info(this.getClass() + "findList-方法请求结果{}",activityPromoteAuthVoList);
        if (activityPromoteAuthVoList == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        return R.success(activityPromoteAuthVoList);
    }

    /**
     * *通过ID获取活动推广权限配置 列表
     **/
    @Override
    public ResObject getInfo(String id) {
        log.info(this.getClass() + "getInfo-方法请求参数{}",id);
        if (StrUtil.isEmpty(id)){
            return R.failure("数据空");
        }
            ActivityPromoteAuthEntity activityPromoteAuth = activityPromoteAuthService.getById(id);
            ActivityPromoteAuthVo activityPromoteAuthVo = BeanConvertUtils.copy(activityPromoteAuth, ActivityPromoteAuthVo.class);
        log.info(this.getClass() + "getInfo-方法请求结果{}",activityPromoteAuthVo);
        if (activityPromoteAuthVo ==null){
            log.error("活动数据对象空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        return R.success(activityPromoteAuthVo);
    }

    /**
     * *保存活动推广权限配置 信息
     **/
    @Override
    public ResObject save(ActivityPromoteAuthEditParam installParam) {

        log.info(this.getClass() + "save方法请求参数{}",installParam);
        if (installParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
            ActivityPromoteAuthEntity activityPromoteAuth = BeanConvertUtils.copy(installParam, ActivityPromoteAuthEntity.class);
        Boolean result = activityPromoteAuthService.save(activityPromoteAuth);
        log.info(this.getClass() + "save-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *修改活动推广权限配置 信息
     **/
    @Override
    public ResObject update(ActivityPromoteAuthEditParam updateParam) {
        log.info(this.getClass() + "update方法请求参数{}",updateParam);
        if (updateParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
            ActivityPromoteAuthEntity activityPromoteAuth = BeanConvertUtils.copy(updateParam, ActivityPromoteAuthEntity.class);
        Boolean result = activityPromoteAuthService.updateById(activityPromoteAuth);
        log.info(this.getClass() + "update-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *删除活动推广权限配置 信息
     **/
    @Override
    public ResObject deleteByIds(String[] ids) {
        return R.toRes(activityPromoteAuthService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * *通过id删除活动推广权限配置 信息
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
        Boolean result = activityPromoteAuthService.removeById(id);
        log.info(this.getClass() + "deleteById-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *导出活动推广权限配置 信息
     **/
    @Override
    public ResObject exportXls(ActivityPromoteAuthPageQueryParam param, HttpServletResponse response) {
        return null;
    }

    /**
     * *修改状态
     **/
    @Override
    public ResObject changeStatus(ActivityPromoteAuthEditParam param) {

            ActivityPromoteAuthEntity ActivityPromoteAuthEntity = new ActivityPromoteAuthEntity();
           //ActivityPromoteAuthEntity.setId(param.getId());
            //ActivityPromoteAuthEntity.setStatus(param.getStatus());
        Boolean result = activityPromoteAuthService.updateById(ActivityPromoteAuthEntity);
        log.info(this.getClass() +"changeStatus方法请求对象参数{}，请求结果{}",ActivityPromoteAuthEntity,result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

}

