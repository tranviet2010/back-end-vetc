package com.vetc.manage.dto.transactiondto;

import java.util.Date;
import lombok.Data;

@Data
public class TransactionInternalAndElecFundsTransferDetailDto {

  private String tranferCode;

  private String bankRequestId;

  private String productCode;

  private String stateTrans;

  private Long amount;

  private String cardNo;

  private Date createdAt;

  private Long cardNoWalletFrom;

  private Long cardNoWalletTo;

  private String notes;

  private Long transId;

  private String custNameTo;

  private String custNameFrom;

//  private String stateCust;

  private String remark;

  private Long fee;

  private Long vat;

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
