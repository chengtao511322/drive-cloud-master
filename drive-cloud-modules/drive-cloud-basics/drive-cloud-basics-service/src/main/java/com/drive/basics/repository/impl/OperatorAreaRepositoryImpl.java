package com.drive.basics.repository.impl;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.basics.pojo.dto.OperatorAreaEditParam;
import com.drive.basics.pojo.dto.OperatorAreaInstallParam;
import com.drive.basics.pojo.dto.OperatorAreaPageQueryParam;
import com.drive.basics.pojo.entity.OperatorAreaEntity;
import com.drive.basics.pojo.vo.OperatorAreaVo;
import com.drive.basics.repository.OperatorAreaRepository;
import com.drive.basics.service.OperatorAreaService;
import com.drive.basics.service.mapstruct.OperatorAreaMapStruct;
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
 * 运营商代理区域 服务类
 *
 * @author xiaoguo
 */
@Slf4j
@Service
public class  OperatorAreaRepositoryImpl extends BaseController<OperatorAreaPageQueryParam, OperatorAreaEntity>  implements OperatorAreaRepository{

    //  运营商代理区域 服务
    @Autowired
    private OperatorAreaService operatorAreaService;
    //  运营商代理区域 DO-DTO转化
    @Autowired
    private OperatorAreaMapStruct operatorAreaMapStruct;

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 运营商代理区域 分页列表
     * @date 2020/2/12 17:09
     * @param  * @param OperatorAreaPageQueryParam
     * @return
     */
    @Override
    public ResObject pageList(OperatorAreaPageQueryParam param) {
        log.info(this.getClass() + "pageList-方法请求参数{}",param);
        Page<OperatorAreaEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        // 条件查询
        QueryWrapper queryWrapper = this.getQueryWrapper(operatorAreaMapStruct, param);

        //  模糊查询
        //queryWrapper.like(StrUtil.isNotEmpty(param.getVagueNameSearch()),"name",param.getVagueNameSearch());
        //  开始时间 结束时间都有才进入
        if (StrUtil.isNotEmpty(param.getBeginTime()) && StrUtil.isNotEmpty(param.getEndTime())){
            queryWrapper.between(StrUtil.isNotEmpty(param.getBeginTime()),"create_time",param.getBeginTime(),param.getEndTime());
        }
        IPage<OperatorAreaEntity> pageList = operatorAreaService.page(page, queryWrapper);
        if (pageList.getRecords().size() <= 0){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }
        Page<OperatorAreaVo> operatorAreaVoPage = operatorAreaMapStruct.toVoList(pageList);
        log.info(this.getClass() + "pageList-方法请求结果{}",operatorAreaVoPage);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),operatorAreaVoPage);
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 运营商代理区域 查询列表
     * @date 2020/2/12 17:09
     * @param  * @param OperatorAreaPageQueryParam
     * @return
     */
    @Override
    public ResObject findList(OperatorAreaPageQueryParam param) {
        log.info(this.getClass() + "findList-方法请求参数{}",param);
        // 这里判断条件进行查询
        QueryWrapper queryWrapper= this.getQueryWrapper(operatorAreaMapStruct, param);
        // 如 queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"phone",param.getPhone());
        List<OperatorAreaEntity> operatorAreaList = operatorAreaService.list(queryWrapper);
        if (operatorAreaList == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),operatorAreaList);
        }
        List<OperatorAreaVo> operatorAreaVoList = operatorAreaMapStruct.toVoList(operatorAreaList);
        log.info(this.getClass() + "findList-方法请求结果{}",operatorAreaVoList);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),operatorAreaVoList);
    }

    /**
    * 对象条件查询返回单条运营商代理区域数据
    * @param param
    * @return
    */
    @Override
    public ResObject getInfo(OperatorAreaPageQueryParam param) {
        log.info(this.getClass() + "getInfo-方法请求参数{}",param);
        if (param == null){
            return R.failure("数据空");
        }
        // 这里判断条件进行查询
        QueryWrapper queryWrapper = this.getQueryWrapper(operatorAreaMapStruct, param);
        OperatorAreaEntity operatorArea = operatorAreaService.getOne(queryWrapper);
        if (operatorArea == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),operatorArea);
        }
        OperatorAreaVo operatorAreaVo = BeanConvertUtils.copy(operatorArea, OperatorAreaVo.class);
        log.info(this.getClass() + "getInfo-方法请求结果{}",operatorAreaVo);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),operatorAreaVo);
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 通过ID获取 运营商代理区域 单条数据
     * @date 2020/2/12 17:09
     * @param  * @param OperatorAreaPageQueryParam
     * @return
     */
    @Override
    public ResObject getById(String id) {
        log.info(this.getClass() + "getById-方法请求参数{}",id);
        if (StrUtil.isEmpty(id)){
            return R.failure("数据空");
        }
        // 通过ID 获取 单条数据
        OperatorAreaEntity operatorArea = operatorAreaService.getById(id);
        if (operatorArea == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),operatorArea);
        }
        OperatorAreaVo operatorAreaVo = BeanConvertUtils.copy(operatorArea, OperatorAreaVo.class);
        log.info(this.getClass() + "getById-方法请求结果{}",operatorAreaVo);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),operatorAreaVo);
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 保存运营商代理区域 数据
     * @date 2020/2/12 17:09
     * @param  * @param OperatorAreaPageQueryParam
     * @return
     */
    @Override
    public ResObject save(OperatorAreaInstallParam installParam) {
        log.info(this.getClass() + "save方法请求参数{}",installParam);
        if (installParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        OperatorAreaEntity operatorArea = BeanConvertUtils.copy(installParam, OperatorAreaEntity.class);
        Boolean result = operatorAreaService.save(operatorArea);
        log.info(this.getClass() + "save-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_INSTALL_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_INSTALL_FAILL.subCode(),SubResultCode.DATA_INSTALL_FAILL.subMsg());
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description  修改运营商代理区域 数据
     * @date 2020/2/12 17:09
     * @param  * @param OperatorAreaPageQueryParam
     * @return
     */
    @Override
    public ResObject update(OperatorAreaEditParam updateParam) {
        log.info(this.getClass() + "update方法请求参数{}",updateParam);
        if (updateParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        OperatorAreaEntity operatorArea = BeanConvertUtils.copy(updateParam, OperatorAreaEntity.class);
        Boolean result = operatorAreaService.updateById(operatorArea);
        log.info(this.getClass() + "update-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_UPDATE_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_UPDATE_FAILL.subCode(),SubResultCode.DATA_UPDATE_FAILL.subMsg());
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 数组删除运营商代理区域 数据
     * @date 2020/2/12 17:09
     * @param  * @param OperatorAreaPageQueryParam
     * @return
     */
    @Override
    public ResObject deleteByIds(String[] ids) {
        if (ids.length <= 0){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        return R.toRes(operatorAreaService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * *通过id删除运营商代理区域 信息
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
        Boolean result = operatorAreaService.removeById(id);
        log.info(this.getClass() + "deleteById-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_DELETE_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_DELETE_FAILL.subCode(),SubResultCode.DATA_DELETE_FAILL.subMsg());
    }

    /**
     * *导出运营商代理区域 信息
     **/
    @Override
    public ResObject exportXls(OperatorAreaPageQueryParam param, HttpServletResponse response)throws IOException {
        log.info(this.getClass() + "exportXls方法请求参数{}",param);
        QueryWrapper queryWrapper = this.getQueryWrapper(operatorAreaMapStruct, param);
        List<OperatorAreaEntity> list = operatorAreaService.list(queryWrapper);
        List<OperatorAreaVo>operatorAreaList = operatorAreaMapStruct.toVoList(list);
        ExcelUtils.exportExcel(operatorAreaList, OperatorAreaVo.class, "", new ExportParams(), response);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.EXPORT_DATA_SUCCESS.subMsg());
    }

    /**
     * *修改状态
     **/
    @Override
    public ResObject changeStatus(OperatorAreaEditParam param) {
        log.info(this.getClass() + "changeStatus方法请求参数{}",param);
        if (StrUtil.isEmpty(param.getId())){
            log.error("ID数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        OperatorAreaEntity OperatorAreaEntity = new OperatorAreaEntity();
        OperatorAreaEntity.setId(param.getId());
        //OperatorAreaEntity.setStatus(param.getStatus());
        //OperatorAreaEntity.setUpdateTime()
        Boolean result = operatorAreaService.updateById(OperatorAreaEntity);
        log.info(this.getClass() +"changeStatus方法请求对象参数{}，请求结果{}",OperatorAreaEntity,result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_STATUS_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_STATUS_FAILL.subCode(),SubResultCode.DATA_STATUS_FAILL.subMsg());
    }

}

