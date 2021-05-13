package com.drive.pay.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.domain.AlipayTradeQueryModel;
import com.alipay.api.request.AlipayFundTransUniTransferRequest;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayFundTransUniTransferResponse;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.drive.pay.pojo.dto.AliPayParam;
import com.drive.pay.pojo.dto.TransferParams;
import com.drive.pay.pojo.vo.BizContentForUniTransferVo;
import com.drive.pay.pojo.vo.Participant;
import com.drive.pay.service.AliPayService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @Author 小郭
 * @Description //TODO
 * @Date $ $
 * @Param $
 * @return $
 **/
@Slf4j
@Service
public class AliPayServiceImpl implements AliPayService {


    private AlipayClient alipayClient;
    @Override
    public boolean isTradeQuery(AlipayTradeQueryModel model) throws AlipayApiException {
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        request.setBizModel(model);
        AlipayTradeQueryResponse alipayTradeQueryResponse = alipayClient.certificateExecute(request);
        if(alipayTradeQueryResponse.isSuccess()){
            return true;
        }
        return false;
    }

    @Override
    public String startAppPay(AlipayTradeAppPayModel model, String notifyUrl) throws AlipayApiException {
        AlipayTradeAppPayRequest aliPayRequest = new AlipayTradeAppPayRequest();
        model.setProductCode("QUICK_MSECURITY_PAY");
        aliPayRequest.setNotifyUrl(notifyUrl);
        aliPayRequest.setBizModel(model);
        // 这里和普通的接口调用不同，使用的是sdkExecute
        AlipayTradeAppPayResponse aliResponse = alipayClient.sdkExecute(aliPayRequest);
        return aliResponse.getBody();
    }

    @Override
    public String webAliPay(AliPayParam transferParams) throws AlipayApiException {
        log.info(this.getClass() + "webAliPay-方法请求参数{}",transferParams);
        //设置请求参数
        AlipayTradePagePayRequest aliPayRequest = new AlipayTradePagePayRequest();
        //aliPayRequest.setReturnUrl(AlipayConfig.return_url);
        //aliPayRequest.setNotifyUrl(AlipayConfig.notify_url);

        //商户订单号，后台可以写一个工具类生成一个订单号，必填
        String order_number = new String(transferParams.getOrderNo());
        //付款金额，从前台获取，必填
        BigDecimal total_amount = transferParams.getPayAmount();
        //订单名称，必填
        String subject = new String(transferParams.getOrderName());
        aliPayRequest.setBizContent("{\"out_trade_no\":\"" + order_number + "\","
                + "\"total_amount\":\"" + total_amount + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //请求
        String result = null;
        try {
            result = alipayClient.pageExecute(aliPayRequest).getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public AlipayFundTransUniTransferResponse doTransferNew(TransferParams transferParams) throws Exception {
        String title = (StringUtils.isNotBlank(transferParams.getRemark()) ? transferParams
                .getRemark() : "转账");
        //转账请求入参
        AlipayFundTransUniTransferRequest request = new AlipayFundTransUniTransferRequest();
        //转账参数
        BizContentForUniTransferVo bizContent = new BizContentForUniTransferVo();
        bizContent.setOut_biz_no(transferParams.getOutBizNo());
        bizContent.setTrans_amount(transferParams.getAmount());
        bizContent.setProduct_code("TRANS_ACCOUNT_NO_PWD");
        bizContent.setBiz_scene("DIRECT_TRANSFER");
        bizContent.setOrder_title(title);
        Participant participant = new Participant();
        participant.setIdentity(transferParams.getPayeeAccount());
        participant.setIdentity_type(transferParams.getPayeeType());
        participant.setName((StringUtils.isNotBlank(transferParams.getPayeeRealName()) ? transferParams
                .getPayeeRealName() : StringUtils.EMPTY));
        bizContent.setPayee_info(participant);
        bizContent.setRemark(title);
        request.setBizContent(JSON.toJSONString(bizContent));
        //转账请求返回
        AlipayFundTransUniTransferResponse response = null;
        try {
            response = alipayClient.certificateExecute(request);
        } catch (Exception e) {
            log.info("doTransfer exception，异常信息：{}", e.toString());
            log.info("doTransfer exception，支付宝返回信息：{}", JSONObject.toJSONString(response));
        }
        log.info("doTransfer,AlipayFundTransUniTransferResponse:{}", JSONObject.toJSONString(response));
        return response;
    }
}
