package com.drive.admin.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.drive.admin.pojo.entity.CoachTeachTimeEntity;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.drive.common.core.base.BaseController;
import com.drive.admin.repository.CoachTeachTimeRepository;
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
import com.drive.admin.service.CoachTeachTimeService;
import com.drive.common.data.utils.ExcelUtils;
import java.util.List;
import java.util.Arrays;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

                                                                                                                                                        
/**
 *
 * 教练课程时间表 服务类
 *
 * @author guyi
 */
@Slf4j
@Service
public class  CoachTeachTimeRepositoryImpl extends BaseController<CoachTeachTimePageQueryParam, CoachTeachTimeEntity>  implements CoachTeachTimeRepository{

    //  教练课程时间表 服务
    @Autowired
    private CoachTeachTimeService coachTeachTimeService;
    //  教练课程时间表 DO-DTO转化
    @Autowired
    private CoachTeachTimeMapStruct coachTeachTimeMapStruct;

    /*
     *
     *功能描述
     * @author guyi
     * @description 教练课程时间表 分页列表
     * @date 2020/2/12 17:09
     * @param  * @param CoachTeachTimePageQueryParam
     * @return
     */
    @Override
    public ResObject pageList(CoachTeachTimePageQueryParam param) {
        log.info(this.getClass() + "pageList-方法请求参数{}",param);
        Page<CoachTeachTimeEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        // 条件查询
        QueryWrapper queryWrapper = this.getQueryWrapper(coachTeachTimeMapStruct, param);

        //  模糊查询
        //queryWrapper.like(StrUtil.isNotEmpty(param.getVagueNameSearch()),"name",param.getVagueNameSearch());
        //  开始时间 结束时间都有才进入
        if (StrUtil.isNotEmpty(param.getBeginTime()) && StrUtil.isNotEmpty(param.getEndTime())){
            queryWrapper.between(StrUtil.isNotEmpty(param.getBeginTime()),"create_time",param.getBeginTime(),param.getEndTime());
        }
        IPage<CoachTeachTimeEntity> pageList = coachTeachTimeService.page(page, queryWrapper);
        if (pageList.getRecords().size() <= 0){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }
        Page<CoachTeachTimeVo> coachTeachTimeVoPage = coachTeachTimeMapStruct.toVoList(pageList);
        log.info(this.getClass() + "pageList-方法请求结果{}",coachTeachTimeVoPage);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),coachTeachTimeVoPage);
    }

    /*
     *
     *功能描述
     * @author guyi
     * @description 教练课程时间表 查询列表
     * @date 2020/2/12 17:09
     * @param  * @param CoachTeachTimePageQueryParam
     * @return
     */
    @Override
    public ResObject findList(CoachTeachTimePageQueryParam param) {
        log.info(this.getClass() + "findList-方法请求参数{}",param);
        // 这里判断条件进行查询
        QueryWrapper queryWrapper= this.getQueryWrapper(coachTeachTimeMapStruct, param);
        // 如 queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"phone",param.getPhone());
        List<CoachTeachTimeEntity> coachTeachTimeList = coachTeachTimeService.list(queryWrapper);
        if (coachTeachTimeList == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),coachTeachTimeList);
        }
        List<CoachTeachTimeVo> coachTeachTimeVoList = coachTeachTimeMapStruct.toVoList(coachTeachTimeList);
        log.info(this.getClass() + "findList-方法请求结果{}",coachTeachTimeVoList);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),coachTeachTimeVoList);
    }

    /**
    * 对象条件查询返回单条教练课程时间表数据
    * @param param
    * @return
    */
    @Override
    public ResObject getInfo(CoachTeachTimePageQueryParam param) {
        log.info(this.getClass() + "getInfo-方法请求参数{}",param);
        if (param == null){
            return R.failure("数据空");
        }
        // 这里判断条件进行查询
        QueryWrapper queryWrapper = this.getQueryWrapper(coachTeachTimeMapStruct, param);
        CoachTeachTimeEntity coachTeachTime = coachTeachTimeService.getOne(queryWrapper);
        if (coachTeachTime == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),coachTeachTime);
        }
        CoachTeachTimeVo coachTeachTimeVo = BeanConvertUtils.copy(coachTeachTime, CoachTeachTimeVo.class);
        log.info(this.getClass() + "getInfo-方法请求结果{}",coachTeachTimeVo);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),coachTeachTimeVo);
    }

    /*
     *
     *功能描述
     * @author guyi
     * @description 通过ID获取 教练课程时间表 单条数据
     * @date 2020/2/12 17:09
     * @param  * @param CoachTeachTimePageQueryParam
     * @return
     */
    @Override
    public ResObject getById(String id) {
        log.info(this.getClass() + "getById-方法请求参数{}",id);
        if (StrUtil.isEmpty(id)){
            return R.failure("数据空");
        }
        // 通过ID 获取 单条数据
        CoachTeachTimeEntity coachTeachTime = coachTeachTimeService.getById(id);
        if (coachTeachTime == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),coachTeachTime);
        }
        CoachTeachTimeVo coachTeachTimeVo = BeanConvertUtils.copy(coachTeachTime, CoachTeachTimeVo.class);
        log.info(this.getClass() + "getById-方法请求结果{}",coachTeachTimeVo);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),coachTeachTimeVo);
    }

    /*
     *
     *功能描述
     * @author guyi
     * @description 保存教练课程时间表 数据
     * @date 2020/2/12 17:09
     * @param  * @param CoachTeachTimePageQueryParam
     * @return
     */
    @Override
    public ResObject save(CoachTeachTimeInstallParam installParam) {
        log.info(this.getClass() + "save方法请求参数{}",installParam);
        if (installParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        CoachTeachTimeEntity coachTeachTime = BeanConvertUtils.copy(installParam, CoachTeachTimeEntity.class);
        Boolean result = coachTeachTimeService.save(coachTeachTime);
        log.info(this.getClass() + "save-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_INSTALL_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_INSTALL_FAILL.subCode(),SubResultCode.DATA_INSTALL_FAILL.subMsg());
    }

    /*
     *
     *功能描述
     * @author guyi
     * @description  修改教练课程时间表 数据
     * @date 2020/2/12 17:09
     * @param  * @param CoachTeachTimePageQueryParam
     * @return
     */
    @Override
    public ResObject update(CoachTeachTimeEditParam updateParam) {
        log.info(this.getClass() + "update方法请求参数{}",updateParam);
        if (updateParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        CoachTeachTimeEntity coachTeachTime = BeanConvertUtils.copy(updateParam, CoachTeachTimeEntity.class);
        Boolean result = coachTeachTimeService.updateById(coachTeachTime);
        log.info(this.getClass() + "update-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_UPDATE_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_UPDATE_FAILL.subCode(),SubResultCode.DATA_UPDATE_FAILL.subMsg());
    }

    /*
     *
     *功能描述
     * @author guyi
     * @description 数组删除教练课程时间表 数据
     * @date 2020/2/12 17:09
     * @param  * @param CoachTeachTimePageQueryParam
     * @return
     */
    @Override
    public ResObject deleteByIds(String[] ids) {
        if (ids.length <= 0){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        return R.toRes(coachTeachTimeService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * *通过id删除教练课程时间表 信息
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
        Boolean result = coachTeachTimeService.removeById(id);
        log.info(this.getClass() + "deleteById-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_DELETE_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_DELETE_FAILL.subCode(),SubResultCode.DATA_DELETE_FAILL.subMsg());
    }

    /**
     * *导出教练课程时间表 信息
     **/
    @Override
    public ResObject exportXls(CoachTeachTimePageQueryParam param, HttpServletResponse response)throws IOException {
        log.info(this.getClass() + "exportXls方法请求参数{}",param);
        QueryWrapper queryWrapper = this.getQueryWrapper(coachTeachTimeMapStruct, param);
        List<CoachTeachTimeEntity> list = coachTeachTimeService.list(queryWrapper);
        List<CoachTeachTimeVo>coachTeachTimeList = coachTeachTimeMapStruct.toVoList(list);
        ExcelUtils.exportExcel(coachTeachTimeList, CoachTeachTimeVo.class, "", new ExportParams(), response);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.EXPORT_DATA_SUCCESS.subMsg());
    }

    /**
     * *修改状态
     **/
    @Override
    public ResObject changeStatus(CoachTeachTimeEditParam param) {
        log.info(this.getClass() + "changeStatus方法请求参数{}",param);
        if (StrUtil.isEmpty(param.getId())){
            log.error("ID数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        CoachTeachTimeEntity CoachTeachTimeEntity = new CoachTeachTimeEntity();
        CoachTeachTimeEntity.setId(param.getId());
        CoachTeachTimeEntity.setStatus(param.getStatus());
        //CoachTeachTimeEntity.setUpdateTime()
        Boolean result = coachTeachTimeService.updateById(CoachTeachTimeEntity);
        log.info(this.getClass() +"changeStatus方法请求对象参数{}，请求结果{}",CoachTeachTimeEntity,result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_STATUS_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_STATUS_FAILL.subCode(),SubResultCode.DATA_STATUS_FAILL.subMsg());
    }

}

