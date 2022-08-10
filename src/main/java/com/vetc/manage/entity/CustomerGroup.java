package com.vetc.manage.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "CUSTOMER_GROUP")
public class CustomerGroup {

  @Id
  @Column(name = "AUTOID", nullable = false)
  private Long id;

  @Column(name = "ROOT_ID")
  private Long rootId;

  @Column(name = "PARENT_ID")
  private Long parentId;

  @Column(name = "NAME", nullable = false, length = 300)
  private String name;

  @Column(name = "CODE", nullable = false, length = 500)
  private String code;

  @Column(name = "STATUS", nullable = false, length = 1)
  private String status;

  @Column(name = "NOTES", length = 500)
  private String notes;

  @Column(name = "CREATED_AT", nullable = false)
  private Date createdAt;

  @Column(name = "CREATED_BY", length = 50)
  private String createdBy;

}
