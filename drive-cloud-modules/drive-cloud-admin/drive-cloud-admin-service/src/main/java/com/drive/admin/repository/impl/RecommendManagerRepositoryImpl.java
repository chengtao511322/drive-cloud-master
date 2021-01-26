package com.drive.admin.repository.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.admin.pojo.dto.RecommendManagerEditParam;
import com.drive.admin.pojo.dto.RecommendManagerPageQueryParam;
import com.drive.admin.pojo.entity.RecommendManagerEntity;
import com.drive.admin.pojo.entity.StudentInfoEntity;
import com.drive.admin.pojo.vo.RecommendManagerVo;
import com.drive.admin.repository.RecommendManagerRepository;
import com.drive.admin.service.RecommendManagerService;
import com.drive.admin.service.StudentInfoService;
import com.drive.admin.service.mapstruct.RecommendManagerMapStruct;
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
 * 推广渠道经理 服务类
 *
 * @author xiaoguo
 */
@Slf4j
@Service
public class  RecommendManagerRepositoryImpl extends BaseController<RecommendManagerPageQueryParam, RecommendManagerEntity>  implements RecommendManagerRepository{

    @Autowired
    private RecommendManagerService recommendManagerService;
    @Autowired
    private RecommendManagerMapStruct recommendManagerMapStruct;

    @Autowired
    private StudentInfoService studentInfoService;

    /**
    * *推广渠道经理 分页列表
    **/
    @Override
    public ResObject pageList(RecommendManagerPageQueryParam param) {
        log.info(this.getClass() + "pageList-方法请求参数{}",param);
        Page<RecommendManagerEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        IPage<RecommendManagerEntity> pageList = recommendManagerService.page(page, this.getQueryWrapper(recommendManagerMapStruct, param));
        Page<RecommendManagerVo> recommendManagerVoPage = recommendManagerMapStruct.toVoList(pageList);
        log.info(this.getClass() + "pageList-方法请求结果{}",recommendManagerVoPage);
        return R.success(recommendManagerVoPage);
    }

    @Override
    public ResObject findList(RecommendManagerPageQueryParam param) {
        log.info(this.getClass() + "findList-方法请求参数{}",param);
        // 这里判断条件进行查询
        QueryWrapper queryWrapper= this.getQueryWrapper(recommendManagerMapStruct, param);
        // 如 queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"phone",param.getPhone());
        List<RecommendManagerEntity> pageList = recommendManagerService.list(queryWrapper);
        List<RecommendManagerVo> recommendManagerVoList = recommendManagerMapStruct.toVoList(pageList);
        log.info(this.getClass() + "findList-方法请求结果{}",recommendManagerVoList);
        if (recommendManagerVoList == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        return R.success(recommendManagerVoList);
    }

    /**
     * *通过ID获取推广渠道经理 列表
     **/
    @Override
    public ResObject getInfo(String id) {
        log.info(this.getClass() + "getInfo-方法请求参数{}",id);
        if (StrUtil.isEmpty(id)){
            return R.failure("数据空");
        }
        RecommendManagerEntity recommendManager = recommendManagerService.getById(id);
        RecommendManagerVo recommendManagerVo = BeanConvertUtils.copy(recommendManager, RecommendManagerVo.class);
        log.info(this.getClass() + "getInfo-方法请求结果{}",recommendManagerVo);
        if (recommendManagerVo ==null){
            log.error("活动数据对象空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        // 查询手机
        StudentInfoEntity studentInfoEntity = studentInfoService.getById(recommendManagerVo.getStudentId());
        if (studentInfoEntity != null){
            // 设置手机
            recommendManagerVo.setPhone(studentInfoEntity.getPhone());
            // 设置姓名
            recommendManagerVo.setRealName(studentInfoEntity.getRealName());
        }
        return R.success(recommendManagerVo);
    }

    /**
     * *保存推广渠道经理 信息
     **/
    @Override
    public ResObject save(RecommendManagerEditParam installParam) {

        log.info(this.getClass() + "save方法请求参数{}",installParam);
        if (installParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        RecommendManagerEntity recommendManager = BeanConvertUtils.copy(installParam, RecommendManagerEntity.class);
        Boolean result = recommendManagerService.save(recommendManager);
        log.info(this.getClass() + "save-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *修改推广渠道经理 信息
     **/
    @Override
    public ResObject update(RecommendManagerEditParam updateParam) {
        log.info(this.getClass() + "update方法请求参数{}",updateParam);
        if (updateParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        RecommendManagerEntity recommendManager = BeanConvertUtils.copy(updateParam, RecommendManagerEntity.class);
        Boolean result = recommendManagerService.updateById(recommendManager);
        log.info(this.getClass() + "update-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *删除推广渠道经理 信息
     **/
    @Override
    public ResObject deleteByIds(String[] ids) {
        return R.toRes(recommendManagerService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * *通过id删除推广渠道经理 信息
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
        Boolean result = recommendManagerService.removeById(id);
        log.info(this.getClass() + "deleteById-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *导出推广渠道经理 信息
     **/
    @Override
    public ResObject exportXls(RecommendManagerPageQueryParam param, HttpServletResponse response) {
        return null;
    }

    /**
     * *修改状态
     **/
    @Override
    public ResObject changeStatus(RecommendManagerEditParam param) {

        RecommendManagerEntity RecommendManagerEntity = new RecommendManagerEntity();
        RecommendManagerEntity.setId(param.getId());
        RecommendManagerEntity.setStatus(param.getStatus());
        Boolean result = recommendManagerService.updateById(RecommendManagerEntity);
        log.info(this.getClass() +"changeStatus方法请求对象参数{}，请求结果{}",RecommendManagerEntity,result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

}

