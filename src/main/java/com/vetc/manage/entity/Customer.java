package com.vetc.manage.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.vetc.manage.annotation.DateConstraint;
import com.vetc.manage.annotation.PhoneNumberConstraint;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CUSTOMER")
public class Customer {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUST_ID")
  @SequenceGenerator(name = "CUST_ID", sequenceName = "CUSTOMER_SEQ", allocationSize = 1)
  @Column(name = "CUST_ID", nullable = false)
  private Long id;

  @Column(name = "ID_NO", length = 30)
  private String idNo;

  @Column(name = "CUST_NO", length = 50)
  private String custNo;

  @Column(name = "ID_ISSUE_DATE")
  private Date idIssueDate;

  @Column(name = "ID_ISSUE_PLACE", length = 500)
  private String idIssuePlace;

  @Column(name = "PARENT_ID")
  private Integer parentId;

  @Column(name = "ROOT_ID")
  private Integer rootId;

  @Column(name = "NAME", nullable = false, length = 200)
  private String name;

  @Column(name = "GENDER")
  private Integer gender;

  @Column(name = "DOB")
  private Date dob;

  @Column(name = "NATIONALITY", nullable = false, length = 100)
  private String nationality;

  @Column(name = "PROVINCE", length = 100)
  private String province;

  @Column(name = "DISTRICT", length = 100)
  private String district;

  @Column(name = "PRECINCT", length = 100)
  private String precinct;

  @Column(name = "ADDRESS", length = 500)
  private String address;

  @Column(name = "EMAIL", length = 200)
  @Email
  private String email;

  @PhoneNumberConstraint(message = "Số điện thoại chỉ được nhập số và từ 8-14 ký tự")
  @Column(name = "MOBI_NUMBER", nullable = false, length = 30)
  private String mobiNumber;

  @Column(name = "CREATED_DATE")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
  @DateConstraint(message = "Sai định dạng!")
  private Date createdDate;

  @Column(name = "LAST_MODIFIED_DATE")
  private Date lastModifiedDate;

  @Column(name = "CREATED_BY", length = 50)
  private String createdBy;

  @Column(name = "NAME_SEARCH", length = 100)
  private String nameSearch;

  @Column(name = "STATUS")
  private String status;

  @Column(name = "STATE", length = 50)
  private String state;

  @Column(name = "CUS_TYPE", length = 20)
  private String cusType;

  @Column(name = "RANKING")
  private Long ranking;

  @Column(name = "CUS_KIND")
  private String cusKind;

  @Column(name = "AVATAR", length = 300)
  private String avatar;

  @Column(name = "QRCODE", length = 100)
  private String qrcode;

  @Column(name = "LINK_INVITE", length = 200)
  private String linkInvite;

  @Column(name = "TIN", length = 100)
  private String tin;

  @Column(name = "BUS_PERMIT_NO", length = 100)
  private String busPermitNo;

  @Column(name = "BUS_PERMIT_ISSUE_DATE")
  private Date busPermitIssueDate;

  @Column(name = "REPRE_NAME", length = 100)
  private String repreName;

  @Column(name = "REPRE_MOBI_NUMBER", length = 20)
  private String repreMobiNumber;

  @Column(name = "REPRE_ID_NO", length = 50)
  private String repreIdNo;

  @Column(name = "REPRE_GENDER", length = 20)
  private String repreGender;

  @Column(name = "REPRE_ID_ISSUE_DATE")
  private Date repreIdIssueDate;

  @Column(name = "REPRE_ID_ISSUE_PLACE", length = 300)
  private String repreIdIssuePlace;

  @Column(name = "REPRE_NATIONALITY", length = 100)
  private String repreNationality;

  @Column(name = "REPRE_DOB")
  private Date repreDob;

  @Column(name = "FAX", length = 100)
  private String fax;

  @Column(name = "TIN_EFFECTED_DATE")
  private Date tinEffectedDate;

  @Column(name = "INVOICE_CYCLE_TYPE")
  private Integer invoiceCycleType;

  @Column(name = "INV_CYCLE_EFFECT_DATE")
  private Date invCycleEffectDate;

  @Column(name = "VERIFY_STATE", nullable = false, length = 50)
  private String verifyState;

  @Column(name = "VECT_STATE", length = 50)
  private String vectState;

  @Column(name = "VETC_ACCOUNT", length = 20)
  private String vetcAccount;

  @Column(name = "MAKER_ID")
  private Integer makerId;

  @Column(name = "MAKER_AT")
  private Date makerAt;

  @Column(name = "CHECKER_ID")
  private Integer checkerId;

  @Column(name = "CHECKER_AT")
  private Date checkerAt;

  @Column(name = "HOMETOWN")
  private String homeTown;

}
