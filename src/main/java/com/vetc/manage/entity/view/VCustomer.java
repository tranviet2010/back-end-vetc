package com.vetc.manage.entity.view;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Data
@Table(name = "V_CUSTOMER")
public class VCustomer {

  @Column(name = "CUST_NAME", nullable = false, length = 200)
  private String custName;

  @Id
  @Column(name = "CUST_ID", nullable = false)
  private Integer custId;

  @Column(name = "MOBI_NUMBER", nullable = false, length = 30)
  private String mobiNumber;

  @Column(name = "EMAIL", length = 200)
  private String email;

  @Column(name = "ID_NO", length = 30)
  private String idNo;

  @Column(name = "WALLET_TYPE", length = 200)
  private String walletType;

  @Column(name = "CREATED_DATE")
  private Date createdDate;

  @Column(name = "STATE")
  private String state;

  @Column(name = "WALLET_TYPE_ID")
  private String walletTypeId;

  @Transient
  private String stateRef;


  protected VCustomer() {
  }


}
