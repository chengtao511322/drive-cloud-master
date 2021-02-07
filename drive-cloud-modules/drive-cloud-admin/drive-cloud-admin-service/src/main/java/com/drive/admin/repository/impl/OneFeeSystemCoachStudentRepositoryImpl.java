package com.drive.admin.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.drive.admin.pojo.entity.OneFeeSystemCoachStudentEntity;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.drive.common.core.base.BaseController;
import com.drive.admin.repository.OneFeeSystemCoachStudentRepository;
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
import com.drive.admin.service.OneFeeSystemCoachStudentService;
import com.drive.common.data.utils.ExcelUtils;
import java.util.List;
import java.util.Arrays;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

                                                                                                                                        
/**
 *
 * 一费制学员教练关联表 服务类
 *
 * @author xiaoguo
 */
@Slf4j
@Service
public class  OneFeeSystemCoachStudentRepositoryImpl extends BaseController<OneFeeSystemCoachStudentPageQueryParam, OneFeeSystemCoachStudentEntity>  implements OneFeeSystemCoachStudentRepository{

    @Autowired
    private OneFeeSystemCoachStudentService oneFeeSystemCoachStudentService;
    @Autowired
    private OneFeeSystemCoachStudentMapStruct oneFeeSystemCoachStudentMapStruct;

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 一费制学员教练关联表 分页列表
     * @date 2020/2/12 17:09
     * @param  * @param OneFeeSystemCoachStudentPageQueryParam
     * @return
     */
    @Override
    public ResObject pageList(OneFeeSystemCoachStudentPageQueryParam param) {
        log.info(this.getClass() + "pageList-方法请求参数{}",param);
        Page<OneFeeSystemCoachStudentEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        IPage<OneFeeSystemCoachStudentEntity> pageList = oneFeeSystemCoachStudentService.page(page, this.getQueryWrapper(oneFeeSystemCoachStudentMapStruct, param));
        Page<OneFeeSystemCoachStudentVo> oneFeeSystemCoachStudentVoPage = oneFeeSystemCoachStudentMapStruct.toVoList(pageList);
        log.info(this.getClass() + "pageList-方法请求结果{}",oneFeeSystemCoachStudentVoPage);
        return R.success(oneFeeSystemCoachStudentVoPage);
    }

    @Override
    public ResObject findList(OneFeeSystemCoachStudentPageQueryParam param) {
        log.info(this.getClass() + "findList-方法请求参数{}",param);
        // 这里判断条件进行查询
        QueryWrapper queryWrapper= this.getQueryWrapper(oneFeeSystemCoachStudentMapStruct, param);
        // 如 queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"phone",param.getPhone());
        List<OneFeeSystemCoachStudentEntity> pageList = oneFeeSystemCoachStudentService.list(queryWrapper);
        List<OneFeeSystemCoachStudentVo> oneFeeSystemCoachStudentVoList = oneFeeSystemCoachStudentMapStruct.toVoList(pageList);
        log.info(this.getClass() + "findList-方法请求结果{}",oneFeeSystemCoachStudentVoList);
        if (oneFeeSystemCoachStudentVoList == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        return R.success(oneFeeSystemCoachStudentVoList);
    }



    @Override
    public ResObject getInfo(OneFeeSystemCoachStudentPageQueryParam param) {
        return null;
    }

    /**
     * *通过ID获取一费制学员教练关联表 列表
     **/
    @Override
    public ResObject getById(String id) {
        log.info(this.getClass() + "getInfo-方法请求参数{}",id);
        if (StrUtil.isEmpty(id)){
            return R.failure("数据空");
        }
        OneFeeSystemCoachStudentEntity oneFeeSystemCoachStudent = oneFeeSystemCoachStudentService.getById(id);
        OneFeeSystemCoachStudentVo oneFeeSystemCoachStudentVo = BeanConvertUtils.copy(oneFeeSystemCoachStudent, OneFeeSystemCoachStudentVo.class);
        log.info(this.getClass() + "getInfo-方法请求结果{}",oneFeeSystemCoachStudentVo);
        if (oneFeeSystemCoachStudentVo ==null){
            log.error("活动数据对象空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        return R.success(oneFeeSystemCoachStudentVo);
    }

    /**
     * *保存一费制学员教练关联表 信息
     **/
    @Override
    public ResObject save(OneFeeSystemCoachStudentEditParam installParam) {
        log.info(this.getClass() + "save方法请求参数{}",installParam);
        if (installParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        OneFeeSystemCoachStudentEntity oneFeeSystemCoachStudent = BeanConvertUtils.copy(installParam, OneFeeSystemCoachStudentEntity.class);
        Boolean result = oneFeeSystemCoachStudentService.save(oneFeeSystemCoachStudent);
        log.info(this.getClass() + "save-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *修改一费制学员教练关联表 信息
     **/
    @Override
    public ResObject update(OneFeeSystemCoachStudentEditParam updateParam) {
        log.info(this.getClass() + "update方法请求参数{}",updateParam);
        if (updateParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        OneFeeSystemCoachStudentEntity oneFeeSystemCoachStudent = BeanConvertUtils.copy(updateParam, OneFeeSystemCoachStudentEntity.class);
        Boolean result = oneFeeSystemCoachStudentService.updateById(oneFeeSystemCoachStudent);
        log.info(this.getClass() + "update-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *删除一费制学员教练关联表 信息
     **/
    @Override
    public ResObject deleteByIds(String[] ids) {
        if (ids.length <= 0){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        return R.toRes(oneFeeSystemCoachStudentService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * *通过id删除一费制学员教练关联表 信息
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
        Boolean result = oneFeeSystemCoachStudentService.removeById(id);
        log.info(this.getClass() + "deleteById-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *导出一费制学员教练关联表 信息
     **/
    @Override
    public ResObject exportXls(OneFeeSystemCoachStudentPageQueryParam param, HttpServletResponse response)throws IOException {
        log.info(this.getClass() + "exportXls方法请求参数{}",param);
        QueryWrapper queryWrapper = this.getQueryWrapper(oneFeeSystemCoachStudentMapStruct, param);
        List<OneFeeSystemCoachStudentEntity> list = oneFeeSystemCoachStudentService.list(queryWrapper);
        List<OneFeeSystemCoachStudentVo>oneFeeSystemCoachStudentList = oneFeeSystemCoachStudentMapStruct.toVoList(list);
        ExcelUtils.exportExcel(oneFeeSystemCoachStudentList, OneFeeSystemCoachStudentVo.class, "", new ExportParams(), response);
        return R.success("导出成功");
    }

    /**
     * *修改状态
     **/
    @Override
    public ResObject changeStatus(OneFeeSystemCoachStudentEditParam param) {
        log.info(this.getClass() + "changeStatus方法请求参数{}",param);
        if (StrUtil.isEmpty(param.getId())){
            log.error("ID数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        OneFeeSystemCoachStudentEntity OneFeeSystemCoachStudentEntity = new OneFeeSystemCoachStudentEntity();
        OneFeeSystemCoachStudentEntity.setId(param.getId());
        OneFeeSystemCoachStudentEntity.setStatus(param.getStatus());
        //OneFeeSystemCoachStudentEntity.setUpdateTime()
        Boolean result = oneFeeSystemCoachStudentService.updateById(OneFeeSystemCoachStudentEntity);
        log.info(this.getClass() +"changeStatus方法请求对象参数{}，请求结果{}",OneFeeSystemCoachStudentEntity,result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

}

