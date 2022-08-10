package com.vetc.manage.entity;

import java.math.BigDecimal;
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
@Table(name = "VERIFY_RULE_CONFIG")
public class VerifyRuleConfig {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VERIFY_ID")
  @SequenceGenerator(name = "VERIFY_ID", sequenceName = "VERIFY_RULE_CONFIG_SEQ", allocationSize = 1)
  @Column(name = "AUTOID", nullable = false)
  private Long id;

  @Column(name = "VERIFY_METHOD", length = 300)
  private String verifyMethod;

  @Column(name = "PRODUCT_ID")
  private Long productId;

  @Column(name = "CATE_ID")
  private Long cateId;

  @Column(name = "MIN_AMOUNT", precision = 19, scale = 2)
  private BigDecimal minAmount;

  @Column(name = "MAX_AMOUNT", precision = 19, scale = 2)
  private BigDecimal maxAmount;

  @Column(name = "CUST_GROUP_CODE", length = 50)
  private String custGroupCode;

  @Column(name = "CUST_TYPE", length = 50)
  private String custType;

  @Column(name = "NOTES", length = 500)
  private String notes;

  @Column(name = "CUST_ID")
  private Long custId;

}
