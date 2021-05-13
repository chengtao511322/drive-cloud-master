package com.drive.pay.repository.impl;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.common.core.base.BaseController;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.biz.SubResultCode;
import com.drive.common.core.utils.BeanConvertUtils;
import com.drive.common.data.utils.ExcelUtils;
import com.drive.marketing.api.RemoteActivityService;
import com.drive.marketing.pojo.dto.CouponEditParam;
import com.drive.pay.pojo.dto.MentTransactionEditParam;
import com.drive.pay.pojo.dto.MentTransactionInstallParam;
import com.drive.pay.pojo.dto.MentTransactionPageQueryParam;
import com.drive.pay.pojo.entity.MentTransactionEntity;
import com.drive.pay.pojo.vo.MentTransactionVo;
import com.drive.pay.repository.MentTransactionRepository;
import com.drive.pay.service.MentTransactionService;
import com.drive.pay.service.mapstruct.MentTransactionMapStruct;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

                                                                                        
/**
 *
 * 支付交易流水信息表 服务类
 *
 * @author xiaoguo
 */
@Slf4j
@Service
public class  MentTransactionRepositoryImpl extends BaseController<MentTransactionPageQueryParam, MentTransactionEntity>  implements MentTransactionRepository{

    //  支付交易流水信息表 服务
    @Autowired
    private MentTransactionService mentTransactionService;
    //  支付交易流水信息表 DO-DTO转化
    @Autowired
    private MentTransactionMapStruct mentTransactionMapStruct;

    @Autowired
    private RemoteActivityService remoteActivityService;

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 支付交易流水信息表 分页列表
     * @date 2020/2/12 17:09
     * @param  * @param MentTransactionPageQueryParam
     * @return
     */
    @Override
    public ResObject pageList(MentTransactionPageQueryParam param) {
        log.info(this.getClass() + "pageList-方法请求参数{}",param);
        Page<MentTransactionEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        // 条件查询
        QueryWrapper queryWrapper = this.getQueryWrapper(mentTransactionMapStruct, param);

        //  模糊查询
        //queryWrapper.like(StrUtil.isNotEmpty(param.getVagueNameSearch()),"name",param.getVagueNameSearch());
        //  开始时间 结束时间都有才进入
        if (StrUtil.isNotEmpty(param.getBeginTime()) && StrUtil.isNotEmpty(param.getEndTime())){
            queryWrapper.between(StrUtil.isNotEmpty(param.getBeginTime()),"create_time",param.getBeginTime(),param.getEndTime());
        }
        IPage<MentTransactionEntity> pageList = mentTransactionService.page(page, queryWrapper);
        if (pageList.getRecords().size() <= 0){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }
        Page<MentTransactionVo> mentTransactionVoPage = mentTransactionMapStruct.toVoList(pageList);
        log.info(this.getClass() + "pageList-方法请求结果{}",mentTransactionVoPage);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),mentTransactionVoPage);
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 支付交易流水信息表 查询列表
     * @date 2020/2/12 17:09
     * @param  * @param MentTransactionPageQueryParam
     * @return
     */
    @Override
    public ResObject findList(MentTransactionPageQueryParam param) {
        log.info(this.getClass() + "findList-方法请求参数{}",param);
        // 这里判断条件进行查询
        QueryWrapper queryWrapper= this.getQueryWrapper(mentTransactionMapStruct, param);
        // 如 queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"phone",param.getPhone());
        List<MentTransactionEntity> mentTransactionList = mentTransactionService.list(queryWrapper);
        if (mentTransactionList == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),mentTransactionList);
        }
        List<MentTransactionVo> mentTransactionVoList = mentTransactionMapStruct.toVoList(mentTransactionList);
        log.info(this.getClass() + "findList-方法请求结果{}",mentTransactionVoList);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),mentTransactionVoList);
    }

    /**
    * 对象条件查询返回单条支付交易流水信息表数据
    * @param param
    * @return
    */
    @Override
    public ResObject getInfo(MentTransactionPageQueryParam param) {
        log.info(this.getClass() + "getInfo-方法请求参数{}",param);
        if (param == null){
            return R.failure("数据空");
        }
        // 这里判断条件进行查询
        QueryWrapper queryWrapper = this.getQueryWrapper(mentTransactionMapStruct, param);
        MentTransactionEntity mentTransaction = mentTransactionService.getOne(queryWrapper);
        if (mentTransaction == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),mentTransaction);
        }
        MentTransactionVo mentTransactionVo = BeanConvertUtils.copy(mentTransaction, MentTransactionVo.class);
        log.info(this.getClass() + "getInfo-方法请求结果{}",mentTransactionVo);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),mentTransactionVo);
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 通过ID获取 支付交易流水信息表 单条数据
     * @date 2020/2/12 17:09
     * @param  * @param MentTransactionPageQueryParam
     * @return
     */
    @Override
    public ResObject getById(String id) {
        log.info(this.getClass() + "getById-方法请求参数{}",id);
        if (StrUtil.isEmpty(id)){
            return R.failure("数据空");
        }
        // 通过ID 获取 单条数据
        MentTransactionEntity mentTransaction = mentTransactionService.getById(id);
        if (mentTransaction == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),mentTransaction);
        }
        MentTransactionVo mentTransactionVo = BeanConvertUtils.copy(mentTransaction, MentTransactionVo.class);
        log.info(this.getClass() + "getById-方法请求结果{}",mentTransactionVo);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),mentTransactionVo);
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 保存支付交易流水信息表 数据
     * @date 2020/2/12 17:09
     * @param  * @param MentTransactionPageQueryParam
     * @return
     */
    @Override
    public ResObject save(MentTransactionInstallParam installParam) {
        log.info(this.getClass() + "save方法请求参数{}",installParam);
        if (installParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        MentTransactionEntity mentTransaction = BeanConvertUtils.copy(installParam, MentTransactionEntity.class);
        Boolean result = mentTransactionService.save(mentTransaction);
        log.info(this.getClass() + "save-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_INSTALL_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_INSTALL_FAILL.subCode(),SubResultCode.DATA_INSTALL_FAILL.subMsg());
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description  修改支付交易流水信息表 数据
     * @date 2020/2/12 17:09
     * @param  * @param MentTransactionPageQueryParam
     * @return
     */
    @Override
    public ResObject update(MentTransactionEditParam updateParam) {
        log.info(this.getClass() + "update方法请求参数{}",updateParam);
        if (updateParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        MentTransactionEntity mentTransaction = BeanConvertUtils.copy(updateParam, MentTransactionEntity.class);
        Boolean result = mentTransactionService.updateById(mentTransaction);
        log.info(this.getClass() + "update-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_UPDATE_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_UPDATE_FAILL.subCode(),SubResultCode.DATA_UPDATE_FAILL.subMsg());
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 数组删除支付交易流水信息表 数据
     * @date 2020/2/12 17:09
     * @param  * @param MentTransactionPageQueryParam
     * @return
     */
    @Override
    public ResObject deleteByIds(String[] ids) {
        if (ids.length <= 0){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        return R.toRes(mentTransactionService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * *通过id删除支付交易流水信息表 信息
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
        Boolean result = mentTransactionService.removeById(id);
        log.info(this.getClass() + "deleteById-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_DELETE_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_DELETE_FAILL.subCode(),SubResultCode.DATA_DELETE_FAILL.subMsg());
    }

    /**
     * *导出支付交易流水信息表 信息
     **/
    @Override
    public ResObject exportXls(MentTransactionPageQueryParam param, HttpServletResponse response)throws IOException {
        log.info(this.getClass() + "exportXls方法请求参数{}",param);
        QueryWrapper queryWrapper = this.getQueryWrapper(mentTransactionMapStruct, param);
        List<MentTransactionEntity> list = mentTransactionService.list(queryWrapper);
        List<MentTransactionVo>mentTransactionList = mentTransactionMapStruct.toVoList(list);
        ExcelUtils.exportExcel(mentTransactionList, MentTransactionVo.class, "", new ExportParams(), response);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.EXPORT_DATA_SUCCESS.subMsg());
    }

    /**
     * *修改状态
     **/
    @Override
    public ResObject changeStatus(MentTransactionEditParam param) {
        log.info(this.getClass() + "changeStatus方法请求参数{}",param);
        if (StrUtil.isEmpty(param.getId())){
            log.error("ID数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        MentTransactionEntity MentTransactionEntity = new MentTransactionEntity();
        MentTransactionEntity.setId(param.getId());
        //MentTransactionEntity.setStatus(param.getStatus());
        //MentTransactionEntity.setUpdateTime()
        Boolean result = mentTransactionService.updateById(MentTransactionEntity);
        log.info(this.getClass() +"changeStatus方法请求对象参数{}，请求结果{}",MentTransactionEntity,result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_STATUS_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_STATUS_FAILL.subCode(),SubResultCode.DATA_STATUS_FAILL.subMsg());
    }

    @GlobalTransactional
    @Override
    public ResObject transactionRollback(String id) {
        log.info(this.getClass() + "transactionRollback-方法请求参数{}",id);
        MentTransactionEntity MentTransactionEntity = new MentTransactionEntity();
        MentTransactionEntity.setId("1253894818946355201");
        MentTransactionEntity.setBody("测试事务");
        //MentTransactionEntity.setStatus(param.getStatus());
        //MentTransactionEntity.setUpdateTime()
        Boolean result = mentTransactionService.updateById(MentTransactionEntity);
        CouponEditParam couponEditParam = new CouponEditParam();
        couponEditParam.setName("测试事务问题");
        ResObject resObject =remoteActivityService.saveCoupon(couponEditParam);
        log.info("resObject请求结果{}",resObject);
        int i = 1 / Integer.parseInt(id);
        return R.success();
    }
}

