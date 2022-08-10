package com.vetc.manage.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "AP_PARAM")
public class ApParam {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AP_PARAM")
  @SequenceGenerator(name = "AP_PARAM", sequenceName = "AP_PARAM_SEQ", allocationSize = 1)
  @Column(name = "AUTOID", nullable = false)
  private Long id;

  @Column(name = "PAR_GROUP", nullable = false, length = 100)
  private String parGroup;

  @Column(name = "PAR_TYPE", nullable = false, length = 100)
  private String parType;

  @Column(name = "PAR_NAME", nullable = false, length = 100)
  private String parName;

  @Column(name = "PAR_VALUE", nullable = false, length = 500)
  private String parValue;

  @Column(name = "DESCRIPTION", length = 300)
  private String description;

}
