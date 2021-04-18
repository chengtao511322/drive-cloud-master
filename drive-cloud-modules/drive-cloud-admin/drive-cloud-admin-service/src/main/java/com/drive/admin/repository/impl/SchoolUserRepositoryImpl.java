package com.drive.admin.repository.impl;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.admin.pojo.dto.SchoolUserEditParam;
import com.drive.admin.pojo.dto.SchoolUserInstallParam;
import com.drive.admin.pojo.dto.SchoolUserPageQueryParam;
import com.drive.admin.pojo.entity.SchoolUserEntity;
import com.drive.admin.pojo.vo.SchoolUserVo;
import com.drive.admin.repository.SchoolUserRepository;
import com.drive.admin.service.SchoolUserService;
import com.drive.admin.service.mapstruct.SchoolUserMapStruct;
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
 * 合作驾校用户 服务类
 *
 * @author xiaoguo
 */
@Slf4j
@Service
public class  SchoolUserRepositoryImpl extends BaseController<SchoolUserPageQueryParam, SchoolUserEntity>  implements SchoolUserRepository{

    //  合作驾校用户 服务
    @Autowired
    private SchoolUserService schoolUserService;
    //  合作驾校用户 DO-DTO转化
    @Autowired
    private SchoolUserMapStruct schoolUserMapStruct;

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 合作驾校用户 分页列表
     * @date 2020/2/12 17:09
     * @param  * @param SchoolUserPageQueryParam
     * @return
     */
    @Override
    public ResObject pageList(SchoolUserPageQueryParam param) {
        log.info(this.getClass() + "pageList-方法请求参数{}",param);
        Page<SchoolUserEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        // 条件查询
        QueryWrapper queryWrapper = this.getQueryWrapper(schoolUserMapStruct, param);

        //  模糊查询
        //queryWrapper.like(StrUtil.isNotEmpty(param.getVagueNameSearch()),"name",param.getVagueNameSearch());
        //  开始时间 结束时间都有才进入
        if (StrUtil.isNotEmpty(param.getBeginTime()) && StrUtil.isNotEmpty(param.getEndTime())){
            queryWrapper.between(StrUtil.isNotEmpty(param.getBeginTime()),"create_time",param.getBeginTime(),param.getEndTime());
        }
        IPage<SchoolUserEntity> pageList = schoolUserService.page(page, queryWrapper);
        if (pageList.getRecords().size() <= 0){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }
        Page<SchoolUserVo> schoolUserVoPage = schoolUserMapStruct.toVoList(pageList);
        log.info(this.getClass() + "pageList-方法请求结果{}",schoolUserVoPage);
        return R.success(SubResultCode.DATA_SEARCH_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),schoolUserVoPage);
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 合作驾校用户 查询列表
     * @date 2020/2/12 17:09
     * @param  * @param SchoolUserPageQueryParam
     * @return
     */
    @Override
    public ResObject findList(SchoolUserPageQueryParam param) {
        log.info(this.getClass() + "findList-方法请求参数{}",param);
        // 这里判断条件进行查询
        QueryWrapper queryWrapper= this.getQueryWrapper(schoolUserMapStruct, param);
        // 如 queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"phone",param.getPhone());
        List<SchoolUserEntity> schoolUserList = schoolUserService.list(queryWrapper);
        if (schoolUserList == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),schoolUserList);
        }
        List<SchoolUserVo> schoolUserVoList = schoolUserMapStruct.toVoList(schoolUserList);
        log.info(this.getClass() + "findList-方法请求结果{}",schoolUserVoList);
        return R.success(SubResultCode.DATA_SEARCH_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),schoolUserVoList);
    }

    /**
    * 对象条件查询返回单条合作驾校用户数据
    * @param param
    * @return
    */
    @Override
    public ResObject getInfo(SchoolUserPageQueryParam param) {
        log.info(this.getClass() + "getInfo-方法请求参数{}",param);
        if (param == null){
            return R.failure("数据空");
        }
        // 这里判断条件进行查询
        QueryWrapper queryWrapper = this.getQueryWrapper(schoolUserMapStruct, param);
        SchoolUserEntity schoolUser = schoolUserService.getOne(queryWrapper);
        if (schoolUser == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),schoolUser);
        }
        SchoolUserVo schoolUserVo = BeanConvertUtils.copy(schoolUser, SchoolUserVo.class);
        log.info(this.getClass() + "getInfo-方法请求结果{}",schoolUserVo);
        return R.success(SubResultCode.DATA_SEARCH_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),schoolUserVo);
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 通过ID获取 合作驾校用户 单条数据
     * @date 2020/2/12 17:09
     * @param  * @param SchoolUserPageQueryParam
     * @return
     */
    @Override
    public ResObject getById(String id) {
        log.info(this.getClass() + "getById-方法请求参数{}",id);
        if (StrUtil.isEmpty(id)){
            return R.failure("数据空");
        }
        // 通过ID 获取 单条数据
        SchoolUserEntity schoolUser = schoolUserService.getById(id);
        if (schoolUser == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),schoolUser);
        }
        SchoolUserVo schoolUserVo = BeanConvertUtils.copy(schoolUser, SchoolUserVo.class);
        log.info(this.getClass() + "getById-方法请求结果{}",schoolUserVo);
        return R.success(SubResultCode.DATA_SEARCH_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),schoolUserVo);
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 保存合作驾校用户 数据
     * @date 2020/2/12 17:09
     * @param  * @param SchoolUserPageQueryParam
     * @return
     */
    @Override
    public ResObject save(SchoolUserInstallParam installParam) {
        log.info(this.getClass() + "save方法请求参数{}",installParam);
        if (installParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        SchoolUserEntity schoolUser = BeanConvertUtils.copy(installParam, SchoolUserEntity.class);
        Boolean result = schoolUserService.save(schoolUser);
        log.info(this.getClass() + "save-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.DATA_INSTALL_SUCCESS.subCode(),SubResultCode.DATA_INSTALL_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_INSTALL_FAILL.subCode(),SubResultCode.DATA_INSTALL_FAILL.subMsg());
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description  修改合作驾校用户 数据
     * @date 2020/2/12 17:09
     * @param  * @param SchoolUserPageQueryParam
     * @return
     */
    @Override
    public ResObject update(SchoolUserEditParam updateParam) {
        log.info(this.getClass() + "update方法请求参数{}",updateParam);
        if (updateParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        SchoolUserEntity schoolUser = BeanConvertUtils.copy(updateParam, SchoolUserEntity.class);
        Boolean result = schoolUserService.updateById(schoolUser);
        log.info(this.getClass() + "update-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.DATA_UPDATE_SUCCESS.subCode(),SubResultCode.DATA_UPDATE_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_UPDATE_FAILL.subCode(),SubResultCode.DATA_UPDATE_FAILL.subMsg());
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 数组删除合作驾校用户 数据
     * @date 2020/2/12 17:09
     * @param  * @param SchoolUserPageQueryParam
     * @return
     */
    @Override
    public ResObject deleteByIds(String[] ids) {
        if (ids.length <= 0){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        return R.toRes(schoolUserService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * *通过id删除合作驾校用户 信息
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
        Boolean result = schoolUserService.removeById(id);
        log.info(this.getClass() + "deleteById-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.DATA_DELETE_SUCCESS.subCode(),SubResultCode.DATA_DELETE_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_DELETE_FAILL.subCode(),SubResultCode.DATA_DELETE_FAILL.subMsg());
    }

    /**
     * *导出合作驾校用户 信息
     **/
    @Override
    public ResObject exportXls(SchoolUserPageQueryParam param, HttpServletResponse response)throws IOException {
        log.info(this.getClass() + "exportXls方法请求参数{}",param);
        QueryWrapper queryWrapper = this.getQueryWrapper(schoolUserMapStruct, param);
        List<SchoolUserEntity> list = schoolUserService.list(queryWrapper);
        List<SchoolUserVo>schoolUserList = schoolUserMapStruct.toVoList(list);
        ExcelUtils.exportExcel(schoolUserList, SchoolUserVo.class, "", new ExportParams(), response);
        return R.success(SubResultCode.EXPORT_DATA_SUCCESS.subCode(),SubResultCode.EXPORT_DATA_SUCCESS.subMsg());
    }

    /**
     * *修改状态
     **/
    @Override
    public ResObject changeStatus(SchoolUserEditParam param) {
        log.info(this.getClass() + "changeStatus方法请求参数{}",param);
        if (StrUtil.isEmpty(param.getId())){
            log.error("ID数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        SchoolUserEntity SchoolUserEntity = new SchoolUserEntity();
        SchoolUserEntity.setId(param.getId());
        SchoolUserEntity.setStatus(param.getStatus());
        //SchoolUserEntity.setUpdateTime()
        Boolean result = schoolUserService.updateById(SchoolUserEntity);
        log.info(this.getClass() +"changeStatus方法请求对象参数{}，请求结果{}",SchoolUserEntity,result);
        // 判断结果
        return result ?R.success(SubResultCode.DATA_STATUS_SUCCESS.subCode(),SubResultCode.DATA_STATUS_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_STATUS_FAILL.subCode(),SubResultCode.DATA_STATUS_FAILL.subMsg());
    }

}

