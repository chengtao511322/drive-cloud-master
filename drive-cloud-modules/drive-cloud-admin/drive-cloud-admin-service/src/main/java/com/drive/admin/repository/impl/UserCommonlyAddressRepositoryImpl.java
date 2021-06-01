package com.drive.admin.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.drive.admin.enums.OperatorEnum;
import com.drive.admin.pojo.entity.UserCommonlyAddressEntity;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.drive.admin.service.*;
import com.drive.admin.util.AdminCacheUtil;
import com.drive.common.core.base.BaseController;
import com.drive.admin.repository.UserCommonlyAddressRepository;
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
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.drive.common.data.utils.ExcelUtils;

import java.util.*;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

                                                        
/**
 *
 * 用户常用地址关联表 服务类
 *
 * @author xiaoguo
 */
@Slf4j
@Service
public class  UserCommonlyAddressRepositoryImpl extends BaseController<UserCommonlyAddressPageQueryParam, UserCommonlyAddressEntity>  implements UserCommonlyAddressRepository{

    //  用户常用地址关联表 服务
    @Autowired
    private UserCommonlyAddressService userCommonlyAddressService;
    //  用户常用地址关联表 DO-DTO转化
    @Autowired
    private UserCommonlyAddressMapStruct userCommonlyAddressMapStruct;

    @Autowired
    private StudentInfoService studentInfoService;

    @Autowired
    private CoachInfoService coachInfoService;

    @Autowired
    private ServiceInfoService serviceInfoService;

    @Autowired
    private CoachingGridService coachingGridService;

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 用户常用地址关联表 分页列表
     * @date 2020/2/12 17:09
     * @param  * @param UserCommonlyAddressPageQueryParam
     * @return
     */
    @Override
    public ResObject pageList(UserCommonlyAddressPageQueryParam param) {
        log.info(this.getClass() + "pageList-方法请求参数{}",param);
        Page<UserCommonlyAddressEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        // 条件查询
        QueryWrapper queryWrapper = this.getQueryWrapper(userCommonlyAddressMapStruct, param);

        //用户名模糊查询
        if(StrUtil.isNotEmpty(param.getVagueUserNameSearch())){
            ArrayList userIdsStr = new ArrayList<>();
            //学员id
            QueryWrapper studentWrapper = new QueryWrapper<>().select("id").like("username", param.getVagueUserNameSearch());
            List<Map<String,Object>> studentUserIds = studentInfoService.listMaps(studentWrapper);
            studentUserIds.stream().forEach((item) -> {
                userIdsStr.addAll(item.values());
            });
            //教练id
            QueryWrapper coachWrapper = new QueryWrapper<>().select("id").like("real_name", param.getVagueUserNameSearch());
            List<Map<String,Object>> coachUserIds = coachInfoService.listMaps(coachWrapper);
            coachUserIds.stream().forEach((item) -> {
                userIdsStr.addAll(item.values());
            });
            //客服id
            QueryWrapper serviceInfoWrapper = new QueryWrapper<>().select("id").like("real_name", param.getVagueUserNameSearch());
            List<Map<String,Object>> serviceInfoIds = serviceInfoService.list(serviceInfoWrapper);
            coachUserIds.stream().forEach((item) -> {
                userIdsStr.addAll(item.values());
            });

            if(userIdsStr != null && userIdsStr.size() > 0){
                queryWrapper.in("user_id",userIdsStr);
            }
        }

        //  开始时间 结束时间都有才进入
        if (StrUtil.isNotEmpty(param.getBeginTime()) && StrUtil.isNotEmpty(param.getEndTime())){
            queryWrapper.between(StrUtil.isNotEmpty(param.getBeginTime()),"create_time",param.getBeginTime(),param.getEndTime());
        }
        IPage<UserCommonlyAddressEntity> pageList = userCommonlyAddressService.page(page, queryWrapper);
        if (pageList.getRecords().size() <= 0){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }
        Page<UserCommonlyAddressVo> userCommonlyAddressVoPage = userCommonlyAddressMapStruct.toVoList(pageList);
        // 循环
        userCommonlyAddressVoPage.getRecords().stream().forEach((item) ->{
            //Optional.ofNullable(item.getUserType()).ifPresent(u ->{item.get(u.getSchoolName())});
           // 学员
            if (item.getUserType().equals(OperatorEnum.STUDENT.getCode())){
                Optional.ofNullable(studentInfoService.getById(item.getUserId())).ifPresent(u ->{item.setUserName(u.getUsername());});
            }
            // 教练
            if (item.getUserType().equals(OperatorEnum.INCOME_USER_TYPE_COACH.getCode())){
                item.setUserName(AdminCacheUtil.getClassName(item.getUserId()));
            }
            // 客服
            if (item.getUserType().equals(OperatorEnum.SERVICE.getCode())){
                item.setUserName(AdminCacheUtil.getServiceRealName(item.getUserId()));
            }
            if (StrUtil.isNotEmpty(item.getSiteId())){
                Optional.ofNullable(coachingGridService.getById(item.getSiteId())).ifPresent(u ->{item.setCoachingGridName(u.getName());});
            }
        });
        log.info(this.getClass() + "pageList-方法请求结果{}",userCommonlyAddressVoPage);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),userCommonlyAddressVoPage);
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 用户常用地址关联表 查询列表
     * @date 2020/2/12 17:09
     * @param  * @param UserCommonlyAddressPageQueryParam
     * @return
     */
    @Override
    public ResObject findList(UserCommonlyAddressPageQueryParam param) {
        log.info(this.getClass() + "findList-方法请求参数{}",param);
        // 这里判断条件进行查询
        QueryWrapper queryWrapper= this.getQueryWrapper(userCommonlyAddressMapStruct, param);
        // 通过名称查询
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueNameSearch()),"name",param.getVagueNameSearch());
        // 如 queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"phone",param.getPhone());
        List<UserCommonlyAddressEntity> userCommonlyAddressList = userCommonlyAddressService.list(queryWrapper);
        if (userCommonlyAddressList == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),userCommonlyAddressList);
        }
        List<UserCommonlyAddressVo> userCommonlyAddressVoList = userCommonlyAddressMapStruct.toVoList(userCommonlyAddressList);
        log.info(this.getClass() + "findList-方法请求结果{}",userCommonlyAddressVoList);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),userCommonlyAddressVoList);
    }

    /**
    * 对象条件查询返回单条用户常用地址关联表数据
    * @param param
    * @return
    */
    @Override
    public ResObject getInfo(UserCommonlyAddressPageQueryParam param) {
        log.info(this.getClass() + "getInfo-方法请求参数{}",param);
        if (param == null){
            return R.failure("数据空");
        }
        // 这里判断条件进行查询
        QueryWrapper queryWrapper = this.getQueryWrapper(userCommonlyAddressMapStruct, param);
        // 这里有且只返回一条数据
        queryWrapper.last("limit 1");
        UserCommonlyAddressEntity userCommonlyAddress = userCommonlyAddressService.getOne(queryWrapper);
        if (userCommonlyAddress == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),userCommonlyAddress);
        }
        UserCommonlyAddressVo userCommonlyAddressVo = BeanConvertUtils.copy(userCommonlyAddress, UserCommonlyAddressVo.class);
        log.info(this.getClass() + "getInfo-方法请求结果{}",userCommonlyAddressVo);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),userCommonlyAddressVo);
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 通过ID获取 用户常用地址关联表 单条数据
     * @date 2020/2/12 17:09
     * @param  * @param UserCommonlyAddressPageQueryParam
     * @return
     */
    @Override
    public ResObject getById(String id) {
        log.info(this.getClass() + "getById-方法请求参数{}",id);
        if (StrUtil.isEmpty(id)){
            return R.failure("数据空");
        }
        // 通过ID 获取 单条数据
        UserCommonlyAddressEntity userCommonlyAddress = userCommonlyAddressService.getById(id);
        if (userCommonlyAddress == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),userCommonlyAddress);
        }
        UserCommonlyAddressVo userCommonlyAddressVo = BeanConvertUtils.copy(userCommonlyAddress, UserCommonlyAddressVo.class);
        // 学员
        if (userCommonlyAddressVo.getUserType().equals(OperatorEnum.STUDENT.getCode())){
            Optional.ofNullable(studentInfoService.getById(userCommonlyAddressVo.getUserId())).ifPresent(u ->{userCommonlyAddressVo.setUserName(u.getUsername());});
        }
        // 教练
        if (userCommonlyAddressVo.getUserType().equals(OperatorEnum.INCOME_USER_TYPE_COACH.getCode())){
            userCommonlyAddressVo.setUserName(AdminCacheUtil.getClassName(userCommonlyAddressVo.getUserId()));
        }
        // 客服
        if (userCommonlyAddressVo.getUserType().equals(OperatorEnum.SERVICE.getCode())){
            userCommonlyAddressVo.setUserName(AdminCacheUtil.getServiceRealName(userCommonlyAddressVo.getUserId()));
        }
        if (StrUtil.isNotEmpty(userCommonlyAddressVo.getSiteId())){
            Optional.ofNullable(coachingGridService.getById(userCommonlyAddressVo.getSiteId())).ifPresent(u ->{userCommonlyAddressVo.setCoachingGridName(u.getName());});
        }
        log.info(this.getClass() + "getById-方法请求结果{}",userCommonlyAddressVo);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),userCommonlyAddressVo);
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 保存用户常用地址关联表 数据
     * @date 2020/2/12 17:09
     * @param  * @param UserCommonlyAddressPageQueryParam
     * @return
     */
    @Override
    public ResObject save(UserCommonlyAddressInstallParam installParam) {
        log.info(this.getClass() + "save方法请求参数{}",installParam);
        if (installParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        UserCommonlyAddressEntity userCommonlyAddress = BeanConvertUtils.copy(installParam, UserCommonlyAddressEntity.class);
        Boolean result = userCommonlyAddressService.save(userCommonlyAddress);
        log.info(this.getClass() + "save-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_INSTALL_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_INSTALL_FAILL.subCode(),SubResultCode.DATA_INSTALL_FAILL.subMsg());
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description  修改用户常用地址关联表 数据
     * @date 2020/2/12 17:09
     * @param  * @param UserCommonlyAddressPageQueryParam
     * @return
     */
    @Override
    public ResObject update(UserCommonlyAddressEditParam updateParam) {
        log.info(this.getClass() + "update方法请求参数{}",updateParam);
        if (updateParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        UserCommonlyAddressEntity userCommonlyAddress = BeanConvertUtils.copy(updateParam, UserCommonlyAddressEntity.class);
        Boolean result = userCommonlyAddressService.updateById(userCommonlyAddress);
        log.info(this.getClass() + "update-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_UPDATE_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_UPDATE_FAILL.subCode(),SubResultCode.DATA_UPDATE_FAILL.subMsg());
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 数组删除用户常用地址关联表 数据
     * @date 2020/2/12 17:09
     * @param  * @param UserCommonlyAddressPageQueryParam
     * @return
     */
    @Override
    public ResObject deleteByIds(String[] ids) {
        if (ids.length <= 0){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        return R.toRes(userCommonlyAddressService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * *通过id删除用户常用地址关联表 信息
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
        Boolean result = userCommonlyAddressService.removeById(id);
        log.info(this.getClass() + "deleteById-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_DELETE_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_DELETE_FAILL.subCode(),SubResultCode.DATA_DELETE_FAILL.subMsg());
    }

    /**
     * *导出用户常用地址关联表 信息
     **/
    @Override
    public ResObject exportXls(UserCommonlyAddressPageQueryParam param, HttpServletResponse response)throws IOException {
        log.info(this.getClass() + "exportXls方法请求参数{}",param);
        QueryWrapper queryWrapper = this.getQueryWrapper(userCommonlyAddressMapStruct, param);
        List<UserCommonlyAddressEntity> list = userCommonlyAddressService.list(queryWrapper);
        List<UserCommonlyAddressVo>userCommonlyAddressList = userCommonlyAddressMapStruct.toVoList(list);
        ExcelUtils.exportExcel(userCommonlyAddressList, UserCommonlyAddressVo.class, "", new ExportParams(), response);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.EXPORT_DATA_SUCCESS.subMsg());
    }

    /**
     * *修改状态
     **/
    @Override
    public ResObject changeStatus(UserCommonlyAddressEditParam param) {
        log.info(this.getClass() + "changeStatus方法请求参数{}",param);
        if (StrUtil.isEmpty(param.getId())){
            log.error("ID数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        UserCommonlyAddressEntity UserCommonlyAddressEntity = new UserCommonlyAddressEntity();
        UserCommonlyAddressEntity.setId(param.getId());
        UserCommonlyAddressEntity.setStatus(param.getStatus());
        //UserCommonlyAddressEntity.setUpdateTime()
        Boolean result = userCommonlyAddressService.updateById(UserCommonlyAddressEntity);
        log.info(this.getClass() +"changeStatus方法请求对象参数{}，请求结果{}",UserCommonlyAddressEntity,result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_STATUS_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_STATUS_FAILL.subCode(),SubResultCode.DATA_STATUS_FAILL.subMsg());
    }

}

