package com.vetc.manage.dto.transactiondto;

import java.util.Date;
import lombok.Data;

@Data
public class TransactionPayDto {


  private String tranferCode;
  //so tien
  private Long amount;
  //trang thai
  private String state;
  private Date createdAt;
  //ma dich vu
  private String productCode;
  //so hoa don
  private Long invoiceId;
  // stk vi
  private Long cardNoWalletFrom;
  // nha cung cap
  private Long partner;
  // ma giao dich
  private Long transId;
}
