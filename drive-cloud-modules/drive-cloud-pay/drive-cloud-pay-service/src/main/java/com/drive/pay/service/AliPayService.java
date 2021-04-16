package com.drive.pay.service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.domain.AlipayTradeQueryModel;
import com.alipay.api.response.AlipayFundTransUniTransferResponse;
import com.drive.pay.pojo.dto.AliPayParam;
import com.drive.pay.pojo.dto.TransferParams;

/**
 * @Author 小郭
 * @Description //TODO 支付宝支付
 * @Date $ $
 * @Param $
 * @return $
 **/
public interface AliPayService {


    /**
     * 交易查询接口
     * @param model
     * @return
     * @throws AlipayApiException
     */
    boolean isTradeQuery(AlipayTradeQueryModel model) throws AlipayApiException;


    /**
     * app支付
     * @param model
     * @param notifyUrl
     * @return
     * @throws AlipayApiException
     */
    String startAppPay(AlipayTradeAppPayModel model, String notifyUrl)  throws AlipayApiException;

    /**
     * web 支付
     * @param transferParams
     * @return
     * @throws AlipayApiException
     */
    String webAliPay(AliPayParam transferParams)  throws AlipayApiException;

    /**
     *转账接口
     * @param transferParams
     * @return
     * @throws Exception
     */
    AlipayFundTransUniTransferResponse doTransferNew(TransferParams transferParams) throws Exception;
}
