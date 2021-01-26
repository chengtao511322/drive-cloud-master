package com.drive.basics.repository.impl;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.basics.pojo.dto.BannerEditParam;
import com.drive.basics.pojo.dto.BannerPageQueryParam;
import com.drive.basics.pojo.entity.BannerEntity;
import com.drive.basics.pojo.vo.BannerVo;
import com.drive.basics.repository.BannerRepository;
import com.drive.basics.service.BannerService;
import com.drive.basics.service.mapstruct.BannerMapStruct;
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
 * banner 轮播图 服务类
 *
 * @author xiaoguo
 */
@Slf4j
@Service
public class  BannerRepositoryImpl extends BaseController<BannerPageQueryParam, BannerEntity>  implements BannerRepository{

    @Autowired
    private BannerService bannerService;
    @Autowired
    private BannerMapStruct bannerMapStruct;

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description banner 轮播图 分页列表
     * @date 2020/2/12 17:09
     * @param  * @param BannerPageQueryParam
     * @return
     */
    @Override
    public ResObject pageList(BannerPageQueryParam param) {
        log.info(this.getClass() + "pageList-方法请求参数{}",param);
        String searchName = param.getName();
        String searchTitle = param.getTitle();
        param.setName(null);
        param.setTitle(null);
        Page<BannerEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        IPage<BannerEntity> pageList = bannerService.page(page, this.getQueryWrapper(bannerMapStruct, param).like(StrUtil.isNotEmpty(searchName),"name",searchName).like(StrUtil.isNotEmpty(searchTitle),"title",searchTitle));
        Page<BannerVo> bannerVoPage = bannerMapStruct.toVoList(pageList);
        log.info(this.getClass() + "pageList-方法请求结果{}",bannerVoPage);
        return R.success(bannerVoPage);
    }

    @Override
    public ResObject findList(BannerPageQueryParam param) {
        log.info(this.getClass() + "findList-方法请求参数{}",param);
        // 这里判断条件进行查询
        QueryWrapper queryWrapper= this.getQueryWrapper(bannerMapStruct, param);
        // 如 queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"phone",param.getPhone());
        List<BannerEntity> pageList = bannerService.list(queryWrapper);
        List<BannerVo> bannerVoList = bannerMapStruct.toVoList(pageList);
        log.info(this.getClass() + "findList-方法请求结果{}",bannerVoList);
        if (bannerVoList == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        return R.success(bannerVoList);
    }

    /**
     * *通过ID获取banner 轮播图 列表
     **/
    @Override
    public ResObject getInfo(String id) {
        log.info(this.getClass() + "getInfo-方法请求参数{}",id);
        if (StrUtil.isEmpty(id)){
            return R.failure("数据空");
        }
        BannerEntity banner = bannerService.getById(id);
        BannerVo bannerVo = BeanConvertUtils.copy(banner, BannerVo.class);
        log.info(this.getClass() + "getInfo-方法请求结果{}",bannerVo);
        if (bannerVo ==null){
            log.error("活动数据对象空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        return R.success(bannerVo);
    }

    /**
     * *保存banner 轮播图 信息
     **/
    @Override
    public ResObject save(BannerEditParam installParam) {
        log.info(this.getClass() + "save方法请求参数{}",installParam);
        if (installParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        BannerEntity banner = BeanConvertUtils.copy(installParam, BannerEntity.class);
        Boolean result = bannerService.save(banner);
        log.info(this.getClass() + "save-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *修改banner 轮播图 信息
     **/
    @Override
    public ResObject update(BannerEditParam updateParam) {
        log.info(this.getClass() + "update方法请求参数{}",updateParam);
        if (updateParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        BannerEntity banner = BeanConvertUtils.copy(updateParam, BannerEntity.class);
        Boolean result = bannerService.updateById(banner);
        log.info(this.getClass() + "update-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *删除banner 轮播图 信息
     **/
    @Override
    public ResObject deleteByIds(String[] ids) {
        return R.toRes(bannerService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * *通过id删除banner 轮播图 信息
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
        Boolean result = bannerService.removeById(id);
        log.info(this.getClass() + "deleteById-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *导出banner 轮播图 信息
     **/
    @Override
    public ResObject exportXls(BannerPageQueryParam param, HttpServletResponse response) throws IOException {
        log.info(this.getClass() + "exportXls方法请求参数{}",param);
        QueryWrapper queryWrapper = this.getQueryWrapper(bannerMapStruct, param);
        List<BannerEntity> list = bannerService.list(queryWrapper);
        List<BannerVo>bannerList = bannerMapStruct.toVoList(list);
        ExcelUtils.exportExcel(bannerList, BannerVo.class, "", new ExportParams(), response);
        return R.success("导出成功");
    }

    /**
     * *修改状态
     **/
    @Override
    public ResObject changeStatus(BannerEditParam param) {

            BannerEntity BannerEntity = new BannerEntity();
            BannerEntity.setId(param.getId());
            BannerEntity.setStatus(param.getStatus());
        Boolean result = bannerService.updateById(BannerEntity);
        log.info(this.getClass() +"changeStatus方法请求对象参数{}，请求结果{}",BannerEntity,result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

}

