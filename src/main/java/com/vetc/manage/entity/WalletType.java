package com.vetc.manage.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "WALLET_TYPE")
public class WalletType {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WALLET_TYPE_ID")
  @SequenceGenerator(name = "Wallet_id", sequenceName = "WALLET_TYPE_SEQ", allocationSize = 1)
  @Column(name = "AUTOID", nullable = false)
  private Long id;

  @Column(name = "CODE", nullable = false, length = 100)
  private String code;

  @Column(name = "NAME", nullable = false, length = 200)
  private String name;

  @Column(name = "NOTES", length = 300)
  private String notes;

  @Column(name = "IS_POSTPAID", nullable = false)
  private Boolean isPostpaid = false;

  @Column(name = "IS_PRIMARY_WALLET")
  private Boolean isPrimaryWallet;

}
