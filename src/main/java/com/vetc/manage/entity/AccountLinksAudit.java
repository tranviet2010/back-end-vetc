package com.vetc.manage.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ACCOUNT_LINKS_AUDIT")
public class AccountLinksAudit {

  @Id
  @Column(name = "AUTOID", nullable = false)
  private Long id;

  @Column(name = "CUST_ID")
  private Long custId;

  @Column(name = "STATE", length = 20)
  private String state;

  @Column(name = "REQUEST_ID", length = 50)
  private String requestId;

  @Column(name = "RESPONSE_ID", length = 50)
  private String responseId;

  @Column(name = "RESPONSE_STATUS", length = 10)
  private String responseStatus;

  @Column(name = "RESPONSE_MESSAGE", length = 500)
  private String responseMessage;

  @Column(name = "LINK_TYPE", length = 100)
  private String linkType;

  @Column(name = "STATUS", length = 1)
  private String status;

  @Column(name = "CREATED_AT")
  private Date createdAt;

  @Column(name = "MODIFIED_AT")
  private Date modifiedAt;

  @Column(name = "REASON", length = 50)
  private String reason;

  @Column(name = "CREATED_BY", length = 50)
  private String createdBy;

  @Column(name = "BANK_ID", nullable = false)
  private Long bank;

  @Column(name = "CARD_OWNER", length = 50)
  private String cardOwner;

  @Column(name = "CARD_OPEN_DATE", length = 10)
  private String cardOpenDate;

  @Column(name = "CARD_NUMBER", length = 50)
  private String cardNumber;

  @Column(name = "TOKEN", length = 50)
  private String token;

  @Column(name = "CARD_NUMBER_VIEW", length = 100)
  private String cardNumberView;


}
