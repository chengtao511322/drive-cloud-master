package com.drive.admin.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.drive.admin.enums.StatusEnum;
import com.drive.admin.pojo.entity.OneFeeSystemUpgradeClassPriceEntity;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.drive.admin.service.OneFeeSystemPriceService;
import com.drive.common.core.base.BaseController;
import com.drive.admin.repository.OneFeeSystemUpgradeClassPriceRepository;
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
import com.drive.admin.service.OneFeeSystemUpgradeClassPriceService;
import com.drive.common.data.utils.ExcelUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.io.IOException;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

                                                            
/**
 *
 * 一费制学员升班价格控制表 服务类
 *
 * @author xiaoguo
 */
@Slf4j
@Service
public class  OneFeeSystemUpgradeClassPriceRepositoryImpl extends BaseController<OneFeeSystemUpgradeClassPricePageQueryParam, OneFeeSystemUpgradeClassPriceEntity>  implements OneFeeSystemUpgradeClassPriceRepository{

    //  一费制学员升班价格控制表 服务
    @Autowired
    private OneFeeSystemUpgradeClassPriceService oneFeeSystemUpgradeClassPriceService;
    //  一费制学员升班价格控制表 DO-DTO转化
    @Autowired
    private OneFeeSystemUpgradeClassPriceMapStruct oneFeeSystemUpgradeClassPriceMapStruct;

    @Autowired
    private OneFeeSystemPriceService oneFeeSystemPriceService;
    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 一费制学员升班价格控制表 分页列表
     * @date 2020/2/12 17:09
     * @param  * @param OneFeeSystemUpgradeClassPricePageQueryParam
     * @return
     */
    @Override
    public ResObject pageList(OneFeeSystemUpgradeClassPricePageQueryParam param) {
        log.info(this.getClass() + "pageList-方法请求参数{}",param);
        Page<OneFeeSystemUpgradeClassPriceEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        // 条件查询
        QueryWrapper queryWrapper = this.getQueryWrapper(oneFeeSystemUpgradeClassPriceMapStruct, param);


        // 原班型查询
        if (StrUtil.isNotEmpty(param.getVagueOriginalClassNameSearch())){
            List<OneFeeSystemPriceEntity> oneFeeSystemPriceList = new ArrayList<>();
            QueryWrapper studentQueryWrapper = new QueryWrapper();
            studentQueryWrapper.like(StrUtil.isNotEmpty(param.getVagueOriginalClassNameSearch()),"name",param.getVagueOriginalClassNameSearch());
            oneFeeSystemPriceList = oneFeeSystemPriceService.list(studentQueryWrapper);
            if(oneFeeSystemPriceList.size() <= 0)return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),oneFeeSystemPriceList);
            queryWrapper.in("original_class_id",oneFeeSystemPriceList.stream().map(OneFeeSystemPriceEntity::getId).collect(Collectors.toList()));
        }

        // 原班型查询
        if (StrUtil.isNotEmpty(param.getVagueUpgradeClassNameSearch())){
            List<OneFeeSystemPriceEntity> oneFeeSystemPriceList = new ArrayList<>();
            QueryWrapper studentQueryWrapper = new QueryWrapper();
            studentQueryWrapper.like(StrUtil.isNotEmpty(param.getVagueUpgradeClassNameSearch()),"name",param.getVagueUpgradeClassNameSearch());
            oneFeeSystemPriceList = oneFeeSystemPriceService.list(studentQueryWrapper);
            if(oneFeeSystemPriceList.size() <= 0)return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),oneFeeSystemPriceList);
            queryWrapper.in("upgrade_class_id",oneFeeSystemPriceList.stream().map(OneFeeSystemPriceEntity::getId).collect(Collectors.toList()));
        }

        if (param.getCreateDateTimeSearchArr() != null && param.getCreateDateTimeSearchArr().length > 0 ){
            queryWrapper.between("create_time",param.getCreateDateTimeSearchArr()[0],param.getCreateDateTimeSearchArr()[1]);
        }

        //  模糊查询
        //queryWrapper.like(StrUtil.isNotEmpty(param.getVagueNameSearch()),"name",param.getVagueNameSearch());
        //  开始时间 结束时间都有才进入
        if (StrUtil.isNotEmpty(param.getBeginTime()) && StrUtil.isNotEmpty(param.getEndTime())){
            queryWrapper.between(StrUtil.isNotEmpty(param.getBeginTime()),"create_time",param.getBeginTime(),param.getEndTime());
        }
        IPage<OneFeeSystemUpgradeClassPriceEntity> pageList = oneFeeSystemUpgradeClassPriceService.page(page, queryWrapper);
        if (pageList.getRecords().size() <= 0){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }
        Page<OneFeeSystemUpgradeClassPriceVo> oneFeeSystemUpgradeClassPriceVoPage = oneFeeSystemUpgradeClassPriceMapStruct.toVoList(pageList);

        log.info(this.getClass() + "pageList-方法请求结果{}",oneFeeSystemUpgradeClassPriceVoPage);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),oneFeeSystemUpgradeClassPriceVoPage);
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 一费制学员升班价格控制表 查询列表
     * @date 2020/2/12 17:09
     * @param  * @param OneFeeSystemUpgradeClassPricePageQueryParam
     * @return
     */
    @Override
    public ResObject findList(OneFeeSystemUpgradeClassPricePageQueryParam param) {
        log.info(this.getClass() + "findList-方法请求参数{}",param);
        // 这里判断条件进行查询
        QueryWrapper queryWrapper= this.getQueryWrapper(oneFeeSystemUpgradeClassPriceMapStruct, param);
        // 如 queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"phone",param.getPhone());
        List<OneFeeSystemUpgradeClassPriceEntity> oneFeeSystemUpgradeClassPriceList = oneFeeSystemUpgradeClassPriceService.list(queryWrapper);
        if (oneFeeSystemUpgradeClassPriceList == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),oneFeeSystemUpgradeClassPriceList);
        }
        List<OneFeeSystemUpgradeClassPriceVo> oneFeeSystemUpgradeClassPriceVoList = oneFeeSystemUpgradeClassPriceMapStruct.toVoList(oneFeeSystemUpgradeClassPriceList);
        log.info(this.getClass() + "findList-方法请求结果{}",oneFeeSystemUpgradeClassPriceVoList);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),oneFeeSystemUpgradeClassPriceVoList);
    }

    /**
    * 对象条件查询返回单条一费制学员升班价格控制表数据
    * @param param
    * @return
    */
    @Override
    public ResObject getInfo(OneFeeSystemUpgradeClassPricePageQueryParam param) {
        log.info(this.getClass() + "getInfo-方法请求参数{}",param);
        if (param == null){
            return R.failure("数据空");
        }
        // 这里判断条件进行查询
        QueryWrapper queryWrapper = this.getQueryWrapper(oneFeeSystemUpgradeClassPriceMapStruct, param);
        OneFeeSystemUpgradeClassPriceEntity oneFeeSystemUpgradeClassPrice = oneFeeSystemUpgradeClassPriceService.getOne(queryWrapper);
        if (oneFeeSystemUpgradeClassPrice == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),oneFeeSystemUpgradeClassPrice);
        }
        OneFeeSystemUpgradeClassPriceVo oneFeeSystemUpgradeClassPriceVo = BeanConvertUtils.copy(oneFeeSystemUpgradeClassPrice, OneFeeSystemUpgradeClassPriceVo.class);
        log.info(this.getClass() + "getInfo-方法请求结果{}",oneFeeSystemUpgradeClassPriceVo);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),oneFeeSystemUpgradeClassPriceVo);
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 通过ID获取 一费制学员升班价格控制表 单条数据
     * @date 2020/2/12 17:09
     * @param  * @param OneFeeSystemUpgradeClassPricePageQueryParam
     * @return
     */
    @Override
    public ResObject getById(String id) {
        log.info(this.getClass() + "getById-方法请求参数{}",id);
        if (StrUtil.isEmpty(id)){
            return R.failure("数据空");
        }
        // 通过ID 获取 单条数据
        OneFeeSystemUpgradeClassPriceEntity oneFeeSystemUpgradeClassPrice = oneFeeSystemUpgradeClassPriceService.getById(id);
        if (oneFeeSystemUpgradeClassPrice == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),oneFeeSystemUpgradeClassPrice);
        }
        OneFeeSystemUpgradeClassPriceVo oneFeeSystemUpgradeClassPriceVo = BeanConvertUtils.copy(oneFeeSystemUpgradeClassPrice, OneFeeSystemUpgradeClassPriceVo.class);
        log.info(this.getClass() + "getById-方法请求结果{}",oneFeeSystemUpgradeClassPriceVo);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),oneFeeSystemUpgradeClassPriceVo);
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 保存一费制学员升班价格控制表 数据
     * @date 2020/2/12 17:09
     * @param  * @param OneFeeSystemUpgradeClassPricePageQueryParam
     * @return
     */
    @Override
    public ResObject save(OneFeeSystemUpgradeClassPriceInstallParam installParam) {
        log.info(this.getClass() + "save方法请求参数{}",installParam);
        if (installParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        // 幂等性查询
        QueryWrapper queryWrapper = new QueryWrapper();
        // 学员id
        queryWrapper.eq(StrUtil.isNotEmpty(installParam.getOriginalClassId()),"original_class_id",installParam.getOriginalClassId());
        queryWrapper.eq(StrUtil.isNotEmpty(installParam.getUpgradeClassId()),"upgrade_class_id",installParam.getUpgradeClassId());
        queryWrapper.eq(StrUtil.isNotEmpty(installParam.getOperatorId()),"operator_id",installParam.getOperatorId());
        queryWrapper.last("limit 1");
        OneFeeSystemUpgradeClassPriceEntity isOneFeeSystemUpgradeClassPrice = oneFeeSystemUpgradeClassPriceService.getOne(queryWrapper);
        if (isOneFeeSystemUpgradeClassPrice != null){
            return R.failure(SubResultCode.DATA_IDEMPOTENT.subCode(),SubResultCode.DATA_IDEMPOTENT.subMsg());
        }
        OneFeeSystemUpgradeClassPriceEntity oneFeeSystemUpgradeClassPrice = BeanConvertUtils.copy(installParam, OneFeeSystemUpgradeClassPriceEntity.class);
        // 待审
        oneFeeSystemUpgradeClassPrice.setStatus(StatusEnum.ENABLE.getCode());
        Boolean result = oneFeeSystemUpgradeClassPriceService.save(oneFeeSystemUpgradeClassPrice);
        log.info(this.getClass() + "save-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_INSTALL_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_INSTALL_FAILL.subCode(),SubResultCode.DATA_INSTALL_FAILL.subMsg());
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description  修改一费制学员升班价格控制表 数据
     * @date 2020/2/12 17:09
     * @param  * @param OneFeeSystemUpgradeClassPricePageQueryParam
     * @return
     */
    @Override
    public ResObject update(OneFeeSystemUpgradeClassPriceEditParam updateParam) {
        log.info(this.getClass() + "update方法请求参数{}",updateParam);
        if (updateParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }

       /* // 幂等性查询
        QueryWrapper queryWrapper = new QueryWrapper();
        // 学员id
        queryWrapper.eq(StrUtil.isNotEmpty(updateParam.getOriginalClassId()),"original_class_id",updateParam.getOriginalClassId());
        queryWrapper.eq(StrUtil.isNotEmpty(updateParam.getUpgradeClassId()),"upgrade_class_id",updateParam.getUpgradeClassId());
        queryWrapper.eq(StrUtil.isNotEmpty(updateParam.getOperatorId()),"operator_id",updateParam.getOperatorId());
        queryWrapper.last("limit 1");
        OneFeeSystemUpgradeClassPriceEntity isOneFeeSystemUpgradeClassPrice = oneFeeSystemUpgradeClassPriceService.getOne(queryWrapper);
        if (isOneFeeSystemUpgradeClassPrice != null){
            return R.failure(SubResultCode.DATA_IDEMPOTENT.subCode(),SubResultCode.DATA_IDEMPOTENT.subMsg());
        }*/

        OneFeeSystemUpgradeClassPriceEntity oneFeeSystemUpgradeClassPrice = BeanConvertUtils.copy(updateParam, OneFeeSystemUpgradeClassPriceEntity.class);
        Boolean result = oneFeeSystemUpgradeClassPriceService.updateById(oneFeeSystemUpgradeClassPrice);
        log.info(this.getClass() + "update-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_UPDATE_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_UPDATE_FAILL.subCode(),SubResultCode.DATA_UPDATE_FAILL.subMsg());
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 数组删除一费制学员升班价格控制表 数据
     * @date 2020/2/12 17:09
     * @param  * @param OneFeeSystemUpgradeClassPricePageQueryParam
     * @return
     */
    @Override
    public ResObject deleteByIds(String[] ids) {
        if (ids.length <= 0){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        return R.toRes(oneFeeSystemUpgradeClassPriceService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * *通过id删除一费制学员升班价格控制表 信息
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
        Boolean result = oneFeeSystemUpgradeClassPriceService.removeById(id);
        log.info(this.getClass() + "deleteById-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_DELETE_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_DELETE_FAILL.subCode(),SubResultCode.DATA_DELETE_FAILL.subMsg());
    }

    /**
     * *导出一费制学员升班价格控制表 信息
     **/
    @Override
    public ResObject exportXls(OneFeeSystemUpgradeClassPricePageQueryParam param, HttpServletResponse response)throws IOException {
        log.info(this.getClass() + "exportXls方法请求参数{}",param);
        QueryWrapper queryWrapper = this.getQueryWrapper(oneFeeSystemUpgradeClassPriceMapStruct, param);
        List<OneFeeSystemUpgradeClassPriceEntity> list = oneFeeSystemUpgradeClassPriceService.list(queryWrapper);
        List<OneFeeSystemUpgradeClassPriceVo>oneFeeSystemUpgradeClassPriceList = oneFeeSystemUpgradeClassPriceMapStruct.toVoList(list);
        ExcelUtils.exportExcel(oneFeeSystemUpgradeClassPriceList, OneFeeSystemUpgradeClassPriceVo.class, "", new ExportParams(), response);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.EXPORT_DATA_SUCCESS.subMsg());
    }

    /**
     * *修改状态
     **/
    @Override
    public ResObject changeStatus(OneFeeSystemUpgradeClassPriceEditParam param) {
        log.info(this.getClass() + "changeStatus方法请求参数{}",param);
        if (StrUtil.isEmpty(param.getId())){
            log.error("ID数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        OneFeeSystemUpgradeClassPriceEntity OneFeeSystemUpgradeClassPriceEntity = new OneFeeSystemUpgradeClassPriceEntity();
        OneFeeSystemUpgradeClassPriceEntity.setId(param.getId());
        OneFeeSystemUpgradeClassPriceEntity.setStatus(param.getStatus());
        //OneFeeSystemUpgradeClassPriceEntity.setUpdateTime()
        Boolean result = oneFeeSystemUpgradeClassPriceService.updateById(OneFeeSystemUpgradeClassPriceEntity);
        log.info(this.getClass() +"changeStatus方法请求对象参数{}，请求结果{}",OneFeeSystemUpgradeClassPriceEntity,result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_STATUS_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_STATUS_FAILL.subCode(),SubResultCode.DATA_STATUS_FAILL.subMsg());
    }

}

