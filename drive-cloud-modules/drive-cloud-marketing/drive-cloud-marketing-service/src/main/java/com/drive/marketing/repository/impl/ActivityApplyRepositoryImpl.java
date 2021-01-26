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
import com.drive.marketing.pojo.dto.ActivityApplyEditParam;
import com.drive.marketing.pojo.dto.ActivityApplyPageQueryParam;
import com.drive.marketing.pojo.entity.ActivityApplyEntity;
import com.drive.marketing.pojo.vo.ActivityApplyVo;
import com.drive.marketing.repository.ActivityApplyRepository;
import com.drive.marketing.service.ActivityApplyService;
import com.drive.marketing.service.mapstruct.ActivityApplyMapStruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

                                                                    
/**
 *
 * 活动参加记录表 服务类
 *
 * @author xiaoguo
 */
@Slf4j
@Service
public class  ActivityApplyRepositoryImpl extends BaseController<ActivityApplyPageQueryParam, ActivityApplyEntity>  implements ActivityApplyRepository{

    @Autowired
    private ActivityApplyService activityApplyService;
    @Autowired
    private ActivityApplyMapStruct activityApplyMapStruct;

    /**
    * *活动参加记录表 分页列表
    **/
    @Override
    public ResObject pageList(ActivityApplyPageQueryParam param) {
        log.info(this.getClass() + "pageList-方法请求参数{}",param);
        Page<ActivityApplyEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        IPage<ActivityApplyEntity> pageList = activityApplyService.page(page, this.getQueryWrapper(activityApplyMapStruct, param));
        Page<ActivityApplyVo> activityApplyVoPage = activityApplyMapStruct.toVoList(pageList);
        log.info(this.getClass() + "pageList-方法请求结果{}",activityApplyVoPage);
        return R.success(activityApplyVoPage);
    }

    @Override
    public ResObject findList(ActivityApplyPageQueryParam param) {
        log.info(this.getClass() + "findList-方法请求参数{}",param);
        // 这里判断条件进行查询
        QueryWrapper queryWrapper= this.getQueryWrapper(activityApplyMapStruct, param);
        // 如 queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"phone",param.getPhone());
        List<ActivityApplyEntity> pageList = activityApplyService.list(queryWrapper);
        List<ActivityApplyVo> activityApplyVoList = activityApplyMapStruct.toVoList(pageList);
        log.info(this.getClass() + "findList-方法请求结果{}",activityApplyVoList);
        if (activityApplyVoList == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        return R.success(activityApplyVoList);
    }

    /**
     * *通过ID获取活动参加记录表 列表
     **/
    @Override
    public ResObject getInfo(String id) {
        log.info(this.getClass() + "getInfo-方法请求参数{}",id);
        if (StrUtil.isEmpty(id)){
            return R.failure("数据空");
        }
        ActivityApplyEntity activityApply = activityApplyService.getById(id);
        ActivityApplyVo activityApplyVo = BeanConvertUtils.copy(activityApply, ActivityApplyVo.class);
        log.info(this.getClass() + "getInfo-方法请求结果{}",activityApplyVo);
        if (activityApplyVo ==null){
            log.error("活动数据对象空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        return R.success(activityApplyVo);
    }

    /**
     * *保存活动参加记录表 信息
     **/
    @Override
    public ResObject save(ActivityApplyEditParam installParam) {

        log.info(this.getClass() + "save方法请求参数{}",installParam);
        if (installParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        ActivityApplyEntity activityApply = BeanConvertUtils.copy(installParam, ActivityApplyEntity.class);
        Boolean result = activityApplyService.save(activityApply);
        log.info(this.getClass() + "save-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *修改活动参加记录表 信息
     **/
    @Override
    public ResObject update(ActivityApplyEditParam updateParam) {
        log.info(this.getClass() + "update方法请求参数{}",updateParam);
        if (updateParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        ActivityApplyEntity activityApply = BeanConvertUtils.copy(updateParam, ActivityApplyEntity.class);
        Boolean result = activityApplyService.updateById(activityApply);
        log.info(this.getClass() + "update-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *删除活动参加记录表 信息
     **/
    @Override
    public ResObject deleteByIds(String[] ids) {
        return R.toRes(activityApplyService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * *通过id删除活动参加记录表 信息
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
        Boolean result = activityApplyService.removeById(id);
        log.info(this.getClass() + "deleteById-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *导出活动参加记录表 信息
     **/
    @Override
    public ResObject exportXls(ActivityApplyPageQueryParam param, HttpServletResponse response) {
        return null;
    }

    /**
     * *修改状态
     **/
    @Override
    public ResObject changeStatus(ActivityApplyEditParam param) {

        ActivityApplyEntity ActivityApplyEntity = new ActivityApplyEntity();
        ActivityApplyEntity.setId(param.getId());
        ActivityApplyEntity.setStatus(param.getStatus());
        Boolean result = activityApplyService.updateById(ActivityApplyEntity);
        log.info(this.getClass() +"changeStatus方法请求对象参数{}，请求结果{}",ActivityApplyEntity,result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

}

