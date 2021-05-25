package com.drive.admin.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.drive.admin.pojo.entity.MessageEntity;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.drive.common.core.base.BaseController;
import com.drive.admin.repository.MessageRepository;
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
import com.drive.admin.service.MessageService;
import com.drive.common.data.utils.ExcelUtils;
import java.util.List;
import java.util.Arrays;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

                                
/**
 *
 * 平台发送短信表 服务类
 *
 * @author xiaoguo
 */
@Slf4j
@Service
public class  MessageRepositoryImpl extends BaseController<MessagePageQueryParam, MessageEntity>  implements MessageRepository{

    //  平台发送短信表 服务
    @Autowired
    private MessageService messageService;
    //  平台发送短信表 DO-DTO转化
    @Autowired
    private MessageMapStruct messageMapStruct;

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 平台发送短信表 分页列表
     * @date 2020/2/12 17:09
     * @param  * @param MessagePageQueryParam
     * @return
     */
    @Override
    public ResObject pageList(MessagePageQueryParam param) {
        log.info(this.getClass() + "pageList-方法请求参数{}",param);
        Page<MessageEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        // 条件查询
        QueryWrapper queryWrapper = this.getQueryWrapper(messageMapStruct, param);

        queryWrapper.like(StrUtil.isNotEmpty(param.getVaguePhoneSearch()),"phone",param.getVaguePhoneSearch());
        //  模糊查询
        //  开始时间 结束时间都有才进入
        if (StrUtil.isNotEmpty(param.getBeginTime()) && StrUtil.isNotEmpty(param.getEndTime())){
            queryWrapper.between(StrUtil.isNotEmpty(param.getBeginTime()),"create_time",param.getBeginTime(),param.getEndTime());
        }
        IPage<MessageEntity> pageList = messageService.pageList(page, queryWrapper);
        if (pageList.getRecords().size() <= 0){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }
        Page<MessageVo> messageVoPage = messageMapStruct.toVoList(pageList);
        log.info(this.getClass() + "pageList-方法请求结果{}",messageVoPage);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),messageVoPage);
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 平台发送短信表 查询列表
     * @date 2020/2/12 17:09
     * @param  * @param MessagePageQueryParam
     * @return
     */
    @Override
    public ResObject findList(MessagePageQueryParam param) {
        log.info(this.getClass() + "findList-方法请求参数{}",param);
        // 这里判断条件进行查询
        QueryWrapper queryWrapper= this.getQueryWrapper(messageMapStruct, param);
        // 通过名称查询
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueNameSearch()),"name",param.getVagueNameSearch());
        // 如 queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"phone",param.getPhone());
        List<MessageEntity> messageList = messageService.list(queryWrapper);
        if (messageList == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),messageList);
        }
        List<MessageVo> messageVoList = messageMapStruct.toVoList(messageList);
        log.info(this.getClass() + "findList-方法请求结果{}",messageVoList);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),messageVoList);
    }

    /**
    * 对象条件查询返回单条平台发送短信表数据
    * @param param
    * @return
    */
    @Override
    public ResObject getInfo(MessagePageQueryParam param) {
        log.info(this.getClass() + "getInfo-方法请求参数{}",param);
        if (param == null){
            return R.failure("数据空");
        }
        // 这里判断条件进行查询
        QueryWrapper queryWrapper = this.getQueryWrapper(messageMapStruct, param);
        // 这里有且只返回一条数据
        queryWrapper.last("limit 1");
        MessageEntity message = messageService.getOne(queryWrapper);
        if (message == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),message);
        }
        MessageVo messageVo = BeanConvertUtils.copy(message, MessageVo.class);
        log.info(this.getClass() + "getInfo-方法请求结果{}",messageVo);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),messageVo);
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 通过ID获取 平台发送短信表 单条数据
     * @date 2020/2/12 17:09
     * @param  * @param MessagePageQueryParam
     * @return
     */
    @Override
    public ResObject getById(String id) {
        log.info(this.getClass() + "getById-方法请求参数{}",id);
        if (StrUtil.isEmpty(id)){
            return R.failure("数据空");
        }
        // 通过ID 获取 单条数据
        MessageEntity message = messageService.getById(id);
        if (message == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),message);
        }
        MessageVo messageVo = BeanConvertUtils.copy(message, MessageVo.class);
        log.info(this.getClass() + "getById-方法请求结果{}",messageVo);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),messageVo);
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 保存平台发送短信表 数据
     * @date 2020/2/12 17:09
     * @param  * @param MessagePageQueryParam
     * @return
     */
    @Override
    public ResObject save(MessageInstallParam installParam) {
        log.info(this.getClass() + "save方法请求参数{}",installParam);
        if (installParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        MessageEntity message = BeanConvertUtils.copy(installParam, MessageEntity.class);
        Boolean result = messageService.save(message);
        log.info(this.getClass() + "save-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_INSTALL_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_INSTALL_FAILL.subCode(),SubResultCode.DATA_INSTALL_FAILL.subMsg());
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description  修改平台发送短信表 数据
     * @date 2020/2/12 17:09
     * @param  * @param MessagePageQueryParam
     * @return
     */
    @Override
    public ResObject update(MessageEditParam updateParam) {
        log.info(this.getClass() + "update方法请求参数{}",updateParam);
        if (updateParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        MessageEntity message = BeanConvertUtils.copy(updateParam, MessageEntity.class);
        Boolean result = messageService.updateById(message);
        log.info(this.getClass() + "update-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_UPDATE_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_UPDATE_FAILL.subCode(),SubResultCode.DATA_UPDATE_FAILL.subMsg());
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 数组删除平台发送短信表 数据
     * @date 2020/2/12 17:09
     * @param  * @param MessagePageQueryParam
     * @return
     */
    @Override
    public ResObject deleteByIds(String[] ids) {
        if (ids.length <= 0){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        return R.toRes(messageService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * *通过id删除平台发送短信表 信息
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
        Boolean result = messageService.removeById(id);
        log.info(this.getClass() + "deleteById-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_DELETE_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_DELETE_FAILL.subCode(),SubResultCode.DATA_DELETE_FAILL.subMsg());
    }

    /**
     * *导出平台发送短信表 信息
     **/
    @Override
    public ResObject exportXls(MessagePageQueryParam param, HttpServletResponse response)throws IOException {
        log.info(this.getClass() + "exportXls方法请求参数{}",param);
        QueryWrapper queryWrapper = this.getQueryWrapper(messageMapStruct, param);
        List<MessageEntity> list = messageService.list(queryWrapper);
        List<MessageVo>messageList = messageMapStruct.toVoList(list);
        ExcelUtils.exportExcel(messageList, MessageVo.class, "", new ExportParams(), response);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.EXPORT_DATA_SUCCESS.subMsg());
    }

    /**
     * *修改状态
     **/
    @Override
    public ResObject changeStatus(MessageEditParam param) {
        log.info(this.getClass() + "changeStatus方法请求参数{}",param);
        if (StrUtil.isEmpty(param.getId())){
            log.error("ID数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        MessageEntity MessageEntity = new MessageEntity();
        MessageEntity.setId(param.getId());
        //MessageEntity.setStatus(param.getStatus());
        //MessageEntity.setUpdateTime()
        Boolean result = messageService.updateById(MessageEntity);
        log.info(this.getClass() +"changeStatus方法请求对象参数{}，请求结果{}",MessageEntity,result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_STATUS_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_STATUS_FAILL.subCode(),SubResultCode.DATA_STATUS_FAILL.subMsg());
    }

}

