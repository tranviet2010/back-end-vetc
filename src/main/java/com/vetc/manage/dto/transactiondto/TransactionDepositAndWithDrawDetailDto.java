package com.vetc.manage.dto.transactiondto;

import java.util.Date;
import lombok.Data;

@Data
public class TransactionDepositAndWithDrawDetailDto {

  private String tranferCode;

  private String bankRequestId;

  private String productCode;

  private String stateTrans;

  private Long amount;

  private Long bankFee;

  private String bankName;

  private String cardNo;

  private String cardOwnerName;

  private Date createdAt;

  private Long cardNoWalletFrom;

  private String notes;

  private Long transId;

  private String custName;

  private String stateCust;
}
