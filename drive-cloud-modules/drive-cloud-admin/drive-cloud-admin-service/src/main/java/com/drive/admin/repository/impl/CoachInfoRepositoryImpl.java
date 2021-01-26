package com.drive.admin.repository.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.admin.pojo.dto.CoachInfoEditParam;
import com.drive.admin.pojo.dto.CoachInfoPageQueryParam;
import com.drive.admin.pojo.entity.CoachInfoEntity;
import com.drive.admin.pojo.vo.CoachInfoVo;
import com.drive.admin.repository.CoachInfoRepository;
import com.drive.admin.service.CoachInfoService;
import com.drive.admin.service.mapstruct.CoachInfoMapStruct;
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

                                                                                                                                                                                                                            
/**
 *
 * 教练信息表 服务类
 *
 * @author xiaoguo
 */
@Slf4j
@Service
public class  CoachInfoRepositoryImpl extends BaseController<CoachInfoPageQueryParam, CoachInfoEntity>  implements CoachInfoRepository{

    @Autowired
    private CoachInfoService coachInfoService;
    @Autowired
    private CoachInfoMapStruct coachInfoMapStruct;

    /**
    * *教练信息表 分页列表
    **/
    @Override
    public ResObject pageList(CoachInfoPageQueryParam param) {
        Page<CoachInfoEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        IPage<CoachInfoEntity> pageList = coachInfoService.page(page, this.getQueryWrapper(coachInfoMapStruct, param));
        Page<CoachInfoVo> coachInfoVoPage = coachInfoMapStruct.toVoList(pageList);
        return R.success(coachInfoVoPage);
    }

    @Override
    public ResObject findList(CoachInfoPageQueryParam param) {
        return null;
    }

    /**
     * *通过ID获取教练信息表 列表
     **/
    @Override
    public ResObject getInfo(String id) {
        if (StrUtil.isEmpty(id)){
            return R.failure("数据空");
        }
        CoachInfoEntity coachInfo = coachInfoService.getById(id);
        CoachInfoVo coachInfoVo = BeanConvertUtils.copy(coachInfo, CoachInfoVo.class);
        if (coachInfoVo ==null){
            log.error("活动数据对象空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        return R.success(coachInfoVo);
    }

    /**
     * *保存教练信息表 信息
     **/
    @Override
    public ResObject save(CoachInfoEditParam installParam) {
        log.info(this.getClass() + "save方法请求参数{}",installParam);
        if (installParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        CoachInfoEntity coachInfo = BeanConvertUtils.copy(installParam, CoachInfoEntity.class);
        Boolean result = coachInfoService.save(coachInfo);
        log.info(this.getClass() + "save-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *修改教练信息表 信息
     **/
    @Override
    public ResObject update(CoachInfoEditParam updateParam) {
        log.info(this.getClass() + "update方法请求参数{}",updateParam);
        if (updateParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        CoachInfoEntity coachInfo = BeanConvertUtils.copy(updateParam, CoachInfoEntity.class);
        Boolean result = coachInfoService.updateById(coachInfo);
        log.info(this.getClass() + "update-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *删除教练信息表 信息
     **/
    @Override
    public ResObject deleteByIds(String[] ids) {
        return R.toRes(coachInfoService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * *通过id删除教练信息表 信息
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
        Boolean result = coachInfoService.removeById(id);
        log.info(this.getClass() + "deleteById-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *导出教练信息表 信息
     **/
    @Override
    public ResObject exportXls(CoachInfoPageQueryParam param, HttpServletResponse response) {
        return null;
    }

    /**
     * *修改状态
     **/
    @Override
    public ResObject changeStatus(CoachInfoEditParam param) {
        return null;
    }

}

