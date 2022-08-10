package com.vetc.manage.entity.view;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Data
@Table(name = "V_ACCOUNT_LINK")
public class VAccountLink {

  @Column(name = "CUST_NAME", nullable = false, length = 200)
  private String custName;

  @Column(name = "MOBI_NUMBER", nullable = false, length = 30)
  private String mobiNumber;

  @Column(name = "ID_NO", length = 30)
  private String idNo;

  @Column(name = "LINK_TYPE", length = 100)
  private String linkType;

  @Column(name = "NAME", nullable = false, length = 200)
  private String name;

  @Column(name = "CARD_NUMBER_VIEW", length = 100)
  private String cardNumberView;

  @Column(name = "STATUS", length = 1)
  private String status;

  @Column(name = "CREATED_AT")
  private Date createdAt;

  @Id
  @Column(name = "AUTOID", nullable = false)
  private Long accountLinkId;

  @Column(name = "CUST_ID", nullable = false)
  private Long custId;

  protected VAccountLink() {
  }
}
