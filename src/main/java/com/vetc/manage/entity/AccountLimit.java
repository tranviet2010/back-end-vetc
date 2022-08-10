package com.vetc.manage.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "ACCOUNT_LIMIT")
public class AccountLimit {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACCOUNT_LIMIT_ID")
  @SequenceGenerator(name = "ACCOUNT_LIMIT_ID", sequenceName = "ACCOUNT_LIMIT_SEQ", allocationSize = 1)
  @Column(name = "AUTOID", nullable = false)
  private Long id;

  @Column(name = "WALLET_ID")
  private Long walletId;

  @Column(name = "WALLET_TYPE", length = 100)
  private String walletType;

  @Column(name = "LIMIT_TYPE", length = 50)
  private String limitType;

  @Column(name = "LIMIT_MIN_VALUE", nullable = false, length = 200)
  private String limitMinValue;

  @Column(name = "LIMIT_MAX_VALUE", nullable = false, length = 200)
  private String limitMaxValue;

  @Column(name = "LIMIT_VALUE_TYPE", length = 50)
  private String limitValueType;

  @Column(name = "VALUE", precision = 19, scale = 2)
  private Long value;

  @Column(name = "MODIFIED_AT")
  private Date modifiedAt;

  @Column(name = "CUST_ID")
  private Long custId;

  @Column(name = "CUST_TYPE", length = 20)
  private String custType;

  @Column(name = "CUST_VERIFY_CHANNEL", length = 50)
  private String custVerifyChannel;

  @Column(name = "CUST_VERIFY_STATE")
  private Integer custVerifyState;

  @Column(name = "PRODUCT_ID", nullable = false)
  private Long productId;

  @Column(name = "CREATED_AT", nullable = false)
  private Date createdAt;

  @Column(name = "CREATED_BY", nullable = false, length = 50)
  private String createdBy;

  @Column(name = "STATUS", length = 1)
  private String status;

  @Column(name = "CATE_ID")
  private Long cateId;

  @Column(name = "CHANNEL", length = 50)
  private String channel;

}
