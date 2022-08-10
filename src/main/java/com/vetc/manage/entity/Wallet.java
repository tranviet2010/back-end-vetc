package com.vetc.manage.entity;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "WALLET")
public class Wallet {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Wallet_id")
  @SequenceGenerator(name = "Wallet_id", sequenceName = "WALLET_SEQ", allocationSize = 1)
  @Column(name = "WALLET_ID", nullable = false)
  private Long id;

  @Column(name = "BALANCE", nullable = false)
  private Long balance;

  @Column(name = "BLOCK_BALANCE", nullable = false)
  private Long blockBalance;

  @Column(name = "AVAILABLE_BALANCE", nullable = false)
  private Long availableBalance;

  @Column(name = "MINIMUM_BALANCE", nullable = false)
  private Long minimumBalance;

  @Column(name = "LAST_MODIFIED_DATE", nullable = false)
  private Date lastModifiedDate;

  @Column(name = "WALLET_TYPE_CODE", nullable = false)
  private String walletTypeCode;

  @Column(name = "CURRENCY", length = 50)
  private String currency;

  @Column(name = "STATE", length = 50)
  private String state;

  @Column(name = "STATUS", length = 1)
  private String status;

  @Column(name = "CREATED_AT")
  private Date createdAt;

  @Column(name = "WALLET_TYPE", nullable = false)
  private Long walletTypeId;


  @Column(name = "CUST_ID", nullable = false)
  private Long custId;



}
