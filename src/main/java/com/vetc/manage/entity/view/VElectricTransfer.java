package com.vetc.manage.entity.view;

import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Data
@Table(name = "V_ELECTRIC_TRANSFER")
public class VElectricTransfer {

  @Id
  @Column(name = "TRANSACTION_CODE", nullable = false)
  private Long transactionCode;

  @Column(name = "AMOUNT", nullable = false)
  private Long amount;

  @Column(name = "STATE", length = 50)
  private String state;

  @Column(name = "BANK_ACCOUNT", length = 100)
  private String bankAccount;

  @Column(name = "TRANS_DATETIME", nullable = false)
  private Instant transDatetime;

  @Column(name = "BANK_ID")
  private Long bankId;

  @Column(name = "FEE")
  private Long fee;

  @Column(name = "CARD_OWNER_NAME", length = 100)
  private String cardOwnerName;

  @Column(name = "BANK_NAME", nullable = false, length = 200)
  private String bankName;

  @Column(name = "CUS_NAME", nullable = false, length = 200)
  private String cusName;


  protected VElectricTransfer() {
  }
}