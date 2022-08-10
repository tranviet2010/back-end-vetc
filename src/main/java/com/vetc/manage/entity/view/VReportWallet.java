package com.vetc.manage.entity.view;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Data
@Table(name = "V_REPORT_WALLET")
public class VReportWallet {

  @Id
  @Column(name = "CUS_TYPE", length = 20)
  private String cusType;

  @Column(name = "WALLET_TYPE")
  private Long walletType;

  @Column(name = "PUBLISHED")
  private Long published;

  @Column(name = "ACTIVED")
  private Long actived;

  @Column(name = "ACCOUNT_LINKS")
  private Long accountLinks;

  @Column(name = "AMOUNT")
  private Long amount;

  public String getCusType() {
    return cusType;
  }

  protected VReportWallet() {
  }
}
