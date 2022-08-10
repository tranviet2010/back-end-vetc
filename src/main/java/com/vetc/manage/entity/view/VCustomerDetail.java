package com.vetc.manage.entity.view;

import java.time.LocalDate;
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
@Table(name = "V_CUSTOMER_DETAIL")
public class VCustomerDetail {

  @Column(name = "CUST_NAME", nullable = false, length = 200)
  private String custName;

  @Id
  @Column(name = "CUST_ID", nullable = false)
  private Long custId;

  @Column(name = "MOBI_NUMBER", nullable = false, length = 30)
  private String mobiNumber;

  @Column(name = "EMAIL", length = 200)
  private String email;

  @Column(name = "ID_NO", length = 30)
  private String idNo;

  @Column(name = "GENDER")
  private Integer gender;

  @Column(name = "DOB")
  private Date dob;

  @Column(name = "NATIONALITY", nullable = false, length = 100)
  private String nationality;

  @Column(name = "AVATAR", length = 300)
  private String avatar;

  @Column(name = "ID_ISSUE_DATE")
  private Date idIssueDate;

  @Column(name = "ID_ISSUE_PLACE", length = 500)
  private String idIssuePlace;

  @Column(name = "ADDRESS", length = 500)
  private String address;

  @Column(name = "WALLET_TYPE", length = 200)
  private String walletType;

  @Column(name = "ACCOUNT_NUMBER", nullable = false, length = 30)
  private String accountNumber;

  @Column(name = "BALANCE")
  private Long balance;

  @Column(name = "CREATED_DATE")
  private Date createdDate;

  @Column(name = "HOMETOWN")
  private String homeTown;

  // mat truoc cmnd
  @Transient
  private String idNoFont;

  // mat sau cmnd
  @Transient
  private String idNoBack;




  protected VCustomerDetail() {
  }
}
