package com.drive.admin.repository.impl;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.admin.pojo.dto.StudentTrainCarApplyEditParam;
import com.drive.admin.pojo.dto.StudentTrainCarApplyInstallParam;
import com.drive.admin.pojo.dto.StudentTrainCarApplyPageQueryParam;
import com.drive.admin.pojo.entity.StudentTrainCarApplyEntity;
import com.drive.admin.pojo.vo.StudentTrainCarApplyVo;
import com.drive.admin.repository.StudentTrainCarApplyRepository;
import com.drive.admin.service.StudentTrainCarApplyService;
import com.drive.admin.service.mapstruct.StudentTrainCarApplyMapStruct;
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
 * 学员学车预约表 服务类
 *
 * @author xiaoguo
 */
@Slf4j
@Service
public class  StudentTrainCarApplyRepositoryImpl extends BaseController<StudentTrainCarApplyPageQueryParam, StudentTrainCarApplyEntity>  implements StudentTrainCarApplyRepository{

    //  学员学车预约表 服务
    @Autowired
    private StudentTrainCarApplyService studentTrainCarApplyService;
    //  学员学车预约表 DO-DTO转化
    @Autowired
    private StudentTrainCarApplyMapStruct studentTrainCarApplyMapStruct;

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 学员学车预约表 分页列表
     * @date 2020/2/12 17:09
     * @param  * @param StudentTrainCarApplyPageQueryParam
     * @return
     */
    @Override
    public ResObject pageList(StudentTrainCarApplyPageQueryParam param) {
        log.info(this.getClass() + "pageList-方法请求参数{}",param);
        Page<StudentTrainCarApplyEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        // 条件查询
        QueryWrapper queryWrapper = this.getQueryWrapper(studentTrainCarApplyMapStruct, param);

        //  模糊查询
        //queryWrapper.like(StrUtil.isNotEmpty(param.getVagueNameSearch()),"name",param.getVagueNameSearch());
        //  开始时间 结束时间都有才进入
        if (StrUtil.isNotEmpty(param.getBeginTime()) && StrUtil.isNotEmpty(param.getEndTime())){
            queryWrapper.between(StrUtil.isNotEmpty(param.getBeginTime()),"create_time",param.getBeginTime(),param.getEndTime());
        }
        IPage<StudentTrainCarApplyEntity> pageList = studentTrainCarApplyService.page(page, queryWrapper);
        if (pageList.getRecords().size() <= 0){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }
        Page<StudentTrainCarApplyVo> studentTrainCarApplyVoPage = studentTrainCarApplyMapStruct.toVoList(pageList);
        log.info(this.getClass() + "pageList-方法请求结果{}",studentTrainCarApplyVoPage);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),studentTrainCarApplyVoPage);
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 学员学车预约表 查询列表
     * @date 2020/2/12 17:09
     * @param  * @param StudentTrainCarApplyPageQueryParam
     * @return
     */
    @Override
    public ResObject findList(StudentTrainCarApplyPageQueryParam param) {
        log.info(this.getClass() + "findList-方法请求参数{}",param);
        // 这里判断条件进行查询
        QueryWrapper queryWrapper= this.getQueryWrapper(studentTrainCarApplyMapStruct, param);
        // 如 queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"phone",param.getPhone());
        List<StudentTrainCarApplyEntity> studentTrainCarApplyList = studentTrainCarApplyService.list(queryWrapper);
        if (studentTrainCarApplyList == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),studentTrainCarApplyList);
        }
        List<StudentTrainCarApplyVo> studentTrainCarApplyVoList = studentTrainCarApplyMapStruct.toVoList(studentTrainCarApplyList);
        log.info(this.getClass() + "findList-方法请求结果{}",studentTrainCarApplyVoList);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),studentTrainCarApplyVoList);
    }

    /**
    * 对象条件查询返回单条学员学车预约表数据
    * @param param
    * @return
    */
    @Override
    public ResObject getInfo(StudentTrainCarApplyPageQueryParam param) {
        log.info(this.getClass() + "getInfo-方法请求参数{}",param);
        if (param == null){
            return R.failure("数据空");
        }
        // 这里判断条件进行查询
        QueryWrapper queryWrapper = this.getQueryWrapper(studentTrainCarApplyMapStruct, param);
        StudentTrainCarApplyEntity studentTrainCarApply = studentTrainCarApplyService.getOne(queryWrapper);
        if (studentTrainCarApply == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),studentTrainCarApply);
        }
        StudentTrainCarApplyVo studentTrainCarApplyVo = BeanConvertUtils.copy(studentTrainCarApply, StudentTrainCarApplyVo.class);
        log.info(this.getClass() + "getInfo-方法请求结果{}",studentTrainCarApplyVo);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),studentTrainCarApplyVo);
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 通过ID获取 学员学车预约表 单条数据
     * @date 2020/2/12 17:09
     * @param  * @param StudentTrainCarApplyPageQueryParam
     * @return
     */
    @Override
    public ResObject getById(String id) {
        log.info(this.getClass() + "getById-方法请求参数{}",id);
        if (StrUtil.isEmpty(id)){
            return R.failure("数据空");
        }
        // 通过ID 获取 单条数据
        StudentTrainCarApplyEntity studentTrainCarApply = studentTrainCarApplyService.getById(id);
        if (studentTrainCarApply == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),studentTrainCarApply);
        }
        StudentTrainCarApplyVo studentTrainCarApplyVo = BeanConvertUtils.copy(studentTrainCarApply, StudentTrainCarApplyVo.class);
        log.info(this.getClass() + "getById-方法请求结果{}",studentTrainCarApplyVo);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),studentTrainCarApplyVo);
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 保存学员学车预约表 数据
     * @date 2020/2/12 17:09
     * @param  * @param StudentTrainCarApplyPageQueryParam
     * @return
     */
    @Override
    public ResObject save(StudentTrainCarApplyInstallParam installParam) {
        log.info(this.getClass() + "save方法请求参数{}",installParam);
        if (installParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        StudentTrainCarApplyEntity studentTrainCarApply = BeanConvertUtils.copy(installParam, StudentTrainCarApplyEntity.class);
        Boolean result = studentTrainCarApplyService.save(studentTrainCarApply);
        log.info(this.getClass() + "save-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_INSTALL_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_INSTALL_FAILL.subCode(),SubResultCode.DATA_INSTALL_FAILL.subMsg());
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description  修改学员学车预约表 数据
     * @date 2020/2/12 17:09
     * @param  * @param StudentTrainCarApplyPageQueryParam
     * @return
     */
    @Override
    public ResObject update(StudentTrainCarApplyEditParam updateParam) {
        log.info(this.getClass() + "update方法请求参数{}",updateParam);
        if (updateParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        StudentTrainCarApplyEntity studentTrainCarApply = BeanConvertUtils.copy(updateParam, StudentTrainCarApplyEntity.class);
        Boolean result = studentTrainCarApplyService.updateById(studentTrainCarApply);
        log.info(this.getClass() + "update-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_UPDATE_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_UPDATE_FAILL.subCode(),SubResultCode.DATA_UPDATE_FAILL.subMsg());
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 数组删除学员学车预约表 数据
     * @date 2020/2/12 17:09
     * @param  * @param StudentTrainCarApplyPageQueryParam
     * @return
     */
    @Override
    public ResObject deleteByIds(String[] ids) {
        if (ids.length <= 0){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        return R.toRes(studentTrainCarApplyService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * *通过id删除学员学车预约表 信息
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
        Boolean result = studentTrainCarApplyService.removeById(id);
        log.info(this.getClass() + "deleteById-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_DELETE_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_DELETE_FAILL.subCode(),SubResultCode.DATA_DELETE_FAILL.subMsg());
    }

    /**
     * *导出学员学车预约表 信息
     **/
    @Override
    public ResObject exportXls(StudentTrainCarApplyPageQueryParam param, HttpServletResponse response)throws IOException {
        log.info(this.getClass() + "exportXls方法请求参数{}",param);
        QueryWrapper queryWrapper = this.getQueryWrapper(studentTrainCarApplyMapStruct, param);
        List<StudentTrainCarApplyEntity> list = studentTrainCarApplyService.list(queryWrapper);
        List<StudentTrainCarApplyVo>studentTrainCarApplyList = studentTrainCarApplyMapStruct.toVoList(list);
        ExcelUtils.exportExcel(studentTrainCarApplyList, StudentTrainCarApplyVo.class, "", new ExportParams(), response);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.EXPORT_DATA_SUCCESS.subMsg());
    }

    /**
     * *修改状态
     **/
    @Override
    public ResObject changeStatus(StudentTrainCarApplyEditParam param) {
        log.info(this.getClass() + "changeStatus方法请求参数{}",param);
        if (StrUtil.isEmpty(param.getTrainApplyNo())){
            log.error("ID数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        StudentTrainCarApplyEntity StudentTrainCarApplyEntity = new StudentTrainCarApplyEntity();
        StudentTrainCarApplyEntity.setTrainApplyNo(param.getTrainApplyNo());
        //StudentTrainCarApplyEntity.setStatus(param.getStatus());
        //StudentTrainCarApplyEntity.setUpdateTime()
        Boolean result = studentTrainCarApplyService.updateById(StudentTrainCarApplyEntity);
        log.info(this.getClass() +"changeStatus方法请求对象参数{}，请求结果{}",StudentTrainCarApplyEntity,result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_STATUS_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_STATUS_FAILL.subCode(),SubResultCode.DATA_STATUS_FAILL.subMsg());
    }

}

