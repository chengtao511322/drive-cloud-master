package com.drive.admin.pojo.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Author 小郭
 * @Description //TODO
 * @Date $ $ 转账操作数据dto
 * @Param $
 * @return $
 **/
@Data
public class TransferOperationDto implements java.io.Serializable{

    /**
     * 转账金额
     */
    private BigDecimal transferAmount;
    /**
     * 转账备注
     */
    private String remark;

    // ID
    private String walletSettlementSummaryId;
    // 转账状态
    private String transferCode;
    // 时间
    private LocalDateTime payDate;

    private String platformWalletDetailId;

    private String userId;
    // 钱包ID
    private String platformWalletId;

    private PlatformWalletEditParam platformWalletEditParam;

    // 清算总金额
    private BigDecimal settleAccountsSum;
}
