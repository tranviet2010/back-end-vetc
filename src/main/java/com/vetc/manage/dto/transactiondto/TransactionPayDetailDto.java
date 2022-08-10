package com.vetc.manage.dto.transactiondto;

import java.util.Date;
import lombok.Data;

@Data
public class TransactionPayDetailDto {

  private String tranferCode;

  private String productCode;

  private String stateTrans;
  private Long amount;

//  private String cardOwnerName;

  private Date createdAt;

  // so tk vi
  private Long cardNoWalletFrom;

//  private Long cardNoWalletTo;

  //so hao don
  private Long invoiceId;

  // ma giao dich
  private Long transId;

  // ten khach hang
  private String custNameFrom;

  // nha cung cap
  private Long partner;

  // noi dung
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
