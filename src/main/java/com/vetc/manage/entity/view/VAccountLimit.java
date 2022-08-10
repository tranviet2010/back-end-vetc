package com.vetc.manage.entity.view;

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
@Table(name = "V_ACCOUNT_LIMIT")
public class VAccountLimit {

  @Id
  @Column(name = "ID", nullable = false)
  private String id;

  @Column(name = "PRODUCT_ID", nullable = false)
  private Long productId;

  @Column(name = "CUST_ID")
  private Long custId;
  @Column(name = "CHANNEL", length = 50)
  private String channel;

  @Column(name = "HM_NGAY")
  private Long hmNgay;

  @Column(name = "HM_GG")
  private Long hmGg;

  @Column(name = "HM_THANG")
  private Long hmThang;

  @Transient
  private String nameProductTypeRef;

  protected VAccountLimit() {
  }
}
