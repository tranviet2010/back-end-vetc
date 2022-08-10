package com.vetc.manage.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "PRODUCT")
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCT_ID")
  @SequenceGenerator(name = "PRODUCT_ID", sequenceName = "PRODUCT_SEQ", allocationSize = 1)
  @Column(name = "PRODUCT_ID", nullable = false)
  private Long id;

  @Column(name = "NAME", nullable = false, length = 300)
  private String name;

  @Column(name = "CODE", nullable = false, length = 500)
  private String code;

  @Column(name = "STATUS", nullable = false, length = 1)
  private String status;

  @Column(name = "CAN_VIEW", nullable = false)
  private Boolean canView = false;

  @Column(name = "NOTES", length = 1000)
  private String notes;

  @Column(name = "ICON", length = 500)
  private String icon;

  @Column(name = "CATA_ID", nullable = false)
  private Long cataId;

  @Column(name = "ORD")
  private Integer ord;

}
