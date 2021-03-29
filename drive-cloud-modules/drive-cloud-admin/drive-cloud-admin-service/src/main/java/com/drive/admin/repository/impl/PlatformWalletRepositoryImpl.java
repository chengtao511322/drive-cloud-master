package com.drive.admin.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.drive.admin.enums.StudyEnrollEnum;
import com.drive.admin.pojo.entity.PlatformWalletEntity;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.drive.admin.service.AccountFlowDetailService;
import com.drive.admin.service.AccountFlowService;
import com.drive.admin.service.PlatformWalletDetailService;
import com.drive.common.core.base.BaseController;
import com.drive.admin.repository.PlatformWalletRepository;
import com.drive.admin.pojo.entity.*;
import com.drive.admin.pojo.vo.*;
import com.drive.admin.pojo.dto.*;
import com.drive.admin.service.mapstruct.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.hutool.core.util.StrUtil;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.SubResultCode;
import com.drive.common.core.exception.BizException;
import com.drive.common.core.utils.BeanConvertUtils;
import lombok.extern.slf4j.Slf4j;
import com.drive.common.core.biz.ResObject;
import org.springframework.beans.factory.annotation.Autowired;
import com.drive.admin.service.PlatformWalletService;
import com.drive.common.data.utils.ExcelUtils;
import java.util.List;
import java.util.Arrays;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * 教练钱包表 服务类
 *
 * @author xiaoguo
 */
@Slf4j
@Service
public class  PlatformWalletRepositoryImpl extends BaseController<PlatformWalletPageQueryParam, PlatformWalletEntity>  implements PlatformWalletRepository{

    //  教练钱包表 服务
    @Autowired
    private PlatformWalletService platformWalletService;

    @Autowired
    private PlatformWalletDetailService platformWalletDetailService;

    //  教练钱包表 DO-DTO转化
    @Autowired
    private PlatformWalletMapStruct platformWalletMapStruct;

    @Autowired
    private AccountFlowDetailService accountFlowDetailService;

    @Autowired
    private AccountFlowService accountFlowService;

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 教练钱包表 分页列表
     * @date 2020/2/12 17:09
     * @param  * @param PlatformWalletPageQueryParam
     * @return
     */
    @Override
    public ResObject pageList(PlatformWalletPageQueryParam param) {
        log.info(this.getClass() + "pageList-方法请求参数{}",param);
        Page<PlatformWalletEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        // 条件查询
        QueryWrapper queryWrapper = this.getQueryWrapper(platformWalletMapStruct, param);

        //  模糊查询
        //queryWrapper.like(StrUtil.isNotEmpty(param.getVagueNameSearch()),"name",param.getVagueNameSearch());
        //  开始时间 结束时间都有才进入
        if (StrUtil.isNotEmpty(param.getBeginTime()) && StrUtil.isNotEmpty(param.getEndTime())){
            queryWrapper.between(StrUtil.isNotEmpty(param.getBeginTime()),"create_time",param.getBeginTime(),param.getEndTime());
        }
        IPage<PlatformWalletEntity> pageList = platformWalletService.page(page, queryWrapper);
        if (pageList.getRecords().size() <= 0){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }
        Page<PlatformWalletVo> platformWalletVoPage = platformWalletMapStruct.toVoList(pageList);
        log.info(this.getClass() + "pageList-方法请求结果{}",platformWalletVoPage);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),platformWalletVoPage);
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 教练钱包表 查询列表
     * @date 2020/2/12 17:09
     * @param  * @param PlatformWalletPageQueryParam
     * @return
     */
    @Override
    public ResObject findList(PlatformWalletPageQueryParam param) {
        log.info(this.getClass() + "findList-方法请求参数{}",param);
        // 这里判断条件进行查询
        QueryWrapper queryWrapper= this.getQueryWrapper(platformWalletMapStruct, param);
        // 如 queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"phone",param.getPhone());
        List<PlatformWalletEntity> platformWalletList = platformWalletService.list(queryWrapper);
        if (platformWalletList == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),platformWalletList);
        }
        List<PlatformWalletVo> platformWalletVoList = platformWalletMapStruct.toVoList(platformWalletList);
        log.info(this.getClass() + "findList-方法请求结果{}",platformWalletVoList);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),platformWalletVoList);
    }

    /**
    * 对象条件查询返回单条教练钱包表数据
    * @param param
    * @return
    */
    @Override
    public ResObject getInfo(PlatformWalletPageQueryParam param) {
        log.info(this.getClass() + "getInfo-方法请求参数{}",param);
        if (param == null){
            return R.failure("数据空");
        }
        // 这里判断条件进行查询
        QueryWrapper queryWrapper = this.getQueryWrapper(platformWalletMapStruct, param);
        PlatformWalletEntity platformWallet = platformWalletService.getOne(queryWrapper);
        if (platformWallet == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),platformWallet);
        }
        PlatformWalletVo platformWalletVo = BeanConvertUtils.copy(platformWallet, PlatformWalletVo.class);
        log.info(this.getClass() + "getInfo-方法请求结果{}",platformWalletVo);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),platformWalletVo);
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 通过ID获取 教练钱包表 单条数据
     * @date 2020/2/12 17:09
     * @param  * @param PlatformWalletPageQueryParam
     * @return
     */
    @Override
    public ResObject getById(String id) {
        log.info(this.getClass() + "getById-方法请求参数{}",id);
        if (StrUtil.isEmpty(id)){
            return R.failure("数据空");
        }
        // 通过ID 获取 单条数据
        PlatformWalletEntity platformWallet = platformWalletService.getById(id);
        if (platformWallet == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),platformWallet);
        }
        PlatformWalletVo platformWalletVo = BeanConvertUtils.copy(platformWallet, PlatformWalletVo.class);
        log.info(this.getClass() + "getById-方法请求结果{}",platformWalletVo);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),platformWalletVo);
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 保存教练钱包表 数据
     * @date 2020/2/12 17:09
     * @param  * @param PlatformWalletPageQueryParam
     * @return
     */
    @Override
    public ResObject save(PlatformWalletInstallParam installParam) {
        log.info(this.getClass() + "save方法请求参数{}",installParam);
        if (installParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        PlatformWalletEntity platformWallet = BeanConvertUtils.copy(installParam, PlatformWalletEntity.class);
        Boolean result = platformWalletService.save(platformWallet);
        log.info(this.getClass() + "save-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_INSTALL_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_INSTALL_FAILL.subCode(),SubResultCode.DATA_INSTALL_FAILL.subMsg());
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description  修改教练钱包表 数据
     * @date 2020/2/12 17:09
     * @param  * @param PlatformWalletPageQueryParam
     * @return
     */
    @Override
    public ResObject update(PlatformWalletEditParam updateParam) {
        log.info(this.getClass() + "update方法请求参数{}",updateParam);
        if (updateParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        PlatformWalletEntity platformWallet = BeanConvertUtils.copy(updateParam, PlatformWalletEntity.class);
        Boolean result = platformWalletService.updateById(platformWallet);
        log.info(this.getClass() + "update-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_UPDATE_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_UPDATE_FAILL.subCode(),SubResultCode.DATA_UPDATE_FAILL.subMsg());
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 数组删除教练钱包表 数据
     * @date 2020/2/12 17:09
     * @param  * @param PlatformWalletPageQueryParam
     * @return
     */
    @Override
    public ResObject deleteByIds(String[] ids) {
        if (ids.length <= 0){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        return R.toRes(platformWalletService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * *通过id删除教练钱包表 信息
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
        Boolean result = platformWalletService.removeById(id);
        log.info(this.getClass() + "deleteById-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_DELETE_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_DELETE_FAILL.subCode(),SubResultCode.DATA_DELETE_FAILL.subMsg());
    }

    /**
     * *导出教练钱包表 信息
     **/
    @Override
    public ResObject exportXls(PlatformWalletPageQueryParam param, HttpServletResponse response)throws IOException {
        log.info(this.getClass() + "exportXls方法请求参数{}",param);
        QueryWrapper queryWrapper = this.getQueryWrapper(platformWalletMapStruct, param);
        List<PlatformWalletEntity> list = platformWalletService.list(queryWrapper);
        List<PlatformWalletVo>platformWalletList = platformWalletMapStruct.toVoList(list);
        ExcelUtils.exportExcel(platformWalletList, PlatformWalletVo.class, "", new ExportParams(), response);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.EXPORT_DATA_SUCCESS.subMsg());
    }

    /**
     * *修改状态
     **/
    @Override
    public ResObject changeStatus(PlatformWalletEditParam param) {
        log.info(this.getClass() + "changeStatus方法请求参数{}",param);
        if (StrUtil.isEmpty(param.getId())){
            log.error("ID数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        PlatformWalletEntity PlatformWalletEntity = new PlatformWalletEntity();
        PlatformWalletEntity.setId(param.getId());
        //PlatformWalletEntity.setStatus(param.getStatus());
        //PlatformWalletEntity.setUpdateTime()
        Boolean result = platformWalletService.updateById(PlatformWalletEntity);
        log.info(this.getClass() +"changeStatus方法请求对象参数{}，请求结果{}",PlatformWalletEntity,result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_STATUS_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_STATUS_FAILL.subCode(),SubResultCode.DATA_STATUS_FAILL.subMsg());
    }

    @Override
    @Transactional
    public ResObject walletSettle(String orderNo) {
        log.info(this.getClass()+"walletSettle-方法请求参数{}",orderNo);
        if (StrUtil.isEmpty(orderNo)){
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),"订单号不能为空");
        }
        // 订单号查询 钱包明细
        QueryWrapper accountFlowQueryWrapper = new QueryWrapper();
        accountFlowQueryWrapper.eq("order_no",orderNo);
        List<AccountFlowDetailEntity> accountFlowDetailList = accountFlowDetailService.list(accountFlowQueryWrapper);
        if (accountFlowDetailList.size() <=0){
            return R.failure(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        // 开始给各个  钱包结算
        accountFlowDetailList.stream().forEach((item) ->{{
            // //判断该账务明细是否已经结算
            QueryWrapper platformWalletDetailQueryWrapper = new QueryWrapper();
            platformWalletDetailQueryWrapper.eq("account_detail_id",item.getId());
            int count = platformWalletDetailService.count(platformWalletDetailQueryWrapper);
            // 未结算状态才进行结算
            if (count <= 0){
                QueryWrapper platformWalletQueryWrapper = new QueryWrapper();
                platformWalletQueryWrapper.eq("user_id",item.getIncomeUserId());
                PlatformWalletEntity platformWallet = platformWalletService.getOne(platformWalletQueryWrapper);
                if (platformWallet == null) throw new BizException(500,SubResultCode.DATA_UPDATE_FAILL.subCode(),"该账户未设置钱包账户");
                // 相加
                platformWallet.setWalletAmount(platformWallet.getWalletAmount().subtract(item.getItemAmount()));
                Boolean platformWalletResult =platformWalletService.updateById(platformWallet);
                if (!platformWalletResult) throw new BizException(500,SubResultCode.DATA_UPDATE_FAILL.subCode(),SubResultCode.DATA_UPDATE_FAILL.subMsg());
                //记录钱包流水明细
                PlatformWalletDetailEntity platformWalletDetail = new PlatformWalletDetailEntity();
                platformWalletDetail.setUserId(platformWallet.getUserId());
                platformWalletDetail.setTradeAmount(item.getItemAmount());
                // 交易类型（支出）
                platformWalletDetail.setTradeType(StudyEnrollEnum.DRIVER_WALLET_SUBMIT_CASH.getCode());
                if(item.getItemAmount() != null){
                    // 交易类型（收益）
                    platformWalletDetail.setTradeType(StudyEnrollEnum.DRIVER_WALLET_INCOME.getCode());
                }
                platformWalletDetail.setWalletDetailName(item.getItemName());
                platformWalletDetail.setTradeSubject(item.getTradeSubject());  // 交易类型科目
                platformWalletDetail.setTradeSubjectItems(item.getTradeSubjectItems()); //交易类型科目明细
                platformWalletDetail.setAccountDetailId(item.getId());
                platformWalletDetail.setBalance(platformWallet.getWalletAmount()); //余额
                platformWalletDetail.setOperatorId(platformWallet.getOperatorId()); // 运营商id
                Boolean platformWalletDetailResult = platformWalletDetailService.save(platformWalletDetail);
                if (!platformWalletResult) throw new BizException(500,SubResultCode.DATA_UPDATE_FAILL.subCode(),SubResultCode.DATA_UPDATE_FAILL.subMsg());
            }
        }});
        return R.success(SubResultCode.WALLET_SETTLE_SUCCESS.subCode(),SubResultCode.WALLET_SETTLE_SUCCESS.subMsg());
    }
}

