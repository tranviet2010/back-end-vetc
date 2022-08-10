package com.vetc.manage.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FEE")
public class Fee {

  @Id
  @Column(name = "AUTOID", nullable = false)
  private Long id;

  @Column(name = "PRODUCT_ID", nullable = false)
  private Long product;

  @Column(name = "FEE", nullable = false)
  private Long fee;

  @Column(name = "CREATED_AT", nullable = false)
  private Date createdAt;

  @Column(name = "CREATED_BY", length = 50)
  private String createdBy;

  @Column(name = "STATUS", nullable = false, length = 1)
  private String status;

  @Column(name = "CUST_GROUP_ID")
  private Long custGroupId;

  @Column(name = "CUST_TYPE", length = 100)
  private String custType;

  @Column(name = "MINFEE")
  private Long minfee;

  @Column(name = "MAXFEE")
  private Long maxfee;

  @Column(name = "FEE_TYPE")
  private String feeType;

  @Column(name = "VAT")
  private Long vat;

  @Column(name = "ACTION_CODE", length = 50)
  private String actionCode;

  @Column(name = "CHANNEL", length = 50)
  private String channel;

  @Column(name = "FEE_ORD")
  private Long feeOrd;

}
