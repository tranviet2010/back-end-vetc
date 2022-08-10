package com.vetc.manage.entity.view;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Data
@Table(name = "V_TRANSACTION")
public class VTransaction {

  @Column(name = "TRANFER_CODE", length = 50)
  private String tranferCode;

  @Column(name = "AMOUNT", nullable = false)
  private Long amount;

  //bank name
  @Column(name = "NAME", length = 200)
  private String name;

  // stk bank
  @Column(name = "CARD_NO", length = 100)
  private String cardNo;

  @Column(name = "STATE", length = 50)
  private String state;

  @Column(name = "CREATED_AT")
  private Date createdAt;

  @Column(name = "CARD_NO_WALLET_FROM")
  private Long cardNoWalletFrom;

  @Column(name = "CARD_NO_WALLET_TO")
  private Long cardNoWalletTo;

  @Column(name = "PRODUCT_CODE")
  private String productCode;

  @Column(name = "FEE_ID")
  private Long feeId;

  @Column(name = "FEE")
  private Long fee;

  @Column(name = "INVOICE_ID")
  private Long invoiceId;

  @Column(name = "PARTNER")
  private Long partner;

  @Column(name = "VAT")
  private Long vat;

  @Id
  @Column(name = "TRANS_ID", nullable = false)
  private Long transId;

}
