package com.vetc.manage.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "CUST_GROUP_MAP")
public class CustGroupMap {

  @Id
  @Column(name = "AUTOID", nullable = false)
  private Long id;

  @Column(name = "GROUP_ID", nullable = false)
  private Long groupId;

  @Column(name = "CUST_ID", nullable = false)
  private Long custId;


}
