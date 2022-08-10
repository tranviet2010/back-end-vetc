package com.vetc.manage.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "FEE_TRANSACTION")
public class FeeTransaction {

  @Id
  @Column(name = "AUTOID", nullable = false)
  private Long id;

  @Column(name = "ACTION_CODE", length = 20)
  private String actionCode;

  @Column(name = "ACCT_NO", length = 50)
  private String acctNo;

  @Column(name = "SWITCH_CODE", length = 50)
  private String switchCode;

  @Column(name = "SWITCH_TYPE", length = 50)
  private String switchType;

  @Column(name = "AMOUNT")
  private Double amount;

  @Column(name = "FEE")
  private Double fee;

  @Column(name = "TRANS_ID")
  private Long transId;

  @Column(name = "STATE")
  private Integer state;

  @Column(name = "FEEMAPID")
  private Long feemapid;

  @Column(name = "FEE_ID")
  private Long feeId;

  @Column(name = "FT_ID", length = 100)
  private String ftId;

  @Column(name = "PRODUCT_ID")
  private Integer productId;

}
