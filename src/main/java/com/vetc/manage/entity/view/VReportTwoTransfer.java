package com.vetc.manage.entity.view;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Data;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Data
@Table(name = "V_REPORT_TWO_TRANSFER")
public class VReportTwoTransfer {

  @Id
  @Column(name = "TRANS_DATE", length = 10)
  private String transDate;

  @Column(name = "AMOUNT_DEPOSIT")
  private Long amountDeposit;

  @Column(name = "SUM_DEPOSIT")
  private Long sumDeposit;

  @Column(name = "FEE_DEPOSIT")
  private Long feeDeposit;

  @Column(name = "AMOUNT_WITHDRAW")
  private Long amountWithdraw;

  @Column(name = "SUM_WITHDRAW")
  private Long sumWithdraw;

  @Column(name = "FEE_WITHDRAW")
  private Long feeWithdraw;

  @Transient
  private Long sumDepositTransfer;

  @Transient
  private Long sumWithDrawTransfer;

  @Transient
  private Long debtDifference;



  protected VReportTwoTransfer() {
  }
}
