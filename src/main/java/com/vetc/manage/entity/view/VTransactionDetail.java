package com.vetc.manage.entity.view;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.Immutable;

@Entity
@Data
@Immutable
@Table(name = "V_TRANSACTION_DETAIL")
public class VTransactionDetail {

  @Column(name = "TRANFER_CODE", length = 50)
  private String tranferCode;

  @Column(name = "BANK_REQUEST_ID", length = 100)
  private String bankRequestId;

  @Column(name = "PRODUCT_CODE", length = 50)
  private String productCode;

  @Column(name = "STATE_TRANS", length = 50)
  private String stateTrans;

  @Column(name = "AMOUNT", nullable = false)
  private Long amount;

  @Column(name = "BANK_FEE")
  private Long bankFee;

  @Column(name = "BANK_NAME", length = 200)
  private String bankName;

  @Column(name = "CARD_NO", length = 100)
  private String cardNo;

  @Column(name = "CARD_OWNER_NAME", length = 100)
  private String cardOwnerName;

  @Column(name = "CREATED_AT")
  private Date createdAt;

  @Column(name = "CARD_NO_WALLET_FROM")
  private Long cardNoWalletFrom;

  @Column(name = "CARD_NO_WALLET_TO")
  private Long cardNoWalletTo;

  @Column(name = "NOTES", length = 300)
  private String notes;

  @Id
  @Column(name = "TRANS_ID", nullable = false)
  private Long transId;

  @Column(name = "CUST_NAME_TO", length = 200)
  private String custNameTo;

  @Column(name = "CUST_NAME_FROM", length = 200)
  private String custNameFrom;

  @Column(name = "STATE_CUST", length = 50)
  private String stateCust;

  @Column(name = "REMARK", length = 300)
  private String remark;

  @Column(name = "INVOICE_ID")
  private Long invoiceId;

  @Column(name = "PARTNER")
  private Long partner;

  @Column(name = "FEE")
  private Long fee;

  @Column(name = "VAT")
  private Long vat;

  protected VTransactionDetail() {
  }
}
