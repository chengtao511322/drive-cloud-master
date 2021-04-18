package com.drive.admin.repository.impl;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.admin.pojo.dto.PlatformWalletDetailEditParam;
import com.drive.admin.pojo.dto.PlatformWalletDetailInstallParam;
import com.drive.admin.pojo.dto.PlatformWalletDetailPageQueryParam;
import com.drive.admin.pojo.entity.PlatformWalletDetailEntity;
import com.drive.admin.pojo.vo.PlatformWalletDetailVo;
import com.drive.admin.repository.PlatformWalletDetailRepository;
import com.drive.admin.service.PlatformWalletDetailService;
import com.drive.admin.service.mapstruct.PlatformWalletDetailMapStruct;
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
 * 教练钱包表明细 服务类
 *
 * @author xiaoguo
 */
@Slf4j
@Service
public class  PlatformWalletDetailRepositoryImpl extends BaseController<PlatformWalletDetailPageQueryParam, PlatformWalletDetailEntity>  implements PlatformWalletDetailRepository{

    //  教练钱包表明细 服务
    @Autowired
    private PlatformWalletDetailService platformWalletDetailService;
    //  教练钱包表明细 DO-DTO转化
    @Autowired
    private PlatformWalletDetailMapStruct platformWalletDetailMapStruct;

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 教练钱包表明细 分页列表
     * @date 2020/2/12 17:09
     * @param  * @param PlatformWalletDetailPageQueryParam
     * @return
     */
    @Override
    public ResObject pageList(PlatformWalletDetailPageQueryParam param) {
        log.info(this.getClass() + "pageList-方法请求参数{}",param);
        Page<PlatformWalletDetailEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        // 条件查询
        QueryWrapper queryWrapper = this.getQueryWrapper(platformWalletDetailMapStruct, param);

        //  模糊查询
        //queryWrapper.like(StrUtil.isNotEmpty(param.getVagueNameSearch()),"name",param.getVagueNameSearch());
        //  开始时间 结束时间都有才进入
        if (StrUtil.isNotEmpty(param.getBeginTime()) && StrUtil.isNotEmpty(param.getEndTime())){
            queryWrapper.between(StrUtil.isNotEmpty(param.getBeginTime()),"create_time",param.getBeginTime(),param.getEndTime());
        }
        IPage<PlatformWalletDetailEntity> pageList = platformWalletDetailService.page(page, queryWrapper);
        if (pageList.getRecords().size() <= 0){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }
        Page<PlatformWalletDetailVo> platformWalletDetailVoPage = platformWalletDetailMapStruct.toVoList(pageList);
        log.info(this.getClass() + "pageList-方法请求结果{}",platformWalletDetailVoPage);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),platformWalletDetailVoPage);
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 教练钱包表明细 查询列表
     * @date 2020/2/12 17:09
     * @param  * @param PlatformWalletDetailPageQueryParam
     * @return
     */
    @Override
    public ResObject findList(PlatformWalletDetailPageQueryParam param) {
        log.info(this.getClass() + "findList-方法请求参数{}",param);
        // 这里判断条件进行查询
        QueryWrapper queryWrapper= this.getQueryWrapper(platformWalletDetailMapStruct, param);
        // 如 queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"phone",param.getPhone());
        List<PlatformWalletDetailEntity> platformWalletDetailList = platformWalletDetailService.list(queryWrapper);
        if (platformWalletDetailList == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),platformWalletDetailList);
        }
        List<PlatformWalletDetailVo> platformWalletDetailVoList = platformWalletDetailMapStruct.toVoList(platformWalletDetailList);
        log.info(this.getClass() + "findList-方法请求结果{}",platformWalletDetailVoList);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),platformWalletDetailVoList);
    }

    /**
    * 对象条件查询返回单条教练钱包表明细数据
    * @param param
    * @return
    */
    @Override
    public ResObject getInfo(PlatformWalletDetailPageQueryParam param) {
        log.info(this.getClass() + "getInfo-方法请求参数{}",param);
        if (param == null){
            return R.failure("数据空");
        }
        // 这里判断条件进行查询
        QueryWrapper queryWrapper = this.getQueryWrapper(platformWalletDetailMapStruct, param);
        PlatformWalletDetailEntity platformWalletDetail = platformWalletDetailService.getOne(queryWrapper);
        if (platformWalletDetail == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),platformWalletDetail);
        }
        PlatformWalletDetailVo platformWalletDetailVo = BeanConvertUtils.copy(platformWalletDetail, PlatformWalletDetailVo.class);
        log.info(this.getClass() + "getInfo-方法请求结果{}",platformWalletDetailVo);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),platformWalletDetailVo);
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 通过ID获取 教练钱包表明细 单条数据
     * @date 2020/2/12 17:09
     * @param  * @param PlatformWalletDetailPageQueryParam
     * @return
     */
    @Override
    public ResObject getById(String id) {
        log.info(this.getClass() + "getById-方法请求参数{}",id);
        if (StrUtil.isEmpty(id)){
            return R.failure("数据空");
        }
        // 通过ID 获取 单条数据
        PlatformWalletDetailEntity platformWalletDetail = platformWalletDetailService.getById(id);
        if (platformWalletDetail == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),platformWalletDetail);
        }
        PlatformWalletDetailVo platformWalletDetailVo = BeanConvertUtils.copy(platformWalletDetail, PlatformWalletDetailVo.class);
        log.info(this.getClass() + "getById-方法请求结果{}",platformWalletDetailVo);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),platformWalletDetailVo);
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 保存教练钱包表明细 数据
     * @date 2020/2/12 17:09
     * @param  * @param PlatformWalletDetailPageQueryParam
     * @return
     */
    @Override
    public ResObject save(PlatformWalletDetailInstallParam installParam) {
        log.info(this.getClass() + "save方法请求参数{}",installParam);
        if (installParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        PlatformWalletDetailEntity platformWalletDetail = BeanConvertUtils.copy(installParam, PlatformWalletDetailEntity.class);
        Boolean result = platformWalletDetailService.save(platformWalletDetail);
        log.info(this.getClass() + "save-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_INSTALL_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_INSTALL_FAILL.subCode(),SubResultCode.DATA_INSTALL_FAILL.subMsg());
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description  修改教练钱包表明细 数据
     * @date 2020/2/12 17:09
     * @param  * @param PlatformWalletDetailPageQueryParam
     * @return
     */
    @Override
    public ResObject update(PlatformWalletDetailEditParam updateParam) {
        log.info(this.getClass() + "update方法请求参数{}",updateParam);
        if (updateParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        PlatformWalletDetailEntity platformWalletDetail = BeanConvertUtils.copy(updateParam, PlatformWalletDetailEntity.class);
        Boolean result = platformWalletDetailService.updateById(platformWalletDetail);
        log.info(this.getClass() + "update-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_UPDATE_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_UPDATE_FAILL.subCode(),SubResultCode.DATA_UPDATE_FAILL.subMsg());
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 数组删除教练钱包表明细 数据
     * @date 2020/2/12 17:09
     * @param  * @param PlatformWalletDetailPageQueryParam
     * @return
     */
    @Override
    public ResObject deleteByIds(String[] ids) {
        if (ids.length <= 0){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        return R.toRes(platformWalletDetailService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * *通过id删除教练钱包表明细 信息
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
        Boolean result = platformWalletDetailService.removeById(id);
        log.info(this.getClass() + "deleteById-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_DELETE_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_DELETE_FAILL.subCode(),SubResultCode.DATA_DELETE_FAILL.subMsg());
    }

    /**
     * *导出教练钱包表明细 信息
     **/
    @Override
    public ResObject exportXls(PlatformWalletDetailPageQueryParam param, HttpServletResponse response)throws IOException {
        log.info(this.getClass() + "exportXls方法请求参数{}",param);
        QueryWrapper queryWrapper = this.getQueryWrapper(platformWalletDetailMapStruct, param);
        List<PlatformWalletDetailEntity> list = platformWalletDetailService.list(queryWrapper);
        List<PlatformWalletDetailVo>platformWalletDetailList = platformWalletDetailMapStruct.toVoList(list);
        ExcelUtils.exportExcel(platformWalletDetailList, PlatformWalletDetailVo.class, "", new ExportParams(), response);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.EXPORT_DATA_SUCCESS.subMsg());
    }

    /**
     * *修改状态
     **/
    @Override
    public ResObject changeStatus(PlatformWalletDetailEditParam param) {
        log.info(this.getClass() + "changeStatus方法请求参数{}",param);
        if (StrUtil.isEmpty(param.getId())){
            log.error("ID数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        PlatformWalletDetailEntity PlatformWalletDetailEntity = new PlatformWalletDetailEntity();
        PlatformWalletDetailEntity.setId(param.getId());
        PlatformWalletDetailEntity.setStatus(param.getStatus());
        //PlatformWalletDetailEntity.setUpdateTime()
        Boolean result = platformWalletDetailService.updateById(PlatformWalletDetailEntity);
        log.info(this.getClass() +"changeStatus方法请求对象参数{}，请求结果{}",PlatformWalletDetailEntity,result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_STATUS_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_STATUS_FAILL.subCode(),SubResultCode.DATA_STATUS_FAILL.subMsg());
    }

}

