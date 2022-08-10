package com.vetc.manage.dto.transactiondto;

import java.util.Date;
import lombok.Data;

@Data
public class TransactionInternalElecFundsTransferDto {

  private String tranferCode;
  private Long amount;

  private String state;

  private Date createdAt;

  private Long cardNoWalletFrom;

  private Long cardNoWalletTo;

  private String productCode;

  private Long feeId;
  private Long fee;

  private Long vat;

  private Long transId;

  private Long sum;

  public Long getSum() {
    Long result = new Long(0);
    if(amount != null){
      result = amount;
      if(vat != null) {
        result = result + vat;
      }
      if(fee != null){
        result = result + fee;
      }
    }
    return result;
  }
}
