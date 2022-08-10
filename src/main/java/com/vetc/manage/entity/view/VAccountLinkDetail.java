package com.vetc.manage.entity.view;

import java.util.Date;
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
@Table(name = "V_ACCOUNT_LINK_DETAIL")
public class VAccountLinkDetail {

  @Column(name = "CODE_TRANS", nullable = false)
  private Long codeTrans;

  @Column(name = "CODE_TRANS_REFERENCE", nullable = false)
  private Long codeTransReference;

  @Column(name = "CREATED_AT")
  private Date createdAt;

  @Column(name = "LINK_TYPE", length = 100)
  private String linkType;

  @Column(name = "STATUS", length = 1)
  private String status;

  @Column(name = "NAME", nullable = false, length = 200)
  private String name;

  @Column(name = "CARD_NUMBER_VIEW", length = 100)
  private String cardNumberView;

  @Column(name = "CARD_OWNER", length = 50)
  private String cardOwner;

  @Column(name = "CARD_OPEN_DATE", length = 10)
  private String cardOpenDate;

  @Column(name = "WALLET_TYPE", nullable = false)
  private Long walletType;

  @Column(name = "CUST_NAME", nullable = false, length = 200)
  private String custName;

  @Column(name = "STATE", length = 50)
  private String state;

  @Column(name = "CARD_WALLET", nullable = false, length = 30)
  private String cardWallet;

  @Id
  @Column(name = "CUST_ID", nullable = false)
  private Long custId;

  @Transient
  private String walletTypeName;

  protected VAccountLinkDetail() {
  }
}
