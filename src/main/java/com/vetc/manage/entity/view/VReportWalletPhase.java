package com.vetc.manage.entity.view;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.Immutable;

@Entity
@Data
@Immutable
@Table(name = "V_REPORT_WALLET_PHASE")
public class VReportWalletPhase {

  @Id
  @Column(name = "TRANS_DATE", length = 10)
  private String transDate;

  @Column(name = "AMOUNT_DEPOSIT")
  private Long amountDeposit;

  @Column(name = "SUM_DEPOSIT")
  private Long sumDeposit;

  @Column(name = "AMOUNT_WITHDRAW")
  private Long amountWithdraw;

  @Column(name = "SUM_WITHDRAW")
  private Long sumWithdraw;

  @Column(name = "AMOUNT_ELEC_FUNDS_TRANSFER")
  private Long amountElecFundsTransfer;

  @Column(name = "SUM_ELEC_FUNDS_TRANSFER")
  private Long sumElecFundsTransfer;

  @Column(name = "AMOUNT_TT")
  private Long amountTt;

  @Column(name = "SUM_TT")
  private Long sumTt;

  @Column(name = "AMOUNT_K")
  private Long amountK;

  @Column(name = "SUM_K")
  private Long sumK;

  protected VReportWalletPhase() {
  }
}
