package com.vetc.manage.dto.transactiondto;

import java.util.Date;
import lombok.Data;

@Data
public class TransactionDepositAndWithDrawDto {

  private String tranferCode;

  private Long amount;

  private String name;

  private String cardNo;

  private String state;

  private Date createdAt;

  private Long cardNoWalletFrom;

  private String productCode;

  private Long transId;

}
