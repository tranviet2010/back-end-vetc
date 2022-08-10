package com.vetc.manage.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name = "ACCOUNT_LINKS")
public class AccountLink {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACCOUNT_LINK_ID")
  @SequenceGenerator(name = "ACCOUNT_LINK_ID", sequenceName = "ACCOUNT_LINK_SEQ", allocationSize = 1)
  @Column(name = "AUTOID", nullable = false)
  private Integer id;

  @Column(name = "CUST_ID")
  private Integer custId;

  @Column(name = "LINK_VALUE", length = 200)
  private String linkValue;

  @Column(name = "LINK_TYPE", length = 100)
  private String linkType;

  @Column(name = "TOKEN", length = 200)
  private String token;

  @Column(name = "STATUS", length = 1)
  private String status;

  @Column(name = "CREATED_AT")
  private Date createdAt;

  @Column(name = "MODIFIED_AT")
  private Date modifiedAt;

  @Column(name = "UNLINK_AT")
  private Date unlinkAt;

  @Column(name = "REASON", length = 50)
  private String reason;

  @Column(name = "CREATED_BY", length = 50)
  private String createdBy;

  @Column(name = "CARD_OWNER", length = 50)
  private String cardOwner;

  @Column(name = "CARD_OPEN_DATE", length = 10)
  private String cardOpenDate;

  @Column(name = "CARD_NUMBER", length = 50)
  private String cardNumber;

  @Column(name = "TOKEN_ISSUE_DATE", length = 6)
  @Size(max = 6)
  private String tokenIssueDate;

  @Column(name = "TOKEN_EXPIRE_DATE", length = 10)
  @Size(max = 10)
  private String tokenExpireDate;

  @Column(name = "CARD_NUMBER_VIEW", length = 100)
  private String cardNumberView;

}
