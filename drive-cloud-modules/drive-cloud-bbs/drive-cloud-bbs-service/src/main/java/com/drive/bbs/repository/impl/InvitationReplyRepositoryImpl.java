package com.drive.bbs.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.drive.bbs.pojo.entity.InvitationReplyEntity;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.drive.common.core.base.BaseController;
import com.drive.bbs.repository.InvitationReplyRepository;
import com.drive.bbs.pojo.entity.*;
import com.drive.bbs.pojo.vo.*;
import com.drive.bbs.pojo.dto.*;
import com.drive.bbs.service.mapstruct.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.hutool.core.util.StrUtil;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.SubResultCode;
import com.drive.common.core.utils.BeanConvertUtils;
import lombok.extern.slf4j.Slf4j;
import com.drive.common.core.biz.ResObject;
import org.springframework.beans.factory.annotation.Autowired;
import com.drive.bbs.service.InvitationReplyService;
import com.drive.common.data.utils.ExcelUtils;
import java.util.List;
import java.util.Arrays;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

                                                            
/**
 *
 * 论坛帖子回复表 服务类
 *
 * @author xiaoguo
 */
@Slf4j
@Service
public class  InvitationReplyRepositoryImpl extends BaseController<InvitationReplyPageQueryParam, InvitationReplyEntity>  implements InvitationReplyRepository{

    //  论坛帖子回复表 服务
    @Autowired
    private InvitationReplyService invitationReplyService;
    //  论坛帖子回复表 DO-DTO转化
    @Autowired
    private InvitationReplyMapStruct invitationReplyMapStruct;

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 论坛帖子回复表 分页列表
     * @date 2020/2/12 17:09
     * @param  * @param InvitationReplyPageQueryParam
     * @return
     */
    @Override
    public ResObject pageList(InvitationReplyPageQueryParam param) {
        log.info(this.getClass() + "pageList-方法请求参数{}",param);
        Page<InvitationReplyEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        // 条件查询
        QueryWrapper queryWrapper = this.getQueryWrapper(invitationReplyMapStruct, param);

        //  模糊查询
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueTextContentSearch()),"text_content",param.getVagueTextContentSearch());
        //  开始时间 结束时间都有才进入
        if (StrUtil.isNotEmpty(param.getBeginTime()) && StrUtil.isNotEmpty(param.getEndTime())){
            queryWrapper.between(StrUtil.isNotEmpty(param.getBeginTime()),"create_time",param.getBeginTime(),param.getEndTime());
        }
        IPage<InvitationReplyEntity> pageList = invitationReplyService.page(page, queryWrapper);
        if (pageList.getRecords().isEmpty()){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }
        Page<InvitationReplyVo> invitationReplyVoPage = invitationReplyMapStruct.toVoList(pageList);
        log.info(this.getClass() + "pageList-方法请求结果{}",invitationReplyVoPage);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),invitationReplyVoPage);
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 论坛帖子回复表 查询列表
     * @date 2020/2/12 17:09
     * @param  * @param InvitationReplyPageQueryParam
     * @return
     */
    @Override
    public ResObject findList(InvitationReplyPageQueryParam param) {
        log.info(this.getClass() + "findList-方法请求参数{}",param);
        // 这里判断条件进行查询
        QueryWrapper queryWrapper= this.getQueryWrapper(invitationReplyMapStruct, param);
        // 通过名称查询
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueNameSearch()),"name",param.getVagueNameSearch());
        // 如 queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"phone",param.getPhone());
        List<InvitationReplyEntity> invitationReplyList = invitationReplyService.list(queryWrapper);
        if (invitationReplyList.isEmpty()){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),invitationReplyList);
        }
        List<InvitationReplyVo> invitationReplyVoList = invitationReplyMapStruct.toVoList(invitationReplyList);
        log.info(this.getClass() + "findList-方法请求结果{}",invitationReplyVoList);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),invitationReplyVoList);
    }

    /**
    * 对象条件查询返回单条论坛帖子回复表数据
    * @param param
    * @return
    */
    @Override
    public ResObject getInfo(InvitationReplyPageQueryParam param) {
        log.info(this.getClass() + "getInfo-方法请求参数{}",param);
        if (param == null){
            return R.failure("数据空");
        }
        // 这里判断条件进行查询
        QueryWrapper queryWrapper = this.getQueryWrapper(invitationReplyMapStruct, param);
        // 这里有且只返回一条数据
        queryWrapper.last("limit 1");
        InvitationReplyEntity invitationReply = invitationReplyService.getOne(queryWrapper);
        if (invitationReply == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),invitationReply);
        }
        InvitationReplyVo invitationReplyVo = BeanConvertUtils.copy(invitationReply, InvitationReplyVo.class);
        log.info(this.getClass() + "getInfo-方法请求结果{}",invitationReplyVo);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),invitationReplyVo);
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 通过ID获取 论坛帖子回复表 单条数据
     * @date 2020/2/12 17:09
     * @param  * @param InvitationReplyPageQueryParam
     * @return
     */
    @Override
    public ResObject getById(String id) {
        log.info(this.getClass() + "getById-方法请求参数{}",id);
        if (StrUtil.isEmpty(id)){
            return R.failure("数据空");
        }
        // 通过ID 获取 单条数据
        InvitationReplyEntity invitationReply = invitationReplyService.getById(id);
        if (invitationReply == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),invitationReply);
        }
        InvitationReplyVo invitationReplyVo = BeanConvertUtils.copy(invitationReply, InvitationReplyVo.class);
        log.info(this.getClass() + "getById-方法请求结果{}",invitationReplyVo);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),invitationReplyVo);
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 保存论坛帖子回复表 数据
     * @date 2020/2/12 17:09
     * @param  * @param InvitationReplyPageQueryParam
     * @return
     */
    @Override
    public ResObject save(InvitationReplyInstallParam installParam) {
        log.info(this.getClass() + "save方法请求参数{}",installParam);
        if (installParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        InvitationReplyEntity invitationReply = BeanConvertUtils.copy(installParam, InvitationReplyEntity.class);
        Boolean result = invitationReplyService.save(invitationReply);
        log.info(this.getClass() + "save-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_INSTALL_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_INSTALL_FAILL.subCode(),SubResultCode.DATA_INSTALL_FAILL.subMsg());
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description  修改论坛帖子回复表 数据
     * @date 2020/2/12 17:09
     * @param  * @param InvitationReplyPageQueryParam
     * @return
     */
    @Override
    public ResObject update(InvitationReplyEditParam updateParam) {
        log.info(this.getClass() + "update方法请求参数{}",updateParam);
        if (updateParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        InvitationReplyEntity invitationReply = BeanConvertUtils.copy(updateParam, InvitationReplyEntity.class);
        Boolean result = invitationReplyService.updateById(invitationReply);
        log.info(this.getClass() + "update-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_UPDATE_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_UPDATE_FAILL.subCode(),SubResultCode.DATA_UPDATE_FAILL.subMsg());
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 数组删除论坛帖子回复表 数据
     * @date 2020/2/12 17:09
     * @param  * @param InvitationReplyPageQueryParam
     * @return
     */
    @Override
    public ResObject deleteByIds(String[] ids) {
        if (ids.length <= 0){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        return R.toRes(invitationReplyService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * *通过id删除论坛帖子回复表 信息
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
        Boolean result = invitationReplyService.removeById(id);
        log.info(this.getClass() + "deleteById-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_DELETE_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_DELETE_FAILL.subCode(),SubResultCode.DATA_DELETE_FAILL.subMsg());
    }

    /**
     * *导出论坛帖子回复表 信息
     **/
    @Override
    public ResObject exportXls(InvitationReplyPageQueryParam param, HttpServletResponse response)throws IOException {
        log.info(this.getClass() + "exportXls方法请求参数{}",param);
        QueryWrapper queryWrapper = this.getQueryWrapper(invitationReplyMapStruct, param);
        List<InvitationReplyEntity> list = invitationReplyService.list(queryWrapper);
        List<InvitationReplyVo>invitationReplyList = invitationReplyMapStruct.toVoList(list);
        ExcelUtils.exportExcel(invitationReplyList, InvitationReplyVo.class, "", new ExportParams(), response);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.EXPORT_DATA_SUCCESS.subMsg());
    }

    /**
     * *修改状态
     **/
    @Override
    public ResObject changeStatus(InvitationReplyEditParam param) {
        log.info(this.getClass() + "changeStatus方法请求参数{}",param);
        if (StrUtil.isEmpty(param.getId())){
            log.error("ID数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        InvitationReplyEntity invitationReplyEntity = new InvitationReplyEntity();
        invitationReplyEntity.setId(param.getId());
        invitationReplyEntity.setStatus(param.getStatus());
        //InvitationReplyEntity.setUpdateTime()
        Boolean result = invitationReplyService.updateById(invitationReplyEntity);
        log.info(this.getClass() +"changeStatus方法请求对象参数{}，请求结果{}",invitationReplyEntity,result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_STATUS_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_STATUS_FAILL.subCode(),SubResultCode.DATA_STATUS_FAILL.subMsg());
    }

}

