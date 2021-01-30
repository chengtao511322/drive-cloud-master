package com.drive.admin.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.drive.admin.pojo.entity.RecommendUserEntity;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.drive.common.core.base.BaseController;
import com.drive.admin.repository.RecommendUserRepository;
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
import com.drive.admin.service.RecommendUserService;
import com.drive.common.data.utils.ExcelUtils;
import java.util.List;
import java.util.Arrays;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

                                                                            
/**
 *
 * 推广人员信息表 服务类
 *
 * @author xiaoguo
 */
@Slf4j
@Service
public class  RecommendUserRepositoryImpl extends BaseController<RecommendUserPageQueryParam, RecommendUserEntity>  implements RecommendUserRepository{

    @Autowired
    private RecommendUserService recommendUserService;
    @Autowired
    private RecommendUserMapStruct recommendUserMapStruct;

    /*
     *功能描述
     * @author xiaoguo
     * @description 推广人员信息表 分页列表
     * @date 2020/2/12 17:09
     * @param  * @param RecommendUserPageQueryParam
     * @return
     */
    @Override
    public ResObject pageList(RecommendUserPageQueryParam param) {
        log.info(this.getClass() + "pageList-方法请求参数{}",param);
        Page<RecommendUserEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        IPage<RecommendUserEntity> pageList = recommendUserService.page(page, this.getQueryWrapper(recommendUserMapStruct, param));
        Page<RecommendUserVo> recommendUserVoPage = recommendUserMapStruct.toVoList(pageList);
        log.info(this.getClass() + "pageList-方法请求结果{}",recommendUserVoPage);
        return R.success(recommendUserVoPage);
    }

    @Override
    public ResObject findList(RecommendUserPageQueryParam param) {
        log.info(this.getClass() + "findList-方法请求参数{}",param);
        // 这里判断条件进行查询
        QueryWrapper queryWrapper= this.getQueryWrapper(recommendUserMapStruct, param);
        // 如 queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"phone",param.getPhone());
        List<RecommendUserEntity> pageList = recommendUserService.list(queryWrapper);
        List<RecommendUserVo> recommendUserVoList = recommendUserMapStruct.toVoList(pageList);
        log.info(this.getClass() + "findList-方法请求结果{}",recommendUserVoList);
        if (recommendUserVoList == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        return R.success(recommendUserVoList);
    }

    /**
     * *通过ID获取推广人员信息表 列表
     **/
    @Override
    public ResObject getInfo(String id) {
        log.info(this.getClass() + "getInfo-方法请求参数{}",id);
        if (StrUtil.isEmpty(id)){
            return R.failure("数据空");
        }
        RecommendUserEntity recommendUser = recommendUserService.getById(id);
        RecommendUserVo recommendUserVo = BeanConvertUtils.copy(recommendUser, RecommendUserVo.class);
        log.info(this.getClass() + "getInfo-方法请求结果{}",recommendUserVo);
        if (recommendUserVo ==null){
            log.error("活动数据对象空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        return R.success(recommendUserVo);
    }

    /**
     * *保存推广人员信息表 信息
     **/
    @Override
    public ResObject save(RecommendUserEditParam installParam) {
        log.info(this.getClass() + "save方法请求参数{}",installParam);
        if (installParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        RecommendUserEntity recommendUser = BeanConvertUtils.copy(installParam, RecommendUserEntity.class);
        Boolean result = recommendUserService.save(recommendUser);
        log.info(this.getClass() + "save-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *修改推广人员信息表 信息
     **/
    @Override
    public ResObject update(RecommendUserEditParam updateParam) {
        log.info(this.getClass() + "update方法请求参数{}",updateParam);
        if (updateParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        RecommendUserEntity recommendUser = BeanConvertUtils.copy(updateParam, RecommendUserEntity.class);
        Boolean result = recommendUserService.updateById(recommendUser);
        log.info(this.getClass() + "update-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *删除推广人员信息表 信息
     **/
    @Override
    public ResObject deleteByIds(String[] ids) {
        if (ids.length <= 0){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        return R.toRes(recommendUserService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * *通过id删除推广人员信息表 信息
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
        Boolean result = recommendUserService.removeById(id);
        log.info(this.getClass() + "deleteById-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *导出推广人员信息表 信息
     **/
    @Override
    public ResObject exportXls(RecommendUserPageQueryParam param, HttpServletResponse response)throws IOException {
        log.info(this.getClass() + "exportXls方法请求参数{}",param);
        QueryWrapper queryWrapper = this.getQueryWrapper(recommendUserMapStruct, param);
        List<RecommendUserEntity> list = recommendUserService.list(queryWrapper);
        List<RecommendUserVo>recommendUserList = recommendUserMapStruct.toVoList(list);
        ExcelUtils.exportExcel(recommendUserList, RecommendUserVo.class, "", new ExportParams(), response);
        return R.success("导出成功");
    }

    /**
     * *修改状态
     **/
    @Override
    public ResObject changeStatus(RecommendUserEditParam param) {
        log.info(this.getClass() + "changeStatus方法请求参数{}",param);
        if (StrUtil.isEmpty(param.getId())){
            log.error("ID数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        RecommendUserEntity RecommendUserEntity = new RecommendUserEntity();
        RecommendUserEntity.setId(param.getId());
        RecommendUserEntity.setStatus(param.getStatus());
        //RecommendUserEntity.setUpdateTime()
        Boolean result = recommendUserService.updateById(RecommendUserEntity);
        log.info(this.getClass() +"changeStatus方法请求对象参数{}，请求结果{}",RecommendUserEntity,result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

}

