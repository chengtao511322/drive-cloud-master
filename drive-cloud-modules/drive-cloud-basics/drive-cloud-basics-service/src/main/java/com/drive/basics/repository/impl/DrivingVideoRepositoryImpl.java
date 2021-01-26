package com.drive.basics.repository.impl;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.basics.pojo.dto.DrivingVideoEditParam;
import com.drive.basics.pojo.dto.DrivingVideoPageQueryParam;
import com.drive.basics.pojo.entity.DrivingVideoEntity;
import com.drive.basics.pojo.vo.DrivingVideoVo;
import com.drive.basics.repository.DrivingVideoRepository;
import com.drive.basics.service.DrivingVideoService;
import com.drive.basics.service.mapstruct.DrivingVideoMapStruct;
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
 * 学车视频表 服务类
 *
 * @author xiaoguo
 */
@Slf4j
@Service
public class  DrivingVideoRepositoryImpl extends BaseController<DrivingVideoPageQueryParam, DrivingVideoEntity>  implements DrivingVideoRepository{

    @Autowired
    private DrivingVideoService drivingVideoService;
    @Autowired
    private DrivingVideoMapStruct drivingVideoMapStruct;

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 学车视频表 分页列表
     * @date 2020/2/12 17:09
     * @param  * @param DrivingVideoPageQueryParam
     * @return
     */
    @Override
    public ResObject pageList(DrivingVideoPageQueryParam param) {
        log.info(this.getClass() + "pageList-方法请求参数{}",param);
        Page<DrivingVideoEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        IPage<DrivingVideoEntity> pageList = drivingVideoService.page(page, this.getQueryWrapper(drivingVideoMapStruct, param));
        Page<DrivingVideoVo> drivingVideoVoPage = drivingVideoMapStruct.toVoList(pageList);
        log.info(this.getClass() + "pageList-方法请求结果{}",drivingVideoVoPage);
        return R.success(drivingVideoVoPage);
    }

    @Override
    public ResObject findList(DrivingVideoPageQueryParam param) {
        log.info(this.getClass() + "findList-方法请求参数{}",param);
        // 这里判断条件进行查询
        QueryWrapper queryWrapper= this.getQueryWrapper(drivingVideoMapStruct, param);
        // 如 queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"phone",param.getPhone());
        List<DrivingVideoEntity> pageList = drivingVideoService.list(queryWrapper);
        List<DrivingVideoVo> drivingVideoVoList = drivingVideoMapStruct.toVoList(pageList);
        log.info(this.getClass() + "findList-方法请求结果{}",drivingVideoVoList);
        if (drivingVideoVoList == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        return R.success(drivingVideoVoList);
    }

    /**
     * *通过ID获取学车视频表 列表
     **/
    @Override
    public ResObject getInfo(String id) {
        log.info(this.getClass() + "getInfo-方法请求参数{}",id);
        if (StrUtil.isEmpty(id)){
            return R.failure("数据空");
        }
        DrivingVideoEntity drivingVideo = drivingVideoService.getById(id);
        DrivingVideoVo drivingVideoVo = BeanConvertUtils.copy(drivingVideo, DrivingVideoVo.class);
        log.info(this.getClass() + "getInfo-方法请求结果{}",drivingVideoVo);
        if (drivingVideoVo ==null){
            log.error("活动数据对象空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        return R.success(drivingVideoVo);
    }

    /**
     * *保存学车视频表 信息
     **/
    @Override
    public ResObject save(DrivingVideoEditParam installParam) {
        log.info(this.getClass() + "save方法请求参数{}",installParam);
        if (installParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        DrivingVideoEntity drivingVideo = BeanConvertUtils.copy(installParam, DrivingVideoEntity.class);
        Boolean result = drivingVideoService.save(drivingVideo);
        log.info(this.getClass() + "save-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *修改学车视频表 信息
     **/
    @Override
    public ResObject update(DrivingVideoEditParam updateParam) {
        log.info(this.getClass() + "update方法请求参数{}",updateParam);
        if (updateParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        DrivingVideoEntity drivingVideo = BeanConvertUtils.copy(updateParam, DrivingVideoEntity.class);
        Boolean result = drivingVideoService.updateById(drivingVideo);
        log.info(this.getClass() + "update-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *删除学车视频表 信息
     **/
    @Override
    public ResObject deleteByIds(String[] ids) {
        if (ids.length <= 0){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        return R.toRes(drivingVideoService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * *通过id删除学车视频表 信息
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
        Boolean result = drivingVideoService.removeById(id);
        log.info(this.getClass() + "deleteById-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *导出学车视频表 信息
     **/
    @Override
    public ResObject exportXls(DrivingVideoPageQueryParam param, HttpServletResponse response)throws IOException {
        log.info(this.getClass() + "exportXls方法请求参数{}",param);
        QueryWrapper queryWrapper = this.getQueryWrapper(drivingVideoMapStruct, param);
        List<DrivingVideoEntity> list = drivingVideoService.list(queryWrapper);
        List<DrivingVideoVo>drivingVideoList = drivingVideoMapStruct.toVoList(list);
        ExcelUtils.exportExcel(drivingVideoList, DrivingVideoVo.class, "", new ExportParams(), response);
        return R.success("导出成功");
    }

    /**
     * *修改状态
     **/
    @Override
    public ResObject changeStatus(DrivingVideoEditParam param) {
        log.info(this.getClass() + "changeStatus方法请求参数{}",param);
        if (StrUtil.isEmpty(param.getId())){
            log.error("ID数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        DrivingVideoEntity DrivingVideoEntity = new DrivingVideoEntity();
        DrivingVideoEntity.setId(param.getId());
        DrivingVideoEntity.setStatus(param.getStatus());
        //DrivingVideoEntity.setUpdateTime()
        Boolean result = drivingVideoService.updateById(DrivingVideoEntity);
        log.info(this.getClass() +"changeStatus方法请求对象参数{}，请求结果{}",DrivingVideoEntity,result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

}

